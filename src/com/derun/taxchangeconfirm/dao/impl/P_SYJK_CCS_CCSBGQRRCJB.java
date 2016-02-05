package com.derun.taxchangeconfirm.dao.impl;

import java.util.Date;

import com.derun.beans.BaseChangeConfirmReqInfo;
import com.derun.beans.TaxDealCode_Type;
import com.derun.common.util.DateUtil;
import com.derun.model.po.SYJK_CCS_CCSBGQRRCJB;
import com.derun.model.po.SYJK_CCS_CODE;

/**
 * @author MILI
 * @time 2014-3-21 9:50:35
 * @描述：插入对象 SYJK_CCS_CCSBGQRRCJB 封装
 * */
public class P_SYJK_CCS_CCSBGQRRCJB {
	/**
	 * @author MILI
	 * @time 2014-3-21 9:50:35
	 * @描述：插入对象 SYJK_CCS_CCSBGQRRCJB 封装
	 * */
	public SYJK_CCS_CCSBGQRRCJB getCcsbgrrcjbb(BaseChangeConfirmReqInfo BCCRI,
			TaxDealCode_Type taxQueryNo, String taxConfirmNo,
			double changeSumTax,SYJK_CCS_CODE code){
		SYJK_CCS_CCSBGQRRCJB ccsbgqrrcjb = new SYJK_CCS_CCSBGQRRCJB();
		String BCFlag = "2";
		if (BCCRI.getChangeConfirmNo() != null) {
			if (BCCRI.getChangeConfirmNo().getTaxDealCode_Type() != null
					&& !"".trim().equals(
							BCCRI.getChangeConfirmNo().getTaxDealCode_Type()
									.trim())) {
				BCFlag = "1";
			}
		}
		ccsbgqrrcjb.setCHANGEQUERYNO( BCCRI.getChangeQueryNo().getTaxDealCode_Type());			// 车船税查询码
		if ("1".equals(BCCRI.getCalcTaxFlag()) && "2".equals(BCFlag)) {
			ccsbgqrrcjb.setCHANGECONFIRMNO(taxQueryNo.getTaxDealCode_Type());					// 车船税确认码
		} else {
			ccsbgqrrcjb.setCHANGECONFIRMNO(BCCRI.getChangeConfirmNo().getTaxDealCode_Type());	// 车船税确认码
		}
		ccsbgqrrcjb.setCALCTAXFLAG(BCCRI.getCalcTaxFlag());		// 算税标志
		if (BCCRI.getTaxInfo() != null && BCCRI.getTaxInfo().getTaxAmount() != null) {
			ccsbgqrrcjb.setTAXAMOUNT_FLAG(BCCRI.getTaxInfo().getTaxAmount().getTaxAmount_Flag());
			ccsbgqrrcjb.setANNUALTAXDUE(BCCRI.getTaxInfo().getTaxAmount().getAnnualTaxDue());
			ccsbgqrrcjb.setSUMTAXDEFAULT(BCCRI.getTaxInfo().getTaxAmount().getSumTaxDefault());
			ccsbgqrrcjb.setSUMOVERDUE(BCCRI.getTaxInfo().getTaxAmount().getSumOverdue());
			ccsbgqrrcjb.setSUMTAX(BCCRI.getTaxInfo().getTaxAmount().getSumTax());
		} else {
			ccsbgqrrcjb.setTAXAMOUNT_FLAG(null);
			ccsbgqrrcjb.setANNUALTAXDUE(0.0);
			ccsbgqrrcjb.setSUMTAXDEFAULT(0.0);
			ccsbgqrrcjb.setSUMOVERDUE(0.0);
			ccsbgqrrcjb.setSUMTAX(0.0);
		}
		ccsbgqrrcjb.setLOGINNAME(code.getCODENAME());		// 操作员
		ccsbgqrrcjb.setREVENUECODE(code.getCODENAME());
		ccsbgqrrcjb.setSJCJRQ(new Date());
		ccsbgqrrcjb.setSJCJFS("0");
		String DYM = BCCRI.getTaxPrintNo() == null ? "" : BCCRI.getTaxPrintNo()
				.getTaxDealCode_Type();
		ccsbgqrrcjb.setTAXPRINTNO(DYM);						// 车船税打印码
		String Ptzt = "";
		if ("2".equals(BCCRI.getCalcTaxFlag())
				|| "3".equals(BCCRI.getCalcTaxFlag()) || "1".equals(BCFlag)) {
			if ("R".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				Ptzt = "4";
			} else if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				Ptzt = "1";
			} else {
				Ptzt = "0";
			}
		} else {
			if ("R".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				Ptzt = "4";
			} else if ("P".equals(BCCRI.getTaxInfo().getTaxConditionCode())) {
				Ptzt = "1";
			} else {
				Ptzt = "0";
			}
		}
		ccsbgqrrcjb.setDECLAREDSTATUS(Ptzt);
		if (BCCRI.getVehicleInfo() != null) {
			ccsbgqrrcjb.setLICENSEPLATENO(BCCRI.getVehicleInfo().getLicensePlateNo());
			ccsbgqrrcjb.setLICENSEPLATETYPE(BCCRI.getVehicleInfo().getLicensePlateType());
			ccsbgqrrcjb.setMOTORTYPECODE(BCCRI.getVehicleInfo().getMotorTypeCode());
			ccsbgqrrcjb.setENGINENO(BCCRI.getVehicleInfo().getEngineNo());
			ccsbgqrrcjb.setVIN(BCCRI.getVehicleInfo().getVIN());
			ccsbgqrrcjb.setMADEFACTORY(BCCRI.getVehicleInfo().getMadeFactory());
			ccsbgqrrcjb.setMOTORUSAGETYPECODE(BCCRI.getVehicleInfo().getMotorUsageTypeCode());
			ccsbgqrrcjb.setNOLICENSEFLAG(BCCRI.getVehicleInfo().getNoLicenseFlag());
			ccsbgqrrcjb.setMODEL(BCCRI.getVehicleInfo().getModel());
			String fisdate = "";
			if (BCCRI.getVehicleInfo() != null
					&& BCCRI.getVehicleInfo().getFirstRegisterDate() != null) {
				if (BCCRI.getVehicleInfo().getFirstRegisterDate().length() >= 10) {
					fisdate = BCCRI.getVehicleInfo().getFirstRegisterDate()
							.substring(0, 10);
				}
			}
			ccsbgqrrcjb.setFIRSTREGISTERDATE(DateUtil.getStringDate(fisdate,null));
			ccsbgqrrcjb.setVEHICLETYPE(BCCRI.getVehicleInfo().getVehicleType());
			ccsbgqrrcjb.setRATEDPASSENGERCAPACITY(BCCRI.getVehicleInfo().getRatedPassengerCapacity());
			ccsbgqrrcjb.setTONNAGE(BCCRI.getVehicleInfo().getTonnage());
			ccsbgqrrcjb.setWHOLEWEIGHT(BCCRI.getVehicleInfo().getWholeWeight());
			ccsbgqrrcjb.setDISPLACEMENT(BCCRI.getVehicleInfo().getDisplacement());
			ccsbgqrrcjb.setPOWER(BCCRI.getVehicleInfo().getPower());
			ccsbgqrrcjb.setFUELTYPE(BCCRI.getVehicleInfo().getFuelType());
			ccsbgqrrcjb.setSPECIALCARTYPE(BCCRI.getVehicleInfo().getSpecialCarType());
		} else {
			ccsbgqrrcjb.setLICENSEPLATENO(null);
			ccsbgqrrcjb.setLICENSEPLATETYPE(null);
			ccsbgqrrcjb.setMOTORTYPECODE(null);
			ccsbgqrrcjb.setENGINENO(null);
			ccsbgqrrcjb.setVIN(null);
			ccsbgqrrcjb.setMADEFACTORY(null);
			ccsbgqrrcjb.setMOTORUSAGETYPECODE(null);
			ccsbgqrrcjb.setNOLICENSEFLAG(null);
			ccsbgqrrcjb.setMODEL(null);
			ccsbgqrrcjb.setFIRSTREGISTERDATE(null);
			ccsbgqrrcjb.setVEHICLETYPE(null);
			ccsbgqrrcjb.setRATEDPASSENGERCAPACITY(0);
			ccsbgqrrcjb.setTONNAGE(0.0);
			ccsbgqrrcjb.setWHOLEWEIGHT(0.0);
			ccsbgqrrcjb.setDISPLACEMENT(0.0);
			ccsbgqrrcjb.setPOWER(0.0);
			ccsbgqrrcjb.setFUELTYPE(null);
			ccsbgqrrcjb.setSPECIALCARTYPE(null);
		}
		if (BCCRI.getVehicleOwnerInfo() != null) {
			ccsbgqrrcjb.setVEHICLEOWNERNAME(BCCRI.getVehicleOwnerInfo().getVehicleOwnerName());
			ccsbgqrrcjb.setCERTICODE(BCCRI.getVehicleOwnerInfo().getCredentialNo());
			ccsbgqrrcjb.setCREDENTIALCODE(BCCRI.getVehicleOwnerInfo().getCredentialCode());
			ccsbgqrrcjb.setADDRESS(BCCRI.getVehicleOwnerInfo().getAddress());
			ccsbgqrrcjb.setPHONENO(BCCRI.getVehicleOwnerInfo().getPhoneNo());
		} else {
			ccsbgqrrcjb.setVEHICLEOWNERNAME(null);
			ccsbgqrrcjb.setCERTICODE(null);
			ccsbgqrrcjb.setCREDENTIALCODE(null);
			ccsbgqrrcjb.setADDRESS(null);
			ccsbgqrrcjb.setPHONENO(null);
		}
		ccsbgqrrcjb.setTAXCONFIRMNO(taxConfirmNo);
		ccsbgqrrcjb.setSUMTAX(changeSumTax);
		String ct = "";
		if ("1".equals(BCCRI.getChangeType())) {
			ct = "4";
		} else {
			ct = BCCRI.getChangeType();
		}
		ccsbgqrrcjb.setCHANGETYPE(ct);
		ccsbgqrrcjb.setINSURESTARTDATE(BCCRI.getInsureStartDate());
		ccsbgqrrcjb.setINSUREENDDATE(BCCRI.getInsureEndDate());
		ccsbgqrrcjb.setCARMATCHID(BCCRI.getCarMatchId());
		return ccsbgqrrcjb ;
	}
}
