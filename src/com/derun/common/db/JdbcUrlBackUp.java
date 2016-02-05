package com.derun.common.db;

import org.apache.log4j.*;



/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-4
 *
 * 说明：构建Jdbc的url
 * @version
 */
public class JdbcUrlBackUp
{
	private static Logger log = Logger.getLogger(com.derun.common.db.JdbcUrlBackUp.class.getName());
    // @Field
    private String DBType;
    private String IP;
    private String Port;
    private String DBName;
    private String ServerName;
    private String UserName;
    private String PassWord;


    // @Constructor
    public JdbcUrlBackUp()
    {
        //WebLogic连接池，其中MyPool为连接池的名称
//        DBType = "WEBLOGICPOOL";
//        DBName = "MyPool";

        //WebSphere连接池，其中MET为连接池的名称
//        DBType = "WEBSPHERE";
//        DBName = "MET";

        //apache连接池
//        DBType = "COMMONSDBCP";
//        DBName = "DB2";

//        DBType = "INFORMIX";
//        IP = "172.16.120.201";
//        Port = "12000";
//        DBName = "lis";
//        ServerName = "server_intel";
//        UserName = "informix";
//        PassWord = "informix";

//        DBType = "ORACLE";
//        IP = "192.168.71.244";
//        Port = "1521";
//        DBName = "lis";
//        UserName = "lis";
//        PassWord = "lis";

//        DBType = "DB2";
//        IP = "192.168.71.244";
//        Port = "50000";
//        DBName = "circ";
//        UserName = "lis";
//        PassWord = "lis";
//
//		DBType = "ORACLE";
//		IP = "192.168.1.7";
//		Port = "1521";
//		DBName = "lis";
//		UserName = "lis";
//		PassWord = "lis";
		 DBType = "ORACLE";
	      IP = "10.3.176.90";
	      Port = "1521";
	      DBName = "pension";
	      UserName = "pension_dev";
	      PassWord = "pension";
	      

//			DBType = "ORACLE";
//			IP = "192.168.1.10";
//			Port = "1521";
//			DBName = "pos";
//			UserName = "ncl";
//			PassWord = "caofang";

//		DBType = "ORACLE";
//		IP = "192.168.1.18";
//		Port = "1521";
//		DBName = "tech";
//		UserName = "ncl";
//		PassWord = "caofang";

//		DBType = "ORACLE";
//		IP = "192.168.1.11";
//		Port = "1521";
//		DBName = "nb";
//		UserName = "ncl";
//		PassWord = "caofang";

//		DBType = "ORACLE";
//		IP = "192.168.1.12";
//		Port = "1521";
//		DBName = "rn";
//		UserName = "ncl";
//		PassWord = "caofang";

//		DBType = "ORACLE";
//		IP = "192.168.1.13";
//		Port = "1521";
//		DBName = "claim";
//		UserName = "ncl";
//		PassWord = "caofang";

//		DBType = "ORACLE";
//		IP = "192.168.1.14";
//		Port = "1521";
//		DBName = "agent";
//		UserName = "ncl";
//		PassWord = "caofang";

//		DBType = "ORACLE";
//		IP = "192.168.1.21";
//		Port = "1521";
//		DBName = "lisdb";
//		UserName = "lis";
//		PassWord = "lis";

    }


    // @Method
    public String getDBType()
    {
        return DBType;
    }

    public String getIP()
    {
        return IP;
    }

    public String getPort()
    {
        return Port;
    }

    public String getDBName()
    {
        return DBName;
    }

    public String getServerName()
    {
        return ServerName;
    }

    public String getUserName()
    {
        return UserName;
    }

    public String getPassWord()
    {
        return PassWord;
    }

    public void setDBType(String aDBType)
    {
        DBType = aDBType;
    }

    public void setIP(String aIP)
    {
        IP = aIP;
    }

    public void setPort(String aPort)
    {
        Port = aPort;
    }

    public void setDBName(String aDBName)
    {
        DBName = aDBName;
    }

    public void setServerName(String aServerName)
    {
        ServerName = aServerName;
    }

    public void setUser(String aUserName)
    {
        UserName = aUserName;
    }

    public void setPassWord(String aPassWord)
    {
        PassWord = aPassWord;
    }


    /**
     * 获取连接句柄
     * @return String
     */
    public String getJdbcUrl()
    {
        //        String sUrl = "";
        StringBuffer sUrl = new StringBuffer(256);
        //Oracle连接句柄
        if (DBType.trim().toUpperCase().equals("ORACLE"))
        {
//            sUrl = "jdbc:oracle:thin:@" + IP + ":"
//                   + Port + ":"
//                   + DBName;
            sUrl.append("jdbc:oracle:thin:@");
            sUrl.append(IP);
            sUrl.append(":");
            sUrl.append(Port);
            sUrl.append(":");
            sUrl.append(DBName);
        }
        //InforMix连接句柄
        if (DBType.trim().toUpperCase().equals("INFORMIX"))
        {
//            sUrl = "jdbc:informix-sqli://" + IP + ":"
//                   + Port + "/"
//                   + DBName + ":"
//                   + "informixserver=" + ServerName + ";"
//                   + "user=" + UserName + ";"
//                   + "password=" + PassWord + ";";
            sUrl.append("jdbc:informix-sqli://");
            sUrl.append(IP);
            sUrl.append(":");
            sUrl.append(Port);
            sUrl.append(DBName);
            sUrl.append(":");
            sUrl.append("informixserver=");
            sUrl.append(ServerName);
            sUrl.append(";");
            sUrl.append("user=");
            sUrl.append(UserName);
            sUrl.append(";");
            sUrl.append("password=");
            sUrl.append(PassWord);
            sUrl.append(";");
        }
        //SqlServer连接句柄
        if (DBType.trim().toUpperCase().equals("SQLSERVER"))
        {
//            sUrl = "jdbc:inetdae:" + IP + ":"
//                   + Port + "?sql7=true&database=" + DBName + "&charset=gbk";
            sUrl.append("jdbc:inetdae:");
            sUrl.append(IP);
            sUrl.append(":");
            sUrl.append(Port);
            sUrl.append("?sql7=true&database=");
            sUrl.append(DBName);
            sUrl.append("&charset=gbk");
        }
        //WebLogicPool连接句柄
        if (DBType.trim().toUpperCase().equals("WEBLOGICPOOL"))
        {
//            sUrl = "jdbc:weblogic:pool:" + DBName;
            sUrl.append("jdbc:weblogic:pool:");
            sUrl.append(DBName);
        }
        //DB2连接句柄
        if (DBType.trim().toUpperCase().equals("DB2"))
        {
//            sUrl = "jdbc:db2://" + IP + ":"
//                   + Port + "/"
//                   + DBName;
            sUrl.append("jdbc:db2://");
            sUrl.append(IP);
            sUrl.append(":");
            sUrl.append(Port);
            sUrl.append("/");
            sUrl.append(DBName);
        }
        return sUrl.toString();
    }
}
