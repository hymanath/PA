package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.CardPrintValidationUserVO;
import com.itgrids.partyanalyst.dto.CardPrintValidationVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ImageCadreVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadrePrintDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VoterSearchVO;
import com.itgrids.partyanalyst.dto.WebServiceCadreVO;
import com.itgrids.partyanalyst.dto.WebServiceOnlineCadreVO;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICadreRegistrationServiceNew;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerServiceForCadre;

public class WebServiceHandlerServiceForCadre implements IWebServiceHandlerServiceForCadre {
		
	private static final Logger log = Logger.getLogger(WebServiceHandlerServiceForCadre.class);
	
	
	private ICadreRegistrationService  cadreRegistrationService;
	private ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService;
	private ICadreRegistrationServiceNew cadreRegistrationServiceNew;
	
	public void setCadreRegistrationService(ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}
	

	public void setCoreDashboardCadreRegistrationService(
			ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService) {
		this.coreDashboardCadreRegistrationService = coreDashboardCadreRegistrationService;
	}
	

	public void setCadreRegistrationServiceNew(
			ICadreRegistrationServiceNew cadreRegistrationServiceNew) {
		this.cadreRegistrationServiceNew = cadreRegistrationServiceNew;
	}


	public List<IdAndNameVO> getStateWiseDistrict(Long stateId) 
	{
		log.debug("Entered into the getStateWiseDistrict() in WebServiceHandlerServiceForCadre ");
		
		List<IdAndNameVO> returnList = null;
		try 
		{
			returnList = cadreRegistrationService.getStateWiseDistrict(stateId);
			
		}catch (Exception e) 
		{
			log.error("Entered into the getStateWiseDistrict() in WebServiceHandlerServiceForCadre ");
		}
		return returnList;
	}
	
	public List<IdAndNameVO> getDistrictWiseConstituency(Long districtId) 
	{
		log.debug("Entered into the getDistrictWiseConstituency() in WebServiceHandlerServiceForCadre ");
		
		List<IdAndNameVO> returnList = null;
		try 
		{
			returnList = cadreRegistrationService.getDistrictWiseConstituency(districtId);
			
		}catch (Exception e) 
		{
			log.error("Entered into the getDistrictWiseConstituency() in WebServiceHandlerServiceForCadre ");
		}
		return returnList;
	}
	public List<IdAndNameVO> getConstitencyWiseTehsil(Long consistuencyId) 
	{
		log.debug("Entered into the getConstitencyWiseTehsil() in WebServiceHandlerServiceForCadre ");
		
		List<IdAndNameVO> returnList = null;
		try 
		{
			returnList = cadreRegistrationService.getConstitencyWiseTehsil(consistuencyId);
			
		}catch (Exception e) 
		{
			log.error("Entered into the getConstitencyWiseTehsil() in WebServiceHandlerServiceForCadre ");
		}
		return returnList;
	}
	
	public List<IdAndNameVO> getAllPanchayatsForMandal(Long mandalOrLocalElectionBodyId) 
	{
		log.debug("Entered into the getAllPanchayatsForMandal() in WebServiceHandlerServiceForCadre ");
		
		List<IdAndNameVO> returnList = null;
		try 
		{
			returnList = cadreRegistrationService.getPanchayatOrConsList(mandalOrLocalElectionBodyId,"1");
			
		}catch (Exception e) 
		{
			log.error("Entered into the getConstitencyWiseTehsil() in WebServiceHandlerServiceForCadre ");
		}
		return returnList;
	}
	
	public List<IdAndNameVO> getAllBoothsForPanchayat(Long panchayatId) 
	{
		log.debug("Entered into the getAllBoothsForPanchayat() in WebServiceHandlerServiceForCadre ");
		
		List<IdAndNameVO> returnList = null;
		try 
		{
			returnList = cadreRegistrationService.getBoothsList(panchayatId);
			
		}catch (Exception e) 
		{
			log.error("Entered into the getAllBoothsForPanchayat() in WebServiceHandlerServiceForCadre ");
		}
		return returnList;
	}
	
	public List<VoterSearchVO> getOnlineVotersBySearch(WebServiceOnlineCadreVO mainInputVO){
		
		List<VoterSearchVO> returnList = null;
		try{
			WebServiceCadreVO inputVO  = new WebServiceCadreVO();
			inputVO.setConstituencyId(mainInputVO.getConstituencyId());
			inputVO.setMandalId(mainInputVO.getMandalId());
			inputVO.setVillageId(mainInputVO.getVillageId());
			inputVO.setBoothId(mainInputVO.getBoothId());
			inputVO.setName(mainInputVO.getName());
			inputVO.setHouseNo(mainInputVO.getHouseNo());
			inputVO.setVoterNo(mainInputVO.getVoterNo());
			if(mainInputVO.getCadreSurveyUserId() == null || mainInputVO.getCadreSurveyUserId() ==0L)
				returnList = getVotersBySearch(inputVO);
			if(mainInputVO.getCadreSurveyUserId() != null && mainInputVO.getCadreSurveyUserId()>0L)
				returnList = getVoterDetailsBySearch(mainInputVO);
		}catch(Exception e) {
			log.error("Entered into the getOnlineVotersBySearch() in WebServiceHandlerServiceForCadre ");
		}
		return returnList;
	}

