package com.sccbb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sccbb.Util.JsonArray2List;

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
        System.out.println("----------"+arr);
//		String code = req.getParameter("code").toString();
		String total = req.getParameter("total").toString();
		List<Map<String, String>> arrList =JsonArray2List.getJsonListByString(arr);
		System.out.println("++++++----------"+arrList);
		req.setAttribute("arrList", arrList);
		req.setAttribute("total", total);
		req.getRequestDispatcher("/dcfmsg.jsp").forward(req, resp);
	}

}
