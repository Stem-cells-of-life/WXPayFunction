package com.sccbb.Util;

import java.security.MessageDigest;
import java.util.Arrays;

public class CheckUtil {

				//private static final String token = "sccbb";//公众号的设置
//	            private static final String token;
                private static  String token=PropertiesUtil.getProperties().getProperty("token").toString();  
	      	         
				public static boolean checkSignature(String signature,String timestamp,String nonce){
						//把参数放在数组里面
					String [] arr = new String[]{token,timestamp,nonce};
					System.out.println("token="+token);
					//排序
					Arrays.sort(arr);
					//生成字符串
					StringBuffer sb = new StringBuffer();
					for(int i=0;i<arr.length;i++){
							sb.append(arr[i]);
					}
					//sha1加密
					String temp = getSha1(sb.toString());
					return  temp.equals(signature);
				}

				//sha1加密算法，百度一下就知道
				private static String getSha1(String str) {
					 if(str==null||str.length()==0){
				            return null;
				        }
				        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
				                'a','b','c','d','e','f'};
				        try {
				            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
				            mdTemp.update(str.getBytes("UTF-8"));

				            byte[] md = mdTemp.digest();
				            int j = md.length;
				            char buf[] = new char[j*2];
				            int k = 0;
				            for (int i = 0; i < j; i++) {
				                byte byte0 = md[i];
				                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				                buf[k++] = hexDigits[byte0 & 0xf];      
				            }
				            return new String(buf);
				        } catch (Exception e) {
				        	e.printStackTrace();
				            return null;
				        }
				}
}
