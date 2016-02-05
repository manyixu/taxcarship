package com.derun.taxconfirm.dao.impl;

import java.util.Date;

import com.derun.all.common.SqlDao;
import com.derun.common.db.SqlText;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSQRCCJBXX;
import com.derun.model.po.SYJK_CCS_CCSQRRCJBXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_RKMX_QS;

/**
 * @author MILI
 * @time 2014-4-17 15:34:17
 * @描述：数据库操作sql类
 * */
public class TaxComfirmDao_SQL {
	static String TODATE = "TO_DATE" ;
	static String YMD = "yyyy-MM-dd" ;
	static String YMDS = "yyyy-MM-dd hh:mm:ss" ;
	static String YMD_HMS = "yyyy-MM-dd hh24:mi:ss" ;
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_CCSQRCCJBXX  sql
	 * */
	public String getCCSQRCCJBXX_sql(SYJK_CCS_CCSQRCCJBXX ccsqrccjbxx){
		String sql_ = "" ;
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_CCSQRRCJBXX sql
	 * */
	public String getCCSQRRCJBXX_sql(SYJK_CCS_CCSQRRCJBXX ccsqrrcjbxx){
		String sql_ = "" ;
		StringBuffer sql = new StringBuffer();
		sql.append(SqlText.C_04_CCSQRRCJBXX_001);
		sql.append("'");
		sql.append(ccsqrrcjbxx.getTAXQUERYNO() + "','");
		sql.append(ccsqrrcjbxx.getTAXPRINTNO() + "','");
		sql.append(ccsqrrcjbxx.getTAXCONFIRMNO() + "','");
		sql.append(ccsqrrcjbxx.getCALCTAXFLAG() + "','");
		sql.append(ccsqrrcjbxx.getLICENSEPLATENO() + "','");
		sql.append(ccsqrrcjbxx.getLICENSEPLATETYPE() + "','");
		sql.append(ccsqrrcjbxx.getMOTORTYPECODE() + "','");
		sql.append(ccsqrrcjbxx.getENGINENO() + "','");
		sql.append(ccsqrrcjbxx.getVIN() + "','");
		sql.append(ccsqrrcjbxx.getMADEFACTORY() + "','");
		sql.append(ccsqrrcjbxx.getMOTORUSAGETYPECODE() + "','");
		sql.append(ccsqrrcjbxx.getNOLICENSEFLAG() + "','");
		sql.append(ccsqrrcjbxx.getMODEL() + "',");
		sql.append(this.getDate_String(ccsqrrcjbxx.getFIRSTREGISTERDATE(),YMD) + ",'");
		sql.append(ccsqrrcjbxx.getVEHICLETYPE() + "',");
		sql.append(ccsqrrcjbxx.getRATEDPASSENGERCAPACITY() + ",");
		sql.append(ccsqrrcjbxx.getTONNAGE() + ",");
		sql.append(ccsqrrcjbxx.getWHOLEWEIGHT() + ",");
		sql.append(ccsqrrcjbxx.getDISPLACEMENT() + ",");
		sql.append(ccsqrrcjbxx.getPOWER() + ",'");
		sql.append(ccsqrrcjbxx.getFUELTYPE() + "','");
		sql.append(ccsqrrcjbxx.getVEHICLEOWNERNAME() + "','");
		sql.append(ccsqrrcjbxx.getCREDENTIALNO() + "','");
		sql.append(ccsqrrcjbxx.getCREDENTIALCODE() + "','");
		sql.append(ccsqrrcjbxx.getADDRESS() + "','");
		sql.append(ccsqrrcjbxx.getPHONENO() + "','");
		sql.append(ccsqrrcjbxx.getTAXAMOUNT_FLAG() + "',");
		sql.append(ccsqrrcjbxx.getANNUALTAXDUE() + ",");
		sql.append(ccsqrrcjbxx.getSUMTAXDEFAULT() + ",");
		sql.append(ccsqrrcjbxx.getSUMOVERDUE() + ",");
		sql.append(ccsqrrcjbxx.getSUMTAX() + ",'");
		sql.append(ccsqrrcjbxx.getLOGINNAME() + "','");
		sql.append(ccsqrrcjbxx.getREVENUECODE() + "',");
		sql.append(this.getDate_String(ccsqrrcjbxx.getSJCJRQ(),YMD) + ",'");
		sql.append(ccsqrrcjbxx.getSJCJFS() + "','");
		sql.append(ccsqrrcjbxx.getSPECIALCARTYPE() + "','");
		sql.append(ccsqrrcjbxx.getDECLAREDSTATUS() + "',");
		sql.append(this.getDate_String(ccsqrrcjbxx.getSTATUSDATE(),YMD) + ",");
		sql.append(this.getDate_String(ccsqrrcjbxx.getINSURESTARTDATE(),YMD) + ",");
		sql.append(this.getDate_String(ccsqrrcjbxx.getINSUREENDDATE(),YMD) + ",'");
		sql.append(ccsqrrcjbxx.getCARMATCHID() + "'");
		sql.append(")");
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 SYJK_CCS_RKMX_QS  sql
	 * */
	public String getRKMX_QS_sql(SYJK_CCS_RKMX_QS rkmx_qs){
		String sql_ = "" ;
		StringBuffer sql = new StringBuffer();
		sql.append(SqlText.C_04_SYJK_CCS_RKMX_QS_001);
		sql.append("'");
		sql.append(rkmx_qs.getTAXQUERYNO() + "','");
		sql.append(rkmx_qs.getTAXCONFIRMNO() + "','");
//		sql.append(rkmx_qs.getTAXCONFIRMNO() + "','");
		sql.append(rkmx_qs.getTAXPRINTNO() + "','");
		sql.append(rkmx_qs.getVIN() + "','");
		sql.append(rkmx_qs.getHPHM() + "','");
		sql.append(rkmx_qs.getHPZL() + "','");
		sql.append(rkmx_qs.getCLLX() + "','");
		sql.append(rkmx_qs.getTAXCONDITIONCODE() + "','");
//		sql.append(rkmx_qs.getTAXCONDITIONCODE() + "','");
		sql.append(rkmx_qs.getTAXREGISTRYNUMBER() + "','");
		sql.append(rkmx_qs.getTAXPAYERNAME() + "','");
		sql.append(rkmx_qs.getTAXPAYERIDENTIFICATIONCODE() + "','");
		sql.append(rkmx_qs.getPAYCOMPANYCODE() + "','");
		sql.append(rkmx_qs.getTAXLOCATIONCODE() + "',");
		sql.append(this.getDate_String(rkmx_qs.getTAXSTARTDATE(),YMD) + ",");
		sql.append(this.getDate_String(rkmx_qs.getTAXENDDATE(),YMD) + ",'");
		sql.append(rkmx_qs.getTAXUNITTYPECODE() + "',");
		sql.append(rkmx_qs.getUNITRATE() + ",");
		sql.append(rkmx_qs.getANNUALTAXAMOUNT() + ",'");
		
		sql.append(rkmx_qs.getTAXDEPARTMENTCODE() + "','");
		sql.append(rkmx_qs.getTAXDEPARTMENT() + "','");
		sql.append(rkmx_qs.getTAXDOCUMENTNUMBER() + "','");
		
		sql.append(rkmx_qs.getDEDUCTIONDEPARTMENTCODE() + "','");
		sql.append(rkmx_qs.getDEDUCTIONDEPARTMENT() + "','");
		sql.append(rkmx_qs.getDEDUCTIONDUECODE() + "','");
		sql.append(rkmx_qs.getDEDUCTIONDUETYPE() + "',");
		sql.append(rkmx_qs.getDEDUCTIONDUEPROPORTION() + ",");
		sql.append(rkmx_qs.getDEDUCTION() + ",'");
		sql.append(rkmx_qs.getDEDUCTIONDOCUMENTNUMBER() + "',");
		
		sql.append(rkmx_qs.getTAXDUE() + ",");
		sql.append(this.getDate_String(rkmx_qs.getEXCEEDDATE(),YMD) + ",");
		sql.append(rkmx_qs.getEXCEEDDAYSCOUNT() + ",");
		sql.append(rkmx_qs.getOVERDUE() + ",");
		sql.append(rkmx_qs.getTOTALAMOUNT() + ",'");
		sql.append(rkmx_qs.getLOGINNAME() + "','");
		sql.append(rkmx_qs.getREVENUECODE() + "',");
		sql.append("TO_DATE('" + DateUtil.getStringDate(rkmx_qs.getSJCJRQ(),YMDS)+ "','" + YMD_HMS + "'),'");
		sql.append(rkmx_qs.getPAYDATE().substring(0, 4) + "','");//wbzhao20151228 substr 0-4
		sql.append(rkmx_qs.getTAXAMOUNT_FLAG() + "',");
		sql.append(rkmx_qs.getANNUALTAXDUE() + ",");
		sql.append(rkmx_qs.getSUMTAXDEFAULT() + ",");
		sql.append(rkmx_qs.getSUMOVERDUE() + ",");
		sql.append(rkmx_qs.getSUMTAX() + ",'");
		sql.append(rkmx_qs.getLOGGEDOUT() + "','");
		sql.append(rkmx_qs.getPLATFORMSTATE() + "','");
		sql.append(rkmx_qs.getCHANGETYPE() + "','");
		sql.append(rkmx_qs.getCOUNTTAXTYPE() + "','");
		sql.append(rkmx_qs.getREFUSETYPE() + "',");
		sql.append(this.getDate_String(rkmx_qs.getSTATUSDATE(),YMD) + ",");
		sql.append(this.getDate_String(rkmx_qs.getFIRSTREGISTERDATE(),YMD) + ",'");
		sql.append(rkmx_qs.getSPECIALCARTYPE() + "','");
		sql.append(rkmx_qs.getTSBZ() + "','");
		sql.append(rkmx_qs.getCARSERIALNO() + "','");
		sql.append(rkmx_qs.getENGINENO() + "',");
		sql.append(this.getDate_String(rkmx_qs.getINSURESTARTDATE(),YMD) + ",");
		sql.append(this.getDate_String(rkmx_qs.getINSUREENDDATE(),YMD) + "");
		sql.append(")");
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库  SYJK_CCS_RKMX sql
	 * */
	public String getRKMX_sql(SYJK_CCS_RKMX rkmx){
		String sql_ = "" ;
		StringBuffer sql = new StringBuffer();
		sql.append(SqlText.C_04_RKMX_001);
		sql.append("'");
		sql.append(rkmx.getTAXQUERYNO() + "','");
		sql.append(rkmx.getTAXCONFIRMNO() + "','");
		sql.append(rkmx.getTAXPRINTNO() + "','");
		sql.append(rkmx.getVIN() + "','");
		sql.append(rkmx.getHPHM() + "','");
		sql.append(rkmx.getHPZL() + "','");
		sql.append(rkmx.getCLLX() + "','");
		
		sql.append(rkmx.getMOTORUSAGETYPECODE()+ "','");
		sql.append(rkmx.getMODEL()+ "','");
		sql.append(rkmx.getVEHICLETYPE()+ "',");
		sql.append(rkmx.getRATEDPASSENGERCAPACITY()+ ",");
		sql.append(rkmx.getTONNAGE()+ ",");
		sql.append(rkmx.getWHOLEWEIGHT()+ ",");
		sql.append(rkmx.getDISPLACEMENT()+ ",");
		sql.append(rkmx.getPOWER()+ ",'");
		sql.append(rkmx.getFUELTYPE()+ "','");
		
		sql.append(rkmx.getTAXCONDITIONCODE() + "','");
		sql.append(rkmx.getTAXREGISTRYNUMBER() + "','");
		sql.append(rkmx.getTAXPAYERNAME() + "','");
		sql.append(rkmx.getTAXPAYERIDENTIFICATIONCODE() + "','");
		sql.append(rkmx.getPAYCOMPANYCODE() + "','");
		sql.append(rkmx.getTAXLOCATIONCODE() + "',");
		sql.append("TO_DATE('" + rkmx.getTAXSTARTDATE() + "','" + YMD + "'),");
		sql.append("TO_DATE('" + rkmx.getTAXENDDATE() + "','" + YMD + "'),'");
		sql.append(rkmx.getTAXUNITTYPECODE() + "',");
		sql.append(rkmx.getUNITRATE() + ",");
		sql.append(rkmx.getANNUALTAXAMOUNT() + ",'");
		sql.append(rkmx.getTAXDEPARTMENTCODE() + "','");
		sql.append(rkmx.getTAXDEPARTMENT() + "','");
		sql.append(rkmx.getTAXDOCUMENTNUMBER() + "','");
		sql.append(rkmx.getDEDUCTIONDEPARTMENTCODE() + "','");
		sql.append(rkmx.getDEDUCTIONDEPARTMENT() + "','");
		sql.append(rkmx.getDEDUCTIONDUECODE() + "','");
		sql.append(rkmx.getDEDUCTIONDUETYPE() + "',");
		sql.append(rkmx.getDEDUCTIONDUEPROPORTION() + ",");
		sql.append(rkmx.getDEDUCTION() + ",'");
		sql.append(rkmx.getDEDUCTIONDOCUMENTNUMBER() + "',");
		sql.append(rkmx.getTAXDUE() + ",");
		sql.append("TO_DATE('" + rkmx.getEXCEEDDATE() + "','" + YMD + "'),");
		sql.append(rkmx.getEXCEEDDAYSCOUNT() + ",");
		sql.append(rkmx.getOVERDUE() + ",");
		sql.append(rkmx.getTOTALAMOUNT() + ",'");
		sql.append(rkmx.getLOGINNAME() + "','");
		sql.append(rkmx.getREVENUECODE() + "',");
		sql.append("TO_DATE('" + rkmx.getSJCJRQ() + "','" + YMD_HMS + "'),'");
		sql.append(rkmx.getPAYDATE().substring(0, 4) + "','");//wbzhao20151228 substr 0-4
		sql.append(rkmx.getTAXAMOUNT_FLAG() + "',");
		sql.append(rkmx.getANNUALTAXDUE() + ",");
		sql.append(rkmx.getSUMTAXDEFAULT() + ",");
		sql.append(rkmx.getSUMOVERDUE() + ",");
		sql.append(rkmx.getSUMTAX() + ",'");
		sql.append(rkmx.getLOGGEDOUT() + "','");
		sql.append(rkmx.getPLATFORMSTATE() + "','");
		sql.append(rkmx.getCHANGETYPE() + "','");
		sql.append(rkmx.getCOUNTTAXTYPE() + "',");
		sql.append(rkmx.getRefusetype() + ",");
		sql.append("TO_DATE('" + rkmx.getSTATUSDATE() + "','" + YMD + "'),");
		sql.append("TO_DATE('" + rkmx.getFIRSTREGISTERDATE() + "','" + YMD + "'),'");
		sql.append(rkmx.getSPECIALCARTYPE() + "','");
		sql.append(rkmx.getTSBZ() + "','");
		sql.append(rkmx.getCARSERIALNO() + "','");
		sql.append(rkmx.getEngineNo() + "',");
		sql.append(this.getDate_String(rkmx.getInsureStartDate(),YMD) + ",");
		sql.append(this.getDate_String(rkmx.getInsureEndDate(),YMD) + ",'");
		sql.append(rkmx.getCARMATCHID() + "','");
		sql.append(rkmx.getCityCode() + "','");
		sql.append(rkmx.getCountryCode() + "'");
		sql.append(")");
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-15 16:49:37
	 * @描述：插入数据库 时间 统一转换
	 * */
	public String getDate_String(Date date,String ymds){
		String date_string = "" ;
		date_string = TODATE + "('" +DateUtil.getStringDate(date,ymds) + "','" + ymds + "')";
		return date_string ;
	}
}
