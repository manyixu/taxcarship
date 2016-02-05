package com.derun.taxquery.dao;

import java.util.Date;

import com.derun.all.common.SqlDao;
import com.derun.common.db.SqlText;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
import com.derun.model.po.SYJK_CCS_CCSCXRCJBXX;
import com.derun.model.po.SYJK_CCS_CCSXX;
/**
 * @author MILI
 * @time 2014-4-17 15:34:17
 * @描述：数据库操作sql类
 * */
public class TaxQueryDao_SQL {
	static String TODATE = "TO_DATE" ;
	static String YMD = "yyyy-MM-dd" ;
	static String YMD_HMS = "yyyy-MM-dd hh24:mi:ss" ;
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 CCSCXCCJBXX sql
	 * */
	public String getCCSCXCCJBXX_sql(SYJK_CCS_CCSCXCCJBXX ccscxccjbxx){
		String sql_ = "" ;
		StringBuffer sql = new StringBuffer();
		sql.append(SqlText.C_01_CCSCXCCJBXX_001);	
		
		sql.append(ccscxccjbxx.getTAXQUERYNO()+ "','");
		sql.append(ccscxccjbxx.getCALCTAXFLAG()+ "','");
		sql.append(ccscxccjbxx.getTAXAMOUNT_FLAG()+ "',");
		sql.append(ccscxccjbxx.getANNUALTAXDUE()+ ",");
		sql.append(ccscxccjbxx.getSUMTAXDEFAULT()+ ",");
		sql.append(ccscxccjbxx.getSUMOVERDUE()+ ",");
		sql.append(ccscxccjbxx.getSUMTAX()+ ",'");
		sql.append(ccscxccjbxx.getRETURNCODE()+ "','");
		sql.append(ccscxccjbxx.getLOGINNAME()+ "','");
		sql.append(ccscxccjbxx.getREVENUECODE()+ "',");
		sql.append(TODATE + "('" + ccscxccjbxx.getSJCJRQ() + "','" + YMD_HMS + "'),'");
		sql.append(ccscxccjbxx.getSJCJFS()+ "','");
		sql.append(ccscxccjbxx.getVIN()+ "','");
		sql.append(ccscxccjbxx.getHPHM()+ "','");
		sql.append(ccscxccjbxx.getHPZL()+ "','");
		sql.append(ccscxccjbxx.getCLLX()+ "','");
		
		sql.append(ccscxccjbxx.getMOTORUSAGETYPECODE()+ "','");
		sql.append(ccscxccjbxx.getMODEL()+ "','");
		sql.append(ccscxccjbxx.getVEHICLETYPE()+ "',");
		sql.append(ccscxccjbxx.getRATEDPASSENGERCAPACITY()+ ",");
		sql.append(ccscxccjbxx.getTONNAGE()+ ",");
		sql.append(ccscxccjbxx.getWHOLEWEIGHT()+ ",");
		sql.append(ccscxccjbxx.getDISPLACEMENT()+ ",");
		sql.append(ccscxccjbxx.getPOWER()+ ",'");
		sql.append(ccscxccjbxx.getFUELTYPE()+ "','");
		
		sql.append(ccscxccjbxx.getMOTORTYPECODE()+ "','");	// 车辆种类
		sql.append(ccscxccjbxx.getMADEFACTORY()+ "','");	// 制造厂名称
		sql.append(ccscxccjbxx.getNOLICENSEFLAG()+ "',");	// 未上牌车辆标志
		
		sql.append(this.getDate_String(ccscxccjbxx.getFIRSTREGISTERDATE(),YMD) + ",'");
		sql.append(ccscxccjbxx.getSPECIALCARTYPE()+ "','");
		sql.append(ccscxccjbxx.getTSBZ()+ "','");
		sql.append(ccscxccjbxx.getCARSERIALNO()+ "','");
		sql.append(ccscxccjbxx.getENGINENO()+ "','");
		sql.append(ccscxccjbxx.getCARMATCHID()+ "',");
		sql.append(this.getDate_String(ccscxccjbxx.getINSURESTARTDATE(),YMD) + ",");
		sql.append(this.getDate_String(ccscxccjbxx.getINSUREENDDATE(),YMD));
		sql.append(",'" + ccscxccjbxx.getISINSERT() + "'");
		sql.append(")");
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 CCSCXRCJBXX sql
	 * */
	public String getCCSCXRCJBXX_sql(SYJK_CCS_CCSCXRCJBXX ccscxcjbxx){
		String sql_ = "";
		StringBuffer sql = new StringBuffer();
		sql.append(SqlText.C_01_CCSCXRCJBXX_001);
				
		sql.append(ccscxcjbxx.getTAXQUERYNO() + "','");
		sql.append(ccscxcjbxx.getLICENSEPLATENO() + "','");
		sql.append(ccscxcjbxx.getLICENSEPLATETYPE() + "','");
		sql.append(ccscxcjbxx.getMOTORTYPECODE() + "','");
		sql.append(ccscxcjbxx.getENGINENO() + "','");
		sql.append(ccscxcjbxx.getVIN() + "','");
		sql.append(ccscxcjbxx.getMADEFACTORY() + "','");
		sql.append(ccscxcjbxx.getMOTORUSAGETYPECODE() + "','");
		sql.append(ccscxcjbxx.getNOLICENSEFLAG() + "','");
		sql.append(ccscxcjbxx.getMODEL() + "',");
		sql.append(this.getDate_String(ccscxcjbxx.getFIRSTREGISTERDATE(),YMD) + ",'");
		sql.append(ccscxcjbxx.getVEHICLETYPE() + "',");
		sql.append(ccscxcjbxx.getRATEDPASSENGERCAPACITY() + ",");
		sql.append(ccscxcjbxx.getTONNAGE() + ",");
		sql.append(ccscxcjbxx.getWHOLEWEIGHT() + ",");
		sql.append(ccscxcjbxx.getDISPLACEMENT() + ",");
		sql.append(ccscxcjbxx.getPOWER() + ",'");
		sql.append(ccscxcjbxx.getFUELTYPE() + "','");
		sql.append(ccscxcjbxx.getTAXAMOUNT_FLAG() + "',");
		sql.append(ccscxcjbxx.getANNUALTAXDUE() + ",");
		sql.append(ccscxcjbxx.getSUMTAXDEFAULT() + ",");
		sql.append(ccscxcjbxx.getSUMOVERDUE() + ",");
		sql.append(ccscxcjbxx.getSUMTAX() + ",'");
		sql.append(ccscxcjbxx.getLOGINNAME() + "','");
		sql.append(ccscxcjbxx.getREVENUECODE() + "',");
		sql.append(this.getDate_String(ccscxcjbxx.getSJCJRQ(),YMD) + ",'");
		sql.append(ccscxcjbxx.getSJCJFS() + "','");
		sql.append(ccscxcjbxx.getSPECIALCARTYPE() + "',");
		sql.append(this.getDate_String(ccscxcjbxx.getQUERYSEQUENCETTIME(),YMD) + ",");
		sql.append(this.getDate_String(ccscxcjbxx.getINSURESTARTDATE(),YMD) + ",");
		sql.append(this.getDate_String(ccscxcjbxx.getINSUREENDDATE(),YMD) + ",'");
		sql.append(ccscxcjbxx.getCARMATCHID() + "'");
		sql.append(")");
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-17 15:37:00
	 * @描述：数据库 CCSXX sql
	 * */
	public String getCCSXX_sql(SYJK_CCS_CCSXX ccsxx){
		String sql_ = "";
		StringBuffer sql = new StringBuffer();
		sql.append(SqlText.C_01_CCSXX_001);
		
		sql.append(ccsxx.getTAXQUERYNO()+ "','");
		sql.append(ccsxx.getTAXTERMTYPECODE()+ "','");
		sql.append(ccsxx.getTAXCONDITIONCODE()+ "','");
		sql.append(ccsxx.getTAXREGISTRYNUMBER()+ "','");
		sql.append(ccsxx.getTAXPAYERNAME()+ "','");
		sql.append(ccsxx.getTAXPAYERIDENTIFICATIONCODE()+ "','");
		sql.append(ccsxx.getTAXLOCATIONCODE()+ "',");
		sql.append(this.getDate_String(ccsxx.getTAXSTARTDATE(),YMD) + ",");
		sql.append(this.getDate_String(ccsxx.getTAXENDDATE(),YMD) + ",'");
		sql.append(ccsxx.getTAXUNITTYPECODE()+ "',");
		sql.append(ccsxx.getUNITRATE()+ ",");
		sql.append(ccsxx.getANNUALTAXAMOUNT()+ ",'");
		sql.append(ccsxx.getPAYDATE()+ "','");
		sql.append(ccsxx.getPAYCOMPANYCODE()+ "','");
		sql.append(ccsxx.getDEDUCTIONDUECODE()+ "','");
		sql.append(ccsxx.getDEDUCTIONDUETYPE()+ "',");
		sql.append(ccsxx.getDEDUCTIONDUEPROPORTION()+ ",");
		sql.append(ccsxx.getDEDUCTION()+ ",'");
		sql.append(ccsxx.getDEDUCTIONDOCUMENTNUMBER()+ "','");
		sql.append(ccsxx.getTAXDEPARTMENTCODE()+ "','");
		sql.append(ccsxx.getTAXDEPARTMENT()+ "','");
		sql.append(ccsxx.getTAXDOCUMENTNUMBER()+ "','");
		sql.append(ccsxx.getDEPARTMENT()+ "','");
		sql.append(ccsxx.getDEPARTMENTCODE()+ "',");
		sql.append(ccsxx.getTAXDUE()+ ",");
		sql.append(this.getDate_String(ccsxx.getEXCEEDDATE(),YMD) + ",");
		sql.append(ccsxx.getEXCEEDDAYSCOUNT()+ ",");
		sql.append(ccsxx.getOVERDUE()+ ",");
		sql.append(ccsxx.getTOTALAMOUNT()+ ",'");
		sql.append(ccsxx.getLOGINNAME()+ "','");
		sql.append(ccsxx.getREVENUECODE()+ "',");
		sql.append(this.getDate_String(ccsxx.getSJCJRQ(),YMD) + ",'");
		sql.append(ccsxx.getSJCJFS()+ "','");
		sql.append(ccsxx.getPARATYPE() + "',");
		sql.append(this.getDate_String(ccsxx.getINSURESTARTDATE(),YMD) + ",");
		sql.append(this.getDate_String(ccsxx.getINSUREENDDATE(),YMD) + ",'");
		sql.append(ccsxx.getCARMATCHID()+ "','");
		sql.append(ccsxx.getTAXESFLAG()+ "'");
		sql.append(")");
		sql_ = SqlDao.off_NUll(sql.toString());
		return sql_ ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-8 16:36:15
	 * @描述：插入数据库 时间 统一转换
	 * */
	public String getDate_String(Date date,String ymds){
		String date_string = "" ;
		date_string = TODATE + "('" +DateUtil.getStringDate(date,ymds) + "','" + ymds + "')";
		return date_string ;
	}
	/**
	 * 描述：自测自用
	 * */
	public static void main(String[] args){
		TaxQueryDao_SQL sql = new TaxQueryDao_SQL();
		System.out.println(sql.getDate_String(new Date(),YMD));
	}
}
