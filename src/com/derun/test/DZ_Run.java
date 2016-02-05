package com.derun.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import com.derun.beans.ReconciliationReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.taxreconciliation.service.TaxReconciliationService;

public class DZ_Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 List<ReconciliationReqInfo> list = new ArrayList<ReconciliationReqInfo>();
	        int n = 3 ;
	        for(int i = 0 ; i < n ;i++){
	        	list.add(startThread(i + 1));
	        }
	        for(int i = 0 ; i < n ;i++){
	        	new MyThread_dz(list.get(i)).start();
	        }

	}
	public static ReconciliationReqInfo startThread(int n){
		ReconciliationReqInfo rri = new ReconciliationReqInfo();
		TaxDealCode_Type[] TaxConfirmNo = new TaxDealCode_Type[10000] ;
		String dzlx = "2" ;		// 1  投保查询   2 投保确认  3 变更查询  4 变更确认
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.1.51:1521:ORCL", "tpl", "tpl");
			pre = conn.prepareStatement("SELECT * FROM(SELECT ROWNUM R, T.* FROM (SELECT TAXCONFIRMNO FROM SYJK_CCS_RKMX WHERE PLATFORMSTATE <> 1 AND ROWNUM <= 30000) T WHERE ROWNUM <= " + n  * 10000 + ") WHERE R > " + (n - 1) * 10000);
			rs = pre.executeQuery();
			int i = 0 ;
			while (rs.next()) {
				TaxDealCode_Type tc = new TaxDealCode_Type();
				String str = rs.getString("TAXCONFIRMNO");
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
/**
 * 
 * 
 * */
class MyThread_dz extends Thread{
	
	ReconciliationReqInfo rri = new ReconciliationReqInfo();
	
	public MyThread_dz(ReconciliationReqInfo rris){
		this.rri = rris ;
	}
	@Override
	public void run() {
		try {  
            
            String url = "http://localhost:8080/taxcarship/services/TaxReconciliationService" ;  
              
            Service service = new ObjectServiceFactory().create(TaxReconciliationService.class) ;  
              
            XFireProxyFactory factory = new XFireProxyFactory() ;  
              
            TaxReconciliationService hello = (TaxReconciliationService) factory.create(service,url) ;  
            
            hello.taxReconciliation(rri);
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	}
}