package com.sccbb.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;



/**
 * 这个是获取用户信息和access_token的类
 * @author panda2wa
 *
 */
public class GetUserInfoUtil {
				
//					public static final String APPID = "wxfe7cdb7a0ac5894f";
//					public static final String SECRET = "c6a5488b81b958000f103042c5e0bdf3";
	               private static  String APPID="APPID";
	               private static  String SECRET="SECRET";
	 static {
     	PropertiesUtil propertiesUtil=new PropertiesUtil();
     	APPID=propertiesUtil.getProperties(APPID).get(APPID);
     	SECRET=propertiesUtil.getProperties(SECRET).get(SECRET);
 	}

					//下面是测试账号的 APPID和密码
				//	public static final String APPID = "wxce126f5a1a10ea21";
				//	public static final String SECRET = "1cf09e81f1de7fccf2ef61e5ffed7f64";
					
					
					//获取全局的Access_token，这个不是支付的Access_token
					public static String getAccess_token() throws Exception {
						URL url  =new URL("https://api.weixin.qq.com/cgi-bin/token?" +
								"grant_type=client_credential" +
								"&appid="+APPID +
								"&secret="+SECRET);
						InputStream is = getIS(url);
						String result = null;  
				        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
				        StringBuffer buffer = new StringBuffer();  
				        String line = "";  
				        while ((line = in.readLine()) != null) {  
				            buffer.append(line);  
				        } 
				        result = buffer.toString();  
				        JSONObject jsonObject = JSONObject.fromObject(result); 
				        String access_token = jsonObject.get("access_token").toString();  
						return access_token;
					}
				
					//获取用户的基本信息
					public static String getUserInfo(String OpenId) throws Exception{
						String actoken = getAccess_token();
						URL url = new URL("https://api.weixin.qq.com/cgi-bin/user/info?" +
								"access_token="+actoken+
								"&openid="+OpenId+
								"&lang=zh_CN");
						InputStream is = getIS(url);
						String result = null;  
				        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
				        StringBuffer buffer = new StringBuffer();  
				        String line = "";  
				        while ((line = in.readLine()) != null) {  
				            buffer.append(line);  
				        } 
				        result = buffer.toString();  
				        JSONObject jsonObject = JSONObject.fromObject(result); 
				//        System.out.println(jsonObject.toString());
						return jsonObject.toString();
					}
					
					
					//获取请求返回流
					private static InputStream getIS (URL url) throws Exception{
					    HttpURLConnection   con = (HttpURLConnection) url.openConnection();
				        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
				        con.setRequestProperty("Accept-Charset", "UTF-8");  
				        con.setRequestProperty("contentType", "utf-8");  
				        con.setConnectTimeout(50000);    
				        con.setReadTimeout(50000);  
				        con.setDoOutput(true);  
				        con.setDoInput(true);  
				        con.setRequestMethod("GET");
						return con.getInputStream();
					}
}
