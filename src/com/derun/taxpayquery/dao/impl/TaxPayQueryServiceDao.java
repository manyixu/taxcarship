package com.derun.taxpayquery.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.derun.beans.AnnualTax_Type;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.TaxPayQueryReqInfo;
import com.derun.beans.Tax_Type;
import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.ExeSQL;
import com.derun.common.db.SqlText;
import com.derun.common.util.DateUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSNSXXCXCC;
import com.derun.model.po.SYJK_CCS_CCSNSXXCXRC;

/**
 * @author 郑艳英
 * 
 * @date 2014-5-5
 * 
 *       车船税纳税信息数据库操作类
 * @version
 */
public class TaxPayQueryServiceDao {
	  //数据库操作类
	  ExeSQL exeSql = new ExeSQL();
	  //车船税纳税信息数据库sql类
	  TaxPayQueryServiceSql taxPayQueryServiceSql = new TaxPayQueryServiceSql();
	// 根据单一条件到入库明细表中查询纳税信息
	public List<Map<String, String>> getCondition(TaxPayQueryReqInfo taxPayQueryReqInfo, String sql, String flag) {
		List<Map<String, String>> listMap = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		Map<String, String> map = null;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, taxPayQueryReqInfo.getVehicleInfo().getVIN());
			if (flag.equals("2")) {
				pre.setString(2, taxPayQueryReqInfo.getVehicleInfo().getLicensePlateNo());
				pre.setString(3, taxPayQueryReqInfo.getVehicleInfo().getLicensePlateType());
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				if (listMap == null) {
					listMap = new ArrayList<Map<String, String>>();
				}
				map = new HashMap<String, String>();
				map.put("TAXQUERYNO", rs.getString("TAXQUERYNO"));// 车船税交易码
				map.put("TAXCONFIRMNO", rs.getString("TAXCONFIRMNO"));// 确认码
				map.put("DECLAREDSTATUS", rs.getString("PLATFORMSTATE"));// 平台状态/ 0=代收// 1=申报（完税）4=拒缴
				map.put("TAXAMOUNT_FLAG", rs.getString("TAXAMOUNT_FLAG"));// 合计金额标志码
				map.put("ANNUALTAXDUE", String.valueOf(rs.getDouble("ANNUALTAXDUE")));// 本年车船税金额
				map.put("SUMTAXDEFAULT", String.valueOf(rs.getDouble("SUMTAXDEFAULT")));// 合计欠税金额
				map.put("SUMOVERDUE", String.valueOf(rs.getDouble("SUMOVERDUE")));// 合计滞纳金
				map.put("SUMTAX", String.valueOf(rs.getDouble("SUMTAX")));// 合计金额
				map.put("CALCTAXFLAG", rs.getString("COUNTTAXTYPE"));// 变更类型// 0=确认// 4=退保，2=批改
				map.put("CHANGETYPE", rs.getString("CHANGETYPE"));// 变更类型 0=确认// 4=退保，2=批改
				map.put("TSBZ", rs.getString("TSBZ"));// 退税标志
				listMap.add(map);
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

		return listMap;
	}

