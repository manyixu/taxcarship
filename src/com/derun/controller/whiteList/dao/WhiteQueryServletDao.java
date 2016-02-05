package com.derun.controller.whiteList.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.controller.whiteList.common.WhiteList_Tool;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX_TEMP;
/**
 * @author MILI
 * @Date：2015-5-29 10:44:47
 * @描述：
 * */
public class WhiteQueryServletDao {
	
	public List<SYJK_CCS_QGCLWTMDXX_TEMP> getWTMD_W(int page,int rows,String status,String sqStartdate,String sqEnddate,String shStartdate,String shEnddate,String name){
		 // 增加参数SQL语句
		StringBuilder paramSQL = new StringBuilder();
		// 增加参数集合
		List<Object> paramList = new ArrayList<Object>();
		//申请人
		if ( StringUtil.isNotEmpty(name) ) {
			StringUtil.paramSQLC(paramSQL).append(" LOGINNAME= ?");
			paramList.add( name);
		}
		//申请状态
		if ( StringUtil.isNotEmpty(status) ) {
			StringUtil.paramSQLC(paramSQL).append(" APPLYSTATUS= ?");
			paramList.add( status);// 
		}
		// 申请开始时间
		if ( StringUtil.isNotEmpty(sqStartdate) ) {
			StringUtil.paramSQLC(paramSQL).append(" SJCJRQ >= TO_DATE(?,'YYYY-MM-DD') ");
			paramList.add(sqStartdate);
		}
		
		// 申请结束时间
		if ( StringUtil.isNotEmpty(sqEnddate) ) {
			StringUtil.paramSQLC(paramSQL).append(" SJCJRQ <= TO_DATE(?,'YYYY-MM-DD') ");
			paramList.add(sqEnddate);
		}
		// 审核开始时间
		if ( StringUtil.isNotEmpty(shStartdate) ) {
			StringUtil.paramSQLC(paramSQL).append(" CHECKDATE >= TO_DATE(?,'YYYY-MM-DD') ");
			paramList.add(shStartdate);
		}
		
		// 审核结束时间
		if ( StringUtil.isNotEmpty(shEnddate) ) {
			StringUtil.paramSQLC(paramSQL).append(" CHECKDATE <= TO_DATE(?,'YYYY-MM-DD') ");
			paramList.add(shEnddate);
		}
		List<SYJK_CCS_QGCLWTMDXX_TEMP> list_wtmd = new ArrayList<SYJK_CCS_QGCLWTMDXX_TEMP>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql1 = "select * from SYJK_CCS_QGCLWTMDXX_TEMP";
		String sql2 =  sql1 + paramSQL +" order by id desc";
		String sql = " select * from(select rownum r, t.* from ("+sql2+") t where rownum <="+page*rows+")where r>"+(page-1)*rows;
//		String sql = "select ID,CLSBDM, LOGINNAME,SJCJRQ,APPLYSTATUS,CHECKMAN,CHECKDATE,CHECKREMARK from SYJK_CCS_QGCLWTMDXX_TEMP";
		System.out.println(sql);
		try {
			conn = DBConnPool.getConnection();
			
			System.out.println(sql);
			pre = conn.prepareStatement(sql);
			for ( int i = 0; i < paramList.size(); i++ ) {
				
				pre.setObject(i+1, paramList.get(i));
			}
			rs = pre.executeQuery();
			while(rs.next()){
				SYJK_CCS_QGCLWTMDXX_TEMP wtmd = new SYJK_CCS_QGCLWTMDXX_TEMP();
				wtmd.setID(rs.getString("ID"));					// ID
				wtmd.setCLSBDM(rs.getString("CLSBDM"));			// 车辆识别代码
				wtmd.setLOGINNAME(rs.getString("LOGINNAME"));			//  申请人
				wtmd.setSJCJRQ(rs.getDate("SJCJRQ"));				// 申请日期
				wtmd.setAPPLYSTATUS(rs.getString("APPLYSTATUS"));	// 申请状态
				wtmd.setCHECKMAN(rs.getString("CHECKMAN"));// 审核人
				wtmd.setCHECKDATE(rs.getDate("CHECKDATE"));// 审核日期
				wtmd.setCHECKREMARK(rs.getString("CHECKREMARK"));// 审核意见
				wtmd.setYCYYDM(rs.getString("YCYYDM"));// 异常原因
				list_wtmd.add(wtmd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询白名单出错。。。。。。。。。。WhiteListCheckServletDao ---  getWTMD_W()");
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		return list_wtmd ;
	}  
	
	
	//查询详细信息
	public List<SYJK_CCS_QGCLWTMDXX_TEMP> getDetail(String id){
		List<SYJK_CCS_QGCLWTMDXX_TEMP> list = new ArrayList<SYJK_CCS_QGCLWTMDXX_TEMP>();
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = " select YCYYDM,CLSBDM,HPHM,HPZL,CLZL,FDJH,ZCCMC,SYXZ,CLXH,CLCSDJRQ,JGCLLX,HDZKS,HDZZL,ZBZL,PL,GL,SKSSSQ,SKSSZQ,DQYNSE,REVENUECODE,WSPZ,WSPZNO FROM SYJK_CCS_QGCLWTMDXX_TEMP t where t.ID=? and rownum<2 ";
		try {
			conn = DBConnPool.getConnection();
			
			System.out.println(sql);
			pre = conn.prepareStatement(sql);
			pre.setString(1, id);
			rs = pre.executeQuery();
			while(rs.next()){
				SYJK_CCS_QGCLWTMDXX_TEMP wtmd = new SYJK_CCS_QGCLWTMDXX_TEMP();
				wtmd.setYCYYDM(rs.getString("YCYYDM"));
				wtmd.setCLSBDM(rs.getString("CLSBDM"));
				wtmd.setHPHM(rs.getString("HPHM"));
				wtmd.setHPZL(rs.getString("HPZL"));
				wtmd.setCLZL(rs.getString("CLZL"));
				wtmd.setFDJH(rs.getString("FDJH"));
				wtmd.setZCCMC(rs.getString("ZCCMC"));
				wtmd.setSYXZ(rs.getString("SYXZ"));
				wtmd.setCLXH(rs.getString("CLXH"));
				wtmd.setCLCSDJRQ(rs.getDate("CLCSDJRQ"));
				wtmd.setJGCLLX(rs.getString("JGCLLX"));
				wtmd.setHDZKS(rs.getDouble("HDZKS"));
				wtmd.setHDZZL(rs.getDouble("HDZZL"));
				wtmd.setZBZL(rs.getDouble("ZBZL"));
				wtmd.setPL(rs.getDouble("PL"));
				wtmd.setGL(rs.getDouble("GL"));
				wtmd.setSKSSSQ(rs.getDate("SKSSSQ"));
				wtmd.setSKSSZQ(rs.getDate("SKSSZQ"));
				wtmd.setDQYNSE(rs.getDouble("DQYNSE"));
				wtmd.setREVENUECODE(rs.getString("REVENUECODE"));
				wtmd.setWSPZ(rs.getString("WSPZ"));
				wtmd.setWSPZNO(rs.getString("WSPZNO"));
				list.add(wtmd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询白名单出错。。。。。。。。。。WhiteListCheckServletDao ---  getWTMD_W()");
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	
	
	public int getWTMD_Count(String status,String sqStartdate,String sqEnddate,String shStartdate,String shEnddate,String name){
		StringBuilder paramSQL = new StringBuilder();
		// 增加参数集合
		List<Object> paramList = new ArrayList<Object>();
		//申请人
		if ( StringUtil.isNotEmpty(name) ) {
			StringUtil.paramSQLC(paramSQL).append(" LOGINNAME= ?");
			paramList.add( name);
		}
		//申请状态
		if ( StringUtil.isNotEmpty(status) ) {
			StringUtil.paramSQLC(paramSQL).append(" APPLYSTATUS= ?");
			paramList.add( status);
		}
		// 申请开始时间
		if ( StringUtil.isNotEmpty(sqStartdate) ) {
			StringUtil.paramSQLC(paramSQL).append(" SJCJRQ >= TO_DATE(?,'YYYY-MM-DD') ");
			paramList.add(sqStartdate);
		}
		
		// 申请结束时间
		if ( StringUtil.isNotEmpty(sqEnddate) ) {
			StringUtil.paramSQLC(paramSQL).append(" SJCJRQ <= TO_DATE(?,'YYYY-MM-DD') ");
			paramList.add(sqEnddate);
		}
		// 审核开始时间
		if ( StringUtil.isNotEmpty(shStartdate) ) {
			StringUtil.paramSQLC(paramSQL).append(" CHECKDATE >= TO_DATE(?,'YYYY-MM-DD') ");
			paramList.add(shStartdate);
		}
		
		// 审核结束时间
		if ( StringUtil.isNotEmpty(shEnddate) ) {
			StringUtil.paramSQLC(paramSQL).append(" CHECKDATE <= TO_DATE(?,'YYYY-MM-DD') ");
			paramList.add(shEnddate);
		}
		int count = 0 ;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "SELECT COUNT(*) AS TOTAL FROM SYJK_CCS_QGCLWTMDXX_TEMP";
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql + paramSQL);
			System.out.println(sql + paramSQL);
			for ( int i = 0; i < paramList.size(); i++ ) {
				
				pre.setObject(i+1, paramList.get(i));
			}
			rs = pre.executeQuery();
			if(rs.next()){
				count = rs.getInt("TOTAL");
			}else{
				count = 0 ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询白名单出错。。。。。。。。。。WhiteListCheckServletDao ---  getWTMD_Count()");
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		return count ;
	}
}
