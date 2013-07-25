package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SuggestiveModelService implements ISuggestiveModelService {
	
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	
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
	
	
	
   
}
