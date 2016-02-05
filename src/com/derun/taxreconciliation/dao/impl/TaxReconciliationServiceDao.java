package com.derun.taxreconciliation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.derun.beans.TaxAmount_Type;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.SqlText;
import com.derun.common.util.DateUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSDZCCSXX;
import com.derun.model.po.SYJK_CCS_CCSDZRCJBXX;

/**
 * 
 * @author 郑艳英 对帐服务dao
 */
public class TaxReconciliationServiceDao {

	/**
	 * 
	 * @param taxDealCodeType
	 * @return List<TaxAmount_Type> 对帐类型为车船税查询明细
	 */
	public List<TaxAmount_Type> taxQueryReconciliation(
			TaxDealCode_Type[] taxDealCodeType) {
		List<TaxAmount_Type> list = new ArrayList<TaxAmount_Type>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		TaxAmount_Type taxAmount_Type = null;
		TaxDealCode_Type taxDealCode_Type = null;
		// 查询出参表
		try {
			conn = DBConnPool.getConnection();
			// 重新优化查询数据库  MILI 2014-11-27 10:49:56 start  
			String sql = "" ;
			StringBuffer sbf = new StringBuffer(SqlText.R_05_CCSCXCCJBXX_002) ;
			for(int i = 0 ; i < taxDealCodeType.length ; i++){
				sbf.append("'" + taxDealCodeType[i].getTaxDealCode_Type() + "',");
			}
			sql = sbf.substring(0, sbf.length() - 1 );
			sql += ")" ;
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while(rs.next()){
				taxAmount_Type = new TaxAmount_Type();
				taxDealCode_Type = new TaxDealCode_Type();
				taxDealCode_Type.setTaxDealCode_Type(rs
						.getString("TAXQUERYNO"));
				taxAmount_Type.setTaxDealCode(taxDealCode_Type);
				taxAmount_Type
						.setAnnualTaxDue(rs.getDouble("ANNUALTAXDUE"));// 本年车船税金额
				taxAmount_Type.setSumTaxDefault(rs
						.getDouble("SUMTAXDEFAULT"));// 合计欠税金额
				taxAmount_Type.setTaxAmount_Flag(rs
						.getString("TAXAMOUNT_FLAG"));// 合计金额标志码
				taxAmount_Type.setSumOverdue(rs.getDouble("SUMOVERDUE"));// 合计滞纳金
				taxAmount_Type.setSumTax(rs.getDouble("SUMTAX"));// 合计金额
				list.add(taxAmount_Type);
			}
			// 重新优化查询数据库  MILI 2014-11-27 10:49:56 end 
//			String sql = SqlText.R_05_CCSCXCCJBXX_001;
//			for (int i = 0; i < taxDealCodeType.length; i++) {
//				pre = conn.prepareStatement(sql);
//				pre.setString(1, taxDealCodeType[i].getTaxDealCode_Type());
//				rs = pre.executeQuery();
//				if (rs.next()) {
//					taxAmount_Type = new TaxAmount_Type();
//					taxDealCode_Type = new TaxDealCode_Type();
//					taxDealCode_Type.setTaxDealCode_Type(rs
//							.getString("TAXQUERYNO"));
//					taxAmount_Type.setTaxDealCode(taxDealCode_Type);
//					taxAmount_Type
//							.setAnnualTaxDue(rs.getDouble("ANNUALTAXDUE"));// 本年车船税金额
//					taxAmount_Type.setSumTaxDefault(rs
//							.getDouble("SUMTAXDEFAULT"));// 合计欠税金额
//					taxAmount_Type.setTaxAmount_Flag(rs
//							.getString("TAXAMOUNT_FLAG"));// 合计金额标志码
//					taxAmount_Type.setSumOverdue(rs.getDouble("SUMOVERDUE"));// 合计滞纳金
//					taxAmount_Type.setSumTax(rs.getDouble("SUMTAX"));// 合计金额
//					list.add(taxAmount_Type);
//				}
//			}
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

	/**
	 * @param taxDealCodeType
	 * @param count
	 * @return flag 保存对帐确认码
	 */
	public boolean saveConfirmNo(TaxDealCode_Type[] taxDealCodeType, int count) {
		PreparedStatement pre = null;
		Connection conn = null;
		boolean flag = false;
		TaxDealCode_Type[] confirmNo = taxDealCodeType;
		try {
			conn = DBConnPool.getConnection();
			conn.setAutoCommit(false);
			String sql = SqlText.C_05_CONFIRM_DZ_002;
			pre = conn.prepareStatement(sql);
			for (int i = 0; i < confirmNo.length; i++) {
				pre.setString(1, confirmNo[i].getTaxDealCode_Type());
				pre.setInt(2, count);
				pre.addBatch();
			}
			int[] i = pre.executeBatch();
			if (i != null) {
				flag = true;
			} else {
				flag = false;
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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

		return flag;

	}

	/**
	 * 对帐类型为车船税确认明细
	 * 
	 * @param taxDealCodeType
	 * @param count
	 * @return List<TaxAmount_Type>
	 */
	public List<TaxAmount_Type> taxConfirmNoReconciliation(int count) {
		List<TaxAmount_Type> list = new ArrayList<TaxAmount_Type>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		TaxAmount_Type taxAmount_Type = null;
		TaxDealCode_Type taxDealCode_Type = null;
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.R_05_003;
			pre = conn.prepareStatement(sql);
			pre.setString(1, String.valueOf(count));
			pre.setString(2, String.valueOf(count));
			rs = pre.executeQuery();
			while (rs.next()) {
				taxAmount_Type = new TaxAmount_Type();
				taxDealCode_Type = new TaxDealCode_Type();
				taxDealCode_Type.setTaxDealCode_Type(rs.getString("TAXQUERYNO"));
				taxAmount_Type.setTaxDealCode(taxDealCode_Type);
				taxAmount_Type.setAnnualTaxDue(rs.getDouble("ANNUALTAXDUE"));// 本年车船税金额
				taxAmount_Type.setSumTaxDefault(rs.getDouble("SUMTAXDEFAULT"));// 合计欠税金额
				String status = rs.getString("PLATFORMSTATE");
				if (status == null || status.equals("4")) {
					status = "";
				}
				taxAmount_Type.setTaxAmount_Flag("2" + status);
				taxAmount_Type.setSumOverdue(rs.getDouble("SUMOVERDUE"));// 合计滞纳金
				taxAmount_Type.setSumTax(rs.getDouble("SUMTAX"));// 合计金额
				list.add(taxAmount_Type);
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

	/**
	 * 删除确认码
	 * 
	 * @param count
	 * @return flag
	 */
	public boolean deleteConfirmNo(int count) {
		PreparedStatement pre = null;
		Connection conn = null;
		boolean flag = false;
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.D_05_CONFIRM_DZ_004;
			String sqls = sql + count;
			pre = conn.prepareStatement(sqls);
			pre.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
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
		return flag;

	}

	/**
	 * 对帐类型为车船税变更查询明细
	 * 
	 * @param taxDealCodeType
	 * @return List<TaxAmount_Type>
	 */
	public List<TaxAmount_Type> taxChangeQqueryReconciliation(
			TaxDealCode_Type[] taxDealCodeType) {
		List<TaxAmount_Type> list = new ArrayList<TaxAmount_Type>();
		TaxAmount_Type taxAmount_Type = null;
		TaxDealCode_Type taxDealCode_Type = null;
		// 变更查询出参
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			// 重新优化查询数据库  MILI 2014-11-27 10:49:56 start
			conn = DBConnPool.getConnection();
			String sql = "" ;
			StringBuffer sbf = new StringBuffer(SqlText.R_05_CONFIRM_DZ_008) ;
			for(int i = 0 ; i < taxDealCodeType.length ; i++){
				sbf.append("'" + taxDealCodeType[i].getTaxDealCode_Type() + "',");
			}
			sql = sbf.substring(0, sbf.length() - 1);
			sql += ")" ;
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				taxAmount_Type = new TaxAmount_Type();
				taxDealCode_Type = new TaxDealCode_Type();
				taxDealCode_Type.setTaxDealCode_Type(rs
						.getString("TAXQUERYNO"));
				taxAmount_Type.setTaxDealCode(taxDealCode_Type);
				taxAmount_Type
						.setAnnualTaxDue(rs.getDouble("ANNUALTAXDUE"));// 本年车船税金额
				taxAmount_Type.setSumTaxDefault(rs
						.getDouble("SUMTAXDEFAULT"));// 合计欠税金额
				taxAmount_Type.setTaxAmount_Flag(rs
						.getString("TAXAMOUNT_FL"));// 合计金额标志码
				taxAmount_Type.setSumOverdue(rs.getDouble("SUMOVERDUE"));// 合计滞纳金
				taxAmount_Type.setSumTax(rs.getDouble("SUMTAX"));// 合计金额
				list.add(taxAmount_Type);
			}
			
//			conn = DBConnPool.getConnection();
//			String sql = SqlText.R_05_CONFIRM_DZ_005;
//			for (int i = 0; i < taxDealCodeType.length; i++) {
//				pre = conn.prepareStatement(sql);
//				pre.setString(1, taxDealCodeType[i].getTaxDealCode_Type());
//				rs = pre.executeQuery();
//				if (rs.next()) {
//					taxAmount_Type = new TaxAmount_Type();
//					taxDealCode_Type = new TaxDealCode_Type();
//					taxDealCode_Type.setTaxDealCode_Type(rs
//							.getString("TAXQUERYNO"));
//					taxAmount_Type.setTaxDealCode(taxDealCode_Type);
//					taxAmount_Type
//							.setAnnualTaxDue(rs.getDouble("ANNUALTAXDUE"));// 本年车船税金额
//					taxAmount_Type.setSumTaxDefault(rs
//							.getDouble("SUMTAXDEFAULT"));// 合计欠税金额
//					taxAmount_Type.setTaxAmount_Flag(rs
//							.getString("TAXAMOUNT_FL"));// 合计金额标志码
//					taxAmount_Type.setSumOverdue(rs.getDouble("SUMOVERDUE"));// 合计滞纳金
//					taxAmount_Type.setSumTax(rs.getDouble("SUMTAX"));// 合计金额
//					list.add(taxAmount_Type);
//				}
//			}
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

	/**
	 * 变更确认对帐信息
	 * 
	 * @param count
	 * @return List<TaxAmount_Type>
	 */
	public List<TaxAmount_Type> taxChangeConfirmReconciliation(int count) {
		List<TaxAmount_Type> list = new ArrayList<TaxAmount_Type>();
		TaxAmount_Type taxAmount_Type = null;
		TaxDealCode_Type taxDealCode_Type = null;
		// 变更确认入参表
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.R_05_CONFIRM_DZ_006;
			String sqls = sql + count;
			pre = conn.prepareStatement(sqls);
			rs = pre.executeQuery();
			while (rs.next()) {
				taxAmount_Type = new TaxAmount_Type();
				taxDealCode_Type = new TaxDealCode_Type();
				taxDealCode_Type
						.setTaxDealCode_Type(rs.getString("TAXQUERYNO"));
				taxAmount_Type.setTaxDealCode(taxDealCode_Type);
				taxAmount_Type.setAnnualTaxDue(rs.getDouble("ANNUALTAXDUE"));// 本年车船税金额
				taxAmount_Type.setSumTaxDefault(rs.getDouble("SUMTAXDEFAULT"));// 合计欠税金额
				taxAmount_Type
						.setTaxAmount_Flag(rs.getString("TAXAMOUNT_FLAG")+rs.getString("PLATFORMSTATE"));// 合计金额标志码
				taxAmount_Type.setSumOverdue(rs.getDouble("SUMOVERDUE"));// 合计滞纳金
				taxAmount_Type.setSumTax(rs.getDouble("SUMTAX"));// 合计金额,Y
				list.add(taxAmount_Type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
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
	 * 保存入参信息
	 * 
	 * @param ccsdzrcjbxx
	 * @return boolean
	 */
	public boolean saveReconciliationReqInfo(SYJK_CCS_CCSDZRCJBXX[] ccsdzrcjbxx) {
		boolean flag = false;
		PreparedStatement pre = null;
		Connection conn = null;
		int[] returnCode = null;
		try {
			if (ccsdzrcjbxx != null) {
				conn = DBConnPool.getConnection();
				conn.setAutoCommit(false);
				String sql = SqlText.C_05_CCSDZRCJBXX_007;
				for (int i = 0; i < ccsdzrcjbxx.length; i++) {
					if (ccsdzrcjbxx[i].getTAXRECONCILIATIONNO() != null) {
						pre = conn.prepareStatement(sql);

						String SJCJRQ = DateUtil.getString(ccsdzrcjbxx[i]
								.getSJCJRQ(), "yyyy-MM-dd HH:mm:ss");
						pre.setString(1, ccsdzrcjbxx[i]
								.getTAXRECONCILIATIONNO());
						pre.setString(2, ccsdzrcjbxx[i].getCHECKINGTYPE());
						pre.setString(3, ccsdzrcjbxx[i].getTAXDEALCODE());
						pre.setString(4, ccsdzrcjbxx[i].getLOGINNAME());
						pre.setString(5, ccsdzrcjbxx[i].getREVENUECODE());
						pre.setString(6, SJCJRQ);
						pre.setString(7, ccsdzrcjbxx[i].getSJCJFS());
						pre.addBatch();

					}
					returnCode = pre.executeBatch();
					conn.commit();
				}
				// int[] returnCode = pre.executeBatch();
				// conn.commit();
				if (returnCode != null) {
					System.out.println("数据库---对账服务入参插入成功！");
					flag = true;
				} else {
					System.out.println("数据库---对账服务入参插入失败！");
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
		return flag;
	}

	/**
	 * 保存出参信息
	 * 
	 * @param cssdzccsxx
	 * @return boolean
	 */
	public boolean saveReconciliationResInfo(SYJK_CCS_CCSDZCCSXX[] cssdzccsxx) {
		boolean flag = false;
		PreparedStatement pre = null;
		Connection conn = null;
		int[] returnCode = null;
		try {
			conn = DBConnPool.getConnection();
			conn.setAutoCommit(false);
			String sql = SqlText.C_05_CCSDZCCJBXX_008;
			for (int i = 0; i < cssdzccsxx.length; i++) {
				pre = conn.prepareStatement(sql);
				String SJCJRQ = DateUtil.getString(cssdzccsxx[i].getSJCJRQ(),
						"yyyy-MM-dd HH:mm:ss");
				pre.setString(1, cssdzccsxx[i].getTAXRECONCILIATIONNO());
				pre.setString(2, cssdzccsxx[i].getCHECKINGTYPE());
				pre.setString(3, cssdzccsxx[i].getTAXDEALCODE());
				pre.setString(4, cssdzccsxx[i].getTAXAMOUNT_FLAG());
				pre.setDouble(5, cssdzccsxx[i].getANNUALTAXDUE());
				pre.setDouble(6, cssdzccsxx[i].getSUMTAXDEFAULT());
				pre.setDouble(7, cssdzccsxx[i].getSUMOVERDUE());
				pre.setDouble(8, cssdzccsxx[i].getSUMTAX());
				pre.setString(9, cssdzccsxx[i].getRETURNCODE());
				pre.setString(10, cssdzccsxx[i].getLOGINNAME());
				pre.setString(11, cssdzccsxx[i].getREVENUECODE());
				pre.setString(12, SJCJRQ);
				pre.setString(13, cssdzccsxx[i].getSJCJFS());
				pre.addBatch();

			}
			returnCode = pre.executeBatch();
			conn.commit();

			if (returnCode != null) {
				System.out.println("数据库---对账服务入参插入成功！");
				flag = true;
			} else {
				System.out.println("数据库---对账服务入参插入失败！");
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
		return flag;
	}

}
