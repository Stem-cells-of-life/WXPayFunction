package com.sccbb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sccbb.Util.JsonArray2List;
import com.sccbb.dao.GetConnection;

public class TestServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");  
        req.setCharacterEncoding("UTF-8");  
        resp.setCharacterEncoding("UTF-8");  
        String arr =  req.getParameter("arr").toString();
       // System.out.println("----------"+arr);
//		String code = req.getParameter("code").toString();
		String total = req.getParameter("total").toString();
		List<Map<String, String>> arrList =JsonArray2List.getJsonListByString(arr);
		//System.out.println("++++++----------"+arrList);
		req.setAttribute("arrList", arrList);
		req.setAttribute("total", total);
		insertList(arrList);
		req.getRequestDispatcher("/dcfmsg.jsp").forward(req, resp);
	}
    public static void insertList(List<Map<String, String>> arrList){
    	Connection conn = new GetConnection().getConnection();
    	PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("insert into wxpay_dcf_qx_xnw values(?,?,?,?,?)");
			 for(Map<String, String> map:arrList){
	    		 System.out.println("insert语句中的map："+map);
 //   		 if(map.get("xy_type")=="脐血协议"){
	    			 stmt.setString(1, map.get("id"));
					 stmt.setString(2, map.get("bs_agreement_0001"));
					 stmt.setString(3, map.get("xy_type"));
					 stmt.setString(4, map.get("bs_fee_0002"));
					 stmt.setString(5, map.get("priceselect"));
						// 2.添加到batch中
					 System.out.println("已添加入Batch中");
						stmt.addBatch();
// }
				 
	    	 }
			    stmt.executeBatch();
				stmt.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("缴费失败");
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
    }
}

