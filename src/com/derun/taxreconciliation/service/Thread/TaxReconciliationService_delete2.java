package com.derun.taxreconciliation.service.Thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.SqlText;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
/**
 * @author MILI
 * @time 2014-11-27 11:28:32
 * @描述：对帐类型为2(车船税确认对帐)  删除数据 线程
 * */
public class TaxReconciliationService_delete2 extends Thread{
	int count = 0 ;
	public TaxReconciliationService_delete2(int count){
		this.count = count ;
	}
	@Override
	public void run() {
		PreparedStatement pre = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.D_05_CONFIRM_DZ_004;
			String sqls = sql + count;
			pre = conn.prepareStatement(sqls);
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	}
}
