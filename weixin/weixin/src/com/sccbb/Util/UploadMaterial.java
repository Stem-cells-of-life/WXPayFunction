package com.sccbb.Util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadMaterial {

	/**
	 * 这个是上传各种素材的方法
	 * @param args
	 */
	//这个方法是所有素材上传的方法
			public static void main(String[] args) {
				//access_token 这个东西每次只有2个小时的时限，
				String access ="8_b8oIaW8GXJ7MUtykYzHXKdOnJvld-WV" +
						"jibL-fE9vMsA0wIHpy5wKuaJsxc3FRTyecsUeahkVyNKJqe37xs" +
						"B7raWCfgoHsLO3yX1t2KjTlheow_VLvHo_JVxiEKEoNmeeaOgj_RJnNx_Z64EPSCJdAJAWGN";
				
				//上传素材的类型
				String type =MessageUtil.MESSAGE_IMAGE;
				
				//上传路径，这个是其他素材的上传路径，注意：我用的是其他素材上传的路径！！！
				String path ="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + access + "&type=" + type;
				File file =  new File("C:\\Users\\zgk\\Desktop\\rsbx.jpg");
				System.out.println(file.getName());
				String result =null;
				try {
					//先判断文件在不在
					if(!file.exists()||!file.isFile()){
						throw new IOException("素材不存在");
					}
					 result = getMedia_id(path, file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(result);
			}
			
			//这一步就是在上传素材
			public static String getMedia_id(String path,File file) throws IOException {
				
				URL urlObj = new URL(path);
			    //连接
			    HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			    String result = null;
//			    con.setRequestMethod("POST");
			    con.setDoInput(true);
			    con.setDoOutput(true);
			    con.setUseCaches(false); // post方式不能使用缓存

			    // 设置请求头信息
			    con.setRequestProperty("Connection", "Keep-Alive");
			    con.setRequestProperty("Charset", "UTF-8");
			    // 设置边界
			    String BOUNDARY = "----------" + System.currentTimeMillis();
			    con.setRequestProperty("Content-Type",
			            "multipart/form-data; boundary="
			                    + BOUNDARY);

			    // 请求正文信息
			    // 第一部分：
			    StringBuilder sb = new StringBuilder();
			    sb.append("--"); // 必须多两道线
			    sb.append(BOUNDARY);
			    sb.append("\r\n");
			    sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\""
			            + file.getName() + "\"\r\n");
			    sb.append("Content-Type:application/octet-stream\r\n\r\n");
			    byte[] head = sb.toString().getBytes("utf-8");
			    // 获得输出流
			    OutputStream out = new DataOutputStream(con.getOutputStream());
			    // 输出表头
			    out.write(head);

			    // 文件正文部分
			    // 把文件以流文件的方式 推入到url中
			    DataInputStream in = new DataInputStream(new FileInputStream(file));
			    int bytes = 0;
			    byte[] bufferOut = new byte[1024];
			    while ((bytes = in.read(bufferOut)) != -1) {
			        out.write(bufferOut, 0, bytes);
			    }
			    in.close();
			    // 结尾部分
			    byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
			    out.write(foot);
			    out.flush();
			    out.close();
			    StringBuffer buffer = new StringBuffer();
			    BufferedReader reader = null;
			    try {
			        // 定义BufferedReader输入流来读取URL的响应
			        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			        String line = null;
			        while ((line = reader.readLine()) != null) {
			            buffer.append(line);
			        }
			        if (result == null) {
			            result = buffer.toString();
			        }
			    } catch (IOException e) {
			        System.out.println("发送POST请求出现异常！" + e);
			        e.printStackTrace();
			    } finally {
			        if (reader != null) {
			            reader.close();
			        }
			    }
			    return result;
			//以上操作，都看不懂，还好可以用。。。
			}

}
