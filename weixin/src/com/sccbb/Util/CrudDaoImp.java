package com.sccbb.Util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sccbb.dao.GetConnection;

public class CrudDaoImp {
       public  boolean qixueFee(){
    	   Connection con=new GetConnection().getConnection();
    	   String sql1="bs_agreement_id,bs_feestandord_id,bs_fee_0001,bs_fee_0002,bs_fee_0004,bs_fee_0005,bs_fee_0006,bs_fee_0007,bs_fee_0008,bs_fee_0009," +
    	   		       "bs_fee_0010,bs_fee_0011,bs_fee_0012,bs_fee_0013,bs_fee_0014,bs_fee_isvalid,bs_fee_state) VALUES (?,?,?,?,?,?,?,?,?,?,'01','01');";
   	       String sql2="{call updatefeedateagreement(?)}";
    	   PreparedStatement pstmt1 = null;
    	   CallableStatement cstmt2=null;
    	   try {
    		con.setAutoCommit(false);
    		pstmt1 = con.prepareStatement(sql1);
    	    int i=pstmt1.executeUpdate();
    		cstmt2 = con.prepareCall(sql2);
    		cstmt2.setString(1, "bs_agreement_id");
    		int j=cstmt2.executeUpdate(sql2);
    		 //提交sql语句执行
    		con.commit();
    		
    		 } catch (SQLException e) {
    		 e.printStackTrace();
    		 try {
    		    con.rollback();//如果两句sql语句中只要有一个语句出错，则回滚，都不执行
    		    return false; 
    		 } catch (SQLException e1) {
    		 // TODO Auto-generated catch block
    		 e1.printStackTrace();
    		 }
    		 } finally {
    			 try {
					pstmt1.close();
					cstmt2.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		 }	
    	   return true; 
    		 }

       public static void main(String[] args) {
    	   CrudDaoImp cdi=new CrudDaoImp();
    	   cdi.qixueFee();
	}

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

	


