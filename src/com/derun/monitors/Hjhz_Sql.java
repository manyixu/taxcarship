package com.derun.monitors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;

/**
 * @author MILI
 * @time 2014-7-24 10:02:47
 * @描述：初始化 合计汇总 sql
 * */
public class Hjhz_Sql {
	/**查询数据库合计汇总相关信息*/
	public static Hjhz_VO getHjhzSql(){
		Hjhz_VO hjhz_vo = new Hjhz_VO();
		Connection con = null ;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql_YYYY = "SELECT COUNT(*) AS YYYY_NUMBER,SUM(RKMX.SUMTAX) AS YYYY_MONEY FROM SYJK_CCS_RKMX RKMX WHERE TO_CHAR(RKMX.SJCJRQ, 'YYYY') = TO_CHAR(SYSDATE,'YYYY') AND RKMX.REFUSETYPE = 1 AND RKMX.LOGGEDOUT = '0' AND RKMX.TAXCONDITIONCODE <> 'R'" ;
        String sql_Month = "SELECT COUNT(*) AS MONTH_NUMBER,SUM(RKMX.SUMTAX) AS MONTH_MONEY FROM SYJK_CCS_RKMX RKMX WHERE TO_CHAR(RKMX.SJCJRQ,'YYYY-MM') = TO_CHAR(SYSDATE,'YYYY-MM') AND RKMX.REFUSETYPE = 1 AND RKMX.LOGGEDOUT = '0' AND RKMX.TAXCONDITIONCODE <> 'R'" ;
        String sql_Date = "SELECT COUNT(*) AS DATE_NUMBER,SUM(RKMX.SUMTAX) AS DATE_MONEY FROM SYJK_CCS_RKMX RKMX WHERE TO_CHAR(RKMX.SJCJRQ,'YYYY-MM-dd') = TO_CHAR(SYSDATE,'YYYY-MM-dd') AND RKMX.REFUSETYPE = 1 AND RKMX.LOGGEDOUT = '0' AND RKMX.TAXCONDITIONCODE <> 'R'" ;
		try {
			con = DBConnPool.getConnection();
			pstmt = con.prepareStatement(sql_YYYY);
			rs = pstmt.executeQuery();
			if(rs.next()){
				hjhz_vo.setYear_Number(rs.getInt("YYYY_NUMBER"));
				hjhz_vo.setYear_Money(rs.getDouble("YYYY_MONEY"));
			}
			pstmt = con.prepareStatement(sql_Month);
			rs = pstmt.executeQuery();
			if(rs.next()){
				hjhz_vo.setMonth_Number(rs.getInt("MONTH_NUMBER"));
				hjhz_vo.setMonth_Money(rs.getDouble("MONTH_MONEY"));
			}
			pstmt = con.prepareStatement(sql_Date);
			rs = pstmt.executeQuery();
			if(rs.next()){
				hjhz_vo.setDate_Number(rs.getInt("DATE_NUMBER"));
				hjhz_vo.setDate_Money(rs.getDouble("DATE_MONEY"));
			}
		}catch(Exception e){
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//			if(con != null){
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
		}
		return hjhz_vo ;
	}
//	/**
//	 * @author MILI
//	 * @time 2014-8-21 10:32:30
//	 * @描述：业务明细  密码验证
//	 * */
//	public String getPassword(){
//		String sql = "" ;
//		String password = null ;
//		Connection con = null ;
//		PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//			con = DBConnPool.getConnection();
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			if(){
//				
//			}
//		}catch(Exception e){
//			
//		}finally{
//			if(rs != null){
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if(pstmt != null){
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if(con != null){
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return password ;
//	}
}
