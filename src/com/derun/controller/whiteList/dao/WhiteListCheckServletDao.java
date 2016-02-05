package com.derun.controller.whiteList.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX_TEMP;

/**
 * @author MILI
 * @time: 2014-11-23 09:26:58
 * @描述：白名单审核 数据库交互
 * */
public class WhiteListCheckServletDao {
	public static List<String> list_abnormal = new ArrayList<String>();
	public static List<String> getList_abnormal() {
		return list_abnormal;
	}
	public static void setList_abnormal(List<String> listAbnormal) {
		list_abnormal = listAbnormal;
	}
	/**
	 * @author MILI
	 * @time 2014-11-23 09:32:23
	 * @描述：取白名单待审核数据
	 * */
	public List<SYJK_CCS_QGCLWTMDXX_TEMP> getWTMD_W(int page,int rows,String START,String END){
		List<SYJK_CCS_QGCLWTMDXX_TEMP> list_wtmd = new ArrayList<SYJK_CCS_QGCLWTMDXX_TEMP>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
//		String sql = "SELECT * FROM(SELECT ROWNUM R, T.* FROM (SELECT * FROM SYJK_CCS_QGCLWTMDXX_TEMP WHERE APPLYSTATUS = '1' ORDER BY ID DESC) T WHERE ROWNUM <= " + page * rows + ") WHERE R > " + (page-1) * rows ;
		String sql = "SELECT * FROM(SELECT ROWNUM R, T.* FROM (SELECT * FROM SYJK_CCS_QGCLWTMDXX_TEMP WHERE APPLYSTATUS = '1'";
		if(StringUtil.isNotEmpty(START)) {
			sql += " AND SJCJRQ >= TO_DATE('" + START + "','YYYY-MM-DD')" ;
		}
		
		if(StringUtil.isNotEmpty(END)) {
			sql += " AND SJCJRQ <= TO_DATE('" + END + "','YYYY-MM-DD')" ;
		}
		
		// 时间查询
//		if(START != null && END != null && !"".equals(START) && !"".equals(END)){ 
//			sql += " AND SJCJRQ BETWEEN TO_DATE('" + START +"', 'yyyy-mm-dd hh24:mi:ss') AND "
//				+ "TO_DATE('" + END + "', 'yyyy-mm-dd hh24:mi:ss')" ;
//		}
		sql += " ORDER BY ID DESC) T WHERE ROWNUM <= " + page * rows + ") WHERE R > " + (page-1) * rows ;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			while(rs.next()){
				SYJK_CCS_QGCLWTMDXX_TEMP wtmd = new SYJK_CCS_QGCLWTMDXX_TEMP();
				wtmd.setID(rs.getString("ID"));					// ID
				wtmd.setCLSBDM(rs.getString("CLSBDM"));			// 车辆识别代码
				wtmd.setWSPZHM(rs.getString("WSPZHM"));			// 已缴税额
				wtmd.setHEJE(rs.getDouble("HEJE"));				// 完税凭证
				wtmd.setLOGINNAME(rs.getString("LOGINNAME"));	// 申请人
				wtmd.setSJCJRQ(rs.getDate("SJCJRQ"));			// 申请日期
				wtmd.setAPPLYSTATUS(rs.getString("APPLYSTATUS"));	// 申请状态
				wtmd.setYCYYDM(rs.getString("YCYYDM"));			// 异常原因
				list_wtmd.add(wtmd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询白名单出错。。。。。。。。。。WhiteListCheckServletDao ---  getWTMD_W()");
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pre != null){
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null){
//					conn.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list_wtmd ;
	}  
	/**
	 * @author MILI
	 * @time 2014-11-23 14:20:41
	 * @描述：查询一共有多少条记录
	 * */
	public int getWTMD_Count(String START,String END){
		int count = 0 ;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "SELECT COUNT(*) AS TOTAL FROM SYJK_CCS_QGCLWTMDXX_TEMP WHERE APPLYSTATUS = '1'" ;
		
		if(StringUtil.isNotEmpty(START)) {
			sql += " AND SJCJRQ >= TO_DATE('" + START + "','YYYY-MM-DD')" ;
		}
		
		if(StringUtil.isNotEmpty(END)) {
			sql += " AND SJCJRQ <= TO_DATE('" + END + "','YYYY-MM-DD')" ;
		}
		
		// 时间查询
//		if(START != null && END != null && !"".equals(START) && !"".equals(END)){ 
//			sql += " AND SJCJRQ BETWEEN TO_DATE('" + START +"', 'yyyy-mm-dd hh24:mi:ss') AND "
//				+ "TO_DATE('" + END + "', 'yyyy-mm-dd hh24:mi:ss')" ;
//		} 
		
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
			if(rs.next()){
				count = rs.getInt("TOTAL");
			}else{
				count = 0 ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询白名单出错。。。。。。。。。。WhiteListCheckServletDao ---  getWTMD_Count()");
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pre != null){
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null){
//					conn.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count ;
	}
	/**
	 * @author MILI
	 * @time 2014-11-24 11:19:41
	 * @描述：批量通过
	 * */
	public int set_pl(String[] str){
		int n = 0 ;
		int m = 0 ;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
//		StringBuffer sql = new StringBuffer("UPDATE SYJK_CCS_QGCLWTMDXX_TEMP SET APPLYSTATUS = '2',CHECKREMARK = '合格' , FILENAME = '0' ,CHECKMAN = 'ADMIN' ,CHECKDATE = SYSDATE WHERE CLSBDM IN (");
//		String sql_p = "" ;
//		for(int i = 0 ; i < str.length ; i++){
//			sql.append("'" + str[i] + "',");
//		}
//		sql_p = sql.substring(0, sql.length() - 1);
//		sql_p += ")";
		
		String sql = "UPDATE SYJK_CCS_QGCLWTMDXX_TEMP SET APPLYSTATUS = '2',CHECKREMARK = '审核通过' , FILENAME = '0' ,CHECKMAN = 'ADMIN' ,CHECKDATE = SYSDATE WHERE CLSBDM = '" ;
		for(int i = 0 ; i < str.length ; i++){
			try {
				conn = DBConnPool.getConnection();
				m = 0 ;
				n = 0 ;
				conn.setAutoCommit(false);
				String sql_p = sql + str[i] + "'" ;
				pre = conn.prepareStatement(sql_p);
				n = pre.executeUpdate();
				List<SYJK_CCS_QGCLWTMDXX_TEMP> list = this.getDetail_xx(str);
				if(list != null && list.size() > 0){
					m = this.Synchronous_wtmd(list.get(i));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				list_abnormal.add(str[i] + " 审批出错");
				System.out.println("批量通过失败。。。。。。。。。。WhiteListCheckServletDao ---  set_pl()");
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(n > 0 && m > 0){
						conn.commit();
					}else{
						conn.rollback();
						m = 0 ;
						n = 0 ;
					}
					if(rs != null){
						rs.close();
					}
					if(pre != null){
						pre.close();
					}
					ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//					if(conn != null){
//						conn.close();
//					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NoFreeConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return n ;
	}
	/**
	 * @author MILI
	 * @time 2014-11-25 16:48:05
	 * @描述：白名单详细信息
	 * */
	public List<SYJK_CCS_QGCLWTMDXX_TEMP> getDetail_xx(String[] clsbdm){
		List<SYJK_CCS_QGCLWTMDXX_TEMP> List_wtmd = new ArrayList<SYJK_CCS_QGCLWTMDXX_TEMP>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql_p = "" ;
		StringBuffer sql = new StringBuffer("SELECT ID,CZMC,CZZJHM,CZZJLX,CZDZ,CZLXDH,YCYYDM,CLSBDM,HPHM,HPZL,CLZL," +
				"FDJH,ZCCMC,SYXZ,WSPCLBZ,CLXH,CLCSDJRQ,JGCLLX,HDZKS,HDZZL,ZBZL,PL,GL,RLZL,SKSSSQ,SKSSZQ,KJWSPZSWJGDM," +
				"KJWSPZSWJGMC,WSPZHM,SWJGJTGJFL,JSDW,DWJSJE,DQNDWSE,JMSYY,JMSFA,JMBL,JMJE,JMSPZH,SWJGDM,SWJGMC," +
				"DQYNSE,YQSJ,YQTS,ZNJ,HEJE,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,ND,OVERDUEPAYMENT,HANDLEFLAG,VALIDATEFLAG," +
				"SENDFLG,CARSERIALNO,SYSTEMDATE,WSPZ " + 
				"FROM SYJK_CCS_QGCLWTMDXX_TEMP T WHERE T.CLSBDM IN(");
		try {
			conn = DBConnPool.getConnection();
			for(int i = 0 ; i < clsbdm.length ; i++){
				sql.append("'" + clsbdm[i] + "',");
			}
			sql_p = sql.substring(0, sql.length() - 1);
			sql_p += ") ORDER BY ID DESC";
			pre = conn.prepareStatement(sql_p);
			rs = pre.executeQuery();
			while(rs.next()){
				SYJK_CCS_QGCLWTMDXX_TEMP wtmd = new SYJK_CCS_QGCLWTMDXX_TEMP();
				wtmd.setID(rs.getString("ID"));
				wtmd.setCZMC(rs.getString("CZMC"));
				wtmd.setCZZJHM(rs.getString("CZZJHM"));
				wtmd.setCZZJLX(rs.getString("CZZJLX"));
				wtmd.setCZDZ(rs.getString("CZDZ"));
				wtmd.setCZLXDH(rs.getString("CZLXDH"));
				wtmd.setYCYYDM(rs.getString("YCYYDM"));
				wtmd.setCLSBDM(rs.getString("CLSBDM"));
				wtmd.setHPHM(rs.getString("HPHM"));
				wtmd.setHPZL(rs.getString("HPZL"));
				wtmd.setCLZL(rs.getString("CLZL"));
				wtmd.setFDJH(rs.getString("FDJH"));
				wtmd.setZCCMC(rs.getString("ZCCMC"));
				wtmd.setSYXZ(rs.getString("SYXZ"));
				wtmd.setWSPCLBZ(rs.getString("WSPCLBZ"));
				wtmd.setCLXH(rs.getString("CLXH"));
				wtmd.setCLCSDJRQ(rs.getDate("CLCSDJRQ"));
				wtmd.setJGCLLX(rs.getString("JGCLLX"));
				wtmd.setHDZKS(rs.getDouble("HDZKS"));
				wtmd.setHDZZL(rs.getDouble("HDZZL"));
				wtmd.setZBZL(rs.getDouble("ZBZL"));
				wtmd.setPL(rs.getDouble("PL"));
				wtmd.setGL(rs.getDouble("GL"));
				wtmd.setRLZL(rs.getString("RLZL"));
				wtmd.setSKSSSQ(rs.getDate("SKSSSQ"));
				wtmd.setSKSSZQ(rs.getDate("SKSSZQ"));
				wtmd.setKJWSPZSWJGDM(rs.getString("KJWSPZSWJGDM"));
				wtmd.setKJWSPZSWJGMC(rs.getString("KJWSPZSWJGMC"));
				wtmd.setWSPZHM(rs.getString("WSPZHM"));
				wtmd.setSWJGJTGJFL(rs.getString("SWJGJTGJFL"));
				wtmd.setJSDW(rs.getString("JSDW"));
				wtmd.setDWJSJE(rs.getDouble("DWJSJE"));
				wtmd.setDQNDWSE(rs.getDouble("DQNDWSE"));
				wtmd.setJMSYY(rs.getString("JMSYY"));
				wtmd.setJMSFA(rs.getString("JMSFA"));
				wtmd.setJMBL(rs.getDouble("JMBL"));
				wtmd.setJMJE(rs.getDouble("JMJE"));
				wtmd.setJMSPZH(rs.getString("JMSPZH"));
				wtmd.setSWJGDM(rs.getString("SWJGDM"));
				wtmd.setSWJGMC(rs.getString("SWJGMC"));
				wtmd.setDQYNSE(rs.getDouble("DQYNSE"));
				wtmd.setYQSJ(rs.getDate("YQSJ"));
				wtmd.setYQTS(rs.getDouble("YQTS"));
				wtmd.setZNJ(rs.getDouble("ZNJ"));
				wtmd.setHEJE(rs.getDouble("HEJE"));
				wtmd.setLOGINNAME(rs.getString("LOGINNAME"));
				wtmd.setREVENUECODE(rs.getString("REVENUECODE"));
				wtmd.setSJCJRQ(rs.getDate("SJCJRQ"));
				wtmd.setSJCJFS(rs.getString("SJCJFS"));
				wtmd.setND(rs.getString("ND"));
				wtmd.setOVERDUEPAYMENT(rs.getString("OVERDUEPAYMENT"));
				wtmd.setWSPZ(rs.getString("WSPZ"));
//				wtmd.setHANDLEFLAG(rs.getString("HANDLEFLAG"));
//				wtmd.setVALIDATEFLAG(rs.getString("VALIDATEFLAG"));
//				wtmd.setSENDFLG(rs.getString("SENDFLG"));
//				wtmd.setCARSERIALNO(rs.getString("CARSERIALNO"));
//				wtmd.setSYSTEMDATE(rs.getDate("SYSTEMDATE"));
				
				List_wtmd.add(wtmd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询白名单出错。。。。。。。。。。WhiteListCheckServletDao ---  getDetail_xx()");
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pre != null){
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null){
//					conn.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return List_wtmd;
	} 
	/**
	 * @author MILI
	 * @time 2014-11-26 09:31:19
	 * @描述：单条审批
	 * */
	public int single_e(String account,String pass_no,String clsbdm){
		String clsbdms[] = {clsbdm} ;
		int n = 0 ;
		int m = 0 ;
		StringBuffer sbf = new StringBuffer("UPDATE SYJK_CCS_QGCLWTMDXX_TEMP SET APPLYSTATUS = ?,CHECKREMARK = ?,CHECKMAN = 'ADMIN' ,CHECKDATE = SYSDATE WHERE CLSBDM = ?");
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			conn.setAutoCommit(false);
			pre = conn.prepareStatement(sbf.toString());
			if(pass_no != null && !"".equals(pass_no)){
				if("go".equals(pass_no)){	
					pre.setString(1, "2");	// 申请通过
					if("".equals(account) || account == null){
						account = "申请通过" ;
					}
				}else{
					pre.setString(1, "3");	// 申请未通过
				}
				pre.setString(2, account);
				pre.setString(3, clsbdm);
			}
			n = pre.executeUpdate();
			if("go".equals(pass_no)){
				List<SYJK_CCS_QGCLWTMDXX_TEMP> list = this.getDetail_xx(clsbdms);
				if(list != null && list.size() > 0){
					m = this.Synchronous_wtmd(list.get(0));
				}
			}else{
				m = 1 ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list_abnormal.add(clsbdm + " 审批出错");
			System.out.println("更新白名单出错。。。。。。。。。。WhiteListCheckServletDao ---  single_e()");
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(n > 0 && m > 0){
					conn.commit();
				}else{
					conn.rollback();
					n = 0 ;
				}
				if(rs != null){
					rs.close();
				}
				if(pre != null){
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null){
//					conn.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n ;
	}
	/**
	 * @author MILI
	 * @time 2014-11-26 10:18:38
	 * @描述：同步到问题名单正式表
	 * */
	public int Synchronous_wtmd(SYJK_CCS_QGCLWTMDXX_TEMP wtmd){
		int n = 0 ;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "INSERT INTO SYJK_CCS_QGCLWTMDXX (ID,CZMC,CZZJHM,CZZJLX,CZDZ,CZLXDH,YCYYDM,CLSBDM,HPHM,HPZL,CLZL," +
				"FDJH,ZCCMC,SYXZ,WSPCLBZ,CLXH,CLCSDJRQ,JGCLLX,HDZKS,HDZZL,ZBZL,PL,GL,RLZL,SKSSSQ,SKSSZQ,KJWSPZSWJGDM," +
				"KJWSPZSWJGMC,WSPZHM,SWJGJTGJFL,JSDW,DWJSJE,DQNDWSE,JMSYY,JMSFA,JMBL,JMJE,JMSPZH,SWJGDM,SWJGMC," +
				"DQYNSE,YQSJ,YQTS,ZNJ,HEJE,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,ND,OVERDUEPAYMENT)" +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + 
				"?,?,?)";
		try {
			conn = DBConnPool.getConnection();
			conn.setAutoCommit(false);
			pre = conn.prepareStatement(sql);
			if(wtmd != null){
				pre.setString(1, wtmd.getID());
				pre.setString(2, wtmd.getCZMC());
				pre.setString(3, wtmd.getCZZJHM());
				pre.setString(4, wtmd.getCZZJLX());
				pre.setString(5, wtmd.getCZDZ());
				pre.setString(6, wtmd.getCZLXDH());
				pre.setString(7, wtmd.getYCYYDM());
				pre.setString(8, wtmd.getCLSBDM());
				pre.setString(9, wtmd.getHPHM());
				pre.setString(10, wtmd.getHPZL());
				pre.setString(11, wtmd.getCLZL());
				pre.setString(12, wtmd.getFDJH());
				pre.setString(13, wtmd.getZCCMC());
				pre.setString(14, wtmd.getSYXZ());
				pre.setString(15, wtmd.getWSPCLBZ());
				pre.setString(16, wtmd.getCLXH());
				pre.setDate(17, (Date) wtmd.getCLCSDJRQ());
				pre.setString(18, wtmd.getJGCLLX());
				pre.setDouble(19, wtmd.getHDZKS());
				pre.setDouble(20, wtmd.getHDZZL());
				pre.setDouble(21, wtmd.getZBZL());
				pre.setDouble(22, wtmd.getPL());
				pre.setDouble(23, wtmd.getGL());
				pre.setString(24, wtmd.getRLZL());
				pre.setDate(25, (Date) wtmd.getSKSSSQ());
				pre.setDate(26, (Date) wtmd.getSKSSZQ());
				pre.setString(27, wtmd.getKJWSPZSWJGDM());
				pre.setString(28, wtmd.getKJWSPZSWJGMC());
				pre.setString(29, wtmd.getWSPZHM());
				pre.setString(30, wtmd.getSWJGJTGJFL());
				pre.setString(31, wtmd.getJSDW());
				pre.setDouble(32, wtmd.getDWJSJE());
				pre.setDouble(33, wtmd.getDQNDWSE());
				pre.setString(34, wtmd.getJMSYY());
				pre.setString(35, wtmd.getJMSFA());
				pre.setDouble(36, wtmd.getJMBL());
				pre.setDouble(37, wtmd.getJMJE());
				pre.setString(38, wtmd.getJMSPZH());
				pre.setString(39, wtmd.getSWJGDM());
				pre.setString(40, wtmd.getSWJGMC());
				pre.setDouble(41, wtmd.getDQYNSE());
				pre.setDate(42, (Date) wtmd.getYQSJ());
				pre.setDouble(43, wtmd.getYQTS());
				pre.setDouble(44, wtmd.getZNJ());
				pre.setDouble(45, wtmd.getHEJE());
				pre.setString(46, wtmd.getLOGINNAME());
				pre.setString(47, wtmd.getREVENUECODE());
				pre.setDate(48, (Date) wtmd.getSJCJRQ());
				pre.setString(49, wtmd.getSJCJFS());
				pre.setString(50, wtmd.getND());
				pre.setString(51, wtmd.getOVERDUEPAYMENT());
				n = pre.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list_abnormal.add(wtmd.getCLSBDM() + " 审批出错");
			System.out.println("同步到问题名单出错。。。。。。。。。。WhiteListCheckServletDao ---  Synchronous_wtmd()");
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(n > 0){
					conn.commit();
				}else{
					conn.rollback();
				}
				if(rs != null){
					rs.close();
				}
				if(pre != null){
					pre.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null){
//					conn.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n ;
	}
}
