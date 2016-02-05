package com.derun.taxdeclaration.dao.impl;

import com.derun.all.common.SqlDao;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.db.SqlText;

/**
 * 
 * @author 郑艳英
 * 申报日期上传sql类
 */
public class TaxDeclaeredUPloadServiceSql {
  
	//保存确认码sql 申报表
//	public String getSaveConfirmNoSql(TaxDealCode_Type[] taxConfirmNo,int count){
//		String reqSql = "";
//		StringBuffer sql = new StringBuffer();
//		sql.append(SqlText.C_07_CONFIRM_SB_001);
//		for(int i=0;i<taxConfirmNo.length;i++){
//			sql.append("'" + taxConfirmNo[i].getTaxDealCode_Type() + "'");
//			sql.append(",'" + count + "'");
//			sql.append(")");
//			reqSql = SqlDao.off_NUll(sql.toString());
//		
//		}
//		return reqSql ;
//	}
	
	//查询出数据库中不存在的 申报表
	public String getSelectNoExists(int count){
		String sql = "";
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(SqlText.R_07_CONFIRM_SB_002);
		sqlBuf.append(count);
		return sql;
	}
	
	
	
}
