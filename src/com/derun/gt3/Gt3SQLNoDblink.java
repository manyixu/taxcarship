package com.derun.gt3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.derun.common.db.SSRS;
import com.derun.common.util.DateUtil;
import com.derun.common.util.StrTool;

public class Gt3SQLNoDblink {
	
	////目前提供的查询sql只有代征单位代码，没有开具完税凭证税务机关代码和税务机关名称，税务机关名称需要关联字典表查询
	StringBuffer sb1 = new StringBuffer("");  
	StringBuffer sb2 = new StringBuffer("");
	StringBuffer sb3 = new StringBuffer("");
	StringBuffer sb4 = new StringBuffer("");
	public Gt3SQLNoDblink(Gt3InPara wsIn) {
		// TODO Auto-generated constructor stub
		sb1.append(" with JBXX as (");
		sb1.append(" select w.djxh, ");
		sb1.append(" w.nsrsbh,");
		sb1.append(" w.CCSBDM,");
		sb1.append(" w.CLHPHM,");
		sb1.append(" w.CLHPZL,");
		sb1.append(" w.CLLX,");
		sb1.append(" w.SYRMC, ");
		sb1.append(" w.pzxh,");
		sb1.append(" w.skssqq,");
		sb1.append(" w.skssqz,");
		sb1.append(" w.cclx ");
		sb1.append(" from (select ");
		
//		sb1.append(" select ");
		
		sb1.append(" a.sbxxuuid,");
		sb1.append(" c.djxh,");
		sb1.append(" c.nsrsbh,");
		sb1.append(" b.clsbdh CCSBDM, ");
		sb1.append(" b.clpzh CLHPHM,");
		sb1.append(" \'\' CLHPZL, ");
		sb1.append(" \'\' CLLX, ");
		sb1.append(" c.nsrmc SYRMC, ");
		sb1.append(" a.pzxh,");
		sb1.append(" a.skssqq,");
		sb1.append(" a.skssqz,");
		sb1.append(" \'0\' cclx ");
		sb1.append("from sb_sysbmxbg a,");
		sb1.append(" sb_clqkxx b,");
		sb1.append(" dj_nsrxx c ");
		sb1.append("");
		sb1.append(" where a.djxh = b.djxh");
		sb1.append(" and a.djxh = c.djxh");
		sb1.append(" and a.sybh_1 = b.clsbdh");
		sb1.append(" and a.sybh_1=\'"+wsIn.getVin()+"\' ");
		sb1.append(" AND to_char(a.skssqq , \'yyyy\') = \'2016\'");
		sb1.append(" and a.zfbz_1=\'N\' ");
		sb1.append(" and a.sybz_dm_1 =\'5\' ");
		
		sb1.append("");
		sb1.append("");
		sb1.append(") w ");
		sb1.append("group by w.djxh,");
		sb1.append("w.nsrsbh, ");
		sb1.append("w.ccsbdm, ");
		sb1.append("w.clhphm, ");
		sb1.append("w.clhpzl, ");
		sb1.append("w.cllx, ");
		sb1.append("w.cclx, ");
		sb1.append("w.syrmc,");
		sb1.append("w.pzxh, ");
		sb1.append("w.skssqq, ");
		sb1.append("w.skssqz");
		sb1.append(" )");
		sb1.append("select");
		sb1.append("");
		sb1.append(" tpl.id_sequences.nextval,");
		sb1.append(" jbxx.nsrsbh NSRSBH, ");
		sb1.append(" jbxx.ccsbdm CCSBDM, ");
		sb1.append(" jbxx.clhphm CLHPHM, ");
		sb1.append(" jbxx.clhpzl CLHPZL, ");
		sb1.append(" jbxx.cllx CLLX, ");
		sb1.append("");
		sb1.append(" jbxx.syrmc SYRMC,");
		sb1.append(" jbxx.skssqq SKSSQQ,");
		sb1.append(" jbxx.skssqz SKSSQZ,");
		sb1.append(" yj.ybtse JNJE, ");
		sb1.append(" yj.yzclrq WSRQ,");
		sb1.append(" jks.zsswjg_dm dzdwdm,");
		sb1.append(" nvl(jks.pzhm, jks.dzsphm) WSPZH, ");
		sb1.append(" sysdate SJCJRQ ");
		sb1.append("from zs_yjsf yj, jbxx, zs_jks jks ");
		sb1.append("");
		sb1.append(" where ");
		sb1.append("yj.zsxm_dm=\'10114\'");
		sb1.append(" and jks.zsxm_dm=\'10114\'");
		sb1.append(" and yj.skzl_dm=\'10\'");
		sb1.append(" AND to_char(yj.skssqq , \'yyyy\') = \'2016\' ");
		sb1.append("");
		sb1.append("");
		sb1.append("");
		sb1.append(" and yj.yzpzxh = jbxx.pzxh");
		sb1.append(" AND yj.skssqq = jbxx.skssqq");
		sb1.append(" AND yj.skssqz = jbxx.skssqz");
		sb1.append(" AND jbxx.ccsbdm = yj.sybh_1");
		sb1.append(" AND yj.sybh_1 = \'"+wsIn.getVin()+"\'");
		sb1.append(" and yj.tzlx_dm in (\'1\', \'4\') ");
		sb1.append(" and jks.tzlx_dm in (\'1\', \'4\')");
		sb1.append(" and yj.skcllx_dm=\'1\' ");
		sb1.append(" and yj.yzclrq is not null");
		sb1.append(" and yj.zsuuid = jks.zsuuid (+) ");
//		sb.append("");
		sb2.append("  with jbxx as ");
		sb2.append("( ");
		sb2.append("");
		sb2.append("");
		sb2.append(" select w.djxh, ");
		sb2.append(" w.nsrsbh,");
		sb2.append(" w.CCSBDM,");
		sb2.append(" w.CLHPHM,");
		sb2.append(" w.CLHPZL,");
		sb2.append(" w.CLLX,");
		sb2.append(" w.SYRMC, ");
		sb2.append(" w.pzxh,");
		sb2.append(" w.skssqq,");
		sb2.append(" w.skssqz,");
		sb2.append(" w.cclx ");
		sb2.append(" from (select ");
		
//		sb2.append(" select ");
		
		sb2.append(" a.sbxxuuid,");
		sb2.append(" c.djxh,");
		sb2.append(" c.nsrsbh,");
		sb2.append(" b.clsbdh CCSBDM, ");
		sb2.append(" b.clpzh CLHPHM,");
		sb2.append(" \'\' CLHPZL, ");
		sb2.append(" \'\' CLLX, ");
		sb2.append(" c.nsrmc SYRMC, ");
		sb2.append(" a.pzxh,");
		sb2.append(" a.skssqq,");
		sb2.append(" a.skssqz,");
		sb2.append(" \'0\' cclx ");
		sb2.append("from sb_sysbmxbg a,");
		sb2.append(" sb_clqkxx b,");
		sb2.append(" dj_nsrxx c ");
		sb2.append("");
		sb2.append(" where a.djxh = b.djxh");
		sb2.append(" and a.djxh = c.djxh");
		sb2.append(" and a.sybh_1 = b.clsbdh");
		sb2.append(" and a.sybh_1=\'"+wsIn.getVin()+"\' ");
		sb2.append(" AND to_char(a.skssqq , \'yyyy\') = \'2016\'");
		sb2.append(" and a.zfbz_1=\'N\' ");
		sb2.append(" and a.sybz_dm_1 =\'5\' ");
		
		sb2.append("");
		sb2.append("");
		sb2.append(") w ");
		sb2.append("group by w.djxh,");
		sb2.append("w.nsrsbh, ");
		sb2.append("w.ccsbdm, ");
		sb2.append("w.clhphm, ");
		sb2.append("w.clhpzl, ");
		sb2.append("w.cllx, ");
		sb2.append("w.cclx, ");
		sb2.append("w.syrmc,");
		sb2.append("w.pzxh, ");
		sb2.append("w.skssqq, ");
		sb2.append("w.skssqz");
		sb2.append(" )");
		sb2.append("select");
		sb2.append("");
		sb2.append(" tpl.id_sequences.nextval,");
		sb2.append(" jbxx.nsrsbh NSRSBH, ");
		sb2.append(" jbxx.ccsbdm CCSBDM, ");
		sb2.append(" jbxx.clhphm CLHPHM, ");
		sb2.append(" jbxx.clhpzl CLHPZL, ");
		sb2.append(" jbxx.cllx CLLX, ");
		sb2.append("");
		sb2.append(" jbxx.syrmc SYRMC,");
		sb2.append(" jbxx.skssqq SKSSQQ,");
		sb2.append(" jbxx.skssqz SKSSQZ,");
		sb2.append(" yj.ybtse JNJE, ");
		sb2.append(" yj.yzclrq WSRQ,");
		sb2.append(" jks.zsswjg_dm dzdwdm,");
		sb2.append(" nvl(jks.pzhm, jks.dzsphm) WSPZH, ");
		sb2.append(" sysdate SJCJRQ ");
		sb2.append("from zs_yjsf yj, jbxx, zs_wsz jks ");
		sb2.append("");
		sb2.append(" where ");
		sb2.append("yj.zsxm_dm=\'10114\'");
		sb2.append("");
		sb2.append(" and yj.skzl_dm=\'10\'");
		sb2.append(" AND to_char(yj.skssqq , \'yyyy\') = \'2016\' ");
		sb2.append("");
		sb2.append("");
		sb2.append("");
		sb2.append(" and yj.yzpzxh = jbxx.pzxh");
		sb2.append(" AND yj.skssqq = jbxx.skssqq");
		sb2.append(" AND yj.skssqz = jbxx.skssqz");
		sb2.append(" AND jbxx.ccsbdm = yj.sybh_1");
		sb2.append(" AND yj.sybh_1 = \'"+wsIn.getVin()+"\' ");
		sb2.append(" and yj.tzlx_dm in (\'1\', \'4\') ");
		sb2.append(" and jks.tzlx_dm in (\'1\', \'4\')");
		sb2.append(" and yj.skcllx_dm=\'1\' ");
		sb2.append(" and yj.yzclrq is not null");
		sb2.append(" and yj.zsuuid = jks.zsuuid (+) ");
//		sb.append("");
		sb3.append("  with jbxx as ");
		sb3.append("( ");
		sb3.append("");
		sb3.append("");
		sb3.append(" select w.djxh, ");
		sb3.append(" w.nsrsbh,");
		sb3.append(" w.CCSBDM,");
		sb3.append(" w.CLHPHM,");
		sb3.append(" w.CLHPZL,");
		sb3.append(" w.CLLX,");
		sb3.append(" w.SYRMC, ");
		sb3.append(" w.pzxh,");
		sb3.append(" w.skssqq,");
		sb3.append(" w.skssqz,");
		sb3.append(" w.cclx ");
		sb3.append(" from (select ");
		
//		sb3.append(" select ");
		
		sb3.append(" a.sbxxuuid,");
		sb3.append(" c.djxh,");
		sb3.append(" c.nsrsbh,");
		sb3.append(" b.clsbdh CCSBDM, ");
		sb3.append(" b.clpzh CLHPHM,");
		sb3.append(" \'\' CLHPZL, ");
		sb3.append(" \'\' CLLX, ");
		sb3.append(" c.XM SYRMC,");
		sb3.append(" a.pzxh,");
		sb3.append(" a.skssqq,");
		sb3.append(" a.skssqz,");
		sb3.append(" \'0\' cclx ");
		sb3.append("from sb_sysbmxbg a,");
		sb3.append(" sb_clqkxx b,");
		sb3.append(" dj_zrr c ");
		sb3.append("");
		sb3.append(" where a.djxh = b.djxh");
		sb3.append(" and a.djxh = c.djxh");
		sb3.append(" and a.sybh_1 = b.clsbdh");
		sb3.append(" and a.sybh_1=\'"+wsIn.getVin()+"\' ");
		sb3.append(" AND to_char(a.skssqq , \'yyyy\') = \'2016\'");
		sb3.append(" and a.zfbz_1=\'N\' ");
		sb3.append(" and a.sybz_dm_1 =\'5\' ");
		
		sb3.append("");
		sb3.append("");
		sb3.append(") w ");
		sb3.append("group by w.djxh,");
		sb3.append("w.nsrsbh, ");
		sb3.append("w.ccsbdm, ");
		sb3.append("w.clhphm, ");
		sb3.append("w.clhpzl, ");
		sb3.append("w.cllx, ");
		sb3.append("w.cclx, ");
		sb3.append("w.syrmc,");
		sb3.append("w.pzxh, ");
		sb3.append("w.skssqq, ");
		sb3.append("w.skssqz");
		sb3.append(" )");
		sb3.append("select");
		sb3.append("");
		sb3.append(" tpl.id_sequences.nextval,");
		sb3.append(" jbxx.nsrsbh NSRSBH, ");
		sb3.append(" jbxx.ccsbdm CCSBDM, ");
		sb3.append(" jbxx.clhphm CLHPHM, ");
		sb3.append(" jbxx.clhpzl CLHPZL, ");
		sb3.append(" jbxx.cllx CLLX, ");
		sb3.append("");
		sb3.append(" jbxx.syrmc SYRMC,");
		sb3.append(" jbxx.skssqq SKSSQQ,");
		sb3.append(" jbxx.skssqz SKSSQZ,");
		sb3.append(" yj.ybtse JNJE, ");
		sb3.append(" yj.yzclrq WSRQ,");
		sb3.append(" jks.zsswjg_dm dzdwdm,");
		sb3.append(" nvl(jks.pzhm, jks.dzsphm) WSPZH, ");
		sb3.append(" sysdate SJCJRQ ");
		sb3.append("from zs_yjsf yj, jbxx, zs_jks jks ");
		sb3.append("");
		sb3.append(" where ");
		sb3.append("yj.zsxm_dm=\'10114\'");
		sb3.append(" and jks.zsxm_dm=\'10114\'");
		sb3.append(" and yj.skzl_dm=\'10\'");
		sb3.append(" AND to_char(yj.skssqq , \'yyyy\') = \'2016\' ");
		sb3.append("");
		sb3.append("");
		sb3.append("");
		sb3.append(" and yj.yzpzxh = jbxx.pzxh");
		sb3.append(" AND yj.skssqq = jbxx.skssqq");
		sb3.append(" AND yj.skssqz = jbxx.skssqz");
		sb3.append(" AND jbxx.ccsbdm = yj.sybh_1");
		sb3.append(" AND yj.sybh_1 = \'"+wsIn.getVin()+"\' ");
		sb3.append(" and yj.tzlx_dm in (\'1\', \'4\') ");
		sb3.append(" and jks.tzlx_dm in (\'1\', \'4\')");
		sb3.append(" and yj.skcllx_dm=\'1\' ");
		sb3.append(" and yj.yzclrq is not null");
		sb3.append(" and yj.zsuuid = jks.zsuuid (+) ");
//		sb.append("");
		sb4.append("  with jbxx as ");
		sb4.append("( ");
		sb4.append("");
		sb4.append("");
		sb4.append(" select w.djxh, ");
		sb4.append(" w.nsrsbh,");
		sb4.append(" w.CCSBDM,");
		sb4.append(" w.CLHPHM,");
		sb4.append(" w.CLHPZL,");
		sb4.append(" w.CLLX,");
		sb4.append(" w.SYRMC, ");
		sb4.append(" w.pzxh,");
		sb4.append(" w.skssqq,");
		sb4.append(" w.skssqz,");
		sb4.append(" w.cclx ");
		sb4.append(" from (select ");
		
//		sb4.append(" select ");
		
		sb4.append(" a.sbxxuuid,");
		sb4.append(" c.djxh,");
		sb4.append(" c.nsrsbh,");
		sb4.append(" b.clsbdh CCSBDM, ");
		sb4.append(" b.clpzh CLHPHM,");
		sb4.append(" \'\' CLHPZL, ");
		sb4.append(" \'\' CLLX, ");
		sb4.append(" c.XM SYRMC,");
		sb4.append(" a.pzxh,");
		sb4.append(" a.skssqq,");
		sb4.append(" a.skssqz,");
		sb4.append(" \'0\' cclx ");
		sb4.append("from sb_sysbmxbg a,");
		sb4.append(" sb_clqkxx b,");
		sb4.append(" dj_zrr c ");
		sb4.append("");
		sb4.append(" where a.djxh = b.djxh");
		sb4.append(" and a.djxh = c.djxh");
		sb4.append(" and a.sybh_1 = b.clsbdh");
		sb4.append(" and a.sybh_1=\'"+wsIn.getVin()+"\' ");
		sb4.append(" AND to_char(a.skssqq , \'yyyy\') = \'2016\'");
		sb4.append(" and a.zfbz_1=\'N\' ");
		sb4.append(" and a.sybz_dm_1 =\'5\' ");
		
		sb4.append("");
		sb4.append(") w ");
		sb4.append("group by w.djxh,");
		sb4.append("w.nsrsbh, ");
		sb4.append("w.ccsbdm, ");
		sb4.append("w.clhphm, ");
		sb4.append("w.clhpzl, ");
		sb4.append("w.cllx, ");
		sb4.append("w.cclx, ");
		sb4.append("w.syrmc,");
		sb4.append("w.pzxh, ");
		sb4.append("w.skssqq, ");
		sb4.append("w.skssqz");
		sb4.append(" )");
		sb4.append("select");
		sb4.append("");
		sb4.append(" tpl.id_sequences.nextval,");
		sb4.append(" jbxx.nsrsbh NSRSBH, ");
		sb4.append(" jbxx.ccsbdm CCSBDM, ");
		sb4.append(" jbxx.clhphm CLHPHM, ");
		sb4.append(" jbxx.clhpzl CLHPZL, ");
		sb4.append(" jbxx.cllx CLLX, ");
		sb4.append("");
		sb4.append(" jbxx.syrmc SYRMC,");
		sb4.append(" jbxx.skssqq SKSSQQ,");
		sb4.append(" jbxx.skssqz SKSSQZ,");
		sb4.append(" yj.ybtse JNJE, ");
		sb4.append(" yj.yzclrq WSRQ,");
		sb4.append(" jks.zsswjg_dm dzdwdm,");
		sb4.append(" nvl(jks.pzhm, jks.dzsphm) WSPZH, ");
		sb4.append(" sysdate SJCJRQ ");
		sb4.append("from zs_yjsf yj, jbxx, zs_wsz jks ");
		sb4.append("");
		sb4.append(" where yj.zsxm_dm=\'10114\'");
		sb4.append("");
		sb4.append(" and yj.skzl_dm=\'10\'");
		sb4.append(" AND to_char(yj.skssqq , \'yyyy\') = \'2016\' ");
		sb4.append("");
		sb4.append(" and yj.yzpzxh = jbxx.pzxh");
		sb4.append(" AND yj.skssqq = jbxx.skssqq");
		sb4.append(" AND yj.skssqz = jbxx.skssqz");
		sb4.append(" AND jbxx.ccsbdm = yj.sybh_1");
		sb4.append(" AND yj.sybh_1 = \'"+wsIn.getVin()+"\' ");
		sb4.append(" and yj.tzlx_dm in (\'1\', \'4\') ");
		sb4.append(" and jks.tzlx_dm in (\'1\', \'4\')");
		sb4.append(" and yj.skcllx_dm=\'1\' ");
		sb4.append(" and yj.yzclrq is not null");
		sb4.append(" and yj.zsuuid = jks.zsuuid (+) ");
		
	}
	

