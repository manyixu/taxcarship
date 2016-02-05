package com.derun.taxdeclaration.vo;

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
 * @time 2014-11-27 15:34:12
 * @描述：申报日期上传服务 线程删除数据
 * */
public class TaxDeclaeredUPloadService_detele extends Thread {
	int count = 0;

	public TaxDeclaeredUPloadService_detele(int count) {
		this.count = count ;
	}
	@Override
	public void run() {
		PreparedStatement pre = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			String sql = SqlText.D_07_CONFIRM_SB_003;
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
