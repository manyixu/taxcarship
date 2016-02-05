package com.derun.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.taxdeclaration.service.impl.TaxDeclaeredUPloadServiceImpl;

public class TaxDeclaeredUPloadServiceImpl_Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		startThread();
	}
	public static void startThread(){
		TaxDeclaeredUPloadServiceImpl tdusi = new TaxDeclaeredUPloadServiceImpl();
		DeclareDateUploadReqInfo ddui = new DeclareDateUploadReqInfo();
		TaxDealCode_Type[] taxConfirmNo = new TaxDealCode_Type[30] ;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.51:1521:ORCL", "tpl", "tpl");
//			conn = DBConnPool.getConnection();
			int i = 0 ;
			pre = conn.prepareStatement("SELECT TAXCONFIRMNO FROM SYJK_CCS_rkmx WHERE PLATFORMSTATE <> 1 and rownum <= 30");
			rs = pre.executeQuery();
			while(rs.next()){
				TaxDealCode_Type tt = new TaxDealCode_Type();
				String str = rs.getString("TAXCONFIRMNO") ;
				tt.setTaxDealCode_Type(str);
				taxConfirmNo[i++] = tt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pre != null) {
					pre.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ddui.setAreaCode("140000");
		ddui.setCompanyCode("TPIC");
		ddui.setDeclareDate("20140313");
		ddui.setUserName("admin123");
		ddui.setPassword("123456");
		ddui.setTaxConfirmNo(taxConfirmNo);
		tdusi.taxDeclaeredUPload(ddui);
	}
}
class MyThread extends Thread{
	TaxDeclaeredUPloadServiceImpl tdusi = new TaxDeclaeredUPloadServiceImpl();
	DeclareDateUploadReqInfo ddui = new DeclareDateUploadReqInfo();
	public MyThread(DeclareDateUploadReqInfo dduii){
		this.ddui = dduii ;
	}
	@Override
	public void run() {
		tdusi.taxDeclaeredUPload(ddui);
	}
}
