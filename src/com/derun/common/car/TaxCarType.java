package com.derun.common.car;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.derun.beans.AnnualTax_Type;
import com.derun.beans.BaseChangeQueryReqInfo;
import com.derun.beans.BaseQueryReqInfo;
import com.derun.beans.Derate_Type;
import com.derun.beans.Paid_Type;
import com.derun.beans.Tax_Type;
import com.derun.beans.Vehicle_Type;
import com.derun.common.init.CfgLoader;
import com.derun.common.match.Matching_Rule;
import com.derun.common.match.Tax_wtmd;
import com.derun.common.util.DateUtil;
import com.derun.common.tax.TaxBase;
import com.derun.common.util.FDMianShui;
import com.derun.common.util.LogUtil;
import com.derun.common.util.Tax_Type_Code;
import com.derun.model.po.Car_Id_No;
import com.derun.model.po.Query_ChangeQurey;
import com.derun.model.po.SYJK_CCS_CCSBGCXRCJB;
import com.derun.model.po.SYJK_CCS_CODE;
import com.derun.model.po.SYJK_CCS_DSCCSJMDJXX;
import com.derun.model.po.SYJK_CCS_QGCLWTMDXX;
import com.derun.model.po.SYJK_CCS_RKMX;
import com.derun.model.po.SYJK_CCS_WSDJXX;
/**
 * @author MILI
 * @time 2013-11-1 9:56:10
 * @描述： 车辆信息、车船税信息匹配
 * */
public class TaxCarType {
	private final static String BDQUEYY_Y = "YES" ;					// 是否需要查询数据库标志
	private final static String BDQUEYY_N = "NO" ;					// 是否需要查询数据库标志
	private Tax_wtmd tax_wtmd = new Tax_wtmd();						// 问题名单
	private CarType_Assist cartype_assist = new CarType_Assist();	// TaxCarType 封装类 
	private Car_Id_No car_id_no = new Car_Id_No();					// 车辆匹配ID、问题序列号
	private Matching_Rule MR = new Matching_Rule();					// 车辆匹配规则
	private CarType_DAO cattype_dao = new CarType_DAO();
	LogUtil log = new LogUtil("车辆信息、车船税信息匹配");
	/**
	 * @author MILI
	 * @time 2013-11-1 9:56:10
	 * @描述：纳税类型、纳税对象信息
	 * @出参：Map<String,Object>
	 * @入参：BaseQueryReqInfo 查询入参
	 * */
	@SuppressWarnings("unchecked")
	public Map<String,Object> getCarType(Object bqri_double,List<SYJK_CCS_QGCLWTMDXX> qgclwtmd,String isQuery,SYJK_CCS_RKMX rkmx_c,String IsLook,boolean same_car){
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.startLog();
		}
		Map<String,Object> map = new HashMap<String,Object>();	// 出参
		String ServiceType = null ;								// 接口标志
		boolean bqri_flag = false ;	
		List<SYJK_CCS_QGCLWTMDXX> listWt = null ;// 	
		String bxcdri = null ; 					// 记录保险公司上传的初等日期
		//	批改查询 公共部分
		Query_ChangeQurey bqri = null ;
		
//----------------------请求对象入参封装 开始------------------------------------------------------------------------------------
		
		//	投保查询服务--请求信息对象入参
		try{
			BaseQueryReqInfo bqrib = (BaseQueryReqInfo) bqri_double;
			bqri = cartype_assist.getObject(bqrib) ;
			ServiceType = "01" ;
		}catch(Exception e){
			bqri_flag = true ;
		}
		if(bqri_flag){
			//	变更查询服务--请求信息对象入参
			try{
				BaseChangeQueryReqInfo bqcri = (BaseChangeQueryReqInfo) bqri_double;
				bqri = cartype_assist.getObject(bqcri) ;
				ServiceType = "02" ;
			}catch(Exception e){
			}
		}
//----------------------请求对象入参封装 结束------------------------------------------------------------------------------------
		Vehicle_Type VT = bqri.getVehicleInfo();	// 车辆信息数据类型
		if(BDQUEYY_Y.equals(isQuery)){				// 是否需要再次查询数据库  
			listWt = MR.getMatching_wtmd(VT); 		// 问题名单车辆匹配
		}else if(BDQUEYY_N.equals(isQuery) && qgclwtmd != null){
			listWt = qgclwtmd ; 					// 问题名单车辆匹配
		}else{
			listWt = new ArrayList<SYJK_CCS_QGCLWTMDXX>();
		}
		String paydate = "";
		boolean isCondition = true ;		// 判断是否应该纳税：免税E完税P
		boolean isCarNo = false ;			// 法定免税车
		boolean boolNewCar = false ;		// 判断是否是当年
		int jmszrq = 0 ;					// 减免登记日期
		int skssjsrq = 0 ;					// 完税登记日期
		String dates = null;    			// 申报日期
		String datet = null;				// 退保日期
		boolean begin_yyyy = false ;		// 系统年的下一年是否有缴税纪录
		String lastReduceTaxDocNo = null ; 	// 上次纳税的减免税凭证号
		Tax_Type tax_type = bqri.getTaxInfo();		// 车船税数据类型
		Paid_Type paid_type = new Paid_Type() ;		// 完税数据类型
		Derate_Type derate_type = new Derate_Type();// 减免税数据类型
		String TaxInsuSynFlag = CfgLoader.getConfigValue("SysSwitch", "TaxInsuSynFlag");	// 税险同步开关
		// 封装 减免税 完税信息对象
		if(tax_type.getCurrentTaxDue() != null ){
			if(tax_type.getCurrentTaxDue().getPaid() != null){
				paid_type = tax_type.getCurrentTaxDue().getPaid();			
			}
			if(tax_type.getCurrentTaxDue().getDerate() != null){
				derate_type = tax_type.getCurrentTaxDue().getDerate();
			}
		}
		// 
		bxcdri = bqri.getVehicleInfo().getFirstRegisterDate();   //保险公司初等日期  mili 2016-1-22 16:38:06
		boolean VT_FLAG = true; // 是否需要计算欠税 标志
		String CF = "false" ;
		String T_dq = "" ;      // 退短期原因 10 丢失 !10 其他
		String TaxPaidFlag = "N" ;	// 批改时传投保时是否已缴税标志
		SYJK_CCS_RKMX rkmx = null , rkmx_w = null ,rkmx_end = null;	// 入库明细
		SYJK_CCS_QGCLWTMDXX questionlist = null ;	// 全国车辆问题名单
		String CT = null , J_VOUCHER_DQ = null;		// 车辆类型  、 金额减免车退短期
		Tax_Type TT = null;							// 车船税数据类型
		AnnualTax_Type[] AT = null ;				// 欠税信息
		Date taxstartdate = null ;					// --------
		String mytime = DateUtil.getDateYYYY();		// 系统当前年Date
		int xtrq = Integer.parseInt(mytime) ;		// 系统当前年Integer
		Date T_START = bqri.getInsureStartDate() ;	// 投保开始日期
		Date T_END = bqri.getInsureEndDate() ;		// 投保结束日期
		int M_START = Integer.parseInt(DateUtil.getStringDate(T_START,"yyyy"));
		if("0".equals(TaxInsuSynFlag)){
//			int M_START = Integer.parseInt(DateUtil.getStringDate(T_START,"yyyy"));
			if(M_START >= xtrq + 1){
				T_START = DateUtil.getYear(T_START,xtrq - M_START );
				bqri.setInsureStartDate(T_START);
			}
			M_START = xtrq ;  // 如果税险同步开关关闭的的情况下  投保时间默认是 系统年
		}else if("1".equals(TaxInsuSynFlag)){
//			int M_START = Integer.parseInt(DateUtil.getStringDate(T_START,"yyyy"));
			if(M_START >= xtrq + 2){
				T_START = DateUtil.getYear(T_START,xtrq - M_START + 1 );
				bqri.setInsureStartDate(T_START);
			}
		}
		SYJK_CCS_WSDJXX wsdj = null ;				// 完税登记
		SYJK_CCS_DSCCSJMDJXX jmdj = null ;			// 减免登记
		boolean flag = true , _flag = true ,wtmdflag = true , shangp = true ,tuibao = true , dear_pai = false; 				// _flag =  完税车、wtmdflag = 完税车 、shangp = 商品车
		boolean dq_flag = false ;
		List<SYJK_CCS_CODE> listcode_jy = CfgLoader.getConfig("SysCode_SSC");				// 节约能源
		List<SYJK_CCS_CODE> listcode_xn = CfgLoader.getConfig("SysCode_NSC");				// 新能源

//		Map<String,Object> map_double = MR.getMatching_rkmx(VT,DateUtil.getStringDate(T_START, "yyyy")); 		// 入库明细车辆匹配
		Map<String,Object> map_double = new HashMap<String,Object>() ;
		if(rkmx_c == null){	// 是否要查询入库明细 批改查询时 不需要再次查询入库明细  mili 2015-5-22 16:20:53
			if ("01".equals(ServiceType)
					&& bqri != null
					&& bqri.getTaxInfo() != null
					&& ("P".equals(bqri.getTaxInfo().getTaxConditionCode()) || "E"
							.equals(bqri.getTaxInfo().getTaxConditionCode()))){//wbzhao20151111 2.2.16广东完税车、免税车不再查询入库明细中的缴税记录
				//根据保险公司传的入参的纳税类型判断，P和E就不用查入库明细（批改不改）
			}else{
				map_double = MR.getMatching_rkmx(VT,DateUtil.getStringDate(T_START, "yyyy")); 		// 入库明细车辆匹配
			}
		}else{
			rkmx = rkmx_c ;
		} 		// 入库明细车辆匹配
		
