package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.dao.INewsReportDAO;
import com.itgrids.partyanalyst.dao.IReportFilesDAO;
import com.itgrids.partyanalyst.dto.FileVO;
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
	
	private static final org.apache.log4j.Logger LOG = Logger.getLogger(ReportService.class);
			
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
		
		Map<Long,FileVO> newsMap = new HashMap<Long,FileVO>();
		FileVO file = null;
		if(stateLvlNews != null && stateLvlNews.size() > 0){
			List<FileVO> stateLvlList = new ArrayList<FileVO>();
			for(Object[] news:stateLvlNews){
				
				file = new FileVO();
				newsMap.put((Long)news[0], file);
				file.setFileId((Long)news[0]);
				file.setTitle(news[1] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[1].toString())):"");
				file.setDescription(news[2] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[2].toString())):"");
				file.setNewsDescription(news[3] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[3].toString())):"");
				file.setLocationId((Long)news[4]);
				file.setLocationVal((Long)news[5]);
				file.setScope("STATE");
				file.setLocationName("Andhra Pradesh");
				
				if(news[6] != null)
				  file.setEenadu(true);
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
				file.setTitle(news[1] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[1].toString())):"");
				file.setDescription(news[2] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[2].toString())):"");
				file.setNewsDescription(news[3] != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(news[3].toString())):"");
				file.setLocationId((Long)news[4]);
				file.setLocationVal((Long)news[5]);
				file.setName(news[7].toString());
				
				file.setScope(news[8] != null?news[8].toString():"");
				if(news[9] != null)
					  file.setEenadu(true);
				file.setLocationName(candidateDetailsService.getLocationDetails((Long)news[4],(Long)news[5]));
				
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
				 if(map2 != null)
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
					  source += " (Main Edition: "+mainEditionStr.substring(0,mainEditionStr.length()-1)+")";
					if(districtStr.length() > 0)
						source += "(District Edition: "+districtStr.substring(0,districtStr.length()-1)+")";
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
}