	// 根据多个条件去入库明细中查询纳税信息
	public List<Map<String, String>> getConditions(TaxPayQueryReqInfo taxPayQueryReqInfo, String sql, String flag) {
		List<Map<String, String>> ListMap = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		Map<String, String> map = null;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, taxPayQueryReqInfo.getVehicleInfo().getVIN());
			if (flag.equals("2")) {
				pre.setString(2, taxPayQueryReqInfo.getVehicleInfo().getLicensePlateNo());
				pre.setString(3, taxPayQueryReqInfo.getVehicleInfo().getLicensePlateType());
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				map = new HashMap<String, String>();
				if (ListMap == null) {
					ListMap = new ArrayList<Map<String, String>>();
				}
				map.put("TAXQUERYNO", rs.getString("TAXQUERYNO"));// 交易码
				map.put("TAXCONFIRMNO", rs.getString("TAXCONFIRMNO"));// 确认码
				map.put("DECLAREDSTATUS", rs.getString("PLATFORMSTATE"));// 平台状态
				map.put("TAXAMOUNT_FLAG", rs.getString("TAXAMOUNT_FLAG"));// 合计金额标志
				map.put("ANNUALTAXDUE", String.valueOf(rs.getDouble("ANNUALTAXDUE")));// 本年车船税金额
				map.put("SUMTAXDEFAULT", String.valueOf(rs.getDouble("SUMTAXDEFAULT")));// 合计欠税
				map.put("SUMOVERDUE", String.valueOf(rs.getDouble("SUMOVERDUE")));// 合计滞纳金
				map.put("SUMTAX", String.valueOf(rs.getDouble("SUMTAX")));// 合计
				map.put("CALCTAXFLAG", rs.getString("COUNTTAXTYPE"));// 算税标志
				map.put("TOTALAMOUNT", String.valueOf(rs.getDouble("TOTALAMOUNT")));// 合计金额
				map.put("TAXDUE", String.valueOf(rs.getDouble("TAXDUE")));// 当期应交
				map.put("TSBZ", rs.getString("TSBZ"));// 退税标志
				ListMap.add(map);
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

		return ListMap;
	}

	// 根据用多个条件查询出来信息的确认码去入库明细中查询纳税信息
	public List<Map<String, String>> getConfirmNo(String confirm) {
		List<Map<String, String>> listMap = null;
		Map<String, String> map = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = SqlText.R_08_RKMX_003;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, confirm);
			rs = pre.executeQuery();

			if (rs.next()) {
				map = new HashMap<String, String>();
				if (listMap == null) {
					listMap = new ArrayList<Map<String, String>>();
				}
				map.put("CHANGETYPE", rs.getString("CHANGETYPE"));// 变更类型
				map.put("TAXQUERYNO", rs.getString("TAXQUERYNO"));// 交易码
				map.put("TAXCONFIRMNO", rs.getString("TAXCONFIRMNO"));// 确认码
				map.put("DECLAREDSTATUS", rs.getString("PLATFORMSTATE"));// 平台状态
				map.put("TAXAMOUNT_FLAG", rs.getString("TAXAMOUNT_FLAG"));// 合计金额标志
				map.put("ANNUALTAXDUE", String.valueOf(rs.getDouble("ANNUALTAXDUE")));// 本年车船税金额
				map.put("SUMTAXDEFAULT", String.valueOf(rs.getDouble("SUMTAXDEFAULT")));// 合计欠税
				map.put("SUMOVERDUE", String.valueOf(rs.getDouble("SUMOVERDUE")));// 合计滞纳金
				map.put("SUMTAX", String.valueOf(rs.getDouble("SUMTAX")));// 合计
				map.put("CALCTAXFLAG", rs.getString("COUNTTAXTYPE"));// 算税标志
				map.put("TOTALAMOUNT", String.valueOf(rs.getDouble("TOTALAMOUNT")));// 合计金额
				map.put("TAXDUE", String.valueOf(rs.getDouble("TAXDUE")));// 当期应交
				map.put("TSBZ", rs.getString("TSBZ"));// 退税标志
				listMap.add(map);
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

		return listMap;
	}

