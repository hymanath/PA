package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyKeywordDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IKeywordDAO;
import com.itgrids.partyanalyst.dto.PartyActivitiesVO;
import com.itgrids.partyanalyst.model.CandidatePartyFile;
import com.itgrids.partyanalyst.model.CandidatePartyKeyword;
import com.itgrids.partyanalyst.service.IPartyActivitiesService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class PartyActivitiesService implements IPartyActivitiesService {
	private static final Logger LOG = Logger.getLogger(PartyActivitiesService.class);
	
	private ICandidatePartyFileDAO candidatePartyFileDAO;
	private ICandidatePartyKeywordDAO candidatePartyKeywordDAO;
	private IKeywordDAO keywordDAO;
	private IDistrictDAO districtDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	
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
	
	public List<PartyActivitiesVO> getActivitiesStatus(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,List<Long> partyIds){
		List<PartyActivitiesVO> returnList = new ArrayList<PartyActivitiesVO>();
		try{
			//0 count,1 partyName,2 keywordid,3 keyword,4 locationId,5 locationName
		     List<Object[]> newsCountList = candidatePartyFileDAO.getAllPoliticalActivitiesCount(fromDate, toDate, locationIds, locationType,partyIds);
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
	
}
