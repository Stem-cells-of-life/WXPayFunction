package com.sccbb.Util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sccbb.dao.GetConnection;

public class CrudDaoImp {
      
       public static void insertList(List<Map<String, String>> arrList){
       	Connection conn = new GetConnection().getConnection();
       	PreparedStatement stmt = null;
   		try {
   			stmt = conn.prepareStatement("insert into wxpay_dcf (id,xy_id,bs_agreement_0001,xy_type,bs_fee_0002,priceselect,payyear,ordernumber) values(SEQ_WD_ID.nextval,?,?,?,?,?,?,?)");
   			 for(Map<String, String> map:arrList){
   	    			 stmt.setString(1, map.get("id"));
   					 stmt.setString(2, map.get("bs_agreement_0001")); 
   					 if("脐血".equals(map.get("xy_type").toString())){
   						 	stmt.setString(3, "01");
   						 }else if("脐带".equals(map.get("xy_type").toString())){
   							 stmt.setString(3, "02");
   						 }else if("胎盘".equals(map.get("xy_type").toString())){
   							 stmt.setString(3, "04");
   						 };
   					 stmt.setString(4, map.get("bs_fee_0002").toString());
   					 stmt.setString(5, map.get("priceselect").toString());
   					 stmt.setString(6, map.get("payyear").toString());
   					 stmt.setString(7, map.get("ordernumber").toString());
   					 stmt.addBatch();
   	    	 }
   			    stmt.executeBatch();
   				stmt.clearBatch();
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}finally {
   			try {
   				stmt.close();
   				conn.close();
   			} catch (SQLException e) {
   				e.printStackTrace();
   			}
   		}
       }
} 	

	


