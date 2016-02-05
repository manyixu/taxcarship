package com.derun.common.db;

import org.apache.log4j.Logger;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-6
 *
 * 说明 系统中所有SQL定义
 * 1、C=创建（增） R=读取（查）U=更新（改）D=删除（删）
 * 2、00=通用、01=投保查询、02=投保确认、03=变更查询、04=变更确认、05=对账、06=问题车辆名单下载、07=申报日期上传、08=纳税信息查询、09=退保查询
 * 3、表名（CODE、RKMX、QS、CXRC<查询入参>）
 * 4、编号
 * @version
 */
public class SqlText {
	
	static Logger log = Logger.getLogger(SqlText.class.getName());
	
	/**查询系统配置开关、参数、代码等*/
	public static String R_00_CODE_001 = "select sct.codetypename, "
			+ "sct.codetype, sc.code, sc.codename, sc.codevalue, sc.codealia, sc.validateflag, sc.ishotpara ,sc.remark "
			+ "from syjk_ccs_codetype sct, syjk_ccs_code sc "
			+ "where sct.codetype = sc.codetype "
			+ "and sc.validateflag = '0' ";
	
	/**投保查询 车辆匹配规则*/
	public static String R_01_RKMX_001 = "TAXQUERYNO,TAXCONFIRMNO,TAXPRINTNO,VIN,HPHM,HPZL,CLLX,MOTORUSAGETYPECODE,MODEL,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,"
									+ "WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,"
									+ "TAXPAYERIDENTIFICATIONCODE ,PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,"
									+ "UNITRATE,ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,DEDUCTIONDEPARTMENTCODE,"
									+ "DEDUCTIONDEPARTMENT,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,"
									+ "TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,REVENUECODE,SJCJRQ,PAYDATE,"
									+ "TAXAMOUNT_FLAG ,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX ,LOGGEDOUT,PLATFORMSTATE,CHANGETYPE,"
									+ "COUNTTAXTYPE,REFUSETYPE,STATUSDATE,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARSERIALNO,ENGINENO,INSURESTARTDATE,"
									+ "INSUREENDDATE,CARMATCHID";
	
	/**批改查询时 判断确认码是否有效  mili*/
	public static String R_03_RKMX_001 = "SELECT TAXCONDITIONCODE,SJCJRQ,TAXQUERYNO,ANNUALTAXDUE,SUMTAXDEFAULT," +
			"SUMTAX,SUMOVERDUE,SPECIALCARTYPE,FIRSTREGISTERDATE FROM SYJK_CCS_RKMX T WHERE LOGGEDOUT = '0' " +
			"AND T.TAXCONFIRMNO = ? ORDER BY SJCJRQ DESC" ;
//			"AND CHANGETYPE = '0' AND T.TAXCONFIRMNO = ? ORDER BY SJCJRQ DESC" ;
	
	/**查询核定库  mili*/
	public static String R_01_CARINFO_001 = "SELECT CARSERIALNO,VIN,ENGINENO,LICENSEPLATENO,LICENSEPLATETYPE,MOTORTYPECODE,MADEFACTORY," +
			"MODEL,FIRSTREGISTERDATE,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,WHOLEWEIGHT,DISPLACEMENT,VEHICLEOWNERNAME," +	
			"CREDENTIALNO,VALIDATWFLAG,APPROVEDFLAG,SJCJRQ FROM SYJK_CCS_CARINFO C " ;
	
	/**投保确认时 插入核定库  mili*/
	public static String C_02_CARINFO_001 = "INSERT INTO SYJK_CCS_CARINFO (CARSERIALNO,VIN,ENGINENO,LICENSEPLATENO,LICENSEPLATETYPE,MOTORTYPECODE,MADEFACTORY," +
			"MODEL,FIRSTREGISTERDATE,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,WHOLEWEIGHT,DISPLACEMENT,VEHICLEOWNERNAME," +	
			"CREDENTIALNO,VALIDATWFLAG,APPROVEDFLAG,SJCJRQ) VALUES ( " ;
	
	/**批改确认时 更新核定库  mili*/
	public static String U_04_CARINFO_001 = "UPDATE SYJK_CCS_CARINFO C SET VIN=?,ENGINENO=?,LICENSEPLATENO=?,LICENSEPLATETYPE=?,MOTORTYPECODE=?,MADEFACTORY=?,MODEL=?," +
			"FIRSTREGISTERDATE=TO_DATE(?,'yyyy-MM-dd'),VEHICLETYPE=?,RATEDPASSENGERCAPACITY=?,TONNAGE=?,WHOLEWEIGHT=?,DISPLACEMENT=?" ;
	
	/**批改查询时 根据确认码  读取入库明细  mili*/
	public static String R_03_RKMX_002 = "SELECT ANNUALTAXAMOUNT,ANNUALTAXDUE,CHANGETYPE,CLLX,MOTORUSAGETYPECODE,MODEL,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,"
						+ "WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,COUNTTAXTYPE,DEDUCTION,DEDUCTIONDEPARTMENT," +
			"DEDUCTIONDEPARTMENTCODE,DEDUCTIONDOCUMENTNUMBER,DEDUCTIONDUECODE,DEDUCTIONDUEPROPORTION,DEDUCTIONDUETYPE,EXCEEDDATE,EXCEEDDAYSCOUNT," +
			"HPHM,HPZL,LOGGEDOUT,LOGINNAME,OVERDUE,PAYCOMPANYCODE,PAYDATE,PLATFORMSTATE,REFUSETYPE,REVENUECODE,SJCJRQ,STATUSDATE,SUMOVERDUE," +
			"SUMTAX,SUMTAXDEFAULT,TAXCONDITIONCODE,TAXCONFIRMNO,TAXDEPARTMENT,TAXDEPARTMENTCODE,TAXDOCUMENTNUMBER,TAXDUE,EXCEEDDATE,TAXLOCATIONCODE," +
			"TAXPAYERIDENTIFICATIONCODE,TAXPAYERNAME,TAXPRINTNO,TAXQUERYNO,TAXREGISTRYNUMBER,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,TOTALAMOUNT," +
			"UNITRATE,VIN,FIRSTREGISTERDATE,SPECIALCARTYPE,INSURESTARTDATE,INSUREENDDATE,CARSERIALNO,ENGINENO,TSBZ FROM  SYJK_CCS_RKMX Q WHERE Q.LOGGEDOUT='0' AND Q.TAXCONFIRMNO = ? ORDER BY Q.SJCJRQ DESC";
	
