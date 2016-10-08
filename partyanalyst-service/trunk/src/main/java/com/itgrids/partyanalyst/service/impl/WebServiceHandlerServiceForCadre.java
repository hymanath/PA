package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerServiceForCadre;

public class WebServiceHandlerServiceForCadre implements IWebServiceHandlerServiceForCadre {
		
	private static final Logger log = Logger.getLogger(WebServiceHandlerServiceForCadre.class);
	
	
	private ICadreRegistrationService  cadreRegistrationService;
	
	
	public void setCadreRegistrationService(ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
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
			returnList = cadreRegistrationService.getPanchayatOrConsList(mandalOrLocalElectionBodyId);
			
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
}
