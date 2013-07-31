package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.YouthLeaderSelectionVO;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ISuggestiveRangeDAO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.SuggestiveRange;
import java.util.Map;
import java.util.HashMap;

public class SuggestiveModelService implements ISuggestiveModelService {
	
	private static final Logger LOG = Logger.getLogger(SuggestiveModelService.class);
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IElectionDAO electionDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO ;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private INominationDAO nominationDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private ISuggestiveRangeDAO suggestiveRangeDAO;
	private IPanchayatDAO panchayatDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IVoterCastBasicInfoDAO voterCastBasicInfoDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPublicationDateDAO publicationDateDAO;
	private ITehsilDAO tehsilDAO;
	private IVoterModificationInfoDAO voterModificationInfoDAO;
	
	
	public IVoterModificationInfoDAO getVoterModificationInfoDAO() {
		return voterModificationInfoDAO;
	}

	public void setVoterModificationInfoDAO(
			IVoterModificationInfoDAO voterModificationInfoDAO) {
		this.voterModificationInfoDAO = voterModificationInfoDAO;
	}


	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}
	public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
		return userConstituencyAccessInfoDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}
	
	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}	

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}
	
	public ISuggestiveRangeDAO getSuggestiveRangeDAO() {
		return suggestiveRangeDAO;
	}

	public void setSuggestiveRangeDAO(ISuggestiveRangeDAO suggestiveRangeDAO) {
		this.suggestiveRangeDAO = suggestiveRangeDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	
     public IVoterCastBasicInfoDAO getVoterCastBasicInfoDAO() {
		return voterCastBasicInfoDAO;
	}

	public void setVoterCastBasicInfoDAO(
			IVoterCastBasicInfoDAO voterCastBasicInfoDAO) {
		this.voterCastBasicInfoDAO = voterCastBasicInfoDAO;
	}
	
	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}
	
	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}
	
	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}

	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}
	
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public void getVotersGroupDetails(List<SelectOptionVO> groupVos,Long constituencyId,Long locationId,String type){
		 Long publicationId = 8l;
		 List<Long> publicationIds = new ArrayList<Long>();
		 publicationIds.add(publicationId);
    	 for(SelectOptionVO group : groupVos){
			if("panchayat".equalsIgnoreCase(type)){
				List<Long> boothIDs = boothDAO.getBoothIdsByLocalValuesList("panchayat", locationId, constituencyId, publicationIds);
				if(boothIDs != null && boothIDs.size() > 0){
					List<Object[]> votersCount = boothPublicationVoterDAO.getVotersCountAgeWise(group.getId(), group.getPopulateId(), boothIDs);
				}
				
			}
		}
	}
	
	public OptionVO getPartyPerformantForSelectedConstituency(Long constituencyId,Long electionId,Long partyId)
	{
		 OptionVO optionVO = null;
		 try {
			 LOG.debug("Enterd Into getPartyPerformantForSelectedConstituency() method in SuggestiveModelService Class ");
			 Long electionYear = Long.valueOf(electionDAO.get(electionId).getElectionYear());
			 String constituencyType = constituencyDAO.get(constituencyId).getAreaType();
			 List<SelectOptionVO> mandals = null;
			 List<SelectOptionVO> wards = null;
			// List<SelectOptionVO> booths = null;
			 if(constituencyType.equalsIgnoreCase(IConstants.RURAL) || constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
			 {
				 List<Object[]> tehsilsList = delimitationConstituencyMandalDAO.getMandalIdsByConstituencyId(constituencyId,electionYear);
				 if(tehsilsList != null && tehsilsList.size() > 0)
				 {
					mandals = new ArrayList<SelectOptionVO>();
					mandals = processSelectOptionVO(tehsilsList);
				 }
			 }
			 else if(constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
			 {
				 Long localBodyId = assemblyLocalElectionBodyDAO.getLocalBodyIdBasedOnConstituencyId(constituencyId);
				 List<Object[]> tehsilsList =  localElectionBodyDAO.getTehsilsByLocalBody(localBodyId);
				 if(tehsilsList != null && tehsilsList.size() > 0)
				 {
					 mandals = new ArrayList<SelectOptionVO>();
					 mandals = processSelectOptionVO(tehsilsList);
				 }
				 
			 }
			 else if(constituencyType.equalsIgnoreCase(IConstants.URBAN))
			 {
				 Long localBodyId = assemblyLocalElectionBodyDAO.getLocalBodyIdBasedOnConstituencyId(constituencyId);
				 List<Object[]> wardsList = constituencyDAO.getWardsInALocalBody(localBodyId);
				 if(wardsList != null && wardsList.size() > 0)
				 {
					 wards = new ArrayList<SelectOptionVO>();
					 wards =  processSelectOptionVO(wardsList);
				 }
			 }
			 
			 if(constituencyType.equalsIgnoreCase(IConstants.RURAL) || constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
			 {
				 List<Long> tehsilIds = new ArrayList<Long>();
				 if(mandals != null && mandals.size() > 0)
				 {
					 for (SelectOptionVO selectOptionVO : mandals) {
						 Long tehsilId = selectOptionVO.getId();
						 tehsilIds.add(tehsilId);
					 }
				 }
				 if(tehsilIds != null && tehsilIds.size() > 0)
				 {
					List<Long> boothIds = new ArrayList<Long>();
					for (Long tehsilId : tehsilIds) {
						List<Long> boothsList =  boothDAO.getboothsByTehsilId(tehsilId);
						if(boothsList != null && boothsList.size() > 0)
						{
						  for (Long boothId : boothsList) {
						  boothIds.add(boothId);
						  }
						}
						List<Object[]> partyWiseVotesList =  candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(constituencyId,electionId,boothIds);
						 if(partyWiseVotesList != null && partyWiseVotesList.size() > 0)
						 {
							 optionVO =  processOptionVO(partyWiseVotesList,partyId,tehsilId); 
						 }
					} 
				 }
			 }
			 else if(constituencyType.equalsIgnoreCase(IConstants.URBAN))
			 {
				 List<Long> wardIds = new ArrayList<Long>();
				 for (SelectOptionVO selectOptionVO : wards) {
					Long wardId = selectOptionVO.getId();
					wardIds.add(wardId);
				}
				if(wardIds != null && wardIds.size() > 0)
				{
					
					for (Long wardId : wardIds) {
						List<Long> boothIds = new ArrayList<Long>();
						List<Long> boothsList =  boothDAO.getboothsByWardId(wardId);
						if(boothsList != null && boothsList.size() > 0)
						{
						  for (Long boothId : boothsList) {
						  boothIds.add(boothId);
						  }
						}
						List<Object[]> partyWiseVotesList =  candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(constituencyId,electionId,boothIds);
						 if(partyWiseVotesList != null && partyWiseVotesList.size() > 0)
						 {
							 optionVO =  processOptionVO(partyWiseVotesList,partyId,wardId);
						 }
					}
				  }
				 }
				
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyPerformantForSelectedConstituency() method in SuggestiveModelService Class ", e);
		}
		return optionVO;
	}
	
	public OptionVO getPartyPerformantForSelectedWard(Long constituencyId,Long wardId,Long electionId,Long partyId)
	{
		OptionVO optionVO = null;
		try {
			LOG.debug("Enterd Into getPartyPerformantForSelectedWard() method in SuggestiveModelService Class ");
			List<Long> boothIds = new ArrayList<Long>();
			List<Long> boothsList =  boothDAO.getboothsByWardId(wardId);
			if(boothsList != null && boothsList.size() > 0)
			{
			  for (Long boothId : boothsList)
			  {
			     boothIds.add(boothId);
			  }
			}
		   List<Object[]> partyWiseVotesList =  candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(constituencyId,electionId,boothIds);
		   if(partyWiseVotesList != null && partyWiseVotesList.size() > 0)
			{
			 optionVO =  processOptionVO(partyWiseVotesList,partyId,wardId);
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyPerformantForSelectedWard() method in SuggestiveModelService Class ", e);
		}	
		return optionVO;
	}
	public List<SelectOptionVO> processSelectOptionVO(List<Object[]> values)
	{
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		for (Object[] parms : values) {
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId((Long)parms[0]);
			selectOptionVO.setName(parms[1].toString());
			returnList.add(selectOptionVO);
			}
		return returnList;
	}
	
	public OptionVO processOptionVO(List<Object[]> values,Long partyId,Long id)
	{
		OptionVO optionVO = new OptionVO();
		try {
			LOG.debug("Enterd Into processOptionVO() method in SuggestiveModelService Class ");
			Long totalVoters = 0l;
			Long SelectedpartyVoters = 0l;
			Long otherPartyVoters = 0l;
			Long goodBoothCount = 0L;
		    Long veryGoodBoothCount = 0L;
		    Long badBoothCount = 0L;
		    Long veryBadBoothCount =0L;
		    Long averageBoothCount = 0L;
		      
		    List<Long> goodBoothIdsList = new ArrayList<Long>(0);
		  	List<Long> veryGoodBoothIdsList = new ArrayList<Long>(0);
		  	List<Long> badBoothIdsList = new ArrayList<Long>(0);
		    List<Long> veryBadBoothIdsList = new ArrayList<Long>(0);
		  	List<Long> averageBoothIdsList = new ArrayList<Long>(0);
			boolean tempVar = false;
			for (Object[] parms : values) {
				if(partyId.equals((Long)parms[1]))
				{
					SelectedpartyVoters = (Long)parms[0];
				}
				totalVoters = totalVoters + (Long)parms[0];
			}
			for (Object[] parms : values) {
				if(!partyId.equals((Long)parms[1]))
				{
					otherPartyVoters = (Long)parms[0];
					break;
				}
			}
			if(values.get(0)[1].equals(partyId))
			{
				tempVar = true;
			}
			double selectedPartyTotalPercent =  new BigDecimal((SelectedpartyVoters*100/totalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double comparePartyTotalPercent =  new BigDecimal((otherPartyVoters*100/totalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double difference = 0.00;
			if(tempVar)
			{
				difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			else
			{
				 difference = new BigDecimal(comparePartyTotalPercent - selectedPartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			if(tempVar)
	    	{
	    	   if(difference <= IConstants.VERY_GOOD)
	    	   {
	    	    veryGoodBoothCount +=1;
	    	    veryGoodBoothIdsList.add(id);
	    	   }
	    	 
	    	   else if(difference <= IConstants.GOOD)
	    	   {
	    		  goodBoothCount +=1;
	    		  goodBoothIdsList.add(id);
	    	   }
	    	   else if(difference <= IConstants.AVERAGE)
	    	   {
		         averageBoothCount += 1;
		         averageBoothIdsList.add(id);
	    	   }
	    	}
	    	else
	    	{
	    	   
	    	   if(difference <= IConstants.BAD)
	    	   {
	    	    badBoothCount += 1;
	    	    badBoothIdsList.add(id);
	    	   }
	    	 
	    	   else
	    	   {
	    	    veryBadBoothCount +=1;
	    	    veryBadBoothIdsList.add(id);
	    	   }
	    	}
		     optionVO.setGoodBoothCount(goodBoothCount);
		   	 optionVO.setVeryGoodBoothCount(veryGoodBoothCount);
		   	 optionVO.setVeryBadBoothCount(veryBadBoothCount);
		   	 optionVO.setBadBoothCount(badBoothCount);
		   	 optionVO.setAverageBoothCount(averageBoothCount); 
		   	 optionVO.setVeryGoodBoothIdsList(veryGoodBoothIdsList);
		   	 optionVO.setVeryBadBoothIdsList(veryBadBoothIdsList);
		   	 optionVO.setGoodBoothIdsList(goodBoothIdsList);
		   	 optionVO.setBadBoothIdsList(badBoothIdsList);
		   	 optionVO.setAverageBoothIdsList(averageBoothIdsList);
		} catch (Exception e) {
			LOG.error("Exception raised in processOptionVO() method in SuggestiveModelService Class ", e);
		}
		 return optionVO;
	}
	public void getPartyPerformanceForlocation(Map<Long,Map<Long,Long>> resultMap,OptionVO optionVO, Long selectedpartyId)
	{
		try{
			
		 for(Long id:resultMap.keySet())
		 {
			boolean tempVar = false;
			Map<Long,Long> partyMap = resultMap.get(id);
			Long totalVotes = 0L;
				 
			for(Long partysId:partyMap.keySet())
			  totalVotes += partyMap.get(partysId); 
				 
			Long selectedPartyTotal = partyMap.get(selectedpartyId);
			Long comparePartyTotal = 0L;
				 
			  for(Long partysId:partyMap.keySet())
			  {
			    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
				 comparePartyTotal = partyMap.get(partysId);
				  
			  }
		   if(selectedPartyTotal > comparePartyTotal)
			tempVar = true;
			     
		  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	 
	    	 double difference = 0.00;
	    	 
	    	 if(tempVar)
	    	  difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	 else
	    	  difference = new BigDecimal(comparePartyTotalPercent - selectedPartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	 
	    	if(tempVar)
	    	{
	    	   if(difference >= IConstants.VERY_GOOD)
	    	   {
	    		optionVO.setVeryGoodBoothCount(optionVO.getVeryGoodBoothCount()+1);
	    	    optionVO.getVeryGoodBoothIdsList().add(id);
	    	   }
	    	 
	    	   else if(difference >= IConstants.GOOD)
	    	   {
	    		  optionVO.setGoodBoothCount(optionVO.getGoodBoothCount()+1);
	    		  optionVO.getGoodBoothIdsList().add(id);
	    	   }
	    	   else if(difference >= IConstants.AVERAGE)
	    	   {
		         optionVO.setAverageBoothCount(optionVO.getAverageBoothCount()+1);
		         optionVO.getAverageBoothIdsList().add(id);
	    	   }
	    	}
	    	else
	    	{
	    	  if(difference <= IConstants.AVERAGE)
		      {
			       optionVO.setAverageBoothCount(optionVO.getAverageBoothCount()+1);
			       optionVO.getAverageBoothIdsList().add(id);
		      }
	    		else if(difference <= IConstants.BAD)
	    	   {
	    	    optionVO.setBadBoothCount(optionVO.getBadBoothCount()+1);
	    	    optionVO.getBadBoothIdsList().add(id);
	    	   }
	    	 
	    	   else
	    	   {
	    	    optionVO.setVeryBadBoothCount(optionVO.getVeryBadBoothCount()+1);
	    	    optionVO.getVeryBadBoothIdsList().add(id);
	    	   }
	    	}
		}
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
		}
	}
	
	
	
	//All ELection Years
	
	public List<PartyPositionVO> getPartyPerformenceReport(Long constituencyId,Long partyId,Long locationId,String locationType,List<Long> electionIds,String tempVar)
	{
		List<PartyPositionVO> resultList = null;
		try{
		List<Long> constituencyIdsList = new ArrayList<Long>(0);
		constituencyIdsList.add(constituencyId);
		List<Long> assemblyEleIdsList = new ArrayList<Long>(0);
		List<Object[]> electionList = null;
		String tempLocationName = "";
		
		if(electionIds != null && electionIds.size()> 0)
		{
			electionList = new ArrayList<Object[]>();
			for (Long eleId : electionIds) {
				Object[] eleIds = {eleId};
				 electionList.add(eleIds);
			}
		}
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
		{
		  if(locationId.toString().substring(0,1).equalsIgnoreCase("2"))
		  {
		   locationId = new Long(locationId.toString().substring(1));
		   if(tempVar != null && tempVar.equalsIgnoreCase("all"))
		    electionList = hamletBoothElectionDAO.findAllElectionsHappendInAMandal(locationId,constituencyIdsList); 
		  
		   locationType = IConstants.MANDAL;
		   tempLocationName = IConstants.PANCHAYAT;
		  }
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
		  if(tempVar != null && tempVar.equalsIgnoreCase("all"))
			electionList = hamletBoothElectionDAO.findAllElectionsHappendInAPanchayat(locationId);
		  tempLocationName = IConstants.BOOTH;
		}
		
		if(electionList != null && electionList.size() > 0)
		{
		   for(Object[] params:electionList)
		   {
			   String electionType = electionDAO.get((Long)params[0]).getElectionScope().getElectionType().getElectionType();
			  if(electionType.equalsIgnoreCase("Assembly"))
				assemblyEleIdsList.add((Long)params[0]);
		   } 	
		}
		
		
		
		if(assemblyEleIdsList != null && assemblyEleIdsList.size() > 0)
		{
			resultList = new ArrayList<PartyPositionVO>(0);
			  List<SuggestiveRange> suggestiveRangeList = suggestiveRangeDAO.getSuggestiveRangeList();
				  
			  PartyPositionVO partyPositionVO = null;
			  for(Long eleId :assemblyEleIdsList)
			  {
				Election election = electionDAO.get(eleId);
				partyPositionVO = new PartyPositionVO();
				List<PartyPositionVO> rangeList = new ArrayList<PartyPositionVO>();
				
				PartyPositionVO range = null;
				for(SuggestiveRange suggestiveRange:suggestiveRangeList)
				  {
					range = new PartyPositionVO();
					range.setName(suggestiveRange.getName());
					range.setMinValue(suggestiveRange.getMinValue());
					range.setMaxValue(suggestiveRange.getMaxValue());
					rangeList.add(range);
				  }
				partyPositionVO.setPartyPositionVOList(rangeList);
				partyPositionVO.setName(election.getElectionYear() != null?election.getElectionYear():" ");
				partyPositionVO.setId(eleId);
				
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
				 getMandalWisePartyPerformanceReport(constituencyId, locationId, eleId, partyPositionVO, partyId);
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				 getPanchayatWisePartyPerformance(constituencyId, locationId, eleId, partyPositionVO, partyId);
				
				resultList.add(partyPositionVO);
			  }
		}
		
		if(resultList != null && resultList.size() == 2){
		  List<PartyPositionVO> suggestedLocations = getSuggestiveLocationsForAParty(resultList);
		  resultList.get(0).setSuggestedLocations(suggestedLocations);
		}	

		//For PollingPercentage Panchayats
		if(resultList != null && resultList.size() > 0){
			getPollingPercentageForALocation(resultList.get(0),tempLocationName,constituencyId);
			List<PartyPositionVO>  panchayatVos = getMoreVotersAddedLocDetailsWherePartyIsPoor(resultList.get(0).getPartyPositionVOList());
			resultList.get(0).setAddedVoterDetails(panchayatVos);
		}
		 return resultList;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in getPartyPerformenceReportForAllElections() method, Exception-"+e);
		 return resultList;
		}
	}
	
	
  public void getPollingPercentageForALocation(PartyPositionVO partyPositionVO,String locationType,Long constituencyId)
  {
	try{
	List<Long> weakLocationIdsList = new ArrayList<Long>(0);//<electionId,PanchayatIdsList>
	List<Long> strongLocationIdsList = new ArrayList<Long>(0);//<electionId,panchayatIdsList
	List<Long> totalLocationIdsList = new ArrayList<Long>(0); 
	
	   List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
	   if(partyPositionVOList != null && partyPositionVOList.size() > 0)
	   {
		   for(PartyPositionVO positionVO :partyPositionVOList)
		   {
			  if(positionVO.getMinValue() < 0)
			  {
			    if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
			    {
				   for(PartyPositionVO positionVO2 :positionVO.getPartyPositionVOList())
				    if(!weakLocationIdsList.contains(positionVO2.getId()))
				     weakLocationIdsList.add(positionVO2.getId());
			     }
			   }
			   else if(positionVO.getMinValue() > 0)
			   {
			     if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
				 {
					for(PartyPositionVO positionVO2 :positionVO.getPartyPositionVOList())
					 if(!strongLocationIdsList.contains(positionVO2.getId()))
					  strongLocationIdsList.add(positionVO2.getId());
				  }
			   }
			  
			  if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
			  {
				for(PartyPositionVO positionVO2 :positionVO.getPartyPositionVOList())
				 if(!totalLocationIdsList.contains(positionVO2.getId()))
					 totalLocationIdsList.add(positionVO2.getId());
			  }
			  
			 }
		   }
	   if(strongLocationIdsList != null && strongLocationIdsList.size() > 0)
		 getPollingPercentage(locationType,constituencyId,strongLocationIdsList,partyPositionVO.getId(),partyPositionVO,"strongLocations",totalLocationIdsList);
	   
	   if(weakLocationIdsList != null && weakLocationIdsList.size() > 0)
		 getPollingPercentage(locationType,constituencyId,weakLocationIdsList,partyPositionVO.getId(),partyPositionVO,"weakLocations",totalLocationIdsList);  
	   
	 
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in getPollingPercentageForALocation() method, Exception - "+e);
			
		}
	}
  
  
  
  
  public void getPollingPercentage(String locationType,Long constituencyId,List<Long> locationIdsList,Long electionId,PartyPositionVO partyPositionVO,String tempVar,List<Long> totalLocationIdsList)
  {
	try{
		
		Map<Long,Double> resultMap = new HashMap<Long, Double>(0);//locationId,PollingPercentage
		Map<Long,Double> partyPercentage = new HashMap<Long, Double>(0);//locationId,selectedPartyPercentage
		Map<Long,String> locationNameMap = new HashMap<Long, String>(0);//locationId,locationName
		
		Map<Long,List<Long>> locationIdsMap = new HashMap<Long, List<Long>>(0);//Map<PanchayatId,List<BoothIds>>
		Map<Long,Long> totalVotesMap = new HashMap<Long, Long>(0);//Map<panchayatId,totalvotes
		Map<Long,Long> validvotesMap = new HashMap<Long, Long>(0);//Map<panchayatId,validvotes
		Map<Long,PartyPositionVO> partyPositionMap = new HashMap<Long, PartyPositionVO>(0);
		if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
		  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(locationIdsList, electionId);
		  if(list != null && list.size() > 0)
		  {
			for(Object[] params:list)
			{
				 List<Long> boothList = locationIdsMap.get((Long)params[0]);
				 if(boothList == null)
				 {
					 boothList = new ArrayList<Long>(0);
					 locationIdsMap.put((Long)params[0], boothList);
				 }
				  if(!boothList.contains((Long)params[1]))
					  boothList.add((Long)params[1]);
			 }
		  }
		 }
		
		
		if(locationType.equalsIgnoreCase(IConstants.BOOTH) && locationIdsList != null && locationIdsList.size() > 0)
		{
		   for(Long boothId : locationIdsList)
		   {
			   List<Long> boothIds = new ArrayList<Long>(0);
			   boothIds.add(boothId);
			   locationIdsMap.put(boothId, boothIds);
		   }
		}
		
		if(locationIdsMap != null && locationIdsMap.size() > 0)
		{
		  for(Long locationId : locationIdsMap.keySet())
		  {
			 List<Long> boothIdList = locationIdsMap.get(locationId);
			 Long totalVotes = boothDAO.getTotalVotesForBooth(boothIdList, constituencyId);
			 totalVotesMap.put(locationId, totalVotes);
		  }
		}
		
		if(partyPositionVO.getPartyPositionVOList() != null && partyPositionVO.getPartyPositionVOList().size() > 0)
		{
		  for(PartyPositionVO positionVO:partyPositionVO.getPartyPositionVOList())
		  {
			  if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
			  {
			   for(PartyPositionVO locationVO:positionVO.getPartyPositionVOList())
			   {
			     partyPercentage.put(locationVO.getId(), locationVO.getPartyPercentage());
			     locationNameMap.put(locationVO.getId(), locationVO.getName());
			     validvotesMap.put(locationVO.getId(), locationVO.getTotalValidVotes());
			     
			     
			     PartyPositionVO positionVO2 = partyPositionMap.get(locationVO.getId());
			     if(positionVO2 == null)
			     {
			    	 positionVO2 = new PartyPositionVO();
			    	 positionVO2.setId(locationVO.getId());
			    	 positionVO2.setName(locationVO.getName());
			    	 positionVO2.setMinValue(positionVO.getMinValue());
			    	 positionVO2.setMaxValue(positionVO.getMaxValue());
			    	 positionVO2.setPartyPercentage(locationVO.getPartyPercentage());
			    	 positionVO2.setTotalValidVotes(locationVO.getTotalValidVotes());
			    	 positionVO2.setTempVar(positionVO.getName());
			    	 partyPositionMap.put(locationVO.getId(), positionVO2);
			    	 
			     }
			     
			   }
			  }
		  }
		}
		  		  
		if(totalVotesMap != null && totalVotesMap.size() > 0)
		{
		  for(Long id : totalVotesMap.keySet())
		  {
			double difference =  new BigDecimal((validvotesMap.get(id)*100/totalVotesMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if(tempVar != null && tempVar.equalsIgnoreCase("strongLocations")) 
			{
			 if(difference < IConstants.LOW_VOTING_PERCENTAGE_IN_STRONG_LOCATIONS) 
			   resultMap.put(id, difference);
			}
			else{
			 if(difference > IConstants.HIGH_VOTING_PERCENTAGE_IN_WEEK_LOCATIONS) 
				resultMap.put(id, difference);	
			}
		  }
		}
	if(resultMap != null && resultMap.size() > 0)
		{
			List<PartyPositionVO> pollingPercentageVOList = new ArrayList<PartyPositionVO>(0);
			
			for(Long locationId:resultMap.keySet())
			{
				PartyPositionVO resultVO = partyPositionMap.get(locationId);
				
				PartyPositionVO positionVO = checkPartyPositionVOExist(resultVO.getMinValue(), resultVO.getMaxValue(), pollingPercentageVOList);
				if(positionVO == null)
				{
					positionVO = new PartyPositionVO();
					positionVO.setName(resultVO.getTempVar());
					positionVO.setMinValue(resultVO.getMinValue());
					positionVO.setMaxValue(resultVO.getMaxValue());
					pollingPercentageVOList.add(positionVO);
				}
				List<PartyPositionVO> partyPositionVOList = positionVO.getPartyPositionVOList();
				if(partyPositionVOList == null)
					partyPositionVOList = new ArrayList<PartyPositionVO>(0);
				
				PartyPositionVO locationVO = checkPartyPositionVOExist(locationId, partyPositionVOList);
				if(locationVO == null)
				{
					locationVO = new PartyPositionVO();
					locationVO.setId(locationId);
					locationVO.setName(resultVO.getName());
					locationVO.setPartyPercentage(resultVO.getPartyPercentage());
					locationVO.setPollingPercentage(resultMap.get(locationId));
					partyPositionVOList.add(locationVO);
				}
				
				positionVO.setPartyPositionVOList(partyPositionVOList);
				
			}
			if(tempVar != null && tempVar.equalsIgnoreCase("strongLocations"))
			 partyPositionVO.setStrongPollingPercentVOList(pollingPercentageVOList);
			else
				partyPositionVO.setWeakPollingPercentVOList(pollingPercentageVOList);
			  
		  }
		  
	}catch (Exception e) {
	 e.printStackTrace();
	 LOG.error(" Exception Occured in getPollingPercentage method, Exception - "+e);
	}
  }
  
  
  public PartyPositionVO checkPartyPositionVOExist(Double minValue,Double maxValue,List<PartyPositionVO> list)
  {
	 try{
		if(list == null || list.size() == 0)
		 return null;
		for(PartyPositionVO partyPositionVO : list)
		 if(partyPositionVO.getMinValue().equals(minValue) && partyPositionVO.getMaxValue().equals(maxValue))
		  return partyPositionVO;
		 
	   return null;
	 }catch (Exception e) {
      e.printStackTrace();
      LOG.error(" Exception Occured in checkPartyPositionVOExist() method, Exception - "+e);
      return null;
	 }
  }
	
	public void getPanchayatWisePartyPerformance(Long constituencyId,Long panchayatId, Long electionId,PartyPositionVO partyPositionVO, Long partyId)
	{
		try{
			Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<boothId,Map<partyId,votesEarned>>
			List<Long> boothIdsList = hamletBoothElectionDAO.getBoothIdsByPanchayatId(panchayatId, electionId);
				if(boothIdsList != null && boothIdsList.size() > 0)
				{
				  List<Object[]> list = candidateBoothResultDAO.getVotesEarnedByBoothIdsList(constituencyId, electionId, boothIdsList);
				  if(list != null && list.size() > 0)
				  {
					  Map<Long,Long> partyMap = null;
					  for(Object[] params:list)
					  {
						  partyMap = resultMap.get((Long)params[0]);
						  if(partyMap == null)
						  {
							  partyMap = new HashMap<Long, Long>(0);
							  resultMap.put((Long)params[0], partyMap);
						  }
						 Long votesEarned = partyMap.get((Long)params[1]);
						 if(votesEarned == null)
						  partyMap.put((Long)params[1], (Long)params[2]);
						 else
						  partyMap.put((Long)params[1], (Long)params[2]+votesEarned);
					  }
				  }
				}
				
				if(resultMap != null && resultMap.size() > 0)
					getPartyPerformance(resultMap,partyPositionVO, partyId,"booth");	
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("ExceptionOccured in getPanchayatWisePartyPerformance() method, Exception - "+e);
		}
	}
	
	
	public void getMandalWisePartyPerformanceReport(Long constituencyId,Long locationId,Long electionId,PartyPositionVO partyPositionVO,Long partyId)
	{
		try{
		Map<Long,List<Long>> boothIds = new HashMap<Long, List<Long>>();//Map<boothId,List<panchayatId>>
		Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<panchayatId,Map<partyId,totalvoters>>
		
		
		List<Long> panchaytIdsList = hamletBoothElectionDAO.getPanchayatIdsByTehsilIdAndElectionId(locationId, electionId);
		
		if(panchaytIdsList != null && panchaytIdsList.size() > 0)
		{
		  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchaytIdsList, electionId); 
		  if(list != null && list.size() > 0)
		  {
			  for(Object[] params:list)
			  {
				  List<Long> panchayatIds = boothIds.get((Long)params[1]);
				  if(panchayatIds == null)
				  {
				   panchayatIds = new ArrayList<Long>(0);
				   boothIds.put((Long)params[1], panchayatIds);
				  }
				  if(!panchayatIds.contains((Long)params[0]))
				   panchayatIds.add((Long)params[0]);
			  }
			  
			  List<Object[]> resultList = candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(boothIds.keySet(), electionId);
			  if(resultList != null && resultList.size() > 0)
			  {
				 for(Object[] params:resultList)
				 {
				   List<Long> panchayatIdsList = boothIds.get((Long)params[0]);
				   if(panchayatIdsList != null && panchayatIdsList.size() > 0)
				   {
					 Map<Long,Long> partyMap = null;//Map<PartyId,totalvotes>
					 for(Long panchayatId :panchayatIdsList)
					 {
						 partyMap = resultMap.get(panchayatId);
						 if(partyMap == null)
						 {
							 partyMap = new HashMap<Long, Long>(0);
							 resultMap.put(panchayatId, partyMap);
						 }
						 Long votesEarned = partyMap.get((Long)params[1]);
						 if(votesEarned == null)
						  partyMap.put((Long)params[1],(Long)params[2]);
						 else
						  partyMap.put((Long)params[1], votesEarned+(Long)params[2]);
					 }
				   }
				   
				 }
			  }
			  
		  }
		}
		if(resultMap != null && resultMap.size() > 0)
			getPartyPerformance(resultMap,partyPositionVO, partyId,"panchayat"); 
		
		
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in getMandalWisePartyPerformanceReport() method, Exception - "+e);
		  }
	}
	
	public void getPartyPerformance(Map<Long,Map<Long,Long>> resultMap,PartyPositionVO partyPositionVO, Long selectedpartyId,String locationType)
	{
		try{
		
		 for(Long id:resultMap.keySet())
		 {
			Map<Long,Long> partyMap = resultMap.get(id);
			Long totalVotes = 0L;
				 
			for(Long partysId:partyMap.keySet())
			  totalVotes += partyMap.get(partysId); 
				 
			Long selectedPartyTotal = partyMap.get(selectedpartyId);
			Long comparePartyTotal = 0L;
				 
			  for(Long partysId:partyMap.keySet())
			  {
			    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
				 comparePartyTotal = partyMap.get(partysId);
				  
			  }
		   
			     
		  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      
	      double difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      
	    	
	      String locationName = "";
	      if(locationType != null && locationType.equalsIgnoreCase("panchayat"))
	    	 locationName = panchayatDAO.getPanchayatNameById(id); 
	      else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
	    	 locationName = boothDAO.getBoothPartNoByBoothId(id);
	      
	      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
	      for(PartyPositionVO positionVO :partyPositionVOList)
	      {
	    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
	    	{
	    	 PartyPositionVO locationVO = null;
	    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
	    	 if(locationList == null || locationList.size() == 0)
	    		locationList = new ArrayList<PartyPositionVO>(0);
	    	 
	    	 locationVO = checkPartyPositionVOExist(id,locationList);
	    	 if(locationVO == null)
	    	 {
	    		 locationVO = new PartyPositionVO();
	    		 locationVO.setId(id);
	    		 locationVO.setName(locationName != null?locationName:" ");
	    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
	    		 locationVO.setTotalValidVotes(totalVotes);
	    		 locationList.add(locationVO);
	    		 positionVO.setPartyPositionVOList(locationList);
	    		 
	    	 }
	    	 
	    	}
	      }
	    	
		}
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
		}
	}
	
	public PartyPositionVO checkPartyPositionVOExist(Long locationId,List<PartyPositionVO> list)
	{
		try{
		if(list == null)
		 return null;
		for(PartyPositionVO positionVO:list)
		 if(positionVO.equals(locationId))
		  return positionVO;
			
		 return null;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" ExceptionOccured in checkPartyPositionVOExist() method, Exception - "+e);
		 return null;
		}
	}
	
	
	
	 @SuppressWarnings("unchecked")
		public List<SelectOptionVO> getConstituenciesForUserAccessByStateId(List<SelectOptionVO> ConstituenciesForUserAccessed,Long electionId,Long electionYear)
		{
		 List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>(0);;
		try{
			List<Long> constituencyIds =  voterInfoDAO.getNONURBANConstituencyIds(electionId,electionYear,1L);
			if(ConstituenciesForUserAccessed != null && ConstituenciesForUserAccessed.size() > 0 && constituencyIds != null)
			{
				for(SelectOptionVO selectOptionVO : ConstituenciesForUserAccessed)
				{
					if(constituencyIds.contains(selectOptionVO.getId()))
						constituencyList.add(selectOptionVO);
				}
			}
			
			return constituencyList;
		}catch (Exception e) {
			e.printStackTrace();
			return constituencyList;
		}
		}
	 public List<SelectOptionVO> getPartyDetailsByMandal(Long tehsilId){
		 List<SelectOptionVO> nominatedPartiesLists = null;
		 try{
			 List<Object[]> partyList= hamletBoothElectionDAO.getParticipatedPartiesByEleIdNTehsilId(tehsilId);
			 nominatedPartiesLists = new ArrayList<SelectOptionVO>();
				if(partyList !=null && partyList.size()>0)
					for (Object[] parms : partyList) {
						nominatedPartiesLists.add(new SelectOptionVO(Long.valueOf(parms[0].toString()),parms[1].toString()));
					}
			 return nominatedPartiesLists;
		 }catch(Exception e){
			 e.printStackTrace();
		 return null;
		}			
	}	
	 
	 public List<SelectOptionVO> getElectionIdsAndYearsBytehsilId(List<Long> electionScope,Long partyId,Long tehsilId){
		 List<SelectOptionVO> electionYearslist;
			List elections;
			try {
				electionYearslist = new ArrayList<SelectOptionVO>();
				if(electionScope !=null && electionScope.size()>0){
					for (Long scopeId : electionScope) {
						elections = nominationDAO.findByPartyIdAndTehsilId(scopeId,partyId,tehsilId);
						for (int i = 0; i < elections.size(); i++) {
							Object[] parms = (Object[]) elections.get(i);
							electionYearslist.add(new SelectOptionVO(Long.parseLong(parms[0].toString()), parms[1].toString().concat("("+parms[2].toString()+")")));
						}						
					}					
				}				
			return electionYearslist;
			} catch (Exception e) {
				e.printStackTrace();
			return null;
			}

	 }
	 
	 public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevel(Long mandalId,Long userid,Long constituencyId)
	 {
		 List<YouthLeaderSelectionVO> returnList = new ArrayList<YouthLeaderSelectionVO>();
		 try {
			 LOG.debug("Enterd Into findingBoothInchargesForBoothLevel() method in SuggestiveModelService Class ");
				List<SelectOptionVO> panchayats = new ArrayList<SelectOptionVO>();
				List<Long> panchayaIds = new ArrayList<Long>();
				List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
				List<Long> boothIds = new ArrayList<Long>();
				Map<Long, Long> totalVotersInPanchayat = new HashMap<Long, Long>();//Map<id,totalVoters>
				Map<Long, Long> totalVotersInBooth = new HashMap<Long, Long>();//Map<id,totalVoters>
				Map<Long,List<BasicVO>> casteMapForPanchayat = new HashMap<Long, List<BasicVO>>();//Map<panchayatid,catseDetails>
				Map<Long,List<BasicVO>> casteMapForBooth = new HashMap<Long, List<BasicVO>>();//Map<booyhid,catseDetails>
				Long publicationId = 0l;
				List<BasicVO> basicVOListForPanchayat = null;
				List<BasicVO> basicVOListForBooth = null;
				List<YouthLeaderSelectionVO> botthLevelList = null;
				List<YouthLeaderSelectionVO> panchayatLevelList = null;
				YouthLeaderSelectionVO boothyouthSelectionVO  = null;
				List<YouthLeaderSelectionVO> botthDetailsList = null;
				 DecimalFormat df = new DecimalFormat("#.##");
				publicationId = publicationDateDAO.getLatestPublicationId();
				List<Object[]> panchayatsList = panchayatDAO.getPanchayatsByTehsilId(mandalId);
				if(panchayatsList != null && panchayatsList.size() > 0)
				{
					panchayats = new ArrayList<SelectOptionVO>();
					panchayaIds = new ArrayList<Long>();
					for (Object[] parms : panchayatsList) {
						SelectOptionVO selectOptionVO = new SelectOptionVO();	
						selectOptionVO.setId((Long)parms[0]);
						selectOptionVO.setName(parms[1].toString());
						panchayats.add(selectOptionVO);
						panchayaIds.add(selectOptionVO.getId());
					}
				}
				
				if(panchayaIds != null && panchayaIds.size() > 0)
				{
					List<Object[]> panchaytVotersCount = voterCastBasicInfoDAO.getToatlVotersForSelectedLevl(panchayaIds,userid,publicationId,3l,constituencyId);
					if(panchaytVotersCount != null && panchaytVotersCount.size() > 0)
					{
						for (Object[] parms : panchaytVotersCount) {
							Long total = ((Long)parms[1] + (Long)parms[2]);
							totalVotersInPanchayat.put((Long)parms[0], total);
						}
					}
					for (Long panchayatId : panchayaIds) {
						List<Object[]> boothsList = boothDAO.getBoothsByPanchayat(panchayatId,publicationId);
						if(boothsList != null && boothsList.size() > 0)
						{
							basicVOListForPanchayat = new ArrayList<BasicVO>();
							for (Object[] parms : boothsList) {
								SelectOptionVO selectOptionVO = new SelectOptionVO();	
								selectOptionVO.setId((Long)parms[0]);
								selectOptionVO.setName(parms[1].toString());
								booths.add(selectOptionVO);
								boothIds.add(selectOptionVO.getId());
							}
							
						}
						List<Object[]> casteDetails = voterCastInfoDAO.getTopThreeCasteFoeSelctedLevel(panchayatId,3l,publicationId,userid);
						if(casteDetails != null && casteDetails.size() > 0)
						{
							int count = 0;
							for (Object[] parms : casteDetails) {
								if(IConstants.MAX_LEVEL > count)
								{
									BasicVO basicVO = new BasicVO();
									basicVO.setId((Long)parms[0]);
									basicVO.setCount((Long)parms[2]);
									basicVO.setName(parms[1].toString());
									basicVO.setPerc((Double)parms[3]);
									basicVOListForPanchayat.add(basicVO);
									
								}
								else
								{
									break;
								}
								count ++;
							}
						}
						casteMapForPanchayat.put(panchayatId, basicVOListForPanchayat);
					}
					
					
				}
				if(boothIds != null && boothIds.size() > 0)
				{
					for (Long boothId : boothIds) {
						Long totalVoter = boothPublicationVoterDAO.getTotalVoters(boothId);
						List<Object[]> casteDetails = userVoterDetailsDAO.getCasteDetailsOfVoterByBoothId(boothId,publicationId,userid);
						totalVotersInBooth.put(boothId, totalVoter);
						int count = 0;
						basicVOListForBooth = new ArrayList<BasicVO>();
						for (Object[] parms : casteDetails) {
							if(IConstants.MAX_LEVEL > count)
							{
								BasicVO basicVO = new BasicVO();
								basicVO.setId(boothId);
								basicVO.setCount((Long)parms[1]);
								basicVO.setName(parms[0].toString());
								
								basicVO.setPerc(Double.valueOf(df.format((Long)parms[1]*100/totalVoter.floatValue())));
								basicVOListForBooth.add(basicVO);
								
							}
							else
							{
								break;
							}
							count ++;
						
						}
						casteMapForBooth.put(boothId, basicVOListForBooth);
					}
					
				}
				if(panchayaIds != null && panchayaIds.size() > 0)
				{
					for (Long panchayatid : panchayaIds) {
						YouthLeaderSelectionVO youthLeaderSelectionVO = new YouthLeaderSelectionVO();
						 List<BasicVO> panchayatDetails = casteMapForPanchayat.get(panchayatid);
						 Long panchayatTotalVoters = totalVotersInPanchayat.get(panchayatid);
						 panchayatLevelList = new ArrayList<YouthLeaderSelectionVO>();
						 if(panchayatDetails != null && panchayatDetails.size() > 0)
						 {
							 for (BasicVO basicVO : panchayatDetails) {
								 YouthLeaderSelectionVO youthLeaderSelection = new YouthLeaderSelectionVO();
								 youthLeaderSelection.setCasteName(basicVO.getName());
								 youthLeaderSelection.setCasteVoters(basicVO.getCount());
								 youthLeaderSelection.setCasteVotersPerc(basicVO.getPerc());
								 panchayatLevelList.add(youthLeaderSelection);
							}
						 }
						List<Long> boothList = boothDAO.getBoothsByPanchayatId(panchayatid,publicationId);
						if(boothList != null && boothList.size() > 0)
						{
							botthDetailsList = new ArrayList<YouthLeaderSelectionVO>();
							for (Long boothId : boothList) {
								boothyouthSelectionVO = new YouthLeaderSelectionVO();
								
								List<BasicVO> boothCasteDate = casteMapForBooth.get(boothId);
								if(boothCasteDate != null && boothCasteDate.size() > 0)
								{
									botthLevelList = new ArrayList<YouthLeaderSelectionVO>();
									for (BasicVO basicVO : boothCasteDate) {
										YouthLeaderSelectionVO youthSelectionVO = new YouthLeaderSelectionVO();										
										youthSelectionVO.setCasteName(basicVO.getName());
										youthSelectionVO.setCasteVoters(basicVO.getCount());
										youthSelectionVO.setCasteVotersPerc(basicVO.getPerc());
										botthLevelList.add(youthSelectionVO);
									}
									
								}
								boothyouthSelectionVO.setBoothId(boothId);
								boothyouthSelectionVO.setBoothName(boothDAO.get(boothId).getPartNo());
								boothyouthSelectionVO.setBoothTotalVoters(totalVotersInBooth.get(boothId));
								boothyouthSelectionVO.setBoothLevelLeadersList(botthLevelList);
								botthDetailsList.add(boothyouthSelectionVO);
								
							}
							
							youthLeaderSelectionVO.setBoothLevelLeadersList(botthDetailsList);
						}
						youthLeaderSelectionVO.setPanchayatTotalVoters(panchayatTotalVoters);
						youthLeaderSelectionVO.setPanchayatId(panchayatid);
						youthLeaderSelectionVO.setMandalName(tehsilDAO.get(mandalId).getTehsilName());
						youthLeaderSelectionVO.setPanchayatName(panchayatDAO.get(panchayatid).getPanchayatName());
						youthLeaderSelectionVO.setPanchayatLevelLeadersList(panchayatLevelList);
						returnList.add(youthLeaderSelectionVO);
					}
				}
				
			} catch (Exception e) {
				LOG.error(" ExceptionOccured in findingBoothInchargesForBoothLevel() method, Exception - "+e);
			}
			
		return returnList;
	}
	 
	 public List<PartyPositionVO> getMoreVotersAddedLocDetailsWherePartyIsPoor(List<PartyPositionVO> partyPositions){
		 List<PartyPositionVO> returnVal = new ArrayList<PartyPositionVO>();
		 Map<Long,PartyPositionVO> panchatayats = new HashMap<Long,PartyPositionVO>();
		 Map<Long,PartyPositionVO> suggestions = new HashMap<Long,PartyPositionVO>();
		 for(PartyPositionVO positionVo:partyPositions){
			 if(positionVo.getMinValue() < 0 && positionVo.getPartyPositionVOList() != null && positionVo.getPartyPositionVOList().size() >0){
				 returnVal.add(positionVo);
				 for(PartyPositionVO panchayat:positionVo.getPartyPositionVOList()){
					 panchatayats.put(panchayat.getId(), panchayat);
					 suggestions.put(panchayat.getId(), positionVo);
				 }
			 }
		 }
		 getAddedVoterInfo(panchatayats,suggestions);
		 return returnVal;
	 }
	 
	 public void getAddedVoterInfo(Map<Long,PartyPositionVO> panchatayats,Map<Long,PartyPositionVO> suggestions)
	 {
		 PartyPositionVO vo = null;
		try{
			
				Long publicationId = publicationDateDAO.getLatestPublicationId();
				List<Object[]> deletedVoters = voterModificationInfoDAO.getAddedVotersByPanchayats(new ArrayList<Long>(panchatayats.keySet()),publicationId);
				if(deletedVoters != null && deletedVoters.size() > 0)
				{
					for(Object[] params : deletedVoters)
					{
					  if(params[0] != null && ((Long)params[0]).longValue() >= IConstants.MIN_ADDED_VOTERS){
						vo = panchatayats.get((Long)params[1]);
						vo.setAddedVotersCount((Long)params[0]);
						PartyPositionVO suggestion = suggestions.get((Long)params[1]);
						if(suggestion != null){
							suggestion.setAddedVotersPresent(true);
						}
					  }
					}
				}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 }
		
	 public List<PartyPositionVO> getSuggestiveLocationsForAParty(List<PartyPositionVO> partyPositions){
		 List<PartyPositionVO> returnValues = new ArrayList<PartyPositionVO>(); 
	    try{
		 if(partyPositions.size() == 2){
			 List<PartyPositionVO> latestElec =  partyPositions.get(0).getPartyPositionVOList();
			 List<PartyPositionVO> prevElec =  partyPositions.get(1).getPartyPositionVOList();
			 int x = -1;
				for(int j = latestElec.size()-1;j>0;j--){//2009
					x = x+1;
					List<PartyPositionVO> panchayaties = latestElec.get(j).getPartyPositionVOList();
					if(panchayaties != null && panchayaties.size() >0){	
						for(int i = 0;i< (prevElec.size()-1-x);i++)
						{//2004
							List<PartyPositionVO> prevPanchayaties = prevElec.get(i).getPartyPositionVOList();
							if(prevPanchayaties != null && prevPanchayaties.size() >0){	
								populateMachedValues(panchayaties,prevPanchayaties,returnValues);
							}
						}
					}
				}
				 x = -1;
				
				for(int j = prevElec.size()-1;j>0;j--){//2004
					x = x+1;
					List<PartyPositionVO> prevPanchayaties = prevElec.get(j).getPartyPositionVOList();
					if(prevPanchayaties != null && prevPanchayaties.size() >0){	
						for(int i = 0;i< (latestElec.size()-1-x);i++)
						{//2009
							List<PartyPositionVO> panchayaties = latestElec.get(i).getPartyPositionVOList();
							if(panchayaties != null && panchayaties.size() >0){	
								populateMachedValues(panchayaties,prevPanchayaties,returnValues);
							}
						}
					}
				}
				 
				for(int i = 0;i<latestElec.size();i++){
					List<PartyPositionVO> panchayaties = latestElec.get(i).getPartyPositionVOList();
					List<PartyPositionVO> prevPanchayaties = prevElec.get(i).getPartyPositionVOList();
					populateMachedValues(panchayaties,prevPanchayaties,returnValues);
				}
		 }
	    }catch(Exception e){
		  
	    }
	    return returnValues;
	 }
	 
	 public void populateMachedValues(List<PartyPositionVO> panchayaties,List<PartyPositionVO> prevPanchayaties,List<PartyPositionVO> returnValues){
		 for(PartyPositionVO prev:prevPanchayaties){
		    for(PartyPositionVO current:panchayaties){
			   if(current.getId().longValue() == prev.getId().longValue()){
				  returnValues.add(current);
				}
			}
		 }
	 }
}
