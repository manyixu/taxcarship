package com.derun.Surrenders.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;


/**
 * @author MILI
 * @time 2014-4-29 10:47:45
 * @描述：退保确认 数据库交互
 * */
public class SuurendersDao {
	/**
	 * @author MILI
	 * @time 2014-4-29 10:51:09
	 * @描述：根据变更查询码找到SYJK_CCS_CCSBGCXCCJB的对应信息
	 * */
	public SYJK_CCS_CCSBGCXCCJB getCCSBGCXCCJB(String changeQueryNO) {
		SYJK_CCS_CCSBGCXCCJB bgccc = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT ");
		sbf.append("ANNUALTAXDUE,SUMOVERDUE,SUMTAX,SUMTAXDEFAULT,SJCJRQ,CLLX,VIN,SPECIALCARTYPE");
		sbf.append(",HPHM,TAXAMOUNT_FL,TAXCONFIRMNO,CHANGESUMTAX,FIRSTREGISTERDATE,TSBZ ");
		sbf.append("FROM SYJK_CCS_CCSBGCXCCJB BCC ");
		sbf.append("WHERE BCC.TAXQUERYNO = ?");
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sbf.toString());
			pre.setString(1, changeQueryNO);
			rs = pre.executeQuery();
			if (rs.next()) {
				bgccc = new SYJK_CCS_CCSBGCXCCJB();
				bgccc.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));
				bgccc.setSUMOVERDUE(rs.getDouble("SUMOVERDUE"));
				bgccc.setSUMTAX(rs.getDouble("SUMTAX"));
				bgccc.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));
				bgccc.setSJCJRQ(rs.getDate("SJCJRQ"));
				bgccc.setTAXQUERYNO(changeQueryNO);
				bgccc.setCLLX(rs.getString("CLLX"));
				bgccc.setVIN(rs.getString("VIN"));
				bgccc.setHPHM(rs.getString("HPHM"));
				bgccc.setHPZL(rs.getString("HPZL"));
				bgccc.setTAXAMOUNT_FL(rs.getString("TAXAMOUNT_FL"));
				bgccc.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));
				bgccc.setCHANGESUMTAX(rs.getDouble("CHANGESUMTAX"));
				bgccc.setFIRSTREGISTERDATE(rs.getDate("FIRSTREGISTERDATE"));
				bgccc.setSPECIALCARTYPE(rs.getString("SPECIALCARTYPE"));
				bgccc.setTSBZ(rs.getString("TSBZ"));
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
		return bgccc;
	}
}
