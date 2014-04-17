package com.itgrids.partyanalyst.service.impl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyKeywordDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IKeywordDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.PartyActivitiesVO;
import com.itgrids.partyanalyst.model.CandidatePartyFile;
import com.itgrids.partyanalyst.model.CandidatePartyKeyword;
import com.itgrids.partyanalyst.service.IPartyActivitiesService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IWebConstants;

public class PartyActivitiesService implements IPartyActivitiesService {
	private static final Logger LOG = Logger.getLogger(PartyActivitiesService.class);
	
	private ICandidatePartyFileDAO candidatePartyFileDAO;
	private ICandidatePartyKeywordDAO candidatePartyKeywordDAO;
	private IKeywordDAO keywordDAO;
	private IDistrictDAO districtDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	
	private ICandidatePartyCategoryDAO candidatePartyCategoryDAO;
	
	private IPartyDAO partyDAO;
	
	public ICandidatePartyFileDAO getCandidatePartyFileDAO() {
		return candidatePartyFileDAO;
	}

	public void setCandidatePartyFileDAO(
			ICandidatePartyFileDAO candidatePartyFileDAO) {
		this.candidatePartyFileDAO = candidatePartyFileDAO;
	}

	public ICandidatePartyKeywordDAO getCandidatePartyKeywordDAO() {
		return candidatePartyKeywordDAO;
	}

	public void setCandidatePartyKeywordDAO(
			ICandidatePartyKeywordDAO candidatePartyKeywordDAO) {
		this.candidatePartyKeywordDAO = candidatePartyKeywordDAO;
	}

	public IKeywordDAO getKeywordDAO() {
		return keywordDAO;
	}

	public void setKeywordDAO(IKeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}


	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public ICandidatePartyCategoryDAO getCandidatePartyCategoryDAO() {
		return candidatePartyCategoryDAO;
	}