		List<SYJK_CCS_RKMX> listRk = (List<SYJK_CCS_RKMX>) map_double.get("RKMX");			// 入库明细List
		int DQ_Number = Integer.parseInt(CfgLoader.getConfigValue("SysCode_DQ", "DQ_VOUCHER")) ;	// 系统配置短期险 时间
		int days = DateUtil.getTwoDates(bqri.getInsureEndDate(),bqri.getInsureStartDate());			// 投保时间(多久) 天
//		int days = DateUtil.getMonthCount(bqri.getInsureEndDate(),bqri.getInsureStartDate());			// 投保时间(多久) 月
		String sysReduceTaxDocNo = CfgLoader.getConfigValue("SysCode_Reduce", "DeductionDocumentNumber") ;	// 系统配置减免税凭证号
		String TaxDocumentNumber = paid_type == null ? "" : paid_type.getTaxDocumentNumber() ;		// 完税凭证号	
		int Newcaryear = Integer.parseInt(CfgLoader.getConfigValue("SysCode_NEW", "newcaryear")) ;  // 新车算税开始年份	
		String DQ_DATE = CfgLoader.getConfigValue("SysCode_DQ", "DQ_DATE");		// 退保短期再次投保  从什么时候开始算税 （1=投保查询日  0=1月1日）
		String carNo = VT.getLicensePlateNo() ;									// 车牌 
		String anyCode = VT.getSpecialCarType() == null ? "" : VT.getSpecialCarType();	// 特殊车标志:2临时入境，3临时上路
		int fd = FDMianShui.getFDCL(carNo);										// 根据车牌号判断是否是法定免税								
		String taxConditionCode = bqri.getTaxInfo().getTaxConditionCode();		// 纳税类型
		String carType = VT.getMotorTypeCode();				// 车辆种类
		String firstTime = VT.getFirstRegisterDate();		// 车辆初始登记日期
		int djrq = Integer.parseInt(firstTime.substring(0 ,4));		// 车辆初始登记日期  年
		boolean wai_flah = CarType_Assist.Car_Tpye(carNo);			// 本地车  or 外地车  
		String carid = (String) map_double.get("CARID");			// 车辆匹配ID
		car_id_no.setCarMatchId(carid);
		String model = VT.getModel();						// 车辆型号
		boolean flag_jy = false ,flag_xn = false;			// 节约能源	// 新能源
//--------------------------------提前续保------------------------------------------------------------------------------------------------		
		int date_s = Integer.parseInt(mytime) ;
		int date_t = Integer.parseInt(DateUtil.getStringDate(T_START,"yyyy"));
//------------------------------------------------------------------------------------------------------------------------------------------------
		if(listRk != null && listRk.size() > 0){			
			rkmx_end = listRk.get(0);	// 只要最后一条缴税纪录  是否退过保险
			for(SYJK_CCS_RKMX _rkmx : listRk){
//				if(DateUtil.getDIY("yyyy").equals(_rkmx.getPAYDATE())){
				if(DateUtil.getStringDate(T_START,"yyyy").equals(_rkmx.getPAYDATE())){
					rkmx = _rkmx;		// 当前年的缴税记录
					break ;
				}
			}
			// 商品车 再次投保不在同一年的情况下 不应该收全年税款  mili 2016-2-1 16:56:48
			if(rkmx == null && rkmx_end != null ){
				int sp_days = DateUtil.getTwoDates(rkmx_end.getInsureEndDate(),rkmx_end.getInsureStartDate());
				if(sp_days <= DQ_Number && "E".equals(rkmx_end.getTAXCONDITIONCODE()) && sysReduceTaxDocNo.equals(rkmx_end.getDEDUCTIONDOCUMENTNUMBER()) ){
					taxstartdate = DateUtil.getStringDates(bxcdri);
				}
			}
			// 如果入库明细没有当期年的缴税记录  不用管初登日期
			if(rkmx == null){
				VT.setFirstRegisterDate(listRk.get(0).getFIRSTREGISTERDATE());
			}
			
			
			
			String sysdate_yyyy = DateUtil.getDIY("yyyy") ;
			for(SYJK_CCS_RKMX _rkmx : listRk){
				if(Integer.parseInt(sysdate_yyyy) + 1 == Integer.parseInt(_rkmx.getPAYDATE().substring(0, 4))){//wbzhao 20151228 rkmx substr0-4
					begin_yyyy = true ;
				}
				
				if(sysdate_yyyy.equals(_rkmx.getPAYDATE().substring(0, 4))){	//wbzhao 20151228 rkmx substr0-4
					rkmx_w = _rkmx;		// 系统年的缴税记录
					break ;
				}
			}
		}
		// 批改的时候  如果 IsLook 标记是 NO 的情况下  说明是要重新算税的  不用管之前的缴税记录  
		if("NO".equals(IsLook)){
			listRk = null ;
		}
		// 做的是 本年有缴税纪录  系统当前年也有缴税纪录  这次是做 批改本年的缴税纪录
		if("02".equals(ServiceType) && date_s + 1 == date_t && rkmx_w != null){ 
			_flag = false ;
		}
		
