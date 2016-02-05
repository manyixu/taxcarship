package com.derun.loggedout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-4-29 
 * @描述：注销服务 数据库 交互
 * */
public class LoggedOutDao {
	/**
	 * @author MILI
	 * @time 2014-4-29 10:26:55
	 * @描述：根据确认码 到rkmx 表查对应的缴税纪录
	 * */
	public List<SYJK_CCS_RKMX> getSYJK_CCS_RKMX(String taxConfirmNo){
		List<SYJK_CCS_RKMX> list = new ArrayList<SYJK_CCS_RKMX>();
		SYJK_CCS_RKMX rkmx = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("TAXCONFIRMNO,LOGGEDOUT,PLATFORMSTATE,CHANGETYPE,COUNTTAXTYPE,");
		sbf.append("REFUSETYPE,STATUSDATE,TAXCONDITIONCODE,SJCJRQ,VIN,HPHM,ENGINENO ");
		sbf.append(",CITYCODE,COUNTRYCODE "); //wbzhao 20150127 批改确认时税款归属地从投保信息中取得
		sbf.append("FROM ");
		sbf.append("SYJK_CCS_RKMX c ");
		sbf.append("WHERE ");
		sbf.append("c.Loggedout='0' AND ");
		sbf.append("c.taxConfirmNo='");
		sbf.append(taxConfirmNo);
		sbf.append("' order by c.SJCJRQ DESC");
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sbf.toString());
			rs = pre.executeQuery();
			while (rs.next()) {
				rkmx = new SYJK_CCS_RKMX();
				rkmx.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));
				rkmx.setLOGGEDOUT(rs.getString("Loggedout"));
				rkmx.setPLATFORMSTATE(rs.getString("Platformstate"));
				rkmx.setCHANGETYPE(rs.getString("ChangeType"));
				rkmx.setCOUNTTAXTYPE(rs.getString("CountTaxType"));
				rkmx.setRefusetype(rs.getString("Refusetype"));
				String STATUSDATE_ = rs.getString("STATUSDATE");  
				if(null != STATUSDATE_ && STATUSDATE_.length() >= 19){
					rkmx.setSTATUSDATE(STATUSDATE_.substring(0, 19));
				}else{
					rkmx.setSTATUSDATE("1111-01-01 00:00:00");
				}
				rkmx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));
				rkmx.setSJCJRQ(rs.getString("sjcjrq"));
				rkmx.setVIN(rs.getString("VIN"));
				rkmx.setHPHM(rs.getString("HPHM"));
				rkmx.setEngineNo(rs.getString("ENGINENO"));
				rkmx.setCityCode(rs.getString("CITYCODE"));	//税款归属（地市） wbzhao 20150127
				rkmx.setCountryCode(rs.getString("COUNTRYCODE"));	//税款归属（县区）wbzhao 20150127
				list.add(rkmx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pre != null){
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null){
//					conn.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	/**
	 * @author MILI
	 * @time 2014-4-29 10:29:32
	 * @描述：更新 数据库 表  type = 1 查询码   type = 0 确认码
	 * */
	public boolean updateSurrenderDeclaredstatus(String changeConfirmNo,String type) {
		boolean bool = false;
		PreparedStatement pre = null;
		Connection conn = null;
		String field = "";
		if (type.equals("1"))
			field = "TAXQUERYNO";
		else
			field = "TAXCONFIRMNO";
		try {
			conn = DBConnPool.getConnection();
			conn.setAutoCommit(false);
			String sql = "UPDATE SYJK_CCS_RKMX BG SET Loggedout='1' WHERE Loggedout='0' and BG." + field + "=?";
			pre = conn.prepareStatement(sql);
			pre.setString(1, changeConfirmNo);
			int returnCode = pre.executeUpdate();
			if (returnCode > 0) {
				bool = true;
				conn.commit();
			} else {
				bool = false;
			}
		} catch (Exception e) {
			try {
				conn.rollback();
				bool = false;
			} catch (SQLException ea) {
				e.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(pre != null){
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null){
//					conn.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bool;
	}
	/**
	 * @author MILI
	 * @time 2014-4-29 10:29:32
	 * @描述：根据确认码 得到 rkmx 信息
	 * @入参：
	 * 
	 * */
	public SYJK_CCS_RKMX getChangeSYJK_CCS_RKMX(String taxChangeConfirmNo) {
		SYJK_CCS_RKMX rkmx = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("TAXCONFIRMNO,LOGGEDOUT,PLATFORMSTATE,CHANGETYPE,COUNTTAXTYPE,");
		sbf.append("REFUSETYPE,STATUSDATE,TAXCONDITIONCODE,SJCJRQ ");
		sbf.append(" FROM SYJK_CCS_RKMX C WHERE ");
		sbf.append("C.TAXQUERYNO = ? ORDER BY C.SJCJRQ");
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sbf.toString());
			pre.setString(1, taxChangeConfirmNo);
			rs = pre.executeQuery();
			while (rs.next()) {
				rkmx = new SYJK_CCS_RKMX();
				rkmx.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));
				rkmx.setLOGGEDOUT(rs.getString("Loggedout"));
				rkmx.setPLATFORMSTATE(rs.getString("Platformstate"));
				rkmx.setCHANGETYPE(rs.getString("ChangeType"));
				rkmx.setCOUNTTAXTYPE(rs.getString("CountTaxType"));
				rkmx.setRefusetype(rs.getString("Refusetype"));
				rkmx.setSTATUSDATE(rs.getString("STATUSDATE"));
				rkmx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));
				rkmx.setSJCJRQ(rs.getString("sjcjrq"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pre != null){
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null){
//					conn.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rkmx;
	}
}
