/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 20,2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEPaperDAO;
import com.itgrids.partyanalyst.dao.IEPaperUrlDAO;
import com.itgrids.partyanalyst.dto.EPaperVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.EPaper;
import com.itgrids.partyanalyst.model.EPaperUrl;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.service.IEPaperService;

public class EPaperService implements IEPaperService {

	private IEPaperDAO epaperDAO;
	private IConstituencyDAO constituencyDAO;
	private IDistrictDAO districtDAO;
	private IEPaperUrlDAO epaperUrlDAO;
	private final static Logger log =  Logger.getLogger(EPaperService.class);
	
	
	public IEPaperUrlDAO getEpaperUrlDAO() {
		return epaperUrlDAO;
	}

	public void setEpaperUrlDAO(IEPaperUrlDAO epaperUrlDAO) {
		this.epaperUrlDAO = epaperUrlDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IEPaperDAO getEpaperDAO() {
		return epaperDAO;
	}

	public void setEpaperDAO(IEPaperDAO epaperDAO) {
		this.epaperDAO = epaperDAO;
	}	
	
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	
	public List<SelectOptionVO> getDistrictsForState(Long stateId)
	{
		List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
		List<District> districtModels = districtDAO.findByStateId(stateId);
		SelectOptionVO district = null;
		try{
		for(District districtModel:districtModels )
		{
			district = new SelectOptionVO(districtModel.getDistrictId(), districtModel.getDistrictName());
			districts.add(district);
		}
		}catch(Exception ex){
			log.error("Exception Raised -->" + ex);
			return null;
		}
		return districts;
	}
	
	public Long getStateIdFromConstitunecyByAccessValue(Long accessValue)
	{	
		Long stateID = null;
		try{
		Constituency constituency = constituencyDAO.get(accessValue);
		stateID = constituency.getState().getStateId();
			}catch(Exception ex){
				log.error("Exception Raised -->" + ex);
				return null;
			}
		return stateID;
	}
	
	public Long getStateIdFromDistrictByDistrictId(Long districtId)
	{
		Long stateID = null;
		try{
		District district = districtDAO.get(districtId);
		stateID = district.getState().getStateId();
		}catch(Exception ex){
			log.error("Exception Raised -->" + ex);
			return null;
		}
		return stateID;
	}
	
	
	/**
	 * This method takes accessType and accessValue based on the user loging and 
	 * returns the list of epapers regarding that user constituency.	
	 * @param accessType
	 * @param accessValue
	 * @return List<EPaperVO>	
	 * */
	
	public List<EPaperVO> getEPapers(String accessType ,Long accessValue) {		
		List<EPaperVO> epaper =new  ArrayList<EPaperVO>();
		List epaperUrl = new ArrayList();	
		List<String> mainUrl = new ArrayList<String>();	
		List<Long> result;
		Long districtId;
		Long stateId;
		try{
		if(accessType.equalsIgnoreCase("mla") || accessType.equalsIgnoreCase("district")){			
			if(accessType.equalsIgnoreCase("mla")){
				if(log.isDebugEnabled())
					log.debug("Calling constituencyDAO.getDistrictIdByConstituencyId() ");	
			result = constituencyDAO.getDistrictIdByConstituencyId(accessValue);
			districtId = result.get(0).longValue();
			}
			else{
				districtId = accessValue;
			}		
			
			epaper  = getEPapersForDistrict(districtId);			
		}		
		else if(accessType.equalsIgnoreCase("mp") || accessType.equalsIgnoreCase("state") || accessType.equalsIgnoreCase("country")){
			if(accessType.equalsIgnoreCase("mp")){
				if(log.isDebugEnabled())
					log.debug("Calling constituencyDAO.getStateIdByConstituencyId()");	
				List<Long> stateIds = constituencyDAO.getStateIdByConstituencyId(accessValue);
				stateId = stateIds.get(0);
				}
			else  {
				stateId = accessValue;
				}
			if(log.isDebugEnabled())
				log.debug("Calling epaperDAO.findByStateId()");	
			List<EPaper> epapers = epaperDAO.findByStateId(stateId);
			for(EPaper parms :epapers){
				EPaperVO epaperVO = new EPaperVO();
				epaperVO.setPaperName(parms.getName().toString());
				if(parms.getCountryUrl().equalsIgnoreCase("null")){
				}
				else{
					epaperVO.setEpaperUrl(parms.getStateUrl().toString());
					epaperVO.setDistrictName("A.P Edition");
				}
				epaperVO.setMainUrl(parms.getCountryUrl());
				epaperVO.setLanguage(parms.getLanguage());
				epaperVO.setImage(parms.getImage());
			
				epaper.add(epaperVO);
			}
			}
		}catch(Exception ex){
			log.error("Exception Raised -->" + ex);
			return null;
		}
		return epaper;
	}


	/**
	 * This method is called by the getEPapers() which sets the 
	 * corresponding values into the VO's.	
	 * @param List districtId
	 * @return List<EPaperVO>	
	 * */
	public List<EPaperVO> getEPapersForDistrict(Long districtId){
		List<EPaperVO> epaper =new  ArrayList<EPaperVO>();
		try{
			if(log.isDebugEnabled())
				log.debug("Calling districtDAO.getDistrictNameByDistrictId()");	
		List list = districtDAO.getDistrictNameByDistrictId(districtId);
		Object[] results= (Object[]) list.get(0);
		String districtName = results[0].toString();
		Long stateID = new Long(results[1].toString());	
		if(log.isDebugEnabled())
			log.debug("Calling epaperDAO.findByStateId()");	
		List<EPaper> epapers = epaperDAO.findByStateId(stateID);
		for(EPaper parms :epapers){
			EPaperVO epaperVO = new EPaperVO();
			if(parms.getClassification().equalsIgnoreCase("np")){
			epaperVO.setPaperName(parms.getName().toString());
			epaperVO.setEpaperUrl(parms.getStateUrl().toString());
			epaperVO.setMainUrl(parms.getCountryUrl());
			epaperVO.setLanguage(parms.getLanguage());
			epaperVO.setImage(parms.getImage());
			epaperVO.setDistrictName("A.P Edition");			
			epaper.add(epaperVO);
			}
		}
		if(log.isDebugEnabled())
			log.debug("Calling epaperUrlDAO.findEPapersForDistrictByDistrictId()");
		List<EPaperUrl> epaperUrls = epaperUrlDAO.findEPapersForDistrictByDistrictId(districtId);
		for(EPaperUrl parms :epaperUrls){
			EPaperVO epaperVO = new EPaperVO();
			epaperVO.setPaperName(parms.getEpaper().getName().toString());	
			epaperVO.setEpaperUrl(parms.getDistrictUrl().toString());	
			epaperVO.setMainUrl(parms.getEpaper().getStateUrl().toString());
			epaperVO.setLanguage(parms.getEpaper().getLanguage());
			epaperVO.setImage(parms.getEpaper().getImage());
			epaperVO.setDistrictName(districtName.concat(" Edition"));			
			epaper.add(epaperVO);
		}
	}catch(Exception ex){
		log.error("Exception Raised -->" + ex);
		return null;
	}
		return epaper;	
	}
	}