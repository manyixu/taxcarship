package com.derun.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import com.derun.common.util.DateUtil;
import com.derun.common.util.StrTool;
import com.derun.common.util.SysConst;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;


/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-4
 *
 * 说明：数据库操作类
 * @version
 */
public class ExeSQL
{
	private static Logger log = Logger.getLogger(com.derun.common.db.ExeSQL.class.getName());
    private Connection con;

    /**
     * mflag = true: 传入Connection
     * mflag = false: 不传入Connection
     */
    private boolean mflag = false;


    // @Constructor
    public ExeSQL(Connection tConnection)
    {
        con = tConnection;
        mflag = true;
    }

    public ExeSQL()
    {
    }

    /**
     * 获取唯一的返回值
     * @param sql String
     * @return String
     */
    public String getOneValue(String sql)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String mValue = "";
        log.debug("ExecSQL : " + sql);

        //如果没有传入连接，则类创建
        if (!mflag)
        {
            try {
				con = DBConnPool.getConnection();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        try
        {
            pstmt = con.prepareStatement(StrTool.GBKToUnicode(sql), ResultSet.TYPE_FORWARD_ONLY
                    , ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                //其实并不是很合适，主要是因为有可能取得对象的数据类型有误
                mValue = rs.getString(1);
                break;
            }
            rs.close();
            pstmt.close();
            //如果连接是类创建的，则关闭连接
            if (!mflag)
            {
            	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
            }
        }
        catch (SQLException e)
        {
            // @@错误处理
            log.debug("### Error ExeSQL at OneValue: " + sql);
            //设置返回值
            mValue = "";
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    //由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try
                    {
                        pstmt.close();
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            log.debug("Sql's bug is very big: " + sql);
                            pstmt.close();
                        }
                        catch (SQLException ex)
                        {}
                    }
                }
                if (!mflag)
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            } catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Stringtools.log("",sql+"="+StrTool.cTrim(mValue),"");
        return StrTool.cTrim(mValue);
    }


    /**
     * 获取SQL的查询结果记录数
     * @param sql String
     * @param pstmt PreparedStatement
     * @param rs ResultSet
     * @return int
     */
    private int getResultCount(String sql, PreparedStatement pstmt, ResultSet rs)
    {
        int iCount = 0;
        //此方法对不同数据库通用
        sql = "select count(1) from (" + sql + ") rsc";
        log.debug("getResultCount : " + sql);

        try
        {
            pstmt = con.prepareStatement(StrTool.GBKToUnicode(sql), ResultSet.TYPE_FORWARD_ONLY
                    , ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
//            rs.next();
            //这样可以保证，没有查询到数据的时候，也返回正常
            while (rs.next())
            {
                iCount = rs.getInt(1);
                break;
            }
            rs.close();
            pstmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            // @@错误处理
            iCount = 0;
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    //由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try
                    {
                        pstmt.close();
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            log.debug("Sql's bug is very big: " + sql);
                            pstmt.close();
                        }
                        catch (SQLException ex)
                        {}
                    }
                }
                if (!mflag)
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (SQLException ex)
            {
                //可能出现连接没有关闭
            } catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        return iCount;
    }

    /**
     * 从指定位置查询全部数据
     * @param sql String
     * @param start int
     * @return String
     */
    public String getEncodedResult(String sql, int start)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        StringBuffer mResult = new StringBuffer(256); //modified by liuqiang
        log.debug("ExecSQL : " + sql);

        if (start <= 0)
        {
            start = 1;
        }

        if (!mflag)
        {
            try {
				con = DBConnPool.getConnection();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        try
        {
            pstmt = con.prepareStatement(StrTool.GBKToUnicode(sql), ResultSet.TYPE_FORWARD_ONLY
                    , ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            //查询字段的个数
            int n = rsmd.getColumnCount();
            //查询记录的数量
            int m = start + (SysConst.MAXSCREENLINES * SysConst.MAXMEMORYPAGES);

            // 取得总记录数
            int k = 0;
            while (rs.next())
            {
                k++;
                if ((k >= start) && (k < m))
                {
                    // only get record we needed
                    for (int j = 1; j <= n; j++)
                    {
                        if (j == 1)
                        {
                            mResult.append(getDataValue(rsmd, rs, j));
                        }
                        else
                        {
                            mResult.append(SysConst.PACKAGESPILTER +
                                    getDataValue(rsmd, rs, j));
                        }
                    }
                    mResult.append(SysConst.RECORDSPLITER);
                }
            }

            if (k >= start)
            {
                if (k > 10000)
                {
                    log.debug("建议采用大批量数据查询模式！");
                }
                //"0|"为查询成功标记，与CODEQUERY统一，MINIM修改
                mResult.insert(0, "0|" + String.valueOf(k) + SysConst.RECORDSPLITER);
                mResult.delete(mResult.length() - 1, mResult.length());
            }
            else
            {
                mResult.append("100|未查询到相关数据!");
            }
            rs.close();
            pstmt.close();
            if (!mflag)
            {
            	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
            }
        }
        catch (SQLException e)
        {
            log.debug("### Error ExeSQL at getEncodedResult(String sql, int start): "
                    + sql);
            e.printStackTrace();
            // @@错误处理
            mResult.setLength(0);
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    //由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try
                    {
                        pstmt.close();
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            log.debug("Sql's bug is very big: " + sql);
                            pstmt.close();
                        }
                        catch (SQLException ex)
                        {}
                    }
                }
                if (!mflag)
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            } catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return mResult.toString();
    }

    /**
     * 从指定位置查询全部数据，此方法为大数据量查询
     * @param sql String
     * @param start int
     * @return String
     */
    public String getEncodedResultLarge(String sql, int start)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        StringBuffer mResult = new StringBuffer(256); //modified by liuqiang

        if (start <= 0)
        {
            start = 1;
        }

        if (!mflag)
        {
            try {
				con = DBConnPool.getConnection();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        //取得总记录数  
        int iCount = getResultCount(sql, pstmt, rs);
        //如果记录数为0，表示没有查询的数据，这个时候，需要关闭连接
        if (iCount <= 0)
        {
            try
            {
                if (!mflag)
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //直接返回，查询结果为空
            return "100|未查询到相关数据!";
        }

        try
        {
            //查询记录的数量
            int m = start + (SysConst.MAXSCREENLINES * SysConst.MAXMEMORYPAGES);

            //根据数据库，查询指定范围数据集，采用此方法可以大幅度提高前台的分页查询效率
            StringBuffer tSBql = new StringBuffer();
            if (SysConst.DBTYPE.equals("ORACLE"))
            {
                tSBql.append("select * from (select rownum rnm,rs.* from (");
                tSBql.append(sql);
                tSBql.append(") rs where rownum < ");
                tSBql.append(m);
                tSBql.append(") rss where rnm >= ");
                tSBql.append(start);
            }
            else
            {
                tSBql.append("select * from (select rownumber() OVER () rnm ,rs.* from (");
                tSBql.append(sql);
                tSBql.append(") rs) rss WHERE rnm BETWEEN ");
                tSBql.append(start);
                tSBql.append(" and ");
                tSBql.append(m - 1);
            }

            log.debug("ExecSQL : " + tSBql.toString());

            pstmt = con.prepareStatement(StrTool.GBKToUnicode(tSBql.toString())
                    , ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            //查询字段的个数
            int n = rsmd.getColumnCount();

            int k = 0; //用来判定是否有数据
            while (rs.next())
            {
                k++;
                //直接从位置2开始就ok了，呵呵，怎么没想到呢！！！
                for (int j = 2; j <= n; j++)
                {
                    if (j == 2)
                    {
                        mResult.append(getDataValue(rsmd, rs, j));
                    }
                    else
                    {
                        mResult.append(SysConst.PACKAGESPILTER +
                                getDataValue(rsmd, rs, j));
                    }
                }
                mResult.append(SysConst.RECORDSPLITER);
            }

            if (k > 0)
            {
                //"0|"为查询成功标记，与CODEQUERY统一，MINIM修改
                mResult.insert(0, "0|" + String.valueOf(iCount) + SysConst.RECORDSPLITER);
                mResult.delete(mResult.length() - 1, mResult.length());
            }
            else
            {
                mResult.append("100|未查询到相关数据!");
            }
            rs.close();
            pstmt.close();
            if (!mflag)
            {
            	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
            }
        }
        catch (SQLException e)
        {
            log.debug("### Error ExeSQL at getEncodedResultLarge(String sql, int start): "
                    + sql);
            e.printStackTrace();
            // @@错误处理
            mResult.setLength(0);
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    //由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try
                    {
                        pstmt.close();
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            log.debug("Sql's bug is very big: " + sql);
                            pstmt.close();
                        }
                        catch (SQLException ex)
                        {}
                    }
                }
                if (!mflag)
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            } catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return mResult.toString();
    }

    /**
     * 查询数据
     * @param sql String
     * @return String
     */
    public String getEncodedResult(String sql)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        StringBuffer mResult = new StringBuffer(256); //modified by liuqiang

        log.debug("ExecSQL : " + sql);
        
        if (!mflag)
        {
            try {
				con = DBConnPool.getConnection();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        try
        {
            pstmt = con.prepareStatement(StrTool.GBKToUnicode(sql), ResultSet.TYPE_FORWARD_ONLY
                    , ResultSet.CONCUR_READ_ONLY);
            //重新设置缓冲区，主要采用此中方式的查询数据量在几千左右
            pstmt.setFetchSize(500);
            rs = pstmt.executeQuery();
            rs.setFetchSize(500);
            rsmd = rs.getMetaData();

            int n = rsmd.getColumnCount();

            int k = 0;
            // 取得总记录数
            while (rs.next())
            {
                k++;
                for (int j = 1; j <= n; j++)
                {
                    if (j == 1)
                    {
                        mResult.append(getDataValue(rsmd, rs, j));
                    }
                    else
                    {
                        mResult.append(SysConst.PACKAGESPILTER);
                        mResult.append(getDataValue(rsmd, rs, j));
                    }
                }
                mResult.append(SysConst.RECORDSPLITER);
            }
            if (k > 0)
            {
                //"0|"为查询成功标记，与CODEQUERY统一，MINIM修改
                mResult.insert(0, "0|" + String.valueOf(k) + SysConst.RECORDSPLITER);
                mResult.delete(mResult.length() - 1, mResult.length());
            }
            else
            {
                mResult.append("100|未查询到相关数据！");
            }
            rs.close();
            pstmt.close();
            if (!mflag)
            {
            	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
            }
        }
        catch (SQLException e)
        {
            log.debug("### Error ExeSQL at getEncodedResult(String sql): " + sql);
            e.printStackTrace();
            // @@错误处理
            mResult.setLength(0);
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    //由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try
                    {
                        pstmt.close();
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            log.debug("Sql's bug is very big: " + sql);
                            pstmt.close();
                        }
                        catch (SQLException ex)
                        {}
                    }
                }
                if (!mflag)
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            } catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return mResult.toString();
    }

    /**
     * 从指定位置查询定量数据
     * @param sql String
     * @param start int
     * @param nCount int
     * @return String
     */
    public String getEncodedResult(String sql, int start, int nCount)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        StringBuffer mResult = new StringBuffer(256); //modified by liuqiang

        log.debug("ExecSQL : " + sql);
        
        if (start <= 0)
        {
            start = 1;
        }
        if (nCount <= 0)
        {
            nCount = 1;
        }
        
        if (!mflag)
        {
            try {
				con = DBConnPool.getConnection();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        //取得总记录数  
        try
        {
            pstmt = con.prepareStatement(StrTool.GBKToUnicode(sql), ResultSet.TYPE_FORWARD_ONLY
                    , ResultSet.CONCUR_READ_ONLY);

            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            int n = rsmd.getColumnCount();
            int m = start + nCount;
            // 取得总记录数
            int k = 0;
            while (rs.next())
            {
                k++;
                //如果超过要取的记录数，直接退出
                if (k >= m)
                {
                    break;
                }
                if ((k >= start) && (k < m))
                {
                    // only get record we needed
                    for (int j = 1; j <= n; j++)
                    {
                        if (j == 1)
                        {
                            mResult.append(getDataValue(rsmd, rs, j));
                        }
                        else
                        {
                            mResult.append(SysConst.PACKAGESPILTER).append(
                                    getDataValue(rsmd, rs, j));
                        }
                    }
                    mResult.append(SysConst.RECORDSPLITER);
                }
            }

            if (k >= start)
            {
                //"0|"为查询成功标记，与CODEQUERY统一，MINIM修改
                mResult.insert(0, "0|" + String.valueOf(k) + SysConst.RECORDSPLITER);
                mResult.delete(mResult.length() - 1, mResult.length());
            }
            else
            {
                mResult.append("100|未查询到相关数据！");
            }
            rs.close();
            pstmt.close();
            if (!mflag)
            {
            	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
            }
        }
        catch (SQLException e)
        {
            log.debug(
                    "### Error ExeSQL at getEncodedResult(String sql, int start, int nCount): "
                    + sql);
            e.printStackTrace();
            // @@错误处理
            mResult.setLength(0);
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    //由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try
                    {
                        pstmt.close();
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            log.debug("Sql's bug is very big: " + sql);
                            pstmt.close();
                        }
                        catch (SQLException ex)
                        {}
                    }
                }
                if (!mflag)
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            } catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return mResult.toString();
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

    /**
     * 输入：cSQL，在ExeSQL类初始化的时候建立连接。
     * 输出：如果成功执行，返回True，否则返回False，并且在Error中设置错误的详细信息
     * @param sql String
     * @return boolean
     */
    public boolean execUpdateSQL(String sql)
    {
        PreparedStatement pstmt = null;
        log.debug("ExecSQL : " + sql);

        
        if (!mflag)
        {
            try {
				con = DBConnPool.getConnection();
			} catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            // 当连接使用weblogic连接池,数据库是informix时, 要将AutoCommit设为false
            try
            {
                con.setAutoCommit(false);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                try
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    return false;
                }
                finally
                {
                }
            }
            // add over
        }

        try
        {

            //这里是否可以修改，还需要测试一下
            pstmt = con.prepareStatement(StrTool.GBKToUnicode(sql), ResultSet.TYPE_FORWARD_ONLY
                    , ResultSet.CONCUR_UPDATABLE);

            pstmt.executeUpdate();
            //int operCount = pstmt.executeUpdate();
            pstmt.close();

            if (!mflag)
            {
                con.commit();
                ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
            }
        }
        catch (SQLException e)
        {
            // @@错误处理
            log.debug("### Error ExeSQL at execUpdateSQL: " + sql);

            try
            {
                if (pstmt != null)
                {
                    //由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try
                    {
                        pstmt.close();
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            log.debug("Sql's bug is very big: " + sql);
                            pstmt.close();
                        }
                        catch (SQLException ex)
                        {}
                    }
                }
                if (!mflag)
                {
                    con.rollback();
                    ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (SQLException ex)
            {
                //在这个地方，有可能会没有关闭连接
                ex.printStackTrace();
                return false;
            } catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            return false;
        } catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return true;
    }

    /**
     * 功能：可以执行输入的任意查询SQL语句。
     * 输入：任意一个查询语句的字符串csql
     * 返回：一个SSRS类的实例，内为查询结果
     * @param sql String
     * @return SSRS
     */
    public SSRS execSQL(String sql)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        SSRS tSSRS = null;

        log.debug("ExecSQL : " + sql);
        
        if (!mflag)
        {
            try {
				con = DBConnPool.getConnection();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        try
        {
            pstmt = con.prepareStatement(StrTool.GBKToUnicode(sql), ResultSet.TYPE_FORWARD_ONLY
                    , ResultSet.CONCUR_READ_ONLY);

            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();

            int n = rsmd.getColumnCount();
            tSSRS = new SSRS(n);

            // 取得总记录数
            while (rs.next())
            {
                for (int j = 1; j <= n; j++)
                {
                    tSSRS.SetText(getDataValue(rsmd, rs, j));
                }
            }

            rs.close();
            pstmt.close();

            if (!mflag)
            {
            	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
            }
        }
        catch (SQLException e)
        {
            log.debug("### Error ExeSQL at execSQL(String sql): " + sql);
            e.printStackTrace();

            // @@错误处理

            tSSRS = null;

            //      tSSRS.ErrorFlag = true;
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    //由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try
                    {
                        pstmt.close();
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            log.debug("Sql's bug is very big: " + sql);
                            pstmt.close();
                        }
                        catch (SQLException ex)
                        {}
                    }
                }
                if (!mflag)
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            } catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return tSSRS;
    }

    public SSRS execSQL(String sql, int start, int nCount)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        SSRS tSSRS = null;

        log.debug("ExecSQL : " + sql);
        
        if (start <= 0)
        {
            start = 1;
        }

        if (nCount <= 0)
        {
            nCount = 1;
        }

        
        if (!mflag)
        {
            try {
				con = DBConnPool.getConnection();
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        try
        {
            pstmt = con.prepareStatement(StrTool.GBKToUnicode(sql), ResultSet.TYPE_FORWARD_ONLY
                    , ResultSet.CONCUR_READ_ONLY);

            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();

            int n = rsmd.getColumnCount();
            tSSRS = new SSRS(n);

            int m = start + nCount;
            int k = 0;

            // 取得总记录数
            while (rs.next())
            {
                k++;

                // 只取特定范围内的记录行
                if ((k >= start) && (k < m))
                {
                    for (int j = 1; j <= n; j++)
                    {
                        tSSRS.SetText(getDataValue(rsmd, rs, j));
                    }
                }
            }

            rs.close();
            pstmt.close();

            if (!mflag)
            {
            	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
            }
        }
        catch (SQLException e)
        {
            log.debug("### Error ExeSQL at execSQL(String sql, int start, int nCount): "
                    + sql);
            e.printStackTrace();

            // @@错误处理

            tSSRS = null;

            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    //由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try
                    {
                        pstmt.close();
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            log.debug("Sql's bug is very big: " + sql);
                            pstmt.close();
                        }
                        catch (SQLException ex)
                        {}
                    }
                }
                if (!mflag)
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            } catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return tSSRS;
    }
    
    /**
     * 批量执行更新SQL语句
     * @param sqlArr
     * @return
     */
    public boolean exeSqlBatch(String[] sqlArr){

        PreparedStatement pstmt = null;

        
        if (!mflag)
        {
            try {
				con = DBConnPool.getConnection();
			} catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            // 当连接使用weblogic连接池,数据库是informix时, 要将AutoCommit设为false
            try
            {
                con.setAutoCommit(false);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                try
                {
                	ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    return false;
                }
                finally
                {
                }
            }
            // add over
        }

        String currentSql = null;
        
        try
        {
        	for(int i = 0; i<sqlArr.length;	i++){
        		
        		currentSql = sqlArr[i];
        		log.debug("ExecSQL : " + currentSql);
        		
        		//这里是否可以修改，还需要测试一下
        		pstmt = con.prepareStatement(StrTool.GBKToUnicode(currentSql), ResultSet.TYPE_FORWARD_ONLY
        				, ResultSet.CONCUR_UPDATABLE);
        		pstmt.executeUpdate();
        		//int operCount = pstmt.executeUpdate();
        		pstmt.close();
        		
        	}


            if (!mflag)
            {
                con.commit();
                ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
            }
        }
        catch (SQLException e)
        {
            // @@错误处理
            log.debug("### Error ExeSQL at execUpdateSQL: " + currentSql);

            try
            {
                if (pstmt != null)
                {
                    //由于描述的问题，导致执行的sql错误百出，因此pstmt的关闭需要特殊处理
                    try
                    {
                        pstmt.close();
                    }
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            log.debug("Sql's bug is very big: " + currentSql);
                            pstmt.close();
                        }
                        catch (SQLException ex)
                        {}
                    }
                }
                if (!mflag)
                {
                    con.rollback();
                    ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, con);
                }
            }
            catch (SQLException ex)
            {
                //在这个地方，有可能会没有关闭连接
                ex.printStackTrace();
                return false;
            } catch (NoFreeConnectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            return false;
        } catch (NoFreeConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return true;
    
    }

    public static void main(String[] args)
    {
//        log.debug(PubFun1.CreateMaxNo("zhuxf", "86110001"));
        ExeSQL tExeSQL = new ExeSQL();
        for (int i = 1; i <= 70; i++)
        {
//            tExeSQL.getOneValue("null");
            tExeSQL.execSQL("select * from zhuxf");
        }
    }
}
