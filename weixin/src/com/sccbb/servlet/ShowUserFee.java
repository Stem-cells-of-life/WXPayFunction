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
		String username = req.getParameter("username").toString();
		String sfznum = req.getParameter("sfznum").toString();
		
		List list = getUserMsg(username, sfznum);
		req.getRequestDispatcher("/showuserfee.jsp").forward(req, resp);

	}
	
	
	
  //模拟用户   胡元芹    420881198304014820   "彭迎凤", "420802198608121582"
	public static List getUserMsg(String username, String sfznum) {
		List list =null;
		GetConnection getCon = new GetConnection();
		Connection conn = getCon.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select " +
						 "agreement_id," +
						 "bs_agreement_0001," +
						 "xy_type," +
						 "bs_fee_0002," +
						 "dcf," +
						 "bs_agreement_0025," +
						 "bs_agreement_0034," +
						 "isisvalid " +
						 "from " +
						 "VIEW_TEMP_XNW_wxpay " +
						 "where " +
						 "BS_AGREEMENT_0025=? and BS_AGREEMENT_0034=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, sfznum);
				rs = ps.executeQuery();
				list = resultSetToList(rs);
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
		return list;
	}

	// 返回json对象
	public static List<Map> resultSetToList(ResultSet rs)
			throws SQLException, JSONException {
			List<Map> list = new ArrayList<Map>();
			// 遍历ResultSet中的每条数据
			while (rs.next()) {
				// 遍历每一列
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", rs.getString("agreement_id").toString());
				map.put("bs_agreement_0001", rs.getString("bs_agreement_0001").toString());
				map.put("xy_type", rs.getString("xy_type").toString());
				map.put("dcf", rs.getString("dcf").toString());
				map.put("bs_agreement_0025", rs.getString("bs_agreement_0025").toString());
				map.put("bs_agreement_0034", rs.getString("bs_agreement_0034").toString());
				map.put("isisvalid", rs.getString("isisvalid").toString());
				System.out.println(map);
				list.add(map);
			}
			return list;
	}
}
