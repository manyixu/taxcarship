package com.derun.controller.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.derun.common.db.ConnectDBBean;
import com.derun.common.db.DBConnPool;
import com.derun.common.db.ExeSQL;
import com.derun.common.db.SSRS;
import com.derun.controller.login.util.User;
import com.derun.dbpool.NoFreeConnectionException;
import com.derun.dbpool.ReadWriteDBPool;

public class LoginDao {
	
	ExeSQL es = new ExeSQL();
   
	//验证登录用户是否存在
	public User ChkUser(String userName,String passWord){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConnPool.getConnection();
		} catch (NoFreeConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		User user = null;
		String sql="select t.*, t.rowid from syjk_ccs_user t where t.username=? and t.password=?";
		
		
		try{
		 pstmt=conn.prepareStatement(sql);
		 pstmt.setString(1, userName);
		 pstmt.setString(2, passWord);
		 rs = pstmt.executeQuery();
		 if(rs.next()){
			 user = new User();
			 user.setUserName(rs.getString("userName"));
			 user.setPassWord(rs.getString("passWord"));
			 user.setMenujson(rs.getString("menujson"));
		 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				ConnectDBBean.closeConnection(ReadWriteDBPool.readPool, conn);   //  因关闭方式有问题  需要原来的关闭方式进行连接的关闭  mili 2015-3-20 16:34:57
//				if(conn != null){
//					conn.close();
//				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	  
		return user;
	}
	
	/**
	 * 保存（新增、更新）用户信息
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user){
		
		boolean saveFlag = false;
		String updSql = null;
		
		if(isExist(user.getUserName())){
			//更新用户信息
			updSql = "UPDATE SYJK_CCS_USER SET PASSWORD = '"+user.getPassWord()+"', MENUJSON = '"+user.getMenujson()+"' WHERE USERNAME= '"+user.getUserName()+"'";
		}else{
			//新增用户
			String userId = String.valueOf(Integer.parseInt(es.getOneValue("select max(id) from syjk_ccs_user"))+1);
			updSql = "INSERT INTO SYJK_CCS_USER (ID,USERNAME, PASSWORD, MENUJSON) VALUES ('"+userId+"','"+user.getUserName()+"','"+user.getPassWord()+"','"+user.getMenujson()+"') ";
		}
		
		if(null!=updSql){
			saveFlag = es.execUpdateSQL(updSql);
		}
		
		return saveFlag;
		
	}
	
	public List<User> getUserList(){
		
		List<User> userList = new ArrayList();
		SSRS ssrs = es.execSQL("SELECT ID, USERNAME, PASSWORD, MENUJSON FROM SYJK_CCS_USER ");
		if(ssrs.MaxRow>0){
			for(int i=0; i<ssrs.MaxRow;	i++){
				User user = new User();
				user.setId(Integer.parseInt(ssrs.GetText(i+1, 1)));
				user.setUserName(ssrs.GetText(i+1, 2));
				user.setPassWord(ssrs.GetText(i+1, 3));
				user.setMenujson(ssrs.GetText(i+1, 4));
				userList.add(user);
			}
		}
		return userList;
	}
	
	public boolean destroyUser(String userid){
		boolean delFlag = false;
		String delSql = "DELETE FROM SYJK_CCS_USER WHERE ID = '"+userid+"'";
		delFlag = es.execUpdateSQL(delSql);
		return delFlag;
	}
	
	/**
	 * 用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean isExist(String username){
		
		SSRS ssrs = es.execSQL("SELECT 1 FROM SYJK_CCS_USER WHERE USERNAME = '"+username+"'");
		return ssrs.getMaxRow()>0;
		
	}
	
}
