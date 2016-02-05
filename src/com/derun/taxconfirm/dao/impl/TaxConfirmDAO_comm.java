package com.derun.taxconfirm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.SqlText;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSXX;

/**
 * @author MILI
 * @time 2014-5-22 15:57:07
 * @描述：投保确认 公共 DAO
 * */
public class TaxConfirmDAO_comm {
	
	/**
	 * @author MILI
	 * @描述:投保确认 时 根据查询码在车船税表中查询出纳税类别欠税
	 * @time 2014-5-22 16:24:35
	 */
	public List<SYJK_CCS_CCSXX> getTaxConditionCode(String taxQueryCode) {
		SYJK_CCS_CCSXX ccsxx = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Connection conn = null;
		List<SYJK_CCS_CCSXX> list_ccsxx = new ArrayList<SYJK_CCS_CCSXX>();
		try {
			StringBuffer sql = new StringBuffer(SqlText.R_02_SYJK_CCS_CCSXX_001);
			conn = DBConnPool.getConnection();
			pre = conn.prepareStatement(sql.toString());
			pre.setString(1, taxQueryCode);
			rs = pre.executeQuery();
			while (rs.next()) {
				ccsxx = new SYJK_CCS_CCSXX();
				ccsxx.setTAXQUERYNO(taxQueryCode);					// 车船税查询码,唯一标示符
				ccsxx.setTAXTERMTYPECODE(rs.getString("TAXTERMTYPECODE"));			// 税种类型代码,Y
				ccsxx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));		// 纳税类型代码,Y
				ccsxx.setTAXREGISTRYNUMBER(rs.getString("TAXREGISTRYNUMBER"));		// 税务登记证号
				ccsxx.setTAXPAYERNAME(rs.getString("TAXPAYERNAME"));// 纳税人名称
				ccsxx.setTAXPAYERIDENTIFICATIONCODE(rs.getString("TAXPAYERIDENTIFICATIONCODE"));	// 纳税人识别号
				ccsxx.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE"));			// 纳税地区代码
				ccsxx.setTAXSTARTDATE(rs.getDate("TAXSTARTDATE"));	// 税款所属始期
				ccsxx.setTAXENDDATE(rs.getDate("TAXENDDATE"));		// 税款所属止期
				ccsxx.setPAYDATE(rs.getDate("PAYDATE"));			// 代收日期,Y
				ccsxx.setTAXUNITTYPECODE(rs.getString("TAXUNITTYPECODE"));			// 计税单位代码
				ccsxx.setUNITRATE(rs.getDouble("UNITRATE"));		// 单位计税金额
				ccsxx.setANNUALTAXAMOUNT(rs.getDouble("ANNUALTAXAMOUNT"));			// 当期年单位税额
				ccsxx.setTAXDEPARTMENTCODE(rs.getString("TAXDEPARTMENTCODE"));		// 开具减免税(完税)凭证的税务机关代码,Y
				ccsxx.setTAXDEPARTMENT(rs.getString("TAXDEPARTMENT"));				// 开具减免税(完税)凭证的税务机关名称,Y
				ccsxx.setTAXDOCUMENTNUMBER(rs.getString("TAXDOCUMENTNUMBER"));		// 完税凭证号码
				ccsxx.setDEDUCTIONDUECODE(rs.getString("DEDUCTIONDUECODE"));		// 减免税原因代码,Y
				ccsxx.setDEDUCTIONDUETYPE(rs.getString("DEDUCTIONDUETYPE"));		// 减免税方案代码,Y
				ccsxx.setDEDUCTIONDUEPROPORTION(rs.getDouble("DEDUCTIONDUEPROPORTION"));	// 减免比例
				ccsxx.setDEDUCTION(rs.getDouble("DEDUCTION"));		// 减免金额
				ccsxx.setDEDUCTIONDOCUMENTNUMBER(rs.getString("DEDUCTIONDOCUMENTNUMBER"));	// 减免税凭证号
				ccsxx.setDEPARTMENT(rs.getString("DEPARTMENT"));
				ccsxx.setDEPARTMENTCODE(rs.getString("DEPARTMENTCODE"));
				ccsxx.setTAXDUE(rs.getDouble("TAXDUE"));			// 当期应纳税额
				ccsxx.setEXCEEDDATE(rs.getDate("EXCEEDDATE"));		// 逾期时间
				ccsxx.setEXCEEDDAYSCOUNT(rs.getInt("EXCEEDDAYSCOUNT"));		// 逾期天数
				ccsxx.setOVERDUE(rs.getDouble("OVERDUE"));			// 滞纳金
				ccsxx.setTOTALAMOUNT(rs.getDouble("TOTALAMOUNT"));	// 合计金额
				ccsxx.setSJCJRQ(rs.getDate("SJCJRQ"));				// 合计金额
				list_ccsxx.add(ccsxx);
			}
		} catch (Exception e) {
			ccsxx = null;
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null)
//					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list_ccsxx;
	}
}
