package com.derun.taxchangequery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSCXRCJBXX;
/**
 * @author MILI
 * @time 2014-4-23 16:06:27
 * @描述：匹配信息
 * */
public class C_SYJK_CCS_RKMX {
	/**
	 * @author MILI
	 * @time 2014-4-23 16:05:05
	 * @描述：验证确认码是否有效
	 * @入参：确认码
	 * @出参：车船税查询入参基本信息
	 * */
	public SYJK_CCS_CCSCXRCJBXX getCcscxrc(String confirmno){
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		SYJK_CCS_CCSCXRCJBXX ccscxrc = null ;
		StringBuffer sql = new StringBuffer("SELECT TAXCONDITIONCODE,SJCJRQ,TAXQUERYNO,ANNUALTAXDUE,");
		sql.append("SUMTAXDEFAULT,SUMTAX,SUMOVERDUE,SPECIALCARTYPE,FIRSTREGISTERDATE");
		sql.append(" FROM SYJK_CCS_RKMX T WHERE LOGGEDOUT='0'  AND CHANGETYPE='0' AND T.TAXCONFIRMNO = ? ORDER BY SJCJRQ DESC");
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql.toString());
			pre.setString(1, confirmno);
			rs = pre.executeQuery();
			if (rs.next()) {
				ccscxrc = new SYJK_CCS_CCSCXRCJBXX();
				ccscxrc.setSJCJRQ(rs.getDate("SJCJRQ"));				// 系统采集日期
				ccscxrc.setTAXQUERYNO(rs.getString("TAXQUERYNO"));		// 交易码
				ccscxrc.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));	// 本年车船税金额
				ccscxrc.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));// 合计欠税金额
				ccscxrc.setSUMTAX(rs.getDouble("SUMTAX"));				// 合计金额
				ccscxrc.setSUMOVERDUE(rs.getDouble("SUMOVERDUE"));		// 合计滞纳金
				ccscxrc.setSPECIALCARTYPE(rs.getString("SPECIALCARTYPE"));			// 特殊车标志
				ccscxrc.setFIRSTREGISTERDATE(rs.getDate("FIRSTREGISTERDATE"));	// 车辆初始登记日期
				ccscxrc.setMOTORTYPECODE(rs.getString("TAXCONDITIONCODE"));			// 用于存储纳税类型
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
		return ccscxrc ;
	}
}
