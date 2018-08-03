package com.panda;

import java.io.IOException;
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


public class login extends HttpServlet {
					
	
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
							String usernum = req.getParameter("usernum").toUpperCase().trim();
							String sfznum = req.getParameter("sfznum").toUpperCase().trim();
							if(("".equals(usernum)||usernum==null)||("".equals(sfznum)||sfznum==null)||
									usernum.length()!=13){
									req.getRequestDispatcher("erro.html").forward(req, resp);
							}else{
								if(getUserMsg(usernum, sfznum)){
									if("X".equals(usernum.substring(2, 3))){
										req.setAttribute("photo","qx.jpg");
									}
									if("D".equals(usernum.substring(2,3))){
										req.setAttribute("photo","qd.jpg");
									}
									if("P".equals(usernum.substring(2, 3))){
										req.setAttribute("photo","tp.jpg");
									}
									req.getRequestDispatcher("end.jsp").forward(req, resp);
								}else{
									req.getRequestDispatcher("erro.html").forward(req, resp);
								}
							}
					}
					
							
					public Boolean getUserMsg(String usernum,String sfznum){
						GetConnection getCon=new GetConnection();
						HashMap hm = new HashMap();
						Connection conn = getCon.getConnection();
						PreparedStatement ps =null;
						ResultSet rs  =null;
						try {
							if("X".equals(usernum.substring(2,3))){
										ps  = conn.prepareStatement("select " +
												"BS_AGREEMENT_" +
												"0001,BS_AGREEMENT_0034 " +
												"from SCCBB_BS_AGREEMENT "+
												"where BS_AGREEMENT_ISVALID =?" +
												"and BS_AGREEMENT_STATE =?" +
												"and BS_AGREEMENT_0001=?"+
												"and BS_AGREEMENT_0034=?");
										ps.setString(1, "01");
										ps.setString(2, "02");
										ps.setString(3,usernum);
										ps.setString(4,sfznum);
							}else if("D".equals(usernum.substring(2,3))){
										ps  = conn.prepareStatement("select " +
												"QIDAI_ID,SHENFENZHENG " +
												"from sccbb_bs_jcz "+
												"where ZUOFEI =?" +
												"and QIDAI_ID=?"+
												"and SHENFENZHENG=?");
										ps.setString(1, "02");
										ps.setString(2,usernum);
										ps.setString(3,sfznum);
							}else if("P".equals(usernum.substring(2, 3))){
										ps  = conn.prepareStatement("select " +
												"TAIPAN_ID,SHENFENZHENG " +
												"from SCCBB_BS_TAIPAN "+
												"where ZUOFEI =?" +
												"and TAIPAN_ID=?"+
												"and SHENFENZHENG=?");
										ps.setString(1, "02");
										ps.setString(2,usernum);
										ps.setString(3,sfznum);
							}
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
