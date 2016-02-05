package com.derun.trace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.SqlText;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;


/**
 * 保存轨迹表
 * @author 郑艳英
 *
 */
public class TraceDao {
	//taxDealType：00 投保查询,10投保确认,20变更查询,30,变更确认，40申报,50纳税信息查询,60对账
	public void saveTrace(String returnCode,String taxDealCode,String carId,String taxDealType){
		 PreparedStatement pstmt = null;
		 Connection conn = null;
		
		 try{
		  
		    conn = DBConnPool.getConnection();
		    conn.setAutoCommit(false);
		    String sql=SqlText.C_SYJK_CCS_TAXTRACE_001;
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, carId);
			pstmt.setString(2, taxDealCode);
			pstmt.setString(3, returnCode);
			pstmt.setString(4, taxDealType);
			
		    pstmt.executeUpdate();
			 conn.commit();
		 }catch(Exception e){
			 e.printStackTrace();
			System.out.println("---------保存轨迹表出错了-----------");
		 }finally{
			 try{
			   if (pstmt != null) {
					pstmt.close();
				}
			   ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if (conn != null) {
//					conn.close();
//				}
			 }catch(Exception e1){
				 e1.printStackTrace();
			 }
		 }
		
	}
	
	//  投保确认服务 线程需要的 车辆信息
	
	public SYJK_CCS_CCSCXCCJBXX getVehicleType(String taxQueryNo){
		PreparedStatement pre = null;
		SYJK_CCS_CCSCXCCJBXX cxcc = null;
		
		ResultSet rs = null;
		Connection conn = null;
		String sql = SqlText.R_04_CCSCXCCJBXX_001;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
		    pre.setString(1, taxQueryNo);
			rs = pre.executeQuery();
			if (rs.next()) {
				cxcc = new SYJK_CCS_CCSCXCCJBXX();
				cxcc.setVIN(rs.getString("VIN"));
				cxcc.setHPHM(rs.getString("HPHM"));
				cxcc.setHPZL(rs.getString("HPZL"));
				cxcc.setENGINENO(rs.getString("ENGINENO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pre != null) {
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if (conn != null) {
//					conn.close();
//				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return cxcc;
	}
	
	//变更确认服务线程需要的车辆信息
	public SYJK_CCS_CCSBGCXCCJB getBGVehicleType(String taxQueryNo){
		PreparedStatement pre = null;
		SYJK_CCS_CCSBGCXCCJB bgcxcc = null;
		
		ResultSet rs = null;
		Connection conn = null;
		String sql = SqlText.R_04_CCSBGCXCCJB_002;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
		    pre.setString(1, taxQueryNo);
			rs = pre.executeQuery();
			if (rs.next()) {
				bgcxcc = new SYJK_CCS_CCSBGCXCCJB();
				bgcxcc.setVIN(rs.getString("VIN"));
				bgcxcc.setHPHM(rs.getString("HPHM"));
				bgcxcc.setHPZL(rs.getString("HPZL"));
				bgcxcc.setENGINENO(rs.getString("ENGINENO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pre != null) {
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if (conn != null) {
//					conn.close();
//				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return bgcxcc;
	}
	
}
