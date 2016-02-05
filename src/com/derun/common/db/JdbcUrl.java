package com.derun.common.db;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-4
 *
 * JdbcUrl
 * @version
 */
public class JdbcUrl
{
	private static Logger log = Logger.getLogger(JdbcUrl.class.getSimpleName());
	static Properties prop;
	// @Constructor
	public JdbcUrl()
	{
		
		if(prop==null || prop.isEmpty()){
			try {
				prop = new Properties();
				prop.load(JdbcUrl.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			} catch (IOException e) {
				log.debug("加载数据库连接配置文件failed："+e.getLocalizedMessage());
				return ;
			}
			log.debug("加载数据库连接配置文件successfully.");
		}
		
		DBType = prop.getProperty("DBType");//"ORACLE";
		IP = prop.getProperty("IP");//"192.168.1.51";
		Port = prop.getProperty("Port");//"1521";
		DBName = prop.getProperty("DBName");//"orcl";
		UserName = prop.getProperty("UserName");//"tpl";
		PassWord = prop.getProperty("PassWord");//"tpl";
		
	}
    // @Constructor
	public JdbcUrl(String type)
    {
		
		Properties prop = new Properties();
		try {
			
			prop.load(JdbcUrl.class.getResourceAsStream("jdbc.properties"));
			
			DBType = type;//"ORACLE";
			IP = prop.getProperty("IP");//"192.168.1.51";
			Port = prop.getProperty("Port");//"1521";
			DBName = prop.getProperty("DBName");//"orcl";
			UserName = prop.getProperty("UserName");//"tpl";
			PassWord = prop.getProperty("PassWord");//"tpl";
			
		} catch (IOException e) {
			log.debug("加载数据库连接配置文件failed："+e.getLocalizedMessage());
			return ;
		}
		log.debug("加载数据库连接配置文件successfully.");
		
	}

    // @Field
    private String DBType;
    private String IP;
    private String Port;
    private String DBName;
    private String ServerName;
    private String UserName;
    private String PassWord;

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
        IP = aPort;
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

    public String getJdbcUrl() {
      StringBuffer sUrl = new StringBuffer(256);
      //Oracle连接句柄
      if (DBType.trim().toUpperCase().equals("ORACLE")) {
          sUrl.append("jdbc:oracle:thin:@");
          sUrl.append(IP);
          sUrl.append(":");
          sUrl.append(Port);
          sUrl.append(":");
          sUrl.append(DBName);
      }
      //InforMix连接句柄
      if (DBType.trim().toUpperCase().equals("INFORMIX")) {
          sUrl.append("jdbc:informix-sqli://");
          sUrl.append(IP);
          sUrl.append(":");
          sUrl.append(Port);
          sUrl.append("/");
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
      if (DBType.trim().toUpperCase().equals("SQLSERVER")) {
          sUrl.append("jdbc:inetdae:");
          sUrl.append(IP);
          sUrl.append(":");
          sUrl.append(Port);
          sUrl.append("?sql7=true&database=");
          sUrl.append(DBName);
          sUrl.append("&charset=gbk");
      }
      //WebLogicPool连接句柄
      if (DBType.trim().toUpperCase().equals("WEBLOGICPOOL")) {
          sUrl.append("jdbc:weblogic:pool:");
          sUrl.append(DBName);
      }
      //DB2连接句柄
      if (DBType.trim().toUpperCase().equals("DB2")) {
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
