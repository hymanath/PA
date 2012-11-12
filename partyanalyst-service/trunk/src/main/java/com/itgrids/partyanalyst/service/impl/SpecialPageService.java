package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IFileTypeDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageCustomPagesDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageDescriptionDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageInfoDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageMetaInfoDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageUpdatesEmailDAO;
import com.itgrids.partyanalyst.dao.IUserGallaryDAO;
import com.itgrids.partyanalyst.dao.hibernate.SpecialPageInfoDAO;
import com.itgrids.partyanalyst.dto.CustomPageVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.MetaInfoVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.model.ContentType;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.model.Gallary;
import com.itgrids.partyanalyst.model.Source;
import com.itgrids.partyanalyst.model.SourceLanguage;
import com.itgrids.partyanalyst.model.SpecialPage;
import com.itgrids.partyanalyst.model.SpecialPageDescription;
import com.itgrids.partyanalyst.model.SpecialPageGallery;
import com.itgrids.partyanalyst.model.SpecialPageInfo;
import com.itgrids.partyanalyst.model.SpecialPageMetaInfo;
import com.itgrids.partyanalyst.model.SpecialPageUpdatesEmail;
import com.itgrids.partyanalyst.model.UserGallary;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;


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

	//implementations of declaration reference variable
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
				
				fileVO.setFileTitle1(file.getFileTitle() != null ? file.getFileTitle() : "");
				fileVO.setFileDescription1(file.getFileDescription() != null ? file.getFileDescription(): "");
				
				fileVO.setFileDate(file.getFileDate() != null ? file.getFileDate().toString() : "");
				fileVO.setCount(totalRecords.get(0).intValue());
				
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
				file.setFilePath(fileVO.getPath());
				file.setFileType(fileTypeDAO.getFileType(fileVO.getContentType()).get(0));
				file.setFileTitle(fileVO.getTitle());
				file.setFileDescription(fileVO.getDescription());
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
			List<Object[]> list = specialPageInfoDAO.getSpecialPagesForHomePage();
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
		
}
