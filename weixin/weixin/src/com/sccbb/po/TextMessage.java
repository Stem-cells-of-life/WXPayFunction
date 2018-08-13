package com.sccbb.po;

public class TextMessage {
				private String ToUserName ;
				private String FromUserName ;//用户的Openid
				private long CreateTime ;
				private String MsgType ;
				private String Content ;//消息内容
				private String MsgId ;
				private String MediaId;
				
				public String getMediaId() {
					return MediaId;
				}
				public void setMediaId(String mediaId) {
					MediaId = mediaId;
				}
				public String getToUserName() {
					return ToUserName;
				}
				public void setToUserName(String toUserName) {
					ToUserName = toUserName;
				}
				public String getFromUserName() {
					return FromUserName;
				}
				public void setFromUserName(String fromUserName) {
					FromUserName = fromUserName;
				}
				public long getCreateTime() {
					return CreateTime;
				}
				public void setCreateTime(long createTime) {
					CreateTime = createTime;
				}
				public String getMsgType() {
					return MsgType;
				}
				public void setMsgType(String msgType) {
					MsgType = msgType;
				}
				public String getContent() {
					return Content;
				}
				public void setContent(String content) {
					Content = content;
				}
				public String getMsgId() {
					return MsgId;
				}
				public void setMsgId(String msgId) {
					MsgId = msgId;
				}
}

