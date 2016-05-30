package com.derun.gt3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import org.apache.log4j.Logger;

import com.derun.common.db.SSRS;
import com.derun.common.util.DateUtil;
import com.derun.common.util.StrTool;


public class Gt3DBUtil {
	
	private static Logger log = Logger.getLogger(Gt3DBUtil.class);
	private PooledConnection dbpool;
	private static Gt3DBUtil gt3DBUtil = null;
	private static String configPath = "/gt3db.properties";
	private static Map<String, String> proMap = new HashMap<String, String>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static SimpleDateFormat sdfd = new SimpleDateFormat("yyyyMMdd");
	

	private Gt3DBUtil(){
		File file = new File(Thread.currentThread().getContextClassLoader().getResource("/").getPath()+configPath);
		InputStream is = null; 
		Properties pro = new Properties();
		try {
			is = new FileInputStream(file);
			pro.load(is);
			Enumeration e = pro.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = (String) pro.get(key);
				proMap.put(key, value);

			}
			
			if (dbpool == null) {
				OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
				ocpds.setURL(proMap.get("url"));
				ocpds.setUser(proMap.get("username"));
				ocpds.setPassword(proMap.get("password"));
				dbpool = ocpds.getPooledConnection();
			}
			
		} catch (IOException e) {
			log.error("Error in IO : "+e);
		} catch (Exception ex) {
			log.error("Error in PooledSQL-construct : "+ex);
		} finally {
			try {
				is.close();// 千万别忘了关闭资源哦！
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Gt3DBUtil getInstance(){
		if(gt3DBUtil==null){
			gt3DBUtil = new Gt3DBUtil();
		}
		return gt3DBUtil;
	}


	/**
	 * 用于查询的SQL语句
	 * 
	 * @param SQL
	 *            SQL语句字串，如：select * from tablename
	 */
	public SSRS Query(String SQL) {
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Connection connection = null;
		SSRS ssrs = null;
		try {
			connection = dbpool.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(SQL);
			rsmd = rs.getMetaData();
			int n = rsmd.getColumnCount();
			ssrs = new SSRS(n);
			while (rs.next()) {
				for (int j = 1; j <= n; j++) {
					ssrs.SetText(getDataValue(rsmd, rs, j));
				}
			}
		} catch (Exception ex) {
			log.error("Error in Query - Sql's bug is very big : "+ex);
			write2File("/gt3errorsql"+sdfd.format(new Date())+".log", sdf.format(new Date())+"\n"+SQL+"\n\n");
		}finally{
			close(stmt, rs, connection);
			//finalize();//用哪个，是否有必要
		}
		return ssrs;
	} // end Query
	
	
	protected void finalize() {
		if (dbpool != null) {
			try {
				dbpool.close();
			} catch (Exception ex) {
			}
		}
	}


	public final Connection getConnection(String connName) {
		try {
			Connection conn = dbpool.getConnection();
			return conn;
		} catch (SQLException e) {
			log.error("can't get the connection :" + e);
			throw new RuntimeException("unable to connect to the database ", e);
		}
	}


	public final void close(Statement pstmt, ResultSet rs, Connection conn) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			log.error("close gt3 connection error ", e);
		}
	}
	
    /**
     * 把ResultSet中取出的数据转换为相应的数据值字符串
     * 输出：如果成功执行，返回True，否则返回False，并且在Error中设置错误的详细信息
     * @param rsmd ResultSetMetaData
     * @param rs ResultSet
     * @param i int
     * @return String
     */
    public String getDataValue(ResultSetMetaData rsmd, ResultSet rs, int i)
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
    
	private static void write2File(String path, String strbuf) {

		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					path)), true);
			pw.println(strbuf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
}
