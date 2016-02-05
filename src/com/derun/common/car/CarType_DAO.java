package com.derun.common.car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.derun.all.common.SqlDao;
import com.derun.beans.Vehicle_Type;
import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.SqlText;
import com.derun.common.init.CfgLoader;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSBGCXRCJB;
import com.derun.model.po.SYJK_CCS_DSCCSJMDJXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_WSDJXX;
/**
 * @author MILI
 * @time 2014-7-29 15:35:05
 * @描述：车辆类型匹配辅助类
 * */
public class CarType_DAO {
	/**
	 * @author MILI
	 * @time 2014-7-29 15:35:39
	 * @描述：查询本地车完税登记的信息
	 * */
	public SYJK_CCS_WSDJXX getSYJK_CCS_WSDJXX(Vehicle_Type VT,SYJK_CCS_RKMX rkmx){
		SYJK_CCS_WSDJXX wsdj = new SYJK_CCS_WSDJXX();
//		String sql1 = SqlText.R_01_WSDJXX_001;	// 号牌号码 + 车辆类型
		VT = this.setVT(VT,rkmx);
		String vin = VT.getVIN() == null ? "" : VT.getVIN().trim();				// 	车架号
		String cllx = VT.getModel() == null ? "" : VT.getModel().trim();		// 	车辆类型
		String hphm = VT.getLicensePlateNo() == null ? "" : VT.getLicensePlateNo().trim();		// 号牌号码
		String[] flag = CfgLoader.getConfigValue("SysSwitch", "WSDJXX_FLAG").split(",");		// 匹配条件开关
		
		StringBuffer sbf = new StringBuffer(SqlText.R_01_WSDJXX_002);
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
		
		// 增加完税库中数据的年度判断，为当前年的数据才提取
		// 实现完税库中增加退税标志字段以及代码不在读取完税库中退税标志为已退税的车辆记录
//		where.append(" AND TO_CHAR(W.SKSSKSRQ,'yyyy') = TO_CHAR(sysdate,'yyyy')");
		where.append(" AND (W.TSBZ = 'N' OR W.TSBZ is null OR W.TSBZ = '') ORDER BY W.SKSSJSRQ DESC") ;   // 新加退税标志字段    N 默认 Y 退税
		
		String sql = sbf.append(where.toString()).toString();
		sql = SqlDao.off_NUll(sql.toString());
		
		Connection con = null ;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
        	con = DBConnPool.getConnection();
        	pstmt = con.prepareStatement(sql);
//        	pstmt.setString(1, VT.getLicensePlateNo());
//        	pstmt.setString(2, VT.getModel());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				wsdj = new SYJK_CCS_WSDJXX();
				wsdj.setID(rs.getString("ID"));
				wsdj.setNSRSBH(rs.getString("NSRSBH"));
				wsdj.setLOGINNAME(rs.getString("LOGINNAME"));
				wsdj.setREVENUECODE(rs.getString("REVENUECODE"));
				wsdj.setSJCJRQ(rs.getDate("SJCJRQ"));
				wsdj.setSJCJFS(rs.getString("SJCJFS"));
				wsdj.setCLHPHM(rs.getString("CLHPHM"));
				wsdj.setCLHPZL(rs.getString("CLHPZL"));
				wsdj.setCLLX(rs.getString("CLLX"));
				wsdj.setSYRMC(rs.getString("SYRMC"));
				wsdj.setKJSWJGDM(rs.getString("KJSWJGDM"));
				wsdj.setZGSWJGMC(rs.getString("ZGSWJGMC"));
				wsdj.setCCSBDM(rs.getString("CCSBDM"));
				wsdj.setDZDWMC(rs.getString("DZDWMC"));
				wsdj.setDZDWDM(rs.getString("DZDWDM"));
				wsdj.setWSPZH(rs.getString("WSPZH"));
				wsdj.setSKSSKSRQ(rs.getDate("SKSSKSRQ"));
				wsdj.setSKSSJSRQ(rs.getDate("SKSSJSRQ"));
				wsdj.setJNJE(rs.getDouble("JNJE"));
				wsdj.setWSRQ(rs.getDate("WSRQ"));
				wsdj.setSKLX(rs.getString("SKLX"));
				wsdj.setZSPM(rs.getString("ZSPM"));
			} else {
				wsdj = null;
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
				if(pstmt != null){
					pstmt.close();
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
		return wsdj ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-29 15:35:39
	 * @描述：查询本地车减免登记的信息
	 * */
	public SYJK_CCS_DSCCSJMDJXX getSYJK_CCS_DSCCSJMDJXX(Vehicle_Type VT,SYJK_CCS_RKMX rkmx){
		SYJK_CCS_DSCCSJMDJXX jmdj = new SYJK_CCS_DSCCSJMDJXX();
//		String sql1 = SqlText.R_01_DSCCSJMDJXX_001 ;	// 号牌号码 + 车辆类型
//		String sql2 = SqlText.R_01_DSCCSJMDJXX_002 ;	// VIN
		VT = this.setVT(VT,rkmx);
		
		String vin = VT.getVIN() == null ? "" : VT.getVIN().trim();				// 	车架号
		String cllx = VT.getModel() == null ? "" : VT.getModel().trim();		// 	车辆类型
		String hphm = VT.getLicensePlateNo() == null ? "" : VT.getLicensePlateNo().trim();		// 号牌号码
		String[] flag = CfgLoader.getConfigValue("SysSwitch", "JMDJXX_FLAG").split(",");		// 匹配条件开关
		
		StringBuffer sbf = new StringBuffer(SqlText.R_01_DSCCSJMDJXX_002);
		StringBuilder where = new StringBuilder();
		if("1".equals(flag[0])){
			if(!"".equals(vin)){
				StringUtil.paramSQLC(where).append(" D.CCSBDM = '" + vin + "'");
			}
		}
		if("1".equals(flag[1])){
			if(!"".equals(cllx)){
				StringUtil.paramSQLC(where).append(" D.CLLX = '" + cllx + "'");
			}
		}
		if("1".equals(flag[2])){
			if(!"".equals(hphm)){
				StringUtil.paramSQLC(where).append(" D.CLHPHM = '" + hphm +"'");
			}
		}
		String sql = sbf.append(where.toString()).toString();
		sql = SqlDao.off_NUll(sql.toString());
		
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
//			pre.setString(1, VT.getLicensePlateNo());
//			pre.setString(2, VT.getModel());
			rs = pre.executeQuery();
			if (rs.next()) {
				jmdj.setID(rs.getString("ID"));
				jmdj.setNSRSBH(rs.getString("NSRSBH"));
				jmdj.setLOGINNAME(rs.getString("LOGINNAME"));
				jmdj.setREVENUECODE(rs.getString("REVENUECODE"));
				jmdj.setSJCJRQ(rs.getDate("SJCJRQ"));
				jmdj.setSJCJFS(rs.getString("SJCJFS"));
				jmdj.setCLHPHM(rs.getString("CLHPHM"));
				jmdj.setCLHPZL(rs.getString("CLHPZL"));
				jmdj.setCLLX(rs.getString("CLLX"));
				jmdj.setCBMC(rs.getString("CBMC"));
				jmdj.setCBLX(rs.getString("CBLX"));
				jmdj.setSYRMC(rs.getString("SYRMC"));
				jmdj.setJMSPZH(rs.getString("JMSPZH"));
				jmdj.setJZBL(rs.getDouble("JZBL"));
				jmdj.setJMSQRQ(rs.getDate("JMSQRQ"));
				jmdj.setJMSZRQ(rs.getDate("JMSZRQ"));
				jmdj.setNJMSE(rs.getDouble("NJMSE"));
				jmdj.setKJSWJGDM(rs.getString("KJSWJGDM"));
				jmdj.setZGSWJGMC(rs.getString("ZGSWJGMC"));
				jmdj.setCCSBDM(rs.getString("CCSBDM"));

			} else {
				jmdj = null;
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
		return jmdj;
	}
	/**
	 * @author MILI
	 * @time 2014-7-30 11:00:05
	 * @描述：处理车辆信息（车牌号码 + 车辆类型）
	 * */
	public Vehicle_Type setVT(Vehicle_Type VT,SYJK_CCS_RKMX rkmx){
		Vehicle_Type _vt = new Vehicle_Type();
		if(VT != null){
			_vt = VT ;
		}else if(rkmx != null){
			_vt.setLicensePlateNo(rkmx.getHPHM());
			_vt.setModel(rkmx.getMODEL());
		}
		return _vt ;
	}
	/**
	 * @author MILI
	 * @time 2014-11-11 11:27:16
	 * @描述：根据确认码 查询变更查询入参信息
	 * */
	public SYJK_CCS_CCSBGCXRCJB getBGCXRC(String taxconfirmno){
		SYJK_CCS_CCSBGCXRCJB bgcxrc = new SYJK_CCS_CCSBGCXRCJB();
		String sql1 = SqlText.R_03_SYJK_CCS_CCSBGCXRCJB_001 ;	
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql1);
			pre.setString(1, taxconfirmno);
			rs = pre.executeQuery();
			if (rs.next()) {
				bgcxrc.setCHANGETYPE(rs.getString("CHANGETYPE"));
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
		return bgcxrc ;
	}
}
