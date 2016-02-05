package com.derun.monitors.yewu;

import java.util.ArrayList;
import java.util.List;

import com.derun.all.common.Double_Decimal;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.monitors.Hjhz_VO;
import com.derun.monitors.List_Monitor;

/**
 * @author MILI
 * @time 2014-9-1 10:25:42
 * @描述：YeWu 辅助
 * */
public class YeWu_Assist {
	/**
	 * @author MILI
	 * @time 2014-9-1 10:04:44
	 * @描述：要显示的td列
	 * */
	public List<String> set_Tabletd(){
		List<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("代收公司");
		list.add("车架号");
		list.add("号牌号码");
		list.add("号牌种类");
		list.add("车辆类型");
		list.add("纳税类型");
		list.add("纳税地区代码");
		list.add("税款所属始期");
		list.add("税款所属止期");
		list.add("计税单位代码");
		list.add("单位计税金额");
		list.add("年单位税额");
		list.add("完税机关代码");
		list.add("完税机关名称");
		list.add("完税凭证号码");
		list.add("减免税机关代码");
		list.add("减免税机关名称");
		list.add("减免税原因");
		list.add("减免税方案");
		list.add("减免比例");
		list.add("减免金额");
		list.add("减免税凭证号");
		list.add("当期应纳税额");
		list.add("逾期时间");
		list.add("逾期天数");
		list.add("滞纳金");
		list.add("合计金额");
		list.add("操作员");
		list.add("税务机关编码");
		list.add("系统采集日期");
		list.add("所属年度");
		list.add("功率");
		list.add("合计金额标志码");
		list.add("本年车船税金额");
		list.add("合计欠税金额");
		list.add("合计滞纳金");
		list.add("合计金额");
		list.add("注销状态 ");  
		list.add("平台状态 "); 
		list.add("变更类型 ");
		list.add("算税标志 "); 
		list.add("拒缴标志");   
		list.add("申报日期");
		list.add("车辆登记日期");
		list.add("特殊车标志 ");
		list.add("退税标志"); 
		list.add("发动机号");
		list.add("投保始期");
		list.add("投保止期");
		list.add("问题序列号");
		list.add("使用性质");
		list.add("车辆型号");
		list.add("交管车辆类型");
		list.add("核定载客人数");
		list.add("核定载客质量");
		list.add("整备质量");
		list.add("排量");
		list.add("能源种类");
		list.add("查询码");
		list.add("确认码");
		list.add("备注");
		return list ;
	}
	/**
	 * @author MILI
	 * @time 2014-9-1 10:04:44
	 * @描述：要显示的td列
	 * */
	public List<String> set_td(SYJK_CCS_RKMX rkmx){
		List<String> list = new ArrayList<String>();
		list.add(rkmx.getPAYCOMPANYCODE());
		list.add(rkmx.getVIN());
		list.add(rkmx.getHPHM());
		list.add(rkmx.getCLLX());
		list.add(rkmx.getTAXCONDITIONCODE());
		list.add(rkmx.getTAXSTARTDATE());
		list.add(rkmx.getTAXENDDATE());
		list.add(String.valueOf(rkmx.getUNITRATE()));
		list.add(String.valueOf(rkmx.getANNUALTAXAMOUNT()));
		list.add(rkmx.getPAYDATE());
		list.add(String.valueOf(rkmx.getSUMTAX()));
		list.add(String.valueOf(rkmx.getSUMTAXDEFAULT()));
		list.add(String.valueOf(rkmx.getOVERDUE()));
		list.add(rkmx.getTSBZ());
		list.add(rkmx.getLOGGEDOUT());
		list.add(rkmx.getFIRSTREGISTERDATE());
		list.add(rkmx.getSPECIALCARTYPE());
		return list ;
	}
	/**
	 * @author MILI
	 * @time 2014-9-1 10:04:44
	 * @描述：保存上一次的RKMX记录
	 * */
	public List<SYJK_CCS_RKMX> set_rkmx(List<YeWu_VO> list){
		List<SYJK_CCS_RKMX> list_rkmx = new ArrayList<SYJK_CCS_RKMX>();
		if(list != null){
			for(int i = 0 ; i < list.size() ; i++){
				list_rkmx.add(list.get(i).getNewrkmx());
			}
		}
		return list_rkmx ;
		
	}
	/**
	 * @author MILI
	 * @time 2014-9-2 11:17:07
	 * @描述：得到入库明细
	 * */
	public SYJK_CCS_RKMX set_Mon(SYJK_CCS_RKMX _rkmx){
		SYJK_CCS_RKMX rkmx = new SYJK_CCS_RKMX();
		rkmx.setANNUALTAXAMOUNT(_rkmx.getANNUALTAXAMOUNT());
		rkmx.setANNUALTAXDUE(_rkmx.getANNUALTAXDUE());
		rkmx.setEXCEEDDATE(_rkmx.getEXCEEDDATE());
		rkmx.setEXCEEDDAYSCOUNT(_rkmx.getEXCEEDDAYSCOUNT());
		rkmx.setOVERDUE(_rkmx.getOVERDUE());
		rkmx.setPAYCOMPANYCODE(_rkmx.getPAYCOMPANYCODE());    
		rkmx.setPAYDATE(_rkmx.getPAYDATE());					
		rkmx.setSUMOVERDUE(_rkmx.getOVERDUE());
		rkmx.setSUMTAX(_rkmx.getSUMTAX());
		rkmx.setSUMTAXDEFAULT(_rkmx.getSUMTAXDEFAULT());
		rkmx.setTAXCONDITIONCODE(_rkmx.getTAXCONDITIONCODE());
		rkmx.setTAXDEPARTMENT(_rkmx.getTAXDEPARTMENT());
		rkmx.setTAXDEPARTMENTCODE(_rkmx.getTAXDEPARTMENTCODE());
		rkmx.setTAXDOCUMENTNUMBER(_rkmx.getTAXDOCUMENTNUMBER());
		rkmx.setTAXDUE(_rkmx.getTAXDUE());
		rkmx.setTAXENDDATE(_rkmx.getTAXENDDATE());
		rkmx.setTAXSTARTDATE(_rkmx.getTAXSTARTDATE());
		rkmx.setTOTALAMOUNT(_rkmx.getTOTALAMOUNT());
		rkmx.setUNITRATE(_rkmx.getUNITRATE());
		rkmx.setPOWER(_rkmx.getPOWER());
		rkmx.setRATEDPASSENGERCAPACITY(_rkmx.getRATEDPASSENGERCAPACITY());
		rkmx.setTONNAGE(_rkmx.getTONNAGE());
		rkmx.setVEHICLETYPE(_rkmx.getVEHICLETYPE());
		rkmx.setVIN(_rkmx.getVIN());
		rkmx.setWHOLEWEIGHT(_rkmx.getWHOLEWEIGHT());
		rkmx.setEngineNo(_rkmx.getEngineNo());
		rkmx.setCLLX(_rkmx.getCLLX());
		rkmx.setDISPLACEMENT(_rkmx.getDISPLACEMENT());
		rkmx.setFIRSTREGISTERDATE(_rkmx.getFIRSTREGISTERDATE());
		rkmx.setFUELTYPE(_rkmx.getFUELTYPE());
		rkmx.setMODEL(_rkmx.getMODEL());
		rkmx.setMOTORUSAGETYPECODE(_rkmx.getMOTORUSAGETYPECODE());
		rkmx.setHPHM(_rkmx.getHPHM());
		rkmx.setHPZL(_rkmx.getHPZL());
		rkmx.setTSBZ(_rkmx.getTSBZ());
		rkmx.setLOGGEDOUT(_rkmx.getLOGGEDOUT());
		rkmx.setSPECIALCARTYPE(_rkmx.getSPECIALCARTYPE());
		rkmx.setSJCJRQ(_rkmx.getSJCJRQ());
		rkmx.setSTATUSDATE(_rkmx.getSTATUSDATE());
		rkmx.setTAXAMOUNT_FLAG(_rkmx.getTAXAMOUNT_FLAG());
		rkmx.setTAXCONFIRMNO(_rkmx.getTAXCONFIRMNO());
		rkmx.setTAXLOCATIONCODE(_rkmx.getTAXLOCATIONCODE());
		rkmx.setTAXPAYERIDENTIFICATIONCODE(_rkmx.getTAXPAYERIDENTIFICATIONCODE());
		rkmx.setTAXPAYERNAME(_rkmx.getTAXPAYERNAME());
		rkmx.setTAXREGISTRYNUMBER(_rkmx.getTAXREGISTRYNUMBER());
		rkmx.setTAXUNITTYPECODE(_rkmx.getTAXUNITTYPECODE());
		rkmx.setTAXPRINTNO(_rkmx.getTAXPRINTNO());
		rkmx.setTAXQUERYNO(_rkmx.getTAXQUERYNO());
		rkmx.setCOUNTTAXTYPE(_rkmx.getCOUNTTAXTYPE());
		rkmx.setDEDUCTION(_rkmx.getDEDUCTION());
		rkmx.setDEDUCTIONDEPARTMENT(_rkmx.getDEDUCTIONDEPARTMENT());
		rkmx.setDEDUCTIONDEPARTMENTCODE(_rkmx.getDEDUCTIONDEPARTMENTCODE());
		rkmx.setDEDUCTIONDOCUMENTNUMBER(_rkmx.getDEDUCTIONDOCUMENTNUMBER());
		rkmx.setDEDUCTIONDUECODE(_rkmx.getDEDUCTIONDUECODE());
		rkmx.setDEDUCTIONDUEPROPORTION(_rkmx.getDEDUCTIONDUEPROPORTION());
		rkmx.setDEDUCTIONDUETYPE(_rkmx.getDEDUCTIONDUETYPE());
		rkmx.setInsureEndDate(_rkmx.getInsureEndDate());
		rkmx.setInsureStartDate(_rkmx.getInsureStartDate());
		rkmx.setLOGGEDOUT(_rkmx.getLOGGEDOUT());
		rkmx.setLOGINNAME(_rkmx.getLOGINNAME());
		rkmx.setPLATFORMSTATE(_rkmx.getPLATFORMSTATE());
		rkmx.setRefusetype(_rkmx.getRefusetype());
		rkmx.setREVENUECODE(_rkmx.getREVENUECODE());
		rkmx.setSPECIALCARTYPE(_rkmx.getSPECIALCARTYPE());
		rkmx.setCARMATCHID(_rkmx.getCARMATCHID());
		rkmx.setCARSERIALNO(_rkmx.getCARSERIALNO());
		rkmx.setCHANGETYPE(_rkmx.getCHANGETYPE());
		return rkmx ;
	}
	/**
	 * @author MILI
	 * @time 2014-9-3 11:29:53
	 * @描述：是否有新的交易业务
	 * */
	public boolean Is_newYewu(SYJK_CCS_RKMX rkmx1 ,SYJK_CCS_RKMX rkmx2){
		if(rkmx1 != null && rkmx2 != null){
			if(!rkmx1.getHPHM().equals(rkmx2.getHPHM()) || !rkmx1.getTAXQUERYNO().equals(rkmx2.getTAXQUERYNO())){
				return true ;
			}else{
				return false ;
			}
		}
		return false ;
	}
	/**
	 * 
	 * */
	public HjHz_VO set_HjHz(Hjhz_VO _hjhz){
		HjHz_VO hjhz_vo = new HjHz_VO();
		hjhz_vo.setDate_Money(Double_Decimal.Partition(_hjhz.getDate_Money()));
		hjhz_vo.setDate_Number(Double_Decimal.set_ZERO(_hjhz.getDate_Number()));
		hjhz_vo.setMonth_Money(Double_Decimal.Partition(_hjhz.getMonth_Money()));
		hjhz_vo.setMonth_Number(Double_Decimal.set_ZERO(_hjhz.getMonth_Number()));
		hjhz_vo.setYear_Money(Double_Decimal.Partition(_hjhz.getYear_Money()));
		hjhz_vo.setYear_Number(Double_Decimal.set_ZERO(_hjhz.getYear_Number()));
		return hjhz_vo ;
	}
	/**
	 * @author MILI
	 * @Date：2015年6月29日11:38:38
	 * @描述：业务明细详细信息遍历
	 * */ 
	public SYJK_CCS_RKMX getRKMX(String taxconfig){
		List<SYJK_CCS_RKMX> list_mon = List_Monitor.getList();
		SYJK_CCS_RKMX rkmx = new SYJK_CCS_RKMX();
		for(SYJK_CCS_RKMX rkmxs : list_mon){
			if(taxconfig.equals(rkmxs.getTAXCONFIRMNO())){
				rkmx = rkmxs ;
			}
		}
		return rkmx ;
	}
	/**
	 * 
	 * */
	public SYJK_CCS_RKMX sub_rkmx(SYJK_CCS_RKMX rkmx){
		if(rkmx != null ){
			String TAXDOCUMENTNUMBER = rkmx.getTAXDOCUMENTNUMBER() ;
			String STATUSDATE = rkmx.getSTATUSDATE();
			String MODEL = rkmx.getMODEL();
			String TAXDEPARTMENT = rkmx.getTAXDEPARTMENT();
			if(TAXDOCUMENTNUMBER != null && TAXDOCUMENTNUMBER.length() >= 6){
				TAXDOCUMENTNUMBER = TAXDOCUMENTNUMBER.substring(0, 6);
			}
			if(STATUSDATE != null && STATUSDATE.length() >= 10){
				STATUSDATE = STATUSDATE.substring(0, 10);
			}
			if(MODEL != null && MODEL.length() >= 5){
				MODEL = MODEL.substring(0, 5);
			}
			if(TAXDEPARTMENT != null && TAXDEPARTMENT.length() >= 5){
				TAXDEPARTMENT = TAXDEPARTMENT.substring(0, 4);
			}
			rkmx.setTAXDEPARTMENT(TAXDEPARTMENT + "...");
			rkmx.setMODEL(MODEL + "...");
			rkmx.setTAXDOCUMENTNUMBER(TAXDOCUMENTNUMBER + "...");
			rkmx.setSTATUSDATE(STATUSDATE + "...") ;
		}
		return rkmx ;
	}
}
