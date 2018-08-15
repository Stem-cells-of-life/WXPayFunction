package com.sccbb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
							resp.setContentType("application/json; charset=UTF-8");//ajax 返回json的设置
//							resp.setHeader("Access-Control-Allow-Origin","*");//跨域问题的解决办法
							req.setCharacterEncoding("UTF-8");
							PrintWriter pw = null;
							try{
								String username = req.getParameter("username").toUpperCase().trim();
								String sfznum = req.getParameter("sfznum").toUpperCase().trim();
								if(("".equals(username)||username==null)||("".equals(sfznum)||sfznum==null)||
									(!getUserMsg(username, sfznum))){
									System.out.println("02");
									String str = "{'state':'02'}";
									pw = resp.getWriter();
									pw.write(str);
								}else{
									System.out.println("01");
									String str = "{'state':'01'}";
									pw = resp.getWriter();
									pw.write(str);
								}
							}catch (Exception e) {
								e.printStackTrace();
							}finally{
								pw.flush();
								pw.close();
								 
							}
					}
					
							
					public Boolean getUserMsg(String username,String sfznum){
						GetConnection getCon=new GetConnection();
						Connection conn = getCon.getConnection();
						PreparedStatement ps =null;
						ResultSet rs  =null;
						String sql="select " +
									"BS_AGREEMENT_0025," +
									"BS_AGREEMENT_0034 " +
									"from SCCBB_BS_AGREEMENT " +
									"where BS_AGREEMENT_0025=? and BS_AGREEMENT_0034=? ";
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
}
