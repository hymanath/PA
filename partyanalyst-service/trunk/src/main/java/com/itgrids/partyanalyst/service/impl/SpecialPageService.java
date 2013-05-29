package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ICandidateSubscriptionsDAO;
import com.itgrids.partyanalyst.dao.IConstituencySubscriptionsDAO;
import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IFileTypeDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IKeyCandidateDAO;
import com.itgrids.partyanalyst.dao.IPartySubscriptionsDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageCustomPagesDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageDataDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageDescriptionDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageHighlightsDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageInfoDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageMetaInfoDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageSubscriptionsDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageUpdatesEmailDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserGallaryDAO;
import com.itgrids.partyanalyst.dto.CustomPageVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.ImportantDatesVO;
import com.itgrids.partyanalyst.dto.MetaInfoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.dto.SubscriptionsMainVO;
import com.itgrids.partyanalyst.dto.SubscriptionsVO;
import com.itgrids.partyanalyst.model.ContentType;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.model.Gallary;
import com.itgrids.partyanalyst.model.Source;
import com.itgrids.partyanalyst.model.SourceLanguage;
import com.itgrids.partyanalyst.model.SpecialPage;
import com.itgrids.partyanalyst.model.SpecialPageData;
import com.itgrids.partyanalyst.model.SpecialPageDescription;
import com.itgrids.partyanalyst.model.SpecialPageGallery;
import com.itgrids.partyanalyst.model.SpecialPageHighlights;
import com.itgrids.partyanalyst.model.SpecialPageInfo;
import com.itgrids.partyanalyst.model.SpecialPageMetaInfo;
import com.itgrids.partyanalyst.model.SpecialPageUpdatesEmail;
import com.itgrids.partyanalyst.model.UserGallary;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.utils.CommonStringUtils;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.YouTubeManager;


public class SpecialPageService implements ISpecialPageService{
	
	//log object creation
	public static final Logger log = Logger.getLogger(SpecialPageService.class);
	private ISpecialPageDAO specialPageDAO;
	//declare DAO class reference variable as property variable
	private ISpecialPageDescriptionDAO specialPageDescriptionDAO;
	private ISpecialPageUpdatesEmailDAO specialPageUpdatesEmailDAO;
	private SpecialPageVO specialPageVO;
	private SpecialPageDescription specialPageDescription;
	private IGallaryDAO gallaryDAO;
	private SpecialPageGallery specialPageGallery;
	private IContentTypeDAO contentTypeDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IUserGallaryDAO userGallaryDAO;
	private ISpecialPageGalleryDAO specialPageGalleryDAO;
	private ISourceDAO sourceDAO;
	private Source source;
	private SelectOptionVO selectOptionVO;
	private ISourceLanguageDAO sourceLanguageDAO;
	private SourceLanguage sourceLanguage;
	private FileVO fileVO;
	private IFileDAO fileDAO;
	private IFileTypeDAO fileTypeDAO;
	private IRegionScopesDAO regionScopesDAO;
	private IFileGallaryDAO fileGallaryDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private List<SelectOptionVO> gallarySelectList;
	private ICandidateDetailsService candidateDetailsService;
	private ISpecialPageCustomPagesDAO specialPageCustomPagesDAO;
	private ISpecialPageMetaInfoDAO specialPageMetaInfoDAO;
    private ISpecialPageInfoDAO specialPageInfoDAO;
    private TransactionTemplate transactionTemplate ;
	private ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO;
	private IPartySubscriptionsDAO partySubscriptionsDAO;
	private ICandidateSubscriptionsDAO candidateSubscriptionsDAO; 
	private IConstituencySubscriptionsDAO constituencySubscriptionsDAO;
	private List<SpecialPageVO> specialPageVOList;
	private IUserDAO userDAO;
	private ISpecialPageHighlightsDAO specialPageHighlightsDAO;
	private IKeyCandidateDAO keyCandidateDAO;
	private ISpecialPageDataDAO specialPageDataDAO;
	
	public IKeyCandidateDAO getKeyCandidateDAO() {
		return keyCandidateDAO;
	}

	public void setKeyCandidateDAO(IKeyCandidateDAO keyCandidateDAO) {
		this.keyCandidateDAO = keyCandidateDAO;
	}

	
	public ISpecialPageDataDAO getSpecialPageDataDAO() {
		return specialPageDataDAO;
	}

	public void setSpecialPageDataDAO(ISpecialPageDataDAO specialPageDataDAO) {
		this.specialPageDataDAO = specialPageDataDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ISpecialPageMetaInfoDAO getSpecialPageMetaInfoDAO() {
		return specialPageMetaInfoDAO;
	}

	public void setSpecialPageMetaInfoDAO(
			ISpecialPageMetaInfoDAO specialPageMetaInfoDAO) {
		this.specialPageMetaInfoDAO = specialPageMetaInfoDAO;
	}

	public ISpecialPageCustomPagesDAO getSpecialPageCustomPagesDAO() {
		return specialPageCustomPagesDAO;
	}

	public void setSpecialPageCustomPagesDAO(
			ISpecialPageCustomPagesDAO specialPageCustomPagesDAO) {
		this.specialPageCustomPagesDAO = specialPageCustomPagesDAO;
	}
	
	public ISpecialPageHighlightsDAO getSpecialPageHighlightsDAO() {
		return specialPageHighlightsDAO;
	}

	public void setSpecialPageHighlightsDAO(
			ISpecialPageHighlightsDAO specialPageHighlightsDAO) {
		this.specialPageHighlightsDAO = specialPageHighlightsDAO;
	}
	
	//getter&setter methods for reference variables

	public void setCandidateDetailsService(ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public List<SelectOptionVO> getGallarySelectList() {
		return gallarySelectList;
	}

	public void setGallarySelectList(List<SelectOptionVO> gallarySelectList) {
		this.gallarySelectList = gallarySelectList;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public IFileDAO getFileDAO() {
		return fileDAO;
	}

	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	public IFileTypeDAO getFileTypeDAO() {
		return fileTypeDAO;
	}

	public void setFileTypeDAO(IFileTypeDAO fileTypeDAO) {
		this.fileTypeDAO = fileTypeDAO;
	}

	public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}

	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}

	public IFileGallaryDAO getFileGallaryDAO() {
		return fileGallaryDAO;
	}

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}

	public FileVO getFileVO() {
		return fileVO;
	}

	public void setFileVO(FileVO fileVO) {
		this.fileVO = fileVO;
	}

