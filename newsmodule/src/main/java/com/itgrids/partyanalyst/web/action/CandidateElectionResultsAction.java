/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateProfileInfoVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.CommentVO;
import com.itgrids.partyanalyst.dto.ContentDetailsVO;
import com.itgrids.partyanalyst.dto.CustomPageVO;
import com.itgrids.partyanalyst.dto.ElectionGoverningBodyVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.PdfGenerationVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IContentManagementService;
import com.itgrids.partyanalyst.service.IElectionLiveResultsAnalysisService;
import com.itgrids.partyanalyst.service.INewsMonitoringService;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.service.IThumbnailService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.IWebConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CandidateElectionResultsAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware,ServletContextAware {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CandidateElectionResultsAction.class);
	private Long candidateId;
	private List<CandidateDetailsVO> candidateElectionDetails;
	private CandidateVO candidateVO;
	private CandidateProfileInfoVO candidateProfileInfoVO;
	private String candidateURLString;
	private JSONObject jObj;
	private String task;
	private List<FileVO> fileVO;
	private FileVO fileVO2;
	private ResultStatus result;
	private GallaryVO gallaryVO;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ICandidateDetailsService candidateDetailsService;
	private List<File> userImage;
	private File userMoreImages;
	private String userMoreImagesContentType;
	private String userMoreImagesFileName;
	private String userImageContentType;
	private String userImageFileName;
	private String problemFilePathList;
	private String contentType;
	private String fileTitle;
	private String fileDescription;
    private HttpServletRequest servletRequest;
    private ServletContext context;
    private String problemFilepath;
    private String tempFileName;
	private String visibility;
	private Long gallaryId;
	private InputStream inputStream;
	private List<SelectOptionVO> selectOptionList;
	private List<String> descriptions;
	private String keywords;
	private Long locationScope;
	private Long locationValue;
	private String fileDate;
	private List<GallaryVO> gallaryList;
	private Long source;
	private Long language;
	private String isAdmin;
	private String profileType;
	private String profileId;
	private String profileGalleryType;
	private Long category;
	private IPartyDetailsService partyDetailsService;
	private Long newsimportance;
	private List<CandidateCommentsVO> candidateCommentsVO;
	private Long contentId;
	private IElectionLiveResultsAnalysisService electionLiveResultsAnalysisService;
	private List<ElectionGoverningBodyVO> electionGoverningBodyVO;
	private List<CustomPageVO> customPages;
	private Long uploadCandidateId;
	private Long uploadPartyId;
	private List<Long> uploadCandidateGalleryId;
	private List<Long> uploadPartyGalleryId;
	private List<Long> uploadSpecialPageGalleryId;
	private List<String> fileNamesList = new ArrayList<String>();
	List<Long> galleryIds = new ArrayList<Long>(0);
	private List<Long> fileSourceId;
	private List<Long> sourceLanguageId;
	private Long stateId;
    private Long constituencyId;
	private Boolean isSubscribed = false;
	private Long regId;
	private String status;
	private String candidatePageLoadingFirstTime;
	FileVO  fileDetails;
	private List<Integer> newsedition;
	private List<Integer> pageno;
	private List<String> newslength;
	private Long fileId ;
	
	private INewsMonitoringService  newsMonitoringService;
	
	
	private List<FileVO> sourceList;
	private List<FileVO> newsImportanceList ;
	private List<FileVO> regionsScopesList ;
	private List<FileVO> categoryList ;
	private PdfGenerationVO  pdfGenerationVO ;
	private List<FileVO> allNewsList ;
	private ContentDetailsVO allNewsListForPopup ;
	private List<FileVO> newsCountByCategoryList;
	private List<CommentVO> commentsList;
	private Long savedCommentId;
	private IContentManagementService contentManagementService;
	private ContentDetailsVO contentDetailsVO;
	

	public List<Integer> getNewsedition() {
		return newsedition;
	}


	public void setNewsedition(List<Integer> newsedition) {
		this.newsedition = newsedition;
	}


	public List<Integer> getPageno() {
		return pageno;
	}


	public void setPageno(List<Integer> pageno) {
		this.pageno = pageno;
	}


	public List<String> getNewslength() {
		return newslength;
	}


	public void setNewslength(List<String> newslength) {
		this.newslength = newslength;
	}

	
	public IContentManagementService getContentManagementService() {
		return contentManagementService;
	}


	public void setContentManagementService(
			IContentManagementService contentManagementService) {
		this.contentManagementService = contentManagementService;
	}


	public ContentDetailsVO getContentDetailsVO() {
		return contentDetailsVO;
	}


	public void setContentDetailsVO(ContentDetailsVO contentDetailsVO) {
		this.contentDetailsVO = contentDetailsVO;
	}



	public ContentDetailsVO getAllNewsListForPopup() {
		return allNewsListForPopup;
	}


	public void setAllNewsListForPopup(ContentDetailsVO allNewsListForPopup) {
		this.allNewsListForPopup = allNewsListForPopup;
	}


	public Long getSavedCommentId() {
		return savedCommentId;
	}


	public void setSavedCommentId(Long savedCommentId) {
		this.savedCommentId = savedCommentId;
	}


	public List<CommentVO> getCommentsList() {
		return commentsList;
	}


	public void setCommentsList(List<CommentVO> commentsList) {
		this.commentsList = commentsList;
	}


	public List<FileVO> getNewsCountByCategoryList() {
		return newsCountByCategoryList;
	}


	public void setNewsCountByCategoryList(List<FileVO> newsCountByCategoryList) {
		this.newsCountByCategoryList = newsCountByCategoryList;
	}


	public List<FileVO> getAllNewsList() {
		return allNewsList;
	}


	public void setAllNewsList(List<FileVO> allNewsList) {
		this.allNewsList = allNewsList;
	}


	public PdfGenerationVO getPdfGenerationVO() {
		return pdfGenerationVO;
	}


	public void setPdfGenerationVO(PdfGenerationVO pdfGenerationVO) {
		this.pdfGenerationVO = pdfGenerationVO;
	}


	public List<FileVO> getSourceList() {
		return sourceList;
	}


	public void setSourceList(List<FileVO> sourceList) {
		this.sourceList = sourceList;
	}


	public List<FileVO> getNewsImportanceList() {
		return newsImportanceList;
	}


	public void setNewsImportanceList(List<FileVO> newsImportanceList) {
		this.newsImportanceList = newsImportanceList;
	}


	public List<FileVO> getRegionsScopesList() {
		return regionsScopesList;
	}


	public void setRegionsScopesList(List<FileVO> regionsScopesList) {
		this.regionsScopesList = regionsScopesList;
	}


	public List<FileVO> getCategoryList() {
		return categoryList;
	}


	public void setCategoryList(List<FileVO> categoryList) {
		this.categoryList = categoryList;
	}


	public INewsMonitoringService getNewsMonitoringService() {
		return newsMonitoringService;
	}


	public void setNewsMonitoringService(
			INewsMonitoringService newsMonitoringService) {
		this.newsMonitoringService = newsMonitoringService;
	}


	private File imageForDisplay;
	private String imageForDisplayContentType;
	private String imageForDisplayFileName;
    private  IThumbnailService thumbnailService;
    private String pdfPath;
   private String pdfName;

	public String getPdfName() {
	return pdfName;
	}
	
	
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}


	public String getPdfPath() {
		return pdfPath;
	}


	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}


	private InputStream fileInputStream;
    
    public InputStream getFileInputStream() {
		return fileInputStream;
	}
 
	
	public IThumbnailService getThumbnailService() {
		return thumbnailService;
	}

	public void setThumbnailService(IThumbnailService thumbnailService) {
		this.thumbnailService = thumbnailService;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public FileVO getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(FileVO fileDetails) {
		this.fileDetails = fileDetails;
	}
	public File getImageForDisplay() {
		return imageForDisplay;
	}

	public void setImageForDisplay(File imageForDisplay) {
		this.imageForDisplay = imageForDisplay;
	}

	public String getImageForDisplayContentType() {
		return imageForDisplayContentType;
	}

	public void setImageForDisplayContentType(String imageForDisplayContentType) {
		this.imageForDisplayContentType = imageForDisplayContentType;
	}

	public String getImageForDisplayFileName() {
		return imageForDisplayFileName;
	}

	public void setImageForDisplayFileName(String imageForDisplayFileName) {
		this.imageForDisplayFileName = imageForDisplayFileName;
	}

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

	public Boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public List<File> getUserImage() {
		return userImage;
	}

	public void setUserImage(List<File> userImage) {
		this.userImage = userImage;
	}

	public List<Long> getFileSourceId() {
		return fileSourceId;
	}

	public void setFileSourceId(List<Long> fileSourceId) {
		this.fileSourceId = fileSourceId;
	}

	public String getUserMoreImagesContentType() {
		return userMoreImagesContentType;
	}

	public void setUserMoreImagesContentType(String userMoreImagesContentType) {
		this.userMoreImagesContentType = userMoreImagesContentType;
	}

	public String getUserMoreImagesFileName() {
		return userMoreImagesFileName;
	}

	public void setUserMoreImagesFileName(String userMoreImagesFileName) {
		this.userMoreImagesFileName = userMoreImagesFileName;
	}

	public File getUserMoreImages() {
		return userMoreImages;
	}

	public void setUserMoreImages(File userMoreImages) {
		this.userMoreImages = userMoreImages;
	}

	public List<String> getFileNamesList() {
		return fileNamesList;
	}

	public void setFileNamesList(List<String> fileNamesList) {
		this.fileNamesList = fileNamesList;
	}

	public Long getUploadCandidateId() {
		return uploadCandidateId;
	}

	public Long getUploadPartyId() {
		return uploadPartyId;
	}

	public List<Long> getUploadCandidateGalleryId() {
		return uploadCandidateGalleryId;
	}

	public List<Long> getUploadPartyGalleryId() {
		return uploadPartyGalleryId;
	}

	public void setUploadCandidateId(Long uploadCandidateId) {
		this.uploadCandidateId = uploadCandidateId;
	}

	public void setUploadPartyId(Long uploadPartyId) {
		this.uploadPartyId = uploadPartyId;
	}

	public void setUploadCandidateGalleryId(List<Long> uploadCandidateGalleryId) {
		this.uploadCandidateGalleryId = uploadCandidateGalleryId;
	}

	public void setUploadPartyGalleryId(List<Long> uploadPartyGalleryId) {
		this.uploadPartyGalleryId = uploadPartyGalleryId;
	}

	public List<CustomPageVO> getCustomPages() {
		return customPages;
	}

	public void setCustomPages(List<CustomPageVO> customPages) {
		this.customPages = customPages;
	}

	public IElectionLiveResultsAnalysisService getElectionLiveResultsAnalysisService() {
		return electionLiveResultsAnalysisService;
	}

	public List<ElectionGoverningBodyVO> getElectionGoverningBodyVO() {
		return electionGoverningBodyVO;
	}

	public void setElectionGoverningBodyVO(
			List<ElectionGoverningBodyVO> electionGoverningBodyVO) {
		this.electionGoverningBodyVO = electionGoverningBodyVO;
	}

	public void setElectionLiveResultsAnalysisService(
			IElectionLiveResultsAnalysisService electionLiveResultsAnalysisService) {
		this.electionLiveResultsAnalysisService = electionLiveResultsAnalysisService;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public List<CandidateCommentsVO> getCandidateCommentsVO() {
		return candidateCommentsVO;
	}

	public void setCandidateCommentsVO(List<CandidateCommentsVO> candidateCommentsVO) {
		this.candidateCommentsVO = candidateCommentsVO;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public IPartyDetailsService getPartyDetailsService() {
		return partyDetailsService;
	}

	public void setPartyDetailsService(IPartyDetailsService partyDetailsService) {
		this.partyDetailsService = partyDetailsService;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getProfileGalleryType() {
		return profileGalleryType;
	}

	public void setProfileGalleryType(String profileGalleryType) {
		this.profileGalleryType = profileGalleryType;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public FileVO getFileVO2() {
		return fileVO2;
	}

	public void setFileVO2(FileVO fileVO2) {
		this.fileVO2 = fileVO2;
	}

	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public Long getLanguage() {
		return language;
	}

	public List<Long> getSourceLanguageId() {
		return sourceLanguageId;
	}

	public void setSourceLanguageId(List<Long> sourceLanguageId) {
		this.sourceLanguageId = sourceLanguageId;
	}

	public void setLanguage(Long language) {
		this.language = language;
	}

	public List<GallaryVO> getGallaryList() {
		return gallaryList;
	}

	public void setGallaryList(List<GallaryVO> gallaryList) {
		this.gallaryList = gallaryList;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Long getLocationScope() {
		return locationScope;
	}

	public void setLocationScope(Long locationScope) {
		this.locationScope = locationScope;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	

	public List<SelectOptionVO> getSelectOptionList() {
		return selectOptionList;
	}

	public void setSelectOptionList(List<SelectOptionVO> selectOptionList) {
		this.selectOptionList = selectOptionList;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public String getProblemFilePathList() {
		return problemFilePathList;
	}

	public void setProblemFilePathList(String problemFilePathList) {
		this.problemFilePathList = problemFilePathList;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getProblemFilepath() {
		return problemFilepath;
	}

	public void setProblemFilepath(String problemFilepath) {
		this.problemFilepath = problemFilepath;
	}

	public String getTempFileName() {
		return tempFileName;
	}

	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Long getGallaryId() {
		return gallaryId;
	}

	public void setGallaryId(Long gallaryId) {
		this.gallaryId = gallaryId;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public String getCandidateURLString() {
		return candidateURLString;
	}

	public void setCandidateURLString(String candidateURLString) {
		this.candidateURLString = candidateURLString;
	}

	public CandidateProfileInfoVO getCandidateProfileInfoVO() {
		return candidateProfileInfoVO;
	}

	public void setCandidateProfileInfoVO(
			CandidateProfileInfoVO candidateProfileInfoVO) {
		this.candidateProfileInfoVO = candidateProfileInfoVO;
	}

	public CandidateVO getCandidateVO() {
		return candidateVO;
	}

	public void setCandidateVO(CandidateVO candidateVO) {
		this.candidateVO = candidateVO;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public List<CandidateDetailsVO> getCandidateElectionDetails() {
		return candidateElectionDetails;
	}

	public void setCandidateElectionDetails(
			List<CandidateDetailsVO> candidateElectionDetails) {
		this.candidateElectionDetails = candidateElectionDetails;
	}
	public List<FileVO> getFileVO() {
		return fileVO;
	}

	public ServletContext getContext() {
		return context;
	}

	
	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void setFileVO(List<FileVO> fileVO) {
		this.fileVO = fileVO;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
    public Long getNewsimportance() {
		return newsimportance;
	}

	public void setNewsimportance(Long newsimportance) {
		this.newsimportance = newsimportance;
	}

	public void setUploadSpecialPageGalleryId(List<Long> uploadSpecialPageGalleryId) {
		this.uploadSpecialPageGalleryId = uploadSpecialPageGalleryId;
	}

	public List<Long> getUploadSpecialPageGalleryId() {
		return uploadSpecialPageGalleryId;
	}
	
	
	@SuppressWarnings("deprecation")
	public String uploadFiles()
	{
		
		System.out.println(context.getRealPath("/"));
		
		
		log.debug("Enter into uploadFiles() Method");
		session = request.getSession();

			
		String fileName = null;
		String filePath = null;
		String fileNames = null;
		
		FileVO fileVO = new FileVO();
		FileVO displayFileVO = new FileVO();
		
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		fileVO.setUserId(regVO.getRegistrationID());
		
		
		
		String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		
		if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE))
			filePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + IConstants.UPLOADED_FILES + pathSeperator;
		else
			filePath = context.getRealPath("/")+IConstants.UPLOADED_FILES + pathSeperator;
		
		if(profileType != null && profileId != null && profileGalleryType != null)
		{
			filePath = filePath + profileType + pathSeperator + profileId + pathSeperator + profileGalleryType + pathSeperator;
		}
		try 
		{
			String fileType = null;
			List<String> fileTypes = new ArrayList<String>();
			Long systime = System.currentTimeMillis();
			Random random = new Random();
			if(userImageContentType.contains("text/plain"))
			{
				String[] str ;
				str = userImageContentType.split(",");
				if(str !=null)
				{
					for(int i=0;i<str.length;i++)
					{
					fileType = str[i].substring(str[i].indexOf("/")+1,str[i].length());
					fileNames = systime.toString()+random.nextInt(IWebConstants.FILE_RANDOM_NO)+"."+fileType;
					fileTypes.add(fileType);
					fileNamesList.add(fileNames);
					}
				}
			}
			else
			{
				String[] str ;
				str = userImageContentType.split(",");
				if(str !=null)
				{
					for(int i=0;i<str.length;i++)
					{
					fileType = str[i].substring(str[i].indexOf("/")+1,str[i].length());
					fileNames = systime.toString()+random.nextInt(IWebConstants.FILE_RANDOM_NO)+"."+fileType;
					fileTypes.add(fileType);
					fileNamesList.add(fileNames);
					}
				}
			}
			List<Long> fileSourceIds = new ArrayList<Long>();
			List<Long> fileSourceLangIds = new ArrayList<Long>();
			
			fileVO.setFileName(fileNamesList);
			fileVO.setName(fileName);
			fileVO.setTitle(escapeUnicode(StringEscapeUtils.unescapeHtml(getFileTitle())));
			fileVO.setDescription(escapeUnicode(StringEscapeUtils.unescapeHtml(getFileDescription())));
			//fileVO.setTitle(getFileTitle());
			//fileVO.setDescription(getFileDescription());
			//fileVO.setContentType(fileType);
			fileVO.setVisibility(getVisibility());
			fileVO.setGallaryId(getGallaryId());
			fileVO.setKeywords(escapeUnicode(StringEscapeUtils.unescapeHtml(getKeywords())));
			//fileVO.setKeywords(getKeywords());
			fileVO.setSourceId(getSource());
			fileVO.setLanguegeId(getLanguage());
			fileVO.setCategoryId(getCategory());
			fileVO.setNewsImportanceId(getNewsimportance());
			fileVO.setLocationScope(getLocationScope());
			fileVO.setLocationValue(getLocationValue() != null ? getLocationValue().toString() : null);
			fileVO.setFileDate(getFileDate());
			fileVO.setFileTypesList(fileTypes);
			
			if(getUploadCandidateGalleryId()!=null)
			{
				for(Long i: uploadCandidateGalleryId)
					galleryIds.add(i);
				
			}
			if(getUploadPartyGalleryId()!=null)
			{
				for(Long i : uploadPartyGalleryId)
				galleryIds.add(i);
			}
			if(uploadSpecialPageGalleryId != null)
			{
				for(Long i : uploadSpecialPageGalleryId)
					galleryIds.add(i);
			}
				
			
			fileVO.setUploadOtherProfileGalleryIds(galleryIds);
			if(fileSourceId!=null)
			{
				for(Long i: fileSourceId)
					fileSourceIds.add(i);
				
			}
			if(sourceLanguageId != null)
			{
				for(Long i: sourceLanguageId)
					fileSourceLangIds.add(i);
			}
			fileVO.setFileSource(fileSourceIds);
			fileVO.setSourceLangIds(fileSourceLangIds);
			fileVO.setNewsedition(newsedition);
			fileVO.setPageno(pageno);
			fileVO.setNewslength(newslength);
			if(profileType != null && profileId != null && profileGalleryType != null)
			{
				String path ;
				List<String> paths = new ArrayList<String>();
				for(int i=0;i<fileNamesList.size();i++)
				{
					path = IWebConstants.UPLOADED_FILES+"/"+profileType+"/"+profileId+"/"+profileGalleryType+"/"+fileNamesList.get(i);
					paths.add(path);
					fileVO.setFilePath(paths);
				}
			}
			else
			{
				String path ;
				List<String> paths = new ArrayList<String>();
				for(int i=0;i<fileNamesList.size();i++)
				{
					path = IWebConstants.UPLOADED_FILES+"/"+fileNamesList.get(i);
					paths.add(path);
					fileVO.setFilePath(paths);
				}
				//fileVO.setPath(IWebConstants.UPLOADED_FILES+"/"+fileName);
			}
			
			if (imageForDisplayContentType != null && imageForDisplay != null
					&& imageForDisplayFileName != null) {
				
				String imageName = systime.toString()+random.nextInt(IWebConstants.FILE_RANDOM_NO)+"."+fileType;
				String path1 = IWebConstants.UPLOADED_FILES+"/"+profileType+"/"+profileId+"/"+profileGalleryType+"/"+imageName;

				
				displayFileVO.setDisplayImageName(imageName);
				displayFileVO.setDisplayImageContentType(imageForDisplayContentType);
				displayFileVO.setDisplayImagePath(path1);
				fileVO.setFileVOForDiaplyImage(displayFileVO);
				
				
			}
			
			if(fileVO.getFileVOForDiaplyImage () != null){
				
				//String imageNameToSave = systime.toString()+random.nextInt(IWebConstants.FILE_RANDOM_NO)+"."+fileType;

				File fileToCreate = new File(filePath, displayFileVO.getDisplayImageName());
				FileUtils.copyFile(imageForDisplay, fileToCreate);
				
			}
			
			
			
			 //Here We are saving the to uploaded_files folder 
			if(fileNamesList !=null && fileNamesList.size()>0)
			{
				for(int i=0;i<fileNamesList.size();i++)
				{
					File fileToCreate = new File(filePath, fileNamesList.get(i));
					FileUtils.copyFile(userImage.get(i), fileToCreate);
				}
			}
				//File fileToCreate = new File(filePath, fileName);
				
				//ERROR HERE
				//FileUtils.copyFile(userImage, fileToCreate);
			                         
				List<String> filepaths= new ArrayList<String>(fileVO.getFilePath());
				
				 if(fileVO.getFileVOForDiaplyImage()!=null && fileVO.getFileVOForDiaplyImage().getDisplayImagePath()!= null && !fileVO.getFileVOForDiaplyImage().getDisplayImagePath().equalsIgnoreCase(""))
				 {
				  filepaths.add(fileVO.getFileVOForDiaplyImage().getDisplayImagePath());  
				
				 }      
			result = candidateDetailsService.uploadAFile(fileVO);
			
			if(result.getResultCode() == ResultCodeMapper.SUCCESS){
				log.debug("fileuploades is sucess Method");
				inputStream = new StringBufferInputStream(SUCCESS);
			//logic to create tumbnails and resizing images
			   thumbnailService.crateThumnailDynamically(filepaths,IWebConstants.STATIC_CONTENT_FOLDER_URL,0,0);
			}
			else
				inputStream = new StringBufferInputStream("fail");
						
		}
		catch (Exception e) 
		{
			inputStream = new StringBufferInputStream("fail");
			log.error("Exception Occured in uploadFile() method, Exception is - "+e); 
		}
		return Action.SUCCESS;
	}
	
	
	
	/*

	public String execute()
	{
		request.setAttribute("candidateId",candidateId);
		
		session = request.getSession();
		
		if(session.getAttribute("candidatePageLoadingFirstTime")== null){
			if(contentId == null){
			   session.setAttribute("candidatePageLoadingFirstTime", "true");
			}
		}else{ 
			session.setAttribute("candidatePageLoadingFirstTime", "false");
		}
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		if(regVO != null)
		{
			isAdmin = regVO.getIsAdmin();
			Long regId = regVO.getRegistrationID();
			RegistrationVO regisVO =  candidateDetailsService.getStateAndConstituency(regId);
			stateId = regisVO.getStateId();
			constituencyId = regisVO.getConstituencyId();
			String page="cPage";
			isSubscribed = candidateDetailsService.getSubscriptionDetails(regId,candidateId,page);
	    }else
	    {
	    	stateId=null;
			constituencyId=null;
	    }
			
		descriptions= candidateDetailsService.getCandidateProfileDescriptionByCandidateID(candidateId);
		
		candidateVO = candidateDetailsService.getCandidateDetails(candidateId);
		
		electionGoverningBodyVO = electionLiveResultsAnalysisService.getAllCandidateDetailsForMinisterProfile(candidateId);
		
		candidateElectionDetails = candidateDetailsService.getCandidateElectionDetails(candidateId);
		
		StringBuffer candidateURLStringBuffer = new StringBuffer(IConstants.CANDIDATE_STATIC_PAGE_URL);
		
		if(candidateVO.getCandidateName().equalsIgnoreCase("Y S RAJASEKHAR REDDY") ||
				candidateVO.getCandidateName().equalsIgnoreCase("Nara Chandrababu Naidu"))
		{
			candidateURLStringBuffer.append(candidateVO.getCandidateName().replace(' ', '_'));
		}
		else
		{
			candidateURLStringBuffer.append("Default_Candidate");
		}
	
		candidateURLString = candidateURLStringBuffer.toString();
		
		request.setAttribute("candidateURLString", candidateURLString);
		
		fileVO = candidateDetailsService.getCandidateLatestVideos(candidateId,0,20);
		
		customPages   = candidateDetailsService.getCustomPagesOfACandidatePage(candidateId);
		
		if(candidateElectionDetails != null)
			return SUCCESS;
		else
			return ERROR;
	}*/
	
	public String getCandidatesPhotoGallaryDetail(){
		try  {
			jObj = new JSONObject(getTask());
			/*if(jObj.getString("task").equalsIgnoreCase("getCandidatePhotoGallaryDetail"))
			{
			    fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetail(jObj.getLong("candidateId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.PHOTO_GALLARY);
			}
			if(jObj.getString("task").equalsIgnoreCase("UpdateGallary"))
			{
			    fileVO2 = candidateDetailsService.getCandidatesGallaryDescForUpdate(jObj.getLong("gallaryId"),jObj.getLong("candidateId"));
			}
			if(jObj.getString("task").equalsIgnoreCase("updateFilesAndPhotos"))
			{
			    fileVO2 = candidateDetailsService.getPhotoUploadDescForUpdate(jObj.getLong("gallaryId"),jObj.getLong("fileId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidatesPhotoGallaryDetailWithOutGallerySizeZero"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetailWithOutGallerySizeZero(jObj.getLong("candidateId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.PHOTO_GALLARY);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPhotosInAGallary"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotosInAGallary(jObj.getLong("gallaryId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidateNewsGallaryDetail"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetail(jObj.getLong("candidateId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.NEWS_GALLARY);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getNewsInAGallary"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotosInAGallary(jObj.getLong("gallaryId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidateDevelopmentGallaryDetail"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetail(jObj.getLong("candidateId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.DEVELOPEMENT_ACTIVITIES);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getDevelopmentsInAGallary"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotosInAGallary(jObj.getLong("gallaryId"));
			}*/
			 if(jObj.getString("task").equalsIgnoreCase("getScopesForNewSearch"))
			{
				fileVO = candidateDetailsService.getScopesForNewSearch();
			}
			else if(jObj.getString("task").equalsIgnoreCase("getStates"))
			{
				fileVO = candidateDetailsService.getStateDetails();
			}
			else if(jObj.getString("task").equalsIgnoreCase("getDistrictsByStateId"))
			{
				fileVO = candidateDetailsService.getDistrictDetailsByStateId(jObj.getLong("stateId"));
			}/*
			else if(jObj.getString("task").equalsIgnoreCase("searchNewsDetails"))
			{    
				  FileVO inputs = new FileVO();
				      inputs.setCandidateId(jObj.getLong("candidateId"));			  
				  if(jObj.getString("keywords")!= null && jObj.getString("keywords").trim().length()>0 )
				      inputs.setKeywords(jObj.getString("keywords"));
				  if(jObj.getString("locationScope")!= null && jObj.getString("locationScope").trim().length()>0 )
					  inputs.setLocationScope(jObj.getLong("locationScope"));  
				  if(jObj.getString("location")!= null && jObj.getString("location").trim().length()>0 )
					  inputs.setLocation(jObj.getLong("location"));
				fileVO = candidateDetailsService.searchNewsDetails(inputs);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getFirstFourNewsRecordsToDisplay"))
			{
				fileVO = candidateDetailsService.getFirstFourNewsRecordsToDisplay(jObj.getLong("candidateId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getNewsToDisplay") )
			{
				//fileVOCount = candidateDetailsService.getNewsCountByScope(jObj.getLong("candidateId"),jObj.getString("queryType"));
				fileVO = candidateDetailsService.getAllNewsdetails(jObj.getLong("candidateId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),jObj.getString("queryType"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getAllNewsToDisplay") )
			{
				String startIndex    = request.getParameter("startIndex");
				String resultsCount  = request.getParameter("resultsCount");	
					
				fileVO = candidateDetailsService.getAllNewsdetails(jObj.getLong("candidateId"),Integer.parseInt(startIndex),Integer.parseInt(resultsCount),jObj.getString("queryType"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getFileByFileId"))
			{
				fileVO = candidateDetailsService.getFileByFileId(jObj.getLong("fileId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getFirstThreeImagesToDisplay"))
			{
				fileVO = candidateDetailsService.getFirstThreeImagesToDisplay(jObj.getLong("fileId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getFirstThreePhotoGallaryDetail"))
			{
				fileVO = candidateDetailsService.getFirstThreePhotoGallaryDetail(jObj.getLong("candidateId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("videoGalleriesForACandidate")){
				fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetail(jObj.getLong("candidateId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.VIDEO_GALLARY);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getVideosInGallary")){
				fileVO = candidateDetailsService.getAllVideosInAGalleryForACandidate(jObj.getLong("gallaryId"));
			}
			if(jObj.getString("task").equalsIgnoreCase("getspaecialPagePhotoGallaryDetails"))
			{
			    fileVO = candidateDetailsService.getSpecialPagePhotoGallaryDetail(jObj.getLong("specialPageId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.PHOTO_GALLARY);
			}
			if(jObj.getString("task").equalsIgnoreCase("specialPageUpdateGallary"))
			{
				fileVO2 = candidateDetailsService.getSpecialPageGallaryDescForUpdate(jObj.getLong("gallaryId"),jObj.getLong("specialPageId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getContentDetails"))
			{
				fileVO2 = candidateDetailsService.getContentDetails(jObj.getLong("contentId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("candidateVisibility"))
			{
				String value = candidateDetailsService.getCandidateGallaryVisibility(jObj.getLong("gallaryId"));
				FileVO fileValue = new FileVO();
				fileValue.setFileType(value);
				fileVO2 = fileValue;
			}
			else if(jObj.getString("task").equalsIgnoreCase("candidateNewsVisibility"))
			{
				String newsValue = candidateDetailsService.getCandidateGallaryVisibility(jObj.getLong("gallaryselectId"));
				FileVO fileValue =new FileVO();
				fileValue.setFileType(newsValue);
				fileVO2 = fileValue;
			}
			else if(jObj.getString("task").equalsIgnoreCase("candidatePhotoVisibility")){
				
				String photoValue = candidateDetailsService.getCandidateGallaryVisibility(jObj.getLong("gallaryphotoselectId"));
				FileVO fileValue = new FileVO();
				fileValue.setFileType(photoValue);
				fileVO2 = fileValue;
			}*/}
			catch(Exception e){
				e.printStackTrace();
			}
			
			
		
		return SUCCESS;
	}
	
	public String getCandidateGallaries(){
		
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");		
		
		String contentType = jObj.getString("contentType");
		
		selectOptionList = candidateDetailsService.getCandidateGallaries(1L , contentType);

		
		return Action.SUCCESS;
		
	}
	
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		/*if(jObj.getString("task").equalsIgnoreCase("createNewGallary") || 
				jObj.getString("task").equalsIgnoreCase("createVideoNewGallary"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setCandidateId(jObj.getLong("candidateId"));
			gallaryVO.setUserId(regVO.getRegistrationID());
			gallaryVO.setGallaryName(jObj.getString("name"));
			gallaryVO.setDescription(jObj.getString("desc"));
			gallaryVO.setVisibility(jObj.getString("visibility"));
			if(jObj.getString("createOrUpdate").trim().equalsIgnoreCase("Create"))
			{
				gallaryVO.setContentType(jObj.getString("contentType"));
			}
			if(jObj.getString("createOrUpdate").trim().equalsIgnoreCase("Update"))
			{
				gallaryVO.setGallaryId(jObj.getLong("gallaryId"));
			}
			result = candidateDetailsService.createNewGallaryOrUpdateGallary(gallaryVO,jObj.getString("createOrUpdate").trim());
		}
		else if(jObj.getString("task").equalsIgnoreCase("candidateGallariesForUplaod"))
		{
			selectOptionList = candidateDetailsService.getCandidateGallarySelectList(jObj.getLong("candidateId"),jObj.getString("contentType"));
		}*/
		 if(jObj.getString("task").equalsIgnoreCase("getSource"))
		 {
			selectOptionList = candidateDetailsService.getSource();
		 }
		else if(jObj.getString("task").equalsIgnoreCase("getLanguage"))
		 {
			selectOptionList = candidateDetailsService.getLanguage();
		 }
		else if(jObj.getString("task").equalsIgnoreCase("getNewsImportance"))
		 {
			selectOptionList = candidateDetailsService.getNewsImportance();
		 }
		else if(jObj.getString("task").equalsIgnoreCase("getCategory"))
		 {
			selectOptionList = candidateDetailsService.getCategory();
		 }/*
		else if(jObj.getString("task").equalsIgnoreCase("updateProfileDiscription"))
		{
			List<Long> orderNo = new ArrayList<Long>();
			List<String> description = new ArrayList<String>();
			List<Long> condiProfDescId = new ArrayList<Long>();
			gallaryList =new ArrayList<GallaryVO>();
			JSONArray jOrderNo = jObj.getJSONArray("orderNoArr");
			JSONArray jDescription = jObj.getJSONArray("descriptionArr");
			JSONArray jprofDescId = jObj.getJSONArray("profDescIdArr");
			Long candidateId = jObj.getLong("candidateId");
			try{
			    
			    for (int i = 0; i < jOrderNo.length(); i++) {
				    orderNo.add(new Long(jOrderNo.get(i).toString()));
				    description.add(jDescription.get(i).toString());
				    condiProfDescId.add(new Long(jprofDescId.get(i).toString()));
			      }
			    for (int i = 0; i < jOrderNo.length(); i++) {
				   GallaryVO gallary = new GallaryVO();
				   gallary.setCandidateId(orderNo.get(i));
				   gallary.setDescription(description.get(i));
				   gallary.setUserId(condiProfDescId.get(i));
				   gallaryList.add(gallary);
			      }
			      result = candidateDetailsService.updateProfileDescription(gallaryList,candidateId);
			 }catch(Exception e)
			       {
				e.printStackTrace();
			    }
		}
		else if(jObj.getString("task").equalsIgnoreCase("getCandidateLatestVideos"))
		{
			fileVO = candidateDetailsService.getCandidateLatestVideos(jObj.getLong("candidateId"),jObj.getInt("startIndex"),jObj.getInt("maxRecords"));
		}*/
		else if(jObj.getString("task").equalsIgnoreCase("candiadteVideoGallariesForUplaod"))
		{
			List<String> filePathList = new ArrayList<String>();
			List<Long> videoSourceIds = new ArrayList<Long>();
			List<Long> videoSourceLangIds = new ArrayList<Long>();
			
			FileVO fileVOObj = new FileVO();
			//fileVOObj.setPath(jObj.getString("paths"));
			fileVOObj.setContentType("video");
			fileVOObj.setTitle(jObj.getString("videoTitle"));
			fileVOObj.setDescription(jObj.getString("videoDescription"));
			fileVOObj.setKeywords(jObj.getString("keywords"));
			//fileVOObj.setSourceId(jObj.getLong("sourceId"));
			//fileVOObj.setLanguegeId(jObj.getLong("languageId"));
			fileVOObj.setFileDate(jObj.getString("fileDate"));
			fileVOObj.setGallaryId(jObj.getLong("gallaryId"));
			fileVOObj.setVisibility(jObj.getString("visibility"));
			JSONArray filePaths = jObj.getJSONArray("paths");
			JSONArray srcLangIds = jObj.getJSONArray("languageId");
			JSONArray sourceIds = jObj.getJSONArray("sourceId");
			for(int i=0;i<filePaths.length();i++)
			{
				filePathList.add(filePaths.get(i).toString());
			}
			for(int i=0;i<sourceIds.length();i++)
			{
				videoSourceIds.add(new Long(sourceIds.get(i).toString()));
			}
			for(int i=0;i<srcLangIds.length();i++)
			{
				videoSourceLangIds.add(new Long(srcLangIds.get(i).toString()));
			}
			if(!jObj.getString("SPGalleryId").equalsIgnoreCase("") && jObj.getString("SPGalleryId").length()>0)
			{
				JSONArray spGalIds = jObj.getJSONArray("SPGalleryId");
				for(int i=0;i<spGalIds.length();i++)
				galleryIds.add(new Long(spGalIds.get(i).toString()));
			}
							
			if(!jObj.getString("partyGalleryId").equalsIgnoreCase("") && jObj.getString("partyGalleryId").length()>0)
			{
				JSONArray partyGalIds = jObj.getJSONArray("partyGalleryId");
				for(int i=0;i<partyGalIds.length();i++)
				galleryIds.add(new Long(partyGalIds.get(i).toString()));
			}
			if(!jObj.getString("canGalleryId").equalsIgnoreCase("") && jObj.getString("canGalleryId").length()>0)
			{
				JSONArray canGalIds = jObj.getJSONArray("canGalleryId");
				for(int i=0;i<canGalIds.length();i++)
				galleryIds.add(new Long(canGalIds.get(i).toString()));
			}
			fileVOObj.setFilePath(filePathList);
			fileVOObj.setUploadOtherProfileGalleryIds(galleryIds);
			fileVOObj.setSourceLangIds(videoSourceLangIds);
			fileVOObj.setFileSource(videoSourceIds);
			result = candidateDetailsService.uploadAFile(fileVOObj);
		}/*
		else if(jObj.getString("task").equalsIgnoreCase("saveDiscription"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setCandidateId(jObj.getLong("candidateId"));
			gallaryVO.setDescription(jObj.getString("fileDesc"));
			result = candidateDetailsService.saveDescription(gallaryVO);
		}
		else if(jObj.getString("task").equalsIgnoreCase("candiadteDescriptionUpdate"))
		{
			gallaryList = candidateDetailsService.getCandidateProfileInfo(jObj.getLong("candidateId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("saveMessage"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setCandidateId(jObj.getLong("candidateId"));
			gallaryVO.setGallaryName(jObj.getString("name"));
			gallaryVO.setGallaryId(jObj.getLong("stateSelect"));
			gallaryVO.setUserId(jObj.getLong("constituencySelect"));
			gallaryVO.setDescription(jObj.getString("message"));
			gallaryVO.setIsPrivate(jObj.getString("isprivate"));
			result = candidateDetailsService.saveMessage(gallaryVO);
		}
		else if(jObj.getString("task").equalsIgnoreCase("saveMessageToParty"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setPartyId(jObj.getLong("partyId"));
			gallaryVO.setGallaryName(jObj.getString("name"));
			gallaryVO.setGallaryId(jObj.getLong("stateSelect"));
			gallaryVO.setUserId(jObj.getLong("constituencySelect"));
			gallaryVO.setDescription(jObj.getString("message"));
			gallaryVO.setIsPrivate(jObj.getString("isprivate"));
			result = partyDetailsService.savePartyMessageFromPeople(gallaryVO);
		}
		else if(jObj.getString("task").equalsIgnoreCase("deleteDiscription"))
		{	
			result = candidateDetailsService.deleteProfileDescById(jObj.getLong("profDescId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("deleteFilesAndPhotos"))
		{	
			result = candidateDetailsService.deleteFilesAndPhotos(jObj.getLong("fileId"),jObj.getLong("gallaryId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("deleteGallary"))
		{	
			result = candidateDetailsService.deleteGallary(jObj.getLong("gallaryId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("deletePartyGallary"))
		{
			result = candidateDetailsService.deletePartyGallary(jObj.getLong("gallaryId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("updateIndividualPhotoDetails"))
		{	
			FileVO fileVO = new FileVO();
			fileVO.setFileId(jObj.getLong("fileId"));
			fileVO.setFileTitle1(jObj.getString("title"));
			fileVO.setDescription(jObj.getString("desc"));
			fileVO.setFileTypeId(jObj.getLong("fileGallaryId"));
			fileVO.setGallaryId(jObj.getLong("gallaryId"));
			fileVO.setFileType(jObj.getString("visibility"));
			result = candidateDetailsService.updateIndividualPhoto(fileVO);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getUserMessages"))
		{
			Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
			Integer resultsCount = Integer.parseInt(request.getParameter("resultsCount"));
			candidateCommentsVO = candidateDetailsService.getUserMessages(jObj.getLong("candidateId"),startIndex,resultsCount);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getPartyMessages"))
		{
			String startIndex    = request.getParameter("startIndex");
			String resultsCount  = request.getParameter("resultsCount");
			candidateCommentsVO = partyDetailsService.getMessageToParty(jObj.getLong("partyId"),Integer.parseInt(startIndex),Integer.parseInt(resultsCount));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getCandidates"))
		{
			selectOptionList = candidateDetailsService.getCandidatesOfAUser(regVO.getRegistrationID());
		}
		else if(jObj.getString("task").trim().equalsIgnoreCase("getMinisterProfile"))
		{
			electionGoverningBodyVo = electionLiveResultsAnalysisService.getAllCandidateDetailsForMinisterProfile(jObj.getLong("candidateId"));
		}*/
		return Action.SUCCESS;
	}
	
	
	/*
 public String checkForMinisterData(){
	 
	 try{	 
	 jObj = new JSONObject(getTask());  
	 }catch(Exception e){
		 e.printStackTrace(); 
	 }
	 
	 String electionType = jObj.getString("electionType");;
	 Long electionId =jObj.getLong("electionId");
	 
	 
      status = candidateDetailsService.checkForMinisterData(electionType,electionId);

	 return Action.SUCCESS;
	 
	 
 }
     
   public String getCandidatesNewsDetail()
	{
	 try  
	  {
		jObj = new JSONObject(getTask());  
		if(jObj.getString("task").equalsIgnoreCase("getNewsCountByScope"))
		 {
			FileVO status = new FileVO();
			fileVO = candidateDetailsService.getNewsCountByScope(jObj.getLong("candidateId"),jObj.getString("queryType"));
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO!=null)
			{
				if(session.getAttribute("UserType")=="PartyAnalyst")
				{
					Long count = candidateDetailsService.getUserCandidateRelationCount(regVO.getRegistrationID(),jObj.getLong("candidateId"));
					if(count>0L)
					{
						status.setVisibility("True");
						fileVO.add(status);
					}
					else
					{
						status.setVisibility("False");
						fileVO.add(status);
					}
				}
				else
				{
					status.setVisibility("False");
					fileVO.add(status);
				}
			}
			else
			{
				status.setVisibility("False");
				fileVO.add(status);
			}
		 }
		else if(jObj.getString("task").equalsIgnoreCase("getNewsByScope"))
		 {
			Long scopeType=null;
			if(jObj.getString("scopeType").trim().length()>0)
				scopeType = jObj.getLong("scopeType");
			String startIndex = request.getParameter("startIndex");
			String maxResults = request.getParameter("resultsCount");
			fileVO = candidateDetailsService.getNewsByScope(jObj.getLong("candidateId"),scopeType,Integer.parseInt(startIndex),Integer.parseInt(maxResults),jObj.getString("queryType"));
		 }
		else if(jObj.getString("task").equalsIgnoreCase("getNewsBySourceScope"))
		 {
			Long scopeType=null;
			String source = null;
			String startIndex = request.getParameter("startIndex");
			String maxResults = request.getParameter("resultsCount");
			if(jObj.getString("scopeType").trim().length()>0)
				scopeType = jObj.getLong("scopeType");
			if(jObj.getString("source").trim().length()>0)
				source = jObj.getString("source");
			fileVO = candidateDetailsService.getNewsBySource(jObj.getLong("candidateId"),scopeType,Integer.parseInt(startIndex),Integer.parseInt(maxResults),jObj.getString("queryType"),source);
		 }
		else if(jObj.getString("task").equalsIgnoreCase("getNewsByLanguageScope"))
		 {
			Long scopeType=null;
			String language = null;
			String startIndex = request.getParameter("startIndex");
			String maxResults = request.getParameter("resultsCount");
			if(jObj.getString("scopeType").trim().length()>0)
				scopeType = jObj.getLong("scopeType");
			if(jObj.getString("language").trim().length()>0)
				language = jObj.getString("language");
			fileVO = candidateDetailsService.getNewsByLanguage(jObj.getLong("candidateId"),scopeType,Integer.parseInt(startIndex),Integer.parseInt(maxResults),jObj.getString("queryType"),language);
		 }
		else if(jObj.getString("task").equalsIgnoreCase("getOtherNews"))
		 {
			String startIndex = jObj.getString("startIndex");
			String maxResults =  jObj.getString("maxResults");
			fileVO = candidateDetailsService.getOtherNews(jObj.getLong("candidateId"),Integer.parseInt(startIndex),Integer.parseInt(maxResults),jObj.getString("queryType"));
		 }
		else if(jObj.getString("task").equalsIgnoreCase("getNewsByCategoryScope"))
		 {
			Long scopeType=null;
			String category = null;
			String startIndex = request.getParameter("startIndex");
			String maxResults = request.getParameter("resultsCount");
			if(jObj.getString("scopeType").trim().length()>0)
				scopeType = jObj.getLong("scopeType");
			if(jObj.getString("category").trim().length()>0)
				category = jObj.getString("category");
			fileVO = candidateDetailsService.getNewsByCategory(jObj.getLong("candidateId"),scopeType,Integer.parseInt(startIndex),Integer.parseInt(maxResults),jObj.getString("queryType"),category);
		 }
		else if(jObj.getString("task").equalsIgnoreCase("getNewsByNewsImportance"))
		 {
			Long scopeType=null;
			String newsImportance = null;
			String startIndex = request.getParameter("startIndex");
			String maxResults = request.getParameter("resultsCount");
			if(jObj.getString("scopeType").trim().length()>0)
				scopeType = jObj.getLong("scopeType");
			if(jObj.getString("newsImportance").trim().length()>0)
				newsImportance = jObj.getString("newsImportance");
			fileVO = candidateDetailsService.getNewsByNewsImportance(jObj.getLong("candidateId"),scopeType,Integer.parseInt(startIndex),Integer.parseInt(maxResults),jObj.getString("queryType"),newsImportance);
		 }
		}
	 catch(Exception e)
	  {
		e.printStackTrace();
	  }
		return SUCCESS;
   }
	public String setEmailAlertsForUser(){
		
		try {
			jObj = new JSONObject(getTask());
			String category=jObj.getString("page");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			
			 if(jObj.getString("task").equalsIgnoreCase("subscriptionDetails"))
			 {
				 
				 if(regVO != null){
					 Long id=jObj.getLong("id");
					 Long userId=regVO.getRegistrationID();
					 result = candidateDetailsService.subscriberDetails(id,userId,category);
				 }
			 }
			  else if(jObj.getString("task").equalsIgnoreCase("unsubscriptionDetails"))
			  {
				  if(regVO != null)
				  {
					  Long id=jObj.getLong("id");
					  Long userId=regVO.getRegistrationID();
					  result = candidateDetailsService.unSubscriptionDetails(id,userId,category);
				  }
			  }
			 else{
				String email = jObj.getString("emailId");
				result = candidateDetailsService.subScribeEmailAlertForAUser(email,jObj.getLong("candidateId"));
			 }
			} catch (ParseException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String ajaxHandlerForThumbnails()
	{
		int[] ids= new int[]{1,2,5,10,11,24,25,35};
	
		  
		status=thumbnailService.crateThumnailForAdmin(ids,IWebConstants.STATIC_CONTENT_FOLDER_URL);
		
		
	
		
		return Action.SUCCESS;
	}
	
	
	public String saveFileCommentAction(){
		 try{	 
			 jObj = new JSONObject(getTask());  
			 }catch(Exception e){
				 e.printStackTrace(); 
			 }
		 
		 
		 Long fileId = jObj.getLong("fileId");
		 String comment = jObj.getString("comment");
		 
		 status = candidateDetailsService.saveFileComment(fileId,comment);
		 
		return Action.SUCCESS;
		
		
	}
	
	
	public String getGallariesByCategory(){
		
		try{	 
			 jObj = new JSONObject(getTask());  
			 }catch(Exception e){
				 e.printStackTrace(); 
			 }
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		
		Long categoryId = jObj.getLong("categoryId");
		Long registrationId = regVO.getRegistrationID();
		
		selectOptionList = candidateDetailsService.getCandidateGallariesByCategory(categoryId ,registrationId );
		
		return Action.SUCCESS;
		
	}
	
	
	public String getAllGallariesForUser(){
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");		
		
		
		selectOptionList = candidateDetailsService.getCandidateGallaries(regVO.getRegistrationID() , "News Gallary");
		sourceList = newsMonitoringService.getAllSourceDetails();
		newsImportanceList = newsMonitoringService.getAllNewsImportanceDetails();
		regionsScopesList = newsMonitoringService.getAllRegionScopes();
		categoryList = newsMonitoringService.getAllCategoryDetails();
		
		return Action.SUCCESS;
		
	}	
	
	public String getFilesInAGallary(){
		
		try{	 
			 jObj = new JSONObject(getTask());  
			 }catch(Exception e){
				 e.printStackTrace(); 
			 }
		
		Long  gallaryId = jObj.getLong("gallaryId");
		
		selectOptionList = candidateDetailsService.getFilesOfAGallary(gallaryId);
		
		return Action.SUCCESS;
		
	}
	
	
	public String callAjaxToGeneratePdfFileForAgallary(){
		
		log.debug("Entered into the callAjaxToGeneratePdfFileForAgallary method");
		
		try{	 
			 jObj = new JSONObject(getTask());  
			
		
		
		String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		String filePath = null;
		
		pdfGenerationVO = new PdfGenerationVO();
		
		if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE)){
			//filePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + IConstants.GALLARY_PDF_FILES + pathSeperator;
			filePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + IConstants.GALLARY_PDF_FILES ;
			pdfGenerationVO.setImagePath(IWebConstants.STATIC_CONTENT_FOLDER_URL );
		}
		else{
			//filePath = context.getRealPath("/")+IConstants.GALLARY_PDF_FILES + pathSeperator;
			filePath = context.getRealPath("/")+IConstants.GALLARY_PDF_FILES ;
			pdfGenerationVO.setImagePath(context.getRealPath("/"));
		}
		
		
		
		File file = new File(filePath);
		if (!file.exists())
			file.mkdir();
		
		
		
		pdfGenerationVO.setGallaryId(jObj.getLong("gallaryId"));
		pdfGenerationVO.setGallaryName(jObj.getString("gallaryName"));
		pdfGenerationVO.setSourceId(jObj.getLong("sourceId"));
		pdfGenerationVO.setCategoryId(jObj.getLong("categoryId"));
		pdfGenerationVO.setImpactLevelId(jObj.getLong("impactLevelId"));
		pdfGenerationVO.setImportanceId(jObj.getLong("importanceId"));
		pdfGenerationVO.setLanguageId(jObj.getLong("languageId"));
		pdfGenerationVO.setBetweenDates(jObj.getString("betweenDates"));
		pdfGenerationVO.setStartDate(jObj.getString("startDate"));
		pdfGenerationVO.setEndDate(jObj.getString("endDate"));
		pdfGenerationVO.setAllFiles(jObj.getString("allFiles"));
		
		
		pdfGenerationVO.setFilePathToSave(filePath);
		
		pdfGenerationVO  = candidateDetailsService.generatePdfForAGallary(pdfGenerationVO);
		
		session = request.getSession();
		
		//String fileName = pdfGenerationVO.getPdfName().replaceAll("", "_");
		
		session.setAttribute("pdfPath", pdfGenerationVO.getPdfPath());
		//session.setAttribute("pdfName", pdfGenerationVO.getPdfName());
		
		session.setAttribute("pdfName", pdfGenerationVO.getPdfName());
		
		
		
		 }catch(Exception e){
			 log.error("Exception raised in callAjaxToGeneratePdfFileForAgallary method :"+e);
			 e.printStackTrace(); 
		}
		
		return Action.SUCCESS;
		
	}
	
	
	public String generatePdfForAGallary() throws Exception{
		
		log.debug("Entered into thegeneratePdfForAGallary method ");
		
		try{	 
		
		try{	 
			 jObj = new JSONObject(getTask());  
			 }catch(Exception e){
				 e.printStackTrace(); 
			 }
		
		//Long  fileId = jObj.getLong("gallaryId");
		
	//String filePath = candidateDetailsService.generatePdfForAGallary(fileId);
		
		// pdfPath = request.getParameter("pdfPath");
		// pdfName = request.getParameter("pdfName");
		
		session = request.getSession();
		pdfPath = session.getAttribute("pdfPath").toString();
		pdfName = session.getAttribute("pdfName").toString()+".pdf";
		
		
		session.removeAttribute("pdfPath");
		session.removeAttribute("pdfName");
	
	fileInputStream = new FileInputStream(new File(pdfPath));
	
		}catch(Exception e){
			
			log.error("Exception raised in generatePdfForAGallary method :"+e);
			e.printStackTrace();
			
		}
		
      return SUCCESS;		
		
	}
	

	
	public String getNewsCountForALocationByCategory(){
		
		
		 try{	 
			 jObj = new JSONObject(getTask());
			 
		   }catch(Exception e){
				 e.printStackTrace(); 
		   }
		 
		 Long locationValue = jObj.getLong("locationValue");
		 Long locationId = jObj.getLong("locationId");
		 Long publicationId = jObj.getLong("publicationId");
		
		 session = request.getSession();
		 RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		 
		
		 
		newsCountByCategoryList = newsMonitoringService
				.getNewsCountForALocationByCategory(regVO.getRegistrationID(),
						locationValue, locationId, publicationId);

		return Action.SUCCESS;
		
	}
	
	
	public String getNewsByLocationAndCategory(){
		
		try{	 
			 jObj = new JSONObject(getTask());
			 
		   }catch(Exception e){
				 e.printStackTrace(); 
		   }
		
		 session = request.getSession();
		 RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		 
		 
		 FileVO fileVO = new FileVO();
		
		
		Long locationValue = jObj.getLong("locationValue");
		Long locationId = jObj.getLong("locationId");
		Long publicationId = jObj.getLong("publicationId");
		Long importanceId = jObj.getLong("importanceId");
		Long categoryId = jObj.getLong("categoryId");
		Integer startIndex = jObj.getInt("startIndex");
		Integer lastIndex = jObj.getInt("lastIndex");
		
		
		fileVO.setUserId(regVO.getRegistrationID());
		fileVO.setLocationVal(locationValue);
		fileVO.setLocationId(locationId);
		fileVO.setPublicationId(publicationId);
		fileVO.setImportanceId(importanceId);
		fileVO.setCategoryId(categoryId);
		fileVO.setStartIndex(startIndex);
		fileVO.setMaxResult(lastIndex);		
		
		allNewsList = newsMonitoringService.getNewsByLocationAndCategory(fileVO);
		return Action.SUCCESS;
		
	}
	
	
		public String getNewsByLocationAndCategoryInPopup(){
		
		try{	 
			 jObj = new JSONObject(getTask());
			 
		   }catch(Exception e){
				 e.printStackTrace(); 
		   }
		
		 session = request.getSession();
		 RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		 
		 
		 FileVO fileVO = new FileVO();
		
		
		Long locationValue = jObj.getLong("locationValue");
		Long locationId = jObj.getLong("locationId");
		//Long publicationId = jObj.getLong("publicationId");
		Long importanceId = jObj.getLong("imprtanceId");
		Long categoryId = jObj.getLong("ctgryId");
		Long contentId=jObj.getLong("contentid");
		String requestFrom=jObj.getString("requestFrom");
		Long requestPageId=jObj.getLong("requestPageId");
		String isCustomer=jObj.getString("isCustomer");
		
		
		boolean tmpvar=true;
		
		
		fileVO.setUserId(regVO.getRegistrationID());
		fileVO.setLocationVal(locationValue);
		fileVO.setLocationId(locationId);
		//fileVO.setPublicationId(publicationId);
		fileVO.setImportanceId(importanceId);
		fileVO.setCategoryId(categoryId);
		fileVO.setTempvar(tmpvar);
		fileVO.setContentId(contentId);
		fileVO.setRequestFrom(requestFrom);
		fileVO.setRequestPageId(requestPageId);
		fileVO.setIsCustomer(isCustomer);
		
		
		
		allNewsListForPopup = newsMonitoringService.getNewsByLocationAndCategoryInPopup(fileVO);
		
		return Action.SUCCESS;
		
	}
	
	
	
	
	
	
	public String saveContentNotesByContentId(){
		
		log.debug("Entered into the saveCommentBYContentId method");
		
		try{
			try{	 
				 jObj = new JSONObject(getTask());
				 
			   }catch(Exception e){
					 e.printStackTrace(); 
			   }
			  Long contentId = jObj.getLong("contentId");
			  String comment = jObj.getString("comment");
			  session = request.getSession();
			  RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			
			savedCommentId = newsMonitoringService.saveContentNotesByContentId(contentId , comment);
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("Exception raised in saveCommentByContentId method :"+e);
		}
		
		return Action.SUCCESS;
	}
	
	public String removeContentNotes(){
		
		log.debug("Entered into the removeCommentByContentId method");
		try{
			
			try{	 
				 jObj = new JSONObject(getTask());
				 
			   }catch(Exception e){
					 e.printStackTrace(); 
			   }
			
			Long conentNotesId = jObj.getLong("conentNotesId");
			
			status = newsMonitoringService.removeContentNotes(conentNotesId);
			
		}catch(Exception e){
			log.debug("Exception raised in removeCommentByContentId method :"+e);
			e.printStackTrace();			
		}
		
		return Action.SUCCESS;
		
	}
	
	
	public String getContentNotesByContentId(){
		
		log.debug("Entered into the getCommnetsByContentId method");
		
		try{		
			try{	 
				 jObj = new JSONObject(getTask());
				 
			   }catch(Exception e){
					 e.printStackTrace(); 
			   }
			  Long contentId = jObj.getLong("contentId");
			  session = request.getSession();
			  RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			 
			 commentsList = newsMonitoringService.getContentNotesByContentId(contentId,regVO.getRegistrationID());
		}catch(Exception e){
			log.error("Exception raised in getCommnetsByContentId method :"+e);
			e.printStackTrace();			
		}
		return Action.SUCCESS;
		
	}
	
	
	public String addFlagToNews(){
		
		log.debug("Entered into the addFlagToNews method");
		try{
			
			try{	 
				 jObj = new JSONObject(getTask());
				 
			   }catch(Exception e){
					 e.printStackTrace(); 
			   }
			session = request.getSession();
			
			Long contentId = jObj.getLong("contentId");
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			
			status = newsMonitoringService.addFlagToNews(contentId ,regVO.getRegistrationID());
			
			
		}catch(Exception e){
			log.error("Exception raised in addFlagToNews method");
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
		
	}
	
	
	public String checkForFlag(){
		log.debug("Entered into the checkForFlag method");
		
		try{
			

			try{	 
				 jObj = new JSONObject(getTask());
				 
			   }catch(Exception e){
					 e.printStackTrace(); 
			   }
			
			
			Long contentId = jObj.getLong("contentId");
			
			status = newsMonitoringService.checkForFlag(contentId);
			
		}catch(Exception e){
		log.error("Exception raised in checkForFlag method");
			e.printStackTrace();
			
		}
		
		return Action.SUCCESS;
	}
	
	public String removeFlagForNews(){
		log.debug("Entered into the removeFlagForNews method");
		
		try{
			try{	 
				 jObj = new JSONObject(getTask());
				 
			   }catch(Exception e){
					 e.printStackTrace(); 
			   }
			
			Long contentId = jObj.getLong("contentId");
			
			status = newsMonitoringService.removeFlagForNews(contentId);
			
		}catch(Exception e){
		log.error("Exception raised in removeFlagForNews method");
			e.printStackTrace();
			
		}
		
		return Action.SUCCESS;
	}
	
	public String updateVisibility(){
		log.debug("Entered into the updateVisibility method");
		
		try{
			try{	 
				 jObj = new JSONObject(getTask());
				 
			   }catch(Exception e){
					 e.printStackTrace(); 
			   }
			
			Long contentId = jObj.getLong("contentId");
			String visibility = jObj.getString("visibility");
			
			status = newsMonitoringService.updateVisibility(contentId , visibility);
			
		}catch(Exception e){
		log.error("Exception raised in updateVisibility method");
			e.printStackTrace();
			
		}
		
		return Action.SUCCESS;
	}
	
	
	public String checkForVisibilityStatus(){
		log.debug("Entered into the checkForVisibilityStatus method");
		
		try{
			try{	 
				 jObj = new JSONObject(getTask());
				 
			   }catch(Exception e){
					 e.printStackTrace(); 
			   }
			
			Long contentId = jObj.getLong("contentId");
			
			status = newsMonitoringService.checkForVisibilityStatus(contentId);
			
		}catch(Exception e){
		log.error("Exception raised in checkForVisibilityStatus method");
			e.printStackTrace();
			
		}
		
		return Action.SUCCESS;
	}

*/
	
	public String getAllTheGallariesOfAparty()
	{
		try{
			
			int startIndex = 1 ;
			int endIndex = 30;
			if(getCategory() == null || getCategory().equals(0l)  )
			fileVO2 = partyDetailsService.getAllTheGallariesOfAparty(872L,startIndex,endIndex);
			else
			fileVO2 = partyDetailsService.getAllTheGallariesForCategory(getCandidateId(), 0, 30, getCategory());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
		
	}
	



		public String getFilesInAGallary(){				
			
			
			Long  gallaryId = Long.parseLong(request.getParameter("gallaryId"));
			int startIndex = Integer.parseInt(request.getParameter("startIndex"));
			int endIndex = Integer.parseInt(request.getParameter("endIndex"));
			
			fileVO = candidateDetailsService.getFilesOfAGallary(gallaryId,startIndex,endIndex);
			
			return Action.SUCCESS;
				
	  }
		
		
	public String showAllFilesOfAGallary()
	{
		
		
		return Action.SUCCESS;
		
	}
			
	
	
	public String showNewsGallariesAction()
	{
		return Action.SUCCESS;
		
	}
	
	public String getVideosForSelectedGallery(){				
		
		
		Long fileid = Long.parseLong(request.getParameter("fileId"));
		
		fileVO = candidateDetailsService.getVideosListForSelectedFile(fileid);
		
		return Action.SUCCESS;
			
  }

//	String s = StringEscapeUtils.unescapeHtml("&#3129;&#3144;&#3110;&#3120;&#3134;&#3116;&#3134;&#3110;&#3149;");
//	System.out.println(s);
//	String ss= escapeUnicode(s);
//	System.out.println(ss);
//	}
	public String escapeUnicode(String input) {
		  StringBuilder b = new StringBuilder(input.length());
		  Formatter f = new Formatter(b);
		  for (char c : input.toCharArray()) {
		    if (c < 128) {
		      b.append(c);
		    } else {
		      f.format("\\u%04x", (int) c);
		    }
		  }
		  return b.toString();
		}
	public Long getFileId() {
		return fileId;
	}


	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	

}