		// 提前续保 退税
		if(rkmx_c != null && "NO".equals(IsLook)){
			rkmx = rkmx_c ;
		}
		if(rkmx != null){
			// 批改时传投保时是否已缴税标志
			if("02".equals(ServiceType) && !same_car){
				listRk = null ;       // 投保查询法免批改成普通车初登日期为前年   没有返回前年的纳税标签
				TaxPaidFlag = "Y" ;
			}
			// mili 2015年5月7日15:45:11  批改后是否是 同一辆车	有欠税批改时 滞纳金天数不正确 start
			if("02".equals(ServiceType) && same_car){
				TaxPaidFlag = "Y" ;
			}
			// mili 2015年5月7日15:45:11  批改后是否是 同一辆车	有欠税批改时 滞纳金天数不正确 end
			dates = rkmx.getSTATUSDATE();    	// 申报日期
			datet = rkmx.getSJCJRQ();			// 退保日期
			paydate = rkmx.getPAYDATE();		// 所属年度
			lastReduceTaxDocNo = rkmx.getDEDUCTIONDOCUMENTNUMBER(); 	// 上次纳税的减免税凭证号
		}
		// 节约能源车判断
		//flag_jy = cartype_assist.Is_listcode_jy(listcode_jy, model);
		int eType = NevMatch.getEnergyType(bqri.getVehicleInfo());	//0 = 匹配失败	1 = 新能源	2 = 节约能源（使用新版判定NevMatch.getEnergyType）
		if(bqri.getVehicleInfo()!=null&&bqri.getVehicleInfo().getNoticeType()!=null&&!"".equals(bqri.getVehicleInfo().getNoticeType().trim())){
			//如果NoticeType不为空，则存入车辆信息的model字段中 20160216
			bqri.getVehicleInfo().setModel(bqri.getVehicleInfo().getNoticeType());
		}
		// 新能源车判断
		//flag_xn = cartype_assist.Is_listcode_xn(listcode_xn, model);
		// 截取初始登记年份
		if (firstTime != null && firstTime.length() >= 4) {
			String datetime = firstTime.substring(0, 4);
			boolNewCar = datetime.equals(mytime);
		} else {
			boolNewCar = false;
		}
		if ("P".equals(taxConditionCode)) {
			isCondition = false;
		}
// -----------------法定免税车封装---------------------------------------------------------------------------
		if (fd == 1 || fd == 2 || fd == 3){
			derate_type = new Derate_Type() ; 
			derate_type.setDeductionDueType("E");
			isCarNo = true;
			if(fd == 1){
				derate_type.setDeductionDueCode("D");		// 领 使 车
			}else if(fd == 2){
				derate_type.setDeductionDueCode("A");		// 军车
			}else if(fd == 3){
				derate_type.setDeductionDueCode("P");		// 警车
			}else{
				// 
			}
			// 法定免税车是不填写 减免信息的  这里要自动添加
			if(bqri.getTaxInfo().getCurrentTaxDue() != null){
				bqri.getTaxInfo().getCurrentTaxDue().setDerate(derate_type);		// 法定免税车 不用管减免信息
				bqri.getTaxInfo().getCurrentTaxDue().setPaid(new Paid_Type());		// 法定免税车 不用管完税信息
			}else{
				AnnualTax_Type at = new AnnualTax_Type();
				at.setDerate(derate_type);			// 法定免税车 不用管减免信息
				at.setPaid(new Paid_Type());		// 法定免税车 不用管完税信息
				bqri.getTaxInfo().setCurrentTaxDue(at);
			}
		}
//----------------------------------纳税类型是C 减税的情况下-------------------------------------------------
		
		
//----------------------------------纳税类型是C 减税的情况下-------------------------------------------------
		
		
// -----------------法定免税车封装---------------------------------------------------------------------------
		jmdj = cattype_dao.getSYJK_CCS_DSCCSJMDJXX(VT,null);
		wsdj = cattype_dao.getSYJK_CCS_WSDJXX(VT,null);
		// 减免登记日期
		if(jmdj != null){
			jmszrq = Integer.parseInt(DateUtil.getStringDate(jmdj.getJMSZRQ(),null).substring(0, 4));
		}
		// 完税登记日期
		if(wsdj != null){
			skssjsrq = Integer.parseInt(DateUtil.getStringDate(wsdj.getSKSSJSRQ(),null).substring(0, 4));
		}
//------------------------问题名单车--------------------------------------------------------------------------------------------------	
		boolean car_type = Car_Type.NO_WTMD(bqri,wsdj,jmdj,jmszrq,skssjsrq,xtrq,djrq,isCarNo,anyCode);
		// 问题名单车辆    判断
//		if(listWt != null && listWt.size() > 0 && !"P".equals(tax_type.getTaxConditionCode())){
		if(listWt != null && listWt.size() > 0 && !(car_type)){ 
			questionlist = listWt.get(0);
			// 问题名单有一条 系统年的数据 就按完税处理 mili 2014-12-12 17:14:36 start
//			if("W".equals(questionlist.getYCYYDM().toUpperCase()) && DateUtil.getDateYYYY().equals(questionlist.getND()) && "1".equals(questionlist.getVALIDATEFLAG())){
//				begin_yyyy = false ;
//				flag = true ;
//				paydate = questionlist.getND();
//				rkmx = new SYJK_CCS_RKMX();		// 封装一个入库明细  update
//			}else{	// 问题名单有一条 系统年的数据 就按完税处理 mili 2014-12-12 17:14:36 end
				// 投保白名单车（13年数据）初登日期填2014年4月  返回的14年信息和税款是从14年4月开始的
				if("W".equals(questionlist.getYCYYDM().toUpperCase())){
					firstTime = null ;
				}
				
				if((rkmx == null || ServiceType.equals("02")) && rkmx_w == null){
					AT = tax_wtmd.setAnnal(listWt);		// 封装欠税信息
					flag = false ;
					wtmdflag = false ;					// 标记
					CT = Tax_Type_Code.BLACK_LIST ;		// 车辆类型 		
				}else if(rkmx != null && "1".equals(rkmx.getTSBZ()) && "01".equals(ServiceType)){    // 问题名单数据 退保长期 再次投保时 没有重新计算问题数据  
					AT = tax_wtmd.setAnnal(listWt);		// 封装欠税信息
					flag = false ;
					wtmdflag = false ;					// 标记
					CT = Tax_Type_Code.BLACK_LIST ;		// 车辆类型 		
				}else{
					car_id_no.setCarSerialNo("");	// 机动车序列号   
					flag = true ;
				}
				car_id_no.setCarSerialNo(questionlist.getCARSERIALNO());	// 机动车序列号 wbzhao 20150127
				
				
				//白名单数据记录年度到该车辆投保起期年（不含）之间的欠税要计算。（有白名单数据则无视初登日期） wbzhao 20150129
				//if("W".equals(questionlist.getYCYYDM().toUpperCase()) && questionlist.getND()!=null && questionlist.getND().length()==4){
				if(questionlist.getND()!=null && questionlist.getND().length()==4){
					int wtmdND = Integer.parseInt(questionlist.getND()); //问题名单数据记录年度
					int tbStartND = Integer.parseInt(DateUtil.getStringDate(T_START, "yyyy")); //投保起始年度
					//看入库明细中有无此日期区间的缴税记录
					if(tbStartND-wtmdND-1>0){
						// mili 2015-3-11 16:51:28  代码null错误 start
						int at_numb = 0 ;
						if(AT != null ){
							at_numb = AT.length ;
						}
						AnnualTax_Type[] AT2 = new AnnualTax_Type[at_numb]; //应收欠税
						for(int s=0;s<at_numb;s++){
							AT2[s]=AT[s];
						}
						TaxBase tb = new TaxBase();
						for(int q=at_numb;q<AT2.length;q++){
						// mili 2015-3-11 16:51:28  代码null错误  end
							int noTaxYear = Integer.parseInt(questionlist.getND())+1;//最新一条问题名单数据记录年度的下一年如果没有缴税记录，
							boolean addFlag = true;//计算欠税标识码
							if(listRk!=null && listRk.size()>0){
								for(SYJK_CCS_RKMX rk:listRk){
									if(rk.getPAYDATE().equals(String.valueOf(noTaxYear)) && rk.getCHANGETYPE().equals("0")){
										addFlag = false;//有缴税记录,不计算欠税
									}
								}
							}
							if(addFlag){
								AnnualTax_Type _AnnualTax_Type = new AnnualTax_Type();
								_AnnualTax_Type.setAnnualTaxAmount(tb.getUnitTax(VT));	// 当期年单位税额
								_AnnualTax_Type.setExceedDate(DateUtil.getStringDate(new Date(),null));		// 逾期时间
								_AnnualTax_Type.setExceedDaysCount(DateUtil.getBetweenDays(DateUtil.getStringDate(String.valueOf(noTaxYear+1)+"-01-01", "yyyy-MM-dd"), new Date()));							// 逾期天数
								_AnnualTax_Type.setOverDue(tb.getOverDue(_AnnualTax_Type.getAnnualTaxAmount(), DateUtil.getStringDate(_AnnualTax_Type.getExceedDate(), "yyyy-MM-dd"),null));				// 滞纳金
								_AnnualTax_Type.setTaxDue(_AnnualTax_Type.getAnnualTaxAmount());				// 当期应纳税额（questionlist.getDQYNSE()）
								_AnnualTax_Type.setTaxEndDate(String.valueOf(noTaxYear)+"-12-31");	// 税款所属止期
								_AnnualTax_Type.setTaxStartDate(String.valueOf(noTaxYear)+"-01-01");	// 税款所属始期
								_AnnualTax_Type.setTaxUnitTypeCode(questionlist.getJSDW());		// 计税单位代码
								_AnnualTax_Type.setTotalAmount(_AnnualTax_Type.getOverDue()+_AnnualTax_Type.getTaxDue());			// 合计金额
								_AnnualTax_Type.setUnitRate(tb.doubleFormat(Double.parseDouble(CfgLoader.getConfigValue("SysCode_SM", "S_"+VT.getMotorTypeCode()+"M")),2));			// 单位计税金额
								AT2[q] = _AnnualTax_Type;
							}
						}
						AT = AT2;
					}
				}
				//wbzhao end 20150129
//			}
		}else{
			car_id_no.setCarSerialNo("");	// 机动车序列号
			flag = true ;
		}
//------------------------保有车 算税方法判断--------------------------------------------------------------------------	
		// 保有车   条件：入库明细没有保单起保日期的缴税纪录 && 不是完税车 && 不是批改查询 && 
		if(((rkmx != null && !"".equals(rkmx)) || begin_yyyy ) && flag && !ServiceType.equals("02")){
			// 退过短期或者长期  没有申报的  再次投保
			if(rkmx_end != null && ("2".equals(rkmx_end.getTSBZ()) || "1".equals(rkmx_end.getTSBZ())) && !"1".equals(rkmx_end.getPLATFORMSTATE())){
				if("2".equals(rkmx.getTSBZ()) && "1".equals(DQ_DATE)){  
					SYJK_CCS_CCSBGCXRCJB BGCXRC = cattype_dao.getBGCXRC(rkmx_end.getTAXCONFIRMNO());
					if("1".equals(TaxInsuSynFlag) && date_s + 1 == date_t){		// 提前续保 退短期 再次投保时
						dq_flag = true ;
					}else{
						VT_FLAG = false ;
					}
//					else{   // MILI 退短期再投保税款所属始期应该为退保查询当前日期，但返回的还是投保时所填的  ZYY 2014-11-11 10:47:01
							 // MILI 根据确认码到变更查询入参表里面找 退保原因 2014-11-11 14:33:26 ZYY
						if("10".equals(BGCXRC.getCHANGETYPE())){
							// 是丢失情况下  税款起期按投保查询日算起
							T_dq = BGCXRC.getCHANGETYPE() ;   
							if(!(date_s + 1 == date_t)){    //	正常车退保短期再投保返回两个14年的  ZYY 2014-11-16 16:12:20 MILI
								taxstartdate = new Date();
							}
						}else{
							T_dq = "" ;
							// 不是丢失情况下  税款起期按退保查询日算起     只能取 税款的起期  有提前续保问题  ZYY 2014-11-19 14:58:07 mili
							taxstartdate = DateUtil.getStringDates(rkmx_end.getTAXSTARTDATE());
							// 不是丢失情况下  税款起期按退保查询月的下个月算起  mili 2015-4-22 11:24:03  start
//							String next_Month = DateUtil.get_nextMonth(rkmx_end.getTAXSTARTDATE());
//							taxstartdate = DateUtil.getStringDates(next_Month);
							// 不是丢失情况下  税款起期按退保查询月的下个月算起  mili 2015-4-22 11:24:03  end
						}
//					}
				}
				rkmx = null ;	// 退过短期 重新算税
				listRk = null ;	// 重新算税
				flag = true ;	// 是否新车
			}
			
//			// 退过短期或者长期  已申报的  再次投保    start 2015年4月21日14:24:07  mili
			else if(rkmx_end != null && ("2".equals(rkmx_end.getTSBZ()) || "1".equals(rkmx_end.getTSBZ())) && "1".equals(rkmx_end.getPLATFORMSTATE()) && (null != rkmx_end.getSTATUSDATE() && !"".equals(rkmx_end.getSTATUSDATE()))){
				if("2".equals(rkmx.getTSBZ()) && "1".equals(DQ_DATE)){  
					SYJK_CCS_CCSBGCXRCJB BGCXRC = cattype_dao.getBGCXRC(rkmx_end.getTAXCONFIRMNO());
					if("1".equals(TaxInsuSynFlag) && date_s + 1 == date_t){		// 提前续保 退短期 再次投保时
						dq_flag = true ;
					}else{
						VT_FLAG = false ;
					}
					// MILI 根据确认码到变更查询入参表里面找 退保原因 2014-11-11 14:33:26 ZYY
					if("10".equals(BGCXRC.getCHANGETYPE())){
						// 是丢失情况下  税款起期按投保查询日算起
						T_dq = BGCXRC.getCHANGETYPE() ;   
						if(!(date_s + 1 == date_t)){    //	正常车退保短期再投保返回两个14年的  ZYY 2014-11-16 16:12:20 MILI
							taxstartdate = new Date();
						}
					}else{
						T_dq = "" ;
						// 不是丢失情况下  税款起期按退保查询日算起     只能取 税款的起期  有提前续保问题  ZYY 2014-11-19 14:58:07 mili
						taxstartdate = DateUtil.getStringDates(rkmx_end.getTAXSTARTDATE());
						// 不是丢失情况下  税款起期按退保查询月的下个月算起  mili 2015-4-22 11:24:03  start
//						String next_Month = DateUtil.get_nextMonth(rkmx_end.getTAXSTARTDATE());
//						taxstartdate = DateUtil.getStringDates(next_Month);
						// 不是丢失情况下  税款起期按退保查询月的下个月算起  mili 2015-4-22 11:24:03  end
					}
				}
				rkmx = null ;	// 退过短期 重新算税
				listRk = null ;	// 重新算税
				flag = true ;	// 是否新车
			}
			
			// 商品车 再次投保为非商品车
			else if(sysReduceTaxDocNo.equals(rkmx.getDEDUCTIONDOCUMENTNUMBER()) && !sysReduceTaxDocNo.equals(derate_type.getDeductionDocumentNumber())){
				flag = true ;
				rkmx = null ;
				listRk = null ;
				rkmx_w = null ;
			}
			// 商品车 在次投保 商品车
			else if("E".equals(rkmx.getTAXCONDITIONCODE()) && sysReduceTaxDocNo.equals(rkmx.getDEDUCTIONDOCUMENTNUMBER())){
				CT = Tax_Type_Code.VOUCHER_M ;
				shangp = false ;
				flag = false ;	// 是否新车
			}
			// 法定免税车再次投保
			else if(isCarNo || FDMianShui.getcarType(carType)){
				CT = Tax_Type_Code.VOUCHER_F ;
				bqri.getTaxInfo().setTaxConditionCode("E");	 //  纳税类型
				flag = false ;	// 是否新车
				_flag = false ;	// 是否需要计算欠税
				CF = "true";	// 重复投保标志
			}
			// 做过提前续保的  再次做系统年投保的情况
			else if(begin_yyyy){		
				flag = false ;	// 是否新车
				_flag = false ;	// 是否需要计算欠税
				CF = "true";	// 重复投保标志
				rkmx = listRk.get(0);
			}
			// 完税车  已缴税 重复投保
			else if(DateUtil.getStringDate(T_START,"yyyy").equals(paydate) && !"4".equals(rkmx.getCOUNTTAXTYPE()) && !"R".equals(bqri.getTaxInfo().getTaxConditionCode())){	
				flag = false ;	// 是否新车
				_flag = false ;	// 是否需要计算欠税
				CF = "true";	// 重复投保标志
			} 
			// 申报 但有退过短期的情况
			else if("2".equals(rkmx.getTSBZ()) && "1".equals(rkmx.getPLATFORMSTATE())){
				CT = Tax_Type_Code.DQ_VOUCHER ;
				flag = false ;	// 是否新车
			}
			// 
			else if(DateUtil.getDateYYYY().equals(rkmx.getPAYDATE())){
				CT = Tax_Type_Code.DQ_VOUCHER ;
				flag = false ;	// 是否新车
			}
			// 申报在退保之前  是不算钱的
			else if(DateUtil.getStringDates(dates).before(DateUtil.getStringDates(datet))){
				CT = Tax_Type_Code.NO_VOUCHER ;
				flag = false ;	// 是否新车
			}
			// 已经申报   但是没有退保的   当做完税处理
			else if((lastReduceTaxDocNo == null || !lastReduceTaxDocNo.equals(sysReduceTaxDocNo)) || !"E".equals(rkmx.getTAXCONDITIONCODE())){
				CT = Tax_Type_Code.NO_VOUCHER ;
				flag = false ;	// 是否新车
			}
		}
//----------------非 保有车 无缴税记录车辆 算税方法判断--------------------------------------------------------------------------------------------------------		
		if(flag){
			// 退短期----判断方法：变更类型8,10短期	 
			if("8".equals(bqri.getChangeType()) || "10".equals(bqri.getChangeType())){
				J_VOUCHER_DQ = "TS";			// 退短期
				CT = Tax_Type_Code.T_VOUCHER ;
				if(date_s + 1 == date_t && rkmx_w != null){ // 做的是 本年有缴税纪录  系统当前年也有缴税纪录  这次是做 本年的退保
					_flag = false ;
				}
				if("2".equals(VT.getSpecialCarType())){    // 提前续保 临时入境车 退短期不正确 ZYY 2014-11-19 08:42:02 mili
					CT = Tax_Type_Code.VOUCHER_M ;
					VT_FLAG = false ;
				}
			}
			// 退长期 ----判断方法：变更类型9	、11、=长期
			else if("9".equals(bqri.getChangeType()) || "11".equals(bqri.getChangeType())
					|| "12".equals(bqri.getChangeType())|| "13".equals(bqri.getChangeType())
					|| "14".equals(bqri.getChangeType())){
				J_VOUCHER_DQ = "TL";			// 退长期
				CT = Tax_Type_Code.T_VOUCHER;
				if(rkmx != null){
					// 退保长期 税款起期用投保时的
					taxstartdate = DateUtil.getStringDate(rkmx.getTAXSTARTDATE(),null);
				}
			}
			
			// 法定免税车---- 判断方法：
			// 按车量号牌号码匹配。军队车辆，参照《新军车新牌照需求文档.doc》 ；
			// 武警车辆，牌照前两位是：“WJ”，警用车辆包含：“警”字；外国驻华使领馆，车辆首汉字是：“使”和“领”。
			else if(isCarNo || FDMianShui.getcarType(carType)){
				CT = Tax_Type_Code.VOUCHER_F ;
				if(!FDMianShui.getcarType(carType)){//wbzhao20151111	拖拉机按法定免税车走，纳税类型按入参返回
					bqri.getTaxInfo().setTaxConditionCode("E");	 //  纳税类型
				}
				_flag = false ;    // 完税车、法定免税车、免税车不在查询之前是否有欠税的情况   mili 2015-1-29 16:38:23  
//				listWt = null ;	   // 完税车、法定免税车、免税车不在查询问题名单   mili 2015-4-21 14:53:07
			}
			// 临时上路车通用算税
			else if(anyCode != null && anyCode.equals("3") && isCondition){
				// 临时上路车没有  提前续保，即使保险起期是15 也不算 15年税款   MILI ZLL 2014-11-11 16:19:20
				if(date_s + 1 == date_t){
					date_t = date_s ;
				}
				if(rkmx != null && ServiceType.equals("02")){
					// 批改临时上路车  税款起期应该是 投保查询日  不是批改查询日
					taxstartdate = DateUtil.getStringDate(rkmx.getSJCJRQ(),"yyyy-MM-dd");
				}else{
					if(!("1".equals(TaxInsuSynFlag) && date_s + 1 == date_t)){
						taxstartdate = new Date();			// 临时上路车  不管是 本地还是外地   初登日期不管是本年还是往年  税款起期都是 投保查询日期
					}
				}
				firstTime = DateUtil.getString(new Date(),"yyyy-MM-dd");     // 2年一起投保时   欠税对象里面应该是  往年  系统日期的
				if(date_s + 1 == date_t){			// 临时上路车  提前续保的情况下按正常车计算税款
//					taxstartdate = T_START ; // 临时上路车 提前续保的情况下 税款起期不准确 不应该是保险起期  应该为 保险 起期年份的1月1日
					taxstartdate = DateUtil.getStringDate(DateUtil.getStringDate(T_START,"yyyy") + "-01-01" , null);   // mili 2014-9-30 11:23:53 ZYY  NO.1、2
				}
				CT = Tax_Type_Code.DQ_VOUCHER ;
				shangp = false ;
			}
			//wbzhao20151022 新能源、节约能源原来判定位置
			// 商品车   判断方法：短期险有效期小于系统配置的“短期险标准”，并且纳税类型是E，并且免税凭证不为空，则认为是商品车，自动补写正确的免税凭证号
			else if(days <= DQ_Number && "E".equals(taxConditionCode) && sysReduceTaxDocNo.equals(derate_type.getDeductionDocumentNumber())){
				shangp = false ;
				CT = Tax_Type_Code.VOUCHER_M ;
			}
			// 零时入境  --- 判断条件：根据特殊车标志==2判断
			else if("2".equals(VT.getSpecialCarType())){
				derate_type = new Derate_Type(); 
				derate_type.setDeductionDueCode("O");		
				derate_type.setDeductionDueType("E");
				bqri.getTaxInfo().setTaxConditionCode("E");	 //  纳税类型
				bqri.getTaxInfo().getCurrentTaxDue().setPaid(new Paid_Type());	// 入境车辆  不用管完税信息
				bqri.getTaxInfo().getCurrentTaxDue().setDerate(derate_type);
				CT = Tax_Type_Code.VOUCHER_M ;
				shangp = false ;
				VT_FLAG = false ;	// 是否需要计算欠税 
			}
			// 本地车凭证号减税----判断方法：必须提供开具免税凭证的机关单位、减免税凭证、该车辆的减征比例或年减免税额
			else if(jmdj != null && jmszrq >= xtrq){
				double JMBL = jmdj.getJZBL();
				double JMJE = jmdj.getNJMSE();
				derate_type = new Derate_Type(); 
				if (JMBL < 1 && 0 < JMBL) {
					bqri.getTaxInfo().setTaxConditionCode("C");
					derate_type.setDeductionDueType("P");// 减免税方案代码,Y
					derate_type.setDeductionDueProportion(JMBL);// 减免比例
					derate_type.setDeduction(0.00);// 减免金额
					derate_type.setTaxDepartment(jmdj.getZGSWJGMC());  // 主管税务机关名称			// MILI  减免信息不全  ZLL 2014-11-10 
					derate_type.setTaxDepartmentCode(jmdj.getKJSWJGDM()); // 开具税务机关代码
					CT = Tax_Type_Code.J_VOUCHER ;
				} else if (JMJE > 0) {
					bqri.getTaxInfo().setTaxConditionCode("C");
					derate_type.setDeductionDueType("A");// 减免税方案代码,Y
					derate_type.setDeductionDueProportion(0.00);// 减免比例
					derate_type.setDeduction(JMJE);// 减免金额
					derate_type.setTaxDepartment(jmdj.getZGSWJGMC());  // 主管税务机关名称
					derate_type.setTaxDepartmentCode(jmdj.getKJSWJGDM()); // 开具税务机关代码
					CT = Tax_Type_Code.J_VOUCHER ;
				} else if (JMBL == 1) {
					bqri.getTaxInfo().setTaxConditionCode("E");
					derate_type.setDeductionDueCode("O");// 减免税原因代码
					derate_type.setDeductionDueType("E");// 减免税方案代码,Y
//					derate_type.setDeductionDueProportion(1.00);// 减免比例
					derate_type.setTaxDepartment(jmdj.getZGSWJGMC());  // 主管税务机关名称
					derate_type.setTaxDepartmentCode(jmdj.getKJSWJGDM()); // 开具税务机关代码
					derate_type.setDeduction(0.00);// 减免金额
					CT = Tax_Type_Code.VOUCHER_M ;
					_flag = false ;    // 完税车、法定免税车、免税车不在查询之前是否有欠税的情况   mili 2015-1-29 16:38:23 
//					listWt = null ;	   // 完税车、法定免税车、免税车不在查询问题名单   mili 2015-4-21 14:53:07
				} else {
					derate_type.setDeductionDueType("O");// 减免税方案代码,Y
					derate_type.setDeductionDueProportion(0.00);// 减免比例
					derate_type.setDeduction(0.00);// 减免金额
					CT = Tax_Type_Code.VOUCHER_M ;
					_flag = false ;    // 完税车、法定免税车、免税车不在查询之前是否有欠税的情况   mili 2015-1-29 16:38:23  
//					listWt = null ;	   // 完税车、法定免税车、免税车不在查询问题名单   mili 2015-4-21 14:53:07
				}
				bqri.getTaxInfo().getCurrentTaxDue().setDerate(derate_type);
			}
			// 本地车凭证号完税
//			else if("T".equals(tax_type.getTaxConditionCode()) && wsdj != null && skssjsrq >= xtrq ){
			else if(wsdj != null && skssjsrq >= xtrq && M_START == skssjsrq){   // 本地车完税信息不完整   
				CT = Tax_Type_Code.VOUCHER_M ;
				if(bqri!=null&&bqri.getTaxInfo()!=null&&"P".equals(bqri.getTaxInfo().getTaxConditionCode()) && bqri.getTaxInfo().getCurrentTaxDue().getPaid()!=null){//bqri 入参P&&paidnotnull
					//wbzhao add 20151021 批改完税信息取入参
					paid_type.setTaxDepartment(bqri.getTaxInfo().getCurrentTaxDue().getPaid().getTaxDepartment());
					paid_type.setTaxDepartmentCode(bqri.getTaxInfo().getCurrentTaxDue().getPaid().getTaxDepartmentCode());
					paid_type.setTaxDocumentNumber(bqri.getTaxInfo().getCurrentTaxDue().getPaid().getTaxDocumentNumber());
				}else{
					paid_type.setTaxDepartment(wsdj.getZGSWJGMC());
					paid_type.setTaxDepartmentCode(wsdj.getKJSWJGDM());
					paid_type.setTaxDocumentNumber(wsdj.getWSPZH());
				}
				bqri.getTaxInfo().setTaxConditionCode("P");
				_flag = false ;    // 完税车、法定免税车、免税车不在查询之前是否有欠税的情况   mili 2015-1-29 16:38:23  
//				listWt = null ;	   // 完税车、法定免税车、免税车不在查询问题名单   mili 2015-4-21 14:53:07
			}
			// 外地车
			else if(!wai_flah){ 
				if("P".equals(taxConditionCode) && TaxDocumentNumber != null && !"".equals(TaxDocumentNumber)){
					CT = Tax_Type_Code.VOUCHER_M ;	// 外地车 完税
				}else if("C".equals(taxConditionCode) && !"E".equals(derate_type.getDeductionDueType())){
					CT = Tax_Type_Code.J_VOUCHER ;	// 外地车 减税
				}else if("E".equals(taxConditionCode)){ 
					CT = Tax_Type_Code.VOUCHER_M ;	// 外地车 免税
				}else{
					CT = Tax_Type_Code.NEW_CARS ;	// 外地车 正常纳税
				}
//				listWt = null ;	   // 完税车、法定免税车、免税车不在查询问题名单   mili 2015-4-21 14:53:07
			}
			// 减税车
			else if("C".equals(taxConditionCode) && !"E".equals(derate_type.getDeductionDueType())){
				// 投保查询时纳税类型为C，比例减免，填写减免金额  返回按比例减免，金额减免字段返回空
				if("P".equals(derate_type.getDeductionDueType())){
					derate_type.setDeduction(0.0);
				}
				CT = Tax_Type_Code.J_VOUCHER ;
			}
			// 凭证号免税  ---判断方法：必须有开具免税凭证的机关单位并提供该车辆的免税凭证
			else if("E".equals(taxConditionCode)){
				if("8".equals(bqri.getChangeType()) || "10".equals(bqri.getChangeType())){
					J_VOUCHER_DQ = "T_E_S";		// 免税退短期
				}
				CT = Tax_Type_Code.VOUCHER_M ;
				_flag = false ;    // 完税车、法定免税车、免税车不在查询之前是否有欠税的情况   mili 2015-1-29 16:38:23  
//				listWt = null ;	   // 完税车、法定免税车、免税车不在查询问题名单   mili 2015-4-21 14:53:07
			}
			// 凭证号完税
			else if("P".equals(taxConditionCode) && TaxDocumentNumber != null && !"".equals(TaxDocumentNumber)){
				if(rkmx_w != null){  
					CT = Tax_Type_Code.VOUCHER_M ;     // 有关于完税的车 还要多测测  MILI  2014-11-3   投保法定免税车批改成本地普通车纳税类型为P，返回正常纳税
//					bqri.getTaxInfo().setTaxConditionCode("T");
//					bqri.getTaxInfo().getCurrentTaxDue().setPaid(new Paid_Type());
				}else{
					if(date_s + 1 == date_t){	//	2014-10-16 ZYY BUG 6 mili 增加一个 if 分支
						CT = Tax_Type_Code.NEW_CARS ;
						bqri.getTaxInfo().setTaxConditionCode("T");
						VT_FLAG = false ;
						taxstartdate =  DateUtil.getStringDate(DateUtil.getStringDate(T_START,"yyyy") + "-01-01" , null);
					}else{
						CT = Tax_Type_Code.VOUCHER_M ;
					}
				}
				_flag = false ;    // 完税车、法定免税车、免税车不在查询之前是否有欠税的情况   mili 2015-1-29 16:38:23 
//				listWt = null ;	   // 完税车、法定免税车、免税车不在查询问题名单   mili 2015-4-21 14:53:07
			}
			
			//wbzhao20151022 新能源、节约能源判定start
			// 新能源车  ---判断方法：与库中配置的新能源车型匹配。参考《新能源车型目录.xlsx》
//			else if(flag_xn){
			else if(eType==1){
				shangp = false ;
				derate_type = new Derate_Type() ; 
				derate_type.setDeductionDueType("E");   // 方案
				derate_type.setDeductionDueCode("E");	// 原因
				bqri.getTaxInfo().getCurrentTaxDue().setDerate(derate_type);
				bqri.getTaxInfo().setTaxConditionCode("E");
				CT = Tax_Type_Code.VOUCHER_M ;
			}
			// 节约能源-------- 判断方法：与库中配置的节约能源车型匹配。参考《新能源车型目录.xlsx》
//			else if(flag_jy){
			else if(eType==2){
				derate_type = new Derate_Type() ; 
				derate_type.setDeductionDueType("P");   // 方案
				derate_type.setDeductionDueCode("E");	// 原因
				derate_type.setDeductionDueProportion(0.5);	// 减免比例
				bqri.getTaxInfo().getCurrentTaxDue().setDerate(derate_type);
				bqri.getTaxInfo().setTaxConditionCode("C");
				dear_pai = true ;
				CT = Tax_Type_Code.J_VOUCHER ;
			}
			//wbzhao20151022 新能源、节约能源判定end
			
			// 短期车  ----- 判断方法：非新车、非临时上路车、并且投保时间小于一年
			else if(days < DQ_Number){
				CT = Tax_Type_Code.DQ_VOUCHER ;
			}
			// 新车算税  
			else if(boolNewCar && !anyCode.equals("3") && !anyCode.equals("2")
					&& isCondition && !"E".equals(taxConditionCode) && xtrq == djrq){
				CT = Tax_Type_Code.NEW_CARS ;  
			}
			// 新车 ,但是有欠税的  判断方法：null
			else if(djrq < xtrq && xtrq > Newcaryear && rkmx == null){
				CT = Tax_Type_Code.NEW_OWING ;
			}
			// 初登日期往前批改的情况下        需要重新算税
			else if(rkmx != null && DateUtil.getStringDates(rkmx.getFIRSTREGISTERDATE()).after(DateUtil.getStringDates(firstTime))){
				rkmx = null ;
				listRk = null ;
				CT = Tax_Type_Code.NEW_OWING ;
			}
			// ------       
			else{
				CT = Tax_Type_Code.NEW_OWING ;
			}
			// 退短期的情况下  不涉及欠税
			if("8".equals(bqri.getChangeType()) || "10".equals(bqri.getChangeType())){
				bqri.getVehicleInfo().setFirstRegisterDate(DateUtil.getString(new Date(),"yyyy-MM-dd"));
			}
		}
		// 针对 投保时是法定免税车  或者 要批改为法定免税车   增加一个 税款起期
		if("02".equals(ServiceType)){
//			listRk = null ;
			int _fd = 4 ;
			if(rkmx == null){
				_fd = 4 ;
			}else{
				_fd = FDMianShui.getFDCL(rkmx.getHPHM());		// 根据车牌号判断是否是法定免税  或者是 退保短期情况
			}
			// 退短期
			if(_fd != 4 || fd != 4 || "8".equals(bqri.getChangeType()) || "10".equals(bqri.getChangeType())){
				if(fd != 4){  // ZXL BUG 124 2014-10-11 10:42:33  MILI
					// 法定免税车 批改为法定免税车情况下   税款起期为 批改后的 初登日期
				}else{
					taxstartdate = new Date();	
				}
				AT = null ;  // 投保滞纳金关闭问题车纳税类型为E-投保确认-退保短期
				J_VOUCHER_DQ = "TS" ;
				// 提前续保算税开始日期
				if(rkmx != null){
					if(cartype_assist.Xubao(rkmx.getPAYDATE())){
//						taxstartdate = DateUtil.getStringDate(sdfy.format(rkmx.getInsureStartDate()) + "-01-01",null);//rkmx.getInsureStartDate();	//提前续保 退短期 本年税款起期不正确20140923
						taxstartdate =  DateUtil.getStringDate(DateUtil.getStringDate(rkmx.getInsureStartDate(),"yyyy") + "-01-01" , null);
					}
				}
				tuibao = false ;
			}
			// 退保长期  法定免税车的情况下
			if(("9".equals(bqri.getChangeType()) || "11".equals(bqri.getChangeType())|| "12".equals(bqri.getChangeType())
					|| "13".equals(bqri.getChangeType()) || "14".equals(bqri.getChangeType())) && _fd != 4 ){
				taxstartdate = null ;
				tuibao = false ;
			}
			if(rkmx != null && "2".equals(rkmx.getSPECIALCARTYPE()) && tuibao){	// 临时入境车辆 批改其他车辆时 税款始期是 XXXX-01-01
				taxstartdate =  DateUtil.getStringDate(DateUtil.getDateYYYY() + "-01-01",null);
			}
		}else{
			
		}
		
		
//		if(anyCode != null && anyCode.equals("3") && isCondition && !"E".equals(taxConditionCode)){
//			
//		}
//-------------------车辆信息 车辆算税信息判断结束 ---------------------------------------------------------------------------------------	
		
//-------------------封装 Map ---------------------------------------------------------------------------------------
		TT = cartype_assist.getTT(bqri);			// 车船税数据类型
		// 外地车不算欠税  完税车不算欠税(重复投保)  问题名单车不用算欠税  商品车不算欠税
		if(wai_flah && _flag && wtmdflag && shangp ){
			AT = cartype_assist.getAT(bqri,rkmx,ServiceType,dear_pai,wsdj);	// 欠税信息
			if(!"02".equals(ServiceType)){ 	// mili 2014-9-30 10:02:00   ZLL NO.2
				AT = cartype_assist.getAT(AT,listRk);						// 去掉以前缴过税的记录
			}
		}else{
			if(AT == null){
				AT = new AnnualTax_Type[0] ;	// 外地车 重复投保 不算欠税					
			}
		}
//--------------------提前续保-------------------------------------------------------------------------------		
		// 提前续保的处理  1=打开;0=关闭
		if("1".equals(TaxInsuSynFlag) && date_s + 1 == date_t){
			if(rkmx_w == null){	// 往年没有缴税纪录的情况下  往年税款信息在欠税对象里面
				AT = cartype_assist.setAT(AT,TT.getCurrentTaxDue(),T_START,T_END,firstTime,wsdj);
				if(dq_flag){	// 退过短期 并且是 提前续保 再次投保时 
					AT = cartype_assist.get_AnnuaTax(AT,T_dq,rkmx_end);
//					if(AT != null && AT.length == 1){
//						AT[0].setTaxStartDate(DateUtil.getStringDate(new Date(), null));
//					}
				}
				// 提前续保 退税时  欠税对象只有往年一条数据时 往年的税款始期不正确  应该是系统时间  
				if("8".equals(bqri.getChangeType()) || "10".equals(bqri.getChangeType()) ||
						"9".equals(bqri.getChangeType()) || "11".equals(bqri.getChangeType())
						|| "12".equals(bqri.getChangeType()) || "13".equals(bqri.getChangeType()) || "14".equals(bqri.getChangeType())  ){
					if(fd != 4 && fd != 0){		// 提前续保 退税时  欠税对象只有往年一条数据时 并且投保是 法定免税车
						AT = new AnnualTax_Type[0];
					}else if("8".equals(bqri.getChangeType()) || "10".equals(bqri.getChangeType())){
						if(AT != null && AT.length == 1){
							if(DateUtil.getDateYYYY().equals(AT[0].getTaxStartDate().substring(0, 4))){
								AT[0].setTaxStartDate(DateUtil.getStringDate(new Date(),null));
							}
						}
					}
				}
			}
		}else{
			if(date_s + 1 == date_t){
				T_START = DateUtil.getStringDates(mytime + DateUtil.getStringDate(T_START,"yyyy-MM-dd HH:mm:ss").substring(4, 19)) ;
				T_END = DateUtil.getStringDates(mytime + DateUtil.getStringDate(T_END,"yyyy-MM-dd HH:mm:ss").substring(4, 19)) ;
			}
		}
		
