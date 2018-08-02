package com.sccbb.Util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sccbb.po.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
				
			public static final String MESSAGE_TEXT = "text";
			public static final String MESSAGE_IMAGE = "image";
			public static final String MESSAGE_VOICE = "voice";
			public static final String MESSAGE_VIDEO = "video";
			public static final String MESSAGE_LINK = "link";
			public static final String MESSAGE_LOCATION = "location";
			public static final String MESSAGE_EVENT = "event";
			public static final String MESSAGE_SUBSCRIBE = "subscribe";
			public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
			
			//这两个貌似必须大写
			public static final String MESSAGE_CLICK = "CLICK";
			public static final String MESSAGE_VIEW = "VIEW";
			
			
			//把返回的xml信息打包成map
			public static Map<String,String> xmlToMap(HttpServletRequest req) throws Exception{
				
				Map<String,String> map = new HashMap<String, String>();

				SAXReader reader = new SAXReader();
				//拿到流，request传过来的
				InputStream ins = req.getInputStream();
				//创建Document对象
				Document doc = reader.read(ins);
				//获取xml的根元素
				Element root = doc.getRootElement();
				//把element 根元素放到 list 中去
				List<Element> list = root.elements();
				//遍历这个list,再装入map	
				for(Element e : list){
						map.put(e.getName(), e.getText());
				}
				ins.close();
				return map;
			}
			
			/**
			 * 将文本消息对象转换为xml
			 * @param textMessage
			 * @return
			 */
			public static String textMessageToXml(TextMessage textMessage){
						XStream xstream = new XStream();
						xstream.alias("xml", textMessage.getClass());//把根元素替换为xml 不然是错的
						return xstream.toXML(textMessage);
			}
}
