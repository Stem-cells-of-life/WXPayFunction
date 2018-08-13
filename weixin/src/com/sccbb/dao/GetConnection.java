package com.sccbb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class GetConnection {
	private static String driver;
    private static String url;
    private static String username;
    private static String password;
    
    private static Properties pro;
    private static FileInputStream inputFile;
			public static Connection getConnection(){
				Connection con = null;
				try {
					pro = new Properties();	
		        	inputFile = new FileInputStream(GetConnection.class.getResource("/")
		        			 .getPath() + "com/sccbb/dao/db.properties");
		 
		            //load方法 从.properties属性文件对应的文件输入流中，加载属性列表到Properties类对象
		        	pro.load(inputFile);
		        	driver = pro.getProperty("jdbc.driver");
			        url = pro.getProperty("jdbc.url");
			        username = pro.getProperty("jdbc.username");
			        password = pro.getProperty("jdbc.password");
					Class.forName(driver);
					con =(Connection) DriverManager.getConnection(url,username,password);
				} catch (Exception e) {
					e.printStackTrace();
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
					return con;
			}
		
		
}