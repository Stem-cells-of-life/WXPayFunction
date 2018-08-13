package com.sccbb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sccbb.dao.GetConnection;

public class SortUser {

//	public static void main(String[] args) throws Exception {
//		getUserList("梅丽");
//	}

	public static JSONArray getUserList(String username) throws Exception {
		GetConnection getCon = new GetConnection();
		Connection conn = getCon.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONArray ja = null;
		String sql = "select  BS_CUS_0001, AGREEMENT_ID  from  sccbb_bs_temp_xnw where BS_CUS_0001=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);

			rs = ps.executeQuery();
			ja=formatRsToJsonArray(rs);

			// while(rs.next()){
			// //rs.get+数据库中对应的类型+(数据库中对应的列别名)
			//
			// String name = rs.getString("BS_CUS_0001");
			// int id = rs.getInt("AGREEMENT_ID");
			// System.out.println(name+id);
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ja;
	}

	public static JSONArray formatRsToJsonArray(ResultSet rs) throws Exception {
		ResultSetMetaData md = rs.getMetaData();// 获取表结构
		int num = md.getColumnCount();// 得到行的总数
		JSONArray array = new JSONArray();// json数组，根据下标找值；[{name1:wp},{name2:{name3:'ww'}}]name为key值，wp为value值
		while (rs.next()) {// 如果结果集中有值
			JSONObject mapOfColValues = new JSONObject();// 创建json对象就是一个{name:wp}
			for (int i = 1; i <= num; i++) {
				mapOfColValues.put(md.getColumnName(i), rs.getObject(i));// 添加键值对，比如说{name:Wp}通过name找到wp

			}
			array.add(mapOfColValues);
//			System.out.println(array);
		}
		return array;
	}

	// 返回json对象
	protected void responseOutWithJson(HttpServletResponse response,
			Object responseObject) {
		// 将实体对象转换为JSON Object转换
		JSONObject responseJSONObject = JSONObject.fromObject(responseObject);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(responseJSONObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
