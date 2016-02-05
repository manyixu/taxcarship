package com.derun.common.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.beanutils.BeanUtils;

import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;
import com.derun.model.po.SYJK_CCS_CCSXX;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-6-5
 *
 * 说明
 * @version
 */
public class BaseDao {
	
	ExeSQL exeSql = new ExeSQL();

	    /** 
	     * 添加操作 
	     */  
	    public boolean save(Object entity) throws SecurityException,  
	            NoSuchMethodException, IllegalArgumentException,  
	            IllegalAccessException, SQLException, ClassNotFoundException {  
	        String insertSql = getInsertSql(entity);
	        return exeSql.execUpdateSQL(String.valueOf(insertSql));
	        
	    }  

		/**
		 * 持久化对象数组（同一事务）
		 * @param entityArray
		 * @return
		 */
		public boolean saveBatch(Object[] entityArray) throws SecurityException,
				NoSuchMethodException, IllegalArgumentException,
				IllegalAccessException, SQLException, ClassNotFoundException {
			
			String[] sqlArr = new String[entityArray.length];
			for(int i=0; i<=entityArray.length; i++){
				sqlArr[i] = getInsertSql(entityArray[i]);
			}
			return exeSql.exeSqlBatch(sqlArr);
	
		}
	    
	    private String getInsertSql(Object entity) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException{
	    	StringBuffer insertSql = new StringBuffer("insert into ");  
	        StringBuffer insertSqlValue = new StringBuffer();  
	        LinkedList<Object> insertParams = new LinkedList<Object>();  
	        Class<?> entityClass = entity.getClass();  
	        String tableName = getTableName(entityClass);  
	        insertSql.append(tableName);  
	        Field[] fields = entityClass.getDeclaredFields();  
	        insertSql.append("(");  
	        insertSqlValue.append(" values(");  
	        for (Field field : fields) {  
	            Annotation[] annotations = field.getAnnotations();  
	            String columnName = field.getName();  
	            // 查找当前属性上面是否有annotation注解  
	            Object[] findAnnotationResult = findAnnotation(annotations);  
	            Boolean isAnnotaionOverField = (Boolean) findAnnotationResult[0];  
	            // 如果在field中上面没有找到annotation,继续到get属性上去找有没有annotation  
	            if (!isAnnotaionOverField) {  
	                // 拼接出field的get属性名  
	                String getMethodName = "get"  
	                        + columnName.substring(0, 1).toUpperCase()  
	                        + columnName.substring(1);  
	                Method method = entityClass.getMethod(getMethodName,  
	                        new Class[] {});  
	                // 同上判断这个方法有没有我们要找的annotation  
	                annotations = method.getAnnotations();  
	                findAnnotationResult = findAnnotation(annotations);  
	                isAnnotaionOverField = (Boolean) findAnnotationResult[0];  
	            }  
	            // 判断通过前面两步操作有没有在当前的字段上面找到有效的annotation  
	            if (isAnnotaionOverField)  
	                continue;  
	            // 到这步说明在当前的字段或字段get属性上面找到有效的annotation了  
	            // 拼接insert sql 语句  
	            String tempColumnName = (String) findAnnotationResult[1];  
	            if (tempColumnName != null && !"".equals(tempColumnName))  
	                columnName = tempColumnName;  
	            insertSql.append(columnName).append(",");// 前面列名部分  
	            insertSqlValue.append("?,"); // 后面?参数部分  
	            // 得到对应的字段值并记录,作为以后?部分值  
	            field.setAccessible(true);  
	            insertParams.add(field.get(entity));  
	        }  
	        insertSql.replace(insertSql.lastIndexOf(","), insertSql.length(), ")");  
	        insertSqlValue.replace(insertSqlValue.lastIndexOf(","), insertSqlValue  
	                .length(), ")");  
	        // 拼接两部分的sql  
	        insertSql.append(insertSqlValue);  
	        System.out.println(insertSql);  
	        int i = 1;  
	        for (Object param : insertParams) {  
	            if (param instanceof Date) {  
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	                String datePara = dateFormat.format(param);
	                insertSql.replace(insertSql.indexOf("?"), insertSql.indexOf("?")+1, "to_date('"+datePara+"','yyyy-MM-dd')");
	            } else if(param instanceof Double){
	            } else {  
	                insertSql.replace(insertSql.indexOf("?"), insertSql.indexOf("?")+1, "'"+String.valueOf(param)+"'");
	            }  
	            i++;  
	        }
	        return String.valueOf(insertSql);
	    }
	    