	// 在入库明细表中查询不等于变更类型4的记录
	public List<Map<String, String>> getNoFour(String confrimNo) {
		List<Map<String, String>> listMap = null;
		Map<String, String> map = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = SqlText.R_08_RKMX_004;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, confrimNo);
			rs = pre.executeQuery();
			if (rs.next()) {
				listMap = new ArrayList<Map<String, String>>();
				map = new HashMap<String, String>();
				map.put("TAXQUERYNO", rs.getString("TAXQUERYNO"));// 交易码
				map.put("TAXCONFIRMNO", rs.getString("TAXCONFIRMNO"));// 确认码
				map.put("DECLAREDSTATUS", rs.getString("PLATFORMSTATE"));// 平台状态
				map.put("TAXAMOUNT_FLAG", rs.getString("TAXAMOUNT_FLAG"));// 合计金额标志
				map.put("ANNUALTAXDUE", String.valueOf(rs.getDouble("ANNUALTAXDUE")));// 本年车船税金额
				map.put("SUMTAXDEFAULT", String.valueOf(rs.getDouble("SUMTAXDEFAULT")));// 合计欠税
				map.put("SUMOVERDUE", String.valueOf(rs.getDouble("SUMOVERDUE")));// 合计滞纳金
				map.put("SUMTAX", String.valueOf(rs.getDouble("SUMTAX")));// 合计
				map.put("CALCTAXFLAG", rs.getString("COUNTTAXTYPE"));// 算税标志
				map.put("CHANGETYPE", rs.getString("CHANGETYPE"));// 变更类型
				map.put("TOTALAMOUNT", String.valueOf(rs.getDouble("TOTALAMOUNT")));// 合计金额
				map.put("TAXDUE", String.valueOf(rs.getDouble("TAXDUE")));// 当期应交
				map.put("TSBZ", rs.getString("TSBZ"));// 退税标志
				listMap.add(map);
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

		return listMap;
	}

