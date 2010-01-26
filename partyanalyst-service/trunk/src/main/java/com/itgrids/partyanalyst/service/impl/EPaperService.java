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
import com.itgrids.partyanalyst.dto.EPaperVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.EPaper;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.service.IEPaperService;

public class EPaperService implements IEPaperService {

	private IEPaperDAO epaperDAO;
	private IConstituencyDAO constituencyDAO;
	private final static Logger log =  Logger.getLogger(EPaperService.class);
	private IDistrictDAO districtDAO;
	
	
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
		try{
		if(accessType.equalsIgnoreCase("mla")){		
			if(log.isDebugEnabled())
				log.debug("Calling constituencyDAO.getDistrictIdByConstituencyId() ");
			result = constituencyDAO.getDistrictIdByConstituencyId(accessValue);
			districtId = result.get(0).longValue();
			epaperUrl = epaperDAO.findEPapersForDistrictByDistrictId(districtId);
			if(log.isDebugEnabled())
				log.debug("Calling getEPaper method");
			epaper = getEPaper(epaperUrl);
		}
		else if(accessType.equalsIgnoreCase("district")){
			if(log.isDebugEnabled())
				log.debug("Calling epaperDAO.findEPapersForDistrictByDistrictId() ");
			epaperUrl = epaperDAO.findEPapersForDistrictByDistrictId(accessValue);
			if(log.isDebugEnabled())
				log.debug("Calling getEPaper method");
			epaper = getEPaper(epaperUrl);			
		}
		else {
			Long districtID=1L;
            int count=0,limit=0;
            char dot='.';
            if(log.isDebugEnabled())
                    log.debug("Calling epaperDAO.findMainEPapersForStateByStateId() ");
            epaperUrl = epaperDAO.findMainEPapersForStateByStateId(districtID);
            for(int i=0;i<epaperUrl.size();i++){
                    count=0;
            EPaperVO epaperVO = new EPaperVO();
            Object[] parms = (Object[]) epaperUrl.get(i);
                    epaperVO.setMainUrl(parms[0].toString());
                    String paperName = parms[0].toString().trim();
                    StringBuffer sb = new StringBuffer(paperName);                                
                    sb = sb.delete(0,11);
                    paperName = sb.toString().toUpperCase();
                    paperName = paperName.toUpperCase();
                    for(int j=0;j<paperName.length();j++){
                            if(paperName.charAt(j) == dot && count==0){
                                    limit=j;count++;
                            }
                            
                    }
                    epaperVO.setPaperName(paperName.substring(0,limit));
                    epaperVO.setLanguage(parms[1].toString());
                    epaperVO.setImage(parms[2].toString());
                    epaper.add(epaperVO);
            }        
    }}catch(Exception ex){
            log.debug("Exception Raised -->" + ex);
            return null;
    }
		return epaper;
	}
	
	/**
	 * This method takes districtId and returns the list of epapers for that district.	
	 * @param accessType
	 * @param accessValue
	 * @return List<EPaperVO>	
	 * */
	public List<EPaperVO> getEPapersForDistrict(Long districtId) {
		List<EPaperVO> epaper =new  ArrayList<EPaperVO>();
		List epaperUrl = new ArrayList();	
		try{
		if(log.isDebugEnabled())
			log.debug("Calling epaperDAO.findEPapersForDistrictByDistrictId() ");
		epaperUrl = epaperDAO.findEPapersForDistrictByDistrictId(districtId);
		if(log.isDebugEnabled())
			log.debug("Calling getEPaper method");
		epaper = getEPaper(epaperUrl);
		}catch(Exception ex){
			log.error("Exception Raised -->" + ex);
			return null;
		}
		return epaper;		
	}
	
	/**
	 * This method is called by the getEPapers() and getEPapersForDistrict which sets the 
	 * corresponding values into the VO's.	
	 * @param List epaperUrl
	 * @return List<EPaperVO>	
	 * */
	public List<EPaperVO> getEPaper(List epaperUrl){
		List<EPaperVO> epaper =new  ArrayList<EPaperVO>();
		char dot='.';
		int count=0,limit=0;
		
		try{
			for(int i=0;i<epaperUrl.size();i++){
				count=0;
				EPaperVO epaperVO = new EPaperVO();
				Object[] parms = (Object[]) epaperUrl.get(i);
				String paperName = parms[1].toString().trim();
				StringBuffer sb = new StringBuffer(paperName);				
				sb = sb.delete(0,11);
				paperName = sb.toString().toUpperCase();
				paperName = paperName.toUpperCase();
				for(int j=0;j<paperName.length();j++){
					if(paperName.charAt(j) == dot && count==0){
						limit=j;count++;
					}
				}
				epaperVO.setEpaperUrl(parms[0].toString());
				epaperVO.setMainUrl(parms[1].toString());
				epaperVO.setPaperName(paperName.substring(0,limit));
				epaperVO.setDistrictName(parms[2].toString());
				epaperVO.setLanguage(parms[3].toString());
				epaperVO.setImage(parms[4].toString());
				epaper.add(epaperVO);
			}
		}catch(Exception ex){
			log.error("Exception Raised -->" + ex);
			return null;
		}
		return epaper;		
	}
}
