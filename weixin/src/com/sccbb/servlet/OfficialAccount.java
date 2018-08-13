package com.sccbb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sccbb.Util.CheckUtil;
import com.sccbb.Util.GetUserInfoUtil;
import com.sccbb.Util.MessageUtil;
import com.sccbb.Util.Sendmsg;
import com.sccbb.po.TextMessage;

public class OfficialAccount extends HttpServlet {
				@Override
				protected void doGet(HttpServletRequest req, HttpServletResponse resp)
						throws ServletException, IOException {
						String signature = req.getParameter("signature");
						String timestamp = req.getParameter("timestamp");
						String nonce = req.getParameter("nonce");
						String echostr = req.getParameter("echostr");
						PrintWriter pw = resp.getWriter();
						if(CheckUtil.checkSignature(signature, timestamp, nonce)){
							pw.print(echostr);
						}
						pw.close();
						System.out.println("连接成功");
				}
				
				@Override
				protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
					//这个设置很重要
					req.setCharacterEncoding("UTF-8");
					resp.setCharacterEncoding("UTF-8");
					PrintWriter pw = resp.getWriter();
					try{
							Map<String,String> map =MessageUtil.xmlToMap(req);
							//拿到此次信息的类型
							System.out.println(map);
							String msgType = map.get("MsgType");
							TextMessage msg =  new TextMessage();
							if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
									msg = Sendmsg.respText(msg, map);
							}
							//如果是个事件
							if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
								String eventType = map.get("Event");
							if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){//关注事件
								msg =Sendmsg.intiText(msg, map);
							}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){//点击事件
								msg = Sendmsg.respCLICK(msg,map);
							}
						}
							
							//把消息类转为xml的字符串
							String sendmsg = MessageUtil.textMessageToXml(msg);
							pw.print(sendmsg);
							
							//获取用户信息的方法,用花生壳没法使用这个方法
//							String json =GetUserInfoUtil.getUserInfo(map.get("FromUserName").toString());
//							System.out.println(json);
					}catch (Exception e) {
							e.printStackTrace();
					}finally{
						pw.flush();
						pw.close();
					}
				}
}