		// 临时入境车辆 不算欠税  提前续保时 不算往年税款   
		if(!VT_FLAG){
			AT = new AnnualTax_Type[0];
		}
//----------------------------Map封装开始----------------------------------------------------------
		
		map.put("WTMD",listWt);					// 问题名单
		map.put("DuplicateInsure", CF);			// 重复投保标志
		map.put("CT", CT);						// 车辆类型
		map.put("VT", VT);						// 车辆信息数据类型
		map.put("TT", TT);						// 车船税数据类型
		map.put("AT", AT);						// 欠税信息
		map.put("rkmx", rkmx);					// 入库明细
		map.put("insureStartDate", T_START);	// 投保开始日期
		map.put("insureEndDate", T_END);		// 投保结束日期
		map.put("CIN",car_id_no);				// 车辆匹配ID、车辆问题序列号
		map.put("ServiceType",ServiceType);		// 接口标志
		map.put("taxstartdate",taxstartdate);	// 税款起期
		map.put("TSCLBZ",J_VOUCHER_DQ); 		// 退税处理标志
		map.put("TaxPaidFlag",TaxPaidFlag);		// 批改时传投保时是否已缴税标志
		map.put("bxcdri",bxcdri);	// 新加一个 保险公司上传日期  mili  2016-1-22 17:09:07
//-------------------------Map封装完成---------------------------------------------------------------------------------------------		
		if("Y".equals(CfgLoader.getConfigValue("SysCode_Log", "Log"))){
			log.endLog();
		}
		return map ; 
	}
}
