package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.log.Logger;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.service.IConstituencyInfoService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;



public class ConstituencyInfoService implements IConstituencyInfoService{

	private IRegionServiceData regionServiceDataImp;
	private IStaticDataService staticDataService;
	private IPublicationDateDAO publicationDateDAO;
	private IVotersAnalysisService votersAnalysisService;
	private IBoothDAO boothDAO;
	
	
    //private static final Logger LOG = Logger.getLogger(ConstituencyInfoService.class);
	
	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}

	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}


	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	
	
	public List<VotersDetailsVO> getConstituencyBasicInfoById(Long constituencyId,Long publicationId,Long userId)
	{
			List<VotersDetailsVO> resultList = new ArrayList<VotersDetailsVO>();
			List<SelectOptionVO> subList = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> mandalList = new ArrayList<SelectOptionVO>();
		    List<SelectOptionVO> localbodiesList = new ArrayList<SelectOptionVO>();
		    List<SelectOptionVO> panchayatList = new ArrayList<SelectOptionVO>();
		  
		    List<Long> mandalIds = new ArrayList<Long>();
		    List<SelectOptionVO> localbodybooths = new ArrayList<SelectOptionVO>();
		    List<SelectOptionVO> localbodyWards = new ArrayList<SelectOptionVO>();
		    List<Long> panchayatIds = new ArrayList<Long>();
		try{
			
			subList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);	
			VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
			
			if(subList != null && subList.size() > 0)
			{
				for(SelectOptionVO params : subList)
				{
					if(params.getId().toString().substring(0,1).trim().equalsIgnoreCase("2"))
					{
					mandalList.add(new SelectOptionVO((Long)params.getId(),params.getName().toString()));
					mandalIds.add(new Long(params.getId().toString().substring(1)));
					}
					else
					{
					localbodiesList.add(new SelectOptionVO((Long)params.getId(),params.getName().toString()));
					}
				}
			}
			
		// mandal Panchayats
			if(mandalList != null && mandalList.size() > 0)
			{
				 List<SelectOptionVO> panchayatsList = staticDataService.getPanchayatiesByMandalIdsListAndConstituencyId(mandalIds,constituencyId,publicationId);
				 if(panchayatsList != null && panchayatsList.size() > 0)
					  for(SelectOptionVO list : panchayatsList)
						  for(SelectOptionVO pid : list.getSelectOptionsList())
						  panchayatIds.add(pid.getId());
				 List<SelectOptionVO> panchayatBoothsList = staticDataService.getBoothsByPanchayatIdsListAndConstituencyIdInAPublication(panchayatIds,constituencyId,publicationId);
				 List<SelectOptionVO> panchayatHamletsList = staticDataService.getHamletsByPanchayatIdsList(panchayatIds);
				 
					for(SelectOptionVO mandals : mandalList)
					{
						SelectOptionVO vo = staticDataService.getSelectOptionVOFromResultList(panchayatsList, new Long(mandals.getId().toString().substring(1)));
						if(vo != null)
							mandals.setSelectOptionsList(vo.getSelectOptionsList());
							
						for(SelectOptionVO panchayat : mandals.getSelectOptionsList())
						{
							SelectOptionVO vo1 = staticDataService.getSelectOptionVOFromResultList(panchayatBoothsList,panchayat.getId());	
							if(vo1 != null)
							panchayat.setSelectOptionsList(vo1.getSelectOptionsList());
							SelectOptionVO vo2 = staticDataService.getSelectOptionVOFromResultList(panchayatHamletsList,panchayat.getId());	
							if(vo2 != null && vo2.getSelectOptionsList().size() > 0)
							panchayat.setSelectOptionsList1(vo2.getSelectOptionsList());
						}
						
						
					}
			}
			
		// Local body booths and wards
			
			if(localbodiesList != null && localbodiesList.size() > 0)
			{
				for(SelectOptionVO localbodyId : localbodiesList)
				{
				localbodyWards = votersAnalysisService.getWardsMunicipality(new Long(localbodyId.getId().toString().substring(1)),publicationId, constituencyId , userId);
				if(localbodyWards != null && localbodyWards.size() > 0)
				localbodyId.setSelectOptionsList(localbodyWards);
					if(localbodyWards == null || localbodyWards.size() <= 0)
				localbodybooths = votersAnalysisService.getBoothsInMunicipality(new Long(localbodyId.getId().toString().substring(1)), publicationId,constituencyId);
					else
					{
						//if(!localbodyWards.get(0).getType().equalsIgnoreCase("Greater Municipal Corp") )
							localbodybooths = votersAnalysisService.getBoothsInMunicipality(new Long(localbodyId.getId().toString().substring(1)), publicationId,constituencyId);
						/*else
							localbodybooths =getBoothsForLocalEleBodyByCOnstituencyId(constituencyId,new Long(localbodyId.getId().toString().substring(1)),publicationId);*/
						if(localbodyWards!= null && localbodyWards.size() > 0)
							localbodyWards =  votersAnalysisService.getBoothsInCustomWardsOfALocalElectionBody(localbodyWards, constituencyId, publicationId, userId);
				    }
				if(localbodybooths != null && localbodybooths.size() > 0)
				localbodyId.setSelectOptionsList1(localbodybooths);
				
				}
			}
			
			votersDetailsVO.setMandalList(mandalList);
			votersDetailsVO.setLocalbodiesList(localbodiesList);
			resultList.add(votersDetailsVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultList;
	}
	public List<VotersDetailsVO> getConstituencyBasicCountInfoById(Long constituencyId,Long userId)
	{
		List<VotersDetailsVO> resultList = new ArrayList<VotersDetailsVO>();
		List<SelectOptionVO> subList = new ArrayList<SelectOptionVO>();
		List<Long> mandalIds = new ArrayList<Long>();
		List<Long> localbodies = new ArrayList<Long>();
		Long latestPublicationId = publicationDateDAO.getLatestPublicationId();
		try{
			VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
			subList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);	
			if(subList!=null && subList.size() > 0)
			{
				for(SelectOptionVO vo : subList)
				{
					if(vo.getId().toString().substring(0,1).trim().equalsIgnoreCase("2"))
						mandalIds.add(new Long(vo.getId().toString().substring(1)));
					else
						localbodies.add((Long)vo.getId());	
				}
				
			}
			votersDetailsVO.setTotalmandals(new Long(mandalIds.size()));
			votersDetailsVO.setNoOfLocalBodies(new Long(localbodies.size()));
			if(mandalIds != null && mandalIds.size() > 0)
			{
				//List<SelectOptionVO> panchayatsList = staticDataService.getPanchayatiesByMandalIdsListAndConstituencyId(mandalIds,constituencyId,latestPublicationId);
				List<Object[]> panchayatsList = boothDAO.getPanchayatiesByMandalsListAndConstituencyId(mandalIds, constituencyId, latestPublicationId);
				if(panchayatsList !=null && panchayatsList.size() > 0)
				votersDetailsVO.setTotalPanchayats(new Long(panchayatsList.size()));
			}
			else
			votersDetailsVO.setTotalPanchayats(0l);	
			
			votersDetailsVO.setTotalBooths(new Long(boothDAO.getBoothIdsByConstituencyIdAndPublicationId(constituencyId,latestPublicationId).size()));
			votersDetailsVO.setTotalNoOfWards(boothDAO.getWardsListByConstituencyId(constituencyId,latestPublicationId).size());
			resultList.add(votersDetailsVO);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	 		
	 		
}
