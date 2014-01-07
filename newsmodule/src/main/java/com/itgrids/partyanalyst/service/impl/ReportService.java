package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityReportDAO;
import com.itgrids.partyanalyst.dao.IActivityReportFilesDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INewsReportDAO;
import com.itgrids.partyanalyst.dao.IReportFilesDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsActivityVO;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IReportService;
import com.itgrids.partyanalyst.utils.CommonStringUtils;

public class ReportService implements IReportService {
	
	private IReportFilesDAO reportFilesDAO;
	private ICandidateDetailsService candidateDetailsService;
	private IFileSourceLanguageDAO fileSourceLanguageDAO;
	private INewsReportDAO newsReportDAO;
	private ICandidatePartyFileDAO candidatePartyFileDAO;
	private IFilePathsDAO filePathsDAO;
	private IActivityReportFilesDAO activityReportFilesDAO;
	private IActivityReportDAO  activityReportDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO; 
	private ITehsilDAO tehsilDAO;  
	private IHamletDAO hamletDAO;  
	private ILocalElectionBodyDAO localElectionBodyDAO;  
	private IBoothDAO boothDAO; 
	
	private IUserAddressDAO userAddressDAO;
	
	private static final org.apache.log4j.Logger LOG = Logger.getLogger(ReportService.class);
			
	
	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	/**
	 * @return the activityReportDAO
	 */
	public IActivityReportDAO getActivityReportDAO() {
		return activityReportDAO;
	}

	/**
	 * @param activityReportDAO the activityReportDAO to set
	 */
	public void setActivityReportDAO(IActivityReportDAO activityReportDAO) {
		this.activityReportDAO = activityReportDAO;
	}

	public IFilePathsDAO getFilePathsDAO() {
		return filePathsDAO;
	}

	public void setFilePathsDAO(IFilePathsDAO filePathsDAO) {
		this.filePathsDAO = filePathsDAO;
	}

	public ICandidatePartyFileDAO getCandidatePartyFileDAO() {
		return candidatePartyFileDAO;
	}

	public void setCandidatePartyFileDAO(
			ICandidatePartyFileDAO candidatePartyFileDAO) {
		this.candidatePartyFileDAO = candidatePartyFileDAO;
	}

	public IReportFilesDAO getReportFilesDAO() {
		return reportFilesDAO;
	}

	public void setReportFilesDAO(IReportFilesDAO reportFilesDAO) {
		this.reportFilesDAO = reportFilesDAO;
	}
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public IFileSourceLanguageDAO getFileSourceLanguageDAO() {
		return fileSourceLanguageDAO;
	}

	public void setFileSourceLanguageDAO(
			IFileSourceLanguageDAO fileSourceLanguageDAO) {
		this.fileSourceLanguageDAO = fileSourceLanguageDAO;
	}

	public INewsReportDAO getNewsReportDAO() {
		return newsReportDAO;
	}

	public void setNewsReportDAO(INewsReportDAO newsReportDAO) {
		this.newsReportDAO = newsReportDAO;
	}

	public IActivityReportFilesDAO getActivityReportFilesDAO() {
		return activityReportFilesDAO;
	}

