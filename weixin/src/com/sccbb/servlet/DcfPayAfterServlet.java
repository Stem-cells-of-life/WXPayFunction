package com.sccbb.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sccbb.Util.CrudDaoImp;

public class DcfPayAfterServlet extends HttpServlet {
	
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
	        String state = req.getParameter("state").toString();
	        try{
	        	if("01".equals(state)||"01"==state){
	        		List<Map<String,String>> arrlist  = (List) req.getSession().getAttribute("arr");
	        		CrudDaoImp.insertList(arrlist);
	        	}
				req.setAttribute("state", state);
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				req.getSession().invalidate();
			}
	        req.getRequestDispatcher("/dcfpayafter.jsp").forward(req, resp);
	}
}