	public List<VoterSearchVO> getVoterDetailsBySearch(WebServiceOnlineCadreVO inputVO){
		List<VoterSearchVO> returnList = null;
		try{
			
			returnList = cadreRegistrationService.getVotersBySearch(inputVO.getConstituencyId(),inputVO.getMandalId(),inputVO.getVillageId(),inputVO.getBoothId(),inputVO.getName(),inputVO.getHouseNo(),inputVO.getVoterNo(),inputVO.getCadreSurveyUserId());
			
		}catch(Exception e) {
			log.error("Entered into the getVotersBySearch() in WebServiceHandlerServiceForCadre ");
		}
		return returnList;
	}

	public List<VoterSearchVO> getVotersBySearch(WebServiceCadreVO inputVO){
		
		List<VoterSearchVO> returnList = null;
		try{
			
			returnList = cadreRegistrationService.getVotersBySearch(inputVO.getConstituencyId(),inputVO.getMandalId(),inputVO.getVillageId(),inputVO.getBoothId(),inputVO.getName(),inputVO.getHouseNo(),inputVO.getVoterNo(),null);
			
		}catch(Exception e) {
			log.error("Entered into the getVotersBySearch() in WebServiceHandlerServiceForCadre ");
		}
		return returnList;
	}
	
	public NewCadreRegistrationVO getRegistrationPersonDetails(WebServiceCadreVO inputVO){
		
		NewCadreRegistrationVO newCadreRegistrationVO = null;
		try{
			newCadreRegistrationVO = coreDashboardCadreRegistrationService.getRegistrationPersonDetails(inputVO.getVoterId(),inputVO.getFamilyVoterId(),inputVO.getTdpCadreId(),inputVO.getStatus());
			
		}catch(Exception e) {
			log.error("Entered into the getRegistrationPersonDetails() in WebServiceHandlerServiceForCadre ");
		}
		return newCadreRegistrationVO;
	}
	
	public List<TdpCadreVO> getOnlineTdpCadresBySearch(WebServiceOnlineCadreVO mainInputVO){
		
		List<TdpCadreVO> cadreList = null;
		try{
			
			WebServiceCadreVO inputVO  = new WebServiceCadreVO();
			inputVO.setMemberShipNo(mainInputVO.getMemberShipNo());
			inputVO.setMobileNo(mainInputVO.getMobileNo());
			inputVO.setVoterNo(mainInputVO.getVoterNo());
			cadreList = getTdpCadresBySearch(mainInputVO.getCadreSurveyUserId(),inputVO);
			
		}catch(Exception e) {
			log.error("Entered into the getOnlineTdpCadresBySearch() in WebServiceHandlerServiceForCadre ");
		}
		return cadreList;
	}

	public List<TdpCadreVO> getTdpCadresBySearch(Long cadreSurveyUserId , WebServiceCadreVO inputVO){
		
		List<TdpCadreVO> cadreList = null;
		try{
			cadreList = cadreRegistrationService.getTdpCadresBySearch(cadreSurveyUserId,inputVO.getMemberShipNo(),inputVO.getMobileNo(), inputVO.getVoterNo());
			
		}catch(Exception e) {
			log.error("Entered into the getTdpCadresBySearch() in WebServiceHandlerServiceForCadre ");
		}
		return cadreList;
	}
	
	public void saveCadreImage(ImageCadreVO inputVO){
		
		try{
			 cadreRegistrationServiceNew.saveCadreImage(inputVO);
			
		}catch(Exception e) {
			log.error("Entered into the getRegistrationPersonDetails() in WebServiceHandlerServiceForCadre ");
		}
		
	}
	
	//Printing Related.
	public CardPrintValidationUserVO validateCardPrintUserLogin(String username,String password){
		
		CardPrintValidationUserVO cardPrintValidationUserVO = null;
		try{
			cardPrintValidationUserVO = cadreRegistrationServiceNew.validateCardPrintUserLogin(username , password);
			
		}catch(Exception e) {
			log.error("Entered into the validateCardPrintUserLogin() in WebServiceHandlerServiceForCadre class");
		}
		return cardPrintValidationUserVO;
	}
	
	public TdpCadrePrintDetailsVO getTdpCadrePrintDetailsByMemberShipId(String memberShipId){
		
		TdpCadrePrintDetailsVO tdpCadrePrintDetailsVO = null;
		try{
			tdpCadrePrintDetailsVO = cadreRegistrationServiceNew.getTdpCadrePrintDetailsByMemberShipId(memberShipId);
			
		}catch(Exception e) {
			log.error("Entered into the getTdpCadrePrintDetailsByMemberShipId() in WebServiceHandlerServiceForCadre class");
		}
		return tdpCadrePrintDetailsVO;
	}
	
	 public ResultStatus updateCardPrintValidStatus(CardPrintValidationVO inputVO){
		 
		   ResultStatus resultStatus = null;
			try{
				
				  resultStatus = cadreRegistrationServiceNew.updateCardPrintValidStatusNEW(inputVO);
				  
			}catch(Exception e) {
				log.error("Entered into the updateCardPrintValidStatus() in WebServiceHandlerServiceForCadre class");
			}
			return resultStatus;
	 }
	
}
