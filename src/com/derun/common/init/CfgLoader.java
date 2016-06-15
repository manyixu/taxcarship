package com.derun.common.init;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.derun.common.db.ExeSQL;
import com.derun.common.db.SSRS;
import com.derun.common.db.SqlText;
import com.derun.dbpool.DBConnectionManager;
import com.derun.gt3.Gt3DBUtil;
import com.derun.model.po.SYJK_CCS_CODE;

/**
 * @author	赵文斌
 * @Email	wbzhao7@gmail.com
 * @date	2014-3-4
 *
 * 说明
 * @version
 */
public class CfgLoader implements ServletContextListener {
	
	private static Logger log = Logger.getLogger(com.derun.common.init.CfgLoader.class.getName());
	static SSRS cfg = null;
	ExeSQL es = null;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		DBConnectionManager.getInstance();
		Gt3DBUtil.getInstance();//金三数据库查询连接工具实例
		
		String sql = SqlText.R_00_CODE_001;
		
		try{
			
			if(null==cfg||cfg.getMaxRow()<1){
				
				cfg = new SSRS();
				
				ExeSQL es = new ExeSQL();
				
				cfg = es.execSQL(sql.toString());
				
				log.debug("共加载到"+cfg.getMaxRow()+"条有效配置!");
				
			}
		}catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		
	}
	
	public static List<SYJK_CCS_CODE> getConfig(String codeType){
		
		List<SYJK_CCS_CODE> sccList = new ArrayList();

		if(null==cfg||cfg.getMaxRow()<1){
			
			log.debug("获取配置信息失败");
			
		}else{
			
			for(int i = 1;i <= cfg.getMaxRow(); i++){
				
				SYJK_CCS_CODE scc =  new SYJK_CCS_CODE();
				
				if(cfg.GetText(i, 2).equals(codeType)){
					
					scc.setCODETYPE(cfg.GetText(i, 2));	//代码类型
					scc.setCODE(cfg.GetText(i, 3));		//代码
					scc.setCODENAME(cfg.GetText(i, 4));	//代码名称
					scc.setCODEVALUE(cfg.GetText(i, 5));//代码值
					scc.setCODEALIA(cfg.GetText(i, 6));	//代码别名
					scc.setVALIDATEFLAG(cfg.GetText(i, 7));//有效标识
					scc.setISHOTPARA(cfg.GetText(i, 8));	//是否热配置
					scc.setREMARK(cfg.GetText(i, 9));	//mili
					sccList.add(scc);
				}
				
			}
		}
		
		return sccList;
	}
	
	/**
	 * 获取完整配置信息
	 * @return
	 */
	public static SYJK_CCS_CODE getConfig(String codeType,String code){
		
		SYJK_CCS_CODE scc = null;
		
		if(null==cfg||cfg.getMaxRow()<1){
			
			log.debug("获取配置信息失败");
			
		}else{
			
			for(int i = 1;i <= cfg.getMaxRow(); i++){
				
				scc =  new SYJK_CCS_CODE();
				
				if(cfg.GetText(i, 2).equals(codeType)&&cfg.GetText(i, 3).equals(code)){
					
					scc.setCODETYPE(cfg.GetText(i, 2));	//代码类型
					scc.setCODE(cfg.GetText(i, 3));		//代码
					scc.setCODENAME(cfg.GetText(i, 4));	//代码名称
					scc.setCODEVALUE(cfg.GetText(i, 5));//代码值
					scc.setCODEALIA(cfg.GetText(i, 6));	//代码别名
					scc.setVALIDATEFLAG(cfg.GetText(i, 7));//有效标识
					scc.setISHOTPARA(cfg.GetText(i, 8));	//是否热配置
					
					break;
				}
				
			}
		}
		return scc;
	}
	
	/**
	 * 获取配置的值
	 * @return 
	 * @return
	 */
	public static String getConfigValue(String codeType,String code){
		
		return getConfig(codeType,code).getCODEVALUE();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args){
		getConfig("SysCode_DueType");
		System.exit(0);
	}

}
