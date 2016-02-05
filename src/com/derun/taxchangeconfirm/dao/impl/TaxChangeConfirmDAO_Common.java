package com.derun.taxchangeconfirm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.SqlText;
import com.derun.common.util.DateUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSBGXX;
import com.derun.model.po.SYJK_CCS_RKMX;
/**
 * @author MILI
 * @time 2014-5-5 17:50:12
 * @描述：批改确认 本地数据库交互类  公共
 * */
public class TaxChangeConfirmDAO_Common {
	/**
	 * @author MILI
	 * @time 2014-5-9 16:14:16
	 * @描述： 根据变更查询码去查询出变更查询出参信息
	 */
	public SYJK_CCS_CCSBGCXCCJB getCCSBGCXCCJB(String changeQueryNO){
		SYJK_CCS_CCSBGCXCCJB bgccc = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = SqlText.R_04_CCSBGCXCCJB_001;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, changeQueryNO);
			rs = pre.executeQuery();
			if (rs.next()) {
				bgccc = new SYJK_CCS_CCSBGCXCCJB();
				bgccc.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));
				bgccc.setSUMOVERDUE(rs.getDouble("SUMOVERDUE"));
				bgccc.setSUMTAX(rs.getDouble("SUMTAX"));
				bgccc.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));
				bgccc.setSJCJRQ(DateUtil.getStringDates(rs.getString("SJCJRQ")));
				bgccc.setTAXQUERYNO(changeQueryNO);
				bgccc.setCLLX(rs.getString("CLLX"));
				bgccc.setCARMATCHID(rs.getString("CARMATCHID"));
				bgccc.setMOTORUSAGETYPECODE(rs.getString("MOTORUSAGETYPECODE")); 	// 使用性质
				bgccc.setMODEL(rs.getString("MODEL")); 					// 车辆型号
				bgccc.setVEHICLETYPE(rs.getString("VEHICLETYPE")); 		// 交管车辆类型
				bgccc.setRATEDPASSENGERCAPACITY(rs.getDouble("RATEDPASSENGERCAPACITY"));// 核定载客数
				bgccc.setTONNAGE(rs.getDouble("TONNAGE"));				// 核定载质量
				bgccc.setWHOLEWEIGHT(rs.getDouble("WHOLEWEIGHT"));		// 整备质量
				bgccc.setDISPLACEMENT(rs.getDouble("DISPLACEMENT"));	// 排量
				bgccc.setPOWER(rs.getDouble("POWER"));					// 功率
				bgccc.setFUELTYPE(rs.getString("FUELTYPE")); 			// 源种类
				bgccc.setENGINENO(rs.getString("ENGINENO"));			// 发动机号
				
				bgccc.setMOTORTYPECODE(rs.getString("MOTORTYPECODE"));
				bgccc.setMADEFACTORY(rs.getString("MADEFACTORY"));
				bgccc.setNOLICENSEFLAG(rs.getString("NOLICENSEFLAG"));
				bgccc.setISINSERT(rs.getString("ISINSERT"));
				
				bgccc.setVIN(rs.getString("VIN"));
				bgccc.setHPHM(rs.getString("HPHM"));
				bgccc.setHPZL(rs.getString("HPZL"));
				bgccc.setTAXAMOUNT_FL(rs.getString("TAXAMOUNT_FL"));
				bgccc.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));
				bgccc.setCHANGESUMTAX(rs.getDouble("CHANGESUMTAX"));
				bgccc.setFIRSTREGISTERDATE(rs.getDate("FIRSTREGISTERDATE"));
				bgccc.setSPECIALCARTYPE(rs.getString("SPECIALCARTYPE"));
				bgccc.setTSBZ(rs.getString("TSBZ"));
				bgccc.setCARSERIALNO(rs.getString("CARSERIALNO"));
			} 
		} catch (Exception e) {
			System.out.println("TaxChangeConfirmDAO_Common 56 ");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if (conn != null)
//				    conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bgccc ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-9 17:08:36
	 * @描述：根据变更查询码 查询 变更信息表
	 * */
	public SYJK_CCS_CCSBGXX getTaxConditionCode(String taxQueryCode){
		SYJK_CCS_CCSBGXX bgxx = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			String sql = SqlText.R_04_CCSBGXX_001 ;
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, taxQueryCode);
			rs = pre.executeQuery();
			if (rs.next()) {
				bgxx = new SYJK_CCS_CCSBGXX();
				bgxx.setTAXQUERYNO(taxQueryCode);								// 车船税查询码,唯一标示符
				bgxx.setTAXUNITTYPECODE(rs.getString("TAXTERMTYPECODE"));		// 税种类型代码,Y
				bgxx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));		// 纳税类型代码,Y
				bgxx.setTAXREGISTRYNUMBER(rs.getString("TAXREGISTRYNUMBER"));	// 税务登记证号
				bgxx.setTAXPAYERNAME(rs.getString("TAXPAYERNAME"));				// 纳税人名称
				bgxx.setTAXPAYERIDENTIFICATIONCODE(rs.getString("TAXPAYERIDENTIFICATIONCODE"));		// 纳税人识别号
				bgxx.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE"));		// 纳税地区代码
				bgxx.setTAXSTARTDATE(rs.getDate("TAXSTARTDATE"));				// 税款所属始期
				bgxx.setTAXENDDATE(rs.getDate("TAXENDDATE"));					// 税款所属止期
				bgxx.setPAYDATE(rs.getDate("PAYDATE"));							// 代收日期,Y
				bgxx.setTAXUNITTYPECODE(rs.getString("TAXUNITTYPECODE"));		// 计税单位代码
				bgxx.setUNITRATE(rs.getDouble("UNITRATE"));						// 单位计税金额
				bgxx.setANNUALTAXAMOUNT(rs.getDouble("ANNUALTAXAMOUNT"));		// 当期年单位税额
				
				bgxx.setTAXDEPARTMENTCODE(rs.getString("TAXDEPARTMENTCODE"));	// 开具减免税(完税)凭证的税务机关代码,Y
				bgxx.setTAXDEPARTMENT(rs.getString("TAXDEPARTMENT"));			// 开具减免税(完税)凭证的税务机关名称,Y
				bgxx.setTAXDOCUMENTNUMBER(rs.getString("TAXDOCUMENTNUMBER"));	// 完税凭证号码
				
				bgxx.setDEDUCTIONDUECODE(rs.getString("DEDUCTIONDUECODE"));		// 减免税原因代码,Y
				bgxx.setDEDUCTIONDUETYPE(rs.getString("DEDUCTIONDUETYPE"));		// 减免税方案代码,Y
				bgxx.setDEDUCTIONDUEPROPORTION(rs.getDouble("DEDUCTIONDUEPROPORTION"));		// 减免比例
				bgxx.setDEDUCTION(rs.getDouble("DEDUCTION"));					// 减免金额
				bgxx.setDEDUCTIONDOCUMENTNUMBER(rs.getString("DEDUCTIONDOCUMENTNUMBER"));	// 减免税凭证号
				bgxx.setDEPARTMENT(rs.getString("DEPARTMENT"));
				bgxx.setDEPARTMENTCODE(rs.getString("DEPARTMENTCODE"));

				
				bgxx.setTAXDUE(rs.getDouble("TAXDUE"));							// 当期应纳税额
				bgxx.setEXCEEDDATE(rs.getDate("EXCEEDDATE"));					// 逾期时间
				bgxx.setEXCEEDDAYSCOUNT(rs.getInt("EXCEEDDAYSCOUNT"));			// 逾期天数
				bgxx.setOVERDUE(rs.getDouble("OVERDUE"));						// 滞纳金
				bgxx.setTOTALAMOUNT(rs.getDouble("TOTALAMOUNT"));				// 合计金额
				bgxx.setSJCJRQ(rs.getDate("SJCJRQ"));							// 合计金额
				bgxx.setPAYCOMPANYCODE(rs.getString("PAYCOMPANYCODE"));
				bgxx.setTAXTERMTYPECODE(rs.getString("TAXTERMTYPECODE"));
			} 
		} catch (Exception e) {
			System.out.println("TaxChangeConfirmDAO_Common   139");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if	(conn != null)
//					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bgxx ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-9 17:34:55
	 * @描述：根据交易码（确认码或变更确认码）查询入库明细
	 * */
	public List<SYJK_CCS_RKMX> getRKMX(String taxConfirmNo) {
		List<SYJK_CCS_RKMX> list_rkmx = null ;
		SYJK_CCS_RKMX rkmx = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		ArrayList<SYJK_CCS_RKMX> rkmxList = new ArrayList<SYJK_CCS_RKMX>();
		String sql = SqlText.R_04_RKMX_001 ;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, taxConfirmNo);
			rs = pre.executeQuery();
			while (rs.next()) {
				rkmx = new SYJK_CCS_RKMX();
				rkmx.setANNUALTAXAMOUNT(rs.getDouble("ANNUALTAXAMOUNT"));		// 当期年单位税额
				rkmx.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));				// 本年车船税金额
				rkmx.setCHANGETYPE(rs.getString("CHANGETYPE"));					// 变更类型
				rkmx.setCLLX(rs.getString("CLLX")); 							// 车辆类型
				rkmx.setCOUNTTAXTYPE(rs.getString("COUNTTAXTYPE")); 			// 算税标志
				rkmx.setDEDUCTION(rs.getDouble("DEDUCTION")); 					// 减免金额
				rkmx.setDEDUCTIONDEPARTMENT(rs.getString("DEDUCTIONDEPARTMENT")); 			// 开具减免税凭证的税务机关名称
				rkmx.setDEDUCTIONDEPARTMENTCODE(rs.getString("DEDUCTIONDEPARTMENTCODE"));	// 开具减免税凭证的税务机关代码
				rkmx.setDEDUCTIONDOCUMENTNUMBER(rs.getString("DEDUCTIONDOCUMENTNUMBER"));	// 减免税凭证号
				rkmx.setDEDUCTIONDUECODE(rs.getString("DEDUCTIONDUECODE")); 	// 减免税原因代码
				rkmx.setDEDUCTIONDUEPROPORTION(rs.getDouble("DEDUCTIONDUEPROPORTION"));		// 减免比例
				rkmx.setDEDUCTIONDUETYPE(rs.getString("DEDUCTIONDUETYPE"));		// 减免税方案代码
				rkmx.setEXCEEDDATE(rs.getString("EXCEEDDATE"));					// 逾期时间
				rkmx.setEXCEEDDAYSCOUNT(rs.getInt("EXCEEDDAYSCOUNT")); 			// 逾期天数
				rkmx.setHPHM(rs.getString("HPHM")); 							// 号牌号码
				rkmx.setHPZL(rs.getString("HPZL")); 							// 号牌种类
				rkmx.setLOGGEDOUT(rs.getString("LOGGEDOUT"));					// 注销状态 0= 未注销 1=注销
				rkmx.setLOGINNAME(rs.getString("LOGINNAME"));					// 操作员
				rkmx.setOVERDUE(rs.getDouble("OVERDUE")); 						// 滞纳金
				rkmx.setPAYCOMPANYCODE(rs.getString("PAYCOMPANYCODE")); 		// 代收公司
				rkmx.setPLATFORMSTATE(rs.getString("PLATFORMSTATE"));			// 平台 0=代收
				rkmx.setRefusetype(rs.getString("REFUSETYPE")); 				// 拒缴标志
				rkmx.setREVENUECODE(rs.getString("REVENUECODE"));				// 9000 税务机关编码
				rkmx.setSJCJRQ(rs.getString("SJCJRQ")); 						// 系统采集日期
				rkmx.setSTATUSDATE(rs.getString("STATUSDATE")); 				// 申报日期
				rkmx.setSUMOVERDUE(rs.getDouble("SUMOVERDUE")); 				// 合计滞纳金
				rkmx.setSUMTAX(rs.getDouble("SUMTAX")); 						// 合计金额
				rkmx.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));			// 合计欠税金额
				rkmx.setTAXAMOUNT_FLAG("3"); 									// 合计金额标志码
				rkmx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));		// 开具减免税凭证的税务机关代码
				rkmx.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));				// 确认码
				rkmx.setTAXDEPARTMENT(rs.getString("TAXDEPARTMENT"));			// 开具完税凭证的税务机关名称
				rkmx.setTAXDEPARTMENTCODE(rs.getString("TAXDEPARTMENTCODE"));	// 开具完税凭证的税务机关代码
				rkmx.setTAXDOCUMENTNUMBER(rs.getString("TAXDOCUMENTNUMBER"));	// 完税凭证号码
				rkmx.setTAXDUE(rs.getDouble("TAXDUE"));							// 当期应纳税额
				rkmx.setTAXENDDATE(rs.getString("EXCEEDDATE"));					// 逾期时间
				rkmx.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE")); 		// 纳税地区代码
				rkmx.setTAXPAYERIDENTIFICATIONCODE(rs.getString("TAXPAYERIDENTIFICATIONCODE"));		// 纳税人识别号
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
				rkmxList.add(rkmx);
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
//				if (conn != null)
//					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return list_rkmx ;
	}
	/**
	 * @author Mili
	 * @time 2013-10-10 16:24:30
	 * @描述：批改确认查询欠税信息
	 * */
	public List<SYJK_CCS_CCSBGXX> getTaxConditionCodeMili(String taxQueryCode) {
		SYJK_CCS_CCSBGXX ccsxx = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		List<SYJK_CCS_CCSBGXX> List_ccsxx = new ArrayList<SYJK_CCS_CCSBGXX>();
		try {
			String sql = "SELECT TAXQUERYNO,TAXTERMTYPECODE,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,TAXPAYERIDENTIFICATIONCODE,"
					+ "TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,PAYDATE,TAXUNITTYPECODE,UNITRATE,"
					+ "ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,"
					+ "DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,DEPARTMENT,DEPARTMENTCODE,TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,"
					+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,PAYCOMPANYCODE FROM SYJK_CCS_CCSBGXX WHERE PARATYPE='1' AND TAXQUERYNO=? AND TAXESFLAG = '1' ORDER BY TAXSTARTDATE ";
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, taxQueryCode);
			rs = pre.executeQuery();
			while(rs.next()) {
				ccsxx = new SYJK_CCS_CCSBGXX();
				ccsxx.setTAXQUERYNO(taxQueryCode);			// 车船税查询码,唯一标示符
				ccsxx.setTAXTERMTYPECODE(rs.getString("TAXTERMTYPECODE"));		// 税种类型代码,Y
				ccsxx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));	// 纳税类型代码,Y
				ccsxx.setTAXREGISTRYNUMBER(rs.getString("TAXREGISTRYNUMBER"));	// 税务登记证号
				ccsxx.setTAXPAYERNAME(rs.getString("TAXPAYERNAME"));			// 纳税人名称
				ccsxx.setTAXPAYERIDENTIFICATIONCODE(rs.getString("TAXPAYERIDENTIFICATIONCODE"));	// 纳税人识别号
				ccsxx.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE"));		// 纳税地区代码
				ccsxx.setTAXSTARTDATE(rs.getDate("TAXSTARTDATE"));				// 税款所属始期
				ccsxx.setTAXENDDATE(rs.getDate("TAXENDDATE"));					// 税款所属止期
				ccsxx.setPAYDATE(rs.getDate("PAYDATE"));						// 代收日期,Y
				ccsxx.setTAXUNITTYPECODE(rs.getString("TAXUNITTYPECODE"));		// 计税单位代码
				ccsxx.setUNITRATE(rs.getDouble("UNITRATE"));					// 单位计税金额
				ccsxx.setANNUALTAXAMOUNT(rs.getDouble("ANNUALTAXAMOUNT"));		// 当期年单位税额
				ccsxx.setTAXDEPARTMENTCODE(rs.getString("TAXDEPARTMENTCODE"));	// 开具减免税(完税)凭证的税务机关代码,Y
				ccsxx.setTAXDEPARTMENT(rs.getString("TAXDEPARTMENT"));			// 开具减免税(完税)凭证的税务机关名称,Y
				ccsxx.setTAXDOCUMENTNUMBER(rs.getString("TAXDOCUMENTNUMBER"));	// 完税凭证号码
				ccsxx.setDEDUCTIONDUECODE(rs.getString("DEDUCTIONDUECODE"));	// 减免税原因代码,Y
				ccsxx.setDEDUCTIONDUETYPE(rs.getString("DEDUCTIONDUETYPE"));	// 减免税方案代码,Y
				ccsxx.setDEDUCTIONDUEPROPORTION(rs.getDouble("DEDUCTIONDUEPROPORTION"));	// 减免比例
				ccsxx.setDEDUCTION(rs.getDouble("DEDUCTION"));					// 减免金额
				ccsxx.setDEDUCTIONDOCUMENTNUMBER(rs.getString("DEDUCTIONDOCUMENTNUMBER"));	// 减免税凭证号
				ccsxx.setDEPARTMENT(rs.getString("DEPARTMENT"));
				ccsxx.setDEPARTMENTCODE(rs.getString("DEPARTMENTCODE"));
				ccsxx.setTAXDUE(rs.getDouble("TAXDUE"));						// 当期应纳税额
				ccsxx.setEXCEEDDATE(rs.getDate("EXCEEDDATE"));					// 逾期时间
				ccsxx.setEXCEEDDAYSCOUNT(rs.getInt("EXCEEDDAYSCOUNT"));			// 逾期天数
				ccsxx.setOVERDUE(rs.getDouble("OVERDUE"));						// 滞纳金
				ccsxx.setTOTALAMOUNT(rs.getDouble("TOTALAMOUNT"));				// 合计金额
				ccsxx.setSJCJRQ(rs.getDate("SJCJRQ"));							// 合计金额
				ccsxx.setPAYCOMPANYCODE(rs.getString("PAYCOMPANYCODE"));		// 代收公司
				List_ccsxx.add(ccsxx);
			} 
		} catch (Exception e) {
			ccsxx = null;
			System.out.println("查询纳税类别失败");
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return List_ccsxx;
	}
}