	public SourceLanguage getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(SourceLanguage sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public ISourceLanguageDAO getSourceLanguageDAO() {
		return sourceLanguageDAO;
	}

	public void setSourceLanguageDAO(ISourceLanguageDAO sourceLanguageDAO) {
		this.sourceLanguageDAO = sourceLanguageDAO;
	}

	public SelectOptionVO getSelectOptionVO() {
		return selectOptionVO;
	}

	public void setSelectOptionVO(SelectOptionVO selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public ISourceDAO getSourceDAO() {
		return sourceDAO;
	}

	public void setSourceDAO(ISourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}

	public ISpecialPageGalleryDAO getSpecialPageGalleryDAO() {
		return specialPageGalleryDAO;
	}

	public void setSpecialPageGalleryDAO(
			ISpecialPageGalleryDAO specialPageGalleryDAO) {
		this.specialPageGalleryDAO = specialPageGalleryDAO;
	}

	public IUserGallaryDAO getUserGallaryDAO() {
		return userGallaryDAO;
	}

	public void setUserGallaryDAO(IUserGallaryDAO userGallaryDAO) {
		this.userGallaryDAO = userGallaryDAO;
	}

	public IContentTypeDAO getContentTypeDAO() {
		return contentTypeDAO;
	}

	public void setContentTypeDAO(IContentTypeDAO contentTypeDAO) {
		this.contentTypeDAO = contentTypeDAO;
	}

	public SpecialPageGallery getSpecialPageGallery() {
		return specialPageGallery;
	}

	public void setSpecialPageGallery(SpecialPageGallery specialPageGallery) {
		this.specialPageGallery = specialPageGallery;
	}

	public IGallaryDAO getGallaryDAO() {
		return gallaryDAO;
	}

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}

	public SpecialPageDescription getSpecialPageDescription() {
		return specialPageDescription;
	}

	public void setSpecialPageDescription(
			SpecialPageDescription specialPageDescription) {
		this.specialPageDescription = specialPageDescription;
	}

	public SpecialPageVO getSpecialPageVO() {
		return specialPageVO;
	}

	public void setSpecialPageVO(SpecialPageVO specialPageVO) {
		this.specialPageVO = specialPageVO;
	}

	public ISpecialPageDAO getSpecialPageDAO() {
		return specialPageDAO;
	}

	public void setSpecialPageDAO(ISpecialPageDAO specialPageDAO) {
		this.specialPageDAO = specialPageDAO;
	}

	public ISpecialPageUpdatesEmailDAO getSpecialPageUpdatesEmailDAO() {
		return specialPageUpdatesEmailDAO;
	}

	public void setSpecialPageUpdatesEmailDAO(
			ISpecialPageUpdatesEmailDAO specialPageUpdatesEmailDAO) {
		this.specialPageUpdatesEmailDAO = specialPageUpdatesEmailDAO;
	}
	
	public ISpecialPageDescriptionDAO getSpecialPageDescriptionDAO() {
		return specialPageDescriptionDAO;
	}
	
	public void setSpecialPageDescriptionDAO(
			ISpecialPageDescriptionDAO specialPageDescriptionDAO) {
		this.specialPageDescriptionDAO = specialPageDescriptionDAO;
	}
	
	public ISpecialPageInfoDAO getSpecialPageInfoDAO() {
		return specialPageInfoDAO;
	}

	public void setSpecialPageInfoDAO(ISpecialPageInfoDAO specialPageInfoDAO) {
		this.specialPageInfoDAO = specialPageInfoDAO;
	}
	
	public ISpecialPageSubscriptionsDAO getSpecialPageSubscriptionsDAO() {
		return specialPageSubscriptionsDAO;
	}

	public void setSpecialPageSubscriptionsDAO(
			ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO) {
		this.specialPageSubscriptionsDAO = specialPageSubscriptionsDAO;
	}
	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public IPartySubscriptionsDAO getPartySubscriptionsDAO() {
		return partySubscriptionsDAO;
	}

	public void setPartySubscriptionsDAO(
			IPartySubscriptionsDAO partySubscriptionsDAO) {
		this.partySubscriptionsDAO = partySubscriptionsDAO;
	}

	public static Logger getLog() {
		return log;
	}

	public ICandidateSubscriptionsDAO getCandidateSubscriptionsDAO() {
		return candidateSubscriptionsDAO;
	}

	public void setCandidateSubscriptionsDAO(
			ICandidateSubscriptionsDAO candidateSubscriptionsDAO) {
		this.candidateSubscriptionsDAO = candidateSubscriptionsDAO;
	}
	
	public IConstituencySubscriptionsDAO getConstituencySubscriptionsDAO() {
		return constituencySubscriptionsDAO;
	}

	public void setConstituencySubscriptionsDAO(
			IConstituencySubscriptionsDAO constituencySubscriptionsDAO) {
		this.constituencySubscriptionsDAO = constituencySubscriptionsDAO;
	}
	
	public List<SpecialPageVO> getSpecialPageVOList() {
		return specialPageVOList;
	}

	public void setSpecialPageVOList(List<SpecialPageVO> specialPageVOList) {
		this.specialPageVOList = specialPageVOList;
	}
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public List<String> getSpecialPageDescription(Long specialPageId)
	{
		 List<String> descList = new ArrayList<String>(0); 
	   try{
			log.debug("Entered into getSpecialPageDescription() Method");
			 
		     List<Object> results = specialPageDescriptionDAO.getSpecialPageDescription(specialPageId);
		 
		   if(results != null && results.size() >0)
		   {
			 
			   for(Object desc :results)
				 descList.add(desc.toString());
			   
		   }
		   return descList;
	   }catch(Exception e){
		 log.error("Exception Occured in getCandidateProfileDescriptionByCandidateID() method - "+e);
		 return descList;
		 }
	}
	
	/**
	 * Used to retrieve SpecialPage BasicDetails
	 * @param specialPageId
	 * @return specialPageVO
	 */
	public SpecialPageVO getSpecialPageBasicDetails(Long specialPageId)
	{
		specialPageVO = new SpecialPageVO();
 	try {
		List<Object[]> specialPageDetails = specialPageDAO.getSpecialPageDetails(specialPageId);
		if(specialPageDetails !=null && specialPageDetails.size() >0)
		{
			Object[] param = specialPageDetails.get(0);
			specialPageVO.setTitle(param[0].toString());
			specialPageVO.setHeading(param[1].toString());
			specialPageVO.setEventImagePath(param[2] != null ? param[2].toString() : "");
			specialPageVO.setSpecialPageId((Long)param[3]);
		 }
		return specialPageVO;
 	}catch (Exception e) {
		e.printStackTrace();
		return specialPageVO;
	}
	
  }
	/**
	 * To get Photo Gallery 
	 * @param specialPageId
	 * @return fileVOList
	 */
	public List<FileVO> getPhotoGalleryBasedOnSpecialPageId(Long specialPageId){
		
		List<FileVO> fileVOList = new ArrayList<FileVO>();
		List<Object[]> gallaries = specialPageGalleryDAO.getSpecialPageGallaryDetails(specialPageId, IConstants.PHOTO_GALLARY);
		
		if(gallaries != null && gallaries.size() >0){
			fileVOList = setGallaryObjectToFileVO(gallaries);
		}
		return fileVOList;
		
	}
	
	/**
	 * To get News Gallery
	 * @param specialPageId
	 * @return fileVOList
	 */
	public List<FileVO> getNewsGalleryBasedOnSpecialPageId(Long specialPageId,int startingRecord,int maxRecord , String queryType){
		
		List<FileVO> fileVOList = new ArrayList<FileVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{
		List<Object[]> fileObject = specialPageGalleryDAO.getGalleriesBasedOnSpecialPageId(specialPageId, startingRecord, maxRecord, IConstants.NEWS_GALLARY);
		List<Long> totalRecords = specialPageGalleryDAO.getGalleryCountBasedOnSpecialPageId(specialPageId,  IConstants.NEWS_GALLARY);
		
		if(fileObject != null && fileObject.size() >0){
			
			for (Object[] params : fileObject) 
			{
				File file = (File)params[0];
				FileVO fileVO = new FileVO();
				fileVO.setFileId((Long) file.getFileId());
				fileVO.setFileName1(file.getFileName() != null ? file.getFileName() : "");
				fileVO.setContentId((Long)params[1]);
				
				fileVO.setFileTitle1(file.getFileTitle() != null ? CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle()) : "");
				fileVO.setFileDescription1(file.getFileDescription() != null ? CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription()): "");
				
				fileVO.setFileDate(file.getFileDate() != null ? (sdf.format((Date)file.getFileDate())) : "");
				fileVO.setCount(totalRecords.size());
				
				List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>();
				 Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
				 
					 
				 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
					 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
				 }
				 fileVO.setMultipleSource(fileVOSourceLanguageList.size());
				 Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
				 fileVO.setFileVOList(fileVOSourceLanguageList);
				
				
				fileVOList.add(fileVO);
			}
		}
		}catch(Exception e){
			log.error("Exception Occured in getNewsGalleryBasedOnSpecialPageId() method - "+e);
		}
		return fileVOList;
	}
	
	/**
	 * To get Video Gallery
	 * @param specialPageId
	 * @return fileVOList
	 */
	public List<FileVO> getVideoGalleryBasedOnSpecialPageId(Long specialPageId ,int startingRecord,int maxRecord){
		
		List<FileVO> fileVOList = new ArrayList<FileVO>();
		List<File> listOfVideos = specialPageGalleryDAO.getGalleryBasedOnSpecialPageId(specialPageId, startingRecord,maxRecord,IConstants.VIDEO_GALLARY);
		
		if(listOfVideos != null && listOfVideos.size() >0){
			for(File fileObj : listOfVideos){
				fileVO = new FileVO();
				fileVO = candidateDetailsService.copyFileToFileVO(fileObj);
				if(fileVO !=null)
					fileVOList.add(fileVO);
			}
		}
		return fileVOList;
	}
	
	public List<FileVO> getSpecialPageGallaryDetail(Long specialPageId,int firstRecord,int maxRecord,String type){
		 List<FileVO> retValue = new ArrayList<FileVO>();
	 try{
		List<Object[]> results = specialPageGalleryDAO.getSpecialPageGallaryDetail(specialPageId,firstRecord,maxRecord,type);
		
		for(Object[] gallary: results){
			String path = null;
			FileVO fileVO = new FileVO();
		    List<File> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    for(File file: record){
		    	fileVO.setFileId(file.getFileId());
		    	fileVO.setTitle(file.getFileTitle() != null ? WordUtils.capitalize(file.getFileTitle()) :"");
		    	
		    	Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
		    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
		    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
		    		for(FilePaths filePath : filePathsSet){
		    			if(path != null && path.trim().length() >0)
		    				break;
		    				path = filePath.getFilePath();
		    		}
		    		if(path != null && path.trim().length() >0)
	    				break;
		    	}
		    	if(path != null && path.trim().length() >0)
    				break;	    	
		    	
		    }
		    fileVO.setPath(path);
		    fileVO.setGallaryId((Long)gallary[0]);
		    fileVO.setSizeOfGallary((long)(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()));
		    fileVO.setGallaryName(gallary[1] != null ? WordUtils.capitalize(gallary[1].toString()) :"");
		    fileVO.setGallaryDescription(gallary[2] != null ? WordUtils.capitalize(gallary[2].toString()) :"");
		    fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString() :"");
		    fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString() :"");
		    retValue.add(fileVO);	  
		}
		return retValue;
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 return retValue;
	 }
	}
	
	/**
	 * Set Gallery Object to FileVO
	 * @param records
	 * @return fileVOList
	 */
	public List<FileVO> setGallaryObjectToFileVO(List<Object[]> records){
		
		List<FileVO> fileVOList = new ArrayList<FileVO>();
		for(Object[] gallariesObj : records){
			String path = null;
			fileVO = new FileVO();
			fileVO.setGallaryId((Long)gallariesObj[0]);
			fileVO.setGallaryName(gallariesObj[1].toString());
			fileVO.setGallaryDescription(gallariesObj[2].toString());
			List<File> fileGallaries = fileGallaryDAO.getStartingRecordInGallary((Long)gallariesObj[0]);
			
			for(File file : fileGallaries)
			{
				fileVO.setFileId(file.getFileId());
				
				String title = "";
				
				if(file.getFileTitle() != null && file.getFileTitle().length() >= 18)
				{
					title = file.getFileTitle().substring(0, 17);
					title = title + "...";
				} 
				else
				{
				if(file.getFileTitle() != null) {
					title = file.getFileTitle().toString();
				}
				Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
		    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
		    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
		    		for(FilePaths filePath : filePathsSet){
		    			if(path != null && path.trim().length() >0)
		    				break;
		    				path = filePath.getFilePath();
		    		}
		    		if(path != null && path.trim().length() >0)
	    				break;
		    	}
		    	if(path != null && path.trim().length() >0)
    				break;
			}
			fileVO.setTitle(title);
			}
			fileVO.setPath(path != null ? path:"");
			fileVOList.add(fileVO);
		}
		return fileVOList;
	}
	
	public List<FileVO> getSpecialPageGallaryDetailWithOutGallerySizeZero(Long specialPageId, int firstRecord, int maxRecord, String type) 
	{
		List<FileVO> retValue = new ArrayList<FileVO>();
		try {
			List<Object[]> results = specialPageGalleryDAO.getSpecialPageGallaryDetail(specialPageId, firstRecord, maxRecord, type);

			if(results != null && results.size() > 0)
			{
				for (Object[] gallary : results) 
				{
					String path = null;
					FileVO fileVO = new FileVO();
					List<File> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
					for (File file : record)
					{
						if (fileGallaryDAO.getAllRecordInGallary((Long) gallary[0]).size() > 0L) {
							fileVO.setFileId(file.getFileId());
							fileVO.setTitle(file.getFileTitle() != null ? file.getFileTitle():"");
							
							Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
							List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
							 Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
					    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList){
					    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
					    		List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
								  Collections.sort(filePathsList,CandidateDetailsService.filePathsSort);
					    		for(FilePaths filePath : filePathsList){
					    			if(path != null && path.trim().length() >0)
					    				break;
					    				path = filePath.getFilePath();
					    		}
					    		if(path != null && path.trim().length() >0)
				    				break;
					    	}
					    	if(path != null && path.trim().length() >0)
			    				break;
							
						}
					}
					fileVO.setPath(path != null ? path: "");
					fileVO.setGallaryId((Long) gallary[0]);
					if(type != null && type.equalsIgnoreCase(IConstants.PHOTO_GALLARY))
						fileVO.setSizeOfGallary((fileGallaryDAO.getAllRecordCountInGallary((Long)gallary[0]).get(0)));
			    	else
					    fileVO.setSizeOfGallary((long) (fileGallaryDAO.getAllRecordInGallary((Long) gallary[0]).size()));
					fileVO.setGallaryName(gallary[1] != null ? gallary[1].toString() : "");
					fileVO.setGallaryDescription(gallary[2] != null ? gallary[2].toString(): "");
					fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString(): "");
					fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString(): "");
					retValue.add(fileVO);
				}
			}
			
			return retValue;
		} catch (Exception e) {
			e.printStackTrace();
			return retValue;
		}
	}

	public ResultStatus subScribeEmailAlertForAEvent(String emailId,
			Long specialPageId) {
		 DateUtilService dateUtilService = new DateUtilService();

		log.debug("Entered into subScribeEmailAlertForAEvent() method in SpecialPageService");

		ResultStatus statusCode = new ResultStatus();
		try {
			
			SpecialPageUpdatesEmail specialPageUpdatesEmail = new SpecialPageUpdatesEmail();
			
			 List<Object> SpecialPageUpdatesEmails= specialPageUpdatesEmailDAO.getSpecialPageUpdatesEmail(emailId,specialPageId);
			 if(!(SpecialPageUpdatesEmails.size()>0))
			 {
			specialPageUpdatesEmail.setEmail(emailId);

			specialPageUpdatesEmail.setSpecialPage(specialPageDAO
					.get(specialPageId));
			specialPageUpdatesEmail.setUnsubscribed("false");
			specialPageUpdatesEmail.setSubscribedDate(dateUtilService.getCurrentDateAndTime());

			specialPageUpdatesEmailDAO.save(specialPageUpdatesEmail);
			 }
			 else
			 {
				 statusCode.setExceptionMsg("You have already subscribed for this page"); 
			 }
			
			statusCode.setResultCode(ResultCodeMapper.SUCCESS);
			return statusCode;

		} catch (Exception e) {
			log.error("Exception encountered in subScribeEmailAlertForAEvent() method in SpecialPageService, Check log for Details - "
							+ e);
			statusCode.setExceptionEncountered(e);
			statusCode.setResultCode(ResultCodeMapper.FAILURE);
			return statusCode;
		}
	}

	
		public List<FileVO> getSpecialPageHighLights(Long specialPageId)
		{
			
			FileVO filevo = null;
			List<FileVO> result = new ArrayList<FileVO>();
			try{
			List<Object[]> list = specialPageHighlightsDAO.getSpecialPageHighLightsBySpecailPageId(specialPageId);
			if(list!= null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					filevo = new FileVO();
					filevo.setOrderNo((Long)params[0]);
					if(params[1] != null)
					filevo.setDescription(params[1].toString());
					filevo.setIds((Long)params[2]);
					result.add(filevo);
				}
			}
			}
			catch (Exception e) {
			e.printStackTrace();
			}
			return result;
		}
	public List<SpecialPageVO> getEventProfileInfo(Long specialPageId) {
		log
				.debug("Entered into getEventProfileInfo() method in  SpecialPageService");
		List<SpecialPageVO> specialPageList = new ArrayList<SpecialPageVO>(0);
		specialPageVO = new SpecialPageVO();

		List<Object[]> list = specialPageDescriptionDAO
				.getEventDescription(specialPageId);
		try {
			for (Object[] params : list) {
				specialPageVO = new SpecialPageVO();
				specialPageVO.setOrderNo((Long) params[0]);
				specialPageVO.setDescription(params[1].toString().replaceAll("\\s", " "));
				specialPageVO.setSpecialPageDescriptionId((Long) params[2]);
				specialPageList.add(specialPageVO);

			}
			return specialPageList;
		} catch (Exception e) {
			log.error("Exception Occured in getEventProfileInfo() method - "
					+ e);
			return specialPageList;
		}
	}

	public ResultStatus deleteEventProfileDescById(Long specialPageDescriptionId) {
		log
				.debug("Entered into deleteEventProfileDescById() method in SpecialPageService");
		ResultStatus resultStatus = new ResultStatus();
		int flag = specialPageDescriptionDAO
				.deleteEventProfileDescriptionById(specialPageDescriptionId);
		if (flag != 0) {
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		} else {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}

	}

	public ResultStatus saveDescription(SpecialPageVO specialPageVO) {
		ResultStatus resultStatus = new ResultStatus();

		specialPageDescription = new SpecialPageDescription();
		try {
			Long orderNo;

			List<Object> results = specialPageDescriptionDAO
					.getMaxOrderNo(specialPageVO.getSpecialPageId());
			orderNo = results.get(0) == null ? 0l : (Long) results.get(0);
			orderNo = orderNo + 1;
			specialPageDescription.setOrderNo(orderNo);
			specialPageDescription.setDescription(specialPageVO
					.getDescription());
			specialPageDescription.setSpecialPage(specialPageDAO
					.get(specialPageVO.getSpecialPageId()));
			specialPageDescriptionDAO.save(specialPageDescription);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		} catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			log.error("Exception Occured in saveDescription() method - " + e);
			return resultStatus;
		}
	}

	public ResultStatus updateEventProfileDescripton(
			List<SpecialPageVO> specialPageVO, Long specialPageId) {
		log
				.debug("Entered into updateEventProfileDescripton() in specialPageService");
		ResultStatus resultStatus = new ResultStatus();
		SpecialPage specialPage = specialPageDAO.get(specialPageId);
		try {
			for (SpecialPageVO params : specialPageVO) {
				specialPageDescription = specialPageDescriptionDAO.get(params
						.getSpecialPageDescriptionId());
				specialPageDescription.setSpecialPage(specialPage);
				specialPageDescription.setOrderNo(params.getOrderNo());
				specialPageDescription.setDescription(params.getDescription());
				specialPageDescriptionDAO.save(specialPageDescription);

			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;

		} catch (Exception e) {
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}

	public ResultStatus createNewGallaryOrUpdateGallary(GallaryVO gallaryVO,
			String createOrUpdate) {
		Gallary gallary = null;

		specialPageGallery = new SpecialPageGallery();
		ResultStatus resultStatus = new ResultStatus();
		try {
			
			if (createOrUpdate.trim().equalsIgnoreCase("Create")) {
			
				List<Object[]> list = specialPageGalleryDAO.checkGalleryExistForASpecialPage(gallaryVO.getCandidateId(),
						   gallaryVO.getGallaryName(), gallaryVO.getContentType());
				if(list != null && list.size() > 0)
				{
					resultStatus.setExceptionMsg("Gallary Name is already Exists.");
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					return resultStatus;
				}
				
			}
			if (createOrUpdate.trim().equalsIgnoreCase("Update")
					&& gallaryVO.getGallaryId() != null)
				gallary = gallaryDAO.get(gallaryVO.getGallaryId());
			else
				gallary = new Gallary();
			UserGallary userGallary = null;
			gallary.setName(gallaryVO.getGallaryName());
			gallary.setDescription(gallaryVO.getDescription());
			if (createOrUpdate.trim().equalsIgnoreCase("Create")) {
				gallary.setContentType((ContentType) contentTypeDAO
						.getContentTypeByType(gallaryVO.getContentType()));
				gallary.setCreatedDate(dateUtilService.getCurrentDateAndTime());
				gallary.setIsDelete(IConstants.FALSE);
			}
			gallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
			gallary.setIsPrivate(gallaryVO.getVisibility());

			gallary = gallaryDAO.save(gallary);
			if (createOrUpdate.trim().equalsIgnoreCase("Create")) {
			specialPageGallery.setIsDelect(IConstants.FALSE);
			// specialPageGallery.setIsPrivate(gallaryVO.getVisibility());
			specialPageGallery.setSpecialPage(specialPageDAO.get(gallaryVO
					.getCandidateId()));
			specialPageGallery.setGallary(gallary);
			specialPageGallery.setCreatedDate(dateUtilService
					.getCurrentDateAndTime());
			specialPageGallery.setUpdatedDate(dateUtilService
					.getCurrentDateAndTime());
			specialPageGallery = specialPageGalleryDAO.save(specialPageGallery);
			
				userGallary = new UserGallary();
				userGallary.setGallary(gallary);
				userGallary.setUserId(gallaryVO.getUserId());
				userGallaryDAO.save(userGallary);
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		} catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}

	}

	public List<SelectOptionVO> getSource() {
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>(0);
		try {
			List<Source> source = sourceDAO.getAll();

			
			for (Source params : source) {
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(params.getSourceId());
				selectOptionVO.setName(params.getSource());
				selectOptionList.add(selectOptionVO);

			}
			return selectOptionList;
		} catch (Exception e) {
			e.printStackTrace();
			return selectOptionList;
		}
	}

	public List<SelectOptionVO> getLanguage() {
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>();
		try {
			
			List<SourceLanguage> sourceLanguage = sourceLanguageDAO.getAll();
			for (SourceLanguage params : sourceLanguage) {
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(params.getLanguageId());
				selectOptionVO.setName(params.getLanguage());
				selectOptionList.add(selectOptionVO);
			}
			return selectOptionList;

		} catch (Exception e) {
			e.printStackTrace();
			return selectOptionList;
		}
	}
	
	public ResultStatus uploadAFile(FileVO fileVO){
		
			ResultStatus resultStatus = new ResultStatus();
			try{
				log.debug("Entered into uploadAFile() method in Candidate Details Service()");
				
				File file = new File();
				FileGallary fileGallary = new FileGallary();
				SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
				file.setFileName(fileVO.getName());
				file.setFilePath(fileVO.getPath().trim());
				file.setFileType(fileTypeDAO.getFileType(fileVO.getContentType()).get(0));
				file.setFileTitle(CommonStringUtils.removeSpecialCharsFromAString(fileVO.getTitle()));
				file.setFileDescription(CommonStringUtils.removeSpecialCharsFromAString(fileVO.getDescription()));
				file.setKeywords(fileVO.getKeywords());
				
				if(fileVO.getLanguegeId() != null && fileVO.getLanguegeId() > 0)
					file.setLanguage(sourceLanguageDAO.get(fileVO.getLanguegeId()));
				if(fileVO.getSourceId() != null && fileVO.getSourceId() > 0)
					file.setSourceObj(sourceDAO.get(fileVO.getSourceId()));
				
				if(fileVO.getLocationScope() != null && fileVO.getLocationScope().longValue() > 0 &&
						fileVO.getLocationValue() != null && Integer.parseInt(fileVO.getLocationValue()) > 0)
				{
					file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));
					file.setLocationValue(getLocationScopeValue(fileVO.getLocationScope(),fileVO.getLocationValue()));
				}
				
				if(fileVO.getFileDate() != null && fileVO.getFileDate().length() > 0)
					file.setFileDate(sdf.parse(fileVO.getFileDate()));
				
				file = fileDAO.save(file);
				
				fileGallary.setGallary(gallaryDAO.get(fileVO.getGallaryId()));
				fileGallary.setFile(file);
				fileGallary.setCreatedDate(dateUtilService.getCurrentDateAndTime());
				fileGallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
				fileGallary.setIsDelete(IConstants.FALSE);
				
				if(fileVO.getVisibility().equalsIgnoreCase("public"))
					fileGallary.setIsPrivate(IConstants.FALSE);
				else
					fileGallary.setIsPrivate(IConstants.TRUE);
				
				fileGallaryDAO.save(fileGallary);
				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
			}catch (Exception e) {
				log.error("Exception encountered, Check log for Details - "+e);
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
		}
		
	public Long getLocationScopeValue(Long scope,String locationValue)
	{
		try{
			int scopeValue = scope.intValue();
			
			if(scopeValue == 1 || scopeValue == 2 || scopeValue == 3 || scopeValue == 4 || scopeValue == 9)
				return Long.parseLong(locationValue);
			else if(scopeValue == 5 || scopeValue == 6 || scopeValue == 8)
				return Long.parseLong(locationValue.substring(1));
			else if(scopeValue == 7)
			{
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(Long.parseLong(locationValue.substring(1)));
				return (Long) list.get(0);
			}
			
			return null;
		}catch (Exception e) {
			log.error("Error Occured in getLocationScopeVlaue() method - "+e); 
			return null;
		}
	}
	
		
	public List<SelectOptionVO> getEventGallarySelectList(Long specialPageId,
			String contentType) {
			
		gallarySelectList = new ArrayList<SelectOptionVO>(0);
		try {
			log.debug("Entered into getCandidateGallarySelectList() Method");
	
			List<Object[]> list = specialPageGalleryDAO.getGallariesBySpecialPageId(specialPageId, contentType);
			
			if (list != null && list.size() > 0) {
				SelectOptionVO selectOptionVO = null;
				for (Object[] params : list) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long) params[0]);
					selectOptionVO.setName(params[1].toString());
					gallarySelectList.add(selectOptionVO);
				}
			}
			return gallarySelectList;
		} catch (Exception e) {
			log.error("Exception Occured in getCandidateGallarySelectList() method - "+ e);
			e.printStackTrace();
			return gallarySelectList;
		}
	}
	public List<SelectOptionVO> getPartyGallarySelectList(Long specialPageId,
			String contentType) {
		try {
			log.debug("Entered into getCandidateGallarySelectList() Method");

			List<SelectOptionVO> gallarySelectList = null;
			List<Object[]> list = specialPageGalleryDAO.getGallariesBySpecialPageId(
					specialPageId, contentType);

			if (list != null && list.size() > 0) {
				gallarySelectList = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for (Object[] params : list) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long) params[0]);
					selectOptionVO.setName(params[1].toString());
					gallarySelectList.add(selectOptionVO);
				}
			}
			return gallarySelectList;
		} catch (Exception e) {
			log
					.error("Exception Occured in getCandidateGallarySelectList() method - "
							+ e);
			return gallarySelectList;
		}
		
	}
	
	public List<CustomPageVO> getCustomPagesOfASpecialPage(Long specialPageId)
	{
		try{
			List<CustomPageVO> customPages = null;
			List<Object[]> list = specialPageCustomPagesDAO.getCustomPagesOfASpecialPage(specialPageId);
			
			if(list != null && list.size() > 0)
			{
				customPages = new ArrayList<CustomPageVO>(0);
				for(Object[] params : list)
					customPages.add(new CustomPageVO(IConstants.CUSTOM_JSP_PAGES_PATH+"/"+params[0].toString(),params[1].toString()));
			}
			return customPages;
		}catch (Exception e) {
			log.error("Exception Occured in getCustomPagesOfASpecialPage(), Exception is - "+e);
			return null;
		}
	}
	
	public ResultStatus createNewSpecialPage(GallaryVO gallaryVO)
	{
		ResultStatus resultStatus = new ResultStatus(); 
		try{
			log.error("Entered into createNewSpecialPage() Method");
			SpecialPage specialPage = new SpecialPage();
			specialPage.setName(gallaryVO.getGallaryName());
			specialPage.setTitle(gallaryVO.getVisibility());
			specialPage.setHeading(gallaryVO.getContentType());
			specialPage.setIsDelete(IConstants.FALSE);
			specialPage.setCreatedDate(dateUtilService.getCurrentDateAndTime());
			specialPage.setUpdateddate(dateUtilService.getCurrentDateAndTime());
			
			specialPageDAO.save(specialPage);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
			
		}catch(Exception e){
		log.error("Ecxeption Occured in createNewSpecialPage(), Exception is - "+e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
		}
	}
	
	public MetaInfoVO getMetaInfoForASpecialPage(Long specialPageId)
	{
		MetaInfoVO metaInfoVO = null;
		try
		{
			List<Object[]> list = specialPageMetaInfoDAO.getMetaInfoForASpecialPage(specialPageId);
			
			if(list != null && list.size() > 0)
			{
				metaInfoVO = new MetaInfoVO();
				metaInfoVO.setKeywords(list.get(0)[0] != null ? list.get(0)[0].toString() : "");
				metaInfoVO.setDescription(list.get(0)[1] != null ? list.get(0)[1].toString() : "");
			}
			return metaInfoVO;
		}catch (Exception e) {
			log.error("Exception Encountered in getMetaInfoForASpecialPage() Method,- "+e);
			return null;
		}
	}
	
	public ResultStatus saveMetaInfoForASpecialPage(MetaInfoVO metaInfoVO)
	{
		log.debug("Entered into saveMetaInfoForASpecialPage() Method");
		ResultStatus resultStatus = new ResultStatus();
		try{
			SpecialPageMetaInfo specialPageMetaInfo = new SpecialPageMetaInfo();
			specialPageMetaInfo.setSpecialPage(specialPageDAO.get(metaInfoVO.getSpecialPageId()));
			specialPageMetaInfo.setKeywords(metaInfoVO.getKeywords());
			specialPageMetaInfo.setDescription(metaInfoVO.getDescription());
			
			specialPageMetaInfo = specialPageMetaInfoDAO.save(specialPageMetaInfo);
			
			if(specialPageMetaInfo != null)
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			
			return resultStatus;
		}catch (Exception e) {
			log.error("Exception encountered in saveMetaInfoForASpecialPage() Method - "+e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}
	}
	public List<FileVO> getFirstThreePhotoGallaryRecords(Long specialPageId)
	{
		List<FileVO> retValue = new ArrayList<FileVO>();
	  try
	  { 
		  log.debug("Entered into getFirstThreePhotoGallaryRecords()of specialpageservice");
		  List<Object[]> results = specialPageGalleryDAO.getSpecialPageGallaryId(specialPageId,0,20,IConstants.PHOTO_GALLARY);
		 if(results !=null && results.size()>0)	
		 { 
			for(Object[] gallary: results){
				String path = null;
				FileVO fileVO = new FileVO();
			    List<File> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
			    if(record !=null && record.size()>0)
			    {
			      for(File file: record){
			    	 fileVO.setFileId(file.getFileId());	    			    	
			    	 
			    	 String title =""; 
			   	     if(file.getFileTitle() != null && file.getFileTitle().length()>=18)
			   	     {
			   	    	title = file.getFileTitle().substring(0, 17);
			   	    	title = title+"...";
			   	     }
			   	     else
			   	     {
			   	       if(file.getFileTitle() != null)
			   	       {	
			   	    	 title = file.getFileTitle();
			   	       }
			   	     }
			    	fileVO.setTitle(title);
			    	    	fileVO.setContentId((Long)fileGallaryDAO.getFileGallaryIdByFileId(file.getFileId()).get(0));
			    	Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
			    	List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
					 Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
			    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList){
			    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
			    		List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
						  Collections.sort(filePathsList,CandidateDetailsService.filePathsSort);
			    		for(FilePaths filePath : filePathsList){
			    			if(path != null && path.trim().length() >0)
			    				break;
			    				path = filePath.getFilePath();
			    		}
			    		if(path != null && path.trim().length() >0)
		    				break;
			    	}
			    	if(path != null && path.trim().length() >0)
	    				break;
			     }
			   }
			    fileVO.setPath(path != null ? path :null);
			     fileVO.setGallaryId((Long)gallary[0]);
			     fileVO.setSizeOfGallary((long)(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()));
			     fileVO.setGallaryName(gallary[1] != null ? gallary[1].toString() :"");
			     fileVO.setGallaryDescription(gallary[2] != null ? gallary[2].toString() :"");
			     fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString() :"");
			     fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString() :"");
			     
			    retValue.add(fileVO);	  
			}
		 }
		  
		  return retValue;
	  }
	  catch(Exception e)
	  {
		  log.error("Exception encountered in getFirstThreePhotoGallaryRecords() of specialpageservice"+e);
		  return retValue;
	  }
	}
	
	public List<SelectOptionVO> getSpecialPageIdsList()
	{
		try{
			List<SelectOptionVO> resultList = null;
			 
			List<Object[]> list = specialPageDAO.getSpecialPageNames();
			
			if(list != null && list.size() > 0)
			{
				resultList = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : list)
				{
					selectOptionVO = new SelectOptionVO((Long)params[0],params[1].toString());
					resultList.add(selectOptionVO);
				}
			}
			
			return resultList;
		}catch (Exception e) {
			log.error("Exception Occred in getSpecialPageIdsList() Method, Exception is - "+e);
			return null;
		}
	}

	private void setSourceLanguageAndPaths(FileSourceLanguage fileSourceLanguage,List<FileVO> fileVOSourceLanguageList){
		FileVO fileVOSourceLanguage = new FileVO();
		 fileVOSourceLanguage.setSource(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSource():"");
		 fileVOSourceLanguage.setSourceId(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSourceId():null);
		 fileVOSourceLanguage.setLanguage(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():"");
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
	
		public ResultStatus createOrUpdateSpecialPageInfo(SpecialPageVO specialPageVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		SpecialPageInfo specialPageInfo = null;
		try{
			if(specialPageVO == null)
			{
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
				
			if(specialPageVO.getSpecialPageId() !=null && specialPageVO.getSpecialPageId()>0)
			{
				Long specialPageInfoId = specialPageInfoDAO.getSpecialPageInfoIdBySpecialPageId(specialPageVO.getSpecialPageId());
				if(specialPageInfoId !=null && specialPageInfoId > 0)
				 specialPageInfo = specialPageInfoDAO.get(specialPageInfoId);
				else
					specialPageInfo = new SpecialPageInfo();
				
			}
				
				 specialPageInfo.setTitle(specialPageVO.getTitle());
				 specialPageInfo.setDescription(specialPageVO.getDescription());
				 specialPageInfo.setIsDisplayEnabled(specialPageVO.getHeading());
				 specialPageInfo.setSpecialPage(specialPageDAO.get(specialPageVO.getSpecialPageId()));
				 if(specialPageVO.getEventImagePath() !=null)
				specialPageInfo.setShowImgPath(specialPageVO.getEventImagePath());
				 
				 specialPageInfoDAO.save(specialPageInfo);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			log.error("Exception Ocuured in createOrUpdateSpecialPageInfo() Method, Exception is -"+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	
	
	public List<SpecialPageVO> getSpecialPageInfo(Long specialPageId)
	{
		List<SpecialPageVO> specialPageVOList = null;
		SpecialPageVO specialPageVO = null;
		try{
			List<SpecialPageInfo> specislPagelist = specialPageInfoDAO.getSpecialPageInfo(specialPageId);
			if(specislPagelist != null && specislPagelist.size() > 0)
			{
				specialPageVOList = new ArrayList<SpecialPageVO>(0);
				for(SpecialPageInfo specialPageDet : specislPagelist)
				{
					specialPageVO = new SpecialPageVO();
					specialPageVO.setTitle(specialPageDet.getTitle().toString());
					specialPageVO.setDescription(specialPageDet.getDescription().toString());
					specialPageVO.setEventImagePath(specialPageDet.getShowImgPath().toString());
					specialPageVO.setHeading(specialPageDet.getIsDisplayEnabled().toString());
					specialPageVO.setSpecialPageId(specialPageDet.getSpecialPage().getSpecialPageId());
					specialPageVOList.add(specialPageVO);
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getSpecialPageInfo() Method,Exception: "+e);
		}
		return specialPageVOList;
	}
	
	public List<SpecialPageVO> getSpecialPageListForHomePage()
	{
		List<SpecialPageVO> specialPageVOList = null;
		SpecialPageVO specialPageVO = null;
		try{
			List<Object[]> list1 = specialPageInfoDAO.getSpecialPagesForHomePage();
			Set<Object[]> set = new HashSet<Object[]>(list1);
			List<Object[]> list = new ArrayList<Object[]>(set);
			if(list != null && list.size() > 0)
			{
				specialPageVOList = new ArrayList<SpecialPageVO>(0);
				for(Object[] params : list)
				{
					specialPageVO = new SpecialPageVO();
					specialPageVO.setTitle(params[0].toString());
					specialPageVO.setDescription(params[1].toString());
					specialPageVO.setEventImagePath(params[2].toString());
					specialPageVO.setSpecialPageId((Long)params[3]);
					specialPageVOList.add(specialPageVO);
				}
			}
			return specialPageVOList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getSpecialPageListForHomePage() Method,Exception: "+e);
			return specialPageVOList;
		}
		
	}
	
	public List<SpecialPageVO> getAllSpecialPageListForHomePage()
	{
		List<SpecialPageVO> specialPageList = null;
		try{
			List<SpecialPageInfo> list  = specialPageInfoDAO.getAllSpecialPageListForHomePage();
			if(list!= null && list.size() >0)
			{
				specialPageList = new ArrayList<SpecialPageVO>(0);
				for(SpecialPageInfo specialPage: list)
				{
					SpecialPageVO specialPageVO = new SpecialPageVO();
					specialPageVO.setTitle(specialPage.getTitle().toString());
					specialPageVO.setDescription(specialPage.getDescription().toString());
					specialPageVO.setEventImagePath(specialPage.getShowImgPath().toString());
					specialPageVO.setHeading(specialPage.getIsDisplayEnabled().toString());
					specialPageVO.setSpecialPageId(specialPage.getSpecialPage().getSpecialPageId());
					specialPageList.add(specialPageVO);
				}
			}
			return specialPageList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAllSpecialPageListForHomePage() method,Exception: "+e);
			return specialPageList;
		}
	}
/* Public Profile Subscriptions Start*/
	
	public SubscriptionsMainVO getAllUserSubScriptions(Long userId, Long profileId)
	{
	SubscriptionsMainVO subscriptionsMainVO = new SubscriptionsMainVO();
		
		try{
			
			subscriptionsMainVO.setUserSpecialPageSubscriptions(getAllUserSubscribedSpecialPages(userId));
			subscriptionsMainVO.setUserPartySubscriptions(getAllUserSubscribedPartyPages(userId));
			subscriptionsMainVO.setUserCandidateSubscriptions(getAllUserSubscribedCandidatePages(userId));
			subscriptionsMainVO.setUserConstituencySubscriptions(getAllUserSubscribedConstituencyPages(userId));
			if(!userId.toString().equalsIgnoreCase(profileId.toString()))
			{
				subscriptionsMainVO.setProfileSpecialPageSubscriptions(getAllProfileSubscribedSpecialPages(profileId));
				subscriptionsMainVO.setProfilePartySubscriptions(getAllProfileSubscribedPartyPages(profileId));
				subscriptionsMainVO.setProfileCandidateSubscriptions(getAllProfileSubscribedCandidatePages(profileId));
				subscriptionsMainVO.setProfileConstituencySubscriptions(getAllProfileSubscribedConstituencyPages(profileId));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return subscriptionsMainVO;
	}
	
	public List<SubscriptionsVO> getAllUserSubscribedSpecialPages(Long userId)
	{
		List<SubscriptionsVO> userSpecialPageSubscriptions = null ;
		SubscriptionsVO subscriptionsVO = null;
		try{
			List<Object[]> list = specialPageSubscriptionsDAO.getAllUserSubscribedSpecialPages(userId);
			if(list !=null && list.size() >0)
			{
				userSpecialPageSubscriptions = new ArrayList<SubscriptionsVO>(0);
				for(Object[] params : list)
				{
					subscriptionsVO = new SubscriptionsVO();
					subscriptionsVO.setId((Long)params[0]);
					subscriptionsVO.setName(params[1].toString());
					subscriptionsVO.setImageURL(params[2].toString());
					subscriptionsVO.setTitle(params[3].toString());
					subscriptionsVO.setDescription(params[4].toString());
					subscriptionsVO.setType("userSpecialPageSunscription");
					subscriptionsVO.setUserId((Long)params[5]);
					userSpecialPageSubscriptions.add(subscriptionsVO);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getSubScribedSpecialPages() Method, Exception - "+e);
		}
		return userSpecialPageSubscriptions;
	}
	
	public List<SubscriptionsVO> getAllProfileSubscribedSpecialPages(Long profileId)
	{
		List<SubscriptionsVO> profileSpecialPageSubscriptions = null ;
		SubscriptionsVO subscriptionsVO = null;
		try{
			List<Object[]> list = specialPageSubscriptionsDAO.getAllUserSubscribedSpecialPages(profileId);
			if(list != null && list.size() >0)
			{
				profileSpecialPageSubscriptions = new ArrayList<SubscriptionsVO>(0);
				for(Object[] params : list)
				{
					subscriptionsVO = new SubscriptionsVO();
					subscriptionsVO = new SubscriptionsVO();
					subscriptionsVO.setId((Long)params[0]);
					subscriptionsVO.setName(params[1].toString());
					if(params[2] !=null)
					subscriptionsVO.setImageURL(params[2].toString());
					subscriptionsVO.setTitle(params[3].toString());
					subscriptionsVO.setDescription(params[4].toString());
					subscriptionsVO.setType("profileSpecialPageSunscription");
					subscriptionsVO.setUserId((Long)params[5]);
					profileSpecialPageSubscriptions.add(subscriptionsVO);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAllProfileSubscribedSpecialPages() Method, Exception - "+e);
		}
		return profileSpecialPageSubscriptions;
	}
	
	public List<SubscriptionsVO> getAllUserSubscribedPartyPages(Long userId)
	{
		List<SubscriptionsVO> subscriptionsVOList = null;
		SubscriptionsVO subscriptionsVO = null;
		try{
			List<Object[]> list = partySubscriptionsDAO.getAllUserSubscribedPartyPages(userId);
			if(list !=null && list.size() > 0)
			{
				subscriptionsVOList = new ArrayList<SubscriptionsVO>(0);
				for(Object[] params : list)
				{
					subscriptionsVO = new SubscriptionsVO();
					subscriptionsVO.setId((Long)params[0]);
					if(params[1] !=null)
					subscriptionsVO.setName(params[1].toString());
					if(params[2] !=null)
					subscriptionsVO.setTitle(params[2].toString());
					subscriptionsVO.setUserId((Long)params[3]);
					subscriptionsVO.setType("userPartyPageSunscription");
					subscriptionsVOList.add(subscriptionsVO);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in getAllUserSubscribedPartyPages() Method, Exception - "+e);
		}
		return subscriptionsVOList;
	}
	
	public List<SubscriptionsVO> getAllProfileSubscribedPartyPages(Long profileId)
	{
		List<SubscriptionsVO> subscriptionsVOList = null;
		SubscriptionsVO subscriptionsVO = null;
		try{
			List<Object[]> list = partySubscriptionsDAO.getAllUserSubscribedPartyPages(profileId);
			if(list !=null && list.size() > 0)
			{
				subscriptionsVOList = new ArrayList<SubscriptionsVO>(0);
				for(Object[] params : list)
				{
					subscriptionsVO = new SubscriptionsVO();
					subscriptionsVO.setId((Long)params[0]);
					if(params[1] !=null)
					subscriptionsVO.setName(params[1].toString());
					if(params[2] !=null)
					subscriptionsVO.setTitle(params[2].toString());
					subscriptionsVO.setUserId((Long)params[3]);
					subscriptionsVO.setType("profilePartyPageSunscription");
					subscriptionsVOList.add(subscriptionsVO);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in getAllProfileSubscribedPartyPages() Method, Exception - "+e);
		}
		return subscriptionsVOList;
	}
	
	public List<SubscriptionsVO> getAllUserSubscribedCandidatePages(Long userId)
	{
		List<SubscriptionsVO> subscriptionsVOList = null;
		SubscriptionsVO subscriptionsVO = null;
		try{
			List<Object[]> list = candidateSubscriptionsDAO.getAllUserSubscribedCandidatePages(userId);
			if(list != null && list.size() >0)
			{
				subscriptionsVOList = new ArrayList<SubscriptionsVO>(0);
				for(Object[] params : list)
				{
					subscriptionsVO = new SubscriptionsVO();
					subscriptionsVO.setId((Long)params[0]);
					subscriptionsVO.setName(params[1].toString());
					subscriptionsVO.setUserId((Long)params[2]);
					subscriptionsVO.setType("userSubscribedCandidatePages");
					subscriptionsVOList.add(subscriptionsVO);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in getAllUserSubscribedCandidatePages() Method, Exception - "+e);
		}
		return subscriptionsVOList;
	}
	
	
	public List<SubscriptionsVO> getAllProfileSubscribedCandidatePages(Long profileId)
	{
		List<SubscriptionsVO> subscriptionsVOList = null;
		SubscriptionsVO subscriptionsVO = null;
		try{
			List<Object[]> list = candidateSubscriptionsDAO.getAllUserSubscribedCandidatePages(profileId);
			if(list != null && list.size() >0)
			{
				subscriptionsVOList = new ArrayList<SubscriptionsVO>(0);
				for(Object[] params : list)
				{
					subscriptionsVO = new SubscriptionsVO();
					subscriptionsVO.setId((Long)params[0]);
					subscriptionsVO.setName(params[1].toString());
					subscriptionsVO.setUserId((Long)params[2]);
					subscriptionsVO.setType("profileSubscribedCandidatePages");
					subscriptionsVOList.add(subscriptionsVO);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in getAllProfileSubscribedCandidatePages() Method, Exception - "+e);
		}
		return subscriptionsVOList;
	}
	
	public List<SubscriptionsVO> getAllUserSubscribedConstituencyPages(Long userId)
	{
		List<SubscriptionsVO> subscriptionsVOList = null;
		SubscriptionsVO subscriptionsVO = null;
		try{
			List<Object[]> list = constituencySubscriptionsDAO.getAllUserSubscribedConstituencyPages(userId);
			if(list != null && list.size() >0)
			{
				subscriptionsVOList = new ArrayList<SubscriptionsVO>(0);
				for(Object[] params : list)
				{
					subscriptionsVO = new SubscriptionsVO();
					subscriptionsVO.setId((Long)params[0]);
					subscriptionsVO.setName(params[1].toString());
					subscriptionsVO.setUserId((Long)params[2]);
					subscriptionsVO.setType("userSubscribedConstituencyPages");
					subscriptionsVOList.add(subscriptionsVO);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in getAllUserSubscribedConstituencyPages() Method, Exception - "+e);
		}
		return subscriptionsVOList;
	}
	
	public List<SubscriptionsVO> getAllProfileSubscribedConstituencyPages(Long profileId)
	{
		List<SubscriptionsVO> subscriptionsVOList = null;
		SubscriptionsVO subscriptionsVO = null;
		try{
			List<Object[]> list = constituencySubscriptionsDAO.getAllUserSubscribedConstituencyPages(profileId);
			if(list != null && list.size() >0)
			{
				subscriptionsVOList = new ArrayList<SubscriptionsVO>(0);
				for(Object[] params : list)
				{
					subscriptionsVO = new SubscriptionsVO();
					subscriptionsVO.setId((Long)params[0]);
					subscriptionsVO.setName(params[1].toString());
					subscriptionsVO.setUserId((Long)params[2]);
					subscriptionsVO.setType("profileSubscribedConstituencyPages");
					subscriptionsVOList.add(subscriptionsVO);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in getAllProfileSubscribedConstituencyPages() Method, Exception - "+e);
		}
		return subscriptionsVOList;
	}
	
	/* Public Profile Subscriptions End*/
	
	/* User Profile Subscriptions Start*/
	
	public SubscriptionsMainVO getUserProfileSubScriptions(Long userId)
	{
		SubscriptionsMainVO subscriptionsMainVO = new SubscriptionsMainVO();
		try{
			subscriptionsMainVO.setUserSpecialPageSubscriptions(getAllSpecialPagesForUserProfile(userId));
			//subscriptionsMainVO.setUserPartySubscriptions(getAllUserSubscribedPartyPages(userId));
			subscriptionsMainVO.setUserPartySubscriptions(getAllPartyPagesForUserProfile(userId));
			subscriptionsMainVO.setUserCandidateSubscriptions(getAllUserSubscribedCandidatePages(userId));
			subscriptionsMainVO.setUserConstituencySubscriptions(getAllUserSubscribedConstituencyPages(userId));
		
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in getUserProfileSubScriptions() Method, Exception - "+e);
		}
		
		return subscriptionsMainVO;
	}
	
	public List<SubscriptionsVO> getAllSpecialPagesForUserProfile(Long userId)
	{
		List<SubscriptionsVO> subscriptionsVOList = new ArrayList<SubscriptionsVO>(0);
		List<SpecialPageVO> specialPageVOList = new ArrayList<SpecialPageVO>(0);
		try{
			specialPageVOList = getAllSpecialPageListForHomePage();
			SubscriptionsVO subscriptionsVO = null;
			
			List<Long> list = specialPageSubscriptionsDAO.getUserSubscribedSpecialPageIds(userId);
				if(specialPageVOList != null && specialPageVOList.size() >0)
					for(SpecialPageVO specialPageVO : specialPageVOList)
					{
						subscriptionsVO = new SubscriptionsVO();
						if(list.contains(specialPageVO.getSpecialPageId()))
							subscriptionsVO.setSubscribed(true);
						subscriptionsVO.setSpecialPageVO(specialPageVO);
						subscriptionsVOList.add(subscriptionsVO);
					}
						
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAllSpecialPagesForUserProfile() Method, Exception - "+e);
		}
		return subscriptionsVOList;
	}
	
	
	/* User Profile Subscriptions End*/
	
	
	public String getProfileUserName(Long profileId)
	{
		String name = "";
		try{
			List<Object[]> list = userDAO.getUserEmailByUserId(profileId);
			if(list != null && list.size() >0)
			{
				for(Object[] params : list)
				{
					if(params[1].toString() != null)
						name += params[1].toString();
					if(params[2].toString() != null)
						name +=" "+params[2].toString();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getProfileUserName() Method,Exception- "+e);
		}
		
		return name;
	}
	        // start youtube delete videos realated code
	
  public Map<String,List<?>> getYoutubeVideosList(Date startDate,Date endDate){
		//conertdate 
	             
	  Map<String,List<?>> rest= new HashMap<String,List<?>>();
	  System.out.println("hello service");
		List<FilePaths> fileVOList = new ArrayList<FilePaths>();
		List<Object[]> listOfVideos = specialPageGalleryDAO.getExpiredVideosList(startDate,endDate,IConstants.VIDEO);
		List <String> file_paths=new ArrayList <String>();
		List <Long> file_source_language=new ArrayList <Long>();
	   //	List <String> Intilal_file_paths=new ArrayList <String>();
		List <Long> Intilal_file_source_language=new ArrayList <Long>();
		boolean validVideo=true;
		
		 if(listOfVideos != null && listOfVideos.size() >0){
			for(int i=0; i<listOfVideos.size();i++ )
			{    
		    String path=(String)listOfVideos.get(i)[0];
			
		  //  Intilal_file_paths.add(path);
		  Intilal_file_source_language.add((Long)listOfVideos.get(i)[1]);
		    
		    
		    validVideo= isInvalidValidVedio(path, i);
			if(!validVideo){
			file_paths.add((String)listOfVideos.get(i)[0]);
			file_source_language.add((Long)listOfVideos.get(i)[1]);
			}
			}
			if(file_paths != null && Intilal_file_source_language != null )
			{
				rest.put("filepaths", file_paths);
				rest.put("languageIdsNotChecked",Intilal_file_source_language );
			}
		
		}
		 
		return rest;
	}

	private boolean isInvalidValidVedio(String pathOfFile,int n) {
		
		YouTubeManager ym1 = new YouTubeManager("rak"+n);
        boolean flag=  ym1.getVedioDetails(pathOfFile);
		return flag;
	}
@Transactional	
  public int[]  deleteExpiredVideosList (List<String> filePaths,List<Long> languageIds){
	
	
 int[] results=(int[]) deleteVideosInTransaction(filePaths, languageIds);
	 
	/*
	
	List<Object> fsli= getFileSourceLanguageIds(filePaths);
	  List<Object> fileIds= getFileIds(fsli);
	
	     
	  
	    
	  
	  int[] results= deleteYoutubeVideoRecords(filePaths, fileIds, fsli, languageIds);
	                
	
	*/
	return results;
	} 
        // method for updating file_paths of lastverfied date of filepaths in database
public int[] deleteYoutubeVideoRecords(List<String> filePaths ,List<Object> fileIds, List<Object> fileSourceLanguageIds, List<Long> languageIds)
{    int[] res=new int[5];

	res[0]=updateLastUpdateDateInFilePaths(languageIds);
	res[1]=deleteRecordsFromFilePath(filePaths);
	res[2]=deleteRecordsFromFileSourceLanguage(fileSourceLanguageIds);
	res[3]=deleteRecordsFromFileGallary(fileIds);
	res[4]=deleteRecordsFromFile(fileIds);

	
return res;
}  

  public List<Object> getFileSourceLanguageIds(List<String> filePaths)
	{
	 List<Object> fsli= specialPageGalleryDAO.getFileSourceLanguageIds(filePaths);

	return fsli;
	}
  public List<Object> getFileIds(List<Object> languageIds)
	{
	 
	 List<Object> fileIds= specialPageGalleryDAO.getFileIds(languageIds);
	 return fileIds;
	}
   
  public int  updateLastUpdateDateInFilePaths(List<Long> languageIds)
 {   
	 return specialPageGalleryDAO.updateLastUpdateDateInFilePaths(languageIds);
 }
 
  public int  deleteRecordsFromFilePath(List<String> filePaths)
 { 
	 return specialPageGalleryDAO.deleteRecordsFromFilePath(filePaths);
 }
 
 public int  deleteRecordsFromFileSourceLanguage(List<Object> fileSourceLanguageIds)
 {
	 return specialPageGalleryDAO.deleteRecordsFromFileSourceLanguage(fileSourceLanguageIds);
 }
 
 public int  deleteRecordsFromFileGallary(List<Object> fileIds)
 {
	 return specialPageGalleryDAO.deleteRecordsFromFileGallary(fileIds);
 }
 
 public int   deleteRecordsFromFile(List<Object> fileIds)
 {
	 return specialPageGalleryDAO.deleteRecordsFromFile(fileIds);
 }
 @Transactional
	public  Object  deleteVideosInTransaction (final List<String> filePaths,final List<Long> languageIds) {
		log.debug("inside cadre skills block");
	Object res=	transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
		
				Object results= null;
				try{
					List<Object> fsli= getFileSourceLanguageIds(filePaths);
					  List<Object> fileIds= getFileIds(fsli);							    
					  	   					  
					results= deleteYoutubeVideoRecords(filePaths, fileIds, fsli, languageIds);
					
				}
				 catch(Exception e){
					status.setRollbackOnly();
					
					log.debug(e);
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while setCadreSkillsInfo() method::", e);
						throw new RuntimeException();
					}					
					e.printStackTrace();
				}
				return results;
			}
		});		
	return res;
	}
 
 /**
  * This method will save the data for special pages
  * @param specialPageId
  * @param specialPageText
  */
 public String saveSpecialPageText(Long specialPageId , String specialPageText){
	 if(log.isDebugEnabled())
		 log.debug("Entered into the saveSpecialPageText service method");
	 
	 SpecialPageData specialPageData  = null;
	try{
		 List<SpecialPageData> specialPageDataList  = specialPageDataDAO.getSpecialPageDataObjectBySpecialPageId(specialPageId);
		 
		 if(specialPageDataList != null && specialPageDataList.size() >0)
			 specialPageData = specialPageDataList.get(0);
		 else
			 specialPageData = new SpecialPageData();
		 
		 specialPageData.setSpecialPage(specialPageDAO.get(specialPageId));
		 specialPageData.setSpecialPageData(specialPageText);		 
		 specialPageDataDAO.save(specialPageData);
		 
		 return IConstants.SUCCESS;
	 
	 }catch(Exception e){
		 
		 log.error("Exception raised in  saveSpecialPageText service method");
		 e.printStackTrace();
		 return null;
		 
	 }
 }
 
 /**
  * This method will fetch the data for special page
  * @param specialPageId
  */
 
 public String getSpecialPageDataBySpecialPageId(Long specialPageId){
	 
	 try{
	 
		List<String> specialPageDataList =  specialPageDataDAO.getSpecialPageDataBySpecialPageId(specialPageId);
		
		if(specialPageDataList != null && specialPageDataList.size() >0)
			return specialPageDataList.get(0);
		else
			return null;
	 }catch(Exception e){
		 
		 e.printStackTrace();
		 return null;
		 
	 }
	 
 }
 
 /**
  * This method will get all special pages details
  * @return
  */
 public List<SelectOptionVO> getAllSpecialPages(){
	 
	 if(log.isDebugEnabled())
		 log.debug("Entered into the getAllSpecialPages service method ");
	 
		List<SelectOptionVO> spcialPageList = null;
		
		try{
				List<Object[]> specialPageDetailsList =  specialPageDAO.getSpecialPageNames();
				
				if(specialPageDetailsList != null && specialPageDetailsList.size() >0)
					spcialPageList = new ArrayList<SelectOptionVO>();
				
			for (Object[] specialPageDetails : specialPageDetailsList) {

				SelectOptionVO slectOptionVO = new SelectOptionVO();
				slectOptionVO.setId((Long) specialPageDetails[0]);
				slectOptionVO.setName(specialPageDetails[1].toString());
				spcialPageList.add(slectOptionVO);

			}
			
		}catch(Exception e){
			
			 log.error("Exception raised  in  getAllSpecialPages service method ");
			e.printStackTrace();
			return null;
			
		}	 
	 return spcialPageList;
	 
 }
 
 
 public List<ImportantDatesVO> getImportantCandidatesInfoByElectionId(Long electionId){	
	 
	 if(log.isDebugEnabled())
		 log.debug("Entered into the getImportantCandidatesInfoByElectionId service method ");
	 
	 List<ImportantDatesVO> importantCandidatesList = null;	 
	 
	 try{
	 
		List<Object[]> cdidatesList =  keyCandidateDAO.getImportantCandidatesInfoByElectionId(electionId);
		
		
		if(cdidatesList != null && cdidatesList.size() >0)
			importantCandidatesList = new ArrayList<ImportantDatesVO>();
		
			for(Object[] obj:cdidatesList){
				
				ImportantDatesVO importantCandidate = new ImportantDatesVO();
				
				importantCandidate.setCandidateId((Long)obj[0]);
				importantCandidate.setCandidateName(obj[1].toString().toUpperCase());
				importantCandidate.setParty(obj[2].toString());
				importantCandidate.setConstituency(obj[3].toString());
				importantCandidate.setStatus(obj[4].toString());
				
				importantCandidatesList.add(importantCandidate);
		  }
			return importantCandidatesList;
	 
	 }catch(Exception e){
		 
		 log.error("Exception raised in getImportantCandidatesInfoByElectionId service method");
		 e.printStackTrace();
		 return null;
		 
	 }
	 
 }
	
 
 public ResultStatus saveSpecialPageHighLights(SpecialPageVO specialPageVO) {
		ResultStatus resultStatus = new ResultStatus();
		SpecialPageHighlights specialPageHighlights = new SpecialPageHighlights();
		try {
			Long orderNo;

			List<Object> results = specialPageHighlightsDAO
					.getMaxOrderNo(specialPageVO.getSpecialPageId());
			orderNo = results.get(0) == null ? 0l : (Long) results.get(0);
			orderNo = orderNo + 1;
			
			specialPageHighlights.setOrderNo(orderNo);
			specialPageHighlights.setDescription(specialPageVO
					.getDescription());
			specialPageHighlights.setSpecialPage(specialPageDAO
					.get(specialPageVO.getSpecialPageId()));
			specialPageHighlightsDAO.save(specialPageHighlights);
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		} catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			log.error("Exception Occured in saveSpecialPageHighLights() method - " + e);
			return resultStatus;
		}
	}
 
 	public ResultStatus deleteSpecialPageHighLights(Long id) {
		log
				.debug("Entered into deleteEventProfileDescById() method in SpecialPageService");
		ResultStatus resultStatus = new ResultStatus();
		int flag = specialPageDescriptionDAO
				.deleteEventProfileDescriptionById(id);
		if (flag != 0) {
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		} else {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}

	}
 	
 	public ResultStatus deleteSpecialPageHighLightDescription(Long specialPageDescriptionId) {
		log
				.debug("Entered into deleteSpecialPageHighLightDescription() method in SpecialPageService");
		ResultStatus resultStatus = new ResultStatus();
		int flag = specialPageHighlightsDAO
				.deleteSpecialHighlightsDescription(specialPageDescriptionId);
		if (flag != 0) {
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		} else {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}

	}
 	
 	public ResultStatus updateSpecialPageHighLightsDesc(
			List<SpecialPageVO> specialPageVO, Long specialPageId) {
		log
				.debug("Entered into updateSpecialPageHighLightsDesc() in specialPageService");
		ResultStatus resultStatus = new ResultStatus();
		SpecialPage specialPage = specialPageDAO.get(specialPageId);
		try {
			for (SpecialPageVO params : specialPageVO) {
				SpecialPageHighlights specialPageHighlights = specialPageHighlightsDAO.get(params
						.getSpecialPageDescriptionId());
				specialPageHighlights.setSpecialPage(specialPage);
				specialPageHighlights.setOrderNo(params.getOrderNo());
				specialPageHighlights.setDescription(params.getDescription());
				specialPageHighlightsDAO.save(specialPageHighlights);

			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;

		} catch (Exception e) {
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
 	
 	public List<SelectOptionVO> getStaticPartiesForSubScriptions()
 	{
 		List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>(0);;
 		try{
 			
 			partiesList.add(new SelectOptionVO(362l,"Indian National Congress","INC"));
 			partiesList.add(new SelectOptionVO(872l,"Telugu Desam Party","TDP"));
 			partiesList.add(new SelectOptionVO(1117l,"Yuvajana Sramika Raithu Congress","YSRC"));
 			partiesList.add(new SelectOptionVO(163l,"Bharath Janathadal Party","BJP"));
  			partiesList.add(new SelectOptionVO(886l,"Telangana Rastra Samithi","TRS"));
 			partiesList.add(new SelectOptionVO(839l,"Samajwadi Party","SP"));
 			partiesList.add(new SelectOptionVO(239l,"Bahujan Samaj Party","BSP"));
 			partiesList.add(new SelectOptionVO(76l,"All India Trinamool Congress","AITC"));
 			partiesList.add(new SelectOptionVO(579l,"Nationalist Congress Party","NCP"));
 			
 			return partiesList;
 			
 		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getStaticPartiesForSubScriptions() Method, Exception - "+e);
			return partiesList;
		}
 	}
 	public List<SubscriptionsVO> getAllPartyPagesForUserProfile(Long userId)
	{
		List<SubscriptionsVO> partyVOList = new ArrayList<SubscriptionsVO>(0);
		SubscriptionsVO subscriptionsVO = null;
		try{
			List<SelectOptionVO> staticPartyList = getStaticPartiesForSubScriptions();
			List<Long> partyIds = partySubscriptionsDAO.getAllPartiesSubscribedByUser(userId);
			if(staticPartyList != null && staticPartyList.size() > 0)
			{
				for(SelectOptionVO staticParties : staticPartyList)
				{
					subscriptionsVO = new SubscriptionsVO();
					if(partyIds.contains(staticParties.getId()))
						subscriptionsVO.setSubscribed(true);
					subscriptionsVO.setName(staticParties.getName());
					subscriptionsVO.setId(staticParties.getId());
					subscriptionsVO.setImageURL(staticParties.getUrl());
					partyVOList.add(subscriptionsVO);
				}
			}
					
			return partyVOList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAllPartyPagesForUserProfile() Method, Exception - "+e);
			return partyVOList;
		}
		
	}
	

}