	    /** 
	     * 得到表的真实名 
	     */  
	    private String getTableName(Class<?> entityClass) {  
	        String tableName = entityClass.getSimpleName();  
//	        if (entityClass.isAnnotationPresent(Entity.class)) {  
//	            Entity entityAnnotation = entityClass.getAnnotation(Entity.class);  
//	            String tempTableName = entityAnnotation.name();  
//	            if (tempTableName != null && !"".equals(tempTableName))  
//	                tableName = tempTableName;  
//	        }  
	        return tableName;  
	    }  
	    /** 
	     * 查询字段或是属性上面有没有有效annotation 
	     */  
	    private Object[] findAnnotation(Annotation[] annotations) {  
	        Object[] resurlt = new Object[] { false, null };  
	        if (annotations.length == 0)  
	            return resurlt;  
	        for (Annotation annotation : annotations) {  
	            // 我们假定当他找到下列标签中任何一个标签就认为是要与数据库映射的  
	            if (annotation instanceof Column) {  
	                resurlt[0] = true;  
	                Column column = (Column) annotation;  
	                String tempColumnName = column.name();  
	                if (tempColumnName != null && !"".equals(tempColumnName))  
	                    resurlt[1] = tempColumnName;  
	            }  
	        }  
	        return resurlt;  
	    }  
	  
	    /** 
	     *  修改操作 
	     */  
	    public boolean update(Object entity) throws SecurityException,  
	            NoSuchMethodException, IllegalArgumentException,  
	            IllegalAccessException, ClassNotFoundException, SQLException {  
	        StringBuffer updateSql = new StringBuffer("update ");  
	        LinkedList<Object> updateParams = new LinkedList<Object>();  
	        String primaryKeyColumn = "";  
	        Integer primaryParam = null;  
	        Class<?> entityClass = entity.getClass();  
	        String tableName = getTableName(entityClass);  
	  
	        updateSql.append(tableName).append(" tab set ");  
	        Field[] fields = entityClass.getDeclaredFields();  
	        for (Field field : fields) {  
	            String columnName = field.getName();  
	            Annotation[] annotations = field.getAnnotations();  
	            // 判断是否是主键  
	            boolean isfindPrimarykey = false;  
	            for (Annotation annotation : annotations) {  
	                if (annotation instanceof Id) {  
	                    primaryKeyColumn = field.getName();  
	                    field.setAccessible(true);  
	                    primaryParam = (Integer) field.get(entity);  
	                    isfindPrimarykey = true;  
	                    break;  
	                }  
	  
	            }  
	            if (isfindPrimarykey)  
	                continue;  
	            Object[] findAnnotationResult = findAnnotation(annotations);  
	            boolean isAnnotaionOverField = (Boolean) findAnnotationResult[0];  
	            if (!isAnnotaionOverField) {  
	                String getMethodName = "get"  
	                        + columnName.substring(0, 1).toUpperCase()  
	                        + columnName.substring(1);  
	                Method method = entityClass.getMethod(getMethodName,  
	                        new Class[] {});  
	                annotations = method.getAnnotations();  
	                findAnnotationResult = findAnnotation(annotations);  
	                isAnnotaionOverField = (Boolean) findAnnotationResult[0];  
	            }  
	            if (!isAnnotaionOverField)  
	                continue;  
	            String tempColumnName = (String) findAnnotationResult[1];  
	            if (tempColumnName != null && !"".equals(tempColumnName))  
	                columnName = tempColumnName;  
	            updateSql.append("tab.").append(columnName).append("=?,");  
	            field.setAccessible(true);  
	            updateParams.add(field.get(entity));  
	        }  
	        updateSql.replace(updateSql.lastIndexOf(","), updateSql.length(), "");  
	        updateSql.append(" where tab.").append(primaryKeyColumn).append("=?");  
	        System.out.println(updateSql);  
	  
	        int i = 1;  
	        for (Object param : updateParams) {  
	            if (param instanceof Date) {  
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	                String datePara = dateFormat.format(param);
	                updateSql.replace(updateSql.indexOf("?"), updateSql.indexOf("?")+1, "to_date('"+datePara+"','yyyy-MM-dd')");
	            } else if(param instanceof Double){
	            } else {  
	            	updateSql.replace(updateSql.indexOf("?"), updateSql.indexOf("?")+1, "'"+String.valueOf(param)+"'");
	            }  
	            i++;  
	        } 
	        
	        return exeSql.execUpdateSQL(String.valueOf(updateSql));  
	    }  
	  
