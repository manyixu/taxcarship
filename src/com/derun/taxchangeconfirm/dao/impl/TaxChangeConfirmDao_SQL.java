package com.derun.taxchangeconfirm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.derun.all.common.SqlDao;
import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSBGQRRCJB;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_RKMX_QS;

/**
 * @author MILI
 * @time 2014-4-17 15:34:17
 * @描述：数据库操作sql类
 * */
public class TaxChangeConfirmDao_SQL {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_CCSBGQRRCJB  sql
	 * */
	public String getCCSBGQRRCJB_sql(SYJK_CCS_CCSBGQRRCJB ccsbgqrrcjb){
		String sql_ = "" ;
		StringBuffer sql = new StringBuffer(
				"INSERT INTO SYJK_CCS_CCSBGQRRCJB(CHANGEQUERYNO,CHANGECONFIRMNO,CALCTAXFLAG,TAXAMOUNT_FLAG,"
						+ "ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,TAXPRINTNO,DECLAREDSTATUS,LICENSEPLATENO,"
						+ "LICENSEPLATETYPE,MOTORTYPECODE,ENGINENO,VIN,MADEFACTORY,MOTORUSAGETYPECODE,NOLICENSEFLAG,MODEL,FIRSTREGISTERDATE,"
						+ "VEHICLETYPE,RATEDPASSENGERCAPACITY, TONNAGE,WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,SPECIALCARTYPE,VEHICLEOWNERNAME,"
						+ "CERTICODE,CREDENTIALCODE,ADDRESS,PHONENO,TAXCONFIRMNO,CHANGESUMTAX,CHANGETYPE "
						+ ",INSURESTARTDATE,INSUREENDDATE,CARMATCHID"
						+ ")VALUES('");
		sql.append(ccsbgqrrcjb.getCHANGEQUERYNO() + "','");
		sql.append(ccsbgqrrcjb.getCHANGECONFIRMNO() + "','");
		sql.append(ccsbgqrrcjb.getCALCTAXFLAG() + "','");
		sql.append(ccsbgqrrcjb.getTAXAMOUNT_FLAG() + "','");
		sql.append(ccsbgqrrcjb.getANNUALTAXDUE() + "','");
		sql.append(ccsbgqrrcjb.getSUMTAXDEFAULT() + "','");
		sql.append(ccsbgqrrcjb.getSUMOVERDUE() + "','");
		sql.append(ccsbgqrrcjb.getSUMTAX() + "','");
		sql.append(ccsbgqrrcjb.getLOGINNAME() + "','");
		sql.append(ccsbgqrrcjb.getREVENUECODE() + "','");
		sql.append(ccsbgqrrcjb.getSJCJRQ() + "','");
		sql.append(ccsbgqrrcjb.getSJCJFS() + "','");
		sql.append(ccsbgqrrcjb.getTAXPRINTNO() + "','");
		sql.append(ccsbgqrrcjb.getDECLAREDSTATUS() + "','");
		sql.append(ccsbgqrrcjb.getLICENSEPLATENO() + "','");
		sql.append(ccsbgqrrcjb.getLICENSEPLATETYPE() + "','");
		sql.append(ccsbgqrrcjb.getMOTORTYPECODE() + "','");
		sql.append(ccsbgqrrcjb.getENGINENO() + "','");
		sql.append(ccsbgqrrcjb.getVIN() + "','");
		sql.append(ccsbgqrrcjb.getMADEFACTORY() + "','");
		sql.append(ccsbgqrrcjb.getMOTORUSAGETYPECODE() + "','");
		sql.append(ccsbgqrrcjb.getNOLICENSEFLAG() + "','");
		sql.append(ccsbgqrrcjb.getMODEL() + "','");
		sql.append(ccsbgqrrcjb.getFIRSTREGISTERDATE() + "','");
		sql.append(ccsbgqrrcjb.getVEHICLETYPE() + "','");
		sql.append(ccsbgqrrcjb.getRATEDPASSENGERCAPACITY() + "','");
		sql.append(ccsbgqrrcjb.getTONNAGE() + "','");
		sql.append(ccsbgqrrcjb.getWHOLEWEIGHT() + "','");
		sql.append(ccsbgqrrcjb.getDISPLACEMENT() + "','");
		sql.append(ccsbgqrrcjb.getPOWER() + "','");
		sql.append(ccsbgqrrcjb.getFUELTYPE() + "','");
		sql.append(ccsbgqrrcjb.getSPECIALCARTYPE() + "','");
		sql.append(ccsbgqrrcjb.getVEHICLEOWNERNAME() + "','");
		sql.append(ccsbgqrrcjb.getCERTICODE() + "','");
		sql.append(ccsbgqrrcjb.getCREDENTIALCODE() + "','");
		sql.append(ccsbgqrrcjb.getADDRESS() + "','");
		sql.append(ccsbgqrrcjb.getPHONENO() + "','");
		sql.append(ccsbgqrrcjb.getTAXCONFIRMNO() + "','");
		sql.append(ccsbgqrrcjb.getCHANGESUMTAX() + "','");
		sql.append(ccsbgqrrcjb.getCHANGETYPE() + "','");
		sql.append(ccsbgqrrcjb.getINSURESTARTDATE() + "','");
		sql.append(ccsbgqrrcjb.getINSUREENDDATE() + "','");
		sql.append(ccsbgqrrcjb.getCARMATCHID() + "'");
				
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_QGCLWTMDXX  sql
	 * */
	public String getQGCLWTMDXX_sql(SYJK_CCS_QGCLWTMDXX qgclwtmdxx){
		String sql_ = "" ;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO SYJK_CCS_CCSBGCXCCJB(TAXQUERYNO,CALCTAXFLAG,TAXAMOUNT_FL,ANNUALTAXDUE,SUMTAXDEFAULT,"
					+ "SUMOVERDUE,SUMTAX,RETURNCODE,TAXCONFIRMNO,CHANGESUMTAX,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,VIN,"
					+ "HPHM,HPZL,CLLX,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARSERIALNO,ENGINENO,INSURESTARTDATE,"
					+ "INSUREENDDATE,CARMATCHID)VALUES('");
		
		
		
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_RKMX_QS sql
	 * */
	public String getRKMX_QS_sql(SYJK_CCS_RKMX_QS rkmx_qs){
		String sql_ = "" ;
		StringBuffer sql = new StringBuffer(
				"INSERT INTO SYJK_CCS_RKMX_QS("
						+ "TAXQUERYNO,TAXCONFIRMNO,TAXPRINTNO,VIN,HPHM,HPZL,CLLX,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,"
						+ "TAXPAYERIDENTIFICATIONCODE,PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE," 
						+ "UNITRATE,ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,DEDUCTIONDEPARTMENTCODE," 
						+ "DEDUCTIONDEPARTMENT,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,"
						+ "TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,REVENUECODE,SJCJRQ,PAYDATE,TAXAMOUNT_FLAG," 
						+ "ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX ,LOGGEDOUT,PLATFORMSTATE,CHANGETYPE,COUNTTAXTYPE,REFUSETYPE,"
						+ "STATUSDATE,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARSERIALNO,ENGINENO,INSURESTARTDATE,INSUREENDDATE "
						+ ")VALUES('");
		sql.append(rkmx_qs.getTAXQUERYNO() + "','");
		sql.append(rkmx_qs.getTAXCONFIRMNO() + "','");
		sql.append(rkmx_qs.getTAXPRINTNO() + "','");
		sql.append(rkmx_qs.getVIN() + "','");
		sql.append(rkmx_qs.getHPHM() + "','");
		sql.append(rkmx_qs.getHPZL() + "','");
		sql.append(rkmx_qs.getCLLX() + "','");
		sql.append(rkmx_qs.getTAXCONDITIONCODE() + "','");
		sql.append(rkmx_qs.getTAXREGISTRYNUMBER() + "','");
		sql.append(rkmx_qs.getTAXPAYERNAME() + "','");
		sql.append(rkmx_qs.getTAXPAYERIDENTIFICATIONCODE() + "','");
		sql.append(rkmx_qs.getPAYCOMPANYCODE() + "','");
		sql.append(rkmx_qs.getTAXLOCATIONCODE() + "','");
		sql.append(rkmx_qs.getTAXSTARTDATE() + "','");
		sql.append(rkmx_qs.getTAXENDDATE() + "','");
		sql.append(rkmx_qs.getTAXUNITTYPECODE() + "','");
		sql.append(rkmx_qs.getUNITRATE() + "','");
		sql.append(rkmx_qs.getANNUALTAXAMOUNT() + "','");
		sql.append(rkmx_qs.getTAXDEPARTMENTCODE() + "','");
		sql.append(rkmx_qs.getTAXDEPARTMENT() + "','");
		sql.append(rkmx_qs.getTAXDOCUMENTNUMBER() + "','");
		sql.append(rkmx_qs.getDEDUCTIONDEPARTMENTCODE() + "','");
		sql.append(rkmx_qs.getDEDUCTIONDEPARTMENT() + "','");
		sql.append(rkmx_qs.getDEDUCTIONDUECODE() + "','");
		sql.append(rkmx_qs.getDEDUCTIONDUETYPE() + "','");
		sql.append(rkmx_qs.getDEDUCTIONDUEPROPORTION() + "','");
		sql.append(rkmx_qs.getDEDUCTION() + "','");
		sql.append(rkmx_qs.getDEDUCTIONDOCUMENTNUMBER() + "','");
		sql.append(rkmx_qs.getTAXDUE() + "','");
		sql.append(rkmx_qs.getEXCEEDDATE() + "','");
		sql.append(rkmx_qs.getEXCEEDDAYSCOUNT() + "','");
		sql.append(rkmx_qs.getOVERDUE() + "','");
		sql.append(rkmx_qs.getTOTALAMOUNT() + "','");
		sql.append(rkmx_qs.getLOGINNAME() + "','");
		sql.append(rkmx_qs.getREVENUECODE() + "','");
		sql.append(rkmx_qs.getSJCJRQ() + "','");
		sql.append(rkmx_qs.getPAYDATE() + "','");
		sql.append(rkmx_qs.getTAXAMOUNT_FLAG() + "','");
		sql.append(rkmx_qs.getANNUALTAXDUE() + "','");
		sql.append(rkmx_qs.getSUMTAXDEFAULT() + "','");
		sql.append(rkmx_qs.getSUMOVERDUE() + "','");
		sql.append(rkmx_qs.getSUMTAX() + "','");
		sql.append(rkmx_qs.getLOGGEDOUT() + "','");
		sql.append(rkmx_qs.getPLATFORMSTATE() + "','");
		sql.append(rkmx_qs.getCHANGETYPE() + "','");
		sql.append(rkmx_qs.getCOUNTTAXTYPE() + "','");
		sql.append(rkmx_qs.getREFUSETYPE() + "','");
		sql.append(rkmx_qs.getSTATUSDATE() + "','");
		sql.append(rkmx_qs.getFIRSTREGISTERDATE() + "','");
		sql.append(rkmx_qs.getSPECIALCARTYPE() + "','");
		sql.append(rkmx_qs.getTSBZ() + "','");
		sql.append(rkmx_qs.getCARSERIALNO() + "','");
		sql.append(rkmx_qs.getENGINENO() + "','");
		sql.append(rkmx_qs.getINSURESTARTDATE() + "','");
		sql.append(rkmx_qs.getINSUREENDDATE() + "','");
		
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_RKMX  sql
	 * */
	public String getRKMX_sql(SYJK_CCS_RKMX rkmx){
		
		
		String sql_ = "" ;
		StringBuffer sql = new StringBuffer(
				"INSERT INTO SYJK_CCS_RKMX("
						+ "TAXQUERYNO,TAXCONFIRMNO,TAXPRINTNO,VIN,HPHM,HPZL,CLLX,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME," 
						+ "TAXPAYERIDENTIFICATIONCODE ,PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE," 
						+ "UNITRATE,ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,DEDUCTIONDEPARTMENTCODE," 
						+ "DEDUCTIONDEPARTMENT,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,"
						+ "TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,REVENUECODE,SJCJRQ,PAYDATE,"
						+ "TAXAMOUNT_FLAG ,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX ,LOGGEDOUT,PLATFORMSTATE,CHANGETYPE,"
						+ "COUNTTAXTYPE,REFUSETYPE,STATUSDATE,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARSERIALNO "
						+ ",ENGINENO,INSURESTARTDATE,INSUREENDDATE)VALUES('");
		sql.append(rkmx.getTAXQUERYNO() + "','");
		sql.append(rkmx.getTAXCONFIRMNO() + "','");
		sql.append(rkmx.getTAXPRINTNO() + "','");
		sql.append(rkmx.getVIN() + "','");
		sql.append(rkmx.getHPHM() + "','");
		sql.append(rkmx.getHPZL() + "','");
		sql.append(rkmx.getCLLX() + "','");
		sql.append(rkmx.getTAXCONDITIONCODE() + "','");
		sql.append(rkmx.getTAXREGISTRYNUMBER() + "','");
		sql.append(rkmx.getTAXPAYERNAME() + "','");
		sql.append(rkmx.getTAXPAYERIDENTIFICATIONCODE() + "','");
		sql.append(rkmx.getPAYCOMPANYCODE() + "','");
		sql.append(rkmx.getTAXLOCATIONCODE() + "','");
		sql.append(rkmx.getTAXSTARTDATE() + "','");
		sql.append(rkmx.getTAXENDDATE() + "','");
		sql.append(rkmx.getTAXUNITTYPECODE() + "','");
		sql.append(rkmx.getUNITRATE() + "','");
		sql.append(rkmx.getANNUALTAXAMOUNT() + "','");
		sql.append(rkmx.getTAXDEPARTMENTCODE() + "','");
		sql.append(rkmx.getTAXDEPARTMENT() + "','");
		sql.append(rkmx.getTAXDOCUMENTNUMBER() + "','");
		sql.append(rkmx.getDEDUCTIONDEPARTMENTCODE() + "','");
		sql.append(rkmx.getDEDUCTIONDEPARTMENT() + "','");
		sql.append(rkmx.getDEDUCTIONDUECODE() + "','");
		sql.append(rkmx.getDEDUCTIONDUETYPE() + "','");
		sql.append(rkmx.getDEDUCTIONDUEPROPORTION() + "','");
		sql.append(rkmx.getDEDUCTION() + "','");
		sql.append(rkmx.getDEDUCTIONDOCUMENTNUMBER() + "','");
		sql.append(rkmx.getTAXDUE() + "','");
		sql.append(rkmx.getEXCEEDDATE() + "','");
		sql.append(rkmx.getEXCEEDDAYSCOUNT() + "','");
		sql.append(rkmx.getOVERDUE() + "','");
		sql.append(rkmx.getTOTALAMOUNT() + "','");
		sql.append(rkmx.getLOGINNAME() + "','");
		sql.append(rkmx.getREVENUECODE() + "','");
		sql.append(rkmx.getSJCJRQ() + "','");
		sql.append(rkmx.getPAYDATE() + "','");
		sql.append(rkmx.getTAXAMOUNT_FLAG() + "','");
		sql.append(rkmx.getANNUALTAXDUE() + "','");
		sql.append(rkmx.getSUMTAXDEFAULT() + "','");
		sql.append(rkmx.getSUMOVERDUE() + "','");
		sql.append(rkmx.getSUMTAX() + "','");
		sql.append(rkmx.getLOGGEDOUT() + "','");
		sql.append(rkmx.getPLATFORMSTATE() + "','");
		sql.append(rkmx.getCHANGETYPE() + "','");
		sql.append(rkmx.getCOUNTTAXTYPE() + "',");
		sql.append(rkmx.getRefusetype() + "','");
		sql.append(rkmx.getSTATUSDATE() + "','");
		sql.append(rkmx.getFIRSTREGISTERDATE() + "','");
		sql.append(rkmx.getSPECIALCARTYPE() + "','");
		sql.append(rkmx.getTSBZ() + "','");
		sql.append(rkmx.getCARSERIALNO() + "','");
		sql.append(rkmx.getEngineNo() + "','");
		sql.append(rkmx.getInsureStartDate() + "','");
		sql.append(rkmx.getInsureEndDate() + "'");
		
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_RKMX  sql
	 * */
	public String getRKMX_BC_sql(BaseChangeConfirmReqInfo bfreqf){
		// 车辆初始登记日期不为空
		String changeRes = this.getChangeRes(bfreqf.getChangeQueryNo().getTaxDealCode_Type());
		StringBuffer insertSql = new StringBuffer("INSERT INTO SYJK_CCS_RKMX(");
		insertSql.append("TAXQUERYNO,TAXCONFIRMNO,TAXPRINTNO,VIN,HPHM,HPZL,CLLX,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,TAXPAYERIDENTIFICATIONCODE ,");
		insertSql.append("PAYCOMPANYCODE, TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,UNITRATE,ANNUALTAXAMOUNT,");
		insertSql.append("TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,");
		insertSql.append("DEDUCTIONDEPARTMENTCODE,DEDUCTIONDEPARTMENT,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,");
		insertSql.append("TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,REVENUECODE,SJCJRQ,PAYDATE,");
		insertSql.append("TAXAMOUNT_FLAG ,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX ,");
		insertSql.append("LOGGEDOUT,PLATFORMSTATE,CHANGETYPE,COUNTTAXTYPE,Refusetype,STATUSDATE,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ");
		insertSql.append(",ENGINENO,INSURESTARTDATE,INSUREENDDATE )SELECT '");
		insertSql.append("null").append("','");
		insertSql.append("null").append("','").append("null").append("','");
		insertSql.append("null").append("','").append("null").append("','");
		insertSql.append("null").append("','").append("null");
		insertSql.append("',TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,TAXPAYERIDENTIFICATIONCODE,");
		insertSql.append("PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,UNITRATE,ANNUALTAXAMOUNT,");
		insertSql.append("TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,");
		insertSql.append("TAXDEPARTMENTCODE,TAXDEPARTMENT,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,");
		insertSql.append("TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,REVENUECODE,sysdate,to_char(sysdate,'YYYY'),");
		insertSql.append("'").append("null").append("',").append("null").append(",");
		insertSql.append("null").append(",").append("null").append(",").append("null").append(",");
		insertSql.append("'0','1','2','1',1,sysdate,TO_DATE('").append("null");
		insertSql.append("','YYYY-MM-DD'),'").append("null").append("','0'");
		
		if (bfreqf.getVehicleInfo().getEngineNo() != null
				&& !"".equals(bfreqf.getVehicleInfo().getEngineNo())) {
			insertSql.append(",'").append(bfreqf.getVehicleInfo().getEngineNo()).append("'");
		} else {
			if (!"".equals(changeRes)) {
				insertSql.append(",'").append(changeRes.split(",")[0]).append("'");
			} else {
				insertSql.append(",null");
			}
		}
		// 投保开始日期存在
		if (bfreqf.getInsureStartDate() != null) {
			insertSql.append(", TO_DATE('").append(DateUtil.getStringDate(bfreqf.getInsureStartDate(), "yyyy-MM-dd"));
			insertSql.append("','YYYY-MM-DD') INSURESTARTDATE ");
		// 投保开始日期不存在
		} else {
			if (!"".equals(changeRes)) {
				insertSql.append(",'").append(changeRes.split(",")[1]).append("'");
			} else {
				insertSql.append(",null");
			}
		}
		// 投保结束日期存在
		if (bfreqf.getInsureEndDate() != null) {
			insertSql.append(", TO_DATE('").append(DateUtil.getStringDate(bfreqf.getInsureEndDate(), "yyyy-MM-dd")).append("','YYYY-MM-DD') INSUREENDDATE ");
		// 投保结束日期不存在
		} else {
			if (!"".equals(changeRes)) {
				insertSql.append(",'").append(changeRes.split(",")[2]).append("'");
			} else {
				insertSql.append(",null");
			}
		}
		insertSql.append(" FROM SYJK_CCS_CCSBGXX WHERE PARATYPE = '1' AND TAXQUERYNO = '");
		insertSql.append(bfreqf.getChangeQueryNo().getTaxDealCode_Type()).append("'");
		return insertSql.toString();
	}
	/**
	 * @author MILI
	 * @time 2014-5-21 17:19:42
	 * @描述：
	 * */
	public String getChangeRes(String changeQueryNO) {
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "SELECT ENGINENO,INSURESTARTDATE,INSUREENDDATE FROM SYJK_CCS_CCSBGCXCCJB BCC WHERE BCC.TAXQUERYNO = ?";
		String resultStr = "";
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setString(1, changeQueryNO);
			rs = pre.executeQuery();
			if (rs.next()) {
				resultStr = rs.getString("ENGINENO") + ","
						+ rs.getString("INSURESTARTDATE") + ","
						+ rs.getString("INSUREENDDATE");
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultStr;
	}
	
	/**
	 * @author MILI
	 * @time 2014-5-21 17:37:23
	 * @描述：
	 * */
	public boolean getRkmx(BaseChangeConfirmReqInfo bfreqf,SYJK_CCS_RKMX rkmx,SYJK_CCS_CCSBGCXCCJB queryRes,
			TaxDealCode_Type taxQueryNo,String taxConfirmNo,String platformstate,String changeRes,double changeSumTax){
		boolean bool = false ;
		Connection con = null ;
		Statement stmt = null;
		try {
			con = DBConnPool.getConnection();
			stmt = con.createStatement();
			int leng = bfreqf.getTaxInfo().getDelinquentTaxDue() == null ? 0
					: bfreqf.getTaxInfo().getDelinquentTaxDue().length;
			boolean taxDueFlag = true;
			AnnualTax_Type[] DelinquentTaxDue = bfreqf.getTaxInfo()
					.getDelinquentTaxDue(); 	// 欠税信息对象数组
			if (leng == 1) {
				DelinquentTaxDue = bfreqf.getTaxInfo()
						.getDelinquentTaxDue();
				if (DelinquentTaxDue[0].getTotalAmount() == 0.00) {
					taxDueFlag = false;
				}
			}
			int num = 0;
			if (leng > 0 && taxDueFlag) {
				System.out.println("------------------------ : 有欠税记录插库");
				System.out.println("数组长度 : " + leng);
				// END 2013-01-10 BY CHENJIAJUN
				AnnualTax_Type delinquentTaxDue = new AnnualTax_Type();
				for (int i = 0; i < leng; i++) {
					delinquentTaxDue = DelinquentTaxDue[i];
					String ccsxxRcxxSqlS = this.getSYJK_CCS_RKMX_QS(bfreqf,
							taxQueryNo, delinquentTaxDue, taxConfirmNo,
							queryRes, platformstate, changeRes);		// 车船税本年入参方法
	
					int c = stmt.executeUpdate(ccsxxRcxxSqlS);
					if (c > 0) {
						bool = true;
					} else {
						num = 1;
						break;
					}
				}
	
			}
			if (num == 0) {
//				System.out.println("***********确认入参插入**********");
				String qrRcSql = this.getBgQrRc(bfreqf, taxQueryNo,
						taxConfirmNo, changeSumTax, platformstate);
//				System.out.println(qrRcSql);
				int a = stmt.executeUpdate(qrRcSql);
				if (a > 0){
					bool = true;
				}else{
					bool = false;
				}
				String ccsxxRcxxSql = this.getSYJK_CCS_RKMX(bfreqf,
						taxQueryNo, bfreqf.getTaxInfo().getCurrentTaxDue(),
						taxConfirmNo, queryRes, platformstate, changeRes, rkmx);//wbzhao 20150126 批改确认税款归属地从投保信息获取，所以参数加上rkmx
//				
				// VIN与原数据库中不同需生成更新原来VINSQL 进行批量操作数据库
				// 2013-3-15 17:48:36 change by yandefa 添加号牌号码和发动机号
				if (null != rkmx) {
					List<String> sqls = new ArrayList<String>() ;
					if ("4".equals(bfreqf.getChangeType())) {
						sqls.add(this.Update_WS(queryRes));
					}
					sqls.add(ccsxxRcxxSql);
					sqls.add(this.returnUpdateSql(bfreqf, queryRes, rkmx));	// 返回拼接的sql
					for (int j = 0; j < sqls.size(); j++) {
						stmt.addBatch(sqls.get(j));
					}
//					String[] sqls = new String[2];
//					sqls[0] = ccsxxRcxxSql;
//					sqls[1] = this.returnUpdateSql(bfreqf, queryRes, rkmx);// 返回拼接的sql
//					for (int j = 0; j < sqls.length; j++) {
//						stmt.addBatch(sqls[j]);
//					}
	
					int[] result = stmt.executeBatch();
					if (result.length > 0) {
						bool = true;
						con.commit();
					} else {
						bool = false;
					}
				} else {
					// END 2013-03-05 BY YANLONG
					int b = stmt.executeUpdate(ccsxxRcxxSql);
					if (bool && b > 0){
						bool = true;
						con.commit();
					}else
						bool = false;
	
				}
			}
		} catch (SQLException e) {
			try {
				con.rollback();
				bool = false ;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			try {
				con.rollback();
				bool = false ;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null){
					stmt.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(con != null){
//					con.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bool ;
	}
	/**
	 * 拼接查询sql  add by YanDeFa at 2013-3-19 11:30:41
	 * 批改确认时 有更新车辆信息的
	 * @return String
	 */
	private String returnUpdateSql(BaseChangeConfirmReqInfo bfreqf,
			SYJK_CCS_CCSBGCXCCJB queryRes, SYJK_CCS_RKMX rkmx) {
		StringBuffer updateSql = new StringBuffer(
				"UPDATE SYJK_CCS_RKMX SET SYJK_CCS_RKMX.VIN = '");
		updateSql.append(queryRes.getVIN());
		updateSql.append("', SYJK_CCS_RKMX.HPHM ='").append(queryRes.getHPHM());
		updateSql.append("', SYJK_CCS_RKMX.HPZL ='").append(queryRes.getHPZL());
		updateSql.append("',SYJK_CCS_RKMX.ENGINENO='");

		// 判断车辆信息和发动机号
		if (null != bfreqf.getVehicleInfo()
				&& null != bfreqf.getVehicleInfo().getEngineNo()) {
			updateSql.append(bfreqf.getVehicleInfo().getEngineNo());
		}else if(queryRes != null){
			updateSql.append(queryRes.getENGINENO());
		} else if (null != rkmx && null != rkmx.getEngineNo()) {
			// 如果请求中的发动机号为空，从查询返回记录中获取发动机号
			updateSql.append(rkmx.getEngineNo());
		}

		updateSql.append("' WHERE SYJK_CCS_RKMX.TAXCONFIRMNO = '").append(
				rkmx.getTAXCONFIRMNO()).append("'");

		return updateSql.toString();
	}
	/**
	 * 拼出批改确认基本信息入参sql语句
	 * 
	 * @param BaseChangeConfirmReqInfo
	 * @param TaxDealCode_Type
	 * @return String
	 */
	public String getBgQrRc(BaseChangeConfirmReqInfo BCCRI,
			TaxDealCode_Type taxQueryNo, String taxConfirmNo,
			double changeSumTax, String platformstate) {
		StringBuffer sql = new StringBuffer(
				"INSERT INTO SYJK_CCS_CCSBGQRRCJB(CHANGEQUERYNO,CHANGECONFIRMNO, CALCTAXFLAG,TAXAMOUNT_FLAG,"
						+ "ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,TAXPRINTNO,DECLAREDSTATUS,LICENSEPLATENO,"
						+ "LICENSEPLATETYPE,MOTORTYPECODE,ENGINENO, VIN,MADEFACTORY,MOTORUSAGETYPECODE,NOLICENSEFLAG,MODEL,FIRSTREGISTERDATE,"
						+ "VEHICLETYPE,RATEDPASSENGERCAPACITY, TONNAGE,WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,SPECIALCARTYPE,VEHICLEOWNERNAME,"
						+ "CERTICODE,CREDENTIALCODE,ADDRESS,PHONENO,TAXCONFIRMNO,CHANGESUMTAX,CHANGETYPE"
						+ ",INSURESTARTDATE,INSUREENDDATE,CARMATCHID"
						+ ") VALUES(");
		String BCFlag = "2";
		if (BCCRI.getChangeConfirmNo() != null) {
			if (BCCRI.getChangeConfirmNo().getTaxDealCode_Type() != null
					&& !"".trim().equals(
							BCCRI.getChangeConfirmNo().getTaxDealCode_Type()
									.trim())) {
				BCFlag = "1";
			}
		}
		sql.append("'" + BCCRI.getChangeQueryNo().getTaxDealCode_Type() + "'");// 车船税查询码

		if ("1".equals(BCCRI.getCalcTaxFlag()) && "2".equals(BCFlag)) {
			sql.append(",'" + taxQueryNo.getTaxDealCode_Type() + "'");// 车船税确认码

		} else {
			sql.append(",'" + BCCRI.getChangeConfirmNo().getTaxDealCode_Type()
					+ "'");// 车船税确认码
		}
		sql.append(",'" + BCCRI.getCalcTaxFlag() + "'");
		if (BCCRI.getTaxInfo() != null) {
			if (BCCRI.getTaxInfo().getTaxAmount() != null) {
				sql.append(",'"
						+ BCCRI.getTaxInfo().getTaxAmount().getTaxAmount_Flag()
						+ "'");
				sql.append(","
						+ BCCRI.getTaxInfo().getTaxAmount().getAnnualTaxDue());
				sql.append(","
						+ BCCRI.getTaxInfo().getTaxAmount().getSumTaxDefault());
				sql.append(","
						+ BCCRI.getTaxInfo().getTaxAmount().getSumOverdue());
				sql.append("," + BCCRI.getTaxInfo().getTaxAmount().getSumTax());
			} else {
				sql.append(",'',0,0,0,0");
			}
		} else {
			sql.append(",'',0,0,0,0");
		}
		sql.append(",'" + CfgLoader.getConfigValue("SysCode_USER", "username") + "'");
		sql.append(",'" + CfgLoader.getConfigValue("SysCode_USER", "usercode") + "'");
		sql.append(",sysdate,'0'");
		String DYM = BCCRI.getTaxPrintNo() == null ? "" : BCCRI.getTaxPrintNo()
				.getTaxDealCode_Type();
		sql.append(",'" + DYM + "'");// 车船税打印码
		String Ptzt = "";
		if ("2".equals(BCCRI.getCalcTaxFlag())
				|| "3".equals(BCCRI.getCalcTaxFlag()) || "1".equals(BCFlag)) {
			if ("R".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				Ptzt = "4";
			} else if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				Ptzt = "1";
			} else {
				if (platformstate.equals("1")) {
					Ptzt = "1";
				} else {
					Ptzt = "0";
				}
			}
		} else {
			if ("R".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				Ptzt = "4";
			} else if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				Ptzt = "1";
			} else {
				if (platformstate.equals("1")) {
					Ptzt = "1";
				} else {
					Ptzt = "0";
				}
			}
		}
		sql.append(",'" + Ptzt + "'");

		if (BCCRI.getVehicleInfo() != null) {
			sql.append(",'" + BCCRI.getVehicleInfo().getLicensePlateNo() + "'");
			sql.append(",'" + BCCRI.getVehicleInfo().getLicensePlateType()
					+ "'");
			sql.append(",'" + BCCRI.getVehicleInfo().getMotorTypeCode() + "'");
			sql.append(",'" + BCCRI.getVehicleInfo().getEngineNo() + "'");
			sql.append(",'" + BCCRI.getVehicleInfo().getVIN() + "'");
			sql.append(",'" + BCCRI.getVehicleInfo().getMadeFactory() + "'");
			sql.append(",'" + BCCRI.getVehicleInfo().getMotorUsageTypeCode()
					+ "'");
			sql.append(",'" + BCCRI.getVehicleInfo().getNoLicenseFlag() + "'");
			sql.append(",'" + BCCRI.getVehicleInfo().getModel() + "'");

			String fisdate = "";
			if (BCCRI.getVehicleInfo() != null
					&& BCCRI.getVehicleInfo().getFirstRegisterDate() != null) {
				if (BCCRI.getVehicleInfo().getFirstRegisterDate().length() >= 10) {
					fisdate = BCCRI.getVehicleInfo().getFirstRegisterDate()
							.substring(0, 10);
				}
			}
			sql.append(",TO_DATE('" + fisdate + "','YYYY-MM-DD')");
			sql.append(",'" + BCCRI.getVehicleInfo().getVehicleType() + "'");
			sql.append("," + BCCRI.getVehicleInfo()
									.getRatedPassengerCapacity());
			sql.append("," + BCCRI.getVehicleInfo().getTonnage());
			sql.append("," + BCCRI.getVehicleInfo().getWholeWeight());
			sql.append("," + BCCRI.getVehicleInfo().getDisplacement());
			sql.append("," + BCCRI.getVehicleInfo().getPower());
			sql.append(",'" + BCCRI.getVehicleInfo().getFuelType() + "'");
			sql.append(",'" + BCCRI.getVehicleInfo().getSpecialCarType() + "'");
		} else {
			sql.append(",'','','','','','','','','','','',0,0,0,0,0,'',''");
		}
		if (BCCRI.getVehicleOwnerInfo() != null) {
			sql.append(",'" + BCCRI.getVehicleOwnerInfo().getVehicleOwnerName()
					+ "'");
			sql.append(",'" + BCCRI.getVehicleOwnerInfo().getCredentialNo()
					+ "'");
			sql.append(",'" + BCCRI.getVehicleOwnerInfo().getCredentialCode()
					+ "'");
			sql.append(",'" + BCCRI.getVehicleOwnerInfo().getAddress() + "'");
			sql.append(",'" + BCCRI.getVehicleOwnerInfo().getPhoneNo() + "'");
		} else {
			sql.append(",'','','','',''");
		}
		sql.append(",'" + taxConfirmNo + "'");
		sql.append(",'" + changeSumTax + "'");
		String ct = "";
		if ("1".equals(BCCRI.getChangeType())) {
			ct = "4";
		} else {
			ct = BCCRI.getChangeType();
		}
		//变更类型 0=确认 4=退保，2=批改
		sql.append(",'" + ct + "'");
		// 投保开始日期存在
		if (BCCRI.getInsureStartDate() != null) {
			sql.append(", TO_DATE('" + DateUtil.getStringDate(BCCRI.getInsureStartDate(),"yyyy-MM-dd")
					+ "','YYYY-MM-DD')");
		// 投保开始日期不存在
		} else {
			sql.append(", null");
		}
		// 投保结束日期存在
		if (BCCRI.getInsureEndDate() != null) {
			sql.append(", TO_DATE('" + DateUtil.getStringDate(BCCRI.getInsureEndDate(),"yyyy-MM-dd")
					+ "','YYYY-MM-DD')");
		// 投保结束日期不存在
		} else {
			sql.append(", null");
		}
		String carMatchId = BCCRI.getCarMatchId();
		if(carMatchId != null && !"".equals(carMatchId)){
			sql.append(",'"+ carMatchId +"'");
		}else{
			sql.append(",null");
		}
		sql.append(")");
		String s = sql.toString();
		s = s.replaceAll("'null'", "null");
		return s;
	}
	/**
	 * 拼出入库信息（SYJK_CCS_RKMX）sql语句
	 * 
	 * @param baseconfirmReqinfo
	 * @param codetype
	 * @return
	 */
	public String getSYJK_CCS_RKMX(BaseChangeConfirmReqInfo BCCRI,
			TaxDealCode_Type taxQueryNo, AnnualTax_Type delinquentTaxDue,
			String taxConfirmNo, SYJK_CCS_CCSBGCXCCJB queryRes,
			String platformstate, String changeRes, SYJK_CCS_RKMX rkmx) {//wbzhao 20150126 批改确认税款归属地从投保信息获取，所以参数加上rkmx
		StringBuffer sql = new StringBuffer(
				"INSERT INTO SYJK_CCS_RKMX("
						+ "TAXQUERYNO,TAXCONFIRMNO,TAXPRINTNO,VIN,HPHM,HPZL,CLLX,MOTORUSAGETYPECODE,MODEL,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,"
						+ "WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,TAXPAYERIDENTIFICATIONCODE ,PAYCOMPANYCODE,"
						+ "TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,UNITRATE,ANNUALTAXAMOUNT,"
						+ "TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,"
						+ "DEDUCTIONDEPARTMENTCODE,DEDUCTIONDEPARTMENT,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,"
						+ "TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,REVENUECODE,SJCJRQ,PAYDATE,"
						+ "TAXAMOUNT_FLAG ,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX ,LOGGEDOUT,PLATFORMSTATE,CHANGETYPE,COUNTTAXTYPE,Refusetype,STATUSDATE,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARSERIALNO"
						+ ",ENGINENO,INSURESTARTDATE,INSUREENDDATE,CityCode,CountryCode "
						+ ") values(");
		String Ptzt = "";
		String middleNo = "";
		String BCFlag = "2";
		if (BCCRI.getChangeConfirmNo() != null) {
			if (BCCRI.getChangeConfirmNo().getTaxDealCode_Type() != null
					&& !"".trim().equals(
							BCCRI.getChangeConfirmNo().getTaxDealCode_Type()
									.trim())) {
				BCFlag = "1";
			}
		}
		if (BCCRI.getCalcTaxFlag() != null
				&& BCCRI.getCalcTaxFlag().equals("1") && "2".equals(BCFlag)) {
			String agoNo = taxConfirmNo.substring(0, 11);
			String endNo = taxConfirmNo.substring(12, taxConfirmNo.length());
			middleNo = agoNo + "G" + endNo;
			sql.append("'" + taxQueryNo.getTaxDealCode_Type() + "'");// 车船税查询码,唯一标示符
			sql.append(",'" + taxConfirmNo + "'");// 车船税查询码,唯一标示符
			if ("R".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				sql.append(",''");// 车船税查询码,唯一标示符
			} else {
				sql.append(",'" + middleNo + "'");// 车船税查询码,唯一标示符
			}
			sql.append(",'" + queryRes.getVIN() + "'");		// VIN
			sql.append(",'" + queryRes.getHPHM() + "'");	// HPHM
			sql.append(",'" + queryRes.getHPZL() + "'");	// HPZL
			sql.append(",'" + queryRes.getCLLX() + "'");	// CLLX
			
			sql.append(",'" + queryRes.getMOTORUSAGETYPECODE() + "'");		// 使用性质
			sql.append(",'" + queryRes.getMODEL() + "'");			// 车辆型号
			sql.append(",'" + queryRes.getVEHICLETYPE() + "'");		// 交管车辆类型
			sql.append("," + queryRes.getRATEDPASSENGERCAPACITY() + "");	// 核定载客数
			sql.append("," + queryRes.getTONNAGE() + "");			// 核定载质量
			sql.append("," + queryRes.getWHOLEWEIGHT() + "");		// 整备质量
			sql.append("," + queryRes.getDISPLACEMENT() + "");		// 排量
			sql.append("," + queryRes.getPOWER() + "");				// 功率
			sql.append(",'" + queryRes.getFUELTYPE() + "'");		// 源种类
			
		} else {
			System.out.println("------------补传");
			// // 车船税批改确认吗,唯一标示符
			sql.append("'" + BCCRI.getChangeConfirmNo().getTaxDealCode_Type()
					+ "'");
			// 车船税确认码,车船税合计金额数据类型中的交易码即是原始保单的确认码，用来与原始保单相匹配。
			// 原始保单确认码
			sql.append(",'"
					+ BCCRI.getTaxInfo().getTaxAmount().getTaxDealCode()
							.getTaxDealCode_Type() + "'");
			if (BCCRI.getTaxPrintNo() == null) {
				sql.append(",''");
			} else {
				sql.append(",'" + BCCRI.getTaxPrintNo().getTaxDealCode_Type()
						+ "'");// 车船税打印码
			}

			if (BCCRI.getVehicleInfo() != null) {
				sql.append(",'" + BCCRI.getVehicleInfo().getVIN() + "'");// VIN
				sql.append(",'" + BCCRI.getVehicleInfo().getLicensePlateNo()
						+ "'");// HPHM
				sql.append(",'" + BCCRI.getVehicleInfo().getLicensePlateType()
						+ "'");// HPZL
				sql.append(",'" + BCCRI.getVehicleInfo().getMotorTypeCode()
						+ "'");// CLLX	
				
				sql.append(",'" + BCCRI.getVehicleInfo().getMotorUsageTypeCode() + "'");		// 使用性质	//wbzhao20151229 车辆使用性质取值修正getMotorTypeCode
				sql.append(",'" + BCCRI.getVehicleInfo().getModel() + "'");				// 车辆型号
				sql.append(",'" + BCCRI.getVehicleInfo().getVehicleType() + "'");		// 交管车辆类型
				sql.append("," + BCCRI.getVehicleInfo().getRatedPassengerCapacity() + "");	// 核定载客数
				sql.append("," + BCCRI.getVehicleInfo().getTonnage() + "");				// 核定载质量
				sql.append("," + BCCRI.getVehicleInfo().getWholeWeight() + "");			// 整备质量
				sql.append("," + BCCRI.getVehicleInfo().getDisplacement() + "");		// 排量
				sql.append("," + BCCRI.getVehicleInfo().getPower() + "");				// 功率
				sql.append(",'" + BCCRI.getVehicleInfo().getFuelType() + "'");			// 源种类
			} else {
				sql.append(",'','','','','','','',0.0,0.0,0.0,0.0,0.0,''");

			}
		}
		sql.append(",'" + BCCRI.getTaxInfo().getTaxConditionCode() + "'");// 纳税类型代码,Y
		sql.append(",'" + BCCRI.getTaxInfo().getTaxRegistryNumber() + "'");// 税务登记证号
		sql.append(",'" + BCCRI.getTaxInfo().getTaxPayerName() + "'");// 纳税人名称
		sql.append(",'" + BCCRI.getTaxInfo().getTaxPayerIdentificationCode()
				+ "'");// 纳税人识别号
		if (BCCRI.getTaxInfo().getPayCompanyCode() != null
				&& !"".equals(BCCRI.getTaxInfo().getPayCompanyCode())) {
			sql.append(",'" + BCCRI.getTaxInfo().getPayCompanyCode() + "'");// 代收公司
		} else if (BCCRI.getCompanyCode() != null
				&& !"".equals(BCCRI.getCompanyCode())) {
			sql.append(",'" + BCCRI.getCompanyCode() + "'");// 代收公司
		} else {
			sql.append(",'"
					+ BCCRI.getChangeQueryNo().getTaxDealCode_Type().substring(
							1, 5) + "'");// 代收公司
		}
		if (delinquentTaxDue != null) {
			sql.append(",'" + delinquentTaxDue.getTaxLocationCode() + "'");// 纳税地区代码
			String startDate = delinquentTaxDue.getTaxStartDate();
			String endDate = delinquentTaxDue.getTaxEndDate();
			if (startDate != null && startDate.length() >= 10) {
				String sd = startDate.substring(0, 10);
				startDate = sd;
			} else {
				startDate = "";
			}
			if (endDate != null && endDate.length() >= 10) {
				String ed = endDate.substring(0, 10);
				endDate = ed;
			} else {
				endDate = "";
			}
			sql.append(",TO_DATE('" + startDate + "','YYYY-MM-DD')");// 税款所属始期
			sql.append(",TO_DATE('" + endDate + "','YYYY-MM-DD')");// 税款所属止期

			sql.append(",'" + delinquentTaxDue.getTaxUnitTypeCode() + "'");// 计税单位代码
			sql.append("," + delinquentTaxDue.getUnitRate());// 单位计税金额
			sql.append("," + delinquentTaxDue.getAnnualTaxAmount());// 当期年单位税额
			if (delinquentTaxDue.getPaid() != null) {
				sql.append(",'"
						+ delinquentTaxDue.getPaid().getTaxDepartmentCode()
						+ "'");// 开具减免税(完税)凭证的税务机关代码,Y
				sql.append(",'" + delinquentTaxDue.getPaid().getTaxDepartment()
						+ "'");// 开具减免税(完税)凭证的税务机关名称,Y
				sql.append(",'"
						+ delinquentTaxDue.getPaid().getTaxDocumentNumber()
						+ "'");// 完税凭证号码
			} else {
				sql.append(",'','',''");// 开具减免税(完税)凭证的税务机关代码,Y
			}
			if (delinquentTaxDue.getDerate() != null) {
				sql.append(",'"
						+ delinquentTaxDue.getDerate().getTaxDepartmentCode()
						+ "'");// 开具减免税(完税)凭证的税务机关代码,Y
				sql
						.append(",'"
								+ delinquentTaxDue.getDerate()
										.getTaxDepartment() + "'");// 开具减免税(完税)凭证的税务机关名称,Y
				sql.append(",'"
						+ delinquentTaxDue.getDerate().getDeductionDueCode()
						+ "'");// 减免税原因代码,Y
				sql.append(",'"
						+ delinquentTaxDue.getDerate().getDeductionDueType()
						+ "'");// 减免税方案代码,Y
				sql.append(",'"
						+ delinquentTaxDue.getDerate()
								.getDeductionDueProportion() + "'");// 减免比例
				sql.append("," + delinquentTaxDue.getDerate().getDeduction());// 减免金额
				sql.append(",'"
						+ delinquentTaxDue.getDerate()
								.getDeductionDocumentNumber() + "'");// 减免税凭证号

			} else {
				sql.append(",'','',0,0,'','',''");
			}

			sql.append("," + delinquentTaxDue.getTaxDue());// 当期应纳税额
			String ExceedDate = delinquentTaxDue.getExceedDate();
			if (ExceedDate != null && ExceedDate.length() >= 10) {
				String exceD = ExceedDate.substring(0, 10);
				ExceedDate = exceD;
			} else {
				ExceedDate = "";
			}
			sql.append(",TO_DATE('" + ExceedDate + "','YYYY-MM-DD')");// 逾期时间
			sql.append("," + delinquentTaxDue.getExceedDaysCount());// 逾期天数
			sql.append("," + delinquentTaxDue.getOverDue());// 滞纳金
			sql.append("," + delinquentTaxDue.getTotalAmount());// 合计金额
		} else {
			sql.append(",'','','',0,0,0,'','','','','','','',0,0,'',0,'',0,0,0");
		}
		sql.append(",'" + CfgLoader.getConfigValue("SysCode_USER", "username") + "'");
		sql.append(",'" + CfgLoader.getConfigValue("SysCode_USER", "usercode") + "'");
		sql.append(",to_date('" + DateUtil.getStringDate(new Date(),"yyyy-MM-dd HH:mm:ss") + "','yyyy-mm-dd hh24:Mi:ss')");
		// mili 2015-5-19 15:35:58   补传时  截取 确认码的 时间作为 系统采集时间   start
//		if (BCCRI.getCalcTaxFlag() != null
//				&& BCCRI.getCalcTaxFlag().equals("1") && "2".equals(BCFlag)) {
//			sql.append(",to_date('" + DateUtil.getStringDate(new Date(),"yyyy-MM-dd HH:mm:ss") + "','yyyy-mm-dd hh24:Mi:ss')");
//		}else{
//			String sjcjrq = BCCRI.getChangeConfirmNo().getTaxDealCode_Type().substring(12, 26);
//			sql.append(",to_date('" + DateUtil.getStringDate(DateUtil.change_Date(sjcjrq),"yyyy-MM-dd HH:mm:ss") + "','yyyy-mm-dd hh24:Mi:ss')");
//		}
		// mili 2015-5-19 15:35:58   补传时  截取 确认码的 时间作为 系统采集时间   end
		// START 2013-01-04 BY CHENJIAJUN
//		if (BCCRI.getTaxInfo() != null
//				&& BCCRI.getTaxInfo().getPayDate() != null
//				&& !"".equals(BCCRI.getTaxInfo().getPayDate()) && BCCRI.getTaxInfo().getPayDate().length()>3) {
//			sql.append(",'" + BCCRI.getTaxInfo().getPayDate().substring(0, 4) + "'");// 所属年度
//		} else {
//			sql.append(",'"
//					+ delinquentTaxDue.getTaxStartDate().substring(0, 4) + "'");// 所属年度
//		}
		if(delinquentTaxDue!=null && delinquentTaxDue.getTaxStartDate()!=null && delinquentTaxDue.getTaxStartDate().length()>3){
			sql.append(",'"
					+ delinquentTaxDue.getTaxStartDate().substring(0, 4) + "'");// 所属年度wbzhao add 20151224
		}else{
			sql.append(",'"
					+ taxConfirmNo.substring(12, 16) + "'");// 所属年度
		}

		// END 2013-01-04 BY CHENJIJAUN
		sql.append(",'" + BCCRI.getTaxInfo().getTaxAmount().getTaxAmount_Flag()
				+ "'");
		sql.append("," + BCCRI.getTaxInfo().getTaxAmount().getAnnualTaxDue());
		sql.append("," + BCCRI.getTaxInfo().getTaxAmount().getSumTaxDefault());
		sql.append("," + BCCRI.getTaxInfo().getTaxAmount().getSumOverdue());
		sql.append("," + BCCRI.getTaxInfo().getTaxAmount().getSumTax());
		sql.append(",'0'");// 注销状态
		String Refusetype = "1";
		if ("R".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
			Ptzt = "4";
			Refusetype = "0";
		} else if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode())
				|| "9".equals(BCCRI.getCalcTaxFlag())) {
			Ptzt = "1";
		} else {
			if (platformstate.equals("1")) {
				Ptzt = "1";
			} else {
				Ptzt = "0";
			}
		}
		sql.append(",'" + Ptzt + "'");// 平台 0=代收 1=申报（完税）
		String ct = "";
		if ("1".equals(BCCRI.getChangeType())) {
			ct = "4";
		} else {
			ct = BCCRI.getChangeType();
		}
		sql.append(",'" + ct + "'");// 变更类型 0=确认 4=退保，2=批改
//		sql.append(",'" + BCCRI.getChangeType() + "'");//变更类型 0=确认 4=退保，2=批改
		sql.append(",'" + BCCRI.getCalcTaxFlag() + "'");// 算税标志 1=税源 2，3=平台（补传）
		sql.append(",'" + Refusetype + "'");
		if ("1".equals(BCCRI.getCalcTaxFlag()) && "2".equals(BCFlag)) {
			String fisdate = "";
			if (queryRes != null && queryRes.getFIRSTREGISTERDATE() != null) {
				fisdate = DateUtil.getStringDate(queryRes.getFIRSTREGISTERDATE(),"yyyy-MM-dd");
				if (fisdate.length() >= 10) {
					fisdate = fisdate.substring(0, 10);
				}else{
					fisdate = "" ;
				}
			}
			if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode()))
				sql.append(",sysdate");
			else
				sql.append(",null");

			sql.append(",TO_DATE('" + fisdate + "','YYYY-MM-DD'),'"
					+ queryRes.getSPECIALCARTYPE() + "','" + queryRes.getTSBZ()
					+ "'");
		} else {
			String fisdate = "";
			if (BCCRI.getVehicleInfo() != null
					&& BCCRI.getVehicleInfo().getFirstRegisterDate() != null) {
				if (BCCRI.getVehicleInfo().getFirstRegisterDate().length() >= 10) {
					fisdate = BCCRI.getVehicleInfo().getFirstRegisterDate()
							.substring(0, 10);
				}
			}
			// 如果算税标志9（申报复用字段）
			if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				sql.append(",sysdate");
			} else if ("9".equals(BCCRI.getCalcTaxFlag())) {
				String statdate = this.getTime(BCCRI.getTaxInfo().getTaxAmount()
						.getTaxDealCode().getTaxDealCode_Type());
				sql.append(",to_date('" + statdate
								+ "','yyyy-mm-dd hh:Mi:ss')");
			} else {
				sql.append(",null");
			}
			String tsbz = "";
			if ("2".equals(BCCRI.getChangeType())) {
				tsbz = "0";
			} else if ("1".equals(BCCRI.getChangeType())) {
				tsbz = "1";
			} else {
				tsbz = "2";
			}
			sql.append(",TO_DATE('" + fisdate + "','YYYY-MM-DD')");
			if(BCCRI.getVehicleInfo() != null){
				sql.append(",'" + BCCRI.getVehicleInfo().getSpecialCarType() + "'");
			}else{
				sql.append(",null");
			}
			sql.append(",'" + tsbz+ "'");
		}
		if(queryRes != null && !"".equals(queryRes)){
			sql.append(",'" + queryRes.getCARSERIALNO() + "'");
		}else{
//			sql.append(",'" + BCCRI.getCarSerialNo() + "'");	// 问题名单序列号
			sql.append(",'null'");
		}
		if (changeRes.split(",")[0] != null
				&& !"".equals(changeRes.split(",")[0])) {
			if (!"".equals(changeRes)) {
				sql.append(",'" + changeRes.split(",")[0] + "'");
			} else {
				sql.append(",null");
			}
		} else {
			sql.append(",'" + BCCRI.getVehicleInfo().getEngineNo() + "'");
		}
		// 投保开始日期存在
		if (BCCRI.getInsureStartDate() != null) {
			sql.append(", TO_DATE('" + DateUtil.getStringDate(BCCRI.getInsureStartDate(), "yyyy-MM-dd")
					+ "','YYYY-MM-DD')");
		// 投保开始日期不存在
		} else {
			if (!"".equals(changeRes)) {
				sql.append(",'" + changeRes.split(",")[1] + "'");
			} else {
				sql.append(",null");
			}
		}
		// 投保结束日期存在
		if (BCCRI.getInsureEndDate() != null) {
			sql.append(", TO_DATE('" + DateUtil.getStringDate(BCCRI.getInsureEndDate(), "yyyy-MM-dd")
					+ "','YYYY-MM-DD')");
		// 投保结束日期不存在
		} else {
			if (!"".equals(changeRes)) {
				sql.append(",'" + changeRes.split(",")[2] + "'");
			} else {
				sql.append(",null");
			}
		}
		if(rkmx!=null && rkmx.getCityCode()!=null){
			sql.append(",'"+rkmx.getCityCode()+"'"); //wbzhao 20150126
		}else{
			sql.append(",null");
		}
		if(rkmx!=null && rkmx.getCountryCode()!=null){
			sql.append(",'"+rkmx.getCountryCode()+"'");
		}else{
			sql.append(",null");
		}

		sql.append(" )");
		String s = sql.toString();
		s = s.replaceAll("'null'", "null");
		return s;
	}
	/**
	 * @author MILI
	 * @time 2014-5-22 9:28:13
	 * @描述：
	 * */
	public String getTime(String TAXCONFIRMNO) {
		System.out.println("------------------税源确认，平台批改，申报后，补传批改记录。");
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		String newTime = "";
		try {
			conn = DBConnPool.getConnection();
			String sql = "SELECT TO_CHAR(STATUSDATE,'YYYY-MM-DD HH:MI:SS') AS STATUSDATE  FROM SYJK_CCS_RKMX WHERE TAXCONFIRMNO='"
					+ TAXCONFIRMNO + "' AND ROWNUM < 2";
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				newTime = rs.getString("STATUSDATE");
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newTime;
	}
	/**
	 * 拼出入库信息（SYJK_CCS_RKMX_QS）sql语句
	 * @param baseconfirmReqinfo
	 * @param codetype
	 * @return
	 */
	public String getSYJK_CCS_RKMX_QS(BaseChangeConfirmReqInfo BCCRI,
			TaxDealCode_Type taxQueryNo, AnnualTax_Type delinquentTaxDue,
			String taxConfirmNo, SYJK_CCS_CCSBGCXCCJB queryRes,
			String platformstate, String changeRes) {
		StringBuffer sql = new StringBuffer(
				"INSERT INTO SYJK_CCS_RKMX_QS("
						+ "TAXQUERYNO,TAXCONFIRMNO,TAXPRINTNO,VIN,HPHM,HPZL,CLLX,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,TAXPAYERIDENTIFICATIONCODE ,PAYCOMPANYCODE,"
						+ "TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,UNITRATE,ANNUALTAXAMOUNT,"
						+ "TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,"
						+ "DEDUCTIONDEPARTMENTCODE,DEDUCTIONDEPARTMENT,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,"
						+ "TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,REVENUECODE,SJCJRQ,PAYDATE,"
						+ "TAXAMOUNT_FLAG ,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX ,LOGGEDOUT,PLATFORMSTATE,CHANGETYPE,COUNTTAXTYPE,REFUSETYPE,STATUSDATE,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARSERIALNO "
						+ ",ENGINENO,INSURESTARTDATE,INSUREENDDATE " 
						+ ")VALUES(");
		String Ptzt = "";
		String middleNo = "";
		String BCFlag = "2";
		if (BCCRI.getChangeConfirmNo() != null) {
			if (BCCRI.getChangeConfirmNo().getTaxDealCode_Type() != null
					&& !"".trim().equals(
							BCCRI.getChangeConfirmNo().getTaxDealCode_Type()
									.trim())) {
				BCFlag = "1";
			}
		}
		if (BCCRI.getCalcTaxFlag() != null
				&& BCCRI.getCalcTaxFlag().equals("1") && "2".equals(BCFlag)) {
			String agoNo = taxConfirmNo.substring(0, 11);
			String endNo = taxConfirmNo.substring(12, taxConfirmNo.length());
			middleNo = agoNo + "G" + endNo;
			sql.append("'" + taxQueryNo.getTaxDealCode_Type() + "'");// 车船税查询码,唯一标示符
			sql.append(",'" + taxConfirmNo + "'");// 车船税查询码,唯一标示符
			if ("R".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				sql.append(",''");// 车船税查询码,唯一标示符
			} else {
				sql.append(",'" + middleNo + "'");// 车船税查询码,唯一标示符
			}
			sql.append(",'" + queryRes.getVIN() + "'");		// VIN
			sql.append(",'" + queryRes.getHPHM() + "'");	// HPHM
			sql.append(",'" + queryRes.getHPZL() + "'");	// HPZL
			sql.append(",'" + queryRes.getCLLX() + "'");	// CLLX
		} else {
			sql.append("'" + BCCRI.getChangeConfirmNo().getTaxDealCode_Type()
					+ "'");// 车船税查询码,唯一标示符
			sql.append(",'" + BCCRI.getChangeConfirmNo().getTaxDealCode_Type()
					+ "'");// 车船税查询码,唯一标示符
			if (BCCRI.getTaxPrintNo() == null) {
				sql.append(",''");
			} else {
				sql.append(",'" + BCCRI.getTaxPrintNo().getTaxDealCode_Type()
						+ "'");// 车船税查询码,唯一标示符
			}

			if (BCCRI.getVehicleInfo() != null) {
				sql.append(",'" + BCCRI.getVehicleInfo().getVIN() + "'");// VIN
				sql.append(",'" + BCCRI.getVehicleInfo().getLicensePlateNo()
						+ "'");// HPHM
				sql.append(",'" + BCCRI.getVehicleInfo().getLicensePlateType()
						+ "'");// HPZL
				sql.append(",'" + BCCRI.getVehicleInfo().getMotorTypeCode()
						+ "'");// CLLX
			} else {
				sql.append(",'','','',''");

			}
		}
		sql.append(",'" + BCCRI.getTaxInfo().getTaxConditionCode() + "'");	// 纳税类型代码,Y
		sql.append(",'" + BCCRI.getTaxInfo().getTaxRegistryNumber() + "'");	// 税务登记证号
		sql.append(",'" + BCCRI.getTaxInfo().getTaxPayerName() + "'");		// 纳税人名称
		sql.append(",'" + BCCRI.getTaxInfo().getTaxPayerIdentificationCode()
				+ "'");// 纳税人识别号
		if (BCCRI.getTaxInfo().getPayCompanyCode() != null
				&& !"".equals(BCCRI.getTaxInfo().getPayCompanyCode())) {
			sql.append(",'" + BCCRI.getTaxInfo().getPayCompanyCode() + "'");// 代收公司
		} else if (BCCRI.getCompanyCode() != null
				&& !"".equals(BCCRI.getCompanyCode())) {
			sql.append(",'" + BCCRI.getCompanyCode() + "'");// 代收公司
		} else {
			sql.append(",'"
					+ BCCRI.getChangeQueryNo().getTaxDealCode_Type().substring(
							1, 5) + "'");// 代收公司
		}
		if (delinquentTaxDue != null) {
			sql.append(",'" + delinquentTaxDue.getTaxLocationCode() + "'");// 纳税地区代码
			String startDate = delinquentTaxDue.getTaxStartDate();
			String endDate = delinquentTaxDue.getTaxEndDate();
			if (startDate != null && startDate.length() >= 10) {
				String sd = startDate.substring(0, 10);
				startDate = sd;
			} else {
				startDate = "";
			}
			if (endDate != null && endDate.length() >= 10) {
				String ed = endDate.substring(0, 10);
				endDate = ed;
			} else {
				endDate = "";
			}
			sql.append(",TO_DATE('" + startDate + "','YYYY-MM-DD')");// 税款所属始期
			sql.append(",TO_DATE('" + endDate + "','YYYY-MM-DD')");// 税款所属止期

			sql.append(",'" + delinquentTaxDue.getTaxUnitTypeCode() + "'");// 计税单位代码
			sql.append("," + delinquentTaxDue.getUnitRate());// 单位计税金额
			sql.append("," + delinquentTaxDue.getAnnualTaxAmount());// 当期年单位税额
			if (delinquentTaxDue.getPaid() != null) {
				sql.append(",'"
						+ delinquentTaxDue.getPaid().getTaxDepartmentCode()
						+ "'");// 开具减免税(完税)凭证的税务机关代码,Y
				sql.append(",'" + delinquentTaxDue.getPaid().getTaxDepartment()
						+ "'");// 开具减免税(完税)凭证的税务机关名称,Y
				sql.append(",'"
						+ delinquentTaxDue.getPaid().getTaxDocumentNumber()
						+ "'");// 完税凭证号码
			} else {
				sql.append(",'','',''");// 开具减免税(完税)凭证的税务机关代码,Y
			}
			if (delinquentTaxDue.getDerate() != null) {
				sql.append(",'"
						+ delinquentTaxDue.getDerate().getTaxDepartmentCode()
						+ "'");// 开具减免税(完税)凭证的税务机关代码,Y
				sql.append(",'"
								+ delinquentTaxDue.getDerate()
										.getTaxDepartment() + "'");// 开具减免税(完税)凭证的税务机关名称,Y
				sql.append(",'"
						+ delinquentTaxDue.getDerate().getDeductionDueCode()
						+ "'");// 减免税原因代码,Y
				sql.append(",'"
						+ delinquentTaxDue.getDerate().getDeductionDueType()
						+ "'");// 减免税方案代码,Y
				sql.append(",'"
						+ delinquentTaxDue.getDerate()
								.getDeductionDueProportion() + "'");// 减免比例
				sql.append("," + delinquentTaxDue.getDerate().getDeduction());// 减免金额
				sql.append(",'"
						+ delinquentTaxDue.getDerate()
								.getDeductionDocumentNumber() + "'");// 减免税凭证号

			} else {
				sql.append(",'','',0,0,'','',''");
			}

			sql.append("," + delinquentTaxDue.getTaxDue());// 当期应纳税额
			String ExceedDate = delinquentTaxDue.getExceedDate();
			if (ExceedDate != null && ExceedDate.length() >= 10) {
				String exceD = ExceedDate.substring(0, 10);
				ExceedDate = exceD;
			} else {
				ExceedDate = "";
			}
			sql.append(",TO_DATE('" + ExceedDate + "','YYYY-MM-DD')");// 逾期时间
			sql.append("," + delinquentTaxDue.getExceedDaysCount());// 逾期天数
			sql.append("," + delinquentTaxDue.getOverDue());// 滞纳金
			sql.append("," + delinquentTaxDue.getTotalAmount());// 合计金额
			System.out.println("------TotalAmount---------- : "
					+ delinquentTaxDue.getTotalAmount());
		} else {
			sql.append(",'','','',0,0,0,'','','','','','','',0,0,'',0,'',0,0,0");
		}
		sql.append(",'" + CfgLoader.getConfigValue("SysCode_USER", "username") + "'");
		sql.append(",'" + CfgLoader.getConfigValue("SysCode_USER", "usercode") + "'");
		sql.append(", sysdate");
		// START 2013-01-10 BY CHENJIAJUN
		if (BCCRI.getTaxInfo() != null
				&& BCCRI.getTaxInfo().getPayDate() != null
				&& !"".equals(BCCRI.getTaxInfo().getPayDate())) {
			sql.append(",'" + BCCRI.getTaxInfo().getPayDate().subSequence(0, 4) + "'");// 所属年度	//wbzhao20151228 substr 0-4
		} else if (delinquentTaxDue.getTaxStartDate() != null
				&& "".equals(delinquentTaxDue.getTaxStartDate())) {
			sql.append(",'"
					+ delinquentTaxDue.getTaxStartDate().substring(0, 4) + "'");// 所属年度
		} else {
			sql.append(",null");// 所属年度
		}
		// END 2013-01-10 BY CHENJIJAUN
		sql.append(",'" + BCCRI.getTaxInfo().getTaxAmount().getTaxAmount_Flag()
				+ "'");
		sql.append("," + BCCRI.getTaxInfo().getTaxAmount().getAnnualTaxDue());
		sql.append("," + BCCRI.getTaxInfo().getTaxAmount().getSumTaxDefault());
		sql.append("," + BCCRI.getTaxInfo().getTaxAmount().getSumOverdue());
		sql.append("," + BCCRI.getTaxInfo().getTaxAmount().getSumTax());
		sql.append(",'0'");// 注销状态
		String Refusetype = "1";
		if ("R".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
			Ptzt = "4";
			Refusetype = "0";
		} else if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
			Ptzt = "1";
		} else {
			if (platformstate.equals("1")) {
				Ptzt = "1";
			} else {
				Ptzt = "0";
			}
		}
		sql.append(",'" + Ptzt + "'");// 平台 0=代收 1=申报（完税）
		String ct = "";
		if ("1".equals(BCCRI.getChangeType())) {
			ct = "4";
		} else {
			ct = BCCRI.getChangeType();
		}
		sql.append(",'" + ct + "'");// 变更类型 0=确认 4=退保，2=批改
		sql.append(",'" + BCCRI.getCalcTaxFlag() + "'");// 算税标志 1=税源 2，3=平台（补传）
		sql.append(",'" + Refusetype + "'");
		if ("1".equals(BCCRI.getCalcTaxFlag()) && "2".equals(BCFlag)) {
			String fisdate = "";
			if (queryRes != null && queryRes.getFIRSTREGISTERDATE() != null) {
				fisdate = DateUtil.getStringDate(queryRes.getFIRSTREGISTERDATE(),"yyyy-MM-dd");
				if (fisdate.length() >= 10) {
					fisdate = fisdate.substring(0, 10);
				}else{
					fisdate = "" ;
				}
			}
			if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode()))
				sql.append(",sysdate");
			else
				sql.append(",null");
			sql.append(",TO_DATE('" + fisdate + "','YYYY-MM-DD'),'"
					+ queryRes.getSPECIALCARTYPE() + "','" + queryRes.getTSBZ()
					+ "'");
		} else {
			String fisdate = "";
			if (BCCRI.getVehicleInfo() != null
					&& BCCRI.getVehicleInfo().getFirstRegisterDate() != null) {
				if (BCCRI.getVehicleInfo().getFirstRegisterDate().length() >= 10) {
					fisdate = BCCRI.getVehicleInfo().getFirstRegisterDate()
							.substring(0, 10);
				}
			}
			if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode()))
				sql.append(",sysdate");
			else
				sql.append(",null");
			String tsbz = "";
			if ("2".equals(BCCRI.getChangeType())) {
				tsbz = "0";
			} else if ("1".equals(BCCRI.getChangeType())) {
				tsbz = "1";
			} else {
				tsbz = "2";
			}
			sql.append(",TO_DATE('" + fisdate + "','YYYY-MM-DD'),'"
					+ BCCRI.getVehicleInfo().getSpecialCarType() + "','" + tsbz
					+ "'");
		}
		if(queryRes != null || "".equals(queryRes) ){
			sql.append(",'" + queryRes.getCARSERIALNO() + "'");
		}else{
//			sql.append(",'" + BCCRI.getCarSerialNo() + "'");	// 问题名单序列号
			sql.append(",'null'");
		}
		if(queryRes != null){
			sql.append(",'" + queryRes.getENGINENO() + "'");
		}else{
			sql.append(",null");
		}
