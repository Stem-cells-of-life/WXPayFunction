package com.sccbb.Util;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
public class PropertiesUtil {
    private static Properties properties;
    static{
        properties = new Properties();
        Reader in;
        try {
            in = new FileReader("src\\com\\sccbb\\Util\\PropertiesUtil.properties");
            //load方法 从.properties属性文件对应的文件输入流中，加载属性列表到Properties类对象
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
          }
			public static  Map<String, String> getProperties(){
				Map<String, String> propertiesValue=new HashMap<String, String>();
				propertiesValue.put("APPID",properties.getProperty("APPID"));
				propertiesValue.put("SECRET",properties.getProperty("SECRET"));//GetUserInfoUtil类常量
				propertiesValue.put("token",properties.getProperty("token"));//CheckUtil类常量
				propertiesValue.put("MESSAGE_TEXT",properties.getProperty("MESSAGE_TEXT"));
				propertiesValue.put("MESSAGE_IMAGE",properties.getProperty("MESSAGE_IMAGE"));
				propertiesValue.put("MESSAGE_VOICE",properties.getProperty("MESSAGE_VOICE"));
				propertiesValue.put("MESSAGE_VIDEO",properties.getProperty("MESSAGE_VIDEO"));
				propertiesValue.put("MESSAGE_LINK",properties.getProperty("MESSAGE_LINK"));
				propertiesValue.put("MESSAGE_LOCATION",properties.getProperty("MESSAGE_LOCATION"));
				propertiesValue.put("MESSAGE_EVENT",properties.getProperty("MESSAGE_EVENT"));
				propertiesValue.put("MESSAGE_SUBSCRIBE",properties.getProperty("MESSAGE_SUBSCRIBE"));
				propertiesValue.put("MESSAGE_UNSUBSCRIBE",properties.getProperty("MESSAGE_UNSUBSCRIBE"));
				propertiesValue.put("MESSAGE_CLICK",properties.getProperty("MESSAGE_CLICK"));
				propertiesValue.put("MESSAGE_VIEW",properties.getProperty("MESSAGE_VIEW"));//MessageUtil类常量
				propertiesValue.put("DOMAIN_API",properties.getProperty("DOMAIN_API"));
				propertiesValue.put("DOMAIN_API2",properties.getProperty("DOMAIN_API2"));
				propertiesValue.put("DOMAIN_APIHK",properties.getProperty("DOMAIN_APIHK"));
				propertiesValue.put("DOMAIN_APIUS",properties.getProperty("DOMAIN_APIUS"));
				propertiesValue.put("FAIL",properties.getProperty("FAIL"));
				propertiesValue.put("SUCCESS",properties.getProperty("SUCCESS"));
				propertiesValue.put("HMACSHA256",properties.getProperty("HMACSHA256"));
				propertiesValue.put("MD5",properties.getProperty("MD5"));
				propertiesValue.put("FIELD_SIGN",properties.getProperty("FIELD_SIGN"));
				propertiesValue.put("FIELD_SIGN_TYPE",properties.getProperty("FIELD_SIGN_TYPE"));
				propertiesValue.put("MICROPAY_URL_SUFFIX",properties.getProperty("MICROPAY_URL_SUFFIX"));
				propertiesValue.put("UNIFIEDORDER_URL_SUFFIX",properties.getProperty("UNIFIEDORDER_URL_SUFFIX"));
				propertiesValue.put("ORDERQUERY_URL_SUFFIX",properties.getProperty("ORDERQUERY_URL_SUFFIX"));
				propertiesValue.put("REVERSE_URL_SUFFIX",properties.getProperty("REVERSE_URL_SUFFIX"));
				propertiesValue.put("CLOSEORDER_URL_SUFFIX",properties.getProperty("CLOSEORDER_URL_SUFFIX"));
				propertiesValue.put("REFUND_URL_SUFFIX",properties.getProperty("REFUND_URL_SUFFIX"));
				propertiesValue.put("REFUNDQUERY_URL_SUFFIX",properties.getProperty("REFUNDQUERY_URL_SUFFIX"));
				propertiesValue.put("DOWNLOADBILL_URL_SUFFIX",properties.getProperty("DOWNLOADBILL_URL_SUFFIX"));
				propertiesValue.put("REPORT_URL_SUFFIX",properties.getProperty("REPORT_URL_SUFFIX"));
				propertiesValue.put("SHORTURL_URL_SUFFIX",properties.getProperty("SHORTURL_URL_SUFFIX"));
				propertiesValue.put("AUTHCODETOOPENID_URL_SUFFIX",properties.getProperty("AUTHCODETOOPENID_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_MICROPAY_URL_SUFFIX",properties.getProperty("SANDBOX_MICROPAY_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_UNIFIEDORDER_URL_SUFFIX",properties.getProperty("SANDBOX_UNIFIEDORDER_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_ORDERQUERY_URL_SUFFIX",properties.getProperty("SANDBOX_ORDERQUERY_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_REVERSE_URL_SUFFIX",properties.getProperty("SANDBOX_REVERSE_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_CLOSEORDER_URL_SUFFIX",properties.getProperty("SANDBOX_CLOSEORDER_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_REFUND_URL_SUFFIX",properties.getProperty("SANDBOX_REFUND_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_REFUNDQUERY_URL_SUFFIX",properties.getProperty("SANDBOX_REFUNDQUERY_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_DOWNLOADBILL_URL_SUFFIX",properties.getProperty("SANDBOX_DOWNLOADBILL_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_REPORT_URL_SUFFIX",properties.getProperty("SANDBOX_REPORT_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_SHORTURL_URL_SUFFIX",properties.getProperty("SANDBOX_SHORTURL_URL_SUFFIX"));
				propertiesValue.put("SANDBOX_AUTHCODETOOPENID_URL_SUFFIX",properties.getProperty("SANDBOX_AUTHCODETOOPENID_URL_SUFFIX"));//WXPayConstants类常量
				return propertiesValue;
			}
//	public static void main(String[] args) {
//		System.out.println(getProperties().get("MESSAGE_SUBSCRIBE"));//通过调用工具类中的getProperties().get("属性名")方法获取属性值
//		
//}		
}