package com.sccbb.Util;

import java.util.Date;
import java.util.Map;

import com.sccbb.po.TextMessage;


/**
 * 给用户发消息的类
 * @author panda2wa
 *
 */
public class Sendmsg {
				
				/**
				 * 
				 * 用户关注后的方法
				 **/ 
				public static TextMessage intiText(TextMessage msg ,Map<String, String> map){
						try{
							msg.setToUserName(map.get("FromUserName").toString());
							msg.setFromUserName(map.get("ToUserName").toString());
							msg.setMsgType(MessageUtil.MESSAGE_TEXT);
							msg.setCreateTime(new Date().getTime());
							msg.setContent("感谢您关注湖北生命源干细胞有限公司,我们将竭诚为您服务！");
							return msg;
						}catch (Exception e) {
							e.printStackTrace();
						}
						return null;
				}
				
				/**
				 * 用户给公众号发消息的自动回复
				 * @param msg
				 * @param map
				 * @return
				 */
				public static TextMessage respText(TextMessage msg ,Map<String, String> map){
					try{
						msg.setToUserName(map.get("FromUserName").toString());
						msg.setFromUserName(map.get("ToUserName").toString());
						msg.setMsgType(MessageUtil.MESSAGE_TEXT);
						msg.setCreateTime(new Date().getTime());
						msg.setContent("您好，公众号目前可以为您查询监测报告，" +
								"您也可以留言。如需要人工服务，请拨打客服热线400-0306-033" +
								"或联系客服QQ。谢谢！");
						return msg;
					}catch(Exception e){
						e.printStackTrace(); 
					}
					return null;
				}
				
				/**
				 * 
				 * 下面的方法未使用
				 * */
				public static TextMessage respCLICK(TextMessage msg,Map<String, String> map) {
					try{
						//测试方法，查询监测报告,已经停用，日后监测报告要修改，可以启用
						if("jiancebg".equals(map.get("EventKey"))){
								msg.setToUserName(map.get("FromUserName").toString());
								msg.setFromUserName(map.get("ToUserName").toString());
								msg.setMsgType(MessageUtil.MESSAGE_TEXT);
								msg.setCreateTime(new Date().getTime());
								msg.setContent("抱歉！监测报告查询功能正在开发中.....");
						}
						//这个也是多余的
						if("daikouxieyi".equals(map.get("EventKey"))){
								msg.setToUserName(map.get("FromUserName").toString());
								msg.setFromUserName(map.get("ToUserName").toString());
								msg.setMsgType(MessageUtil.MESSAGE_IMAGE);
								msg.setCreateTime(new Date().getTime());
								msg.setMediaId("");
						}
						return msg;
					}catch(Exception e){
						e.printStackTrace();
					}
						return null;
				}
//				//回复图片信息
//				public static TextMessage respPIC(TextMessage msg,Map<String, String> map) {
//					try{
//								msg.setToUserName(map.get("FromUserName").toString());
//								msg.setFromUserName(map.get("ToUserName").toString());
//								msg.setMsgType(MessageUtil.MESSAGE_IMAGE);
//								msg.setCreateTime(new Date().getTime());
//								msg.setMediaId("");
//								return msg;
//					}catch(Exception e){
//						e.printStackTrace();
//					}
//						return null;
//				}
				
}
