package com.derun.monitors;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derun.model.po.SYJK_CCS_RKMX;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @author MILI
 * @time 2014-6-26 17:52:43
 * @描述: servlet 
 * */
public class Monitor_Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final static int SIZE = 8 ;
//	private static String Refresh = "Y" ;
	private static String TAXCONFIRMNO = "" ;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<SYJK_CCS_RKMX> list_mon = List_Monitor.getList();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		JSONArray jsonarray = new JSONArray();
		// 最多显现条数  如果 现有条数小于 规定条数  只显示现有条数
		int i = 0 ;
		int number = 0 ,listsize = list_mon.size();
		if(listsize <= SIZE){
			number = listsize ;
		}else{
			number = SIZE ;
		}
		i = listsize - 1 ;
		for(int j = 0 ;j < number ; j++){
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("TAXQUERYNO", list_mon.get(i).getTAXQUERYNO());		
			jsonobj.put("TAXPRINTNO", list_mon.get(i).getTAXPRINTNO());		
			jsonobj.put("TAXCONFIRMNO", list_mon.get(i).getTAXCONFIRMNO());	
			jsonobj.put("VIN", list_mon.get(i).getVIN());		
			jsonobj.put("HPHM", list_mon.get(i).getHPHM());					
			jsonobj.put("HPZL", list_mon.get(i).getHPZL());	
			jsonobj.put("CLLX", list_mon.get(i).getCLLX());				
			jsonobj.put("TAXCONDITIONCODE", list_mon.get(i).getTAXCONDITIONCODE());			
			jsonobj.put("TAXREGISTRYNUMBER", list_mon.get(i).getTAXREGISTRYNUMBER());					
			jsonobj.put("TAXPAYERNAME", list_mon.get(i).getTAXPAYERNAME());	
			jsonobj.put("TAXPAYERIDENTIFICATIONCODE", list_mon.get(i).getTAXPAYERIDENTIFICATIONCODE());	
			jsonobj.put("TAXLOCATIONCODE", list_mon.get(i).getTAXLOCATIONCODE());	
			jsonobj.put("TAXENDDATE", list_mon.get(i).getTAXENDDATE());	
			jsonobj.put("TAXSTARTDATE", list_mon.get(i).getTAXSTARTDATE());	
			jsonobj.put("TAXUNITTYPECODE", list_mon.get(i).getTAXUNITTYPECODE());	
			jsonobj.put("UNITRATE", list_mon.get(i).getUNITRATE());	
			jsonobj.put("ANNUALTAXAMOUNT", list_mon.get(i).getANNUALTAXAMOUNT());	
			jsonobj.put("TAXDEPARTMENTCODE", list_mon.get(i).getTAXDEPARTMENTCODE());	
			jsonobj.put("TAXDEPARTMENT", list_mon.get(i).getTAXDEPARTMENT());	
			jsonobj.put("TAXDOCUMENTNUMBER", list_mon.get(i).getTAXDOCUMENTNUMBER());	
			jsonobj.put("DEDUCTIONDEPARTMENTCODE", list_mon.get(i).getDEDUCTIONDEPARTMENTCODE());	
			jsonobj.put("DEDUCTIONDEPARTMENT", list_mon.get(i).getDEDUCTIONDEPARTMENT());	
			jsonobj.put("DEDUCTIONDUECODE", list_mon.get(i).getDEDUCTIONDUECODE());	
			jsonobj.put("DEDUCTIONDUETYPE", list_mon.get(i).getDEDUCTIONDUETYPE());	
			jsonobj.put("DEDUCTIONDUEPROPORTION", list_mon.get(i).getDEDUCTIONDUEPROPORTION());	
			jsonobj.put("DEDUCTION", list_mon.get(i).getDEDUCTION());	
			jsonobj.put("DEDUCTIONDOCUMENTNUMBER", list_mon.get(i).getDEDUCTIONDOCUMENTNUMBER());	
			jsonobj.put("TAXDUE", list_mon.get(i).getTAXDUE());	
			jsonobj.put("EXCEEDDATE", list_mon.get(i).getEXCEEDDATE());	
			jsonobj.put("EXCEEDDAYSCOUNT", list_mon.get(i).getEXCEEDDAYSCOUNT());	
			jsonobj.put("OVERDUE", list_mon.get(i).getOVERDUE());	
			jsonobj.put("TOTALAMOUNT", list_mon.get(i).getTOTALAMOUNT());	
			jsonobj.put("LOGINNAME", list_mon.get(i).getLOGINNAME());	
			jsonobj.put("REVENUECODE", list_mon.get(i).getREVENUECODE());	
			jsonobj.put("SJCJRQ", list_mon.get(i).getSJCJRQ());	
			jsonobj.put("PAYCOMPANYCODE", list_mon.get(i).getPAYCOMPANYCODE());	
			jsonobj.put("PAYDATE", list_mon.get(i).getPAYDATE());	
			jsonobj.put("TAXAMOUNT_FLAG", list_mon.get(i).getTAXAMOUNT_FLAG());	
			jsonobj.put("ANNUALTAXDUE", list_mon.get(i).getANNUALTAXDUE());	
			jsonobj.put("SUMTAXDEFAULT", list_mon.get(i).getSUMTAXDEFAULT());	
			jsonobj.put("SUMOVERDUE", list_mon.get(i).getSUMOVERDUE());	
			jsonobj.put("SUMTAX", list_mon.get(i).getSUMTAX());	
			jsonobj.put("LOGGEDOUT", list_mon.get(i).getLOGGEDOUT());	
			jsonobj.put("PLATFORMSTATE", list_mon.get(i).getPLATFORMSTATE());	
			jsonobj.put("CHANGETYPE", list_mon.get(i).getCHANGETYPE());	
			jsonobj.put("COUNTTAXTYPE", list_mon.get(i).getCOUNTTAXTYPE());	
			jsonobj.put("Refusetype", list_mon.get(i).getRefusetype());	
			jsonobj.put("FIRSTREGISTERDATE", list_mon.get(i).getFIRSTREGISTERDATE());	
			jsonobj.put("SPECIALCARTYPE", list_mon.get(i).getSPECIALCARTYPE());	
			jsonobj.put("TSBZ", list_mon.get(i).getTSBZ());	
			jsonobj.put("engineNo", list_mon.get(i).getEngineNo());	
//			jsonobj.put("insureStartDate", list_mon.get(i).getInsureStartDate());	
//			jsonobj.put("insureEndDate", list_mon.get(i).getInsureEndDate());	
			jsonobj.put("CARSERIALNO", list_mon.get(i).getCARSERIALNO());	
			jsonobj.put("CARMATCHID", list_mon.get(i).getCARMATCHID());	
			jsonobj.put("MOTORUSAGETYPECODE", list_mon.get(i).getMOTORUSAGETYPECODE());
			jsonobj.put("MODEL", list_mon.get(i).getMODEL());
			jsonobj.put("VEHICLETYPE", list_mon.get(i).getVEHICLETYPE());
			jsonobj.put("RATEDPASSENGERCAPACITY", list_mon.get(i).getRATEDPASSENGERCAPACITY());
			jsonobj.put("TONNAGE", list_mon.get(i).getTONNAGE());
			jsonobj.put("WHOLEWEIGHT", list_mon.get(i).getWHOLEWEIGHT());
			jsonobj.put("DISPLACEMENT", list_mon.get(i).getDISPLACEMENT());
			jsonobj.put("POWER", list_mon.get(i).getPOWER());
			jsonobj.put("FUELTYPE", list_mon.get(i).getFUELTYPE());
			if(!TAXCONFIRMNO.equals(list_mon.get(listsize - 1).getTAXCONFIRMNO())){
				jsonobj.put("REFRESH","Y");
			}else{
				jsonobj.put("REFRESH","N");
			}
			TAXCONFIRMNO = list_mon.get(listsize - 1).getTAXCONFIRMNO();
			i-- ;
			jsonarray.add(jsonobj);
		}
//		System.out.println(jsonarray);
		PrintWriter	out = resp.getWriter();
		out.print(jsonarray);
	}
}
