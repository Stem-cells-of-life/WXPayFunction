package com.sccbb.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sccbb.Util.HttpRequestMethod;
import com.sccbb.Util.PropertiesUtil;
import com.sccbb.Util.WXPayUtil;

import net.sf.json.JSONObject;


/**
 * 支付主要业务处理方式
 * @author panda2wa
 *
 */
public class GetPayInfoServlet extends HttpServlet {
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
		        String code = req.getParameter("code");
		        String fee =null;//金额
		        HttpSession session = req.getSession();
	        	String username =session.getAttribute("username").toString();
		        String number = session.getAttribute("number").toString();
		        String phone = session.getAttribute("phone").toString();
		        String address = session.getAttribute("address").toString();
		    
		        try{
		        	//spbill_create_ip 获取请求方的ip 
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
		           
		            fee = String.valueOf(Integer.valueOf(number)*218*100); // 卖218一盒 正式代码 单位为分
		            String fee2 = String.valueOf(Integer.valueOf(number)*218);//显示费用
		        	String openid =getOpenId(code);//获取OpenId
		        	String ordernumber = getordernumber();//生成订单号
		        	String prepay_id = getPrepay_id(ip,openid,fee,ordernumber);//获取预支付ID
		        	
		//			session.setAttribute("money", fee);//保存实际支付金额，单位为分
		
		        	Map<String,String>  payMap = getPayMap(prepay_id);
		        	String paySign = getSign(payMap);//获取签名
		        	req.setAttribute("appId", payMap.get("appId"));
		        	req.setAttribute("timeStamp", payMap.get("timeStamp"));
		        	req.setAttribute("nonceStr", payMap.get("nonceStr"));
		        	req.setAttribute("prepayId", payMap.get("package"));
		        	req.setAttribute("signType", payMap.get("signType"));
		        	req.setAttribute("paySign", paySign);
		        	
		        	req.setAttribute("fee", fee2);
		        	req.setAttribute("username",username );
		        	req.setAttribute("number",number );
		        	req.setAttribute("phone",phone );
		        	req.setAttribute("address",address );
		        	req.setAttribute("ordernumber", ordernumber);
		        }catch (Exception e) {
					e.printStackTrace();
				}
		        req.getRequestDispatcher("/pay.jsp").forward(req, resp);
		}
		
		
		//订单号生成
		public static String getordernumber(){
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmssSSS");//当前时间精确到时分秒毫秒
		    String ordernumber = "smymm"+df.format(new Date()).toString().substring(2,16);
			return ordernumber;
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
		
		
		//sign 算法
		public String getSign (Map<String,String> data) throws Exception{
			// api密钥 公众号里面设置的   s1LJrq1rCAI7APV3ZZfUlj1SLEoFjtiQ
			String key =PropertiesUtil.getProperties().getProperty("key").trim();
			String sign = WXPayUtil.generateSignature(data, key);
			return sign;
		}
		
		//获取预支付id
		private String getPrepay_id(String ip, String openid,String fee,String ordernumber) throws Exception {
			String prepay_id = null;
			Map<String,String> hm = new HashMap();
			String appid =PropertiesUtil.getProperties().getProperty("APPID").toString().trim();
			String mch_id =PropertiesUtil.getProperties().getProperty("mch_id").toString().trim();
			String nonce_str = WXPayUtil.generateNonceStr();
			String body = "干细胞面膜";  //注意中文有编码问题 
			String out_trade_no  = ordernumber;//订单号不能重复
			String total_fee  = fee;
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
		
		//获取OpenId
		private static String getOpenId (String code) throws IOException{
			InputStream is = null;
			String openid =null;
			String appid = PropertiesUtil.getProperties().getProperty("APPID").toString().trim();
			String secret = PropertiesUtil.getProperties().getProperty("SECRET").toString().trim();
			try{
						/*测试号
		    			 * appid = wxce126f5a1a10ea21
		    			 * secret = 1cf09e81f1de7fccf2ef61e5ffed7f64
		    			 *  正是号
		    			 *  appid = wxfe7cdb7a0ac5894f
		    			 *  secret = c6a5488b81b958000f103042c5e0bdf3
		    			 */ 
				        URL url = new URL("https://api.weixin.qq.com/sns/oauth2/access_token?" +
				        		"appid=" +appid+
				        		"&secret=" +secret+
				        		"&code="+code +
				        		"&grant_type=authorization_code");
				        HttpURLConnection   con = (HttpURLConnection) url.openConnection();
				        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
				        con.setRequestProperty("Accept-Charset", "UTF-8");  
				        con.setRequestProperty("contentType", "utf-8");  
				        con.setConnectTimeout(50000);    
				        con.setReadTimeout(50000);  
				        con.setDoInput(true);  
		                //默认GET  
				        con.setRequestMethod("GET");
				        is = con.getInputStream();
				        
				        String result = null;  
				        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
			            StringBuffer buffer = new StringBuffer();  
			            String line = "";  
			            while ((line = in.readLine()) != null) {  
			                buffer.append(line);  
			            }  
			          //这个里面有个特殊的access_token 
			            result = buffer.toString();  
			            JSONObject jsonObject = JSONObject.fromObject(result); 
			            openid = jsonObject.get("openid").toString(); 
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				is.close();
			}
			return openid;
		}
				
}
