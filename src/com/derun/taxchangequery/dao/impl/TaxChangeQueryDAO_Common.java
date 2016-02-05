package com.derun.taxchangequery.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.SqlText;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_RKMX_QS;
import com.derun.model.po.TaxConfirmno_CHK;
/**
 * @author MILI
 * @time 2014-5-5 17:50:12
 * @描述：批改查询 本地数据库交互类  公共
 * */
public class TaxChangeQueryDAO_Common {
	/**
	 * @author MILI
	 * @time 2014-5-5 17:52:27
	 * @描述：判断确认码是否有效
	 * */
	public List<TaxConfirmno_CHK> checkConfirm(String confirmno){
		List<TaxConfirmno_CHK> list_taxcon = new ArrayList<TaxConfirmno_CHK>();
		TaxConfirmno_CHK taxcon = null ;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = SqlText.R_03_RKMX_001;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, confirmno);
			rs = pre.executeQuery();
			while (rs.next()) {
				taxcon = new TaxConfirmno_CHK();
				taxcon.setSJCJRQ(rs.getDate("SJCJRQ"));							// 系统采集日期
				taxcon.setTAXQUERYNO(rs.getString("TAXQUERYNO"));				// 交易码
				taxcon.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));			// 本年车船税金额
				taxcon.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));			// 合计欠税金额
				taxcon.setSUMTAX(rs.getDouble("SUMTAX"));						// 合计金额
				taxcon.setSUMOVERDUE(rs.getDouble("SUMOVERDUE"));				// 合计滞纳金
				taxcon.setSPECIALCARTYPE(rs.getString("SPECIALCARTYPE"));		// 特殊车标志
				taxcon.setFIRSTREGISTERDATE(rs.getDate("FIRSTREGISTERDATE"));	// 车辆初始登记日期
				taxcon.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));	// 用于存储纳税类型
				taxcon.setTAXCONFIRMNO(confirmno);
				list_taxcon.add(taxcon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		return list_taxcon ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-6 9:41:30
	 * @描述：根据确认码查 入库明细
	 **/
	public SYJK_CCS_RKMX getRKMX(String confirmNo){
		SYJK_CCS_RKMX rkmx = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.R_03_RKMX_002;
			pre = conn.prepareStatement(sql);
			pre.setString(1, confirmNo);
			rs = pre.executeQuery();
			if (rs.next()) {
				rkmx = new SYJK_CCS_RKMX();
				rkmx.setANNUALTAXAMOUNT(rs.getDouble("ANNUALTAXAMOUNT"));		// 当期年单位税额
				rkmx.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));				// 本年车船税金额
				rkmx.setCHANGETYPE(rs.getString("CHANGETYPE"));					// 变更类型
				rkmx.setCLLX(rs.getString("CLLX")); 							// 车辆类型
				rkmx.setCOUNTTAXTYPE(rs.getString("COUNTTAXTYPE")); 			// 算税标志
				
				rkmx.setDEDUCTION(rs.getDouble("DEDUCTION")); 								// 减免金额
				rkmx.setDEDUCTIONDEPARTMENT(rs.getString("DEDUCTIONDEPARTMENT")); 			// 开具减免税凭证的税务机关名称
				rkmx.setDEDUCTIONDEPARTMENTCODE(rs.getString("DEDUCTIONDEPARTMENTCODE"));	// 开具减免税凭证的税务机关代码
				rkmx.setDEDUCTIONDOCUMENTNUMBER(rs.getString("DEDUCTIONDOCUMENTNUMBER"));	// 减免税凭证号
				rkmx.setDEDUCTIONDUECODE(rs.getString("DEDUCTIONDUECODE")); 				// 减免税原因代码
				rkmx.setDEDUCTIONDUEPROPORTION(rs.getDouble("DEDUCTIONDUEPROPORTION"));		// 减免比例
				rkmx.setDEDUCTIONDUETYPE(rs.getString("DEDUCTIONDUETYPE"));					// 减免税方案代码
				
				rkmx.setEXCEEDDATE(rs.getString("EXCEEDDATE"));					// 逾期时间
				rkmx.setEXCEEDDAYSCOUNT(rs.getInt("EXCEEDDAYSCOUNT")); 			// 逾期天数
				rkmx.setHPHM(rs.getString("HPHM")); 							// 号牌号码
				rkmx.setHPZL(rs.getString("HPZL")); 							// 号牌种类
				rkmx.setLOGGEDOUT(rs.getString("LOGGEDOUT"));					// 注销状态 0= 未注销 1=注销
				rkmx.setLOGINNAME(rs.getString("LOGINNAME"));					// 操作员
				rkmx.setOVERDUE(rs.getDouble("OVERDUE")); 						// 滞纳金
				rkmx.setPAYCOMPANYCODE(rs.getString("PAYCOMPANYCODE")); 		// 代收公司
				rkmx.setPAYDATE(rs.getString("PAYDATE"));						// 所属年度
				rkmx.setPLATFORMSTATE(rs.getString("PLATFORMSTATE"));			// 平台 0=代收
				rkmx.setRefusetype(rs.getString("REFUSETYPE"));			 		// 拒缴标志
				rkmx.setREVENUECODE(rs.getString("REVENUECODE"));				// 9000 税务机关编码
				rkmx.setSJCJRQ(rs.getString("SJCJRQ").substring(0, 19)); 						// 系统采集日期
				rkmx.setSTATUSDATE(rs.getString("STATUSDATE")); 				// 申报日期
				rkmx.setSUMOVERDUE(rs.getDouble("SUMOVERDUE")); 				// 合计滞纳金
				rkmx.setSUMTAX(rs.getDouble("SUMTAX")); 						// 合计金额
				rkmx.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));			// 合计欠税金额
				rkmx.setTAXAMOUNT_FLAG("3"); 	
				// 合计金额标志码
				rkmx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));		// 开具减免税凭证的税务机关代码
				rkmx.setTAXDEPARTMENT(rs.getString("TAXDEPARTMENT"));			// 开具完税凭证的税务机关名称
				rkmx.setTAXDEPARTMENTCODE(rs.getString("TAXDEPARTMENTCODE"));	// 开具完税凭证的税务机关代码
				rkmx.setTSBZ(rs.getString("TSBZ"));								// 退税标志
				rkmx.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));				// 确认码
				rkmx.setTAXDOCUMENTNUMBER(rs.getString("TAXDOCUMENTNUMBER"));	// 完税凭证号码
				rkmx.setTAXDUE(rs.getDouble("TAXDUE"));							// 当期应纳税额
				rkmx.setTAXENDDATE(rs.getString("EXCEEDDATE"));					// 逾期时间
				rkmx.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE")); 		// 纳税地区代码
				rkmx.setTAXPAYERIDENTIFICATIONCODE(rs
						.getString("TAXPAYERIDENTIFICATIONCODE"));				// 纳税人识别号
				rkmx.setTAXPAYERNAME(rs.getString("TAXPAYERNAME"));				// 纳税人名称
				rkmx.setTAXPRINTNO(rs.getString("TAXPRINTNO"));					// 打印码
				rkmx.setTAXQUERYNO(rs.getString("TAXQUERYNO"));					// 车船税交易码
				rkmx.setTAXREGISTRYNUMBER(rs.getString("TAXREGISTRYNUMBER"));	// 税务登记证号
				rkmx.setTAXSTARTDATE(rs.getString("TAXSTARTDATE"));				// 税款所属始期
				rkmx.setTAXENDDATE(rs.getString("TAXENDDATE"));					// 税款所属止期
				rkmx.setTAXUNITTYPECODE(rs.getString("TAXUNITTYPECODE"));		// 计税单位代码
				rkmx.setTOTALAMOUNT(rs.getDouble("TOTALAMOUNT"));				// 合计金额
				rkmx.setUNITRATE(rs.getDouble("UNITRATE")); 					// 单位计税金额
				rkmx.setVIN(rs.getString("VIN"));								// Vin a
				rkmx.setFIRSTREGISTERDATE(rs.getString("FIRSTREGISTERDATE"));
				rkmx.setSPECIALCARTYPE(rs.getString("SPECIALCARTYPE"));
				rkmx.setInsureStartDate(rs.getDate("INSURESTARTDATE"));
				rkmx.setInsureEndDate(rs.getDate("INSUREENDDATE"));
				rkmx.setCARSERIALNO(rs.getString("CARSERIALNO"));
				rkmx.setEngineNo(rs.getString("ENGINENO"));						//发动机号
				
				rkmx.setMOTORUSAGETYPECODE(rs.getString("MOTORUSAGETYPECODE"));
				rkmx.setMODEL(rs.getString("MODEL"));
				rkmx.setVEHICLETYPE(rs.getString("VEHICLETYPE"));
				rkmx.setRATEDPASSENGERCAPACITY(rs.getDouble("RATEDPASSENGERCAPACITY"));
				rkmx.setTONNAGE(rs.getDouble("TONNAGE"));
				rkmx.setWHOLEWEIGHT(rs.getDouble("WHOLEWEIGHT"));
				rkmx.setDISPLACEMENT(rs.getDouble("DISPLACEMENT"));
				rkmx.setPOWER(rs.getDouble("POWER"));
				rkmx.setFUELTYPE(rs.getString("FUELTYPE"));
				
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
//				if(conn != null)
//					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rkmx;
	}
	/**
	 * @author MILI
	 * @time 2014-5-6 9:41:30
	 * @描述：根据确认码查 入库明细
	 **/
	public List<SYJK_CCS_RKMX> getRKMXList(String confirmNo){
		List<SYJK_CCS_RKMX> list_rkmx = new ArrayList<SYJK_CCS_RKMX>();
		SYJK_CCS_RKMX rkmx = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.R_03_RKMX_002;
			pre = conn.prepareStatement(sql);
			pre.setString(1, confirmNo);
			rs = pre.executeQuery();
			while (rs.next()) {
				rkmx = new SYJK_CCS_RKMX();
				rkmx.setANNUALTAXAMOUNT(rs.getDouble("ANNUALTAXAMOUNT"));		// 当期年单位税额
				rkmx.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));				// 本年车船税金额
				rkmx.setCHANGETYPE(rs.getString("CHANGETYPE"));					// 变更类型
				rkmx.setCLLX(rs.getString("CLLX")); 							// 车辆类型
				rkmx.setCOUNTTAXTYPE(rs.getString("COUNTTAXTYPE")); 			// 算税标志
				
				rkmx.setDEDUCTION(rs.getDouble("DEDUCTION")); 								// 减免金额
				rkmx.setDEDUCTIONDEPARTMENT(rs.getString("DEDUCTIONDEPARTMENT")); 			// 开具减免税凭证的税务机关名称
				rkmx.setDEDUCTIONDEPARTMENTCODE(rs.getString("DEDUCTIONDEPARTMENTCODE"));	// 开具减免税凭证的税务机关代码
				rkmx.setDEDUCTIONDOCUMENTNUMBER(rs.getString("DEDUCTIONDOCUMENTNUMBER"));	// 减免税凭证号
				rkmx.setDEDUCTIONDUECODE(rs.getString("DEDUCTIONDUECODE")); 				// 减免税原因代码
				rkmx.setDEDUCTIONDUEPROPORTION(rs.getDouble("DEDUCTIONDUEPROPORTION"));		// 减免比例
				rkmx.setDEDUCTIONDUETYPE(rs.getString("DEDUCTIONDUETYPE"));					// 减免税方案代码
				
				rkmx.setEXCEEDDATE(rs.getString("EXCEEDDATE"));					// 逾期时间
				rkmx.setEXCEEDDAYSCOUNT(rs.getInt("EXCEEDDAYSCOUNT")); 			// 逾期天数
				rkmx.setHPHM(rs.getString("HPHM")); 							// 号牌号码
				rkmx.setHPZL(rs.getString("HPZL")); 							// 号牌种类
				rkmx.setLOGGEDOUT(rs.getString("LOGGEDOUT"));					// 注销状态 0= 未注销 1=注销
				rkmx.setLOGINNAME(rs.getString("LOGINNAME"));					// 操作员
				rkmx.setOVERDUE(rs.getDouble("OVERDUE")); 						// 滞纳金
				rkmx.setPAYCOMPANYCODE(rs.getString("PAYCOMPANYCODE")); 		// 代收公司
				rkmx.setPAYDATE(rs.getString("PAYDATE"));						// 所属年度
				rkmx.setPLATFORMSTATE(rs.getString("PLATFORMSTATE"));			// 平台 0=代收
				rkmx.setRefusetype(rs.getString("REFUSETYPE"));			 		// 拒缴标志
				rkmx.setREVENUECODE(rs.getString("REVENUECODE"));				// 9000 税务机关编码
				rkmx.setSJCJRQ(rs.getString("SJCJRQ").substring(0, 19)); 						// 系统采集日期
				rkmx.setSTATUSDATE(rs.getString("STATUSDATE")); 				// 申报日期
				rkmx.setSUMOVERDUE(rs.getDouble("SUMOVERDUE")); 				// 合计滞纳金
				rkmx.setSUMTAX(rs.getDouble("SUMTAX")); 						// 合计金额
				rkmx.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));			// 合计欠税金额
				rkmx.setTAXAMOUNT_FLAG("3"); 	
				// 合计金额标志码
				rkmx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));		// 开具减免税凭证的税务机关代码
				rkmx.setTAXDEPARTMENT(rs.getString("TAXDEPARTMENT"));			// 开具完税凭证的税务机关名称
				rkmx.setTAXDEPARTMENTCODE(rs.getString("TAXDEPARTMENTCODE"));	// 开具完税凭证的税务机关代码
				
				rkmx.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));				// 确认码
				rkmx.setTAXDOCUMENTNUMBER(rs.getString("TAXDOCUMENTNUMBER"));	// 完税凭证号码
				rkmx.setTAXDUE(rs.getDouble("TAXDUE"));							// 当期应纳税额
				rkmx.setTAXENDDATE(rs.getString("EXCEEDDATE"));					// 逾期时间
				rkmx.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE")); 		// 纳税地区代码
				rkmx.setTAXPAYERIDENTIFICATIONCODE(rs
						.getString("TAXPAYERIDENTIFICATIONCODE"));				// 纳税人识别号
				rkmx.setTAXPAYERNAME(rs.getString("TAXPAYERNAME"));				// 纳税人名称
				rkmx.setTAXPRINTNO(rs.getString("TAXPRINTNO"));					// 打印码
				rkmx.setTAXQUERYNO(rs.getString("TAXQUERYNO"));					// 车船税交易码
				rkmx.setTAXREGISTRYNUMBER(rs.getString("TAXREGISTRYNUMBER"));	// 税务登记证号
				rkmx.setTAXSTARTDATE(rs.getString("TAXSTARTDATE"));				// 税款所属始期
				rkmx.setTAXENDDATE(rs.getString("TAXENDDATE"));					// 税款所属止期
				rkmx.setTAXUNITTYPECODE(rs.getString("TAXUNITTYPECODE"));		// 计税单位代码
				rkmx.setTOTALAMOUNT(rs.getDouble("TOTALAMOUNT"));				// 合计金额
				rkmx.setUNITRATE(rs.getDouble("UNITRATE")); 					// 单位计税金额
				rkmx.setVIN(rs.getString("VIN"));								// Vin a
				rkmx.setFIRSTREGISTERDATE(rs.getString("FIRSTREGISTERDATE"));
				rkmx.setSPECIALCARTYPE(rs.getString("SPECIALCARTYPE"));
				rkmx.setInsureStartDate(rs.getDate("INSURESTARTDATE"));
				rkmx.setInsureEndDate(rs.getDate("INSUREENDDATE"));
				rkmx.setCARSERIALNO(rs.getString("CARSERIALNO"));
				rkmx.setEngineNo(rs.getString("ENGINENO"));						//发动机号
				list_rkmx.add(rkmx);
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
//				if(conn != null)
//					conn.close();
			} catch (SQLException e) {
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list_rkmx;
	}
	/**
	 * @author MILI
	 * @time 2014-7-15 13:46:42
	 * @描述：查询欠税信息
	 * */
	public List<SYJK_CCS_RKMX_QS> getRKMX_QS(String confirmNo){
		SYJK_CCS_RKMX_QS rkmx_qs = null;
		List<SYJK_CCS_RKMX_QS> list_rkmx_qs = new ArrayList<SYJK_CCS_RKMX_QS>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.R_09_RKMX_QS_001;
			pre = conn.prepareStatement(sql);
			pre.setString(1, confirmNo);
			rs = pre.executeQuery();
			while (rs.next()) {
				rkmx_qs = new SYJK_CCS_RKMX_QS();
				rkmx_qs.setANNUALTAXAMOUNT(rs.getDouble("ANNUALTAXAMOUNT"));		// 当期年单位税额
				rkmx_qs.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));				// 本年车船税金额
				rkmx_qs.setCHANGETYPE(rs.getString("CHANGETYPE"));					// 变更类型
