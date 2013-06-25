package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidateNewsResponseDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.INewsDetailsDAO;
import com.itgrids.partyanalyst.dao.INewsFlagDAO;
import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.dto.ContentDetailsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IContentManagementService;
import com.itgrids.partyanalyst.utils.CommonStringUtils;
import com.itgrids.partyanalyst.utils.IConstants;

public class ContentManagementService implements IContentManagementService{
	
	private static final Logger log = Logger.getLogger(ContentManagementService.class);
	private IFileGallaryDAO fileGallaryDAO;
	private ISpecialPageGalleryDAO specialPageGalleryDAO;
	private IGallaryDAO gallaryDAO;
	private IPartyGalleryDAO partyGalleryDAO;
	private INewsFlagDAO newsFlagDAO;
	private ICandidateDetailsService candidateDetailsService;
	private ICandidateNewsResponseDAO candidateNewsResponseDAO;
	private ICandidateDAO candidateDAO;
	private INewsDetailsDAO newsDetailsDAO;
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public INewsFlagDAO getNewsFlagDAO() {
		return newsFlagDAO;
	}

	public void setNewsFlagDAO(INewsFlagDAO newsFlagDAO) {
		this.newsFlagDAO = newsFlagDAO;
	}

	public IGallaryDAO getGallaryDAO() {
		return gallaryDAO;
	}

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}

	public IPartyGalleryDAO getPartyGalleryDAO() {
		return partyGalleryDAO;
	}

	public void setPartyGalleryDAO(IPartyGalleryDAO partyGalleryDAO) {
		this.partyGalleryDAO = partyGalleryDAO;
	}

	public ISpecialPageGalleryDAO getSpecialPageGalleryDAO() {
		return specialPageGalleryDAO;
	}

	public void setSpecialPageGalleryDAO(
			ISpecialPageGalleryDAO specialPageGalleryDAO) {
		this.specialPageGalleryDAO = specialPageGalleryDAO;
	}

	public IFileGallaryDAO getFileGallaryDAO() {
		return fileGallaryDAO;
	}

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}

	public ICandidateNewsResponseDAO getCandidateNewsResponseDAO() {
		return candidateNewsResponseDAO;
	}

	public void setCandidateNewsResponseDAO(
			ICandidateNewsResponseDAO candidateNewsResponseDAO) {
		this.candidateNewsResponseDAO = candidateNewsResponseDAO;
	}

	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public INewsDetailsDAO getNewsDetailsDAO() {
		return newsDetailsDAO;
	}

	public void setNewsDetailsDAO(INewsDetailsDAO newsDetailsDAO) {
		this.newsDetailsDAO = newsDetailsDAO;
	}

	/**
	 * This Method will give the Selected File Details, their related files and Other Galleries Details
	 * @param contentId Long
	 * @param requestFrom String
	 * @param requestPageId Long
	 * @return {@link ContentDetailsVO}
	 * @author Kamalakar Dandu
	 */
	public ContentDetailsVO getSelectedContentAndRelatedGalleries(
			Long contentId, String requestFrom, Long requestPageId,
			String isCustomer)	{
		log.debug("Entered into getSelectedContentAndRelatedGalleries() Method");
		try{
			ContentDetailsVO contentDetailsVO = new ContentDetailsVO();
			Boolean contentReq = true;
			Long fileId = null;
			List<Long> gallaryIds = null;
			String contentType = null;
			Long falseContentIdForPhotoGal = 1l;
			Long tempPartNo = 1L;
			
			fileId = (Long)fileGallaryDAO.getFileIdByFileGallaryId(contentId);
			
			List<Object> list = fileGallaryDAO.getGalleryIdsOfAFile(fileId);
			
			if(list != null && list.size() > 0)
			{
				gallaryIds = new ArrayList<Long>(0);
				for(Object obj : list)
					gallaryIds.add((Long)obj);
				
				List<FileGallary> files = new ArrayList<FileGallary>(0);
				files.add(fileGallaryDAO.get(contentId));
				
				List<FileGallary> filesObjList = new ArrayList<FileGallary>();
				
				if(isCustomer.equalsIgnoreCase("true")){
					filesObjList = fileGallaryDAO.getFilesOfInGallariesForCustomer(gallaryIds);
					requestPageId = fileGallaryDAO.get(contentId).getGallary().getCandidate().getCandidateId();
				}
				
				else
				 filesObjList = fileGallaryDAO.getFilesOfInGallaries(gallaryIds);
				
				for(FileGallary fileGallary : filesObjList)
					if(!checkForFileExistance(files,fileGallary))
						files.add(fileGallary);
				
				Map<Long,Long> flagCount = new HashMap<Long, Long>();
				List<Long> contentIds = new ArrayList<Long>();
				for(FileGallary fileGallary:files)					
					contentIds.add(fileGallary.getFileGallaryId());
				
				List<Object[]> flagCountList = newsFlagDAO.getCountForFlagByFileGallaryId(contentIds);
				
				
				for(Object[] count:flagCountList)					
					flagCount.put((Long)count[0],(Long)count[1]);
				
					
				
				List<GallaryVO> relatedGalleries = new ArrayList<GallaryVO>(0);
				List<GallaryVO> otherGalleries = new ArrayList<GallaryVO>(0);
				GallaryVO relatedGallary = new GallaryVO();
				FileVO fileVO = null;
				List<FileVO> filesList = new ArrayList<FileVO>(0);
				for(FileGallary fileGallary : files)
				{
					fileVO = new FileVO();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					
					if(fileGallary.getFile() == null)
						continue;
					if(contentId.longValue() == fileGallary.getFileGallaryId().longValue())
					{
						fileVO.setIsSelectedContent(true);
						contentType = fileGallary.getGallary().getContentType().getContentType();
						contentDetailsVO.setContentType(contentType);
					}
					if(contentType !=null && contentType.equalsIgnoreCase(IConstants.PHOTO_GALLARY))
					{
						Set<FileSourceLanguage> fileSourceLanguageSet = fileGallary.getFile().getFileSourceLanguage();
						 List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
						Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
						for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList){
							 
							 Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
							 List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
							  Collections.sort(filePathsList,CandidateDetailsService.filePathsSort);
							 
							 for(FilePaths filePath : filePathsList){
								 fileVO = new FileVO();
								 List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>();
								 FileVO fileVOSourceLanguage = new FileVO();
								 fileVOSourceLanguage.setFileSourceLanguageId(fileSourceLanguage.getFileSourceLanguageId());
								 fileVOSourceLanguage.setMultipleNews(1);
								 List<FileVO> fileVOPathsList = new ArrayList<FileVO>();
								 if(contentReq && contentId.longValue() == fileGallary.getFileGallaryId().longValue())
									{
										fileVO.setIsSelectedContent(true);
										contentReq = false;
									}
								 
								   
								    fileVO.setTitle(fileGallary.getFile().getFileTitle() !=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileTitle())):"");
									fileVO.setDescription(fileGallary.getFile().getFileDescription()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileDescription())):"");
									fileVO.setContentType(fileGallary.getGallary().getContentType().getContentType());
									fileVO.setContentId(falseContentIdForPhotoGal);
									falseContentIdForPhotoGal = falseContentIdForPhotoGal+1L;
							
									/*List<Object[]> editionDets = newsDetailsDAO.getEditionAndPageNoByFileSourceId(fileSourceLanguage.getFileSourceLanguageId());		
									if(editionDets != null && editionDets.size() > 0)
									{
									  fileVO.setPageNo((Long)editionDets.get(0)[0]);
									  Long edition = (Long)editionDets.get(0)[1];
									  if(edition.equals(1L))
										  fileVO.setNewsEdition("Main Edition");
									  else
										 fileVO.setNewsEdition("District/Sub Edition");
									}*/
								 
								 FileVO fileVOPath = new FileVO();
								 fileVOPath.setPath(filePath.getFilePath());
								 fileVOPath.setOrderNo(filePath.getOrderNo());
								 fileVOPath.setOrderName("Part-"+filePath.getOrderNo());
								 fileVOPathsList.add(fileVOPath);
								 fileVOSourceLanguage.setFileVOList(fileVOPathsList);
								 fileVOSourceLanguageList.add(fileVOSourceLanguage);
								 fileVO.setMultipleSource(fileVOSourceLanguageList.size());
								 fileVO.setFileVOList(fileVOSourceLanguageList);
								 fileVO.setFileDate(fileGallary.getFile().getFileDate() == null ? null :
										sdf.format(fileGallary.getFile().getFileDate()));
								 fileVO.setReqFileDate(fileGallary.getFile().getFileDate());
									filesList.add(fileVO);
							 }
						}
					}
					else
					{
					if(flagCount.get(fileGallary.getFileGallaryId())!= null)						
						fileVO.setFlagSet("true");
					else
						fileVO.setFlagSet("false");
					
					if(fileGallary.getIsPrivate().equalsIgnoreCase("false"))
						fileVO.setVisibility("public");
					else
						fileVO.setVisibility("private");
						
					fileVO.setFileId(fileGallary.getFile().getFileId());
					
					if(fileGallary.getFile().getCategory() != null){
						fileVO.setCategoryType(fileGallary.getFile().getCategory().getCategoryType());
						fileVO.setCandidateId(fileGallary.getFile().getCategory().getCategoryId());
					}
					fileVO.setComments(fileGallary.getFile().getComment());
					
					if(fileGallary.getFile().getRegionScopes() != null)
					  fileVO.setLocationScopeValue(fileGallary.getFile().getRegionScopes().getScope());
					
					fileVO.setTitle(fileGallary.getFile().getFileTitle()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileTitle())):"");
					fileVO.setDescription(fileGallary.getFile().getFileDescription()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileDescription())):"");
					fileVO.setContentType(fileGallary.getGallary().getContentType().getContentType());
					fileVO.setContentId(fileGallary.getFileGallaryId());
					fileVO.setNewsDescription(fileGallary.getFile().getNewsDescription() !=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getNewsDescription())):"");

					
					List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>();
					Set<FileSourceLanguage> fileSourceLanguageSet = fileGallary.getFile().getFileSourceLanguage();
					
					for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
						FileVO fileVOSourceLanguage = new FileVO();
						 fileVOSourceLanguage.setSource(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSource():null);
						 fileVOSourceLanguage.setSourceId(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSourceId():null);
						 fileVOSourceLanguage.setLanguage(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():null);
						 fileVOSourceLanguage.setLanguegeId(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguageId():null);
						 fileVOSourceLanguage.setFileSourceLanguageId(fileSourceLanguage.getFileSourceLanguageId());
						 
						 List<Object[]> editionDets = newsDetailsDAO.getEditionAndPageNoByFileSourceId(fileSourceLanguage.getFileSourceLanguageId());		
							if(editionDets != null && editionDets.size() > 0)
							{
							 
							fileVOSourceLanguage.setPageNo(Long.parseLong(editionDets.get(0)[0].toString()));
							  Long edition = Long.parseLong(editionDets.get(0)[1].toString());
							  if(edition.equals(1L))
								  fileVOSourceLanguage.setNewsEdition("Main Edition");
							  else
								  fileVOSourceLanguage.setNewsEdition("District/Sub Edition");
							}
						 
						 
						 List<FileVO> fileVOPathsList = new ArrayList<FileVO>();
						 
						 Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
						 fileVOSourceLanguage.setMultipleNews(filePathsSet.size());
						 
						 for(FilePaths filePath : filePathsSet){
							 FileVO fileVOPath = new FileVO();
							 fileVOPath.setPath(filePath.getFilePath());
							 fileVOPath.setOrderNo(filePath.getOrderNo());
							 //fileVOPath.setOrderName("Part-"+filePath.getOrderNo());
							 fileVOPath.setOrderName("Part-"+tempPartNo);
							 tempPartNo = tempPartNo+1;
							 fileVOPathsList.add(fileVOPath);
						 }
						 Collections.sort(fileVOPathsList,CandidateDetailsService.sortData);
						 fileVOSourceLanguage.setFileVOList(fileVOPathsList);
						 fileVOSourceLanguageList.add(fileVOSourceLanguage);
					 }
					 fileVO.setMultipleSource(fileVOSourceLanguageList.size());
					 Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
					 fileVO.setFileVOList(fileVOSourceLanguageList);
					 
					
					fileVO.setFileDate(fileGallary.getFile().getFileDate() == null ? null :
						sdf.format(fileGallary.getFile().getFileDate()));
					 fileVO.setReqFileDate(fileGallary.getFile().getFileDate());
					filesList.add(fileVO);
				  }
				}
				Collections.sort(filesList,date_comparator);				
				relatedGallary.setFilesList(filesList);
				relatedGalleries.add(relatedGallary);
				contentDetailsVO.setRelatedGalleries(relatedGalleries);
				
				List<Object> otherGalIdsResult = null;
				GallaryVO otherGallary = null;
				List<FileVO> otherFiles = null;
				
				if(requestFrom.equalsIgnoreCase(IConstants.SPECIAL_PAGE))
				{
					otherGalIdsResult = specialPageGalleryDAO.getOtherGalleries(requestPageId,gallaryIds,contentType);
				}
				else if(requestFrom.equalsIgnoreCase(IConstants.CANDIDATE_PAGE))
				{
					if(!isCustomer.equalsIgnoreCase("true"))
					 otherGalIdsResult = gallaryDAO.getOtherGalleries(requestPageId,gallaryIds,contentType);
					else
					 otherGalIdsResult = gallaryDAO.getOtherGalleriesForCandidate(requestPageId,gallaryIds,contentType);
						
				}
				else if(requestFrom.equalsIgnoreCase(IConstants.PARTY_PAGE))
				{
					otherGalIdsResult = partyGalleryDAO.getOtherGalleries(requestPageId,gallaryIds,contentType);
				}
				
				for(Object galId : otherGalIdsResult)
				{
					List<Object[]> galInfoList = null;
					if(contentType !=null && contentType.equalsIgnoreCase(IConstants.PHOTO_GALLARY))
						galInfoList = fileGallaryDAO.getFirstFileAndGallaryInfo((Long)galId," count(model.fileGallaryId) ");
					else{
						
						if(!isCustomer.equalsIgnoreCase("true"))
					      galInfoList = fileGallaryDAO.getFirstFileAndGallaryInfo((Long)galId," count(distinct model.fileGallaryId) ");
						else
							galInfoList = fileGallaryDAO.getFirstFileAndGallaryInfoForCustomer((Long)galId," count(distinct model.fileGallaryId) ");
					}
					if(galInfoList != null && galInfoList.size() > 0)
					{
						Object[] galAndFileInfo = galInfoList.get(0);
						otherGallary = new GallaryVO();
						otherFiles = new ArrayList<FileVO>();
						FileVO otherFileVO = new FileVO();
						
						otherGallary.setGallaryName(galAndFileInfo[0].toString());
						otherGallary.setDescription(CommonStringUtils.removeSpecialCharsFromAString(galAndFileInfo[1].toString()));
						otherGallary.setOrderNo((Long)galAndFileInfo[2]);
						
						otherFileVO.setFileId((Long)galAndFileInfo[3]);
						otherFileVO.setPath(galAndFileInfo[4].toString());
						otherFiles.add(otherFileVO);
						otherGallary.setFilesList(otherFiles);
						otherGalleries.add(otherGallary);
					}
				}
				contentDetailsVO.setOtherGalleries(otherGalleries);
			}
			
			return contentDetailsVO;
		}catch (Exception e) {
			log.debug("Exception occured in getSelectedContentAndRelatedGalleries() Method, Exception is - "+e);
			return null;
		}
	}
	
	public List<FileVO> getResponseTrackingNews(Long responseFileGallaryId)
	{
		
		try{
			
			List<FileVO> resultList = new ArrayList<FileVO>();
			
			List<Long> totalIds = new ArrayList<Long>();
			
			List<Long> responseIds = null;
			
			List<Long> ids = candidateNewsResponseDAO.getFileGallaryIdsByResponseGallaryId(responseFileGallaryId);
			totalIds.add(responseFileGallaryId);
			 totalIds.addAll(ids);
			 
			 List<Long> list = new ArrayList<Long>();
			 list.add(responseFileGallaryId);
			
			 responseIds = candidateNewsResponseDAO.getResponseFileGallaryidForANews(list);
			 
			 while(responseIds != null && responseIds.size() >0){				 
				 totalIds.addAll(responseIds);
				 responseIds = candidateNewsResponseDAO.getResponseFileGallaryidForANews(responseIds);	 
			 }			 
			 
		List<FileGallary> fileGallaries =  fileGallaryDAO.getFileGallariesByFileGallaryIdsList(totalIds);
		
		
		for(FileGallary fileGallary:fileGallaries)
		{
			FileVO fileVO = new FileVO();
			
			File file = fileGallary.getFile();
			
			fileVO.setFileTitle1(file.getFileTitle());
			fileVO.setFileDescription1(file.getFileDescription());
			fileVO.setNewsDescription(file.getNewsDescription()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getNewsDescription())):"");
			
			List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>();
			Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
			
			for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
				FileVO fileVOSourceLanguage = new FileVO();
				 fileVOSourceLanguage.setSource(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSource():null);
				 fileVOSourceLanguage.setSourceId(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSourceId():null);
				 fileVOSourceLanguage.setLanguage(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():null);
				 fileVOSourceLanguage.setLanguegeId(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguageId():null);
				 fileVOSourceLanguage.setFileSourceLanguageId(fileSourceLanguage.getFileSourceLanguageId());
				 
				 List<FileVO> fileVOPathsList = new ArrayList<FileVO>();
				 
				 Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
				 fileVOSourceLanguage.setMultipleNews(filePathsSet.size());
				 
				 for(FilePaths filePath : filePathsSet){
					 FileVO fileVOPath = new FileVO();
					 fileVOPath.setPath(filePath.getFilePath());
					 fileVOPath.setOrderNo(filePath.getOrderNo());
					 fileVOPath.setOrderName("Part-"+filePath.getOrderNo());
					 fileVOPathsList.add(fileVOPath);
				 }
				 Collections.sort(fileVOPathsList,CandidateDetailsService.sortData);
				 fileVOSourceLanguage.setFileVOList(fileVOPathsList);
				 fileVOSourceLanguageList.add(fileVOSourceLanguage);
			 }
			
			 fileVO.setFileVOList(fileVOSourceLanguageList);
			 
			 resultList.add(fileVO);
		}
		
		return resultList;			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	
	public ContentDetailsVO getSelectedContentAndRelatedGalleriesInPopup(
			Long contentId, String requestFrom, Long requestPageId,
			String isCustomer,List<FileGallary> allNewsListForPopup)	{
		log.debug("Entered into getSelectedContentAndRelatedGalleries() Method");
		try{
			ContentDetailsVO contentDetailsVO = new ContentDetailsVO();
			Boolean contentReq = true;
			Long fileId = null;
			List<Long> gallaryIds = null;
			String contentType = null;
			Long falseContentIdForPhotoGal = 1l;
			
			fileId = (Long)fileGallaryDAO.getFileIdByFileGallaryId(contentId);
			
			List<Object> list = fileGallaryDAO.getGalleryIdsOfAFile(fileId);
			
			if(list != null && list.size() > 0)
			{
				gallaryIds = new ArrayList<Long>(0);
				for(Object obj : list)
					gallaryIds.add((Long)obj);
				
				List<FileGallary> files = new ArrayList<FileGallary>(0);
				files.add(fileGallaryDAO.get(contentId));
				
				List<FileGallary> filesObjList = new ArrayList<FileGallary>(allNewsListForPopup);
				
				if(isCustomer.equalsIgnoreCase("true")){
					//filesObjList = fileGallaryDAO.getFilesOfInGallariesForCustomer(gallaryIds);
					requestPageId = fileGallaryDAO.get(contentId).getGallary().getCandidate().getCandidateId();
				}
				
				//else
				// filesObjList = fileGallaryDAO.getFilesOfInGallaries(gallaryIds);
				
				for(FileGallary fileGallary : filesObjList)
					if(!checkForFileExistance(files,fileGallary))
						files.add(fileGallary);
				
				Map<Long,Long> flagCount = new HashMap<Long, Long>();
				List<Long> contentIds = new ArrayList<Long>();
				for(FileGallary fileGallary:files)					
					contentIds.add(fileGallary.getFileGallaryId());
				
				List<Object[]> flagCountList = newsFlagDAO.getCountForFlagByFileGallaryId(contentIds);
				
				
				for(Object[] count:flagCountList)					
					flagCount.put((Long)count[0],(Long)count[1]);
				
					
				
				List<GallaryVO> relatedGalleries = new ArrayList<GallaryVO>(0);
				List<GallaryVO> otherGalleries = new ArrayList<GallaryVO>(0);
				GallaryVO relatedGallary = new GallaryVO();
				FileVO fileVO = null;
				List<FileVO> filesList = new ArrayList<FileVO>(0);
				for(FileGallary fileGallary : files)
				{
					fileVO = new FileVO();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					
					if(fileGallary.getFile() == null)
						continue;
					if(contentId.longValue() == fileGallary.getFileGallaryId().longValue())
					{
						fileVO.setIsSelectedContent(true);
						contentType = fileGallary.getGallary().getContentType().getContentType();
						contentDetailsVO.setContentType(contentType);
					}
					if(contentType !=null && contentType.equalsIgnoreCase(IConstants.PHOTO_GALLARY))
					{
						Set<FileSourceLanguage> fileSourceLanguageSet = fileGallary.getFile().getFileSourceLanguage();
						 List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
						Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
						for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList){
							 
							 Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
							 List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
							  Collections.sort(filePathsList,CandidateDetailsService.filePathsSort);
							 
							 for(FilePaths filePath : filePathsList){
								 fileVO = new FileVO();
								 List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>();
								 FileVO fileVOSourceLanguage = new FileVO();
								 fileVOSourceLanguage.setFileSourceLanguageId(fileSourceLanguage.getFileSourceLanguageId());
								 fileVOSourceLanguage.setMultipleNews(1);
								 List<FileVO> fileVOPathsList = new ArrayList<FileVO>();
								 if(contentReq && contentId.longValue() == fileGallary.getFileGallaryId().longValue())
									{
										fileVO.setIsSelectedContent(true);
										contentReq = false;
									}
								 
								   
								    fileVO.setTitle(fileGallary.getFile().getFileTitle()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileTitle())):"");
									fileVO.setDescription(fileGallary.getFile().getFileDescription()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileDescription())):"");
									fileVO.setContentType(fileGallary.getGallary().getContentType().getContentType());
									fileVO.setContentId(falseContentIdForPhotoGal);
									falseContentIdForPhotoGal = falseContentIdForPhotoGal+1L;
								 
								 FileVO fileVOPath = new FileVO();
								 fileVOPath.setPath(filePath.getFilePath());
								 fileVOPath.setOrderNo(filePath.getOrderNo());
								 fileVOPath.setOrderName("Part-"+filePath.getOrderNo());
								 fileVOPathsList.add(fileVOPath);
								 fileVOSourceLanguage.setFileVOList(fileVOPathsList);
								 fileVOSourceLanguageList.add(fileVOSourceLanguage);
								 fileVO.setMultipleSource(fileVOSourceLanguageList.size());
								 fileVO.setFileVOList(fileVOSourceLanguageList);
								 fileVO.setFileDate(fileGallary.getFile().getFileDate() == null ? null :
										sdf.format(fileGallary.getFile().getFileDate()));
								 fileVO.setReqFileDate(fileGallary.getFile().getFileDate());
									filesList.add(fileVO);
							 }
						}
					}
					else
					{
					if(flagCount.get(fileGallary.getFileGallaryId())!= null)						
						fileVO.setFlagSet("true");
					else
						fileVO.setFlagSet("false");
					
					if(fileGallary.getIsPrivate().equalsIgnoreCase("false"))
						fileVO.setVisibility("public");
					else
						fileVO.setVisibility("private");
						
					fileVO.setFileId(fileGallary.getFile().getFileId());
					
					//change for location name
					
					
					if(fileGallary.getFile().getRegionScopes() != null){
					  String locationName = candidateDetailsService.getLocationDetails(fileGallary.getFile().getRegionScopes().getRegionScopesId(), fileGallary.getFile().getLocationValue());
					  fileVO.setLocationName(locationName);
					}
					
					if(fileGallary.getFile().getCategory() != null){
						fileVO.setCategoryType(fileGallary.getFile().getCategory().getCategoryType());
						//fileVO.setCandidateId(fileGallary.getFile().getCategory().getCategoryId());
						fileVO.setCategoryId(fileGallary.getFile().getCategory().getCategoryId());
					}
					fileVO.setComments(fileGallary.getFile().getComment());
					
					if(fileGallary.getFile().getRegionScopes() != null)
					  fileVO.setLocationScopeValue(fileGallary.getFile().getRegionScopes().getScope());
					
					fileVO.setTitle(fileGallary.getFile().getFileTitle()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileTitle())):"");
					fileVO.setDescription(fileGallary.getFile().getFileDescription()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileDescription())):"");
					fileVO.setContentType(fileGallary.getGallary().getContentType().getContentType());
					fileVO.setContentId(fileGallary.getFileGallaryId());
					
					List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>();
					Set<FileSourceLanguage> fileSourceLanguageSet = fileGallary.getFile().getFileSourceLanguage();
					
					for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
						FileVO fileVOSourceLanguage = new FileVO();
						 fileVOSourceLanguage.setSource(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSource():null);
						 fileVOSourceLanguage.setSourceId(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSourceId():null);
						 fileVOSourceLanguage.setLanguage(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():null);
						 fileVOSourceLanguage.setLanguegeId(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguageId():null);
						 fileVOSourceLanguage.setFileSourceLanguageId(fileSourceLanguage.getFileSourceLanguageId());
						 
						 List<FileVO> fileVOPathsList = new ArrayList<FileVO>();
						 
						 Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
						 fileVOSourceLanguage.setMultipleNews(filePathsSet.size());
						 
						 for(FilePaths filePath : filePathsSet){
							 FileVO fileVOPath = new FileVO();
							 fileVOPath.setPath(filePath.getFilePath());
							 fileVOPath.setOrderNo(filePath.getOrderNo());
							 fileVOPath.setOrderName("Part-"+filePath.getOrderNo());
							 fileVOPathsList.add(fileVOPath);
						 }
						 Collections.sort(fileVOPathsList,CandidateDetailsService.sortData);
						 fileVOSourceLanguage.setFileVOList(fileVOPathsList);
						 fileVOSourceLanguageList.add(fileVOSourceLanguage);
					 }
					 fileVO.setMultipleSource(fileVOSourceLanguageList.size());
					 Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
					 fileVO.setFileVOList(fileVOSourceLanguageList);
					 
					
					fileVO.setFileDate(fileGallary.getFile().getFileDate() == null ? null :
						sdf.format(fileGallary.getFile().getFileDate()));
					 fileVO.setReqFileDate(fileGallary.getFile().getFileDate());
					 fileVO.setFileGallaryDate(fileGallary.getCreatedDate());
					filesList.add(fileVO);
				  }
				}
				Collections.sort(filesList,reverse_date_comparator);				
				relatedGallary.setFilesList(filesList);
				relatedGalleries.add(relatedGallary);
				contentDetailsVO.setRelatedGalleries(relatedGalleries);
				
				List<Object> otherGalIdsResult = null;
				GallaryVO otherGallary = null;
				List<FileVO> otherFiles = null;
				
				if(requestFrom.equalsIgnoreCase(IConstants.SPECIAL_PAGE))
				{
					otherGalIdsResult = specialPageGalleryDAO.getOtherGalleries(requestPageId,gallaryIds,contentType);
				}
				else if(requestFrom.equalsIgnoreCase(IConstants.CANDIDATE_PAGE))
				{
					if(!isCustomer.equalsIgnoreCase("true"))
					 otherGalIdsResult = gallaryDAO.getOtherGalleries(requestPageId,gallaryIds,contentType);
					else
					 otherGalIdsResult = gallaryDAO.getOtherGalleriesForCandidate(requestPageId,gallaryIds,contentType);
						
				}
				else if(requestFrom.equalsIgnoreCase(IConstants.PARTY_PAGE))
				{
					otherGalIdsResult = partyGalleryDAO.getOtherGalleries(requestPageId,gallaryIds,contentType);
				}
				
				for(Object galId : otherGalIdsResult)
				{
					List<Object[]> galInfoList = null;
					if(contentType !=null && contentType.equalsIgnoreCase(IConstants.PHOTO_GALLARY))
						galInfoList = fileGallaryDAO.getFirstFileAndGallaryInfo((Long)galId," count(model.fileGallaryId) ");
					else{
						
						if(!isCustomer.equalsIgnoreCase("true"))
					      galInfoList = fileGallaryDAO.getFirstFileAndGallaryInfo((Long)galId," count(distinct model.fileGallaryId) ");
						else
							galInfoList = fileGallaryDAO.getFirstFileAndGallaryInfoForCustomer((Long)galId," count(distinct model.fileGallaryId) ");
					}
					if(galInfoList != null && galInfoList.size() > 0)
					{
						Object[] galAndFileInfo = galInfoList.get(0);
						otherGallary = new GallaryVO();
						otherFiles = new ArrayList<FileVO>();
						FileVO otherFileVO = new FileVO();
						
						otherGallary.setGallaryName(galAndFileInfo[0].toString());
						otherGallary.setDescription(galAndFileInfo[1].toString());
						otherGallary.setOrderNo((Long)galAndFileInfo[2]);
						
						otherFileVO.setFileId((Long)galAndFileInfo[3]);
						otherFileVO.setPath(galAndFileInfo[4].toString());
						otherFiles.add(otherFileVO);
						otherGallary.setFilesList(otherFiles);
						otherGalleries.add(otherGallary);
					}
				}
				contentDetailsVO.setOtherGalleries(otherGalleries);
			}
			
			return contentDetailsVO;
		}catch (Exception e) {
			log.debug("Exception occured in getSelectedContentAndRelatedGalleries() Method, Exception is - "+e);
			return null;
		}
	}
	
	public boolean checkForFileExistance(List<FileGallary> list, FileGallary fileGallary)
	{
		try{
			for(FileGallary fileGallary2 : list)
				if(fileGallary2.getFile().getFileId().equals(fileGallary.getFile().getFileId()))
					return true;
			
			return false;
		}catch (Exception e) {
			return false;
		}
	}
	public static Comparator<FileVO> date_comparator = new Comparator<FileVO>()
		    {
		   
		        public int compare(FileVO fileVO1, FileVO fileVO2)
		        {
		        	long n1 = 0l;
		        	 long n2 = 0l;
		        	 if(fileVO1.getReqFileDate() != null)
		        	 n1 = fileVO1.getReqFileDate().getTime();
		        	 if(fileVO2.getReqFileDate() != null)
			         n2 = fileVO2.getReqFileDate().getTime();
		        	 if (n1 < n2) return -1;
		 	        else if (n1 > n2) return 1;
		 	        else return 0;
		        }
		    };
		    
		    public static Comparator<FileVO> reverse_date_comparator = new Comparator<FileVO>()
				    {
				   
				        public int compare(FileVO fileVO1, FileVO fileVO2)
				        {
				        	long n1 = 0l;
				        	 long n2 = 0l;
				        	 if(fileVO1.getFileGallaryDate() != null)
				        	 n1 = fileVO1.getFileGallaryDate().getTime();
				        	 if(fileVO2.getFileGallaryDate() != null)
					         n2 = fileVO2.getFileGallaryDate().getTime();
				        	 if (n1 > n2) return -1;
				 	        else if (n1 < n2) return 1;
				 	        else return 0;
				        }
				    };
				    
				    
				  //get Response fileGallaries for selected fileGallary
				    
					public GallaryVO getResponseGallariesForSelectedGallary(Long fileGallaryId,Integer startIndex, Integer maxIndex)
					{
						GallaryVO gallaryVO = new GallaryVO();
						try{
							List<Object[]> responseGallaryList = candidateNewsResponseDAO.getResponsefileGallaryIds(fileGallaryId,startIndex,maxIndex);
							if(responseGallaryList != null && responseGallaryList.size() > 0)
							{
							   List<Long> responseFileGallaryIdsList = new ArrayList<Long>(0);
							   List<FileVO> responseFileList = new ArrayList<FileVO>();
								for(Object[] params : responseGallaryList)
								{
								  FileVO fileVO = new FileVO();
								  fileVO.setContentId((Long)params[0]);
								  fileVO.setCandidateId((Long)params[1]);
								  fileVO.setCandidateName(candidateDAO.get((Long)params[1]).getLastname());
								  responseFileList.add(fileVO);
								  responseFileGallaryIdsList.add((Long)params[0]);
								}
							 setFileGalaryDetailsByFileGallaryIdsList(responseFileGallaryIdsList, responseFileList);
							 gallaryVO.setResponseGallaryList(responseFileList);
							 gallaryVO.setResGallTotRecordsCount(candidateNewsResponseDAO.getResponsefileGallaryIds(fileGallaryId,null,null).size());
							 
							}
							
							
							return gallaryVO;
						}catch (Exception e) {
						 e.printStackTrace();
						 log.error("Exception Occured in getResponseGallariesForSelectedGallary() method, Exception - "+e);
						 return gallaryVO;
						}
					}
					
					public void setFileGalaryDetailsByFileGallaryIdsList(List<Long> fileGallaryIdsList,List<FileVO> fileVOResultList)
					{
						try{
						List<FileGallary> fileGallariesList = fileGallaryDAO.getFileGallariesByFileGallaryIdsList(fileGallaryIdsList);
						for(FileGallary fileGallary: fileGallariesList)
						{
						  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						  
						  FileVO fileVO = getFileVOFromFileList(fileGallary.getFileGallaryId(), fileVOResultList);
						  
						  if(fileGallary.getFile() == null || fileVO == null)
							 continue;
						  
						  fileVO.setCount(candidateNewsResponseDAO.getResponsefileGallaryIds(fileGallary.getFileGallaryId(),null,null).size());
						  
						  fileVO.setTitle(fileGallary.getFile().getFileTitle() != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileTitle())):"");
						  fileVO.setDescription(fileGallary.getFile().getFileDescription()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileDescription())):"");
						  
						  Set<FileSourceLanguage> fileSourceLanguages = fileGallary.getFile().getFileSourceLanguage();
						  List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguages);
						  Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
						 
						  List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(0);
						  if(fileSourceLanguageList != null && fileSourceLanguageList.size() > 0)
						  {
							 for(FileSourceLanguage fileSourceLanguage:fileSourceLanguageList)
							 {
								FileVO fileVOSourceLanguage = new FileVO();
								fileVOSourceLanguage.setSourceId(fileSourceLanguage.getSource()!= null?fileSourceLanguage.getSource().getSourceId():null);
								fileVOSourceLanguage.setSource(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSource():null);
								fileVOSourceLanguage.setLanguegeId(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguageId():null);
								fileVOSourceLanguage.setLanguage(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():null);
								fileVOSourceLanguage.setFileSourceLanguageId(fileSourceLanguage.getFileSourceLanguageId());
								
								List<FileVO> fileVOPathsList = new ArrayList<FileVO>();
								Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
								for(FilePaths filePath:filePathsSet)
								{
								  FileVO fileVOFilePath = new FileVO();
								  fileVOFilePath.setPath(filePath.getFilePath());
								  fileVOFilePath.setOrderNo(filePath.getOrderNo());
								  fileVOFilePath.setOrderName("Part-"+filePath.getOrderNo());
								  fileVOPathsList.add(fileVOFilePath);
								}
								  
								Collections.sort(fileVOPathsList,CandidateDetailsService.sortData);
								fileVOSourceLanguage.setFileVOList(fileVOPathsList);
								fileVOSourceLanguageList.add(fileVOSourceLanguage);
							 }
							 
						  }
						  
						  
						  fileVO.setMultipleSource(fileVOSourceLanguageList.size());
						  Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
						  fileVO.setFileVOList(fileVOSourceLanguageList);
							
						  fileVO.setFileDate(fileGallary.getFile().getFileDate() == null ? null :
								sdf.format(fileGallary.getFile().getFileDate()));
						  fileVO.setReqFileDate(fileGallary.getFile().getFileDate());
							 
						  //fileVOResultList.add(fileVO);
						  
						}
					 }catch (Exception e) {
						 e.printStackTrace();
						 log.error("Exception Occured in setFileGalaryDetailsByFileGallaryIdsList() method, Exception - "+e);
					}
				}
					
					public FileVO getFileVOFromFileList(Long fileGallaryId, List<FileVO> list)
					{
					  try{
						 if(list == null || list.size() == 0)
						   return null;
							
						for(FileVO fileVO : list)
						 if(fileVO.getContentId().equals(fileGallaryId))
							 return fileVO;
							return null;
						}catch (Exception e) {
							log.error("Exception Occured in getFileVOFromFileList() method, Exception - "+e);
							return null;
						}
						
					}
					
	//Main Articles
					
	public GallaryVO getMainArticlesDetails(Long fileGallaryId, Integer startIndex, Integer maxIndex)
	{
		GallaryVO gallaryVO = new GallaryVO();
		try{
			List<Object[]> list = candidateNewsResponseDAO.getFileGallaryIdsByResponseGallaryId(fileGallaryId, startIndex,maxIndex);
			if(list != null && list.size() > 0)
			{
				List<Long> fileGallaryIdslist = new ArrayList<Long>(0);
				List<FileVO> fileVOsList = new ArrayList<FileVO>(0);
				for(Object[] params :list)
				{
				  FileVO fileVO = new FileVO();
				  fileVO.setContentId((Long)params[0]);
				  fileVO.setCandidateId((Long)params[1]);
				  fileVO.setCandidateName(candidateDAO.get((Long)params[1]).getLastname());
				  fileVOsList.add(fileVO);
				  fileGallaryIdslist.add((Long)params[0]);
				}
				setFileGalaryDetailsByFileGallaryIdsList(fileGallaryIdslist, fileVOsList);
				gallaryVO.setFilesList(fileVOsList);
				gallaryVO.setCount(candidateNewsResponseDAO.getFileGallaryIdsByResponseGallaryId(fileGallaryId,null,null).size());
			}
			return gallaryVO;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getMainArticlesDetails() method, Exception - "+e);
			return gallaryVO;
		}
	}
			
}
