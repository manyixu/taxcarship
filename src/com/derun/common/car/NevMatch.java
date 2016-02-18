package com.derun.common.car;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.derun.beans.Vehicle_Type;
import com.derun.common.db.ExeSQL;
import com.derun.common.init.CfgLoader;

/**
 * 新能源车型匹配（New Energy Vehicle Match）
 * @author wbzhao
 *
 */
public class NevMatch {
	
	private static final Logger log = Logger.getLogger(NevMatch.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 新能源车型匹配
	 * 0 = 匹配失败
	 * 1 = 新能源
	 * 2 = 节约能源
	 * @param vt
	 * @return
	 */
	public static int getEnergyType(Vehicle_Type vt){
		
		int energyType = 0;	//是否新能源车辆
		
		if(isEleCar(vt)){//判断纯电动乘用车或插电式混合动力乘用车
			energyType = 1;
			return energyType;
		}
		
		log.debug("新能源车型匹配开始------------------------");
		//1-入参中车型匹配车型目录表
		//2-入参匹配不到，用vin+engineno去核定库中查找新的车型，再去车型目录表进行匹配
		try{
			if(vt!=null){
				if(vt.getFirstRegisterDate()!=null && !"".equals(vt.getFirstRegisterDate())){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
					//新能源匹配规则 - [批次号1&批次号2&•••&批次号n:起期-止期], [批次号1&批次号2&•••&批次号n : 起期-止期],•••, [批次号1&批次号2&•••&批次号n:起期-止期]
					String matchCfg = CfgLoader.getConfigValue("SysSwitch", "NevMatchFlag");
					String matchRuleCfg = CfgLoader.getConfigValue("SysParam", "NevMatchRule");
					if(matchCfg!=null && "1".equals(matchCfg)){//新能源系统判定开关打开
						if(matchRuleCfg!=null && !"".equals(matchRuleCfg)){
							String[] rules = matchRuleCfg.split(",");	
							for(int i=0; i<rules.length; i++){	//[批次号1&批次号2&•••&批次号n:起期-止期]
								String[] ruleDet = rules[i].substring(1, rules[i].length()-1).split(":");
								String dates = ruleDet[1];
								int startDate = Integer.parseInt(dates.split("-")[0]);	//有效起期
								int endDate = Integer.parseInt(dates.split("-")[1]);	//有效止期
								int firstRegisterDate = Integer.parseInt(sdf2.format(sdf.parse(vt.getFirstRegisterDate())));	//初登日期
								if((startDate==0||(startDate>=0&&firstRegisterDate>=startDate)) && (endDate==0||(endDate>=0&&firstRegisterDate<=endDate))){
									String[] batchNo = ruleDet[0].split("&");	//新能源车型批次号
									String batchNoSql = "(";
									for(int j=0; j<batchNo.length; j++){
										batchNoSql = batchNoSql+"'"+batchNo[j]+"',";
									}
									batchNoSql = batchNoSql.substring(0, batchNoSql.length()-1)+")";
									//新能源类型匹配sql
									String vEnergyType = "";
									String sql = "select venergytype from syjk_ccs_necars where 1=1 and vbatchno in "+batchNoSql;
									if(vt.getNoticeType()!=null && !"".equals(vt.getNoticeType())){
										sql = sql+" and instr('"+vt.getNoticeType()+"', vmodel) > 0";
										vEnergyType = new ExeSQL().getOneValue(sql);	//能源类型匹配结果
									}
									if(vEnergyType!=null && !"".equals(vEnergyType)){
										//直接用入参车辆类型匹配成功
										if("1".equals(vEnergyType) || "2".equals(vEnergyType)){
											energyType = Integer.parseInt(vEnergyType);
											log.debug("新能源车型匹配成功。");
											log.debug("Model="+vEnergyType+" （1=新能源，2=节约能源）");
										}else{
											log.debug("错误的能源类型，新能源车型匹配失败。");
										}
									}else{
										//使用核定库车辆信息匹配（如果存在）
										if(vt.getVIN()!=null && !"".equals(vt.getVIN())){//vt.getEngineNo()!=null && !"".equals(vt.getVIN()) && !"".equals(vt.getEngineNo())
											String hdkMatchSql = "select model from syjk_ccs_carinfo where 1=1 and vin = '"+vt.getVIN()+"'";
											String hdkModel = "";
											if(vt.getEngineNo()!=null && !"".equals(vt.getEngineNo())){
												hdkMatchSql = hdkMatchSql+" and engineno = '"+vt.getEngineNo()+"'";
												hdkModel = new ExeSQL().getOneValue(hdkMatchSql);
											}
											if(hdkModel!=null && !"".equals(hdkModel)){
												//根据核定库中匹配到车辆型号匹配新能源车型目录
												String hdkModelSql = sql+" and instr('"+hdkModel+"', vmodel) > 0";
												String hdkMatchModel = new ExeSQL().getOneValue(hdkModelSql);
												if(hdkMatchModel!=null && !"".equals(hdkMatchModel)){
													energyType = Integer.parseInt(hdkMatchModel);
													log.debug("使用核定库中匹配到的车辆型号查询新能源车型目录，匹配成功。");
													log.debug("Model="+hdkMatchModel+" （1=新能源，2=节约能源）");
												}else{
													log.debug("使用核定库中匹配到的车辆型号查询新能源车型目录，匹配失败。");
												}
											}else{
												log.debug("入参中的车辆信息在核定库中不存在，新能源车型匹配失败。");
											}
										}else {
											log.debug("车架号为空，新能源车型匹配失败。");
										}
										
									}
									
								}
							}
						}else {
							log.debug("未配置新能源车型匹配规则，匹配失败。");
						}
					}else{
						log.debug("新能源系统判定开关关闭，不自动匹配。");
					}
				}else{
					log.debug("入参车辆初等日期为空，新能源车型匹配失败。");
				}
				
			}else{
				log.debug("车辆信息对象为空，新能源车型匹配失败。");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			log.debug("匹配异常，新能源车型匹配失败。");
			e.printStackTrace();
		}finally{
			log.debug("新能源车型匹配结束------------------------"+energyType);
		}
		
		
		return energyType;		
	}
	
	//2.纯电动乘用车
	/**
	 * 车辆种类代码为11或12，并且能源种类代码为1纯电动车或3插电式混合动力，并且核定载客人数小于等于9人
	 * @param vt
	 * @return
	 */
	private static boolean isEleCar(Vehicle_Type vt){
		if (vt.getMotorTypeCode() != null
				&& ("11".equals(vt.getMotorTypeCode()) || "12".equals(vt
						.getMotorTypeCode()))
				&& vt.getFuelType() != null
				&& ("1".equals(vt.getFuelType()) || "3"
						.equals(vt.getFuelType()))
				&& vt.getRatedPassengerCapacity() <= 9){
			return true;
		}else{
			return false;
		}
	}

}
