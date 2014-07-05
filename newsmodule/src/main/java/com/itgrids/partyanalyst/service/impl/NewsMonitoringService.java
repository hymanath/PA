package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidateNewsResponseDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyKeywordDAO;
import com.itgrids.partyanalyst.dao.ICandidateRelatedNewsDAO;
import com.itgrids.partyanalyst.dao.ICategoryDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IContentNotesDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDesignationDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IFileKeywordDAO;
import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IGallaryKeywordDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IKeywordDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMainCategoryDAO;
import com.itgrids.partyanalyst.dao.INewsFlagDAO;
import com.itgrids.partyanalyst.dao.INewsImportanceDAO;
import com.itgrids.partyanalyst.dao.INewsReportDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IReportFilesDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.dao.hibernate.FileDAO;
import com.itgrids.partyanalyst.dto.CandidateNewsCountVO;
import com.itgrids.partyanalyst.dto.CandidatePartyDestinationVO;
import com.itgrids.partyanalyst.dto.FileSourceVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsCountVO;
import com.itgrids.partyanalyst.dto.NewsDetailsVO;
import com.itgrids.partyanalyst.dto.NewsEditVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidatePartyFile;
import com.itgrids.partyanalyst.model.Category;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.model.GallaryKeyword;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.NewsFlag;
import com.itgrids.partyanalyst.model.NewsImportance;
import com.itgrids.partyanalyst.model.NewsReport;
import com.itgrids.partyanalyst.model.ReportFiles;
import com.itgrids.partyanalyst.model.Source;
import com.itgrids.partyanalyst.model.SourceLanguage;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.INewsMonitoringService;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.service.IReportService;
import com.itgrids.partyanalyst.utils.CommonStringUtils;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
/**
 * @author ITGRIDS
 *
 */
public class NewsMonitoringService implements INewsMonitoringService {
	
	private static final Logger log = Logger.getLogger(NewsMonitoringService.class);
	
	private IFileGallaryDAO fileGallaryDAO;
	private ICandidateDetailsService candidateDetailsService;
	private INewsFlagDAO newsFlagDAO;
	private IContentNotesDAO contentNotesDAO;
    private ISourceDAO sourceDAO;
    private ICategoryDAO categoryDAO;
    private ISourceLanguageDAO sourceLanguageDAO;
    private INewsImportanceDAO newsImportanceDAO;
    private FileDAO fileDAO;
    private IFileSourceLanguageDAO fileSourceLanguageDAO;
    private IGallaryDAO gallaryDAO;
    private IRegionScopesDAO regionScopesDAO;
    private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
    private ICandidateRelatedNewsDAO candidateRelatedNewsDAO;
    private IUserDAO userDAO;
    private IDistrictDAO districtDAO;
    private ICandidateNewsResponseDAO candidateNewsResponseDAO;
    private TransactionTemplate transactionTemplate = null;
    private IUserAddressDAO userAddressDAO;
	private INewsReportDAO newsReportDAO;
	private IReportFilesDAO reportFilesDAO ;
    private IGallaryKeywordDAO gallaryKeywordDAO;
    private IKeywordDAO keywordDAO;
    private IFileKeywordDAO fileKeywordDAO;
    private IMainCategoryDAO mainCategoryDAO;
    private ICandidatePartyFileDAO candidatePartyFileDAO;
    private INominationDAO nominationDAO;
    private ICandidatePartyDAO candidatePartyDAO;
    private ICandidateDAO candidateDAO;
    private IPartyDAO partyDAO;
    private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
    private IDesignationDAO designationDAO;
    private IConstituencyDAO constituencyDAO;
    private IStateDAO stateDAO;
    private ICandidatePartyCategoryDAO candidatePartyCategoryDAO;
    private ICandidatePartyKeywordDAO candidatePartyKeywordDAO;
    private IFilePathsDAO filePathsDAO;
    private ITehsilDAO tehsilDAO;
    private ILocalElectionBodyDAO localElectionBodyDAO;
    private IWardDAO wardDAO;
    private IBoothDAO boothDAO;
    private IHamletDAO hamletDAO;
    private IPartyDetailsService partyDetailsService;
    private IReportService reportService;

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IWardDAO getWardDAO() {
		return wardDAO;
	}

	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public IFilePathsDAO getFilePathsDAO() {
		return filePathsDAO;
	}

	public void setFilePathsDAO(IFilePathsDAO filePathsDAO) {
		this.filePathsDAO = filePathsDAO;
	}

	public ICandidatePartyKeywordDAO getCandidatePartyKeywordDAO() {
	return candidatePartyKeywordDAO;
	}

	public void setCandidatePartyKeywordDAO(
		ICandidatePartyKeywordDAO candidatePartyKeywordDAO) {
	this.candidatePartyKeywordDAO = candidatePartyKeywordDAO;
	}

	public ICandidatePartyCategoryDAO getCandidatePartyCategoryDAO() {
		return candidatePartyCategoryDAO;
	}

	public void setCandidatePartyCategoryDAO(
			ICandidatePartyCategoryDAO candidatePartyCategoryDAO) {
		this.candidatePartyCategoryDAO = candidatePartyCategoryDAO;
	}

	public IPartyDetailsService getPartyDetailsService() {
		return partyDetailsService;
	}

	public void setPartyDetailsService(IPartyDetailsService partyDetailsService) {
		this.partyDetailsService = partyDetailsService;
	}

	/**
	 * @param stateDAO the stateDAO to set
	 */
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	/**
	 * @param constituencyDAO the constituencyDAO to set
	 */
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IDesignationDAO getDesignationDAO() {
		return designationDAO;
	}

	public void setDesignationDAO(IDesignationDAO designationDAO) {
		this.designationDAO = designationDAO;
	}

	public IMainCategoryDAO getMainCategoryDAO() {
		return mainCategoryDAO;
	}

	public void setMainCategoryDAO(IMainCategoryDAO mainCategoryDAO) {
		this.mainCategoryDAO = mainCategoryDAO;
	}

	public IFileKeywordDAO getFileKeywordDAO() {
		return fileKeywordDAO;
	}

	public void setFileKeywordDAO(IFileKeywordDAO fileKeywordDAO) {
		this.fileKeywordDAO = fileKeywordDAO;
	}

	public IKeywordDAO getKeywordDAO() {
		return keywordDAO;
	}

	public void setKeywordDAO(IKeywordDAO keywordDAO) {
		this.keywordDAO = keywordDAO;
	}

	public IGallaryKeywordDAO getGallaryKeywordDAO() {
	return gallaryKeywordDAO;
  }

	public void setGallaryKeywordDAO(IGallaryKeywordDAO gallaryKeywordDAO) {
		this.gallaryKeywordDAO = gallaryKeywordDAO;
	}

	public INewsReportDAO getNewsReportDAO() {
	return newsReportDAO;
	}

public void setNewsReportDAO(INewsReportDAO newsReportDAO) {
	this.newsReportDAO = newsReportDAO;
}

public IReportFilesDAO getReportFilesDAO() {
	return reportFilesDAO;
}

public void setReportFilesDAO(IReportFilesDAO reportFilesDAO) {
	this.reportFilesDAO = reportFilesDAO;
}

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ICandidateRelatedNewsDAO getCandidateRelatedNewsDAO() {
		return candidateRelatedNewsDAO;
	}