	public static void main(String[] args) {
		Gt3InPara wsIn = new Gt3InPara();
		wsIn.setVin("LZWACAGA9A4181610");
		Gt3SQLNoDblink gt3sql = new Gt3SQLNoDblink(wsIn);
		
		//读取文件SQL
//		File file = new File("C:\\Users\\Administrator\\Desktop\\gt3errorsql20160602.sql");
//		String content = "";
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
//            String s = null;
//            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//            	content = content + "\n" +s;
//            }
//            br.close();    
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        
		
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Connection connection = null;
		SSRS ssrs = null;
		try {
//			connection = dbpool.getConnection();
			
			String url = "jdbc:Oracle:thin:@192.168.109.129:1521:orcl" ;   
		    String username = "gt3" ;   
		    String password = "gt3" ;
			connection = DriverManager.getConnection(url , username , password ) ; 
			
			
			
			stmt = connection.createStatement();
//			rs = stmt.executeQuery("with jb as(select 1 from dual) select jb.*,2 from dual,jb");
			boolean rsFull = false;
			rs = stmt.executeQuery(gt3sql.sb1.toString());//gt3sql.sb.toString()
			if(rs.next()){
				rsFull = true;
			}else{
				rs = stmt.executeQuery(gt3sql.sb2.toString());
				if(rs.next()){
					rsFull = true;
				}else{
					rs = stmt.executeQuery(gt3sql.sb3.toString());
					if(rs.next()){
						rsFull = true;
					}else{
						rs = stmt.executeQuery(gt3sql.sb4.toString());
					}
				}
			}
			if(rsFull){
				rsmd = rs.getMetaData();
				int n = rsmd.getColumnCount();
				ssrs = new SSRS(n);
				while (rs.next()) {
					for (int j = 1; j <= n; j++) {
						ssrs.SetText(getDataValue(rsmd, rs, j));
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Error in Query - Sql's bug is very big : "+ex);
		}finally{
			close(stmt, rs, connection);
			//finalize();//用哪个，是否有必要
		}
	}
	
	public final static void close(Statement pstmt, ResultSet rs, Connection conn) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("close gt3 connection error : "+e);
		}
	}
	
    public static String getDataValue(ResultSetMetaData rsmd, ResultSet rs, int i)
    {
        String strValue = "";

        try
        {
            int dataType = rsmd.getColumnType(i);
            int dataScale = rsmd.getScale(i);
            int dataPrecision = rsmd.getPrecision(i);
            //数据类型为字符
            if ((dataType == Types.CHAR) || (dataType == Types.VARCHAR))
            {
                //由于存入数据库的数据是GBK模式，因此没有必要做一次unicodeToGBK
//                strValue = StrTool.unicodeToGBK(rs.getString(i));
                strValue = rs.getString(i);
            }
            //数据类型为日期、时间
            else if ((dataType == Types.TIMESTAMP) || (dataType == Types.DATE))
            {
                strValue = DateUtil.getString(rs.getDate(i),"yyyy-MM-dd");
            }
            //数据类型为浮点
            else if ((dataType == Types.DECIMAL) || (dataType == Types.FLOAT))
            {
                //strValue = String.valueOf(rs.getFloat(i));
                //采用下面的方法使得数据输出的时候不会产生科学计数法样式
                strValue = String.valueOf(rs.getBigDecimal(i));
                //去零处理
                strValue = StrTool.getInt(strValue);
            }
            //数据类型为整型
            else if ((dataType == Types.INTEGER) || (dataType == Types.SMALLINT))
            {
                strValue = String.valueOf(rs.getInt(i));
                strValue = StrTool.getInt(strValue);
            }
            //数据类型为浮点
            else if (dataType == Types.NUMERIC)
            {
                if (dataScale == 0)
                {
                    if (dataPrecision == 0)
                    {
                        //strValue = String.valueOf(rs.getDouble(i));
                        //采用下面的方法使得数据输出的时候不会产生科学计数法样式
                        strValue = String.valueOf(rs.getBigDecimal(i));
                    }
                    else
                    {
                        strValue = String.valueOf(rs.getLong(i));
                    }
                }
                else
                {
                    //strValue = String.valueOf(rs.getDouble(i));
                    //采用下面的方法使得数据输出的时候不会产生科学计数法样式
                    strValue = String.valueOf(rs.getBigDecimal(i));
                }
                strValue = StrTool.getInt(strValue);
            }

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return StrTool.cTrim(strValue);
    }

}