//		if (BCCRI.getVehicleInfo() != null && !"".equals(BCCRI.getVehicleInfo())) {   // mili  2013-10-11 18:30:34 
//			sql.append(",'" + BCCRI.getVehicleInfo().getEngineNo() + "'");
//		} else {
//			if (!"".equals(changeRes)) {
//				sql.append(",'" + changeRes.split(",")[0] + "'");
//			} else {
//				sql.append(",null");
//			}
//		}
		
		// 投保开始日期存在
		if (BCCRI.getInsureStartDate() != null) {
			sql.append(", TO_DATE('" + DateUtil.getStringDate(BCCRI.getInsureStartDate(),"yyyy-MM-dd")
					+ "','YYYY-MM-DD')");
			// 投保开始日期不存在
		} else {
			if (!"".equals(changeRes)) {
				sql.append(",'" + changeRes.split(",")[1] + "'");
			} else {
				sql.append(",null");
			}
		}
		// 投保结束日期存在
		if (BCCRI.getInsureEndDate() != null) {
			sql.append(", TO_DATE('" +DateUtil.getStringDate(BCCRI.getInsureEndDate(),"yyyy-MM-dd")
					+ "','YYYY-MM-DD')");
			// 投保结束日期不存在
		} else {
			if (!"".equals(changeRes)) {
				sql.append(",'" + changeRes.split(",")[2] + "'");
			} else {
				sql.append(",null");
			}
		}
		sql.append(" )");
		String s = sql.toString();
		s = s.replaceAll("'null'", "null");
		return s;
	}
	/**
	 * @author MILI
	 * @Date 2015年11月5日09:27:29
	 * @描述：更新完税库退保标志 字段 （tsbz）
	 * */
	public String Update_WS(SYJK_CCS_CCSBGCXCCJB queryRes){
		StringBuffer sbf = new StringBuffer()  ;
		sbf.append("UPDATE SYJK_CCS_WSDJXX W SET TSBZ = 'Y' ");
		String vin = queryRes.getVIN() == null ? "" : queryRes.getVIN().trim();				// 	车架号
		String cllx = queryRes.getMODEL() == null ? "" : queryRes.getMODEL().trim();		// 	车辆类型
		String hphm = queryRes.getHPHM() == null ? "" : queryRes.getHPHM().trim();		// 号牌号码
		String[] flag = CfgLoader.getConfigValue("SysSwitch", "WSDJXX_FLAG").split(",");		// 匹配条件开关
		StringBuilder where = new StringBuilder();
		if("1".equals(flag[0])){
			if(!"".equals(vin)){
				StringUtil.paramSQLC(where).append(" W.CCSBDM = '" + vin + "'");
			}
		}
		if("1".equals(flag[1])){
			if(!"".equals(cllx)){
				StringUtil.paramSQLC(where).append(" W.CLLX = '" + cllx + "'");
			}
		}
		if("1".equals(flag[2])){
			if(!"".equals(hphm)){
				StringUtil.paramSQLC(where).append(" W.CLHPHM = '" + hphm +"'");
			}
		}
		String sql = sbf.append(where.toString()).toString();
		return sql ;
	}
}
