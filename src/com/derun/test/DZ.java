package com.derun.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.derun.beans.ReconciliationReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.taxreconciliation.service.TaxReconciliationService;

public class DZ {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		char s1[]={'a','b','c'}; 
//		char s2[]={'c','b','a'}; 
//		char s3[]=new char[s1.length+s2.length]; 
//		System.arraycopy(s1,0,s3,0,s1.length);    // s1复制到s3里面，从s1的第0个开始 s3的第0个开始放 放s1的全部
//		System.arraycopy(s2,0,s3,s1.length,s2.length); // s2复制到s3里面，从s2的第0个开始 s3的第s1.length个开始放 放s2的全部
//		System.out.println(s3); 
		try {
			//          
			String url = "http://localhost:8080/taxcarship/services/TaxReconciliationService";

			Service service = new ObjectServiceFactory()
					.create(TaxReconciliationService.class);

			XFireProxyFactory factory = new XFireProxyFactory();

			TaxReconciliationService hello = (TaxReconciliationService) factory
					.create(service, url);

			hello.taxReconciliation(startThread());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ReconciliationReqInfo startThread(){
		ReconciliationReqInfo rri = new ReconciliationReqInfo();
		TaxDealCode_Type[] TaxConfirmNo = new TaxDealCode_Type[100] ;
		String dzlx = "3" ;		// 1  投保查询   2 投保确认  3 变更查询  4 变更确认
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.1.51:1521:ORCL", "tpl", "tpl");
			pre = conn.prepareStatement("SELECT TAXQUERYNO FROM SYJK_CCS_CCSBGCXCCJB WHERE ROWNUM <= 100");
			rs = pre.executeQuery();
			int i = 0 ;
			while (rs.next()) {
				TaxDealCode_Type tc = new TaxDealCode_Type();
				String str = rs.getString("TAXQUERYNO");
				tc.setTaxDealCode_Type(str);
				TaxConfirmNo[i++] = tc ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		rri.setAreaCode("140000");
		rri.setCheckingType(dzlx);
		rri.setCompanyCode("TPIC");
		rri.setTaxConfirmNo(TaxConfirmNo);
		rri.setUserName("admin123");
		rri.setPassword("123456");
	
		return rri ;
	}
}
