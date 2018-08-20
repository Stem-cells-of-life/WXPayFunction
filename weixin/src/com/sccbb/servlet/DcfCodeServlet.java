package com.sccbb.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sccbb.Util.JsonArray2List;
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
	        String arr=req.getParameter("arr").toString().trim();
	        //通过工具类JsonArray2List将数组转换为list数组
	        List<HashMap<String,String>> list=JsonArray2List.getOrder(arr);
		    System.out.println(list);
		    
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
