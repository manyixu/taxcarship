package com.derun.monitors;

import java.util.ArrayList;
import java.util.List;
import com.derun.model.po.SYJK_CCS_RKMX;
//import com.derun.monitors.yewu.YeWu_Assist;

/**
 * @author MILI
 * @time 2014-6-26 17:11:45
 * @描述：
 * */
public class List_Monitor {
	private static List<SYJK_CCS_RKMX> list = new ArrayList<SYJK_CCS_RKMX>();
	private static Hjhz_VO hjhz = new Hjhz_VO();
	static int number = 100 ;
//	private YeWu_Assist yewu_assist = new YeWu_Assist();
	/**
	 * @author MILI
	 * @time 2014-6-26 17:14:10
	 * @描述：确认封装
	 * */
	public List<SYJK_CCS_RKMX> addMonitor(SYJK_CCS_RKMX rkmx){
		hjhz = this.set_Hjhz(rkmx);
		
		if(list != null){
			if(list.size() < number){
				// 过滤
				rkmx = Ywmx_Assist.Filter(rkmx);	
//				boolean flag = yewu_assist.Is_newYewu(list.get(list.size() - 1),rkmx);
//				if(flag){
					list.add(rkmx);
//				}
			}
			if(list.size() >= number){
				list.remove(0);
			}
		}else{
			list = new ArrayList<SYJK_CCS_RKMX>();
		}
		return list ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-24 9:50:38
	 * @描述：时时统计合计汇总
	 * */
	public Hjhz_VO set_Hjhz(SYJK_CCS_RKMX rkmx){
		hjhz.setDate_Money(hjhz.getDate_Money() + rkmx.getSUMTAX());
		hjhz.setDate_Number(hjhz.getDate_Number() + 1);
		hjhz.setMonth_Money(hjhz.getMonth_Money() + rkmx.getSUMTAX());
		hjhz.setMonth_Number(hjhz.getMonth_Number() + 1);
		hjhz.setYear_Money(hjhz.getYear_Money() + rkmx.getSUMTAX());
		hjhz.setYear_Number(hjhz.getYear_Number() + 1);
		return hjhz ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-24 9:50:38
	 * @描述：初始化 业务监控
	 * */
	public static void init_Ywjk(List<SYJK_CCS_RKMX> _list){
		list = _list ;
	}
	/**
	 * @author MILI
	 * @time 2014-7-24 9:50:38
	 * @描述：初始化 合计汇总
	 * */
	public static void init_Hjhz(Hjhz_VO _hjhz){
		hjhz = _hjhz ;
	}
	/**
	 * @author MILI
	 * @time 2014-6-26 17:33:06
	 * @描述：
	 * */
	public SYJK_CCS_RKMX set_Mon(SYJK_CCS_RKMX rkmx){
		
//		rkmx.setANNUALTAXAMOUNT(tt.getCurrentTaxDue().getAnnualTaxAmount());
//		rkmx.setANNUALTAXDUE(tt.getTaxAmount().getAnnualTaxDue());
//		rkmx.setEXCEEDDATE(tt.getCurrentTaxDue().getExceedDate());
//		rkmx.setEXCEEDDAYSCOUNT(tt.getCurrentTaxDue().getExceedDaysCount());
//		rkmx.setOVERDUE(tt.getCurrentTaxDue().getOverDue());
//		rkmx.setPAYCOMPANYCODE(tt.getPayCompanyCode());    // 
//		rkmx.setPAYDATE(tt.getPayDate());					
//		rkmx.setSUMOVERDUE(tt.getTaxAmount().getSumOverdue());
//		rkmx.setSUMTAX(tt.getTaxAmount().getSumTax());
//		rkmx.setSUMTAXDEFAULT(tt.getTaxAmount().getSumTaxDefault());
//		rkmx.setTAXCONDITIONCODE(tt.getTaxConditionCode());
//		rkmx.setTAXDEPARTMENT(tt.getCurrentTaxDue().getPaid().getTaxDepartment());
//		rkmx.setTAXDEPARTMENTCODE(tt.getCurrentTaxDue().getPaid().getTaxDepartmentCode());
//		rkmx.setTAXDOCUMENTNUMBER(tt.getCurrentTaxDue().getPaid().getTaxDocumentNumber());
//		rkmx.setTAXDUE(tt.getCurrentTaxDue().getTaxDue());
//		rkmx.setTAXENDDATE(tt.getCurrentTaxDue().getTaxEndDate());
//		rkmx.setTAXSTARTDATE(tt.getCurrentTaxDue().getTaxStartDate());
//		rkmx.setTOTALAMOUNT(tt.getCurrentTaxDue().getTotalAmount());
//		rkmx.setUNITRATE(tt.getCurrentTaxDue().getUnitRate());
		
		
		
//		rkmx.setPOWER(vt.getPower());
//		rkmx.setRATEDPASSENGERCAPACITY(vt.getRatedPassengerCapacity());
//		rkmx.setTONNAGE(vt.getTonnage());
//		rkmx.setVEHICLETYPE(vt.getVehicleType());
//		rkmx.setVIN(vt.getVIN());
//		rkmx.setWHOLEWEIGHT(vt.getWholeWeight());
//		rkmx.setEngineNo(vt.getEngineNo());
//		rkmx.setCLLX(vt.getMotorTypeCode());
//		rkmx.setDISPLACEMENT(vt.getDisplacement());
//		rkmx.setFIRSTREGISTERDATE(vt.getFirstRegisterDate());
//		rkmx.setFUELTYPE(vt.getFuelType());
//		rkmx.setHPHM(vt.getLicensePlateNo());
//		rkmx.setHPZL(vt.getLicensePlateType());
//		rkmx.setMODEL(vt.getModel());
//		rkmx.setMOTORUSAGETYPECODE(vt.getMotorUsageTypeCode());
		
		
		
//		rkmx.setSTATUSDATE(statusdate);
//		rkmx.setTAXAMOUNT_FLAG(taxamount_flag);
//		rkmx.setTAXCONFIRMNO(taxconfirmno);
//		rkmx.setTAXLOCATIONCODE(taxlocationcode);
//		rkmx.setTAXPAYERIDENTIFICATIONCODE(taxpayeridentificationcode);
//		rkmx.setTAXPAYERNAME(taxpayername);
//		rkmx.setTAXREGISTRYNUMBER(taxregistrynumber);
//		rkmx.setTAXUNITTYPECODE(taxunittypecode);
//		rkmx.setTAXPRINTNO(taxprintno);
//		rkmx.setTAXQUERYNO(taxqueryno);
//		rkmx.setCOUNTTAXTYPE(counttaxtype);
//		rkmx.setDEDUCTION(deduction);
//		rkmx.setDEDUCTIONDEPARTMENT(deductiondepartment);
//		rkmx.setDEDUCTIONDEPARTMENTCODE(deductiondepartmentcode);
//		rkmx.setDEDUCTIONDOCUMENTNUMBER(deductiondocumentnumber);
//		rkmx.setDEDUCTIONDUECODE(deductionduecode);
//		rkmx.setDEDUCTIONDUEPROPORTION(deductiondueproportion);
//		rkmx.setDEDUCTIONDUETYPE(deductionduetype);
//		rkmx.setInsureEndDate(insureEndDate);
//		rkmx.setInsureStartDate(insureStartDate);
//		rkmx.setLOGGEDOUT(loggedout);
//		rkmx.setLOGINNAME(loginname);
//		rkmx.setPLATFORMSTATE(platformstate);
//		rkmx.setRefusetype(refusetype);
//		rkmx.setREVENUECODE(revenuecode);
//		rkmx.setSJCJRQ(sjcjrq);
//		rkmx.setSPECIALCARTYPE(specialcartype);
//		rkmx.setTSBZ(tsbz);
//		rkmx.setCARMATCHID(cARMATCHID);
//		rkmx.setCARSERIALNO(cARSERIALNO);
//		rkmx.setCHANGETYPE(changetype);
		return rkmx ;
	}
	public static List<SYJK_CCS_RKMX> getList() {
		return list;
	} 
	public static Hjhz_VO getHjhz() {
		return hjhz;
	}
//	/**过滤*/
//	public static SYJK_CCS_RKMX setRkmx(SYJK_CCS_RKMX rkmx){
//		if("T".equals(rkmx.getTAXCONDITIONCODE())){
//			rkmx.setTAXCONDITIONCODE("正常纳税");
//		}else if("E".equals(rkmx.getTAXCONDITIONCODE())){
//			rkmx.setTAXCONDITIONCODE("免税车辆");
//		}else if("P".equals(rkmx.getTAXCONDITIONCODE())){
//			rkmx.setTAXCONDITIONCODE("完税车辆");
//		}else if("R".equals(rkmx.getTAXCONDITIONCODE())){
//			rkmx.setTAXCONDITIONCODE("拒缴车辆");
//		}else if("C".equals(rkmx.getTAXCONDITIONCODE())){
//			rkmx.setTAXCONDITIONCODE("减税车辆");
//		}
//		String vin = rkmx.getVIN() , _vin = "" ;;
//		String xin = "" ;
//		if(vin != null && !"".equals(vin)){
//			int vin_length = vin.length() / 2 ;
//			if(vin_length != 0){
//				_vin = vin.substring(0, vin_length);
//				for(int i = 0 ;i < vin.length() - 1 - vin_length; i++){
//					xin += "*";
//				}
//				_vin += xin ;
//			}else{
//				vin = "*" ;
//			}
//		}
//		rkmx.setVIN(_vin);
//		return rkmx ;
//	}
}
