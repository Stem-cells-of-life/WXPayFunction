package com.sccbb.Util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.sccbb.dao.GetConnection;


public class PropertiesUtil {
    private static Properties properties;
    private static FileInputStream inputFile;
    
    
    public static Properties getProperties() {
    	try {
	        properties = new Properties();
	        Reader in;
	        inputFile = new FileInputStream(GetConnection.class.getResource("/")
       			 .getPath() + "com/sccbb/Util/PropertiesUtil.properties");
            //load方法 从.properties属性文件对应的文件输入流中，加载属性列表到Properties类对象
            properties.load(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        } 
		return properties;
	}
    
    
    //获取OpenId
    public static String getOpenId (String code) throws IOException{
  			InputStream is = null;
  			String openid =null;
  			String appid = PropertiesUtil.getProperties().getProperty("APPID").toString().trim();
  			String secret = PropertiesUtil.getProperties().getProperty("SECRET").toString().trim();
  			try{
  				        URL url = new URL("https://api.weixin.qq.com/sns/oauth2/access_token?" +
  				        		"appid=" +appid+
  				        		"&secret=" +secret+
  				        		"&code="+code +
  				        		"&grant_type=authorization_code");
  				        HttpURLConnection   con = (HttpURLConnection) url.openConnection();
  				        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
  				        con.setRequestProperty("Accept-Charset", "UTF-8");  
  				        con.setRequestProperty("contentType", "utf-8");  
  				        con.setConnectTimeout(50000);    
  				        con.setReadTimeout(50000);  
  				        con.setDoInput(true);  
  		                //默认GET  
  				        con.setRequestMethod("GET");
  				        is = con.getInputStream();
  				        
  				        String result = null;  
  				        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
  			            StringBuffer buffer = new StringBuffer();  
  			            String line = "";  
  			            while ((line = in.readLine()) != null) {  
  			                buffer.append(line);  
  			            }  
  			          //这个里面有个特殊的access_token 
  			            result = buffer.toString();  
  			            JSONObject jsonObject = JSONObject.fromObject(result); 
  			            openid = jsonObject.get("openid").toString(); 
  			}catch (Exception e) {
  				e.printStackTrace();
  			}finally{
  				is.close();
  			}
  			return openid;
  		}
    
        //自动计算时间
    	public static String getnexttime (String date,int i) throws Exception{
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(sdf.parse(date));
		        calendar.add(Calendar.YEAR, i);
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		        String dateString = formatter.format(calendar.getTime());
		        return dateString;
    	}
}