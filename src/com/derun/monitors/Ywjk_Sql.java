package com.derun.monitors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-7-24 10:02:47
 * @描述：初始化 业务监控 sql
 * */
public class Ywjk_Sql {
	public static List<SYJK_CCS_RKMX> getYwjkSql(){
		List<SYJK_CCS_RKMX> list = new ArrayList<SYJK_CCS_RKMX>() ;
		SYJK_CCS_RKMX rkmx = null ;
		String sql_rkmx = "TAXQUERYNO,TAXCONFIRMNO,TAXPRINTNO,VIN,HPHM,HPZL,CLLX,MOTORUSAGETYPECODE,MODEL,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,"
						+ "WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,"
						+ "TAXPAYERIDENTIFICATIONCODE ,PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,"
						+ "UNITRATE,ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,DEDUCTIONDEPARTMENTCODE,"
						+ "DEDUCTIONDEPARTMENT,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,"
						+ "TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,REVENUECODE,SJCJRQ,PAYDATE,"
						+ "TAXAMOUNT_FLAG ,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX ,LOGGEDOUT,PLATFORMSTATE,CHANGETYPE,"
						+ "COUNTTAXTYPE,REFUSETYPE,STATUSDATE,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARSERIALNO,ENGINENO,INSURESTARTDATE,"
						+ "INSUREENDDATE,CARMATCHID" ;
		String sql = "SELECT " + sql_rkmx + " FROM SYJK_CCS_RKMX RKMX WHERE TO_CHAR(RKMX.SJCJRQ,'YYYY-MM-dd') = TO_CHAR(SYSDATE,'YYYY-MM-dd') AND RKMX.REFUSETYPE = 1 AND RKMX.LOGGEDOUT = '0' AND RKMX.TAXCONDITIONCODE <> 'R' AND ROWNUM <= 8" ;
		Connection con = null ;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
			con = DBConnPool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				rkmx = new SYJK_CCS_RKMX();
				rkmx.setPAYCOMPANYCODE(rs.getString("PAYCOMPANYCODE"));
				rkmx.setVIN(rs.getString("VIN"));
				rkmx.setHPHM(rs.getString("HPHM"));
				rkmx.setCLLX(rs.getString("CLLX"));
				rkmx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));
				rkmx.setTAXSTARTDATE(rs.getString("TAXSTARTDATE"));
				rkmx.setTAXENDDATE(rs.getString("TAXENDDATE"));
				rkmx.setANNUALTAXAMOUNT(rs.getDouble("ANNUALTAXAMOUNT"));
				rkmx.setSTATUSDATE(rs.getString("STATUSDATE"));
				rkmx.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));
				rkmx.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE"));
				rkmx.setPAYDATE(rs.getString("PAYDATE"));
				rkmx.setUNITRATE(rs.getDouble("UNITRATE"));
				rkmx.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));
				rkmx.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));
				rkmx.setSUMOVERDUE(rs.getDouble("SUMOVERDUE"));
				rkmx.setSUMTAX(rs.getDouble("SUMTAX"));
				rkmx.setTAXDEPARTMENTCODE(rs.getString("TAXDEPARTMENTCODE"));
				rkmx.setTAXDEPARTMENT(rs.getString("TAXDEPARTMENT"));
				rkmx.setTAXDOCUMENTNUMBER(rs.getString("TAXDOCUMENTNUMBER"));
				rkmx.setTAXPAYERIDENTIFICATIONCODE(rs.getString("TAXPAYERIDENTIFICATIONCODE"));
				rkmx.setTAXPAYERNAME(rs.getString("TAXPAYERNAME"));
				rkmx.setTAXREGISTRYNUMBER(rs.getString("TAXREGISTRYNUMBER"));
				rkmx.setTAXUNITTYPECODE(rs.getString("TAXUNITTYPECODE"));
				rkmx.setTAXPRINTNO(rs.getString("TAXPRINTNO"));
				rkmx.setTAXQUERYNO(rs.getString("TAXQUERYNO"));
				rkmx.setCOUNTTAXTYPE(rs.getString("COUNTTAXTYPE"));
				rkmx.setDEDUCTION(rs.getDouble("DEDUCTION"));
				rkmx.setDEDUCTIONDEPARTMENT(rs.getString("DEDUCTIONDEPARTMENT"));
				rkmx.setDEDUCTIONDEPARTMENTCODE(rs.getString("DEDUCTIONDEPARTMENTCODE"));
				rkmx.setDEDUCTIONDOCUMENTNUMBER(rs.getString("DEDUCTIONDOCUMENTNUMBER"));
				rkmx.setDEDUCTIONDUECODE(rs.getString("DEDUCTIONDUECODE"));
				rkmx.setDEDUCTIONDUEPROPORTION(rs.getDouble("DEDUCTIONDUEPROPORTION"));
				rkmx.setDEDUCTIONDUETYPE(rs.getString("DEDUCTIONDUETYPE"));
				rkmx.setInsureEndDate(rs.getDate("INSUREENDDATE"));
				rkmx.setInsureStartDate(rs.getDate("INSURESTARTDATE"));
				rkmx.setLOGGEDOUT(rs.getString("LOGGEDOUT"));
				rkmx.setLOGINNAME(rs.getString("LOGINNAME"));
				rkmx.setPLATFORMSTATE(rs.getString("PLATFORMSTATE"));
				rkmx.setRefusetype(rs.getString("REFUSETYPE"));
				rkmx.setREVENUECODE(rs.getString("REVENUECODE"));
				rkmx.setSJCJRQ(rs.getString("SJCJRQ"));
				rkmx.setSPECIALCARTYPE(rs.getString("SPECIALCARTYPE"));
				rkmx.setCHANGETYPE(rs.getString("CHANGETYPE"));
				list.add(rkmx);
			}
			
		}catch(Exception e){
			System.out.println("业务监控 查询封装失败。。。。。。。。。");
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
		return list ;
	}
}
