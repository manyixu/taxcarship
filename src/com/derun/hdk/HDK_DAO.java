package com.derun.hdk;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import com.derun.all.common.SqlDao;
import com.derun.beans.TaxCarCount;
import com.derun.beans.Vehicle_Type;
import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.SqlText;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CARINFO;
import com.derun.model.po.SYJK_CCS_CCSBGCXCCJB;
import com.derun.model.po.SYJK_CCS_CCSCXCCJBXX;

/**
 * @author MILI
 * @描述：核定库数据库操作类
 * @Date:2015-4-1 11:07:44
 * */

public class HDK_DAO {
	private HDK_TOOL hdk_tool = new HDK_TOOL();
	/**
	 * @author MILI
	 * @描述:核定库新增
	 * @Date：2015-4-1 09:05:45
	 * */
	public boolean Insert_carinfo(Object object,String acerCode,String flag_){
		boolean flag = false ;
		String sql_ = "" ;
		SYJK_CCS_CCSCXCCJBXX ccscxccjbxx = new SYJK_CCS_CCSCXCCJBXX();
		SYJK_CCS_CCSBGCXCCJB ccsbgcxccjb = new SYJK_CCS_CCSBGCXCCJB();
		Vehicle_Type vv = new Vehicle_Type();
		if("CX".equals(flag_)){
			ccscxccjbxx = (SYJK_CCS_CCSCXCCJBXX)object ;
			vv = hdk_tool.setCXCC(ccscxccjbxx) ;
		}else if("BG".equals(flag_)){
			ccsbgcxccjb = (SYJK_CCS_CCSBGCXCCJB)object ;
			vv = hdk_tool.setBGCXCC(ccsbgcxccjb) ;
			if ("X".equals(ccsbgcxccjb.getISINSERT())) {	// 有效未核定  
				return Update_carinfo(vv);
			}else if("N".equals(ccsbgcxccjb.getISINSERT())){	// 有效已核定
				return true ;
			}
		}
		PreparedStatement pre = null;
		Connection conn = null;
		if(vv != null){
			try {
				String ValidateFlag = "Y";				//	有效状态		NO	
				String updateflag = "N";				//	核定状态		NO
//				String VEHICLETYPE = ccscxccjb.getVEHICLETYPE();		//	车辆类型代码
				String MOTORUSAGETYPECODE = vv.getMotorUsageTypeCode();	//	车辆使用性质代码
				String MOTORTYPECODE = vv.getMotorTypeCode();				//	车辆种类代码		NO
				String acompany = acerCode.substring(1, 11);	//	保险公司代码
				StringBuffer sql = new StringBuffer(SqlText.C_02_CARINFO_001);
				sql.append("'" + TaxCarCount.getHDK_CODE(ValidateFlag,updateflag,MOTORUSAGETYPECODE,MOTORTYPECODE,acompany) + "','");
				sql.append(vv.getVIN() + "','");				// VIN			Y
				sql.append(vv.getEngineNo() + "','");			// 发动机号		Y
				sql.append(vv.getLicensePlateNo() + "','");		// 号牌号码		Y
				sql.append(vv.getLicensePlateType() + "','");	// 好牌种类
				sql.append(MOTORTYPECODE + "','");				// 车辆种类
				sql.append(vv.getMadeFactory() + "','");		// 制造厂名称
				sql.append(vv.getModel() + "',");	// 车辆型号
				sql.append("to_date('" + vv.getFirstRegisterDate() + "','yyyy-MM-dd'),'");	// 初等日期
				sql.append(vv.getVehicleType() + "',");			// 车辆类型代码
				sql.append(vv.getRatedPassengerCapacity() + ",");	// 核定载客数
				sql.append(vv.getTonnage() + ",");				// 核定载客质量
				sql.append(vv.getWholeWeight() + ",");			// 整备质量
				sql.append(vv.getDisplacement() + ",");			// 车辆排量
				sql.append("null,");	//	车主姓名
				sql.append("null,'");	//	证件类型代码
				sql.append(ValidateFlag + "','");	//	有效状态
				sql.append(updateflag + "',");		//	核定状态
				sql.append("sysdate");				//	系统时间
				sql.append(")");
				conn = DBConnPool.getConnection();
				conn.setAutoCommit(false);
				sql_ = SqlDao.off_NUll(sql.toString());
				pre = conn.prepareStatement(sql_);
				int n = pre.executeUpdate();
				if(n != 0){
					conn.commit();
					flag = true ;
				}else{
					conn.rollback();
				}
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					if (pre != null)
						pre.close();
					ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return flag ;
	}
	
	/**
	 * @author MILI
	 * @描述:核定库更新
	 * @Date：2015-6-15 16:10:02
	 * */
	public boolean Update_carinfo(Vehicle_Type vv){
		boolean flag_ = false ;
		PreparedStatement pre = null;
		Connection conn = null;
		String vin = vv.getVIN() == null ? "" : vv.getVIN().trim();						// 	车架号
		String engineno = vv.getEngineNo() == null ? "" : vv.getEngineNo().trim();		// 	发动机号
		String hphm = vv.getLicensePlateNo() == null ? "" : vv.getLicensePlateNo().trim();		// 号牌号码
		String[] flag = CfgLoader.getConfigValue("SysSwitch", "CARINFO_FLAG").split(",");		// 匹配条件开关
		StringBuffer sql = new StringBuffer(SqlText.U_04_CARINFO_001);
		StringBuilder where = new StringBuilder();
		if("1".equals(flag[0])){
			StringUtil.paramSQLC(where).append(" VIN = '" + vin + "'");
		}
		if("1".equals(flag[1])){
			StringUtil.paramSQLC(where).append(" ENGINENO = '" + engineno + "'");
		}
		if("1".equals(flag[2])){
			if(!"".equals(hphm)){
				StringUtil.paramSQLC(where).append(" LICENSEPLATENO = '" + hphm +"'");
			}
		}
		sql.append(where.toString()).toString();
		try {
			conn = DBConnPool.getConnection();
			conn.setAutoCommit(false);
			pre = conn.prepareStatement(sql.toString());
			pre.setString(1, vv.getVIN());
			pre.setString(2, vv.getEngineNo());
			pre.setString(3, vv.getLicensePlateNo());
			pre.setString(4, vv.getLicensePlateType());
			pre.setString(5, vv.getMotorTypeCode());
			pre.setString(6, vv.getMadeFactory());
			pre.setString(7, vv.getModel());
//			pre.setDate(8, new Date(DateUtil.getStringDates(vv.getFirstRegisterDate()).getTime()));
			pre.setString(8, vv.getFirstRegisterDate());
			pre.setString(9, vv.getVehicleType());
			pre.setDouble(10, vv.getRatedPassengerCapacity());
			pre.setDouble(11, vv.getTonnage());
			pre.setDouble(12, vv.getWholeWeight());
			pre.setDouble(13, vv.getDisplacement());
			int n = pre.executeUpdate();
			if(n != 0){
				flag_ = true ;
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (NoFreeConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return flag_ ;
	}
	
	/**
	 * @author MILI
	 * @time 2014-6-12 17:14:12
	 * @描述：匹配本地车辆信息
	 * @条件： 车架号、发动机号、车牌号码、号牌种类
	 * */
	public SYJK_CCS_CARINFO getSYJK_CCS_CARINFO(Vehicle_Type vt){
		SYJK_CCS_CARINFO carinfo = null ; 
		String vin = vt.getVIN() == null ? "" : vt.getVIN().trim();						// 	车架号
		String engineno = vt.getEngineNo() == null ? "" : vt.getEngineNo().trim();		// 	发动机号
		String hphm = vt.getLicensePlateNo() == null ? "" : vt.getLicensePlateNo().trim();		// 号牌号码
		String[] flag = CfgLoader.getConfigValue("SysSwitch", "CARINFO_FLAG").split(",");		// 匹配条件开关
		
		if(vin.length() >= 17){
			StringBuffer sbf = new StringBuffer();
			StringBuilder where = new StringBuilder();
			sbf.append(SqlText.R_01_CARINFO_001);
			if("1".equals(flag[0])){
				StringUtil.paramSQLC(where).append(" C.VIN = '" + vin + "'");
			}
			if("1".equals(flag[1])){
				StringUtil.paramSQLC(where).append(" C.ENGINENO = '" + engineno + "'");
			}
			if("1".equals(flag[2])){
				if(!"".equals(hphm)){
					StringUtil.paramSQLC(where).append(" C.LICENSEPLATENO = '" + hphm +"'");
				}
			}
			String sql = sbf.append(where.toString()).toString();
			sql = SqlDao.off_NUll(sql.toString());
			Connection con = null ;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DBConnPool.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()){
					carinfo = new SYJK_CCS_CARINFO();
					carinfo.setCARSERIALNO(rs.getString("CARSERIALNO"));
					carinfo.setVIN(rs.getString("VIN"));
					carinfo.setENGINENO(rs.getString("ENGINENO"));
					carinfo.setLICENSEPLATENO(rs.getString("LICENSEPLATENO"));
					carinfo.setLICENSEPLATETYPE(rs.getString("LICENSEPLATETYPE"));
					carinfo.setMOTORTYPECODE(rs.getString("MOTORTYPECODE"));
					carinfo.setMADEFACTORY(rs.getString("MADEFACTORY"));
					carinfo.setMODEL(rs.getString("MODEL"));
					carinfo.setFIRSTREGISTERDATE(rs.getDate("FIRSTREGISTERDATE"));
					carinfo.setVEHICLETYPE(rs.getString("VEHICLETYPE"));
					carinfo.setRATEDPASSENGERCAPACITY(rs.getInt("RATEDPASSENGERCAPACITY"));
					carinfo.setTONNAGE(rs.getDouble("TONNAGE"));
					carinfo.setWHOLEWEIGHT(rs.getDouble("WHOLEWEIGHT"));
					carinfo.setDISPLACEMENT(rs.getDouble("DISPLACEMENT"));
					carinfo.setVEHICLEOWNERNAME(rs.getString("VEHICLEOWNERNAME"));
					carinfo.setCREDENTIALNO(rs.getString("CREDENTIALNO"));
					carinfo.setVALIDATWFLAG(rs.getString("VALIDATWFLAG"));
					carinfo.setAPPROVEDFLAG(rs.getString("APPROVEDFLAG"));
					carinfo.setSJCJRQ(rs.getDate("SJCJRQ"));
				}else{
					carinfo = null ;
				}
			} catch (SQLException e) {
				carinfo = null ;
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(rs != null)
						rs.close();
					if(pstmt != null){
						pstmt.close();
					}
					ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NoFreeConnectionException e) {
					e.printStackTrace();
				}
			} 
			
		}else{
			carinfo = null ;
		}
		return carinfo ;
	}
	/**
	 * @author MILI
	 * @描述：核定库封装Vehicle_Type
	 * @Date:2015-3-31 15:44:26
	 * */
	public Map<String,Object> getVehicle_Type(Vehicle_Type vt){
		Map<String,Object> map = new HashMap<String,Object>();
		SYJK_CCS_CARINFO carinfo = this.getSYJK_CCS_CARINFO(vt);
		Vehicle_Type vv = null ;
		//	已审核并且是有效
		if(carinfo != null && "Y".equals(carinfo.getAPPROVEDFLAG()) && "Y".equals(carinfo.getVALIDATWFLAG())){	
			vv = new Vehicle_Type();
			vv.setVIN(carinfo.getVIN());
			vv.setEngineNo(carinfo.getENGINENO());
			vv.setLicensePlateNo(carinfo.getLICENSEPLATENO());
			vv.setLicensePlateType(carinfo.getLICENSEPLATETYPE());
			vv.setMotorTypeCode(carinfo.getMOTORTYPECODE());
			vv.setMadeFactory(carinfo.getMADEFACTORY());
			vv.setModel(carinfo.getMODEL());
			vv.setFirstRegisterDate(DateUtil.getStringDate(carinfo.getFIRSTREGISTERDATE(),null));
			vv.setVehicleType(carinfo.getVEHICLETYPE());
			vv.setRatedPassengerCapacity(carinfo.getRATEDPASSENGERCAPACITY());
			vv.setTonnage(carinfo.getTONNAGE());
			vv.setWholeWeight(carinfo.getWHOLEWEIGHT());
			vv.setDisplacement(carinfo.getDISPLACEMENT());
			map.put("VT", vv);
			map.put("SF", "N");		// N=不对核定库做操作
		// 有效未核定
		}else if(carinfo != null && !("Y".equals(carinfo.getAPPROVEDFLAG()) && "Y".equals(carinfo.getVALIDATWFLAG()))){
			map.put("VT", vt);
			map.put("SF", "X");		// X=更新核定库
		}else{ // 无效
			vv = vt ;
			map.put("VT", vv);
			map.put("SF", "Y");   	// Y=插入核定库
		}
		return map ;
	}
}
