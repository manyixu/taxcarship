package com.derun.taxchangequery.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.derun.all.common.SqlDao;
import com.derun.common.db.SqlText;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSBGCXRCJB;
import com.derun.model.po.SYJK_CCS_CCSBGXX;

/**
 * @author MILI
 * @time 2014-4-17 15:34:17
 * @描述：数据库操作sql类
 * */
public class TaxChangQueryDao_SQL {
	static String TODATE = "TO_DATE" ;
	static String YMDS = "yyyy-MM-dd HH:mm:ss" ;
	static String YMD = "yyyy-MM-dd" ;
	static String YMD_HMS = "yyyy-MM-dd hh24:mi:ss" ;
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_CCSBGCXCCJB  sql
	 * */
	public String getCCSBGCXCCJB_sql(SYJK_CCS_CCSBGCXCCJB ccsbgcxccjb){
		String sql = "" ;
		StringBuffer sbf = new StringBuffer();
		sbf.append(SqlText.C_03_CCSBGCXCCJB_001);
		sbf.append("'");
		sbf.append(ccsbgcxccjb.getTAXQUERYNO() + "','");
		sbf.append(ccsbgcxccjb.getCALCTAXFLAG() + "','");
		sbf.append(ccsbgcxccjb.getTAXAMOUNT_FL() + "',");
		sbf.append(ccsbgcxccjb.getANNUALTAXDUE() + ",");
		sbf.append(ccsbgcxccjb.getSUMTAXDEFAULT() + ",");
		sbf.append(ccsbgcxccjb.getSUMOVERDUE() + ",");
		sbf.append(ccsbgcxccjb.getSUMTAX() + ",'");
		sbf.append(ccsbgcxccjb.getRETURNCODE() + "','");
		sbf.append(ccsbgcxccjb.getTAXCONFIRMNO() + "',");
		sbf.append(ccsbgcxccjb.getCHANGESUMTAX() + ",'");
		sbf.append(ccsbgcxccjb.getLOGINNAME() + "','");
		sbf.append(ccsbgcxccjb.getREVENUECODE() + "',");
		sbf.append("TO_DATE('" + DateUtil.getStringDate(ccsbgcxccjb.getSJCJRQ(),YMDS) + "','"+ YMD_HMS + "'),'");
//		sbf.append(this.getDate_String(ccsbgcxccjb.getSJCJRQ(),YMDS) + ",'");
		sbf.append(ccsbgcxccjb.getSJCJFS() + "','");
		sbf.append(ccsbgcxccjb.getVIN() + "','");
		sbf.append(ccsbgcxccjb.getHPHM() + "','");
		sbf.append(ccsbgcxccjb.getHPZL() + "','");
		sbf.append(ccsbgcxccjb.getCLLX() + "','");
		
		sbf.append(ccsbgcxccjb.getMOTORUSAGETYPECODE() + "','"); 	// 使用性质
		sbf.append(ccsbgcxccjb.getMODEL() + "','"); 							// 车辆型号
		sbf.append(ccsbgcxccjb.getVEHICLETYPE() + "',"); 				// 交管车辆类型
		sbf.append(ccsbgcxccjb.getRATEDPASSENGERCAPACITY() + ",");// 核定载客数
		sbf.append(ccsbgcxccjb.getTONNAGE() + ",");						// 核定载质量
		sbf.append(ccsbgcxccjb.getWHOLEWEIGHT() + ",");				// 整备质量
		sbf.append(ccsbgcxccjb.getDISPLACEMENT() + ",");				// 排量
		sbf.append(ccsbgcxccjb.getPOWER() + ",'");							// 功率
		sbf.append(ccsbgcxccjb.getFUELTYPE() + "','"); 						// 源种类
		
		sbf.append(ccsbgcxccjb.getMOTORTYPECODE() + "','"); 
		sbf.append(ccsbgcxccjb.getMADEFACTORY() + "','"); 
		sbf.append(ccsbgcxccjb.getNOLICENSEFLAG() + "','"); 
		sbf.append(ccsbgcxccjb.getISINSERT() + "',"); 
		
		sbf.append(this.getDate_String(ccsbgcxccjb.getFIRSTREGISTERDATE(),YMD) + ",'");
		sbf.append(ccsbgcxccjb.getSPECIALCARTYPE() + "','");
		sbf.append(ccsbgcxccjb.getTSBZ() + "','");
		sbf.append(ccsbgcxccjb.getCARSERIALNO() + "','");
		sbf.append(ccsbgcxccjb.getENGINENO() + "',");
		sbf.append(this.getDate_String(ccsbgcxccjb.getINSURESTARTDATE(),YMD) + ",");
		sbf.append(this.getDate_String(ccsbgcxccjb.getINSUREENDDATE(),YMD) + ",'");
		sbf.append(ccsbgcxccjb.getCARMATCHID() + "'");
		sbf.append(")");
		sql = SqlDao.off_NUll(sbf.toString());
		return sql ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库   SYJK_CCS_CCSBGCXRCJB sql
	 * */
	public String getCCSBGCXRCJB_sql(SYJK_CCS_CCSBGCXRCJB ccsgbcxrcjb) {
		String sql_ = "";
		StringBuffer sql = new StringBuffer();
		sql.append(SqlText.C_03_CCSBGCXRCJB_001);
		sql.append("'");
		sql.append(ccsgbcxrcjb.getTAXQUERYNO() + "','");
		sql.append(ccsgbcxrcjb.getTAXCONFIRMNO() + "','");
		sql.append(ccsgbcxrcjb.getCHANGETYPE() + "','");
		sql.append(ccsgbcxrcjb.getLICENSEPLATENO() + "','");
		sql.append(ccsgbcxrcjb.getLICENSEPLATETYPE() + "','");
		sql.append(ccsgbcxrcjb.getMOTORTYPECODE() + "','");
		sql.append(ccsgbcxrcjb.getENGINENO() + "','");
		sql.append(ccsgbcxrcjb.getVIN() + "','");
		sql.append(ccsgbcxrcjb.getMADEFACTORY() + "','");
		sql.append(ccsgbcxrcjb.getMOTORUSAGETYPECODE() + "','");
		sql.append(ccsgbcxrcjb.getNOLICENSEFLAG() + "','");
		sql.append(ccsgbcxrcjb.getMODEL() + "',");
		sql.append(this.getDate_String(ccsgbcxrcjb.getFIRSTREGISTERDATE(),YMD) + ",'");
		sql.append(ccsgbcxrcjb.getVEHICLETYPE() + "',");
		sql.append(ccsgbcxrcjb.getRATEDPASSENGERCAPACITY() + ",");
		sql.append(ccsgbcxrcjb.getTONNAGE() + ",");
		sql.append(ccsgbcxrcjb.getWHOLEWEIGHT() + ",");
		sql.append(ccsgbcxrcjb.getDISPLACEMENT() + ",");
		sql.append(ccsgbcxrcjb.getPOWER() + ",'");
		sql.append(ccsgbcxrcjb.getFUELTYPE() + "','");
		sql.append(ccsgbcxrcjb.getVEHICLEOWNERNAME() + "','");
		sql.append(ccsgbcxrcjb.getCERTICODE() + "','");
		sql.append(ccsgbcxrcjb.getCREDENTIALCODE() + "','");
		sql.append(ccsgbcxrcjb.getADDRESS() + "','");
		sql.append(ccsgbcxrcjb.getPHONENO() + "','");
		sql.append(ccsgbcxrcjb.getTAXAMOUNT_FLAG() + "',");
		sql.append(ccsgbcxrcjb.getANNUALTAXDUE() + ",");
		sql.append(ccsgbcxrcjb.getSUMTAXDEFAULT() + ",");
		sql.append(ccsgbcxrcjb.getSUMOVERDUE() + ",");
		sql.append(ccsgbcxrcjb.getSUMTAX() + ",'");
		sql.append(ccsgbcxrcjb.getLOGINNAME() + "','");
		sql.append(ccsgbcxrcjb.getREVENUECODE() + "',");
		sql.append(this.getDate_String(ccsgbcxrcjb.getSJCJRQ(),YMD) + ",'");
		sql.append(ccsgbcxrcjb.getSJCJFS() + "','");
		sql.append(ccsgbcxrcjb.getSPECIALCARTYPE() + "',");
		sql.append(this.getDate_String(ccsgbcxrcjb.getINSURESTARTDATE(),YMD) + ",");
		sql.append(this.getDate_String(ccsgbcxrcjb.getINSUREENDDATE(),YMD) + ",'");
		sql.append(ccsgbcxrcjb.getCARMATCHID() + "'");
		sql.append(")");
		sql_ = SqlDao.off_NUll(sql.toString());	
		return sql_;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_CCSBGXX  sql
	 * */
	public String getCCSBGXX_sql(SYJK_CCS_CCSBGXX ccsbgxx){
		String sql_ = "" ;
		StringBuffer sql = new StringBuffer();
		sql.append(SqlText.C_03_CCSBGXX_001);
		sql.append("'");
		sql.append(ccsbgxx.getTAXQUERYNO() + "','");
		sql.append(ccsbgxx.getTAXTERMTYPECODE() + "','");
		sql.append(ccsbgxx.getTAXCONDITIONCODE() + "','");
		sql.append(ccsbgxx.getTAXREGISTRYNUMBER() + "','");
		sql.append(ccsbgxx.getTAXPAYERNAME() + "','");
		sql.append(ccsbgxx.getTAXPAYERIDENTIFICATIONCODE() + "','");
		sql.append(ccsbgxx.getTAXLOCATIONCODE() + "',");
		sql.append(this.getDate_String(ccsbgxx.getTAXSTARTDATE(),YMD) + ",");
		sql.append(this.getDate_String(ccsbgxx.getTAXENDDATE(),YMD) + ",'");
		sql.append(ccsbgxx.getTAXUNITTYPECODE() + "',");
		sql.append(ccsbgxx.getUNITRATE() + ",");
		sql.append(ccsbgxx.getANNUALTAXAMOUNT() + ",'");
		sql.append(ccsbgxx.getTAXDEPARTMENTCODE() + "','");
		sql.append(ccsbgxx.getTAXDEPARTMENT() + "','");
		sql.append(ccsbgxx.getTAXDOCUMENTNUMBER() + "','");
		sql.append(ccsbgxx.getDEDUCTIONDUECODE() + "','");
		sql.append(ccsbgxx.getDEDUCTIONDUETYPE() + "',");
		sql.append(ccsbgxx.getDEDUCTIONDUEPROPORTION() + ",");
		sql.append(ccsbgxx.getDEDUCTION() + ",'");
		sql.append(ccsbgxx.getDEDUCTIONDOCUMENTNUMBER() + "','");
		sql.append(ccsbgxx.getDEPARTMENT() + "','");
		sql.append(ccsbgxx.getDEPARTMENTCODE() + "',");
		sql.append(ccsbgxx.getTAXDUE() + ",");
		sql.append(this.getDate_String(ccsbgxx.getEXCEEDDATE(),YMD) + ",");
		sql.append(ccsbgxx.getEXCEEDDAYSCOUNT() + ",");
		sql.append(ccsbgxx.getOVERDUE() + ",");
		sql.append(ccsbgxx.getTOTALAMOUNT() + ",'");
		sql.append(ccsbgxx.getLOGINNAME() + "','");
		sql.append(ccsbgxx.getREVENUECODE() + "',");
		sql.append(this.getDate_String(ccsbgxx.getSJCJRQ(),YMD) + ",'");
		sql.append(ccsbgxx.getSJCJFS() + "','");
		sql.append(ccsbgxx.getPAYCOMPANYCODE() + "',");
		sql.append(this.getDate_String(ccsbgxx.getPAYDATE(),YMD) + ",'");
		sql.append(ccsbgxx.getPARATYPE() + "','");
		sql.append(ccsbgxx.getCARSERIALNO() + "',");
		sql.append(this.getDate_String(ccsbgxx.getINSURESTARTDATE(),YMD) + ",");
		sql.append(this.getDate_String(ccsbgxx.getINSUREENDDATE(),YMD)+ "");
		sql.append(")");
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_CCSBGXX  欠税 sql
	 * */
	public List<String> getCCSBGXX_sql_qs(List<SYJK_CCS_CCSBGXX> list_ccsbgxx){
		List<String> list_sql = new ArrayList<String>();
		for(SYJK_CCS_CCSBGXX ccsbgxx : list_ccsbgxx){
			String sql_ = "" ;
			StringBuffer sql = new StringBuffer();
			sql.append(SqlText.C_03_CCSBGXX_qs_001);
			sql.append("'");
			sql.append(ccsbgxx.getTAXQUERYNO() + "','");
			sql.append(ccsbgxx.getTAXTERMTYPECODE() + "','");
			sql.append(ccsbgxx.getTAXCONDITIONCODE() + "','");
			sql.append(ccsbgxx.getTAXREGISTRYNUMBER() + "','");
			sql.append(ccsbgxx.getTAXPAYERNAME() + "','");
			sql.append(ccsbgxx.getTAXPAYERIDENTIFICATIONCODE() + "','");
			sql.append(ccsbgxx.getTAXLOCATIONCODE() + "',");
			sql.append(this.getDate_String(ccsbgxx.getTAXSTARTDATE(),YMD) + ",");
			sql.append(this.getDate_String(ccsbgxx.getTAXENDDATE(),YMD) + ",'");
			sql.append(ccsbgxx.getTAXUNITTYPECODE() + "',");
			sql.append(ccsbgxx.getUNITRATE() + ",");
			sql.append(ccsbgxx.getANNUALTAXAMOUNT() + ",'");
			sql.append(ccsbgxx.getTAXDEPARTMENTCODE() + "','");
			sql.append(ccsbgxx.getTAXDEPARTMENT() + "','");
			sql.append(ccsbgxx.getTAXDOCUMENTNUMBER() + "','");
			sql.append(ccsbgxx.getDEDUCTIONDUECODE() + "','");
			sql.append(ccsbgxx.getDEDUCTIONDUETYPE() + "',");
			sql.append(ccsbgxx.getDEDUCTIONDUEPROPORTION() + ",");
			sql.append(ccsbgxx.getDEDUCTION() + ",'");
			sql.append(ccsbgxx.getDEDUCTIONDOCUMENTNUMBER() + "','");
			sql.append(ccsbgxx.getDEPARTMENT() + "','");
			sql.append(ccsbgxx.getDEPARTMENTCODE() + "',");
			sql.append(ccsbgxx.getTAXDUE() + ",");
			sql.append(this.getDate_String(ccsbgxx.getEXCEEDDATE(),YMD) + ",");
			sql.append(ccsbgxx.getEXCEEDDAYSCOUNT() + ",");
			sql.append(ccsbgxx.getOVERDUE() + ",");
			sql.append(ccsbgxx.getTOTALAMOUNT() + ",'");
			sql.append(ccsbgxx.getLOGINNAME() + "','");
			sql.append(ccsbgxx.getREVENUECODE() + "',");
			sql.append(this.getDate_String(ccsbgxx.getSJCJRQ(),YMD) + ",'");
			sql.append(ccsbgxx.getSJCJFS() + "','");
			sql.append(ccsbgxx.getPAYCOMPANYCODE() + "',");
			sql.append(this.getDate_String(ccsbgxx.getPAYDATE(),YMD) + ",'");
			sql.append(ccsbgxx.getPARATYPE() + "','");
			sql.append(ccsbgxx.getCARSERIALNO() + "','");
			sql.append(ccsbgxx.getTAXESFLAG() + "',");
			sql.append(this.getDate_String(ccsbgxx.getINSURESTARTDATE(),YMD) + ",");
			sql.append(this.getDate_String(ccsbgxx.getINSUREENDDATE(),YMD)+ "");
			sql.append(")");
			sql_ = SqlDao.off_NUll(sql.toString());
			list_sql.add(sql_);
		}	
		return list_sql ;
	}
	
	/**
	 * @author MILI
	 * @time 2014-5-8 16:36:15
	 * @描述：插入数据库 时间 统一转换
	 * */
	public String getDate_String(Date date,String ymds){
		String date_string = "" ;
		date_string = TODATE + "('" + DateUtil.getStringDate(date,ymds) + "','" + ymds + "')";
		return date_string ;
	}
}
