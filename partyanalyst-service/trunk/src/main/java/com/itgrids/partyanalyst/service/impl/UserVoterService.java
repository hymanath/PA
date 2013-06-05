package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAreaTypeDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICustomVoterGroupDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.hibernate.ConstituencyDAO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.CustomVoterGroup;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
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
	
	private IInfluencingPeopleDAO influencingPeopleDAO;
	private ICadreDAO cadreDAO;
    private ICandidateDAO candidateDAO;
    private IUserVoterDetailsDAO userVoterDetailsDAO;
    private IUserVoterCategoryDAO userVoterCategoryDAO;
    private IDelimitationConstituencyMandalService delimitationConstituencyMandalService; 
    private MandalInfoVO mandalInfoVO;
    private IRegionServiceData regionServiceDataImp;
    private IUserRolesDAO userRolesDAO; 
    private IUserDAO userDAO;
    
    private ICustomVoterGroupDAO customVoterGroupDAO;
    
    private IConstituencyDAO constituencyDAO;
    
    private IAreaTypeDAO areaTypeDAO;

    
	public IAreaTypeDAO getAreaTypeDAO() {
		return areaTypeDAO;
	}

	public void setAreaTypeDAO(IAreaTypeDAO areaTypeDAO) {
		this.areaTypeDAO = areaTypeDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public ICustomVoterGroupDAO getCustomVoterGroupDAO() {
		return customVoterGroupDAO;
	}

	public void setCustomVoterGroupDAO(ICustomVoterGroupDAO customVoterGroupDAO) {
		this.customVoterGroupDAO = customVoterGroupDAO;
	}

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
	
	public IInfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}

	public void setInfluencingPeopleDAO(IInfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
	}

	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
	
	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}
	
	public IUserVoterCategoryDAO getUserVoterCategoryDAO() {
		return userVoterCategoryDAO;
	}

	public void setUserVoterCategoryDAO(IUserVoterCategoryDAO userVoterCategoryDAO) {
		this.userVoterCategoryDAO = userVoterCategoryDAO;
	}

	public IDelimitationConstituencyMandalService getDelimitationConstituencyMandalService() {
		return delimitationConstituencyMandalService;
	}

	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}
	
	public MandalInfoVO getMandalInfoVO() {
		return mandalInfoVO;
	}

	public void setMandalInfoVO(MandalInfoVO mandalInfoVO) {
		this.mandalInfoVO = mandalInfoVO;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public IUserRolesDAO getUserRolesDAO() {
		return userRolesDAO;
	}

	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
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
				 if(value[4] != null)
				 votersDetailsVO.setOrderNo(new Long(value[4].toString()));
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
		
	@SuppressWarnings({ "unused", "unchecked" })
	public List<VoterVO> getCategoryWiseVoterData(VoterDataVO voterDataVO , Long userId , List<Long> categories)
	{
		List<VoterVO> voterData = new ArrayList<VoterVO>();
		VoterVO voterVO = null;
		List<Object[]> voters = null;
		List<Long> voterIds = new ArrayList<Long>();
		Long totalCount = 0l;
		Map<Long , VoterVO> voterMap = new HashMap<Long, VoterVO>();
		try {
			LOG.debug("entered into the getVoterData() method in VotersAnalysisSevice");
		
			if(voterDataVO.getBuildType().equalsIgnoreCase(IConstants.HAMLET))
				voters  = boothPublicationVoterDAO.getCategoryWiseVoterDetailsByHamletId(voterDataVO.getId(), voterDataVO.getConstituencyId(), voterDataVO.getCategoryId(), voterDataVO.getPublicationId(), userId, voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue(), voterDataVO.getDir(), voterDataVO.getSort());
			else
			 voters  = boothPublicationVoterDAO.getCategoryWiseVoterDetailsByCategoryId(voterDataVO.getBuildType(), voterDataVO.getId(), voterDataVO.getConstituencyId(), voterDataVO.getCategoryId(), voterDataVO.getPublicationId(), userId, voterDataVO.getStartIndex().intValue(), voterDataVO.getMaxIndex().intValue(), voterDataVO.getDir(), voterDataVO.getSort());
			
			//totalCount =(Long) boothPublicationVoterDAO.getcategoryWiseVotersCount(voterDataVO.getBuildType(), voterDataVO.getId(), voterDataVO.getConstituencyId(), voterDataVO.getCategoryId(), voterDataVO.getPublicationId(), userId).get(0);
			
			if(voters != null && voters.size() > 0)
			{
				totalCount = new Long(voters.size());
				for (Object[] voterDetails : voters) {
					Voter voterInfo = (Voter) voterDetails[0];
					
					voterVO = new VoterVO();
					voterVO.setVoterId(voterInfo.getVoterIDCardNo());
					voterVO.setName(voterInfo.getName());
					voterVO.setGender(voterInfo.getGender());
					voterVO.setAge(voterInfo.getAge());
					voterVO.setHouseNo(voterInfo.getHouseNo());
					voterVO.setMobileNo(voterInfo.getMobileNo());
					voterVO.setRelativeFirstName(voterInfo.getRelativeName());
					voterVO.setPartNo(new Long(voterDetails[2].toString()));
					voterVO.setTotalVoters(totalCount);
					voterVO.setVoterIds(voterInfo.getVoterId());
					voterIds.add(voterInfo.getVoterId());
					voterMap.put(voterInfo.getVoterId(), voterVO);
					voterData.add(voterVO);
					voterVO.setSerialNo((Long)(voterDetails[1]));
					
				}
			}
			
			List<Long> influencingPeopleList = influencingPeopleDAO.findInfluencingPeopleDetails(voterIds,userId);
			if(influencingPeopleList != null && influencingPeopleList.size() > 0)
			{
				
				for (Long influencingPeople : influencingPeopleList) {
					if(influencingPeople != null)
					{
						voterVO = voterMap.get(influencingPeople);
						voterVO.setInfluencePerson(true);
					}
				}
			}
			
			List<Long> cadrePeopleList = cadreDAO.findCadrePeopleDetails(voterIds,userId);
			if(cadrePeopleList != null && cadrePeopleList.size() > 0)
			{
				for (Long cadrePeople : cadrePeopleList) {
					if(cadrePeople != null)
					{
						voterVO = voterMap.get(cadrePeople);
						voterVO.setIsCadrePerson(true);
					}
				}
			}
			
			List<Long> candidatePeopleList = candidateDAO.findCandidatePeopleDetails(voterIds);
			if(candidatePeopleList != null && candidatePeopleList.size() > 0)
			{
				for (Long candidatePeople : candidatePeopleList) {
					if(candidatePeople != null)
					{
						voterVO = voterMap.get(candidatePeople);
						voterVO.setIsPoliticion(true);
					}
				}
			}
			if((voterDataVO.getPartyPresent() != null && voterDataVO.getPartyPresent()) || (voterDataVO.getCastePresent() !=null &&voterDataVO.getCastePresent()))
			{
				List<UserVoterDetails> votersPartyCastList = userVoterDetailsDAO.getAllUserVoterDetails(voterIds,userId);
				if(votersPartyCastList != null && votersPartyCastList.size() > 0)
				{
					for (UserVoterDetails voterPartyAndCasteDetails : votersPartyCastList) {
						voterVO = voterMap.get(voterPartyAndCasteDetails.getVoter().getVoterId());
						if(voterPartyAndCasteDetails != null)
						{
							
							voterVO.setPartyName(voterPartyAndCasteDetails.getParty()!=null ? voterPartyAndCasteDetails.getParty().getShortName():"");
							voterVO.setCasteName(voterPartyAndCasteDetails.getCasteState().getCaste() != null ? voterPartyAndCasteDetails.getCasteState().getCaste().getCasteName():"");
						}
						else
						{
							voterVO.setPartyName("");
							voterVO.setCasteName("");
						}
					}
				}
			}
			if(categories != null && categories.size() > 0){
				VoterVO category = null;
				 List<Object[]> votersPartyCastList = voterCategoryValueDAO.getVoterCategoryValuesForVoters(userId,voterIds);
			     if(votersPartyCastList != null && votersPartyCastList.size() >0)
			     {
			    	 for(Object[] voterDetails:votersPartyCastList){
			    	 VoterVO voterObj = voterMap.get((Long)voterDetails[0]);
			    	 if(voterObj != null){
			    		 List<VoterVO> categoriesList = voterObj.getCategoriesList();
			    		 if(categoriesList == null){
			    			 categoriesList = new ArrayList<VoterVO>();
			    			 voterObj.setCategoriesList(categoriesList);
			    		 }
			    		  category = new VoterVO();
			    		  categoriesList.add(category);
			    		  category.setCategoryValuesId((Long)voterDetails[1]);
			    		  category.setName(voterDetails[2]!=null?voterDetails[2].toString():"");
			    	 }
			      }
			    }
			 }
		} catch (Exception e) {
			LOG.error("error occured in the getCategoryWiseVoterData() method in VotersAnalysis" , e) ;
		}
		return voterData;
	}
	public String getCategoryNameByCategoryId(Long userVoterCategoryId)
	{
		try{
			return userVoterCategoryDAO.getCategoryNameByCategoryId(userVoterCategoryId);
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getCategoryNameByCategoryId() method,Exception - "+e);
			return null;
		}
	}
	
	public List<MandalInfoVO> getCensusDetailsInALocation(String locationType, Long locationValue, Long constituencyId)
	{
		List<MandalInfoVO> mandalInfoVOList = null;
		try{
			if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				mandalInfoVOList = delimitationConstituencyMandalService.getCensusInfoForConstituency(constituencyId);
			else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
			{
				if(locationValue.toString().substring(0, 1).equalsIgnoreCase("2"))
				  mandalInfoVOList = delimitationConstituencyMandalService.getCensusInfoForMandals(locationValue.toString().substring(1));
			}
			
			return mandalInfoVOList;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getCensusDetailsInALocation() method,Exception - "+e);
			return mandalInfoVOList;
		}
	}
	
	
	public List<MandalInfoVO> getCensusReportForSubLevels(String locationType,Long locationValue,Long constituencyId)
	{
		List<MandalInfoVO> mandalInfoVOList = null;
		String mandalIdsString = "";
		try{
			if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(locationValue ,IConstants.PRESENT_YEAR, null);
				if(mandalsList == null || mandalsList.size() == 0)
					return mandalInfoVOList;
				for(SelectOptionVO selectOptionVO : mandalsList)
				{
					if(selectOptionVO.getId().toString().substring(0, 1).equalsIgnoreCase(IConstants.RURAL_TYPE))
						mandalIdsString = mandalIdsString+selectOptionVO.getId().toString().substring(1)+",";
				}
				if(!mandalIdsString.isEmpty())
				{
				    mandalIdsString = mandalIdsString.substring(0,mandalIdsString.length()-1);
					mandalInfoVOList = delimitationConstituencyMandalService.getCensusInfoForMandals(mandalIdsString);
				}
			}
			
			return mandalInfoVOList;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getCensusReportForSubLevels() method,Exception - "+e);
			return mandalInfoVOList;
		}
	}
	
	public List<SelectOptionVO> getAllPAUsers()
	{
		List<SelectOptionVO> optionVOList = new ArrayList<SelectOptionVO>(0);
		try{
			
			List<Object[]> list = userRolesDAO.getAllUsersByRoleType(IConstants.PARTY_ANALYST_USER);
			if(list != null && list.size() > 0)
				for(Object[] params : list)
				{
					String name = "";
					if(params[1] != null)
						name += params[1].toString();
					if(params[2] != null)
						name += " "+params[2].toString();
				  optionVOList.add(new SelectOptionVO((Long)params[0],name));
				}
			
			return optionVOList;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getAllPAUsers() methos,Exception - "+e);
			return optionVOList;
		}
	}
	
	public SelectOptionVO getAllParentUsers(Long userId)
	{
		SelectOptionVO selectOptionVO = null;
		try{
			List<Object[]> list = userRolesDAO.getAllUsersByRoleType(IConstants.PARTY_ANALYST_USER);
			if(list != null && list.size() > 0)
			{
				selectOptionVO = new SelectOptionVO();
				List<SelectOptionVO> optionVOList = new ArrayList<SelectOptionVO>(0);
				List<SelectOptionVO> optionVOList1 = new ArrayList<SelectOptionVO>(0);
				optionVOList.add(new SelectOptionVO(0l,"select"));
				optionVOList1.add(new SelectOptionVO(0l,"select"));
				for(Object[] params : list)
				{
					if(!userId.equals((Long)params[0]))
					{
						String name = "";
						if(params[1] != null)
							name += params[1].toString();
						if(params[2] != null)
							name += " "+params[2].toString();
					  optionVOList.add(new SelectOptionVO((Long)params[0],name));
					  optionVOList1.add(new SelectOptionVO((Long)params[0],name));
					  
					}
				}
				selectOptionVO.setSelectOptionsList(optionVOList);
				selectOptionVO.setSelectOptionsList1(optionVOList1);
				
				selectOptionVO.setParentUserId(userDAO.get(userId).getParentUser() != null ? userDAO.get(userId).getParentUser().getUserId():0l);
				selectOptionVO.setMainAccountId(userDAO.get(userId).getMainAccountUser() != null ?userDAO.get(userId).getMainAccountUser().getUserId():0l);
			}
			
			return selectOptionVO;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getAllParentUsers() method,Exception - "+e);
			return selectOptionVO;
		}
	}
	
	public ResultStatus assignParentUser(Long selectedUserId,Long parentuserId,Long mainAccountId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			User user = userDAO.get(selectedUserId);
			if(parentuserId != null && parentuserId > 0)
				user.setParentUser(userDAO.get(parentuserId));
			else
				user.setParentUser(null);
			if(mainAccountId != null && mainAccountId > 0)
				user.setMainAccountUser(userDAO.get(mainAccountId));
			else 
				user.setMainAccountUser(null);
			userDAO.save(user);
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	/*public List<VotersDetailsVO> getCasteWiseUserVoterCategory(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId)
	{
		 Map<Long,VotersDetailsVO> category = new HashMap<Long, VotersDetailsVO>();
		 Map<Long, Map<Long,VotersDetailsVO>> categoryValues = new HashMap<Long, Map<Long,VotersDetailsVO>>();
		 List<Object[]> votersList = null;
		 List<VotersDetailsVO> list1 = null;
		 Map<String , VotersDetailsVO> map  = null;
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
			 if(!locationType.equalsIgnoreCase("hamlet"))
			 {
			// votersList = boothPublicationVoterDAO.getAgeWiseDetails(userId,attributeIds,locationType,locationId,constituencyId,publicationId,18l,25l);
				 votersList = boothPublicationVoterDAO.getCasteWiseDetails(userId,attributeIds,locationType,locationId,constituencyId,publicationId);
			
				 map = new HashMap<String , VotersDetailsVO>();
				 VotersDetailsVO votersDetailsVO1 = null;
				 
				 for(Object[] obj:votersList)
				 {	 
					 if(map.get(obj[0].toString()) != null){
						 
						  votersDetailsVO1 = map.get(obj[0].toString());		
						 votersDetailsVO1.setCasteId(obj[0].toString());
						 votersDetailsVO1.setCastName(obj[1].toString());
						 
						 if(obj[3].toString().equalsIgnoreCase("M"))
						   votersDetailsVO1.setTotalMaleVoters((Long)obj[2]);
						 else if(obj[3].toString().equalsIgnoreCase("F"))
							 votersDetailsVO1.setTotalFemaleVoters((Long)obj[2]);
						 
						 //votersDetailsVO1.setTotalVoters(votersDetailsVO1.getTotalMaleVoters()+votersDetailsVO1.getTotalFemaleVoters());
						 
					 }else{

						 
						  votersDetailsVO1 = new VotersDetailsVO();		
						 votersDetailsVO1.setCasteId(obj[0].toString());
						 votersDetailsVO1.setCastName(obj[1].toString());
						 
						 if(obj[3].toString().equalsIgnoreCase("M"))
						   votersDetailsVO1.setTotalMaleVoters((Long)obj[2]);
						 else if(obj[3].toString().equalsIgnoreCase("F"))
							 votersDetailsVO1.setTotalFemaleVoters((Long)obj[2]);
					 }
					 
					 votersDetailsVO1.setTotalVoters(votersDetailsVO1.getTotalMaleVoters()+votersDetailsVO1.getTotalFemaleVoters());						 

					 map.put(obj[0].toString(), votersDetailsVO1);
				 }
				 list1 = new ArrayList<VotersDetailsVO>(map.values());				 
				 
			 }
			 
			
			 else if(locationType.equalsIgnoreCase("hamlet"))
			 {
				 votersList = boothPublicationVoterDAO.getCasteWiseDetailsForHamlet(userId,attributeIds,locationType,locationId,constituencyId,publicationId);
				 
				 map = new HashMap<String , VotersDetailsVO>();
				 VotersDetailsVO votersDetailsVO1 = null;
				 
				 for(Object[] obj:votersList)
				 {	 
					 if(map.get(obj[1].toString()) != null){
						 
						  votersDetailsVO1 = map.get(obj[1].toString());		
						 votersDetailsVO1.setCasteId(obj[0].toString());
						 votersDetailsVO1.setCastName(obj[1].toString());
						 
						 if(obj[3].toString().equalsIgnoreCase("M"))
						   votersDetailsVO1.setTotalMaleVoters((Long)obj[2]);
						 else if(obj[3].toString().equalsIgnoreCase("F"))
							 votersDetailsVO1.setTotalFemaleVoters((Long)obj[2]);
						 
						 //votersDetailsVO1.setTotalVoters(votersDetailsVO1.getTotalMaleVoters()+votersDetailsVO1.getTotalFemaleVoters());
						 
					 }else{

						 
						  votersDetailsVO1 = new VotersDetailsVO();		
						 votersDetailsVO1.setCasteId(obj[0].toString());
						 votersDetailsVO1.setCastName(obj[1].toString());
						 
						 if(obj[3].toString().equalsIgnoreCase("M"))
						   votersDetailsVO1.setTotalMaleVoters((Long)obj[2]);
						 else if(obj[3].toString().equalsIgnoreCase("F"))
							 votersDetailsVO1.setTotalFemaleVoters((Long)obj[2]);
					 }
					 
					 votersDetailsVO1.setTotalVoters(votersDetailsVO1.getTotalMaleVoters()+votersDetailsVO1.getTotalFemaleVoters());						 

					 map.put(obj[1].toString(), votersDetailsVO1);
				 }
				 list1 = new ArrayList<VotersDetailsVO>(map.values());
				
			 } 
			 
			 int totalVoters = 0;
			 for(VotersDetailsVO vo:list1)
				 totalVoters+=vo.getTotalVoters();
			 
			 for(VotersDetailsVO vo:list1)				 
				 vo.setTotalVotersPercent(round((float)vo.getTotalVoters()/totalVoters*100,2));
			 
			 Collections.sort(list1,sortByNumber);			 
			 list1.get(0).setCategoryName(categoriesAndValues.get(0)[1].toString());
			
			 
			
		 }
		catch(Exception e)
		{
			LOG.error("error occured in the getAgeRangeByUserVoterCategory() method in VotersAnalysis" , e) ;
		}
		
		
		 return list1;
	}*/
	
	
	public List<VotersDetailsVO> getCasteWiseUserVoterCategory(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId)
	{
	  List<VotersDetailsVO> votersDetailsVOsList = null;
		try{
			List<Object[]> categoriesAndValues = userVoterCategoryValueDAO.getCatergoryAndValues(attributeIds,userId);
			if(locationType.equalsIgnoreCase("mandal") && locationId.toString().substring(0, 1).equalsIgnoreCase("2"))
				locationId = new Long(locationId.toString().substring(1));
			else if(locationType.equalsIgnoreCase("mandal") && locationId.toString().substring(0, 1).equalsIgnoreCase("1"))
			{
			   List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(locationId.toString().substring(1)));
			   locationId = (Long) list.get(0); 
			}
			
			List<Object[]> votersList = boothPublicationVoterDAO.getCasteWiseDetails(userId,attributeIds,locationType,locationId,constituencyId,publicationId);
			
			if(votersList != null && votersList.size() > 0)
			{
			  votersDetailsVOsList = new ArrayList<VotersDetailsVO>(0);
			  VotersDetailsVO votersDetailsVO = null;
			  
			  for(Object[] params : votersList)
			  {
				 votersDetailsVO = checkVotersDetailsVOIsExists((Long)params[0],(Long)params[5],votersDetailsVOsList);
			     if(votersDetailsVO == null)
			     {
			       votersDetailsVO = new VotersDetailsVO();
			       votersDetailsVO.setCastId((Long)params[0]);
			       votersDetailsVO.setUserVoterCategoryValueId((Long)params[5]);
			       votersDetailsVO.setCastName(params[1] != null ? params[1].toString():" ");
			       votersDetailsVO.setName(params[6] != null ?params[6].toString() : "");
			       votersDetailsVOsList.add(votersDetailsVO);
			     }
			     if(params[3].toString().equalsIgnoreCase(IConstants.MALE))
			      votersDetailsVO.setTotalMaleVoters((Long)params[2]);
			     else if(params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
			      votersDetailsVO.setTotalFemaleVoters((Long)params[2]);
			     
			     votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalMaleVoters()+votersDetailsVO.getTotalFemaleVoters());
			  }
			  
			  int totalVoters = 0;
			  for(VotersDetailsVO vo:votersDetailsVOsList)
				totalVoters += vo.getTotalVoters();
			  
			  for(VotersDetailsVO detailsVO : votersDetailsVOsList)
				  detailsVO.setTotalVotersPercent(round((float)detailsVO.getTotalVoters()/totalVoters*100,2)); 
			  
			  votersDetailsVOsList.get(0).setCategoryName(categoriesAndValues.get(0)[1].toString());
			}
			return votersDetailsVOsList;
		 }catch(Exception e){
			LOG.error("error occured in the getAgeRangeByUserVoterCategory() method in VotersAnalysis" , e) ;
			return votersDetailsVOsList;
		}
	}
	
	public VotersDetailsVO checkVotersDetailsVOIsExists(Long casteId,Long userVoterCategoryValueId,List<VotersDetailsVO> list)
	{
		try{
		if(list == null || list.size() == 0)
		 return null;
		 
		 for(VotersDetailsVO detailsVO : list)
		   if(detailsVO.getCastId().equals(casteId) && detailsVO.getUserVoterCategoryValueId().equals(userVoterCategoryValueId)) 
			 return detailsVO;
		 
		 return null;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in checkVotersDetailsVOIsExists() method, Exception - "+e);
			return null;
		}
	}
	
	public static float round(float value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (float) tmp / factor;
	}
	
	
	 
   public static Comparator<VotersDetailsVO> sortByNumber = new Comparator<VotersDetailsVO>()
		    {
		   
		        public int compare(VotersDetailsVO votersDetailsVO1, VotersDetailsVO votersDetailsVO2)
		        {
		            if (votersDetailsVO1.getTotalVoters() > votersDetailsVO2.getTotalVoters()) return -1;
		            if (votersDetailsVO1.getTotalVoters() < votersDetailsVO2.getTotalVoters()) return 1;
		            return 0;
		        }
	 };
	 /** This method is used to save groupName and details in custom Voter Group */
	 public ResultStatus saveCustomVoterGroup(Long userId,Long constituencyId,Long locationValue,String name)
	 {
		 
		ResultStatus resultStatus = new ResultStatus();
		CustomVoterGroup customVoterGroup = null;
		Long localbody = 0l;
		Long mandalId = 0l;
		List<Object[]> list = null;
		Constituency constituency =constituencyDAO.get(constituencyId);
		String areaType = constituency.getAreaType();
		if(locationValue.toString().substring(0,1).trim().equalsIgnoreCase("1"))
		{
		localbody = assemblyLocalElectionBodyDAO.get(new Long(locationValue.toString().substring(1))).getLocalElectionBody().getLocalElectionBodyId();
		list = customVoterGroupDAO.checkDuplicateGroupName(userId,localbody,name);
		}
		if(locationValue.toString().substring(0,1).trim().equalsIgnoreCase("2"))
		{
		mandalId = new Long(locationValue.toString().substring(1));
		list = customVoterGroupDAO.checkDuplicateGroupName(userId,mandalId,name);
		}
		
		if(list != null && list.size() > 0)
		{
			resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			return resultStatus;
		}
		try{
			customVoterGroup = new CustomVoterGroup();
			customVoterGroup.setName(name);
			customVoterGroup.setUser(userDAO.get(userId));
			if(locationValue.toString().substring(0,1).trim().equalsIgnoreCase("1"))
			customVoterGroup.setLocationValue(localbody);
			else
			customVoterGroup.setLocationValue(mandalId);
			
			customVoterGroup.setConstituency(constituencyDAO.get(constituencyId));
			if(areaType.equalsIgnoreCase("RURAL"))
			customVoterGroup.setAreaType(areaTypeDAO.get(2l));
			else if(areaType.equalsIgnoreCase("URBAN"))
			customVoterGroup.setAreaType(areaTypeDAO.get(1l));
			else
			customVoterGroup.setAreaType(areaTypeDAO.get(3l));	
			customVoterGroupDAO.save(customVoterGroup);	
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}	
		
		catch(Exception e)
		{
			LOG.error("error occured in the saveCustomVoterGroup() method " , e) ;	
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		return resultStatus;
		
	 }

}
