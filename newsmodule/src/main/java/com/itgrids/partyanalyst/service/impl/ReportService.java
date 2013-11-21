package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

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
	
	private static final org.apache.log4j.Logger LOG = Logger.getLogger(ReportService.class);
			
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
		List<Object[]> sourceDetails = fileSourceLanguageDAO.getSourceDetailsByFileIds(newsMap.keySet());
		for(Object[] source:sourceDetails){
			FileVO vo = newsMap.get((Long)source[0]);
			if(vo != null){
				if(vo.getSource() != null && vo.getSource().trim().length() > 0){
					vo.setSource(vo.getSource()+","+source[1].toString());
				}else{
					vo.setSource(source[1].toString());
				}
			   if("Eenadu Telugu".equalsIgnoreCase(vo.getSource()))
				  vo.setNames("true");
			}
		}
	 }catch(Exception e){
		 LOG.error("Exception rised in getReportData ",e);
	 }
		return returnVo;
	}
}
