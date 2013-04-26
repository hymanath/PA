package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.service.IUserVoterService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserVoterService implements IUserVoterService{

	private static final Logger LOG = Logger.getLogger(UserVoterService.class);
	
	private IVoterCategoryValueDAO voterCategoryValueDAO;
	
	private IUserVoterCategoryValueDAO userVoterCategoryValueDAO;
	
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	
	private IVoterInfoDAO voterInfoDAO;
	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	private IVotersAnalysisService votersAnalysisService;
	
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public IUserVoterCategoryValueDAO getUserVoterCategoryValueDAO() {
		return userVoterCategoryValueDAO;
	}

	public void setUserVoterCategoryValueDAO(
			IUserVoterCategoryValueDAO userVoterCategoryValueDAO) {
		this.userVoterCategoryValueDAO = userVoterCategoryValueDAO;
	}

	public IVoterCategoryValueDAO getVoterCategoryValueDAO() {
		return voterCategoryValueDAO;
	}

	public void setVoterCategoryValueDAO(
			IVoterCategoryValueDAO voterCategoryValueDAO) {
		this.voterCategoryValueDAO = voterCategoryValueDAO;
	}


	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public List<SelectOptionVO> getUserVoterCategoryList(List<Long> userIdsList)
	{
		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>(0);
		try{
			if(userIdsList != null && userIdsList.size() > 0)
			{
				List<Object[]> list = voterCategoryValueDAO.getUserVoterCategories(userIdsList);
				
				if(list != null && list.size() > 0)
				for(Object[] params : list)
					resultList.add(new SelectOptionVO((Long)params[0],params[1].toString()));	
			}
			return resultList;
		}
		catch(Exception e)
		{
			LOG.error("error occured in the getUserVoterCategoryList() method in VotersAnalysis" , e) ;
			return resultList;
		}
	}
	
	public List<VotersDetailsVO> getAgeRangeByUserVoterCategory(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId)
	{
		 Map<Long,VotersDetailsVO> category = new HashMap<Long, VotersDetailsVO>();
		 Map<Long, Map<Long,VotersDetailsVO>> categoryValues = new HashMap<Long, Map<Long,VotersDetailsVO>>();
		 List<Object[]> votersList = null;
		try{
			 List<Object[]> categoriesAndValues = userVoterCategoryValueDAO.getCatergoryAndValues(attributeIds,userId);
			 String locationType1 = locationType;
			 if(locationType.equalsIgnoreCase("mandal"))
				{
					String mandalId= locationId.toString();
					String id=mandalId.substring(1);
					locationId = new Long(id);
					if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
						locationType = "mandal";
						
					}else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						locationType = "localElectionBody";
						locationType1 = IConstants.LOCALELECTIONBODY;
						List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationId);
						locationId = (Long) list.get(0);
					}
				}
			 Long reportLvlId = votersAnalysisService.getReportLevelId(locationType1);
			 Long totalVotersCount = voterInfoDAO.getVotersCountInALocation(reportLvlId,locationId,publicationId,constituencyId);
			 
			 Map<Long,VotersDetailsVO> categoryValue = null;
			 VotersDetailsVO votersDetailsVO = null;
			 Long totalVoters = 0l;
			   for(Object[] value:categoriesAndValues){
				 if(categoryValues.get((Long)value[0]) == null){
					 votersDetailsVO = new VotersDetailsVO();
					 votersDetailsVO.setId((Long)value[0]);
					 votersDetailsVO.setName(value[1] != null?value[1].toString():"");
					 category.put((Long)value[0],votersDetailsVO);
					
					 categoryValue = new HashMap<Long,VotersDetailsVO>();
					 categoryValues.put((Long)value[0], categoryValue);
				 }else{
					 categoryValue = categoryValues.get((Long)value[0]);
				 }
				 
				 if(categoryValue.get((Long)value[2]) == null){
					 votersDetailsVO = new VotersDetailsVO();
					 categoryValue.put((Long)value[2], votersDetailsVO);
				 }else{
					 votersDetailsVO =  categoryValue.get((Long)value[2]);
				 }
				 
				 votersDetailsVO.setId((Long)value[2]);
				 votersDetailsVO.setName(value[3] != null?value[3].toString():"");
			 }
			 if(!locationType.equalsIgnoreCase("hamlet"))
			 {
			 votersList = boothPublicationVoterDAO.getAgeWiseDetails(userId,attributeIds,locationType,locationId,constituencyId,publicationId,18l,25l);
			 if(votersList != null && votersList.size() > 0)
			   getVoterDetails(categoryValues,category,votersList,"18-25",totalVoters);
			 
			 votersList = boothPublicationVoterDAO.getAgeWiseDetails(userId,attributeIds,locationType,locationId,constituencyId,publicationId,26l,35l);
			 if(votersList != null && votersList.size() > 0)
			   getVoterDetails(categoryValues,category,votersList,"26-35",totalVoters);
			 
			 votersList = boothPublicationVoterDAO.getAgeWiseDetails(userId,attributeIds,locationType,locationId,constituencyId,publicationId,36l,45l);
			 if(votersList != null && votersList.size() > 0)
			  getVoterDetails(categoryValues,category,votersList,"36-45",totalVoters);
			 votersList = boothPublicationVoterDAO.getAgeWiseDetails(userId,attributeIds,locationType,locationId,constituencyId,publicationId,46l,60l);
			 if(votersList != null && votersList.size() > 0)
			  getVoterDetails(categoryValues,category,votersList,"46-60",totalVoters);
			 votersList = boothPublicationVoterDAO.getAgeWiseDetails(userId,attributeIds,locationType,locationId,constituencyId,publicationId,60l,150l);
			 if(votersList != null && votersList.size() > 0)
			  getVoterDetails(categoryValues,category,votersList,"60",totalVoters);
			 }
			 else if(locationType.equalsIgnoreCase("hamlet"))
			 {
				 votersList = boothPublicationVoterDAO.getAgeWiseDetailsForHamlet(userId,attributeIds,locationType,locationId,constituencyId,publicationId,18l,25l);
				 if(votersList != null && votersList.size() > 0)
				   getVoterDetails(categoryValues,category,votersList,"18-25",totalVoters);
				 
				 votersList = boothPublicationVoterDAO.getAgeWiseDetailsForHamlet(userId,attributeIds,locationType,locationId,constituencyId,publicationId,26l,35l);
				 if(votersList != null && votersList.size() > 0)
				   getVoterDetails(categoryValues,category,votersList,"26-35",totalVoters);
				 
				 votersList = boothPublicationVoterDAO.getAgeWiseDetailsForHamlet(userId,attributeIds,locationType,locationId,constituencyId,publicationId,36l,45l);
				 if(votersList != null && votersList.size() > 0)
				  getVoterDetails(categoryValues,category,votersList,"36-45",totalVoters);
				 votersList = boothPublicationVoterDAO.getAgeWiseDetailsForHamlet(userId,attributeIds,locationType,locationId,constituencyId,publicationId,46l,60l);
				 if(votersList != null && votersList.size() > 0)
				  getVoterDetails(categoryValues,category,votersList,"46-60",totalVoters);
				 votersList = boothPublicationVoterDAO.getAgeWiseDetailsForHamlet(userId,attributeIds,locationType,locationId,constituencyId,publicationId,60l,150l);
				 if(votersList != null && votersList.size() > 0)
				  getVoterDetails(categoryValues,category,votersList,"60",totalVoters); 
			 }
			 for(Long categoryKey:categoryValues.keySet()){
				 Map<Long,VotersDetailsVO> mainCategory1 = categoryValues.get(categoryKey);
				 VotersDetailsVO mainCategory2 = category.get(categoryKey);
				 mainCategory2.setTotalAssignedCount(totalVoters);
				 mainCategory2.setTotalVoters(totalVotersCount);
				 for(Long key:mainCategory1.keySet()){
					 votersDetailsVO = mainCategory1.get(key);
					 if(votersDetailsVO != null && votersDetailsVO.getTotalVoters() != null && votersDetailsVO.getTotalVoters().longValue() > 0l){
						 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters().longValue());
						 votersDetailsVO.setVotersPercentFor18To25((new BigDecimal(totalVoters*(100.0)/votersDetailsVO.getTotalVoters().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					 }else{
						 votersDetailsVO.setVotersPercentFor18To25("0.00");
					 }
				 }
				 mainCategory2.setVotersDetailsVOList(new ArrayList<VotersDetailsVO>(mainCategory1.values()));
			 }
		 }
		catch(Exception e)
		{
			LOG.error("error occured in the getAgeRangeByUserVoterCategory() method in VotersAnalysis" , e) ;
		}
		 return new ArrayList<VotersDetailsVO>(category.values());
	}
	
	
	
	public void getVoterDetails(Map<Long, Map<Long,VotersDetailsVO>> categoryValues,Map<Long,VotersDetailsVO> category,List<Object[]> votersList,String ageRange,Long totalVoters)
	{
		VotersDetailsVO votersDetailsVO = null;
		VotersDetailsVO categoryVO = null;
		Map<Long,VotersDetailsVO> categoryValue = null;
		
		if(votersList != null && votersList.size() > 0)
			for(Object[] params : votersList)
			{
				categoryVO = category.get((Long)params[2]);
				categoryValue = categoryValues.get((Long)params[2]);
				if(categoryValue != null){
					votersDetailsVO = categoryValue.get((Long)params[3]);
					if(votersDetailsVO != null){
						if(params[1].toString().equalsIgnoreCase("M"))
						{
							if(ageRange.equalsIgnoreCase("18-25")){
								
								votersDetailsVO.setMaleVotersCountBetween18To25((Long)params[0]);
									
							}else if(ageRange.equalsIgnoreCase("26-35")){
								
								votersDetailsVO.setMaleVotersCountBetween26To35((Long)params[0]);
									
							}else if(ageRange.equalsIgnoreCase("36-45")){
								
								votersDetailsVO.setMaleVotersCountBetween36To45((Long)params[0]);
								
							}
							else if(ageRange.equalsIgnoreCase("46-60")){
								
								votersDetailsVO.setMaleVotersCountBetween46To60((Long)params[0]);
								
							}
							else if(ageRange.equalsIgnoreCase("60")){
								
								votersDetailsVO.setMaleVotersCountAbove60((Long)params[0]);
								
							}
						}
						if(params[1].toString().equalsIgnoreCase("F"))
						{
                            if(ageRange.equalsIgnoreCase("18-25")){
                            	
                            	votersDetailsVO.setFemaleVotersCountBetween18To25((Long)params[0]);
                            	
                            		
							}else if(ageRange.equalsIgnoreCase("26-35")){
								
								votersDetailsVO.setFemaleVotersCountBetween26To35((Long)params[0]);
								
							}else if(ageRange.equalsIgnoreCase("36-45")){
								
								votersDetailsVO.setFemaleVotersCountBetween36To45((Long)params[0]);
								
							}
							else if(ageRange.equalsIgnoreCase("46-60")){
								
								votersDetailsVO.setFemaleVotersCountBetween46To60((Long)params[0]);
									
							}
							else if(ageRange.equalsIgnoreCase("60")){
								
								votersDetailsVO.setFemaleVotersCountAbove60((Long)params[0]);
								
							}
						}
						if(categoryVO.getTotalVoters() != null){
							categoryVO.setTotalVoters(categoryVO.getTotalVoters()+(Long)params[0]);
						}else{
							categoryVO.setTotalVoters((Long)params[0]);
						}
						
						totalVoters = totalVoters+(Long)params[0];
					}
				}
			}
		
		
		
	}
	
}