	    /** 
	     *  删除操作 
	     */  
	    public boolean delete(Object entity) throws IllegalArgumentException,  
	            IllegalAccessException, ClassNotFoundException, SQLException {  
	        // delete from stuInfo stu where stu.stuid=43  
	        StringBuffer deleteSql = new StringBuffer("delete from ");  
	        Integer primaryParam = null;  
	        Class<?> entityClass = entity.getClass();  
	        String tableName = getTableName(entityClass);  
	        deleteSql.append(tableName).append(" tab ").append("where ");  
	        Field[] fields = entityClass.getDeclaredFields();  
	        for (Field field : fields) {  
	            Annotation[] annotations = field.getAnnotations();  
	            boolean isfindPrimary = false;  
	            for (Annotation annotation : annotations) {  
	                if (annotation instanceof Id) {  
	                    deleteSql.append("tab.").append(field.getName()).append(  
	                            "=?");  
	                    field.setAccessible(true);  
	                    primaryParam = (Integer) field.get(entity);  
	                    isfindPrimary = true;  
	                    break;  
	                }  
	            }  
	            if (isfindPrimary)  
	                break;  
	        }  
	        Connection conn = null;
	        PreparedStatement prep = null ;
			try {
				conn = DBConnPool.getConnection();
				System.out.println(deleteSql.toString());  
				prep = conn.prepareStatement(deleteSql.toString());  
				prep.setInt(1, primaryParam);  
				if (prep.executeUpdate() > 0)  
					return true;  
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(prep != null){
					prep.close();
				}
				try {
					ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn); //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
				} catch (NoFreeConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
			} 
	        return false;  
	    }  
	  
