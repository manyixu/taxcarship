package com.derun.controller.paraCfg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;

import com.derun.controller.paraCfg.util.ParaCfgUtil;
import com.derun.controller.paraCfg.util.StringUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;

public class ParaCfgDao {

	// 查询
	public List<ParaCfgUtil> query(int page, int rows, String code,
			String codeType, String idhotp, String validateflag) {
		// 增加参数SQL语句
		StringBuilder paramSQL = new StringBuilder();
		// 增加参数集合
		List<Object> paramList = new ArrayList<Object>();
		// 系统代码
		if (StringUtil.isNotEmpty(code)) {
			StringUtil.paramSQL(paramSQL).append(" CODE like ? ");
			paramList.add("%" + code + "%");// 
		}
		// 系统代码类型
		if (StringUtil.isNotEmpty(codeType)) {
			StringUtil.paramSQL(paramSQL).append(" CODETYPE like ? ");
			paramList.add("%" + codeType + "%");// 
		}
		// 是否热配置
		if (StringUtil.isNotEmpty(idhotp)) {
			StringUtil.paramSQL(paramSQL).append(" ISHOTPARA like ? ");
			paramList.add("%" + idhotp + "%");// 
		}
		// 是否有效标识
		if (StringUtil.isNotEmpty(validateflag)) {
			StringUtil.paramSQL(paramSQL).append(" VALIDATEFLAG like ? ");
			paramList.add("%" + validateflag + "%");// 
		}

		List<ParaCfgUtil> list = new ArrayList<ParaCfgUtil>();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
			String pageSQL = " select * from(select rownum r, t.* from (select * from syjk_ccs_code) t where rownum <="
					+ page * rows + ")where r>" + (page - 1) * rows;
//			System.out.println(pageSQL + paramSQL);
			pre = conn.prepareStatement(pageSQL + paramSQL);
			for (int i = 0; i < paramList.size(); i++) {
				pre.setObject(i + 1, paramList.get(i));
			}
			rs = pre.executeQuery();
			while (rs.next()) {
				ParaCfgUtil paraCfgUtil = new ParaCfgUtil();
				paraCfgUtil.setCODE(rs.getString("CODE"));
				paraCfgUtil.setCODEALIA(rs.getString("CODEALIA"));
				paraCfgUtil.setCODENAME(rs.getString("CODENAME"));
				paraCfgUtil.setCODETYPE(rs.getString("CODETYPE"));
				paraCfgUtil.setCODEVALUE(rs.getString("CODEVALUE"));
				paraCfgUtil.setISHOTPARA(rs.getString("ISHOTPARA"));
				paraCfgUtil.setREMARK(rs.getString("REMARK"));
				paraCfgUtil.setVALIDATEFLAG(rs.getString("VALIDATEFLAG"));
				list.add(paraCfgUtil);
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
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn); // 因关闭方式有问题
																				// 需要原来的关闭方式进行连接的关闭
																				// mili
																				// 2015-3-20
																				// 16:34:57
				// if (conn != null) {
				// conn.close();
				// }
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;

	}

	// 获得总条数
	public int getCount() throws SQLException {
		int numb = 0;
		String sql = "select count(*) as total from syjk_ccs_code";
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			numb = rs.getInt("total");
		} else {
			numb = 0;
		}
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		try {
			ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 因关闭方式有问题 需要原来的关闭方式进行连接的关闭 mili 2015-3-20 16:34:57
		return numb;
	}

	// 增加
	public int addPara(ParaCfgUtil paraCfgUtil) throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = null;
		int i = 0;
		try {
			String sql = "insert into syjk_ccs_code values(?,?,?,?,?,?,?,?,SYSDATE)";
			conn = DBConnPool.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraCfgUtil.getCODE());
			pstmt.setString(2, paraCfgUtil.getCODETYPE());
			pstmt.setString(3, paraCfgUtil.getCODENAME());
			pstmt.setString(4, paraCfgUtil.getCODEALIA());
			pstmt.setString(5, paraCfgUtil.getVALIDATEFLAG());
			pstmt.setString(6, paraCfgUtil.getISHOTPARA());
			pstmt.setString(7, paraCfgUtil.getCODEVALUE());
			pstmt.setString(8, paraCfgUtil.getREMARK());
			i = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			i = 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			try {
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 因关闭方式有问题 需要原来的关闭方式进行连接的关闭 mili 2015-3-20 16:34:57
			// if (conn != null) {
			// conn.close();
			// }
		}
		return i;

	}

	// 删除
	public int delete(String id) throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = null;
		int i = 0;
		try {

			String sql = "delete from syjk_ccs_code c where c.code = ?";
			conn = DBConnPool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			i = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			i = 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			try {
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 因关闭方式有问题 需要原来的关闭方式进行连接的关闭 mili 2015-3-20 16:34:57
			// if (conn != null) {
			// conn.close();
			// }
		}
		return i;
	}

	// 修改

	public int updata(ParaCfgUtil paraCfgUtil, String id) throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = null;
		int i = 0;
		try {
			String sql = "update syjk_ccs_code set CODE=?,CODETYPE=?,CODENAME=?,CODEALIA=?,VALIDATEFLAG=?,ISHOTPARA=?,CODEVALUE=?,getREMARK=? where CODE=?";
			conn = DBConnPool.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraCfgUtil.getCODE());
			pstmt.setString(2, paraCfgUtil.getCODETYPE());
			pstmt.setString(3, paraCfgUtil.getCODENAME());
			pstmt.setString(4, paraCfgUtil.getCODEALIA());
			pstmt.setString(5, paraCfgUtil.getVALIDATEFLAG());
			pstmt.setString(6, paraCfgUtil.getISHOTPARA());
			pstmt.setString(7, paraCfgUtil.getCODEVALUE());
			pstmt.setString(8, paraCfgUtil.getREMARK());
			pstmt.setString(9, id);
			i = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			i = 0;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn); // 因关闭方式有问题
																				// 需要原来的关闭方式进行连接的关闭
																				// mili
																				// 2015-3-20
																				// 16:34:57
				// if (conn != null) {
				// conn.close();
				// }
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return i;
	}

}
