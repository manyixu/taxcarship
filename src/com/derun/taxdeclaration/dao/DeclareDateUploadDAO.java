/*************************************************
 * 
 * 该类用于保存车船税申报日期上传入参和出参实体
 * 该类已被单独编译测试过
 * @see
 * @author xuelianh
 * @since JDK1.5
 * @version 1.0, 24/Sept/2011
 * 修改：
 * 复核：
 * 
 ************************************************/
package com.derun.taxdeclaration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.DeclareDateUploadResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.init.CfgLoader;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;

public class DeclareDateUploadDAO
{
//	private static final String username=PropertiesConfig.getUserName("username");
//	private	 static final String revenuecode =PropertiesConfig.getUserName("revenuecode");
	private static final String username =  CfgLoader.getConfigValue("SysParam", "Username");
	private static final String revenuecode =  CfgLoader.getConfigValue("SysParam", "TaxDocumentNumber");
	/**
	 * 找出数据库里不存在的
	 * @param arry
	 * @return
	 */
 public String[] getDateUploadNotIn(DeclareDateUploadReqInfo decDUreq,int count){
	 PreparedStatement pre = null;
	 ResultSet rs = null;
	 Connection conn=null; 
	 String confirmNo = "";
	 List<String> list = new ArrayList();
	 try {
		conn = DBConnPool.getConnection();
//		String sqlArray = "SELECT TAXCONFIRMNO,SJCJRQ FROM syjk_ccs_confirm_sb left join SYJK_CCS_RKMX using(TAXCONFIRMNO) and flag="+count;
//		String sqlArray = "SELECT s.TAXCONFIRMNO,r.SJCJRQ FROM syjk_ccs_confirm_sb s,SYJK_CCS_RKMX r where s.TAXCONFIRMNO<>r.TAXCONFIRMNO and flag="+count;
		String sqlArray = "select t.TAXCONFIRMNO from syjk_ccs_confirm_sb t where not exists (select * from SYJK_CCS_RKMX b where t.TAXCONFIRMNO=b.TAXCONFIRMNO) and flag="+count;
		pre = conn.prepareStatement(sqlArray);
		rs = pre.executeQuery();
		while(rs.next()){
//			if(rs.getString("SJCJRQ")==null){
			System.out.println(rs.getString("TAXCONFIRMNO")+"------------确认码");
			   list.add(rs.getString("TAXCONFIRMNO"));
			   System.out.println(list.size()+" --------------------------数组的长度");
//			}
		}
		
//		
//		for(int i=0;i<decDUreq.getTaxConfirmNo().length;i++){
//		pre = conn.prepareStatement(sqlArray);
//		pre.setString(1, decDUreq.getTaxConfirmNo()[i].getTaxDealCode_Type());
//		System.out.println(decDUreq.getTaxConfirmNo()[i].getTaxDealCode_Type());
//		rs = pre.executeQuery();
//		if(rs.next()==false){
//			confirmNo =decDUreq.getTaxConfirmNo()[i].getTaxDealCode_Type();
//			System.out.println(confirmNo+"查看一下哪些是不存在的");
//			list.add(confirmNo);
//		}
//		rs.close();
//		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoFreeConnectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			if (rs != null)
				rs.close();
			if (pre != null)
				pre.close();
			ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String[] str = new String[list.size()];
	for(int i=0;i<list.size();i++){
		str[i] = list.get(i);
	}
	 return str;
 }
 
 /**
  * 删除确认码
  * @return
  */
 public boolean deleteConfirm(int count){
	 PreparedStatement pre = null;
	 Connection conn=null; 
	 boolean flag = false;
	 try {
		conn = DBConnPool.getConnection();
//		String sql="truncate table syjk_ccs_confirm_sb";
		String sql = "delete from syjk_ccs_confirm_sb where flag="+count;
		pre = conn.prepareStatement(sql);
		int j = pre.executeUpdate();
		if(j>0){
			flag = true;
		}else {
			flag = false;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoFreeConnectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			if (pre != null)
				pre.close();
			ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 return flag;
 }
 
 
 
 
 /**
  * 查询数据库存在的进行更新
  * @param sqlArray
  * @return
  */
 public boolean  updateDeclaredStatus(TaxDealCode_Type[] sqlArray){
	 PreparedStatement pre = null;
	 Connection conn=null; 
	 boolean flag = false;
	 try {
		conn = DBConnPool.getConnection();
		conn.setAutoCommit(false);
		String sql="update SYJK_CCS_RKMX set PLATFORMSTATE = '1',STATUSDATE = sysdate where TAXCONFIRMNO=?";
		for(int i=0;i<sqlArray.length;i++){
		pre = conn.prepareStatement(sql);
		pre.setString(1, sqlArray[i].getTaxDealCode_Type());
//		System.out.println("--------"+sqlArray[i].getTaxDealCode_Type());
		int j = pre.executeUpdate();
		if(j>0){
			flag = true;
		}else {
			flag = true;
		}
		pre.close();
		}
		conn.commit();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoFreeConnectionException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	}finally {
		try {
			if (pre != null)
				pre.close();
//			conn.close();
			ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 return flag;
 }
 
 
 /**
  * 查询数据库存在的进行更新
  * @param sqlArray
  * @return
  */
 public boolean  updateDeclaredStatusChange(TaxDealCode_Type[] sqlArray){
	 PreparedStatement pre = null;
	 Connection conn=null; 
	 boolean flag = false;
	 try {
		conn = DBConnPool.getConnection();
		String sql="update SYJK_CCS_RKMX set PLATFORMSTATE = '1' where TAXCONFIRMNO=?";
		for(int i=0;i<sqlArray.length;i++){
//			System.out.println("--------------------------------");
		pre = conn.prepareStatement(sql);
		pre.setString(1, sqlArray[i].getTaxDealCode_Type());
		int j = pre.executeUpdate();
		if(j>0){
			flag = true;
		}else {
			flag = true;
		}
		pre.close();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoFreeConnectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			if (pre != null)
				pre.close();
//			conn.close();
			ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 return flag;
 }
 
 /**
  * 确认码入参插库
  * @param decDUreq
  * @return
  */
 public boolean insertConfrim(DeclareDateUploadReqInfo decDUreq,int count){
	 PreparedStatement pre = null;
	 Connection conn = null;
	 boolean flag = false;
	 TaxDealCode_Type[] confirmNo = decDUreq.getTaxConfirmNo();
	 try {
		conn = DBConnPool.getConnection();
		conn.setAutoCommit(false);   
		String sql = "insert into syjk_ccs_confirm_sb values(?,?)";
		pre = conn.prepareStatement(sql);
		for(int i=0;i<confirmNo.length;i++){
			System.out.println(confirmNo[i].getTaxDealCode_Type()+"----");
			pre.setString(1, confirmNo[i].getTaxDealCode_Type());
			pre.setInt(2, count);
			pre.addBatch();
		}
		int[] i = pre.executeBatch();
		if(i!=null){
			flag = true;
//			System.out.println("入参插入成功--------------------------");
		}else {
			flag = false;
//			System.out.println("入参插入失败--------------------------");
		}
		conn.commit();
	} catch (SQLException e) {
//		System.out.println("入参插入失败--------------------------");
	} catch (NoFreeConnectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		if(pre != null)
			try{
				pre.close();
//		if(conn != null)
			conn.commit();
		ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				conn.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	 return flag;
 }
 
 
 /**
  * 申报日期入参插入数据库
  * @param decDUreq
  * @return
  */
 public boolean insertDateUpLoadReq(DeclareDateUploadReqInfo decDUreq){
	 PreparedStatement pre = null;
	 Connection conn = null;
	 boolean flag = false;
	 String year = decDUreq.getDeclareDate();
	 TaxDealCode_Type[] confirmNo = decDUreq.getTaxConfirmNo();
	 Date date = new Date();
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 8位日期+8位时间
	 String xtcjri = df.format(date);
	 try {
		conn = DBConnPool.getConnection();
		conn.setAutoCommit(false);   
		String sql = "insert into syjk_ccs_ccssbrqscrc(DECLAREDATE,LISTRESPECTIVEANNUAL,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,TAXCHANGEQUERYNO,AREACODE,COMPANYCODE) values(TO_DATE(?,'YYYY-MM-DD'),?,?,?,sysdate,?,?,?,?)";
		pre = conn.prepareStatement(sql);
		for(int i=0;i<confirmNo.length;i++){
			pre.setString(1, year);
			pre.setString(2, confirmNo[i].getTaxDealCode_Type());
			pre.setString(3, username);
			pre.setString(4, revenuecode);
			pre.setString(5, "0");
			pre.setString(6, "");
			pre.setString(7, "");
			pre.setString(8, "");
			pre.addBatch();
		}
		int[] i = pre.executeBatch();
		if(i!=null){
			flag = true;
//			System.out.println("入参插入成功--------------------------");
		}else {
			flag = false;
//			System.out.println("入参插入失败--------------------------");
		}
		conn.commit();
	} catch (SQLException e) {
		try{
			conn.rollback();
		}catch (SQLException ea) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("入参插入失败--------------------------");
	} catch (NoFreeConnectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
			
			try{
				if(pre != null)
					pre.close();
				conn.commit();
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null)
//					conn.commit();
//					conn.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	 return flag;
 }
 
 
 /**
  * 申报日期出参插入数据库
  * @param decDUres
  * @return
  */
 public boolean insertDateUpLoadRes(DeclareDateUploadResInfo decDUres){
	 PreparedStatement pre = null;
	 Connection conn = null;
	 boolean flag = false;
	 TaxDealCode_Type[] confirmNo =null;
	 if(decDUres.getTaxConfirmNo()==null){
		 confirmNo = new  TaxDealCode_Type[1];
	 }else{
		 confirmNo =  decDUres.getTaxConfirmNo();
	 }
	 try {
		conn = DBConnPool.getConnection();
		conn.setAutoCommit(false);   
		String sql = "insert into syjk_ccs_ccssbrqsccc(RETURNCODE,LISTRESPECTIVEANNUAL,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS) values(?,?,?,?,sysdate,?)";
		pre = conn.prepareStatement(sql);
		for(int i=0;i<confirmNo.length;i++){
			pre.setString(1, decDUres.getReturnCode());
			pre.setString(2, confirmNo[i].getTaxDealCode_Type());
			pre.setString(3, username);
			pre.setString(4, revenuecode);
			pre.setString(5, "0");            
			pre.addBatch();
		}
		int[] i = pre.executeBatch();
		if(i!=null){
			flag = true;
//			System.out.println("出参插入成功--------------------------");
		}else {
			flag = false;
//			System.out.println("出参插入失败--------------------------");
		}
		conn.commit();
	} catch (SQLException e) {
//		System.out.println("出参插入失败--------------------------");
	} catch (NoFreeConnectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		if(pre != null)
			try{
				pre.close();
				conn.commit();
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//		if(conn != null)
//				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	 return flag;
 }
}