	    /** 
	     * 根据Id查询某个实体对象 
	     */  
	    public <T> T get(Class<T> entityClass, Integer id)  
	            throws ClassNotFoundException, SQLException,  
	            InstantiationException, IllegalAccessException, SecurityException,  
	            NoSuchMethodException, IllegalArgumentException,  
	            InvocationTargetException {  
	        T entity = null;  
	        StringBuffer selectByIdSql = new StringBuffer("select * from ");  
	        String tableName = getTableName(entityClass);  
	        selectByIdSql.append(tableName).append(" tab where tab.");  
	        Field[] fields = entityClass.getDeclaredFields();  
	        for (Field field : fields) {  
	            Annotation[] annotations = field.getAnnotations();  
	            boolean isfindPrimaryfield = false;  
	            String columnName = field.getName();  
	            for (Annotation annotation : annotations) {  
	                if (annotation instanceof Id) {  
	                    selectByIdSql.append(columnName).append("=?");  
	                    isfindPrimaryfield = true;  
	                    break;  
	                }  
	            }  
	            if (!isfindPrimaryfield) {  
	                String getMethodName = "get"  
	                        + columnName.substring(0, 1).toUpperCase()  
	                        + columnName.substring(1);  
	                Method getMethod = entityClass.getMethod(getMethodName,  
	                        new Class[] {});  
	                annotations = getMethod.getAnnotations();  
	                for (Annotation annotation : annotations) {  
	                    if (annotation instanceof Id) {  
	                        selectByIdSql.append(columnName).append("=?");  
	                        isfindPrimaryfield = true;  
	                        break;  
	                    }  
	                }  
	            }  
	            if (isfindPrimaryfield)  
	                break;  
	  
	        }  
	        System.out.println(selectByIdSql.toString());  
	        Connection conn = null;
	        PreparedStatement prep = null ;
	        ResultSet rs = null ;
			try {
				conn = DBConnPool.getConnection();
				prep = conn  
				.prepareStatement(selectByIdSql.toString());  
				prep.setInt(1, id);  
				rs = prep.executeQuery();  
				while (rs.next()) {  
					entity = setData2Entity(entityClass, fields, rs);  
				}  
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try {
					if(rs != null){
						rs.close();
					}
					if(prep != null){
						prep.close();
					}
					ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);	//  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
				} catch (NoFreeConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
			}
	        return entity;  
	    }  
	    /** 
	     *装result中的数据据,用反射加入到对应的实体中  
	     */  
	    private <T> T setData2Entity(Class<T> entityClass, Field[] fields,  
	            ResultSet result) throws InstantiationException,  
	            IllegalAccessException, NoSuchMethodException, SQLException,  
	            InvocationTargetException {  
	        // 把数据组拼到对象中去  
	        T entity = entityClass.newInstance();  
	        for (Field field : fields) {  
	            String fieldName = field.getName();  
	            String columnName = fieldName;  
	            Annotation[] annotations = field.getAnnotations();  
	            Object[] findAnnotationResult = findAnnotation(annotations);  
	            boolean isfindAnotation = (Boolean) findAnnotationResult[0];  
	            if (!isfindAnotation) {  
	                String getMethodName = "get"  
	                        + fieldName.substring(0, 1).toUpperCase()  
	                        + fieldName.substring(1);  
	                Method method = entityClass.getMethod(getMethodName,  
	                        new Class[] {});  
	                annotations = method.getAnnotations();  
	                findAnnotationResult = findAnnotation(annotations);  
	                isfindAnotation = (Boolean) findAnnotationResult[0];  
	            }  
	            String tempColumnName = (String) findAnnotationResult[1];  
	            if (tempColumnName != null && !"".equals(tempColumnName))  
	                columnName = tempColumnName;  
	            Object value = result.getObject(columnName);  
	            BeanUtils.setProperty(entity, fieldName, value);  
	        }  
	        return entity;  
	    }  
	  
