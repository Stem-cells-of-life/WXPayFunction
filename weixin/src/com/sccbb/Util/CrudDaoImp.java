package com.sccbb.Util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
} 	

	


