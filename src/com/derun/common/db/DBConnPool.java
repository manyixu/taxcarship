package com.derun.common.db;

import org.apache.log4j.*;

import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-4
 *
 * 说明：数据库连接池
 * @version
 */
public class DBConnPool
{
	private static Logger log = Logger.getLogger(com.derun.common.db.DBConnPool.class.getName());
    //虽然没有调用该对象，但是没有的话，无法连接到数据库，采用一种很古怪的方式来实现类的加载
//    private static DBConnPool dbconnpool = new DBConnPool();

    //连接池对象
    private static DBConn[] dbConns;
    //设置应用程序的最大连接数，可相对扩充，但是会常驻内存，占用空间，因此大小需要适度
    private static final int nConnCount = 600;
    
    private static DataSource dataSource;

    // @Constructor
//    static
//    {
//        dbConns = new DBConn[nConnCount];
//        for (int nIndex = 0; nIndex < nConnCount; nIndex++)
//        {
//            dbConns[nIndex] = new DBConn();
//        }
//    }

    //构建函数
    private DBConnPool()
    {}
    /**
     * 获取连接
     * jinsh add
     * usefor fetch conn from a diffrent way so we can conn to DATACHANGE System
     * @return DBConn
     */
    static public DBConn getConnectionO(String uri)
    {
        DBConn dbConn = new DBConn(uri);
        if (!dbConn.createConnection())
        {
            //如果创建连接失败
            DBSemaphore.UnLock();
            return null;
        }
        try
        {
            //特殊处理连接的AutoCommit是否已经被设置
            dbConn.setAutoCommit(true);
            dbConn.setInUse();
            DBSemaphore.UnLock();
            return dbConn;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

            DBSemaphore.UnLock();

            return null;
        }


    }
    static public Connection getConnection() throws NoFreeConnectionException {
    	
    	Connection conn = null;
    	
    	conn = ConnectDBBean.getConnection(ReadWriteDBPool.readPool);
//		try {
//			if(dataSource == null){
//				dataSource = DataSourceUtil.getDataSource(0);
//			}
//			conn = dataSource.getConnection();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		return conn;
    }
    /**
     * 获取连接
     * @return DBConn
     */
    static public DBConn getConnectionO()
    {
        JdbcUrl JUrl = new JdbcUrl();
        //为了支持WebLogic、apache、WebSphere的连接池，在得到连接的最前面判断
        if (JUrl.getDBType().toUpperCase().equals("WEBLOGICPOOL")
                || JUrl.getDBType().toUpperCase().equals("COMMONSDBCP")
                || JUrl.getDBType().toUpperCase().equals("WEBSPHERE"))
        {
            DBConn tDBConn = new DBConn();
            if (tDBConn.createConnection())
            {
            	tDBConn.setInUse();
                return tDBConn;
            }
            else
            {
                return null;
            }
        }

        try
        {
            DBSemaphore.Lock();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }

        //如果没有返回，则表示使用的是自己的连接池
        for (int nIndex = 0; nIndex < nConnCount; nIndex++)
        {
            DBConn dbConn = dbConns[nIndex];
            //判定连接是否被使用
            if (dbConn.isInUse())
            {
                continue;
            }
            if (!dbConn.createConnection())
            {
                //如果创建连接失败
                DBSemaphore.UnLock();
                return null;
            }
            //如果连接数超过1的话，才输出，否则输出太多，很烦人
            if (nIndex >= 1)
            {
                log.debug("DBConnPool : get connection, index is " +
                        String.valueOf(nIndex));
            }
            try
            {
                //特殊处理连接的AutoCommit是否已经被设置
                dbConn.setAutoCommit(true);
                dbConn.setInUse();
                DBSemaphore.UnLock();
                return dbConn;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();

                DBSemaphore.UnLock();

                return null;
            }
        }
        //如果全部的连接数都被占用的话，应用程序出错
        log.debug("DBConnPool : All connections are in use");
        //如果为了应用正常流转，可以在这里强制释放掉全部的应用连接数
        DBSemaphore.UnLock();
        return null;
    }

    /**
     * 连接查看
     * @param os OutputStream
     */
    public static void dumpConnInfo(OutputStream os)
    {
        try
        {
            if (dbConns == null)
            {
                os.write("all connections are free".getBytes());
                return;
            }

            for (int nIndex = 0; nIndex < nConnCount; nIndex++)
            {
                DBConn dbConn = dbConns[nIndex];

                os.write((String.valueOf(nIndex) +
                        "------------------------------------\r\n\r").
                        getBytes());
                if (dbConn != null && dbConn.isInUse())
                {
                    dbConn.dumpConnInfo(os);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    protected static DBConn[] getDBConns()
    {
        return dbConns;
    }
}