	public void setCandidateRelatedNewsDAO(
			ICandidateRelatedNewsDAO candidateRelatedNewsDAO) {
		this.candidateRelatedNewsDAO = candidateRelatedNewsDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
    
    public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}

	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}
	
	
    
    public IGallaryDAO getGallaryDAO() {
		return gallaryDAO;
	}

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}



	public IContentNotesDAO getContentNotesDAO() {
		return contentNotesDAO;
	}

	public void setContentNotesDAO(IContentNotesDAO contentNotesDAO) {
		this.contentNotesDAO = contentNotesDAO;
	}


	
	public IFileGallaryDAO getFileGallaryDAO() {
        return fileGallaryDAO;
    }

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
	      this.fileGallaryDAO = fileGallaryDAO;
	
	}  
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
	public ISourceDAO getSourceDAO() {
		return sourceDAO;
	}

	public void setSourceDAO(ISourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}


	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public INewsImportanceDAO getNewsImportanceDAO() {
		return newsImportanceDAO;
	}

	public void setNewsImportanceDAO(INewsImportanceDAO newsImportanceDAO) {
		this.newsImportanceDAO = newsImportanceDAO;
	}

	public ISourceLanguageDAO getSourceLanguageDAO() {
		return sourceLanguageDAO;
	}

	public void setSourceLanguageDAO(ISourceLanguageDAO sourceLanguageDAO) {
		this.sourceLanguageDAO = sourceLanguageDAO;
	}


	public FileDAO getFileDAO() {
		return fileDAO;
	}

	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	public IFileSourceLanguageDAO getFileSourceLanguageDAO() {
		return fileSourceLanguageDAO;
	}

	public void setFileSourceLanguageDAO(
			IFileSourceLanguageDAO fileSourceLanguageDAO) {
		this.fileSourceLanguageDAO = fileSourceLanguageDAO;
	}
	
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public ICandidateNewsResponseDAO getCandidateNewsResponseDAO() {
		return candidateNewsResponseDAO;
	}

	public void setCandidateNewsResponseDAO(
			ICandidateNewsResponseDAO candidateNewsResponseDAO) {
		this.candidateNewsResponseDAO = candidateNewsResponseDAO;
	}
	
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public ICandidatePartyDAO getCandidatePartyDAO() {
		return candidatePartyDAO;
	}

	public void setCandidatePartyDAO(ICandidatePartyDAO candidatePartyDAO) {
		this.candidatePartyDAO = candidatePartyDAO;
	}

	public ICandidatePartyFileDAO getCandidatePartyFileDAO() {
		return candidatePartyFileDAO;
	}

	public void setCandidatePartyFileDAO(
			ICandidatePartyFileDAO candidatePartyFileDAO) {
		this.candidatePartyFileDAO = candidatePartyFileDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	/* 
 
  
   
  
   
  

	
	
   
    private IBoothDAO boothDAO;
    private IUserCandidateRelationDAO userCandidateRelationDAO;
   
 
	private TransactionTemplate transactionTemplate;
	private IPanchayatHamletDAO panchayatHamletDAO;
	private IContentManagementService contentManagementService;
	private ContentDetailsVO contentDetailsVO;
	private IPanchayatDAO panchayatDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private INewsProblemDAO newsProblemDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	
	
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public INewsProblemDAO getNewsProblemDAO() {
		return newsProblemDAO;
	}

	public void setNewsProblemDAO(INewsProblemDAO newsProblemDAO) {
		this.newsProblemDAO = newsProblemDAO;
	}

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public IContentManagementService getContentManagementService() {
		return contentManagementService;
	}

	public void setContentManagementService(
			IContentManagementService contentManagementService) {
		this.contentManagementService = contentManagementService;
	}

	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

    
    public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	


   

    
    public IUserCandidateRelationDAO getUserCandidateRelationDAO() {
		return userCandidateRelationDAO;
	}

	public void setUserCandidateRelationDAO(
			IUserCandidateRelationDAO userCandidateRelationDAO) {
		this.userCandidateRelationDAO = userCandidateRelationDAO;
	}
	


	
    
    public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	
	
	public List<FileVO> getNewsForRegisterUsers(FileVO inputs){
      log.debug("Enter into getNewsForRegisterUsers Method of NewsMonitoringService ");
       List<FileVO> fileVOList = new ArrayList<FileVO>();
    	try{  
    	  List<File> fileList = fileGallaryDAO.getNewsForRegisterUsers(inputs);
    	  for(File file:fileList){
    		  FileVO  fileVO = new FileVO();
    		  fileVO.setFileId(file.getFileId());
    		  fileVO.setName(file.getFileName());
    		  fileVO.setPath(file.getFilePath());
    		  fileVO.setFileTitle1(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle()));
    		  fileVO.setDescription(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription()));
    		  fileVO.setFileDate(file.getFileDate()!=null?file.getFileDate().toString():"");
    		  
    		  Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
    		  List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(); 
    		  Set<String> sourceSet = new HashSet<String>();
 			  Set<String> languageSet = new HashSet<String>();
 			 StringBuilder sourceVal =new StringBuilder();
			 StringBuilder languageVal =new StringBuilder();
				 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
					 if(inputs.getSourceId() == null && inputs.getLanguegeId() == null)
					 {
						  setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
						  if(fileSourceLanguage.getSource() != null)
						  sourceSet.add(fileSourceLanguage.getSource().getSource());
						  if(fileSourceLanguage.getLanguage() != null)
						  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
					 }
					 else if(inputs.getLanguegeId() != null){
						 if(inputs.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
						 {
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							 if(fileSourceLanguage.getSource() != null)
							 sourceSet.add(fileSourceLanguage.getSource().getSource());
							 if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
					 }
					 else if(inputs.getSourceId() != null){
						 if(inputs.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
						 { 
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							 if(fileSourceLanguage.getSource() != null)
							 sourceSet.add(fileSourceLanguage.getSource().getSource());
							 if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
					 }
					 
				 }
				 for(String source:sourceSet){
					 sourceVal.append(source);
					 sourceVal.append("-");
				 }
	             for(String language:languageSet){
	            	 languageVal.append(language);
	            	 languageVal.append("-");
				 }
	             if(sourceVal != null && sourceVal.length() > 0)
	             sourceVal.deleteCharAt(sourceVal.length() - 1);
	             if(languageVal != null && languageVal.length() > 0)
	             languageVal.deleteCharAt(languageVal.length() - 1);
			fileVO.setMultipleSource(fileVOSourceLanguageList.size());
			Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
			fileVO.setFileVOList(fileVOSourceLanguageList);
			
			
    		  fileVO.setSource(sourceVal!=null?sourceVal.toString():"");
    		  fileVO.setLanguage(languageVal!=null?languageVal.toString():"");
    		  fileVO.setCategoryId(file.getCategory()!=null?file.getCategory().getCategoryId():null);
    		  fileVO.setCategoryType(file.getCategory()!=null?file.getCategory().getCategoryType():"N/A");
    		  fileVO.setNewsImportanceId(file.getNewsImportance()!=null?file.getNewsImportance().getNewsImportanceId():null);
    		  fileVO.setImportance(file.getNewsImportance()!=null?file.getNewsImportance().getImportance():"");
    		  fileVO.setLocationScope(file.getRegionScopes()!=null?file.getRegionScopes().getRegionScopesId():null);
    		  fileVO.setLocationScopeValue(file.getRegionScopes()!=null?file.getRegionScopes().getScope():"");
    		  fileVO.setLocation(file.getLocationValue()!=null?file.getLocationValue():null);
    		  if(file.getRegionScopes()!=null)
    		  fileVO.setLocationValue(candidateDetailsService.getLocationDetails(file.getRegionScopes().getRegionScopesId(), file.getLocationValue()));
    		  
    		  fileVOList.add(fileVO);
    	  }
    	}
    	catch(Exception e){
    		log.error("Exception rised in  getNewsForRegisterUsers Method of NewsMonitoringService", e);
    		e.printStackTrace();
    	}
    	  return fileVOList;
      }
	
	public List<FileVO> getNewsForRegisterUsers1(FileVO inputs){
	      log.debug("Enter into getNewsForRegisterUsers Method of NewsMonitoringService ");
	       List<FileVO> fileVOList = new ArrayList<FileVO>();
	    	try{
	    		
	    		List<Long> contentIds = new ArrayList<Long>();
	    		Map<Long , Long> countMap = new HashMap<Long, Long>();
	    		Map<Long , Long> notesCountMap = new HashMap<Long, Long>();
	    		List<Object[]> countList = new ArrayList<Object[]>();
	    		List<Object[]> notesCountList = new ArrayList<Object[]>();
	    		
	    	  List<Object[]> fileList = fileGallaryDAO.getNewsForRegisterUsers1(inputs);
	    	  
	    	  for(Object[] obj:fileList)	    		  
	    		  contentIds.add((Long)obj[0]);
	    	  
	    	  if(contentIds.size() >0)	    	  
	    	      countList= newsFlagDAO.getCountForFlagByFileGallaryId(contentIds);
	    	  
	    	  
	    	  //if(inputs.getCandidateId() != null){
	    	  
	    	  if(contentIds.size() > 0)
	    	        notesCountList =  contentNotesDAO.getContentNotesCountByContentIds(contentIds);
	    	    
	    	    for(Object[] obj:notesCountList)	    	    	
	    	    	notesCountMap.put((Long)obj[0], (Long)obj[1]);
	    	    	
	    	 // }
	    	  
	    	  
	    	  for(Object[] obj:countList)
	    		  countMap.put((Long)obj[0],(Long)obj[1]);	
	    		  
	    	  for(Object[] obj:fileList){
	    		  
	    		  File file = (File)obj[1];
	    		  FileVO  fileVO = new FileVO();
	    		  
                  if(fileVOList.size() == 0){
                	  fileVO.setTotalFlaggedNews(countList.size());
                	  fileVO.setTotalNotesNews(notesCountMap.size());
                  }
	    		  
	    		  if(countMap.get((Long)obj[0]) == null)
	    			  fileVO.setFlagSet("false");
	    		  else
	    			  fileVO.setFlagSet("true");
	    		  
	    		  if(notesCountMap.get((Long)obj[0]) != null){
	    			  
	    			  if(notesCountMap.get((Long)obj[0]).longValue() >0)
	    				  fileVO.setNotesExist("true");
	    			  else
	    				  fileVO.setNotesExist("false");
	    			  
	    		  }
	    		  
	    		  fileVO.setContentId((Long)obj[0]);
	    		  fileVO.setKeywords(file.getKeywords());
	    		  fileVO.setFileDate(file.getFileDate().toString());
	    		  
	    		  String dateString =file.getFileDate().getDate()+"/"+(file.getFileDate().getMonth()+1)+"/"+(file.getFileDate().getYear()+1900);
	    		  fileVO.setFileDateAsString(dateString);
	    		  fileVO.setComments(file.getComment());
	    		  
	    		  fileVO.setDisplayImagePath(file.getFilePath());
	    		  fileVO.setVisibility(obj[2].toString());
	    		  
	    		  if(file.getRegionScopes() != null)
	    		    fileVO.setLocationScope(file.getRegionScopes().getRegionScopesId());
	    		  
	    		  if(file.getLocationValue() != null)
	    		    fileVO.setLocationValue(file.getLocationValue().toString());
	    		  
	    		  fileVO.setFileId(file.getFileId());
	    		  fileVO.setName(file.getFileName());
	    		  fileVO.setPath(file.getFilePath());
	    		  fileVO.setFileTitle1(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle()));
	    		  fileVO.setDescription(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription()));
	    		  String fileDate = file.getFileDate().toString();
	    		  String dateObj = fileDate.substring(8,10)+'-'+fileDate.substring(5,7)+'-'+fileDate.substring(0,4);
	    		  fileVO.setFileDate(dateObj!=null?dateObj:"");
	    		  fileVO.setFileGallaryId((Long)obj[3]);
	    		  fileVO.setGallaryName(obj[4].toString());
	    		  
	    		  Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
	    		  List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(); 
	    		  Set<String> sourceSet = new HashSet<String>();
	 			  Set<String> languageSet = new HashSet<String>();
	 			 StringBuilder sourceVal =new StringBuilder();
				 StringBuilder languageVal =new StringBuilder();
					 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
						 if(inputs.getSourceId() == null && inputs.getLanguegeId() == null)
						 {
							  setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							  if(fileSourceLanguage.getSource() != null)
							  sourceSet.add(fileSourceLanguage.getSource().getSource());
							  if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
						 else if(inputs.getLanguegeId() != null){
							 if(inputs.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
							 {
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
								 if(fileSourceLanguage.getSource() != null)
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								 if(fileSourceLanguage.getLanguage() != null)
								 languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
							 }
						 }
						 else if(inputs.getSourceId() != null){
							 if(inputs.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
							 { 
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList); 
								 if(fileSourceLanguage.getSource() != null)
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								 if(fileSourceLanguage.getLanguage() != null)
								  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
							 }
						 }
						 
					 }
					 for(String source:sourceSet){
						 sourceVal.append(source);
						 sourceVal.append("-");
					 }
		             for(String language:languageSet){
		            	 languageVal.append(language);
		            	 languageVal.append("-");
					 }
		             if(sourceVal != null && sourceVal.length() > 0)
		             sourceVal.deleteCharAt(sourceVal.length() - 1);
		             if(languageVal != null && languageVal.length() > 0)
		             languageVal.deleteCharAt(languageVal.length() - 1);
				fileVO.setMultipleSource(fileVOSourceLanguageList.size());
				Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
				fileVO.setFileVOList(fileVOSourceLanguageList);
				
				
	    		  fileVO.setSource(sourceVal!=null?sourceVal.toString():"");
	    		  fileVO.setLanguage(languageVal!=null?languageVal.toString():"");
	    		  fileVO.setCategoryId(file.getCategory()!=null?file.getCategory().getCategoryId():null);
	    		  fileVO.setCategoryType(file.getCategory()!=null?file.getCategory().getCategoryType():"N/A");
	    		  fileVO.setNewsImportanceId(file.getNewsImportance()!=null?file.getNewsImportance().getNewsImportanceId():null);
	    		  fileVO.setImportance(file.getNewsImportance()!=null?file.getNewsImportance().getImportance():"");
	    		  fileVO.setLocationScope(file.getRegionScopes()!=null?file.getRegionScopes().getRegionScopesId():null);
	    		  fileVO.setLocationScopeValue(file.getRegionScopes()!=null?file.getRegionScopes().getScope():"");
	    		  fileVO.setLocation(file.getLocationValue()!=null?file.getLocationValue():null);
	    		  fileVO.setLocationVal(file.getLocationValue()!=null?file.getLocationValue():null);
	    		  if(file.getRegionScopes()!=null)
	    		  fileVO.setLocationValue(candidateDetailsService.getLocationDetails(file.getRegionScopes().getRegionScopesId(), file.getLocationValue()));
	    		  
	    		  fileVOList.add(fileVO);
	    	  }
	    	}
	    	catch(Exception e){
	    		log.error("Exception rised in  getNewsForRegisterUsers Method of NewsMonitoringService", e);
	    		e.printStackTrace();
	    	}
	    	  return fileVOList;
	      }
	
	public List<FileVO> getAllCountDetails(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO){
		log.debug("Enter into getAllCountDetails Method of NewsMonitoringService ");
	       List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	       FileVO fileVO = null;
	    	try{ 
	    		Long totalCount = 0L; 
	    		List<Object[]> category = fileGallaryDAO.getCountDetailsForCategory(fromDate,toDate,fileType,regId,fileInputVO);
	    		FileVO categoryFileVO = new FileVO();
	    		List<FileVO> categoryFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:category){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setCategoryType(data[1].toString()!=null?data[1].toString():"N/A");
	    			fileVO.setCategoryId((Long)data[2]);
	    			categoryFileVOList.add(fileVO);
	    			totalCount += (Long)data[0];
	    		}
	    		categoryFileVO.setFileVOList(categoryFileVOList);
	    		returnFileVOList.add(categoryFileVO);
	    		
	    		
	    		List<Object[]> source = fileGallaryDAO.getCountDetailsForSource(fromDate,toDate,fileType,regId,fileInputVO);
	    		FileVO sourceFileVO = new FileVO();
	    		List<FileVO> sourceFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:source){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setSource(data[1].toString());
	    			fileVO.setSourceId((Long)data[2]);
	    			sourceFileVOList.add(fileVO);
	    			
	    		}
	    		sourceFileVO.setFileVOList(sourceFileVOList);
	    		returnFileVOList.add(sourceFileVO);
	    		
	    		
	    		List<Object[]> language = fileGallaryDAO.getCountDetailsForLanguage(fromDate,toDate,fileType,regId,fileInputVO);
	    		FileVO languageFileVO = new FileVO();
	    		List<FileVO> languageFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:language){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setLanguage(data[1].toString());
	    			fileVO.setLanguegeId((Long)data[2]);
	    			languageFileVOList.add(fileVO);
	    		}
	    		languageFileVO.setFileVOList(languageFileVOList);
	    		returnFileVOList.add(languageFileVO);
	    		
	    		
	    		List<Object[]> newsImportance = fileGallaryDAO.getCountDetailsForNewsImportance(fromDate,toDate,fileType,regId,fileInputVO);
	    		FileVO importanceFileVO = new FileVO();
	    		List<FileVO> importanceFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:newsImportance){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setImportance(data[1].toString());
	    			fileVO.setNewsImportanceId((Long)data[2]);
	    			importanceFileVOList.add(fileVO);
	    		}
	    		importanceFileVO.setFileVOList(importanceFileVOList);
	    		returnFileVOList.add(importanceFileVO);
	    		
	    		
	    		List<Object[]> locationScope = fileGallaryDAO.getCountDetailsForLocationScope(fromDate,toDate,fileType,regId,fileInputVO);
	    		FileVO scopeFileVO = new FileVO();
	    		List<FileVO> scopeFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:locationScope){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setLocationScopeValue(data[1].toString());
	    			fileVO.setLocationScope((Long)data[2]);
	    			scopeFileVOList.add(fileVO);
	    		}
	    		scopeFileVO.setFileVOList(scopeFileVOList);
	    		returnFileVOList.add(scopeFileVO);
	    		
	    		
	    		List<Object[]> locationScopeValue = fileGallaryDAO.getCountDetailsForLocationScopeValue(fromDate,toDate,fileType,regId);
	    		FileVO valueFileVO = new FileVO();
	    		List<FileVO> valueFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:locationScopeValue){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setLocationScopeValue(data[1].toString());
	    			fileVO.setLocationScope((Long)data[2]);
	    			fileVO.setLocation(data[3]!=null?(Long)data[3]:null);
	    			fileVO.setLocationValue(candidateDetailsService.getLocationDetails((Long)data[2], data[3]!=null?(Long)data[3]:null));
	    			valueFileVOList.add(fileVO);
	    		}
	    		valueFileVO.setFileVOList(valueFileVOList);
	    		returnFileVOList.add(valueFileVO);
	    		
	    		if(returnFileVOList != null && returnFileVOList.size() > 0)
	    			returnFileVOList.get(0).setCount(totalCount.intValue());
	    	}
	    	catch(Exception e){
	    		log.error("Exception rised in  getAllCountDetails Method of NewsMonitoringService", e);
	    		e.printStackTrace();
	    	}
	    	  return returnFileVOList;
	}
	
	
	 public List<FileVO> getCategoryCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getCategoryCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<Category> catgryList = categoryDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  cateDetailsList = fileGallaryDAO.getDetailsForCategory(fromDate,toDate,fileType,regId,fileInputVO);
		  for(Object[] categRecord: cateDetailsList)
		    {
			   if(categRecord[0] != null)
			    {
				   if(completeData.get(categRecord[0].toString()) != null)
				    {
					   Map<Long,FileVO> singleCateg = completeData.get(categRecord[0].toString());
					   FileVO fileVO = singleCateg.get((Long)categRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
				    }
				   else
				    {
					   Map<Long,FileVO> singleCateg = new LinkedHashMap<Long,FileVO>();
					   FileVO fileVO = null;
					   for(Category categ: catgryList)
					    {
						   fileVO = new FileVO();
						   fileVO.setCategoryId(categ.getCategoryId());
						   fileVO.setCandidateName(categ.getCategoryType());
						   singleCateg.put(categ.getCategoryId(), fileVO);
					    }
					   fileVO = singleCateg.get((Long)categRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
					   completeData.put(categRecord[0].toString(), singleCateg);
				    }
			   }
		   }
		   convertMapToList(completeData,returnVal);
	   }
	   catch(Exception e){
		   log.error("Exception rised in getCategoryCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
	 
	 private void convertMapToList(Map<String,Map<Long,FileVO>> completeData,List<FileVO> returnVal){
		 Set<String> mapKeys = completeData.keySet();
		 FileVO retVO = null;
		 List<FileVO> fileVOList = null;
		 
		 for(String mapKey: mapKeys)
		 {
			 retVO = new FileVO();
			 fileVOList = new ArrayList<FileVO>();
			 Map<Long,FileVO> catObjs = completeData.get(mapKey);
			 Set<Long> catObjsKeys = catObjs.keySet();
			 for(Long catObjsKey: catObjsKeys)
			 {
				 FileVO fileVO = catObjs.get(catObjsKey);
				 fileVOList.add(fileVO);
			 }
			 retVO.setFileVOList(fileVOList);
			 String fileDate = mapKey.substring(8,10)+'-'+mapKey.substring(5,7)+'-'+mapKey.substring(0,4);
			 retVO.setFileDate(fileDate);
			 returnVal.add(retVO);
		 }
		 
		 
	 }
	 public List<FileVO> getSourceCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getSourceCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<Source> sourceList = sourceDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  sourceDetailsList = fileGallaryDAO.getDetailsForSource(fromDate,toDate,fileType,regId,fileInputVO);
		  for(Object[] sourceRecord: sourceDetailsList)
		    {
			   if(sourceRecord[0] != null)
			    {
				   if(completeData.get(sourceRecord[0].toString()) != null)
				    {
					   Map<Long,FileVO> singleCateg = completeData.get(sourceRecord[0].toString());
					   FileVO fileVO = singleCateg.get((Long)sourceRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
				    }
				   else
				    {
					   Map<Long,FileVO> singleCateg = new LinkedHashMap<Long,FileVO>();
					   FileVO fileVO = null;
					   for(Source source: sourceList)
					    {
						   fileVO = new FileVO();
						   fileVO.setCategoryId(source.getSourceId());
						   fileVO.setCandidateName(source.getSource());
						   singleCateg.put(source.getSourceId(), fileVO);
					    }
					   fileVO = singleCateg.get((Long)sourceRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
					   completeData.put(sourceRecord[0].toString(), singleCateg);
				    }
			   }
		   }
		   convertMapToList(completeData,returnVal);
	   }
	   catch(Exception e){
		   log.error("Exception rised in getSourceCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
	 
	 public List<FileVO> getLanguageCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getLanguageCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<SourceLanguage> langugeList = sourceLanguageDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  langugeDetailsList = fileGallaryDAO.getDetailsForLanguage(fromDate,toDate,fileType,regId,fileInputVO);
		  for(Object[] langugeRecord: langugeDetailsList)
		    {
			   if(langugeRecord[0] != null)
			    {
				   if(completeData.get(langugeRecord[0].toString()) != null)
				    {
					   Map<Long,FileVO> singleCateg = completeData.get(langugeRecord[0].toString());
					   FileVO fileVO = singleCateg.get((Long)langugeRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
				    }
				   else
				    {
					   Map<Long,FileVO> singleCateg = new LinkedHashMap<Long,FileVO>();
					   FileVO fileVO = null;
					   for(SourceLanguage languge: langugeList)
					    {
						   fileVO = new FileVO();
						   fileVO.setCategoryId(languge.getLanguageId());
						   fileVO.setCandidateName(languge.getLanguage());
						   singleCateg.put(languge.getLanguageId(), fileVO);
					    }
					   fileVO = singleCateg.get((Long)langugeRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
					   completeData.put(langugeRecord[0].toString(), singleCateg);
				    }
			   }
		   }
		   convertMapToList(completeData,returnVal);
	   }
	   catch(Exception e){
		   log.error("Exception rised in getLanguageCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
	 
	 public List<FileVO> getNewsImpCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getNewsImpCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<NewsImportance> newsImpList = newsImportanceDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  newsImpDetailsList = fileGallaryDAO.getDetailsForNewsImportance(fromDate,toDate,fileType,regId,fileInputVO);
		  for(Object[] newsImpRecord: newsImpDetailsList)
		    {
			   if(newsImpRecord[0] != null)
			    {
				   if(completeData.get(newsImpRecord[0].toString()) != null)
				    {
					   Map<Long,FileVO> singleCateg = completeData.get(newsImpRecord[0].toString());
					   FileVO fileVO = singleCateg.get((Long)newsImpRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
				    }
				   else
				    {
					   Map<Long,FileVO> singleCateg = new LinkedHashMap<Long,FileVO>();
					   FileVO fileVO = null;
					   for(NewsImportance newsImp: newsImpList)
					    {
						   fileVO = new FileVO();
						   fileVO.setCategoryId(newsImp.getNewsImportanceId());
						   fileVO.setCandidateName(newsImp.getImportance());
						   singleCateg.put(newsImp.getNewsImportanceId(), fileVO);
					    }
					   fileVO = singleCateg.get((Long)newsImpRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
					   completeData.put(newsImpRecord[0].toString(), singleCateg);
				    }
			   }
		   }
		   convertMapToList(completeData,returnVal);
	   }
	   catch(Exception e){
		   log.error("Exception rised in getNewsImpCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
	 
	 public ResultStatus updateDeleteNews(FileVO fileVO,String task,List<FileVO> sourceIds,List<FileVO> languageIds){
		 if(log.isDebugEnabled())
			 log.debug("Enter into updateDeleteNews Method of NewsMonitoringService ");
		 ResultStatus resultStatus = new ResultStatus();
	  try{ 
		 if(task.equalsIgnoreCase("Update")){
			 
			 Category category  = null;
			 File file = fileDAO.get(fileVO.getFileId());
			 
			 if(fileVO.getCategoryId() != 0)
			  category = categoryDAO.get(fileVO.getCategoryId());
			 
			 NewsImportance newsImportance = newsImportanceDAO.get(fileVO.getNewsImportanceId());
			 
			 file.setFileTitle(CommonStringUtils.removeSpecialCharsFromAString(fileVO.getTitle()));
			 file.setFileDescription(CommonStringUtils.removeSpecialCharsFromAString(fileVO.getDescription()));
			 file.setCategory(category);
			 file.setNewsImportance(newsImportance);
			 
			 file.setKeywords(fileVO.getKeywords());
			 
			// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
				//Date fileDate = (Date)formatter.parse(fileVO.getFileDate());
			// file.setFileDate(fileDate);	
			 
			 String[] dateArray = fileVO.getFileDate().split("/");
			 
			 Integer date = Integer.parseInt(dateArray[0]);
			 Integer month = Integer.parseInt(dateArray[1]) - 1;
			 Integer year = Integer.parseInt(dateArray[2]) - 1900;
			 
			 Date fileDate = new Date();
			 
			 fileDate.setDate(date);
			 fileDate.setMonth(month);
			 fileDate.setYear(year);
			 
			 file.setFileDate(fileDate);
			 
			 if(fileVO.getLocationScope() != 0){
				 
				 if(fileVO.getLocationScope().longValue() != 7){
					 file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));				 
					 file.setLocationValue(fileVO.getRegionValue());
				 }else{
					 file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));	
					 List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getRegionValue());
					 
					 if(localElectionBodyList != null && localElectionBodyList.size() >0){
						 
						List<Long> locationValuesList = new ArrayList<Long>();	    		  
						 file.setLocationValue((Long)localElectionBodyList.get(0));
						 
						 
						 
					 }
					 
					 
				 }
			 }
			 
			 fileDAO.save(file);
			 
			 for(FileVO languageFile:languageIds){
				 SourceLanguage sourceLanguage = sourceLanguageDAO.get(languageFile.getLanguegeId());
				 FileSourceLanguage	 fileSourceLanguage = fileSourceLanguageDAO.get(languageFile.getFileSourceLanguageId());
				 fileSourceLanguage.setLanguage(sourceLanguage);
				 fileSourceLanguageDAO.save(fileSourceLanguage);
			 }
			 for(FileVO sourceFile:sourceIds){
				 Source source = sourceDAO.get(sourceFile.getSourceId());
				 FileSourceLanguage	 fileSourceLanguage = fileSourceLanguageDAO.get(sourceFile.getFileSourceLanguageId());
				 fileSourceLanguage.setSource(source);
				 fileSourceLanguageDAO.save(fileSourceLanguage);
			 }
			 
			 DateUtilService dateUtilService = new DateUtilService();
			 
			List<FileGallary> fileGallaries = fileGallaryDAO.getFileGallariesByFileId(fileVO.getFileId());
			
			
			for(FileGallary fileGallary:fileGallaries){
				
				fileGallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
				fileGallaryDAO.save(fileGallary);
				
				
			}
			 
			 
			// fileGallaryDAO.updateFileDate(dateUtilService.getCurrentDateAndTime(),fileVO.getFileId());
			 
			 
          if(fileVO.getFileGallaryId() !=0 && fileVO.getGallaryId() != 0){
				 
				 FileGallary fileGallary = fileGallaryDAO.get(fileVO.getFileGallaryId());
				 fileGallary.setGallary(gallaryDAO.get(fileVO.getGallaryId()));
				 
				 if(fileVO.getVisibility().equalsIgnoreCase("public"))
				    fileGallary.setIsPrivate("false");
				 else
				    fileGallary.setIsPrivate("true");
				 
				 fileGallaryDAO.save(fileGallary);
				 
				 
			 }
          
          if(fileVO.getFlagSet().equalsIgnoreCase("false"))        	  
        	  newsFlagDAO.removeFlagForNews(fileVO.getFileGallaryId());          
          else
        	  addFlagToNews(fileVO.getFileGallaryId(),fileVO.getUserId());
			 
			 if(fileVO.getVisibility().equalsIgnoreCase("public")){
				 
				 fileGallaryDAO.updateVisibility(fileVO.getFileId(),"false");
				 
			 }else{
				 
				 fileGallaryDAO.updateVisibility(fileVO.getFileId(),"true");
				 
			 }
			 
		 }
		 else if(task.equalsIgnoreCase("Delete")){
			 fileGallaryDAO.deleteFile(fileVO.getFileId());
		 }
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	  }
	  catch(Exception e){
		  log.error("Exception rised in updateDeleteNews Method of NewsMonitoringService ",e);
		   e.printStackTrace();
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	  }
	  
	  return resultStatus;
	 }*/
	 /*
	 private void setSourceLanguageAndPaths(FileSourceLanguage fileSourceLanguage,List<FileVO> fileVOSourceLanguageList){
			 
		     FileVO fileVOSourceLanguage = new FileVO();
			 fileVOSourceLanguage.setSource(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSource():"");
			 if(fileSourceLanguage.getSource()!=null)
				
			 fileVOSourceLanguage.setSourceId(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSourceId():null);
			 fileVOSourceLanguage.setLanguage(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():"");
			 if(fileSourceLanguage.getLanguage()!=null)
				 
			 
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
	 
	 public List<FileVO> getAllRegionScopes(){
			log.debug("Enter into getAllRegionScopes Method of NewsMonitoringService ");
		    List<FileVO> returnFileVOList = new ArrayList<FileVO>();
		    FileVO fileVO = null;
		 try{ 
			List<Object[]>  regionScopesList = regionScopesDAO.getAllRegionScopesWithOutOrderBy();
			for(Object[] regionScope: regionScopesList){
				 fileVO = new FileVO();
				 fileVO.setIds((Long)regionScope[0]);
				 fileVO.setNames(regionScope[1].toString());
				 
				 returnFileVOList.add(fileVO);
			}
		  }
		 catch(Exception e){
		   log.error("Exception rised in  getAllRegionScopes Method of NewsMonitoringService", e);
		   e.printStackTrace();
		 }
		return returnFileVOList;
	  }
	 
 public List<FileVO> getNewsCountForALocationByCategory(Long registrationId,
			Long locationValue, Long locationId, Long publicationId) {
	 
	 List<Long> candidateIds = getCandidateDetailsByRegistrationId(registrationId);
	// List<Long> locationValuesList = new ArrayList<Long>();	
	 //List<Object[]> countByCategoryList = null ;
	 List<FileVO> filesList = null;
	 
	 try{
	 
	     if(locationId.longValue() == 5)
	    	 filesList =  getNewsCountForMandalLevel(candidateIds , locationId , locationValue);		    	 
	     else if(locationId.longValue() == 3)
	    	 filesList = getNewsCountDetailsByPanchayat(candidateIds , locationValue);	    	
	     else if(locationId.longValue() == 4)
	    	 filesList =  getNewsCountDetailsByConstituencyLevel(candidateIds , locationId , locationValue);
	     else if(locationId.longValue() == 7)
	    	 filesList =  getNewsCountDetailsForMuncipality(candidateIds , locationId , locationValue);
	     else if(locationId.longValue() == 8)
	    	 filesList =  getNewsCountDetailsForWard(candidateIds , locationId , locationValue);
	     else if(locationId.longValue() == 6)
	    	 filesList =  getNewsCountForAHamlet(candidateIds , locationId , locationValue);
	    	 
	 }catch(Exception e){
		 e.printStackTrace();			 
	 }
	 
	 return filesList;
 }
 
 
public  List<FileVO> getNewsCountForMandalLevel(List<Long> candidateIds ,Long  locationId ,Long locationValue){
        log.debug("Entered into the getNewsCountForMandalLevel service method");
        List<FileVO> filesList = null;

		try{
		
			List<Long> locationValuesList = new ArrayList<Long>();	
			List<Long> hamletIds = null;
			Long hamletScopeId = null;
			List<Object[]> countByCategoryList = null ;
				
		    locationValuesList.add(locationValue);
			
			List<Long> panchayitIdsList = panchayatDAO.getPanchayatIdsByTehsilId(locationValue);
			
			if(panchayitIdsList != null && panchayitIdsList.size() >0){
			    hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);					
				hamletScopeId  = 6L;
			}				
			
			 countByCategoryList =	fileGallaryDAO.getNewsCountForMandalLevel(
					candidateIds,locationId,locationValuesList,hamletScopeId,hamletIds);
			 
			 NewsCountVO newsCountVO = new NewsCountVO();
			 
			 newsCountVO.setTehsilScopeId(locationId);
			 newsCountVO.setTehsilIds(locationValuesList);
			 newsCountVO.setHamletScopeId(hamletScopeId);
			 newsCountVO.setHamletIds(hamletIds);
			 newsCountVO.setCandidateIds(candidateIds);
			 
			 filesList  = setNewsCountValuesToFileVO1(countByCategoryList , newsCountVO , "tehsil");
			 
			 
			 //setNewsCountValuesToFileVO(countByCategoryList,candidateIds,locationId,locationValuesList,hamletScopeId,hamletIds);
		}catch(Exception e){
			log.error("Exception raised in the getNewsCountForMandalLevel service method");
			e.printStackTrace();		
		}
		return filesList;

}

public List<FileVO> getNewsCountDetailsByPanchayat(List<Long> candidateIds ,Long  locationValue){

 log.debug("Entered into the getNewsCountDetailsByPanchayat service method");	
 List<FileVO> filesList = null;
 
		 try{
			 List<Object[]> countByCategoryList = null ;
			     List<Long> hamletIds = null;
			     Long hamletScopeId = 6L;
			
			 List<Long> panchayitIdsList  = new ArrayList<Long>();
			 panchayitIdsList.add(locationValue);
			
			 hamletIds =  panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
		
		    countByCategoryList =	fileGallaryDAO.getNewsCountForMandalLevel(
						candidateIds,null,null,hamletScopeId,hamletIds);
		    
		    NewsCountVO newsCountVO = new NewsCountVO();
		    
		    newsCountVO.setHamletScopeId(hamletScopeId);
		    newsCountVO.setHamletIds(hamletIds);
		    newsCountVO.setCandidateIds(candidateIds);
		    
		    filesList  = setNewsCountValuesToFileVO1(countByCategoryList , newsCountVO , "panchayat");
		    
		    //setNewsCountValuesToFileVO(countByCategoryList,candidateIds,null,null,hamletScopeId,hamletIds);
		   
		
			
		}catch(Exception e){
	      log.error("Exception raised in getNewsCountDetailsByPanchayat service method");
	     e.printStackTrace();	
         }
		 
       return filesList;

}

*//**
 * This method will get the news count details for a hamlet
 * @author Samba Penugonda
 * @param candidateIds  , list of candidate whose news can see the logged in user
 * @param locationId , this is the scopeId for hamlet
 * @param locationValue , this is the hamlet id which we want new count
 * @return news count of a hamlet by categorywise
 *//*
public List<FileVO> getNewsCountForAHamlet(
	      List<Long> candidateIds ,Long locationId ,Long locationValue)
{
	log.debug("Entered into  getNewsCountForAHamlet service method");
	 List<FileVO> filesList = null;
	 List<Long> locationValuesList = new ArrayList<Long>();
	 Long hamletScopeId = 6L;
	 List<Object[]> countByCategoryList = null;
	 
	 
	 try
	 {
		 locationValuesList.add(locationValue); 
		 countByCategoryList =  fileGallaryDAO.getNewsCountForHamlets(candidateIds,hamletScopeId,locationValuesList);
		 
		 NewsCountVO newsCountVO = new NewsCountVO();
		 
		 newsCountVO.setCandidateIds(candidateIds);
		 newsCountVO.setHamletScopeId(hamletScopeId);
		 newsCountVO.setHamletIds(locationValuesList);
		 
		 
		  filesList  = setNewsCountValuesToFileVO1(countByCategoryList , newsCountVO , "hamlet");
		 
		 
	 }catch(Exception e)
	 {
		 log.error("Exception raised in getNewsCountForAHamlet service method");
		 e.printStackTrace();
		 return null;
		 
	 }
	 return filesList;
	
}

public List<FileVO> getNewsCountDetailsForWard(
	      List<Long> candidateIds ,Long locationId ,Long locationValue){
	
	 List<FileVO> filesList = null;
	 List<Long> locationValuesList = new ArrayList<Long>();
	 Long wardScopeId = 8L;
	 List<Object[]> countByCategoryList = null;
	 
	 try{
		 
		 locationValuesList.add(locationValue);
		 
		 countByCategoryList = 	fileGallaryDAO.getNewsCountForWards(candidateIds,wardScopeId,locationValuesList);
		 
		 NewsCountVO newsCountVO = new NewsCountVO();
		 
		 newsCountVO.setCandidateIds(candidateIds);
		 newsCountVO.setWardScopeId(wardScopeId);
		 newsCountVO.setWardIdsList(locationValuesList);
		 
		 
		  filesList  = setNewsCountValuesToFileVO1(countByCategoryList , newsCountVO , "ward");
		 
	 }catch(Exception e){
		 e.printStackTrace();
		 return null;
		 
	 }
	 
	 return filesList;
	
	
}

public List<FileVO> getNewsCountDetailsForMuncipality(
		      List<Long> candidateIds ,Long locationId ,Long locationValue){
	 List<FileVO> filesList = null;
	 List<Long> locationValuesList = null;
	 List<Object[]> countByCategoryList = null;
	
	try{

 	   
   	  List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationValue);
   	  
   	  if(localElectionBodyList != null && localElectionBodyList.size() >0){
   		  
   		 locationValuesList = new ArrayList<Long>();	    		  
   		  locationValuesList.add((Long)localElectionBodyList.get(0));
   		  
   	  }
   	  
   	 List<Long> wardIds =  boothDAO.getWardIdsByLocalElectionBodyIds(locationValuesList);
   	  
   	  NewsCountVO newsCountVO = new NewsCountVO();
   	  
   	    newsCountVO.setCandidateIds(candidateIds);
   	    newsCountVO.setMuncipalityScopeId(7L);
    	newsCountVO.setMuncipalityValuesList(locationValuesList);
    	
    	
    	if(wardIds != null && wardIds.size() >0){
    		newsCountVO.setWardIdsList(wardIds);
        	newsCountVO.setWardScopeId(8L);
        	
    	 countByCategoryList = 	fileGallaryDAO.getNewsCountForMuncipalityWithWards(newsCountVO);
    	}
    	else
    	 countByCategoryList = fileGallaryDAO.getNewsCountForMuncipality(
  	    		   candidateIds ,locationId ,locationValuesList);
   	
    filesList  = setNewsCountValuesToFileVO1(countByCategoryList , newsCountVO , "muncipality");

  	   
     
		
	}catch(Exception e){
		e.printStackTrace();
		
	}
	return filesList;
}

public List<FileVO> getNewsCountDetailsByConstituencyLevel(
		List<Long> candidateIds, Long locationId, Long locationValue) {	
	
	log.debug("Entered into the getNewsCountDetailsByConstituencyLevel service method");	
    List<FileVO> filesList = null;
 
 try
	 {
		 List<Object[]> countByCategoryList = null ;
		 List<Long> panchayitIdsList = new ArrayList<Long>();
		// Long constituencyScopeId = locationId;
		 Long constituencyScopeId = 4L;
		 Long constituencyVal = locationValue;
	
		 Long tehsilScopeId = 5L;
		 List<Long> tehsilIds = new ArrayList<Long>();
		 tehsilIds.add(0L);
	
		 Long hamletScopeId = 6L;
		 List<Long> hamletIds = new ArrayList<Long>();
		 hamletIds.add(0L);
		 
		 Long muncipalityScopeId = 7L;
		 Long wardScopeId = 8L;
		 List<Long> wardIds = new ArrayList<Long>();
		 wardIds.add(0L);
	
		 List<Object[]> mandalsList = delimitationConstituencyMandalDAO.getMandalsOfConstituency(constituencyVal);
	
		 for(Object[] obj:mandalsList)
			 tehsilIds.add((Long)obj[0]);
	
		 List<Object[]> panchayitisList = panchayatDAO.getPanchayatIdsByMandalIdsList(tehsilIds);
		 
		 
		 for(Object[] obj :panchayitisList)
			 panchayitIdsList.add((Long)obj[0]);
	
		 if(panchayitIdsList != null && panchayitIdsList.size() >0)
			 hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
		 
		 
		List<Long> localElectionBodyIds = null;
				
		if(tehsilIds != null&& tehsilIds.size()  != 1)
			localElectionBodyIds = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(tehsilIds);
		else
			localElectionBodyIds = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByConstituency(constituencyVal);
	
		 if(localElectionBodyIds != null && localElectionBodyIds.size() >0){
			 
			  wardIds =  boothDAO.getWardIdsByLocalElectionBodyIds(localElectionBodyIds);
			 
			 
			 if(wardIds != null && wardIds.size() >0)
				 countByCategoryList =	fileGallaryDAO.getNewsCountForConstituencyLevelWithMuncipalityAndWards(
							candidateIds,constituencyScopeId,constituencyVal,tehsilScopeId,
							tehsilIds,hamletScopeId,hamletIds,muncipalityScopeId , localElectionBodyIds,wardScopeId,wardIds);
			 else
			 countByCategoryList =	fileGallaryDAO.getNewsCountForConstituencyLevelWithMuncipality(
						candidateIds,constituencyScopeId,constituencyVal,tehsilScopeId,
						tehsilIds,hamletScopeId,hamletIds,muncipalityScopeId , localElectionBodyIds);
			 
			 
		
		 	
		 }else{
			 
			 countByCategoryList =	fileGallaryDAO.getNewsCountForConstituencyLevel(
						candidateIds,constituencyScopeId,constituencyVal,tehsilScopeId,
						tehsilIds,hamletScopeId,hamletIds);
			 
			 
		 }
	 
	 
	 setNewsCountValuesToFileVO1(countByCategoryList,
				candidateIds,constituencyScopeId,constituencyVal,tehsilScopeId,
				tehsilIds,hamletScopeId,hamletIds);
	
	 
		 	NewsCountVO newsCountVO = new NewsCountVO();
	 
		 	newsCountVO.setConstituencyScopeId(constituencyScopeId);
		 	newsCountVO.setConstituencyValue(constituencyVal);
		 	newsCountVO.setTehsilScopeId(tehsilScopeId);
		 	newsCountVO.setTehsilIds(tehsilIds);
		 	newsCountVO.setHamletScopeId(hamletScopeId);
		 	newsCountVO.setHamletIds(hamletIds);
		 	newsCountVO.setCandidateIds(candidateIds);
		 	
		 	if(wardIds != null){
		 		
		 		newsCountVO.setWardScopeId(8L);
		 		newsCountVO.setWardIdsList(wardIds);
		 		
		 	}
		 		
		 	
		 	if(localElectionBodyIds != null && localElectionBodyIds.size() >0){
		 	  newsCountVO.setMuncipalityScopeId(muncipalityScopeId);
		 	  newsCountVO.setMuncipalityValuesList(localElectionBodyIds);
		 	}
	
		 	filesList  =  setNewsCountValuesToFileVO1
		 			(countByCategoryList , newsCountVO , "constituency");
		 	
	 }catch(Exception e){
	 
	 log.error("Exception raised in getNewsCountDetailsByConstituencyLevel service method");
	 e.printStackTrace();	 
     }
    return filesList;

   }
	 
	public List<FileVO> setNewsCountValuesToFileVO1(
			List<Object[]> countByCategoryList, NewsCountVO newsCountVO , String type) {
		 
		 List<FileVO> filesList = new ArrayList<FileVO>();
		 List<Object[]> importanceCountList = null;
		 try{
			 
			 for(Object[] obj:countByCategoryList){				
					
					FileVO file = new FileVO();				
					Long categoryId = (Long)obj[2];				
					file.setCategoryName(obj[0].toString());	
					file.setCategoryId(categoryId);
			
					
					if(type.equalsIgnoreCase("constituency")){
						
						if(newsCountVO.getMuncipalityScopeId() != null && newsCountVO.getMuncipalityValuesList() != null){
							
							
							if(newsCountVO.getWardScopeId() != null && newsCountVO.getWardIdsList() != null && newsCountVO.getWardIdsList().size() >0)
								importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForConstituencyWithMuncipalityAndWards(
										 categoryId ,newsCountVO );
						
							else
							importanceCountList = fileGallaryDAO
									.getNewsCountForALocationByCategoryAndImportanceForConstituencyWithMuncipality(
											 categoryId ,newsCountVO );
							
						}else{
					        importanceCountList = fileGallaryDAO
							.getNewsCountForALocationByCategoryAndImportanceForConstituency(
									 categoryId ,newsCountVO );
						}
					 
					}else if(type.equalsIgnoreCase("tehsil")){
						
						importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForMandal(
										 categoryId ,newsCountVO );
						
					}else if(type.equalsIgnoreCase("panchayat")){
						
						importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForPanchayat(
										 categoryId ,newsCountVO );
					}else if(type.equalsIgnoreCase("muncipality")){
						
						
						if(newsCountVO.getWardIdsList() != null &&newsCountVO.getWardIdsList().size()>0 && newsCountVO.getWardScopeId() != null)
							importanceCountList = fileGallaryDAO
							.getNewsCountForALocationByCategoryAndImportanceForMuncipalityWithWards(
									 categoryId ,newsCountVO );
						else							
						  importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForMuncipality(
										 categoryId ,newsCountVO );
					}else if(type.equalsIgnoreCase("ward")){
						
						importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForWard(
										 categoryId ,newsCountVO );
					}else if(type.equalsIgnoreCase("hamlet")){
						
						importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForHamlet(
										 categoryId ,newsCountVO );
					}
			
					for(Object[] importanceNews:importanceCountList){					
						Long importanceId = (Long)importanceNews[0];
						Long newsCount = (Long)importanceNews[2];
						
						if(importanceId == 1)							
							file.setLowImpactCount(newsCount);
						else if(importanceId == 2)
							file.setMediumImpactCount(newsCount);
						else if(importanceId == 3)
							file.setHighImpactCount(newsCount);
						
					}			
						filesList.add(file);
						
					}
			 
		 }catch(Exception e){
			 e.printStackTrace();			 
		 }
		 
		 return filesList;
		 
	 } 
	 
	 public List<FileVO> setNewsCountValuesToFileVO2(List<Object[]> countByCategoryList,
				List<Long> candidateIds,Long constituencyScopeId,Long constituencyVal,Long tehsilScopeId,
				List<Long> tehsilIds,Long hamletScopeId,List<Long> hamletIds){
		 
		 List<FileVO> filesList = new ArrayList<FileVO>();
		 
		 try{
			 
			 for(Object[] obj:countByCategoryList){				
					
					FileVO file = new FileVO();				
					Long categoryId = (Long)obj[2];				
					file.setCategoryName(obj[0].toString());	
					file.setCategoryId(categoryId);
					
					List<Object[]> importanceCountList = fileGallaryDAO
							.getNewsCountForALocationByCategoryAndImportanceForACandidate(
									candidateIds, categoryId , locationId , locationValuesList );
					
					List<Object[]> importanceCountList = fileGallaryDAO
							.getNewsCountForALocationByCategoryAndImportanceForConstituency(
									candidateIds, categoryId , constituencyScopeId,constituencyVal,
									tehsilScopeId,tehsilIds,hamletScopeId,hamletIds);
			
						for(Object[] importanceNews:importanceCountList){					
							Long importanceId = (Long)importanceNews[0];
							Long newsCount = (Long)importanceNews[2];
							
							if(importanceId == 1)							
								file.setLowImpactCount(newsCount);
							else if(importanceId == 2)
								file.setMediumImpactCount(newsCount);
							else if(importanceId == 3)
								file.setHighImpactCount(newsCount);
							
						}				
						filesList.add(file);
						
					}
			 
		 }catch(Exception e){
			 e.printStackTrace();
			 
		 }
		 
	 }
	 
	public List<FileVO> setNewsCountValuesToFileVO(List<Object[]> countByCategoryList,List<Long> candidateIds,
			Long locationId, List<Long> locationValuesList, Long hamletScopeId,
			List<Long> hamletIds) {
		 log.debug("Entered into the setNewsCountValuesToFileVO service method");
		 try{
			 
			 
			 for(Object[] obj:countByCategoryList){				
					
					FileVO file = new FileVO();				
					Long categoryId = (Long)obj[2];				
					file.setCategoryName(obj[0].toString());	
					file.setCategoryId(categoryId);
					
					List<Object[]> importanceCountList = fileGallaryDAO
							.getNewsCountForALocationByCategoryAndImportanceForACandidate(
									candidateIds, categoryId , locationId , locationValuesList );
					
					 List<File> importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportance(
										candidateIds, categoryId , locationId , locationValuesList,hamletScopeId , hamletIds );
					 
					 
					 for(File file1:importanceCountList){
						 
						// file1.
						 
						 
						 
					 }
			
						for(Object[] importanceNews:importanceCountList){					
							Long importanceId = (Long)importanceNews[0];
							Long newsCount = (Long)importanceNews[2];
							
							if(importanceId == 1)							
								file.setLowImpactCount(newsCount);
							else if(importanceId == 2)
								file.setMediumImpactCount(newsCount);
							else if(importanceId == 3)
								file.setHighImpactCount(newsCount);
							
						}				
						filesList.add(file);
						
					}
			 
			
			 
			
			 
			 
		 }catch(Exception e){
			 log.error("Exception raised in setNewsCountValuesToFileVO service method");
			 e.printStackTrace();
		}
		 return null;
	 }
	
	public List<FileVO> getNewsCountForALocationByCategory1(Long registrationId,
			Long locationValue, Long locationId, Long publicationId) {
		
		log.debug("Enterd into the getNewsCountForALocationByCategory service method");
		 
		 try{			 
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(registrationId);

            List<Long> locationValuesList = new ArrayList<Long>();			
            locationValuesList.add(locationValue);
			
			
           if(locationId == 3)	{
				//locationValuesList = getAllBoothsInPanchayat(locationValue,
					//	publicationId, locationValuesList); 
        	   
        	   locationValuesList = new ArrayList<Long>();
	    	   
 	    	  List<Object[]> hamletsList =  panchayatHamletDAO.getHamletsOfAPanchayat(locationValue);
 	    	  
                for(Object[] obj:hamletsList){
             	   locationValuesList.add((Long)obj[0]);
                }
                locationId = 6L;
           }
           
           
           if(locationId == 7){
	    	   
 	    	  List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationValue);
 	    	  
 	    	  if(localElectionBodyList != null && localElectionBodyList.size() >0){
 	    		  
 	    		 locationValuesList = new ArrayList<Long>();	    		  
 	    		  locationValuesList.add((Long)localElectionBodyList.get(0));
 	    		  
 	    	  }
        	   
           }
        	   
			List<Object[]> countByCategoryList = fileGallaryDAO
					.getNewsCountForALocationByCategoryForACandidate(
							candidateIds,locationId,
							locationValuesList);	
				
			List<FileVO> filesList = setCountNewsCountValuesToVO(
					countByCategoryList, candidateIds, locationId,
					locationValuesList);
				
		
			return filesList;
			
		 }catch(Exception e){
			 
		    log.error("Exception raised in getNewsCountForALocationByCategory service method:"+e);
			 e.printStackTrace();
			 return null;			 
		 }
	}
	
	
	public List<Long> getCandidateDetailsByRegistrationId(Long registrationId){		
		
		log.debug("Entered into the getCandidateDetailsByRegistrationId service method");
		
		 List<Long> candidateIds = new ArrayList<Long>();
		 
		 try{
		 
		 List<Object[]> candidateDetails = userCandidateRelationDAO
				.getCandidatesOfAUser(registrationId);
			
			for(Object[] obj:candidateDetails)
				candidateIds.add((Long)obj[0]);
		 }catch(Exception e){
			 e.printStackTrace();
			 log.debug("Exception raised in  getCandidateDetailsByRegistrationId service method:"+e);			 
		 }		
		return candidateIds;
		
	}
	
	public List<Long> getAllBoothsInPanchayat(Long panchayatId,
			Long publicationId, List<Long> locationValuesList) {
		
		log.debug("Entered into the getAllBoothsInPanchayat service method");

		try
		{
			//List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(
					//panchayatId, publicationId);
					
					List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(
							panchayatId, new Long(IConstants.PRESENT_ELECTION_YEAR));
			
			for(Object[] boothDtls:boothsList)					
				locationValuesList.add((Long)boothDtls[0]);
		}catch(Exception e){
			log.debug("Exception raised in getAllBoothsInPanchayat service method:"+e);
			e.printStackTrace();
			
		}
		return locationValuesList;
		
	}
	
	
	public List<FileVO> setCountNewsCountValuesToVO(List<Object[]> countByCategoryList,
			List<Long> candidateIds, Long locationId,
			List<Long> locationValuesList) {
		log.debug("Entered into the setCountNewsCountValuesToVO service method");
		
		List<FileVO> filesList = new ArrayList<FileVO>();
		
		try{		
			for(Object[] obj:countByCategoryList){				
			
			FileVO file = new FileVO();				
			Long categoryId = (Long)obj[2];				
			file.setCategoryName(obj[0].toString());	
			file.setCategoryId(categoryId);
			
			List<Object[]> importanceCountList = fileGallaryDAO
					.getNewsCountForALocationByCategoryAndImportanceForACandidate(
							candidateIds, categoryId , locationId , locationValuesList );
	
				for(Object[] importanceNews:importanceCountList){					
					Long importanceId = (Long)importanceNews[0];
					Long newsCount = (Long)importanceNews[2];
					
					if(importanceId == 1)							
						file.setLowImpactCount(newsCount);
					else if(importanceId == 2)
						file.setMediumImpactCount(newsCount);
					else if(importanceId == 3)
						file.setHighImpactCount(newsCount);
					
				}				
				filesList.add(file);
				
			}
		}catch(Exception e){
			log.error("Exception raised in setCountNewsCountValuesToVO service method");
			e.printStackTrace();
			return null;			
		}
		
		return filesList;	
		
	}
	
	
	public ContentDetailsVO getNewsByLocationAndCategoryInPopup(FileVO fileVO){
		
		//List<FileVO> fileList = new ArrayList<FileVO>();
		List<FileGallary> filesList =new ArrayList<FileGallary>();
		
		try{			
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(fileVO.getUserId());
			
			if(fileVO.getLocationId().longValue() == 3)
				filesList = getNewsDetailsForPanchayat(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 5)
				filesList = getNewsDetailsForMandal(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 4)
				filesList = getNewsDetailsForConstituency(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 7)
				filesList = getNewsDetailsForMuncipality(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 8 )
				filesList = getNewsDetailsForWard(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 6 )
				filesList = getNewsDetailsForHamlet(candidateIds,fileVO);
			
		contentDetailsVO = contentManagementService.getSelectedContentAndRelatedGalleriesInPopup(
				fileVO.getContentId(),fileVO.getRequestFrom(),fileVO.getRequestPageId(),
				fileVO.getIsCustomer(),filesList);

			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return contentDetailsVO;
	}
	
	public List<FileGallary> getNewsDetailsForPanchayat(List<Long> candidateIds , FileVO fileVO){
		List<FileGallary> fileGallaryList = null;
		
		try{
			
            List<Long> hamletIds = null;
            Long hamletScopeId = 6L;
           
			 List<Long> panchayitIdsList  = new ArrayList<Long>();
			 panchayitIdsList.add(fileVO.getLocationVal());
			
			 hamletIds =  panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
			 
			 NewsCountVO newsCountVO = new NewsCountVO();
			 
			 newsCountVO.setCandidateIds(candidateIds);
			 newsCountVO.setHamletScopeId(hamletScopeId);
			 newsCountVO.setHamletIds(hamletIds);
			 newsCountVO.setCategoryId(fileVO.getCategoryId());
			 newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			
			 
			 fileGallaryList = fileGallaryDAO.getFilegallaryDetailsForPanchayat(newsCountVO);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fileGallaryList;
	}
	
	public List<FileGallary> getNewsDetailsForMandal(List<Long> candidateIds , FileVO fileVO){
		
		List<FileGallary> fileGallaryList = null;
		try{

			Long tehsilScopeId = 5L;
			Long hamletScopeId = 6L;
			List<Long> hamletIds = null;
			List<Long> tehsilIds = new ArrayList<Long>();
			
			List<Long> panchayitIdsList = panchayatDAO.getPanchayatIdsByTehsilId(fileVO.getLocationVal());
			
			if(panchayitIdsList != null && panchayitIdsList.size() >0){
			    hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);					
			}
			
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			tehsilIds.add(fileVO.getLocationVal());
			
			newsCountVO.setCandidateIds(candidateIds);
			newsCountVO.setTehsilIds(tehsilIds);
			newsCountVO.setTehsilScopeId(tehsilScopeId);
			newsCountVO.setHamletScopeId(hamletScopeId);
			newsCountVO.setHamletIds(hamletIds);
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			
			fileGallaryList = fileGallaryDAO.getNewsDetailsByForMandal(newsCountVO);
			//filesList =  processAllFileDetails(fileVO,fileList);
	
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return fileGallaryList;
	}
	
	*//**
	 * This method will get the file gallaries details which the news contains for given hamlet by a category 
	 * @param candidateIds , list of candidate ids who the logged in user has the privilise to see the news
	 * @param fileVO , this contains all the details related to category ,  hamletscopeId and hamletId
	 * @return all the fileGallaries of news related to the input category of a hamlet
	 *//*
	public List<FileGallary> getNewsDetailsForHamlet(List<Long> candidateIds , FileVO fileVO)
	{
		log.debug("Entered into the getNewsDetailsForHamlet service method");
		
		List<FileGallary> fileGallaryList = null;
		List<Long> hamletIds = new ArrayList<Long>();
		
		try{
			hamletIds.add(fileVO.getLocationVal());
			
			List<Long> wardsList = new ArrayList<Long>(); 
			wardsList.add(fileVO.getLocationVal());
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			newsCountVO.setCandidateIds(candidateIds);
			newsCountVO.setHamletScopeId(6L);
			newsCountVO.setHamletIds(hamletIds);
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			
			 fileGallaryList = fileGallaryDAO.getNewsDetailsByForHamlets(newsCountVO);
			
			
		}catch(Exception e){
			log.error("Exception raised in getNewsDetailsForHamlet service method");
			e.printStackTrace();
		}
		
		return fileGallaryList;	
	}
	
	
	public List<FileGallary> getNewsDetailsForWard(List<Long> candidateIds , FileVO fileVO){
		
		List<FileGallary> fileGallaryList = null;
		
		try{
			
			
			List<Long> wardsList = new ArrayList<Long>(); 
			wardsList.add(fileVO.getLocationVal());
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			newsCountVO.setCandidateIds(candidateIds);
			newsCountVO.setWardScopeId(8L);
			newsCountVO.setWardIdsList(wardsList);
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			
			 fileGallaryList = fileGallaryDAO.getNewsDetailsByForWards(newsCountVO);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return fileGallaryList;		
		
	}
	
	public List<FileGallary> getNewsDetailsForMuncipality(List<Long> candidateIds , FileVO fileVO){
		
		List<FileGallary> fileGallaryList = null;
		
		try{
			Long muncipalityScopeId = 7L;
			List<Long> muncipalityValues = null;
			
	       List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getLocationVal());
	  
	       if(localElectionBodyList != null && localElectionBodyList.size() >0){
		     muncipalityValues = new ArrayList<Long>();	    		  
		     muncipalityValues.add((Long)localElectionBodyList.get(0));	
		     
	       }
	       
	       List<Long> wardIds =  boothDAO.getWardIdsByLocalElectionBodyIds(muncipalityValues);
	       
	      
		     NewsCountVO newsCountVO = new NewsCountVO();
		     
		     newsCountVO.setCandidateIds(candidateIds);
		     newsCountVO.setMuncipalityScopeId(muncipalityScopeId);
		     newsCountVO.setMuncipalityValuesList(muncipalityValues);
		     newsCountVO.setCategoryId(fileVO.getCategoryId());
			 newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			 newsCountVO.setStartIndex(fileVO.getStartIndex());
			 newsCountVO.setMaxResults(fileVO.getMaxResult());
			 newsCountVO.setWardScopeId(8L);
			 newsCountVO.setWardIdsList(wardIds);
			 
			 if(wardIds != null && wardIds.size() >0){
				 fileGallaryList = fileGallaryDAO.getNewsDetailsByForMuncipalityWithWards(newsCountVO);
		    	   
		     }else
	            fileGallaryList = fileGallaryDAO.getNewsDetailsByForMuncipality(newsCountVO);
	       
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return fileGallaryList;
		
		
	}
	
	
	public List<FileGallary> getNewsDetailsForConstituency(List<Long> candidateIds , FileVO fileVO){
		List<FileGallary> fileGallaryList = null;
		
		try{
			Long constituencyScopeId = 4L;
			List<Long> constituencyValuesList = new ArrayList<Long>();
			Long tehsilScopeId = 5L;
			List<Long> tehsilIds = new ArrayList<Long>();
			Long hamletScopeId = 6L;
			List<Long> hamletIds = new ArrayList<Long>();
			List<Long> panchayitIdsList = new ArrayList<Long>();
			 List<Long> wardIds = new ArrayList<Long>();
			
			constituencyValuesList.add(fileVO.getLocationVal());
			
			List<Object[]> mandalsList = delimitationConstituencyMandalDAO.getMandalsOfConstituency(fileVO.getLocationVal());
			
			 for(Object[] obj:mandalsList)
				 tehsilIds.add((Long)obj[0]);
			 
			 tehsilIds.add(0L);
			 List<Object[]> panchayitisList = panchayatDAO.getPanchayatIdsByMandalIdsList(tehsilIds);
			 
			 
			 for(Object[] obj :panchayitisList)
				 panchayitIdsList.add((Long)obj[0]);
			 
			 panchayitIdsList.add(0L);
		
			 if(panchayitIdsList != null && panchayitIdsList.size() >0)
				 hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
			 
			List<Long> localElectionBodyIds =  null;
			
			if(tehsilIds != null && tehsilIds.size() != 1)		
				localElectionBodyIds = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(tehsilIds);
			else
			    localElectionBodyIds = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByConstituency(fileVO.getLocationVal());

					
			
			localElectionBodyIds.add(0L);

	       wardIds =  boothDAO.getWardIdsByLocalElectionBodyIds(localElectionBodyIds);

	       
			//	tehsilIds.add(0L);
				hamletIds.add(0L);
				wardIds.add(0L);
				//panchayitIdsList.add(0L);
			
			 NewsCountVO newsCountVO = new NewsCountVO();
			 
			 newsCountVO.setCandidateIds(candidateIds);
			 newsCountVO.setConstituencyScopeId(constituencyScopeId);
			 newsCountVO.setConstituencyValuesList(constituencyValuesList);
			 newsCountVO.setTehsilScopeId(tehsilScopeId);
			 newsCountVO.setTehsilIds(tehsilIds);
			 newsCountVO.setHamletScopeId(hamletScopeId);
			 newsCountVO.setHamletIds(hamletIds);
			 newsCountVO.setCategoryId(fileVO.getCategoryId());
			 newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			 newsCountVO.setStartIndex(fileVO.getStartIndex());
			 newsCountVO.setMaxResults(fileVO.getMaxResult());
			 
			 if(wardIds != null && wardIds.size() >0){
				 
				 newsCountVO.setWardScopeId(8L);
				 newsCountVO.setWardIdsList(wardIds);
				 
			 }
			 
			 if(localElectionBodyIds != null && localElectionBodyIds.size() >0){
				 newsCountVO.setMuncipalityScopeId(7L);
				 newsCountVO.setMuncipalityValuesList(localElectionBodyIds);
				 
				 if(wardIds != null && wardIds.size() >0)
					 fileGallaryList = fileGallaryDAO.getNewsDetailsForConstituencyWithMuncipalityAndWards(newsCountVO);
				 else				 
				   fileGallaryList = fileGallaryDAO.getNewsDetailsForConstituencyWithMuncipality(newsCountVO);

			 
			 }else{			 
			  fileGallaryList = fileGallaryDAO.getNewsDetailsForConstituency(newsCountVO);
			 }
			 
			// filesList =  processAllFileDetails(fileVO,fileList);
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return fileGallaryList;
	}
	
	//by sasi start
	public ContentDetailsVO getNewsByLocationAndCategoryInPopup1(FileVO fileVO){
		log.debug("Entered into the getNewsByLocationAndCategoryInPopup service method");
		
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		List<FileGallary> filesList =new ArrayList<FileGallary>();
		
		try{
		
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(fileVO.getUserId());
	
	        List<Long> locationValuesList = new ArrayList<Long>();			
	        locationValuesList.add(fileVO.getLocationVal());
			
			
	       if(fileVO.getLocationId() == 3){
	    	   
	       
				//locationValuesList = getAllBoothsInPanchayat(fileVO.getLocationVal(),
						//fileVO.getPublicationId(), locationValuesList);
	    	   locationValuesList = new ArrayList<Long>();
	    	   
	    	  List<Object[]> hamletsList =  panchayatHamletDAO.getHamletsOfAPanchayat(fileVO.getLocationVal());
	    	  
               for(Object[] obj:hamletsList){
            	   locationValuesList.add((Long)obj[0]);
            	   
            	   
               }
               fileVO.setLocationId(6L);
				
				//fileVO.setLocationId(9L);
	       }
	       
	       if(fileVO.getLocationId() == 7){
	    	   
	    	  List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getLocationVal());
	    	  
	    	  if(localElectionBodyList != null && localElectionBodyList.size() >0){
	    		  
	    		  
	    		  locationValuesList = new ArrayList<Long>();	    		  
	    		  locationValuesList.add((Long)localElectionBodyList.get(0));
	    		  
	    	  }
	    		  
	       }
	       filesList = fileGallaryDAO.getNewsByLocationAndCategoryInPopup(
					candidateIds,fileVO,locationValuesList); 
	       
	       
	       contentDetailsVO = contentManagementService.getSelectedContentAndRelatedGalleriesInPopup(fileVO.getContentId(),fileVO.getRequestFrom(),fileVO.getRequestPageId(),fileVO.getIsCustomer(),filesList);
	      
	       return contentDetailsVO;
			}catch(Exception e){
			
			log.error("Exception raised in  the getNewsByLocationAndCategory service method");
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public List<FileVO> getNewsByLocationAndCategory(FileVO fileVO){
		List<FileVO> filesList = null;
		
		try{
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(fileVO.getUserId());
			
			if(fileVO.getLocationId().longValue() == 3)
				filesList = getNewsForPanchayat(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 5)
				filesList = getNewsForMandal(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 4)
				filesList = getNewsForConstituency(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 7)
				filesList = getNewsForMuncipality(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 8)
				filesList = getNewsForWard(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 6)
				filesList = getNewsForHamlet(candidateIds,fileVO);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return filesList;
	}
	
	
	public List<FileVO> getNewsForPanchayat(List<Long> candidateIds , FileVO fileVO){
		 List<FileVO> filesList = null;
		
		try{
			
             List<Long> hamletIds = null;
             Long hamletScopeId = 6L;
            
			 List<Long> panchayitIdsList  = new ArrayList<Long>();
			 panchayitIdsList.add(fileVO.getLocationVal());
			
			 hamletIds =  panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
			 
			 NewsCountVO newsCountVO = new NewsCountVO();
			 
			 newsCountVO.setCandidateIds(candidateIds);
			 newsCountVO.setHamletScopeId(hamletScopeId);
			 newsCountVO.setHamletIds(hamletIds);
			 newsCountVO.setCategoryId(fileVO.getCategoryId());
			 newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			 newsCountVO.setStartIndex(fileVO.getStartIndex());
			 newsCountVO.setMaxResults(fileVO.getMaxResult());
			 
			List<Object[]> fileList = fileGallaryDAO.getNewsByForPanchayat(newsCountVO);
			filesList = processAllFileDetails(fileVO,fileList);
		
			
		}catch(Exception e){
			e.printStackTrace();			
		}
		
		return filesList;
		
	}
	
	*//**
	 * This method will get the news details for a hamlet by selected category
	 * @param candidateIds  , list of candidate whose news can see the logged in user
	 * @param fileVO , this contains the details like selected category ,scopeId and hamlet values
	 * @return all the selected category news details for a hamlet
	 *//*
	public List<FileVO> getNewsForHamlet(List<Long> candidateIds , FileVO fileVO)
	{
		
		log.debug("Entered into the getNewsForHamlet service method");
		List<FileVO> filesList = null;
		Long hamletScopeId = 6L;
		List<Long> hamletIds = new ArrayList<Long>();
		try
		{
			hamletIds.add(fileVO.getLocationVal());
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
            newsCountVO.setCandidateIds(candidateIds);
			
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			newsCountVO.setStartIndex(fileVO.getStartIndex());
			newsCountVO.setMaxResults(fileVO.getMaxResult());
			newsCountVO.setHamletScopeId(hamletScopeId);
			newsCountVO.setHamletIds(hamletIds);
			
			List<Object[]> fileList = null;
			
			 fileList = fileGallaryDAO.getNewsByHamlet(newsCountVO);
			 
			 filesList =  processAllFileDetails(fileVO,fileList);
		
		}
		catch(Exception e)
		{
			log.debug("Exception raised in getNewsForHamlet service method");
			e.printStackTrace();
		}
		
		return filesList;
		
		
	}
	

	
	public List<FileVO> getNewsForWard(List<Long> candidateIds , FileVO fileVO){
		List<FileVO> filesList = null;
		
		try{
			
			List<Long> wardIdsList = new ArrayList<Long>();
			wardIdsList.add(fileVO.getLocationVal());
			Long wardScopeId = 8L;
			
			
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			newsCountVO.setCandidateIds(candidateIds);
			
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			newsCountVO.setStartIndex(fileVO.getStartIndex());
			newsCountVO.setMaxResults(fileVO.getMaxResult());
			newsCountVO.setWardScopeId(8L);
			newsCountVO.setWardIdsList(wardIdsList);
			
			List<Object[]> fileList = null;
			
				 fileList = fileGallaryDAO.getNewsByWard(newsCountVO);
			

			 
			
			  filesList =  processAllFileDetails(fileVO,fileList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
       return filesList;
		
	}
	
	public List<FileVO> getNewsForMuncipality(List<Long> candidateIds , FileVO fileVO){
		List<FileVO> filesList = null;
		
		try{
			
			List<Long> muncipalityValuesList = new ArrayList<Long>();
			
			List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getLocationVal());
	    	  
	    	  if(localElectionBodyList != null && localElectionBodyList.size() >0)
	    		  muncipalityValuesList.add((Long)localElectionBodyList.get(0));
	    	
	    	  
	    	  List<Long> wardIds =  boothDAO.getWardIdsByLocalElectionBodyIds(muncipalityValuesList);
	    	  
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			newsCountVO.setCandidateIds(candidateIds);
			newsCountVO.setMuncipalityScopeId(7L);
			newsCountVO.setMuncipalityValuesList(muncipalityValuesList);
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			newsCountVO.setStartIndex(fileVO.getStartIndex());
			newsCountVO.setMaxResults(fileVO.getMaxResult());
			newsCountVO.setWardScopeId(8L);
			newsCountVO.setWardIdsList(wardIds);
			
			List<Object[]> fileList = null;
			
			if(wardIds != null && wardIds.size() >0)
				 fileList = fileGallaryDAO.getNewsByForMuncipalityWithWards(newsCountVO);
			else
				fileList = fileGallaryDAO.getNewsByForMuncipality(newsCountVO);

			 
			
			  filesList =  processAllFileDetails(fileVO,fileList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
       return filesList;
		
	}
	
	
	public List<FileVO> getNewsForMandal(List<Long> candidateIds , FileVO fileVO){
		List<FileVO> filesList = null;
		
		try{
			Long tehsilScopeId = 5L;
			Long hamletScopeId = 6L;
			List<Long> hamletIds = null;
			List<Long> tehsilIds = new ArrayList<Long>();
			
			List<Long> panchayitIdsList = panchayatDAO.getPanchayatIdsByTehsilId(fileVO.getLocationVal());
			
			if(panchayitIdsList != null && panchayitIdsList.size() >0){
			    hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);					
			}
			
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			tehsilIds.add(fileVO.getLocationVal());
			
			newsCountVO.setCandidateIds(candidateIds);
			newsCountVO.setTehsilIds(tehsilIds);
			newsCountVO.setTehsilScopeId(tehsilScopeId);
			newsCountVO.setHamletScopeId(hamletScopeId);
			newsCountVO.setHamletIds(hamletIds);
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			newsCountVO.setStartIndex(fileVO.getStartIndex());
			newsCountVO.setMaxResults(fileVO.getMaxResult());
			 
			
			List<Object[]> fileList = fileGallaryDAO.getNewsByForMandal(newsCountVO);
			filesList =  processAllFileDetails(fileVO,fileList);

			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return filesList;
	}
	
	
	public List<FileVO> getNewsForConstituency(List<Long> candidateIds , FileVO fileVO){
		List<FileVO> filesList = null;
		try{
			Long constituencyScopeId = 4L;
			List<Long> constituencyValuesList = new ArrayList<Long>();
			Long tehsilScopeId = 5L;
			List<Long> tehsilIds = new ArrayList<Long>();			
			Long hamletScopeId = 6L;
			List<Long> hamletIds = new ArrayList<Long>();			
			List<Long> panchayitIdsList = new ArrayList<Long>();			
			List<Long> wardIds = new ArrayList<Long>();
			
			constituencyValuesList.add(fileVO.getLocationVal());
			
			List<Object[]> mandalsList = delimitationConstituencyMandalDAO.getMandalsOfConstituency(fileVO.getLocationVal());
			
			 for(Object[] obj:mandalsList)
				 tehsilIds.add((Long)obj[0]);
			 
			 tehsilIds.add(0L);
			 
			 List<Object[]> panchayitisList = panchayatDAO.getPanchayatIdsByMandalIdsList(tehsilIds);
			 
			 
			 for(Object[] obj :panchayitisList)
				 panchayitIdsList.add((Long)obj[0]);
			 
			 panchayitIdsList.add(0L);
		
			 if(panchayitIdsList != null && panchayitIdsList.size() >0)
				 hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
			 
		List<Long> localElectionBodyIds  =  null;
		
		if(tehsilIds != null && tehsilIds.size() != 1)		
			localElectionBodyIds = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(tehsilIds);
		else
		 localElectionBodyIds = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByConstituency(fileVO.getLocationVal());


		if(localElectionBodyIds != null && localElectionBodyIds.size() >0)
		  wardIds = boothDAO.getWardIdsByLocalElectionBodyIds(localElectionBodyIds);
			 
		  
			hamletIds.add(0L);
			
			wardIds.add(0L);
			
			 NewsCountVO newsCountVO = new NewsCountVO();
			 
			 newsCountVO.setCandidateIds(candidateIds);
			 newsCountVO.setConstituencyScopeId(constituencyScopeId);
			 newsCountVO.setConstituencyValuesList(constituencyValuesList);
			 newsCountVO.setTehsilScopeId(tehsilScopeId);
			 newsCountVO.setTehsilIds(tehsilIds);
			 newsCountVO.setHamletScopeId(hamletScopeId);
			 newsCountVO.setHamletIds(hamletIds);
			 newsCountVO.setCategoryId(fileVO.getCategoryId());
			 newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			 newsCountVO.setStartIndex(fileVO.getStartIndex());
			 newsCountVO.setMaxResults(fileVO.getMaxResult());
			 
			 if(wardIds != null && wardIds.size() >0){
				 
				 newsCountVO.setWardScopeId(8L);
				 newsCountVO.setWardIdsList(wardIds);
				 
				 
			 }
			   
			 
			 List<Object[]> fileList  = null;
			 
			 if(localElectionBodyIds != null && localElectionBodyIds.size() >0){
				 newsCountVO.setMuncipalityScopeId(7L);
				 newsCountVO.setMuncipalityValuesList(localElectionBodyIds);
				 
				 if(wardIds != null && wardIds.size() >0)
					fileList = fileGallaryDAO.getNewsByForConstituencyWithMuncipalityWithWards(newsCountVO);
				 else				 
				   fileList = fileGallaryDAO.getNewsByForConstituencyWithMuncipality(newsCountVO);
				 
			 }else{
			 
			 fileList = fileGallaryDAO.getNewsByForConstituency(newsCountVO);
			 }
			 
			 filesList =  processAllFileDetails(fileVO,fileList);
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return filesList;
		
	}
	
	
	public List<FileVO> processAllFileDetails(FileVO fileVO , List<Object[]> filesList){
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		try{
			List<Long> fileIdsList = new ArrayList<Long>();
			
			for(Object[] obj1:filesList)
				fileIdsList.add(((File)obj1[0]).getFileId());
			
			Map<Long ,Long> problemCountMap = new HashMap<Long, Long>();
			List<Object[]> problemCountList = newsProblemDAO.getProblemIdsByFileIds(fileIdsList);
			
			for(Object[] problemCount:problemCountList)
				problemCountMap.put((Long)problemCount[1], (Long)problemCount[0]);
			
			 for(Object[] obj:filesList){
		    	   
		    	   File fileDetails = (File)obj[0];
		    	   String visibility = obj[2].toString();
		    	   
		    	   FileVO file = new FileVO();
		    	   file.setIsPrivate(visibility);
		    	   
		    	   if(fileDetails.getRegionScopes() != null)
		    		   file.setLocationScopeValue(fileDetails.getRegionScopes().getScope());
		    	   
		    	   
		    	   
		    	   if(problemCountMap.get(fileDetails.getFileId()) != null){
		    		   file.setIsProblem("true");
		    		   file.setProblemId(problemCountMap.get(fileDetails.getFileId()));
		    	   }
		    	   else
		    		   file.setIsProblem("false");
		    	   
		    	   
		    	     Set<FileSourceLanguage> fileSourceLanguageSet = fileDetails.getFileSourceLanguage();
		    		 List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(); 
		    		 Set<String> sourceSet = new HashSet<String>();
		 			 Set<String> languageSet = new HashSet<String>();
		 			 StringBuilder sourceVal =new StringBuilder();
					 StringBuilder languageVal =new StringBuilder();
					 
					 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
						 if(fileVO.getSourceId() == null && fileVO.getLanguegeId() == null)
						 {
							  setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							  if(fileSourceLanguage.getSource() != null)
							  sourceSet.add(fileSourceLanguage.getSource().getSource());
							  if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
						 else if(fileVO.getLanguegeId() != null){
							 if(fileVO.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
							 {
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
								 if(fileSourceLanguage.getSource() != null)
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								 if(fileSourceLanguage.getLanguage() != null)
								  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
							 }
						 }
						 else if(fileVO.getSourceId() != null){
							 if(fileVO.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
							 { 
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
								 if(fileSourceLanguage.getSource() != null)
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								 if(fileSourceLanguage.getLanguage() != null)
								  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
							 }
						 }
						 
					 }
					 
					 
					 for(String source:sourceSet){
						 sourceVal.append(source);
						 sourceVal.append("-");
					 }
		             for(String language:languageSet){
		            	 languageVal.append(language);
		            	 languageVal.append("-");
					 }
		             if(sourceVal != null && sourceVal.length() > 0)
		             sourceVal.deleteCharAt(sourceVal.length() - 1);
		             if(languageVal != null && languageVal.length() > 0)
		             languageVal.deleteCharAt(languageVal.length() - 1);
		             file.setMultipleSource(fileVOSourceLanguageList.size());
		             Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
		             file.setFileVOList(fileVOSourceLanguageList);
				
				
	    		  file.setSource(sourceVal!=null?sourceVal.toString():"");
	    		  file.setLanguage(languageVal!=null?languageVal.toString():"");
		    	  file.setTitle(fileDetails.getFileTitle());
		    	  file.setDescription(fileDetails.getFileDescription());
		    	  file.setContentId((Long)obj[1]);
		    	  file.setFileDate(fileDetails.getFileDate()!=null?fileDetails.getFileDate().toString():"");
		    	   
		    	  if(fileDetails.getRegionScopes() != null && fileDetails.getLocationValue() != null)
		    	  file.setLocationName(candidateDetailsService.getLocationDetails(fileDetails.getRegionScopes().getRegionScopesId() ,fileDetails.getLocationValue()));
		    	  fileList.add(file);
		    	   
		       }
			
		}catch(Exception e){e.printStackTrace();
			
		}
		return fileList;
		
	}
	
	
	public List<FileVO> getNewsByLocationAndCategory1(FileVO fileVO){
		
		log.debug("Entered into the getNewsByLocationAndCategory service method");
		
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		List<Object[]> filesList =new ArrayList<Object[]>();
		
		try{
		
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(fileVO.getUserId());
	
	        List<Long> locationValuesList = new ArrayList<Long>();			
	        locationValuesList.add(fileVO.getLocationVal());
			
			
	       if(fileVO.getLocationId() == 3){
	    	   
	       
				//locationValuesList = getAllBoothsInPanchayat(fileVO.getLocationVal(),
						//fileVO.getPublicationId(), locationValuesList);
	    	   locationValuesList = new ArrayList<Long>();
	    	   
	    	  List<Object[]> hamletsList =  panchayatHamletDAO.getHamletsOfAPanchayat(fileVO.getLocationVal());
	    	  
               for(Object[] obj:hamletsList){
            	   locationValuesList.add((Long)obj[0]);
            	   
            	   
               }
               fileVO.setLocationId(6L);
				
				//fileVO.setLocationId(9L);
	       }
	       
	       if(fileVO.getLocationId() == 7){
	    	   
	    	  List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getLocationVal());
	    	  
	    	  if(localElectionBodyList != null && localElectionBodyList.size() >0){
	    		  
	    		  
	    		  locationValuesList = new ArrayList<Long>();	    		  
	    		  locationValuesList.add((Long)localElectionBodyList.get(0));
	    		  
	    	  }
	    		  
	       }
	       
	       
	       filesList = fileGallaryDAO
					.getNewsByLocationAndCategory(
							candidateIds,fileVO,locationValuesList);
	       
	       
	       for(Object[] obj:filesList){
	    	   
	    	   File fileDetails = (File)obj[0];
	    	   
	    	   FileVO file = new FileVO();
	    	   
	    	   
	    	     Set<FileSourceLanguage> fileSourceLanguageSet = fileDetails.getFileSourceLanguage();
	    		 List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(); 
	    		 Set<String> sourceSet = new HashSet<String>();
	 			 Set<String> languageSet = new HashSet<String>();
	 			 StringBuilder sourceVal =new StringBuilder();
				 StringBuilder languageVal =new StringBuilder();
				 
				 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
					 if(fileVO.getSourceId() == null && fileVO.getLanguegeId() == null)
					 {
						  setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
						  if(fileSourceLanguage.getSource() != null)
						  sourceSet.add(fileSourceLanguage.getSource().getSource());
						  if(fileSourceLanguage.getLanguage() != null)
						  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
					 }
					 else if(fileVO.getLanguegeId() != null){
						 if(fileVO.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
						 {
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							 if(fileSourceLanguage.getSource() != null)
							 sourceSet.add(fileSourceLanguage.getSource().getSource());
							 if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
					 }
					 else if(fileVO.getSourceId() != null){
						 if(fileVO.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
						 { 
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							 if(fileSourceLanguage.getSource() != null)
							 sourceSet.add(fileSourceLanguage.getSource().getSource());
							 if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
					 }
					 
				 }
				 
				 
				 for(String source:sourceSet){
					 sourceVal.append(source);
					 sourceVal.append("-");
				 }
	             for(String language:languageSet){
	            	 languageVal.append(language);
	            	 languageVal.append("-");
				 }
	             if(sourceVal != null && sourceVal.length() > 0)
	             sourceVal.deleteCharAt(sourceVal.length() - 1);
	             if(languageVal != null && languageVal.length() > 0)
	             languageVal.deleteCharAt(languageVal.length() - 1);
	             file.setMultipleSource(fileVOSourceLanguageList.size());
	             Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
	             file.setFileVOList(fileVOSourceLanguageList);
			
			
    		  file.setSource(sourceVal!=null?sourceVal.toString():"");
    		  file.setLanguage(languageVal!=null?languageVal.toString():"");
	    	  file.setTitle(fileDetails.getFileTitle());
	    	  file.setDescription(fileDetails.getFileDescription());
	    	  file.setContentId((Long)obj[1]);
	    	  file.setFileDate(fileDetails.getFileDate()!=null?fileDetails.getFileDate().toString():"");
	    	   
	    	  fileList.add(file);
	    	   
	       }
			
			return fileList;
		}catch(Exception e){
			
			log.error("Exception raised in  the getNewsByLocationAndCategory service method");
			e.printStackTrace();
			return null;
			
		}		
	}
	
	
public Long saveContentNotesByContentId(final Long contentId ,final  String commentText){
		
		log.debug("Entered into the saveCommentByContentId service method");
		 Long savedId = null;
		
		try{
			 savedId = (Long)transactionTemplate.execute(new TransactionCallback() {
				public Long doInTransaction(TransactionStatus status) {
			ContentNotes contentNotes = new ContentNotes();
			//contentNotes.setContentId(contentId);
			contentNotes.setFileGallary(fileGallaryDAO.get(contentId));
			contentNotes.setIsDelete("false");
			contentNotes.setNotes(commentText);
			contentNotes.setInsertedTime(new Date());
			contentNotes.setUpdatedTime(new Date());
			
			contentNotes = contentNotesDAO.save(contentNotes);
			
			return contentNotes.getContentNotesId();
				}
			});
			
		}catch(Exception e){
			log.error("Exception raised in saveCommentByContentId method :"+e);
			e.printStackTrace();
		}
		
		return savedId;
		
	}
	
	
	public List<CommentVO> getContentNotesByContentId(Long contentId,
			Long registrationId) {
		
		log.debug("Entered into the getCommnetsByContentId service method");
		List<CommentVO> commentsList = new ArrayList<CommentVO>();
		
		try{			
			List<Object[]> comments = contentNotesDAO
					.getContentNotesByContentId(contentId);		
			
			for(Object[] obj:comments){				
				CommentVO commentVO = new CommentVO();				
				commentVO.setCommentId((Long)obj[0]);
				commentVO.setComment(obj[1].toString());
				commentsList.add(commentVO);			
			}
			
		}catch(Exception e){
			log.error("Exception raised in getCommnetsByContentId method :"+e);
			e.printStackTrace();	
			return null;
		}
		return commentsList;
	}
	
	
	public String removeContentNotes(Long contentNotesId){
		log.debug("Entered into the removeContentNotes method");
		
		try{
			//contentNotesDAO.remove(contentNotesId);
			
			ContentNotes contentNews = contentNotesDAO.get(contentNotesId);
			
			contentNews.setIsDelete(IConstants.TRUE);
			
			contentNews = contentNotesDAO.save(contentNews);
			
		}catch(Exception e){
			log.error("Exception raised in the removeContentNotes method :" +e);
		 e.printStackTrace();			
		}
		return IConstants.SUCCESS;		
	}
	*/
	public String addFlagToNews(Long contentId , Long userId){
		
		log.debug("Entered into the addFlagToNews service method");
		
		try{
			Long count = 0L;
			
			List<Long> flagCountList = newsFlagDAO.getCountForFlagByContentId(contentId);
			
			if(flagCountList != null && flagCountList.size() >0)
				count = flagCountList.get(0);
			
			if(count.longValue() == 0 ){
				
				NewsFlag newsFlag = new NewsFlag();
				
				newsFlag.setFileGallary(fileGallaryDAO.get(contentId));
				newsFlag.setUser(userDAO.get(userId));			
				newsFlagDAO.save(newsFlag);
			}
						
		}catch(Exception e){			
			log.debug("Exception raised in addFlagToNews service method");			
			e.printStackTrace();
		}
		return IConstants.SUCCESS;
		
	}
	/*
	public String checkForFlag(Long contentId){
		
		log.debug("Entered into the checkForFlag service method");
		
		try{
		
		List<Long> contentIds = new ArrayList<Long>();		
		
		contentIds.add(contentId);
		
		List<Object[]> countList= newsFlagDAO.getCountForFlagByFileGallaryId(contentIds);
		
		if(countList != null && countList.size() >0){
		 Object[] obj = countList.get(0);
		 
		 Long count =(Long) obj[1];
		 
		 if( count.longValue() == 0)
			 return "false";
		 else
			 return "true";
		 
		}else
			return "false";
		}catch(Exception e){
			log.error("Exception raised in checkForFlag service method");
			e.printStackTrace();
			return null;
			
		}
	}
	
	
	public String removeFlagForNews(Long contentId){
		
		log.debug("Entered into the removeFlagForNews service method");
		
		try{
			
			newsFlagDAO.removeFlagForNews(contentId);
			return IConstants.SUCCESS;
			
		}catch(Exception e){
			log.error("Exception raised in removeFlagForNews service method");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public String updateVisibility(Long contentId , String visibility){
		
		log.debug("Entered into the updateVisibility service method");
		
		try{
		
			FileGallary fileGallary = fileGallaryDAO.get(contentId);
			
			fileGallary.setIsPrivate(visibility);
			if(visibility.equalsIgnoreCase("false"))
			{
				
				Gallary gallary = gallaryDAO.get(fileGallary.getGallary().getGallaryId());
				gallary.setIsPrivate(visibility);
			}
			fileGallaryDAO.save(fileGallary);
			
			return IConstants.SUCCESS;
			
		}catch(Exception e){
			log.error("Exception raised in updateVisibility service method");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String checkForVisibilityStatus(Long contentId){
		
		log.debug("Entered into the checkForVisibilityStatus service method");
		
		try{
			
			List<String> visibilityList = fileGallaryDAO.checkForVisibilityStatus(contentId);
			
			if(visibilityList != null && visibilityList.size() >0){
				
				String visibility = visibilityList.get(0);
				
				if(visibility.equalsIgnoreCase("false"))
					return IConstants.PUBLIC;
				else
					return IConstants.PRIVATE;				
				
			}
				
		
			
			return IConstants.SUCCESS;
			
		}catch(Exception e){
			log.error("Exception raised in checkForVisibilityStatus service method");
			e.printStackTrace();
			return null;
		}
		
	}

	*//**
	 * This Method is uesd for get the flged news and noted news 
	 * @param FileVO inputs
	 * @return List<FileVO>
	 * @date 01-04-2013
	 *//*
	public List<FileVO> getNewsForFlagedAndNoted(FileVO inputs) {
		List<FileVO> newsList = new ArrayList<FileVO>();
		try {
			log.debug("Entered into the getNewsForFlagedAndNOted service method in NewsMonitoringService");
			List<Object[]> fileList = fileGallaryDAO.getNewsForRegisterUsers1(inputs);
			List<Long> contentIds = new ArrayList<Long>();
			FileVO fileVO = null;
			List<FileGallary> newsFile = null; 
			Long flaggedCount = null;
			Long notedCount = null;
	  	    for(Object[] obj:fileList)
	  	    {
	  		  contentIds.add((Long)obj[0]);
	  		  
	  	    }
	  	    if(inputs.getTitle().equalsIgnoreCase("flagedNews"))
	  	    {
	  	    	if(contentIds.size() >0)
	  	    	{
	  	    		newsFile= newsFlagDAO.getFlagedMews(contentIds);
	  	    	}
	  	    }	
	  	    else if(inputs.getTitle().equalsIgnoreCase("flagedCount"))
	  	    {
	  	    	if(contentIds.size() >0)
	  	    	{
	  	    		flaggedCount= newsFlagDAO.getFlaggedNewsCount(inputs,contentIds);
	  	    		fileVO = new FileVO();
	  	    		fileVO.setTotalFlaggedNews(flaggedCount.intValue());
	  	    		newsList.add(fileVO);
	  	    	}
	  	    	
	  	    }
	  	    else if(inputs.getTitle().equalsIgnoreCase("notedCount"))
	  	    {
	  	    	if(contentIds.size() >0)
	  	    	{
	  	    		notedCount= contentNotesDAO.getNotedNewsCount(inputs,contentIds);
	  	    		fileVO = new FileVO();
	  	    		fileVO.setTotalFlaggedNews(notedCount.intValue());
	  	    		newsList.add(fileVO);
	  	    	}
	  	    	
	  	    }
	  	    else 
		    {
		    	if(contentIds.size() >0)
		    	{
		    		newsFile = contentNotesDAO.getNotedNews(contentIds);
		    	}
		    }
	  	    
	  	    if(newsFile != null && newsFile.size() > 0 )
	  	    {
	  	    		for (FileGallary news : newsFile)
	  	    		{
	  	    			fileVO = new FileVO();
	  	    			File file = news.getFile();
	  	    			Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
	  	    			fileVO.setTitle(file.getFileTitle());
	  	    			fileVO.setFileId(file.getFileId());
	  	    			fileVO.setVisibility(news.getIsPrivate());
	  	    			fileVO.setDescription(file.getFileDescription());
	  	    			fileVO.setFileDate(file.getFileDate().toString());
	  	    			fileVO.setLocationScopeValue(file.getRegionScopes().getScope());
	  	    			fileVO.setGallaryName(news.getGallary().getName());
	  	    			if(file.getCategory() != null)
	  	    			fileVO.setCategoryName(file.getCategory().getCategoryType());
	  	    			fileVO.setGallaryId(news.getFileGallaryId());
	  	    			for (FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet) {
	  	    				fileVO.setSource(fileSourceLanguage.getSource().getSource());
						}
	  	    			fileVO.setLocationValue(candidateDetailsService.getLocationDetails(file.getRegionScopes().getRegionScopesId(), file.getLocationValue()));
	  	    			newsList.add(fileVO);
					}
	  	    }
	  	    	
			} 
		    catch (Exception e) 
		    {
		    	log.error("Exception raised in the getNewsForFlagedAndNOted service method in NewsMonitoringService", e);
			}
		
		return newsList;
	}
	
*/
	
	public List<FileVO> getNewsForAuser(FileVO inputs){
	      log.debug("Enter into getNewsForRegisterUsers Method of NewsMonitoringService ");
	       List<FileVO> fileVOList = new ArrayList<FileVO>();
	    	try{
	    		
	    		List<Long> contentIds = new ArrayList<Long>();
	    		Map<Long , Long> countMap = new HashMap<Long, Long>();
	    		Map<Long , Long> notesCountMap = new HashMap<Long, Long>();
	    		List<Object[]> countList = new ArrayList<Object[]>();
	    		List<Object[]> notesCountList = new ArrayList<Object[]>();
	    		Date fromDate = null;
	    		Date toDate = null;
	    		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    		
	    	 // List<Object[]> fileList = fileGallaryDAO.getNewsForRegisterUsers1(inputs);
	    		
	    		if(inputs.getFromDateStr() != null && !inputs.getFromDateStr().equalsIgnoreCase(""))
	    		 fromDate = format.parse(inputs.getFromDateStr());
	    		
	    		if(inputs.getToDateStr() != null && !inputs.getToDateStr().equalsIgnoreCase(""))
		    	 toDate = format.parse(inputs.getToDateStr());
	    		
	    		 List<Object[]> fileList = fileGallaryDAO.getAllTheNewsForAUser(inputs.getUserId(),fromDate,toDate);
	    	  
	    	  for(Object[] obj:fileList)	    		  
	    		  contentIds.add((Long)obj[0]);
	    	  
	    	  if(contentIds.size() >0)	    	  
	    	      countList= newsFlagDAO.getCountForFlagByFileGallaryId(contentIds);
	    	  
	    	  
	    	  
	    	  if(contentIds.size() > 0)
	    	        notesCountList =  contentNotesDAO.getContentNotesCountByContentIds(contentIds);
	    	    
	    	    for(Object[] obj:notesCountList)	    	    	
	    	    	notesCountMap.put((Long)obj[0], (Long)obj[1]);
	    	    	
	    	  
	    	  
	    	  for(Object[] obj:countList)
	    		  countMap.put((Long)obj[0],(Long)obj[1]);	
	    		  
	    	  for(Object[] obj:fileList){
	    		  
	    		  File file = (File)obj[1];
	    		  FileVO  fileVO = new FileVO();
	    		  
                if(fileVOList.size() == 0){
              	  fileVO.setTotalFlaggedNews(countList.size());
              	  fileVO.setTotalNotesNews(notesCountMap.size());
                }
	    		  
	    		  if(countMap.get((Long)obj[0]) == null)
	    			  fileVO.setFlagSet("false");
	    		  else
	    			  fileVO.setFlagSet("true");
	    		  
	    		  if(notesCountMap.get((Long)obj[0]) != null){
	    			  
	    			  if(notesCountMap.get((Long)obj[0]).longValue() >0)
	    				  fileVO.setNotesExist("true");
	    			  else
	    				  fileVO.setNotesExist("false");
	    			  
	    		  }
	    		  
	    		  fileVO.setContentId((Long)obj[0]);
	    		  fileVO.setKeywords(file.getKeywords()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getKeywords())):"");
	    		  fileVO.setFileDate(file.getFileDate() != null?file.getFileDate().toString():" ");
	    		  
	    		  if(file.getFileDate() != null)
	    		  {
	    		   String dateString =file.getFileDate().getDate()+"/"+(file.getFileDate().getMonth()+1)+"/"+(file.getFileDate().getYear()+1900);
	    		   fileVO.setFileDateAsString(dateString);
	    		  }
	    		  fileVO.setComments(file.getComment());
	    		  
	    		  fileVO.setDisplayImagePath(file.getFilePath());
	    		  fileVO.setVisibility(obj[2].toString());
	    		  
	    		  if(file.getRegionScopes() != null)
	    		    fileVO.setLocationScope(file.getRegionScopes().getRegionScopesId());
	    		  
	    		  if(file.getLocationValue() != null)
	    		    fileVO.setLocationValue(file.getLocationValue().toString());
	    		  
	    		  fileVO.setFileId(file.getFileId());
	    		  fileVO.setName(file.getFileName());
	    		  fileVO.setPath(file.getFilePath());
	    		  fileVO.setNewsDescription(file.getNewsDescription()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getNewsDescription())):"");
	    		  fileVO.setFileTitle1(file.getFileTitle()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle())):"");
	    		  fileVO.setDescription(file.getFileDescription()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription())):"");
	    		  String fileDate = file.getFileDate().toString();
	    		  String dateObj = fileDate.substring(8,10)+'-'+fileDate.substring(5,7)+'-'+fileDate.substring(0,4);
	    		  fileVO.setFileDate(dateObj!=null?dateObj:"");
	    		  fileVO.setFileGallaryId((Long)obj[3]);
	    		  fileVO.setGallaryName(obj[4].toString());
	    		  
	    		  Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
	    		  List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(); 
	    		  Set<String> sourceSet = new HashSet<String>();
	 			  Set<String> languageSet = new HashSet<String>();
	 			 StringBuilder sourceVal =new StringBuilder();
				 StringBuilder languageVal =new StringBuilder();
					 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
						 if(inputs.getSourceId() == null && inputs.getLanguegeId() == null)
						 {
							  setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							  if(fileSourceLanguage.getSource() != null)
							  sourceSet.add(fileSourceLanguage.getSource().getSource());
							  if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
						 else if(inputs.getLanguegeId() != null){
							 if(inputs.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
							 {
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
								 if(fileSourceLanguage.getSource() != null)
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								 if(fileSourceLanguage.getLanguage() != null)
								 languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
							 }
						 }
						 else if(inputs.getSourceId() != null){
							 if(inputs.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
							 { 
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList); 
								 if(fileSourceLanguage.getSource() != null)
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								 if(fileSourceLanguage.getLanguage() != null)
								  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
							 }
						 }
						 
					 }
					 for(String source:sourceSet){
						 sourceVal.append(source);
						 sourceVal.append("-");
					 }
		             for(String language:languageSet){
		            	 languageVal.append(language);
		            	 languageVal.append("-");
					 }
		             if(sourceVal != null && sourceVal.length() > 0)
		             sourceVal.deleteCharAt(sourceVal.length() - 1);
		             if(languageVal != null && languageVal.length() > 0)
		             languageVal.deleteCharAt(languageVal.length() - 1);
				fileVO.setMultipleSource(fileVOSourceLanguageList.size());
				Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
				fileVO.setFileVOList(fileVOSourceLanguageList);
				
				
	    		  fileVO.setSource(sourceVal!=null?sourceVal.toString():"");
	    		  fileVO.setLanguage(languageVal!=null?languageVal.toString():"");
	    		  fileVO.setCategoryId(file.getCategory()!=null?file.getCategory().getCategoryId():null);
	    		  fileVO.setCategoryType(file.getCategory()!=null?file.getCategory().getCategoryType():"N/A");
	    		  fileVO.setNewsImportanceId(file.getNewsImportance()!=null?file.getNewsImportance().getNewsImportanceId():null);
	    		  fileVO.setImportance(file.getNewsImportance()!=null?file.getNewsImportance().getImportance():"");
	    		  fileVO.setLocationScope(file.getRegionScopes()!=null?file.getRegionScopes().getRegionScopesId():null);
	    		  fileVO.setLocationScopeValue(file.getRegionScopes()!=null?file.getRegionScopes().getScope():"");
	    		  fileVO.setLocation(file.getLocationValue()!=null?file.getLocationValue():null);
	    		  fileVO.setLocationVal(file.getLocationValue()!=null?file.getLocationValue():null);
	    		  if(file.getRegionScopes()!=null)
	    		  fileVO.setLocationValue(candidateDetailsService.getLocationDetails(file.getRegionScopes().getRegionScopesId(), file.getLocationValue()));
	    		  
	    		  fileVOList.add(fileVO);
	    	  }
	    	}
	    	catch(Exception e){
	    		log.error("Exception rised in  getNewsForRegisterUsers Method of NewsMonitoringService", e);
	    		e.printStackTrace();
	    	}
	    	  return fileVOList;
	      }
	
	
	public FileVO getTotalNews(FileVO inputs){
	      log.debug("Enter into getTotalNews() Method of NewsMonitoringService ");
	      
	      FileVO resultVO = new FileVO();
	      List<FileVO> fileVOList = null;
	    	try{
	    	List<String> userType = userDAO.getUserType(inputs.getUserId());	
	    	Long scopeval = 0l;	
	    	 Date fromDate = null;
	    	 Date toDate = null;
	    	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    		
	    	 if(inputs.getFromDateStr() != null && !inputs.getFromDateStr().equalsIgnoreCase(""))
	    	   fromDate = format.parse(inputs.getFromDateStr());
	    		
	    	 if(inputs.getToDateStr() != null && !inputs.getToDateStr().equalsIgnoreCase(""))
		       toDate = format.parse(inputs.getToDateStr());
	    		if(inputs.getScope().equalsIgnoreCase("ALL"))
	    			scopeval = 0l;
	    		else if(inputs.getScope().equalsIgnoreCase("DISTRICT"))
	    			scopeval = 3l;
	    		else if(inputs.getScope().equalsIgnoreCase("ASSEMBLY CONSTITUENCY"))
	    			scopeval = 4l;
	    		else if(inputs.getScope().equalsIgnoreCase("PARLIAMENT CONSTITUENCY"))
	    			scopeval = 5l;
	    		
	    	// List<File> filesList = fileDAO.getTotalFilesList(inputs.getUserId(), fromDate, toDate,inputs.getStartIndex(),inputs.getMaxResult());
	    		if(inputs.getIsSelectedContent() == true)
	    			inputs.setUserId(inputs.getUserId());
	    		else
	    			inputs.setUserId(0l);	
	    	 List<File> filesList = fileDAO.getTotalFilesListByLocation(inputs.getUserId(), fromDate, toDate,inputs.getStartIndex(),inputs.getMaxResult(),inputs.getLocationId(),scopeval); 
		    if(filesList != null && filesList.size() > 0)
	    	{
	    	   fileVOList = new ArrayList<FileVO>();
	    	   for(File file:filesList)
	    	   {
		    		   try{
			    		 FileVO fileVO = new FileVO();
			    		 fileVO.setFileId(file.getFileId());
			    		 fileVO.setTitle(file.getFileTitle() != null? StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle())):"");
			    		 fileVO.setDescription(file.getFileDescription() != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription())): " ");
			    		 
			    		 if(file.getFileDate() != null)
			    		 {
			    		   String dateString =file.getFileDate().getDate()+"/"+(file.getFileDate().getMonth()+1)+"/"+(file.getFileDate().getYear()+1900);
			    		   fileVO.setFileDateAsString(dateString);
			    		 }
			    		  
			    		 //if(file.getRegionScopes() != null)
			    		   //fileVO.setLocationScope(file.getRegionScopes().getRegionScopesId());
			    		  
			    		  String fileDate = file.getFileDate().toString();
			    		  String dateObj = fileDate.substring(8,10)+'-'+fileDate.substring(5,7)+'-'+fileDate.substring(0,4);
			    		  fileVO.setFileDate(dateObj!=null?dateObj:"");
			    		  
			    		  if(file.getFont() != null && file.getFont().getFontId().equals(1))
			    		   fileVO.setEenaduTeluguFontStr("Eenadu Telugu");
			    		  if(file.getDescFont() !=null)
				    		   fileVO.setDescEenadu(true);
			    		  
			    		  String fileSourceStr = "";
			    		  Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
			    		  if(fileSourceLanguageSet != null && fileSourceLanguageSet.size() > 0)
			    		   for(FileSourceLanguage fileSourceLanguage:fileSourceLanguageSet)
			    			   fileSourceStr +=fileSourceLanguage.getSource().getSource()+",";
			    		  
			    		  if(fileSourceStr.length() > 0)
			    			  fileSourceStr = fileSourceStr.substring(0, fileSourceStr.length()-1);
			    		  
			    		  fileVO.setSource(fileSourceStr);
			    		  fileVO.setUserType(userType.get(0).toString());
			    		 // fileVO.setLocationScopeValue(file.getRegionScopes()!=null?file.getRegionScopes().getScope():"");
			    		  //fileVO.setLocation(file.getLocationValue()!=null?file.getLocationValue():null);
			    		 // fileVO.setLocationVal(file.getLocationValue()!=null?file.getLocationValue():null);
			    		  //if(file.getRegionScopes()!=null)
			    		  fileVO.setLocationValue(reportService.getLocationDetails1(file.getFileId(),null));
			    		  
			    		  
			    		  fileVOList.add(fileVO);
		    		   }catch (Exception e) {
						log.error("exception raised in getNewsForRegisterUsers  Method of NewsMonitoringService at inner loop",e);
					}
	    	     }
	    	  // resultVO.setUserType(userType.get(0).toString());
	    	   resultVO.setFileVOList(fileVOList);
	    	   //resultVO.setCount(fileDAO.getTotalFilesListCount(fromDate, toDate).intValue());
	    	   resultVO.setCount(fileDAO.getTotalFilesListCountByLocation(inputs.getUserId(), fromDate, toDate,inputs.getLocationId(),scopeval).intValue());
	    	  }
	    	}
	    	catch(Exception e){
	    		log.error("Exception rised in  getNewsForRegisterUsers Method of NewsMonitoringService", e);
	    		e.printStackTrace();
	    	}
	    	  return resultVO;
	      }
	
	

	private void setSourceLanguageAndPaths(FileSourceLanguage fileSourceLanguage,List<FileVO> fileVOSourceLanguageList){
		 
	     FileVO fileVOSourceLanguage = new FileVO();
		 fileVOSourceLanguage.setSource(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSource():"");
		 if(fileSourceLanguage.getSource()!=null)
			
		 fileVOSourceLanguage.setSourceId(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSourceId():null);
		 fileVOSourceLanguage.setLanguage(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():"");
		 if(fileSourceLanguage.getLanguage()!=null)
			 
		 
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

	public List<FileVO> getAllSourceDetails(){
		log.debug("Enter into getAllSourceDetails Method of NewsMonitoringService ");
	    List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	    FileVO fileVO = null;
	 try{ 	
		List<Object[]>  sourceList = sourceDAO.getSourceDetails();
		 for(Object[] source: sourceList){
			 fileVO = new FileVO();
			 fileVO.setIds((Long)source[0]);
			 fileVO.setNames(source[1].toString());
			 
			 returnFileVOList.add(fileVO);
		  }
	 }
 	 catch(Exception e){
 		log.error("Exception rised in  getAllSourceDetails Method of NewsMonitoringService", e);
 		e.printStackTrace();
 	 }
 	  return returnFileVOList;
		
  }
	
	public List<FileVO> getAllCategoryDetails(){
		log.debug("Enter into getAllCategoryDetails Method of NewsMonitoringService ");
	    List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	    FileVO fileVO = null;
	 try{ 	
		List<Object[]>  categoryList = categoryDAO.getCategoryDetails();
		for(Object[] category: categoryList){
			 fileVO = new FileVO();
			 fileVO.setIds((Long)category[0]);
			 fileVO.setNames(category[1].toString());
			 
			 returnFileVOList.add(fileVO);
		 }
	  }
	 catch(Exception e){
	   log.error("Exception rised in  getAllCategoryDetails Method of NewsMonitoringService", e);
	   e.printStackTrace();
	 }
	 return returnFileVOList;
		
  }
	
	public List<FileVO> getAllSourceLanguageDetails(){
	   log.debug("Enter into getAllSourceLanguageDetails Method of NewsMonitoringService ");
	   List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	   FileVO fileVO = null;
	 try{ 	
	   List<Object[]>  sourceLanguageList = sourceLanguageDAO.getSourceLanguageDetails();
	   for(Object[] sourceLanguage: sourceLanguageList){
			 fileVO = new FileVO();
			 fileVO.setIds((Long)sourceLanguage[0]);
			 fileVO.setNames(sourceLanguage[1].toString());
			 
			 returnFileVOList.add(fileVO);
		 }
	 }
	 catch(Exception e){
	  log.error("Exception rised in  getAllSourceLanguageDetails Method of NewsMonitoringService", e);
	  e.printStackTrace();
	}
	return returnFileVOList;
		
  }

	public List<FileVO> getAllNewsImportanceDetails(){
		log.debug("Enter into getAllNewsImportanceDetails Method of NewsMonitoringService ");
	    List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	    FileVO fileVO = null;
	 try{ 
		List<Object[]>  newsImportanceList = newsImportanceDAO.getNewsImportanceDetailsWithOutOrderBy();
		for(Object[] newsImportance: newsImportanceList){
			 fileVO = new FileVO();
			 fileVO.setIds((Long)newsImportance[0]);
			 fileVO.setNames(newsImportance[1].toString());
			 
			 returnFileVOList.add(fileVO);
		}
	  }
	 catch(Exception e){
	   log.error("Exception rised in  getAllNewsImportanceDetails Method of NewsMonitoringService", e);
	   e.printStackTrace();
	 }
	return returnFileVOList;
  }
	
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
	
	public ResultStatus updateDeleteNews(FileVO fileVO,String task,List<FileVO> sourceIds,List<FileVO> languageIds){
		 if(log.isDebugEnabled())
			 log.debug("Enter into updateDeleteNews Method of NewsMonitoringService ");
		 ResultStatus resultStatus = new ResultStatus();
	  try{ 
		 if(task.equalsIgnoreCase("Update")){
			 
			 Category category  = null;
			 File file = fileDAO.get(fileVO.getFileId());
			 
			 if(fileVO.getCategoryId() != 0)
			  category = categoryDAO.get(fileVO.getCategoryId());
			 
			 NewsImportance newsImportance = newsImportanceDAO.get(fileVO.getNewsImportanceId());
			 file.setFileTitle(escapeUnicode(StringEscapeUtils.unescapeHtml(fileVO.getTitle())));
			 file.setFileDescription(escapeUnicode(StringEscapeUtils.unescapeHtml(fileVO.getDescription())));
			 file.setNewsDescription(escapeUnicode(StringEscapeUtils.unescapeHtml(fileVO.getNewsDescription())));

			 file.setCategory(category);
			 file.setNewsImportance(newsImportance);
			 
			 file.setKeywords(escapeUnicode(StringEscapeUtils.unescapeHtml(fileVO.getKeywords())));
			 
			 
			 String[] dateArray = fileVO.getFileDate().split("/");
			 
			 Integer date = Integer.parseInt(dateArray[0]);
			 Integer month = Integer.parseInt(dateArray[1]) - 1;
			 Integer year = Integer.parseInt(dateArray[2]) - 1900;
			 
			 Date fileDate = new Date();
			 
			 fileDate.setDate(date);
			 fileDate.setMonth(month);
			 fileDate.setYear(year);
			 
			 file.setFileDate(fileDate);
			 
			 if(fileVO.getLocationScope() != 0){
				 
				 if(fileVO.getLocationScope().longValue() != 7){
					 file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));				 
					 file.setLocationValue(fileVO.getRegionValue());
				 }else{
					 file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));	
					 List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getRegionValue());
					 
					 if(localElectionBodyList != null && localElectionBodyList.size() >0){
						 
						List<Long> locationValuesList = new ArrayList<Long>();	    		  
						 file.setLocationValue((Long)localElectionBodyList.get(0));
						 
					 }
					 
					 
				 }
			 }
			 
			 fileDAO.save(file);
			 
			 for(FileVO languageFile:languageIds){
				 SourceLanguage sourceLanguage = sourceLanguageDAO.get(languageFile.getLanguegeId());
				 FileSourceLanguage	 fileSourceLanguage = fileSourceLanguageDAO.get(languageFile.getFileSourceLanguageId());
				 fileSourceLanguage.setLanguage(sourceLanguage);
				 fileSourceLanguageDAO.save(fileSourceLanguage);
			 }
			 for(FileVO sourceFile:sourceIds){
				 Source source = sourceDAO.get(sourceFile.getSourceId());
				 FileSourceLanguage	 fileSourceLanguage = fileSourceLanguageDAO.get(sourceFile.getFileSourceLanguageId());
				 fileSourceLanguage.setSource(source);
				 fileSourceLanguageDAO.save(fileSourceLanguage);
			 }
			 
			 DateUtilService dateUtilService = new DateUtilService();
			 
			List<FileGallary> fileGallaries = fileGallaryDAO.getFileGallariesByFileId(fileVO.getFileId());
			
			
			for(FileGallary fileGallary:fileGallaries){
				
				fileGallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
				fileGallaryDAO.save(fileGallary);
				
				
			}
			 
			 
			 
			 
         if(fileVO.getFileGallaryId() !=0 && fileVO.getGallaryId() != 0){
				 
				 FileGallary fileGallary = fileGallaryDAO.get(fileVO.getFileGallaryId());
				 fileGallary.setGallary(gallaryDAO.get(fileVO.getGallaryId()));
				 
				 if(fileVO.getVisibility().equalsIgnoreCase("public"))
				    fileGallary.setIsPrivate("false");
				 else
				    fileGallary.setIsPrivate("true");
				 
				 fileGallaryDAO.save(fileGallary);
				 
				 
			 }
         
         if(fileVO.getFlagSet().equalsIgnoreCase("false"))        	  
       	  newsFlagDAO.removeFlagForNews(fileVO.getFileGallaryId());          
         else
       	  addFlagToNews(fileVO.getFileGallaryId(),fileVO.getUserId());
			 
			 if(fileVO.getVisibility().equalsIgnoreCase("public")){
				 
				 fileGallaryDAO.updateVisibility(fileVO.getFileId(),"false");
				 
			 }else{
				 
				 fileGallaryDAO.updateVisibility(fileVO.getFileId(),"true");
				 
			 }
			 
		 }
		 else if(task.equalsIgnoreCase("Delete")){
			 fileGallaryDAO.deleteFile(fileVO.getFileId());
		 }
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	  }
	  catch(Exception e){
		  log.error("Exception rised in updateDeleteNews Method of NewsMonitoringService ",e);
		   e.printStackTrace();
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	  }
	  
	  return resultStatus;
	 }

	public List<SelectOptionVO> getCandidates(){
		List<SelectOptionVO> candidates=new ArrayList<SelectOptionVO>();
		List<Object[]> list1=candidateRelatedNewsDAO.getCandidates();
		if(list1!=null){
		for(Object[] params:list1){
			SelectOptionVO selectOptionVO=new SelectOptionVO();
			selectOptionVO.setId((Long)params[0]);
			selectOptionVO.setName(params[1] !=null?params[1].toString():"");
			candidates.add(selectOptionVO);
		}
		}
		return candidates;
	}
	
	public ResultStatus storeSourceDetails(String value)
	{
		ResultStatus resultStatus = null;
		
		try {
			resultStatus = new ResultStatus();
			 log.debug("Enter into storeSourceDetails Method of NewsMonitoringService ");
			 
			 List<Object> sourceId=sourceDAO.getSourceIdBySource(value);
			 
			 if(sourceId.size()<=0){
				 Source source = new Source();
				 source.setSource(value);
				 sourceDAO.save(source);
				 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			 }else{
				 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				 resultStatus.setMessage("exist");
			 }
		} catch (Exception e) {
			log.error("Exception rised in storeSourceDetails Method of NewsMonitoringService ",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return resultStatus;
	}
	
	public List<SelectOptionVO> getCandidatesByRemovingDots(){
		List<SelectOptionVO> candidates=new ArrayList<SelectOptionVO>();
		
		//List<Object[]> list1=candidateRelatedNewsDAO.getCandidates();
		List<Object[]> list1=candidatePartyFileDAO.getCandidatesNewsCount();
		if(list1!=null){
		for(Object[] params:list1){
			SelectOptionVO selectOptionVO=new SelectOptionVO();
			selectOptionVO.setId((Long)params[1]);
			String name = "";
			if(params[2].toString().substring(0, 1).equalsIgnoreCase("."))
				name +=params[2].toString().substring(1);
			else
				name += params[2].toString();
			name += " ("+(Long)params[0]+")";
			
			 selectOptionVO.setName(name);
			candidates.add(selectOptionVO);
		}
		}
		return candidates;
	}
	public List<SelectOptionVO> getCandidatesNewsCount(){
		List<SelectOptionVO> candidates=new ArrayList<SelectOptionVO>();
		try{
		Map<Long,Long> candidateNewsCountmap = new HashMap<Long, Long>();
		List<Object[]> list1=candidatePartyFileDAO.getSourceCandidates();
		List<Object[]> list2=candidatePartyFileDAO.getDestinationCandidates();
		if(list1!=null){
			for(Object[] params:list1){
				Long count = candidateNewsCountmap.get(params[1]);
				if(count == null)
				candidateNewsCountmap.put((Long)params[1], (Long)params[0]) ;	
				else
				candidateNewsCountmap.put((Long)params[1], (Long)params[0]+count) ;	
				}
		}
		if(list2 != null && list2.size() > 0)
		{
			for(Object[] params1 : list2)
			{
				Long count = candidateNewsCountmap.get(params1[1]);
				if(count == null)
				candidateNewsCountmap.put((Long)params1[1], (Long)params1[0]) ;	
				else
				candidateNewsCountmap.put((Long)params1[1], (Long)params1[0]+count) ;		
			}
		}
		if(candidateNewsCountmap != null)
		for(Long candidateId : candidateNewsCountmap.keySet())
		{
			SelectOptionVO selectOptionVO=new SelectOptionVO();
			selectOptionVO.setId(candidateId);
			Long newsCount = candidateNewsCountmap.get(candidateId);
			String name = "";
			String candidateName = candidateDAO.getCandidateName(candidateId);
			if(candidateName.toString().substring(0, 1).equalsIgnoreCase("."))
				name +=candidateName.toString().substring(1);
			else
				name +=candidateName.toString();
			name += " ("+newsCount+")";
			selectOptionVO.setName(name);
			candidates.add(selectOptionVO);	
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
		
		Collections.sort(candidates, sortcandidates);
		return candidates;
	}
	
	
	 public static Comparator<SelectOptionVO> sortcandidates = new Comparator<SelectOptionVO>()
			 {
				  
			  public int compare(SelectOptionVO newsCountVO, SelectOptionVO newsCountVO2)
				{
				   return (newsCountVO.getName()).compareTo(newsCountVO2.getName());
				}
		    };
	
	public List<CandidateNewsCountVO> getNewsCountForACandidate(String fromDateStr,String toDateStr,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,String tempVar,String locationScope)
	{
		try{
		 List<CandidateNewsCountVO> candidateNewsCountVOList = new ArrayList<CandidateNewsCountVO>(0);
		 Date fromDate = null;
		 Date toDate = null;
		 List<Object[]> list = new ArrayList<Object[]>(0);
		 
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
		  fromDate = format.parse(fromDateStr);
		 if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
		  toDate = format.parse(toDateStr);
		 
		  Long locationScopeId = 0L;
		  if(!locationScope.equalsIgnoreCase("null") && !locationScope.equalsIgnoreCase(""))
		   locationScopeId = regionScopesDAO.getRegionScopeIdByScope(locationScope);
		 
		 List<Object[]> fromNominationList = candidateRelatedNewsDAO.getNewsCountForACandidate(fromDate,toDate,categoryIdsList,galleryIdsList,locationIdsList,locationScopeId,tempVar,872L);
		 if(fromNominationList != null && fromNominationList.size() > 0)
		  list.addAll(fromNominationList);
		 
		 List<Object[]> fromCandidatePartyList = candidateRelatedNewsDAO.getNewsCountForACandidateFromCandidateParty(fromDate,toDate,categoryIdsList,galleryIdsList,locationIdsList,locationScopeId,tempVar,872L);
		 if(fromCandidatePartyList != null && fromCandidatePartyList.size() > 0)
		  list.addAll(fromCandidatePartyList);
		 
		 if(list != null && list.size() > 0)
		 {
			CandidateNewsCountVO candidateNewsCountVO = null;
			for(Object[] params : list)
			{
				candidateNewsCountVO = checkCandidateNewsCountVOExist((Long)params[1], candidateNewsCountVOList);
			  if(candidateNewsCountVO == null)
			  {
			    candidateNewsCountVO = new CandidateNewsCountVO();
			    candidateNewsCountVO.setId((Long)params[1]);
			    candidateNewsCountVO.setName(params[2]!=null?params[2].toString():"");
			    candidateNewsCountVOList.add(candidateNewsCountVO);
			  }
			  if(params[3].equals(2L))
				candidateNewsCountVO.setStateNewsCount((Long)params[0]);
			  else if(params[3].equals(3L))
				candidateNewsCountVO.setDistrictNewsCount((Long)params[0]);
			  else if(params[3].equals(4L))
				candidateNewsCountVO.setConstituencyNewsCount((Long)params[0]);
			  else if(params[3].equals(5L))
				candidateNewsCountVO.setMandalNewsCount((Long)params[0]);
			  else if(params[3].equals(6L))
				candidateNewsCountVO.setVillageNewsCount((Long)params[0]);
			  else if(params[3].equals(7L))
				candidateNewsCountVO.setLocalEleBodyNewsCount((Long)params[0]);
			  else if(params[3].equals(8L))
				candidateNewsCountVO.setWardNewsCount((Long)params[0]);
			  else if(params[3].equals(9L))
				candidateNewsCountVO.setBoothNewsCount((Long)params[0]);
				
			}
			
			Collections.sort(candidateNewsCountVOList,sortCandidateNames);
		 }
		List<Long> stateList = new ArrayList<Long>();
		List<Long> districtList = new ArrayList<Long>();
		List<Long> constituencyList = new ArrayList<Long>();
		List<Long> mandalList = new ArrayList<Long>();
		List<String> names= new ArrayList<String>();
		if(candidateNewsCountVOList != null && candidateNewsCountVOList.size() > 0)
		{
		  for(CandidateNewsCountVO list1 : candidateNewsCountVOList)
		  {
			stateList.add(list1.getStateNewsCount());
			districtList.add(list1.getDistrictNewsCount());
			constituencyList.add(list1.getConstituencyNewsCount());
			mandalList.add(list1.getMandalNewsCount());
			names.add(list1.getName());
		  }
		  candidateNewsCountVOList.get(0).setStateCounts(stateList);
		  candidateNewsCountVOList.get(0).setDistrictCounts(districtList);
		  candidateNewsCountVOList.get(0).setConstituencyCounts(constituencyList);
		  candidateNewsCountVOList.get(0).setMandalCounts(mandalList);
		  candidateNewsCountVOList.get(0).setCandidateNames(names);
		}
		 return candidateNewsCountVOList;
		}catch (Exception e) {
		 e.printStackTrace();
		 log.error(" Exception Occured in getNewsCountForACandidate() method, Exception - "+e);
		 return null;
		}
	}
	
	public CandidateNewsCountVO checkCandidateNewsCountVOExist(Long candidateId,List<CandidateNewsCountVO> list)
	{
	  try{
		if(list == null || list.size() == 0)
		 return null;  
		 for(CandidateNewsCountVO newsCountVO:list)
		  if(newsCountVO.getId().equals(candidateId))
			  return newsCountVO;    
		return null;  
	  }catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in checkCandidateNewsCountVOExist() method, Exception - "+e);
		return null;
	}
	}
	
	
	public List<FileVO> getLocationWiseNewsDetailsForACandidate(Long candidateId,String fromDateStr,String toDateStr,String locationScope,Integer startIndex,Integer maxIndex,String galleryIdsStr,String categoryIdsStr)
	{
		List<FileVO> fileVOsList = null;
		try{
			 Date fromDate = null;
			 Date toDate = null;
			 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			 if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
			  fromDate = format.parse(fromDateStr);
			 if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
			  toDate = format.parse(toDateStr);
			Long locationScopeId = regionScopesDAO.getRegionScopeIdByScope(locationScope);
			
			List<Long> galleryIdsList = new ArrayList<Long>(0);
			List<Long> categoryIdsList = new ArrayList<Long>(0);
			StringTokenizer str = null;
			if(!galleryIdsStr.equalsIgnoreCase("null") && !galleryIdsStr.equalsIgnoreCase("") && galleryIdsStr != null)
			{
			  str = new StringTokenizer(galleryIdsStr,",");
			  while (str.hasMoreTokens()) 
				 galleryIdsList.add(Long.parseLong(str.nextToken()));
			}
			if(categoryIdsStr != null && !categoryIdsStr.equalsIgnoreCase("") && !categoryIdsStr.equalsIgnoreCase("null"))
			{
			  str = new StringTokenizer(categoryIdsStr,",");
			  while (str.hasMoreTokens()) 
			  categoryIdsList.add(Long.parseLong(str.nextToken()));
			}
			
			List<FileGallary> fileGallaryList = candidateRelatedNewsDAO.getLocationWiseFileGalleryList(candidateId, fromDate, toDate, locationScopeId, startIndex, maxIndex,galleryIdsList,categoryIdsList);
			if(fileGallaryList != null && fileGallaryList.size() > 0)
			{
				fileVOsList = new ArrayList<FileVO>(0);
				//candidateDetailsService.setfileGallaryDetails(fileGallaryList, fileVOsList);
				fileVOsList.get(0).setCount(candidateRelatedNewsDAO.getLocationWiseFileGalleryList(candidateId, fromDate, toDate, locationScopeId, null, null,galleryIdsList,categoryIdsList).size());
			}
			return fileVOsList;
		}catch (Exception e) {
			
			log.error("Exception Occured in getLocationWiseNewsDetailsForACandidate() method, Exception - "+e);
		 return fileVOsList;
		}
	}
	
	public List<SelectOptionVO> getCategoryList(String fromDateStr, String toDateStr,String locationScope,List<Long> locationIdsList)
	{
		try{
			List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date fromDate = null;
			Date toDate = null;
			Long locationScopeId = 0L;
			if(locationScope != null && !locationScope.equalsIgnoreCase(""))
			 locationScopeId = regionScopesDAO.getRegionScopeIdByScope(locationScope);
			
			if(!fromDateStr.equalsIgnoreCase("null") && !fromDateStr.equalsIgnoreCase(""))
			 fromDate = format.parse(fromDateStr);
			if(!toDateStr.equalsIgnoreCase("null") && !toDateStr.equalsIgnoreCase(""))
			 toDate = format.parse(toDateStr);
			
			List<Object[]> list = candidateRelatedNewsDAO.getCategoryList(fromDate, toDate, locationScopeId, locationIdsList);
			if(list !=null && list.size() > 0)
			 for(Object[] params : list)
			  selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!= null?params[1].toString():""));
			
			return selectOptionVOList;
		}catch (Exception e) {
		 e.printStackTrace();
		 log.error("Exception Occured in getCategoryList() method, Exception -"+e);
		 return null;
		}
	}
	
	public List<SelectOptionVO> getGalleryListForSelectedCategory(List<Long> categoryIdsList)
	{
	  try{
		  List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		  List<Object[]> list = candidateRelatedNewsDAO.getGalleryListForSelectedCategory(categoryIdsList);
		  if(list !=null && list.size() > 0)
			for(Object[] params : list)
			 selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!= null?params[1].toString():""));
		  return selectOptionVOList;
	  }catch (Exception e) {
		  e.printStackTrace();
		  log.error(" Exception Occured in getGalleryListForSelectedCategory() method, Exception - "+e);
		 return null;
	}
	}
	
	public List<SelectOptionVO> getLocationsListByScopeId(String locationScope,String fromDateStr, String toDateStr)
	{
		try{
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate = null;
		Date toDate = null;
		Long locationScopeId = regionScopesDAO.getRegionScopeIdByScope(locationScope);
		if(!fromDateStr.equalsIgnoreCase("null") && !fromDateStr.equalsIgnoreCase(""))
		 fromDate = format.parse(fromDateStr);
		if(!toDateStr.equalsIgnoreCase("null") && !toDateStr.equalsIgnoreCase(""))
		 toDate = format.parse(toDateStr);
		
		List<Long> locationIdsList = candidateRelatedNewsDAO.getLocationValuesByLocationScopeId(locationScopeId, fromDate, toDate);
		if(locationIdsList != null && locationIdsList.size() > 0)
		{
		  if(locationScopeId.equals(3L))
		  {
			List<Object[]> list = districtDAO.getDistrictNamesByDistrictIdsList(locationIdsList);
			for(Object[] params:list)
			 selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!= null?params[1].toString():" "));
		  }
		}
			
		return selectOptionVOList;
		}catch (Exception e) {
		 e.printStackTrace();
		 log.error("Exception Occured in getLocationsListByScopeId() method, Exception - "+e);
		 return null;
		}
	}
	
	
	public NewsCountVO getRespondedAndNotRespondedNewsCount(String fromDateStr,String toDateStr,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,String locationScope,String tempVar)
	{
		NewsCountVO newsCountVO = new NewsCountVO();
	  try{
		  Long locationScopeId = 0L;
		  Date fromDate = null;
		  Date toDate = null;
		  List<Long> totalNewsIdsList = new ArrayList<Long>(0);
		  List<Long> responseNewsFileGalleryIds = new ArrayList<Long>(0);
		  
		  if(locationScope != null && !locationScope.equalsIgnoreCase(""))
		   locationScopeId = regionScopesDAO.getRegionScopeIdByScope(locationScope);
		 
		  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		  if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
			  fromDate = format.parse(fromDateStr);  
		  
		  if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
			  toDate = format.parse(toDateStr);  
		  
		  
		  
		  // total news count 
		/*  List<Long> totalNewsIdsListFromCandidateRElatedNews = candidateRelatedNewsDAO.getTotalNewsCount(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, tempVar,null,null,0L);
		  
		  List<Long> totalNewsIdsListFroCandidateParty = candidateRelatedNewsDAO.getTotalNewsCountCustom(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, tempVar);
		  if(totalNewsIdsListFromCandidateRElatedNews != null && totalNewsIdsListFromCandidateRElatedNews.size() > 0)
			  totalNewsIdsList.addAll(totalNewsIdsListFromCandidateRElatedNews);
		  
		  if(totalNewsIdsListFroCandidateParty != null && totalNewsIdsListFroCandidateParty.size() > 0)
		  {
			 for(Long fileGalleryId : totalNewsIdsListFroCandidateParty)
			  if(!totalNewsIdsList.contains(fileGalleryId))
				  totalNewsIdsList.add(fileGalleryId); 
		  }
		  
		  newsCountVO.setTotalNewsCount((long)totalNewsIdsList.size());*/
		  // response news count 
		  
		  
		 /* if(totalNewsIdsList != null && totalNewsIdsList.size() > 0)
		  {
			//responseNewsFileGalleryIds = candidateNewsResponseDAO.getResponseNewsCount(totalNewsIdsList);
			 List<Long> responseNewsFileGalIdsFromCanRelNews = candidateRelatedNewsDAO.getResponseCountBasedTotalNewsCount(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, tempVar,null,null,0L);
			  
			 List<Long> responseNewsIdsFromCandidateParty = candidateRelatedNewsDAO.getResponseCountBasedTotalNewsCountCustom(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, tempVar);
			
			 if(responseNewsFileGalIdsFromCanRelNews != null && responseNewsFileGalIdsFromCanRelNews.size() > 0)
			  responseNewsFileGalleryIds.addAll(responseNewsFileGalIdsFromCanRelNews);
			
			  if(responseNewsIdsFromCandidateParty != null && responseNewsIdsFromCandidateParty.size() > 0)
			  {
			   for(Long filegalleryId:responseNewsIdsFromCandidateParty)
				 if(!responseNewsFileGalleryIds.contains(filegalleryId))
					responseNewsFileGalleryIds.add(filegalleryId);
			  }
			  
		  }
		  */
		//  newsCountVO.setResponseNewsCount((long)responseNewsFileGalleryIds.size());
		  
		  // not respondent news count 
		 // List<Long> notRespondFileGalleryIds = candidateRelatedNewsDAO.getNotRespondFileGalleryIds(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, responseNewsFileGalleryIds);
		// List<Long> notRespondFileGalleryIds = (List<Long>)candidateRelatedNewsDAO.getNotResponseCountBasedTotalNewsCount(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, tempVar,null,null,0L);
	    //	long count2=0;
		  //response table 
		  SelectOptionVO optionVO = new SelectOptionVO();
		  List<Object[]> list = null;
		  List<Object[]>  listFromNamination = null;
		  List<Object[]>  listFromCandidateParty = null;
		  List<Object[]>  listFromNamination1 = null;
		  List<Object[]>  listFromCandidateParty1 = null;
			 Map<Long , Object[]> utilMap  = new HashMap<Long,Object[]>();
			 Map<Long , Object[]> utilMap1  = new HashMap<Long,Object[]>();

		 
			// List<Object[]> list = candidateRelatedNewsDAO.getRespondNewsPartyDetails(responseNewsFileGalleryIds);
			// List<Object[]> list1 = candidateRelatedNewsDAO.getRespondNewsPartyDetailsCustom(responseNewsFileGalleryIds);
			   listFromNamination = candidateRelatedNewsDAO.getRespondNewsPartyDetails(fromDate, toDate, 872l, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, tempVar, null, null, null,"");
			   listFromCandidateParty = candidateRelatedNewsDAO.getRespondNewsPartyDetailsForCandidateTable(fromDate, toDate, 872l, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, tempVar, null, null, null,"");
			       if(listFromNamination == null || listFromNamination.size()== 0)
			    	 //return    listFromCandidateParty;
			    	   System.out.println("anil");
			
			       // iterate first loop  for partyids 
			       
			       Iterator<Object[]> list1=   listFromNamination.iterator();
			  while(  list1.hasNext())
			 {
				 Object [] newob = list1.next();
				 utilMap.put((Long)newob[2], newob);
				 
			  }
			  // second loop for to add party ids
			 Iterator<Object[]> list2 =  listFromCandidateParty.iterator();
			 while(  list2.hasNext())
			 {
				 Object [] newob = list2.next();
				 Object [] exObj= utilMap.get((Long)newob[2]);
				 if(exObj != null)
					 exObj[0]=(Long)exObj[0]+(Long)newob[0];
				 else 
					 utilMap.put((Long)newob[2], newob);

			 }
			   List<SelectOptionVO> responseNewsCountList = new ArrayList<SelectOptionVO>(0); 
			  long count = 0l;
			Iterator<Long > itr = utilMap.keySet().iterator();
			             while(itr.hasNext()){
					  Object[] params  =  utilMap.get((Long)itr.next());
					  SelectOptionVO optionVO2 = new SelectOptionVO();
					  optionVO2.setId((Long)params[2]);
					  optionVO2.setPopulateId((Long)params[0]);
					  optionVO2.setName(params[1]!=null?params[1].toString():"");
					  responseNewsCountList.add(optionVO2);
					  count+=((Long)params[0]).longValue();
		  }
				
					//responseNewsCountList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
				  optionVO.setSelectOptionsList(responseNewsCountList);
			  
			  newsCountVO.setResponseNewsCount(count);
			  //count2 =count;
			/* if(list1 != null && list1.size() > 0)
			  {
				  for(Object[] params : list1)
				  {
					  SelectOptionVO optionVO2 = new SelectOptionVO();
					  optionVO2.setId((Long)params[2]);
					  optionVO2.setPopulateId((Long)params[0]);
					  optionVO2.setName(params[1]!=null?params[1].toString():"");
					  responseNewsCountList.add(optionVO2);  
				  }
					//responseNewsCountList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
				  optionVO.setSelectOptionsList(responseNewsCountList);
			  }*/
		

		  //not response table
		
			  long count1 = 0l;

			  listFromNamination1= candidateRelatedNewsDAO.getNotResponseCount(fromDate, toDate,872l,null,null,null,null,tempVar,null,null,null,"partyDetails");
				// List<Object[]> list1 = candidateRelatedNewsDAO.getRespondNewsPartyDetailsCustom(notRespondFileGalleryIds);
			  listFromCandidateParty1  =  candidateRelatedNewsDAO.getNotResponseCountForCandidateParty(fromDate, toDate,872l,null,null,null,null,tempVar,null,null,null,"partyDetails");
			  List<SelectOptionVO> notResponseNewsCountList = new ArrayList<SelectOptionVO>(0);; 

		       if(listFromNamination1 == null || listFromNamination1.size()== 0)
			    	 //return    listFromCandidateParty;
			    	   System.out.println("anil");
			
			       // iterate first loop  for partyids 
			       
			       Iterator<Object[]> list3=   listFromNamination1.iterator();
			  while(  list3.hasNext())
			 {
				 Object [] newob = list3.next();
				 utilMap1.put((Long)newob[2], newob);
				 
			  }
			  // second loop for to add party ids
			 Iterator<Object[]> list4 =  listFromCandidateParty1.iterator();
			 while(  list4.hasNext())
			 {
				 Object [] newob = list4.next();
				 Object [] exObj= utilMap1.get((Long)newob[2]);
				 if(exObj != null)
					 exObj[0]=(Long)exObj[0]+(Long)newob[0];
				 else 
					 utilMap1.put((Long)newob[2], newob);

			 }
			
			Iterator<Long > itr1 = utilMap1.keySet().iterator();
			             while(itr1.hasNext()){
					  Object[] params  =  utilMap1.get((Long)itr1.next());
					  SelectOptionVO optionVO2 = new SelectOptionVO();
					  optionVO2.setId((Long)params[2]);
					  optionVO2.setPopulateId((Long)params[0]);
					  optionVO2.setName(params[1]!=null?params[1].toString():"");
					  notResponseNewsCountList.add(optionVO2);
					  count1+=((Long)params[0]).longValue();
		  }
				  optionVO.setSelectOptionsList1(notResponseNewsCountList);
			  
			  newsCountVO.setNotResponseNewsCount(count1);
		
		
			  newsCountVO.setTotalNewsCount(count+count1);
			  
		  newsCountVO.setSelectOptionVO(optionVO);
		  return newsCountVO;
	  }catch (Exception e) {
		  e.printStackTrace();
		  log.error("Exception Occured in getRespondedAndNotRespondedNewsCount() method, Exception - "+e);
		  return null;
	}
  }
	
	  public void setSelectOptionVOList(List<Object[]> list, List<SelectOptionVO> selectOptionVOList)
	  {
		 try{
			 if(list != null && list.size() > 0)
			 {
			  
			 }
			 
		 }catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in setSelectOptionVOList() method, Exception - "+e);
		}
	  }


  public List<FileVO> getNewsDetailsForAParty(NewsDetailsVO vo)
  {
	  List<FileVO> fileVOList = new ArrayList<FileVO>(0);
	  List<Long> fileGalleryIdsList = null;
	 try{
		 Date fromDate = null;
		 Date toDate = null;
		 
		 String fromDateStr = vo.getFromDateStr();
		 String toDateStr = vo.getToDateStr();
		 
		 Long locationScopeId = 0L;
		 StringTokenizer str = null;
		 List<Long> categoryIdsList = new ArrayList<Long>(0);
		 List<Long> galleryIdsList = new ArrayList<Long>(0);
		 List<Long> locationIdsList = new ArrayList<Long>(0);
		 
		  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		  if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
			  fromDate = format.parse(fromDateStr);  
		  
		  if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
			  toDate = format.parse(toDateStr);  
		  
		 if(vo.getNewsType() != null)
		  vo.setNewsType(vo.getNewsType().trim());
		 
		 if(vo.getCategoryIdsList()!= null && !vo.getCategoryIdsList().equalsIgnoreCase(""))
		 {
			str = new StringTokenizer(vo.getCategoryIdsList(),",");
			while(str.hasMoreTokens())
			  categoryIdsList.add(Long.parseLong(str.nextToken()));
		 }
		 
		 if(vo.getGalleryIdsList()!= null && !vo.getGalleryIdsList().equalsIgnoreCase(""))
		 {
			str = new StringTokenizer(vo.getGalleryIdsList(),",");
			while(str.hasMoreTokens())
				galleryIdsList.add(Long.parseLong(str.nextToken()));
		 }
		 
		 if(vo.getLocationIdsList()!= null && !vo.getLocationIdsList().equalsIgnoreCase(""))
		 {
			str = new StringTokenizer(vo.getLocationIdsList(),",");
			while(str.hasMoreTokens())
				locationIdsList.add(Long.parseLong(str.nextToken()));
		 }
		 
		 
		 if(vo.getLocationScope() != null && vo.getLocationScope().equalsIgnoreCase(""))
		  locationScopeId = regionScopesDAO.getRegionScopeIdByScope(vo.getLocationScope());
		 
		if(vo.getNewsType() != null && vo.getNewsType().equalsIgnoreCase("total"))
			//  fileGalleryIdsList = candidateRelatedNewsDAO.getTotalNewsCount(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, vo.getTempVar(),vo.getStartIndex(),vo.getMaxIndex(),vo.getSelectedPartyId());
			fileGalleryIdsList = buildTotalIds(fromDate, toDate, 872L , vo.getTempVar(),vo.getStartIndex(),vo.getMaxIndex(),null,vo.getCandidateId(),vo.getTempVarForParty());
		 
		  /*fileGalleryIdsList = new ArrayList<Long>(0);
			
		  List<Long> totFileGalleryIdsFromNomina = candidateRelatedNewsDAO.getTotalNewsCount(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, vo.getTempVar(),vo.getStartIndex(),vo.getMaxIndex(),vo.getSelectedPartyId(),vo.getCandidateId(),vo.getTempVarForParty());
		  List<Long> totFileGalleryIdsFromCandParty = candidateRelatedNewsDAO.getTotalNewsCountFromCandidateParty(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, vo.getTempVar(),vo.getStartIndex(),vo.getMaxIndex(),vo.getSelectedPartyId(),vo.getCandidateId(),vo.getTempVarForParty()); 
		  if(totFileGalleryIdsFromNomina != null && totFileGalleryIdsFromNomina.size() > 0)
		   fileGalleryIdsList.addAll(totFileGalleryIdsFromNomina);
		  
		  if(totFileGalleryIdsFromCandParty != null && totFileGalleryIdsFromCandParty.size() > 0)
			fileGalleryIdsList.addAll(totFileGalleryIdsFromCandParty);
		  */
		 
		else if(vo.getNewsType() != null && vo.getNewsType().equalsIgnoreCase("responded")){
		// fileGalleryIdsList = candidateRelatedNewsDAO.getResponseCountBasedTotalNewsCount(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, vo.getTempVar(),vo.getStartIndex(),vo.getMaxIndex(),vo.getSelectedPartyId());
			if(!vo.getSelectedPartyId().equals(0l)){
			fileGalleryIdsList = candidateRelatedNewsDAO.getResponseForParty(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId,null,null,null,vo.getSelectedPartyId());
			List<Long> items =     candidateRelatedNewsDAO.getResponseForPartyForPartyCandidate(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId,null,null,null,vo.getSelectedPartyId());
        for(Long id : items)
        {   if(fileGalleryIdsList == null ||fileGalleryIdsList.size() == 0  )
        	fileGalleryIdsList =  new ArrayList<Long>();
        
        	fileGalleryIdsList.add(id);
        }
			
			}else
			{
				  List<Object[]> list = candidateRelatedNewsDAO.getRespondNewsIds(fromDate, toDate, 872l, null, null, null, null,  "all",null,null, null,vo.getCandidateId(),vo.getTempVarForParty());
				 
				  fileGalleryIdsList = new ArrayList<Long>();
                  
					List<Object[]> items =     candidateRelatedNewsDAO.getRespondNewsIdsForCandidateParty(fromDate, toDate,872l,null,null,null,null,"all",null,null,null,vo.getCandidateId(),vo.getTempVarForParty());
					  for(Object[]  id : items)
				        {    fileGalleryIdsList.add((Long)id[0]);
				        }
				  if(list != null && list.size() > 0)
				  {
					  for(Object[] params : list)
					  {
						  fileGalleryIdsList.add((Long)params[0]);
						
						 
					  }
					
						//responseNewsCountList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
					
				  }
			}
		}
		else if(vo.getNewsType() != null && vo.getNewsType().equalsIgnoreCase("notResponded")){
			if(!vo.getSelectedPartyId().equals(0l)){
				fileGalleryIdsList = new ArrayList<Long>();
			List <Long> fileGalleryIdsList1 =(List<Long>) candidateRelatedNewsDAO.getNotResponseCountBasedTotalNewsCount(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId,"all",null,null,vo.getSelectedPartyId(),vo.getCandidateId(),vo.getTempVarForParty());
		    List<Long> items =  (List<Long>)candidateRelatedNewsDAO.getNotResponseCountBasedTotalNewsCountForCandidateParty(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId,"all",null,null,vo.getSelectedPartyId(),vo.getCandidateId(),vo.getTempVarForParty());//.getNotResponseCountBasedTotalNewsCount(null,null,872,null,null,null,null,null,null,null,null);
		//this if future reference for how to avoid slow data 
		    if(fileGalleryIdsList1 !=null && fileGalleryIdsList1.size()>0)
			{ 				 
				
		    	fileGalleryIdsList = fileGalleryIdsList1;
		    	
			/*for(Long obj:fileGalleryIdsList1 )
			{
				int count =candidateNewsResponseDAO.getFileGalleryIdByResponseGalleryId(obj).size();
				
				if(count == 0)

					fileGalleryIdsList.add(obj);
				}*/
               				
			}
		    for(Long id : items)
	        {   if(fileGalleryIdsList == null ||fileGalleryIdsList.size() == 0  )
	        	fileGalleryIdsList =  new ArrayList<Long>();
	        
	        	fileGalleryIdsList.add(id);
	        }
			}
			else
			{
				 List<Long> list = (List<Long>)candidateRelatedNewsDAO.getNotResponseCountBasedTotalNewsCount(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId,"all",null,null,0L,vo.getCandidateId(),vo.getTempVarForParty());
				  fileGalleryIdsList = new ArrayList<Long>();
				  List<Long> items =  (List<Long>)candidateRelatedNewsDAO.getNotResponseCountBasedTotalNewsCountForCandidateParty(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId,"all",null,null,vo.getSelectedPartyId(),vo.getCandidateId(),vo.getTempVarForParty());//.getNotResponseCountBasedTotalNewsCount(null,null,872,null,null,null,null,null,null,null,null);
					//this if future reference for how to avoid slow data 
					    if(list !=null && list.size()>0)
						{ 				 
							
					    	fileGalleryIdsList = list;
					    	
						/*for(Long obj:fileGalleryIdsList1 )
						{
							int count =candidateNewsResponseDAO.getFileGalleryIdByResponseGalleryId(obj).size();
							
							if(count == 0)

								fileGalleryIdsList.add(obj);
							}*/
			               				
						}
					    for(Long id : items)
				        {   if(fileGalleryIdsList == null ||fileGalleryIdsList.size() == 0  )
				        	fileGalleryIdsList =  new ArrayList<Long>();
				        
				        	fileGalleryIdsList.add(id);
				        }
				/*  if(list != null && list.size() > 0)
				  {
					  for(Object params : list)
					  { int count =candidateNewsResponseDAO.getFileGalleryIdByResponseGalleryId((Long)params).size();
						if(count>0)
							continue;
						  fileGalleryIdsList.add((Long)params);
						
						 
					  }
					
						//responseNewsCountList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
					
				  }*/
			}
			
			}
		if(fileGalleryIdsList != null && fileGalleryIdsList.size() > 0)
		{
		 List<FileGallary> fileGallaries = fileGallaryDAO.getFileGallariesByFileGallaryIdsList(fileGalleryIdsList.subList(vo.getStartIndex() ,(vo.getMaxIndex()+vo.getStartIndex()) > fileGalleryIdsList.size() ? fileGalleryIdsList.size() : (vo.getMaxIndex()+vo.getStartIndex())));
		 //candidateDetailsService.setfileGallaryDetails(fileGallaries, fileVOList);
		 		 
		 
		 fileVOList.get(0).setCount(fileGalleryIdsList.size());
			
		 /*if(vo.getNewsType() != null && vo.getNewsType().equalsIgnoreCase("total"))
		   //fileVOList.get(0).setCount(candidateRelatedNewsDAO.getTotalNewsCount(fromDate, toDate, 872L, categoryIdsList, galleryIdsList, locationIdsList, locationScopeId, vo.getTempVar(),null,null,vo.getSelectedPartyId()).size());
		 
		 else if(vo.getNewsType() != null && vo.getNewsType().equalsIgnoreCase("responded"))
		  fileVOList.get(0).setCount(fileGalleryIdsList.size());
		 
		 else if(vo.getNewsType() != null && vo.getNewsType().equalsIgnoreCase("notResponded"))
		   fileVOList.get(0).setCount(fileGalleryIdsList.size());*/
		 
		}
		
		
		 return fileVOList;
	 }catch (Exception e) {
		 e.printStackTrace();
		  log.error("Exception Occured in getNewsDetailsForAParty() method, Exception - "+e);
		return fileVOList;
	}
  }
  
  public static Comparator<CandidateNewsCountVO> sortCandidateNames = new Comparator<CandidateNewsCountVO>()
			{
				  
			  public int compare(CandidateNewsCountVO newsCountVO, CandidateNewsCountVO newsCountVO2)
				{
				   return (newsCountVO.getName()).compareTo(newsCountVO2.getName());
				}
		  };
		  
		  public List<CandidateNewsCountVO> getCandidateCritiesNewsDetails(String fromDateStr,String toDateStr)
		  {
		 	 try{
		 		 Date fromDate = null;
		 		 Date toDate = null;
		 		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 		 if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
		 		  fromDate = format.parse(fromDateStr);
		 		 
		 		if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
			 	 toDate = format.parse(toDateStr);
		 		 
		 		 List<Object[]> responseNewsList = new ArrayList<Object[]>(0);
		 		 List<Object[]> notResponseNewsList = new ArrayList<Object[]>(0);
		 		 
		 		 List<CandidateNewsCountVO> candidateNewsCountVOList = new ArrayList<CandidateNewsCountVO>(0);
		 		 
		 		 List<Object[]> responseNewsListFromNamination = candidateRelatedNewsDAO.getRespondNewsPartyDetails(fromDate, toDate, 872l, null, null, null, 0L, "all", null, null, null,"candidateDetails");
		 		 List<Object[]> responseNewsListFromCandidateParty = candidateRelatedNewsDAO.getRespondNewsPartyDetailsForCandidateTable(fromDate, toDate, 872l, null, null, null, 0L, "all", null, null, null,"candidateDetails");
		 		 
		 		 if(responseNewsListFromNamination != null && responseNewsListFromNamination.size() > 0)
		 			responseNewsList.addAll(responseNewsListFromNamination);
		 		 
		 		if(responseNewsListFromCandidateParty != null && responseNewsListFromCandidateParty.size() > 0)
		 		 responseNewsList.addAll(responseNewsListFromCandidateParty);
		 		
		 		 if(responseNewsList != null && responseNewsList.size() > 0)
		 		 {
		 			
		 		  for(Object[] params:responseNewsList)
		 		  {
		 			 CandidateNewsCountVO newsCountVO = new CandidateNewsCountVO();
		 			 newsCountVO.setId((Long)params[4]);
		 			 newsCountVO.setName(params[5] != null?params[5].toString():" ");
		 			 newsCountVO.setResponseNewsCount((Long)params[0]);
		 			 newsCountVO.setPartyName(params[1] != null?params[1].toString():" ");
		 			 candidateNewsCountVOList.add(newsCountVO);
		 		  }
		 		 }
		 		 
		 		List<Object[]> notResponseListFromNamination= candidateRelatedNewsDAO.getNotResponseCount(fromDate, toDate,872l,null,null,null,null,"all",null,null,null,"candidateDetails");
		 		List<Object[]> notResponseListFromCandidateParty  =  candidateRelatedNewsDAO.getNotResponseCountForCandidateParty(fromDate, toDate,872l,null,null,null,null,"all",null,null,null,"candidateDetails");
		 		if(notResponseListFromNamination != null && notResponseListFromNamination.size() > 0)
		 			notResponseNewsList.addAll(notResponseListFromNamination);
		 		
		 		if(notResponseListFromCandidateParty != null && notResponseListFromCandidateParty.size() > 0)
		 			notResponseNewsList.addAll(notResponseListFromCandidateParty);
		 		
		 		if(notResponseNewsList != null && notResponseNewsList.size() > 0)
		 		{
		 			CandidateNewsCountVO newsCountVO = null;
		 			for(Object[] params:notResponseNewsList)
		 			{
		 				newsCountVO = checkCandidateNewsCountVOExist((Long)params[4], candidateNewsCountVOList);
		 				if(newsCountVO == null)
		 				{
		 				 newsCountVO = new CandidateNewsCountVO();
		 				 newsCountVO.setId((Long)params[4]);
		 				 newsCountVO.setName(params[5] != null?params[5].toString():" ");
		 				 newsCountVO.setPartyName(params[1] != null ?params[1].toString():" ");
		 				 candidateNewsCountVOList.add(newsCountVO);
		 				}
		 				newsCountVO.setNotResponseNewsCount((Long)params[0]);
		 				
		 			}
		 		}
		 		 
		 		 if(candidateNewsCountVOList != null && candidateNewsCountVOList.size() > 0)
		 		 {
		 		   for(CandidateNewsCountVO countVO:candidateNewsCountVOList)
		 			  countVO.setTotalNewsCount(countVO.getResponseNewsCount()+countVO.getNotResponseNewsCount()); 
		 		 }
		 		 
		 		 return candidateNewsCountVOList;
		 		 
		 	 }catch (Exception e) {
		 	  e.printStackTrace();
		 	  log.error("Exception Occured in getCandidateCritiesNewsDetails() method, Exception - "+e);
		 	  return null;
		 	}
		  }	  
  public List<Long>  buildTotalIds( Date fromDate,Date toDate,Long partyId,String tempVar,Integer startIndex,Integer maxIndex,Long selectedPartyId,Long candidateId,String tempVarForParty)
  {
	  
	  List<Object[]> list = candidateRelatedNewsDAO.getRespondNewsIds(fromDate, toDate, 872l, null, null, null, null,  "all",null,null, null,candidateId,tempVarForParty);
		 
	  List<Long>    fileGalleryIdsList = new ArrayList<Long>();
      
		List<Object[]> items =     candidateRelatedNewsDAO.getRespondNewsIdsForCandidateParty(fromDate, toDate,872l,null,null,null,null,"all",null,null,null,candidateId,tempVarForParty);
		  for(Object[]  id : items)
	        {    fileGalleryIdsList.add((Long)id[0]);
	        }
	  if(list != null && list.size() > 0)
	  {
		  for(Object[] params : list)
		  {
			  fileGalleryIdsList.add((Long)params[0]);
			
			 
		  }
		
			//responseNewsCountList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
		
	  }
	  
  
  List<Long> list1 = (List<Long>)candidateRelatedNewsDAO.getNotResponseCountBasedTotalNewsCount(fromDate, toDate, 872L, null, null, null, null,"all",null,null,0L,candidateId,tempVarForParty);
  List<Long> items1 =  (List<Long>)candidateRelatedNewsDAO.getNotResponseCountBasedTotalNewsCountForCandidateParty(fromDate, toDate, 872L, null, null, null, null,"all",null,null,0l,candidateId,tempVarForParty);//.getNotResponseCountBasedTotalNewsCount(null,null,872,null,null,null,null,null,null,null,null);
	//this if future reference for how to avoid slow data 
	    if(list1 !=null && list1.size()>0)
		{ 				 
			
	    	//fileGalleryIdsList = list1;
	    	
		for(Long obj:list1 )
		{
			//int count =candidateNewsResponseDAO.getFileGalleryIdByResponseGalleryId(obj).size();
			
			//if(count == 0)

				fileGalleryIdsList.add(obj);
			}
           				
		}
	    for(Long id : items1)
        {   if(fileGalleryIdsList == null ||fileGalleryIdsList.size() == 0  )
        	fileGalleryIdsList =  new ArrayList<Long>();
        
        	fileGalleryIdsList.add(id);
        }
	return fileGalleryIdsList;
  }
  
  public ResultStatus changePassword(final String currentPWD,final String newPWD,final Long userId)
  {
	  final ResultStatus resultStatus = new ResultStatus();
	  try{
		 
		  transactionTemplate.execute(new TransactionCallback() {
		  public Object doInTransaction(TransactionStatus status) {
		String password = userDAO.checkCurrentPasswordExist(currentPWD.trim(), userId);  
			if(password == null)
			{
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  resultStatus.setMessage(" Invalid Current Password ");
			  return resultStatus;
			}
				 User user = userDAO.get(userId);
				 user.setPassword(newPWD.trim());
				 userDAO.save(user);
				 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  return resultStatus;
			  }
		  });
	  }
		  catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in changePassword() method , Exception - "+e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
	return resultStatus;
	  }
 
	  public List<FileVO> setData(List<Long> locationList,Long regionVal,Long userID) {
		  List<FileVO> result = new ArrayList<FileVO>();
		  List<Long> fileGalIds = new ArrayList<Long>();
		  String locationScope ="";
		  if(regionVal == 2)
			  locationScope = IConstants.STATE;
		  else if(regionVal == 3)
			  locationScope = IConstants.DISTRICT;
		  else if(regionVal == 4)
			 locationScope = IConstants.CONSTITUENCY;
		  	int i=0;
			try{
				for(Long locationVal : locationList)
				{
				List<Object[]> list = fileGallaryDAO.getNewsByLocationWise(locationVal,regionVal,userID);
				Map<Long,String> candidateMap = new HashMap<Long, String>();//<filegalId,CandidateName>
				for(Object[] id : list)
				fileGalIds.add((Long)id[1]);
				if(fileGalIds != null && fileGalIds.size() > 0)
				{
				List<Object[]> candidateNamesList = candidateRelatedNewsDAO.getCandidateNameByFileGalleryIdsList(fileGalIds);
				if(candidateNamesList != null && candidateNamesList.size() > 0)
				 for(Object[] params:candidateNamesList)
					candidateMap.put((Long)params[0], params[1] != null?params[1].toString():"");
				 }
				
				FileVO fileVO = new FileVO();
				if(i == 0)
				{
				fileVO.setLocationScopeValue(locationScope);
				fileVO.setRegionValue(regionVal);
				}
				fileVO.setLocationName(candidateDetailsService.getLocationDetails(regionVal,locationVal));
				fileVO.setLocationId(locationVal);
				
				if(list != null && list.size() > 0)
				{
					List<FileVO> subList = new ArrayList<FileVO>();
					for(Object[] params : list)
					{
						FileVO fileVO2 = new FileVO();
						File file =(File) params[0];
						fileVO2.setContentId((Long)params[1]);
						fileVO2.setFileTitle1(file.getFileTitle());
						fileVO2.setFileDescription1(file.getFileDescription());
						String fileDate = file.getFileDate().toString();
			    		String dateObj = fileDate.substring(8,10)+'-'+fileDate.substring(5,7)+'-'+fileDate.substring(0,4);
			    		fileVO2.setFileDate(dateObj!=null?dateObj:"");
			    		Set<FileSourceLanguage> set = file.getFileSourceLanguage();
			    		String sourceString = "";
			    		String language = "";
						for(FileSourceLanguage source:set)
						{
							if(source.getSource() != null && (source.getSource().getSource() != null && !source.getSource().getSource().equals("")))
							sourceString+=source.getSource().getSource()+" ";
							if(source.getLanguage() != null && (source.getLanguage().getLanguage() != null && !source.getLanguage().getLanguage().equals("")))
							language+=source.getLanguage().getLanguage()+" ";
						}
						fileVO2.setSource(sourceString);
						fileVO2.setLanguage(language);
						fileVO2.setCandidateName(candidateMap.get((Long)params[1]));
						fileVO2.setLocationName(candidateDetailsService.getLocationDetails(regionVal,locationVal));
					    subList.add(fileVO2);
					    
					}
					fileVO.setFileVOList(subList);
				}
				
				result.add(fileVO);
				i++;
				}
			}
		catch (Exception e) {
		e.printStackTrace();
		}
			return result;
	}
	  
	  
	  public FileVO getAllNewsDetails(FileVO fileVO)
	  {
		  
		  FileVO resultFileVO = new FileVO();
		  
		  List<FileVO> resultList = new ArrayList<FileVO>();
		  List<Long> locationIds = new ArrayList<Long>();
		  Long locationVal = 0l;
		  String locationScope = null;
		
		  try{
			  Date fromDate = null;
			  Date toDate = null;
			  List<File> list = null;
			  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				if(fileVO.getFromDateStr() != null)
					fromDate = format.parse(fileVO.getFromDateStr() );
				 
				if(fileVO.getToDateStr() != null)
					toDate = format.parse(fileVO.getToDateStr());
			    List<String> userType =  userDAO.getUserType(fileVO.getUserId());
			    List<Long> userIds = new ArrayList<Long>();
			    userIds.add(fileVO.getUserId());
			    String type = "";
			    if(userType != null && userType.size() > 0){
			    	type = userType.get(0);
			    }
			    if("byLevel".equalsIgnoreCase(fileVO.getFileType())){
			    	
			    	if(fileVO.getGallaryIds() != null && fileVO.getGallaryIds().size() > 0)
			    	{
			    		 list =candidatePartyCategoryDAO.getAllTheNewsForAUserBasedByUserId(type,fileVO.getUserId(),fromDate,toDate,fileVO.getImportanceId(),fileVO.getRegionValue(),fileVO.getGallaryIds(),fileVO.getStartIndex(),fileVO.getMaxResult());
			    		 Long count = candidatePartyCategoryDAO.getAllTheNewsCountForAUserBasedByUserIdCount(type,fileVO.getUserId(),fromDate,toDate,fileVO.getImportanceId(),fileVO.getRegionValue(),fileVO.getGallaryIds());
			    		 if(count != null){
			    		   resultFileVO.setCount(count.intValue());
			    		 }else{
			    			 resultFileVO.setCount(0);
			    		 }
			    	}
			    	else if(fileVO.getKeywordIds() != null && fileVO.getKeywordIds().size() > 0)
			    	{
			    		 list =candidatePartyKeywordDAO.getAllTheNewsForAUserBasedByUserId(type,fileVO.getUserId(),fromDate,toDate,fileVO.getImportanceId(),fileVO.getRegionValue(),fileVO.getKeywordIds(),fileVO.getStartIndex(),fileVO.getMaxResult());
			    		 Long count = candidatePartyKeywordDAO.getAllTheNewsCountForAUserBasedByUserId(type,fileVO.getUserId(),fromDate,toDate,fileVO.getImportanceId(),fileVO.getRegionValue(),fileVO.getKeywordIds());
			    		 if(count != null){
				    		   resultFileVO.setCount(count.intValue());
				    		 }else{
				    			 resultFileVO.setCount(0);
				    		 }	
			    	}
			    	else
			    	{
				   list = fileDAO.getAllTheNewsForAUserBasedByUserId(type,fileVO.getUserId(),fromDate,toDate,fileVO.getImportanceId(),fileVO.getRegionValue(),fileVO.getStartIndex(),fileVO.getMaxResult());
				   Long count = fileDAO.getAllTheNewsCountForAUserBasedByUserId(type,fileVO.getUserId(),fromDate,toDate,fileVO.getImportanceId(),fileVO.getRegionValue());
				   if(count != null){
		    		   resultFileVO.setCount(count.intValue());
		    		 }else{
		    			 resultFileVO.setCount(0);
		    		 }	
			    	}
			    }else{
			    	List<Long> ids = null;
			    	if(fileVO.getLocationId().longValue() == 3l){
			    		ids = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByParliamId(fileVO.getLocationVal());
			    	}
			    	if(fileVO.getGallaryIds() != null && fileVO.getGallaryIds().size() > 0)
			    	{
			    		 list = candidatePartyCategoryDAO.getAllTheNewsForAUserBasedByUserIdForALocation(type, fileVO.getUserId(), fromDate, toDate, fileVO.getLocationId(), fileVO.getLocationVal(),ids,fileVO.getGallaryIds(),fileVO.getImportanceId(),fileVO.getStartIndex(),fileVO.getMaxResult());				       
			    		 Long count = candidatePartyCategoryDAO.getAllTheNewsCountForAUserBasedByUserIdForALocation(type, fileVO.getUserId(), fromDate, toDate, fileVO.getLocationId(), fileVO.getLocationVal(),ids,fileVO.getGallaryIds(),fileVO.getImportanceId());
			    		 if(count != null){
				    		   resultFileVO.setCount(count.intValue());
				    		 }else{
				    			 resultFileVO.setCount(0);
				    		 }	
					    	
			    	}
			    	else if(fileVO.getKeywordIds() != null && fileVO.getKeywordIds().size() > 0)
			    	{
			    	 	 list = candidatePartyKeywordDAO.getAllTheNewsForAUserBasedByUserIdForALocation(type, fileVO.getUserId(), fromDate, toDate, fileVO.getLocationId(), fileVO.getLocationVal(),ids,fileVO.getKeywordIds(),fileVO.getImportanceId(),fileVO.getStartIndex(),fileVO.getMaxResult());	       
			    	 	Long count = candidatePartyKeywordDAO.getAllTheNewsForAUserBasedByUserIdForALocationCount(type, fileVO.getUserId(), fromDate, toDate, fileVO.getLocationId(), fileVO.getLocationVal(),ids,fileVO.getKeywordIds(),fileVO.getImportanceId());
			    	 	if(count != null){
				    		   resultFileVO.setCount(count.intValue());
				    		 }else{
				    			 resultFileVO.setCount(0);
				    		 }		
			    	}
			    	else{
				       list = fileDAO.getAllTheNewsForAUserBasedByUserIdForALocation(type, fileVO.getUserId(), fromDate, toDate, fileVO.getLocationId(), fileVO.getLocationVal(),ids,fileVO.getImportanceId(),fileVO.getStartIndex(),fileVO.getMaxResult());
				       
				    	Long count = fileDAO.getAllTheNewsForAUserBasedByUserIdForALocationCount(type, fileVO.getUserId(), fromDate, toDate, fileVO.getLocationId(), fileVO.getLocationVal(),ids);
				    	if(count != null){
				    		   resultFileVO.setCount(count.intValue());
				    		 }else{
				    			 resultFileVO.setCount(0);
				    		 }	
			    	}
			    }
				resultList = setDataForAllLocations(list,fileVO.getUserId());
				 resultFileVO.setFileVOList(resultList);
				
		  }
		  catch (Exception e) {
			log.error("Exception Occured in getAllNewsDetails() method , Exception - ",e);
		}
		return resultFileVO;
	  }
	 
	  
	  public List<FileVO> setDataForAllLocations(List<File> list,Long userID)
	  {
		  List<FileVO> result = new ArrayList<FileVO>();
		  List<Long> fileIds = new ArrayList<Long>();
		  //Map<Long,String> candidateMap = new HashMap<Long, String>();//<filegalId,CandidateName>
		  Map<Long,Map<Long,List<FileVO>>> regionWiseMap = new HashMap<Long, Map<Long,List<FileVO>>>();//<region,Map<location,filesList>>
		  try
		  {
			 /* if(list != null && list.size() > 0)
			  {
				
				for(File file : list){
					fileIds.add(file.getFileId());
				}
				if(fileIds != null && fileIds.size() > 0)
				{
				List<Object[]> candidateNamesList = candidateRelatedNewsDAO.getCandidateNameByFileGalleryIdsList(fileIds);
				if(candidateNamesList != null && candidateNamesList.size() > 0)
				 for(Object[] params:candidateNamesList)
					candidateMap.put((Long)params[0], params[1] != null?params[1].toString():"");
				 }
			  }*/
			  Set<Long> canfileIds = new HashSet<Long>();
			  for(File file : list){
				  canfileIds.add(file.getFileId());
			  }
			  Map<Long,String> candidateNames = getCandidateNames(canfileIds);
			  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    		 DateUtilService dateUtilService = new DateUtilService();
	    		 Date currentDate = dateUtilService.getCurrentDateAndTime();
				for(File file : list)
				{
					
					boolean tempvar=false;
					
					
					FileVO fileVO = new FileVO();
					fileVO.setContentId(file.getFileId());
					fileVO.setFileTitle1(StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle())));
					fileVO.setFileDescription1(StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription())));
					String dateObj = null;
					if(file.getCreatedDate() != null){
					String fileDate = file.getCreatedDate().toString();
		    		 dateObj = fileDate.substring(8,10)+'-'+fileDate.substring(5,7)+'-'+fileDate.substring(0,4);
					}
					String dateObj1 = null;
					if(file.getFileDate() != null){
						String fileDate = file.getFileDate().toString();
			    		 dateObj1 = fileDate.substring(8,10)+'-'+fileDate.substring(5,7)+'-'+fileDate.substring(0,4);
					}
					fileVO.setFileDate(dateObj1!=null?dateObj1:"");
		    		if(dateObj != null && dateObj.equals(sdf.format(currentDate)))
		    		   tempvar= true;
		    		fileVO.setTempvar(tempvar);
		    		if(file.getFont() != null)
		    		 fileVO.setEenaduTeluguFontStr("Eenadu Telugu");
		    		
		    		Set<FileSourceLanguage> set = file.getFileSourceLanguage();
		    		//source setting
		    		String fileSourceStr = "";
		    		if(set != null && set.size() > 0)
		    		 for(FileSourceLanguage sourceLanguage:set)
		    		  fileSourceStr +=sourceLanguage.getSource().getSource()+",";
		    		
		    		if(fileSourceStr != null && fileSourceStr.length() > 0)
		    		 fileSourceStr = fileSourceStr.substring(0, fileSourceStr.length()-1);
		    		
		    		fileVO.setSource(fileSourceStr);
		    		
		    		
					fileVO.setCandidateName(candidateNames.get(file.getFileId()) != null?candidateNames.get(file.getFileId()):"");
					
					fileVO.setLocationName(reportService.getLocationDetails1(file.getFileId(),null));
					result.add(fileVO);
					
			}
				 
				
			}
		  catch (Exception e) {
			  log.error("Exception Occured in setDataForAllLocations method in NewsMonitoringService", e);
		  }
		return result;
	  }
	  
	  public String getRegionLvl(int id){
		  switch (id) {
           
          case 2:  return IConstants.STATE;          
          case 3:  return IConstants.DISTRICT;
          case 4:  return IConstants.CONSTITUENCY;          
          case 5:  return "MANDAL";
          case 6:  return "VILLAGE";          
          case 7:  return "MUNICIPAL-CORP-GMC";
          case 8:  return "WARD";          
          case 9:  return "BOOTH";
          default: return "";
		  }
	  }
	  
	  public Map<Long,String> getCandidateNames(Set<Long> fileIds){
		  Map<Long,String> candidateNames = new HashMap<Long,String>();
		 try{
		  if(fileIds != null && fileIds.size() > 0){
			
			List<Object[]> candidates = candidatePartyFileDAO.getCandidateNamesByFileIds(fileIds);
		      if(candidates != null && candidates.size() > 0){
		    	 for(Object[] person:candidates){
		    		 if(candidateNames.get((Long)person[0]) != null){
		    			 candidateNames.put((Long)person[0], candidateNames.get((Long)person[0])+", "+person[1].toString());
		    		 }else{
		    			 candidateNames.put((Long)person[0],person[1].toString());
		    		 }
		    	 }  
		      } 
		  }
		 }catch(Exception e){
			 log.error("Exception Occured in getCandidateNames method in NewsMonitoringService", e);
		 }
		  return candidateNames;
	  }
	  public ResultStatus saveNewsReport(final List<Long> fileIds,final Long userId,final String decription)
	  {
		  final ResultStatus resultStatus = new ResultStatus();
		  try{
			 
			  transactionTemplate.execute(new TransactionCallback() {
			  public Object doInTransaction(TransactionStatus status) {
		     DateUtilService currentDate = new DateUtilService();
		    NewsReport newsReport = new NewsReport();
			newsReport.setDescription(decription);
			newsReport.setUser(userDAO.get(userId));
			newsReport.setCreatedDate(currentDate.getCurrentDateAndTime());
			newsReport = newsReportDAO.save(newsReport);
			ReportFiles reportFiles = new ReportFiles();
			for(Long ID : fileIds)
			{
			reportFiles.setFile(fileDAO.get(ID));
			reportFiles.setNewsReport(newsReport);
			reportFilesDAO.save(reportFiles);
			
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
			  }
			  });
		
		  }	 
		catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		    e.printStackTrace();
		}
		
		return resultStatus;
	  }
	  /** This method is used to get unmapped keywords(from gallaryKeyword)**/
	  public List<SelectOptionVO> getKeywords(Long userId,Boolean flag)
	  {
		List<SelectOptionVO> result =new ArrayList<SelectOptionVO>();
		List<Object[]> list = null;
		try{
			if(flag == false)
		    list = gallaryKeywordDAO.getUnAssignedKeyWords(userId);  
		    if(flag == true)
			list = gallaryKeywordDAO.getGallaryMapedKeyWords(userId); 
			if(list != null && list.size() > 0)
			for(Object[] params : list)
				result.add(new SelectOptionVO((Long)params[0],params[1].toString()));
		}
		catch (Exception e) {
		log.error("Exception Occured in getKeywords() method in NewsMonitoringService", e);
		}
		return result;
		
	  }
	  /** This method is used to Map Keyword to Gallary**/ 
	 public ResultStatus updateGallaryKeyword(List<Long> gallaryIds,List<Long> keywords,Long userId) 
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 GallaryKeyword gallaryKeyword = new GallaryKeyword();
		 DateUtilService date = new DateUtilService();
		 try{
			 if(keywords != null && keywords.size() > 0)
				gallaryKeywordDAO.DeleteKeyWords(keywords,userId);
			 if(gallaryIds != null && gallaryIds.size() > 0)
			 for(Long gallaryId : gallaryIds)
			 {
				 if(keywords != null && keywords.size() > 0)
				 for(Long keyword : keywords)
				 {
					gallaryKeyword.setGallary(gallaryDAO.get(gallaryId));
					gallaryKeyword.setKeyword(keywordDAO.get(keyword));
					gallaryKeyword.setCreatedDate(date.getCurrentDateAndTime());
					gallaryKeyword.setUpdatedDate(date.getCurrentDateAndTime());
					gallaryKeyword.setCreatedBy(userId);
					gallaryKeywordDAO.save(gallaryKeyword);
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS); 
				 }
				 
			 }
			 
			List<Long> fileIds =fileKeywordDAO.getFilesForEachKeyWord(keywords);
			UpdateDefaultGallariesInFileGallary(fileIds,gallaryIds);
			
		 }
		 catch (Exception e) {
			 resultStatus.setResultCode(ResultCodeMapper.FAILURE); 
			 log.error("Exception Occured in updateGallaryKeyword() method", e);
		}
		return resultStatus;
	 }
	  /** This method is used to delete default gallries and update files and gallaries in fileGallary**/
	public ResultStatus UpdateDefaultGallariesInFileGallary(List<Long> fileIds,List<Long> gallaryIds)
	{
		ResultStatus resultStatus = new ResultStatus();
		DateUtilService date = new DateUtilService();
		try{
			FileGallary fileGallary = new FileGallary();
			if(fileIds != null && fileIds.size() > 0)
			fileGallaryDAO.deleteDefaultGallaries(fileIds);
			if(gallaryIds != null && gallaryIds.size() > 0)
			{
			for(Long gallaryId : gallaryIds)
			{
				for(Long fileId :fileIds)
				{
					Long fileGalId = fileGallaryDAO.checkFileGallaryExist(gallaryId,fileId);
					if(fileGalId == null)
					{
					fileGallary.setGallary(gallaryDAO.get(gallaryId));
					fileGallary.setFile(fileDAO.get(fileId));
					fileGallary.setIsPrivate(IConstants.FALSE);
					fileGallary.setIsDelete(IConstants.FALSE);
					fileGallary.setCreatedDate(date.getCurrentDateAndTime());
					fileGallary.setUpdateddate(date.getCurrentDateAndTime());
					fileGallaryDAO.save(fileGallary);
					}
				}
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}
		}
		catch (Exception e) {
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		log.error("Exception Occured in UpdateDefaultGallariesInFileGallary() method", e);
		
		}
		return resultStatus;
	}
	/* this method is used to update gallaries for a keyword */
	public ResultStatus updateExistingGallaryKeyword(List<Long> checkedgallaryIds,List<Long> uncheckedgallaryIds,Long keyword,Long userId)
	{
		ResultStatus resultStatus = new ResultStatus();
		DateUtilService date = new DateUtilService();
		try{
			if(uncheckedgallaryIds != null && uncheckedgallaryIds.size() > 0)
			gallaryKeywordDAO.deleteGallaries(keyword,userId,uncheckedgallaryIds);
			for(Long gallaryId : checkedgallaryIds)
			{
				Long gallaryKeywordId = gallaryKeywordDAO.getGallaryKeywordId(keyword, gallaryId);
			
				if(gallaryKeywordId == null)
				{
					GallaryKeyword gallaryKeyword = new GallaryKeyword();
					gallaryKeyword.setKeyword(keywordDAO.get(keyword));
					gallaryKeyword.setGallary(gallaryDAO.get(gallaryId));
					gallaryKeyword.setCreatedDate(date.getCurrentDateAndTime());
					gallaryKeyword.setUpdatedDate(date.getCurrentDateAndTime());
					gallaryKeyword.setCreatedBy(userId);
					gallaryKeywordDAO.save(gallaryKeyword);
				}
				
			}
	
		}
		catch(Exception e)
		{
			log.error("Exception Occured in updateExistingGallaryKeyword() method", e);
		}
		return resultStatus;
	}
	public List<Long> getGallaryId(Long userId,Long keyword)
	{
		List<Long> gallaryIds = new ArrayList<Long>();
		try{
			gallaryIds = gallaryKeywordDAO.getGallaryMapedKeyWords(userId,keyword);
		}
		catch (Exception e) {
			log.error("Exception Occured in getGallaryId() method", e);
		
		}
		return gallaryIds;
	}
	
	public FileVO getNewsReports(Long userId,Integer startIndex,Integer maxIndex)
	{
		
		FileVO resultFileVO = new FileVO();
		
		try{
			List<Object[]> list = newsReportDAO.getNewsReports(userId,startIndex,maxIndex);
			if(list != null && list.size() > 0)
			{
				List<FileVO> resultList = new ArrayList<FileVO>();
				for(Object[] params : list)
				{
					FileVO fileVO = new FileVO();
					fileVO.setNewsImportanceId((Long)params[0]);
					fileVO.setDescription(params[1].toString());
					String dateObj = params[2].toString().substring(8,10)+'-'+params[2].toString().substring(5,7)+'-'+params[2].toString().substring(0,4);
		    		fileVO.setIdentifiedDateOn(dateObj!=null?dateObj:"");
					resultList.add(fileVO);
				}
				resultFileVO.setFileVOList(resultList);
				resultFileVO.setCount(newsReportDAO.getNewsReports(userId,null,null).size());
			}
		}
		catch(Exception e)
		{
			log.error("Exception Occured in getNewsReports() method", e);
			
		}
		return resultFileVO;
	}
	
	public List<SelectOptionVO> getMainCategories()
	{
		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
		try{
			List<Object[]> list =mainCategoryDAO.getCategories();
			if(list != null && list.size() > 0)
				for(Object[] params : list)
			resultList.add(new SelectOptionVO((Long) params[0],params[1].toString()));
		}
		catch (Exception e) {
			log.error("Exception Occured in getMainCategories() method", e);
			
		}
		return resultList;
	}
	
	public List<SelectOptionVO> getCandidatesByPartyIdsList(List<Long> partyIdsList)
	{
	  try{
		  List<SelectOptionVO> selectOptionVOsList = new ArrayList<SelectOptionVO>(0);
		  
		  List<Object[]> nominationCandidates = nominationDAO.getCandidatesListByPartyIdsList(partyIdsList);
		  List<Object[]> candidatePartyCandidates = candidatePartyDAO.getCandidatesListByPartyIdsList(partyIdsList);
		  
		  if(nominationCandidates != null && nominationCandidates.size() > 0)
		   for(Object[] params:nominationCandidates)
		   {
			 SelectOptionVO optionVO = new SelectOptionVO();
			 optionVO.setId((Long)params[0]);
			 if(params[1] != null && params[1].toString().substring(0, 1).equalsIgnoreCase("."))
			  optionVO.setName(params[1].toString());
			 else
			  optionVO.setName(params[1] != null?params[1].toString():" ");
			 selectOptionVOsList.add(optionVO);
			 
		   }
		   
		  if(candidatePartyCandidates != null && candidatePartyCandidates.size() > 0)
		   for(Object[] params: candidatePartyCandidates)
			  selectOptionVOsList.add(new SelectOptionVO((Long)params[0],params[1] != null?params[1].toString():"")); 
		  
		  return selectOptionVOsList;
	  }catch (Exception e) {
		log.error("Exception Occured in getCandidatesByPartyIdsList() method, Exception - ",e);
		return null;
	  }
	}
	
	public ResultStatus saveCandidatesAndParty(final Long partyId,final String candidateName,final Long designationId,final Long loctionId,final Long locationValue,final String isDebate)
	{
		final ResultStatus resultStatus = new ResultStatus();
	  try{
		  
		  List<Long> candidateIdsList = candidateDAO.getCandidateIdByPartyIdAndCandidateName(partyId, candidateName,designationId);
		  if(candidateIdsList != null && candidateIdsList.size() > 0)
		  {
			  resultStatus.setMessage("Candidate is already exist.");
			  return resultStatus;
		  }
		  transactionTemplate.execute(new TransactionCallback() {
			  public Object doInTransaction(TransactionStatus status) {
		  Candidate candidate = new Candidate();
		  candidate.setParty(partyDAO.get(partyId));
		  candidate.setLastname(candidateName);
		  candidate.setDesignation(designationDAO.get(designationId));
		  if(loctionId == 1)
		  {
			 Object[] stateAndDisrictValues = constituencyDAO.getStateAndDistrictDetails(locationValue).get(0);
			 Long pcId = delimitationConstituencyAssemblyDetailsDAO.getAllAssemblyConstituencyByParlimentId(locationValue).get(0);
			 if(stateAndDisrictValues != null)
			 {
				 candidate.setState(stateDAO.get((Long)stateAndDisrictValues[0])) ;
				 candidate.setDistrict(districtDAO.get((Long)stateAndDisrictValues[1]));
				 candidate.setAssembly(constituencyDAO.get(locationValue));
			 }
			 if(pcId > 0)
			 {
				 candidate.setParliament(constituencyDAO.get(pcId)); 
			 }
		  }
		  else if(loctionId == 2)
		  {
			  Long stateId = constituencyDAO.getPcConstituency(locationValue).get(0);
			  if(stateId > 0)
			  {
				  candidate.setState(stateDAO.get(stateId));
				  candidate.setParliament(constituencyDAO.get(locationValue)); 
			  }
		  }
		  else if(loctionId == 3)
		  {
			  candidate.setState(stateDAO.get(1l));
		  }
		  else 
		  {
			 candidate.setState(stateDAO.get(1l));
		  }
		  if(isDebate != null){
			  candidate.setIsDebateCandidate("Y");
		  }
		  else
			  candidate.setIsDebateCandidate("N");
		  
		  candidate=candidateDAO.save(candidate);
		  if(candidate != null){
			  resultStatus.setId(candidate.getCandidateId());
		  }
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  resultStatus.setMessage(" "+candidateName+" Created Successfully...");
		  return resultStatus;
			  }
			  
		  });
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	  }catch (Exception e) {
		
		log.error(" Exception Occured in saveCandidatesAndParty() method, Exception - ",e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		  return resultStatus;
	  }
	  return resultStatus;
	}
	
    public String generateUrlForNewsReport(Long reportId,Long userId,String path){
    	String url = "invalid";
    	try{
    		Long count = newsReportDAO.checkValidUserForReport(userId, reportId);
	    	if(count > 0){
	    		String key = UUID.randomUUID().toString();
	    		newsReportDAO.updateNewKey(key,reportId);
	    		url = path+"key="+key;
	    	}
    	}catch(Exception e){
    		log.error(" Exception Occured in generateUrlForNewsReport method, Exception - ",e);
    		url = "exception";
    	}
    	return url;
    }
    
    public ResultStatus deleteNews(Long fileId,Long userId)
    {
    	Long count = fileDAO.checkFileBelongsToUser(userId,fileId);
    	ResultStatus resultStatus = new ResultStatus();
    	try{
    		List<String> userType = userDAO.getUserType(userId);
    		if(userType.get(0).equalsIgnoreCase("Admin"))
    		{
    		fileDAO.deleteFile(fileId);
    		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
    		}
    		else if( userType.get(0).equalsIgnoreCase("SubUser") && count>0)
    		{
    		fileDAO.deleteFile(fileId);
    		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
    		}
    		else
    			resultStatus.setResultCode(ResultCodeMapper.FAILURE);	
    		
    	}
    	catch (Exception e) {
    	log.error(" Exception Occured in deleteNews method, Exception - ",e);
		
		}
		return resultStatus;
    }
    
    public NewsEditVO getInfoForFile(String userType,Long fileId,Long userId)
    {
    	NewsEditVO result = new NewsEditVO();
    	try{
    		File file = fileDAO.get(fileId);
    		if(!"Admin".equalsIgnoreCase(userType)){
    		  if(!(file.getUser().getUserId().longValue() == userId.longValue())){
    			  return null;
    		  }
    		}
    		if(file != null){
	    		result.setFileTitle(StringEscapeUtils.unescapeJava(file.getFileTitle()));
	    		result.setFileId(fileId);
	    		if(file.getFileDate() != null){
	    			Date fileDate = file.getFileDate();
	    			Calendar cal = Calendar.getInstance();
	    			cal.setTime(fileDate);
	    			result.setDay(cal.get(Calendar.DAY_OF_MONTH));
	    			result.setMonth(cal.get(Calendar.MONTH));
	    			result.setYear(cal.get(Calendar.YEAR));
	    		}
	    		if(file.getNewsImportance() != null){
        		   result.setNewsimportance(file.getNewsImportance().getNewsImportanceId());
	    		}
	    		if(file.getDescFont() != null)
	    			result.setFileDescriptionCheckBox("on");
	    		if(file.getFileDescription() != null)
	    			result.setFileDescription(StringEscapeUtils.unescapeJava(file.getFileDescription()));
	    		
	    		if(file.getSynopsysDescription() != null)
	    			result.setSynopsysDescription(StringEscapeUtils.unescapeJava(file.getSynopsysDescription()));
	    		if(file.getSynopsysFont() != null)
	    			result.setSynopsysCheckBox("on");
	    		
        		result.setDefaultImg(file.getFilePath());
        		if(file.getFont() != null){
        			result.setTitleCheckBox("on");
        		}
        		
        		List<CandidatePartyDestinationVO> variousUserDetials = getWhoWhomeDetails(fileId,userId);
        		List<FileSourceVO> newsInfo = getNewsFromDiffSources(fileId);
        		List<SelectOptionVO> newsLocation = getFileScope(fileId);
        		List<SelectOptionVO> keywordsList = getTotalKeyWords();
        		
        		result.setKeywordsList(keywordsList);
        		result.setFileSourceVOList(newsInfo);
        		result.setNewsEdition(getNewsEditionDetails());
        		result.setSelectOptionVOList(newsLocation);
        		result.setSourceVOList(variousUserDetials);        		
        		
    		}
    		
    		List<SelectOptionVO> partyList = candidateDetailsService.getPartiesList(); 
    		if(partyList != null){
    			partyList.add(0, new SelectOptionVO(0l,"Select Party"));
    		}
    		List<FileVO> locationScopes = candidateDetailsService.getScopesForNewSearch();
    		List<SelectOptionVO> benefitsOptionList = candidateDetailsService.getBenefitList();
    			//result = getDataForPrePopulate(file,userId);
    		
    		result.setPartyList(partyList);
    		result.setFileVOLIst(locationScopes);
    		result.setBenefitsOptionList(benefitsOptionList);
    	}
    	catch (Exception e) {
    		log.error(" Exception Occured in getInfoForFile method, Exception - ",e);
		}
		return result;
    	
    }
    public List<SelectOptionVO> getNewsEditionDetails(){
    	List<SelectOptionVO> newsEditionList = new ArrayList<SelectOptionVO>();
    	newsEditionList.add(new SelectOptionVO(1l,"Main Edition"));
    	newsEditionList.add(new SelectOptionVO(2l,"District/Sub Edition"));
      return newsEditionList;
    }
   public List<SelectOptionVO> getTotalKeyWords(){
	   log.error("entered into getTotalKeyWords() in NewsMonitoringSevice class.");
	   List<SelectOptionVO> keywordsList = new ArrayList<SelectOptionVO>();
	   try {
		   
		   List<Object[]> keywords = keywordDAO.getTotalKeyWords();
		   if(keywords.size() > 0){
			  for (Object[] param : keywords) {
				  SelectOptionVO vo = new SelectOptionVO();
				   vo.setId(Long.valueOf(param[0].toString()));
				   vo.setName(param[1].toString());
				  keywordsList.add(vo);
			}
		   }
		
	} catch (Exception e) {
		keywordsList = null;
		log.error(" Exception Occured in getTotalKeyWords method in NewsMonitoringSevice class, Exception - ",e);
	}
	   return keywordsList;
	   
   }
    public List<CandidatePartyDestinationVO> getWhoWhomeDetails(Long fileId,Long userId){
    	
    	log.info("entered into getWhoWhomeDetails() in NewsMonitoringSevice class.");
    	Set<Long> whoCandidateSet = new HashSet<Long>();
		Set<Long> whomeCandidateSet = new HashSet<Long>();
		Set<Long> whoSet = new HashSet<Long>();
		Set<Long> whomeSet = new HashSet<Long>();
		List<CandidatePartyDestinationVO> candidatePartyDestinationVOList = new ArrayList<CandidatePartyDestinationVO>();
		CandidatePartyDestinationVO candidatePartyDestinationVO = new CandidatePartyDestinationVO();
		List<CandidatePartyDestinationVO> sourceVOList = new ArrayList<CandidatePartyDestinationVO>();
		List<CandidatePartyDestinationVO> destinationVOList = new ArrayList<CandidatePartyDestinationVO>();
		List<SelectOptionVO> candidatesList = new ArrayList<SelectOptionVO>();
		CandidatePartyDestinationVO candidate = null;
		List<SelectOptionVO> categoriesList = partyDetailsService.getPartyGallarySelectList(null, null);
		try {
			
		List<CandidatePartyFile> candidatePartyFileList =  candidatePartyFileDAO.getInvolvedCandidatesInANews(fileId);
		
		if(candidatePartyFileList != null && candidatePartyFileList.size() > 0){
			for(CandidatePartyFile cpf :candidatePartyFileList){
				
				
        		List<String> candidatePartyKeywords = getKeywordsForACandidate(cpf.getCandidatePartyFileId());
        		
				if(cpf.getSourceCandidate() != null){
						if(!whoCandidateSet.contains(cpf.getSourceCandidate().getCandidateId())){
							whoCandidateSet.add(cpf.getSourceCandidate().getCandidateId());
							candidate = new CandidatePartyDestinationVO();
							candidate.setCandidatePartyFileId(cpf.getCandidatePartyFileId());
							candidate.setPartyId(cpf.getSourceParty().getPartyId());
							if(cpf.getSourceCandidate() != null){
								candidate.setCandidateId(cpf.getSourceCandidate().getCandidateId());
							  candidatesList= getCandidateListByPartyId(userId,cpf.getSourceCandidate().getCandidateId(),cpf.getSourceParty().getPartyId());
							}else{
							  candidatesList= getCandidateListByPartyId(userId,null,cpf.getSourceParty().getPartyId());
							}
							candidate.setCanidateList(candidatesList);
							
							candidate.setBenefitId(cpf.getSourceBenefit().getBenefitId());
							sourceVOList.add(candidate);
						}
					}else if(cpf.getSourceParty() != null){
						if(!whoSet.contains(cpf.getSourceParty().getPartyId())){
							whoSet.add(cpf.getSourceParty().getPartyId());
							candidate = new CandidatePartyDestinationVO();
							candidate.setCandidatePartyFileId(cpf.getCandidatePartyFileId());
							candidate.setPartyId(cpf.getSourceParty().getPartyId());
							if(cpf.getSourceCandidate() != null){
								candidatesList= getCandidateListByPartyId(userId,cpf.getSourceCandidate().getCandidateId(),cpf.getSourceParty().getPartyId());
								}else{
									candidatesList= getCandidateListByPartyId(userId,null,cpf.getSourceParty().getPartyId());
								}
							candidate.setCanidateList(candidatesList);		
							
							candidate.setBenefitId(cpf.getSourceBenefit().getBenefitId());
							sourceVOList.add(candidate);
						}
					}
				
					if(cpf.getDestinationCandidate() != null){
						if(!whomeCandidateSet.contains(cpf.getDestinationCandidate().getCandidateId())){
							whomeCandidateSet.add(cpf.getDestinationCandidate().getCandidateId());
							candidate = new CandidatePartyDestinationVO();
							candidate.setCandidatePartyFileId(cpf.getCandidatePartyFileId());
							candidate.setPartyId(cpf.getDestinationParty().getPartyId());
							if(cpf.getDestinationCandidate() != null){
								candidate.setCandidateId(cpf.getDestinationCandidate().getCandidateId());
							     candidatesList= getCandidateListByPartyId(userId,cpf.getDestinationCandidate().getCandidateId(),cpf.getDestinationParty().getPartyId());
							}else{
								candidatesList= getCandidateListByPartyId(userId,null,cpf.getDestinationParty().getPartyId());
							}
							candidate.setCanidateList(candidatesList);
							
							
							candidate.setBenefitId(cpf.getDestinationBenefit().getBenefitId());
							List<Long> gallaryIds = getCategoryForACandidate(cpf.getCandidatePartyFileId());
							String  categories = "";
							for(Long gallaryId:gallaryIds){
								categories = categories+","+gallaryId;
							}
							if(categories.length() > 0){
								categories = categories.substring(1);
							}
							candidate.setCategoryIds(gallaryIds);
							candidate.setCategoryIdsStr(categories);
							candidate.setCategoriesList(categoriesList);
							candidate.setKeywords(candidatePartyKeywords);
							List<SelectOptionVO> keywordNameList = new ArrayList<SelectOptionVO>();
							
							if(candidatePartyKeywords !=null && candidatePartyKeywords.size()>0){
								for (String keyid : candidatePartyKeywords) {
										
										SelectOptionVO vo = new SelectOptionVO();
										vo.setId(keywordDAO.get(Long.valueOf(keyid.toString())).getKeywordId());
										vo.setName(keywordDAO.get(Long.valueOf(keyid.toString())).getType().toString());
										keywordNameList.add(vo);
								}								
							}
							candidate.setFileKeywordList(keywordNameList);
							
							destinationVOList.add(candidate);
						}
					}
					else if(cpf.getDestinationParty() != null){
						if(!whomeSet.contains(cpf.getDestinationParty().getPartyId())){
								whomeSet.add(cpf.getDestinationParty().getPartyId());
								candidate = new CandidatePartyDestinationVO();
								candidate.setCandidatePartyFileId(cpf.getCandidatePartyFileId());
								candidate.setPartyId(cpf.getDestinationParty().getPartyId());
								if(cpf.getDestinationCandidate() != null){
								   candidatesList= getCandidateListByPartyId(userId,cpf.getDestinationCandidate().getCandidateId(),cpf.getDestinationParty().getPartyId());
								}else{
									candidatesList= getCandidateListByPartyId(userId,null,cpf.getDestinationParty().getPartyId());
								}
								candidate.setCanidateList(candidatesList);
								List<Long> gallaryIds = getCategoryForACandidate(cpf.getCandidatePartyFileId());
								String  categories = "";
								for(Long gallaryId:gallaryIds){
									categories = categories+","+gallaryId;
								}
								if(categories.length() > 0){
									categories = categories.substring(1);
								}
								candidate.setCategoryIds(gallaryIds);
								candidate.setCategoryIdsStr(categories);
								candidate.setCategoriesList(categoriesList);
								candidate.setKeywords(candidatePartyKeywords);
								List<SelectOptionVO> keywordNameList = new ArrayList<SelectOptionVO>();
								if(candidatePartyKeywords !=null && candidatePartyKeywords.size()>0){
									for (String keyid : candidatePartyKeywords) {
										SelectOptionVO vo = new SelectOptionVO();
										vo.setId(keywordDAO.get(Long.valueOf(keyid.toString())).getKeywordId());
										vo.setName(keywordDAO.get(Long.valueOf(keyid.toString())).getType().toString());
										keywordNameList.add(vo);
									}									
								}
								candidate.setFileKeywordList(keywordNameList);
								
								candidate.setBenefitId(cpf.getDestinationBenefit().getBenefitId());
								destinationVOList.add(candidate);
						}
					}else{
						candidate = new CandidatePartyDestinationVO();
						List<Long> gallaryIds = getCategoryForACandidate(cpf.getCandidatePartyFileId());
						String  categories = "";
						for(Long gallaryId:gallaryIds){
							categories = categories+","+gallaryId;
						}
						if(categories.length() > 0){
							categories = categories.substring(1);
						}
						candidate.setCategoryIds(gallaryIds);
						candidate.setCategoryIdsStr(categories);
						candidate.setCategoriesList(categoriesList);
						candidate.setKeywords(candidatePartyKeywords);
						candidate.setCandidateId(0l);
						List<SelectOptionVO> emptyCandidatesList = new ArrayList<SelectOptionVO>();
						emptyCandidatesList.add(0,new SelectOptionVO(0l,"Select Candidate"));
						candidate.setCanidateList(emptyCandidatesList);
						List<SelectOptionVO> keywordNameList = new ArrayList<SelectOptionVO>();
						if(candidatePartyKeywords !=null && candidatePartyKeywords.size()>0){
							for (String keyid : candidatePartyKeywords) {
								SelectOptionVO vo = new SelectOptionVO();
								vo.setId(keywordDAO.get(Long.valueOf(keyid.toString())).getKeywordId());
								vo.setName(keywordDAO.get(Long.valueOf(keyid.toString())).getType().toString());
								keywordNameList.add(vo);
							}									
						}
						candidate.setFileKeywordList(keywordNameList);
						if(keywordNameList.size() > 0 || categories.length() > 0)
						destinationVOList.add(candidate);
					}
					
					
			}
			if(sourceVOList.size() == 0 || destinationVOList.size() == 0){
				 
				  candidate = new CandidatePartyDestinationVO();
				  candidatesList.add(new SelectOptionVO(0l,"Select Candidate"));
				  candidate.setCanidateList(candidatesList);
				  candidate.setCategoryIds(new ArrayList<Long>());
				  candidate.setCategoryIdsStr("");
				  candidate.setCategoriesList(categoriesList);
				  candidate.setKeywords(new ArrayList<String>());
				  if(sourceVOList.size() == 0){
					  sourceVOList.add(candidate);
				  }
				  if(destinationVOList.size() == 0){
					  destinationVOList.add(candidate);
				  }
			}
			candidatePartyDestinationVO.setSourceVOList(sourceVOList);
			candidatePartyDestinationVO.setDestinationVOList(destinationVOList);
			candidatePartyDestinationVOList.add(candidatePartyDestinationVO);
		}else{
				  candidate = new CandidatePartyDestinationVO();
				  candidatesList.add(new SelectOptionVO(0l,"Select Candidate"));
				  candidate.setCanidateList(candidatesList);
				  candidate.setCategoryIds(new ArrayList<Long>());
				  candidate.setCategoryIdsStr("");
				  candidate.setCategoriesList(categoriesList);
				  candidate.setKeywords(new ArrayList<String>());
				  sourceVOList.add(candidate);
				  destinationVOList.add(candidate);
				  candidatePartyDestinationVO.setSourceVOList(sourceVOList);
				  candidatePartyDestinationVO.setDestinationVOList(destinationVOList);
				  candidatePartyDestinationVOList.add(candidatePartyDestinationVO);
		}
		} catch (Exception e) {
			log.error("Exception occured in getWhoWhomeDetails() in NewsMonitoringSevice class.",e);
			candidatePartyDestinationVOList = null;
		}
		
		return candidatePartyDestinationVOList;
    }
    
    public List<SelectOptionVO> getCandidateListByPartyId(Long userId,Long candidateId,Long partyId){
    	List<SelectOptionVO> candidatesList = null;
    	
    	try {
    		candidatesList = candidateDetailsService.getCandidatesByPartyIdFromCandidateTable(userId,partyId);
    		if(candidatesList == null){
    		   candidatesList = new ArrayList<SelectOptionVO>();
    		}
    		
			List<Long> canidateIds =  new ArrayList<Long>();
			if(candidatesList!= null && candidatesList.size() >0){
				for (SelectOptionVO candidateData : candidatesList) {
						canidateIds.add(candidateData.getId());
				}
				if(candidateId != null && !canidateIds.contains(candidateId)){									 
					 SelectOptionVO vo = new SelectOptionVO();
					 
					 vo.setId(candidateId);
					 vo.setName(candidateDAO.getCandidateName(candidateId));
					 candidatesList.add(vo);
				}			
			}
			
			candidatesList.add(0,new SelectOptionVO(0l,"Select Candidate"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return candidatesList;
    }
    
    public List<Long> getCategoryForACandidate(Long candidatePartyFileId){
    	log.error("entered into getCategoryForACandidate() in NewsMonitoringSevice class.");
    	List<Long> gallaryIds = new ArrayList<Long>();
    	
    	try{
    		List<Object[]> CandidatePartyFile = candidatePartyCategoryDAO.getCandidatePartyCategoryDetialsByFileId(candidatePartyFileId);
    		if(CandidatePartyFile != null && CandidatePartyFile.size() >0)
    			for (Object[] param : CandidatePartyFile) {
    				gallaryIds.add(Long.valueOf(param[1].toString()));
				} 
    		
    	}catch (Exception e) {
			log.error("Exception occured in getCategoryForACandidate() in NewsMonitoringSevice class.",e);
		}    		
    	return gallaryIds;
    }
    
    public List<String> getKeywordsForACandidate(Long candidatePartyFileId){
    	log.error("entered into getKeywordsForACandidate() in NewsMonitoringSevice class.");
        List<String> keywords = new ArrayList<String>();    	
    	try{
    		List<Object[]>  candidatePartyKeywordsList = candidatePartyKeywordDAO.getCandidatePartyKeywordsByFileIds(candidatePartyFileId);
    		
    		if(candidatePartyKeywordsList != null && candidatePartyKeywordsList.size() >0)
    			for (Object[] param : candidatePartyKeywordsList) {
    				keywords.add(param[1].toString());
    			}  
    	}catch (Exception e) {
			log.error("Exception occured in getKeywordsForACandidate() in NewsMonitoringSevice class.",e);
			keywords = null;
		}    		
    	return keywords;    	
    	
    }
    
    public List<SelectOptionVO> getFileScope(Long fileId){
    	log.debug("entered into getFileScope() of NewsMonitoringService class.");
    	List<SelectOptionVO> newsAreaDetails = null;
    	try {
			//File file = fileDAO.get(fileId);
			
			List<Object[]> fileaddresList = userAddressDAO.getfileAddressListByFileId(fileId);
			
			if(fileaddresList != null && fileaddresList.size()>0){
				newsAreaDetails = new ArrayList<SelectOptionVO>();
				for (Object[] param : fileaddresList) {
					Long scopeId = Long.valueOf(param[0].toString());
					Long scopeValue = Long.valueOf(param[1].toString());
					Long constituencyId = (Long)param[2];
					String locationName =null;
					String locationId = null;
					String assLocElecBdyId =null;
					Long assConstituencyId = null;
					if(param[0] != null){
					 assConstituencyId = Long.valueOf(param[2] != null ? param[2].toString():"0");
					}
					switch (scopeId.intValue()) {
					case 2:
							State state = stateDAO.get(scopeValue);
							locationName = state.getStateName();
							locationId = state.getStateId().toString();
						break;

					case 3:
							District district = districtDAO.get(scopeValue);
							locationName = district.getDistrictName();
							locationId = district.getDistrictId().toString();
						break;
					case 4:
							Constituency constituency = constituencyDAO.get(scopeValue);
							locationName = constituency.getName();
							locationId = constituency.getConstituencyId().toString();
						break;
					case 5:
							Tehsil tehsil = tehsilDAO.get(scopeValue);
							locationName = tehsil.getTehsilName();
							locationId =  "2"+tehsil.getTehsilId().toString();
						break;
					case 6:
							Hamlet hamlet = hamletDAO.get(scopeValue);
							locationName = hamlet.getHamletName();
							locationId = "2"+hamlet.getHamletId().toString();
						break;
					case 7:
						LocalElectionBody localElectionBody = localElectionBodyDAO.get(scopeValue);					
						locationName = localElectionBody.getName()+" "+localElectionBody.getElectionType().getElectionType();
						List<Object[]> muncipalityDetails = null;
						if(assConstituencyId == null){
						  muncipalityDetails = assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyDetails(localElectionBody.getLocalElectionBodyId());
						}else{
						  muncipalityDetails = assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyDetailsByConstiId(localElectionBody.getLocalElectionBodyId(),assConstituencyId);	
						}
						 if(muncipalityDetails.size() > 0){
								for (Object[] parms : muncipalityDetails) {
									locationId = "1"+parms[0].toString();
								}
							
							}
						break;
					case 8:
							Constituency ward = constituencyDAO.get(scopeValue);
							
							if(ward.getLocalElectionBody().getName() !=""){
								locationName = ward.getName();
								locationId = "1"+ward.getConstituencyId().toString();
							}
							else{
								locationName = ward.getName()+" ("+ward.getLocalElectionBody().getName().trim()+")";
								locationId = "1"+ward.getConstituencyId().toString();
							}
							if(assConstituencyId != null && locationId != null){
							   List<Object[]> munciDetails = assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyDetailsByConstiId(ward.getLocalElectionBody().getLocalElectionBodyId(),assConstituencyId);
							   if(munciDetails.size() > 0){
									for (Object[] parms : munciDetails) {
										assLocElecBdyId = "1"+parms[0].toString();
									}
								
								}
							}
						break;
					case 9:
							Booth booth = boothDAO.get(scopeValue);
							locationName = "Booth - "+booth.getPartNo()+" ("+booth.getLocation()+")";
							locationId = booth.getBoothId().toString();
						break;
					default:
						break;
					}
					
					if(locationName != null){						
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId(scopeId);
						selectOptionVO.setName(locationName);
						selectOptionVO.setLocation(locationId);	
						selectOptionVO.setValue(assLocElecBdyId);
						selectOptionVO.setConstituencyId(constituencyId);
						newsAreaDetails.add(selectOptionVO);
					}
				}
			}
		} catch (Exception e) {
			log.debug("entered into getFileScope() of NewsMonitoringService class.",e);
		}
    	
    	return newsAreaDetails;
    }
    public List<FileSourceVO> getNewsFromDiffSources(Long fileId){    	
    	log.debug("entered into getNewsFromDiffSources() of NewsMonitoringService class.");
    	List<FileSourceVO> fileDetailsInfo = new ArrayList<FileSourceVO>();
    	try{
    		//List<Object[]> fileSourveLanguageDetails = fileSourceLanguageDAO.getfileSourveLanguageDetailsByFileId(fileId);
    		List<Long> fileSourceLanguageIdList = fileSourceLanguageDAO.getFileSourceIdsBasedOnFile(fileId);
    		if(fileSourceLanguageIdList != null && fileSourceLanguageIdList.size() >0){
    			for (Long sourceLangId : fileSourceLanguageIdList) {
    				
    				FileSourceVO fileSourceVO1 = null;
    				List<FileSourceVO> newsfileDetails = new ArrayList<FileSourceVO>();
    				
    				List<Object[]> fileDetails = filePathsDAO.getFileDetailsByFileId(fileId,sourceLangId);
    	    		if(fileDetails !=null && fileDetails.size() > 0 && fileSourceLanguageIdList.size() >0){    			
    	    			for (Object[] params : fileDetails) {
    	    				
    	    				FileSourceVO fileSourceVO = new FileSourceVO();
    	    				fileSourceVO.setFilePathId(params[8]!= null?Long.valueOf(params[8].toString()):null);
    	    				fileSourceVO.setFileSourceLangId(sourceLangId);
    	    				fileSourceVO.setFileImageFileName(params[0]!= null?params[0].toString():"");
    	    				fileSourceVO.setNewsEdition(Integer.parseInt(params[1]!= null?params[1].toString():"0"));
    	    				fileSourceVO.setPageNo(Long.valueOf(params[2]!= null?params[2].toString():"0"));
    	    				fileSourceVO.setNewsLength(Long.valueOf((params[3]!= null?params[3].toString():"0")));
    	    				fileSourceVO.setFileSourceId(Long.valueOf(params[4]!= null?params[4].toString():"0"));
    	    					
    	    				List<SelectOptionVO> sourcesList = new ArrayList<SelectOptionVO>();
	    	    				if(fileSourceVO.getFileSourceId() != null){
	    	    						List<Source> sourceList = sourceDAO.getAll();
	    	    						if(sourceList != null && sourceList.size() >0){
	    	    							for (Source source : sourceList) {
	    	    								SelectOptionVO sourceVO = new SelectOptionVO();    	    								
	    	    								sourceVO.setId(source.getSourceId());
	    	    								sourceVO.setName(source.getSource());
	    	    								
	    	    								sourcesList.add(sourceVO);
											}    	    							
	    	    						}
	    	    				}
	    	    				
    	    				fileSourceVO.setSourceList(sourcesList);
    	    				fileSourceVO.setSourceLangId(Long.valueOf(params[5]!= null?params[5].toString():"0"));
    	    				
    	    				List<SelectOptionVO> languagesList = new ArrayList<SelectOptionVO>();
    	    					if(fileSourceVO.getSourceLangId() != null){
    	    						List<SourceLanguage> languageList = sourceLanguageDAO.getAll();
    	    						if(languageList != null && languageList.size() >0){
    	    							for (SourceLanguage language : languageList) {
    	    								SelectOptionVO languageVO = new SelectOptionVO();    	    								
        	    							languageVO.setId(language.getLanguageId());
        	    							languageVO.setName(language.getLanguage());
        	    							
    	    								languagesList.add(languageVO);
										}
    	    							
    	    						}
    	    					}
    	    					
    	    				fileSourceVO.setLanguageList(languagesList);
    	    				fileSourceVO.setNewsDescCheck(params[6]!= null?StringEscapeUtils.unescapeJava(params[6].toString()):"");
    	    				fileSourceVO.setNewsFont(Long.valueOf(params[7]!= null?params[7].toString():"0"));
    	    				
    	    				newsfileDetails.add(fileSourceVO);
    					}
    	    			
    	    			if(newsfileDetails != null && newsfileDetails.size() > 0){
    	    				fileSourceVO1 = new FileSourceVO();
    	    				fileSourceVO1.setFileSourceLangId(sourceLangId);
    	    				fileSourceVO1.setSourceFileList(newsfileDetails);
    	    			}
				}else{
    				FileSourceVO fileSourceVO = new FileSourceVO();
    				fileSourceVO.setFilePathId(null);
    				fileSourceVO.setFileSourceLangId(sourceLangId);
    				fileSourceVO.setFileImageFileName("");
    				FileSourceLanguage fileSourceLanguage = fileSourceLanguageDAO.get(sourceLangId);
    				fileSourceVO.setNewsEdition(2);
    				fileSourceVO.setPageNo(0l);
    				fileSourceVO.setNewsLength(0l);
    				fileSourceVO.setFileSourceId(fileSourceLanguage.getSource() != null?fileSourceLanguage.getSource().getSourceId():0l);
    					
    				List<SelectOptionVO> sourcesList = new ArrayList<SelectOptionVO>();
	    				if(fileSourceVO.getFileSourceId() != null){
	    						List<Source> sourceList = sourceDAO.getAll();
	    						if(sourceList != null && sourceList.size() >0){
	    							for (Source source : sourceList) {
	    								SelectOptionVO sourceVO = new SelectOptionVO();    	    								
	    								sourceVO.setId(source.getSourceId());
	    								sourceVO.setName(source.getSource());
	    								
	    								sourcesList.add(sourceVO);
									}    	    							
	    						}
	    				}
	    				
    				fileSourceVO.setSourceList(sourcesList);
    				fileSourceVO.setSourceLangId(fileSourceLanguage.getLanguage() != null?fileSourceLanguage.getLanguage().getLanguageId():0l);
    				
    				List<SelectOptionVO> languagesList = new ArrayList<SelectOptionVO>();
    					if(fileSourceVO.getSourceLangId() != null){
    						List<SourceLanguage> languageList = sourceLanguageDAO.getAll();
    						if(languageList != null && languageList.size() >0){
    							for (SourceLanguage language : languageList) {
    								SelectOptionVO languageVO = new SelectOptionVO();    	    								
	    							languageVO.setId(language.getLanguageId());
	    							languageVO.setName(language.getLanguage());
	    							
    								languagesList.add(languageVO);
								}
    							
    						}
    					}
    					
    				fileSourceVO.setLanguageList(languagesList);
    				fileSourceVO.setNewsDescCheck(fileSourceLanguage.getNewsDetailedDescription()!= null?StringEscapeUtils.unescapeJava(fileSourceLanguage.getNewsDetailedDescription()):"");
    				fileSourceVO.setNewsFont(fileSourceLanguage.getFont()!= null?fileSourceLanguage.getFont().getFontId():0l);
    				
    				newsfileDetails.add(fileSourceVO);
    				if(newsfileDetails != null && newsfileDetails.size() > 0){
	    				fileSourceVO1 = new FileSourceVO();
	    				fileSourceVO1.setFileSourceLangId(sourceLangId);
	    				fileSourceVO1.setSourceFileList(newsfileDetails);
	    			}
				}
    	    		if(fileSourceVO1 != null)
    	    			fileDetailsInfo.add(fileSourceVO1);
    		}
    		
    		}
    	}catch (Exception e) {
			log.error(" exception occure in getNewsFromDiffSources() of NewsMonitoringService class.",e);
		}
    	return fileDetailsInfo;
    }
    
	  public FileVO getDataForPrePopulate(List<File> list,Long userID)
	  {
		  FileVO result = new FileVO();
		  List<Long> fileIds = new ArrayList<Long>();
		  try
		  {
			  Set<Long> canfileIds = new HashSet<Long>();
			  for(File file : list){
				  canfileIds.add(file.getFileId());
			  }
			  Map<Long,String> candidateNames = getCandidateNames(canfileIds);
			  List<FileVO> resultList = new ArrayList<FileVO>();
				for(File file : list)
				{
					FileVO fileVO = new FileVO();
					fileVO.setContentId(file.getFileId());
					fileVO.setFileTitle1(StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle())));
					fileVO.setFileDescription1(StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription())));
					String fileDate = file.getFileDate().toString();
		    		String dateObj = fileDate.substring(8,10)+'-'+fileDate.substring(5,7)+'-'+fileDate.substring(0,4);
		    		fileVO.setFileDate(dateObj!=null?dateObj:"");
		    		fileVO.setNewsImportanceId(file.getNewsImportance().getNewsImportanceId());
		    		fileVO.setImportance(file.getNewsImportance().getImportance());
		    		if(file.getFont() != null)
		    		 fileVO.setEenaduTeluguFontStr("Eenadu Telugu");
		    		Set<FileSourceLanguage> set = file.getFileSourceLanguage();
		    		//source setting
		    		String fileSourceStr = "";
		    		if(set != null && set.size() > 0)
		    		 for(FileSourceLanguage sourceLanguage:set)
		    		  fileSourceStr +=sourceLanguage.getSource().getSource()+",";
		    		 if(fileSourceStr != null && fileSourceStr.length() > 0)
		    		 fileSourceStr = fileSourceStr.substring(0, fileSourceStr.length()-1);
		    	     fileVO.setSource(fileSourceStr);
		    		String sourceString = "";
		    		String language = "";
		    		List<FileVO> sourceList = new ArrayList<FileVO>();
					for(FileSourceLanguage source:set)
					{
						FileVO filesource = new FileVO();
						if(source.getSource() != null && (source.getSource().getSource() != null && !source.getSource().getSource().equals("")))
						{
						filesource.setSource(source.getSource().getSource());
						}
						if(source.getLanguage() != null && (source.getLanguage().getLanguage() != null && !source.getLanguage().getLanguage().equals("")))
						{
						filesource.setLanguage(source.getLanguage().getLanguage());
						}
						sourceList.add(filesource);
					}
					if(file.getFont() != null){
						fileVO.setEenadu(true);
					}
					//fileVO.setSource(sourceString);
					//fileVO.setLanguage(language);
					fileVO.setFileVOList(sourceList);
					fileVO.setCandidateName(candidateNames.get(file.getFileId()) != null?candidateNames.get(file.getFileId()):"");
					fileVO.setLocationScopeValue(getRegionLvl(file.getRegionScopes().getRegionScopesId().intValue()));
					fileVO.setLocationName(candidateDetailsService.getLocationDetails(file.getRegionScopes().getRegionScopesId(),file.getLocationValue()));
					fileVO.setLocationId(file.getLocationValue());
					resultList.add(fileVO);
				}
					result.setFileVOList(resultList);
			
		}
		  catch (Exception e) {
			  log.error("Exception Occured in getDataForPrePopulate method in NewsMonitoringService", e);
		  }
		return result;
	  }
		public ResultStatus updateCandidatesAndParty(Long candidateId,Long partyId,String candidateName,Long designationId,Long loctionId,Long locationValue)
		{
			ResultStatus resultStatus = new ResultStatus();
		  try{
			  Candidate candidate =  candidateDAO.get(candidateId);
			  candidate.setParty(partyDAO.get(partyId));
			  candidate.setLastname(candidateName);
			  candidate.setDesignation(designationDAO.get(designationId));
			  if(loctionId == 1)
			  {
				 Object[] stateAndDisrictValues = constituencyDAO.getStateAndDistrictDetails(locationValue).get(0);
				 Long pcId = delimitationConstituencyAssemblyDetailsDAO.getAllAssemblyConstituencyByParlimentId(locationValue).get(0);
				 if(stateAndDisrictValues != null)
				 {
					 candidate.setState(stateDAO.get((Long)stateAndDisrictValues[0])) ;
					 candidate.setDistrict(districtDAO.get((Long)stateAndDisrictValues[1]));
					 candidate.setAssembly(constituencyDAO.get(locationValue));
				 }
				 if(pcId > 0)
				 {
					 candidate.setParliament(constituencyDAO.get(pcId)); 
				 }
			  }
			  else if(loctionId == 2)
			  {
				  Long stateId = constituencyDAO.getPcConstituency(locationValue).get(0);
				  if(stateId > 0)
				  {
					  candidate.setState(stateDAO.get(stateId));
					  candidate.setParliament(constituencyDAO.get(locationValue)); 
					  candidate.setAssembly(null);
				  }
			  }
			  else if(loctionId == 3)
			  {
				  candidate.setState(stateDAO.get(1l));
				  candidate.setParliament(null); 
				  candidate.setAssembly(null);
			  }
			  else 
			  {
				 candidate.setState(stateDAO.get(1l));
			  }
			  
			  candidateDAO.save(candidate);
			  
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			
			log.error(" Exception Occured in updateCandidatesAndParty() method, Exception - ",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		  }
		}
}