	/**批改查询时 根据问题序列号 读取问题名单信息 mili*/
	public static String R_03_QGCLWTMDXX_001 = "SELECT CZMC ,CZZJHM , CZZJLX , CZDZ , CZLXDH, YCYYDM, CLSBDM , HPHM , HPZL , CLZL , FDJH ," +
			"ZCCMC ,SYXZ , WSPCLBZ, CLXH ,CLCSDJRQ ,JGCLLX ,HDZKS , HDZZL,ZBZL, PL ,GL , RLZL, SKSSSQ , SKSSZQ ,KJWSPZSWJGDM , KJWSPZSWJGMC , WSPZHM ," +
	  		"SWJGJTGJFL, JSDW , DWJSJE ,DQNDWSE , JMSYY , JMSFA, JMBL , JMJE , JMSPZH , SWJGDM ,SWJGMC , DQYNSE , YQSJ , YQTS , ZNJ, HEJE ,LOGINNAME ," +
	  		"REVENUECODE ,SJCJRQ,SJCJFS,ND,OVERDUEPAYMENT,CARSERIALNO FROM SYJK_CCS_QGCLWTMDXX Q where Q.CARSERIALNO = ? ORDER BY Q.SKSSZQ DESC" ;
	
	/**批改查询时 插入数据库 CCSBGCXRCJB sql mili*/
	public static String C_03_CCSBGCXRCJB_001 = "INSERT INTO SYJK_CCS_CCSBGCXRCJB(TAXQUERYNO,TAXCONFIRMNO,CHANGETYPE,LICENSEPLATENO,LICENSEPLATETYPE," 
						+ "MOTORTYPECODE,ENGINENO,VIN,MADEFACTORY,MOTORUSAGETYPECODE,NOLICENSEFLAG,MODEL,FIRSTREGISTERDATE,"
						+ "VEHICLETYPE, RATEDPASSENGERCAPACITY,TONNAGE,WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,VEHICLEOWNERNAME,"
						+ "CERTICODE,CREDENTIALCODE,ADDRESS,PHONENO,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,"
						+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,SPECIALCARTYPE,INSURESTARTDATE,INSUREENDDATE,CARMATCHID"
						+ ")VALUES(";
	
	/**退保短期在投保时 查询变更查询入参表 上次退短期原因*/
	public static String R_03_SYJK_CCS_CCSBGCXRCJB_001 = "SELECT TAXQUERYNO,TAXCONFIRMNO,CHANGETYPE,LICENSEPLATENO,LICENSEPLATETYPE," 
		+ "MOTORTYPECODE,ENGINENO,VIN,MADEFACTORY,MOTORUSAGETYPECODE,NOLICENSEFLAG,MODEL,FIRSTREGISTERDATE,"
		+ "VEHICLETYPE, RATEDPASSENGERCAPACITY,TONNAGE,WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,VEHICLEOWNERNAME,"
		+ "CERTICODE,CREDENTIALCODE,ADDRESS,PHONENO,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,"
		+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,SPECIALCARTYPE,INSURESTARTDATE,INSUREENDDATE,CARMATCHID"
		+ " FROM SYJK_CCS_CCSBGCXRCJB WHERE TAXCONFIRMNO = ?";
	
	/**批改查询时 插入数据库 CCSBGCXRCJB sql mili*/
	public static String C_03_CCSBGCXCCJB_001 = "INSERT INTO SYJK_CCS_CCSBGCXCCJB(TAXQUERYNO,CALCTAXFLAG,TAXAMOUNT_FL,ANNUALTAXDUE,SUMTAXDEFAULT,"
						+ "SUMOVERDUE,SUMTAX,RETURNCODE,TAXCONFIRMNO,CHANGESUMTAX,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,VIN,"
						+ "HPHM,HPZL,CLLX,MOTORUSAGETYPECODE,MODEL,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,"
						+ "WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,MOTORTYPECODE,MADEFACTORY,NOLICENSEFLAG,ISINSERT,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARSERIALNO,ENGINENO,INSURESTARTDATE,"
						+ "INSUREENDDATE,CARMATCHID)VALUES(";
	
