package com.derun.taxpayquery.dao.impl;


import com.derun.all.common.SqlDao;
import com.derun.common.db.SqlText;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSNSXXCXCC;
import com.derun.model.po.SYJK_CCS_CCSNSXXCXRC;

/**
 * @author 郑艳英
 * 
 * @date 2014-5-9
 * 
 *       车船税纳税信息数据库sql类
 * @version
 */
public class TaxPayQueryServiceSql {

	//保存纳税信息查询入参信息SQL
	public String getTaxPayQueryReqSql(SYJK_CCS_CCSNSXXCXRC ccsnsxxcxrc){
		String reqSql = "";
		StringBuffer sql = new StringBuffer();
		String FIRSTREGISTERDATE = DateUtil.getString(ccsnsxxcxrc.getFIRSTREGISTERDATE(),"yyyy-MM-dd HH:mm:ss");
		String SJCJRQ = DateUtil.getString(ccsnsxxcxrc.getSJCJRQ(),"yyyy-MM-dd HH:mm:ss");
		sql.append(SqlText.C_08_CCSNSXXCXRC_009);
		sql.append("'" + ccsnsxxcxrc.getLICENSEPLATENO() + "'");
		sql.append(",'" + ccsnsxxcxrc.getLICENSEPLATETYPE() + "'");
		sql.append(",'" + ccsnsxxcxrc.getMOTORTYPECODE() + "'");
		sql.append(",'" + ccsnsxxcxrc.getENGINENO() + "'");
		sql.append(",'" + ccsnsxxcxrc.getVIN() + "'");
		sql.append(",'" + ccsnsxxcxrc.getMADEFACTORY() + "'");
		sql.append(",'" + ccsnsxxcxrc.getMOTORUSAGETYPECODE() + "'");
		sql.append(",'" + ccsnsxxcxrc.getNOLICENSEFLAG() + "'");
		sql.append(",'" + ccsnsxxcxrc.getMODEL() + "'");
		if(FIRSTREGISTERDATE != null){
			sql.append(",TO_DATE('" + FIRSTREGISTERDATE + "','yyyy-MM-dd HH24:mi:ss')" );
		}else{
			sql.append(",'" + FIRSTREGISTERDATE + "'");
		}
		sql.append(",'" + ccsnsxxcxrc.getVEHICLETYPE() + "'");
		sql.append(",'" + ccsnsxxcxrc.getRATEDPASSENGERCAPACITY() + "'");
		sql.append(",'" + ccsnsxxcxrc.getTONNAGE() + "'");
		sql.append(",'" + ccsnsxxcxrc.getWHOLEWEIGHT() + "'");
		sql.append(",'" + ccsnsxxcxrc.getDISPLACEMENT() + "'");
		sql.append(",'" + ccsnsxxcxrc.getPOWER() + "'");
		sql.append(",'" + ccsnsxxcxrc.getFUELTYPE() + "'");
		sql.append(",'" + ccsnsxxcxrc.getSPECIALCARTYPE() + "'");
		sql.append(",'" + ccsnsxxcxrc.getLOGINNAME() + "'");
		sql.append(",'" + ccsnsxxcxrc.getREVENUECODE() + "'");
		if(SJCJRQ != null){
			sql.append(",TO_DATE('" + SJCJRQ + "','yyyy-MM-dd HH24:mi:ss')" );
		}else{
			sql.append(",'" + SJCJRQ + "'" );
		}
		sql.append(",'" + ccsnsxxcxrc.getSJCJFS()+ "'");
		sql.append(")");
		reqSql = SqlDao.off_NUll(sql.toString());
		return reqSql ;
	}
	
	//纳税信息查询 保存纳税信息查询出参信息 
	public String getTaxPayQueryResSql(SYJK_CCS_CCSNSXXCXCC ccsnsxxcxcc){
		String resSql = "";
		StringBuffer sql = new StringBuffer();
		String TAXSTARTDATE = DateUtil.getString(ccsnsxxcxcc.getTAXSTARTDATE(), "yyyy-MM-dd HH:mm:ss");
		String TAXENDDATE = DateUtil.getString(ccsnsxxcxcc.getTAXENDDATE(), "yyyy-MM-dd HH:mm:ss");
		String EXCEEDDATE = DateUtil.getString(ccsnsxxcxcc.getEXCEEDDATE(), "yyyy-MM-dd HH:mm:ss");
		String SJCJRQ = DateUtil.getString(ccsnsxxcxcc.getSJCJRQ(), "yyyy-MM-dd HH:mm:ss");
		String PAYDATE = DateUtil.getString(ccsnsxxcxcc.getPAYDATE(), "yyyy-MM-dd HH:mm:ss");
		sql.append(SqlText.C_08_CCSNSXXCXCC_010);
		sql.append("'" + ccsnsxxcxcc.getRETURNCODE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getDECLAREDSTATUS() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXTERMTYPECODE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXCONDITIONCODE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXREGISTRYNUMBER() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXPAYERNAME() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXPAYERIDENTIFICATIONCODE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXLOCATIONCODE() + "'");
		if(TAXSTARTDATE != null){
			sql.append(",TO_DATE('" + TAXSTARTDATE + "','yyyy-MM-dd HH24:mi:ss')" );
		}else{
			sql.append(",'" + TAXSTARTDATE + "'" );
		}
		if(TAXENDDATE != null){
			sql.append(",TO_DATE('" + TAXENDDATE + "','yyyy-MM-dd HH24:mi:ss')");
		}else{
			sql.append(",'" + TAXENDDATE + "'" );
		}
		sql.append(",'" + ccsnsxxcxcc.getPAYCOMPANYCODE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXUNITTYPECODE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getUNITRATE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getANNUALTAXAMOUNT() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXDEPARTMENTCODE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXDEPARTMENT() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXDOCUMENTNUMBER() + "'");
		sql.append(",'" + ccsnsxxcxcc.getDEDUCTIONDUECODE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getDEDUCTIONDUETYPE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getDEDUCTIONDUEPROPORTION() + "'");
		sql.append(",'" + ccsnsxxcxcc.getDEDUCTION() + "'");
		sql.append(",'" + ccsnsxxcxcc.getDEDUCTIONDOCUMENTNUMBER() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTAXDUE() + "'");
		if(EXCEEDDATE != null){
			sql.append(",TO_DATE('" + EXCEEDDATE + "','yyyy-MM-dd HH24:mi:ss')");
		}else{
			sql.append(",'" + EXCEEDDATE + "'" );
		}
		sql.append(",'" + ccsnsxxcxcc.getEXCEEDDAYSCOUNT() + "'");
		sql.append(",'" + ccsnsxxcxcc.getOVERDUE() + "'");
		sql.append(",'" + ccsnsxxcxcc.getTOTALAMOUNT()+ "'");
		sql.append(",'" + ccsnsxxcxcc.getLOGINNAME() + "'");
		sql.append(",'" + ccsnsxxcxcc.getREVENUECODE() + "'");
		if(SJCJRQ != null){
			sql.append(",TO_DATE('" + SJCJRQ + "','yyyy-MM-dd HH24:mi:ss')");
		}else{
			sql.append(",'" + SJCJRQ + "'" );
		}
		sql.append(",'" + ccsnsxxcxcc.getSJCJFS() + "'");
		if(PAYDATE != null){
			sql.append(",TO_DATE('" + PAYDATE + "','yyyy-MM-dd HH24:mi:ss')");
		}else{
			sql.append(",'" + PAYDATE + "'" );
		}
		sql.append(")");
		resSql = SqlDao.off_NUll(sql.toString());
		return resSql ;
	}
	
}