	public void setActivityReportFilesDAO(
			IActivityReportFilesDAO activityReportFilesDAO) {
		this.activityReportFilesDAO = activityReportFilesDAO;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
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


	public FileVO getReportData(Long reportId,Long userId,String key){
		FileVO returnVo = new FileVO();
	 try{
		 List<Object[]> stateLvlNews = null;
		 List<Object[]> districtLvlNews = null;
		 if(key == null || key.trim().length() == 0){
		   Long count = newsReportDAO.checkValidUserForReport(userId, reportId);
		   if(count > 0){
			   stateLvlNews = reportFilesDAO.getStateLvlReportDetails(reportId, userId);
			   districtLvlNews = reportFilesDAO.getOtherLvlReportDetails(reportId, userId);
		   }else{
			   returnVo.setName("Invalid User");
		   }
		 }else{
			 Long count = newsReportDAO.checkValidReportKey(key);
			 if(count > 0){
			   stateLvlNews = reportFilesDAO.getStateLvlReportDetailsByKey(reportId, key);
			   districtLvlNews = reportFilesDAO.getOtherLvlReportDetailsByKey(reportId,key);
			   newsReportDAO.updateKey(key);
			 }else{
				 returnVo.setName("Invalid Key");
				 return returnVo;
			 }
		 }
		if((stateLvlNews == null || stateLvlNews.size() == 0) && (districtLvlNews == null || districtLvlNews.size() == 0)){
			returnVo.setName("All Newses Deleted");
			 return returnVo;
		}
		Map<Long,FileVO> newsMap = new HashMap<Long,FileVO>();
		FileVO file = null;
		if(stateLvlNews != null && stateLvlNews.size() > 0){
			List<FileVO> stateLvlList = new ArrayList<FileVO>();
			for(Object[] news:stateLvlNews){
				
				file = new FileVO();
				newsMap.put((Long)news[0], file);
				file.setFileId((Long)news[0]);
				Date date= null;
				    String newDate =null;
			        if(news[8] != null)
				    {
				      date= (Date)news[8];
				      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                      newDate = formatter.format(date);
				    }
			    file.setFileDate(newDate != null ? newDate : "");
				file.setTitle(news[1] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[1].toString())):"");
				file.setDescription(news[2] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[2].toString())):"");
				file.setNewsDescription(news[3] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[3].toString())):"");
				file.setLocationId((Long)news[4]);
				file.setLocationVal((Long)news[5]);
				file.setScope("STATE");
				file.setLocationName("Andhra Pradesh");
				
				if(news[6] != null)
				  file.setEenadu(true);
				if(news[7] != null)
					  file.setDescEenadu(true);
				stateLvlList.add(file);
			}
			returnVo.setMainArticalsList(stateLvlList);//setting state level news
		}
		
		if(districtLvlNews != null && districtLvlNews.size() > 0){
			LinkedHashMap<Long,FileVO> distLvlList = new LinkedHashMap<Long,FileVO>();
			for(Object[] news:districtLvlNews){
				FileVO vo = distLvlList.get((Long)news[6]);
				if(vo == null){
					vo = new FileVO();
					vo.setLocationName(news[7].toString());
					vo.setLocationId((Long)news[6]);
					vo.setFileVOList(new ArrayList<FileVO>());
					distLvlList.put((Long)news[6], vo);
				}
				file = new FileVO();
				newsMap.put((Long)news[0], file);
				file.setFileId((Long)news[0]);
				Date date= null;
			    String newDate =null;
		        if(news[11] != null)
			    {
			      date= (Date)news[11];
			      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                  newDate = formatter.format(date);
			    }
		    file.setFileDate(newDate != null ? newDate : "");
				file.setTitle(news[1] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[1].toString())):"");
				file.setDescription(news[2] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[2].toString())):"");
				file.setNewsDescription(news[3] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[3].toString())):"");
				file.setLocationId((Long)news[4]);
				file.setLocationVal((Long)news[5]);
				file.setName(news[7].toString());
				file.setScope(news[8] != null?news[8].toString():"");
				if(news[9] != null)
					  file.setEenadu(true);
				if(news[10] != null)
					  file.setDescEenadu(true);
				file.setLocationName(getLocationDetails1((Long)news[4],(Long)news[5],(Long)news[12]));
				
				vo.getFileVOList().add(file);
			}
			returnVo.setFileVOList(new ArrayList<FileVO>(distLvlList.values()));//setting all news in all districts
		}
		
		List<Object[]> SourceIds = fileSourceLanguageDAO.getFileSourceByFileIds(newsMap.keySet());
		
		Map<Long,String> sourceNameMap = new HashMap<Long, String>(0);
		Map<Long,Map<Long,Map<Long,String>>> resultMap = new HashMap<Long, Map<Long,Map<Long,String>>>(0);//<fileIs,sourceId,editionId,pageNo>
		if(SourceIds != null && SourceIds.size() > 0)
		 for(Object[] params: SourceIds)
		 {
			Map<Long,Map<Long,String>> map = resultMap.get((Long)params[0]); 
			if(map == null)
			{
				map = new HashMap<Long, Map<Long,String>>(0);
				resultMap.put((Long)params[0], map);
			}
			Map<Long,String> map2 = map.get((Long)params[1]);
			if(map2 == null)
			{
				map2 = new HashMap<Long, String>(0);
				map.put((Long)params[1], map2);
			}
			
			String name = sourceNameMap.get((Long)params[1]);
			  if(name == null)
				sourceNameMap.put((Long)params[1], params[2].toString());
			  
			  
		 }
		
		List<Object[]> list = filePathsDAO.getSourceIdsAndPageNos(newsMap.keySet());
		if(list != null && list.size() > 0)
		{
		 for(Object[] params:list)
		 {
			 Map<Long,Map<Long,String>> map = resultMap.get((Long)params[0]); 
			 if(map != null && map.size() > 0)
			 {
				 Map<Long,String> map2 = map.get((Long)params[1]);
				 if(map2 != null && map2.size() > 0)
				 {
					 String pageNo = map2.get(new Long((Integer)params[2]));
					 if(pageNo == null)
						 map2.put(new Long((Integer)params[2]), params[3].toString());
					 else
					 {
						 pageNo +=","+params[3].toString();
						 map2.put(new Long((Integer)params[2]), pageNo);
					 }
				 }
			 }
		 }
		}
		
		Map<Long,List<String>> fileSourceMap = new HashMap<Long, List<String>>(0);
		
		if(resultMap != null && resultMap.size() > 0)
		{
			for(Long fileId: resultMap.keySet())
			{
			  Map<Long,Map<Long,String>> map = resultMap.get(fileId);
			  if(map != null && map.size() > 0)
			  {
				
				for(Long sourceId :map.keySet())
				{
					String source = "";
					String mainEditionStr = "";
					String districtStr = "";
				  source += sourceNameMap.get(sourceId);
				  Map<Long,String> map2 = map.get(sourceId);
				  
				  if(map2 != null)
				  {
					for(Long edition: map2.keySet())
					{
					 if(edition.equals(1L))
						mainEditionStr += map2.get(edition)+",";
					 else
						 districtStr += map2.get(edition)+",";
					}
					if(mainEditionStr.length() > 0)
					  source += " (Main Edition: PAGE NO "+mainEditionStr.substring(0,mainEditionStr.length()-1)+")";
					if(districtStr.length() > 0)
						source += "(District Edition: PAGE NO "+districtStr.substring(0,districtStr.length()-1)+")";
				  }
				  
				  List<String> sourceList = fileSourceMap.get(fileId);
				  if(sourceList == null)
				  {
					  sourceList = new ArrayList<String>(0);
					  fileSourceMap.put(fileId, sourceList);
				  }
				  
				  sourceList.add(source);
				  fileSourceMap.put(fileId, sourceList);
				  
				}
			  }
			}
		}
		
		List<Object[]> candidateDetails = candidatePartyFileDAO.getCandidateNamesByFileIds(newsMap.keySet());
		String name="";
		Map<Long,String> candidateNames = new HashMap<Long, String>();
		for(Object[] val:candidateDetails){
			if(candidateNames.containsKey(val[0]))
				name += val[1].toString()+", ";
			else
				name = val[1].toString()+", ";
			candidateNames.put((Long)val[0],name);
		}
		
		if(districtLvlNews != null && districtLvlNews.size() > 0)
		{
			if(returnVo.getFileVOList() != null && returnVo.getFileVOList().size() > 0)
				for(FileVO fileVo:returnVo.getFileVOList())
				{
					if(fileVo.getFileVOList() != null && fileVo.getFileVOList().size() > 0)
						for(FileVO fileVo2:fileVo.getFileVOList())
						{
							List<String> keyWordsList = fileSourceMap.get(fileVo2.getFileId());
							fileVo2.setKeyWordsList(keyWordsList);
							//for candidateNames
							if(name != null && name.length() > 0 && candidateNames.get(fileVo2.getFileId()) != null)
								fileVo2.setCandidateName((candidateNames.get(fileVo2.getFileId())).substring(0,(candidateNames.get(fileVo2.getFileId())).length()-2));
							else
								fileVo2.setCandidateName("");
						}
				}
		}
		
		if(stateLvlNews != null && stateLvlNews.size() > 0)
		{
			if(returnVo.getMainArticalsList()!=null && returnVo.getMainArticalsList().size()>0)
				for(FileVO fileVO :returnVo.getMainArticalsList())
				{
					List<String> keyWordsList = fileSourceMap.get(fileVO.getFileId());
					fileVO.setKeyWordsList(keyWordsList);
					//for candidateNames
					if(name != null && name.length() > 0 && candidateNames.get(fileVO.getFileId()) != null)
						fileVO.setCandidateName((candidateNames.get(fileVO.getFileId())).substring(0,(candidateNames.get(fileVO.getFileId())).length()-2));
					else
						fileVO.setCandidateName("");
				}
		}
		
	 }catch(Exception e){
		 LOG.error("Exception rised in getReportData ",e);
	 }
		return returnVo;
	}
	
	public NewsActivityVO getActivitiesReportData(String key){
		NewsActivityVO returnVO = new NewsActivityVO();
		Map<Long,String> districtNames = new HashMap<Long,String>();
		Map<Long,String> categoryNames = new HashMap<Long,String>();
		Map<Long,String> constituencyNames = new HashMap<Long,String>();
		List<NewsActivityVO> categoryList = new ArrayList<NewsActivityVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		returnVO.setList(categoryList);
		// 0title 1fontId 2fileDate 3districtid 4distname 5constiId 6constiname 7categoryId 8catgoryName
		LinkedHashMap<Long,LinkedHashMap<Long,LinkedHashMap<Long,List<NewsActivityVO>>>> newsMap = new LinkedHashMap<Long,LinkedHashMap<Long,LinkedHashMap<Long,List<NewsActivityVO>>>>();//<categoryId,Map<distrctId,Map<constituencyId,news>>>
		LinkedHashMap<Long,LinkedHashMap<Long,List<NewsActivityVO>>> districtyMap = null;
		LinkedHashMap<Long,List<NewsActivityVO>> constituencyMap = null;
		List<NewsActivityVO> newsList = null;
		NewsActivityVO news = null;
		String categories = activityReportDAO.getCategoeryIds(key);
		String[] categoriesArry = categories.split(",");
		List<Long> cateList = new ArrayList<Long>();
		for (String catg : categoriesArry) {
			cateList.add(Long.valueOf(catg));
		}
		List<Object[]> activitiesList = activityReportFilesDAO.getActivitiesList(key,cateList);
		for(Object[] activity:activitiesList){
			districtyMap = newsMap.get((Long)activity[7]);
			if(districtyMap == null){
				districtyMap = new LinkedHashMap<Long,LinkedHashMap<Long,List<NewsActivityVO>>>();
				newsMap.put((Long)activity[7], districtyMap);
				categoryNames.put((Long)activity[7],activity[8]!=null?activity[8].toString():"");
			}
			 constituencyMap = districtyMap.get((Long)activity[3]);
			 if(constituencyMap == null){
			      constituencyMap = new LinkedHashMap<Long,List<NewsActivityVO>>();
			      districtyMap.put((Long)activity[3], constituencyMap);
			      districtNames.put((Long)activity[3], activity[4]!=null?activity[4].toString():"");
			 }
			 newsList = constituencyMap.get((Long)activity[5]);
			 if(newsList == null){
				 newsList = new ArrayList<NewsActivityVO>();
				 constituencyMap.put((Long)activity[5],newsList);
				 constituencyNames.put((Long)activity[5], activity[6]!=null?activity[6].toString():"");
			 }
			 NewsActivityVO constituency = new NewsActivityVO();
			 newsList.add(constituency);
			 constituency.setName(activity[6]!=null?activity[6].toString():"");
			 constituency.setDescription(StringEscapeUtils.unescapeJava(activity[0].toString()));
			 if(activity[1] != null){
				 constituency.setFont("eenadu");
			 }
			 constituency.setDate(activity[2] != null ? sdf.format((Date)activity[2]):"");
		}
		for(Long categoryId:newsMap.keySet()){
			NewsActivityVO category = new NewsActivityVO();
			List<NewsActivityVO> districtList = new ArrayList<NewsActivityVO>();
			category.setList(districtList);
			categoryList.add(category);
			category.setName(categoryNames.get(categoryId));
			districtyMap = newsMap.get(categoryId);
			for(Long districtId:districtyMap.keySet()){
				NewsActivityVO district = new NewsActivityVO();
				district.setName(districtNames.get(districtId));
				List<NewsActivityVO> constituencyList = new ArrayList<NewsActivityVO>();
				district.setList(constituencyList);
				districtList.add(district);
				constituencyMap = districtyMap.get(districtId);
				for(Long constituencyId:constituencyMap.keySet()){
					NewsActivityVO constituency = new NewsActivityVO();
					constituency.setName(constituencyNames.get(constituencyId));
					constituencyList.add(constituency);
					constituency.setList(constituencyMap.get(constituencyId));
				}
			}
		}
		return returnVO;
	}
	public String getLocationDetails1(Long scope,Long locationValue,Long id){
	  try{
		UserAddress userAddress = null;
		String tehsil = null;
    	String hamletName =  null;
    	String constituency =null;
    	String ward=null;
		String resultStr ="";
		String local= null;
		String booth = null;
	 if(scope != null)
	 { 
	   if(scope == 1L)
	    {
	    	//return countryDAO.get(1L).getCountryName();
	    }
	    else if(scope == 2L && locationValue != null && locationValue > 0)
	    {
	    	return stateDAO.get(locationValue).getStateName();
	    }
	    else if(scope == 3L && locationValue != null && locationValue > 0)
	    {
	    	return districtDAO.get(locationValue).getDistrictName();
	    }
	    else if(scope == 4L && locationValue != null && locationValue > 0)
	    {
	    	return constituencyDAO.get(locationValue).getName();
	    }
	    else if(scope == 5L && locationValue != null && locationValue > 0)
	    {
	    	userAddress = userAddressDAO.get(id);
	    	constituency = userAddress.getConstituency().getName();
	    	tehsil = userAddress.getTehsil().getTehsilName();
	    	resultStr ="Constituency: "+constituency+", Mandal: "+tehsil;
	    	return resultStr;
	    }
	    else if(scope == 6L && locationValue != null && locationValue > 0)	    	
	    {
	    	userAddress = userAddressDAO.get(id);
	    	tehsil = userAddress.getTehsil().getTehsilName();
	    	hamletName =  userAddress.getHamlet().getHamletName();
	    	constituency = userAddress.getConstituency().getName();
	    	resultStr ="Constituency: "+constituency+", Mandal: "+tehsil+", Village:"+hamletName;
	    	return resultStr;
	    	
	    }
	    else if(scope == 7L && locationValue != null && locationValue > 0)
	    {
	    	userAddress = userAddressDAO.get(id); 	
	    	constituency = userAddress.getConstituency().getName();
	    	 local=localElectionBodyDAO.get(locationValue).getName();
	    	 resultStr ="Constituency: "+constituency+", MUNICIPAL-CORP-GMC: "+local;
		    return resultStr;
	    }
	    else if(scope == 8L && locationValue != null && locationValue > 0)
	    {
	    	userAddress = userAddressDAO.get(id); 	
	    	constituency = userAddress.getConstituency().getName();
	    	local=userAddress.getLocalElectionBody().getName();
	        ward=  constituencyDAO.get(locationValue).getName();
	    	resultStr ="Constituency: "+constituency+", MUNICIPAL-CORP-GMC: "+local+", Ward: "+ward;
	    	return resultStr;
	    	
	    }
	    else if(scope == 9L && locationValue != null && locationValue > 0)
	    {
	    	userAddress = userAddressDAO.get(id); 	
	    	booth= userAddress.getBooth().getPartNo();
	    	resultStr ="Booth No "+booth;
	    	return resultStr;
	    }
	    else  return " ";
	 }
	    return " ";
	}catch(Exception e){
		LOG.error("Exception rised in getLocationDetails1 ",e);
		return "";
	}
  }
	
}
