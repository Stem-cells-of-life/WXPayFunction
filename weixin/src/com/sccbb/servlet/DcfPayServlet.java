package com.sccbb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sccbb.Util.HttpRequestMethod;
import com.sccbb.Util.JsonArray2List;
import com.sccbb.Util.PropertiesUtil;
import com.sccbb.Util.WXPayUtil;

public class DcfPayServlet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
				doPost(req, resp);
		}
		
		
		@Override	
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			resp.setContentType("text/html");  
	        req.setCharacterEncoding("UTF-8");  
	        resp.setCharacterEncoding("UTF-8");  
	        PrintWriter pw = null;
	        String arr = req.getSession().getAttribute("arr").toString();
			String code = req.getParameter("code").toString();
			String total = req.getSession().getAttribute("total").toString();
			List<Map<String,String>> arrlist =JsonArray2List.getJsonListByString(arr);
			String bs_fee_0002 =null;
			try {
	            String ordernumber = getordernumber();//订单号
				for(Map<String, String> msgmap : arrlist){
					if("600".equals(msgmap.get("priceselect"))){
						msgmap.put("payyear", "1");
						bs_fee_0002 = msgmap.get("bs_fee_0002");
						bs_fee_0002 = PropertiesUtil.getnexttime(bs_fee_0002,1);
					}else if("2910".equals(msgmap.get("priceselect"))){
						msgmap.put("payyear", "5");
						bs_fee_0002 = msgmap.get("bs_fee_0002");
						bs_fee_0002 = PropertiesUtil.getnexttime(bs_fee_0002,5);
					}else if("5580".equals(msgmap.get("priceselect"))){
						msgmap.put("payyear", "10");
						bs_fee_0002 = msgmap.get("bs_fee_0002");
						bs_fee_0002 = PropertiesUtil.getnexttime(bs_fee_0002,10);
					}else{
						msgmap.put("payyear", "0");
					}
					msgmap.put("bs_fee_0002", bs_fee_0002);
					msgmap.put("ordernumber", ordernumber);
				}
				req.setAttribute("arrList", arrlist);
				req.setAttribute("total", total);
				//把新的list存到session中去，覆盖老的arr
				req.getSession().setAttribute("arr", arrlist);

//        	spbill_create_ip 获取请求方的ip
	            String ip  =req.getHeader("x-forwarded-for");
	            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){  
	                ip = req.getHeader("Proxy-Client-IP");  
	            }  
	            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){  
	                ip = req.getHeader("WL-Proxy-Client-IP");  
	            }  
	            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){  
	                ip = req.getRemoteAddr();  
	            }  
	            if(ip.indexOf(",")!=-1){  
	                String[] ips = ip.split(",");  
	                ip = ips[0].trim();  
	            } 
	           
	            
	            String openid = PropertiesUtil.getOpenId(code);//获取openid
	            total = String.valueOf(Integer.valueOf(total)*100);//以分为单位
				String prepay_id = getPrepay_id(ip, openid, total, ordernumber);//获取预支付ID
				Map<String,String>  payMap = getPayMap(prepay_id);
				String paySign = getSign(payMap);//获取签名
				
				req.setAttribute("appId", payMap.get("appId"));
	        	req.setAttribute("timeStamp", payMap.get("timeStamp"));
	        	req.setAttribute("nonceStr", payMap.get("nonceStr"));
	        	req.setAttribute("prepayId", payMap.get("package"));
	        	req.setAttribute("signType", payMap.get("signType"));
	        	req.setAttribute("paySign", paySign);
				
				} catch (Exception e) {
					e.printStackTrace();
				
			}
            req.getRequestDispatcher("/dcfmsg.jsp").forward(req, resp);
		}
		
		//订单号生成
		public static String getordernumber(){
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmssSSS");//当前时间精确到时分秒毫秒
		    String ordernumber = "smydcf"+df.format(new Date()).toString().substring(2,16);
			return ordernumber;
		}
		
		//获取预支付id
		private String getPrepay_id(String ip, String openid,String fee,String ordernumber) throws Exception {
			String prepay_id = null;
			Map<String,String> hm = new HashMap();
			String appid =PropertiesUtil.getProperties().getProperty("APPID").toString().trim();
			String mch_id =PropertiesUtil.getProperties().getProperty("mch_id").toString().trim();
			String nonce_str = WXPayUtil.generateNonceStr();
			String body = "冻存费续缴";  //注意中文有编码问题 
			String out_trade_no  = ordernumber;//订单号不能重复
		//	String total_fee  = fee;
			String total_fee  = "1"; //1分钱,测试用
			String notify_url ="203624vk44.iok.la/weixin/recellpay.do";//支付完成后的回调
			String trade_type  ="JSAPI"; //支付类型
		//	String sign_type ="MD5";   //如果有问题，就把这个加上去
			hm.put("appid",appid );
			hm.put("mch_id",mch_id );
			hm.put("nonce_str",nonce_str );
			hm.put("body",body );
			hm.put("out_trade_no",out_trade_no );
			hm.put("total_fee",total_fee );
			hm.put("notify_url",notify_url );
			hm.put("trade_type",trade_type );
			hm.put("spbill_create_ip", ip);
			hm.put("openid", openid);
		//	hm.put("sign_type", sign_type);
			String sign = getSign(hm); //获取这个sign
			hm.put("sign", sign);	   //把这个sign装进hm
			
			String xml = WXPayUtil.mapToXml(hm);//把封装好的map转成xml 微信提供的方法 
		    
		    //统一下单的接口 https://api.mch.weixin.qq.com/pay/unifiedorder  
		    String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		    //预支付id:prepay_id  
		    String xmlStr = HttpRequestMethod.sendPost(unifiedorder_url, xml);
		    if (xmlStr.indexOf("SUCCESS") != -1) {    
		        Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);    
		        prepay_id = (String) map.get("prepay_id");    
		    }  
			return prepay_id;
		
		}
		
		
		//sign 算法
		public String getSign (Map<String,String> data) throws Exception{
			String key =PropertiesUtil.getProperties().getProperty("key").trim();
			String sign = WXPayUtil.generateSignature(data, key);
			return sign;
		}
		
		//得到一个组装的用来获取paySign值得MAP   5个参数
		public Map<String,String> getPayMap (String prepay_id){
			Map<String,String> map = new HashMap<String, String>();
			map.put("appId", PropertiesUtil.getProperties().getProperty("APPID").toString().trim());//appId "I" 是大写
			map.put("timeStamp", WXPayUtil.getCurrentTimestamp()+"");//大写timeStamp
			map.put("nonceStr", WXPayUtil.generateNonceStr());
			map.put("package", "prepay_id="+prepay_id);  //我的妈呀，这个key值必须叫"package",不然要影响签名算法!!
			map.put("signType",  "MD5");
			return map;
		}
}
