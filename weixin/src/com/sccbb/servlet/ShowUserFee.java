package com.sccbb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.sccbb.dao.GetConnection;

public class ShowUserFee extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getParameter("username"));
		System.out.println(req.getParameter("sfznum"));
		req.getRequestDispatcher("/showuerfee.jsp").forward(req, resp);
//		PrintWriter out = resp.getWriter();
//		//将数据拼接成JSON格式
//		out.print(getUserMsg("彭迎凤", "420802198608121582"));
////		out.flush();
////		out.close();

	}
  //模拟用户   胡元芹    420881198304014820   "彭迎凤", "420802198608121582"
	public static JSONArray getUserMsg(String username, String sfznum) {
		GetConnection getCon = new GetConnection();
		Connection conn = getCon.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONArray js=null;
		String sql = "select agreement_id," +
					 "bs_agreement_0001," +
					 "xy_type,bs_fee_0002," +
					 "dcf,bs_agreement_0025," +
					 "bs_agreement_0034," +
					 "isisvalid " +
					 "from " +
					 "VIEW_TEMP_XNW_wxpay " +
					 "where " +
					 "BS_AGREEMENT_0025=? and BS_AGREEMENT_0034=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, sfznum);
			rs = ps.executeQuery();
            js=resultSetToJsonArry(rs);
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
		return js;
	}
public static void main(String[] args) {
	getUserMsg("彭迎凤", "420802198608121582");
}
	// 返回json对象
	public static JSONArray resultSetToJsonArry(ResultSet rs)
			throws SQLException, JSONException {
		JSONArray array = new JSONArray();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {

				String columnName = metaData.getColumnLabel(i);

				String value = rs.getString(columnName);

				jsonObj.put(columnName, value);
			}

			array.add(jsonObj);
		}
		System.out.println(array);
		return array;
	}
}
