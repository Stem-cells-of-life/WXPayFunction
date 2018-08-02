package com.panda;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class uploadFile extends HttpServlet {
						@Override
						protected void doGet(HttpServletRequest req, HttpServletResponse resp)
								throws ServletException, IOException {
									doPost(req, resp);
						}
			
						@Override
						protected void doPost(HttpServletRequest req, HttpServletResponse resp)
						throws ServletException, IOException {
						File file  =  new File("C:\\Users\\zgk\\Desktop\\DKXY.rar");
//							File file  =  new File("C:\\Users\\Administrator\\Desktop\\DKXY.rar");
								  	if(file.exists()){
								  		String fname = URLEncoder.encode(file.getName(),"UTF-8");
								  		InputStream fis = new FileInputStream(file);
								  		int len =0;
								  		byte [] buffer = new byte[1024];
								  		resp.setCharacterEncoding("UTF-8");
								  		resp.setHeader("content-disposition", "attachment;filename="+fname);
								  		resp.setContentType("application/octet-stream");
								  		OutputStream os =resp.getOutputStream();
								  		while((len=fis.read(buffer))>0){
								  						os.write(buffer,0,len);
								  		}
								  		fis.close();
								  		os.close();
								  	}else{
								  		throw new IOException("文件不存在");
								  	}
						}
}
