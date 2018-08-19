package com.sccbb.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sccbb.Util.PropertiesUtil;

public class DcfCodeServlet extends HttpServlet {
	
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
	        //String username = req.getParameter("username").toUpperCase().trim();
		    //System.out.println(username);
		    System.out.println(req.getParameter("arr"));
//	        String appid = PropertiesUtil.getProperties().getProperty("APPID").toString().trim();
//	        HashMap map = new HashMap();  
//	        HttpSession session = req.getSession();
	        
//	        try{
//		        String redirect_uri=URLEncoder.encode("http://203624vk44.iok.la/weixin/dcfpay.do", "UTF-8");
//		        StringBuffer url = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?" +
//	        		"appid="+appid+
//	        		"&redirect_uri=" +redirect_uri+
//	        		"&response_type=code" +
//	        		"&scope=snsapi_base" +
//	        		"&state=ok#wechat_redirect");
//				    resp.sendRedirect(url.toString());
//	        }catch (Exception e) {
//	        		e.printStackTrace();
//	        }
		}
}