	    /** 
	     * 分页查询所有记录 
	     **/  
	    public <T> List<T> getPaging(Class<T> entityClass, int firstIndex,  
	            int maxResult) throws ClassNotFoundException, SQLException,  
	            InstantiationException, IllegalAccessException,  
	            NoSuchMethodException, InvocationTargetException {  
	        List<T> results = new ArrayList<T>();  
	        StringBuffer pageIngSql = new StringBuffer(  
	                "select * from (select rownum rn,tab.* from ");  
	        String tableName = getTableName(entityClass);  
	        pageIngSql.append(tableName).append(" tab ) where rn between ? and ?");  
	        System.out.println(pageIngSql.toString());  
	        Connection conn = null;
	        PreparedStatement prep = null ;
	        ResultSet rs = null ;
			try {
				conn = DBConnPool.getConnection();
				prep = conn.prepareStatement(pageIngSql.toString());  
				
				prep.setInt(1, firstIndex);  
				prep.setInt(2, firstIndex + maxResult);  
				rs = prep.executeQuery();  
				Field[] fields = entityClass.getDeclaredFields();  
				while (rs.next()) {  
					T entity = setData2Entity(entityClass, fields, rs);  
					results.add(entity);  
				}  
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  finally {
				try {
					if(rs != null){
						rs.close();
					}
					if(prep != null){
						prep.close();
					}
					ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);	 //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
				} catch (NoFreeConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
	        return results;  
	    }  
	  
	    /** 
	     * 得到总页数 
	     */  
	    public <T> int getCount(Class<T> entityClass)  
	            throws ClassNotFoundException, SQLException {  
	        int count = 0;  
	        StringBuffer countSql = new StringBuffer("select count(*) count from ");  
	        String tableName = getTableName(entityClass);  
	        countSql.append(tableName);  
	        System.out.println(countSql.toString());  
	        Connection conn = null;
	        PreparedStatement prep = null ;
	        ResultSet rs = null ;
			try {
				conn = DBConnPool.getConnection();
				prep = conn.prepareStatement(countSql.toString());  
				rs = prep.executeQuery();  
				if (rs.next()) {  
					count = rs.getInt("count");  
				}  
			} catch (NoFreeConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(rs != null){
						rs.close();
					}
					if(prep != null){
						prep.close();
					}
					ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);	 //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
				} catch (NoFreeConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}  
	        return count;  
	    }  
	    //测试  
	    public static void main(String[] args) throws SecurityException,  
	            IllegalArgumentException, NoSuchMethodException,  
	            IllegalAccessException, SQLException, ClassNotFoundException,  
	            InstantiationException, InvocationTargetException, ParseException {  
	        BaseDao baseDao = new BaseDao();  
	        SYJK_CCS_CCSXX ccsxx = new SYJK_CCS_CCSXX();
	        /* 
	        SYJK_CCS_CODETYPE codeType = new SYJK_CCS_CODETYPE();  
	        codeType.setCODETYPE("wbzhaoTEST");  
	        codeType.setCODETYPENAME("wbzhaoTest"); 
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        codeType.setMAKEDATE(sdf.parse("2014-06-05"));
	        codeType.setREMARK("wbzhaoTest");
	         */  
	        if (baseDao.save(ccsxx)) { 
	            System.out.println("添加成功"); 
	        } else { 
	            System.out.println("添加失败"); 
	        } 
	        /*
	        /* 
	         if (baseDao.update(codeType)) { 
	           System.out.println("修改成功");  
	         } else { 
	           System.out.println("修改失败"); 
	         } 
	         */  
	  
	        /* 
	         if (baseDao.delete(codeType)) { 
	           System.out.println("删除成功");  
	         } else { 
	           System.out.println("删除失败"); 
	         } 
	         */   
	  
	        /* 
	         * 分页查询 
	        int currentPage = 1;  
	        int maxResult = 2;  
	        int count = baseDao.getCount(SYJK_CCS_CODETYPE.class); 
	        System.out.println("查询结果："+count+" 行记录");
	        int countPage = count / maxResult == 0 ? count / maxResult : count  
	                / maxResult + 1;  
	        int firstIndex = (currentPage - 1) * maxResult+1;  
	        List<SYJK_CCS_CODETYPE> pagingList = baseDao.getPaging(SYJK_CCS_CODETYPE.class,  
	                firstIndex, maxResult);  
	        System.out  
	                .println("总页数:" + countPage + " \t 当前第 " + currentPage + " 页");  
	        System.out.println("类型编码\t类型名称\t创建日期");  
	        for (SYJK_CCS_CODETYPE codeType2 : pagingList) {  
	            System.out.println(codeType2.getCODETYPE() + "\t"  
	                    + codeType2.getCODETYPENAME() + "\t" + codeType2.getMAKEDATE());  
	        }    
	         */  
	    }  
	
}
