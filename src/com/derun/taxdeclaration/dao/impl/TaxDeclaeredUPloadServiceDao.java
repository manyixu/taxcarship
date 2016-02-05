package com.derun.taxdeclaration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.derun.all.common.SqlDao;
import com.derun.beans.AnnualTax_Type;
import com.derun.beans.DeclareDateUploadReqInfo;
import com.derun.beans.DeclareDateUploadResInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.beans.Tax_Type;
import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.ExeSQL;
import com.derun.common.db.SqlText;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSSBRQSCCC;
import com.derun.model.po.SYJK_CCS_CCSSBRQSCRC;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * 
 * @author 郑艳英
 * 申报日期dao
 */
public class TaxDeclaeredUPloadServiceDao {
	 //数据库操作类
	  ExeSQL exeSql = new ExeSQL();
	  //sql类
	  TaxDeclaeredUPloadServiceSql taxDeclaeredUPloadServiceSql = new TaxDeclaeredUPloadServiceSql();
     //保存确认码
	public boolean savaConfirmNo(TaxDealCode_Type[] taxConfirmNo,int count){
		PreparedStatement pre = null;
		 Connection conn = null;
		 boolean flag = false;
		 
		 try {
			 conn = DBConnPool.getConnection();
			 conn.setAutoCommit(false);   
			 String sql = SqlText.C_07_CONFIRM_SB_001;
			 pre = conn.prepareStatement(sql);
			 for(int i=0;i<taxConfirmNo.length;i++){
				pre.setString(1, taxConfirmNo[i].getTaxDealCode_Type());
			    pre.setInt(2, count);
				pre.addBatch();
				
			}
			 int[] i = pre.executeBatch();
				if(i!=null){
					flag = true;
				System.out.println("入参插入成功--------------------------");
				}else {
					flag = false;
				System.out.println("入参插入失败--------------------------");
				}
				conn.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try{
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
		 return flag;
	}
	

	
	//查询不存在的
	 public String[] getDateUploadNotIn(int count){
		 PreparedStatement pre = null;
		 ResultSet rs = null;
		 Connection conn=null; 
		 List<String> list = new ArrayList<String>();
		 try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.R_07_CONFIRM_SB_002;
			String sqls = sql + count;
			pre = conn.prepareStatement(sqls);
			rs = pre.executeQuery();
			while(rs.next()){
				   list.add(rs.getString("TAXCONFIRMNO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		String[] str = new String[list.size()];
		for(int i=0;i<list.size();i++){
			str[i] = list.get(i);
		}
		 return str;
	 }
	 
	 //删除确认码
	 public boolean deleteConfirmNo(int count){
		 PreparedStatement pre = null;
		 Connection conn=null; 
		 boolean flag = false;
		 try {
			 conn = DBConnPool.getConnection();
			String sql = SqlText.D_07_CONFIRM_SB_003;
			String sqls = sql + count;
			pre = conn.prepareStatement(sqls);
			pre.executeUpdate();
	
		 }catch (SQLException e) {
		    	e.printStackTrace();
		    	
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {  
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
		 return flag;
	 }
	
	 //数据库中不存在的，更新入库明细
	 public boolean updateRkMX(TaxDealCode_Type[] taxConfirmNo){
		 PreparedStatement pre = null;
		 Connection conn=null; 
		 boolean flag = false;
		 int j = 0;
		 try {
		   conn = DBConnPool.getConnection();
			conn.setAutoCommit(false);
			String sql=SqlText.U_07_RKMX_004;
			for(int i=0;i<taxConfirmNo.length;i++){
				pre = conn.prepareStatement(sql);
				pre.setString(1, taxConfirmNo[i].getTaxDealCode_Type());
				j = pre.executeUpdate();
				if(j>0){
					flag = true;
				}else {
					flag = true;
				}
				pre.close();
			}
			conn.commit();
		 }catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if (pre != null) {
						pre.close();
					}
					ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//					if (conn != null) {
//						conn.close();
//					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NoFreeConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 return flag;
	 }
     //保存申报日期上传入参信息
	public boolean saveTaxDeclaeredUPloadReq(SYJK_CCS_CCSSBRQSCRC[] ccssbrqscrcs) {
		
		PreparedStatement pre = null;
		 Connection conn = null;
		 boolean flag = false;
		 int[]returnCode = null;
		 try {
			 conn = DBConnPool.getConnection();
			 conn.setAutoCommit(false);   
			String sql = SqlText.C_07_CCSSBRQSCRC_005;
			pre = conn.prepareStatement(sql);
			for(int i=0;i<ccssbrqscrcs.length;i++){
				
				SYJK_CCS_CCSSBRQSCRC ccssbrqscrc = ccssbrqscrcs[i];
				String DECLAREDATE = DateUtil.getString(ccssbrqscrc.getDECLAREDATE(), "yyyy-MM-dd HH:mm:ss");
				String SJCJRQ = DateUtil.getString(ccssbrqscrc.getSJCJRQ(), "yyyy-MM-dd HH:mm:ss");
				pre.setString(1, DECLAREDATE);
				pre.setString(2, ccssbrqscrc.getLISTRESPECTIVEANNUAL());
				pre.setString(3, ccssbrqscrc.getLOGINNAME());
				pre.setString(4, ccssbrqscrc.getREVENUECODE());
				pre.setString(5,SJCJRQ);
				pre.setString(6, ccssbrqscrc.getSJCJFS());
				pre.setString(7, ccssbrqscrc.getTAXCHANGEQUERYNO());
				pre.setString(8, ccssbrqscrc.getAREACODE());
				pre.setString(9, ccssbrqscrc.getCOMPANYCODE());
				pre.addBatch();
				//returnCode = pre.executeBatch();
				
			}
			int[] i = pre.executeBatch();
			if(i!=null){
				flag = true;
			System.out.println("申报入参插入成功--------------------------");
			}else {
				flag = false;
			System.out.println("申报入参插入失败--------------------------");
			}
			conn.commit();
//			if(returnCode!=null){
//				flag = true;
//			}else {
//				flag = false;
//			}
		
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try{
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
		 return flag;
	}
	
	
	 //保存申报日期上传出参信息
	public boolean saveTaxDeclaeredUPloadRes(SYJK_CCS_CCSSBRQSCCC[] ccssbrqscccs) {
		PreparedStatement pre = null;
		 Connection conn = null;
		 boolean flag = false;
		 int[] returnCode = null;
	
		 try {
			 conn = DBConnPool.getConnection();
			conn.setAutoCommit(false);   
			String sql = SqlText.C_07_CCSSBRQSCCC_006;
			pre = conn.prepareStatement(sql);
			for(int i=0;i<ccssbrqscccs.length;i++){
				
				SYJK_CCS_CCSSBRQSCCC ccssbrqsccc = ccssbrqscccs[i];
				String SJCJRQ = DateUtil.getString(ccssbrqsccc.getSJCJRQ(), "yyyy-MM-dd HH:mm:ss");
				pre.setString(1, ccssbrqsccc.getRETURNCODE());
				pre.setString(2, ccssbrqsccc.getLISTRESPECTIVEANNUAL());
				pre.setString(3, ccssbrqsccc.getLOGINNAME());
				pre.setString(4, ccssbrqsccc.getREVENUECODE());
				pre.setString(5, SJCJRQ);
				pre.setString(6, ccssbrqsccc.getSJCJFS());
				pre.addBatch();
				//returnCode = pre.executeBatch();
				
				
			}
			int[] i = pre.executeBatch();
			if(i!=null){
				flag = true;
				System.out.println("出参插入成功--------------------------");
			}else {
				flag = false;
				System.out.println("出参插入失败--------------------------");
			}
			conn.commit();
			
			
		} catch (SQLException e) {
			
			
				e.printStackTrace();
	
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				
				try{
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
		 return flag;
	}
	
	//查询入库明细,取vin,发动机号,号牌号码,号牌种类(保存轨迹表的时候需要)
	public SYJK_CCS_RKMX getVehicleType(String taxConfirmNo) {
		PreparedStatement pre = null;
		SYJK_CCS_RKMX rkmx = null;
		
		ResultSet rs = null;
		Connection conn = null;
		String sql = SqlText.R_07_RKMX_007;
		try {
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql);
		    pre.setString(1, taxConfirmNo);
			rs = pre.executeQuery();
			if (rs.next()) {
				rkmx = new SYJK_CCS_RKMX();
				rkmx.setVIN(rs.getString("VIN"));
				rkmx.setHPHM(rs.getString("HPHM"));
				rkmx.setHPZL(rs.getString("HPZL"));
				rkmx.setEngineNo(rs.getString("ENGINENO"));
						
						
					}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
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

		return rkmx;
	}
	

	  //保存申报日期上传入参信息2014-11-18修改申报日期上传超时
	public boolean saveTaxDeclaeredUPloadReq(DeclareDateUploadReqInfo declareDateUploadReqInfo) {
		
		PreparedStatement pre = null;
		 Connection conn = null;
		 boolean flag = false;
		 int[]returnCode = null;
		 TaxDealCode_Type[] confirmNo = declareDateUploadReqInfo.getTaxConfirmNo();
		 try {
			 conn = DBConnPool.getConnection();
			 conn.setAutoCommit(false);   
			String sql = SqlText.C_07_CCSSBRQSCRC_005;
			pre = conn.prepareStatement(sql);
			for(int i=0;i<confirmNo.length;i++){
				
				//SYJK_CCS_CCSSBRQSCRC ccssbrqscrc = ccssbrqscrcs[i];
				//String DECLAREDATE = DateUtil.getString(ccssbrqscrc.getDECLAREDATE(), "yyyy-MM-dd HH:mm:ss");
				String SJCJRQ = DateUtil.getString(new Date(), "yyyy-MM-dd HH:mm:ss");
				 //申报时间
				pre.setString(1, declareDateUploadReqInfo.getDeclareDate());
				//车船税确认码
				pre.setString(2, confirmNo[i].getTaxDealCode_Type());
				 //操作员
				pre.setString(3, CfgLoader.getConfigValue("SysCode_USER", "username"));
				//税务机关编码
				pre.setString(4, CfgLoader.getConfigValue("SysCode_USER", "usercode"));
				//系统采集日期
				pre.setString(5,SJCJRQ);
				//系统采集方式
				pre.setString(6, "0");
				//车船税变更确认码
				pre.setString(7, confirmNo[i].getTaxDealCode_Type());
				//国标区域代码
				pre.setString(8, declareDateUploadReqInfo.getAreaCode());
				
				//公司代码
				pre.setString(9, declareDateUploadReqInfo.getCompanyCode());
				pre.addBatch();
				//returnCode = pre.executeBatch();
			}
			int[] j = pre.executeBatch();
			if(j!=null){
				flag = true;
			System.out.println("申报入参插入成功--------------------------");
			}else {
				flag = false;
			System.out.println("申报入参插入失败--------------------------");
			}
			conn.commit();
//			if(returnCode!=null){
//				flag = true;
//			}else {
//				flag = false;
//			}
		
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try{
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
		 return flag;
	}


	 //保存申报日期上传出参信息2014-11-18修改申报日期上传超时
	public boolean saveTaxDeclaeredUPloadRes(DeclareDateUploadResInfo declareDateUploadResInfo) {
		PreparedStatement pre = null;
		 Connection conn = null;
		 boolean flag = false;
		 int[] returnCode = null;
		 TaxDealCode_Type[] confirmNo =null;
		 if(declareDateUploadResInfo.getTaxConfirmNo()==null){
			 confirmNo = new TaxDealCode_Type[0];
		 }else{
			 confirmNo =  declareDateUploadResInfo.getTaxConfirmNo();
		 }
		 try {
			 conn = DBConnPool.getConnection();
			conn.setAutoCommit(false);   
			String sql = SqlText.C_07_CCSSBRQSCCC_006;
			pre = conn.prepareStatement(sql);
			for(int i=0;i<confirmNo.length;i++){
				String SJCJRQ = DateUtil.getString(new Date(), "yyyy-MM-dd HH:mm:ss");
				pre.setString(1, declareDateUploadResInfo.getReturnCode());
				pre.setString(2, confirmNo[i].getTaxDealCode_Type());
				pre.setString(3, CfgLoader.getConfigValue("SysCode_USER", "username"));
				pre.setString(4, CfgLoader.getConfigValue("SysCode_USER", "usercode"));
				pre.setString(5, SJCJRQ);
				pre.setString(6, "0");
				pre.addBatch();
				//returnCode = pre.executeBatch();
				
				
			}
			int[] i = pre.executeBatch();
			if(i!=null){
				flag = true;
				System.out.println("出参插入成功--------------------------");
			}else {
				flag = false;
				System.out.println("出参插入失败--------------------------");
			}
			conn.commit();
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				
				try{
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
		 return flag;
		
		  
	}

	}
	
	 
	 
