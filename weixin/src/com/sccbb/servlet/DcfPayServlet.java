package com.sccbb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sccbb.Util.JsonArray2List;
import com.sccbb.Util.PropertiesUtil;

public class DcfPayServlet extends HttpServlet {
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
	        PrintWriter pw = null;
	        String arr = req.getSession().getAttribute("arr").toString();
			String code = req.getParameter("code").toString();
			String total = req.getSession().getAttribute("total").toString();
			System.out.println("进入dcfpay");
			System.out.println(arr);
			System.out.println(code);
			System.out.println(total);
			
//        	spbill_create_ip 获取请求方的ip
            String ip  =req.getHeader("x-forwarded-for");
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){  
                ip = req.getHeader("Proxy-Client-IP");  
            }  
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){  
                ip = req.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){  
                ip = req.getRemoteAddr();  
            }  
            if(ip.indexOf(",")!=-1){  
                String[] ips = ip.split(",");  
                ip = ips[0].trim();  
            } 
            
//            String openid = PropertiesUtil.getOpenId(code);
            req.getRequestDispatcher("/dcfmsg.jsp").forward(req, resp);
		}
}
