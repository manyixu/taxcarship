package com.derun.all.common;
/**
 * @author MILI
 * @time 2014-4-17 16:36:54
 * @描述：数据库操作DAO
 * */
public class SqlDao {
	/**
	 * @author MILI
	 * @time 2014-4-17 16:38:39
	 * @描述：去掉 sql 中 'null'
	 * */
	public static String off_NUll(String sql){
		sql = sql.replaceAll("'null'", "null");
		return sql ;
	}
	
}