//				rkmx_qs.setCLLX(rs.getString("CLLX")); 							// 车辆类型
				rkmx_qs.setCOUNTTAXTYPE(rs.getString("COUNTTAXTYPE")); 			// 算税标志
				
				rkmx_qs.setDEDUCTION(rs.getDouble("DEDUCTION")); 								// 减免金额
				rkmx_qs.setDEDUCTIONDEPARTMENT(rs.getString("DEDUCTIONDEPARTMENT")); 			// 开具减免税凭证的税务机关名称
				rkmx_qs.setDEDUCTIONDEPARTMENTCODE(rs.getString("DEDUCTIONDEPARTMENTCODE"));	// 开具减免税凭证的税务机关代码
				rkmx_qs.setDEDUCTIONDOCUMENTNUMBER(rs.getString("DEDUCTIONDOCUMENTNUMBER"));	// 减免税凭证号
				rkmx_qs.setDEDUCTIONDUECODE(rs.getString("DEDUCTIONDUECODE")); 					// 减免税原因代码
				rkmx_qs.setDEDUCTIONDUEPROPORTION(rs.getDouble("DEDUCTIONDUEPROPORTION"));		// 减免比例
				rkmx_qs.setDEDUCTIONDUETYPE(rs.getString("DEDUCTIONDUETYPE"));					// 减免税方案代码
				
				rkmx_qs.setEXCEEDDATE(rs.getDate("EXCEEDDATE"));					// 逾期时间
				rkmx_qs.setEXCEEDDAYSCOUNT(rs.getInt("EXCEEDDAYSCOUNT")); 			// 逾期天数
				rkmx_qs.setHPHM(rs.getString("HPHM")); 							// 号牌号码
				rkmx_qs.setHPZL(rs.getString("HPZL")); 							// 号牌种类
				rkmx_qs.setLOGGEDOUT(rs.getString("LOGGEDOUT"));					// 注销状态 0= 未注销 1=注销
				rkmx_qs.setLOGINNAME(rs.getString("LOGINNAME"));					// 操作员
				rkmx_qs.setOVERDUE(rs.getDouble("OVERDUE")); 						// 滞纳金
				rkmx_qs.setPAYCOMPANYCODE(rs.getString("PAYCOMPANYCODE")); 		// 代收公司
				rkmx_qs.setPAYDATE(rs.getString("PAYDATE"));						// 所属年度
				rkmx_qs.setPLATFORMSTATE(rs.getString("PLATFORMSTATE"));			// 平台 0=代收
				rkmx_qs.setREFUSETYPE(rs.getString("REFUSETYPE"));			 		// 拒缴标志
				rkmx_qs.setREVENUECODE(rs.getString("REVENUECODE"));				// 9000 税务机关编码
				rkmx_qs.setSJCJRQ(rs.getDate("SJCJRQ")); 						// 系统采集日期
				rkmx_qs.setSTATUSDATE(rs.getDate("STATUSDATE")); 				// 申报日期
				rkmx_qs.setSUMOVERDUE(rs.getDouble("SUMOVERDUE")); 				// 合计滞纳金
				rkmx_qs.setSUMTAX(rs.getDouble("SUMTAX")); 						// 合计金额
				rkmx_qs.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));			// 合计欠税金额
				rkmx_qs.setTAXAMOUNT_FLAG("3"); 	
				// 合计金额标志码
				rkmx_qs.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));		// 开具减免税凭证的税务机关代码
				rkmx_qs.setTAXDEPARTMENT(rs.getString("TAXDEPARTMENT"));			// 开具完税凭证的税务机关名称
				rkmx_qs.setTAXDEPARTMENTCODE(rs.getString("TAXDEPARTMENTCODE"));	// 开具完税凭证的税务机关代码
				
				rkmx_qs.setTAXDOCUMENTNUMBER(rs.getString("TAXDOCUMENTNUMBER"));	// 完税凭证号码
				
				rkmx_qs.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));				// 确认码
				rkmx_qs.setTAXDUE(rs.getDouble("TAXDUE"));							// 当期应纳税额
				rkmx_qs.setTAXENDDATE(rs.getDate("EXCEEDDATE"));					// 逾期时间
				rkmx_qs.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE")); 		// 纳税地区代码
				rkmx_qs.setTAXPAYERIDENTIFICATIONCODE(rs
						.getString("TAXPAYERIDENTIFICATIONCODE"));				// 纳税人识别号
				rkmx_qs.setTAXPAYERNAME(rs.getString("TAXPAYERNAME"));				// 纳税人名称
				rkmx_qs.setTAXPRINTNO(rs.getString("TAXPRINTNO"));					// 打印码
				rkmx_qs.setTAXQUERYNO(rs.getString("TAXQUERYNO"));					// 车船税交易码
				rkmx_qs.setTAXREGISTRYNUMBER(rs.getString("TAXREGISTRYNUMBER"));	// 税务登记证号
				rkmx_qs.setTAXSTARTDATE(rs.getDate("TAXSTARTDATE"));				// 税款所属始期
				rkmx_qs.setTAXENDDATE(rs.getDate("TAXENDDATE"));					// 税款所属止期
				rkmx_qs.setTAXUNITTYPECODE(rs.getString("TAXUNITTYPECODE"));		// 计税单位代码
				rkmx_qs.setTOTALAMOUNT(rs.getDouble("TOTALAMOUNT"));				// 合计金额
				rkmx_qs.setUNITRATE(rs.getDouble("UNITRATE")); 					// 单位计税金额
				rkmx_qs.setVIN(rs.getString("VIN"));								// Vin a
				rkmx_qs.setFIRSTREGISTERDATE(rs.getDate("FIRSTREGISTERDATE"));
				rkmx_qs.setSPECIALCARTYPE(rs.getString("SPECIALCARTYPE"));
				rkmx_qs.setINSURESTARTDATE(rs.getDate("INSURESTARTDATE"));
				rkmx_qs.setINSUREENDDATE(rs.getDate("INSUREENDDATE"));
				rkmx_qs.setCARSERIALNO(rs.getString("CARSERIALNO"));
				rkmx_qs.setENGINENO(rs.getString("ENGINENO"));						//发动机号
				list_rkmx_qs.add(rkmx_qs);
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
//				if(conn != null)
//					conn.close();
			} catch (SQLException e) {
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list_rkmx_qs ;
	}
}
