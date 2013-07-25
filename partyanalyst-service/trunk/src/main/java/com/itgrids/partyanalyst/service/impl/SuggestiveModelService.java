package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.utils.IConstants;
import org.apache.log4j.Logger;

public class SuggestiveModelService implements ISuggestiveModelService {
	
	private static final Logger LOG = Logger.getLogger(SuggestiveModelService.class);
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IElectionDAO electionDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO ;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
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

	public OptionVO getPartyPerformanceForSelectedLocation(Long constituencyId,Long electionId,Long partyId,Long locationId,String type)
	{
		OptionVO optionVO = null;
		List<Long> locationIdsList = null;
		try{
		if(type != null && type.equalsIgnoreCase("panchayat"))
		 locationIdsList = hamletBoothElectionDAO.getBoothIdsByPanchayatId(locationId, electionId); 	
		
		/*else if(type != null && type.equalsIgnoreCase("mandal"))
		 locationIdsList = hamletBoothElectionDAO.getPanchayatIdsByTehsilIdAndElectionId(locationId, electionId);*/
		
	    if(locationIdsList != null && locationIdsList.size() > 0)
	    {
	      optionVO = new OptionVO();
	      
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
	      
	      for(Long id:locationIdsList)
	      {
	    	  Long totalVoters = 0L;
	    	  List<Object[]> list = null;
	    	  
	    	  /*if(type != null && type.equalsIgnoreCase("panchayat")) 
	    	   list = candidateBoothResultDAO.getVotesEarnedForSelectedLocation(constituencyId, electionId, id,IConstants.BOOTH);
	    	  else if(type != null && type.equalsIgnoreCase("mandal")) 
	    	   list = candidateBoothResultDAO.getVotesEarnedForSelectedLocation(constituencyId, electionId, id,IConstants.PANCHAYAT);*/
	    	  
	    	  list = candidateBoothResultDAO.getVotesEarnedForSelectedbooth(constituencyId, electionId, id);
	    	  
	    	  if(list != null && list.size() > 0)
		      {
	    		Long selectedPartyTotal = 0L;
	    		Long comparePartyTotal = 0L;
	    		boolean tempVar = false;
	    		
	    		 for(Object[] params:list)
	    		 {
	    		  if(params[1].equals(partyId))
	    			selectedPartyTotal = (Long)params[0];
	    		  
	    		  totalVoters += (Long)params[0];
	    		  
	    		 }
	    		 if(list.get(0)[1].equals(partyId))
	    			tempVar = true;
	    		 
		    	 for(Object[] params :list)
		    	 {
		    		if(!params[1].equals(partyId))
		    		{
		    		 comparePartyTotal = (Long)params[0];
		    		 break;
		    		}
		    	 }
		    	 double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100/totalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		    	 double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100/totalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		    	 
		    	 double difference = 0.00;
		    	 
		    	 if(tempVar)
		    	  difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		    	 else
		    	  difference = new BigDecimal(comparePartyTotalPercent - selectedPartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		    	 
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
	    	
	    }
		
		
		  return optionVO;
		}catch (Exception e) {
		 e.printStackTrace();
		 return optionVO;
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
			 List<SelectOptionVO> booths = null;
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
				 List<Object[]> tehsilsList = localElectionBodyDAO.getTehsilsByLocalBody(localBodyId);
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
						List<Long> boothsList = boothDAO.getboothsByTehsilId(tehsilId);
						if(boothsList != null && boothsList.size() > 0)
						{
						  for (Long boothId : boothsList) {
						  boothIds.add(boothId);
						  }
						}
						List<Object[]> partyWiseVotesList = candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(constituencyId,electionId,boothIds);
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
						List<Long> boothsList = boothDAO.getboothsByWardId(wardId);
						if(boothsList != null && boothsList.size() > 0)
						{
						  for (Long boothId : boothsList) {
						  boothIds.add(boothId);
						  }
						}
						List<Object[]> partyWiseVotesList = candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(constituencyId,electionId,boothIds);
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
			List<Long> boothsList = boothDAO.getboothsByWardId(wardId);
			if(boothsList != null && boothsList.size() > 0)
			{
			  for (Long boothId : boothsList)
			  {
			     boothIds.add(boothId);
			  }
			}
		   List<Object[]> partyWiseVotesList = candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(constituencyId,electionId,boothIds);
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
   
}
