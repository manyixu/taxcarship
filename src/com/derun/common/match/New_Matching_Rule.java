package com.derun.common.match;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.derun.all.common.SqlDao;
import com.derun.beans.Vehicle_Type;
import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.ExeSQL;
import com.derun.common.db.SqlText;
import com.derun.common.init.CfgLoader;
import com.derun.common.util.DateUtil;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CODE;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX;
import com.derun.model.po.SYJK_CCS_RKMX;

/**
 * @author MILI
 * @time 2014-4-3 14:25:54
 * @描述：车辆匹配规则
 * */
public class New_Matching_Rule {
	// 数据库操作类
	ExeSQL exesql = new ExeSQL();
	/**
	 * @author MILI
	 * @time 2014-4-3 14:25:54
	 * @描述：RKMX 入库明细车辆匹配规则
	 * */
	public Map<String,Object> getMatching_rkmx(Vehicle_Type vt,String tax_date){
		Map<String,Object> map = new HashMap<String,Object>();
		List<SYJK_CCS_RKMX> rkmxL = new ArrayList<SYJK_CCS_RKMX>();						//	查询到RKMX所有的缴税记录
		List<SYJK_CCS_RKMX> _rkmxL = new ArrayList<SYJK_CCS_RKMX>();					//	过滤只要投保时的缴税记录
		String vin = vt.getVIN() == null ? "" : vt.getVIN().trim();						// 	车架号
		String hphm = vt.getLicensePlateNo() == null ? "" : vt.getLicensePlateNo();		// 	号牌号码
		String hpzl = vt.getLicensePlateType() == null ? "" : vt.getLicensePlateType();	// 	号牌种类
		String engineNo = vt.getEngineNo() == null ? "" : vt.getEngineNo();				// 	发动机号
		String motorTypeCode = vt.getMotorTypeCode();									// 	车辆类型
		String vin_EndSix = null ;														//	VIN后6位
		String engineNo_EndSix = null ;	
		// 发动机号后6位
		if(vin.length() > 6){
			vin_EndSix = vt.getVIN().substring(vt.getVIN().length() - 6 );
		}else{ // 如VIN 不够6位  有多少位放多少位  MILI 2014-11-6 
			vin_EndSix = vt.getVIN();
		}
		if(engineNo.length() > 6){
			engineNo_EndSix = vt.getEngineNo().substring(vt.getEngineNo().length() - 6);
		}else{ // 如发动机号 不够6位  有多少位放多少位  MILI 2014-11-6
			engineNo_EndSix = vt.getEngineNo() ;
		}
		String sql_con = SqlText.R_01_RKMX_001 ;
		
		// 1 根据 根据“车牌号码＋号牌种类”匹配车辆信息
//		String sqlhphm_hpzl = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND PAYDATE = ? AND HPHM = ? AND HPZL = ? ORDER BY SJCJRQ DESC" ;
		String sqlhphm_hpzl = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND HPHM = ? AND HPZL = ? ORDER BY SJCJRQ DESC" ;
		// 2 根据“车牌号码＋发动机号后6位”匹配车辆信息
//		String sqlhphm_engineNoEndSix = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND PAYDATE = ? AND HPHM = ? ORDER BY SJCJRQ DESC" ;
		String sqlhphm_engineNoEndSix = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND HPHM = ? ORDER BY SJCJRQ DESC" ;
		// 3 根据“车牌号码＋车架号后6位”匹配车辆信息
//		String sqlhphm_vinEndSix = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND PAYDATE = ? AND HPHM = ? ORDER BY SJCJRQ DESC" ;
		String sqlhphm_vinEndSix = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND HPHM = ? ORDER BY SJCJRQ DESC" ;
		// 4 根据“车架号后6位＋发动机号后6位” 匹配车辆信息
//		String sqlvinEndSix_engineNoEndSix = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND PAYDATE = ?  AND SUBSTR(VIN,-6,6) = ? AND SUBSTR(ENGINENO,-6,6) = ? ORDER BY SJCJRQ DESC" ;
		String sqlvinEndSix_engineNoEndSix = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND SUBSTR(VIN,-6,6) = ? AND SUBSTR(ENGINENO,-6,6) = ? ORDER BY SJCJRQ DESC" ;
		// 5 根据“车架号”匹配车辆信息
//		String sqlVin = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND PAYDATE = ? AND VIN = ? ORDER BY SJCJRQ DESC";
		String sqlVin = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND VIN = ? ORDER BY SJCJRQ DESC";
		// 6 根据“17位车架号”匹配车辆信息
//		String sql_17vin = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND PAYDATE = ? AND VIN = ? ORDER BY SJCJRQ DESC";
		String sql_17vin = "SELECT " + sql_con + " FROM SYJK_CCS_RKMX WHERE REFUSETYPE = 1 AND LOGGEDOUT = '0' AND TAXCONDITIONCODE <> 'R' AND VIN = ? ORDER BY SJCJRQ DESC";
		// 封装批改规则对应的  value 获取规则列表
		Map<String, String> map_code = this.Scheme();
		if (map_code.isEmpty()) {	// 匹配失败
			map.put("RKMX", new ArrayList<SYJK_CCS_RKMX>());
			map.put("CARID", "0");
			return map ;
		}
		String[] CarMatchList = map_code.get("CarMatchList").split(",");
		if (!map_code.containsKey("onLineTime")) {	// 获取 上线时间
			map.put("RKMX", new ArrayList<SYJK_CCS_RKMX>());
	    	map.put("CARID","0");
			return map;
		}
		String[] trailer = {"25", "26", "27", "28", "31", "41", "51"};	//	挂车,41为特种车二挂车，按挂车算
		for (String str : trailer) {
			if (str.equals(motorTypeCode)) {	//	挂车
				if ("".equals(hphm)) {		// 无牌挂车（未上牌）使用车架号进行匹配
					if (!map_code.containsKey("5")) {
						map.put("RKMX", new ArrayList<SYJK_CCS_RKMX>());
						map.put("CARID", "0");
						return map ;
					}
//					rkmxL = getRkmx_list(sqlVin,tax_date,vin);
					rkmxL = getRkmx_list(sqlVin,vin);
					
					// 过滤  只要 投保时的记录
					if(!rkmxL.isEmpty() && rkmxL.size() != 0){
						for(SYJK_CCS_RKMX rkmx : rkmxL){
							if("0".equals(rkmx.getCHANGETYPE())){
								_rkmxL.add(rkmx);
							}
						}
					}
					
					if (_rkmxL.isEmpty() || _rkmxL.size() > Integer.parseInt(map_code.get("5"))) {
						map.put("RKMX", new ArrayList<SYJK_CCS_RKMX>());
						map.put("CARID", "0");
						return map ;
					}
					map.put("RKMX", rkmxL);
					map.put("CARID", "5");
					return map;
				}
		    	//有牌挂车：原有匹配规则顺序上基础上，去除含发动机号的规则。
		    	for (String sequence : CarMatchList) {
		    		if (!map_code.containsKey(sequence)) {
						continue;
					}
		    		if (sequence.equals("2") || sequence.equals("4")) {	//	去除含发动机号的规则
						continue;
					}
		    		if ("1".equals(sequence)) {			// 1 根据 根据“车牌号码＋号牌种类”匹配车辆信息
//						rkmxL = this.getRkmx_list(sqlhphm_hpzl,tax_date,hphm,hpzl);
						rkmxL = this.getRkmx_list(sqlhphm_hpzl,hphm,hpzl);
					}else if ("3".equals(sequence)) {	// 3 根据“车牌号码＋车架号后6位”匹配车辆信息
//						rkmxL = this.getRkmx_list(sqlhphm_vinEndSix,tax_date,hphm);
						rkmxL = this.getRkmx_list(sqlhphm_vinEndSix,hphm);
					}else if ("5".equals(sequence)) {	// 5 根据“车架号”匹配车辆信息
//						rkmxL = this.getRkmx_list(sqlVin,tax_date,vin_EndSix);
						rkmxL = this.getRkmx_list(sqlVin,vin_EndSix);
					}else if ("6".equals(sequence)){	// 6 根据“17位车架号”匹配车辆信息
//						rkmxL = this.getRkmx_list(sql_17vin,tax_date);
						rkmxL = this.getRkmx_list(sql_17vin);
					}else{
						continue ;
					}
		    		
		    		// 过滤  只要 投保时的记录
					if(!rkmxL.isEmpty() && rkmxL.size() != 0){
						for(SYJK_CCS_RKMX rkmx : rkmxL){
							if("0".equals(rkmx.getCHANGETYPE())){
								_rkmxL.add(rkmx);
							}
						}
//						rkmxL = _rkmxL ;		// 过滤只要 投保时的缴税记录
					}
		    		
		    		//如果没有找到或者找到过多，则根据下一条规则匹配；
					if (_rkmxL.isEmpty() || _rkmxL.size() < 1) {
						continue;
					}else if (_rkmxL.size() > Integer.parseInt(map_code.get("V" + map_code.get(sequence)))) {
						continue;
					}else{
						map.put("RKMX", rkmxL);
				    	map.put("CARID",sequence);
				    	break ;
					}
				}
		    	// 如果没有一条匹配到的 返回匹配失败信息
				if(_rkmxL.isEmpty() || _rkmxL.size() < 1){
					map.put("RKMX", new ArrayList<SYJK_CCS_RKMX>());
			    	map.put("CARID","0");
				}
				return map;
			}
		}
		Date frd = DateUtil.getStringDate(vt.getFirstRegisterDate(), "yyyy-MM-dd");
		Date onLineTime = DateUtil.getStringDate(map_code.get("onLineTime"),"yyyy-MM-dd");
		//初登日期在功能校验上线日期之后
		if (frd.after(onLineTime) && !"33".equals(motorTypeCode)) {	
			if (!map_code.containsKey("5")) {
				map.put("RKMX", new ArrayList<SYJK_CCS_RKMX>());
		    	map.put("CARID","0");
				return map;
			}
//			rkmxL = this.getRkmx_list(sqlVin,tax_date,vin);
			rkmxL = this.getRkmx_list(sqlVin,vin);
			
			// 过滤  只要 投保时的记录
			if(!rkmxL.isEmpty() && rkmxL.size() != 0){
				for(SYJK_CCS_RKMX rkmx : rkmxL){
					if("0".equals(rkmx.getCHANGETYPE())){
						_rkmxL.add(rkmx);
					}
				}
//				rkmxL = _rkmxL ;		// 过滤只要 投保时的缴税记录
			}
			
			if (_rkmxL.isEmpty() || _rkmxL.size() > Integer.parseInt(map_code.get("V5"))) {
				map.put("RKMX", rkmxL);
		    	map.put("CARID","0");
				return map;
			}
			map.put("RKMX", rkmxL);
	    	map.put("CARID","5");
			return map;
		}
		//初登日期在功能校验上线日期之前
		for (String sequence : CarMatchList) {
			if (!map_code.containsKey(sequence)) {
				continue;
			}
			//没有发动机号去除含发动机号的规则
			if ((sequence.equals("2") || sequence.equals("4")) && "".equals(engineNo)) {
				continue;
			}
			//没有车牌号 去除含车牌号的规则
			if ((sequence.equals("1") || sequence.equals("2") || sequence.equals("3")) && "".equals(hphm)) {
				continue;
			}
			if ("1".equals(sequence)) {			// 1 根据 根据“车牌号码＋号牌种类”匹配车辆信息
//				rkmxL = this.getRkmx_list(sqlhphm_hpzl,tax_date,hphm,hpzl);
				rkmxL = this.getRkmx_list(sqlhphm_hpzl,hphm,hpzl);
			}else if ("2".equals(sequence)) {	// 2 根据“车牌号码＋发动机号后6位”匹配车辆信息
//				rkmxL = this.getRkmx_list(sqlhphm_engineNoEndSix,tax_date,hphm);
				rkmxL = this.getRkmx_list(sqlhphm_engineNoEndSix,hphm);
				rkmxL = getRkmx_list(rkmxL,sequence,vin_EndSix,engineNo_EndSix);
			}else if ("3".equals(sequence)) {	// 3 根据“车牌号码＋车架号后6位”匹配车辆信息
//				rkmxL = this.getRkmx_list(sqlhphm_vinEndSix,tax_date,hphm);
				rkmxL = this.getRkmx_list(sqlhphm_vinEndSix,hphm);
				rkmxL = getRkmx_list(rkmxL,sequence,vin_EndSix,engineNo_EndSix);
			}else if ("4".equals(sequence)) {	// 4 根据“车架号后6位＋发动机号后6位” 匹配车辆信息
//				rkmxL = this.getRkmx_list(sqlvinEndSix_engineNoEndSix,tax_date,vin_EndSix,engineNo_EndSix);
				rkmxL = this.getRkmx_list(sqlvinEndSix_engineNoEndSix,vin_EndSix,engineNo_EndSix);
//				rkmxL = getRkmx_list(rkmxL,sequence,vin_EndSix,engineNo_EndSix);
			}else if ("5".equals(sequence)) {	// 5 根据“车架号”匹配车辆信息
//				rkmxL = this.getRkmx_list(sqlVin,tax_date,vin);
				rkmxL = this.getRkmx_list(sqlVin,vin);
			}else if ("6".equals(sequence)){	// 6 根据“17位车架号”匹配车辆信息
//				rkmxL = this.getRkmx_list(sql_17vin,tax_date);
				rkmxL = this.getRkmx_list(sql_17vin);
			}else{
				continue ;
			}
			_rkmxL = rkmxL ;
//			_rkmxL = new ArrayList<SYJK_CCS_RKMX>();
			// 过滤  只要 投保时的记录
//			if(!rkmxL.isEmpty() && rkmxL.size() != 0){
//				for(SYJK_CCS_RKMX rkmx : rkmxL){
//					if("0".equals(rkmx.getCHANGETYPE())){
//						_rkmxL.add(rkmx);
//					}
//				}
////				rkmxL = _rkmxL ;		// 过滤只要 投保时的缴税记录
//			}
			
			//如果没有找到或者找到过多，则根据下一条规则匹配；
			if (_rkmxL.isEmpty() || _rkmxL.size() < 1) {
				continue;
			}else if (_rkmxL.size() > Integer.parseInt(map_code.get("V" + map_code.get(sequence)))) {
				continue;
			}else{
				map.put("RKMX", rkmxL);
		    	map.put("CARID",sequence);
		    	break ;
			}
		}
		// 如果没有一条匹配到的 返回匹配失败信息
		if(_rkmxL.isEmpty() || _rkmxL.size() < 1){
			map.put("RKMX", new ArrayList<SYJK_CCS_RKMX>());
	    	map.put("CARID","0");
		}
		return map;
	}
	/**
	 * @author MILI
	 * @time 2014-4-11 11:04:55
	 * @描述：封装批改规则对应的value  获取规则列表
	 * */
	public Map<String, String> Scheme() {
		List<SYJK_CCS_CODE> list_code = CfgLoader.getConfig("IadsysConfig");
		Map<String,String> map_code = new HashMap<String, String>();
		for (SYJK_CCS_CODE code : list_code) {
			if ("CarMatchList".equals(code.getCODE())) {
//					&& "1".equals(code.getREMARK())) {
				map_code.put("CarMatchList", code.getCODEALIA());
			}else if ("CarMatchNO.1".equals(code.getCODE())) {
//					&& "1".equals(code.getREMARK())) {
				map_code.put("1", code.getCODEALIA());
				map_code.put("V1", code.getCODEVALUE());
			}else if ("CarMatchNO.2".equals(code.getCODE())) {
//					&& "1".equals(code.getREMARK())) {
				map_code.put("2", code.getCODEALIA());
				map_code.put("V2", code.getCODEVALUE());
			}else if ("CarMatchNO.3".equals(code.getCODE())) {
//					&& "1".equals(code.getREMARK())) {
				map_code.put("3", code.getCODEALIA());
				map_code.put("V3", code.getCODEVALUE());
			}else if ("CarMatchNO.4".equals(code.getCODE())) {
//					&& "1".equals(code.getREMARK())) {
				map_code.put("4", code.getCODEALIA());
				map_code.put("V4", code.getCODEVALUE());
			}else if ("CarMatchNO.5".equals(code.getCODE())) {
//					&& "1".equals(code.getREMARK())) {
				map_code.put("5", code.getCODEALIA());
				map_code.put("V5", code.getCODEVALUE());
			}else if ("onLineTime".equals(code.getCODE())) {
//					&& "1".equals(code.getREMARK())) {
				map_code.put("onLineTime", code.getCODEALIA());
			}
		}
		return map_code ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-11 11:29:13
	 * @描述：根据条件进行匹配
	 * */
	public List<SYJK_CCS_RKMX> getRkmx_list(String sql,String... args ){
		List<SYJK_CCS_RKMX> rkmxlist = new ArrayList<SYJK_CCS_RKMX>();
		SYJK_CCS_RKMX rkmx = new SYJK_CCS_RKMX();
		Connection con = null ;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {
			con = DBConnPool.getConnection();
			pstmt = con.prepareStatement(sql);
			for(int i = 0 ; i < args.length ; i++){
				pstmt.setString(i + 1, args[i]);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rkmx = new SYJK_CCS_RKMX();
//				rkmx.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));
//				rkmx.setVIN(rs.getString("VIN"));
//				rkmx.setHPHM(rs.getString("HPHM"));
//				rkmx.setHPZL(rs.getString("HPZL"));
//				rkmx.setEngineNo(rs.getString("ENGINENO"));
//				rkmx.setLOGGEDOUT(rs.getString("LOGGEDOUT"));
//				rkmx.setPLATFORMSTATE(rs.getString("PLATFORMSTATE"));
//				rkmx.setCHANGETYPE(rs.getString("CHANGETYPE"));
//				rkmx.setCOUNTTAXTYPE(rs.getString("COUNTTAXTYPE"));
//				rkmx.setRefusetype(rs.getString("REFUSETYPE"));
//				rkmx.setSTATUSDATE(rs.getString("STATUSDATE"));
//				rkmx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));
//				rkmx.setSJCJRQ(rs.getString("SJCJRQ"));
				rkmx.setTSBZ(rs.getString("TSBZ"));
//				rkmx.setPAYDATE(rs.getString("PAYDATE"));
//				rkmx.setTAXDEPARTMENTCODE(rs.getString("TAXDEPARTMENTCODE"));		// 开具完税凭证的税务机关代码
//				rkmx.setTAXDEPARTMENT(rs.getString("TAXDEPARTMENT"));				// 开具完税凭证的税务机关名称
//				rkmx.setDEDUCTIONDOCUMENTNUMBER(rs.getString("DEDUCTIONDOCUMENTNUMBER"));	 //减免税凭证号  VARCHAR2(30)
//				rkmx.setTAXDOCUMENTNUMBER(rs.getString("TAXDOCUMENTNUMBER"));		// 完税凭证号码
				
//				rkmx = new SYJK_CCS_RKMX();
				rkmx.setANNUALTAXAMOUNT(rs.getDouble("ANNUALTAXAMOUNT"));		// 当期年单位税额
				rkmx.setANNUALTAXDUE(rs.getDouble("ANNUALTAXDUE"));				// 本年车船税金额
				rkmx.setCHANGETYPE(rs.getString("CHANGETYPE"));					// 变更类型
				rkmx.setCLLX(rs.getString("CLLX")); 							// 车辆类型
				rkmx.setCOUNTTAXTYPE(rs.getString("COUNTTAXTYPE")); 			// 算税标志
				
				rkmx.setDEDUCTION(rs.getDouble("DEDUCTION")); 								// 减免金额
				rkmx.setDEDUCTIONDEPARTMENT(rs.getString("DEDUCTIONDEPARTMENT")); 			// 开具减免税凭证的税务机关名称
				rkmx.setDEDUCTIONDEPARTMENTCODE(rs.getString("DEDUCTIONDEPARTMENTCODE"));	// 开具减免税凭证的税务机关代码
				rkmx.setDEDUCTIONDOCUMENTNUMBER(rs.getString("DEDUCTIONDOCUMENTNUMBER"));	// 减免税凭证号
				rkmx.setDEDUCTIONDUECODE(rs.getString("DEDUCTIONDUECODE")); 				// 减免税原因代码
				rkmx.setDEDUCTIONDUEPROPORTION(rs.getDouble("DEDUCTIONDUEPROPORTION"));		// 减免比例
				rkmx.setDEDUCTIONDUETYPE(rs.getString("DEDUCTIONDUETYPE"));					// 减免税方案代码
				
				rkmx.setEXCEEDDATE(rs.getString("EXCEEDDATE"));					// 逾期时间
				rkmx.setEXCEEDDAYSCOUNT(rs.getInt("EXCEEDDAYSCOUNT")); 			// 逾期天数
				rkmx.setHPHM(rs.getString("HPHM")); 							// 号牌号码
				rkmx.setHPZL(rs.getString("HPZL")); 							// 号牌种类
				rkmx.setLOGGEDOUT(rs.getString("LOGGEDOUT"));					// 注销状态 0= 未注销 1=注销
				rkmx.setLOGINNAME(rs.getString("LOGINNAME"));					// 操作员
				rkmx.setOVERDUE(rs.getDouble("OVERDUE")); 						// 滞纳金
				rkmx.setPAYCOMPANYCODE(rs.getString("PAYCOMPANYCODE")); 		// 代收公司
				rkmx.setPAYDATE(rs.getString("PAYDATE"));						// 所属年度
				rkmx.setPLATFORMSTATE(rs.getString("PLATFORMSTATE"));			// 平台 0=代收
				rkmx.setRefusetype(rs.getString("REFUSETYPE"));			 		// 拒缴标志
				rkmx.setREVENUECODE(rs.getString("REVENUECODE"));				// 9000 税务机关编码
				rkmx.setSJCJRQ(rs.getString("SJCJRQ").substring(0, 19)); 						// 系统采集日期
				rkmx.setSTATUSDATE(rs.getString("STATUSDATE")); 				// 申报日期
				rkmx.setSUMOVERDUE(rs.getDouble("SUMOVERDUE")); 				// 合计滞纳金
				rkmx.setSUMTAX(rs.getDouble("SUMTAX")); 						// 合计金额
				rkmx.setSUMTAXDEFAULT(rs.getDouble("SUMTAXDEFAULT"));			// 合计欠税金额
				rkmx.setTAXAMOUNT_FLAG("3"); 	
				// 合计金额标志码
				rkmx.setTAXCONDITIONCODE(rs.getString("TAXCONDITIONCODE"));		// 开具减免税凭证的税务机关代码
				rkmx.setTAXDEPARTMENT(rs.getString("TAXDEPARTMENT"));			// 开具完税凭证的税务机关名称
				rkmx.setTAXDEPARTMENTCODE(rs.getString("TAXDEPARTMENTCODE"));	// 开具完税凭证的税务机关代码
				
				rkmx.setTAXCONFIRMNO(rs.getString("TAXCONFIRMNO"));				// 确认码
				rkmx.setTAXDOCUMENTNUMBER(rs.getString("TAXDOCUMENTNUMBER"));	// 完税凭证号码
				rkmx.setTAXDUE(rs.getDouble("TAXDUE"));							// 当期应纳税额
				rkmx.setTAXENDDATE(rs.getString("EXCEEDDATE"));					// 逾期时间
				rkmx.setTAXLOCATIONCODE(rs.getString("TAXLOCATIONCODE")); 		// 纳税地区代码
				rkmx.setTAXPAYERIDENTIFICATIONCODE(rs
						.getString("TAXPAYERIDENTIFICATIONCODE"));				// 纳税人识别号
				rkmx.setTAXPAYERNAME(rs.getString("TAXPAYERNAME"));				// 纳税人名称
				rkmx.setTAXPRINTNO(rs.getString("TAXPRINTNO"));					// 打印码
				rkmx.setTAXQUERYNO(rs.getString("TAXQUERYNO"));					// 车船税交易码
				rkmx.setTAXREGISTRYNUMBER(rs.getString("TAXREGISTRYNUMBER"));	// 税务登记证号
				rkmx.setTAXSTARTDATE(rs.getString("TAXSTARTDATE"));				// 税款所属始期
				rkmx.setTAXENDDATE(rs.getString("TAXENDDATE"));					// 税款所属止期
				rkmx.setTAXUNITTYPECODE(rs.getString("TAXUNITTYPECODE"));		// 计税单位代码
				rkmx.setTOTALAMOUNT(rs.getDouble("TOTALAMOUNT"));				// 合计金额
				rkmx.setUNITRATE(rs.getDouble("UNITRATE")); 					// 单位计税金额
				rkmx.setVIN(rs.getString("VIN"));								// Vin a
				rkmx.setFIRSTREGISTERDATE(rs.getString("FIRSTREGISTERDATE"));
				rkmx.setSPECIALCARTYPE(rs.getString("SPECIALCARTYPE"));
				rkmx.setInsureStartDate(rs.getDate("INSURESTARTDATE"));
				rkmx.setInsureEndDate(rs.getDate("INSUREENDDATE"));
				rkmx.setCARSERIALNO(rs.getString("CARSERIALNO"));
				rkmx.setEngineNo(rs.getString("ENGINENO"));						//发动机号
				
				rkmx.setMOTORUSAGETYPECODE(rs.getString("MOTORUSAGETYPECODE")); 	// 使用性质
				rkmx.setMODEL(rs.getString("MODEL")); 				// 车辆型号
				rkmx.setVEHICLETYPE(rs.getString("VEHICLETYPE")) ; 		// 交管车辆类型
				rkmx.setRATEDPASSENGERCAPACITY(rs.getDouble("RATEDPASSENGERCAPACITY"));// 核定载客数
				rkmx.setTONNAGE(rs.getDouble("TONNAGE"));				// 核定载质量
				rkmx.setWHOLEWEIGHT(rs.getDouble("WHOLEWEIGHT"));			// 整备质量
				rkmx.setDISPLACEMENT(rs.getDouble("DISPLACEMENT"));			// 排量
				rkmx.setPOWER(rs.getDouble("POWER"));					// 功率
				rkmx.setFUELTYPE(rs.getString("FUELTYPE")); 			// 源种类
				
				rkmxlist.add(rkmx);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("封装 入库明细对象出错 .... " + e);
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rkmxlist ;
	}
	/**
	 * @author MILI
	 * @time 2014-5-28 10:05:45
	 * @描述：-----------------------
	 * */
	public List<SYJK_CCS_RKMX> getRkmx_list(List<SYJK_CCS_RKMX> list_rkmx,String sequence,String vin,String engineNo){
		List<SYJK_CCS_RKMX> rkmxList = new ArrayList<SYJK_CCS_RKMX>();
		String _vin = null ;
		String _engineNo = null ;
		if("2".equals(sequence)){
			for (SYJK_CCS_RKMX rkmx : list_rkmx) {
				_engineNo = rkmx.getEngineNo() == null ? "" : rkmx.getEngineNo().trim();
				if (_engineNo.length() > 6) {
					_engineNo = _engineNo.substring(_engineNo.length() - 6);
				}
				if (_engineNo.equals(engineNo)) {
					rkmxList.add(rkmx);
				}
			}
		}else if("3".equals(sequence)){
			for (SYJK_CCS_RKMX rkmx : list_rkmx) {
				_vin = rkmx.getVIN() == null ? "" : rkmx.getVIN().trim();
				if (_vin.length() > 6) {
					_vin = _vin.substring(_vin.length() - 6);
				}
				if (_vin.equals(vin)) {
					rkmxList.add(rkmx);
				}
			}
		}else if("4".equals(sequence)){
			for (SYJK_CCS_RKMX rkmx : list_rkmx) {
				_vin = rkmx.getVIN() == null ? "" : rkmx.getVIN().trim();
				_engineNo = rkmx.getEngineNo() == null ? "" : rkmx.getEngineNo().trim();
				if (_vin.length() > 6 && _engineNo.length() > 6) {
					_vin = _vin.substring(_vin.length() - 6);
					_engineNo = _engineNo.substring(_engineNo.length() - 6);
				}
				if (_vin.equals(vin) && _engineNo.equals(engineNo)) {
					rkmxList.add(rkmx);
				}
			}
		}else{
			rkmxList = list_rkmx ;
		}
		return rkmxList;
	}
	//-------------------------------------------------------------------------------------------------//
	/**
	 * @author MILI
	 * @time 2014-4-9 16:44:32
	 * @描述：WTMD 问题名单车辆匹配规则
	 * 按照传入的的数据库表名及字段值，按问题名单匹配规则返回查询出的相应的数据集合
	 * 问题名单车辆匹配规则：若车架号为17位，则以17位车架号作为优先匹配，若匹配唯一，
	 * 则确认为同一辆车，否则，匹配失败；
	 * 若车架号非17位，
	 * 则以号牌号码+号牌种类+车架号后六位+发动机号后六位进行匹配；若匹配唯一，
	 * 则确认为同一辆车，否则，匹配失败。
	 * */
	public List<SYJK_CCS_QGCLWTMDXX> getMatching_wtmd(Vehicle_Type vt){
		List<SYJK_CCS_QGCLWTMDXX> wtmd_list = new ArrayList<SYJK_CCS_QGCLWTMDXX>() ;
		String QNameListFlag = CfgLoader.getConfigValue("SysSwitch", "QNameListFlag");
		if("1".equals(QNameListFlag)){ // 0=打开;1=关闭 MILI 增加问题名单开关 2014-11-13 10:33:29
			return wtmd_list ;
		}else{
			// sql
			StringBuffer querySql = new StringBuffer("SELECT * FROM SYJK_CCS_QGCLWTMDXX WHERE " ) ;
			// VIN
			String vin = vt.getVIN() == null ? "" : vt.getVIN().trim();
			// 发动机号
			String engineNo = vt.getEngineNo() == null ? "" : vt.getEngineNo().trim() ;
			// 号牌号码
			String hphm = vt.getLicensePlateNo() == null ? "" : vt.getLicensePlateNo().trim() ;
			// 号牌种类
			String hpzl = vt.getLicensePlateType() == null ? "" : vt.getLicensePlateType().trim();
			// 条件字段
			String tableCondition = " AND VALIDATEFLAG = '1' ORDER BY SJCJRQ DESC";
			// 若车架号为17位，则以17位车架号作为优先匹配，若匹配唯一
			if(vin.length() >= 17 ){
				querySql.append("CLSBDM = '" + vin + "'");
				querySql.append(tableCondition);
				wtmd_list = this.getWTMDList(querySql.toString());
				return wtmd_list ;
				// MILI 2014-11-6 新加一个匹配规则
				// 若车架号不够17位 则以号牌号码+号牌种类+车架号后六位+发动机号后六位进行匹配
			}else if(vin.length() < 17 && vin.length() >= 6 && engineNo.length() >= 6){
				// 车架号后六位
				vin = vin.substring(vin.length() - 6); 
				// 发动机号后六
				engineNo = engineNo.substring(engineNo.length() - 6);
				querySql.append(" SUBSTR(CLSBDM,-6,6) = '" + vin);
				querySql.append("' AND HPHM = '" + hphm);
				querySql.append("' AND HPZL = '" + hpzl);
				querySql.append("' AND SUBSTR(FDJH,-6,6) = '" + engineNo + "'");
				querySql.append(tableCondition);
				wtmd_list = this.getWTMDList(querySql.toString());
				return wtmd_list ;
				// MILI 2014-11-6 新加一个匹配规则
				// 若车架号不够17位 则以号牌号码+号牌种类+车架号后+发动机号进行匹配
			}else{
				querySql.append(" CLSBDM = '" + vin);
				querySql.append("' AND HPHM = '" + hphm);
				querySql.append("' AND HPZL = '" + hpzl);
				querySql.append("' AND FDJH = '" + engineNo + "'");
				querySql.append(tableCondition);
				wtmd_list = this.getWTMDList(querySql.toString());
				return wtmd_list ;
			}
		}
	}
	/**
	 * @author MILI
	 * @time 2014-4-11 16:12:44
	 * @描述:封装结果 SYJK_CCS_QGCLWTMDXX
	 * */
	public List<SYJK_CCS_QGCLWTMDXX> getWTMDList(String sql,String... args){
		List<SYJK_CCS_QGCLWTMDXX> wtmd_list = new ArrayList<SYJK_CCS_QGCLWTMDXX>() ;
		Connection con = null ;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {
			con = DBConnPool.getConnection();
			pstmt = con.prepareStatement(sql);
			for(int i = 0 ; i < args.length ; i++){
				pstmt.setString(i + 1, args[i]);
			}
			rs = pstmt.executeQuery();
			wtmd_list = this.getDataList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(con != null){
//					con.close();
//				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return wtmd_list ;
	}
	/**
	 * @author MILI
	 * @time 2014-4-11 16:35:54
	 * @描述：封装数据结果
	 * */
	public List<SYJK_CCS_QGCLWTMDXX> getDataList(ResultSet rs) {
		List<SYJK_CCS_QGCLWTMDXX> list_WTMD = new ArrayList<SYJK_CCS_QGCLWTMDXX>();
		SYJK_CCS_QGCLWTMDXX qgclwtmdxx = null;
		try {
			while (rs.next()) {
				qgclwtmdxx = new SYJK_CCS_QGCLWTMDXX();
				qgclwtmdxx.setCLCSDJRQ(rs.getDate("CLCSDJRQ"));
				qgclwtmdxx.setCLSBDM(rs.getString("CLSBDM"));
				qgclwtmdxx.setCLXH(rs.getString("CLXH"));
				qgclwtmdxx.setCLZL(rs.getString("CLZL"));
				qgclwtmdxx.setCZDZ(rs.getString("CZDZ"));
				qgclwtmdxx.setCZLXDH(rs.getString("CZLXDH"));
				qgclwtmdxx.setCZMC(rs.getString("CZMC"));
				qgclwtmdxx.setCZZJHM(rs.getString("CZZJHM"));
				qgclwtmdxx.setCZZJLX(rs.getString("CZZJLX"));
				qgclwtmdxx.setDQNDWSE(rs.getDouble("DQNDWSE"));
				qgclwtmdxx.setDQYNSE(rs.getDouble("DQYNSE"));
				qgclwtmdxx.setDWJSJE(rs.getDouble("DWJSJE"));
				qgclwtmdxx.setFDJH(rs.getString("FDJH"));
				qgclwtmdxx.setGL(rs.getDouble("GL"));
				qgclwtmdxx.setHDZKS(rs.getDouble("HDZKS"));
				qgclwtmdxx.setHDZZL(rs.getDouble("HDZZL"));
				qgclwtmdxx.setHEJE(rs.getDouble("HEJE"));
				qgclwtmdxx.setHPHM(rs.getString("HPHM"));
				qgclwtmdxx.setHPZL(rs.getString("HPZL"));
//				qgclwtmdxx.setID(rs.getString("ID"));
				qgclwtmdxx.setJGCLLX(rs.getString("JGCLLX"));
				qgclwtmdxx.setJMBL(rs.getDouble("JMBL"));
				qgclwtmdxx.setJMJE(rs.getDouble("JMJE"));
				qgclwtmdxx.setJMSFA(rs.getString("JMSFA"));
				qgclwtmdxx.setJMSPZH(rs.getString("JMSPZH"));
				qgclwtmdxx.setJMSYY(rs.getString("JMSYY"));
				qgclwtmdxx.setJSDW(rs.getString("JSDW"));
				qgclwtmdxx.setKJWSPZSWJGDM(rs.getString("KJWSPZSWJGDM"));
				qgclwtmdxx.setKJWSPZSWJGMC(rs.getString("KJWSPZSWJGMC"));
				qgclwtmdxx.setLOGINNAME(rs.getString("LOGINNAME"));
				qgclwtmdxx.setND(rs.getString("ND"));
				qgclwtmdxx.setPL(rs.getDouble("PL"));
				qgclwtmdxx.setREVENUECODE(rs.getString("REVENUECODE"));
				qgclwtmdxx.setRLZL(rs.getString("RLZL"));
				qgclwtmdxx.setSJCJFS(rs.getString("SJCJFS"));
				qgclwtmdxx.setSJCJRQ(rs.getDate("SJCJRQ"));
				qgclwtmdxx.setSKSSSQ(rs.getDate("SKSSSQ"));
				qgclwtmdxx.setSKSSZQ(rs.getDate("SKSSZQ"));
				qgclwtmdxx.setSWJGDM(rs.getString("SWJGDM"));
				qgclwtmdxx.setSWJGJTGJFL(rs.getString("SWJGJTGJFL"));
				qgclwtmdxx.setSWJGMC(rs.getString("SWJGMC"));
				qgclwtmdxx.setSYXZ(rs.getString("SYXZ"));
				qgclwtmdxx.setWSPCLBZ(rs.getString("WSPCLBZ"));
				qgclwtmdxx.setWSPZHM(rs.getString("WSPZHM"));
				qgclwtmdxx.setYCYYDM(rs.getString("YCYYDM"));
				qgclwtmdxx.setYQSJ(rs.getDate("YQSJ"));
				qgclwtmdxx.setYQTS(rs.getDouble("YQTS"));
				qgclwtmdxx.setZBZL(rs.getDouble("ZBZL"));
				qgclwtmdxx.setZCCMC(rs.getString("ZCCMC"));
				qgclwtmdxx.setZNJ(rs.getDouble("ZNJ"));
				qgclwtmdxx.setOVERDUEPAYMENT(rs.getString("OVERDUEPAYMENT"));
				qgclwtmdxx.setCARSERIALNO(rs.getString("CARSERIALNO"));
				list_WTMD.add(qgclwtmdxx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("封装 问题名单对象出错 .... " + e);
		}
		return list_WTMD;
	}
	/**
	 * @author MILI
	 * @time 2014-6-12 17:14:12
	 * @描述：匹配本地车辆信息
	 * @条件： 车架号、发动机号、车牌号码、号牌种类
	 * */
	public Vehicle_Type getVehicle_Type(Vehicle_Type vt){
		Vehicle_Type vv = new Vehicle_Type();
		String vin = vt.getVIN() == null ? "" : vt.getVIN().trim();						// 	车架号
		String hphm = vt.getLicensePlateNo() == null ? "" : vt.getLicensePlateNo();		// 	号牌号码
		String hpzl = vt.getLicensePlateType() == null ? "" : vt.getLicensePlateType();	// 	号牌种类
		String engineNo = vt.getEngineNo() == null ? "" : vt.getEngineNo();				// 	发动机号
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(SqlText.R_01_CARINFO_001);
		sbf.append(" WHERE C.VIN = '" + vin + "'");
		sbf.append(" AND C.EngineNo = '" + engineNo + "'");
		sbf.append(" AND C.LicensePlateNo = '" + hphm + "'");
		sbf.append(" AND C.LicensePlateType = '" + hpzl + "'");
		String sql = sbf.toString();
		sql = SqlDao.off_NUll(sql.toString());
		Connection con = null ;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {
			con = DBConnPool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				vv.setDisplacement(rs.getDouble("Displacement"));
				vv.setEngineNo(rs.getString("EngineNo"));
				vv.setFirstRegisterDate(rs.getString("FirstRegisterDate"));
				vv.setFuelType(rs.getString("FuelType"));
				vv.setLicensePlateNo(rs.getString("LicensePlateNo"));
				vv.setLicensePlateType(rs.getString("LicensePlateType"));
				vv.setMadeFactory(rs.getString("MadeFactory"));
				vv.setModel(rs.getString("Model"));
				vv.setMotorTypeCode(rs.getString("MotorTypeCode"));
				vv.setMotorUsageTypeCode(rs.getString("MotorUsageTypeCode"));
				vv.setNoLicenseFlag(rs.getString("NoLicenseFlag"));
				vv.setPower(rs.getDouble("Power"));
				vv.setRatedPassengerCapacity(rs.getInt("RatedPassengerCapacity"));
				vv.setSpecialCarType(rs.getString("SpecialCarType"));
				vv.setTonnage(rs.getDouble("Tonnage"));
				vv.setVehicleType(rs.getString("VehicleType"));
				vv.setVIN(rs.getString("VIN"));
				vv.setWholeWeight(rs.getDouble("wholeWeight"));
			}else{
				vv = vt ;
			}
		} catch (SQLException e) {
			vv = vt ;
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//			if(con != null){
//				con.close();
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vv ;
	}
	
	
	/**
	 * 测试 mian
	 * */
	public static void main(String[] args){
		
	}
}
