package com.sccbb.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sccbb.Util.WXPayUtil;
import com.sccbb.dao.GetConnection;



/**
 * 微信支付成功后回调方法，可以用于验证支付金额是否正确
 * 
 * @author panda2wa
 *
 */
public class ReCellPay extends HttpServlet {
				@Override
				protected void doGet(HttpServletRequest req, HttpServletResponse resp)
						throws ServletException, IOException {
						doPost(req, resp);
				}
				
				@Override
				protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
						InputStream is = null; 
						String resultxml = null;  
					    StringBuffer buffer = new StringBuffer();  
					    String line = "";  
					    Map hm = new HashMap();
					    //获取到实际缴费金额
						try{
							is = req.getInputStream();//回调流
					        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
					        while ((line = in.readLine()) != null) {  
				                buffer.append(line);  
				            }
					        resultxml = buffer.toString(); 
					        hm= WXPayUtil.xmlToMap(resultxml);
					        System.out.println(hm);
					        //out_trade_no 订单号 onumber
					        //transaction_id 交易单号
					        //total_fee 金额 单位分
					        //is_subscribe 是否关注公众号
					        //time_end 交易时间
					        //fee_type 货币种类
					        // bank_type 付款银行
					        if("SUCCESS".equals(hm.get("result_code").toString())){
					        		insertRewxpayinfo(hm);
					        }else{
					        	System.out.println("支付失败");
					        }
					        //告诉微信服务器成功收到回调，不然他会一直访问这个ReCellPay。。。。
							resp.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							is.close();
						}
			}
				
				
			public void insertRewxpayinfo(Map map){
				Connection conn = GetConnection.getConnection();
				PreparedStatement ps =null;
				try{
					String sql ="insert into REWXPAYINFO " +
											"(id,ordernumber,transaction_id,is_subscribe,total_fee," +
											"time_end,fee_type,bank_type,cash_fee,openid,isexpress,isvalid)" +
											"values (SEQ_REWXPAYINFO.nextval,?,?,?,?,?,?,?,?,?,?,?)";
					ps=conn.prepareStatement(sql);
					ps.setString(1, map.get("out_trade_no").toString());
					ps.setString(2, map.get("transaction_id").toString());
					ps.setString(3, map.get("is_subscribe").toString());
					ps.setString(4, map.get("total_fee").toString());
					ps.setString(5, map.get("time_end").toString());
					ps.setString(6, map.get("fee_type").toString());
					ps.setString(7, map.get("bank_type").toString());
					ps.setString(8, map.get("cash_fee").toString());
					ps.setString(9, map.get("openid").toString());
					ps.setString(10,"01");
					ps.setString(11, "01");
					ps.execute();
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						ps.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}	
				}
			}
}