	public void setCandidatePartyCategoryDAO(
			ICandidatePartyCategoryDAO candidatePartyCategoryDAO) {
		this.candidatePartyCategoryDAO = candidatePartyCategoryDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public PartyActivitiesVO getNewsToUpdateKeywords(List<Long> categoryIds,Date fromDate,Date toDate,List<Long> districtIds,Integer startIndex,Integer maxIndex){
		LinkedHashMap<Long,PartyActivitiesVO> newsMap = new LinkedHashMap<Long,PartyActivitiesVO>();
		PartyActivitiesVO returnVO = new PartyActivitiesVO();
		try{
			//0 fileId,1 partyName,2 districtId,3 districtName,4 constituencyId,5 constiname,6 fileTitle,7 fileDescription,8 fileDate,9 fontId,10 desc fontId,11 partyId
			PartyActivitiesVO partyActivitiesVO = null;
			List<Object[]>  newsList = candidatePartyFileDAO.getPoliticalActivitiesNews(fromDate, toDate, categoryIds, districtIds,startIndex,maxIndex);
			Long totalNews = candidatePartyFileDAO.getPoliticalActivitiesNewsCount(fromDate, toDate, categoryIds, districtIds);
			returnVO.setCount(totalNews);
			for(Object[] news:newsList){
				 partyActivitiesVO = new PartyActivitiesVO();
				 partyActivitiesVO.setId((Long)news[0]);
				 partyActivitiesVO.setDate(news[8].toString());
				 if(news[1] != null){
				   partyActivitiesVO.setName(news[1].toString());
				 }
				 partyActivitiesVO.setPartyId((Long)news[11]);
				 if(news[5] != null){
					 partyActivitiesVO.setLocation(news[5].toString()+" Constituency");
				 }else{
					 partyActivitiesVO.setLocation(news[3].toString()+" District"); 
				 }
				 if(news[6] != null){
				     partyActivitiesVO.setTitle(StringEscapeUtils.unescapeJava(news[6].toString()));
				 }else{
					 partyActivitiesVO.setTitle("");
				 }
				 if(news[7] != null){
				     partyActivitiesVO.setDescription(StringEscapeUtils.unescapeJava(news[7].toString()));
				 }else{
					 partyActivitiesVO.setDescription("");
				 }
				 if(news[9] != null){
					 partyActivitiesVO.setTileFont(true);
				 }
				 if(news[10] != null){
					 partyActivitiesVO.setDescFont(true);
				 }
				 newsMap.put((Long)news[0], partyActivitiesVO);
			 }
			 if(newsMap.size() > 0){
				 //0 fileId,1 keyword
				 List<Object[]> keywordsList =  candidatePartyKeywordDAO.getKeywordsByFileIds(newsMap.keySet());
				 for(Object[] keyword:keywordsList){
					 if(keyword[1] != null){
						 partyActivitiesVO = newsMap.get((Long)keyword[0]);
						 if(partyActivitiesVO != null){
							 if(partyActivitiesVO.getKeywords() != null && partyActivitiesVO.getKeywords().length() > 0){
								 partyActivitiesVO.setKeywords(partyActivitiesVO.getKeywords()+", "+keyword[1].toString());
							 }else{
								 partyActivitiesVO.setKeywords(keyword[1].toString()); 
							 }
						 }
					 }
				 }
			 }
			
		}catch(Exception e){
			LOG.error("Exception rised in getNewsToUpdateKeywords ",e);
		}
		if(newsMap.size() > 0){
			returnVO.setActivitiesList(new ArrayList<PartyActivitiesVO>(newsMap.values()));
		}else{
			returnVO.setActivitiesList(new ArrayList<PartyActivitiesVO>());
		}
		return returnVO;
	}
	
	public String updateKeywords(List<PartyActivitiesVO> activities){
		String status = "Success";
	  try{
		for(PartyActivitiesVO activity:activities){
			//check it is from destination
			List<Long> candidatePartyFileIds = candidatePartyFileDAO.getDestinationDetails(activity.getPartyId(),activity.getId());
			if(candidatePartyFileIds.size() > 0){
				List<String>  existingKeywords = candidatePartyKeywordDAO.getExistingKeywords(activity.getKeywordsList(), candidatePartyFileIds);
				for(String keyword:activity.getKeywordsList()){
					if(!existingKeywords.contains(keyword)){
						CandidatePartyKeyword cpk = new CandidatePartyKeyword();
						cpk.setCandidatePartyFile(candidatePartyFileDAO.get(candidatePartyFileIds.get(0)));
						cpk.setKeyword(keywordDAO.getKeywordByName(keyword).get(0));
						candidatePartyKeywordDAO.save(cpk);
					}
				}
			}else{
				 candidatePartyFileIds = candidatePartyFileDAO.getSourceDetails(activity.getPartyId(), activity.getId());
				 if(candidatePartyFileIds.size() > 0){
						List<Object[]>  existingKeywords = candidatePartyKeywordDAO.getAllExistingKeywords(activity.getKeywordsList(), candidatePartyFileIds);
						if(existingKeywords.size() > 0){
							List<Long> cpfIds = new ArrayList<Long>();
							List<String> keywords = new ArrayList<String>();
							for(Object[] keyword:existingKeywords){
								cpfIds.add((Long)keyword[0]);
								keywords.add(keyword[1].toString());
							}
							for(String keyword:activity.getKeywordsList()){
								if(!keywords.contains(keyword)){
									CandidatePartyKeyword cpk = new CandidatePartyKeyword();
									cpk.setCandidatePartyFile(candidatePartyFileDAO.get(cpfIds.get(0)));
									cpk.setKeyword(keywordDAO.getKeywordByName(keyword).get(0));
									candidatePartyKeywordDAO.save(cpk);
								}
							}
						}else{
							DateUtilService dateUtilService = new DateUtilService();
							CandidatePartyFile cpf = new CandidatePartyFile();
							CandidatePartyFile existingCpf = candidatePartyFileDAO.get(candidatePartyFileIds.get(0));
							cpf.setSourceCandidate(existingCpf.getSourceCandidate());
							cpf.setSourceBenefit(existingCpf.getSourceBenefit());
							cpf.setSourceParty(existingCpf.getSourceParty());
							cpf.setFile(existingCpf.getFile());
							cpf.setCreatedDate(dateUtilService.getCurrentDateAndTime());
							cpf.setUpdateddate(dateUtilService.getCurrentDateAndTime());
							cpf = candidatePartyFileDAO.save(cpf);
							for(String keyword:activity.getKeywordsList()){
									CandidatePartyKeyword cpk = new CandidatePartyKeyword();
									cpk.setCandidatePartyFile(cpf);
									cpk.setKeyword(keywordDAO.getKeywordByName(keyword).get(0));
									candidatePartyKeywordDAO.save(cpk);
							}
						}
						
					}
			}
		}
	  }catch(Exception e){
		  LOG.error("Exception rised in updateKeywords ",e);
		  status = "error occured";
	  }
		return status;
	}
	
	public List<PartyActivitiesVO> getActivitiesStatus(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,List<Long> partyIds,List<Long> categoryIds){
		List<PartyActivitiesVO> returnList = new ArrayList<PartyActivitiesVO>();
		try{
			//0 count,1 partyName,2 keywordid,3 keyword,4 locationId,5 locationName
		     List<Object[]> newsCountList = candidatePartyFileDAO.getAllPoliticalActivitiesCount(fromDate, toDate, locationIds, locationType,partyIds,3991l);
		     if(newsCountList.size() == 0){
		    	 return returnList;
		     }
		     
		     //Map<locationId,Map<partyName,keywordCountList>>
		     LinkedHashMap<Long,LinkedHashMap<String,List<PartyActivitiesVO>>> locationMap = new LinkedHashMap<Long,LinkedHashMap<String,List<PartyActivitiesVO>>>();
		     //Map<partyName,Map<keywordId,countVO>>
		     LinkedHashMap<String,List<PartyActivitiesVO>> partyMap = null;
		     
		     List<PartyActivitiesVO> keywordsList = null;
		     List<Object[]> locationDetails = null;
		     LinkedHashMap<Long,String> locationNames = new LinkedHashMap<Long,String>();
		     if(locationType.longValue() == 1l){
		    	 locationDetails = districtDAO.getDistrictNames(locationIds);
		     }else{
		    	 locationDetails = delimitationConstituencyDAO.findConstituencyiOrder(locationIds);
		     }
		     List<String> partyNamesList = new ArrayList<String>();
		     for(Object[]  partyNames:newsCountList){
		    	if(!partyNamesList.contains(partyNames[1].toString())){
		    		partyNamesList.add(partyNames[1].toString());
		    	}
		     }
		     for(Object[] location:locationDetails){
		    	 locationNames.put((Long)location[0],location[1].toString());
		     }
		     for(Long locationId:locationNames.keySet()){
	    		 partyMap = new LinkedHashMap<String,List<PartyActivitiesVO>>();
	    		 for(String partyName:partyNamesList){
	    			 keywordsList = new ArrayList<PartyActivitiesVO>();
		    		 PartyActivitiesVO cadre = new PartyActivitiesVO();
		    		 cadre.setName("Cadre");
		    		 cadre.setCount(0l);
		    		 PartyActivitiesVO mlaIncharge = new PartyActivitiesVO();
		    		 mlaIncharge.setName("MLA/Incharge");
		    		 mlaIncharge.setCount(0l);
		    		 PartyActivitiesVO mpIncharge = new PartyActivitiesVO();
		    		 mpIncharge.setName("MP/Incharge");
		    		 mpIncharge.setCount(0l);
		    		 keywordsList.add(cadre);
		    		 keywordsList.add(mlaIncharge);
		    		 keywordsList.add(mpIncharge);
		    		 partyMap.put(partyName,keywordsList);
	    		 }
	    		 locationMap.put(locationId,partyMap);
	    	 
		     }
		     for(Object[] newsCount:newsCountList){
		    	 partyMap = locationMap.get((Long)newsCount[4]);
		    	 keywordsList = partyMap.get(newsCount[1].toString());
		    	 if(keywordsList == null){
		    		 keywordsList = partyMap.get("All Other Parties");
		    	 }
		    	 if(newsCount[3].toString().equalsIgnoreCase("Cadre")){
		    		 keywordsList.get(0).setCount((Long)newsCount[0]);
		    	 }else if(newsCount[3].toString().equalsIgnoreCase("MLA/Incharge")){
		    		 keywordsList.get(1).setCount((Long)newsCount[0]);
		    	 }else if(newsCount[3].toString().equalsIgnoreCase("MP/Incharge")){
		    		 keywordsList.get(2).setCount((Long)newsCount[0]);
		    	 }
		     }
		     for(Long locationId:locationMap.keySet()){
		    	 PartyActivitiesVO locationVO = new PartyActivitiesVO();
		    	 List<PartyActivitiesVO> partiesList = new ArrayList<PartyActivitiesVO>();
		    	 locationVO.setActivitiesList(partiesList);
		    	 locationVO.setName(locationNames.get(locationId));
		    	 partyMap = locationMap.get(locationId);
		    	 for(String partyName:partyMap.keySet()){
		    		 PartyActivitiesVO partyVO = new PartyActivitiesVO();
		    		 partiesList.add(partyVO);
		    		 partyVO.setName(partyName);
		    		 partyVO.setActivitiesList(partyMap.get(partyName));
		    	 }
		    	 returnList.add(locationVO);
		     }
		 }catch(Exception e){
			 LOG.error("Exception rised in getActivitiesStatus ",e);
		 }
		return returnList;
	}
	
	public Map<Long,Long> getProblemCounts(Date fromDate,Date toDate,Long locationType,List<Long> locationIds){
		//Map<locationId,count>
		Map<Long,Long> problemsCount = new HashMap<Long,Long>();
		try{
			//0 count,1locationId
			List<Object[]> problemsCountList = candidatePartyCategoryDAO.getProblemsCount(fromDate, toDate, locationType, locationIds, 7l);
			for(Object[] count:problemsCountList){
				problemsCount.put((Long)count[1], (Long)count[0]);
			}
		}catch(Exception e){
			 LOG.error("Exception rised in getProblemCounts ",e);
		}
		return problemsCount;
	}
	
	public Map<Long,Map<String,Long>> getElectionIssuesCount(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,List<Long> partyIds){
		//Map<locationId,Map<partyName,count>>
		Map<Long,Map<String,Long>> electionIssuesCount = new HashMap<Long,Map<String,Long>>();
		try{
			//0 count,1 partyName, 2 locationId
			List<Object[]> electionIssuesList = candidatePartyCategoryDAO.getElectionIssues(fromDate, toDate, locationType, locationIds, 4015l, partyIds);
			for(Object[] count:electionIssuesList){
				Map<String,Long> partyMap = electionIssuesCount.get((Long)count[2]);
				if(partyMap == null){
					partyMap = new HashMap<String,Long>();
					electionIssuesCount.put((Long)count[2], partyMap);
				}
				partyMap.put(count[1].toString(), (Long)count[0]);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getElectionIssuesCount ",e);
		}
		return electionIssuesCount;
	}
	
	public LinkedHashMap<Long,LinkedHashMap<String,List<PartyActivitiesVO>>> getActivitiesCount(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,List<Long> partyIds,LinkedHashMap<Long,String> locationNames,List<String> partyNamesList){
		
	    
	     
	     //Map<locationId,Map<partyName,keywordCountList>>
	     LinkedHashMap<Long,LinkedHashMap<String,List<PartyActivitiesVO>>> locationMap = new LinkedHashMap<Long,LinkedHashMap<String,List<PartyActivitiesVO>>>();
	     try{
	     //Map<partyName,Map<keywordId,countVO>>
	     LinkedHashMap<String,List<PartyActivitiesVO>> partyMap = null;
	     
	     List<PartyActivitiesVO> keywordsList = null;
	   //0 count,1 partyName,2 keywordid,3 keyword,4 locationId,5 locationName
	     List<Object[]> newsCountList = candidatePartyFileDAO.getAllPoliticalActivitiesCount(fromDate, toDate, locationIds, locationType,partyIds,3991l);
	     
	     for(Long locationId:locationNames.keySet()){
    		 partyMap = new LinkedHashMap<String,List<PartyActivitiesVO>>();
    		 for(String partyName:partyNamesList){
    			 keywordsList = new ArrayList<PartyActivitiesVO>();
	    		 PartyActivitiesVO cadre = new PartyActivitiesVO();
	    		 cadre.setName("Cadre");
	    		 cadre.setCount(0l);
	    		 PartyActivitiesVO mlaIncharge = new PartyActivitiesVO();
	    		 mlaIncharge.setName("MLA/Incharge");
	    		 mlaIncharge.setCount(0l);
	    		 PartyActivitiesVO mpIncharge = new PartyActivitiesVO();
	    		 mpIncharge.setName("MP/Incharge");
	    		 mpIncharge.setCount(0l);
	    		 keywordsList.add(cadre);
	    		 keywordsList.add(mlaIncharge);
	    		 keywordsList.add(mpIncharge);
	    		 partyMap.put(partyName,keywordsList);
    		 }
    		 locationMap.put(locationId,partyMap);
    	 
	     }
	     for(Object[] newsCount:newsCountList){
	    	 partyMap = locationMap.get((Long)newsCount[4]);
	    	 keywordsList = partyMap.get(newsCount[1].toString());
	    	 if(keywordsList == null){
	    		 keywordsList = partyMap.get("All Other Parties");
	    	 }
	    	 String keyword = newsCount[3].toString().toLowerCase();
	    	 if(keyword.contains("cadre")){
	    		 keywordsList.get(0).setCount((Long)newsCount[0]);
	    	 }else if(keyword.contains("mla/incharge") || keyword.contains("mla incharge")){
	    		 keywordsList.get(1).setCount((Long)newsCount[0]);
	    	 }else if(keyword.contains("mp/incharge") || keyword.contains("mp incharge")){
	    		 keywordsList.get(2).setCount((Long)newsCount[0]);
	    	 }
	     }
	     }catch(Exception e){
	    	 LOG.error("Exception rised in getActivitiesCount ",e);
	     }
	     return locationMap;
	}
	
	public LinkedHashMap<Long,LinkedHashMap<String,List<PartyActivitiesVO>>> getElecCampionCount(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,List<Long> partyIds,LinkedHashMap<Long,String> locationNames,List<String> partyNamesList){
		
	    
	     
	     //Map<locationId,Map<partyName,keywordCountList>>
	     LinkedHashMap<Long,LinkedHashMap<String,List<PartyActivitiesVO>>> locationMap = new LinkedHashMap<Long,LinkedHashMap<String,List<PartyActivitiesVO>>>();
	     try{
		     //Map<partyName,Map<keywordId,countVO>>
		     LinkedHashMap<String,List<PartyActivitiesVO>> partyMap = null;
		     
		     List<PartyActivitiesVO> keywordsList = null;
		   //0 count,1 partyName,2 keywordid,3 keyword,4 locationId,5 locationName
		     List<Object[]> newsCountList = candidatePartyFileDAO.getAllElectionCampanionCount(fromDate, toDate, locationIds, locationType,partyIds,1l);
		     
		     for(Long locationId:locationNames.keySet()){
	    		 partyMap = new LinkedHashMap<String,List<PartyActivitiesVO>>();
	    		 for(String partyName:partyNamesList){
	    			 keywordsList = new ArrayList<PartyActivitiesVO>();
		    		
		    		 PartyActivitiesVO mlaIncharge = new PartyActivitiesVO();
		    		 mlaIncharge.setName("MLA/Incharge");
		    		 mlaIncharge.setCount(0l);
		    		 PartyActivitiesVO mpIncharge = new PartyActivitiesVO();
		    		 mpIncharge.setName("MP/Incharge");
		    		 mpIncharge.setCount(0l);
		    		 keywordsList.add(mlaIncharge);
		    		 keywordsList.add(mpIncharge);
		    		 partyMap.put(partyName,keywordsList);
	    		 }
	    		 locationMap.put(locationId,partyMap);
	    	 
		     }
		     for(Object[] newsCount:newsCountList){
		    	 partyMap = locationMap.get((Long)newsCount[4]);
		    	 keywordsList = partyMap.get(newsCount[1].toString());
		    	 if(keywordsList == null){
		    		 keywordsList = partyMap.get("All Other Parties");
		    	 }
		    	 String keyword = newsCount[3].toString().toLowerCase();
		    	 if(keyword.contains("mla/incharge") || keyword.contains("mla incharge")){
		    		 keywordsList.get(0).setCount((Long)newsCount[0]);
		    	 }else{
		    		 keywordsList.get(1).setCount((Long)newsCount[0]);
		    	 }
		     }
	     }catch(Exception e){
	    	 LOG.error("Exception rised in getElecCampionCount ",e);
	     }
	     return locationMap;
	}
	
	public List<PartyActivitiesVO> getCategoryWiseActivities(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,List<Long> partyIds,List<Long> categoryIds){
		List<PartyActivitiesVO> returnList = new ArrayList<PartyActivitiesVO>();
		Map<Long,Long> problemsCountMap = null;
		Map<Long,Map<String,Long>> elecIssuesCountMap = null;
		LinkedHashMap<Long,LinkedHashMap<String,List<PartyActivitiesVO>>> activitiesMap = null;
		LinkedHashMap<Long,LinkedHashMap<String,List<PartyActivitiesVO>>> elecCampionMap = null;
		List<Long> sortedLocationIds = new ArrayList<Long>();
		PartyActivitiesVO initialVO = new PartyActivitiesVO();
		
		//Map<partyName,Map<keywordId,countVO>>
	     LinkedHashMap<String,List<PartyActivitiesVO>> partyMap = null;
	     
		List<String> partyNamesList = partyDAO.getPartyShortNames(partyIds);
		
		List<Object[]> locationDetails = null;
	     LinkedHashMap<Long,String> locationNames = new LinkedHashMap<Long,String>();
	     if(locationType.longValue() == 1l){
	    	 locationDetails = districtDAO.getDistrictNames(locationIds);
	     }else{
	    	 locationDetails = delimitationConstituencyDAO.findConstituencyiOrder(locationIds);
	     }
	     for(Object[] location:locationDetails){
	    	 locationNames.put((Long)location[0],location[1].toString());
	    	 sortedLocationIds.add((Long)location[0]);
	     }
	     
		if(categoryIds.contains(7l)){
			//getting problem info
			problemsCountMap = getProblemCounts(fromDate,toDate,locationType,locationIds);
			initialVO.setProblemsPresnt("true");
		}
		if(categoryIds.contains(4015l)){
			//getting election issues info
			elecIssuesCountMap = getElectionIssuesCount(fromDate,toDate,locationType,locationIds,partyIds);
			initialVO.setElecIssusPresnt("true");
		}
		if(categoryIds.contains(3991l)){
			//getting activities  info
			activitiesMap = getActivitiesCount(fromDate, toDate, locationType, locationIds, partyIds, locationNames, partyNamesList);
			initialVO.setActitityPresent("true");
		}
		if(categoryIds.contains(1l)){
			//getting election campaign info
			elecCampionMap = getElecCampionCount(fromDate, toDate, locationType, locationIds, partyIds, locationNames, partyNamesList);
			initialVO.setElecCampionPresnt("true");
		}
		
		for(Long locationId:sortedLocationIds){
	    	 PartyActivitiesVO locationVO = new PartyActivitiesVO();
	    	 if(returnList.size() == 0){
	    		 locationVO = initialVO;
	    	 }
	    	 locationVO.setName(locationNames.get(locationId));
	    	 if(initialVO.getProblemsPresnt() != null){
	    		 Long problemCount = problemsCountMap.get(locationId);
	    		 if(problemCount != null){
	    		   locationVO.setCount(problemCount);
	    		 }else{
	    			 locationVO.setCount(0l);
	    		 }
	    	 }
	    	 if(initialVO.getElecIssusPresnt() != null || initialVO.getActitityPresent() != null ||  initialVO.getElecCampionPresnt() != null ){
		    	 List<PartyActivitiesVO> partiesList = new ArrayList<PartyActivitiesVO>();
		    	 locationVO.setActivitiesList(partiesList);
		    	 for(String partyName:partyNamesList){
		    		 PartyActivitiesVO partyVO = new PartyActivitiesVO();
		    		 partiesList.add(partyVO);
		    		 partyVO.setName(partyName);
		    		 if(initialVO.getElecIssusPresnt() != null){
		    			 Map<String,Long> issueCountMap =  elecIssuesCountMap.get(locationId);
		    			 if(issueCountMap != null){
		    				 Long count = issueCountMap.get(partyName);
		    				 if(count != null){
		    					 partyVO.setCount(count);
		    				 }else{
		    					 partyVO.setCount(0l);
		    				 }
		    			 }else{
		    				 partyVO.setCount(0l);
		    			 }
		    		 }
                     if(initialVO.getActitityPresent() != null){
                    	 partyMap = activitiesMap.get(locationId);
                    	 partyVO.setActivitiesList(partyMap.get(partyName));
		    		 }
                     if(initialVO.getElecCampionPresnt() != null){
                    	 partyMap = elecCampionMap.get(locationId);
                    	 partyVO.setElectionCampanion(partyMap.get(partyName));
		    		 }
		    	 }
		    }
	    	 returnList.add(locationVO);
	     }
		return returnList;
	}
	
	public String generateExcelForActivities(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,List<Long> partyIds,List<Long> categoryIds){
		String url="";
		FileOutputStream fileOut = null;
	   try{		
		List<PartyActivitiesVO> activitiesList = getCategoryWiseActivities(fromDate,toDate,locationType,locationIds,partyIds,categoryIds);
		boolean problemsPresent = false;
		boolean campanionPresent = false;
		boolean activityPresent = false;
		boolean elecIssuesPresent = false;
		  if(activitiesList.get(0).getElecCampionPresnt() != null && activitiesList.get(0).getElecCampionPresnt().equalsIgnoreCase("true")){
		    campanionPresent = true;
		  }
		  if(activitiesList.get(0).getActitityPresent() != null && activitiesList.get(0).getActitityPresent().equalsIgnoreCase("true") ){
		    activityPresent = true;
		  }
		  if(activitiesList.get(0).getElecIssusPresnt() != null && activitiesList.get(0).getElecIssusPresnt().equalsIgnoreCase("true") ){
		    elecIssuesPresent = true;
		  }
		  if(activitiesList.get(0).getProblemsPresnt() != null && activitiesList.get(0).getProblemsPresnt().equalsIgnoreCase("true") ){
		    problemsPresent = true;
		  }
		  
		     Random randomNum = new Random();
			 String filename = "Reports"+"/Activities"+"/"+"report"+randomNum.nextInt(1000000000)+".xls";
			 url = filename;
			 String FILE = IWebConstants.STATIC_CONTENT_FOLDER_URL+filename;
			 java.io.File file  = new java.io.File(FILE);
			 fileOut =  new FileOutputStream(FILE);
			 file.createNewFile();
			 HSSFWorkbook workbook=new HSSFWorkbook();
			    HSSFFont font1= workbook.createFont();
			    font1.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
			    font1.setItalic(false);
			    HSSFFont font= workbook.createFont();
			    font.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
			    font.setItalic(false);
			    font.setFontHeight((short)240);
			    HSSFCellStyle style = workbook.createCellStyle();
			    style.setFont(font1);
			    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			    HSSFCellStyle style1 = workbook.createCellStyle();
			    style1.setFont(font1);
			 HSSFSheet sheet =  workbook.createSheet("Report"); 
		    int row = 0;
		   if(problemsPresent && !campanionPresent && !activityPresent && !elecIssuesPresent){
				HSSFRow rowhead=   sheet.createRow((short)row);
			    Cell cell = rowhead.createCell(0);
			    if(locationType.longValue() == 1l){
				   cell.setCellValue("District");
			    }else{
			       cell.setCellValue("Constituency");
			    }
				 cell.setCellStyle(style);
				 cell = rowhead.createCell(1);
				 cell.setCellValue("Public Issues");
				 cell.setCellStyle(style);
				
				 row = row+1;
				 for(PartyActivitiesVO vo: activitiesList){
					 rowhead=   sheet.createRow((short)row);
					 cell = rowhead.createCell(0);
					 cell.setCellValue(vo.getName());
					 cell.setCellStyle(style);
					 cell = rowhead.createCell(1);
					 cell.setCellValue(vo.getCount());
					 cell.setCellStyle(style);	
					 row = row+1;
				 }
				
			}else{		
				    HSSFRow rowhead=   sheet.createRow((short)row);
				    Cell cell = rowhead.createCell(0);
					if(locationType.longValue() == 1l){
						cell.setCellValue("District");
						 cell.setCellStyle(style);
					  if(activityPresent || campanionPresent){
						  sheet.addMergedRegion(new CellRangeAddress(row,row+2,0,0));	
					  }else{
						  sheet.addMergedRegion(new CellRangeAddress(row,row+1,0,0));
					  }
					}else{
						cell.setCellValue("Constituency");
						 cell.setCellStyle(style);	
						if(activityPresent || campanionPresent){
							sheet.addMergedRegion(new CellRangeAddress(row,row+2,0,0));
						
						}else{
							sheet.addMergedRegion(new CellRangeAddress(row,row+1,0,0));
						
						}
					 }
					int colum =  1;
					 if(problemsPresent){
						  cell = rowhead.createCell(colum);
						  
						  cell.setCellValue("Public Issues");
						  cell.setCellStyle(style);
					   if(activityPresent || campanionPresent){
						   sheet.addMergedRegion(new CellRangeAddress(row,row+2,colum,colum));
					   }else{
						   sheet.addMergedRegion(new CellRangeAddress(row,row+1,colum,colum));
					   }
					   colum=colum+1;
					  }
					  int colspanLength = 0;
					  if(campanionPresent){
						 colspanLength = colspanLength+2;
					  }
					  if(activityPresent){
						 colspanLength = colspanLength+3;
					  }
					  if(elecIssuesPresent){
						 colspanLength = colspanLength+1;
					  }
					for(PartyActivitiesVO vo: activitiesList.get(0).getActivitiesList()){
						  cell = rowhead.createCell(colum);
						  
						  cell.setCellValue(vo.getName());
						  cell.setCellStyle(style);
						  if(colspanLength > 0){
						     sheet.addMergedRegion(new CellRangeAddress(row,row,colum,colum+colspanLength-1));
						     colum=colum+colspanLength;
						  }else{
						     colum=colum+1;
						  }
					}
					 row = row+1;
					 rowhead=   sheet.createRow((short)row);
					 if(problemsPresent){
						 colum = 2;
					 }else{
						 colum = 1;
					 }
					  for(int i = 0; i<activitiesList.get(0).getActivitiesList().size();i++){
					   
					   if(activityPresent || campanionPresent){
						
						   if(activityPresent){
							   cell = rowhead.createCell(colum); 
							   cell.setCellValue("Activities");
							   cell.setCellStyle(style);
							   sheet.addMergedRegion(new CellRangeAddress(row,row,colum,colum+2));
							   colum = colum+3;	     
						   }
							if(campanionPresent){
								   cell = rowhead.createCell(colum); 
								   cell.setCellValue("Election Campaign");
								   cell.setCellStyle(style);
								   sheet.addMergedRegion(new CellRangeAddress(row,row,colum,colum+1));
								   colum = colum+2;			     
						   }
						   if(elecIssuesPresent){
							   cell = rowhead.createCell(colum); 
							   cell.setCellValue("Election Issues");
							   cell.setCellStyle(style);
							   sheet.addMergedRegion(new CellRangeAddress(row,row+1,colum,colum));
							   colum = colum+1; 
						   }					 
					   }else{
						   if(elecIssuesPresent){
							   cell = rowhead.createCell(colum); 
							   cell.setCellValue("Election Issues");
							   cell.setCellStyle(style);
							   colum = colum+1;
							
						   }
					   }
					  
					  }
					 if(activityPresent || campanionPresent){
						  row = row+1;
						  rowhead=   sheet.createRow((short)row);
						  if(problemsPresent){
								 colum = 2;
							 }else{
								 colum = 1;
							 }
						  for(int i = 0; i<activitiesList.get(0).getActivitiesList().size();i++){		  
								   if(activityPresent){
									   cell = rowhead.createCell(colum); 
									   cell.setCellValue("Cadre");
									   cell.setCellStyle(style);
									   colum = colum+1;
									   cell = rowhead.createCell(colum); 
									   cell.setCellValue("MLA/Incharge");
									   cell.setCellStyle(style);
									   colum = colum+1;
									   cell = rowhead.createCell(colum); 
									   cell.setCellValue("MP/Incharge");
									   cell.setCellStyle(style);
									   colum = colum+1;		     
								   }
								   if(campanionPresent){
									   cell = rowhead.createCell(colum); 
									   cell.setCellValue("MLA/Incharge");
									   cell.setCellStyle(style);
									   colum = colum+1;
									   cell = rowhead.createCell(colum); 
									   cell.setCellValue("MP/Incharge");
									   cell.setCellStyle(style);
									   colum = colum+1;			     
								   }
								   if(elecIssuesPresent){
									   colum = colum+1;
								   }
						  }
			        }
					for(PartyActivitiesVO locationVO:activitiesList){//itreating locations
						row = row+1;
						colum = 0;
						 rowhead=   sheet.createRow((short)row);
						   cell = rowhead.createCell(colum); 
						   cell.setCellValue(locationVO.getName());
						   colum=colum+1;
						   if(problemsPresent){
							   cell = rowhead.createCell(colum); 
							   cell.setCellValue(locationVO.getCount());
							   colum=colum+1;
						   }
					  for(PartyActivitiesVO partyVO:locationVO.getActivitiesList()){//iterating parties
						if(activityPresent){
							for(PartyActivitiesVO activityVO:partyVO.getActivitiesList()){//iterating activities
								 cell = rowhead.createCell(colum); 
								 cell.setCellValue(activityVO.getCount());
								 colum=colum+1;
							}
						}
						if(campanionPresent){
							for(PartyActivitiesVO elecCampVO:partyVO.getElectionCampanion()){//iterating electionCampanion
								cell = rowhead.createCell(colum); 
								 cell.setCellValue(elecCampVO.getCount());
								 colum=colum+1;
							}
						}
						if(elecIssuesPresent){
							cell = rowhead.createCell(colum); 
							 cell.setCellValue(partyVO.getCount());
							 colum=colum+1;
						  }
					  }

					}
					
			 }
		   workbook.write(fileOut);
			 fileOut.close();
	   }catch(Exception e){
		   LOG.error("Exception rised in generateExcelForActivities ",e);
	   }
	   finally{
			if(fileOut != null){
				 try{
				  fileOut.close();
				 }catch(Exception e1){
						
				 }
			}
		}
	 return url;
	}
}