	// 从入库明细获取上次退保的钱
	public Map<String, Double> getSumTax(String confirmNo) {
		Map<String, Double> map = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.R_08_RKMX_005;;
			pre = conn.prepareStatement(sql);
			pre.setString(1, confirmNo);
			rs = pre.executeQuery();
			while (rs.next()) {
				map = new HashMap<String, Double>();
				map.put("SUMTAX", rs.getDouble("SUMTAX"));
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
		return map;
	}

	// 从入库明细获取车船税信息
	public Tax_Type getTaxInfo(String confirmNo) {
		PreparedStatement pre = null;
		Tax_Type TaxInfo = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = SqlText.R_08_RKMX_006;;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, confirmNo);
			rs = pre.executeQuery();
			if (rs.next()) {
				TaxInfo = new Tax_Type();
				TaxInfo.setTaxTermTypeCode("08");// // 税种类型代码,Y
				TaxInfo.setTaxConditionCode(rs.getString("taxConditionCode"));// 纳税类型代码,Y
				TaxInfo.setTaxPayerIdentificationCode(rs.getString("TAXPAYERIDENTIFICATIONCODE"));// 纳税人识别号
				TaxInfo.setTaxPayerName(rs.getString("TAXPAYERNAME"));// 纳税人名称
				TaxInfo.setTaxRegistryNumber(rs.getString("TAXREGISTRYNUMBER"));// 税务登记证号
				TaxInfo.setPayCompanyCode(rs.getString("PAYCOMPANYCODE"));// 代收公司
				TaxInfo.setCurrentTaxDue(getAnnualTaxType(conn, confirmNo));// 本年的纳税对象
				AnnualTax_Type[] delinquent = new AnnualTax_Type[0]; // 欠税信息对象
				TaxInfo.setDelinquentTaxDue(delinquent);// 欠税信息数组
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

		return TaxInfo;
	}

	// 从入库明细获得本年纳税信息对象
	public AnnualTax_Type getAnnualTaxType(Connection conn, String confirmNo) {
		PreparedStatement pre = null;
		ResultSet rs = null;
		AnnualTax_Type annualTax_Type = null;
		String sql = SqlText.R_08_RKMX_007;
		try {
			pre = conn.prepareStatement(sql);
			pre.setString(1, confirmNo);
			rs = pre.executeQuery();
			while (rs.next()) {
				// *************本年纳税信息对象*****************
				annualTax_Type = new AnnualTax_Type();
				annualTax_Type.setTaxLocationCode(rs.getString("TAXLOCATIONCODE"));// 纳税地区代码
				annualTax_Type.setTaxStartDate(rs.getString("TAXSTARTDATE").substring(0, 10));// 税款所属始期
				annualTax_Type.setTaxEndDate(rs.getString("TAXENDDATE").substring(0, 10));// 税款所属止期
				annualTax_Type.setTaxUnitTypeCode(rs.getString("TAXUNITTYPECODE"));// 计税单位代码
				annualTax_Type.setUnitRate(rs.getDouble("UNITRATE"));// 单位计税金额
				annualTax_Type.setAnnualTaxAmount(rs.getDouble("ANNUALTAXAMOUNT"));// 当期年单位税额

				// *************使用完税对象*****************
				Paid_Type paid = new Paid_Type();
				if ("P".equals(rs.getString("TAXCONDITIONCODE")) == true) {
					paid.setTaxDepartment(rs.getString("TAXDEPARTMENT"));// 开具减免（完）税凭证的税务机关名称,Y
					paid.setTaxDepartmentCode(rs.getString("TAXDEPARTMENTCODE"));// 开具减免（完）税凭证的税务机关代码,Y
					paid.setTaxDocumentNumber(rs.getString("TAXDOCUMENTNUMBER"));// 完税凭证号码
				} else {
					paid.setTaxDepartment("");// 开具减免（完）税凭证的税务机关名称,Y
					paid.setTaxDepartmentCode("");// 开具减免（完）税凭证的税务机关代码,Y
					paid.setTaxDocumentNumber("");// 完税凭证号码
				}

				annualTax_Type.setPaid(paid);// 使用完税对象
				// ***************适用减免税对像*****************
				Derate_Type derate = new Derate_Type();// 适用减免税对像
				derate.setDeduction(rs.getDouble("DEDUCTION"));// 减免金额
				derate.setDeductionDueProportion(rs
						.getDouble("DEDUCTIONDUEPROPORTION"));// 减免比例
				if ("C".equals(rs.getString("TAXCONDITIONCODE"))|| "E".equals(rs.getString("TAXCONDITIONCODE"))) {
					derate.setDeductionDueCode(rs.getString("DEDUCTIONDUECODE"));// 减免税原因代码,Y
					derate.setDeductionDueType(rs.getString("DEDUCTIONDUETYPE"));// 减免税方案代码,Y
					derate.setDeductionDocumentNumber(rs.getString("DEDUCTIONDOCUMENTNUMBER"));// 减免税凭证号
					derate.setTaxDepartment(rs.getString("DEDUCTIONDEPARTMENT"));// 开具减免（完）税凭证的税务机关名称,Y
					derate.setTaxDepartmentCode(rs.getString("DEDUCTIONDEPARTMENTCODE"));// 开具减免（完）税凭证的税务机关代码,Y
				} else {
					derate.setDeductionDocumentNumber("");// 减免税凭证号
					derate.setTaxDepartment("");// 开具减免（完）税凭证的税务机关名称,Y
					derate.setTaxDepartmentCode("");// 开具减免（完）税凭证的税务机关代码,Y
				}
				annualTax_Type.setDerate(derate);// 适用减免税对像
				// *****************结束*****************************
				annualTax_Type.setTaxDue(rs.getDouble("TAXDUE"));// 当期应纳税额
				annualTax_Type.setExceedDate(rs.getString("EXCEEDDATE"));// 逾期时间
				annualTax_Type.setExceedDaysCount(rs.getInt("EXCEEDDAYSCOUNT"));// 逾期天数
				annualTax_Type.setOverDue(rs.getDouble("OVERDUE"));// 滞纳金
				annualTax_Type.setTotalAmount(rs.getDouble("TOTALAMOUNT"));// 合计金额
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
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
		return annualTax_Type;
	}

	// 从入库明细中获取合计欠税金额
	public Map<String, String> getSUMTAXDEFAULT(String confirmNo) {
		Map<String, String> map = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.R_08_RKMX_008;
			pre = conn.prepareStatement(sql);
			pre.setString(1, confirmNo);
			rs = pre.executeQuery();
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("SUMTAXDEFAULT", String.valueOf(rs.getDouble("SUMTAXDEFAULT")));
				map.put("SUMOVERDUE", String.valueOf(rs.getDouble("SUMOVERDUE")));
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
		return map;
	}

	// 保存纳税信息请求入参信息
	public boolean savePayReq(SYJK_CCS_CCSNSXXCXRC ccsnsxxcxrc) {
		String reqSql = taxPayQueryServiceSql.getTaxPayQueryReqSql(ccsnsxxcxrc);
		boolean flag = exeSql.execUpdateSQL(reqSql);
		return flag;
	}

	// 保存纳税信息请求出参信息
	 public boolean savePayRes(SYJK_CCS_CCSNSXXCXCC ccsnsxxcxcc){
		 String resSql = taxPayQueryServiceSql.getTaxPayQueryResSql(ccsnsxxcxcc);
		 boolean flag = exeSql.execUpdateSQL(resSql);
    	return flag;
    }

	 //取得纳税信息
	public List<Map<Object, Object>> getRKMX(TaxPayQueryReqInfo taxPayQueryReqInfo, String sql,String falg) {
		List<Map<Object, Object>> list_map = new ArrayList<Map<Object, Object>>() ;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		Map<Object, Object> map = null;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			if("1".equals(falg)){
			  pre.setString(1, taxPayQueryReqInfo.getVehicleInfo().getVIN());
			  pre.setString(2, taxPayQueryReqInfo.getVehicleInfo().getLicensePlateNo());
			  pre.setString(3, taxPayQueryReqInfo.getVehicleInfo().getEngineNo());
			}else if("2".equals(falg)){
				pre.setString(1, taxPayQueryReqInfo.getVehicleInfo().getVIN());
				pre.setString(2, taxPayQueryReqInfo.getVehicleInfo().getLicensePlateNo());
				pre.setString(3, taxPayQueryReqInfo.getVehicleInfo().getLicensePlateType());
				
			}else if("3".equals(falg)){
				pre.setString(1, taxPayQueryReqInfo.getVehicleInfo().getVIN());
				pre.setString(2, taxPayQueryReqInfo.getVehicleInfo().getEngineNo());
			}else{
				pre.setString(1, taxPayQueryReqInfo.getVehicleInfo().getVIN());
			}
			
			rs = pre.executeQuery();
			while(rs.next()) {
				map = new HashMap<Object, Object>();
				map.put("DECLAREDSTATUS", rs.getString("PLATFORMSTATE"));// 纳税状态/ 0=代收// 1=申报（完税）4=拒缴
				map.put("CALCTAXFLAG", rs.getString("COUNTTAXTYPE"));// 算税标志
				map.put("TaxTermTypeCode", "08");// 税种类型代码
				map.put("TAXCONDITIONCODE", rs.getString("TAXCONDITIONCODE"));// 纳税类型代码
				map.put("TAXREGISTRYNUMBER", rs.getString("TAXREGISTRYNUMBER"));// 税务登记证号
				map.put("TAXPAYERNAME", rs.getString("TAXPAYERNAME"));// 纳税人名称
				map.put("TAXPAYERIDENTIFICATIONCODE", rs.getString("TAXPAYERIDENTIFICATIONCODE"));// 纳税人识别号
				map.put("SJCJRQ", rs.getDate("SJCJRQ"));// 代收日期     mili 2014-12-23 16:11:16  代收日期 给 数据采集日期 中科软 截取时不正确
				map.put("PAYCOMPANYCODE", rs.getString("PAYCOMPANYCODE"));// 代收公司
				map.put("TAXLOCATIONCODE", rs.getString("TAXLOCATIONCODE"));// 纳税地区代码
				map.put("TAXSTARTDATE",rs.getString("TAXSTARTDATE").substring(0, 10));// 税款所属始期
				map.put("TAXENDDATE",rs.getString("TAXENDDATE").substring(0, 10));// 税款所属止期
				map.put("TAXUNITTYPECODE",rs.getString("TAXUNITTYPECODE"));// 计税单位代码
				map.put("UNITRATE",rs.getDouble("UNITRATE"));// 单位计税金额
				map.put("ANNUALTAXAMOUNT",rs.getDouble("ANNUALTAXAMOUNT"));// 当期年单位税额
				map.put("TAXDUE",rs.getDouble("TAXDUE"));// 当期应纳税额
				map.put("EXCEEDDATE",rs.getString("EXCEEDDATE"));// 逾期时间
				map.put("EXCEEDDAYSCOUNT",rs.getInt("EXCEEDDAYSCOUNT"));// 逾期天数
				map.put("OVERDUE",rs.getDouble("OVERDUE"));// 滞纳金
				map.put("TOTALAMOUNT",rs.getDouble("TOTALAMOUNT"));// 合计金额
				map.put("SPECIALCARTYPE", rs.getString("SPECIALCARTYPE"));//特殊车标志
				// 适用完税对象 Paid
				if ("P".equals(rs.getString("TAXCONDITIONCODE")) == true) {
					map.put("TAXDEPARTMENT",rs.getString("TAXDEPARTMENT"));// 开具减免（完）税凭证的税务机关名称,Y
					map.put("TAXDEPARTMENTCODE",rs.getString("TAXDEPARTMENTCODE"));// 开具减免（完）税凭证的税务机关代码,Y
					map.put("TAXDOCUMENTNUMBER",rs.getString("TAXDOCUMENTNUMBER"));// 完税凭证号码
				} else {
					map.put("TAXDEPARTMENT","");// 开具减免（完）税凭证的税务机关名称,Y
					map.put("TAXDEPARTMENTCODE","");// 开具减免（完）税凭证的税务机关代码,Y
					map.put("TAXDOCUMENTNUMBER","");// 完税凭证号码
				}
				// ***************适用减免税对像*****************
				
				map.put("DEDUCTION",rs.getDouble("DEDUCTION"));// 减免金额
				map.put("DEDUCTIONDUEPROPORTION",rs
						.getDouble("DEDUCTIONDUEPROPORTION"));// 减免比例
				if ("C".equals(rs.getString("TAXCONDITIONCODE"))|| "E".equals(rs.getString("TAXCONDITIONCODE"))) {
					map.put("DEDUCTIONDUECODE",rs.getString("DEDUCTIONDUECODE"));// 减免税原因代码,Y
					map.put("DEDUCTIONDUETYPE",rs.getString("DEDUCTIONDUETYPE"));// 减免税方案代码,Y
					map.put("DEDUCTIONDOCUMENTNUMBER",rs.getString("DEDUCTIONDOCUMENTNUMBER"));// 减免税凭证号
					map.put("DEDUCTIONDEPARTMENT",rs.getString("DEDUCTIONDEPARTMENT"));// 开具减免（完）税凭证的税务机关名称,Y
					map.put("DEDUCTIONDEPARTMENTCODE",rs.getString("DEDUCTIONDEPARTMENTCODE"));// 开具减免（完）税凭证的税务机关代码,Y
				} else {
					map.put("DEDUCTIONDOCUMENTNUMBER","");// 减免税凭证号
					map.put("DEDUCTIONDEPARTMENT","");// 开具减免（完）税凭证的税务机关名称,Y
					map.put("DEDUCTIONDEPARTMENTCODE","");// 开具减免（完）税凭证的税务机关代码,Y
				}
				 //车船税合计金额 TaxAmount
				// 车船税交易码,用于对账服务
				map.put("TAXDEALCODE", rs.getString("TAXQUERYNO"));// 交易码
				map.put("TAXCONFIRMNO", rs.getString("TAXCONFIRMNO"));//确认码
				map.put("TAXAMOUNT_FLAG", rs.getString("TAXAMOUNT_FLAG"));// 合计金额标志
				map.put("ANNUALTAXDUE", rs.getDouble("ANNUALTAXDUE"));// 本年车船税金额
				map.put("SUMTAXDEFAULT", rs.getDouble("SUMTAXDEFAULT"));// 合计欠税
				map.put("SUMOVERDUE", rs.getDouble("SUMOVERDUE"));// 合计滞纳金
				map.put("SumTax", rs.getDouble("SumTax"));// 合计金额
				map.put("PAYDATE", rs.getString("PAYDATE"));//所属年度
				list_map.add(map);
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

		return list_map;
	}
	
    //看有没有欠税信息
	public List<AnnualTax_Type> getQS(String taxConfirmNo) {
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		List<AnnualTax_Type> list = new ArrayList<AnnualTax_Type>();
		AnnualTax_Type delinquentTaxDue = null;
		
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.C_08_rkmx_qs_001;
			pre = conn.prepareStatement(sql);
			pre.setString(1, taxConfirmNo);
			rs = pre.executeQuery();
			while(rs.next()){
				delinquentTaxDue = new AnnualTax_Type();
				delinquentTaxDue.setAnnualTaxAmount(rs.getDouble("ANNUALTAXAMOUNT"));// 当期年单位税额
				delinquentTaxDue.setTaxLocationCode(rs.getString("TAXLOCATIONCODE"));// 纳税地区代码
				delinquentTaxDue.setTaxStartDate(DateUtil.getStringDate(rs.getDate("TAXSTARTDATE"),"yyyy-MM-dd"));// 税款所属始期
				delinquentTaxDue.setTaxEndDate(DateUtil.getStringDate(rs.getDate("TAXENDDATE"),"yyyy-MM-dd"));// 税款所属止期
				delinquentTaxDue.setTaxUnitTypeCode(rs.getString("TAXUNITTYPECODE"));// 计税单位代码
				delinquentTaxDue.setUnitRate(rs.getDouble("UNITRATE"));// 单位计税金额
				delinquentTaxDue.setTaxDue(rs.getDouble("TAXDUE"));// 当期应纳税额
				delinquentTaxDue.setExceedDate(DateUtil.getStringDate(rs.getDate("EXCEEDDATE"),"yyyy-MM-dd"));// 逾期时间
				delinquentTaxDue.setExceedDaysCount(rs.getInt("EXCEEDDAYSCOUNT"));// 逾期天数
				delinquentTaxDue.setOverDue(rs.getDouble("OVERDUE"));// 滞纳金
				delinquentTaxDue.setTotalAmount(rs.getDouble("TOTALAMOUNT"));// 合计金额
                Derate_Type derate = new Derate_Type();;// 适用减免税对象
                derate.setDeduction(rs.getDouble("DEDUCTION"));// 减免金额
                derate.setDeductionDueProportion(rs.getDouble("DEDUCTIONDUEPROPORTION"));// 减免比例
                derate.setDeductionDocumentNumber(rs.getString("DEDUCTIONDOCUMENTNUMBER"));// 减免税凭证号
				if ("C".equals(rs.getString("TAXCONDITIONCODE"))|| "E".equals(rs.getString("TAXCONDITIONCODE"))) {
					 derate.setDeduction(rs.getDouble("DEDUCTION"));// 减免金额
		             derate.setDeductionDueProportion(rs.getDouble("DEDUCTIONDUEPROPORTION"));// 减免比例
		             derate.setDeductionDocumentNumber(rs.getString("DEDUCTIONDOCUMENTNUMBER"));// 减免税凭证号
					 derate.setDeductionDueCode(rs.getString("DEDUCTIONDUECODE"));// 减免税原因代码,Y
					 derate.setDeductionDueType(rs.getString("DEDUCTIONDUETYPE"));// 减免税方案代码,Y
					 derate.setTaxDepartmentCode(rs.getString("DEDUCTIONDEPARTMENTCODE"));// 开具减免（完）税凭证的税务机关代码,Y
					 derate.setTaxDepartment(rs.getString("DEDUCTIONDEPARTMENT"));// 开具减免（完）税凭证的税务机关名称,Y 
				
				}
				list.add(delinquentTaxDue);
				 
				
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

}
