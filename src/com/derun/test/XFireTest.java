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

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.taxdeclaration.service.TaxDeclaeredUPloadService;
  
public class XFireTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {  
//            
//            String url = "http://localhost:8080/taxcarship/services/TaxDeclaeredUPloadService" ;  
//              
//            Service service = new ObjectServiceFactory().create(TaxDeclaeredUPloadService.class) ;  
//              
//            XFireProxyFactory factory = new XFireProxyFactory() ;  
//              
//            TaxDeclaeredUPloadService hello = (TaxDeclaeredUPloadService) factory.create(service,url) ;  
//            
//            hello.taxDeclaeredUPload(startThread()) ;  
//              
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
        List<DeclareDateUploadReqInfo> list = new ArrayList<DeclareDateUploadReqInfo>();
        int n = 3 ;
        for(int i = 0 ; i < n ;i++){
        	list.add(startThread(i + 1));
        	System.out.println(list.get(0).getTaxConfirmNo()[0].getTaxDealCode_Type() + "--------------------------------");
        }
        for(int i = 0 ; i < n ;i++){
        	new MyThreads(list.get(i)).start();
        }
	}
	/**
	 * 
	 * 
	 * */
	public static DeclareDateUploadReqInfo startThread(int n){
		DeclareDateUploadReqInfo ddui = new DeclareDateUploadReqInfo();
		TaxDealCode_Type[] taxConfirmNo = new TaxDealCode_Type[10000] ;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.51:1521:ORCL", "tpl", "tpl");
			int i = 0 ;
//			pre = conn.prepareStatement("SELECT TAXCONFIRMNO FROM SYJK_CCS_RKMX WHERE PLATFORMSTATE <> 1 AND ROWNUM <= 10000");
			String sql = "SELECT * FROM(SELECT ROWNUM R, T.* FROM (SELECT TAXCONFIRMNO FROM SYJK_CCS_RKMX WHERE PLATFORMSTATE <> 1 AND ROWNUM <= 30000) T WHERE ROWNUM <= " + n  * 10000 + ") WHERE R > " + (n - 1) * 10000 ;
			pre = conn.prepareStatement(sql);
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
//		tdusi.taxDeclaeredUPload(ddui);
		return ddui ;
	}
}
/**
 * 
 * 
 * */
class MyThreads extends Thread{
	
	DeclareDateUploadReqInfo ddui = new DeclareDateUploadReqInfo();
	
	public MyThreads(DeclareDateUploadReqInfo dduis){
		this.ddui = dduis ;
	}
	@Override
	public void run() {
		try {  
            
            String url = "http://localhost:8080/taxcarship/services/TaxDeclaeredUPloadService" ;  
              
            Service service = new ObjectServiceFactory().create(TaxDeclaeredUPloadService.class) ;  
              
            XFireProxyFactory factory = new XFireProxyFactory() ;  
              
            TaxDeclaeredUPloadService hello = (TaxDeclaeredUPloadService) factory.create(service,url) ;  
            
            hello.taxDeclaeredUPload(ddui) ;  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	}
}
