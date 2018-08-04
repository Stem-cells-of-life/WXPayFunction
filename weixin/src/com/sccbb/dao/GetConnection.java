package com.sccbb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class GetConnection {
	private static String driver;
    private static String url;
    private static String username;
    private static String password;
	    static{
        Properties properties = new Properties();
        Reader in;
        try {
            in = new FileReader("src\\com\\sccbb\\dao\\oracledb.properties");
            //load方法 从.properties属性文件对应的文件输入流中，加载属性列表到Properties类对象
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将配置文件中的参数提取出来
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }
	
			public static Connection getConnection(){
				Connection con = null;
				try {
					Class.forName(driver);
					con =(Connection) DriverManager.getConnection
							(url,username,password);
				} catch (Exception e) {
					e.printStackTrace();
				}
					return con;
			}
		
}