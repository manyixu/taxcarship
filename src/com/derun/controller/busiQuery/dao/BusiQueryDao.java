package com.derun.controller.busiQuery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.controller.busiQuery.util.BusiQueryUtil;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;

public class BusiQueryDao {
	public List<BusiQueryUtil> getCondition(int page,int rows ,String vin,String taxqueryno,String cph,String jym) {
		 // 增加参数SQL语句
		StringBuilder paramSQL = new StringBuilder();
		// 增加参数集合
		List<Object> paramList = new ArrayList<Object>();
		//vin
		if ( StringUtil.isNotEmpty(vin) ) {
			StringUtil.paramSQL(paramSQL).append(" vin like ? ");
			paramList.add("%"+ vin +"%");// 
		}
		//交易码
		if(StringUtil.isNotEmpty(taxqueryno)){
			StringUtil.paramSQL(paramSQL).append(" TAXQUERYNO like ? ");
			paramList.add("%"+ taxqueryno +"%");
		}
		//车牌号
		if(StringUtil.isNotEmpty(cph)){
			StringUtil.paramSQL(paramSQL).append(" HPHM like ? ");
			paramList.add("%"+ cph +"%");
		}
		//交易码类型
		if(StringUtil.isNotEmpty(jym)){
			StringUtil.paramSQL(paramSQL).append(" TAXQUERYNO like ? ");
			paramList.add("%"+ jym +"%");
		}
		List<BusiQueryUtil> list = new ArrayList<BusiQueryUtil>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			String pageSQL = " select * from(select rownum r, t.* from (select * from syjk_ccs_rkmx) t where rownum <="+page*rows+")where r>"+(page-1)*rows;
			System.out.println(pageSQL + paramSQL);
			pre = conn.prepareStatement(pageSQL + paramSQL);
			for ( int i = 0; i < paramList.size(); i++ ) {
				
				pre.setObject(i+1, paramList.get(i));
			}
			//pre.setInt(1, rows);
			//pre.setInt(2, page);
			rs = pre.executeQuery();
			while (rs.next()) {
				BusiQueryUtil busiQueryUtil = new BusiQueryUtil(); 
				busiQueryUtil.setTAXQUERYNO(rs.getString("TAXQUERYNO"));
				busiQueryUtil.setVIN(rs.getString("VIN"));
				busiQueryUtil.setTAXPAYERNAME(rs.getString("TAXPAYERNAME"));
				busiQueryUtil.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE"));
				busiQueryUtil.setEngineNo(rs.getString("EngineNo"));
				busiQueryUtil.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));
				list.add(busiQueryUtil);
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

		return list;
	}
	
	
	
	public List<BusiQueryUtil> getBusiQuery(String vin) throws SQLException{  
		PreparedStatement pre = null;
		Connection conn = null;
		ResultSet rs = null;
		List<BusiQueryUtil> list = new ArrayList<BusiQueryUtil>();
		try{
			conn = DBConnPool.getConnection();
			String pageSQL = " select * from syjk_ccs_rkmx t where t.VIN=? and rownum<2";
			System.out.println(pageSQL);
			pre = conn.prepareStatement(pageSQL);
			pre.setString(1, vin);
		    rs=pre.executeQuery();
			if(rs.next()){
				BusiQueryUtil busiQueryUtil = new BusiQueryUtil(); 
				busiQueryUtil.setTAXQUERYNO(rs.getString("TAXQUERYNO"));
				busiQueryUtil.setVIN(rs.getString("VIN"));
				busiQueryUtil.setTAXPAYERNAME(rs.getString("TAXPAYERNAME"));
				busiQueryUtil.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE"));
				busiQueryUtil.setEngineNo(rs.getString("EngineNo"));
				busiQueryUtil.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));
				busiQueryUtil.setHPHM(rs.getString("HPHM"));
				busiQueryUtil.setSUMTAX(rs.getDouble("SUMTAX"));
				busiQueryUtil.setSJCJRQ(rs.getString("SJCJRQ"));
				busiQueryUtil.setHPZL(rs.getString("HPZL"));
				busiQueryUtil.setCLLX(rs.getString("CLLX"));
				
				list.add(busiQueryUtil);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
		

		   return list;
	
  }
	
	public int busiCount()throws Exception{
		int numb = 0 ;
		String sql="select count(*) as total from syjk_ccs_rkmx";
		Connection conn = DBConnPool.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			numb = rs.getInt("total");
		}else{
			numb = 0;
		}
		if(rs != null){
			rs.close();
		}
		if(pstmt != null){
			pstmt.close();
		}
		ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
		return numb ;
	}
	
}
