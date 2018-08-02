package com.sccbb.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sccbb.dao.GetConnection;


public class PayAfterServlet extends HttpServlet {
				@Override
				protected void doGet(HttpServletRequest req, HttpServletResponse resp)
						throws ServletException, IOException {
						doPost(req, resp);
				}
				@Override
				protected void doPost(HttpServletRequest req, HttpServletResponse resp)

						throws ServletException, IOException {
						req.setCharacterEncoding("UTF-8");
						resp.setCharacterEncoding("UTF-8");
						try{
								//订单时间
								SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
					    		String ordertimes = sdf.format(new Date());

								String state =req.getParameter("state").toString();
								req.setAttribute("state", state);
								String ordernumber = req.getParameter("ordernumber").toString();
								String cusname =URLDecoder.decode(req.getParameter("username").toString(),"utf-8");
								String cusphone =req.getParameter("phone").toString();
								String bynumber =req.getParameter("number").toString();
								String address =URLDecoder.decode(req.getParameter("address").toString(),"utf-8");
								String fee =req.getParameter("fee").toString();
								
//								System.out.println(ordertimes);
//								System.out.println(ordernumber);
//								System.out.println(state);
//								System.out.println(cusname);
//								System.out.println(cusphone);
//								System.out.println(bynumber);
//								System.out.println(address);
//								System.out.println(fee);
								
								Map<String,String> hm = new HashMap<String,String>();
								hm.put("ordernumber", ordernumber);
								hm.put("cusname", cusname);
								hm.put("cusphone", cusphone);
								hm.put("bynumber", bynumber);
								hm.put("address", address);
								hm.put("fee", fee);
								hm.put("state", state);
								hm.put("ordertimes", ordertimes);
								insertOrdersinfo(hm);//保存交易信息
								req.getRequestDispatcher("/payafter.jsp").forward(req, resp);
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
								req.getSession().invalidate();//清理session
						}
				}
				
				public void insertOrdersinfo(Map map){
					Connection conn = GetConnection.getConnection();
					PreparedStatement ps =null;
						try{
							String sql ="insert into ordersinfo " +
									"(id,ordernumber,cusname,cusphone,bynumber,address,fee,ordertimes,isvalid,state)" +
									" values (seq_ordersinfo.nextval,?,?,?,?,?,?,?,?,?)";
							ps = conn.prepareStatement(sql);
							ps.setString(1, map.get("ordernumber").toString());
							ps.setString(2, map.get("cusname").toString());
							ps.setString(3, map.get("cusphone").toString());
							ps.setString(4, map.get("bynumber").toString());
							ps.setString(5, map.get("address").toString());
							ps.setString(6, map.get("fee").toString());
							ps.setString(7, map.get("ordertimes").toString());
							ps.setString(8, "01");
							ps.setString(9, map.get("state").toString());
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
