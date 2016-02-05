package com.derun.taxconfirm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.SqlText;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.LogUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;
/**
 * @author MILI
 * @time 2014-3-19 16:00:09
 * @描述：插入对象 SYJK_CCS_CCSCXCCJBXX 封装
 * */
public class C_SYJK_CCS_CCSCXCCJBXX {
	
	/**
	 * @author MILI
	 * @time 2014-4-22 16:10:19
	 * @描述：SYJK_CCS_CCSCXCCJBXX 封装
	 * @入参：查询码
	 * @出参：SYJK_CCS_CCSCXCCJBXX 查询出参基本信息
	 * */
	public SYJK_CCS_CCSCXCCJBXX getQrccjbxx(String taxQueryCode){
		LogUtil log = new LogUtil("投保确认 查询出参基本信息");
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.startLog();
		}
		SYJK_CCS_CCSCXCCJBXX cxccjb = new SYJK_CCS_CCSCXCCJBXX();
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer(SqlText.R_02_CCSCXCCJBXX_001);
		try {
			con = DBConnPool.getConnection();
			pre = con.prepareStatement(sql.toString());
			pre.setString(1, taxQueryCode);
			rs = pre.executeQuery();
			if(rs.next()){
				cxccjb.setTAXQUERYNO(taxQueryCode);
				cxccjb.setCALCTAXFLAG(rs.getString("CALCTAXFLAG"));
				cxccjb.setTAXAMOUNT_FLAG(rs.getString("TAXAMOUNT_FLAG"));
				cxccjb.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));
				cxccjb.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));
				cxccjb.setSUMOVERDUE(rs.getDouble("SUMOVERDUE"));
				cxccjb.setSUMTAX(rs.getDouble("SUMTAX"));
				cxccjb.setRETURNCODE(rs.getString("RETURNCODE"));
				cxccjb.setVIN(rs.getString("VIN"));
				cxccjb.setHPHM(rs.getString("HPHM"));
				cxccjb.setHPZL(rs.getString("HPZL"));
				cxccjb.setCLLX(rs.getString("CLLX"));
				
				cxccjb.setMOTORUSAGETYPECODE(rs.getString("MOTORUSAGETYPECODE")); 	// 使用性质
				cxccjb.setMODEL(rs.getString("MODEL")); 					// 车辆型号
				cxccjb.setVEHICLETYPE(rs.getString("VEHICLETYPE")); 		// 交管车辆类型
				cxccjb.setRATEDPASSENGERCAPACITY(rs.getDouble("RATEDPASSENGERCAPACITY"));// 核定载客数
				cxccjb.setTONNAGE(rs.getDouble("TONNAGE"));				// 核定载质量
				cxccjb.setWHOLEWEIGHT(rs.getDouble("WHOLEWEIGHT"));		// 整备质量
				cxccjb.setDISPLACEMENT(rs.getDouble("DISPLACEMENT"));	// 排量
				cxccjb.setPOWER(rs.getDouble("POWER"));					// 功率
				cxccjb.setFUELTYPE(rs.getString("FUELTYPE")); 			// 源种类
				
				cxccjb.setMOTORTYPECODE(rs.getString("MOTORTYPECODE")) ;// 车辆种类
				cxccjb.setMADEFACTORY(rs.getString("MADEFACTORY"));		// 制造厂名称
				cxccjb.setNOLICENSEFLAG(rs.getString("NOLICENSEFLAG"));	// 未上牌车辆标志
				
				cxccjb.setFIRSTREGISTERDATE(rs.getDate("firstregisterdate"));
				cxccjb.setSPECIALCARTYPE(rs.getString("specialcartype"));
				cxccjb.setTSBZ(rs.getString("tsbz"));
				cxccjb.setENGINENO(rs.getString("ENGINENO"));
				cxccjb.setCARSERIALNO(rs.getString("CARSERIALNO"));
				cxccjb.setCARMATCHID(rs.getString("CARMATCHID"));
				cxccjb.setINSURESTARTDATE(rs.getDate("INSURESTARTDATE"));
				cxccjb.setINSUREENDDATE(rs.getDate("INSUREENDDATE"));
				cxccjb.setISINSERT(rs.getString("ISINSERT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(pre != null){
					pre.close();
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
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.endLog();
		}
		return cxccjb ;
	}
}
