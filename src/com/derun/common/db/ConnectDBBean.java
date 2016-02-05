package com.derun.common.db;

/**
 * 获取连接、测试连接、关闭连接的通用类
 * @author tan_yi_min  
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import com.derun.dbpool.DBConnectionManager;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;

public class ConnectDBBean {

	protected static Logger log = Logger.getLogger(ConnectDBBean.class);// 加入log4j类

	private static int errNum = 0;

	private static DBConnectionManager mgr = DBConnectionManager.getInstance();

	public static Connection getConnection(String poolname) throws NoFreeConnectionException {
		Connection conn = mgr.getConnection(poolname);
		return conn;
	}

	public static void closeConnection(String poolname, Connection conn) throws NoFreeConnectionException {
		mgr.freeConnection(poolname, conn);
	}

	/**
	 * 测试连接有效性
	 *
	 * @param con
	 * @return
	 */
	private static boolean testConn(Connection con) {
		Statement st = null;
		try {
			st = con.createStatement();
			if (null == st) {
				log.info("LIUKE连接有问题，ID： " + con);
				return false;
			}
		} catch (SQLException e) {
			log.error(e);
			return false;
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}

		return true;
	}

	public static void Nls_Date_Format(Connection con) {
		String sql = "alter session set nls_date_format='YYYY-MM-DD'";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException, NoFreeConnectionException {
		Connection conn = null;
		ResultSet dataset = null;

		conn = ConnectDBBean.getConnection(ReadWriteDBPool.readPool);

		Statement stmt = conn.createStatement();
		dataset = stmt.executeQuery("select swdjrq,sjcjrq from SY_NSRJBDJXX ");

		while (dataset.next()) {
			log.info(dataset.getDate("swdjrq"));
			log.info(dataset.getDate("sjcjrq"));
		}
	}
}