	/**批改查询时 插入数据库 CCSBGXX sql mili*/
	public static String C_03_CCSBGXX_001 = "INSERT INTO SYJK_CCS_CCSBGXX(TAXQUERYNO,TAXTERMTYPECODE,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,TAXPAYERIDENTIFICATIONCODE,"
						+ "TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,UNITRATE,ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,"
						+ "DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,DEPARTMENT,DEPARTMENTCODE,TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,"
						+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,PAYCOMPANYCODE,PAYDATE,PARATYPE,CARSERIALNO,INSURESTARTDATE,INSUREENDDATE) "
						+ "VALUES(";
	
	/**批改查询时 插入数据库 CCSBGXX_QS sql mili*/
	public static String C_03_CCSBGXX_qs_001 = "INSERT INTO SYJK_CCS_CCSBGXX(TAXQUERYNO,TAXTERMTYPECODE, TAXCONDITIONCODE, TAXREGISTRYNUMBER,TAXPAYERNAME,"
						+ "TAXPAYERIDENTIFICATIONCODE, TAXLOCATIONCODE,TAXSTARTDATE,  TAXENDDATE, TAXUNITTYPECODE, UNITRATE, ANNUALTAXAMOUNT, TAXDEPARTMENTCODE, TAXDEPARTMENT,"
						+ "TAXDOCUMENTNUMBER, DEDUCTIONDUECODE, DEDUCTIONDUETYPE, DEDUCTIONDUEPROPORTION, DEDUCTION, DEDUCTIONDOCUMENTNUMBER,DEPARTMENT,DEPARTMENTCODE,TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,"
						+ "OVERDUE, TOTALAMOUNT,LOGINNAME, REVENUECODE, SJCJRQ,SJCJFS, PAYCOMPANYCODE,PAYDATE,PARATYPE,INSURESTARTDATE,TAXESFLAG,INSUREENDDATE,CARSERIALNO)VALUES(";
	
	/**投保查询时 插入数据库 CCSCXCCJBXX sql mili*/
	public static String C_01_CCSCXCCJBXX_001 = "INSERT INTO SYJK_CCS_CCSCXCCJBXX"
						+ "(TAXQUERYNO,CALCTAXFLAG,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,RETURNCODE,"
						+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,VIN,HPHM,HPZL,CLLX,MOTORUSAGETYPECODE,MODEL,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,"
						+ "WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,MOTORTYPECODE,MADEFACTORY,NOLICENSEFLAG,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,"
						+ "CARSERIALNO,ENGINENO,CARMATCHID,INSURESTARTDATE,INSUREENDDATE,ISINSERT )VALUES('";
	
	/**投保查询时 插入数据库 CCSCXRCJBXX sql mili*/
	public static String C_01_CCSCXRCJBXX_001 = "INSERT INTO SYJK_CCS_CCSCXRCJBXX (TAXQUERYNO,LICENSEPLATENO,LICENSEPLATETYPE,MOTORTYPECODE,ENGINENO,"
						+ "VIN,MADEFACTORY,MOTORUSAGETYPECODE,NOLICENSEFLAG,MODEL,FIRSTREGISTERDATE,VEHICLETYPE,RATEDPASSENGERCAPACITY," 
						+ "TONNAGE,WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,"
						+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,SPECIALCARTYPE,QUERYSEQUENCETTIME,INSURESTARTDATE,INSUREENDDATE,CARMATCHID) VALUES ('" ;
	
	/**投保查询时 插入数据库 CCSXX sql mili*/
	public static String C_01_CCSXX_001 = "INSERT INTO SYJK_CCS_CCSXX(TAXQUERYNO,TAXTERMTYPECODE,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,"
						+ "TAXPAYERIDENTIFICATIONCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,UNITRATE," 
						+ "ANNUALTAXAMOUNT,PAYDATE,PAYCOMPANYCODE,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,"
						+ "DEDUCTION,DEDUCTIONDOCUMENTNUMBER,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,DEPARTMENT,DEPARTMENTCODE,"
						+ "TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,PARATYPE," 
						+ "INSURESTARTDATE,INSUREENDDATE,CARMATCHID,TAXESFLAG)VALUES('";
	
	/**批改确认时 查询数据库 CCSBGCXCCJB sql mili*/
	public static String R_04_CCSBGCXCCJB_001 = "SELECT ANNUALTAXDUE,SUMOVERDUE,SUMTAX,SUMTAXDEFAULT,SJCJRQ,CLLX,MOTORUSAGETYPECODE,MODEL,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,"
						+ "WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,VIN,HPHM,HPZL,ENGINENO,CARSERIALNO,"
						+ "TAXAMOUNT_FL,TAXCONFIRMNO,CHANGESUMTAX,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARMATCHID,NOLICENSEFLAG,MADEFACTORY,MOTORTYPECODE,ISINSERT FROM SYJK_CCS_CCSBGCXCCJB BCC WHERE BCC.TAXQUERYNO = ?";
	/**批改确认时 查询数据库 CCSBGXX sql mili*/
	public static String R_04_CCSBGXX_001 = "SELECT TAXQUERYNO,TAXTERMTYPECODE,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,TAXPAYERIDENTIFICATIONCODE,"
						+ "TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,PAYDATE,TAXUNITTYPECODE,UNITRATE,"
						+ "ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,"
						+ "DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,DEPARTMENT,DEPARTMENTCODE,TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,"
						+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,PAYCOMPANYCODE FROM SYJK_CCS_CCSBGXX WHERE PARATYPE = '1' AND TAXESFLAG IS NULL AND TAXQUERYNO = ? ORDER BY SJCJRQ";
	
	/**批改确认时 查询数据库 RKMX sql mili*/
	public static String R_04_RKMX_001 = "SELECT ANNUALTAXAMOUNT,ANNUALTAXDUE,CHANGETYPE,CLLX,COUNTTAXTYPE,DEDUCTION,DEDUCTIONDEPARTMENT,DEDUCTIONDEPARTMENTCODE,"
						+ "DEDUCTIONDOCUMENTNUMBER,DEDUCTIONDUECODE,DEDUCTIONDUEPROPORTION,DEDUCTIONDUETYPE,EXCEEDDATE,EXCEEDDAYSCOUNT,HPHM,HPZL,LOGGEDOUT,LOGINNAME,"
						+ "OVERDUE,PAYCOMPANYCODE,PLATFORMSTATE,REFUSETYPE,REVENUECODE,SJCJRQ,STATUSDATE,SUMOVERDUE,SUMTAX,SUMTAXDEFAULT,TAXCONDITIONCODE,TAXCONFIRMNO,"
						+ "TAXDEPARTMENT,TAXDEPARTMENTCODE,TAXDOCUMENTNUMBER,TAXDUE,EXCEEDDATE,TAXLOCATIONCODE,TAXPAYERIDENTIFICATIONCODE,TAXPAYERNAME,TAXPRINTNO,TAXQUERYNO,"
						+ "TAXREGISTRYNUMBER,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,TOTALAMOUNT,UNITRATE,VIN,FIRSTREGISTERDATE,SPECIALCARTYPE,INSURESTARTDATE,INSUREENDDATE" + " FROM SYJK_CCS_RKMX C WHERE REFUSETYPE=1 AND LOGGEDOUT='0' AND PAYDATE=TO_CHAR(SYSDATE,'YYYY') AND TAXCONDITIONCODE = 'P' AND CHANGETYPE = '0' AND C.TAXQUERYNO = ? ORDER BY C.SJCJRQ DESC" ;
	
	
	/** 批改确认时 查询数据库 SYJK_CCS_CCSBGCXCCJB 保存轨迹信息时需要  sql 郑艳英*/
	public static String R_04_CCSBGCXCCJB_002 ="SELECT T.*, T.ROWID FROM SYJK_CCS_CCSBGCXCCJB T WHERE T.TAXQUERYNO=?";
	
	
	/**投保确认时 查询数据库 CCSCXCCJBXX sql mili*/
	public static String R_02_CCSCXCCJBXX_001 = "SELECT CALCTAXFLAG,TAXAMOUNT_FLAG,ANNUALTAXDUE,"
						+ "SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,RETURNCODE,VIN,HPHM,HPZL,CLLX,MOTORUSAGETYPECODE,MODEL,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,"
						+ "WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,MOTORTYPECODE,MADEFACTORY,NOLICENSEFLAG,"
						+ "FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,ENGINENO,CARSERIALNO,CARMATCHID,"
						+ "INSURESTARTDATE,INSUREENDDATE,ISINSERT FROM SYJK_CCS_CCSCXCCJBXX WHERE TAXQUERYNO = ? " ;
	
	/**投保确认时 查询数据库 SYJK_CCS_CCSXX sql mili*/
	public static String R_02_SYJK_CCS_CCSXX_001 = "SELECT TAXQUERYNO,TAXTERMTYPECODE,TAXCONDITIONCODE,"
						+ "TAXREGISTRYNUMBER,TAXPAYERNAME,TAXPAYERIDENTIFICATIONCODE,"
						+ "TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,PAYDATE,TAXUNITTYPECODE,UNITRATE,"
						+ "ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,"
						+ "DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,DEPARTMENT,DEPARTMENTCODE,TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,"
						+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS FROM SYJK_CCS_CCSXX WHERE PARATYPE = '1' AND TAXQUERYNO = ? AND TAXESFLAG = '1' ORDER BY  TAXSTARTDATE DESC ";
	
	/**投保确认时 查询数据库 SYJK_CCS_CCSXX sql mili*/
	public static String R_02_SYJK_CCS_CCSXX_002 = "SELECT TAXQUERYNO,TAXTERMTYPECODE,TAXCONDITIONCODE,"
						+ "TAXREGISTRYNUMBER,TAXPAYERNAME,TAXPAYERIDENTIFICATIONCODE,"
						+ "TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,PAYDATE,TAXUNITTYPECODE,UNITRATE,"
						+ "ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,"
						+ "DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,DEPARTMENT,DEPARTMENTCODE,TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,"
						+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS FROM SYJK_CCS_CCSXX WHERE PARATYPE='1' AND TAXESFLAG IS NULL AND TAXQUERYNO = ? ORDER BY SJCJRQ ";
	
	
	/**投保确认时 插入数据库 CCSQRRCJBXX  sql mili*/
	public static String C_04_CCSQRRCJBXX_001 = "INSERT INTO SYJK_CCS_CCSQRRCJBXX(TAXQUERYNO,TAXPRINTNO,TAXCONFIRMNO,CALCTAXFLAG,LICENSEPLATENO,LICENSEPLATETYPE,"
						+ "MOTORTYPECODE,ENGINENO,VIN,MADEFACTORY,MOTORUSAGETYPECODE,NOLICENSEFLAG,MODEL,FIRSTREGISTERDATE,VEHICLETYPE,"
						+ "RATEDPASSENGERCAPACITY,TONNAGE,WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,VEHICLEOWNERNAME,CREDENTIALNO,"
						+ "CREDENTIALCODE,ADDRESS,PHONENO,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,LOGINNAME,"
						+ "REVENUECODE,SJCJRQ,SJCJFS,SPECIALCARTYPE,DECLAREDSTATUS,STATUSDATE"
						+ ",INSURESTARTDATE,INSUREENDDATE,CARMATCHID)VALUES" + "(" ;
	
	/**投保确认时 插入数据库 SYJK_CCS_RKMX_QS  sql mili*/
	public static String C_04_SYJK_CCS_RKMX_QS_001 = "INSERT INTO SYJK_CCS_RKMX_QS("
						+ "TAXQUERYNO,TAXCONFIRMNO,TAXPRINTNO,VIN,HPHM,HPZL,CLLX,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,"
						+ "TAXPAYERIDENTIFICATIONCODE ,PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,"
						+ "UNITRATE,ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,"
						+ "DEDUCTIONDEPARTMENTCODE,DEDUCTIONDEPARTMENT,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,"
						+ "DEDUCTION,DEDUCTIONDOCUMENTNUMBER,TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,"
						+ "REVENUECODE,SJCJRQ,PAYDATE,TAXAMOUNT_FLAG ,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX ,LOGGEDOUT," 
						+ "PLATFORMSTATE,CHANGETYPE,COUNTTAXTYPE,REFUSETYPE,STATUSDATE,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARSERIALNO"
						+ ",ENGINENO,INSURESTARTDATE,INSUREENDDATE)VALUES(";
	/**退保查询时  查询欠税表中的欠税信息 mili*/
	public static String R_09_RKMX_QS_001 = "SELECT ANNUALTAXAMOUNT,ANNUALTAXDUE,CHANGETYPE,COUNTTAXTYPE,DEDUCTION,DEDUCTIONDEPARTMENT," +
						"DEDUCTIONDEPARTMENTCODE,DEDUCTIONDOCUMENTNUMBER,DEDUCTIONDUECODE,DEDUCTIONDUEPROPORTION,DEDUCTIONDUETYPE,EXCEEDDATE,EXCEEDDAYSCOUNT," +
						"HPHM,HPZL,LOGGEDOUT,LOGINNAME,OVERDUE,PAYCOMPANYCODE,PAYDATE,PLATFORMSTATE,REFUSETYPE,REVENUECODE,SJCJRQ,STATUSDATE,SUMOVERDUE," +
						"SUMTAX,SUMTAXDEFAULT,TAXCONDITIONCODE,TAXCONFIRMNO,TAXDEPARTMENT,TAXDEPARTMENTCODE,TAXDOCUMENTNUMBER,TAXDUE,EXCEEDDATE,TAXLOCATIONCODE," +
						"TAXPAYERIDENTIFICATIONCODE,TAXPAYERNAME,TAXPRINTNO,TAXQUERYNO,TAXREGISTRYNUMBER,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,TOTALAMOUNT," +
						"UNITRATE,VIN,FIRSTREGISTERDATE,SPECIALCARTYPE,INSURESTARTDATE,INSUREENDDATE,CARSERIALNO,ENGINENO,TSBZ FROM  SYJK_CCS_RKMX_QS Q WHERE Q.TAXCONFIRMNO = ? ORDER BY Q.SJCJRQ DESC" ;
	
	/**投保确认时 插入数据库 SYJK_CCS_RKMX  sql mili*/
	public static String C_04_RKMX_001 = "INSERT INTO SYJK_CCS_RKMX("
						+ "TAXQUERYNO,TAXCONFIRMNO,TAXPRINTNO,VIN,HPHM,HPZL,CLLX,MOTORUSAGETYPECODE,MODEL,VEHICLETYPE,RATEDPASSENGERCAPACITY,TONNAGE,"
						+ "WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE,TAXCONDITIONCODE,TAXREGISTRYNUMBER,TAXPAYERNAME,"
						+ "TAXPAYERIDENTIFICATIONCODE ,PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,TAXUNITTYPECODE,"
						+ "UNITRATE,ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER,DEDUCTIONDEPARTMENTCODE,"
						+ "DEDUCTIONDEPARTMENT,DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER,"
						+ "TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,LOGINNAME,REVENUECODE,SJCJRQ,PAYDATE,"
						+ "TAXAMOUNT_FLAG ,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX ,LOGGEDOUT,PLATFORMSTATE,CHANGETYPE,"
						+ "COUNTTAXTYPE,REFUSETYPE,STATUSDATE,FIRSTREGISTERDATE,SPECIALCARTYPE,TSBZ,CARSERIALNO,ENGINENO,INSURESTARTDATE,"
						+ "INSUREENDDATE,CARMATCHID,CITYCODE,COUNTRYCODE)VALUES(";
	
	/**查询本地车完税登记的信息 SYJK_CCS_WSDJXX sql mili*/
	public static String R_01_WSDJXX_001 = "SELECT * FROM SYJK_CCS_WSDJXX W WHERE W.CLHPHM = ? AND W.CLLX = ?";
	public static String R_01_WSDJXX_002 = "SELECT ID,NSRSBH,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,CLHPHM,CLHPZL,CLLX,SYRMC,KJSWJGDM," +
			"ZGSWJGMC,CCSBDM,DZDWMC,DZDWDM,WSPZH,SKSSKSRQ,SKSSJSRQ,JNJE,WSRQ,SKLX,ZSPM FROM SYJK_CCS_WSDJXX W ";
	
	/**查询本地车减免登记的信息 SYJK_CCS_DSCCSJMDJXX sql mili*/
	public static String R_01_DSCCSJMDJXX_001 = "SELECT * FROM SYJK_CCS_DSCCSJMDJXX D WHERE D.CLHPHM = ? AND D.CLLX = ?";
	public static String R_01_DSCCSJMDJXX_002 = "SELECT ID,NSRSBH,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,CLHPHM,CLHPZL,CLLX,CBMC,CBLX," +
			"SYRMC,JMSPZH,JZBL,JMSQRQ,JMSZRQ,NJMSE,KJSWJGDM,ZGSWJGMC,CCSBDM FROM SYJK_CCS_DSCCSJMDJXX D ";
	
	/**投保确认时 查询数据库 SYJK_CCS_CCSCXCCJBXX 保存轨迹信息时需要  sql 郑艳英*/
	public static String R_04_CCSCXCCJBXX_001 ="SELECT T.VIN,T.HPHM,T.HPZL,T.ENGINENO, T.ROWID FROM SYJK_CCS_CCSCXCCJBXX T WHERE T.TAXQUERYNO=?";
	
	/**纳税信息查询  单一条件的情况下去入库明细中查找 纳税信息 郑艳英*/
	public static String R_08_RKMX_001 = "SELECT TAXQUERYNO,TAXCONFIRMNO,CHANGETYPE,PLATFORMSTATE,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,COUNTTAXTYPE,TSBZ  FROM SYJK_CCS_RKMX R WHERE R.LOGGEDOUT ='0' AND TSBZ='0' AND VIN = ? order by R.SJCJRQ DESC";
	/**纳税信息查询  多个条件去入库明细中查询纳税信息   郑艳英*/
	public static String R_08_RKMX_002 = "SELECT TAXQUERYNO,TAXCONFIRMNO,CHANGETYPE,PLATFORMSTATE,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,COUNTTAXTYPE,TOTALAMOUNT,TAXDUE,TSBZ  FROM SYJK_CCS_RKMX R  WHERE   VIN = ? AND HPHM=? AND HPZL = ? AND R.LOGGEDOUT ='0' AND TSBZ='0' ORDER BY R.SJCJRQ DESC";	
	/**纳税信息查询  根据用多个条件查询出来信息的确认码去入库明细中查询纳税信息 郑艳英*/
	public static String R_08_RKMX_003 = "SELECT TAXCONFIRMNO,CHANGETYPE,PLATFORMSTATE,TAXQUERYNO,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,COUNTTAXTYPE,SUMTAX,TOTALAMOUNT,TAXDUE,TSBZ FROM SYJK_CCS_RKMX R  WHERE R.TAXCONFIRMNO=? AND R.LOGGEDOUT ='0' ORDER BY R.SJCJRQ DESC ";
	/**纳税信息查询  在入库明细表中查询不等于变更类型4的记录 郑艳英*/
	public static String R_08_RKMX_004 = "SELECT TAXCONFIRMNO,CHANGETYPE ,PLATFORMSTATE,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,COUNTTAXTYPE,TAXQUERYNO,TOTALAMOUNT,TAXDUE,TSBZ FROM SYJK_CCS_RKMX R  WHERE TSBZ='0' AND R.TAXCONFIRMNO=?  ORDER BY R.SJCJRQ DESC ";
	/**纳税信息查询  从入库明细获取上次退保的钱  郑艳英*/
	public static String R_08_RKMX_005 = "SELECT SUMTAX FROM SYJK_CCS_RKMX WHERE TAXCONFIRMNO=? AND TSBZ ='2'";
	/**纳税信息查询  从入库明细获取车船税信息  郑艳英*/
	public static String R_08_RKMX_006 = "SELECT * FROM SYJK_CCS_RKMX WHERE TAXQUERYNO =? ORDER BY SJCJRQ DESC";
	/**纳税信息查询 从入库明细获得本年纳税信息对象  郑艳英*/
	public static String R_08_RKMX_007 = "SELECT * FROM SYJK_CCS_RKMX WHERE TAXQUERYNO =? AND TO_CHAR(SJCJRQ,'YYYY')= TO_CHAR(TAXSTARTDATE,'YYYY')";
	/**纳税信息查询  从入库明细中获取合计欠税金额  郑艳英*/
	public static String R_08_RKMX_008 = "SELECT SUMTAXDEFAULT,SUMOVERDUE FROM SYJK_CCS_RKMX WHERE REFUSETYPE = '1' AND TAXQUERYNO=?";
	/**纳税信息查询  保存纳税信息查询入参信息 郑艳英*/
	public static String C_08_CCSNSXXCXRC_009 = "INSERT INTO SYJK_CCS_CCSNSXXCXRC (LICENSEPLATENO,LICENSEPLATETYPE,MOTORTYPECODE,ENGINENO,VIN,"
			+ "MADEFACTORY,MOTORUSAGETYPECODE,NOLICENSEFLAG,MODEL,FIRSTREGISTERDATE,VEHICLETYPE," 
			+ "RATEDPASSENGERCAPACITY,TONNAGE,WHOLEWEIGHT,DISPLACEMENT,POWER,FUELTYPE," 
			+ "SPECIALCARTYPE,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS)VALUES(";
	/**纳税信息查询  保存纳税信息查询出参信息 郑艳英*/
	public static String C_08_CCSNSXXCXCC_010 = "INSERT INTO SYJK_CCS_CCSNSXXCXCC (RETURNCODE,DECLAREDSTATUS,TAXTERMTYPECODE,TAXCONDITIONCODE,TAXREGISTRYNUMBER,"
	+ "TAXPAYERNAME,TAXPAYERIDENTIFICATIONCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXENDDATE,PAYCOMPANYCODE," 
	+ "TAXUNITTYPECODE,UNITRATE,ANNUALTAXAMOUNT,TAXDEPARTMENTCODE,TAXDEPARTMENT,TAXDOCUMENTNUMBER," 
	+ "DEDUCTIONDUECODE,DEDUCTIONDUETYPE,DEDUCTIONDUEPROPORTION,DEDUCTION,DEDUCTIONDOCUMENTNUMBER," 
	+ "TAXDUE,EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT," 
	+ "LOGINNAME,REVENUECODE,SJCJRQ," 
	+ "SJCJFS,PAYDATE)VALUES(";
	
	/**纳税信息查询  rkmx 郑艳英*/
	public static String C_08_RKMX_001 ="select PLATFORMSTATE,COUNTTAXTYPE,taxConditionCode,TAXREGISTRYNUMBER,TAXPAYERNAME," +
			"TAXPAYERIDENTIFICATIONCODE,PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXLOCATIONCODE," +
			"TAXENDDATE,TAXUNITTYPECODE,UNITRATE,ANNUALTAXAMOUNT,TAXDUE," +
			"EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,TAXDEPARTMENT," +
			"TAXDEPARTMENTCODE,TAXDOCUMENTNUMBER,DEDUCTION,DEDUCTIONDUEPROPORTION,DEDUCTIONDUECODE," +
			"DEDUCTIONDUETYPE,DEDUCTIONDOCUMENTNUMBER,DEDUCTIONDEPARTMENT,DEDUCTIONDEPARTMENTCODE," +
			"TAXQUERYNO,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE," +
			"SumTax,TAXCONFIRMNO,SPECIALCARTYPE,PAYDATE,SJCJRQ from (select t.*, t.rowid from syjk_ccs_rkmx t where t.vin =? and t.hphm=? and t.engineno=? and LOGGEDOUT ='0' order by t.sjcjrq desc) c ";
	
	/**纳税信息查询  rkmx 郑艳英*/
	public static String C_08_RKMX_002="select PLATFORMSTATE,COUNTTAXTYPE,taxConditionCode,TAXREGISTRYNUMBER,TAXPAYERNAME," +
	"TAXPAYERIDENTIFICATIONCODE,PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXLOCATIONCODE," +
	"TAXENDDATE,TAXUNITTYPECODE,UNITRATE,ANNUALTAXAMOUNT,TAXDUE," +
	"EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,TAXDEPARTMENT," +
	"TAXDEPARTMENTCODE,TAXDOCUMENTNUMBER,DEDUCTION,DEDUCTIONDUEPROPORTION,DEDUCTIONDUECODE," +
	"DEDUCTIONDUETYPE,DEDUCTIONDOCUMENTNUMBER,DEDUCTIONDEPARTMENT,DEDUCTIONDEPARTMENTCODE," +
	"TAXQUERYNO,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE," +
	"SumTax,TAXCONFIRMNO,SPECIALCARTYPE,PAYDATE,SJCJRQ from (select t.*, t.rowid from syjk_ccs_rkmx t where t.hphm=? and t.hpzl=? and LOGGEDOUT ='0' order by t.sjcjrq desc) c ";
	
	/**纳税信息查询  rkmx 郑艳英*/
	public static String C_08_RKMX_003="select PLATFORMSTATE,COUNTTAXTYPE,taxConditionCode,TAXREGISTRYNUMBER,TAXPAYERNAME," +
	"TAXPAYERIDENTIFICATIONCODE,PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXLOCATIONCODE," +
	"TAXENDDATE,TAXUNITTYPECODE,UNITRATE,ANNUALTAXAMOUNT,TAXDUE," +
	"EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,TAXDEPARTMENT," +
	"TAXDEPARTMENTCODE,TAXDOCUMENTNUMBER,DEDUCTION,DEDUCTIONDUEPROPORTION,DEDUCTIONDUECODE," +
	"DEDUCTIONDUETYPE,DEDUCTIONDOCUMENTNUMBER,DEDUCTIONDEPARTMENT,DEDUCTIONDEPARTMENTCODE," +
	"TAXQUERYNO,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE," +
	"SumTax,TAXCONFIRMNO,SPECIALCARTYPE ,PAYDATE,SJCJRQ from (select t.*, t.rowid from syjk_ccs_rkmx t where t.vin =? and t.engineno=? and LOGGEDOUT ='0' order by t.sjcjrq desc) c "; 
	
	/**纳税信息查询  rkmx 郑艳英*/
	public static String C_08_RKMX_004="select PLATFORMSTATE,COUNTTAXTYPE,taxConditionCode,TAXREGISTRYNUMBER,TAXPAYERNAME," +
	"TAXPAYERIDENTIFICATIONCODE,PAYCOMPANYCODE,TAXLOCATIONCODE,TAXSTARTDATE,TAXLOCATIONCODE," +
	"TAXENDDATE,TAXUNITTYPECODE,UNITRATE,ANNUALTAXAMOUNT,TAXDUE," +
	"EXCEEDDATE,EXCEEDDAYSCOUNT,OVERDUE,TOTALAMOUNT,TAXDEPARTMENT," +
	"TAXDEPARTMENTCODE,TAXDOCUMENTNUMBER,DEDUCTION,DEDUCTIONDUEPROPORTION,DEDUCTIONDUECODE," +
	"DEDUCTIONDUETYPE,DEDUCTIONDOCUMENTNUMBER,DEDUCTIONDEPARTMENT,DEDUCTIONDEPARTMENTCODE," +
	"TAXQUERYNO,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE," +
	"SumTax,TAXCONFIRMNO,SPECIALCARTYPE ,PAYDATE,SJCJRQ from (select t.*, t.rowid from syjk_ccs_rkmx t where t.vin =? and LOGGEDOUT ='0' order by t.sjcjrq desc) c ";
	/**纳税信息查询  syjk_ccs_rkmx_qs 郑艳英*/
	public static String C_08_rkmx_qs_001 = "select t.*, t.rowid from syjk_ccs_rkmx_qs t where t.taxconfirmno=? order by t.sjcjrq desc";
	
	/**申报日期上传  保存确认码 郑艳英*/
	public static String C_07_CONFIRM_SB_001="INSERT INTO SYJK_CCS_CONFIRM_SB(TAXCONFIRMNO,FLAG) VALUES(?,?)";
	/**申报日期上传 ,查没有申报的 郑艳英 **/
	public static String R_07_CONFIRM_SB_002 = "SELECT T.TAXCONFIRMNO FROM SYJK_CCS_CONFIRM_SB T WHERE NOT EXISTS (SELECT * FROM SYJK_CCS_RKMX B WHERE T.TAXCONFIRMNO=B.TAXCONFIRMNO) AND FLAG=";
	/**申报日期 上传,删除确认码  郑艳英 **/
	public static String D_07_CONFIRM_SB_003 = "DELETE FROM SYJK_CCS_CONFIRM_SB WHERE FLAG=";
	/**申报日期上传 ,更新入库明细状态  郑艳英*/
	public static String U_07_RKMX_004 = "UPDATE SYJK_CCS_RKMX SET PLATFORMSTATE = '1',STATUSDATE = SYSDATE WHERE TAXCONFIRMNO=?";
	/**申报日期上传 ,保存申报日期入参信息  郑艳英*/
	public static String C_07_CCSSBRQSCRC_005 = "INSERT INTO SYJK_CCS_CCSSBRQSCRC(DECLAREDATE,LISTRESPECTIVEANNUAL,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS,TAXCHANGEQUERYNO,AREACODE,COMPANYCODE) VALUES(TO_DATE(?,'yyyy-MM-dd HH24:mi:ss'),?,?,?,TO_DATE(?,'yyyy-MM-dd HH24:mi:ss'),?,?,?,?)";
	/**申报日期上传 ,保存申报日期出参信息  郑艳英*/
	public static String C_07_CCSSBRQSCCC_006 ="INSERT INTO SYJK_CCS_CCSSBRQSCCC(RETURNCODE,LISTRESPECTIVEANNUAL,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS) VALUES(?,?,?,?,TO_DATE(?,'yyyy-MM-dd HH24:mi:ss'),?)";
	
	/**申报日期上传  从入库明细获取车船税信息  郑艳英*/
	public static String R_07_RKMX_007 = "SELECT * FROM SYJK_CCS_RKMX WHERE TAXQUERYNO =? ORDER BY SJCJRQ DESC";
	
	/**对帐服务 ,车船税查询出参基本信息表   郑艳英*/
	public static String R_05_CCSCXCCJBXX_001 = "SELECT TAXQUERYNO,CALCTAXFLAG,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,RETURNCODE,"
		                                         + "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS "
		                                         + "FROM SYJK_CCS_CCSCXCCJBXX WHERE TAXQUERYNO=?";
	/**对帐服务 ,车船税查询出参基本信息表   MILI*/
	public static String R_05_CCSCXCCJBXX_002 = "SELECT TAXQUERYNO,CALCTAXFLAG,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,RETURNCODE,"
		                                         + "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS "
		                                         + "FROM SYJK_CCS_CCSCXCCJBXX WHERE TAXQUERYNO IN (";
	
	/**对帐服务 ,保存对帐确认码   郑艳英*/
	public static String C_05_CONFIRM_DZ_002 = "INSERT INTO SYJK_CCS_CONFIRM_DZ VALUES(?,?)";
	/**对账服务  , 车船税确认信息  郑艳英*/
	public static String R_05_003 = "select SJCJRQ, TAXQUERYNO, COUNTTAXTYPE,TAXAMOUNT_FLAG, ANNUALTAXDUE, SUMTAXDEFAULT, SUMOVERDUE,SUMTAX, " +
	"decode(pd, null, Platformstate, pd) as PLATFORMSTATE from " +
	"(SELECT SJCJRQ,a.TAXQUERYNO, COUNTTAXTYPE, TAXAMOUNT_FLAG, ANNUALTAXDUE, SUMTAXDEFAULT, SUMOVERDUE, SUMTAX,Platformstate " +
	"FROM syjk_ccs_confirm_dz d, syjk_ccs_rkmx a where a.taxqueryno=d.taxqueryno and d.flag=?) " +
	"left join (select TAXQUERYNO,pd from " +
	"(SELECT b.TAXQUERYNO, Platformstate pd FROM syjk_ccs_confirm_dz b, syjk_ccs_rkmx a where b.TAXQUERYNO = a.TAXCONFIRMNO  and flag=? and Loggedout = 0 order by sjcjrq desc) where rownum < 2)using (TAXQUERYNO)";
	/**对账服务  , 删除确认码   郑艳英*/
	public static String D_05_CONFIRM_DZ_004 = "DELETE FROM SYJK_CCS_CONFIRM_DZ WHERE FLAG=";
	/**对账服务  , 车船税变更查询信息   郑艳英*/
	public static String R_05_CONFIRM_DZ_005 = "select TAXQUERYNO,CALCTAXFLAG,TAXAMOUNT_FL,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,"
		+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS "
		+ "from SYJK_CCS_CCSBGCXCCJB where TAXQUERYNO=?";
	/**对账服务  , 车船税变更查询信息   MILI 2014-11-27 14:23:20*/
	public static String R_05_CONFIRM_DZ_008 = "SELECT TAXQUERYNO,CALCTAXFLAG,TAXAMOUNT_FL,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,"
		+ "LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS "
		+ "FROM SYJK_CCS_CCSBGCXCCJB WHERE TAXQUERYNO IN(";
	/**对账服务  , 车船税变更确认信息   郑艳英*/
	public static String  R_05_CONFIRM_DZ_006= "SELECT a.SJCJRQ,a.TAXQUERYNO,a.COUNTTAXTYPE,a.TAXAMOUNT_FLAG,a.ANNUALTAXDUE,a.SUMTAXDEFAULT,a.SUMOVERDUE," +
	"a.SUMTAX,a.PLATFORMSTATE FROM syjk_ccs_confirm_dz d , SYJK_CCS_RKMX a where a.taxqueryno=d.taxqueryno and flag=";
	/**对账服务  , 保存对帐入参信息   郑艳英*/
	public static String  C_05_CCSDZRCJBXX_007 = "INSERT INTO SYJK_CCS_CCSDZRCJBXX(TAXRECONCILIATIONNO,CHECKINGTYPE,TAXDEALCODE,LOGINNAME,REVENUECODE,SJCJRQ,SJCJFS)"
		+ "VALUES(?,?,?,?,?,TO_DATE(?,'yyyy-MM-dd HH24:mi:ss'),?)";
	/**对账服务  , 保存对帐出参信息   郑艳英*/
	public static String  C_05_CCSDZCCJBXX_008 = "INSERT INTO SYJK_CCS_CCSDZCCSXX(TAXRECONCILIATIONNO,CHECKINGTYPE,TAXDEALCODE,TAXAMOUNT_FLAG,ANNUALTAXDUE,SUMTAXDEFAULT,SUMOVERDUE,SUMTAX,RETURNCODE,LOGINNAME,REVENUECODE,SJCJRQ, SJCJFS)"
		+ "values(?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-MM-dd HH24:mi:ss'),?)";
	
	/** 保存轨迹表   郑艳英*/
	public static String C_SYJK_CCS_TAXTRACE_001 = "INSERT INTO SYJK_CCS_TAXTRACE VALUES(?,?,?,?,SYSDATE)";
	
	
	public static void main (String[] args){
		
		log.debug(R_00_CODE_001);
		
	}

}
