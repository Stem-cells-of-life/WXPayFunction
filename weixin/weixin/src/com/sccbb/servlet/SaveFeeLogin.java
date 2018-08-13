package com.sccbb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.sccbb.dao.GetConnection;


public class SaveFeeLogin extends HttpServlet {
					
	
					@Override
					protected void doGet(HttpServletRequest req, HttpServletResponse resp)
							throws ServletException, IOException {
							doPost(req, resp);
					}
					
					@Override
					protected void doPost(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
							resp.setContentType("text/html; charset=UTF-8");
							req.setCharacterEncoding("UTF-8");
							String username = req.getParameter("username").toUpperCase().trim();
							String sfznum = req.getParameter("sfznum").toUpperCase().trim();
							
							System.out.println(username+sfznum);
							if(("".equals(username)||username==null)||("".equals(sfznum)||sfznum==null)||
							(!getUserMsg(username, sfznum))){
								
								responseOutWithJson(resp, "{\"errcode\":\"1\",\"errdesc\":\"用户名或者密码错误\"}");
							}else{
								try {
									
									responseOutWithJson(resp, "{\"errcode\":\"0\",\"errdesc\":\"ok\",\"data\":"+SortUser.getUserList(username)+"}");
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
//								req.getRequestDispatcher("success.html").forward(req, resp);
							}
					}
					
							
					public Boolean getUserMsg(String username,String sfznum){
						GetConnection getCon=new GetConnection();
						Connection conn = getCon.getConnection();
						PreparedStatement ps =null;
						ResultSet rs  =null;
						String sql="select BS_AGREEMENT_0025,BS_AGREEMENT_0034 from SCCBB_BS_AGREEMENT where BS_AGREEMENT_0025=? and BS_AGREEMENT_0034=? ";
						try {
										ps  = conn.prepareStatement(sql);
										ps.setString(1,username);
										ps.setString(2,sfznum);
							rs = ps.executeQuery();
							if(rs.next()){
								return true;
								}else{
									return false;
								}
						} catch (SQLException e) {
							e.printStackTrace();
						}finally{
							try {
								ps.close();
								rs.close();
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}	
						}
						return false;
					}
					
					 
					//返回json对象
					protected void responseOutWithJson(HttpServletResponse response,  
					        Object responseObject) {  
					    //将实体对象转换为JSON Object转换  
					    JSONObject responseJSONObject = JSONObject.fromObject(responseObject);  
					    response.setCharacterEncoding("UTF-8");  
					    response.setContentType("application/json; charset=utf-8");  
					    PrintWriter out = null;  
					    try {  
					        out = response.getWriter();  
					        out.append(responseJSONObject.toString());  
					    } catch (IOException e) {  
					        e.printStackTrace();  
					    } finally {  
					        if (out != null) {  
					            out.close();  
					        }  
					    }  
					} 
}
