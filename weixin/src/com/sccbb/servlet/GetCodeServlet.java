package com.sccbb.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sccbb.Util.PropertiesUtil;



/**
 * 获取用的的Code
 * @author panda2wa
 *
 */
public class GetCodeServlet extends HttpServlet {
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
			        String appid = PropertiesUtil.getProperties().getProperty("APPID").toString().trim();
			        //这里要将你的授权回调地址处理一下，否则微信识别不了  
			        //测试号域名为：sccbb.natappvip.cc     正式号域名：203624vk44.iok.la
			        //测试号 appid = wxce126f5a1a10ea21
			        //正式号 appid = wxfe7cdb7a0ac5894f
//			        snsapi_userinfo  snsapi_base
//	        		String username = req.getParameter("username").toString();
//					String number =	 req.getParameter("number").toString();
//					String phone =	 req.getParameter("phone").toString();
//					String address =	 req.getParameter("address").toString();
			    try{
			        	HttpSession session = req.getSession();
						session.setAttribute("username", req.getParameter("username").toString());
						session.setAttribute("number", req.getParameter("number").toString());
						session.setAttribute("phone", req.getParameter("phone").toString());
						session.setAttribute("address", req.getParameter("address").toString());
				        String redirect_uri=URLEncoder.encode("http://203624vk44.iok.la/weixin/pay.do", "UTF-8");
				        StringBuffer url = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?" +
			        		"appid="+appid+
			        		"&redirect_uri=" +redirect_uri+
			        		"&response_type=code" +
			        		"&scope=snsapi_base" +
			        		"&state=ok#wechat_redirect");
						    resp.sendRedirect(url.toString());
					}catch (Exception e) {
						e.printStackTrace();
						resp.getWriter().print("<h1>后台程序出错</h1>");
					}
	 }
}