package com.derun.controller.whiteList.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.ExeSQL;
import com.derun.common.util.DateUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX_TEMP;

public class WhiteApplyServletDao {
	ExeSQL es = new ExeSQL();

	// 查询
	public List<SYJK_CCS_QGCLWTMDXX_TEMP> getWTMD_W(int page, int rows,String name) {
		List<SYJK_CCS_QGCLWTMDXX_TEMP> list_wtmd = new ArrayList<SYJK_CCS_QGCLWTMDXX_TEMP>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = " select * from(select rownum r, t.* from (select * from SYJK_CCS_QGCLWTMDXX_TEMP a WHERE APPLYSTATUS in('0','3') and LOGINNAME='"+name+"' order by a.id desc ) t where rownum <="
				+ page * rows + ")where r>" + (page - 1) * rows;
		try {
			conn = DBConnPool.getConnection();
			System.out.println(sql);
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while (rs.next()) {
				SYJK_CCS_QGCLWTMDXX_TEMP wtmd = new SYJK_CCS_QGCLWTMDXX_TEMP();
				wtmd.setID(rs.getString("ID")); // ID
				wtmd.setCLSBDM(rs.getString("CLSBDM")); // 车辆识别代码
				wtmd.setSKSSSQ(rs.getDate("SKSSSQ")); // 税款所属始期
				wtmd.setSKSSZQ(rs.getDate("SKSSZQ")); // 税款所属止期
				wtmd.setDQYNSE(rs.getDouble("DQYNSE")); // 当期应纳税额
				wtmd.setREVENUECODE(rs.getString("REVENUECODE")); // 税务机关代码
				wtmd.setWSPZ(rs.getString("WSPZ")); // 完税凭证
				wtmd.setYCYYDM(rs.getString("YCYYDM")); // 完税凭证
				list_wtmd.add(wtmd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("查询白名单出错。。。。。。。。。。WhiteListCheckServletDao ---  getWTMD_W()");
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pre != null) {
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if (conn != null) {
//					conn.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list_wtmd;
	}

	public int getWTMD_Count(String name) {
		int count = 0;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "SELECT COUNT(*) AS TOTAL FROM SYJK_CCS_QGCLWTMDXX_TEMP WHERE APPLYSTATUS in('0','3') and LOGINNAME='"+name+"' ";
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			if (rs.next()) {
				count = rs.getInt("TOTAL");
			} else {
				count = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("查询白名单出错。。。。。。。。。。WhiteListCheckServletDao ---  getWTMD_Count()");
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pre != null) {
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if (conn != null) {
//					conn.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 申请
	public boolean saveWtmd(SYJK_CCS_QGCLWTMDXX_TEMP wtmd,String name) {
		String cl = "";
		if(wtmd.getCLCSDJRQ() != null && !"".equals(wtmd.getCLCSDJRQ())){
			cl = DateUtil.getStringDate(wtmd.getCLCSDJRQ(),null);
		}
		String sq = "" ;
		if(wtmd.getSKSSSQ() != null && !"".equals(wtmd.getSKSSSQ())){
			sq = DateUtil.getStringDate(wtmd.getSKSSSQ(),null);
		}
		String zq = "" ;
		if(wtmd.getSKSSZQ() != null && !"".equals(wtmd.getSKSSZQ())){
			zq = DateUtil.getStringDate(wtmd.getSKSSZQ(),null);
		}else{
			zq = sq.substring(0, 4) + "-12-31" ;
		}
		Double HDZKS = wtmd.getHDZKS();
		if (null == HDZKS) {
			HDZKS = 0.0;
		}
		Double HDZZL = wtmd.getHDZZL();
		if (null == HDZZL) {
			HDZZL = 0.0;
		}
		Double ZBZL = wtmd.getZBZL();
		if (null == ZBZL) {
			ZBZL = 0.0;
		}
		Double PL = wtmd.getPL();
		if (null == PL) {
			PL = 0.0;
		}
		Double GL = wtmd.getGL();
		if (null == GL) {
			GL = 0.0;
		}
		boolean saveFlag = false;
		String Id = "" ;
		String num_id = es.getOneValue("SELECT MAX(TO_NUMBER(NVL(ID,0))) AS MAXID FROM SYJK_CCS_QGCLWTMDXX_TEMP ORDER BY TO_NUMBER(ID) DESC");
		if(num_id == null || "".equals(num_id)){
			Id = "1";
		}else{
			Id = String.valueOf(Integer.parseInt(num_id) + 1);
		}
		saveFlag = es
				.execUpdateSQL("INSERT INTO SYJK_CCS_QGCLWTMDXX_TEMP (ID,CLSBDM,YCYYDM,HPHM,HPZL,CLZL,FDJH,ZCCMC,SYXZ,CLXH,CLCSDJRQ,JGCLLX,HDZKS"
						+ ",HDZZL,ZBZL,PL,GL,SKSSSQ,SKSSZQ,DQYNSE,DWJSJE,DQNDWSE,HEJE,REVENUECODE,APPLYSTATUS,WSPZ,LOGINNAME,WSPZNO)VALUES"
						+ " ('"
						+ Id
						+ "','"
						+ wtmd.getCLSBDM()
						+ "','"
						+ wtmd.getYCYYDM()
						+ "','"
						+ wtmd.getHPHM()
						+ "','"
						+ wtmd.getHPZL()
						+ "','"
						+ wtmd.getCLZL()
						+ "','"
						+ ""
						+ wtmd.getFDJH()
						+ "','"
						+ wtmd.getZCCMC()
						+ "','"
						+ wtmd.getSYXZ()
						+ "','"
						+ wtmd.getCLXH()
						+ "',TO_DATE('"
						+ cl
						+ "','yyyy-MM-dd'),'"
						+ ""
						+ wtmd.getJGCLLX()
						+ "','"
						+ HDZKS
						+ "','"
						+ HDZZL
						+ "','"
						+ ZBZL
						+ "','"
						+ PL
						+ "',"
						+ "'"
						+ GL
						+ "',TO_DATE('"
						+ sq
						+ "','yyyy-MM-dd'),TO_DATE('"
						+ zq
						+ "','yyyy-MM-dd'),'"
						+ wtmd.getDQYNSE()
						+ "','"
						+ wtmd.getDWJSJE()
						+ "','"
						+ wtmd.getDQNDWSE()
						+ "','"
						+ wtmd.getHEJE()
						+ "','"
						+ wtmd.getREVENUECODE()
						+ "','0','" + wtmd.getWSPZ() + "','" + name + "','" + wtmd.getWSPZNO() + "')");

		return saveFlag;
	}

	// 删除
	public boolean deleteWtmd(String[] ids) {
		boolean deleteFlag = true;
		for (String id : ids ) {
		deleteFlag = es.execUpdateSQL("delete from SYJK_CCS_QGCLWTMDXX_TEMP t where t.id="+ id);
		}
		return deleteFlag;
	}

	// 提交审核
	public boolean updataWtmd(String[] ids,String name) {
		boolean deleteFlag = true;

		String SJCJRQ = DateUtil.getDate();
		String sjcjrq = SJCJRQ.replaceAll("/", "");
		for (String id : ids ) {
			deleteFlag =  es.execUpdateSQL("UPDATE SYJK_CCS_QGCLWTMDXX_TEMP t set t.APPLYSTATUS = '1', t.SJCJRQ = TO_DATE("+sjcjrq+",'yyyy-MM-dd '),t.LOGINNAME='"+name+"' where t.id=" + id);
			
		}
		
		
		return deleteFlag;
	}

	// 修改查询
	public boolean saveEdit(SYJK_CCS_QGCLWTMDXX_TEMP wtmd, String id) {
		
//		String CLCSDJRQ = DateUtil.getStringDate(wtmd.getCLCSDJRQ(), null);
		String cl = "";
		if(wtmd.getCLCSDJRQ() != null && !"".equals(wtmd.getCLCSDJRQ())){
			cl = DateUtil.getStringDate(wtmd.getCLCSDJRQ(),null);
		}
//		String SKSSSQ = DateUtil.getStringDate(wtmd.getSKSSSQ(), null);
		String sq = "";
		if(wtmd.getSKSSSQ() != null && !"".equals(wtmd.getSKSSSQ())){
			sq = DateUtil.getStringDate(wtmd.getSKSSSQ(),null);
		}
//		String SKSSZQ = DateUtil.getStringDate(wtmd.getSKSSZQ(), null);
		String zq = "" ;
		if(wtmd.getSKSSZQ() != null && !"".equals(wtmd.getSKSSZQ())){
			zq = DateUtil.getStringDate(wtmd.getSKSSZQ(),null);
		}
		Double HDZKS = wtmd.getHDZKS();
		if (null == HDZKS) {
			HDZKS = 0.0;
		}
		Double HDZZL = wtmd.getHDZZL();
		if (null == HDZZL) {
			HDZZL = 0.0;
		}

		Double ZBZL = wtmd.getZBZL();
		if (null == ZBZL) {
			ZBZL = 0.0;
		}

		Double PL = wtmd.getPL();
		if (null == PL) {
			PL = 0.0;
		}

		Double GL = wtmd.getGL();
		if (null == GL) {
			GL = 0.0;
		}
		boolean saveFlag = false;
		saveFlag = es
				.execUpdateSQL("UPDATE SYJK_CCS_QGCLWTMDXX_TEMP t set t.CLSBDM='"
						+ wtmd.getCLSBDM()
						+ "',t.HPHM='"
						+ wtmd.getHPHM()
						+ "',t.HPZL='"
						+ wtmd.getHPZL()
						+ "',t.CLZL='"
						+ wtmd.getCLZL()
						+ "',t.FDJH='"
						+ wtmd.getFDJH()
						+ "',"
						+ "t.ZCCMC='"
						+ wtmd.getZCCMC()
						+ "',t.SYXZ='"
						+ wtmd.getSYXZ()
						+ "',t.CLXH='"
						+ wtmd.getCLXH()
						+ "',t.CLCSDJRQ=TO_DATE('"
						+ cl
						+ "','yyyy-MM-dd'),t.JGCLLX='"
						+ wtmd.getJGCLLX()
						+ "',t.HDZKS='"
						+ HDZKS
						+ "',"
						+ "t.HDZZL='"
						+ HDZZL
						+ "',t.ZBZL='"
						+ ZBZL
						+ "',t.PL='"
						+ PL
						+ "',t.GL='"
						+ GL
						+ "',t.SKSSSQ=TO_DATE('"
						+ sq
						+ "','yyyy-MM-dd'),"
						+ "t.SKSSZQ=TO_DATE('"
						+ zq
						+ "','yyyy-MM-dd'),t.DQYNSE='"
						+ wtmd.getDQYNSE()
						+ "',t.REVENUECODE='"
						+ wtmd.getREVENUECODE()
						+ "',t.WSPZ='" + wtmd.getWSPZ() + "',t.WSPZNO='" + wtmd.getWSPZNO() + "' where t.id=" + id);

		return saveFlag;
	}
}
