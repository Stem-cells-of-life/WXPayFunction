package com.sccbb.Util;


import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.sccbb.dao.GetConnection;


public class PropertiesUtil {
    private static Properties properties;
    private static FileInputStream inputFile;
    
    
    public static Properties getProperties() {
        properties = new Properties();
        Reader in;
        try {
        	inputFile = new FileInputStream(GetConnection.class.getResource("/")
       			 .getPath() + "com/sccbb/Util/PropertiesUtil.properties");
        	//weixin/src/com/sccbb/Util/PropertiesUtil.properties
            //load方法 从.properties属性文件对应的文件输入流中，加载属性列表到Properties类对象
            properties.load(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        } 
		return properties;
	}
}