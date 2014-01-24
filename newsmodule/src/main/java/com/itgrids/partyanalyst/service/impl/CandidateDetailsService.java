/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBenefitDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidateNewsResponseDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.dao.ICandidatePartyKeywordDAO;
import com.itgrids.partyanalyst.dao.ICandidateRelatedNewsDAO;
import com.itgrids.partyanalyst.dao.ICategoryDAO;
import com.itgrids.partyanalyst.dao.IChannelDAO;
import com.itgrids.partyanalyst.dao.IObserverDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDesignationDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IFileKeywordDAO;
import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IFileTypeDAO;
import com.itgrids.partyanalyst.dao.IFontDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IGallaryKeywordDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IKeywordDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMainCategoryDAO;
import com.itgrids.partyanalyst.dao.INewsDetailsDAO;
import com.itgrids.partyanalyst.dao.INewsImportanceDAO;
import com.itgrids.partyanalyst.dao.INewsResponseDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserCandidateRelationDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserNewsCategoryDAO;
import com.itgrids.partyanalyst.dao.hibernate.ChannelDAO;
import com.itgrids.partyanalyst.dao.hibernate.ObserverDAO;
import com.itgrids.partyanalyst.dto.CandidatePartyDestinationVO;
import com.itgrids.partyanalyst.dto.CandidatePartyNewsVO;
import com.itgrids.partyanalyst.dto.CategoryVO;
import com.itgrids.partyanalyst.dto.FileSourceVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Benefit;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateNewsResponse;
import com.itgrids.partyanalyst.model.CandidatePartyCategory;
import com.itgrids.partyanalyst.model.CandidatePartyFile;
import com.itgrids.partyanalyst.model.CandidatePartyKeyword;
import com.itgrids.partyanalyst.model.CandidateRealatedNews;
import com.itgrids.partyanalyst.model.Category;
import com.itgrids.partyanalyst.model.Channel;
import com.itgrids.partyanalyst.model.Observer;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.FileKeyword;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.model.GallaryKeyword;
import com.itgrids.partyanalyst.model.Keyword;
import com.itgrids.partyanalyst.model.NewsDetails;
import com.itgrids.partyanalyst.model.NewsImportance;
import com.itgrids.partyanalyst.model.NewsResponse;
import com.itgrids.partyanalyst.model.RegionScopes;
import com.itgrids.partyanalyst.model.Source;
import com.itgrids.partyanalyst.model.SourceLanguage;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.util.IConstants;
import com.itgrids.partyanalyst.utils.CommonStringUtils;
import com.itgrids.partyanalyst.utils.DateUtilService;


public class CandidateDetailsService implements ICandidateDetailsService {

	//private ICandidateResultDAO candidateResultDAO;
	private static final Logger log = Logger.getLogger(CandidateDetailsService.class);
	
	private DateUtilService dateUtilService = new DateUtilService();
	
	private ICandidateDAO candidateDAO;
	private IGallaryDAO gallaryDAO;
	private IFileGallaryDAO fileGallaryDAO;
	private INewsDetailsDAO newsDetailsDAO;
	private ICandidateRelatedNewsDAO candidateRelatedNewsDAO;
	private ICandidateNewsResponseDAO candidateNewsResponseDAO;
	private ICandidatePartyDAO candidatePartyDAO;
	private IUserNewsCategoryDAO userNewsCategoryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO; 
	private ITehsilDAO tehsilDAO;  
	private IHamletDAO hamletDAO;  
	private ILocalElectionBodyDAO localElectionBodyDAO;  
	private IBoothDAO boothDAO; 
	private IRegionScopesDAO regionScopesDAO;
	private ISourceDAO sourceDAO;
	private IFileDAO fileDAO;
	private IFileTypeDAO fileTypeDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private INominationDAO nominationDAO;
	private ICategoryDAO categoryDAO; 
	private TransactionTemplate transactionTemplate;
	private INewsImportanceDAO newsImportanceDAO;
	private IPartyGalleryDAO partyGalleryDAO;
	private ISourceLanguageDAO sourceLanguageDAO;
	private IFilePathsDAO filePathsDAO;
	private IFileSourceLanguageDAO fileSourceLanguageDAO;
	private IUserDAO userDAO;
	private IRegionServiceData regionServiceDataImp;
	private IStaticDataService staticDataService;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	 private IMainCategoryDAO mainCategoryDAO;
	 private INewsResponseDAO newsResponseDAO;
	 private IChannelDAO channelDAO;
	 private IObserverDAO observerDAO; 
	//private IContentTypeDAO contentTypeDAO;
	//private IUserGallaryDAO userGallaryDAO;

	//private ICountryDAO countryDAO;

	//private ICandidateProfileDescriptionDAO candidateProfileDescriptionDAO;

	//private CandidateProfileDescription candidateProfileDescription;
   // private IMessageToCandidateDAO messageToCandidateDAO;
	private IUserCandidateRelationDAO userCandidateRelationDAO;
	//private ICandidateUpdatesEmailDAO candidateUpdatesEmailDAO;

	//private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	//private List<SelectOptionVO> candidatesList;
	
	//private ISpecialPageGalleryDAO specialPageGalleryDAO;
	//private IMessageToPartyDAO messageToPartyDAO;
	private IElectionDAO electionDAO;
	//private IElectionGoverningBodyDAO electionGoverningBodyDAO;
//	private ICandidatePageCustomPagesDAO candidatePageCustomPagesDAO;
	//private ICustomPageTypeDAO customPageTypeDAO;
	//private ICustomPageDAO customPageDAO;
	//private IPartyDAO partyDAO;
	//private IPartyPageCustomPagesDAO partyPageCustomPagesDAO;
	//private ISpecialPageDAO specialPageDAO;
	//private ISpecialPageCustomPagesDAO specialPageCustomPagesDAO;
	//private ICandidateSubscriptionsDAO candidateSubscriptionsDAO;
	//private IPartySubscriptionsDAO partySubscriptionsDAO;
	//private IUserPartyRelationDAO userPartyRelationDAO;	
	
    private IDelimitationConstituencyDAO delimitationConstituencyDAO;
    private ICountryDAO countryDAO;
    private IUserAddressDAO userAddressDAO;
    private IKeywordDAO keywordDAO;
    private IGallaryKeywordDAO gallaryKeywordDAO; 
    private IFileKeywordDAO fileKeywordDAO;
    private IFontDAO fontDAO;
    private ICandidatePartyFileDAO candidatePartyFileDAO;
    private ICandidatePartyKeywordDAO candidatePartyKeywordDAO;
    private IPartyDAO partyDAO;
    private IBenefitDAO benefitDAO;
    private ICandidatePartyCategoryDAO candidatePartyCategoryDAO;
    private IDesignationDAO designationDAO;
	
    
	public IChannelDAO getChannelDAO() {
		return channelDAO;
	}

	public void setChannelDAO(IChannelDAO channelDAO) {
		this.channelDAO = channelDAO;
	}
	public IObserverDAO getObserverDAO() {
		return observerDAO;
	}

	public void setObserverDAO(IObserverDAO observerDAO) {
		this.observerDAO = observerDAO;
	}
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IDesignationDAO getDesignationDAO() {
		return designationDAO;
	}

	public void setDesignationDAO(IDesignationDAO designationDAO) {
		this.designationDAO = designationDAO;
	}

	public ICandidatePartyCategoryDAO getCandidatePartyCategoryDAO() {
		return candidatePartyCategoryDAO;
	}

	public void setCandidatePartyCategoryDAO(
			ICandidatePartyCategoryDAO candidatePartyCategoryDAO) {
		this.candidatePartyCategoryDAO = candidatePartyCategoryDAO;
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

	public ICandidatePartyDAO getCandidatePartyDAO() {
		return candidatePartyDAO;
	}

	public void setCandidatePartyDAO(ICandidatePartyDAO candidatePartyDAO) {
		this.candidatePartyDAO = candidatePartyDAO;
	}

	public ICandidateNewsResponseDAO getCandidateNewsResponseDAO() {
		return candidateNewsResponseDAO;
	}

	public void setCandidateNewsResponseDAO(
			ICandidateNewsResponseDAO candidateNewsResponseDAO) {
		this.candidateNewsResponseDAO = candidateNewsResponseDAO;
	}

	public ICandidateRelatedNewsDAO getCandidateRelatedNewsDAO() {
		return candidateRelatedNewsDAO;
	}

	public void setCandidateRelatedNewsDAO(
			ICandidateRelatedNewsDAO candidateRelatedNewsDAO) {
		this.candidateRelatedNewsDAO = candidateRelatedNewsDAO;
	}
	
	
	public INewsDetailsDAO getNewsDetailsDAO() {
		return newsDetailsDAO;
	}

	public void setNewsDetailsDAO(INewsDetailsDAO newsDetailsDAO) {
		this.newsDetailsDAO = newsDetailsDAO;
	}
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}


	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IFilePathsDAO getFilePathsDAO() {
		return filePathsDAO;
	}

	public void setFilePathsDAO(IFilePathsDAO filePathsDAO) {
		this.filePathsDAO = filePathsDAO;
	}

	public IFileSourceLanguageDAO getFileSourceLanguageDAO() {
		return fileSourceLanguageDAO;
	}

	public void setFileSourceLanguageDAO(IFileSourceLanguageDAO fileSourceLanguageDAO) {
		this.fileSourceLanguageDAO = fileSourceLanguageDAO;
	}


	public IPartyGalleryDAO getPartyGalleryDAO() {
		return partyGalleryDAO;
	}

	public void setPartyGalleryDAO(IPartyGalleryDAO partyGalleryDAO) {
		this.partyGalleryDAO = partyGalleryDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}


		public ISourceLanguageDAO getSourceLanguageDAO() {
		return sourceLanguageDAO;
	}

	public void setSourceLanguageDAO(ISourceLanguageDAO sourceLanguageDAO) {
		this.sourceLanguageDAO = sourceLanguageDAO;
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

	

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
		
	public IGallaryDAO getGallaryDAO() {
		return gallaryDAO;
	}

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}

	public IFileGallaryDAO getFileGallaryDAO() {
		return fileGallaryDAO;
	}

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
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

	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public ISourceDAO getSourceDAO() {
		return sourceDAO;
	}

	public void setSourceDAO(ISourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}

	public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}

	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}

	/*public ICountryDAO getCountryDAO() {
		return countryDAO;
	}

	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}*/	

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	
	public IUserCandidateRelationDAO getUserCandidateRelationDAO() {
		return userCandidateRelationDAO;
	}

	public void setUserCandidateRelationDAO(
			IUserCandidateRelationDAO userCandidateRelationDAO) {
		this.userCandidateRelationDAO = userCandidateRelationDAO;
	}
	public INewsImportanceDAO getNewsImportanceDAO() {
		return newsImportanceDAO;
	}

	public void setNewsImportanceDAO(INewsImportanceDAO newsImportanceDAO) {
		this.newsImportanceDAO = newsImportanceDAO;
	}


	
	public IUserNewsCategoryDAO getUserNewsCategoryDAO() {
		return userNewsCategoryDAO;
	}

	public void setUserNewsCategoryDAO(IUserNewsCategoryDAO userNewsCategoryDAO) {
		this.userNewsCategoryDAO = userNewsCategoryDAO;
	}
	
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}

	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}
    
	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
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

	public IFontDAO getFontDAO() {
		return fontDAO;
	}

	public void setFontDAO(IFontDAO fontDAO) {
		this.fontDAO = fontDAO;
	}
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
	
	public IBenefitDAO getBenefitDAO() {
		return benefitDAO;
	}

	public void setBenefitDAO(IBenefitDAO benefitDAO) {
		this.benefitDAO = benefitDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	
	/*public ResultStatus uploadAFile(final FileVO fileVO)
	{
		
		ResultStatus resultStatus = new ResultStatus();
		try{
			log.debug("Entered into uploadAFile() method in Candidate Details Service()");
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				
			
			//userAddress Table
			 UserAddress userAddress = saveFileLocationInUserAddress(fileVO.getLocationScope(),Long.parseLong(fileVO.getLocationValue()));	
			
				
			File file = new File();
			FileSourceLanguage fileSourceLanguage = new FileSourceLanguage();
			FilePaths filePaths = new FilePaths();
			FileGallary fileGallary = new FileGallary();
			Long orderNO = 1L;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			file.setFileName(fileVO.getName());
			file.setFilePath(fileVO.getPath() != null ? fileVO.getPath().trim():null);
			//file.setFileType(fileTypeDAO.getFileType(fileVO.getContentType()).get(0));
			file.setFileTitle(CommonStringUtils.removeSpecialCharsFromAString(fileVO.getTitle()));
			file.setFileDescription(fileVO.getDescription().replace("\r\n", ""));
			file.setKeywords(fileVO.getKeywords());
			file.setNewsDescription(fileVO.getNewsDescription());
			
			if(fileVO.getCategoryId() != null && fileVO.getCategoryId() > 0)
				file.setCategory(categoryDAO.get(fileVO.getCategoryId()));
			if(fileVO.getNewsImportanceId() != null && fileVO.getNewsImportanceId() > 0)
				file.setNewsImportance(newsImportanceDAO.get(fileVO.getNewsImportanceId()));
			
			
			if(fileVO.getLocationScope() != null && fileVO.getLocationScope().longValue() > 0 &&
					fileVO.getLocationValue() != null && Integer.parseInt(fileVO.getLocationValue()) > 0)
			{
				file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));
				file.setLocationValue(getLocationScopeValue(fileVO.getLocationScope(),fileVO.getLocationValue()));
			}
			else if(fileVO.getLocationScope() != null && fileVO.getLocationScope().longValue() > 0 && fileVO.getLocationScope() == 1L)
				file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));
				
			if(fileVO.getFileDate() != null && fileVO.getFileDate().length() > 0)
				try {
					file.setFileDate(sdf.parse(fileVO.getFileDate()));
				} catch (ParseException e) {
					log.error(e);
				}
			
			
			 FileVO 	displayImage = fileVO.getFileVOForDiaplyImage();
			    
			   if(displayImage != null){
					file.setFileName(displayImage.getDisplayImageName());
					file.setFilePath(displayImage.getDisplayImagePath() !=null?displayImage.getDisplayImagePath().trim():null);
			   }
				
			  file.setUser(userDAO.get(fileVO.getUserId()));
			   file.setNewsDescription(fileVO.getNewsDescription());
			 //  file.setUser(userDAO.get(1L));
			   
			   file.setUserAddress(userAddress);
				file = fileDAO.save(file);
			
				//file = fileDAO.save(file);
				List<Object> maxOrderNo = filePathsDAO.getMaxOrderNo();
					if(maxOrderNo == null && maxOrderNo.size()==0)
					 	orderNO = 1L;
					else if(maxOrderNo.get(0)!=null)
						orderNO = (Long)maxOrderNo.get(0)+1;
					if(fileVO.getSourceLangIds()!=null && fileVO.getSourceLangIds().size()>0)
					{
					for(int i=0;i<fileVO.getSourceLangIds().size();i++)
					{
						List<FileSourceLanguage> fileSourceLanguageObj = fileSourceLanguageDAO.getFileSourceLanguageObject(file.getFileId(),fileVO.getFileSource().get(i),fileVO.getSourceLangIds().get(i));
						if(fileSourceLanguageObj !=null && fileSourceLanguageObj.size() >0)
						{
							fileSourceLanguage = fileSourceLanguageObj.get(0);
							orderNO++;
						}
						else
						{
							fileSourceLanguage = new FileSourceLanguage();
							fileSourceLanguage.setLanguage(sourceLanguageDAO.get(fileVO.getSourceLangIds().get(i)));
							fileSourceLanguage.setSource(sourceDAO.get(fileVO.getFileSource().get(i)));
							fileSourceLanguage.setFile(file);
							fileSourceLanguage = fileSourceLanguageDAO.save(fileSourceLanguage);
							NewsDetails newsDetails = new NewsDetails();
							if(fileVO.getPageno() != null && fileVO.getPageno().size() > 0){
								newsDetails.setFileSourceLanguage(fileSourceLanguage);
								newsDetails.setPageNo(fileVO.getPageno().get(i));
								newsDetails.setEdition(fileVO.getNewsedition().get(i));
								newsDetails.setNewsLength(fileVO.getNewslength().get(i));
								newsDetailsDAO.save(newsDetails);
							}
						}
						if(fileVO.getFilePath() !=null)
						{
							
						 filePaths.setFilePath(fileVO.getFilePath().get(i) != null?fileVO.getFilePath().get(i).trim():null);
						 filePaths.setOrderNo(orderNO);
						 if(fileVO.getFileTypesList()!=null)
						 filePaths.setFileType(fileTypeDAO.getFileType(fileVO.getFileTypesList().get(i)).get(0));
						 filePaths.setFileSourceLanguage(fileSourceLanguage);
						 filePathsDAO.save(filePaths);
							
						}
					}
				}
					else
					{
						//Long languageId = (Long)sourceLanguageDAO.getLanguageIdByLanguage("No Language").get(0);
						//Long sourceId = (Long)sourceDAO.getSourceIdBySource("No Source").get(0);
						fileSourceLanguage.setLanguage(null);
						fileSourceLanguage.setSource(null);
						fileSourceLanguage.setFile(file);
						fileSourceLanguage = fileSourceLanguageDAO.save(fileSourceLanguage);
						if(fileVO.getFilePath() !=null)
						{
						for(int i=0;i<fileVO.getFilePath().size();i++)
						{
							 filePaths.setFilePath(fileVO.getFilePath().get(i) != null?fileVO.getFilePath().get(i).trim():null);
							 filePaths.setOrderNo(orderNO);
							 if(fileVO.getFileTypesList()!=null)
							 filePaths.setFileType(fileTypeDAO.getFileType(fileVO.getFileTypesList().get(i)).get(0));
							 filePaths.setFileSourceLanguage(fileSourceLanguage);
							 filePathsDAO.save(filePaths);
							 orderNO++;
						}
						}
					}
						
				fileGallary.setGallary(gallaryDAO.get(fileVO.getGallaryId()));
				fileGallary.setFile(file);
				fileGallary.setCreatedDate(dateUtilService.getCurrentDateAndTime());
				fileGallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
				fileGallary.setIsDelete(IConstants.FALSE);
				
				if(fileVO.getVisibility().equalsIgnoreCase("public"))
					fileGallary.setIsPrivate(IConstants.FALSE);
				else
					fileGallary.setIsPrivate(IConstants.TRUE);
				
				fileGallary = fileGallaryDAO.save(fileGallary);
				
				if(fileVO.getCandidateId() != null){
					
                    CandidateRealatedNews candidateRelatedNews = new CandidateRealatedNews();
					
					candidateRelatedNews.setCandidate(candidateDAO.get(fileVO.getCandidateId()));
					candidateRelatedNews.setFileGallary(fileGallary);
					
					candidateRelatedNewsDAO.save(candidateRelatedNews);	
					
					
				}
				
				if(fileVO.getCandidateIds().size() > 0){
					
					for(Long candidateId:fileVO.getCandidateIds()){
					
					CandidateRealatedNews candidateRelatedNews = new CandidateRealatedNews();
					
					candidateRelatedNews.setCandidate(candidateDAO.get(candidateId));
					candidateRelatedNews.setFileGallary(fileGallary);
					
					candidateRelatedNewsDAO.save(candidateRelatedNews);					
					
					}					
				}
				
				if(fileVO.getResponseFileIds() != null && fileVO.getResponseFileIds().size() >0)
					
					for(Long responseFileGallaryId:fileVO.getResponseFileIds())
					{
						CandidateNewsResponse candidateNewsresponse = new CandidateNewsResponse();
						
						candidateNewsresponse.setFileGallary(fileGallaryDAO.get(responseFileGallaryId));
						candidateNewsresponse.setResponseFileGallary(fileGallary);
						
						candidateNewsResponseDAO.save(candidateNewsresponse);						
						
					}
					
				
				if(fileVO.getUploadOtherProfileGalleryIds()!=null && fileVO.getUploadOtherProfileGalleryIds().size()>0)
				{
					for(int i=0;i<fileVO.getUploadOtherProfileGalleryIds().size();i++)
					{
						if(fileVO.getUploadOtherProfileGalleryIds().get(i) !=0)
						{
							fileGallary.setGallary(gallaryDAO.get(fileVO.getUploadOtherProfileGalleryIds().get(i)));
							fileGallary.setFile(file);
							fileGallary.setCreatedDate(dateUtilService.getCurrentDateAndTime());
							fileGallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
							fileGallary.setIsDelete(IConstants.FALSE);
							
							if(fileVO.getVisibility().equalsIgnoreCase("public"))
								fileGallary.setIsPrivate(IConstants.FALSE);
							else
								fileGallary.setIsPrivate(IConstants.TRUE);
							
							fileGallaryDAO.save(fileGallary);
						}
					}
			   }
		}
		});
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
		}catch (Exception e) {
			log.error("Exception encountered, Check log for Details - ",e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}*/
	
	

	
  //1111
	public ResultStatus uploadAFileForCandidateParty(final FileVO fileVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			log.debug("Entered into uploadAFileForCandidateParty() method in Candidate Details Service()");
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
			 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
			UserAddress userAddress = saveFileLocationInUserAddress(fileVO.getLocationScope(),Long.parseLong(fileVO.getLocationValue()));
			
			File file = new File();
			
			file.setFileTitle(fileVO.getTitle());
			file.setFileDescription(fileVO.getDescription());
			file.setSynopsysDescription(fileVO.getFileDescription1());
			file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));
			Long regionScopeId = regionScopesDAO.get(fileVO.getLocationScope()).getRegionScopesId();
			
			if(regionScopeId == 5 || regionScopeId == 6 || regionScopeId == 8 ){
				file.setLocationValue(Long.parseLong(fileVO.getLocationValue().substring(1)));			
			}
			else if(regionScopeId == 7){
				Long localEleBodyId = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(Long.valueOf(fileVO.getLocationValue().toString().substring(1))).get(0);
				file.setLocationValue(localElectionBodyDAO.get(localEleBodyId).getLocalElectionBodyId());				
			}
			else
				file.setLocationValue(Long.parseLong(fileVO.getLocationValue()));
			
			if(fileVO.getNewsImportanceId() != null && fileVO.getNewsImportanceId() > 0)
			 file.setNewsImportance(newsImportanceDAO.get(fileVO.getNewsImportanceId()));
			
			if(fileVO.getFileVOForDiaplyImage() != null)
			{
			  file.setFileName(fileVO.getFileVOForDiaplyImage().getDisplayImageName());
			  file.setFilePath(fileVO.getFileVOForDiaplyImage().getDisplayImagePath() !=null?fileVO.getFileVOForDiaplyImage().getDisplayImagePath().trim():null);	
			}
			
			if(fileVO.getFileDate() != null)
			{
			 try{
				 file.setFileDate(sdf.parse(fileVO.getFileDate())); 
			 }catch (Exception e) {
			  e.printStackTrace();
			  log.error(e);
			}
			}
			
			file.setUserAddress(userAddress);
			file.setUser(userDAO.get(fileVO.getUserId()));
			
			if(fileVO.isEenadu())
			 file.setFont(fontDAO.get(1));
			
			if(fileVO.isSynopsysEenadu())
				 file.setSynopsysFont(fontDAO.get(1));
				
			if(fileVO.isDescEenadu())
			 file.setDescFont(fontDAO.get(1));
			
			file.setCreatedDate(dateUtilService.getCurrentDateAndTime());
			file.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
			file.setIsDeleted("N");
			
			if(!fileVO.getVisibility().equalsIgnoreCase("public"))
			 file.setIsPrivate("Y");
			else
				file.setIsPrivate("N");	
			file = fileDAO.save(file);
			
			
			
			if(fileVO.getFileSourceVOList() != null && fileVO.getFileSourceVOList().size() > 0)
			{
			  for(FileSourceVO fileSourceVO :fileVO.getFileSourceVOList())
			  {
				FileSourceLanguage fileSourceLanguage = new FileSourceLanguage();
				fileSourceLanguage.setFile(file);
				if(fileSourceVO.getNewsDescCheck() != null)
				 fileSourceLanguage.setFont(fontDAO.get(1));
				
				fileSourceLanguage.setNewsDetailedDescription(fileSourceVO.getCompleteDesc());
				fileSourceLanguage.setLanguage(sourceLanguageDAO.get(fileSourceVO.getSourceLangId()));
				fileSourceLanguage.setSource(sourceDAO.get(fileSourceVO.getFileSourceId()));
				
				
				fileSourceLanguage = fileSourceLanguageDAO.save(fileSourceLanguage);
				
				if(fileSourceVO.getFileVOsList() != null && fileSourceVO.getFileVOsList().size() > 0)
				{
				 Long orderNo = 1L;
				 for(FileVO filePathFileVO :fileSourceVO.getFileVOsList())
				 {
					 FilePaths filePaths = new FilePaths();
					 
					 filePaths.setFilePath(filePathFileVO.getDisplayImagePath() !=null?filePathFileVO.getDisplayImagePath().trim():null);
					 filePaths.setOrderNo(orderNo);
					 filePaths.setHaveThumnails(IConstants.FALSE);
					 filePaths.setEdition(filePathFileVO.getNewsEditionId());
					 if(filePathFileVO.getPageNo() != null)
					 filePaths.setPageNo(filePathFileVO.getPageNo().intValue());
					 if(filePathFileVO.getImportanceId() != null)
					 filePaths.setNewsLength(filePathFileVO.getImportanceId().intValue());
					 if(filePathFileVO.getFileType()!=null)
					  filePaths.setFileType(fileTypeDAO.getFileType(filePathFileVO.getFileType()).get(0));
					 
					 filePaths.setFileSourceLanguage(fileSourceLanguage);
					 filePathsDAO.save(filePaths); 
					 orderNo++;
				 }
				}
				
			  }
			}
			
			//keywords saving
			if(fileVO.getCandidatePartyNewsVOList() != null)
			{
			  List<String> keywordsList = new ArrayList<String>(0);
			    CandidatePartyNewsVO newsVO = fileVO.getCandidatePartyNewsVOList();
				if(newsVO.getDestinationVOList() != null && newsVO.getDestinationVOList().size() > 0)
				 for(CandidatePartyDestinationVO destinationVO :newsVO.getDestinationVOList())	
				 {
					 if(destinationVO != null){
					  String keywords = destinationVO.getKeywordsList();
					  if(keywords != null && !keywords.equalsIgnoreCase(""))
					  {
						String[] str = keywords.split(",");
						if(str != null)
						 for(String keyword:str)
						  if(keyword != null && !keyword.trim().equalsIgnoreCase("") && !keywordsList.contains(""+keyword.substring(1)+""))
							  keywordsList.add(keyword.substring(1));
					  }
				    }
				 }
			 
			    if(keywordsList != null && keywordsList.size() > 0)
			    {
				  List<String> existingKeywords = keywordDAO.getExistingKeywordsByKeywordsList(keywordsList);
				  for(String keyword:keywordsList)
				   if(!existingKeywords.contains(keyword) && !keyword.equalsIgnoreCase(" "))
				   {
					 /*Keyword keyword2 = new Keyword(); 
					 keyword2.setType(keyword);
					 keyword2.setCreatedDate(dateUtilService.getCurrentDateAndTime());
					 keyword2.setCreatedBy(fileVO.getUserId());
					 keywordDAO.save(keyword2);*/
					   saveKeyword(fileVO.getUserId(),keyword);
				   }
			    }
			}
			
			
			if(fileVO.getCandidatePartyNewsVOList() != null)
			{
				boolean sourcePresent = false;
				boolean destinationPresent = false;
				 CandidatePartyNewsVO newsVO = fileVO.getCandidatePartyNewsVOList();
				for(CandidatePartyDestinationVO  source:newsVO.getSourceVOList()){
					if(sourcePresent)
						break;
					if(source != null){
						if((source.getPartyId()!= null &&  source.getPartyId() > 0) || (source.getCandidateId()!= null &&  source.getCandidateId() > 0)){
							sourcePresent = true;
						}
					}
				}
				for(CandidatePartyDestinationVO  destination:newsVO.getDestinationVOList()){
					if(destination != null){
						if(destinationPresent)
							break;
						if(destination != null){
							if((destination.getPartyId()!= null &&  destination.getPartyId() > 0) || (destination.getCandidateId()!= null &&  destination.getCandidateId() > 0)  || (destination.getCategoryIdsStr()!= null &&  destination.getCategoryIdsStr().trim().length() > 0) || (fileVO.getResponseFileIdsStr() != null && fileVO.getResponseFileIdsStr().trim().length() > 0) || (destination.getKeywordsList() != null && destination.getKeywordsList().trim().length() > 0) ){
								destinationPresent = true;
							}
						}
					}
				}
			    if(sourcePresent && destinationPresent)
				    saveSourceAndDestination(file,newsVO,fileVO.getResponseFileIdsStr());
			    else if(destinationPresent)
				{
				  for(CandidatePartyDestinationVO destinationVO :newsVO.getDestinationVOList())
				  {
				   if(destinationVO != null){
					CandidatePartyFile candidatePartyFile = new CandidatePartyFile();
					candidatePartyFile.setFile(file);
					
					candidatePartyFile.setDestinationBenefit(benefitDAO.get(destinationVO.getBenefitId()));
					if(destinationVO.getPartyId() != null && destinationVO.getPartyId() > 0)
					 candidatePartyFile.setDestinationParty(partyDAO.get(destinationVO.getPartyId()));
					if(destinationVO.getCandidateId() != null && destinationVO.getCandidateId() > 0)
					 candidatePartyFile.setDestinationCandidate(candidateDAO.get(destinationVO.getCandidateId()));
					
					 candidatePartyFile.setCreatedDate(dateUtilService.getCurrentDateAndTime());
					 candidatePartyFile.setUpdateddate(dateUtilService.getCurrentDateAndTime());
					 
					 candidatePartyFile = candidatePartyFileDAO.save(candidatePartyFile);
					 
					/* if(fileVO.getFileId() != null){
						 NewsResponse newsResponse = new NewsResponse();
						 newsResponse.setFile(fileDAO.get(fileVO.getFileId()));
						 newsResponse.setCandidatePartyFile(candidatePartyFile);
						 newsResponseDAO.save(newsResponse);
					 }*/
					 
					 if(fileVO.getResponseFileIdsStr() != null && fileVO.getResponseFileIdsStr().length() > 0)
					 {
					  String[] fileIdsStr = fileVO.getResponseFileIdsStr().split(",");
					  if(fileIdsStr != null)
					  {
						for(String responseFileId: fileIdsStr)
						{
							NewsResponse newsResponse = new NewsResponse();
							newsResponse.setFile(fileDAO.get(Long.parseLong(responseFileId)));
							newsResponse.setCandidatePartyFile(candidatePartyFile);
							newsResponseDAO.save(newsResponse);	
						}
					  }
					 }
					 //saving categories
					 if(destinationVO.getCategoryIdsStr() != null && destinationVO.getCategoryIdsStr().trim().length() > 0)
					 {
						 String[] str = destinationVO.getCategoryIdsStr().split(",");
						 if(str != null)
						    {
							  for(String categoryId: str)
							  {
								 if(categoryId != null && categoryId.trim().length() > 0){
									CandidatePartyCategory candidatePartyCategory = new CandidatePartyCategory();
									candidatePartyCategory.setGallary(gallaryDAO.get(Long.valueOf(categoryId.trim())));
									candidatePartyCategory.setCandidatePartyFile(candidatePartyFile);
									candidatePartyCategoryDAO.save(candidatePartyCategory);
								 }
							  }
						    }
					 }
					 
					 //keywords saving
					  if(destinationVO.getKeywordsList() != null && !destinationVO.getKeywordsList().equalsIgnoreCase(""))
					  {
					    String[] str = destinationVO.getKeywordsList().split(",");
					    if(str != null)
					    {
						  for(String keyword:str)
						  {
						    if(keyword != null && !keyword.equalsIgnoreCase(""))
						    {
						     CandidatePartyKeyword candidatePartyKeyword = new CandidatePartyKeyword();
						     candidatePartyKeyword.setCandidatePartyFile(candidatePartyFile);
						     candidatePartyKeyword.setKeyword(keywordDAO.get(keywordDAO.getKeywordIdByKeyword(keyword.substring(1)).get(0)));
						     candidatePartyKeywordDAO.save(candidatePartyKeyword);
						    }
						   }
					  	 }
					   }
				  }
				  }
				}
				else if(sourcePresent)
				{
					for(CandidatePartyDestinationVO  source:newsVO.getSourceVOList()){
						if(source != null){
							CandidatePartyFile candidatePartyFile = new CandidatePartyFile();
							candidatePartyFile.setFile(file);
							
							candidatePartyFile.setSourceBenefit(benefitDAO.get(source.getBenefitId()));
							if(source.getMediaId() != null && source.getMediaId() > 0)
							 candidatePartyFile.setMediaId(newsVO.getMediaId());
							else
							{
							  if(source.getPartyId() != null)
							  candidatePartyFile.setSourceParty(partyDAO.get(source.getPartyId()));
							  if(source.getCandidateId() != null && source.getCandidateId() > 0)	
							   candidatePartyFile.setSourceCandidate(candidateDAO.get(source.getCandidateId()));
							}
							 candidatePartyFile = candidatePartyFileDAO.save(candidatePartyFile);
							 
							/*if(fileVO.getFileId() != null){
								 NewsResponse newsResponse = new NewsResponse();
								 newsResponse.setFile(fileDAO.get(fileVO.getFileId()));
								 newsResponse.setCandidatePartyFile(candidatePartyFile);
								 newsResponseDAO.save(newsResponse);
							 }*/
							 
							 if(fileVO.getResponseFileIdsStr() != null && fileVO.getResponseFileIdsStr().length() > 0)
							 {
							  String[] fileIdsStr = fileVO.getResponseFileIdsStr().split(",");
							  if(fileIdsStr != null)
							  {
								for(String responseFileId: fileIdsStr)
								{
									NewsResponse newsResponse = new NewsResponse();
									newsResponse.setFile(fileDAO.get(Long.parseLong(responseFileId)));
									newsResponse.setCandidatePartyFile(candidatePartyFile);
									newsResponseDAO.save(newsResponse);	
								}
							  }
							 }
						}
					}
					
				}
				
			  
			}
			
			}
			});
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
			}catch (Exception e) {
				log.error("Exception encountered in uploadAFileForCandidateParty() method, Exception - ",e);
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
	}
	
	public void saveSourceAndDestination(File file,CandidatePartyNewsVO newsVO,String responseFileIds){

		
		List<CandidatePartyDestinationVO> sourceList = newsVO.getSourceVOList();
		List<CandidatePartyDestinationVO> destinationList = newsVO.getDestinationVOList();
		for(CandidatePartyDestinationVO  source:sourceList){
			if(source != null){try {

				for(CandidatePartyDestinationVO  destination:destinationList){
					if(destination != null){
						CandidatePartyFile candidatePartyFile = new CandidatePartyFile();
						candidatePartyFile.setFile(file);
						
						candidatePartyFile.setSourceBenefit(benefitDAO.get(source.getBenefitId()));
						if(source.getMediaId() != null && source.getMediaId() > 0)
						 candidatePartyFile.setMediaId(source.getMediaId());
						else
						{
						 if(source.getPartyId() != null && source.getPartyId() > 0)
						   candidatePartyFile.setSourceParty(partyDAO.get(source.getPartyId()));
						  if(source.getCandidateId() != null && source.getCandidateId() > 0)	
						   candidatePartyFile.setSourceCandidate(candidateDAO.get(source.getCandidateId()));
						}
						
						candidatePartyFile.setDestinationBenefit(benefitDAO.get(destination.getBenefitId()));
						 if(destination.getPartyId() != null && destination.getPartyId() > 0)
						    candidatePartyFile.setDestinationParty(partyDAO.get(destination.getPartyId()));
						if(destination.getCandidateId() != null && destination.getCandidateId() > 0)
						   candidatePartyFile.setDestinationCandidate(candidateDAO.get(destination.getCandidateId()));
						
						 candidatePartyFile.setCreatedDate(dateUtilService.getCurrentDateAndTime());
						 candidatePartyFile.setUpdateddate(dateUtilService.getCurrentDateAndTime());
						 
						 candidatePartyFile = candidatePartyFileDAO.save(candidatePartyFile);
						
						 
						 if(responseFileIds != null && responseFileIds.length() > 0)
						 {
						   String[] fileIdsStr = responseFileIds.split(",");
						   if(fileIdsStr != null)
						   {
							 for(String responseFileId :fileIdsStr)
							 {
								 NewsResponse newsResponse = new NewsResponse();
								 newsResponse.setFile(fileDAO.get(Long.parseLong(responseFileId)));
								 newsResponse.setCandidatePartyFile(candidatePartyFile);
								 newsResponseDAO.save(newsResponse);
							 }
						   }
						 }
						 //saving categories
						 if(destination.getCategoryIdsStr() != null && destination.getCategoryIdsStr().trim().length() > 0)
						 {
							 String[] str = destination.getCategoryIdsStr().split(",");
							 if(str != null)
							    {
								  for(String categoryId: str)
								  {
									 if(categoryId != null && categoryId.trim().length() > 0){
										CandidatePartyCategory candidatePartyCategory = new CandidatePartyCategory();
										candidatePartyCategory.setGallary(gallaryDAO.get(Long.valueOf(categoryId.trim())));
										candidatePartyCategory.setCandidatePartyFile(candidatePartyFile);
										candidatePartyCategoryDAO.save(candidatePartyCategory);
									 }
								  }
							    }
						 }
						 
						 //keywords saving
						  if(destination.getKeywordsList() != null && !destination.getKeywordsList().equalsIgnoreCase(""))
						  {
						    String[] str = destination.getKeywordsList().split(",");
						    if(str != null)
						    {
							  for(String keyword:str)
							  {
							    if(keyword != null && !keyword.equalsIgnoreCase("") && !"".equalsIgnoreCase(keyword.substring(1)) && keyword.substring(1).length() > 0)
							    {
							     CandidatePartyKeyword candidatePartyKeyword = new CandidatePartyKeyword();
							     candidatePartyKeyword.setCandidatePartyFile(candidatePartyFile);
							     candidatePartyKeyword.setKeyword(keywordDAO.get(keywordDAO.getKeywordIdByKeyword(keyword.substring(1)).get(0)));
							     candidatePartyKeywordDAO.save(candidatePartyKeyword);
							    }
							   }
						  	 }
						   }
					}
				}
			
			} catch (Exception e) {
			
			}
			
			}
			
		}
		
	  
		
	}
	
	public INewsResponseDAO getNewsResponseDAO() {
		return newsResponseDAO;
	}

	public void setNewsResponseDAO(INewsResponseDAO newsResponseDAO) {
		this.newsResponseDAO = newsResponseDAO;
	}

	public void saveCandidatePartyDetailsInCandidatePartyFileTable(List<Long> candidateOrPartyIds,File file,String type,String spokenBy,Long benefitId)
	{
	 try{
		if(candidateOrPartyIds != null && candidateOrPartyIds.size() > 0)
		{
		  for(Long id: candidateOrPartyIds)
		  {
			  CandidatePartyFile candidatePartyFile = new CandidatePartyFile();
			  candidatePartyFile.setFile(file);
			  candidatePartyFile.setCreatedDate(dateUtilService.getCurrentDateAndTime());
			  candidatePartyFile.setUpdateddate(dateUtilService.getCurrentDateAndTime());
			 /* if(type.equalsIgnoreCase("party"))
			    candidatePartyFile.setParty(partyDAO.get(id));
			  else
				candidatePartyFile.setCandidate(candidateDAO.get(id));
			  if(spokenBy!= null && spokenBy.equalsIgnoreCase("fromSource"))
			   candidatePartyFile.setSpokenBy(1L);*/
			  
			  /*if(benefitId != null && benefitId > 0)
				  candidatePartyFile.setBenefit(benefitDAO.get(benefitId));*/
			  
			  candidatePartyFileDAO.save(candidatePartyFile);   
		  }
		}
		 
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error("Exception Occured in saveCandidatePartyDetailsInCandidatePartyFileTable() method, Exception - "+e);
	 }
	}
	
	public ResultStatus uploadAFile(final FileVO fileVO)
	{
		
		ResultStatus resultStatus = new ResultStatus();
		try{
			log.debug("Entered into uploadAFile() method in Candidate Details Service()");
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
			Map<String,Long> existingKeywordsMap = new HashMap<String, Long>(0);	//<keywordName,keywordId>
			Map<String,Long> totalKeywordsMap = new HashMap<String, Long>(0);//<type,keywordId>
			
			
			List<Object[]> existingKeywordsList = keywordDAO.getKeyWordsList(fileVO.getKeyWordsList());
			if(existingKeywordsList != null && existingKeywordsList.size() > 0)
			 for(Object[] params:existingKeywordsList)
				 existingKeywordsMap.put(params[1] != null?params[1].toString():" ",(Long)params[0]);
			
			
			for(String keywordStr: fileVO.getKeyWordsList())
			{
				Long isExistKeyword = existingKeywordsMap.get(keywordStr);
				if(isExistKeyword == null)
				{
				 /*Keyword keyword = new Keyword();
				  keyword.setType(keywordStr);
				  //keyword.setDescription(keywordStr);
				  keyword.setCreatedDate(dateUtilService.getCurrentDateAndTime());
				  keyword.setCreatedBy(fileVO.getUserId());
				  keyword = keywordDAO.save(keyword);
				  */
					Keyword keyword = saveKeyword(fileVO.getUserId(),keywordStr);
				}
			}
			
			//total keywords
			List<Object[]> totalKeywordsList = keywordDAO.getKeyWordsList(fileVO.getKeyWordsList());
			if(totalKeywordsList != null && totalKeywordsList.size() > 0)
			 for(Object[] params:totalKeywordsList)
				 totalKeywordsMap.put(params[1] != null?params[1].toString():" ",(Long)params[0]);
			
			
			//get gallaryMapped keywords and save the unmapped keywords in gallarykeyword table with defaultgallaryId
			List<String> gallaryMappedKeywordsList = gallaryKeywordDAO.getGallaryMappedKeywordsList(fileVO.getKeyWordsList());
			 for(String keyword:totalKeywordsMap.keySet())
			  if(!gallaryMappedKeywordsList.contains(keyword))
			  {
				  GallaryKeyword gallaryKeyword = new GallaryKeyword();
				  gallaryKeyword.setGallary(gallaryDAO.get(IConstants.DEFAULT_GALLARY_ID));
				  gallaryKeyword.setKeyword(keywordDAO.get(totalKeywordsMap.get(keyword)));
				  gallaryKeyword.setCreatedDate(dateUtilService.getCurrentDateAndTime());
				  gallaryKeyword.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
				  gallaryKeyword.setCreatedBy(fileVO.getUserId());
				  gallaryKeywordDAO.save(gallaryKeyword);  
			  }
			
			 
			  UserAddress userAddress = saveFileLocationInUserAddress(fileVO.getLocationScope(),Long.parseLong(fileVO.getLocationValue()));
			  
			  File file = new File();
			  FileSourceLanguage fileSourceLanguage = new FileSourceLanguage();
			  FilePaths filePaths = new FilePaths();
			    Long orderNO = 1L;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				file.setFileName(fileVO.getName());
				file.setFilePath(fileVO.getPath() != null ? fileVO.getPath().trim():null);
				file.setFileTitle(CommonStringUtils.removeSpecialCharsFromAString(fileVO.getTitle()));
				file.setFileDescription(fileVO.getDescription().replace("\r\n", ""));
				//file.setKeywords(keywordStr);
				file.setNewsDescription(fileVO.getNewsDescription());
				
				/*if(fileVO.getCategoryId() != null && fileVO.getCategoryId() > 0)
					file.setCategory(categoryDAO.get(fileVO.getCategoryId()));*/
				if(fileVO.getNewsImportanceId() != null && fileVO.getNewsImportanceId() > 0)
					file.setNewsImportance(newsImportanceDAO.get(fileVO.getNewsImportanceId()));
				
				
				if(fileVO.getLocationScope() != null && fileVO.getLocationScope().longValue() > 0 &&
				    fileVO.getLocationValue() != null && Integer.parseInt(fileVO.getLocationValue()) > 0)
				{
					file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));
					file.setLocationValue(getLocationScopeValue(fileVO.getLocationScope(),fileVO.getLocationValue()));
				}
				else if(fileVO.getLocationScope() != null && fileVO.getLocationScope().longValue() > 0 && fileVO.getLocationScope() == 1L)
					file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));
					
				if(fileVO.getFileDate() != null && fileVO.getFileDate().length() > 0)
					try {
						file.setFileDate(sdf.parse(fileVO.getFileDate()));
					} catch (ParseException e) {
						log.error(e);
					}
				
				
				 FileVO displayImage = fileVO.getFileVOForDiaplyImage();
				    
				   if(displayImage != null){
						file.setFileName(displayImage.getDisplayImageName());
						file.setFilePath(displayImage.getDisplayImagePath() !=null?displayImage.getDisplayImagePath().trim():null);
				   }
					
				  file.setUser(userDAO.get(fileVO.getUserId()));
				  file.setNewsDescription(fileVO.getNewsDescription());
				   
				  file.setUserAddress(userAddress);
				  file = fileDAO.save(file);
				
				  List<Object> maxOrderNo = filePathsDAO.getMaxOrderNo();
					if(maxOrderNo == null && maxOrderNo.size()==0)
					 	orderNO = 1L;
					else if(maxOrderNo.get(0)!=null)
						orderNO = (Long)maxOrderNo.get(0)+1;
				if(fileVO.getSourceLangIds()!=null && fileVO.getSourceLangIds().size()>0)
				{
					for(int i=0;i<fileVO.getSourceLangIds().size();i++)
					{
						List<FileSourceLanguage> fileSourceLanguageObj = fileSourceLanguageDAO.getFileSourceLanguageObject(file.getFileId(),fileVO.getFileSource().get(i),fileVO.getSourceLangIds().get(i));
						if(fileSourceLanguageObj !=null && fileSourceLanguageObj.size() >0)
						{
							fileSourceLanguage = fileSourceLanguageObj.get(0);
							orderNO++;
						}
						else
						{
							fileSourceLanguage = new FileSourceLanguage();
							fileSourceLanguage.setLanguage(sourceLanguageDAO.get(fileVO.getSourceLangIds().get(i)));
							fileSourceLanguage.setSource(sourceDAO.get(fileVO.getFileSource().get(i)));
							fileSourceLanguage.setFile(file);
							fileSourceLanguage = fileSourceLanguageDAO.save(fileSourceLanguage);
							NewsDetails newsDetails = new NewsDetails();
							if(fileVO.getPageno() != null && fileVO.getPageno().size() > 0){
								newsDetails.setFileSourceLanguage(fileSourceLanguage);
								newsDetails.setPageNo(fileVO.getPageno().get(i));
								newsDetails.setEdition(fileVO.getNewsedition().get(i));
								newsDetails.setNewsLength(fileVO.getNewslength().get(i));
								newsDetailsDAO.save(newsDetails);
							}
						}
						if(fileVO.getFilePath() !=null)
						{
							
						 filePaths.setFilePath(fileVO.getFilePath().get(i) != null?fileVO.getFilePath().get(i).trim():null);
						 filePaths.setOrderNo(orderNO);
						 if(fileVO.getFileTypesList()!=null)
						 filePaths.setFileType(fileTypeDAO.getFileType(fileVO.getFileTypesList().get(i)).get(0));
						 filePaths.setFileSourceLanguage(fileSourceLanguage);
						 filePathsDAO.save(filePaths);
							
						}
					}
				}
				else
				{
						//Long languageId = (Long)sourceLanguageDAO.getLanguageIdByLanguage("No Language").get(0);
						//Long sourceId = (Long)sourceDAO.getSourceIdBySource("No Source").get(0);
						fileSourceLanguage.setLanguage(null);
						fileSourceLanguage.setSource(null);
						fileSourceLanguage.setFile(file);
						fileSourceLanguage = fileSourceLanguageDAO.save(fileSourceLanguage);
						if(fileVO.getFilePath() !=null)
						{
						for(int i=0;i<fileVO.getFilePath().size();i++)
						{
							 filePaths.setFilePath(fileVO.getFilePath().get(i) != null?fileVO.getFilePath().get(i).trim():null);
							 filePaths.setOrderNo(orderNO);
							 if(fileVO.getFileTypesList()!=null)
							 filePaths.setFileType(fileTypeDAO.getFileType(fileVO.getFileTypesList().get(i)).get(0));
							 filePaths.setFileSourceLanguage(fileSourceLanguage);
							 filePathsDAO.save(filePaths);
							 orderNO++;
						}
						}
				}

				List<Object[]> gallaryKeywordList = gallaryKeywordDAO.getGallaryKeywords(fileVO.getKeyWordsList());
				Map<Long,List<Long>> gallaryKeywordMap = new HashMap<Long, List<Long>>(0);
				if(gallaryKeywordList != null && gallaryKeywordList.size() > 0)
				 for(Object[] params :gallaryKeywordList)
				 {
				  	List<Long> keywordIdsList = gallaryKeywordMap.get((Long)params[0]);
				  	if(keywordIdsList == null)
				  	{
				  		keywordIdsList = new ArrayList<Long>(0);
				  		gallaryKeywordMap.put((Long)params[0], keywordIdsList);
				  	}
				  	if(!keywordIdsList.contains((Long)params[2]))
				  	{
				  	  keywordIdsList.add((Long)params[2]);
				  	gallaryKeywordMap.put((Long)params[0], keywordIdsList);
				  	}
				  	
				 }
				
				if(gallaryKeywordMap != null && gallaryKeywordMap.size() > 0)
				{
				  for(Long gallaryId :gallaryKeywordMap.keySet())
				  {
					FileGallary fileGallary = new FileGallary();
					fileGallary.setGallary(gallaryDAO.get(gallaryId));
					fileGallary.setFile(file);
					fileGallary.setCreatedDate(dateUtilService.getCurrentDateAndTime());
					fileGallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
					fileGallary.setIsDelete(IConstants.FALSE);
					if(fileVO.getVisibility().equalsIgnoreCase("public"))
					 fileGallary.setIsPrivate(IConstants.FALSE);
					else
					 fileGallary.setIsPrivate(IConstants.PRIVATE);
					fileGallary = fileGallaryDAO.save(fileGallary);
					
					if(fileVO.getCandidateId() != null){
					 CandidateRealatedNews candidateRealatedNews = new CandidateRealatedNews();
					 candidateRealatedNews.setCandidate(candidateDAO.get(fileVO.getCandidateId()));
					 candidateRealatedNews.setFileGallary(fileGallary);
					 candidateRelatedNewsDAO.save(candidateRealatedNews);
					}
					
					if(fileVO.getResponseFileIds() != null && fileVO.getResponseFileIds().size() > 0){
					  for(Long responseFileGallaryId:fileVO.getResponseFileIds())
					  {
						 CandidateNewsResponse candidateNewsResponse = new CandidateNewsResponse();
						 candidateNewsResponse.setFileGallary(fileGallary);
						 candidateNewsResponse.setResponseFileGallary(fileGallaryDAO.get(responseFileGallaryId));
						 candidateNewsResponseDAO.save(candidateNewsResponse);
					  }
					}
					
					if(fileVO.getUploadOtherProfileGalleryIds()!=null && fileVO.getUploadOtherProfileGalleryIds().size()>0)
					{
						for(int i=0;i<fileVO.getUploadOtherProfileGalleryIds().size();i++)
						{
							if(fileVO.getUploadOtherProfileGalleryIds().get(i) !=0)
							{
								fileGallary.setGallary(gallaryDAO.get(fileVO.getUploadOtherProfileGalleryIds().get(i)));
								fileGallary.setFile(file);
								fileGallary.setCreatedDate(dateUtilService.getCurrentDateAndTime());
								fileGallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
								fileGallary.setIsDelete(IConstants.FALSE);
								
								if(fileVO.getVisibility().equalsIgnoreCase("public"))
									fileGallary.setIsPrivate(IConstants.FALSE);
								else
									fileGallary.setIsPrivate(IConstants.TRUE);
								
								fileGallaryDAO.save(fileGallary);
							}
						}
				   }
				
				  }
				}
						
			for(String keywordStr: fileVO.getKeyWordsList())
			{
			  FileKeyword fileKeyword = new FileKeyword();
			  fileKeyword.setFile(file);
			  fileKeyword.setKeyword(keywordDAO.get(totalKeywordsMap.get(keywordStr)));
			  fileKeywordDAO.save(fileKeyword);
			  
			}		
			
		}
		});
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
		}catch (Exception e) {
			log.error("Exception encountered, Check log for Details - ",e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public UserAddress saveFileLocationInUserAddress(Long locationId,Long locationValue)
	{
	 try{
		 
		 if(locationId == null || locationId == 0L  )
			return null;
		 UserAddress userAddress = new UserAddress(); 
		 userAddress.setCountry(countryDAO.get(1L));
		
		if(locationId == 2L)
		  userAddress.setState(stateDAO.get(locationValue));
		
		else if(locationId == 3L)
		{
		  List<Object[]> list = districtDAO.getStateByDistrictID(locationValue);
		  if(list != null)
		   userAddress.setState(stateDAO.get((Long) list.get(0)[0]));
		   userAddress.setDistrict(districtDAO.get(locationValue));
		}
		else if(locationId == 4L)
		{
			List<Object[]> list = constituencyDAO.getStateDistrictConstituencyIds(locationValue);
			userAddress.setState(stateDAO.get((Long) list.get(0)[0]));
			userAddress.setDistrict(districtDAO.get((Long) list.get(0)[1]));
			userAddress.setConstituency(constituencyDAO.get(locationValue));
			userAddress.setParliamentConstituency(getParliamentConstiForAssembly(locationValue));
		}
		
		else if(locationId == 5L)
		{
			
			List<Object[]> list = delimitationConstituencyMandalDAO.getStateDistrictConstituencyIdByMandalId(Long.parseLong(locationValue.toString().substring(1)));
			if(list != null && list.size() > 0)
			{
				userAddress.setState(stateDAO.get((Long)list.get(0)[1]));
				userAddress.setDistrict(districtDAO.get((Long)list.get(0)[2]));	
				userAddress.setConstituency(constituencyDAO.get((Long)list.get(0)[0]));
				userAddress.setParliamentConstituency(getParliamentConstiForAssembly((Long)list.get(0)[0]));
				userAddress.setTehsil(tehsilDAO.get((Long)list.get(0)[3]));
				
			}
		}
		
		else if(locationId == 6L)
		{
			locationValue = Long.parseLong(locationValue.toString().substring(1));
			Long tehsilId = hamletDAO.get(locationValue).getTownship().getTehsil().getTehsilId();
			
			List<Long> constituencyIdsList = delimitationConstituencyMandalDAO.getConstituencyIdByTehsilId(tehsilId, Long.parseLong(IConstants.PRESENT_ELECTION_YEAR));
			
			if(constituencyIdsList != null && constituencyIdsList.size() > 0)
				userAddress.setConstituency(constituencyDAO.get(constituencyIdsList.get(0)));
			userAddress.setParliamentConstituency(getParliamentConstiForAssembly(constituencyIdsList.get(0)));
				
			userAddress.setState(stateDAO.get(hamletDAO.get(locationValue).getTownship().getTehsil().getDistrict().getState().getStateId()));
			userAddress.setDistrict(districtDAO.get(hamletDAO.get(locationValue).getTownship().getTehsil().getDistrict().getDistrictId()));	
			userAddress.setTehsil(tehsilDAO.get(hamletDAO.get(locationValue).getTownship().getTehsil().getTehsilId()));
			userAddress.setHamlet(hamletDAO.get(locationValue));
		}
		
		else if(locationId == 7L)
		{
			Long localEleBodyId = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(Long.valueOf(locationValue.toString().substring(1))).get(0);
			userAddress.setState(stateDAO.get(assemblyLocalElectionBodyDAO.get(Long.valueOf(locationValue.toString().substring(1))).getConstituency().getState().getStateId()));
			userAddress.setDistrict(districtDAO.get(assemblyLocalElectionBodyDAO.get(Long.valueOf(locationValue.toString().substring(1))).getConstituency().getDistrict().getDistrictId()));
			Long assId = assemblyLocalElectionBodyDAO.get(Long.valueOf(locationValue.toString().substring(1))).getConstituency().getConstituencyId();
			userAddress.setConstituency(constituencyDAO.get(assId));
			userAddress.setParliamentConstituency(getParliamentConstiForAssembly(assId));
			userAddress.setLocalElectionBody(localElectionBodyDAO.get(localEleBodyId));
		}
		
		else if(locationId == 8L)
		{
			locationValue = Long.parseLong(locationValue.toString().substring(1));
			Long assemBlyLocalEleId = (Long)assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyId(constituencyDAO.get(locationValue).getLocalElectionBody().getLocalElectionBodyId()).get(0);
			Long assId = assemblyLocalElectionBodyDAO.get(assemBlyLocalEleId).getConstituency().getConstituencyId();
			userAddress.setConstituency(constituencyDAO.get(assId));
			userAddress.setParliamentConstituency(getParliamentConstiForAssembly(assId));
			userAddress.setState(stateDAO.get(assemblyLocalElectionBodyDAO.get(assemBlyLocalEleId).getConstituency().getState().getStateId()));
			userAddress.setDistrict(districtDAO.get(assemblyLocalElectionBodyDAO.get(assemBlyLocalEleId).getConstituency().getDistrict().getDistrictId()));	
			//userAddress.setTehsil(tehsilDAO.get(assemblyLocalElectionBodyDAO.get(assemBlyLocalEleId).getConstituency().getTehsil().getTehsilId()));
			userAddress.setLocalElectionBody(localElectionBodyDAO.get(assemblyLocalElectionBodyDAO.get(assemBlyLocalEleId).getLocalElectionBody().getLocalElectionBodyId()));
			userAddress.setWard(constituencyDAO.get(locationValue));
		}
		
		else if(locationId == 9L)
		{
		  List<Object[]> list = boothDAO.getStateDistrictConstituencyIdsByBoothId(locationValue);
		  if(list != null && list.size() >0)
		  {
			  userAddress.setState(stateDAO.get((Long)list.get(0)[0]));
			  userAddress.setDistrict(districtDAO.get((Long)list.get(0)[1]));	
			  userAddress.setConstituency(constituencyDAO.get((Long)list.get(0)[2]));
			  userAddress.setParliamentConstituency(getParliamentConstiForAssembly((Long)list.get(0)[2]));
			  userAddress.setTehsil(tehsilDAO.get((Long)list.get(0)[3]));  
			  userAddress.setBooth(boothDAO.get(locationValue));
		  }
		}
		
		 
		userAddress = userAddressDAO.save(userAddress);
		 return userAddress;
	 }catch (Exception e) {
	  log.error(" Exception Occured in saveFileLocationInUserAddress() method, Exception - ",e);
	  return null;
	 }
	 	
	}
	
	public Constituency getParliamentConstiForAssembly(Long assemblyId){
		Constituency pariament = null;
		try{
		List<Object[]> parliamentConsti = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentByAssembly(assemblyId);
		if(parliamentConsti != null && parliamentConsti.size() > 0){
			Object[] parliament = parliamentConsti.get(0);
			if(parliament[0] != null){
				pariament = constituencyDAO.get((Long)parliament[0]);
				
			}
		}
		}catch(Exception e){
			log.error(" Exception Occured in getParliamentConstiForAssembly method, Exception - ",e);
		}
		return pariament;
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
			log.error("Error Occured in getLocationScopeVlaue() method - ",e); 
			return null;
		}
	}
	 public Map<String, List<FileVO>> getPhotosNewsVideosUpdateForACandidate(int startIndex,int maxResults,String level,String newsType)
	 {
		 try{
		 Map<String ,List<FileVO>> resultMap = new HashMap<String,List<FileVO>>();
/*		 List<FileGallary> photoGallaryresultList = new ArrayList<FileGallary>();
		 List<FileGallary> newsGallaryresultList = new ArrayList<FileGallary>();
		 List<FileGallary> singleNewsGallaryresultList = null;
		 List<FileGallary> singleVodeosGallaryresultList = null;
		 List<FileGallary> videoGallaryresultList = new ArrayList<FileGallary>();
		 List<FileVO> resultList = new ArrayList<FileVO>();
		 List<FileVO> vedioresultList = new ArrayList<FileVO>();
		 List<FileVO> newsresultList = new ArrayList<FileVO>();
		 
*/
		 		List<FileVO> nl;
		 		if(level.equalsIgnoreCase("state")||level.equalsIgnoreCase(""))
		 		{ 
				 List <Object[]> newsDetails =  partyGalleryDAO.getAllNewsDetailsForState(IConstants.TDPID, startIndex,maxResults,newsType,1l,2l);
				 /*if(newsDetails == null || newsDetails.isEmpty())
					 return null;*/
				   if(newsDetails != null && newsDetails.size() > 0)
				   {
					 int count=partyGalleryDAO.getCountOfNewsFiles(IConstants.TDPID,newsType,1l,2l);
					 nl = buildFileVo(newsDetails,count);
					 resultMap.put("NewsGallary", nl);
				   }
				 
		 		}
				 
			 if(level.equalsIgnoreCase("district")||level.equalsIgnoreCase("")){
				 List<Long> distIds = districtDAO.getAllDistrictByStateIds(1l);
				 List <Object[]> newsDetailsForDist =  partyGalleryDAO.getAllNewsDetailsForDistrict(872l,startIndex,maxResults,newsType,3l,distIds);
				 
				/* if(newsDetailsForDist == null || newsDetailsForDist.isEmpty() )
					 return null;
				 */
				 if(newsDetailsForDist != null && newsDetailsForDist.size() > 0)
				 {
				   int count1=partyGalleryDAO.getCountOfNewsFilesForDistrict(IConstants.TDPID,newsType,3l,distIds);			 
					 
				   if(newsDetailsForDist != null && !newsDetailsForDist.isEmpty()){
					  nl = buildFileVo(newsDetailsForDist,count1);
							
				    resultMap.put("NewsGallaryForDist", nl);
				   }
				 }
				 //List <Object[]> categoryList= partyGalleryDAO.getCategoryIdsForParty(IConstants.TDPID, 0, 5, newsType);
		/* newsGallaryresultList = fileGallaryDAO.getHomePageNewsDetails(startIndex, maxResults);
		 resultList = setToFileVO(newsGallaryresultList);
		 resultMap.put("NewsGallary", resultList);getCategoryIdsForParty*/
				 
				/* if(categoryList != null && !categoryList.isEmpty()){
				 List<FileVO> fo=buildFileVoForCategory(categoryList);
		
				 resultMap.put("categories", fo);
				 }*/
				 }
			 return resultMap;
		 }
		 catch (Exception e) {
			 log.error("Exception occured - ",e);
			 return null;
		 }
	}//18111
		 public List<FileVO>  buildFileVoForCategory(List<Object[]> newsDetails)
		 {
			 
			 List<FileVO> nl= new ArrayList<FileVO>();
			  for(Object[] obj : newsDetails )
			  {
				  long categoryId =(Long)obj[0];
		             String categoryName = (String)obj[1];
				        long partyId= (Long)obj[2];
				        FileVO v =new FileVO();
				        v.setCategoryId(categoryId);
				        v.setCategoryName(categoryName);
				        v.setCandidateId(partyId);
				        nl.add(v);
			  }
			 return nl;
		 }
		 
		 public List<FileVO>  buildFileVo(List<Object[]> newsDetails,int count)
		 { 
			 List<FileVO> nl = new ArrayList<FileVO>();
			 Set<Long> fileIds = new HashSet<Long>();
			
			 for(Object[] obj : newsDetails){
				  fileIds.add((Long)obj[0]);
			  }
			  Map<Long,String> sourceNames = getSourceNames(fileIds);
			  Map<Long,String> candidateNames = getCandidateNames(fileIds);
			  Map<Long,String> keywordsCount = getKeywordsCountByFileId(fileIds);
			  Map<Long,String> categorysCount = getCategorysCountByFileId(fileIds);
			    for(Object[] obj : newsDetails )
			    {  		       
				    FileVO v =new FileVO();
				    long id = (Long)obj[0];
				    Date date= null;
				    String newDate =null;
			        if(obj[1] != null)
				    {
				      date= (Date)obj[1];
				      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                      newDate = formatter.format(date);
				    }
                    v.setFileDate(newDate != null ? newDate : "");
	   
			        v.setContentId(id);
			        if(obj[4] != null){
			        	v.setEenadu(true);
			        }
			        if(obj[7] != null){
			        	v.setDescEenadu(true);
			        }
			        v.setCategoryName(categorysCount.get(id)!=null ? categorysCount.get(id) : "");
			        v.setKeywords(keywordsCount.get(id)!=null ? keywordsCount.get(id) : "");
			        v.setCandidateName(candidateNames.get(id) != null ? candidateNames.get(id) : "");
			    	v.setSource(sourceNames.get(id) != null?sourceNames.get(id):"");
	                v.setFileTitle1(StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString((String) obj[2])));
	                v.setDescription(StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString((String)obj[3])));
	                
	                List<Long> fileId = new ArrayList<Long>();
			        fileId.add(Long.valueOf(obj[0].toString()));
			        List<Object[]> responseCount = newsResponseDAO.getResponceCountForFiles(fileId);
			        
	                if(responseCount.size() > 0 && responseCount != null){
	                	for (Object[] parmas : responseCount) {
	                		v.setResponseCount(Integer.parseInt(parmas[1].toString()));
						}	                
	                }
	                else
	                	v.setResponseCount(0);
	                v.setDisplayImageName((String)obj[5]);
					v.setDisplayImagePath((String)obj[6]);
					v.setFileType("Party");
					v.setCount(count);
					
					nl.add(v);
			    	
			    }
			 
	     return nl;
		 } 
		 public Map<Long,String> getSourceNames(Set<Long> fileIds){
			  Map<Long,String> sourceNames = new HashMap<Long,String>();
			 try{
			  if(fileIds != null && fileIds.size() > 0){
				
				List<Object[]> sources = fileSourceLanguageDAO.getSourceDetailsByFileIds(fileIds);
				
			      if(sources != null && sources.size() > 0){
			    	 for(Object[] name:sources){
			    		 String source = sourceNames.get((Long)name[0]);
			    		 if(source == null)
			    			 sourceNames.put((Long)name[0], name[1].toString());
			    		 else
			    		 {
			    			 String source1 = source+", "+name[1].toString() ;
			    			 sourceNames.put((Long)name[0],source1.toString());
			    		 }
			    		 
			    	 }  
			      } 
			  }
			 }catch(Exception e){
				 log.error("Exception Occured in getSourceNames method in NewsMonitoringService", e);
			 }
			  return sourceNames;
		  }
		 public Map<Long,String> getCandidateNames(Set<Long> fileIds)
		 {
			  Map<Long,String> candidateNames = new HashMap<Long,String>();  //<fileId,CandName>
			  try
			  {
				  if(fileIds != null && fileIds.size() > 0)
				  {
					List<Object[]> candidates = fileSourceLanguageDAO.getCandidateDetailsByFileIds(fileIds);
					Map<Long,Set<Long>> map = new HashMap<Long, Set<Long>>();   //<fileId,CandIds>
					Map<Long,String> cadidateNameMap = new HashMap<Long, String>();//candidateId,Name
					Set<Long> candidateIds = null;
					Set<Long> candidateIdsList = new HashSet<Long>();
					for(Object[] param:candidates)
					{
						candidateIds = new HashSet<Long>();
						if(param[1] != null)
						candidateIds.add((Long)param[1]);
						if(param[2] != null)
						candidateIds.add((Long)param[2]);
						Set<Long> candIds = map.get((Long)param[0]);
						if(candIds != null && candIds.size()>0)
							candidateIds.addAll(candIds);
						map.put((Long)param[0], candidateIds);
						if(candidateIds != null)
						candidateIdsList.addAll(candidateIds);
					}
					if(candidateIdsList != null && candidateIdsList.size() > 0){
						List<Object[]> candidates1 = candidateDAO.getCandidateNames(candidateIdsList);
						for(Object[] param:candidates1){
							cadidateNameMap.put((Long)param[0], param[1].toString());
						}
					    if(map.size() > 0){
						   for(Long fileId:map.keySet())
						   {
							   Set<Long> candIds = map.get(fileId);
							   String candidateName1 = "";
							   for(Long candId:candIds)
								   if(candId != null)
								   candidateName1 += cadidateNameMap.get(candId)+", ";
							   
							   candidateNames.put(fileId, candidateName1.substring(0, candidateName1.length()-2));
						   }
					    }
					}
				 }
			 }catch(Exception e){
				 log.error("Exception Occured in getCandidateNames method in NewsMonitoringService", e);
			 }
			  return candidateNames;
		  }
		 
	public List<FileVO> getScopesForNewSearch()
	{   
		 List<FileVO> retValue = new ArrayList<FileVO>();
	try{
		List<RegionScopes> regionScopes = regionScopesDAO.getAll();
		for(RegionScopes result:regionScopes)
		{
			FileVO fileVO = new FileVO();
			fileVO.setLocationScope(result.getRegionScopesId());
			fileVO.setLocationScopeValue(result.getScope());
			retValue.add(fileVO);
		 }
		 
		return retValue;
		}
		catch(Exception e){
			e.printStackTrace();
			return retValue;
		}
	}/*
	public List<FileVO> searchNewsDetails(FileVO inputs){
		  List<FileVO> retValue = new ArrayList<FileVO>();
		  try{
			  List<Object[]> results = fileGallaryDAO.getNewsRecordsBySearchCriteria(inputs,IConstants.NEWS_GALLARY);
			
			  for(Object[] newsDetails: results){
			    FileVO fileVO = new FileVO();
		    	fileVO.setName(newsDetails[0] != null ? newsDetails[0].toString() :"");		    			    	
		   	  	fileVO.setPath(newsDetails[1] != null ? newsDetails[1].toString() :"");
		   	    fileVO.setFileTitle1(newsDetails[2] != null ? CommonStringUtils.removeSpecialCharsFromAString(newsDetails[2].toString()) :"");
		   	    fileVO.setFileDescription1(newsDetails[3] != null ? CommonStringUtils.removeSpecialCharsFromAString(newsDetails[3].toString()) :"");
		   	    fileVO.setScope(newsDetails[4] != null ? newsDetails[4].toString() :"");
		   	    Long scope = newsDetails[5]!= null ?(Long)newsDetails[5]:null;
		   	    Long locationValue = newsDetails[6]!= null ?(Long)newsDetails[6]:null;
		   	    
		   	    fileVO.setLocationValue(getLocationDetails(scope,locationValue));
		
		    	retValue.add(fileVO);	  
		      }
			return retValue;
			}
		  catch(Exception e){
				e.printStackTrace();
				return retValue;
			}
		}

	*/
	/*public List<FileVO> getDistrictDetailsByStateId(Long stateId)
	{   
		 List<FileVO> retValue = new ArrayList<FileVO>();
	try{
		List<Object[]> distList = districtDAO.getDistrictIdAndNameByState(stateId);    	 
    	for(Object[] param : distList)
		{
			FileVO fileVO = new FileVO();
			fileVO.setIds((Long)param[0]);
			fileVO.setNames(param[1].toString());
			retValue.add(fileVO);
		 }
		 
		return retValue;
		}
	catch(Exception e){
			e.printStackTrace();
			return retValue;
		}
	}*/
	
	public List<FileVO> getDistrictDetailsByStateId(Long stateId,String accessType,Long accessValue)
	{   
		 List<FileVO> retValue = new ArrayList<FileVO>();
	try{
		List<Object[]> districtList = null;
		if(accessType.equalsIgnoreCase("STATE"))
		{
		 List list  = districtDAO.getDistrictIdAndNameByState(stateId); 
		 if(list != null && list.size() > 0)
			 districtList = (List<Object[]>)list;
		}
		
		if(accessType.equalsIgnoreCase("DISTRICT"))
		  districtList = districtDAO.getDistrictIdAndNameByDistrictId(accessValue);	
		
		else if(accessType.equalsIgnoreCase("MLA"))
		{
		 List<Long> constituencyIdsList = new ArrayList<Long>(0);
		 constituencyIdsList.add(accessValue);
		 districtList = constituencyDAO.getDistrictByConstituencyId(constituencyIdsList);
		}
		
		else if(accessType.equalsIgnoreCase("MP"))
		{
			 List<Long> assemBlyIdsList = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsListByParliamId(accessValue);
			 if(assemBlyIdsList != null && assemBlyIdsList.size() > 0)
			  districtList = constituencyDAO.getDistrictByConstituencyId(assemBlyIdsList);
		}
		
    	for(Object[] param : districtList)
		{
			FileVO fileVO = new FileVO();
			fileVO.setIds((Long)param[0]);
			fileVO.setNames(param[1].toString());
			retValue.add(fileVO);
		 }
		 
		return retValue;
		}
	catch(Exception e){
			e.printStackTrace();
			return retValue;
		}
	}
	
	
	public List<FileVO> getStateDetails(String accessType,Long accessValue)
	{   
		 List<FileVO> retValue = new ArrayList<FileVO>();
	try{
		//List<com.itgrids.partyanalyst.model.State> states = stateDAO.getAll();
		List<Object[]> stateList = null;
		
		if(accessType.equalsIgnoreCase("STATE"))
		{
			State state = stateDAO.get(accessValue);
			if(state != null)
			{
				FileVO fileVO = new FileVO();
				fileVO.setIds(state.getStateId());
				fileVO.setNames(state.getStateName()!= null?state.getStateName():" ");
				retValue.add(fileVO);
			}
		}
		
		else if(accessType.equalsIgnoreCase("DISTRICT"))
		  stateList = districtDAO.getStateByDistrictID(accessValue);
			
		else if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP"))
		  stateList = constituencyDAO.getStateByConstituencyId(accessValue);
		
		if(stateList != null && stateList.size() > 0)
		{
		  for(Object[] params:stateList)
		  {
			 FileVO fileVO = new FileVO();
			 fileVO.setIds((Long)params[0]);
			 fileVO.setNames(params[1]!= null?params[1].toString():" ");
			 retValue.add(fileVO);  
		  }
		}
		
		 
		return retValue;
		}
		catch(Exception e){
			e.printStackTrace();
			return retValue;
		}
	}
	
	/*public List<FileVO> getStateDetails()
	{   
		 List<FileVO> retValue = new ArrayList<FileVO>();
	try{
		List<com.itgrids.partyanalyst.model.State> states = stateDAO.getAll();
		for(State result:states)
		{
			FileVO fileVO = new FileVO();
			fileVO.setIds(result.getStateId());
			fileVO.setNames(result.getStateName());
			retValue.add(fileVO);
		 }
		 
		return retValue;
		}
		catch(Exception e){
			e.printStackTrace();
			return retValue;
		}
	}*/
	/*
	public CandidateVO getCandidateDetails(Long candidateId){
		CandidateVO candidateVO = new CandidateVO();
		List<Candidate> candidate = candidateDAO.findCandidateDetails(candidateId);
		
		if(candidate != null){
			for(Candidate candidateDetails:candidate){
				candidateVO.setId(candidateDetails.getCandidateId());
				String name = null;
				  if(candidateDetails.getFirstname()!= null && candidateDetails.getLastname()!= null){
				    name = candidateDetails.getFirstname() + " " + candidateDetails.getLastname();
				  }
				  else if(candidateDetails.getFirstname() == null && candidateDetails.getLastname() != null)
					name = candidateDetails.getLastname();
				  else
					name = candidateDetails.getFirstname();
				candidateVO.setCandidateName(name);
				
				candidateVO.setImage(candidateDetails.getImage());
			}
		return candidateVO;
		}
		return null;
	}
	
	public List<CandidateDetailsVO> getCandidateElectionDetails(Long candidateId) {
		
		List<CandidateDetailsVO> candidateElectionDetails = new ArrayList<CandidateDetailsVO>(0);
		List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>(0);
		
		List<CandidateResult> candidateResults = candidateResultDAO.findCandidateResults(candidateId);
		 
		 if(candidateResults != null){
			//Nomination nomination = null;
			Candidate candidate = null;
			Party party = null;
			Constituency constituency = null;
			
			Election election = null;
			String districtName = "";
			for(CandidateResult result:candidateResults){
				//nomination = result.getNomination();
				candidate = result.getNomination().getCandidate();
				Party cndtParty=result.getNomination().getCandidate().getParty();
				if(cndtParty==null)
					party = result.getNomination().getParty();
				else
					party=cndtParty;
				
				constituency = result.getNomination().getConstituencyElection().getConstituency();
				election = result.getNomination().getConstituencyElection().getElection();
				
				CandidateDetailsVO candidateDetails = new CandidateDetailsVO();
												
				List<Object[]> districtsInfo = delimitationConstituencyAssemblyDetailsDAO
						.findDistrictsOfParliamentConstituencies(constituency.getConstituencyId());
				if(districtsInfo !=null)
				{
					districts = new ArrayList<SelectOptionVO>();
					SelectOptionVO optionVO = new SelectOptionVO();
					for(Object[] districtDetails : districtsInfo)
					{
						optionVO = new SelectOptionVO();
						optionVO.setId((Long)districtDetails[0]);
						optionVO.setName(districtDetails[1].toString());
						districts.add(optionVO);
						
					}
					candidateDetails.setGetDistricts(districts);				
				}			
		
				candidateDetails.setCandidateId(candidate.getCandidateId());
				String name = null;
				  if(candidate.getFirstname()!= null && candidate.getLastname()!= null){
				    name = candidate.getFirstname() + " " + candidate.getLastname();
				  }
				  else if(candidate.getFirstname() == null &&candidate.getLastname() != null)
					name = candidate.getLastname();
				  else
					name = candidate.getFirstname();
				candidateDetails.setCandidateName(name);
				if(constituency !=null){
					if(constituency.getConstituencyId()!=null){
						candidateDetails.setConstituencyName(constituency.getName());
						candidateDetails.setConstituencyId(constituency.getConstituencyId());
					}
					List pConstituencyList = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituenciesForAAssemblyConstituency(constituency.getConstituencyId());
					if(pConstituencyList != null && pConstituencyList.size()>0){
					for(int i=0;i<pConstituencyList.size();i++){
						Object[] params = (Object[]) pConstituencyList.get(i);
						candidateDetails.setParliamentConstituencyId(new Long(params[0].toString()));
						candidateDetails.setParliamentConstituencyName(params[2].toString());
					 }
					}
				}
				if(election !=null){
					if(election.getElectionId() != null){
						candidateDetails.setElectionId(election.getElectionId());
						candidateDetails.setElectionType(election.getElectionScope().getElectionType().getElectionType());
						candidateDetails.setElectionYear(election.getElectionYear());
					}
				}
				if(party != null){
					if(party.getPartyId()!=null){
						candidateDetails.setPartyName(party.getLongName());
						candidateDetails.setShortName(party.getShortName());
						candidateDetails.setPartyFlag(party.getPartyFlag());
						candidateDetails.setPartyId(party.getPartyId());
						
					}
				}
				
				candidateDetails.setRank(result.getRank());
				candidateDetails.setEducation(candidate.getEducation());
				String votes = result.getVotesEarned().toString();
				String votesEarn[] = StringUtils.split(votes, ".");
				candidateDetails.setVotesEarned(votesEarn[0]);
				candidateDetails.setVotesPercentage(result.getVotesPercengate());
				if(constituency.getDistrict() != null){
					districtName = constituency.getDistrict().getDistrictName();
					candidateDetails.setDistrictId(constituency.getDistrict().getDistrictId());
					candidateDetails.setDistrictName(districtName);
				}
				
				if(constituency.getState() != null)
				{
					candidateDetails.setStateId(constituency.getState().getStateId());
					candidateDetails.setStateName(constituency.getState().getStateName());
				}
				else
				{
					candidateDetails.setStateId(constituency.getLocalElectionBody().getDistrict().getState().getStateId());
					candidateDetails.setStateName(constituency.getLocalElectionBody().getDistrict().getState().getStateName());
				}
				candidateDetails.setImage(candidate.getImage());
				
				if(result.getRank().equals(new Long(1)))
					candidateDetails.setStatus(true);
				else
					candidateDetails.setStatus(false);
				
				List<CandidateOppositionVO> oppositionCandidates = getCandidatesOppositionList(candidate.getCandidateId(),election.getElectionId(),constituency.getConstituencyId());
				candidateDetails.setOppositionCandidates(oppositionCandidates);
				
				
				candidateElectionDetails.add(candidateDetails);
			} 
			getCandidateRole(candidateElectionDetails);	
			
			return candidateElectionDetails;
		 }
	return null; 
	}

	public String getCandidateRole(List<CandidateDetailsVO> candidateElectionDetails){
		String cndtRole="";
		boolean latestWon=false;
		try{
			if(candidateElectionDetails!=null && candidateElectionDetails.size()>0){
				Iterator itr=candidateElectionDetails.iterator();
				while(itr.hasNext()){
					CandidateDetailsVO latestElectionDtlVO=(CandidateDetailsVO)itr.next();
					if(latestElectionDtlVO.getStatus()==true){
						latestWon=true;
						if(latestElectionDtlVO.getElectionType().equalsIgnoreCase("Parliament")){
							cndtRole=IConstants.MP;					
						}
						else if(latestElectionDtlVO.getElectionType().equalsIgnoreCase("Assembly")){
							cndtRole=IConstants.MLA;
						}
					}
					else{
						if(!latestWon)
							latestWon=false;
						break;
					}
				}
			}
			
			if(latestWon==false){
				int wonCount=0;
				int lostCount=0;
				List<String> wonElctnTypeList=new ArrayList<String>();	
				List<String> lostElctnTypeList=new ArrayList<String>();	
				
				for(CandidateDetailsVO cndtDtlsVO : candidateElectionDetails){
					if(cndtDtlsVO.getStatus()==true){
						wonCount++;
						wonElctnTypeList.add(cndtDtlsVO.getElectionType());						
					}
					else{
						lostCount++;
						lostElctnTypeList.add(cndtDtlsVO.getElectionType());
					}
				}
				if(wonCount==0){
					if(lostElctnTypeList.contains(IConstants.PARLIAMENT_ELECTION_TYPE))
						cndtRole=IConstants.Contested_MP;
					else
						cndtRole=IConstants.Contested_MLA;
				}
				else{
					if(wonElctnTypeList.contains(IConstants.PARLIAMENT_ELECTION_TYPE))
						cndtRole=IConstants.Ex_MP;
					else
						cndtRole=IConstants.Ex_MLA;
				}
			}
			if(candidateElectionDetails!=null && candidateElectionDetails.size()>0){
				Iterator<CandidateDetailsVO> cndtDtlsItr=candidateElectionDetails.iterator();
				while(cndtDtlsItr.hasNext()){
					CandidateDetailsVO cndtLatestElectionDtlVO=(CandidateDetailsVO)cndtDtlsItr.next();
					cndtLatestElectionDtlVO.setCandidateRole(cndtRole);
					break;
				}
			}
		}
		catch(Exception e){
			log.error("Exception raised in getCandidateRole method of CandidateDetailsService: "+e);
			e.printStackTrace();
		}		
		return cndtRole;
	}
	
	public List<CandidateOppositionVO> getCandidatesOppositionList(Long candidateId,Long electionId,Long constituencyId){
		
		List<CandidateOppositionVO> oppositionCandidatesList =  new ArrayList<CandidateOppositionVO>(0);
		List<CandidateResult> candidateResultsList = candidateResultDAO.findCandidateResults(candidateId, electionId, constituencyId);
		 
		  if(candidateResultsList != null){
			  
			  Party party = null;
			  Candidate candidate = null;
			  
			  for(CandidateResult result:candidateResultsList){
				  
				  party = result.getNomination().getParty();
				  candidate  = result.getNomination().getCandidate();
				  
				  CandidateOppositionVO oppositionCandidate = new CandidateOppositionVO();
				  
				  oppositionCandidate.setCandidateId(candidate.getCandidateId());
				  String name = null;
				  if(candidate.getFirstname()!= null && candidate.getLastname()!= null){
				    name = candidate.getFirstname() + " " + candidate.getLastname();
				  }
				  else if(candidate.getFirstname() == null &&candidate.getLastname() != null)
					name = candidate.getLastname();
				  else
					name = candidate.getFirstname();
				  oppositionCandidate.setCandidateName(name);
				  oppositionCandidate.setPartyId(party.getPartyId());
				  oppositionCandidate.setPartyName(party.getLongName());
				  oppositionCandidate.setRank(result.getRank());
				  String votes = result.getVotesEarned().toString();
				  String votesEarn[] = StringUtils.split(votes, ".");
				  oppositionCandidate.setVotesEarned(votesEarn[0]);
				  oppositionCandidate.setVotesPercentage(result.getVotesPercengate());
				  
				  if(result.getRank().equals(new Long(1)))
					  oppositionCandidate.setStatus(true);
					else
						oppositionCandidate.setStatus(false);
				  oppositionCandidatesList.add(oppositionCandidate);
				  
			  }
		  return oppositionCandidatesList;	  
		  }
		
		return null;
	}
	
	public List<FileVO> getCandidatesPhotoGallaryDetail(Long candidateId,int firstRecord,int maxRecord,String type){
		 List<FileVO> retValue = new ArrayList<FileVO>();
	 try{
		List<Object[]> results = gallaryDAO.getCandidateGallaryDetail(candidateId,firstRecord,maxRecord,type);
		
		for(Object[] gallary: results){
			FileVO fileVO = new FileVO();
		    List<File> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    String path= null;
		    for(File file: record){
		    	fileVO.setFileId(file.getFileId());
		    	
		    	fileVO.setTitle(file.getFileTitle());
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
	public List<FileVO> getCandidatesPhotoGallaryDetailWithOutGallerySizeZero(Long candidateId,int firstRecord,int maxRecord,String type){
		 List<FileVO> retValue = new ArrayList<FileVO>();
	 try{
		List<Object[]> results = gallaryDAO.getCandidateGallaryDetail(candidateId,firstRecord,maxRecord,type);
		
		for(Object[] gallary: results){
			FileVO fileVO = new FileVO();
		    List<File> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    for(File file: record){
		    if(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()>0L)
		    {
		    	fileVO.setFileId(file.getFileId());		    			    	
		    	Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
		    	List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
				 Collections.sort(fileSourceLanguageList,fileSourceLanguageSort);
		    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList){
		    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
		    		List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
					  Collections.sort(filePathsList,filePathsSort);
		    		for(FilePaths filePath : filePathsList){
		    			
		    			fileVO.setPath(filePath.getFilePath());
		    			
		    			break;	
		    		}
		    	}
		    	fileVO.setTitle(file.getFileTitle());
		    	fileVO.setGallaryId((Long)gallary[0]);
		    	if(type != null && type.equalsIgnoreCase(IConstants.PHOTO_GALLARY))
		    		fileVO.setSizeOfGallary((fileGallaryDAO.getAllRecordCountInGallary((Long)gallary[0]).get(0)));
		    	else
			        fileVO.setSizeOfGallary((long)(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()));
			    fileVO.setGallaryName(gallary[1] != null ? gallary[1].toString() :"");
			    fileVO.setGallaryDescription(gallary[2] != null ? gallary[2].toString() :"");
			    fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString() :"");
			    fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString() :"");
			   
			    retValue.add(fileVO);
		    }
			    
	      }
		    	  
		}
		return retValue;
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 return retValue;
	 }
	}
	public List<FileVO> getFirstThreePhotoGallaryDetail(Long candidateId){
		 List<FileVO> retValue = new ArrayList<FileVO>();
	 try{
		List<Object[]> results = gallaryDAO.getCandidateGallaryDetail(candidateId,0,20,IConstants.PHOTO_GALLARY);
		
		for(Object[] gallary: results){
			FileVO fileVO = new FileVO();
		    List<File> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    for(File file: record){
		    	fileVO.setFileId(file.getFileId());
		    	String path=null;
		    	Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
		    	List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
				 Collections.sort(fileSourceLanguageList,fileSourceLanguageSort);
		    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList){
		    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
		    		List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
					  Collections.sort(filePathsList,filePathsSort);
		    		for(FilePaths filePath : filePathsList){
		    			path = filePath.getFilePath();
		    			if(path != null)
		    				break;
		    		}
		    	}
		    	fileVO.setPath(path != null ? path :null);
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
		    	
		    }
		    fileVO.setGallaryId((Long)gallary[0]);
		    fileVO.setSizeOfGallary((long)(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()));
		    fileVO.setGallaryName(gallary[1] != null ? gallary[1].toString() :"");
		    fileVO.setGallaryDescription(gallary[2] != null ? gallary[2].toString() :"");
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
	
	public List<FileVO> getCandidatesPhotosInAGallary(Long gallaryId){
	 List<FileVO> retValue = new ArrayList<FileVO>();
	 try{
		 List<Object[]> results = fileGallaryDAO.getAllRecordInGallary(gallaryId);
		 for(Object[] imageDetails: results){
			    FileVO fileVO = new FileVO();
			    File file =  (File)imageDetails[0];
			    fileVO.setFileId(file.getFileId());
			    Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
		    	List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
				 Collections.sort(fileSourceLanguageList,fileSourceLanguageSort);
		    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList){
		    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
		    		List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
					  Collections.sort(filePathsList,filePathsSort);
		    		List<String> filePaths = new ArrayList<String>();
		    		for(FilePaths filePath : filePathsList){
		    			
		    			filePaths.add(filePath.getFilePath() != null?filePath.getFilePath().trim():null);
		    			
		    				
		    		}
		    	
		    		fileVO.setFilePath(filePaths);	
		    	}
		   	  	fileVO.setPath(imageDetails[2] != null ? imageDetails[2].toString() :"");
		   	    fileVO.setFileTitle1(file.getFileTitle() != null ? CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle()) :"");
		   	    fileVO.setFileDescription1(file.getFileDescription() != null ? CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription()) :"");
		   	    fileVO.setGallaryName(imageDetails[1] != null ? imageDetails[1].toString() :"");
		    	retValue.add(fileVO);	  
		 }
		 
		return retValue;
		}
		catch(Exception e){
			e.printStackTrace();
			return retValue;
		}
	}
	public List<FileVO> getFirstFourNewsRecordsToDisplay(Long candidateId){
		if(log.isDebugEnabled())
			log.debug("Entered into getFirstFourNewsRecordsToDisplay() of candidateDetailsService");

		 return getAllNewsdetails(candidateId,0,4,"Public");
		 
		}
	public List<FileVO> getNewsToDisplay(Long candidateId,int firstResult,int maxResult,String queryType){
		 List<FileVO> retValue = new ArrayList<FileVO>();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 try{
			  FileVO fileVO;
			  List<Long> list = fileGallaryDAO.getNewsCountByScope(candidateId,null,queryType); 
			 
			 List<Object[]> results = fileGallaryDAO.getFirstFourNewsToDisplay(candidateId,firstResult,maxResult,queryType);
			 for(Object[] newsDetails: results){
				    fileVO = new FileVO();
				    fileVO.setFileId((Long)newsDetails[0]);
			    	fileVO.setName(newsDetails[1] != null ? newsDetails[1].toString() :"");		    			    	
			   	  	fileVO.setPath(newsDetails[2] != null ? newsDetails[2].toString() :"");
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? CommonStringUtils.removeSpecialCharsFromAString(newsDetails[3].toString()) :"");
			   	    fileVO.setFileDescription1(newsDetails[4] != null ? CommonStringUtils.removeSpecialCharsFromAString(newsDetails[4].toString()) :"");
			   	    fileVO.setSource(newsDetails[5] != null ? newsDetails[5].toString() :"");
			   	    fileVO.setLanguage(newsDetails[6] != null ? newsDetails[6].toString() :"");
			   	    fileVO.setFileDate(newsDetails[7] != null ? (sdf.format((Date)newsDetails[7])) :"");
			   	    fileVO.setCandidateId((Long)newsDetails[8]);
			   	    fileVO.setNewsImportanceId((Long)newsDetails[9]);
			   	    fileVO.setImportance(newsDetails[10] != null ? newsDetails[10].toString() :"");
			   	    fileVO.setTotalResultsCount(list.get(0));
			   	    List<Object[]> category = fileDAO.getCategoryDetailsOfAFile((Long)newsDetails[0]);
			   	    if(category != null && category.size() > 0)
			   	    {
			   	    	fileVO.setCategoryId((Long)category.get(0)[0]);
			   	    	fileVO.setCategoryType(category.get(0)[1].toString());
			   	    }
			   	    	
			   	    retValue.add(fileVO);	  
			 }
			 
			return retValue;
			}
			catch(Exception e){
				e.printStackTrace();
				return retValue;
			}
		}
	public List<FileVO> getFirstThreeImagesToDisplay(Long candidateId,int firstResult,int maxResult){
		 List<FileVO> retValue = new ArrayList<FileVO>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try{
			 List<Object[]> results = fileGallaryDAO.getFirstThreeImagesToDisplay(candidateId,firstResult,maxResult);
			 for(Object[] newsDetails: results){
				    FileVO fileVO = new FileVO();
				    fileVO.setFileId((Long)newsDetails[0]);
			    	fileVO.setName(newsDetails[1] != null ? newsDetails[1].toString() :"");		    			    	
			   	  	fileVO.setPath(newsDetails[2] != null ? newsDetails[2].toString() :"");
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? CommonStringUtils.removeSpecialCharsFromAString(newsDetails[3].toString()) :"");
			   	    fileVO.setFileDescription1(newsDetails[4] != null ? CommonStringUtils.removeSpecialCharsFromAString(newsDetails[4].toString()) :"");
			   	    fileVO.setSource(newsDetails[5] != null ? newsDetails[5].toString() :"");
			   	    fileVO.setFileDate(newsDetails[6] != null ? (sdf.format((Date)newsDetails[6])) :"");
			   	    fileVO.setCandidateId((Long)newsDetails[7]);
			    	retValue.add(fileVO);	  
			 }
			 
			return retValue;
			}
			catch(Exception e){
				e.printStackTrace();
				return retValue;
			}
		}
	public List<FileVO> getFileByFileId(Long fileId){
		 List<FileVO> retValue = new ArrayList<FileVO>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try{
			 List<Object[]> results = fileDAO.getFileByFileId(fileId);
			 for(Object[] newsDetails: results){
				 try{
				    FileVO fileVO = new FileVO();
				    fileVO.setFileId((Long)newsDetails[0]);
			    	fileVO.setName(newsDetails[1] != null ? newsDetails[1].toString() :"");		    			    	
			   	  	fileVO.setPath(newsDetails[2] != null ? newsDetails[2].toString() :"");
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? CommonStringUtils.removeSpecialCharsFromAString(newsDetails[3].toString()) :"");
			   	    fileVO.setFileDescription1(newsDetails[4] != null ? CommonStringUtils.removeSpecialCharsFromAString(newsDetails[4].toString()) :"");
			   	    fileVO.setSource(newsDetails[5] != null ? newsDetails[5].toString() :"");
			   	    fileVO.setFileDate(newsDetails[6] != null ? (sdf.format((Date)newsDetails[6])) :"");
			   	    fileVO.setContentId((Long)fileGallaryDAO.getFileGallaryIdByFileId(fileVO.getFileId()).get(0));
			    	retValue.add(fileVO);
				 }catch (Exception e) {}
			 }
			 
			return retValue;
			}
			catch(Exception e){
				e.printStackTrace();
				return retValue;
			}
		}
	
	
	*//**
	* This Method will Create A New Gallary by taking Basic information
	* @param GallaryVO 
	* @return ResultStatus
	* @author kamalakarDandu
	*//*
	
	public ResultStatus createNewGallaryOrUpdateGallary(GallaryVO gallaryVO,String createOrUpdate)
	{   Gallary gallary = null;
		ResultStatus resultStatus = new ResultStatus();
		try{
			if(createOrUpdate.trim().equalsIgnoreCase("Update") && gallaryVO.getGallaryId()!=null)
				gallary = gallaryDAO.get( gallaryVO.getGallaryId());
			else
			    gallary = new Gallary();
			UserGallary userGallary = null;
			gallary.setName(gallaryVO.getGallaryName());
			gallary.setDescription(gallaryVO.getDescription());
			if(createOrUpdate.trim().equalsIgnoreCase("Create"))
			{
				List<Object[]> list = gallaryDAO.checkGallaryNameExistenceForSelectedCandidate(gallaryVO.getCandidateId(), gallaryVO.getGallaryName(), gallaryVO.getContentType());
				if(list != null && list.size() >0)
				{
					resultStatus.setExceptionMsg("Gallery Name is already Exist for selected Candidate.");
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					return resultStatus;
				}
				
				gallary.setCandidate(candidateDAO.get(gallaryVO.getCandidateId()));
			    gallary.setContentType((ContentType)contentTypeDAO.getContentTypeByType(gallaryVO.getContentType()));
			    gallary.setCreatedDate(dateUtilService.getCurrentDateAndTime());
			    gallary.setIsDelete(IConstants.FALSE);
			}
			gallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
			gallary.setIsPrivate(gallaryVO.getVisibility());	
			
			gallary = gallaryDAO.save(gallary);
			if(createOrUpdate.trim().equalsIgnoreCase("Create")) 
			{	
			userGallary = new UserGallary();
			userGallary.setGallary(gallary);
			userGallary.setUserId(gallaryVO.getUserId());
			userGallaryDAO.save(userGallary);
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	
	
	
	*//**
	 * This method will give Gallaries Id and name in List<SelectOptionVO>, 
	 * when we pass candidateId as Argument.
	 * @author Kamalakar Dandu
	 * @param Long candidateId
	 * @return List<SelectOptionVO>
	 *//*
	public List<SelectOptionVO> getCandidateGallarySelectList(Long candidateId,String contentType)
	{
		try{
			log.debug("Entered into getCandidateGallarySelectList() Method");
			
			List<SelectOptionVO> gallarySelectList = null;
			List<Object[]> list = gallaryDAO.getGallariesByCandidateId(candidateId,contentType);
			
			if(list != null && list.size() > 0)
			{
				gallarySelectList = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : list)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					gallarySelectList.add(selectOptionVO);
				}
			}
			return gallarySelectList;
		}catch (Exception e) {
			log.error("Exception Occured in getCandidateGallarySelectList() method - "+e);
			return null;
		}
	}
	
	*//**
	 * This method returns Candidate Profile Descriptions when we pass CandidateId as Argument
	 * @author Sachin
	 * @param Long candidateId
	 * @return List <String>
	 *//*
	public List<String> getCandidateProfileDescriptionByCandidateID(Long candidateId)
	{
	 try{
			log.debug("Entered into getCandidateProfileDescriptionByCandidateID() Method");
		 List<Object> results = candidateProfileDescriptionDAO.getCandidateProfileDescription(candidateId);
		 
		 if(results != null && results.size() >0)
		 {
			 List<String> descList = new ArrayList<String>(0); 
			 for(Object desc :results)
				 descList.add(desc.toString());
			 return descList;
		 }
		 else 
			return null;
		 
	 }catch(Exception e){
		 log.error("Exception Occured in getCandidateProfileDescriptionByCandidateID() method - "+e);
		 return null;
	 }


	}
	
	*//**
	 * This method will save the Candidate Profile Descriptions when we pass the candidate id and description (In GallaryVO) as Argument
	 * @author Sachin
	 * @param GallaryVO gallaryVO
	 * @return ResultStatus
	 *//*
	
	public ResultStatus saveDescription(GallaryVO gallaryVO)
	{
		log.debug("Entered into saveDescription() Method");
		Long orderNo;
		candidateProfileDescription = new CandidateProfileDescription() ;
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Object> results =candidateProfileDescriptionDAO.getMaxOrderNo(gallaryVO.getCandidateId());
			
			orderNo = results.get(0) == null ? 0l : (Long)results.get(0);
			orderNo = orderNo + 1;
			candidateProfileDescription.setDescription(gallaryVO.getDescription());
			candidateProfileDescription.setOrderNo(orderNo);
			candidateProfileDescription.setCandidate(candidateDAO.get(gallaryVO.getCandidateId()));
			candidateProfileDescriptionDAO.save(candidateProfileDescription);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			 log.error("Exception Occured in saveDescription() method - "+e);
			return resultStatus;
		}
	}
	
	*//**
	 * This method will populate  the Candidate Profile Descriptions information when we pass the candidate id  as Argument
	 * @author Sachin
	 * @param Long candidateId
	 * @return List<GallaryVO>
	 *//*
	public List<GallaryVO> getCandidateProfileInfo(Long candidateId)
	{
		log.debug("Entered into getCandidateProfileInfo() Method");
		
		List<GallaryVO> gallaryVO = new ArrayList<GallaryVO>();
	List<Object[]> list = candidateProfileDescriptionDAO.getCandidateProfileInfo(candidateId);
	
	try{
	for(Object[] params : list)
	{
		GallaryVO gallary = new GallaryVO();
		gallary.setCandidateId((Long)params[0]);
		gallary.setDescription(params[1].toString().replaceAll("\\s+", " "));
		gallary.setUserId((Long)params[2]);
		gallaryVO.add(gallary);
	}
	}catch (Exception e)
	{
		 log.error("Exception Occured in getCandidateProfileInfo() method - "+e);
		return null;
	}
		return gallaryVO;
		
	}
	
	*//**
	 * This method will update  the Candidate Profile Descriptions information when we pass the gallaryVO (to update the description and order no) and candidate id  as Argument
	 * @author Sachin
	 * @param List<GallaryVO> gallaryVO
	 * @param Long candidateId
	 * @return ResultStatus
	 *//*
	public ResultStatus updateProfileDescription(List<GallaryVO> gallaryVO , Long candidateId)
	{
		log.debug("Entered into updateProfileDescription() Method");
		ResultStatus resultStatus = new ResultStatus();	
	
		try{
			Candidate candidate = candidateDAO.get(candidateId);
			
				for(GallaryVO params : gallaryVO)
				{
			    CandidateProfileDescription candidateProfileDescription = candidateProfileDescriptionDAO.get(params.getUserId());
			    candidateProfileDescription.setCandidate(candidate);
			    candidateProfileDescription.setDescription(params.getDescription());
			    candidateProfileDescription.setOrderNo(params.getCandidateId());
		        candidateProfileDescriptionDAO.save(candidateProfileDescription);
				}
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
     	}catch (Exception e) {
		resultStatus.setExceptionEncountered(e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 log.error("Exception Occured in updateProfileDescription() method - "+e);
		return resultStatus;
	    }
	}
	
	*//**
	 * This method will save  the message send to the candidate and it will take argument as gallaryVO(sender name,constituencyId and message)
	 * @author Sachin
	 * @param GallaryVO gallaryVO
	 * @return ResultStatus
	 *//*
	public ResultStatus saveMessage(GallaryVO gallaryVO)
	{
		log.debug("Entered into saveMessage() Method");
		ResultStatus resultStatus = new ResultStatus();	
	
		try{
		MessageToCandidate candidate = new MessageToCandidate();
		Constituency constituency = constituencyDAO.get(gallaryVO.getUserId());
		Candidate candidate2 = candidateDAO.get(gallaryVO.getCandidateId());
		candidate.setName(gallaryVO.getGallaryName());
		candidate.setCandidate(candidate2);
		candidate.setConstituency(constituency);
		candidate.setIsApproved(IConstants.FALSE);
		candidate.setIsDelete(IConstants.FALSE);
		candidate.setTime(dateUtilService.getCurrentDateAndTime());
		candidate.setMessage(gallaryVO.getDescription());
		if(gallaryVO.getIsPrivate().equalsIgnoreCase(IConstants.TRUE))
			candidate.setIsPrivate(IConstants.TRUE);
		if(gallaryVO.getIsPrivate().equalsIgnoreCase(IConstants.FALSE))
			candidate.setIsPrivate(IConstants.FALSE);
			messageToCandidateDAO.save(candidate);
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
     	}catch (Exception e) {
		resultStatus.setExceptionEncountered(e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 log.error("Exception Occured in saveMessage() method - "+e);
		return resultStatus;
	    }
	}
	
	*//**
	 * This method will delete  the Candidate Profile Descriptions information when we pass the CandidateProfileDescriptionId  as argument
	 * @author Sachin
	 * @param Long profDescId
	 * @return ResultStatus
	 *//*
	public ResultStatus deleteProfileDescById(Long profDescId)
	{
		log.debug("Entered into deleteProfileDescById() Method");
		ResultStatus resultStatus = new ResultStatus();	
		
	    int flag = candidateProfileDescriptionDAO.deleteCandidateProfileDescriptionById(profDescId);
          if(flag!=0){	
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
          }
          else
          {  
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		   }
		}
		
  public ResultStatus deleteFilesAndPhotos(Long fileId , Long gallaryId)
	 {
	 	log.debug("Entered into deleteFilesAndPhotos() Method");
		ResultStatus resultStatus = new ResultStatus();	
		
	    int flag = fileGallaryDAO.deleteFilesAndPhotos(fileId , gallaryId);
          if(flag!=0){	
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
          }
          else
          {  
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		   }
		}
  
  public ResultStatus deleteGallary(Long gallaryId)
	 {
	 	log.debug("Entered into deleteFilesAndPhotos() Method");
		ResultStatus resultStatus = new ResultStatus();	
		
	    int flag = gallaryDAO.deleteGallary(gallaryId);
       if(flag!=0){	
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
       }
       else
       {  
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		   }
	}
  public ResultStatus deletePartyGallary(Long gallaryId)
  {
	  log.debug("Entered into deletePartyGallary() method");
	  ResultStatus resultStatus = new ResultStatus();
	  try
	  {
		int flag =partyGalleryDAO.deletePartyGallary(gallaryId);
		if(flag!=0)
		{
			deleteGallary(gallaryId);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
		}
  	 else
  	 {
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
	   	
  	 }
	  }
	  
	  catch(Exception e)
	  {
		  log.error("Exception Occured in deletePartyGallary() method of CandidateDetailsService"+e);
	  }
	return resultStatus;
  }
	*//**
	 * This Method will give Videos Details As List<FileVO> when we pass candidateId,start Index and Max results As Arguements
	 * @author Kamalakar Dandu
	 * @param Long candidateId
	 * @param Integer startIndex
	 * @param Integer maxRecords
	 * @return List<FileVO>
	 *//*
	public List<FileVO> getCandidateLatestVideos(Long candidateId,Integer startIndex, Integer maxRecords)
	{
		try{
			log.debug("Entered into getCandidateLatestVideos() Method");
			
			List<FileVO> fileList = null;
			List<File> list = fileGallaryDAO.getCandidateLatestVideos(candidateId,startIndex,maxRecords);
			
			if(list != null && list.size() > 0)
			{
				fileList = new ArrayList<FileVO>(0);
				FileVO fileVO = null;
				for(File file : list)
				{
					fileVO = copyFileToFileVO(file);
					if(fileVO != null)
						fileList.add(fileVO);
				}
			}
			
			return fileList;
		}catch (Exception e) {
			log.error("Error Occured in getCandidateLatestVideos() Method - "+e);
			return null;
		}
	}
	
	*//**
	 * This Method Copy The Data From File Object To FileVO Object
	 * @author Kamalakar Dandu
	 * @param File
	 * @return FileVO
	 *//*
	public FileVO copyFileToFileVO(File file)
	{
		try{
			log.debug("Entered into copyFileToFileVO() Method");
			FileVO fileVO = null;
			
			if(file != null)
			{
				fileVO = new FileVO();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				fileVO.setFileId(file.getFileId());
				fileVO.setName(file.getFileName());
				fileVO.setTitle(file.getFileTitle());
				fileVO.setContentId((Long)fileGallaryDAO.getFileGallaryIdByFileId(fileVO.getFileId()).get(0));
				fileVO.setFileType(file.getFileType() != null ? file.getFileType().getType() : "");
				
				fileVO.setDescription(file.getFileDescription());
				fileVO.setKeywords(file.getKeywords());
				
				fileVO.setFileDate(file.getFileDate() != null ? sdf.format(file.getFileDate()) : "");
				String path = null;
				Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
				for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
					fileVO.setSource(fileSourceLanguage.getSource()!= null?fileSourceLanguage.getSource().getSource():"");
		    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
		    		for(FilePaths filePath : filePathsSet){
		    			if(path != null && path.trim().length() >0)
		    				break;
		    				path = filePath.getFilePath();
		    		}
		    		if(path != null && path.trim().length() >0)
	    				break;
		    	}
				fileVO.setPath(path);
			}
			return fileVO;
		}catch (Exception e) {
			log.error("Error Occured in copyFileToFileVO() Method - "+e);
			return null;
		}
	}
	public List<SelectOptionVO> getCandidateDetailsBySearchCriteria(String gender,String name,Long constituencyId,Long userId,Long stateId)
	{
		List<SelectOptionVO> returnValue = new ArrayList<SelectOptionVO>();
		try
		{
		   List<Object[]> results = nominationDAO.getCandidatesToMapWithUser(gender,name,constituencyId,userId,stateId);
		   for(Object[] candidateDetails: results)
		   {
			  SelectOptionVO selectOptionVO = new SelectOptionVO();
			  selectOptionVO.setId((Long)candidateDetails[0]);
			  selectOptionVO.setName(candidateDetails[1] != null ? candidateDetails[1].toString() :"");
			  returnValue.add(selectOptionVO);
		   }
		  return returnValue;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return returnValue;
		}
	}
	
	public List<FileVO> getAllVideosInAGalleryForACandidate(Long gallaryId){
		
		FileVO fileVO = new FileVO();
		List<FileVO> filesList = new ArrayList<FileVO>(0);
		
		List<Object[]> records = fileGallaryDAO.getAllRecordInGallary(gallaryId);
		
		for(Object[] videos :records){
			File file= (File)videos[0];
			String path = null;
			fileVO = new FileVO();
			fileVO.setFileId(file.getFileId());
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
	    	fileVO.setPathOfFile(path);
			fileVO.setTitle(file.getFileTitle());
			fileVO.setDescription(file.getFileDescription());
			fileVO.setContentId((Long)videos[2]);
			filesList.add(fileVO);
	    }
		
		return filesList;
		
	}
	
	public ResultStatus saveUserCandidateRelation(Long userId,Long candidateId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
		  UserCandidateRelation userCandidateRelation = new UserCandidateRelation();
		  userCandidateRelation.setCandidate(candidateDAO.get(candidateId));
		  userCandidateRelation.setUser(userDAO.get(userId));
		  userCandidateRelationDAO.save(userCandidateRelation);
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  return resultStatus;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	public ResultStatus deleteUserCandidateRelation(String userCandidateRelationIds)
	{   List<String> elements = null;
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(userCandidateRelationIds.length()>0)
			{
				elements = new ArrayList<String>(new HashSet<String>(Arrays.asList(new String(userCandidateRelationIds).split(","))));	
				for(int i=0;i<elements.size();i++)
				  {
					Long id = new Long(elements.get(i));
					userCandidateRelationDAO.deleteUserCandidateRelation(id);
					
				  }
			}
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  return resultStatus;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	public List<FileVO> getAllCandidateDetailsAssignedToAUser(Long userId)
	{
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{
		   List<Object[]> results = userCandidateRelationDAO.getUserCandidateRelationDetails(userId);
		   for(Object[] candidateDetails: results)
		   {
			   FileVO fileVO = new FileVO();
			   fileVO.setIds((Long)candidateDetails[0]);
			   fileVO.setCandidateId((Long)candidateDetails[1]);
			   fileVO.setNames(candidateDetails[2] != null ? candidateDetails[2].toString() :"");
			  returnValue.add(fileVO);
		   }
		  return returnValue;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return returnValue;
		}
	}
	
		public List<FileVO> getNewsCountByScope(Long candidateId,String queryType)
			{
				List<FileVO> returnValue = new ArrayList<FileVO>();
				try
				{
				   FileVO fileVO = null;
				   for(int index=1;index<10;index++)
				   {
					   List<Long> list = fileGallaryDAO.getNewsCountByScope(candidateId,(long)index,queryType);  
					   fileVO = new FileVO();
					   fileVO.setFileTypeId(list.get(0));
					   fileVO.setName(regionScopesDAO.getScopeById((long)index).get(0));
					   returnValue.add(fileVO);
				   }
				  
				   List<Long> list = fileGallaryDAO.getNewsCountByScope(candidateId,null,queryType); 
				   fileVO = new FileVO();
				   fileVO.setFileTypeId(list.get(0));
				   returnValue.add(fileVO);
				   return returnValue;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return returnValue;
				}
	       }
	public List<FileVO> getNewsByScope(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType)
	{
		if(log.isDebugEnabled())
			log.debug("Entered into getAllNewsdetails() of candidateDetailsService");

	     List<FileVO> retValue = new ArrayList<FileVO>();
		 try{
		     List<Long>	list = fileGallaryDAO.getNewsCountByScope(candidateId,scopeType,queryType);
			 List<Object[]> dataList = fileGallaryDAO.getNewsByScope(candidateId,scopeType,startIndex,maxResults,queryType,null,null,null,null);
			 retValue = convertDataToFileVO(dataList,list,candidateId,null,null);
			return retValue;
			}
			catch(Exception e){
				log.error("Exception rised in getAllNewsdetails method ",e);
				return retValue;
			}
	}
	
	public List<FileVO> getNewsByLanguage(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType,String language)
	{
		List<FileVO> retValue = new ArrayList<FileVO>();
		try
		{
			 List<Long> list = fileGallaryDAO.getTotalIndividualSources( candidateId,queryType ,null ,language,null,null);
			 List<Object[]> dataList = fileGallaryDAO.getNewsByScope(candidateId,scopeType,startIndex,maxResults,queryType,null,language,null,null);
			 retValue = convertDataToFileVO(dataList,list,candidateId,language,null);
			 
			return retValue;
		  
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return retValue;
		}
	}
	
	public List<FileVO> getNewsBySource(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType , String source)
	{
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{
			 List<Long> list = fileGallaryDAO.getTotalIndividualSources( candidateId,queryType ,source ,null,null,null);
			 List<Object[]> dataList = fileGallaryDAO.getNewsByScope(candidateId,scopeType,startIndex,maxResults,queryType,source,null,null,null);
			 returnValue = convertDataToFileVO(dataList,list,candidateId,null,source);
		  
		  return returnValue;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return returnValue;
		}
	}
	
	
	public Long getUserCandidateRelationCount(Long userId,Long candidateId)
	{
		log.debug("Entered into getUserCandidateRelationCount() method in Candidate Details Service()");
		try
		{
			List<Long> result = userCandidateRelationDAO.getUserCandidateRelationCount(userId,candidateId);
			return result.get(0);
		}
		catch(Exception e)
		{
			log.error("Exception encountered, Check log for Details - "+e);
			e.printStackTrace();
			return 0L;
		}
	}
	
	public ResultStatus subScribeEmailAlertForAUser(String emailId ,Long candidateId)
	{sasi
		 DateUtilService dateUtilService = new DateUtilService();
		 ResultStatus statusCode = new ResultStatus()  ;
		 try {
			  List<Object> candidateUpdatesEmails = candidateUpdatesEmailDAO.getCandidateUpdatesEmail(emailId, candidateId);
			   if(!(candidateUpdatesEmails.size() >0))
			   {
			     CandidateUpdatesEmail candidateUpdatesEmail = new CandidateUpdatesEmail();
				 
			     candidateUpdatesEmail.setEmail(emailId);
				 candidateUpdatesEmail.setCandidate(candidateDAO.get(candidateId));
				 candidateUpdatesEmail.setUnsubscribed("false");
				 candidateUpdatesEmail.setSubscribedDate(dateUtilService.getCurrentDateAndTime());
				 candidateUpdatesEmailDAO.save(candidateUpdatesEmail);
			   }
			   else
			   {
				   statusCode.setExceptionMsg("You have already subscribed for this candidate"); 
			   }
		     statusCode.setResultCode(ResultCodeMapper.SUCCESS);
		 
		 
		     return statusCode;
		
		 }catch(Exception e){
			 e.printStackTrace();
			 statusCode.setExceptionEncountered(e);
			 statusCode.setResultCode(ResultCodeMapper.FAILURE);
			 return statusCode;
		 }
	 }
	public List<FileVO> getOtherNews(Long candidateId,int startIndex,int maxResults,String queryType)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{
			 List<Object[]> results = fileGallaryDAO.getOtherNews(candidateId,startIndex,maxResults,queryType);
			 for(Object[] newsDetails: results){
				    FileVO fileVO = new FileVO();
				    fileVO.setFileId((Long)newsDetails[0]);
			    	fileVO.setName(newsDetails[1] != null ? newsDetails[1].toString() :"");		    			    	
			   	  	fileVO.setPath(newsDetails[2] != null ? newsDetails[2].toString() :"");
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? CommonStringUtils.removeSpecialCharsFromAString(newsDetails[3].toString()) :"");
			   	    fileVO.setFileDescription1(newsDetails[4] != null ? CommonStringUtils.removeSpecialCharsFromAString(newsDetails[4].toString()) :"");
			   	    fileVO.setSource(newsDetails[5] != null ? newsDetails[5].toString() :"");
			   	    fileVO.setLanguage(newsDetails[6] != null ? newsDetails[6].toString() :"");
			   	    fileVO.setFileDate(newsDetails[7] != null ? (sdf.format((Date)newsDetails[7])) :"");
			   	    fileVO.setCandidateId((Long)newsDetails[8]);
			   	    returnValue.add(fileVO);	  
			 }
		  
		  return returnValue;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return returnValue;
		}
	}

	*/
	
	public List<SelectOptionVO> getSource()
	{   
		List<SelectOptionVO> gallarySelectList = new ArrayList<SelectOptionVO>(0);
		
	try{
		List<Source> source = sourceDAO.getAll();
		SelectOptionVO selectOptionVO = null;
		for(Source result:source)
		{
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(result.getSourceId());
			selectOptionVO.setName(result.getSource());
			gallarySelectList.add(selectOptionVO);
		 }
		 
		return gallarySelectList;
		}
		catch(Exception e){
			e.printStackTrace();
			return gallarySelectList;
		}
	}
	
 public List<SelectOptionVO> getLanguage()
	{   
		List<SelectOptionVO> gallarySelectList = new ArrayList<SelectOptionVO>(0);
		
	try{
		List<SourceLanguage> sourceLanguage = sourceLanguageDAO.getAll();
		SelectOptionVO selectOptionVO = null;
		for(SourceLanguage result:sourceLanguage)
		{
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(result.getLanguageId());
			selectOptionVO.setName(result.getLanguage());
			gallarySelectList.add(selectOptionVO);
		 }
		 
		return gallarySelectList;
		}
		catch(Exception e){
			e.printStackTrace();
			return gallarySelectList;
		}
	}
 
 public List<SelectOptionVO> getCategory()
	{   
		List<SelectOptionVO> categorySelectList = new ArrayList<SelectOptionVO>(0);
		categorySelectList.add(new SelectOptionVO(0L,"Select"));
	try{
		List<Category> category = categoryDAO.getAll();
		
		List<Long> deletedCategoryIds = userNewsCategoryDAO.getDeletedCategoryIds();
		SelectOptionVO selectOptionVO = null;
		for(Category result:category)
		{
			if(!deletedCategoryIds.contains(result.getCategoryId()))
			{
			 selectOptionVO = new SelectOptionVO();
			 selectOptionVO.setId(result.getCategoryId());
			 selectOptionVO.setName(result.getCategoryType());
			 categorySelectList.add(selectOptionVO);
			}
		 }
		 
		return categorySelectList;
		}
		catch(Exception e){
			e.printStackTrace();
			return categorySelectList;
		}
	}
 public List<SelectOptionVO> getNewsImportance()
	{   
		List<SelectOptionVO> newsImportanceSelectList = new ArrayList<SelectOptionVO>(0);
		
	try{
		//List<NewsImportance> newsImportance = newsImportanceDAO.getAll();
		List<NewsImportance> newsImportance = newsImportanceDAO.getNewsImportanceList();
		
		SelectOptionVO selectOptionVO = null;
		for(NewsImportance result:newsImportance)
		{
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(result.getNewsImportanceId());
			selectOptionVO.setName(result.getImportance());
			newsImportanceSelectList.add(selectOptionVO);
		 }
		 
		return newsImportanceSelectList;
		}
		catch(Exception e){
			e.printStackTrace();
			return newsImportanceSelectList;
		}
	}
public List<SelectOptionVO> getCandidatesOfAUser(Long userId)
	{
		try{
			List<Object[]> list = userCandidateRelationDAO.getCandidatesOfAUser(userId);
			
			List<Long> candidateIds = new ArrayList<Long>();
			
			for(Object[] obj:list)
				candidateIds.add((Long)obj[0]);
			
			list = nominationDAO.getCandidatesForAParty(candidateIds);
			
			
			List<SelectOptionVO> cadidatesList = null;
			if(list != null && list.size() > 0)
			{
				cadidatesList = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : list)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1] != null ? params[1].toString() : "");
					cadidatesList.add(selectOptionVO);
				}
			}
			
			return cadidatesList;
		}catch(Exception e){
			return null;
		}
	}

public List<SelectOptionVO> getCandidatesOfAParty(Long partyId)
{
	
	try
	{
	List<Object[]> list = nominationDAO.getCandidatesParticipatedInAssemblyAndParlimentElections(partyId);
	
	List<Object[]> list1  = candidatePartyDAO.getCandidatesOfAParty(partyId);
	
	List<SelectOptionVO> cadidatesList = null;
	if(list != null && list.size() > 0)
	{
		cadidatesList = new ArrayList<SelectOptionVO>(0);
		
		SelectOptionVO selectOptionVO = null;
		for(Object[] params : list)
		{
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId((Long)params[0]);
			
			if(params[1] != null)
			if(params[1].toString().substring(0, 1).equalsIgnoreCase("."))
				  selectOptionVO.setName(params[1].toString().substring(1));
			else
				  selectOptionVO.setName(params[1].toString());
			
			cadidatesList.add(selectOptionVO);
		}
	}
	
	if(list1 != null && list1.size() > 0)
	{
		SelectOptionVO selectOptionVO = null;
		for(Object[] params : list1)
		{
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId((Long)params[0]);
			selectOptionVO.setName(params[1] != null ? params[1].toString() : "");
			cadidatesList.add(selectOptionVO);
		}
	}
	
	Collections.sort(cadidatesList);
	
	
	
	return cadidatesList;
	}catch(Exception e)
	{
		e.printStackTrace();
		return null;
		
	}
	
}


/*
 public	FileVO getCandidatesGallaryDescForUpdate(Long gallaryId , Long candidateId)
 {
	 FileVO fileVO = new FileVO(); 
	 try {
		  List<Object[]> result = gallaryDAO.getCandidatesGallaryDescForUpdate(gallaryId, candidateId);
		 for (Object[] objects : result) {
			fileVO.setGallaryId((Long)objects[0]);
			fileVO.setGallaryName(objects[1].toString());
			fileVO.setGallaryDescription(objects[2].toString());
			fileVO.setFileName1(objects[3].toString());
		 }
		 return fileVO;
	    } catch (Exception e) {
	    	e.printStackTrace();
		return fileVO;
	    }
}
 
 public	FileVO getPhotoUploadDescForUpdate(Long gallaryId , Long fileId)
 {
	 FileVO fileVO = new FileVO(); 
	 try {
		  List<Object[]> result = fileGallaryDAO.getPhotoAndFileDescForUpdate(gallaryId, fileId);
		 for (Object[] objects : result) {
			fileVO.setFileId((Long)objects[0]);
			fileVO.setGallaryId((Long)objects[1]);
			fileVO.setGallaryName(objects[2]!=null?objects[2].toString():"");
			fileVO.setFileTitle1(objects[3]!=null?CommonStringUtils.removeSpecialCharsFromAString(objects[3].toString()):"");
			fileVO.setFileDescription1(objects[4]!=null?CommonStringUtils.removeSpecialCharsFromAString(objects[4].toString()):"");
			fileVO.setFilePath1(objects[5]!=null?objects[5].toString().trim():"");
			fileVO.setFile(objects[6].toString());
			fileVO.setFileTypeId((Long)objects[7]);
		}
		 
	    } catch (Exception e) {
	    	e.printStackTrace();
		return null;
	    }
	 return fileVO;
 }
 public ResultStatus updateIndividualPhoto(FileVO fileVO)
 {   
	 ResultStatus statusCode = new ResultStatus()  ;
	try{
	   File file =  fileDAO.get(fileVO.getFileId());
	   file.setFileTitle(CommonStringUtils.removeSpecialCharsFromAString(fileVO.getFileTitle1()));
	   file.setFileDescription(CommonStringUtils.removeSpecialCharsFromAString(fileVO.getDescription()));
	   fileDAO.save(file);
	   FileGallary fileGallary = fileGallaryDAO.get(fileVO.getFileTypeId());
	   Gallary gallary = gallaryDAO.get(fileVO.getGallaryId());
	   fileGallary.setGallary(gallary);
	   fileGallary.setIsPrivate(fileVO.getFileType());
	   fileGallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());

	     statusCode.setResultCode(ResultCodeMapper.SUCCESS);
	 
	     return statusCode;
	
	 }catch(Exception e){
		 e.printStackTrace();
		 statusCode.setExceptionEncountered(e);
		 statusCode.setResultCode(ResultCodeMapper.FAILURE);
		 return statusCode;
	 }
 }
 
 public Map<String, List<FileVO>> getPhotosNewsVideosUpdateForACandidate(int startIndex,int maxResults)
 {
	 try{
	 Map<String ,List<FileVO>> resultMap = new HashMap<String,List<FileVO>>();
	 List<FileGallary> photoGallaryresultList = new ArrayList<FileGallary>();
	 List<FileGallary> newsGallaryresultList = new ArrayList<FileGallary>();
	 List<FileGallary> singleNewsGallaryresultList = null;
	 List<FileGallary> singleVodeosGallaryresultList = null;
	 List<FileGallary> videoGallaryresultList = new ArrayList<FileGallary>();
	 List<FileVO> resultList = new ArrayList<FileVO>();
	 List<FileVO> vedioresultList = new ArrayList<FileVO>();
	 List<FileVO> newsresultList = new ArrayList<FileVO>();
	 
	 
	 List<Long> latestgalIds =  fileGallaryDAO.getRecentlyUploadedGallaries(startIndex, maxResults);
	 if(latestgalIds != null)
	 {
		for(Long value :latestgalIds)
		{
			photoGallaryresultList = fileGallaryDAO.getStartingRecordInGallaries(value);
				
			List<FileVO> result = setToFileVO(photoGallaryresultList,"Photos");
			
			if(!result.isEmpty())
			resultList.add(result.get(0));		
		}
		resultMap.put("photogallary", resultList);
	 }
	 
	 List<FileGallary> latestPhotoGalleryList  = fileGallaryDAO.getRecentlyUploadedGallaries(startIndex, 400);
	 
	 Map<Long,Long> photoGallaryIds = new HashMap<Long,Long>();
	 Map<Long,Long> photoFileIds = new HashMap<Long,Long>();
	 Long photoFileId = null;
	 Long photoGallaryId= null;
	 int photoCount = 0;
	 
	 for(FileGallary fileGallary:latestPhotoGalleryList){
		 photoFileId = fileGallary.getFile().getFileId();
		 photoGallaryId = fileGallary.getGallary().getGallaryId();
		 if(photoFileId != null && photoFileIds.get(photoFileId) == null){
			 if(photoGallaryId != null && photoGallaryIds.get(photoGallaryId) == null){
				 
				 photoFileIds.put(photoFileId,photoFileId);
				 photoGallaryIds.put(photoGallaryId,photoGallaryId);
				 singleVodeosGallaryresultList = new ArrayList<FileGallary>();				 
				 singleVodeosGallaryresultList.add(fileGallary);
				 List<FileVO> photosList =setToFileVO(singleVodeosGallaryresultList , "Photos");
				 if(!photosList.isEmpty()){
					 resultList.add(photosList.get(0));
					 photoCount =photoCount+1;
				 }
			 }
		 }
		 if(photoCount >= maxResults){
				break;
			}
	 }
	 
	 resultMap.put("photogallary", resultList);
	 
	 String queryStr2 = "where model.gallary.contentType.contentType = 'Video Gallary'";
		 List<Long> videosList = fileGallaryDAO.getRecentlyUploadedFileIds(startIndex, maxResults, queryStr2);
		 videoGallaryresultList = fileGallaryDAO.getFileGallaryByFileIdsList(videosList);
		 List<Long> latestVedioGalIds = fileGallaryDAO.getRecentlyUploadedVedioGallaryIds(startIndex, maxResults, queryStr2);
		 if(latestVedioGalIds != null)
		 {
			 for(Long vediogalId : latestVedioGalIds)
			 {
				 videoGallaryresultList=  fileGallaryDAO.getStartingRecordInGallaries(vediogalId);
				 List<FileVO> vedioresult = setToFileVO(videoGallaryresultList,"Videos");
				 if(!vedioresult.isEmpty())
				vedioresultList.add(vedioresult.get(0));		
				 
			 	}
			 resultMap.put("VideoGallary", vedioresultList);
		 }
	 
	 
	 List<FileGallary> latestVedioGalList = fileGallaryDAO.getRecentlyUploadedVedioGallaryIds(startIndex, 400, queryStr2);
	 Map<Long,Long> videoGallaryIds = new HashMap<Long,Long>();
	 Map<Long,Long> videoFileIds = new HashMap<Long,Long>();
	 Long videoFileId = null;
	 Long vodeoGallaryId= null;
	 int videoCount = 0;
	 
	 for(FileGallary fileGallary:latestVedioGalList){
		 videoFileId = fileGallary.getFile().getFileId();
		 vodeoGallaryId = fileGallary.getGallary().getGallaryId();
		 if(videoFileId != null && videoFileIds.get(videoFileId) == null){
			 if(vodeoGallaryId != null && videoGallaryIds.get(vodeoGallaryId) == null){
				 
				 videoFileIds.put(videoFileId,videoFileId);
				 videoGallaryIds.put(vodeoGallaryId,vodeoGallaryId);
				 singleVodeosGallaryresultList = new ArrayList<FileGallary>();				 
				 singleVodeosGallaryresultList.add(fileGallary);
				 List<FileVO> vodeosList =setToFileVO(singleVodeosGallaryresultList , "Videos");
				 if(!vodeosList.isEmpty()){
					 vedioresultList.add(vodeosList.get(0));
					 videoCount =videoCount+1;
				 }
			 }
		 }
		 if(videoCount >= maxResults){
				break;
			}
	 }
	 
	 resultMap.put("VideoGallary", vedioresultList);
	 
	
	// photoGallaryresultList=fileGallaryDAO.getStartingRecordInGallaries(latestgalIds);
 	 List<Long> photosList = fileGallaryDAO.getRecentlyUploadedPhotoIds(startIndex, maxResults);
 	   photoGallaryresultList = fileGallaryDAO.getFileGallaryByFileIdsList(photosList);
	   // String queryStr1 = "where model.gallary.contentType.contentType = 'News Gallary'";
	   //List<Long> newsList = fileGallaryDAO.getRecentlyUploadedFileIds(startIndex, maxResults, queryStr1);
	   //newsGallaryresultList = fileGallaryDAO.getFileGallaryByFileIdsListForNews(newsList);
		 
		 
		 String queryStr3 = "where model.gallary.contentType.contentType = 'News Gallary' and  model.file.regionScopes.regionScopesId < 4 ";
		// List<Long> newsGalIds = fileGallaryDAO.getRecentlyUploadedNewsGallaryIds(startIndex, 150, queryStr3);
		 Map<Long,Long> gallaryIds = new HashMap<Long,Long>();
		 Map<Long,Long> fileIds = new HashMap<Long,Long>();
		 Long fileId = null;
		 Long gallaryId = null;
		 int count = 0;
		 if(newsGalIds != null)
		 {
			 for(Long newsgalIds : newsGalIds)
			 {
				if(gallaryIds.get(newsgalIds) == null){
					
					gallaryIds.put(newsgalIds,newsgalIds);
				 newsGallaryresultList =fileGallaryDAO.getStartingRecordInNewsGallaries(newsgalIds);
				if(!newsGallaryresultList.isEmpty()){
					Long fileId = newsGallaryresultList.get(0).getFile().getFileId();
				if(fileIds.get(fileId) == null){
					count =count+1;
					fileIds.put(fileId,fileId);
					singleNewsGallaryresultList = new ArrayList<FileGallary>();
					singleNewsGallaryresultList.add(newsGallaryresultList.get(0));
				   List<FileVO> newsList =setToFileVO(singleNewsGallaryresultList);
				   if(!newsList.isEmpty())
					newsresultList.add(newsList.get(0));	
				   }
				  }
				}
				if(count >= maxResults){
					break;
				}
			 }
		  }
			 List<FileGallary>  fileGallaryList =  fileGallaryDAO.getRecentlyUploadedNewsFileIds(startIndex, 400, queryStr3);
			 for(FileGallary fileGallary:fileGallaryList){
				 fileId = fileGallary.getFile().getFileId();
				 gallaryId = fileGallary.getGallary().getGallaryId();
				 if(fileId != null && fileIds.get(fileId) == null){
					 if(gallaryId != null && gallaryIds.get(gallaryId) == null){
						 
						 fileIds.put(fileId,fileId);
						 gallaryIds.put(gallaryId,gallaryId);
						 singleNewsGallaryresultList = new ArrayList<FileGallary>();
						 singleNewsGallaryresultList.add(fileGallary);
						 List<FileVO> newsList =setToFileVO(singleNewsGallaryresultList , "News");
						 if(!newsList.isEmpty()){
							newsresultList.add(newsList.get(0));
							count =count+1;
						 }
					 }
				 }
				 if(count >= maxResults){
						break;
					}
			 }
			 resultMap.put("NewsGallary", newsresultList);
		 
	 newsGallaryresultList = fileGallaryDAO.getHomePageNewsDetails(startIndex, maxResults);
	 resultList = setToFileVO(newsGallaryresultList);
	 resultMap.put("NewsGallary", resultList);
	 
	
	
	
		 
     return resultMap;
	 }catch (Exception e) {
		 log.error("Exception occured - "+e);
		 return null;
	 }
}
 
 
 public List<FileVO> setToFileVO(List<FileGallary> result ,String type)
 {
	List<FileVO> fileVOs = new ArrayList<FileVO>();
	FileVO fileVO = new FileVO();
	 try{
	String str_date=null;
	String photogal=null;
	
	if(result != null)
	{
		  for(int i=0; i<result.size(); i++)
		  {
			 fileVO = new FileVO();
			 if(result.get(i).getGallary() != null)
			 {
			 if(result.get(i).getGallary() != null && result.get(i).getGallary().getCandidate() != null)
			 {
				 fileVO.setCandidateName(WordUtils.capitalize(result.get(i).getGallary().getCandidate().getLastname().toLowerCase()));
				 fileVO.setFileType("Candidate");
				 fileVO.setCandidateId(result.get(i).getGallary().getCandidate().getCandidateId());
				 
                   if(type.equalsIgnoreCase("News")){					 
					 fileVO.setDisplayImageName(result.get(i).getFile().getFileName());
					 fileVO.setDisplayImagePath(result.get(i).getFile().getFilePath());
					 fileVO.setImagePathInUpperCase(result.get(i).getGallary().getCandidate().getLastname().toUpperCase());
				}
			 }
			 else
			 {
				List<Party> party = partyGalleryDAO.getPartyByGalleryId(result.get(i).getGallary().getGallaryId());
				
				if(party != null && party.size() > 0)
				{
					fileVO.setCandidateName(party.get(0).getShortName()+" Party");
					fileVO.setFileType("Party");
					fileVO.setCandidateId(party.get(0).getPartyId());
					
					if(type.equalsIgnoreCase("News")){						 
						 fileVO.setDisplayImageName(result.get(0).getFile().getFileName());
						 fileVO.setDisplayImagePath(result.get(0).getFile().getFilePath());
						 fileVO.setImagePathInUpperCase(party.get(0).getPartyFlag());
					 }
					
				}
				else
				{
					List<SpecialPage> specialPage = specialPageGalleryDAO.getSpecialPageByGalleryId(result.get(i).getGallary().getGallaryId());
					
					if(specialPage != null && specialPage.size() > 0)
					{
						fileVO.setCandidateName(specialPage.get(0).getHeading()+" Page");
						fileVO.setFileType("Special Page");
						fileVO.setCandidateId(specialPage.get(0).getSpecialPageId());						
						
						if(type.equalsIgnoreCase("News")){							 
							 fileVO.setDisplayImageName(result.get(0).getFile().getFileName());
							 fileVO.setDisplayImagePath(result.get(0).getFile().getFilePath());
							 fileVO.setImagePathInUpperCase(specialPage.get(0).getProfileImgPath());
						 }
					}
				}
			 }
			 
			 fileVO.setContentType(result.get(i).getGallary().getContentType().getContentType());
			 fileVO.setContentId(result.get(i).getFileGallaryId());
			 fileVO.setFileId(result.get(i).getFile().getFileId());
			 fileVO.setDescription(CommonStringUtils.removeSpecialCharsFromAString(result.get(i).getFile().getFileDescription()));
			 Set<FileSourceLanguage> fileSourceLanguageSet = result.get(i).getFile().getFileSourceLanguage();
			 String filePath = null;
			 String source = null;
			 String language = null;
			 List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
			 Collections.sort(fileSourceLanguageList,fileSourceLanguageSort);
			 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList)
			 {
				 source = fileSourceLanguage.getSource()!= null?fileSourceLanguage.getSource().getSource():"";
				 language = fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():"";
				 Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
				 List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
				  Collections.sort(filePathsList,filePathsSort);
				 
				 for(FilePaths singleFilePath : filePathsList)
				 {
					 filePath = singleFilePath.getFilePath(); 
					 break;
				 }
				 if(filePath != null)
					 break;
			 }
			 fileVO.setPathOfFile(filePath);
			 fileVO.setFileTitle1(CommonStringUtils.removeSpecialCharsFromAString(result.get(i).getFile().getFileTitle()));
			 fileVO.setGallaryName(result.get(i).getGallary().getName());
			 fileVO.setGallaryUpdatedDate(result.get(i).getGallary().getUpdateddate().toString());
			 
			 if(result.get(i).getGallary().getContentType().getContentType().equalsIgnoreCase(IConstants.VIDEO_GALLARY))
				 fileVO.setPathOfFile(filePath);
			 
			 if(result.get(i).getFile().getFileDate()!= null)
				 fileVO.setFileDate(result.get(i).getFile().getFileDate().toString());
			 if(result.get(i).getGallary().getContentType().getContentType().equalsIgnoreCase(IConstants.PHOTO_GALLARY))
			 	fileVO.setPathOfFile(filePath);
			 if(result.get(i).getFile().getFileDate() != null)
			 {
			str_date = result.get(i).getFile().getFileDate().toString();
			photogal = str_date.substring(8,10)+'-'+str_date.substring(5,7)+'-'+str_date.substring(0,4);
			fileVO.setFileDate(photogal);
			 }
				
			 
			 if(source != null)
				 fileVO.setSource(source);
			 
			 if(language != null)
				 fileVO.setLanguage(language);
			 
			 if(!checkForFileExistance(fileVOs,fileVO.getFileId()))
				 fileVOs.add(fileVO);
		   }
		  }
		  
	}
	 
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	return fileVOs;
	
 }
 
 public boolean checkForFileExistance(List<FileVO> filesList,Long fileId)
 {
	 try{
		 
		 if(filesList == null || filesList.size() == 0)
			 return false;
		 
		 for(FileVO fileVO : filesList)
			 if(fileVO.getFileId().equals(fileId))
				return true;
		 return false;
		 
	 }catch (Exception e) {
		 log.error(" Exception Occured in checkForFileExistance() Method - "+e);
		 return false;
	 }
 }
 
 public SelectOptionVO assignAndReturnCandidate(Long userId,Long candidateId)
 {
	 SelectOptionVO selectOptionVO = null;
	 if(saveUserCandidateRelation(userId, candidateId).getResultCode() == ResultCodeMapper.SUCCESS)
	 {
		 selectOptionVO = new SelectOptionVO();
		 selectOptionVO.setId(candidateId);
		 selectOptionVO.setName(candidateDAO.getCandidateNameByCandidateId(candidateId).toString());
	 }
	 return selectOptionVO;
 }
 
 public boolean checkForCandidateExistence(List<SelectOptionVO> candidatesList,Long candidateId)
 {
	 if(candidatesList != null && candidatesList.size() > 0)
	 for(SelectOptionVO selectOptionVO :candidatesList)
		 if(selectOptionVO.getId().equals(candidateId))
			 return true;
	 return false;
 }



 @SuppressWarnings("unchecked")
 public List<FileVO> getNewsGalleryByUserIdFromUserGallery(Long userId)
 {
	List<FileVO> fileVOList = new ArrayList<FileVO>();
	log.debug("Entered into getNewsGalleryByUserIdFromUserGallery() Method");
	try{
	GallaryVO gallaryVO = new GallaryVO();
	FileVO fileVO = new FileVO();
	List gallaryIds = new ArrayList();

	List<Object[]> gallaries = userGallaryDAO.getAllNewsGallaryBasedOnUserId(userId);
	
	if(gallaries != null && gallaries.size() > 0)
	{
		for(Object[] params : gallaries){
			gallaryVO.setGallaryId(new Long(params[0].toString()));
			gallaryVO.setGallaryName(params[1].toString());
			gallaryIds.add(params[0]);
		}
		List<Object[]> files = fileGallaryDAO.getNewsByGalleryId(gallaryIds);
		for(Object[] filesObj : files){
			fileVO = new FileVO();
			fileVO.setFileId(new Long(filesObj[0].toString()));
			fileVO.setFileName1(filesObj[1].toString());
			fileVO.setPathOfFile(filesObj[2].toString());
			fileVO.setTitle(filesObj[3].toString());
			fileVO.setDescription(filesObj[4].toString());
			fileVO.setFileType(filesObj[5].toString());
			fileVO.setScope(filesObj[6].toString());
			fileVO.setFileDate(filesObj[7].toString());
			fileVO.setSource(filesObj[8].toString());
			fileVO.setLanguage(filesObj[9].toString());
			fileVO.setLocationScope((Long)filesObj[10]);
			
			if(filesObj[10] != null)
				fileVO.setLocationScopeValue(getLocationDetails((Long)filesObj[10],(Long)filesObj[11]));
			fileVOList.add(fileVO);
		}
	}
	return fileVOList;
	}catch (Exception e) {
		log.error("Exception occured in getNewsGalleryByUserIdFromUserGallery() Method, exception is - "+e);
		return fileVOList;
	}
}
 public String getLocationBasedOnScopeId(Long scopeId,Long locationId){
	 String locationName = "";
	 if(scopeId==2){
		 locationName = stateDAO.get(locationId).getStateName();
	 }
	if(scopeId == 3)
		locationName = districtDAO.get(locationId).getDistrictName();
	if(scopeId == 4)
		locationName = constituencyDAO.get(locationId).getName();
	if(scopeId == 5)
		locationName = tehsilDAO.get(locationId).getTehsilName();
	
	return locationName;
	 
 }
 
 public List<FileVO> getNewsByCategory(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType , String source)
	{
		
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{    List<Long> list = fileGallaryDAO.getTotalIndividualSources( candidateId,queryType ,null ,null,source,null);
			 List<Object[]> dataList = fileGallaryDAO.getNewsByScope(candidateId,scopeType,startIndex,maxResults,queryType,null,null,source,null);
			 returnValue = convertDataToFileVO(dataList,list,candidateId,null,null);
		  return returnValue;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return returnValue;
		}
	}
 public List<FileVO> getNewsByNewsImportance(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType , String newsImportance)
	{
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{    List<Long> list = fileGallaryDAO.getTotalIndividualSources( candidateId,queryType ,null ,null,null,newsImportance);
			 List<Object[]> dataList = fileGallaryDAO.getNewsByScope(candidateId,scopeType,startIndex,maxResults,queryType,null,null,null,newsImportance);
			 returnValue = convertDataToFileVO(dataList,list,candidateId,null,null);
		  
		  return returnValue;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return returnValue;
		}
	}*/
 public String getLocationDetails(Long scope,Long locationValue){
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
	    	return tehsilDAO.get(locationValue).getTehsilName();
	    }
	    else if(scope == 6L && locationValue != null && locationValue > 0)
	    {
	    	return hamletDAO.get(locationValue).getHamletName();
	    }
	    else if(scope == 7L && locationValue != null && locationValue > 0)
	    {
	    	return localElectionBodyDAO.get(locationValue).getName();
	    }
	    else if(scope == 8L && locationValue != null && locationValue > 0)
	    {
	    	return constituencyDAO.get(locationValue).getName();
	    }
	    else if(scope == 9L && locationValue != null && locationValue > 0)
	    {
	    	return boothDAO.get(locationValue).getPartNo();
	    }
	    else  return " ";
	 }
	    return " ";
   }
 /*public List<CandidateCommentsVO> getMessages(String fromDate, String toDate,String selectstatus,String decidestatus)
 {
	 List<CandidateCommentsVO> candidateComments = new ArrayList<CandidateCommentsVO>();
	 if(log.isDebugEnabled())
		 log.debug("getMessages()method ......");
		try{
			
			List comments;
			Date firstDate = DateService.convertStringToDate(fromDate, IConstants.DATE_PATTERN_YYYY_MM_DD);
			Date secondDate = DateService.convertStringToDate(toDate, IConstants.DATE_PATTERN_YYYY_MM_DD);
			if(decidestatus.equals(IConstants.CANDIDATE_MEG))
			{
			     comments = messageToCandidateDAO.getAllOpenedMessages(firstDate, secondDate,selectstatus);
			     candidateComments = commentsDetailsFromList(comments);
			}
			if(decidestatus.equals(IConstants.PARTY_MEG))
			{
				comments = messageToPartyDAO.getAllPartyMessages(firstDate,secondDate,selectstatus);
				candidateComments = commentsDetailsFromPartyList(comments);
			} 			
		}catch(Exception e){
			log.error("Exception in getMessages in candidate details service",e);
		}
		return candidateComments;
 }
 public List<CandidateCommentsVO> commentsDetailsFromList(List comments)
	{
		List<CandidateCommentsVO> commentsList = null;
		 if(log.isDebugEnabled())
			 log.debug("commentsDetailsFromList()method ......");
		
		if(comments != null || comments.size() > 0)
		{
			commentsList = new ArrayList<CandidateCommentsVO>();
			for (int i = 0; i < comments.size(); i++)
			{
				CandidateCommentsVO comment = new CandidateCommentsVO();
				Object[] params = (Object[])comments.get(i);
				comment.setCandidate(params[0].toString());
				comment.setPostedBY(params[1].toString());
				comment.setMessage(params[2].toString());
				comment.setConstituency(params[3].toString());
				if(params[4] != null && params[4].toString().equalsIgnoreCase(IConstants.TRUE))
					comment.setStatus(IConstants.APPROVED);
				if(params[4] != null && params[4].toString().equalsIgnoreCase(IConstants.FALSE))
					comment.setStatus(IConstants.NEW);
				if(params[4] == null)
					comment.setStatus(IConstants.REJECTED);
				comment.setMessageToCandidateId((Long)params[5]);
				comment.setCandidateId((Long)params[6]);
				comment.setConsituencyId((Long)params[7]);
				if(params[8]!=null && params[8].toString().equalsIgnoreCase(IConstants.FALSE) )
				   comment.setVisibility(IConstants.PUBLIC);
				if(params[8]!=null && params[8].toString().equalsIgnoreCase(IConstants.TRUE) )
					   comment.setVisibility(IConstants.PRIVATE);
					
				
				commentsList.add(comment);
				
			}
		}			
		
		return commentsList;
	}
 public ResultStatus controlMessages(List<CandidateCommentsVO> VO,String actionType)
 {
		String isApproved = IConstants.FALSE;
		ResultStatus resultStatus = new ResultStatus();
		
		try {
			if(log.isDebugEnabled())
				log.debug("Enterd into controlMessages in candidate details service");
			
			if(actionType.equalsIgnoreCase(IConstants.APPROVED))
				isApproved = IConstants.TRUE;
			
			else if(actionType.equalsIgnoreCase(IConstants.REJECTED))
				//isApproved = IConstants.FALSE;
				isApproved = null;
			
           for(int i=0; i<VO.size();i++)
           {
        	   CandidateCommentsVO ccv = (CandidateCommentsVO)VO.get(i);
        	   Long id = ccv.getMessageToCandidateId();
        	   String message = ccv.getMessage();
        	   messageToCandidateDAO.controlMessages(id, message, isApproved);
        	   
        	   
           }
           resultStatus.setResultState(1l);
			
		} catch (Exception e) {
			if(log.isDebugEnabled())
				log.error("Exception in controlMessages in candidate details service",e);
			 resultStatus.setResultState(0l);
			 resultStatus.setExceptionEncountered(e);
		}
		return resultStatus;
 }
 
		public List<CandidateCommentsVO> getUserMessages(Long candidateId,int startIndex,int resultsCount)
		{
			List<CandidateCommentsVO> userlist = new ArrayList<CandidateCommentsVO>(0);

		   try{
			   if(log.isDebugEnabled())
				   log.debug("entered into getUserMessages()in CandidateDetailsService");
			   List<Long> messCount = messageToCandidateDAO.getUserMessagesCount(candidateId);
			   List<Object[]> list= messageToCandidateDAO.getUserMessages(candidateId,startIndex,resultsCount);
			   
			   if(list!=null && list.size()>0)
			   {                   
                     CandidateCommentsVO candidateCommentsVO = null;
                     for(Object[] params:list)
                     {
                    	 candidateCommentsVO = new CandidateCommentsVO();
                    	 
                    	 candidateCommentsVO.setUserName(params[0].toString());
                    	 candidateCommentsVO.setMessage(params[1].toString());
                    	 candidateCommentsVO.setConstituency(params[2].toString());
                    	 candidateCommentsVO.setTime(params[3].toString().substring(0,19));
                    	 candidateCommentsVO.setConsituencyId((Long)params[4]);
                    	 candidateCommentsVO.setTotalResultsCount(messCount.get(0));
                    	 userlist.add(candidateCommentsVO);
                     }
                     
			   }			   
			   
		   }catch(Exception e){
			   log.error("Exception in getUserMessages()of CandidateDetailsService",e);
			   
			   
		   }
		   return userlist;
		}
		*//**
		 * 
		 * @param electionId
		 * @return selectOptionList
		 * @author Swathi
		 *//*
		public List<SelectOptionVO> getCandidatesBasedOnSelection(String candidateName,Long partyId,Long electionId){
			List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>(0);
			SelectOptionVO optionVO = new SelectOptionVO();
			List<Object[]> candidatesList = nominationDAO.getCandidatesBasedOnElectionId(candidateName,partyId,electionId);
			try{
				if(candidatesList!=null){
					for(Object[] params : candidatesList){
				optionVO = new SelectOptionVO();
				optionVO.setId(new Long(params[0].toString()));
				optionVO.setName(WordUtils.capitalize(params[1].toString().toLowerCase()));
				selectOptionList.add(optionVO);
			}
		}
			 return selectOptionList;
		}catch(Exception e){
			e.printStackTrace();
			return selectOptionList;
		}
			
	}
		public List<FileVO> getSpecialPagePhotoGallaryDetail(Long specialPageId,int firstRecord,int maxRecord,String type){
			
			 List<FileVO> retValue = new ArrayList<FileVO>();
			 
		 try{
			 if(log.isDebugEnabled())
				   log.debug("entered into getSpecialPagePhotoGallaryDetail()in CandidateDetailsService");
			  List<Object[]> results = specialPageGalleryDAO.getSpecialPageGallaryId(specialPageId,firstRecord,maxRecord,type);
			if(results !=null && results.size()>0)
			{
			  for(Object[] gallary: results)
			  {
				FileVO fileVO = new FileVO();
			    List<File> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
			       for(File file: record){
			    	fileVO.setFileId(file.getFileId());
			    	
			    	Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
			    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
			    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
			    		List<String> filePaths = new ArrayList<String>();
			    		for(FilePaths filePath : filePathsSet){
			    			
			    			filePaths.add(filePath.getFilePath() != null?filePath.getFilePath().trim():null);
			    			
			    				
			    		}
			    		fileVO.setFilePath(filePaths);
			    	}
			    	fileVO.setTitle(file.getFileTitle() != null ?file.getFileTitle() :"");
			    	
			        }
			    fileVO.setGallaryId((Long)gallary[0]);
			    fileVO.setSizeOfGallary((long)(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()));
			    fileVO.setGallaryName(gallary[1] != null ? WordUtils.capitalize(gallary[1].toString()) :"");
			    fileVO.setGallaryDescription(gallary[2] != null ? WordUtils.capitalize(gallary[2].toString()) :"");
			    fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString() :"");
			    fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString() :"");
			    retValue.add(fileVO);	  
			  }
			}
			return retValue;
		 }
		 catch(Exception e)
		 {
			 log.error("Exception in getSpecialPagePhotoGallaryDetail()of CandidateDetailsService",e);
			
			 return retValue;
		 }
	  }
	  
	  
	 	
		public	FileVO getSpecialPageGallaryDescForUpdate(Long gallaryId , Long specialPageId)
		 {
			 FileVO fileVO = new FileVO(); 
			 try {
				 if(log.isDebugEnabled())
					   log.debug("entered into getSpecialPageGallaryDescForUpdate()()in CandidateDetailsService");
				  List<Object[]> result = gallaryDAO.getSpecialPageGallaryDescForUpdate(gallaryId);
				 for (Object[] objects : result) {
					fileVO.setGallaryId((Long)objects[0]);
					fileVO.setGallaryName(objects[1].toString());
					fileVO.setGallaryDescription(objects[2].toString());
					fileVO.setFileName1(objects[3].toString());
				 }
				 return fileVO;
			    } catch (Exception e) {
			    	log.error("Exception in getSpecialPageGallaryDescForUpdate()of CandidateDetailsService",e);
				  return fileVO;
			    }
		}
	
	public FileVO getContentDetails(Long contentId)
	{
		try{
			FileVO fileVO = new FileVO();
			FileGallary fileGallary = fileGallaryDAO.get(contentId);
			
			if(fileGallary != null)
			{
				fileVO = new FileVO();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				fileVO.setTitle(fileGallary.getFile().getFileTitle());
				fileVO.setDescription(fileGallary.getFile().getFileDescription());
				fileVO.setContentType(fileGallary.getGallary().getContentType().getContentType());
				fileVO.setPath(fileGallary.getFile().getFilePath());
				fileVO.setFileDate(fileGallary.getFile().getFileDate() == null ? null :
					sdf.format(fileGallary.getFile().getFileDate()));
				fileVO.setSource(fileGallary.getFile().getSourceObj() == null ? null :
					fileGallary.getFile().getSourceObj().getSource());
				
			}
			return fileVO;
		}catch (Exception e) {
			log.error("Exception Occured in getContentDetails() Method");
			return null;
		}
	}
		
	public String getCandidateGallaryVisibility(Long gallaryId)
		{
			
			List<Object> result= gallaryDAO.getDetailsOfVisibility(gallaryId);
		try
		{
			log.debug("entered into getCandidateGallaryVisibility()in CandidateDetailsService");
			
			return result.get(0).toString();
		}
			
		catch(Exception e)
		{
			log.error("Error occured getCandidateGallaryVisibility of candidateDetailService");
			return result.get(0).toString();
		}
			
		}
	
	public List<CandidateCommentsVO> commentsDetailsFromPartyList(List comments)
	{
		List<CandidateCommentsVO> commentsList = null;
		 if(log.isDebugEnabled())
			 log.debug("commentsDetailsFromList()method ......");
		
		if(comments != null || comments.size() > 0)
		{
			commentsList = new ArrayList<CandidateCommentsVO>();
			for (int i = 0; i < comments.size(); i++)
			{
				CandidateCommentsVO comment = new CandidateCommentsVO();
				Object[] params = (Object[])comments.get(i);
				comment.setShortName(params[0].toString());
				comment.setPostedBY(params[1].toString());
				comment.setMessage(params[2].toString());
				comment.setConstituency(params[3].toString());
				if(params[4] != null && params[4].toString().equalsIgnoreCase(IConstants.TRUE))
					comment.setStatus(IConstants.APPROVED);
				if(params[4] != null && params[4].toString().equalsIgnoreCase(IConstants.FALSE))
					comment.setStatus(IConstants.NEW);
				if(params[4] == null)
					comment.setStatus(IConstants.REJECTED);
				comment.setMessageToPartyId((Long)params[5]);
				comment.setPartyId((Long)params[6]);
				comment.setConsituencyId((Long)params[7]);
				if(params[8]!=null && params[8].toString().equalsIgnoreCase(IConstants.FALSE) )
				   comment.setVisibility(IConstants.PUBLIC);
				if(params[8]!=null && params[8].toString().equalsIgnoreCase(IConstants.TRUE) )
					   comment.setVisibility(IConstants.PRIVATE);
					
				
				commentsList.add(comment);
				
			}
		}			
		
		return commentsList;
	}
    public ResultStatus adminModifiedMessages(List<CandidateCommentsVO> upMessageVO,String actionType)
    {
			String isApproved = IConstants.FALSE;
			ResultStatus resultStatus = new ResultStatus();
			
			try {
				if(log.isDebugEnabled())
					log.debug("Enterd into adminModifiedMessages in candidate details service");
				
				if(actionType.equalsIgnoreCase(IConstants.APPROVED))
					isApproved = IConstants.TRUE;
				
				else if(actionType.equalsIgnoreCase(IConstants.REJECTED))
					//isApproved = IConstants.FALSE;
					isApproved = null;
				
	           for(int i=0; i<upMessageVO.size();i++)
	           {
	        	   CandidateCommentsVO ccv = (CandidateCommentsVO)upMessageVO.get(i);
	        	   Long id = ccv.getMessageToCandidateId();
	        	   String message = ccv.getMessage();
	        	   messageToPartyDAO.adminModifiedMessages(id, message, isApproved);
	        	   
	        	   
	           }
	           resultStatus.setResultState(1l);
				
			} catch (Exception e) {
				if(log.isDebugEnabled())
					log.error("Exception in adminModifiedMessages in candidate details service",e);
				 resultStatus.setResultState(0l);
				 resultStatus.setExceptionEncountered(e);
			}
			return resultStatus;
	  }
	 
    public ElectionGoverningBodyVO getElectionDetailsForMinister(Long electionId)
    {
    	log.debug("Entered into getElectionDetailsForMinister() of candidateDetailService");
    	ElectionGoverningBodyVO electionGoverningBodyVO = null;
    	try
    	{
    		Election election = electionDAO.get(electionId);
    		if(election != null)
    		{
    			electionGoverningBodyVO = new ElectionGoverningBodyVO();
    			electionGoverningBodyVO.setElectionType(election.getElectionScope().getElectionType().getElectionType());
    			electionGoverningBodyVO.setElectionTypeId(election.getElectionScope().getElectionType().getElectionTypeId());
    			electionGoverningBodyVO.setElectionYear(election.getElectionYear());
    			
    			if(election.getElectionScope().getState() != null)
    			{
    				electionGoverningBodyVO.setStateName(election.getElectionScope().getState().getStateName());
    				electionGoverningBodyVO.setStateId(election.getElectionScope().getState().getStateId());
    			}
    		}
    		return electionGoverningBodyVO;
    	}
    	
    	catch(Exception e)
    	{
    		log.error("Exception occured in getElectionDetailsForMinister()");
    		return null;
    	}
    }
    
    public List<CandidateMinistriesVO> getAllMinistersDetailsForAnElection(Long electionId)
    {
    	log.debug("Entered in getAllMinistersDetails() method of CandidateDetailService");
    	List<CandidateMinistriesVO> resultList = null;
    	List<Long> canidateIds = null;
    	Map<Long , CandidateMinistriesVO>  candidateMap= null;
    	boolean isParlimentElection = false;
    	
    	try
    	{
	    	List<ElectionGoverningBody> list = electionGoverningBodyDAO.getAllMinistersDetails(electionId);
	    	if(list !=null && list.size() > 0)
	    	{
	    		resultList = new ArrayList<CandidateMinistriesVO>(0);
	    		candidateMap = new HashMap<Long, CandidateMinistriesVO>();	    		
	    		canidateIds = new ArrayList<Long>(0);
	    		boolean hasCabinetMinisters = false;
	    		boolean hasMSIC = false;
	    		boolean hasMS = false;
	    		
	    	
	    		CandidateMinistriesVO candidateMinistriesVO = null;
		    	for(ElectionGoverningBody params : list)
		    	{
		    		candidateMinistriesVO = getCandidateMinistriesVO(resultList,params.getCandidate().getCandidateId());
		    		boolean isNew = false;
		    		
		    		if(candidateMinistriesVO == null)
		    		{
		    			candidateMinistriesVO = new CandidateMinistriesVO();
		    			candidateMinistriesVO.setCandidateId(params.getCandidate().getCandidateId());
		    			candidateMinistriesVO.setCandidateName(params.getCandidate().getLastname());
		    			candidateMinistriesVO.setPartyId(params.getParty().getPartyId());
		    			candidateMinistriesVO.setPartyName(params.getParty().getShortName());
		    			candidateMinistriesVO.setMinistries(new ArrayList<ElectionGoverningBodyVO>());
		    			candidateMinistriesVO.setMinistryTypes(new ArrayList<String>(0));
		    			isNew = true;
		    			
		    			canidateIds.add(params.getCandidate().getCandidateId());
		    			candidateMap.put(params.getCandidate().getCandidateId(), candidateMinistriesVO);
		    		}
		    		
		    		ElectionGoverningBodyVO governingBodyVO = new ElectionGoverningBodyVO();
	    			governingBodyVO.setMinistry(params.getPositionScope().getElectionGoverningBodyPosition().getGoverningBodyPosition());
	    			
	    			if(governingBodyVO.getMinistry().equalsIgnoreCase(IConstants.CHIEF_MINISTER))
	    				if(params.getToDate() == null)
	    				candidateMinistriesVO.setIsChiefMinister(true);
	    			
	    			if(governingBodyVO.getMinistry().equalsIgnoreCase(IConstants.DEPUTY_CHIEF_MINISTER))
	    				if(params.getToDate() == null)
	    				  candidateMinistriesVO.setIsDeputyChiefMinister(true);
	    			
	    			if(governingBodyVO.getMinistry().equalsIgnoreCase(IConstants.PRIME_MINISTER))
	    				if(params.getToDate() == null){
	    				 candidateMinistriesVO.setIsPrimeMinister(true);
	    				 isParlimentElection = true;
	    				}
	    			
	    			governingBodyVO.setMinisterType(params.getPositionScope().getMinisterType().getMinisterType());
	    			
	    			if((candidateMinistriesVO.getIsChiefMinister() == null || !candidateMinistriesVO.getIsChiefMinister()) && 
	    				(candidateMinistriesVO.getIsDeputyChiefMinister() == null || !candidateMinistriesVO.getIsDeputyChiefMinister()) &&
	    				 (candidateMinistriesVO.getIsPrimeMinister() == null || !candidateMinistriesVO.getIsPrimeMinister()))
	    			{
		    			if(governingBodyVO.getMinisterType().equalsIgnoreCase(IConstants.CABINET_MINISTER) &&
		    					!hasCabinetMinisters)
		    				hasCabinetMinisters = true;
		    				
		    			if(governingBodyVO.getMinisterType().equalsIgnoreCase(IConstants.MINISTER_OF_STATE) &&
		    					!hasMS)
		    				hasMS = true;
		    			
		    			if(governingBodyVO.getMinisterType().equalsIgnoreCase(IConstants.MINISTER_OF_STATE_WITH_INDEPENDENT_CHARGE) &&
		    					!hasMSIC)
	    				hasMSIC = true;
	    			
	    			}
	    			
	    			if(!candidateMinistriesVO.getMinistryTypes().contains(governingBodyVO.getMinisterType()))
	    			{
	    				List<String> mtypes = candidateMinistriesVO.getMinistryTypes();
	    				mtypes.add(governingBodyVO.getMinisterType());
	    				candidateMinistriesVO.setMinistryTypes(mtypes);
	    			}
	    			governingBodyVO.setFromDate(params.getFromDate());
	    			governingBodyVO.setToDate(params.getToDate());
	    			
	    			candidateMinistriesVO.getMinistries().add(governingBodyVO);
	    			candidateMinistriesVO.setNoOfMinistries(candidateMinistriesVO.getMinistries().size());
	    			
		    		if(isNew)
		    			resultList.add(candidateMinistriesVO);
		    		
		    		
		    	}
		    	
		    	if(resultList != null && resultList.size() > 0)
		    	{
		    		resultList.get(0).setHasCabinetMinisters(hasCabinetMinisters);
		    		resultList.get(0).setHasMS(hasMS);
		    		resultList.get(0).setHasMSIC(hasMSIC);
		    	}
		    	
		    	
	    	}
	    	
	    	//CHANGE BY SAMBA START TO DISPLAY RESIGNED MINISTRIES
	    	
	    	 for(CandidateMinistriesVO candidateMinistriesVO:resultList){
	    		 
	    		 //boolean allExpired = true;
	    		 int size = candidateMinistriesVO.getMinistries().size();
	    		 int i=0;
	    		 
	    		 for(ElectionGoverningBodyVO governingBodyVO:candidateMinistriesVO.getMinistries()){ 
	    		
	    			 if(governingBodyVO.getToDate() != null){	    				 
	    				 candidateMinistriesVO.setExpireOneMinistry(true);
	    				 i++;
	    			}	    			   			
	    		 }
	    		 
	    		 if(size == i)
	    			 candidateMinistriesVO.setExpisreAllMinistry(true);
	    		 
	    		 candidateMinistriesVO.setNoOfMinistriesExpired(i);
	    		 candidateMinistriesVO.setNoOfMinistriesNotExpired(size-i);    			
	       
	    	 }
	    	 
	    	//CHANGE BY SAMBA END TO DISPLAY RESIGNED MINISTRIES
	    	 
	    	 
	    	 Map<String , Long> stateMap = null;
	    	 Map<String , Long> stateMap1 = null;
	    	 Map<String , Long> districtMap = null;
	    	 Map<String , Long> districtMap1 = null;
	    	 
	    	 //NEW CONTENT START 
	    	 
	 		List<CandidateResult> candidateResults = candidateResultDAO.findCandidateResultsByCandidateIds(canidateIds , electionId);
	 		
	 		if(candidateResults != null && candidateResults.size() >0){
	 			stateMap = new HashMap<String, Long>(0);
	 			stateMap1 = new HashMap<String, Long>(0);
	 			districtMap = new HashMap<String, Long>(0);
	 			districtMap1 = new HashMap<String, Long>(0);
	 			 
	 		}
	 		
	 		for(CandidateResult candidateResult:candidateResults){
	 			
	 			
		 		if(candidateResult.getRank().equals(new Long(1))){
		 				
		 			Long candidateId = candidateResult.getNomination().getCandidate().getCandidateId();
		 			
		 			CandidateMinistriesVO candidateMinistresVO = candidateMap.get(candidateId);
		 			
		 			Constituency constituency = candidateResult.getNomination().getConstituencyElection().getConstituency();
		 			
		 			if(candidateMinistresVO.getExpisreAllMinistry() == null)
                    if(stateMap.get(constituency.getState().getStateName()) == null){
                    	
			 			stateMap.put(constituency.getState().getStateName(),constituency.getState().getStateId());
			 			stateMap1.put(constituency.getState().getStateName(), 1L);
			 			
                    }else{
                    	
                    	Long count = stateMap1.get(constituency.getState().getStateName());                    	
                    	count++;                    	
                    	stateMap1.put(constituency.getState().getStateName(), count);                    	
                    	
                    }
		 			
		 			List<StatePageVO> stateList = new ArrayList<StatePageVO>();
		 			for (Map.Entry<String, Long> entry : stateMap1.entrySet()) {
		 				
		 				StatePageVO statePageVO = new StatePageVO();
		 				
		 				statePageVO.setStateId(stateMap.get(entry.getKey()));
		 				statePageVO.setStateName(entry.getKey().toString());
		 				statePageVO.setStateCount((Long)entry.getValue());
		 				
		 				stateList.add(statePageVO);
		 			}
		 			
                     Collections.sort(stateList);
                     
                     
                     
                     
                   //GET MINISTERS IN DISTRICTS COUNT START
 		 			
                    if(!isParlimentElection )
 		 			if(candidateMinistresVO.getExpisreAllMinistry() == null)
 		 			 if(districtMap.get(constituency.getDistrict().getDistrictName()) == null){
 		 				if(constituency.getDistrict() != null && constituency.getDistrict().getDistrictName() != null){
 		 				districtMap.put(constituency.getDistrict().getDistrictName(), constituency.getDistrict().getDistrictId());
 		 				districtMap1.put(constituency.getDistrict().getDistrictName(), 1L);		
 		 				}
 		 				
 		 			}else{
 		 				
 		 				Long count = districtMap1.get(constituency.getDistrict().getDistrictName());
 		 				count++;
 		 				districtMap1.put(constituency.getDistrict().getDistrictName(),count );
 		 				
 		 			}
 		 			
 		 			
 		 			List<ConstituencyInfoVO> districtList = new ArrayList<ConstituencyInfoVO>(0);
 		 			Map<Long ,ConstituencyInfoVO > districtMap2 = new HashMap<Long, ConstituencyInfoVO>();
 		 			
 		 			
 		 			for (Map.Entry<String, Long> entry : districtMap1.entrySet()) {
 		 				
 		 				ConstituencyInfoVO districtVO = new ConstituencyInfoVO();
 		 				
 		 				districtVO.setDistrictName(entry.getKey().toString());
 		 				districtVO.setDistrictId(districtMap.get(entry.getKey()));
 		 				districtVO.setDistrictCount((Long)entry.getValue());	
 		 				
 		 				districtMap2.put(districtVO.getDistrictId(), districtVO);
 		 				
 		 				districtList.add(districtVO);
 		 			}
 		 			
                      Collections.sort(districtList);
 		 			
 		 			
 		 			
 		 			//GET MINISTERS IN DISTRICTS COUNT END
                     
                     
                     
                    
                    if(resultList != null && resultList.size() > 0)
    		    	{
    		    		resultList.get(0).setStatesMap(stateMap1);
    		    		resultList.get(0).setStatesList(stateList);
    		    		resultList.get(0).setDistrictList(districtList);
    		    		
    		    	}
		 			
					if(constituency.getConstituencyId()!=null && candidateMinistresVO.getCandidateConstiuencyName() == null){
						candidateMinistresVO.setCandidateConstiuencyId(constituency.getId());
						candidateMinistresVO.setCandidateConstiuencyName(constituency.getName());
					}
					
					if(constituency.getState() != null && candidateMinistresVO.getCandidateStateName() == null){
						candidateMinistresVO.setCandidateStateId(constituency.getState().getStateId());	
						candidateMinistresVO.setCandidateStateName(constituency.getState().getStateName());				
					}
					if(constituency.getState() == null && candidateMinistresVO.getCandidateStateName() == null){	
						candidateMinistresVO.setCandidateStateId(constituency.getLocalElectionBody().getDistrict().getState().getStateId());
						candidateMinistresVO.setCandidateStateName(constituency.getLocalElectionBody().getDistrict().getState().getStateName());
					}
					
					if(constituency.getDistrict() != null && candidateMinistresVO.getCandidateDistrictName() == null){
						
						if(constituency.getLocalElectionBody() != null && constituency.getLocalElectionBody().getDistrict() != null  && constituency.getLocalElectionBody().getDistrict().getState() != null)
						candidateMinistresVO.setCandidateStateId(constituency.getLocalElectionBody().getDistrict().getState().getStateId());
						candidateMinistresVO.setCandidateDistrictName(constituency.getDistrict().getDistrictName());	
					}
			
		 		}	 			
	 			
	 		}
	 		
	 		for(CandidateMinistriesVO candidateVO:resultList){
	 			
	 			if(candidateVO.getCandidateStateId() == null){
	 				List<CandidateResult> cadtResults =candidateResultDAO.findCandidateResults(candidateVO.getCandidateId()); 
	 				
	 				
	 				for(CandidateResult candidateResult:cadtResults){
	 					
		 				Constituency constituency = candidateResult.getNomination().getConstituencyElection().getConstituency();
		 				
		 				if(candidateVO.getCandidateStateName() == null){
		 				
		 				candidateVO.setCandidateStateId(constituency.getState().getStateId());	
		 				candidateVO.setCandidateStateName(constituency.getState().getStateName());	
		 				
		 				if(resultList.get(0).getStatesMap().get(constituency.getState().getStateName()) == null){
		 					
		 					StatePageVO stateVO = new StatePageVO();
		 					
		 					stateVO.setStateCount(1L);
		 					stateVO.setStateName(constituency.getState().getStateName());
		 					stateVO.setStateId(constituency.getState().getStateId());
		 					
		 					resultList.get(0).getStatesList().add(stateVO);
		 					
		 					Collections.sort(resultList.get(0).getStatesList());
		 					
		 				}else{//need to check
		 				
		 				Long count = resultList.get(0).getStatesMap().get(constituency.getState().getStateName());
		 				count++;
		 				for(StatePageVO stageVO:resultList.get(0).getStatesList())		 					
		 					if(stageVO.getStateName().equalsIgnoreCase(constituency.getState().getStateName())){
		 						stageVO.setStateCount(count);
		 						//candidateVO.getStatesMap().put(constituency.getState().getStateName(), count);
		 					}
		 				}
		 				}
	 				}
	 				
	 				 cadtResults = null;
	 			}
	 		}
	    	 //NEW CONTENT END	
	 		
	 		//get Constituency Details Of Districts	 
	 		if(!isParlimentElection )
	 		 getConstituencyDetailsOfDistricts(resultList,electionId);
	 		else
	 			getParlimentConstituencyDetailsOfDistricts(resultList,electionId);
	    	
    	return resultList;
    	}
    	
    	catch(Exception e)
    	{
    		log.error("Exception occured in getAllMinistersDetails() of CandidateDetailsService");
    		return resultList;
    	}
    }
    
    public List<CandidateMinistriesVO> getParlimentConstituencyDetailsOfDistricts(List<CandidateMinistriesVO> resultList,Long electionId){
    	
    	List<StatePageVO> statesList = resultList.get(0).getStatesList();
    	
    	 Map<String ,StateLevelPartyReportVO> stateConstituencyCountMap = new HashMap<String, StateLevelPartyReportVO>();
    	 Map<String ,StateLevelPartyReportVO> sortedStateConstituencyCountMap = new HashMap<String, StateLevelPartyReportVO>();
    	 
    	 List<Long> stateIdList = new ArrayList<Long>();
    	 
    	 for(StatePageVO statePageVO:statesList){
    		 stateIdList.add(statePageVO.getStateId());
    		 stateConstituencyCountMap.put(statePageVO.getStateName(), new StateLevelPartyReportVO());
    	 }
    	 
		List<PartyElectionStateResult> partyElectionStateResultList = partyElectionStateResultDAO
				.getParticipatedPartiesDetailsInStates(electionId, stateIdList); 
		
		
		for(PartyElectionStateResult partyElectionStateResult:partyElectionStateResultList){
			
			StateLevelPartyReportVO stateLevelPartyReportVO = stateConstituencyCountMap.get(partyElectionStateResult.getState().getStateName());
			
			if(stateLevelPartyReportVO.getPartyResultList() == null){
				
				List<PartyResultVO> partyResultVOList = new ArrayList<PartyResultVO>();
					
					PartyResultVO partyResultVO = new PartyResultVO();
					
					partyResultVO.setPartyName(partyElectionStateResult.getParty().getShortName());
					partyResultVO.setTotalSeatsOwn(Integer.parseInt(partyElectionStateResult.getTotalSeatsWon()));
					partyResultVO.setTotalSeatsParticipated(Long.parseLong(partyElectionStateResult.getTotalConstiParticipated()));
					partyResultVOList.add(partyResultVO);
					
					stateLevelPartyReportVO.setPartyResultList(partyResultVOList);					
 					
					stateLevelPartyReportVO.setTotalNoOfConstituencies(Long.parseLong(partyElectionStateResult.getTotalSeatsWon()));
				
			}else{
					
                PartyResultVO partyResultVO = new PartyResultVO();
				
				 partyResultVO.setPartyName(partyElectionStateResult.getParty().getShortName());
				 partyResultVO.setTotalSeatsOwn(Integer.parseInt(partyElectionStateResult.getTotalSeatsWon()));
				 partyResultVO.setTotalSeatsParticipated(Long.parseLong(partyElectionStateResult.getTotalConstiParticipated()));

				 
				Long count = stateLevelPartyReportVO.getTotalNoOfConstituencies();
				
				count = count + Long.parseLong(partyElectionStateResult.getTotalSeatsWon());
				stateLevelPartyReportVO.setTotalNoOfConstituencies(count);
				
				stateLevelPartyReportVO.getPartyResultList().add(partyResultVO);
			
			}			
		}
		
		List<String> sortedKeylist = new ArrayList<String>();
		//Map<String, String> map = new HashMap<String, String>();
		for (String str : stateConstituencyCountMap.keySet()) {
			sortedKeylist.add(str);
		}
		Collections.sort(sortedKeylist);
		

		 for(String stateName:sortedKeylist)			 
			 sortedStateConstituencyCountMap.put(stateName, stateConstituencyCountMap.get(stateName));
			 
		
		
		resultList.get(0).setStateConstituencyCountMap(sortedStateConstituencyCountMap);
    	 
    	return resultList;
    }
    
    
    public List<CandidateMinistriesVO> getConstituencyDetailsOfDistricts(List<CandidateMinistriesVO> resultList,Long electionId){
    	
    	
 		List<ConstituencyInfoVO> districtList = resultList.get(0).getDistrictList();
        Map<String ,DistrictWisePartyResultVO> districtConstituencyCountMap = new HashMap<String, DistrictWisePartyResultVO>();
        Map<String ,DistrictWisePartyResultVO> sortedDistrictConstituencyCountMap = new LinkedHashMap<String, DistrictWisePartyResultVO>();
 		
 		List<Long> districtIdsList = new ArrayList<Long>();
       for(ConstituencyInfoVO constituencyInfoVO:districtList) {      	   
    	   districtIdsList.add(constituencyInfoVO.getDistrictId());
    	   districtConstituencyCountMap.put(constituencyInfoVO.getDistrictName(),new  DistrictWisePartyResultVO());
       }
       
		List<PartyElectionDistrictResult> electionResultList = partyElectionDistrictResultDAO
				.getParticipatedPartiesDetailsInDistricts(electionId,
						districtIdsList);
       
 			 for(PartyElectionDistrictResult partyResult:electionResultList){
 				 
 				DistrictWisePartyResultVO districtWisePartyResultVO = districtConstituencyCountMap.get(partyResult.getDistrict().getDistrictName());
 				
 				if(districtWisePartyResultVO.getPartyElectionResultsList() == null){
 					
 					List<PartyResultVO> partyResultVOList = new ArrayList<PartyResultVO>();
 					
 					PartyResultVO partyResultVO = new PartyResultVO();
 					
 					partyResultVO.setPartyName(partyResult.getParty().getShortName());
 					partyResultVO.setTotalSeatsOwn(Integer.parseInt(partyResult.getTotalSeatsWon()));
 					partyResultVO.setTotalSeatsParticipated(Long.parseLong(partyResult.getTotalConstiParticipated()));
 					partyResultVOList.add(partyResultVO);
 					
 					districtWisePartyResultVO.setPartyElectionResultsList(partyResultVOList);
 					
 					districtWisePartyResultVO.setTotalConstituencies(Long.parseLong(partyResult.getTotalSeatsWon()));
 					
 				}else{
 					
                     PartyResultVO partyResultVO = new PartyResultVO();
 					
 					 partyResultVO.setPartyName(partyResult.getParty().getShortName());
 					 partyResultVO.setTotalSeatsOwn(Integer.parseInt(partyResult.getTotalSeatsWon()));
 					partyResultVO.setTotalSeatsParticipated(Long.parseLong(partyResult.getTotalConstiParticipated()));
 					 
 					Long count = districtWisePartyResultVO.getTotalConstituencies();
 					
 					count = count + Long.parseLong(partyResult.getTotalSeatsWon());
 					districtWisePartyResultVO.setTotalConstituencies(count);
 					
 					 districtWisePartyResultVO.getPartyElectionResultsList().add(partyResultVO);
 				}	 				 
 				 
 			 }
 			 
 			List<String> sortedKeylist = new ArrayList<String>();
 			//Map<String, String> map = new HashMap<String, String>();
 			for (String str : districtConstituencyCountMap.keySet()) {
 				sortedKeylist.add(str);
 			}
 			Collections.sort(sortedKeylist);
 			

 			 for(String districtName:sortedKeylist)			 
 				sortedDistrictConstituencyCountMap.put(districtName, districtConstituencyCountMap.get(districtName));
 		
 			 resultList.get(0).setDistrictConstituencyCountMap(sortedDistrictConstituencyCountMap);
 		
 			//GET CONSTITUENCY DETAILS OF DISTRICTS END 
 			 
 			 return resultList;
    	
    }
    public CandidateMinistriesVO getCandidateMinistriesVO(List<CandidateMinistriesVO> list,Long candidateId)
    {
    	try{
    		if(list == null || list.size() == 0)
    			return null;
			for(CandidateMinistriesVO candidateMinistriesVO : list)
				if(candidateMinistriesVO.getCandidateId().longValue() == candidateId.longValue())
					return candidateMinistriesVO;
			return null;
    	}catch (Exception e) {
			log.error("Exception Occured in getMinistryVO method, Exception is - "+e);
			return null;
		}
    }
    
    public List<SelectOptionVO> getMinistryYearsForAState(String electionType, Long stateId)
    {
    	try{
    		List<SelectOptionVO> years = null;
    		List<Object[]> list = null;
    		
    		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
    		{
    			list = electionGoverningBodyDAO.getMinistryYearsForAssembly(stateId);
    		}
    		else if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
    		{
    			list = electionGoverningBodyDAO.getMinistryYearsForParliament();
    		}
    		
    		if(list != null && list.size() > 0)
    		{
    			years = new ArrayList<SelectOptionVO>(0);
    			
    			for(Object[] params : list)
    			{
    				years.add(new SelectOptionVO((Long)params[0],params[1].toString()));
    			}
    		}
    		return years;
    	}catch (Exception e) {
    		log.error("Exception occured in getMinistryYearsForAState() Method, Exception is - "+e);
    		return null;
    	}
    }
    
    public List<CustomPageVO> getCustomPagesOfACandidatePage(Long candidateId)
    {
    	try{
			List<CustomPageVO> customPages = null;
			List<Object[]> list = candidatePageCustomPagesDAO.getCustomPagesOfACandidatePage(candidateId);
			
			if(list != null && list.size() > 0)
			{
				customPages = new ArrayList<CustomPageVO>(0);
				for(Object[] params : list)
					customPages.add(new CustomPageVO(IConstants.CUSTOM_JSP_PAGES_PATH+"/"+params[0].toString(),params[1].toString()));
			}
			return customPages;
		}catch (Exception e) {
			log.error("Exception Occured in getCustomPagesOfACandidatePage(), Exception is - "+e);
			return null;
		}
    }
    
    public MetaInfoVO getMetaInfoOfMinistersPage(ElectionGoverningBodyVO electionGoverningBodyVO,List<CandidateMinistriesVO> ministersList)
    {
    	try{
    		MetaInfoVO metaInfoVO = null;
    		
    		if(electionGoverningBodyVO != null && ministersList != null)
    		{
    			metaInfoVO = new MetaInfoVO();
    			String keyWords = "";
    			String description = "";
    			String CMstr = "";
    			String DCMstr = "";
    			String PMstr = "";
    			String electionType = electionGoverningBodyVO.getElectionType();
    			
    			keyWords = "Ministers Of ";
    			
    			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
    				keyWords += electionGoverningBodyVO.getStateName()+" ";
    			else
    				keyWords += "India ";
    			
    			keyWords += electionGoverningBodyVO.getElectionYear()+", ";
    			
    			keyWords += "Cabinet Ministers, Ministers of State, Ministers of State with Independent Charge, ";
    			
    			if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
    				keyWords += "Chief Minister, Deputy Chief Minister of "+electionGoverningBodyVO.getStateName();
    			else
    				keyWords += "Prime Minister of India";
    			
    			for(CandidateMinistriesVO ministriesVO : ministersList)
    			{
    				if(ministriesVO.getIsChiefMinister() != null && ministriesVO.getIsChiefMinister())
    				{
    					CMstr += "Shri "+ministriesVO.getCandidateName()+ " is the Chief Minister of "+electionGoverningBodyVO.getStateName()+",";
    				}
    				else if(ministriesVO.getIsDeputyChiefMinister() != null && ministriesVO.getIsDeputyChiefMinister())
    				{
    					DCMstr += "Shri "+ministriesVO.getCandidateName()+ " is the Deputy Chief Minister of "+electionGoverningBodyVO.getStateName()+",";
    				}
    				else if(ministriesVO.getIsPrimeMinister() != null && ministriesVO.getIsPrimeMinister())
    				{
    					PMstr += "Shri "+ministriesVO.getCandidateName()+ " is the Prime Minister of India";
    				}
    			}
    			
    			description = PMstr +" "+CMstr+" "+DCMstr;
    			description += " Now you can know all three types of ministers(Cabinet minister,State minister,State Minister " +
    					" with independent Charge), their working duration";
    			metaInfoVO.setKeywords(keyWords);
    			metaInfoVO.setDescription(description);
    		}
    		
    		return metaInfoVO;
    	}catch (Exception e) {
    		log.error("Exception occured in getMetaInfoOfMinistersPage() Method, Exception is - "+e);
    		return null;
    	}
    }
    
    public List<FileVO> customPagesType()
	{   
			 List<FileVO> retValue = new ArrayList<FileVO>();
		  try{
		    
			 if(log.isDebugEnabled())
				log.debug("Entered into customPagesType() of candidateDetailsService ");
			 List<CustomPageType> customPageType = customPageTypeDAO.getAll();
			 for(CustomPageType result:customPageType)
			 {
				FileVO fileVO = new FileVO();
				fileVO.setIds(result.getCustomPageTypeId());
				fileVO.setNames(result.getCustomPageType());
				retValue.add(fileVO);
			 }
			 
			
		  }catch(Exception e){
				log.error("Exception occured in customPagesType() of candidateDetailsService ", e);
				
		   }
		
		return retValue;
     }
    public ResultStatus createCustomPages(GallaryVO gallaryVO)
    {
    	ResultStatus resultStatus = new ResultStatus();
    	CustomPage customPage = new CustomPage();
    	CandidatePageCustomPages candidatePageCustomPages = new CandidatePageCustomPages();
    	PartyPageCustomPages partyPageCustomPages = new PartyPageCustomPages();
    	SpecialPageCustomPages specialPageCustomPages = new SpecialPageCustomPages();
    	try
    	{
    	  if(log.isDebugEnabled())
    		  log.debug("Entered into createCustomPages of candidateDetailsService");
    	  if(gallaryVO.getPageName().equalsIgnoreCase(IConstants.CANDIDATE_PAGE))  
    	  {   List<CandidatePageCustomPages>  cpcp = candidatePageCustomPagesDAO.candidateExistsOrNot(gallaryVO.getPageId(),gallaryVO.getCustomPageName());
    	      if(cpcp == null || cpcp.size()== 0)
    	      {
    	    	//save data in Custom_Page table
    	    	customPage.setName(gallaryVO.getCustomPageName());
    			customPage.setCustomPageType(customPageTypeDAO.get(gallaryVO.getCustomPageType()));
    			customPage = customPageDAO.save(customPage);
    			//save data in candidate_Page_Custom_Pages table
    		    candidatePageCustomPages.setCustomPage(customPage);
    		    candidatePageCustomPages.setCandidate(candidateDAO.get(gallaryVO.getPageId()));
    		    candidatePageCustomPagesDAO.save(candidatePageCustomPages);
    		    resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
    	      }
    	      else if(cpcp != null && cpcp.size()> 0)
    	      {
    	    	  resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
    	      }
    	  }
          else if(gallaryVO.getPageName().equalsIgnoreCase(IConstants.PARTY_PAGE))
          {
        	  List<PartyPageCustomPages> ppcp = partyPageCustomPagesDAO.partyExistsOrNot(gallaryVO.getPageId(),gallaryVO.getCustomPageName());
        	  if(ppcp == null || ppcp.size()== 0)
        	  {

      	    	//save data in Custom_Page table
      	    	customPage.setName(gallaryVO.getCustomPageName());
      			customPage.setCustomPageType(customPageTypeDAO.get(gallaryVO.getCustomPageType()));
      			customPage = customPageDAO.save(customPage);
        		//save data in party_Page_Custom_Pages table
        	    partyPageCustomPages.setCustomPage(customPage);
        	    partyPageCustomPages.setParty(partyDAO.get(gallaryVO.getPageId()));
        	    partyPageCustomPagesDAO.save(partyPageCustomPages);
        	    resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
        	  }
        	  else if(ppcp != null && ppcp.size()> 0)
    	      {
    	    	  resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
    	      }
          }
          
          else if(gallaryVO.getPageName().equalsIgnoreCase(IConstants.SPECIAL_PAGE))
          {
        	  List<SpecialPageCustomPages> spcp = specialPageCustomPagesDAO.specialIdExistsOrNot(gallaryVO.getPageId(),gallaryVO.getCustomPageName());
        	  if(spcp == null || spcp.size()==0)
        	  {

      	    	 //save data in Custom_Page table
      	    	 customPage.setName(gallaryVO.getCustomPageName());
      			 customPage.setCustomPageType(customPageTypeDAO.get(gallaryVO.getCustomPageType()));
      			 customPage = customPageDAO.save(customPage);
      			//save data in special_Page_Custom_Pages table
        	     specialPageCustomPages.setCustomPage(customPage);
        	     specialPageCustomPages.setSpecialPage(specialPageDAO.get(gallaryVO.getPageId()));
        	     specialPageCustomPagesDAO.save(specialPageCustomPages);
        	     resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
        	  }
        	  else if(spcp != null && spcp.size()> 0)
    	      {
    	    	  resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
    	      }
        	  
          }
    	  
    	}catch(Exception e){
    	
    		resultStatus.setExceptionEncountered(e);
    		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
    		 log.error("Exception Occured in createCustomPages() of candidateDetailsService "+e);
    	}
    	
    	return resultStatus;
    }
    
    public List<CustomPageVO> getCustomPages(Long pageId,String pageName)
    {   
    	
    	List<CustomPageVO> customPages = new ArrayList<CustomPageVO>();
    	
    	try
    	{
    		if(log.isDebugEnabled())
    			log.debug("Enterd into getCustomPages() of candidateDetailsService");
    		
    		
           if(pageName.equalsIgnoreCase(IConstants.CANDIDATE_PAGE))
           {
        	  List<Object[]>list = candidatePageCustomPagesDAO.getCandidateCustomPage(pageId);
        	  if(list !=null && list.size()>0)
        	  {
        		  for(Object[] params :list)
        		  {
					  CustomPageVO customPageVO =new CustomPageVO();
        			  customPageVO.setName(params[0].toString());
        			  customPageVO.setType(params[1].toString());
        			  customPageVO.setTypeId((Long)params[2]);
        			  customPages.add(customPageVO);
        		  }
        	  }
        	  else if(list == null || list.size() == 0)
        	  {
				  CustomPageVO customPageVO =new CustomPageVO();
        		  customPageVO.setError(ResultCodeMapper.DATA_NOT_FOUND);
        		  customPages.add(customPageVO);
        	  }
           }
           else if(pageName.equalsIgnoreCase(IConstants.PARTY_PAGE))
           {
        	  List<Object[]>list = partyPageCustomPagesDAO.getPartyCustomPage(pageId);
        	  if(list !=null && list.size()>0)
        	  {
        		  for(Object[] params :list)
        		  {
					  CustomPageVO customPageVO =new CustomPageVO();
        			  customPageVO.setName(params[0].toString());
        			  customPageVO.setType(params[1].toString());
        			  customPageVO.setTypeId((Long)params[2]);
        			  customPages.add(customPageVO);
        		  }
        	  }
        	  else if(list == null || list.size() == 0)
        	  {
				  CustomPageVO customPageVO =new CustomPageVO();
        		  customPageVO.setError(ResultCodeMapper.DATA_NOT_FOUND);
        		  customPages.add(customPageVO);
        	  }
           }
           else if(pageName.equalsIgnoreCase(IConstants.SPECIAL_PAGE))
           {
        	  List<Object[]>list = specialPageCustomPagesDAO.getSpecialCustomPage(pageId);
        	  if(list !=null && list.size()>0)
        	  {
        		  for(Object[] params :list)
        		  { 
					  CustomPageVO customPageVO =new CustomPageVO();
        			  customPageVO.setName(params[0].toString());
        			  customPageVO.setType(params[1].toString());
        			  customPageVO.setTypeId((Long)params[2]);
        			  customPages.add(customPageVO);
        		  }
        	  }
        	  else if(list == null || list.size() == 0)
        	  {
				  CustomPageVO customPageVO =new CustomPageVO();
        		  customPageVO.setError(ResultCodeMapper.DATA_NOT_FOUND);
        		  customPages.add(customPageVO);
        	  }
           }
    	}catch(Exception e)
    	{
    		log.error("Exception occured in getCustomPages() of candidateDetailsService " +e);
    	}
    	return customPages;
    }
    
    public ResultStatus updateCustomPages(GallaryVO gallaryVO)
    {
    	ResultStatus resultStatus = new ResultStatus();
    	
    	try
    	{
    	  if(log.isDebugEnabled())
    		  log.debug("Entered into updateCustomPages() of candidateDetailsService");
    	  
    	  
          if(gallaryVO.getPageName().equalsIgnoreCase(IConstants.CANDIDATE_PAGE))  
    	  {  
        	  List<Object>list = candidatePageCustomPagesDAO.getCustomPageId(gallaryVO.getPageId());
        	  customPageDAO.updateCustompage((Long)list.get(0),gallaryVO.getCustomPageName(),gallaryVO.getCustomPageType());
        	  
    	  }
          else if(gallaryVO.getPageName().equalsIgnoreCase(IConstants.PARTY_PAGE))
          {
        	  List<Object>list = partyPageCustomPagesDAO.getPartyPageId(gallaryVO.getPageId());
        	  customPageDAO.updateCustompage((Long)list.get(0),gallaryVO.getCustomPageName(),gallaryVO.getCustomPageType());
          }
          
          else if(gallaryVO.getPageName().equalsIgnoreCase(IConstants.SPECIAL_PAGE))
          {
        	  List<Object>list = specialPageCustomPagesDAO.getSpecialPageId(gallaryVO.getPageId());
        	  customPageDAO.updateCustompage((Long)list.get(0),gallaryVO.getCustomPageName(),gallaryVO.getCustomPageType());
          }
    	  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
    	}catch(Exception e){
    	
    		resultStatus.setExceptionEncountered(e);
    		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
    		 log.error("Exception Occured in updateCustomPages() of candidateDetailsService "+e);
    	}
    	
    	return resultStatus;
    }
    public String getMandalName(Long mandalId)
    {
    	String mandalName =null;
    	try{
    		if(log.isDebugEnabled())
    			log.debug("Entered into getMandalName() of candidateDetailsService");
    		
    		 mandalName = tehsilDAO.get(mandalId).getTehsilName();
    	}catch(Exception e){
    		
    		 log.error("Exception Occured in getMandalName() of candidateDetailsService "+e);
    		
    	}
    	return mandalName;
    }
   //for getNewsToDisplay
   public List<FileVO> getAllNewsdetails(Long candidateId,int firstResult,int maxResult,String queryType){
	   if(log.isDebugEnabled())
			log.debug("Entered into getAllNewsdetails() of candidateDetailsService");
	   List<FileVO> retValue = new ArrayList<FileVO>();
		 try{
			  List<Long> list = fileGallaryDAO.getNewsCountByScope(candidateId,null,queryType); 
			  List<Object[]> dataList =  fileGallaryDAO.getAllNewsDetails(candidateId,firstResult,maxResult,queryType);
			 
			  retValue = convertDataToFileVO(dataList,list,candidateId,null,null);
			  
			return retValue;
			}
			catch(Exception e){
				log.error("Exception rised in getAllNewsdetails method ",e);
				return retValue;
			}
     }*/
      public static Comparator<FileVO> sortData = new Comparator<FileVO>()
		    {
		   
		        public int compare(FileVO fileVO1, FileVO fileVO2)
		        {
		            return (fileVO1.getOrderNo().intValue()) - (fileVO2.getOrderNo().intValue());
		        }
		    };
	 public static Comparator<FileVO> sourceSort = new Comparator<FileVO>()
			{
				  
			  public int compare(FileVO fileVO1, FileVO fileVO2)
				{
				   return (fileVO1.getFileSourceLanguageId().intValue()) - (fileVO2.getFileSourceLanguageId().intValue());
				}
		  };
	 public static Comparator<FileSourceLanguage> fileSourceLanguageSort = new Comparator<FileSourceLanguage>()
			{
						  
					  public int compare(FileSourceLanguage fileSourceLanguage1, FileSourceLanguage fileSourceLanguage2)
						{
						   return (fileSourceLanguage1.getFileSourceLanguageId().intValue()) - (fileSourceLanguage2.getFileSourceLanguageId().intValue());
						}
			};
	public static Comparator<FilePaths> filePathsSort = new Comparator<FilePaths>()
					{
								  
					   public int compare(FilePaths filePaths1, FilePaths filePaths2)
						{
					       return (filePaths1.getOrderNo().intValue()) - (filePaths2.getOrderNo().intValue());
						}
					};

	/*private List<FileVO> convertDataToFileVO(List<Object[]> dataList,List<Long> list,Long candidateId,String language,String source){
		
		if(log.isDebugEnabled())
			log.debug("Entered into convertDataToFileVO() of candidateDetailsService");

	     List<FileVO> retValue = new ArrayList<FileVO>();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 try{
			  FileVO fileVO;
			  
			  for(Object[] data : dataList){
				  
				 try{
				 File file = (File)data[0];
				 fileVO = new FileVO();
                 
				 fileVO.setFileId(file.getFileId());
				 fileVO.setTotalResultsCount(list.get(0));
				 fileVO.setFileTitle1(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle()));
				 fileVO.setFileDescription1(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription()));
				 fileVO.setFileDate(data[2] != null ? (sdf.format((Date)data[2])) :"");
				 fileVO.setContentId((Long)data[1]);
				 fileVO.setCandidateId(candidateId);
				 if(file.getCategory() != null)
				 { 
					 fileVO.setCategoryId(file.getCategory().getCategoryId());
					 fileVO.setCategoryType(file.getCategory().getCategoryType());
				 }
				 if(file.getNewsImportance() !=null)
				 {
				  fileVO.setNewsImportanceId(file.getNewsImportance().getNewsImportanceId());
				  fileVO.setImportance(file.getNewsImportance().getImportance());
				 }
				 List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>();
				 Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
				 
					 
				 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
					 if(language == null && source == null)
					    setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
					 else if(language != null){
						 if(language.equalsIgnoreCase(fileSourceLanguage.getLanguage().getLanguage()))
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList); 
					 }
					 else if(source != null){
						 if(source.equalsIgnoreCase(fileSourceLanguage.getSource().getSource()))
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList); 
					 }
				 }
				 fileVO.setMultipleSource(fileVOSourceLanguageList.size());
				 Collections.sort(fileVOSourceLanguageList,sourceSort);
				 fileVO.setFileVOList(fileVOSourceLanguageList);
				 retValue.add(fileVO);
				 }catch (Exception e) {
					 log.error("Exception Occured in convertDataToFileVO method ",e);
				 }
			 }
			 
			return retValue;
			}
			catch(Exception e){
				log.error("Exception rised in convertDataToFileVO method ",e);
				return retValue;
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
		 Collections.sort(fileVOPathsList,sortData);
		 fileVOSourceLanguage.setFileVOList(fileVOPathsList);
		 fileVOSourceLanguageList.add(fileVOSourceLanguage);
	}
	
	public RegistrationVO getStateAndConstituency(Long userId)
	{
		try{
			RegistrationVO regVO = new RegistrationVO();
			List<Object[]> userDtlsList = userDAO.findByUserId(userId);
			Object[] userDtls = userDtlsList.get(0);
			  if(userDtls[0] != null)
				  regVO.setConstituencyId((Long)userDtls[1]);
			      regVO.setStateId((Long)userDtls[0]);
			return regVO;
		}catch (Exception e) {
			log.error("Exception Occured in getStateAndConstituency() Method");
			return null;
		}
	}
	
	public Boolean getSubscriptionDetails(Long userId,Long candidateId,String page)
	{
		Boolean flag = false;
		try
		{
			if(page.equalsIgnoreCase("cPage")){
			Long count = candidateSubscriptionsDAO.getSubscriptionCount(userId,candidateId);
			
			if(count.longValue() > 0)
				flag = true;
			
			return flag;
		}
			else if(page.equalsIgnoreCase("pPage")){
				Long count = partySubscriptionsDAO.getSubscriptionCount(userId,candidateId);
				
				if(count.longValue() > 0)
					flag = true;
				
				return flag;
			}
			else if(page.equalsIgnoreCase("sPage")){
				Long count = specialPageSubscriptionsDAO.getSubscriptionCount(userId,candidateId);
				
				if(count.longValue() > 0)
					flag = true;
				
				return flag;
			}
			else {
				return flag;
			}
			
		}catch (Exception e) {
			log.error("Exception occured in getSubscriptionDetails() Method, Exception is - "+e);
			return flag;
		}
			
		
	}
	public ResultStatus subscriberDetails(Long id,Long userId,String category)
	{
		log.debug("Entered Into subscriberDetails() Method of CandidateDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		
		try{
			if(category.equalsIgnoreCase("candidatePage"))
			{
			    List<CandidateSubscriptions> candidateUpdatesEmails = candidateSubscriptionsDAO.getSubscriberDetails(id, userId);
			   
			    if(!(candidateUpdatesEmails.size() > 0))
			    {
					CandidateSubscriptions candidateSubscriptions = new CandidateSubscriptions();
					
					candidateSubscriptions.setCandidateId(id);
					candidateSubscriptions.setUserId(userId);
					candidateSubscriptions.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					candidateSubscriptions.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					candidateSubscriptions = candidateSubscriptionsDAO.save(candidateSubscriptions);
			    }
			}
			
			else if(category.equalsIgnoreCase("partyPage"))
			{
			    List<PartySubscriptions> candidateUpdatesEmails = partySubscriptionsDAO.getSubscriberDetails(id, userId);
			    
			    if(!(candidateUpdatesEmails.size() >0))
			    {
			    	PartySubscriptions partySubscriptions = new PartySubscriptions();
				
			    	partySubscriptions.setPartyId(id);
			    	partySubscriptions.setUserId(userId);
			    	partySubscriptions.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			    	partySubscriptions.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			    	partySubscriptions = partySubscriptionsDAO.save(partySubscriptions);
			    }
			}
			
			else if(category.equalsIgnoreCase("specialPage"))
			{
			    List<SpecialPageSubscriptions> candidateUpdatesEmails = specialPageSubscriptionsDAO.getSubscriberDetails(id, userId);
			    
			    if(!(candidateUpdatesEmails.size() >0))
			    {
			    	SpecialPageSubscriptions specialPageSubscriptions = new SpecialPageSubscriptions();
				
			    	specialPageSubscriptions.setSpecialPageId(id);
			    	specialPageSubscriptions.setUserId(userId);
			    	specialPageSubscriptions.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			    	specialPageSubscriptions.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			    	specialPageSubscriptions = specialPageSubscriptionsDAO.save(specialPageSubscriptions);
			    }
			}
		    else
		    {
		    	resultStatus.setExceptionMsg("You have already subscribed for this candidate"); 
		    }
		    resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		    return resultStatus;
		}catch(Exception e){
			
			log.error("Exception occured in subscriberDetails() Method, Exception is - "+e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
					
	}
	public ResultStatus unSubscriptionDetails(Long id,Long userId,String category)
	{
		log.debug("Entered Into unSubscriptionDetails() Method of CandidateDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		try{
			if(category.equalsIgnoreCase("candidatePage"))
			{
					int flag=candidateSubscriptionsDAO.unSubscriptionDetails(id,userId);
						
							if (flag != 0)
							{
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
							return resultStatus;
						    }
							else
							{
							resultStatus.setResultCode(ResultCodeMapper.FAILURE);
							return resultStatus;
							}
						
			}
			if(category.equalsIgnoreCase("partyPage"))
			{
					int flag=partySubscriptionsDAO.unSubscriptionDetails(id,userId);
						
							if (flag != 0)
							{
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
							return resultStatus;
						    }
							else
							{
							resultStatus.setResultCode(ResultCodeMapper.FAILURE);
							return resultStatus;
							}
						
			}
			if(category.equalsIgnoreCase("specialPage"))
			{
					int flag=specialPageSubscriptionsDAO.unSubscriptionDetails(id,userId);
						
							if (flag != 0)
							{
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
							return resultStatus;
						    }
							else
							{
							resultStatus.setResultCode(ResultCodeMapper.FAILURE);
							return resultStatus;
							}
						
			}else{
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
			}		
		}
		catch(Exception e){
			
			log.error("Exception occured in unSubscriptionDetails() Method, Exception is - "+e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
	}
	
	public List<SelectOptionVO> getAllRegisterUsersForAssigningParty()
	{
		List<SelectOptionVO> listOfUsers = null; 
		try{
			log.info("Entered into getAllRegisterUsersForAssigningParty()");
			List<Object[]> userList = userDAO.allRegisteredUsersData();
			if(userList != null && userList.size() > 0)
			{
				listOfUsers = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : userList)
				{
					String name = new String();
					if(params[1] != null)
						name = params[1].toString();
					name +=" ";
					if(params[2] != null)
						name +=params[2].toString();
					
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(name.toString());
					listOfUsers.add(selectOptionVO);
				}
			}
			return listOfUsers;
		}catch (Exception e) {
			log.error("Exception Occured in getAllRegisterUsersForAssigningParty(), Exception - "+e);
			e.printStackTrace();
			return  null;
		}
	}
	public List<FileVO> getAllPartyDetailsAssignedToAUser(Long userId)
	{
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{
		   List<Object[]> results = userPartyRelationDAO.getUserPartyRelationDetails(userId);
		   for(Object[] candidateDetails: results)
		   {
			   FileVO fileVO = new FileVO();
			   fileVO.setIds((Long)candidateDetails[0]);
			   fileVO.setCandidateId((Long)candidateDetails[1]);
			   fileVO.setNames(candidateDetails[2] != null ? candidateDetails[2].toString() :"");
			  returnValue.add(fileVO);
		   }
		  return returnValue;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return returnValue;
		}
	}
	
	public ResultStatus deleteUserPartyRelation(String userPartyRelationIds)
	{   List<String> elements = null;
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(userPartyRelationIds.length()>0)
			{
				elements = new ArrayList<String>(new HashSet<String>(Arrays.asList(new String(userPartyRelationIds).split(","))));	
				for(int i=0;i<elements.size();i++)
				  {
					Long id = new Long(elements.get(i));
					userPartyRelationDAO.deleteUserPartyRelation(id);
					
				  }
			}
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  return resultStatus;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public List<CandidateCommentsVO> getAbuseComment(String fromDate, String toDate,String selectstatus)
	 {
		 List<CandidateCommentsVO> candidateComments = new ArrayList<CandidateCommentsVO>();
		 if(log.isDebugEnabled())
			 log.debug("Enterd into getAbuseComment()method of CandidateDetailsServive......");
			try{
				
				
				Date firstDate = DateService.convertStringToDate(fromDate, IConstants.DATE_PATTERN_YYYY_MM_DD);
				Date secondDate = DateService.convertStringToDate(toDate, IConstants.DATE_PATTERN_YYYY_MM_DD);
				
				
				List<AbusedComments>  comments = abusedCommentsDAO.getAllAbuseComment(firstDate, secondDate,selectstatus);
				candidateComments = commentsDetailsFromAbuseCommeentList(comments);
							
			}catch(Exception e){
				log.error("Exception in getAbuseComment in candidate details service",e);
			}
			return candidateComments;
	 }
	public List<CandidateCommentsVO> commentsDetailsFromAbuseCommeentList(List<AbusedComments> comments)
	{
		List<CandidateCommentsVO> commentsList = new ArrayList<CandidateCommentsVO>();
		 if(log.isDebugEnabled())
			 log.debug("commentsDetailsFromList()method ......");
		if(comments != null && comments.size()>0)
		{
			for(AbusedComments abusedComments :comments)
			{
				String username = null;
				CandidateCommentsVO comment = new CandidateCommentsVO();
				comment.setCandidate(abusedComments.getAbusedCommentsId().toString());
				if(abusedComments.getUser()!=null)
					username = abusedComments.getUser().getFirstName().toString()+" "+abusedComments.getUser().getLastName().toString();
				else
					username = "UnKnown";
				comment.setPostedBY(username);
				comment.setMessage(abusedComments.getComment().getComment());
				if(abusedComments.getStatus().equalsIgnoreCase(IConstants.TRUE) && abusedComments.getIsDelete().equalsIgnoreCase(IConstants.FALSE))
				     comment.setStatus(IConstants.APPROVED);
				else if(abusedComments.getStatus().equalsIgnoreCase(IConstants.FALSE) && abusedComments.getIsDelete().equalsIgnoreCase(IConstants.FALSE))
				     comment.setStatus(IConstants.NEW);
				else if(abusedComments.getIsDelete().equalsIgnoreCase(IConstants.TRUE))
				     comment.setStatus(IConstants.REJECTED);
				comment.setTime(abusedComments.getTime().toString());
				
				commentsList.add(comment);
					
			}
		}
		
		return commentsList;
	}
	public ResultStatus controlAbuseComments(List<CandidateCommentsVO> VO,String actionType)
	 {
			String status = null;
			String isDelete = null;
			String isAbused = null;
			ResultStatus resultStatus = new ResultStatus();
			DateUtilService dateUtilService = new DateUtilService();
			
			try {
				if(log.isDebugEnabled())
					log.debug("Enterd into controlMessages in candidate details service");
				
				if(actionType.equalsIgnoreCase(IConstants.APPROVED))
				{
					status = IConstants.TRUE;
					isAbused = IConstants.TRUE;
				}
				
				else if(actionType.equalsIgnoreCase(IConstants.REJECTED))
				{
					isDelete = IConstants.TRUE;
					isAbused = IConstants.FALSE;
				}
				
	           for(CandidateCommentsVO candidateCommentsVO :VO)
	           {
	        	   
	        	   Long id = candidateCommentsVO.getMessageToCandidateId();
	        	   if(actionType.equalsIgnoreCase(IConstants.APPROVED))
					{
	        		   if(candidateCommentsVO.getMessage().equalsIgnoreCase(IConstants.REJECTED))
	        			    isDelete = IConstants.FALSE;
					}

	        	   AbusedComments abusedComments =  abusedCommentsDAO.get(id);
	        	   Comment comment = abusedComments.getComment();
	        	   comment.setIsAbused(isAbused);
	        	   comment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
	        	   commentDAO.save(comment);
	        	   
	        	   abusedCommentsDAO.controlAbuseComments(id,status,isDelete);
	        	   
	        	   
	           }
	           resultStatus.setResultState(1l);
				
			} catch (Exception e) {
				if(log.isDebugEnabled())
					log.error("Exception in controlMessages in candidate details service",e);
				 resultStatus.setResultState(0l);
				 resultStatus.setExceptionEncountered(e);
			}
			return resultStatus;
	 }
	
	public String checkForMinisterData(String electionType , Long electionId){
		
		List countList = electionGoverningBodyDAO.checkForMinisterData(electionType,electionId);
		
		if(countList != null && countList.size() > 0 ){
			
			Long count = (Long)countList.get(0);
			
			if(count > 0)
			return "Exist";
		}
		return null;
	}
	
	
	
	
	public List<SelectOptionVO> getCandidateGallariesByCategory(Long categoryId , Long registrationId){
		
		
		List<Long> candidateIds = new ArrayList<Long>();
		
		List<SelectOptionVO> gallaries = new ArrayList<SelectOptionVO>();
		
		List<Object[]> candidateDetails = userCandidateRelationDAO.getCandidatesOfAUser(registrationId);
		
		
		for(Object[] obj:candidateDetails)
			candidateIds.add((Long)obj[0]);
		
		
		List<Object[]> gallaryList = fileGallaryDAO.getCandidateGallariesByCategory(candidateIds , categoryId);
		
		
		for(Object[] obj:gallaryList){
			
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			
			selectOptionVO.setId((Long)obj[0]);
			selectOptionVO.setName(obj[1].toString() +  " (" +( obj[2].toString())+ ")");
			
			gallaries.add(selectOptionVO);
			
			
		}
		return gallaries;
		
	}
	
	
	*//**
	 * This method will save comment for a file
	 *//*
	public String saveFileComment(Long fileId , String comment){
		
		try{
		
		File file = fileDAO.get(fileId);
		
		file.setComment(comment);
		
		fileDAO.save(file);
		
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
		return IConstants.SUCCESS;	
	}
	
	
	public List<SelectOptionVO> getFilesOfAGallary(Long gallaryId){
		
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		
		List<Long> gallaryIdsList = new ArrayList<Long>();
		gallaryIdsList.add(gallaryId);
		
		List<FileGallary> fileGallaryList = fileGallaryDAO
				.getFilesOfInGallaries(gallaryIdsList);
		
		for(FileGallary fileGallary : fileGallaryList){			
			
			SelectOptionVO option = new SelectOptionVO();
			
			option.setId(fileGallary.getFileGallaryId());
			option.setName(fileGallary.getFile().getFileDescription());
			
			returnList.add(option);
		}
		
		return returnList;		
	}

*//**
 * This method will prepare query to get files in a gallery based on given criteria
 * @param pdfGenerationVO
 * @return fileName
 *//*
public PdfGenerationVO generatePdfForAGallary(PdfGenerationVO pdfGenerationVO){
	
	log.debug("Entered into the generatePdfForAGallary service method");
	
	 String fileName="";
	
	try{
		
		String queryString ="";
		
		if(!pdfGenerationVO.getAllFiles().equalsIgnoreCase("true")){
			
			//if(pdfGenerationVO.getCategoryId() != 0L) 
			//	queryString += " model.file.category.categoryId = :categoryId and ";
			
			if(pdfGenerationVO.getImportanceId() != 0L)
				queryString += " model.file.newsImportance.newsImportanceId = :newsImportanceId and ";			
			
			if(pdfGenerationVO.getLanguageId() != 0L)
				queryString += " model.file.language.languageId = :languageId and ";			
			
			if(pdfGenerationVO.getImpactLevelId() != 0L)
				queryString += " model.file.regionScopes.regionScopesId = :regionScopesId and ";
				
		
			
			DateFormat formatter = new SimpleDateFormat("MM/DD/yyyy");
			
			if(pdfGenerationVO.getBetweenDates().equalsIgnoreCase("true")){
				
				Date startDate = null;
				Date endDate = null;
				
				if(!pdfGenerationVO.getStartDate().equalsIgnoreCase("")){
					
					String[] strtDt = pdfGenerationVO.getStartDate().split("/");
					 //startDate = formatter.parse(pdfGenerationVO.getStartDate());
					
					int startingMonth = Integer.parseInt(strtDt[0]);
					int startingDate = Integer.parseInt(strtDt[1]);
					int startingYear = Integer.parseInt(strtDt[2]);
					
					
					startDate = new Date();
					startDate.setDate(startingDate);
					startDate.setMonth(startingMonth - 1);
					startDate.setYear(startingYear - 1900);
					
					 pdfGenerationVO.setStartDateInDateFormat(startDate);
					
					// pdfGenerationVO.setStartDateInDateFormat(startDate);
				}
				if(!pdfGenerationVO.getEndDate().equalsIgnoreCase("")){				
					 endDate = formatter.parse(pdfGenerationVO.getEndDate());
					 
					 String[] endeDt = pdfGenerationVO.getEndDate().split("/");
					 
					    int endingMonth = Integer.parseInt(endeDt[0]);
						int endingDate = Integer.parseInt(endeDt[1]);
						int endingYear = Integer.parseInt(endeDt[2]);
						
						
						 endDate = new Date();
						 endDate.setDate(endingDate);
						 endDate.setMonth(endingMonth - 1);
						 endDate.setYear(endingYear - 1900);
						
					 //pdfGenerationVO.setEndDateInDateFormat(endDate);
						pdfGenerationVO.setEndDateInDateFormat(endDate);
						
				}
				
				if(startDate != null && endDate != null)				
					queryString += "  model.file.fileDate >= :startDate and model.file.fileDate <= :endDate and ";
				else if(startDate == null && endDate != null)
					queryString += "  model.file.fileDate <= :endDate and ";
				else if(endDate == null && startDate != null)
					queryString += "  model.file.fileDate >= :startDate and ";
				
				
			}
		}
			
		    queryString += " model.file.category.categoryId = :categoryId and ";
			queryString += " model.isDelete = :deleteInd and ";
			
		
		
		
			pdfGenerationVO = preparePdfWithMatchedFilesContentReturnFilePath(queryString , pdfGenerationVO);
		
	}catch(Exception e){
		log.error("Exception raised in  generatePdfForAGallary service method");
		e.printStackTrace();
		return null;
	}
	
	return pdfGenerationVO;
}


*//**
 * This method will get the file details for a gallery based on given criteria
 * @return
 *//*
private PdfGenerationVO preparePdfWithMatchedFilesContentReturnFilePath(String queryString,PdfGenerationVO pdfGenerationVO ) throws Exception{
	
	
	String noFiles = "false";
	List<FileVO> filesList = new ArrayList<FileVO>();	
	List<Long> fileIds = new ArrayList<Long>();	
	
	log.debug("Entered into the preparePdfWithMatchedFilesContentReturnFilePath service method");
	
	try{
	
	
	List<File> filesInGallary = fileGallaryDAO.getAllFilesInAGallry(queryString,pdfGenerationVO);
	
	for(File file:filesInGallary)			
		fileIds.add(file.getFileId());
	
	
	if(filesInGallary != null && filesInGallary.size() >0){
		
	
	  List<File> fileList = fileDAO.getAllFilesByFileIds(fileIds);		
			
		for(File file :fileList){
					
			FileVO fileCompleteDetails = new FileVO();
					
			fileCompleteDetails.setDescription(file.getFileDescription());
			fileCompleteDetails.setFileDateAsString(file.getFileDate().toString());
					
			List<FileVO> fileSourceLanguageList = new ArrayList<FileVO>();
					 
				 for(FileSourceLanguage fileSource :file.getFileSourceLanguage()){
							 
					FileVO fileSourceLanguage = new FileVO();
					fileSourceLanguage.setSource(fileSource.getSource().getSource());
					fileSourceLanguage.setSourceId(fileSource.getSource().getSourceId());
					String locationName =  getLocationDetails(fileSource.getFile().getRegionScopes().getRegionScopesId(), fileSource.getFile().getLocationValue());		 
						if(locationName!=null)
							fileSourceLanguage.setLocationScopeValue(fileSource.getFile().getRegionScopes().getScope()+" " +
							"("+locationName+")");	 
						else
							fileSourceLanguage.setLocationScopeValue(fileSource.getFile().getRegionScopes().getScope());
						
					List<FileVO> filePathList = new ArrayList<FileVO>();	
						       
						for(FilePaths filePath :fileSource.getFilePaths()){					   
							FileVO filePath1 = new FileVO();
							filePath1.setFilePath1(filePath.getFilePath() != null ?filePath.getFilePath().trim():null);
							filePathList.add(filePath1);
						}
							   
					fileSourceLanguage.setFileVOList(filePathList);				 
					fileSourceLanguageList.add(fileSourceLanguage);
						 
				 }				 
				    fileCompleteDetails.setFileVOList(fileSourceLanguageList); 				 
				    filesList.add(fileCompleteDetails);
		  }	
		
		Random random = new Random();
		
		pdfGenerationVO.setPdfName(pdfGenerationVO.getGallaryName().replaceAll(" ","_")+"_"+random.nextInt(1000000000));
			
		pdfGenerationVO =  generatePdf(filesList , pdfGenerationVO);
			  
			  
	}else
		noFiles = "true";	
	
	pdfGenerationVO.setNoFilesExist(noFiles);
	
	}catch(Exception e){
		
		log.error("Exception raised in preparePdfWithMatchedFilesContentReturnFilePath service method :"+e);
		e.printStackTrace();
		return null;
		
	}
   return pdfGenerationVO;	
}


*//**
 * This method will generate pdf for files in a gallery
 * @param filesList
 * @throws Exception
 *//*
public PdfGenerationVO generatePdf(List<FileVO> filesList , PdfGenerationVO pdfGenerationVO) throws Exception{
	log.debug("Entered into the generatePdf service method");	
	String pathToSave = pdfGenerationVO.getFilePathToSave()+"\\"+pdfGenerationVO.getGallaryName().trim()+".pdf";
	
	pdfGenerationVO.setPdfPath(pathToSave);
	try {
		
		Document document = new Document();
		//PdfWriter.getInstance(document , new FileOutputStream("D:/Samba/ItextExamples/"+pdfGenerationVO.getGallaryName()+".pdf"));
		PdfWriter.getInstance(document , new FileOutputStream(pathToSave));
		document.open();
		
		Paragraph preface = new Paragraph();

		
		for(int i=0;i<6;i++)
			document.add(new Paragraph(" "));
		
		//preface.add(new Paragraph(pdfGenerationVO.getGallaryName()+" Gallary   Files", new Font(Font.FontFamily.TIMES_ROMAN, 22,
			  //    Font.BOLD , BaseColor.DARK_GRAY)));
		Date date = new Date();
		
		Paragraph gallery = new Paragraph("Gallery Name\u00a0\u00a0\u00a0\u00a0\u00a0: "+pdfGenerationVO.getGallaryName(), new Font(
				Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD,
				BaseColor.GRAY));		
		gallery.setAlignment(Paragraph.ALIGN_CENTER);		
		document.add(gallery);
		
		Paragraph category = new Paragraph("Catogery Name\u00a0\u00a0\u00a0\u00a0: "+categoryDAO.get(pdfGenerationVO.getCategoryId()).getCategoryType(), new Font(
				Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD,
				BaseColor.GRAY));		
		category.setAlignment(Paragraph.ALIGN_CENTER);		
		document.add(category);
		
		//Paragraph newsCount = new Paragraph("Total News Count\u00a0\u00a0\u00a0: "+filesList.size(), new Font(
		//		Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD,
		//		BaseColor.GRAY));		
		//newsCount.setAlignment(Paragraph.ALIGN_CENTER);			
		//document.add(newsCount);
		
		Paragraph generatedDate = new Paragraph("Pdf Generate Date: "+date.getDate()+"/"+(date.getMonth()+1)+"/"+(date.getYear()+1900), new Font(
				Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD,
				BaseColor.GRAY));
		generatedDate.setAlignment(Paragraph.ALIGN_CENTER);			
		document.add(generatedDate);
		
		
		
		
		
		for(int i=0;i<2;i++)
			preface.add(new Paragraph(" "));
		
		
		//preface.add(new Paragraph("Report generated  On" + date.getDate()+"/"+(date.getMonth()+1)+"/"+(date.getYear()+1900), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				//new Font(Font.FontFamily.TIMES_ROMAN, 12,
				//	      Font.BOLD ,BaseColor.DARK_GRAY)));
		
		document.add(preface);
		
		document.newPage();
		
		for(FileVO mainFile:filesList){	
			
			 Chunk underline = new Chunk(mainFile.getDescription());
			 underline.setUnderline(0.1f, -2f);
			
			Paragraph description = new Paragraph(mainFile.getDescription(), new Font(
					Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL,
					BaseColor.RED));
			description.setAlignment(Paragraph.ALIGN_CENTER);	
			
			description.add(new Paragraph(""));
		
			 int j=0;
			
			for(FileVO fileSource:mainFile.getFileVOList()){
				
				
				 document.newPage();
				
			      if(pdfGenerationVO.getSourceId() != 0)
			    	  if(fileSource.getSourceId().longValue() != pdfGenerationVO.getSourceId().longValue())
			    		  continue;
			      
			      if( j == 0)
			    	  document.add(description);
			      
			    
				
				Paragraph sourcePage = new Paragraph();
				
					sourcePage.add(new Paragraph("DATE - "
							+ mainFile.getFileDateAsString()
							+ "                              " + "                                  \t\t\tSOURCE - "
							+ fileSource.getSource(), new Font(
							Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
							BaseColor.DARK_GRAY)));
					sourcePage.add(new Paragraph("REGION SCOPE : "+fileSource.getLocationScopeValue(),new Font(
								Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
								BaseColor.DARK_GRAY)));
				
				
				//sourcePage.add("DATE:"+mainFile.getFileDateAsString());
				//sourcePage.add("                              ");
				//sourcePage.add("SOURCE:"+fileSource.getSource());
				
				 for(int i=0;i<2;i++)
					 sourcePage.add(new Paragraph(" "));
				 
				 for(FileVO filePath:fileSource.getFileVOList()){
					 String path = filePath.getFilePath1();
					 
					// path  = "C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/PartyAnalyst/"+path;
					 
					 path  = pdfGenerationVO.getImagePath()+"//"+path;
					 
					 
					Image image = Image.getInstance(path);
					
					image.setAlignment(Image.ALIGN_MIDDLE);

					// Image image = Image.getInstance(pathToSave);
					 image.scaleToFit(400, 400);
					 image.setBorder(2);
					 image.setBorderColor(new BaseColor(Color.RED));
					 //image.scaleToFit(500, 500);
					// image.scaleToFit(450, 450);
					 sourcePage.add(image);
					 
					 sourcePage.add(new Paragraph(" "));					 
				 }				 
				 document.add(sourcePage);
				 
				  j++;
			}
			
			
			
			document.newPage();
			
		}
		document.close();
		
	} catch (Exception e) {
		
		log.error("Exception raised in  generatePdf service method :" +e);
		e.printStackTrace();
		return null;
	}
	
	
	return pdfGenerationVO;
	
}
*//**
 * This Method Is Used To Get The Candidate Details Based On Candidate Name Search Criteria ,
 * gender , ConstituencyId and StateId
 * @author Prasad Thiragabathina
 * @param String gender
 * @param String name
 * @param Long constituencyId
 * @param Long stateId
 * @return List<SelectOptionVO>
*//*
public List<SelectOptionVO> getCandidateDetailsBySearch(String gender,
		String name, Long constituencyId, Long stateId,String selectedType) {
	List<SelectOptionVO> returnValue = new ArrayList<SelectOptionVO>();
	try
	{
		log.debug("Entered into the getCandidateDetailsBySearch() method");
		List<Object[]> results = null;
		
		if(selectedType.equalsIgnoreCase("") || selectedType.equalsIgnoreCase("assembly") || selectedType.equalsIgnoreCase("parliament"))
	      results = candidateDAO.getCandidateDetailsBySearch(gender,name,constituencyId,stateId,selectedType);
		else{
			
			List list = delimitationConstituencyMandalDAO.getLatestMandalDetailsForAConstituency(constituencyId);
			
			List<Long> tehsilIds = new ArrayList<Long>();
			
			for(Object obj:list){
				
				Object[] obj1 = (Object[])obj;
				tehsilIds.add((Long)obj1[0]);
			}
			
			Long electionScopeId = null;
			
			if(selectedType.equalsIgnoreCase("mptc"))
					electionScopeId = IConstants.MPTC_ELCTION_TYPE_ID;
			else if(selectedType.equalsIgnoreCase("zptc"))
			electionScopeId = IConstants.ZPTC_ELCTION_TYPE_ID;
					
			
			
			results = nominationDAO.getMptcAndZptcCandidateNamesByTehsilIds(tehsilIds,electionScopeId);
				
	
			
			
		}
	   for(Object[] candidateDetails: results)
	   {
		  SelectOptionVO selectOptionVO = new SelectOptionVO();
		  selectOptionVO.setId((Long)candidateDetails[0]);
		  selectOptionVO.setName(candidateDetails[1] != null ? candidateDetails[1].toString() :"");
		  selectOptionVO.setType(candidateDetails[2] != null ? candidateDetails[2].toString():"");
		  returnValue.add(selectOptionVO);
	   }
	  return returnValue;
	}
	catch(Exception e)
	{
		log.error("error occured in the getCandidateDetailsBySearch() method");
		e.printStackTrace();
		return returnValue;
	}
}
*//**
 * This Method Is Used To Store Voter Details Into The Candidate
 * @author Prasad Thiragabathina
 * @param  Long CandidateId
 * @param Long voterId
 * @return ResultStatus
 *//*	
@SuppressWarnings("unchecked")
public ResultStatus saveCandidateVoterDetails(Long CandidateId, Long voterId) {
	ResultStatus resultStatus = new ResultStatus();
	try {
		log.debug("Entered into the saveCandidateVoterDetails() method");
		if(voterId != null && voterId.longValue() > 0l)
		{
			Candidate candidate =  candidateDAO.get(CandidateId);
			
			if(candidate != null )
			{	
				candidate.setVoter(voterDAO.get(voterId));
				candidateDAO.save(candidate);
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}
		return resultStatus;
	} catch (Exception e) {
		log.debug("Error occured in saveCandidateVoterDetails() method");
		e.printStackTrace();
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		return resultStatus;
	}
	
}
*/
public List<FileVO>	getFilesOfACategory(Long gallaryId,Integer startIndex,Integer endIndex,String newsType,Long categoryId,String fromDateStr,String toDateStr)
{
	List<FileVO> returnList = new ArrayList<FileVO>();
	
	try{
		List<File> fileList = null;
		
		Long count = 0L;
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
		 fromDate = format.parse(fromDateStr);
		if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
			toDate = format.parse(toDateStr);
		fileList=fileDAO.getFilesByCategoryId(categoryId,startIndex,endIndex,newsType,fromDate,toDate);
		 count = fileDAO.getFilesByCategoryIdCount(categoryId,newsType,fromDate,toDate);
		if(fileList != null && fileList.size() > 0)
		{
			setDataToFileVo(fileList,returnList);
			
		}
		if(returnList != null && returnList.size() > 0)
		returnList.get(0).setTotalResultsCount(count);
	}
	catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	return returnList;
}
 public List<FileVO> getFilesOfAGallary(Long gallaryId , int startIndex , int endIndex,String newsType,Long categoryId,String fromDateStr,String toDateStr,String requestFor){
		
		List<FileVO> returnList = new ArrayList<FileVO>();
		
		List<Long> gallaryIdsList = new ArrayList<Long>();
		gallaryIdsList.add(gallaryId);
	 try{
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
		 fromDate = format.parse(fromDateStr);
		if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
			toDate = format.parse(toDateStr);
		
		/*List<FileGallary> fileGallaryList = fileGallaryDAO
				.getFilesOfGallaries(gallaryIdsList,startIndex,endIndex,newsType,categoryId,fromDate,toDate,requestFor);*/
		
		
		List<FileGallary> fileGallaryList = null;
		Long count = 0L;
		
		if(requestFor != null && requestFor.equalsIgnoreCase("respondedNews"))
		{
		  fileGallaryList = fileGallaryDAO.getFileGallaryByGalleryIdsList(gallaryIdsList, startIndex, endIndex, newsType, categoryId, fromDate, toDate, requestFor);
		  count = (long)fileGallaryDAO.getFileGallaryByGalleryIdsList(gallaryIdsList, null, null, newsType, categoryId, fromDate, toDate, requestFor).size();
		}
		else
		{
			fileGallaryList = fileGallaryDAO
					.getFilesByGalleryIdsList(gallaryIdsList,startIndex,endIndex,newsType,categoryId,fromDate,toDate,requestFor);
			
			count = fileGallaryDAO.getAllRecordsCountInGallary(gallaryId,newsType,categoryId,fromDate,toDate).get(0);
		}
				
		for(FileGallary fileGallary : fileGallaryList){
			if(fileGallary.getFile() !=null){
				
					FileVO file = new FileVO();
									
									file.setFileId(fileGallary.getFile().getFileId());
									file.setFileGallaryId(fileGallary.getFileGallaryId());
									file.setFileName1(fileGallary.getFile().getFileTitle() !=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileTitle())):"");
									file.setFileDescription1(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileDescription()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileDescription())):""));
									file.setFileDate(new SimpleDateFormat("yyyy-MM-dd").format(fileGallary.getFile().getFileDate()));
									file.setResponseCount(candidateNewsResponseDAO.getFileGalleryIdByResponseGalleryId((Long)fileGallary.getFileGallaryId()).size());
									
									if(fileGallary.getFile().getCategory() != null){
									file.setCategoryId(fileGallary.getFile().getCategory().getCategoryId());
									file.setCategoryName(fileGallary.getFile().getCategory().getCategoryType());
									}
									
									file.setFilePath1(fileGallary.getFile().getFilePath());
									
									Set<FileSourceLanguage> set = fileGallary.getFile().getFileSourceLanguage();
									
									String sourceString = "";
									for(FileSourceLanguage source:set)
										sourceString+=source.getSource().getSource()+" ";
									
									file.setFileType(sourceString);
									
									
									returnList.add(file);
									returnList.get(0).setTotalResultsCount(count);
			}
		}
	 }catch (Exception e) {
		 e.printStackTrace();
		 log.error(" Exception Occured in getFilesOfAGallary() method, Exception - ",e);
	}
		
		return returnList;		
	}
 
 public List<FileVO> getLatestResponsedNews(){
	 List<FileVO> fileVoList=new ArrayList<FileVO>();
	// List<Object[]> fileGalList=partyGalleryDAO.getLatestNewsResponses();
	 List<Object[]> fileGalList=newsResponseDAO.getLatestNewsResponses();
	 
	 for(Object[] objs:fileGalList){
		 FileVO file=new FileVO();
		 file.setFileId((Long)objs[0]);
		 file.setTitle(objs[1]!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(objs[1].toString())):"");
		 file.setFileDate(objs[2]!=null?objs[2].toString():"");
		// file.setSource(objs[3]!=null?objs[3].toString():"");
		 file.setFontId((objs[3]!=null?(Integer)objs[3]:0));
		 fileVoList.add(file);
	 }
	 return fileVoList;
 }

public List<SelectOptionVO> getLatestgallaries()
{
	   List<SelectOptionVO> gallariesList = new ArrayList<SelectOptionVO>();
	   
		List<Object[]> newsList = partyGalleryDAO.getPartyGallaryDetail(IConstants.TDPID, 

IConstants.NEWS_GALLARY,0,5);
		
		for(Object[] obj:newsList){
			SelectOptionVO vo = new SelectOptionVO();
			vo.setId((Long)obj[0]);
			vo.setName(obj[1].toString());
			gallariesList.add(vo);
		}
		
		return gallariesList;


}

public List<FileVO> getVideosForSelectedParty(Long partyId,String newsType)
{
	List<FileVO> file = null;
	List<Object[]> filesList = fileGallaryDAO.getAllVideoFilesOfInGallaries(0,6,newsType);
	
	if(filesList != null && filesList.size() > 0)
	{
		file = new ArrayList<FileVO>();
		for (Object[] fileGallary : filesList) {			
			FileVO fileVO = new FileVO();
			fileVO.setFileId((Long) fileGallary[0]);
			fileVO.setDescription(fileGallary[1].toString());
			fileVO.setFilePath1(fileGallary[2].toString());
			file.add(fileVO);
		}
	}
	
	return file;
}

public List<FileVO> getVideosListForSelectedFile(Long fileId)
{
	List<FileVO> fileList = null;
	FileVO fileVO = null;
	List<Long> fileSourceIds = null;
	List<Long> fileSourceLIst = fileSourceLanguageDAO.getFileSourceIdsBasedOnFile(fileId);
	if(fileSourceLIst != null && fileSourceLIst.size() > 0)
	{
		fileSourceIds = new  ArrayList<Long>();
		for (Long fileSourceId : fileSourceLIst) {
			fileSourceIds.add(fileSourceId);
		}
	}
	List<Object[]> filePathsList = filePathsDAO.getFilePathsBasedOnFileSource(fileSourceIds);
	if(filePathsList != null && filePathsList.size() > 0)
	{
		fileList = new ArrayList<FileVO>();
		for (Object[] objects : filePathsList) {
			fileVO = new FileVO();
			fileVO.setIds((Long)objects[0]);
			fileVO.setFilePath1(objects[1].toString());
			//fileVO.setFileData((File) objects[2]);
			fileList.add(fileVO);
		}
	}
	/*List<FileGallary> videos = fileGallaryDAO.getAllVodeosForSelectedFile(fileId);
	if(videos != null && videos.size() > 0)
	{
		fileList = new ArrayList<FileVO>();
		for (FileGallary fileGallary : videos) {
			fileVO = new FileVO();
			fileVO.setFileId(fileGallary.getFile().getFileId());
			fileVO.setFileDescription1(fileGallary.getFile().getFileDescription().toString());
			fileVO.setFilePath1(fileGallary.getFile().getFilePath());
			fileList.add(fileVO);
		}
	}*/
	
	
	return fileList;
}


 					 
	public List<FileVO> getAllNews(int startIndex,int maxIndex,String contenttype,Long partyId,String newsType)
	{
		List<FileVO> fileVOsList = new ArrayList<FileVO>(0);
		try{
		
		 List<File> fileList = fileGallaryDAO.getRecentlyUploadedNewsDetails(startIndex, maxIndex);
		 if(fileList != null && fileList.size() > 0)
		 {
			 setfileDetails(fileList, fileVOsList);
		 }
		 
		 fileVOsList.get(0).setCount(fileGallaryDAO.getRecentlyUploadedNewsDetailsCount().intValue());
		 return fileVOsList;
		 
		
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getRecentlyUploadedNews() method, Exception - ",e);
			 return fileVOsList;
		}
	}
	
	
	
	
	public List<FileVO> getRecentlyUploadedNewsTitles(int startIndex,int maxIndex,String contenttype,Long partyId,String newsType)
    {
        List<FileVO> fileVOsList = new ArrayList<FileVO>(0);
        try{
       
       
         List<Object[]> fileGallaryList = fileGallaryDAO.getRecentlyUploadedNews(startIndex, maxIndex, contenttype,partyId,newsType);
         if(fileGallaryList != null && fileGallaryList.size() > 0)
         {
             for(Object[] params : fileGallaryList)
             {
                FileVO fileVO = new FileVO();
                fileVO.setContentId((Long)params[0]);
                fileVO.setFileTitle1(params[1]!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(params[1].toString())):"");
                //fileVO.setSource(params[2] != null? params[2].toString():"");
                if(params[2] != null){
                  fileVO.setSource("Eenadu Telugu");
                }else{
                     fileVO.setSource("others");
                }
                fileVOsList.add(fileVO);
             }
         }
         
         return fileVOsList;
         
       
        }catch (Exception e) {
            log.error("Exception Occured in getRecentlyUploadedNews() method, Exception - ",e);
             return fileVOsList;
        }
    }

	 public List<FileVO> getAllVideosList(Long partyId,int firstResult,int maxResult,String queryType)
	 {
	 	List<FileVO> fileList = null;
	 	FileVO fileVO = null;
	 	
	 	List<Object[]> gallariesList = partyGalleryDAO.getAllVideosOfParty(partyId, firstResult, maxResult, queryType);
	 	int count=partyGalleryDAO.getAllVideosOfPartyCount(partyId, firstResult, maxResult, queryType);
	 	
	 	
	 	if(gallariesList != null && gallariesList.size() > 0)
	 	{
	 		fileList = new ArrayList<FileVO>();
	 		for (Object[] objects : gallariesList) {
	 			fileVO = new FileVO();
	 			fileVO.setIds((Long)objects[0]);
	 			fileVO.setName(objects[1].toString());
	 			fileVO.setDescription(objects[2].toString());
	 			fileVO.setPath(objects[3].toString());
	 			fileVO.setGallaryCreatedDate(objects[4].toString());
	 			fileVO.setCount(count);
	 			Long count1=fileGallaryDAO.getVideosCountIntheGallary((Long)objects[0],queryType);
	 			fileVO.setTotalResultsCount(count1);
	 			fileList.add(fileVO);
	 		}
	 	}
	 	/*List<FileGallary> videos = fileGallaryDAO.getAllVodeosForSelectedFile(fileId);
	 	if(videos != null && videos.size() > 0)
	 	{
	 		fileList = new ArrayList<FileVO>();
	 		for (FileGallary fileGallary : videos) {
	 			fileVO = new FileVO();
	 			fileVO.setFileId(fileGallary.getFile().getFileId());
	 			fileVO.setFileDescription1(fileGallary.getFile().getFileDescription().toString());
	 			fileVO.setFilePath1(fileGallary.getFile().getFilePath());
	 			fileList.add(fileVO);
	 		}
	 	}*/
	 	
	 	
	 	return fileList;
	 }
	 public List<FileVO> getVideosForGalleryId(Long galId,int maxRecord,int startRecord){
		 List<FileVO> fileList = null;
		 FileVO fileVO = null;
		 List<Object[]> videosList = fileGallaryDAO.getVideosOfGalleryId(galId,maxRecord,startRecord);
		 int count=fileGallaryDAO.getVideosCountOfGalleryId(galId);
		 
		 if(videosList != null && videosList.size() > 0)
		 	{
		 		fileList = new ArrayList<FileVO>();
		 		for (Object[] objects : videosList) {
		 			fileVO = new FileVO();
		 			fileVO.setIds((Long)objects[1]);
		 			fileVO.setTitle(objects[2]!=null?objects[2].toString():null);
		 			fileVO.setDescription(objects[3]!=null?objects[3].toString():null);
		 			fileVO.setPath(objects[0]!=null?objects[0].toString():null);
		 			fileVO.setCount(count);
		 			fileList.add(fileVO);
		 		}
		 	}
		 
		 return fileList;
	 }
	 public List<SelectOptionVO> getCandidateGallaries(Long registrationId,String contentType){
			try{
				log.debug("Entered into getCandidateGallarySelectList() Method");
				
				List<SelectOptionVO> gallarySelectList = null;
				
				List<Long> candidateIds = new ArrayList<Long>();
				
				/*//List<Object[]> candidateDetails = userCandidateRelationDAO.getCandidatesOfAUser(registrationId);
				
				
				for(Object[] obj:candidateDetails)
					candidateIds.add((Long)obj[0]);
				*/
				//List<Object[]> list = gallaryDAO.getGallariesByCandidateIds(candidateIds,contentType);
				List<Object[]> list = gallaryDAO.getAllGallaries(contentType);
				
				if(list != null && list.size() > 0)
				{
					gallarySelectList = new ArrayList<SelectOptionVO>(0);
					SelectOptionVO selectOptionVO = null;
					for(Object[] params : list)
					{
						selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)params[0]);
						/*if(params[2] == null)
						params[2] = "";
						if(params[3] == null)
							params[3] = "";
						
						selectOptionVO.setName(params[1].toString()+" - "+params[2].toString()+" "+params[3].toString());*/
						selectOptionVO.setName(params[1].toString());
						gallarySelectList.add(selectOptionVO);
					}
				}
				return gallarySelectList;
			}catch (Exception e) {
				log.error("Exception Occured in getCandidateGallarySelectList() method - ",e);
				return null;
			}
		}
 
	
	 public List<SelectOptionVO> buildSelectOptionVO(List<Object[]> list)
	 {
		 List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
		 try
		 {			 
		   if(list != null && list.size() > 0)
		   {
			 for(Object[] obj:list){
				 
				 SelectOptionVO selectOptionVO = new SelectOptionVO();
				 
				 selectOptionVO.setId((Long)obj[0]);
				// selectOptionVO.setName(obj[1].toString());
				 
				 selectOptionVO.setName(obj[1]!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(obj[1].toString())):"");
	    	
				 File file =(File) obj[2];
				 
				 Set<FileSourceLanguage> languages = file.getFileSourceLanguage();
				 
				 boolean eenaduExist = false;
				 if(languages != null && languages.size() >0){
					 
					 for(FileSourceLanguage language:languages)						
						 if(language.getSource().getSource().equalsIgnoreCase("Eenadu Telugu"))
							 eenaduExist = true;
					
					 
				 }
				 
				 selectOptionVO.setFlag(eenaduExist);
				 
				 resultList.add(selectOptionVO);
				 
			 }
		   }
			 
		 }catch(Exception e)
		 {
			e.printStackTrace();			 
		 }		 
		 return resultList;
		 
	 }
	 
	 /*public List<FileVO> getCandidatesNews(Long candidateId,int firstRecord,int maxRecord,String type){
		 
		try{
			log.debug("In CandidateDetailsService getCandidatesNews Method");
			List<FileVO> fileVOList = null;
			FileVO fileVO = null;
			List<Object[]> candidatesNews=new ArrayList<Object[]>();
			candidatesNews=partyGalleryDAO.getNewsOfCandidate(candidateId, firstRecord, maxRecord, type);
			int count=partyGalleryDAO.getCountOfNewsOfCandidate(candidateId, firstRecord, maxRecord, type);
			
			
			if(candidatesNews != null && candidatesNews.size() > 0)
		 	{
				fileVOList = new ArrayList<FileVO>();
		 		for (Object[] objects : candidatesNews) {
		 			fileVO = new FileVO();
		 			fileVO.setIds((Long)objects[1]);
		 			int responseCount=partyGalleryDAO.getResponseNewsCountOfCandidate(candidateId, type, fileVO.getIds());
		 			fileVO.setTitle(objects[2]!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(objects[2].toString())):null);
		 			fileVO.setDescription(objects[3]!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(objects[3].toString())):null);
		 			fileVO.setPath(objects[0]!=null?objects[0].toString():null);
		 			fileVO.setCount(count);
		 			fileVO.setSource(objects[4]!=null?objects[4].toString():null);
		 			fileVO.setResponseCount(responseCount);
		 			fileVOList.add(fileVO);
		 		}
		 	}
			return fileVOList;
			
		}catch (Exception e) {
			log.debug("Exception In CandidateDetailsService getCandidatesNews Method "+e);
			
		}
		return null;
	 }
	 */
	 
  public List<FileVO> getCandidatesNews(Long candidateId,int firstRecord,int maxRecord,String type,String fromDateStr, String toDateStr,String gallaryIdsStr,String categoryIdsStr){
		 List<FileVO> fileVOList = null;
	 try{
		 Date fromDate = null;
		 Date toDate = null;
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 if(fromDateStr != null && !fromDateStr.equalsIgnoreCase("") && !fromDateStr.equalsIgnoreCase("null"))
			fromDate = format.parse(fromDateStr);
		 if(toDateStr != null && !toDateStr.equalsIgnoreCase("") && !toDateStr.equalsIgnoreCase("null"))
			 toDate = format.parse(toDateStr);
		 
		 List<Long> gallaryIdsList = new ArrayList<Long>(0);
		 List<Long> categoryIdsList = new ArrayList<Long>(0);
		 StringTokenizer str = null;
		 if(gallaryIdsStr!=null && !gallaryIdsStr.equalsIgnoreCase("") && !gallaryIdsStr.equalsIgnoreCase("null"))
		 {
		   str = new StringTokenizer(gallaryIdsStr,",");
		   while(str.hasMoreTokens())
			   gallaryIdsList.add(Long.parseLong(str.nextToken()));
		 }
		 
		 if(categoryIdsStr != null && !categoryIdsStr.equalsIgnoreCase("") && !categoryIdsStr.equalsIgnoreCase("null"))
		 {
			 str = new StringTokenizer(categoryIdsStr,","); 
			 while (str.hasMoreTokens()) 
				categoryIdsList.add(Long.parseLong(str.nextToken()));
		 }
		 List<FileGallary> fileGallariesList = candidateRelatedNewsDAO.getFileGallaryListByCandidateId(candidateId, firstRecord, maxRecord, type,fromDate,toDate,gallaryIdsList,categoryIdsList);
		 if(fileGallariesList != null && fileGallariesList.size() > 0)
		 {
			fileVOList = new ArrayList<FileVO>(0);
			//setfileGallaryDetails(fileGallariesList, fileVOList);
			fileVOList.get(0).setCount(candidateRelatedNewsDAO.getFileGallaryListByCandidateId(candidateId, null, null, type,fromDate,toDate,gallaryIdsList,categoryIdsList).size());
		  }
		 return fileVOList;
	}catch (Exception e) {
				log.debug("Exception Occured in getCandidatesNews() Method, Exception - ",e);
				return fileVOList;
		}
	}
		 
	 
	 public List<SelectOptionVO> getNewsContainedCandidates()
	 {
			try{
				List<Object[]> list = candidateRelatedNewsDAO.getCandidates();
				
				List<SelectOptionVO> cadidatesList = null;
				if(list != null && list.size() > 0)
				{
					cadidatesList = new ArrayList<SelectOptionVO>(0);
					SelectOptionVO selectOptionVO = null;
					for(Object[] params : list)
					{
						selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)params[0]);
						
						if(params[1] != null)
						if(params[1].toString().substring(0, 1).equalsIgnoreCase("."))
							  selectOptionVO.setName(params[1].toString().substring(1));
						else
							  selectOptionVO.setName(params[1].toString());
						
						cadidatesList.add(selectOptionVO);
					}
				}
				
				return cadidatesList;
			}catch(Exception e){
				return null;
			}
		}

	 
	 public List<FileVO> getNewsBetweenSelectedDates(String fromDateStr,String toDateStr,Integer starIndex, Integer maxResults, String newsType, String level)
	 {
		 List<FileVO> fileVOsList = new ArrayList<FileVO>(0);
		 try{
			 Date fromDate = null;
			 Date toDate = null;
			 Long scopeID = 0l;
			 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null)
				fromDate = format.parse(fromDateStr);
			 
			if(toDateStr != null)
				toDate = format.parse(toDateStr);
			if(level.equalsIgnoreCase("state"))
				scopeID = 2l;
			else if(level.equalsIgnoreCase("district"))
				scopeID = 3l;
			
			List<File> fileList = fileGallaryDAO.getNewsDetailsBetweenSelectedDates(fromDate, toDate, starIndex, maxResults, scopeID);
			if(fileList != null && fileList.size() > 0)
			{
				setfileDetails(fileList, fileVOsList);
			    fileVOsList.get(0).setCount(fileGallaryDAO.getNewsDetailsBetweenSelectedDatesCount(fromDate, toDate, scopeID).intValue());
			}
			
			 return fileVOsList;
		 }catch (Exception e) {
			 log.error("Exception Occured in getNewsBetweenSelectedDates() method, Exception - ",e);
			 return fileVOsList;
		}
	 }
	 
	 public String insertMLCCandidateDetails(final Long partyId ,final String candidateName ,final String  education , final String gender , final Long userId,final Long designationId,final Long locationId,final Long locationValue)
	 {
		 
		 try
		 {
			 
			 transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
			 Candidate candidate = new Candidate();
			 
			 candidate.setLastname(candidateName);
			 candidate.setEducation(education);
			 candidate.setGender(gender);
			 candidate.setDesignation(designationDAO.get(designationId));
			 if(locationId == 1)
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
			 else if(locationId == 2)
			 {
				 Long stateId = constituencyDAO.getPcConstituency(locationValue).get(0);
				  if(stateId > 0)
				  {
					  candidate.setState(stateDAO.get(stateId));
					  candidate.setParliament(constituencyDAO.get(locationValue)); 
				  }
			 }
			 else if(locationId == 3)
			 {
				 candidate.setState(stateDAO.get(1l));
			 }
			 else 
			 {
				 candidate.setState(stateDAO.get(1l));
			 }
			 candidate.setParty(partyDAO.get(partyId));
			 
			 candidate = candidateDAO.save(candidate);
			
			 /*CandidateParty candidateParty = new CandidateParty();
			 
			 candidateParty.setCandidateId(candidate.getCandidateId());
			 candidateParty.setPartyId(partyId);
			 candidateParty.setElectionTypeId(IConstants.MLC_ELECTION_SCOPE_ID);
			 candidateParty.setUserId(userId);
			 
			 candidatePartyDAO.save(candidateParty);*/
			}
		});
			 
			 
			 return "success";
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return null;
			 
		 }		 
		 
	 }
	
	 public String insertChannelDetails(final String channelName )
	 {
		 
		 try
		 {
			 
			 transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						
			Channel channel = new Channel();
			 
			channel.setChannelName(channelName);
			
			channel = channelDAO.save(channel);
			 
			 }
		});
			 
			  return "success";
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return null;
			 
		 }		 
		 
	 }
	 public String insertObserverDetails(final String observerName )
	 {
		 
		 try
		 {
			 
			 transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						
			Observer observer = new Observer();
			 
			observer.setObserverName(observerName);
			
			observer= observerDAO.save(observer);
			 
			 }
		});
			 
			  return "success";
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return null;
			 
		 }		 
		 
	 }
	 
		public ResultStatus createUserNewsCategory(String name, String visibility, Long userId,Long mainCategoryId)
		{
			ResultStatus resultStatus = new ResultStatus();
			try{
				List<String> list = categoryDAO.checkCategoryNameExist(name.trim());
				if(list != null && list.size() > 0)
				{
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					resultStatus.setMessage("News Category is Already Exists.");
					return resultStatus;
				}
				
				Long orderNo = categoryDAO.getMaxOrderNo();
				if(orderNo == null || orderNo == 0L)
					orderNo = 1L;
				else
					orderNo = orderNo+1;
				Category category = new Category();
				category.setCategoryType(name.trim());
				category.setOrderNo(orderNo);
				category.setUserId(userId);
				category.setIsPrivate(visibility);
				category.setIsDelete(IConstants.FALSE);
				category.setCreatedDate(dateUtilService.getCurrentDateAndTime());
				category.setUpdateddate(dateUtilService.getCurrentDateAndTime());
				if(mainCategoryId != null && mainCategoryId > 0)
				category.setMaincategory(mainCategoryDAO.get(mainCategoryId));
				category = categoryDAO.save(category);
				
				/*UserNewsCategory userNewsCategory = new UserNewsCategory();
				userNewsCategory.setIsPrivate(visibility);
				userNewsCategory.setIsDelete("false");
				userNewsCategory.setCreatedDate(dateUtilService.getCurrentDateAndTime());
				userNewsCategory.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
				userNewsCategory.setUser(userDAO.get(userId));
				userNewsCategory.setCategory(category);
				userNewsCategoryDAO.save(userNewsCategory);*/
				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
				
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in createUserNewsCategory() method, Exception - ",e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
	
}
		
		public List<CategoryVO> getAllCategoriesOfUser(Long userId){
			List<CategoryVO> categoriesList=new ArrayList<CategoryVO>();
			try{
				//List<Object[]> categories=userNewsCategoryDAO.allCategoriesOfUser(userId);
				List<Object[]> categories = categoryDAO.getCategoriesByUserId(userId);
				if(categories != null && categories.size()>0){
					for(Object[] param:categories){
						CategoryVO co=new CategoryVO();
						co.setId(Long.valueOf(param[0].toString()).longValue());
						co.setName(param[1] != null?param[1].toString():"");
						co.setIsDeleted(param[2] != null?param[2].toString():"");
						co.setVisibility(param[3] != null?param[3].toString():"");
						categoriesList.add(co);
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getAllCategoriesOfUser() ,Exception-",e);
			}
			
			
			return categoriesList;
			
		}
		public ResultStatus updateCategory(final Long userId,final Long categoryId,final String cateName,final String visibility){
				
			
				ResultStatus resultStatus = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					ResultStatus rs=new ResultStatus();
					
					List<Object[]> list = categoryDAO.checkCategoryNameIdExist(cateName.trim());
					if(list != null && list.size() > 0)
					{
						for(Object[] param:list){
							Long id=Long.valueOf(param[1].toString()).longValue();
							if(id!=categoryId){
								rs.setResultCode(ResultCodeMapper.FAILURE);
								rs.setMessage("News Category is Already Exists.");
								return rs;
							}
						}
						
					}
					
					/*int updated=userNewsCategoryDAO.updateCategory(userId, categoryId, cateName, visibility);
				
					int updateNow=userNewsCategoryDAO.updateCategoryName(userId, categoryId, cateName);*/
					
					Category category = categoryDAO.get(categoryId);
					category.setCategoryType(cateName.trim());
					category.setIsPrivate(visibility);
					category.setUpdateddate(dateUtilService.getCurrentDateAndTime());
					categoryDAO.save(category);
					
					rs.setResultCode(ResultCodeMapper.SUCCESS);
					
					return rs;
					}
					
				});
				return resultStatus;
				
		}
		public ResultStatus updateCategoryStatus(Long userId,Long categoryId,String name){
			ResultStatus res=new ResultStatus();
			String status="";
			if(name.equalsIgnoreCase("delete")){
				 status="true";
			}else{
				status="false";
			}
				try{
					
					//int del=userNewsCategoryDAO.updateCategoryStatus(userId, categoryId, status);
					int del = categoryDAO.updateCategoryByUserId(userId, categoryId, status);
					
				}catch (Exception e) {
						System.out.println(e);
						res.setResultCode(ResultCodeMapper.FAILURE);
						res.setExceptionMsg("Exception Raised..Please Try again later");
				}
			res.setResultCode(ResultCodeMapper.SUCCESS);
			return res;
		}
		
		public List<SelectOptionVO> getNewsForCandidate(Long candidateId , Date fromDate , Date toDate)
		 {
			 
			return buildSelectOptionVO(candidateRelatedNewsDAO.getAllfileGallariesOfCandidate(candidateId , fromDate , toDate));
			 
			 
		 }
		
		public List<SelectOptionVO> getAllGallariesOfAParty(Long partyId)
		{
			
		  List<SelectOptionVO> partyGallariesList = new ArrayList<SelectOptionVO>();
		  
		  try
		  {
			
          List<Object[]> gallaries = gallaryDAO.getAllGallariesForParty(partyId , IConstants.NEWS_GALLARY);
        
        
	        for(Object[] obj:gallaries)
	        {
	        	SelectOptionVO vo = new SelectOptionVO();
	        	
	        	vo.setId((Long)obj[0]);
	        	vo.setName(obj[1].toString());
	        	partyGallariesList.add(vo);
	        	
	        }
	        
		  }catch(Exception e)
		  {
			  e.printStackTrace();
			  
		  }
	        return partyGallariesList;
			 
		}
		
		public List<SelectOptionVO> getAllTheFilesOfAGallary(Long gallaryId  , Date fromDate , Date toDate)
		{
			//return fileGallaryDAO.getAllFilesOfAGallaryByGallaryId(gallaryId,  fromDate ,  toDate);
			
			return buildSelectOptionVO(fileGallaryDAO.getAllFilesOfAGallaryByGallaryId(gallaryId,  fromDate ,  toDate));
		}
		
		public List<SelectOptionVO> getCandidateRelatedGallaries(Long candidateId,String fromDateStr,String toDateStr,Long partyId,String queryType)
		{
			List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
			try{
				Date fromDate = null;
				Date toDate = null;
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
				 fromDate = format.parse(fromDateStr);
				if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
				 toDate = format.parse(toDateStr);
				List<Object[]> list = partyGalleryDAO.getCandidateRelatedGallaries(candidateId, partyId, fromDate, toDate, queryType);
				if(list != null && list.size() > 0)
				  for(Object[] params : list)
				   selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
				
				return selectOptionVOList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getCandidateRelatedGallaries() method, Exception - ",e);
				return null;
			}
		}


		 
		
		public List<SelectOptionVO> getCandidateRelatedCategories(Long candidateId,String fromDateStr,String toDateStr,Long partyId)
		{
			List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
			try{
				Date fromDate = null;
				Date toDate = null;
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
				 fromDate = format.parse(fromDateStr);
				if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
				 toDate = format.parse(toDateStr);
				List<Object[]> list = partyGalleryDAO.getCandidateRelatedCategories(candidateId, partyId, fromDate, toDate);
				if(list != null && list.size() > 0)
				  for(Object[] params : list)
					 selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
				
				return selectOptionVOList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getCandidateRelatedCategories() method, Exception - ",e);
				return null;
			}
		}
		
   public List<SelectOptionVO> getGallariesForSelectedCategories(List<Long> categoryIdsList,Long candidateId)
   {
	  try{
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		 if(categoryIdsList != null && categoryIdsList.size() > 0)
		 {
			List<Object[]> list = fileGallaryDAO.getGalleriesByCategoryIds(categoryIdsList, IConstants.TDPID, candidateId);
			if(list != null && list.size() > 0)
			 for(Object[] params : list)
			  selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1] != null?params[1].toString():""));
			}
				
		  return selectOptionVOList;
		}catch (Exception e) {
		   e.printStackTrace();
		   log.error("Exception Occured in getGallariesForSelectedCategory() method, Exception - ",e);
		   return null;
		 }
	}
   
    public List<SelectOptionVO> getNewsTitlesForACandidateByGalleryId(Long candidateId,Long gallaryId,String fromDateStr,String toDateStr)
    {
    	try{
    		Date fromDate = null;
    		Date toDate = null;
    		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    		if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
    		 fromDate = format.parse(fromDateStr);
    		if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
    		 toDate = format.parse(toDateStr);
    		return buildSelectOptionVO(candidateRelatedNewsDAO.getCandidateRelatedNewsByGallaryId(candidateId, gallaryId, fromDate, toDate));
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    		log.error("Exception Occured in getNewsTitlesForACandidateByGalleryId() method, Exception - ",e);
    		return null;
		}
    }
    
    public List<SelectOptionVO> getNewsForACandidateByCategoryId(Long candidateId,Long categoryId,String fromDateStr,String toDateStr)
    {
      try{
    	  Date fromDate = null;
    	  Date toDate = null;
    	  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	  if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
    	   fromDate = format.parse(fromDateStr);
    	  if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
    		toDate = format.parse(toDateStr);
    	    
    	  return buildSelectOptionVO(candidateRelatedNewsDAO.getNewsForACandidateByCategoryId(candidateId, categoryId, fromDate, toDate));
    	  
      }catch (Exception e) {
    	e.printStackTrace();
    	log.error("Exception Occured in getNewsForACandidateByCategoryId() method, Exception - ",e);
    	return null;
	}
    }
    
    public List<SelectOptionVO> getGalleryListForAParty(String fromDateStr,String toDateStr,List<Long> locationIdsList,String locationScope)
    {
    	try{
    	 List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
    	 Date fromDate = null;
    	 Date toDate = null;
    	 Long locationScopeId = 0L;
    	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	 if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
    		 fromDate = format.parse(fromDateStr);
    	 if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
    		toDate = format.parse(toDateStr);
    	 if(locationScope != null && !locationScope.equalsIgnoreCase(""))
    	  locationScopeId = regionScopesDAO.getRegionScopeIdByScope(locationScope);
    	 
    	 List<Object[]> list = candidateRelatedNewsDAO.getAllGallariesListForParty(fromDate, toDate, locationScopeId, locationIdsList);
    	 if(list != null && list.size() > 0)
    	  for(Object[] params : list)
    		selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1] != null?params[1].toString():""));
    		 
    	 return selectOptionVOList;
    	}catch (Exception e) {
    	 e.printStackTrace();
    	 log.error("Exception Occured in getGalleryListForAParty() method, Exception - ",e);
    	 return null;
		}
    }
    
   public List<SelectOptionVO> getNewsByGalleryId(Long galleryId,String fromDateStr,String toDateStr)
   {
	   try{
		   List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		   Date fromDate = null;
	    	 Date toDate = null;
	    	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    	 if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
	    		 fromDate = format.parse(fromDateStr);
	    	 if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
	    		toDate = format.parse(toDateStr);
	    	 
	    	 selectOptionVOList = buildSelectOptionVO(fileGallaryDAO.getnewsListByGalleryId(galleryId, fromDate, toDate));
	    	   
	    	 
		   return selectOptionVOList;
	   }catch (Exception e) {
		 e.printStackTrace();
		 log.error("Exception Occured in getNewsByGalleryId() method, Exception - ",e);
		 return null;
	}
   }
   
   public ResultStatus assignResToCandidateOrAGallary(Long candidateId,Long fileGalleryId,Long resFileGalId,String tempVar)
   {
	   ResultStatus resultStatus = new ResultStatus();
	   try{ 
		   if(tempVar != null && tempVar.equalsIgnoreCase("assignResponse"))
		   {
			   List<Long> list =  candidateNewsResponseDAO.getFileGalleryIdByResponseGalleryId(fileGalleryId);
			   if(list == null || list.size() == 0 || !list.contains(fileGalleryId))
			   {
				   CandidateNewsResponse candidateNewsResponse = new CandidateNewsResponse();
				   candidateNewsResponse.setFileGallary(fileGallaryDAO.get(fileGalleryId));
				   candidateNewsResponse.setResponseFileGallary(fileGallaryDAO.get(resFileGalId));
				   candidateNewsResponseDAO.save(candidateNewsResponse);
			   }
			   else
				resultStatus.setMessage("already assigned.");  
		   }
		   else
		   {
			 List<Long> list1 = candidateRelatedNewsDAO.getFileGalleryIdByCandidateId(candidateId);
			   if(list1 == null || list1.size() == 0 || !list1.contains(fileGalleryId) )
			   {
				   CandidateRealatedNews candidateRealatedNews = new CandidateRealatedNews();
				   candidateRealatedNews.setFileGallary(fileGallaryDAO.get(fileGalleryId));
				   candidateRealatedNews.setCandidate(candidateDAO.get(candidateId));
				   candidateRelatedNewsDAO.save(candidateRealatedNews);
			   }
			   else
				resultStatus.setMessage("already assigned.");  
			   
		   }
		   
		   resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		   return resultStatus;
	   }catch (Exception e) {
			 e.printStackTrace();
			 log.error("Exception Occured in assignResToCandidateOrAGallary() method, Exception - ",e);
			 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			 return resultStatus;
		}
   }
   
   public List<FileVO> getCandidateNewsResponseNewsForSelectdDates(Integer startIndex,Integer maxIndex,String fromDateStr,String toDateStr)
   {
	   List<FileVO> fileVOsList = null;
		try{
		Map<Long,Long> responceCountMap = null;
		// List<FileGallary> fileGallaryList = fileGallaryDAO.getRecentlyUploadedNewsDetails(startIndex, maxIndex, contenttype,partyId,newsType);
		//List<FileGallary> fileGallaryList = partyGalleryDAO.getLatestNewsResPonses(startIndex, maxIndex);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate = format.parse(fromDateStr);
		Date toDate = format.parse(toDateStr);
	     List<File> fileGallaryList = newsResponseDAO.getLatestFileDetailsForBtDates(startIndex,maxIndex,fromDate,toDate);
		 Long count = newsResponseDAO.getCountForNesResponce();
		 List<Long> fileIds = newsResponseDAO.getNewsResponceIdsForBtDates(startIndex,maxIndex,fromDate,toDate);
		 List<Object[]> responceListCount = newsResponseDAO.getResponceCountForFiles(fileIds);
		 if(responceListCount != null && responceListCount.size() > 0)
		 {
			 responceCountMap = new HashMap<Long, Long>();
			 for (Object[] objects : responceListCount) {
				 responceCountMap.put((Long)objects[0], (Long)objects[1]);
			}
		 }
		 if(fileGallaryList != null && fileGallaryList.size() > 0)
		 {
			 fileVOsList = new ArrayList<FileVO>();
			 fillFileVOForResponceDisplay(fileGallaryList,count, responceCountMap, fileVOsList);
		 }
		 
		 //fileVOsList.get(0).setCount(partyGalleryDAO.getLatestNewsResPonses(null, null).size());
		 return fileVOsList;
		 
		
		}catch (Exception e) {
			//e.printStackTrace();
			log.error("Exception Occured in getRecentlyUploadedNews() method, Exception - ",e);
				 return fileVOsList;
			}
	   
   }
   
   public List<FileVO> getCandidateNewsResponseNews(Integer startIndex,Integer maxIndex)
   {
	   List<FileVO> fileVOsList = null;
		try{
		Map<Long,Long> responceCountMap = null;
		// List<FileGallary> fileGallaryList = fileGallaryDAO.getRecentlyUploadedNewsDetails(startIndex, maxIndex, contenttype,partyId,newsType);
		//List<FileGallary> fileGallaryList = partyGalleryDAO.getLatestNewsResPonses(startIndex, maxIndex);
	     List<File> fileGallaryList = newsResponseDAO.getLatestFileDetails(startIndex, maxIndex);
		 Long count = newsResponseDAO.getCountForNesResponce();
		 List<Long> fileIds = newsResponseDAO.getNewsResponceIds(startIndex,maxIndex);
		 List<Object[]> responceListCount = newsResponseDAO.getResponceCountForFiles(fileIds);
		 if(responceListCount != null && responceListCount.size() > 0)
		 {
			 responceCountMap = new HashMap<Long, Long>();
			 for (Object[] objects : responceListCount) {
				 responceCountMap.put((Long)objects[0], (Long)objects[1]);
			}
		 }
		 if(fileGallaryList != null && fileGallaryList.size() > 0)
		 {
			 fileVOsList = new ArrayList<FileVO>();
			 fillFileVOForResponceDisplay(fileGallaryList,count, responceCountMap, fileVOsList);
		 }
		 
		 //fileVOsList.get(0).setCount(partyGalleryDAO.getLatestNewsResPonses(null, null).size());
		 return fileVOsList;
		 
		
		}catch (Exception e) {
			//e.printStackTrace();
			log.error("Exception Occured in getRecentlyUploadedNews() method, Exception - ",e);
				 return fileVOsList;
			}
	}
   
   public void fillFileVOForResponceDisplay(List<File> fileGallaryList,Long count,Map<Long,Long> responceCountMap,List<FileVO> fileVOsList)
   {
		Set<Long> fileIds = new HashSet<Long>();
		for(File file:fileGallaryList){
			fileIds.add(file.getFileId());
		}
		Map<Long,String> candidateNames = getCandidateNames(fileIds);
		Map<Long,String> keywordsCount = getKeywordsCountByFileId(fileIds);
	   for (File file : fileGallaryList) {
			 if(file != null)
			 {
				 FileVO fileVO = new FileVO();
				 fileVO.setContentId(file.getFileId());
				 fileVO.setTitle(StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle())));
				 fileVO.setDescription(StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription())));
				 fileVO.setFilePath1(file.getFilePath());
				 fileVO.setFileDate(file.getFileDate().toString());
				 fileVO.setSource(file.getSourceObj() != null ? file.getSourceObj().getSource() : "");
				 fileVO.setFontId(file.getFont() != null ?  file.getFont().getFontId():0);
				 if(file.getDescFont() != null)
				 fileVO.setDescEenadu(true);
				 fileVO.setCount(count.intValue());
				 fileVO.setKeywords(keywordsCount.get(file.getFileId())!= null ? keywordsCount.get(file.getFileId()) : "");
				 fileVO.setCandidateName(candidateNames.get(file.getFileId())!=null?candidateNames.get(file.getFileId()):"");
				 if(responceCountMap.get(file.getFileId()) != null)
				 fileVO.setResponseCount(responceCountMap.get(file.getFileId()).intValue());
				 fileVOsList.add(fileVO);
			 }
			
		}
   }
   public static Comparator<FileVO> dateSort = new Comparator<FileVO>()
			{
								  
			  public int compare(FileVO fileVO, FileVO fileVO2)
				{
				  return (fileVO.getFileDate()).compareTo(fileVO2.getFileDate());
				}
			 };
			 
			 
 public List<SelectOptionVO> getLocationValuesByRegionScope(String regionScope, String queryType)
 {
	 List<SelectOptionVO> selectOptionVOList = null;
	 try{
		 List<Object[]> locationList = null;
		 Long regionScopeId = regionScopesDAO.getRegionScopeIdByScope(regionScope);
		 List<Long> locationValuesList = fileGallaryDAO.getLocationValuesByRegionScopeId(regionScopeId,queryType,872L);
		 if(locationValuesList != null && locationValuesList.size() > 0)
		 {
		  if(regionScope != null && regionScope.equalsIgnoreCase(IConstants.CONSTITUENCY))
			locationList = constituencyDAO.getConstituencyNameByConstituencyIdsList(locationValuesList);
		  else if(regionScope != null && regionScope.equalsIgnoreCase(IConstants.DISTRICT))
			locationList = districtDAO.getDistrictNamesByDistrictIdsList(locationValuesList); 
			  
		 }
		 
		 if(locationList != null && locationList.size() > 0)
		 {
			selectOptionVOList = new ArrayList<SelectOptionVO>(0);
			selectOptionVOList.add(new SelectOptionVO(0L,"Select"));
			for(Object[] params: locationList)
			 selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1] != null?WordUtils.capitalize(params[1].toString().toLowerCase()):" "));
		 }
		 
		 return selectOptionVOList;
	 }catch (Exception e) {
      e.printStackTrace();
      log.error("Exception Occured in getLocationValuesByRegionScope() method, Exception - ",e);
      return null;
	 }
 }
 
 
 public List<SelectOptionVO> getTotalCategoriesList(String queryType)
 {
	 List<SelectOptionVO> selectOptionVOList =  null;
	try{
	
		List<Object[]> categoryList = partyGalleryDAO.getTotalCategories(IConstants.TDPID, queryType);
		if(categoryList != null && categoryList.size() > 0)
		{
			selectOptionVOList = new ArrayList<SelectOptionVO>(0);
			for(Object[] params :categoryList)
			{
			  SelectOptionVO optionVO = new SelectOptionVO();
			  optionVO.setId((Long)params[0]);
			  optionVO.setName(params[1] != null ?params[1].toString():" ");
			  optionVO.setOrderId((Long)params[2]);
			  selectOptionVOList.add(optionVO);
			}
		}
		
		return selectOptionVOList;
	}catch (Exception e) {
     e.printStackTrace();
     log.error("Exception Occured in getTotalCategoriesList() method, Exception - ",e);
     return selectOptionVOList;
	}
 }
 
 public List<SelectOptionVO> getAllCategories()
 {
	 try{
		 List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);	
		 List<Object[]> list = categoryDAO.getAllCategories();
		 if(list != null && list.size() > 0)
		  for(Object[] params:list)
			selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1] != null?params[1].toString():" "));
		 
		 return selectOptionVOList;
	 }catch (Exception e) {
      e.printStackTrace();
      log.error(" Exception Occured in getAllCategories() method, Exception - ",e);
      return null;
	 }
 }
 
 public List<SelectOptionVO> getGallariesInCategory(Long categoryId)
 {
	try{
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);	
				
		List<Object[]> list = gallaryDAO.getGallariesForSelectedCategory(categoryId);
		if(list != null && list.size() > 0)
		 for(Object[] params:list)
			selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1] != null?params[1].toString():" "));
		
		return selectOptionVOList;
	}catch (Exception e) {
		e.printStackTrace();
	    log.error(" Exception Occured in getGallariesInCategory() method, Exception - ",e);
	    return null;
	}
	 
 }
 
/* public LocationVO getLocationListForSelectedUser(String accessType,Long accessValue,Long userId)
 {
	 LocationVO locationVO = new LocationVO();
	 
	 List<SelectOptionVO> stateList = null;
	 List<SelectOptionVO> districtList = null;
	 List<SelectOptionVO> constituencyList = null;
	 List<SelectOptionVO> mandalList = null;
	 
	 try{
		 locationVO.setPopulatedId(accessValue);
		if(accessType.equalsIgnoreCase("MLA"))
		{
		
		 locationVO.setScopeId(4L);
		 
		 stateList = new ArrayList<SelectOptionVO>(0);
		 districtList = new ArrayList<SelectOptionVO>(0);
		 constituencyList = new ArrayList<SelectOptionVO>(0);
		 mandalList = new ArrayList<SelectOptionVO>(0);
		 
		 List stateDistrictConstituency = constituencyDAO.getStateDistrictConstituency(accessValue);
		 Object[] objVO = (Object[])stateDistrictConstituency.get(0);
          
		 stateList.add(new SelectOptionVO((Long)objVO[0],objVO[1]!= null?objVO[1].toString():""));
		 districtList.add(new SelectOptionVO((Long)objVO[2],objVO[3]!= null?objVO[3].toString():""));
		 constituencyList.add(new SelectOptionVO(accessValue,WordUtils.capitalize(objVO[4].toString().toLowerCase())));
		 
		 mandalList = regionServiceDataImp.getSubRegionsInConstituency(accessValue, IConstants.PRESENT_YEAR, null);
		 
		 locationVO.setStateId((Long)objVO[0]);
		 locationVO.setDistrictId((Long)objVO[2]);
		 locationVO.setConstituencyId(accessValue);
		 
		 
		}
		else if(accessType.equalsIgnoreCase("MP"))
		{
			
			//locationVO.setScopeId(4L);
			locationVO.setScopeId(2L);
			
			 stateList = new ArrayList<SelectOptionVO>(0);
			 districtList = new ArrayList<SelectOptionVO>(0);
			 constituencyList = new ArrayList<SelectOptionVO>(0);
			 mandalList = new ArrayList<SelectOptionVO>(0);
			 
			 List<Long> assemblyConstituenyIds = new ArrayList<Long>(0);
			
			List state = constituencyDAO.getStateForParliamentConstituency(accessValue);
			if(state!=null && state.size()==1){
				Object[] object = (Object[]) state.get(0);
				stateList.add(new SelectOptionVO((Long)object[0],object[1]!=null?object[1].toString():""));
				
				locationVO.setStateId((Long)object[0]);
				
			}
			
			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			
			constituencyList = constituencyInfoVO.getAssembyConstituencies();
			if(constituencyList != null && constituencyList.size() > 0)
				 for(SelectOptionVO optionVO:constituencyList)
				  assemblyConstituenyIds.add(optionVO.getId());
			
			List<SelectOptionVO> constituencies = constituencyInfoVO.getAssembyConstituencies();
			if(constituencies != null && constituencies.size() > 0)
				 for(SelectOptionVO optionVO:constituencies)
				  assemblyConstituenyIds.add(optionVO.getId());
			
			
			if(assemblyConstituenyIds != null && assemblyConstituenyIds.size() > 0)
			{
			 List<Object[]> districtsList = constituencyDAO.getDistrictsByConstituencyIds(assemblyConstituenyIds);
			 if(districtsList != null && districtsList.size() > 0)
			  for(Object[] params:districtsList)
				districtList.add(new SelectOptionVO((Long)params[0],params[1] != null?WordUtils.capitalize(params[1].toString().toLowerCase()):" "));
			 
			 for(Long constituencyId:assemblyConstituenyIds)
			 {
			  List<SelectOptionVO> mandalsTempList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);
			  if(mandalsTempList != null && mandalsTempList.size() > 0)
			   for(SelectOptionVO optionVO:mandalsTempList)
			    mandalList.add(new SelectOptionVO(optionVO.getId(),optionVO.getName()));
			 }
			 
			}
			
		}
		else if(accessType.equalsIgnoreCase("STATE"))
		{
			locationVO.setScopeId(2L);
			
			 stateList = new ArrayList<SelectOptionVO>(0);
			 districtList = new ArrayList<SelectOptionVO>(0);
			
			State state = stateDAO.get(accessValue);
			String name = "";
			if (state != null)
			 name = state.getStateName();
			stateList.add(new SelectOptionVO(accessValue,name));
			
			
			List<Object[]> distinctList = districtDAO.getDistrictNamesByStateId(accessValue);
			if(distinctList != null && distinctList.size() >0)
			  for(Object[] params :distinctList)	
			districtList.add(new SelectOptionVO((Long)params[0],params[1] != null?WordUtils.capitalize(params[1].toString().toLowerCase()):""));
			
			locationVO.setStateId(accessValue);
			
			
			
		}
		else if(accessType.equalsIgnoreCase("DISTRICT"))
		{
			locationVO.setScopeId(3L);
			
			 stateList = new ArrayList<SelectOptionVO>(0);
			 districtList = new ArrayList<SelectOptionVO>(0);
			 constituencyList = new ArrayList<SelectOptionVO>(0);
			
			List stateDistrictConstituency = districtDAO.getStateDistrictByDistrictID(accessValue);
			Object[] objVO = (Object[])stateDistrictConstituency.get(0);
            
			stateList.add(new SelectOptionVO((Long)objVO[0],objVO[1] != null?objVO[1].toString():" "));
			districtList.add(new SelectOptionVO(accessValue,objVO[2] != null?objVO[2].toString():" "));
			
			List<Object[]> constituenciesList = delimitationConstituencyDAO.getConstituenciesForDistrict(accessValue);
			if(constituenciesList != null && constituenciesList.size() > 0)
			 for(Object[] params:constituenciesList)
			  constituencyList.add(new SelectOptionVO((Long)params[0],params[1] != null?WordUtils.capitalize(params[1].toString().toLowerCase()):" "));
			
			locationVO.setStateId((Long)objVO[0]);
			locationVO.setDistrictId(accessValue);
			
			
		}
		locationVO.setStateList(stateList);
		locationVO.setDistrictList(districtList);
		locationVO.setConstituencyList(constituencyList);
		locationVO.setMandalList(mandalList);
		
	  return locationVO;
	  
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error("Exception Occured in getLocationListForSelectedUser() method, Exception - "+e);
	  return null;
	}
 }*/
 
 public LocationVO getLocationListForSelectedUser(String accessType,Long accessValue,Long userId)
 {
	 LocationVO locationVO = new LocationVO();
	 
	 List<SelectOptionVO> stateList = null;
	 List<SelectOptionVO> districtList = null;
	 List<SelectOptionVO> constituencyList = null;
	 List<SelectOptionVO> mandalList = null;
	 
	 try{
		 locationVO.setPopulatedId(accessValue);
		if(accessType.equalsIgnoreCase("MLA"))
		{
		
		 locationVO.setScopeId(2L);
		 
		 stateList = new ArrayList<SelectOptionVO>(0);
		 /*districtList = new ArrayList<SelectOptionVO>(0);
		 constituencyList = new ArrayList<SelectOptionVO>(0);
		 mandalList = new ArrayList<SelectOptionVO>(0);*/
		 
		 List stateDistrictConstituency = constituencyDAO.getStateDistrictConstituency(accessValue);
		 Object[] objVO = (Object[])stateDistrictConstituency.get(0);
          
		 stateList.add(new SelectOptionVO((Long)objVO[0],objVO[1]!= null?objVO[1].toString():""));
		 /*districtList.add(new SelectOptionVO((Long)objVO[2],objVO[3]!= null?objVO[3].toString():""));
		 constituencyList.add(new SelectOptionVO(accessValue,WordUtils.capitalize(objVO[4].toString().toLowerCase())));
		 
		 mandalList = regionServiceDataImp.getSubRegionsInConstituency(accessValue, IConstants.PRESENT_YEAR, null);*/
		 
		 locationVO.setStateId((Long)objVO[0]);
		 //locationVO.setDistrictId((Long)objVO[2]);
		 //locationVO.setConstituencyId(accessValue);
		 
		 
		}
		else if(accessType.equalsIgnoreCase("MP"))
		{
			
			//locationVO.setScopeId(4L);
			locationVO.setScopeId(2L);
			
			 stateList = new ArrayList<SelectOptionVO>(0);
			/* districtList = new ArrayList<SelectOptionVO>(0);
			 constituencyList = new ArrayList<SelectOptionVO>(0);
			 mandalList = new ArrayList<SelectOptionVO>(0);
			 
			 List<Long> assemblyConstituenyIds = new ArrayList<Long>(0);*/
			
			List state = constituencyDAO.getStateForParliamentConstituency(accessValue);
			if(state!=null && state.size()==1){
				Object[] object = (Object[]) state.get(0);
				stateList.add(new SelectOptionVO((Long)object[0],object[1]!=null?object[1].toString():""));
				
				locationVO.setStateId((Long)object[0]);
				
			}
			
			/*ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			
			constituencyList = constituencyInfoVO.getAssembyConstituencies();
			if(constituencyList != null && constituencyList.size() > 0)
				 for(SelectOptionVO optionVO:constituencyList)
				  assemblyConstituenyIds.add(optionVO.getId());
			
			List<SelectOptionVO> constituencies = constituencyInfoVO.getAssembyConstituencies();
			if(constituencies != null && constituencies.size() > 0)
				 for(SelectOptionVO optionVO:constituencies)
				  assemblyConstituenyIds.add(optionVO.getId());
			
			
			if(assemblyConstituenyIds != null && assemblyConstituenyIds.size() > 0)
			{
			 List<Object[]> districtsList = constituencyDAO.getDistrictsByConstituencyIds(assemblyConstituenyIds);
			 if(districtsList != null && districtsList.size() > 0)
			  for(Object[] params:districtsList)
				districtList.add(new SelectOptionVO((Long)params[0],params[1] != null?WordUtils.capitalize(params[1].toString().toLowerCase()):" "));
			 
			 for(Long constituencyId:assemblyConstituenyIds)
			 {
			  List<SelectOptionVO> mandalsTempList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);
			  if(mandalsTempList != null && mandalsTempList.size() > 0)
			   for(SelectOptionVO optionVO:mandalsTempList)
			    mandalList.add(new SelectOptionVO(optionVO.getId(),optionVO.getName()));
			 }
			 
			}*/
			
		}
		else if(accessType.equalsIgnoreCase("STATE"))
		{
			locationVO.setScopeId(2L);
			
			 stateList = new ArrayList<SelectOptionVO>(0);
			/* districtList = new ArrayList<SelectOptionVO>(0);*/
			
			State state = stateDAO.get(accessValue);
			String name = "";
			if (state != null)
			 name = state.getStateName();
			stateList.add(new SelectOptionVO(accessValue,name));
			
			
			/*List<Object[]> distinctList = districtDAO.getDistrictNamesByStateId(accessValue);
			if(distinctList != null && distinctList.size() >0)
			  for(Object[] params :distinctList)	
			districtList.add(new SelectOptionVO((Long)params[0],params[1] != null?WordUtils.capitalize(params[1].toString().toLowerCase()):""));*/
			
			locationVO.setStateId(accessValue);
			
			
			
		}
		else if(accessType.equalsIgnoreCase("DISTRICT"))
		{
			locationVO.setScopeId(2L);
			
			 stateList = new ArrayList<SelectOptionVO>(0);
			 /*districtList = new ArrayList<SelectOptionVO>(0);
			 constituencyList = new ArrayList<SelectOptionVO>(0);*/
			
			List stateDistrictConstituency = districtDAO.getStateDistrictByDistrictID(accessValue);
			Object[] objVO = (Object[])stateDistrictConstituency.get(0);
            
			stateList.add(new SelectOptionVO((Long)objVO[0],objVO[1] != null?objVO[1].toString():" "));
			/*districtList.add(new SelectOptionVO(accessValue,objVO[2] != null?objVO[2].toString():" "));
			
			List<Object[]> constituenciesList = delimitationConstituencyDAO.getConstituenciesForDistrict(accessValue);
			if(constituenciesList != null && constituenciesList.size() > 0)
			 for(Object[] params:constituenciesList)
			  constituencyList.add(new SelectOptionVO((Long)params[0],params[1] != null?WordUtils.capitalize(params[1].toString().toLowerCase()):" "));*/
			
			locationVO.setStateId((Long)objVO[0]);
			//locationVO.setDistrictId(accessValue);
			
			
		}
		locationVO.setStateList(stateList);
		//locationVO.setDistrictList(districtList);
		//locationVO.setConstituencyList(constituencyList);
		//locationVO.setMandalList(mandalList);
		
	  return locationVO;
	  
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error("Exception Occured in getLocationListForSelectedUser() method, Exception - ",e);
	  return null;
	}
 }
 
 
 public List<SelectOptionVO> getConstituencyList(Long districtId,String accessType,Long accessValue)
 {
	try{
		List<SelectOptionVO> selectOptionVOsList = new ArrayList<SelectOptionVO>(0);
		List<Object[]> constituencyList = null;
		List<Long> assemBlyIdsList = new ArrayList<Long>(0);
		
		if(accessType.equalsIgnoreCase("STATE") || accessType.equalsIgnoreCase("DISTRICT"))
		 constituencyList = delimitationConstituencyDAO.getConstituenciesByDistrictId(districtId);
		
		else if(accessType.equalsIgnoreCase("MLA"))
			assemBlyIdsList.add(accessValue);
		
		else if(accessType.equalsIgnoreCase("MP"))
			assemBlyIdsList = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsListByParliamId(accessValue);
			
		if(assemBlyIdsList != null && assemBlyIdsList.size() > 0)
		 constituencyList = constituencyDAO.getConstituencyByConstituencyIdsList(assemBlyIdsList,districtId);
		
		if(constituencyList != null && constituencyList.size() > 0)
		 for(Object[] params:constituencyList)
		  selectOptionVOsList.add(new SelectOptionVO((Long)params[0],params[1] != null?params[1].toString():" "));	 
			
		return selectOptionVOsList;
		
	}catch (Exception e) {
	 e.printStackTrace();
	 log.error("Exception Occured in getConstituencyList() method, Exception - ",e);
	 return null;
	}
 }
 
 public List<SelectOptionVO> getTotalKeyWords(Long partyId,String newsType)
 {
	 try{
		 List<SelectOptionVO> selectOptionVOsList = null;
		 List<String> keyWordsList = partyGalleryDAO.getTotalKeywordsForKeywordSearch(partyId, newsType);
		 if(keyWordsList != null && keyWordsList.size() > 0)
		 {
			selectOptionVOsList = new ArrayList<SelectOptionVO>(0);
			Long count = 0L;
			for(String keyword: keyWordsList)
			{
			 count ++;
			 selectOptionVOsList.add(new SelectOptionVO(count,keyword != null ?keyword:" "));
			}
		 }
		 
		 return selectOptionVOsList;
		 
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error("Exception Occured in getTotalKeyWords() method, Exception - "+e);
	  return null;
	 }
 }
 

 
 public List<SelectOptionVO> getTotalKeyWords()
 {
	 try{
		 List<SelectOptionVO> optionVOs = null;
		 List<Object[]> keyWordList = keywordDAO.getTotalKeyWords();
		 if(keyWordList != null && keyWordList.size() > 0)
		 {
			optionVOs = new ArrayList<SelectOptionVO>(0); 
			for(Object[] params:keyWordList)
			 optionVOs.add(new SelectOptionVO((Long)params[0],params[1] != null?params[1].toString():" "));
		 }
		return optionVOs; 
		 
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error(" Exception Occured in getTotalKeyWords() method, Exception - "+e);
	  return null;
	 }
 }
	 
	 public List<SelectOptionVO> getKeywordsListCount()
	 {
		 try{
			 List<SelectOptionVO> optionVOs = null;
			 List<Object[]> keyWordListCount = candidatePartyKeywordDAO.getTotalKeyWordsCount();
			 if(keyWordListCount != null && keyWordListCount.size() > 0)
			 {
				optionVOs = new ArrayList<SelectOptionVO>(0); 
				for(Object[] params:keyWordListCount)
					 optionVOs.add(new SelectOptionVO((Long)params[0],params[1] != null?params[1].toString():" ",(Long)params[2]));

			 }
			return optionVOs; 
			 
		 }catch (Exception e) {
		  e.printStackTrace();
		  log.error(" Exception Occured in getTotalKeyWords() method, Exception - "+e);
		  return null;
		 }
 }

	
 public void setDataToFileVo(List<File> filesList,List<FileVO> returnList)
 {
	 try{
	
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Set<Long> fileIds = new HashSet<Long>();
		for(File file:filesList){
			fileIds.add(file.getFileId());
		}
		Map<Long,String> candidateNames = getCandidateNames(fileIds);
		Map<Long,String> keywordsCount = getKeywordsCountByFileId(fileIds);
		Map<Long,String> categorysCount = getCategorysCountByFileId(fileIds);
	 for(File file : filesList)
	 {
 
	FileVO filevo = new FileVO();
	filevo.setFileId(file.getFileId());
	//filevo.setFileGallaryId(fileGallary.getFileGallaryId());
	filevo.setFileName1(file.getFileTitle() !=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle())):"");
	filevo.setFileDescription1(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription()!=null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription())):""));
	if(file.getFileDate() != null)
	filevo.setFileDate(new SimpleDateFormat("yyyy-MM-dd").format(file.getFileDate()));
	filevo.setResponseCount(newsResponseDAO.getCandidateNewsResponseFileIdsByFileID((Long)file.getFileId()).size());
	if(file.getFont() != null)
	filevo.setFontId(file.getFont().getFontId());
	if(file.getDescFont() != null)
		filevo.setDescEenadu(true);
	if(file.getCategory() != null){
		filevo.setCategoryId(file.getCategory().getCategoryId());
		filevo.setCategoryName(file.getCategory().getCategoryType());
	}
	filevo.setCategoryName(categorysCount.get(file.getFileId())!=null ? categorysCount.get(file.getFileId()) : "");
	filevo.setKeywords(keywordsCount.get(file.getFileId())!=null ? keywordsCount.get(file.getFileId()) : "");
	filevo.setCandidateName(candidateNames.get(file.getFileId())!=null ? candidateNames.get(file.getFileId()) : "");
	filevo.setFilePath1(file.getFilePath());
	
	Set<FileSourceLanguage> set = file.getFileSourceLanguage();
	
	String sourceString = "";
	for(FileSourceLanguage source:set)
		if(source.getSource() != null)
		sourceString+=source.getSource().getSource()+" ";
	
	filevo.setFileType(sourceString);
	
	
	returnList.add(filevo);
	
	 }
	 }
	 catch (Exception e) {
			e.printStackTrace();
	 }
 }
 
 public List<FileVO> getNewsForSelectedKeyword(String keyWord,Long partyId,String newsType,Integer startIndex,Integer maxIndex)
 {
	 List<FileVO> fileVOList = null;
	 try{
		 List<FileGallary> fileGallariesList = fileGallaryDAO.getNewsForSelectedKeyWord(keyWord, partyId, newsType, startIndex, maxIndex);
		 if(fileGallariesList != null && fileGallariesList.size() > 0)
		 {
			fileVOList = new ArrayList<FileVO>(0);
			//setfileGallaryDetails(fileGallariesList, fileVOList);
			fileVOList.get(0).setCount(fileGallaryDAO.getNewsForSelectedKeyWord(keyWord, partyId, newsType, null, null).size()); 
		 }
		 
		return fileVOList;
	 }catch (Exception e) {
	  e.printStackTrace();
	  log.error(" Exception Occured in getNewsForSelectedKeyword() method, Exception - "+e); 
	  return fileVOList;
	}
 }
 

	 public List<SelectOptionVO> getAllNewsGallaries()
	 {
		 List<SelectOptionVO> result = new ArrayList<SelectOptionVO>(); 
		 try{
	 
		 List<Object[]> list = gallaryDAO.getAllGallaries(com.itgrids.partyanalyst.utils.IConstants.NEWS_GALLARY);
		 if(list != null && list.size() > 0)
			{
			 for(Object[] params : list)
				 result.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
			}
			}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	 }
		 
	 public List<SelectOptionVO> getCandidatesByPartyIdFromCandidateTable(Long userId,Long partyId)
	 {
	 	try
	 	{
	 	List<SelectOptionVO> candidateList = new ArrayList<SelectOptionVO>(0);
	 	User user = userDAO.get(userId);
	 	StringBuffer query = new StringBuffer();
	 	if(user.getAccessType().equalsIgnoreCase("STATE"))	 		
	 		query = query.append(" and model.state.stateId = "+user.getAccessValue());
	 	else if(user.getAccessType().equalsIgnoreCase("DISTRICT")){

			List<Long> assmblyConstIds = new ArrayList<Long>(0);
			List<Long> parliamentConstIds = new ArrayList<Long>(0);
			
	 		List latestElectionYear = electionDAO.findLatestElectionYearHappenedInState(1l,IConstants.ASSEMBLY_ELECTION_TYPE,IConstants.ELECTION_SUBTYPE_MAIN);
	 		List result = constituencyDAO.findConstituencyByDistrictElectionTypeAndYear(Long.valueOf(user.getAccessValue().trim()),IConstants.ASSEMBLY_ELECTION_TYPE,latestElectionYear.get(0).toString());

			for (Object constituency : result) {
				Object[] param = (Object[]) constituency;
				assmblyConstIds.add((Long) param[0]);				
				List list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(Long.parseLong(param[0].toString()));
				for (Object constituencies : list) {
					Object[] params = (Object[]) constituencies;
					if(!parliamentConstIds.contains(Long.valueOf(params[0].toString())))
						parliamentConstIds.add(Long.valueOf(params[0].toString()));
				}
			}

	 		query .append(" and (model.assembly.constituencyId in (");
	 		
			for (int i = 0; i < assmblyConstIds.size(); i++) {
				query.append(assmblyConstIds.get(i));
				if(i < assmblyConstIds.size()-1)
					query.append(", ");
			}
			
			query .append(" ) or ");
			
			
			query .append("  model.parliament.constituencyId in ( ");
			
			for (int i = 0; i < parliamentConstIds.size(); i++) {
				query.append(parliamentConstIds.get(i));
				if(i < parliamentConstIds.size()-1)
					query.append(", ");
			}
			
			query .append(" ))");
	 	}
	 	else if(user.getAccessType().equalsIgnoreCase("MLA"))
	 		query.append(" and model.assembly.constituencyId = "+user.getAccessValue());
	 	else if(user.getAccessType().equalsIgnoreCase("MP"))
	 		query.append(" and model.parliament.constituencyId = "+user.getAccessValue());
	 	
	 	List<Object[]> list = candidateDAO.getCandidateListByPartyId(query.toString(),partyId);
	 	if(list != null && list.size() > 0)
	 	 for(Object[] params:list)
	 	 {
	 		//candidateList.add(new SelectOptionVO((Long)params[0],params[1]!= null?params[1].toString():""));
	 		SelectOptionVO optionVO = new SelectOptionVO();
	 		optionVO.setId((Long)params[0]);
	 		String name="";
	 		if(params[1] != null)
	 		 name += params[1].toString();
	 		if(params[2] != null)
	 		 name += " ("+params[2].toString()+")";
	 		optionVO.setName(name);
	 		candidateList.add(optionVO);
	 		
	 	 }
	 	return candidateList;
	 	
	 	}catch(Exception e)
	 	{
	 		e.printStackTrace();
	 		log.error("Exception Occured in getCandidatesByPartyIdFromCandidateTable() method, Exception - "+e);
	 		return null;
	 	}
	 }
	 
	 public List<SelectOptionVO> getBenefitList()
	 {
		 List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		 try{
		
		  List<Benefit> benefitsList = benefitDAO.getBenifitsList();
		  if(benefitsList != null && benefitsList.size() > 0)
			for(Benefit benefit: benefitsList)
				selectOptionVOList.add(new SelectOptionVO(benefit.getBenefitId(),benefit.getName()));
		  
		  return selectOptionVOList;
			 
		 }catch (Exception e) {
		  e.printStackTrace();
		  log.error(" Exception Occured in getBenefitList() method, Exception - "+e);
		  return null;
		 }
	 }
	 

 
 public List<FileVO> getCandidatesNewsForHomePage(Long candidateId,int firstRecord,int maxRecord,String type,String fromDateStr, String toDateStr,String categoryIdsStr,String keywordIdStr){
	 List<FileVO> fileVOList = null;
 try{
	 List<File> filesList = null;
	 Date fromDate = null;
	 Date toDate = null;
	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	 if(fromDateStr != null && !fromDateStr.equalsIgnoreCase("") && !fromDateStr.equalsIgnoreCase("null"))
		fromDate = format.parse(fromDateStr);
	 if(toDateStr != null && !toDateStr.equalsIgnoreCase("") && !toDateStr.equalsIgnoreCase("null"))
		 toDate = format.parse(toDateStr);
	 
	 List<Long> gallaryIdsList = new ArrayList<Long>(0);
	 List<Long> categoryIdsList = new ArrayList<Long>(0);
	 List<Long> keywordIdsList =new ArrayList<Long>(0);
	 StringTokenizer str = null;
	 
	 
	/* if(categoryIdsStr != null && !categoryIdsStr.equalsIgnoreCase("") && !categoryIdsStr.equalsIgnoreCase("null"))
	 {
		 str = new StringTokenizer(categoryIdsStr,","); 
		 while (str.hasMoreTokens()) 
			categoryIdsList.add(Long.parseLong(str.nextToken()));
	 }
	 if(categoryIdsList != null && categoryIdsList.size() > 0)
	  filesList = candidatePartyCategoryDAO.getFileListByCandidateId(candidateId, firstRecord, maxRecord, type,fromDate,toDate,categoryIdsList);
	 else
		 filesList = candidatePartyFileDAO.getCandidateFileListByCandidateId(candidateId, firstRecord, maxRecord, type,fromDate,toDate); 
	 if(filesList != null && filesList.size() > 0)
	 {
			fileVOList = new ArrayList<FileVO>(0);
	 setfileDetails(filesList, fileVOList); 
	 if(categoryIdsList != null && categoryIdsList.size() > 0)
	 fileVOList.get(0).setCount(candidatePartyCategoryDAO.getFileListByCandidateId(candidateId, null, null, type,fromDate,toDate,categoryIdsList).size());
	 else
		 fileVOList.get(0).setCount(candidatePartyFileDAO.getCandidateFileListByCandidateId(candidateId, null, null, type,fromDate,toDate).size());	 
	 }
	 return fileVOList;
	 
}catch (Exception e) {
			log.debug("Exception Occured in getCandidatesNews() Method, Exception - ",e);
			return fileVOList;
	}*/
	 if(categoryIdsStr != null && !categoryIdsStr.equalsIgnoreCase("") && !categoryIdsStr.equalsIgnoreCase("null"))
	 {
		 str = new StringTokenizer(categoryIdsStr,","); 
		 while (str.hasMoreTokens()) 
			 categoryIdsList.add(Long.parseLong(str.nextToken()));
	 }
	 else if(keywordIdStr != null && !keywordIdStr.equalsIgnoreCase("") && !keywordIdStr.equalsIgnoreCase("null"))
	 {
		 str = new StringTokenizer(keywordIdStr,","); 
		 while (str.hasMoreTokens()) 
			keywordIdsList.add(Long.parseLong(str.nextToken()));
	 }
	 
	 
	 if (categoryIdsList != null && categoryIdsList.size() > 0)
	    filesList = candidatePartyCategoryDAO.getFileListByCandidateId(candidateId, firstRecord, maxRecord, type,fromDate,toDate,categoryIdsList);
	 else if(keywordIdsList !=null && keywordIdsList.size()>0)
		     filesList = candidatePartyKeywordDAO.getFileListByCandidateId(candidateId, firstRecord, maxRecord, type,fromDate,toDate,keywordIdsList);
     else	 
		  filesList = candidatePartyFileDAO.getCandidateFileListByCandidateId(candidateId, firstRecord, maxRecord, type,fromDate,toDate); 
	 
	 if(filesList != null && filesList.size() > 0)
	 {
	  fileVOList = new ArrayList<FileVO>(0);
	 setfileDetails(filesList, fileVOList); 
	 if(categoryIdsList != null && categoryIdsList.size() > 0)
	  fileVOList.get(0).setCount(candidatePartyCategoryDAO.getFileListByCandidateId(candidateId, null, null, type,fromDate,toDate,categoryIdsList).size());
	 else if(keywordIdsList != null && keywordIdsList.size() > 0)
		 fileVOList.get(0).setCount(candidatePartyKeywordDAO.getFileListByCandidateId(candidateId, null, null, type,fromDate,toDate,keywordIdsList).size());
	 else
		 fileVOList.get(0).setCount(candidatePartyFileDAO.getCandidateFileListByCandidateId(candidateId, null, null, type,fromDate,toDate).size());	 
	 }
	 return fileVOList;
	 
}catch (Exception e) {
			log.debug("Exception Occured in getCandidatesNews() Method, Exception - ",e);
			return fileVOList;
	 
}
 }
 public void setfileDetails(List<File> fileList,List<FileVO> fileVOsList)
	{
		try{
			
			List<Long> fileGalleryIdsList = new ArrayList<Long>(0);
			Set<Long> fileIds = new HashSet<Long>();
			for(File file:fileList){
				fileIds.add(file.getFileId());
			}
			Map<Long,String> categorysCount = getCategorysCountByFileId(fileIds);
			Map<Long,String> keywordsCount = getKeywordsCountByFileId(fileIds);
			Map<Long,String> candidateNames = getCandidateNames(fileIds);
			for(File file : fileList)
			 {
			/*	int count =candidateNewsResponseDAO.getFileGalleryIdByResponseGalleryId(fileGallary.getFileGallaryId()).size();
				if(count>0)
					return;*/
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
					
				fileGalleryIdsList.add(file.getFileId());
					
				FileVO fileVO = new FileVO(); 
				fileVO.setContentId(file.getFileId());
				fileVO.setFileId(file.getFileId());
				fileVO.setCategoryName(categorysCount.get(file.getFileId())!=null ? categorysCount.get(file.getFileId()) : "");
				fileVO.setKeywords(keywordsCount.get(file.getFileId())!=null ? keywordsCount.get(file.getFileId()) : "");
				fileVO.setCandidateName(candidateNames.get(file.getFileId())!=null ? candidateNames.get(file.getFileId()) : "");
				fileVO.setTitle(file.getFileTitle() != null?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle().toString())):"");
				fileVO.setDescription(file.getFileDescription() != null ?StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription())):"");
				if(file.getRegionScopes() != null){
					Long locationScopeId = file.getRegionScopes().getRegionScopesId();
					if(locationScopeId.equals(2L))
					 fileVO.setLocationName(stateDAO.get(file.getLocationValue()).getStateName());
					else if(locationScopeId.equals(3L))
					 fileVO.setLocationName(districtDAO.get(file.getLocationValue()).getDistrictName());
					else if(locationScopeId.equals(4L))
					 fileVO.setLocationName(constituencyDAO.get(file.getLocationValue()).getName());
					else if(locationScopeId.equals(5L))
					 fileVO.setLocationName(tehsilDAO.get(file.getLocationValue()).getTehsilName());
					
					fileVO.setLocationId(file.getLocationValue());
				}
				if(file.getFont() != null){
					fileVO.setFlagSet(file.getFont().getName());//setting font name
					fileVO.setProblemFileId(file.getFont().getFontId().longValue());
					fileVO.setFontId(file.getFont().getFontId());
				}
				if(file.getDescFont() != null){
					fileVO.setDescEenadu(true);
				}
				fileVO.setFilePath1(file.getFilePath());
				int responseCount = 0 ;
				//fileVO.setResponseCount(candidateNewsResponseDAO.getFileGalleryIdByResponseGalleryId(fileGallary.getFileGallaryId()).size());
				responseCount = newsResponseDAO.getCandidateNewsResponseFileIds(file.getFileId()).size();
				fileVO.setResponseCount(responseCount);
				Set<FileSourceLanguage> fileSourceLanguages = file.getFileSourceLanguage();
				List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguages);
				Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
				
				List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(0);
				if(fileSourceLanguageList != null && fileSourceLanguageList.size() > 0)
				{
					for(FileSourceLanguage fileSourceLanguage:fileSourceLanguageList)
					{
					   FileVO fileVOSourceLanguage = new FileVO();
					   fileVOSourceLanguage.setSource(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSource():null);
					   fileVOSourceLanguage.setSourceId(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSourceId():null);
					   fileVOSourceLanguage.setLanguage(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():null);
					   fileVOSourceLanguage.setLanguegeId(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguageId():null);
					   fileVOSourceLanguage.setFileSourceLanguageId(fileSourceLanguage.getFileSourceLanguageId());
						
						List<FileVO> fileVOPathsList = new ArrayList<FileVO>();
						Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
						for(FilePaths filePath : filePathsSet)
						{
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
				}
				
				fileVO.setMultipleSource(fileVOSourceLanguageList.size());
				Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
				fileVO.setFileVOList(fileVOSourceLanguageList);
				 
				
				fileVO.setFileDate(file.getFileDate() == null ? null :
					sdf.format(file.getFileDate()));
				 fileVO.setReqFileDate(file.getFileDate());
				 
				fileVOsList.add(fileVO);
			 }
			/*if(fileGalleryIdsList != null && fileGalleryIdsList.size() > 0)
			{
			 Map<Long,String> candidateNamesMap = new HashMap<Long, String>(0);
			 List<Object[]> list = candidateRelatedNewsDAO.getCandidateNameByFileGalleryIdsList(fileGalleryIdsList);
			 if(list != null && list.size() > 0)
			  for(Object[] params:list)
				 candidateNamesMap.put((Long)params[0], params[1] != null?params[1].toString():" ");
			 
			 for(FileVO fileVO:fileVOsList)
			  fileVO.setCandidateName(candidateNamesMap.get(fileVO.getContentId()));
			 
			}
			
			if(fileVOsList != null && fileVOsList.size() > 0)
			{
			 Collections.sort(fileVOsList,dateSort);
			 Collections.reverse(fileVOsList);
			}*/
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in setfileGallaryDetails() method, Exception - ",e);
			
		}
	}
 
/* public List<SelectOptionVO> getCandidateRelatedSubCategoriesByCandidateId(Long candidateId,String queryType)
	{
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		try{
			
		//	List<Object[]> list = partyGalleryDAO.getCandidateRelatedGallaries(candidateId, partyId, fromDate, toDate, queryType);
			List<Object[]> list = candidatePartyCategoryDAO.getCandidateRelatedCategories(candidateId, queryType);
			if(list != null && list.size() > 0)
			  for(Object[] params : list)
			   selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
			
			return selectOptionVOList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getCandidateRelatedGallaries() method, Exception - ",e);
			return null;
		}
		

	}
 */
 
 public List<SelectOptionVO> getCandidateRelatedSubCategoriesByCandidateId(Long candidateId,String fromDateStr,String toDateStr,String queryType)
	{
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
			 fromDate = format.parse(fromDateStr);
			if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
			 toDate = format.parse(toDateStr);
		//	List<Object[]> list = partyGalleryDAO.getCandidateRelatedGallaries(candidateId, partyId, fromDate, toDate, queryType);
			List<Object[]> list = candidatePartyCategoryDAO.getCandidateRelatedCategories(candidateId,fromDate, toDate, queryType);
			if(list != null && list.size() > 0)
			  for(Object[] params : list)
			   selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
			
			return selectOptionVOList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getCandidateRelatedGallaries() method, Exception - ",e);
			return null;
		}
		

	}
 
 
 public List<SelectOptionVO> getCandidateRelatedKeywordsByCandidateId(Long candidateId,String fromDateStr,String toDateStr,String queryType)
	{
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		try{
			Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && !fromDateStr.equalsIgnoreCase(""))
		 fromDate = format.parse(fromDateStr);
		if(toDateStr != null && !toDateStr.equalsIgnoreCase(""))
		 toDate = format.parse(toDateStr);
			
		//	List<Object[]> list = partyGalleryDAO.getCandidateRelatedGallaries(candidateId, partyId, fromDate, toDate, queryType);
			List<Object[]> list = candidatePartyKeywordDAO.getCandidateRelatedkeywords(candidateId,fromDate, toDate,queryType);
			if(list != null && list.size() > 0)
			  for(Object[] params : list)
			   selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!=null?params[1].toString():""));
			
			return selectOptionVOList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getCandidateRelatedGallaries() method, Exception - ",e);
			return null;
		}
		

	}
 
 
 public List<SelectOptionVO> getLatestGalleries()
 {
	 List<SelectOptionVO> returnList = null;
	 
	 List<Object[]> gallarysList = candidatePartyCategoryDAO.getLatestGallerices();
	 if(gallarysList != null && gallarysList.size() > 0)
	 {
		 returnList = new ArrayList<SelectOptionVO>();
		 for (Object[] parms : gallarysList) {
			 SelectOptionVO selectOptionVO = new SelectOptionVO();
			 selectOptionVO.setId((Long)parms[0]);
			 selectOptionVO.setName(parms[1].toString());
			 returnList.add(selectOptionVO);
		}
	 }
	 return returnList;
 }
 
 public List<FileVO> getSelectedGallaryDetails(int startIndex,int maxIndex,Long gallaryId)
 {
	 List<Long> fileIds = new ArrayList<Long>();
	 LinkedHashMap<Long,FileVO> fileMap = new LinkedHashMap<Long, FileVO>();
	 try{
		 Long count = candidatePartyCategoryDAO.getCountForNewsInASelectedGallery(gallaryId);
		
		 List<Object[]> gallarysList = candidatePartyCategoryDAO.getSelectdGalleryNews(startIndex,maxIndex,gallaryId);
		 populateNewsDataToVO(gallarysList,fileIds,fileMap,count);
		 
		 return new ArrayList<FileVO>(fileMap.values());
	 }catch(Exception e){
		 return new ArrayList<FileVO>();
	 }
 }
 
 public void populateNewsDataToVO(List<Object[]> newsList,List<Long> fileIds,LinkedHashMap<Long,FileVO> fileMap,Long count){
	 Map<Long,String> fileSourceMap = null;
	 List<FileVO> returnList = null;
	 if(newsList != null && newsList.size() > 0)
	 {
		 Set<Long> fileIdList = new HashSet<Long>();
			for(Object[] file:newsList){
				fileIdList.add((Long)file[4]);
			}
			Map<Long,String> candidateNames = getCandidateNames(fileIdList);
			Map<Long,String> keywordsCount = getKeywordsCountByFileId(fileIdList);
			Map<Long,String> categorysCount = getCategorysCountByFileId(fileIdList);
		 for (Object[] parms : newsList) {
			 FileVO fileVO = new FileVO();
			 
			 fileVO.setTitle(parms[0] != null ? StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(parms[0].toString())):"");
			 fileVO.setDescription(parms[1] != null ? StringEscapeUtils.unescapeJava(CommonStringUtils.removeSpecialCharsFromAString(parms[1].toString())):"");
			 fileVO.setFileDate(parms[2] != null ? parms[2].toString() : "");
			 fileVO.setFileName1(parms[3] != null ? parms[3].toString() : null);
			 fileVO.setCount(count.intValue());
			 fileVO.setFileId((Long)parms[4]);
			 fileVO.setFontId( parms[5] != null ? (Integer)parms[5] :0);
			 if(parms[6] != null)
			 fileVO.setDescEenadu(true);
			 fileVO.setCategoryName(categorysCount.get((Long)parms[4])!=null ? categorysCount.get((Long)parms[4]) : "");
			 fileVO.setKeywords(keywordsCount.get((Long)parms[4])!=null ? keywordsCount.get((Long)parms[4]) : "");
			 fileVO.setCandidateName(candidateNames.get((Long)parms[4])!=null ? candidateNames.get((Long)parms[4]) : "");
			 fileVO.setResponseCount(newsResponseDAO.getCandidateNewsResponseFileIdsByFileID(fileVO.getFileId()).size());
			 fileIds.add(fileVO.getFileId());
			 fileMap.put(fileVO.getFileId(), fileVO);
			 
		}
	 }
	 List<Object[]> fileSoureces = null;
	  if(fileIds != null && fileIds.size() > 0)
	  fileSoureces = fileSourceLanguageDAO.getFileSourceType(fileIds);
	  if(fileSoureces != null && fileSoureces.size() > 0)
	  {
		 fileSourceMap = new HashMap<Long, String>();
		 for (Object[] parms : fileSoureces) {
			 if(fileSourceMap.get((Long)parms[0]) == null)
			 {			 
				 fileSourceMap.put((Long)parms[0], parms[1].toString());
			 }
			 else
			 {
				 fileSourceMap.put((Long)parms[0], fileSourceMap.get((Long)parms[0])+" , "+parms[1].toString());
			 }
			
		}
	 }
	  List<Object[]> keyWordsList = null;
	  List<Object[]> galleriesList = null;
	  if(fileIds.size()>0){
	  keyWordsList = candidatePartyKeywordDAO.getKeyWords(fileIds);
	  galleriesList = candidatePartyCategoryDAO.getCategoriesList(fileIds);
	  }
	  Map<Long,List<String>> keyWordsMap = new HashMap<Long, List<String>>();
	  Map<Long,List<String>> categoerysMap = new HashMap<Long, List<String>>();
	  List<String> keysList = null;
	  List<String> categoerysList = null;
	  if(keyWordsList != null && keyWordsList.size() > 0)
	  {
		 for (Object[] parms : keyWordsList)
		 {
			
			if(keyWordsMap.get((Long)parms[0]) == null)
			{
				keysList = new ArrayList<String>();
				 keyWordsMap.put((Long)parms[0], keysList);
			}
			keysList.add(parms[1].toString());
			
		 } 
	  }
	 
	  if(galleriesList != null && galleriesList.size() > 0)
	  {
		  for (Object[] parms : galleriesList)
		  {
			  if(categoerysMap.get((Long)parms[0]) == null)
			  {
				  categoerysList = new ArrayList<String>();
				  categoerysMap.put((Long)parms[0], categoerysList);
			  }
			  categoerysList.add(parms[1].toString());
		  } 
	  }
	 Set<Long> fileSet = fileMap.keySet();
	 if(fileSet != null && fileSet.size() > 0)
	 {
		 returnList = new ArrayList<FileVO>();
		 for (Long fileId : fileSet) {
			 FileVO fileVO = fileMap.get(fileId);
			 fileVO.setSource(fileSourceMap.get(fileId));
			 fileVO.setKeywordsList(keyWordsMap.get(fileId));
			 fileVO.setCategoriesList(categoerysMap.get(fileId));
			 returnList.add(fileVO);
		}
	 }
 }
 public List<SelectOptionVO> getLocationValuesByRegionScope1(String regionScope, String queryType)
 {
	 List<SelectOptionVO> selectOptionVOList = null;
	 try{
		 List<Object[]> locationList = null;
		 List<Object[]> list = null;
		 List<Long> locationValuesList = new ArrayList<Long>();
		 Long regionScopeId = regionScopesDAO.getRegionScopeIdByScope(regionScope);
		 Map<Long,Long> locationCount = new HashMap<Long, Long>(0);
		  if(regionScope != null && regionScope.equalsIgnoreCase(IConstants.CONSTITUENCY)){
		     //locationValuesList = fileGallaryDAO.getLocationValuesByRegionScopeId2(regionScopeId,queryType,872L);
			 list = fileGallaryDAO.getLocationValuesCountByRegionScopeId2(regionScopeId,queryType,872L);
		  }
		    else if(regionScope != null && regionScope.equalsIgnoreCase(IConstants.DISTRICT)){
			     //locationValuesList = fileGallaryDAO.getLocationValuesByRegionScopeId1(regionScopeId,queryType,872L);
		    	 list = fileGallaryDAO.getLocationValuesCountByRegionScopeId1(regionScopeId,queryType,872L);
			} 
			  if(list != null && list.size() > 0)
			  for(Object[] params : list)
			  {
				  locationValuesList.add((Long)params[1]);  
				 Long count = locationCount.get((Long)params[1]) ;
				 if(count == null)
				  locationCount.put((Long)params[1], (Long)params[0]);
				 else
					 locationCount.put((Long)params[1], (Long)params[0]+count); 
			  }
			  if(regionScope != null && regionScope.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 {
				if(locationValuesList != null && locationValuesList.size() >0)
				locationList = constituencyDAO.getConstituencyNameByConstituencyIdsList(locationValuesList);
			  }
			 else if(regionScope != null && regionScope.equalsIgnoreCase(IConstants.DISTRICT)) 
			 {
				if(locationValuesList != null && locationValuesList.size() >0)
					locationList = districtDAO.getDistrictNamesByDistrictIdsList(locationValuesList); 
			 }
		 
		 
		 if(locationList != null && locationList.size() > 0)
		 {
			selectOptionVOList = new ArrayList<SelectOptionVO>(0);
			selectOptionVOList.add(new SelectOptionVO(0L,"Select"));
			for(Object[] params: locationList)
			{
				Long count = locationCount.get((Long)params[0]);
			   selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1] != null?WordUtils.capitalize(params[1].toString().toLowerCase() +"(" +count+ ")"):" "));
			}
		 }
		 
		 return selectOptionVOList;
	 }catch (Exception e) {
      e.printStackTrace();
      log.error("Exception Occured in getLocationValuesByRegionScope() method, Exception - ",e);
      return null;
	 }
 }

 public List<GallaryVO> getAllGalariyes()
 {
	 List<GallaryVO> returnlist = null;
	 List<Object[]> gallarysList = candidatePartyCategoryDAO.getAllCategoryes();
	 if(gallarysList != null && gallarysList.size() > 0)
	 {
		 returnlist = new ArrayList<GallaryVO>();
		 for (Object[] parms : gallarysList) {
			 GallaryVO gallaryVO = new GallaryVO();
			 gallaryVO.setGallaryId(parms[0] != null ? (Long)parms[0] :0l);
			 gallaryVO.setGallaryName(parms[1] != null ? parms[1].toString() : "");
			 gallaryVO.setDescription(parms[2] != null ? parms[2].toString() : "");
			 if(parms[3] != null)
			 {
				 Long count = (Long)parms[3];
				 gallaryVO.setCount(count.intValue());
			 }
			
			 returnlist.add(gallaryVO);
		}
	 }
	 return returnlist;
 }
 
 public List<SelectOptionVO> getPartiesList()
 {
	 try{
		 List<SelectOptionVO> selectOptionVOList = null;
		 List<Object[]> list = partyDAO.getPartiesList();
		 if(list != null && list.size() > 0)
		 {
			selectOptionVOList = new ArrayList<SelectOptionVO>(0);
			for(Object[] params:list)
			 selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1]!= null?params[1].toString():" "));
		 }
		 
		return selectOptionVOList; 
	 }catch (Exception e) {
		//e.printStackTrace();
		log.error("Exception Occured in getPartiesList() method, Exception - ",e);
		return null;
	}
 }
 
 public List<SelectOptionVO> getDesignationsList()
 {
	try{
	 List<SelectOptionVO> selectOptionVOList = null;
	 List<Object[]> list = designationDAO.getDesignationsList();
	 if(list != null && list.size() > 0)
	 {
	   selectOptionVOList = new ArrayList<SelectOptionVO>(0);
	   for(Object[] params: list)
	    selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1] != null?params[1].toString():" "));
	 }
	return selectOptionVOList;
	
	}catch (Exception e) {
	 e.printStackTrace();
	 log.error(" Exception Occured in getDesignationsList() method, Exception - "+e);
	 return null;
	}
 }
 
  
 public String isKeywordExist(Long userId,String keyword){
	 log.debug("");
	 String status = null;
	 try{
		List<Object[]> keywordsList = keywordDAO.getKeyWordIdByName(keyword);
		if(keywordsList != null && keywordsList.size() > 0){
			status = " "+keyword+" Keyword Already Exist.Please Enter New Keyword. ";				
		}
		else
		{
			Keyword keyword1 = saveKeyword(userDAO.get(userId).getUserId(),keyword);
			  status = " "+keyword+" Keyword Was Successfully Created.";
		}
		
	 }catch (Exception e) {
		log.error("Exception rised in isKeywordExist ",e);
	}
	 return status;
 }
 
 public String mergeSelectedKeywords(final Long userId,final List<Long> keywordsList,final String newKeyword1){
	 
	 
	 log.debug("Entered into mergeSelectedKeywords() in CandidateDetailsService class. ");
	 String status = "failure";
	 try{
		 Object vo1 = transactionTemplate.execute(new TransactionCallback() {
			 
			public Object doInTransaction(TransactionStatus status1) {
				@SuppressWarnings("unused")
				List<Long> keywordIds  = null;
				List<Object[]> keywordsDetails = keywordDAO.getKeyWordIdByName(newKeyword1);
				Keyword newKeyword = null;
				SelectOptionVO vo = new SelectOptionVO();
				
				if(keywordsDetails.size() == 0){
					newKeyword = saveKeyword(userId,newKeyword1);
					keywordIds = updateKeywordList(newKeyword,userId,keywordsList);	
					vo.setPopulateId(0L);
			   }
				else{
					for (Object[] parms : keywordsDetails) {						
						keywordIds = updateKeywordList(keywordDAO.get(Long.valueOf(parms[0].toString())),userId,keywordsList);
						vo.setPopulateId(Long.valueOf(parms[0].toString()));
					}					
				}
				vo.setLocationValuesList(keywordIds);
				
				return vo;				
			}
			
		});

		 if(vo1 != null){
			 
			 SelectOptionVO vo2 = (SelectOptionVO) vo1;
			 List<Long> keywordsDetails = vo2.getLocationValuesList();
			 
			 if(keywordsDetails != null && keywordsDetails.size() > 0){
				 
				 for (Long keywordId : keywordsDetails) {
					 try{
						 if(vo2.getPopulateId() != 0){
							 if(vo2.getPopulateId() != keywordId)
								 candidatePartyKeywordDAO.removeKeywordsList(keywordId);
						 }
							 else
								 candidatePartyKeywordDAO.removeKeywordsList(keywordId);
					 }catch(Exception e){}
				}
				
			  status = " "+newKeyword1 + " was successfully updated. ";
			 }
			 else 
				 status = " Error occured while updating keywords.";
		 }
		 
				
	 
	 }catch (Exception e) {
		log.error("Exception ocuured in mergeSelectedKeywords() in CandidateDetailsService class :",e);
		status = "Error occured while updating Keyword details.";
	}
	 
	 return status;
 }
 
 private List<Long> updateKeywordList(Keyword newKeyword,Long userId,List<Long> keywordsList){
	 
	 final Map<Long,List<CandidatePartyKeyword>> candidateKeys = new HashMap<Long,List<CandidatePartyKeyword>>();
	 List<Long> keywordIds = new ArrayList<Long>();
	 try{
	 List<CandidatePartyKeyword> candidateKeywordsDetails = null;
	 
		for (Long keywordId : keywordsList) {
			 
			 candidateKeywordsDetails = candidatePartyKeywordDAO.getCandidatePartyKeywordList(keywordId);
			 
			 if(candidateKeywordsDetails !=null && candidateKeywordsDetails.size() > 0){
				candidateKeys.put(keywordId, candidateKeywordsDetails);	
			 }
			 keywordIds.add(keywordId);
		 }
		 
		if(candidateKeys != null && candidateKeys.size() >0){			
		
		 for (Long key : candidateKeys.keySet()) {
			 
			   List<CandidatePartyKeyword> candidateFileDetails = candidateKeys.get(key);
			   
			   if(candidateFileDetails !=null && candidateFileDetails.size() > 0){
				   
				   for (CandidatePartyKeyword parms : candidateFileDetails) {
		
					   parms.setCandidatePartyFile(parms.getCandidatePartyFile());
					   parms.setKeyword(newKeyword);
						   
					   candidatePartyKeywordDAO.save(parms);									  
				   }
			   }
			  // keywordIds.add(key);
		 }
		 	 		
		 List<Long> candidatePartyFileIds = candidatePartyKeywordDAO.getCandidateFileIds(newKeyword.getKeywordId());
		 Set<Long> candidatePartyFileId = new HashSet<Long>();
		 candidatePartyFileId.addAll(candidatePartyFileIds);
		 candidatePartyFileIds.clear();
		 candidatePartyFileIds.addAll(candidatePartyFileId);
		 
		 List<CandidatePartyKeyword> candidateList = null;
		 
		 for (Long parms : candidatePartyFileIds) {
			 candidateList = candidatePartyKeywordDAO.getCandidatePartyKeywordListByUserwise(parms,newKeyword.getKeywordId());
		
			 Long count = 0L;
			 for (CandidatePartyKeyword params : candidateList) {
				 count = count+1;
					 if(count >1){
						 try{
						 candidatePartyKeywordDAO.removeDublicateData(params.getCandidatePartyKeywordId(),newKeyword.getKeywordId());
						 //candidatePartyKeywordDAO.remove(params.getCandidatePartyKeywordId());
						 }catch(Exception e){
							 log.error("Exception ocuured while deleting duplicate candidatePartyKeyword :",e);					 
						 }
					 }
			}
		 
		 }
		 
		}

	
	 }catch(Exception e){
		 keywordIds.clear();
	 }
	 
	 return keywordIds;
 }
private Keyword saveKeyword(Long userId, String keyword1){
	 log.error("entered into saveKeyword() in CandidateDetailsService class. ");
	 try{
		 Keyword keyword = new Keyword();
		 
		 keyword.setType(keyword1.toString() != null ? keyword1.toString():"");
		 keyword.setCreatedDate(dateUtilService.getCurrentDateAndTime());
		 keyword.setCreatedBy(userId);
		
		 keyword = keywordDAO.save(keyword);
		 
		 return keyword;	 
		 
	 }catch (Exception e) {		 
	  log.error("Exception ocuured in saveKeyword() in CandidateDetailsService class :",e);	
	 return null;
	 }
 }
public void testgetCandidatesListByPartyIdsList()
{
	List<Object[]> data1 = nominationDAO.getTestCandidatesParticipatedInAssemblyAndParlimentElections();
	
	System.out.println(data1.size());
	for(Object[] c:data1){
		Candidate can = candidateDAO.get((Long)c[0]);
		can.setDesignation(designationDAO.get(1l));
		//can.setDesignation(designationDAO.get(2l));
		can.setParty(partyDAO.get((Long)c[1]));
		can.setParliament(constituencyDAO.get((Long)c[2]));
		//can.setAssembly(constituencyDAO.get((Long)c[2]));
		//can.setDistrict(districtDAO.get((Long)c[3]));
		can.setState(stateDAO.get(1l));
		candidateDAO.save(can);
		constituencyDAO.flushAndclearSession();
		
	}
	List<Object[]> data2 = nominationDAO.getTestCandidatesParticipatedInAssemblyAndParlimentElectionsAss();
	List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.getAllAssemblyConstituencyByParliamId();
	Map<Long,Long> consti = new HashMap<Long,Long>();
	for(Object[] dat:list){
		consti.put((Long)dat[0], (Long)dat[1]);
	}
	for(Object[] c:data2){
		Candidate can = candidateDAO.get((Long)c[0]);
		//can.setDesignation(designationDAO.get(1l));
		can.setDesignation(designationDAO.get(2l));
		can.setParty(partyDAO.get((Long)c[1]));
		if(consti.get((Long)c[2]) != null)
		can.setParliament(constituencyDAO.get(consti.get((Long)c[2])));
		can.setAssembly(constituencyDAO.get((Long)c[2]));
		can.setDistrict(districtDAO.get((Long)c[3]));
		can.setState(stateDAO.get(1l));
		candidateDAO.save(can);
		constituencyDAO.flushAndclearSession();
		
	}
}

	public List<SelectOptionVO> getFilteredCandidates(Long stateId,String partyId,String designationId,String locationType,String locationId,String searchString){
		log.debug("entered into getFilteredCandidates() in CandidateDetailsService class . ");
		List<SelectOptionVO> candidateList= new ArrayList<SelectOptionVO>();
		try{
			List<Object[]> candidatesList = candidateDAO.getFilteredCandidateListByName(Long.valueOf(partyId.toString()),Long.valueOf(designationId),Long.valueOf(locationId.toString()),locationType,searchString);
			if(candidatesList != null && candidatesList.size() > 0){
				for (Object[] params : candidatesList) {
					SelectOptionVO vo = new SelectOptionVO();
					vo.setId(Long.valueOf(params[0].toString()));
					vo.setName(params[1].toString());
					candidateList.add(vo);
				}			
			}
			
		}catch (Exception e) {
			log.error("Exception occured in getFilteredCandidates() in CandidateDetailsService class",e);
			candidateList = null;
		}	
		return candidateList;
	}
	
	public Map<Long,String> getKeywordsCountByFileId(Set<Long> fileIds){
		Map<Long,String> keywordsCount = new HashMap<Long, String>();
		List<Object[]> fileKeywords = candidatePartyFileDAO.getKeywordsCountByFileIds(fileIds);
		for(Object[] param:fileKeywords){
			String count = keywordsCount.get((Long)param[0]);
			if(count != null && count != "")
				keywordsCount.put((Long)param[0], count+","+param[1]);
			else
				keywordsCount.put((Long)param[0], param[1].toString());
		}
		return keywordsCount;
	}
	
	public Map<Long,String> getCategorysCountByFileId(Set<Long> fileIds){
		Map<Long,String> categorysCount = new HashMap<Long, String>();
		List<Object[]> fileKeywords = candidatePartyCategoryDAO.getCategorysCountByFileId(fileIds);
		for(Object[] param:fileKeywords){
			String count = categorysCount.get((Long)param[0]);
			if(count != null && count != "")
				categorysCount.put((Long)param[0], count+" , " +param[1]);
			else
				categorysCount.put((Long)param[0], param[1].toString());
		}
		return categorysCount;
	}
 
public void saveNewSource(File file,FileSourceVO fileSourceVO){

	FileSourceLanguage fileSourceLanguage = new FileSourceLanguage();
	fileSourceLanguage.setFile(file);
	if(fileSourceVO.getNewsDescCheck() != null)
	 fileSourceLanguage.setFont(fontDAO.get(1));
	
	fileSourceLanguage.setNewsDetailedDescription(fileSourceVO.getCompleteDesc());
	fileSourceLanguage.setLanguage(sourceLanguageDAO.get(fileSourceVO.getSourceLangId()));
	fileSourceLanguage.setSource(sourceDAO.get(fileSourceVO.getFileSourceId()));
	
	
	fileSourceLanguage = fileSourceLanguageDAO.save(fileSourceLanguage);
	
	if(fileSourceVO.getFileVOsList() != null && fileSourceVO.getFileVOsList().size() > 0)
	{
	 Long orderNo = 1L;
	 for(FileVO filePathFileVO :fileSourceVO.getFileVOsList())
	 {
		 FilePaths filePaths = new FilePaths();
		 
		 filePaths.setFilePath(filePathFileVO.getDisplayImagePath() !=null?filePathFileVO.getDisplayImagePath().trim():null);
		 filePaths.setOrderNo(orderNo);
		 filePaths.setHaveThumnails(IConstants.FALSE);
		 filePaths.setEdition(filePathFileVO.getNewsEditionId());
		 if(filePathFileVO.getPageNo() != null)
		 filePaths.setPageNo(filePathFileVO.getPageNo().intValue());
		 if(filePathFileVO.getImportanceId() != null)
		 filePaths.setNewsLength(filePathFileVO.getImportanceId().intValue());
		 if(filePathFileVO.getFileType()!=null)
		  filePaths.setFileType(fileTypeDAO.getFileType(filePathFileVO.getFileType()).get(0));
		 
		 filePaths.setFileSourceLanguage(fileSourceLanguage);
		 filePathsDAO.save(filePaths); 
		 orderNo++;
	 }
	}
}
public void saveNewKeyWords(FileVO fileVO){
	if(fileVO.getCandidatePartyNewsVOList() != null)
	{
	  List<String> keywordsList = new ArrayList<String>(0);
	    CandidatePartyNewsVO newsVO = fileVO.getCandidatePartyNewsVOList();
		if(newsVO.getDestinationVOList() != null && newsVO.getDestinationVOList().size() > 0)
		 for(CandidatePartyDestinationVO destinationVO :newsVO.getDestinationVOList())	
		 {
		  String keywords = destinationVO.getKeywordsList();
		  if(keywords != null && !keywords.equalsIgnoreCase(""))
		  {
			String[] str = keywords.split(",");
			if(str != null)
			 for(String keyword:str)
			  if(keyword != null && !keyword.trim().equalsIgnoreCase("") && !keywordsList.contains(""+keyword.substring(1)+""))
				  keywordsList.add(keyword.substring(1));
		  }
		 }
	 
	    if(keywordsList != null && keywordsList.size() > 0)
	    {
		  List<String> existingKeywords = keywordDAO.getExistingKeywordsByKeywordsList(keywordsList);
		  for(String keyword:keywordsList)
		   if(!existingKeywords.contains(keyword) && !keyword.equalsIgnoreCase(" "))
		   {
			 /*Keyword keyword2 = new Keyword(); 
			 keyword2.setType(keyword);
			 keyword2.setCreatedDate(dateUtilService.getCurrentDateAndTime());
			 keyword2.setCreatedBy(fileVO.getUserId());
			 keywordDAO.save(keyword2);*/
			   saveKeyword(fileVO.getUserId(),keyword);
		   }
	    }
	}
}

public void saveWhoAndWhomeDetails(FileVO fileVO,File file,Date createdDate){
	if(fileVO.getCandidatePartyNewsVOList() != null)
	{
		boolean sourcePresent = false;
		boolean destinationPresent = false;
		 CandidatePartyNewsVO newsVO = fileVO.getCandidatePartyNewsVOList();
		for(CandidatePartyDestinationVO  source:newsVO.getSourceVOList()){
			if(sourcePresent)
				break;
			if(source != null){
				if((source.getPartyId()!= null &&  source.getPartyId() > 0) || (source.getCandidateId()!= null &&  source.getCandidateId() > 0)){
					sourcePresent = true;
				}
			}
		}
		for(CandidatePartyDestinationVO  destination:newsVO.getDestinationVOList()){
			if(destination != null){
				if(destinationPresent)
					break;
				if(destination != null){
					if((destination.getPartyId()!= null &&  destination.getPartyId() > 0) || (destination.getCandidateId()!= null &&  destination.getCandidateId() > 0)  || (destination.getCategoryIdsStr()!= null &&  destination.getCategoryIdsStr().trim().length() > 0)   || (destination.getKeywordsList()!= null &&  destination.getKeywordsList().trim().length() > 0)){
						destinationPresent = true;
					}
				}
			}
		}
	    if(sourcePresent && destinationPresent)
		    saveSourceAndDestination(file,newsVO,fileVO.getResponseFileIdsStr());
	    else if(destinationPresent)
		{
		  for(CandidatePartyDestinationVO destinationVO :newsVO.getDestinationVOList())
		  {
		   if(destinationVO != null){
			CandidatePartyFile candidatePartyFile = new CandidatePartyFile();
			candidatePartyFile.setFile(file);
			
			candidatePartyFile.setDestinationBenefit(benefitDAO.get(destinationVO.getBenefitId()));
			if(destinationVO.getPartyId() != null && destinationVO.getPartyId() > 0)
			 candidatePartyFile.setDestinationParty(partyDAO.get(destinationVO.getPartyId()));
			if(destinationVO.getCandidateId() != null && destinationVO.getCandidateId() > 0)
			 candidatePartyFile.setDestinationCandidate(candidateDAO.get(destinationVO.getCandidateId()));
			if(createdDate != null){
				candidatePartyFile.setCreatedDate(createdDate);
			}else{
			 candidatePartyFile.setCreatedDate(dateUtilService.getCurrentDateAndTime());
			}
			 candidatePartyFile.setUpdateddate(dateUtilService.getCurrentDateAndTime());
			 
			 candidatePartyFile = candidatePartyFileDAO.save(candidatePartyFile);
			 
			/* if(fileVO.getFileId() != null){
				 NewsResponse newsResponse = new NewsResponse();
				 newsResponse.setFile(fileDAO.get(fileVO.getFileId()));
				 newsResponse.setCandidatePartyFile(candidatePartyFile);
				 newsResponseDAO.save(newsResponse);
			 }*/
			 
			 if(fileVO.getResponseFileIdsStr() != null && fileVO.getResponseFileIdsStr().length() > 0)
			 {
			  String[] fileIdsStr = fileVO.getResponseFileIdsStr().split(",");
			  if(fileIdsStr != null)
			  {
				for(String responseFileId: fileIdsStr)
				{
					NewsResponse newsResponse = new NewsResponse();
					newsResponse.setFile(fileDAO.get(Long.parseLong(responseFileId)));
					newsResponse.setCandidatePartyFile(candidatePartyFile);
					newsResponseDAO.save(newsResponse);	
				}
			  }
			 }
			 //saving categories
			 if(destinationVO.getCategoryIdsStr() != null && destinationVO.getCategoryIdsStr().trim().length() > 0)
			 {
				 String[] str = destinationVO.getCategoryIdsStr().split(",");
				 if(str != null)
				    {
					  for(String categoryId: str)
					  {
						 if(categoryId != null && categoryId.trim().length() > 0){
							CandidatePartyCategory candidatePartyCategory = new CandidatePartyCategory();
							candidatePartyCategory.setGallary(gallaryDAO.get(Long.valueOf(categoryId.trim())));
							candidatePartyCategory.setCandidatePartyFile(candidatePartyFile);
							candidatePartyCategoryDAO.save(candidatePartyCategory);
						 }
					  }
				    }
			 }
			 
			 //keywords saving
			  if(destinationVO.getKeywordsList() != null && !destinationVO.getKeywordsList().equalsIgnoreCase(""))
			  {
			    String[] str = destinationVO.getKeywordsList().split(",");
			    if(str != null)
			    {
				  for(String keyword:str)
				  {
				    if(keyword != null && !keyword.equalsIgnoreCase(""))
				    {
				     CandidatePartyKeyword candidatePartyKeyword = new CandidatePartyKeyword();
				     candidatePartyKeyword.setCandidatePartyFile(candidatePartyFile);
				     candidatePartyKeyword.setKeyword(keywordDAO.get(keywordDAO.getKeywordIdByKeyword(keyword.substring(1)).get(0)));
				     candidatePartyKeywordDAO.save(candidatePartyKeyword);
				    }
				   }
			  	 }
			   }
		  }
		  }
		}
		else if(sourcePresent)
		{
			for(CandidatePartyDestinationVO  source:newsVO.getSourceVOList()){
				if(source != null){
					CandidatePartyFile candidatePartyFile = new CandidatePartyFile();
					candidatePartyFile.setFile(file);
					
					candidatePartyFile.setSourceBenefit(benefitDAO.get(source.getBenefitId()));
					if(source.getMediaId() != null && source.getMediaId() > 0)
					 candidatePartyFile.setMediaId(newsVO.getMediaId());
					else
					{
					  if(source.getPartyId() != null)
					  candidatePartyFile.setSourceParty(partyDAO.get(source.getPartyId()));
					  if(source.getCandidateId() != null && source.getCandidateId() > 0)	
					   candidatePartyFile.setSourceCandidate(candidateDAO.get(source.getCandidateId()));
					}
					 candidatePartyFile = candidatePartyFileDAO.save(candidatePartyFile);
					 
					/*if(fileVO.getFileId() != null){
						 NewsResponse newsResponse = new NewsResponse();
						 newsResponse.setFile(fileDAO.get(fileVO.getFileId()));
						 newsResponse.setCandidatePartyFile(candidatePartyFile);
						 newsResponseDAO.save(newsResponse);
					 }*/
					 
					 if(fileVO.getResponseFileIdsStr() != null && fileVO.getResponseFileIdsStr().length() > 0)
					 {
					  String[] fileIdsStr = fileVO.getResponseFileIdsStr().split(",");
					  if(fileIdsStr != null)
					  {
						for(String responseFileId: fileIdsStr)
						{
							NewsResponse newsResponse = new NewsResponse();
							newsResponse.setFile(fileDAO.get(Long.parseLong(responseFileId)));
							newsResponse.setCandidatePartyFile(candidatePartyFile);
							newsResponseDAO.save(newsResponse);	
						}
					  }
					 }
				}
			}
			
		}
		
	  
	}
}
public ResultStatus editUploadedFileForCandidateParty(final FileVO fileVO)
{
	log.debug("Entered into editUploadedFileForCandidateParty() method in Candidate Details Service()");
	ResultStatus resultStatus = new ResultStatus();
	try{
		log.debug("Entered into uploadAFileForCandidateParty() method in Candidate Details Service()");
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
		 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		UserAddress userAddress = saveFileLocationInUserAddress(fileVO.getLocationScope(),Long.parseLong(fileVO.getLocationValue()));
		
		File file = fileDAO.get(fileVO.getFileId());
		
		file.setFileTitle(fileVO.getTitle());
		file.setFileDescription(fileVO.getDescription());
		file.setSynopsysDescription(fileVO.getFileDescription1()!= null ? fileVO.getFileDescription1():null);
		file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));
		Long regionScopeId = regionScopesDAO.get(fileVO.getLocationScope()).getRegionScopesId();
		
		if(regionScopeId == 5 || regionScopeId == 6 || regionScopeId == 8 ){
			file.setLocationValue(Long.parseLong(fileVO.getLocationValue().substring(1)));			
		}
		else if(regionScopeId == 7){
			Long localEleBodyId = (Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(Long.valueOf(fileVO.getLocationValue().toString().substring(1))).get(0);
			file.setLocationValue(localElectionBodyDAO.get(localEleBodyId).getLocalElectionBodyId());				
		}
		else
			file.setLocationValue(Long.parseLong(fileVO.getLocationValue()));
		
		if(fileVO.getNewsImportanceId() != null && fileVO.getNewsImportanceId() > 0)
		 file.setNewsImportance(newsImportanceDAO.get(fileVO.getNewsImportanceId()));
		
		if(fileVO.getFileVOForDiaplyImage() != null)
		{
		  file.setFileName(fileVO.getFileVOForDiaplyImage().getDisplayImageName());
		  file.setFilePath(fileVO.getFileVOForDiaplyImage().getDisplayImagePath() !=null?fileVO.getFileVOForDiaplyImage().getDisplayImagePath().trim():null);	
		}else if(fileVO.getImgToDisplayDeleted()!=null && fileVO.getImgToDisplayDeleted().equalsIgnoreCase("true")){
			file.setFileName(null);
			  file.setFilePath(null);	
			
		}
		
		if(fileVO.getFileDate() != null)
		{
		 try{
			 file.setFileDate(sdf.parse(fileVO.getFileDate())); 
		 }catch (Exception e) {
		  e.printStackTrace();
		  log.error(e);
		}
		}
		
		file.setUserAddress(userAddress);
		file.setUpdatedBy(userDAO.get(fileVO.getUserId()));
		
		if(fileVO.isEenadu())
			file.setFont(fontDAO.get(1));
		else
			file.setFont(null);
		if(fileVO.isDescEenadu())
			file.setDescFont(fontDAO.get(1));
		else 
			file.setDescFont(null);
		
		if(fileVO.isSynopsysEenadu())
			file.setSynopsysFont(fontDAO.get(1));
		else
			file.setSynopsysFont(null);
		//file.setCreatedDate(dateUtilService.getCurrentDateAndTime());
		file.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
		file.setIsDeleted("N");
		
		if(!fileVO.getVisibility().equalsIgnoreCase("public"))
		 file.setIsPrivate("Y");
		else
			file.setIsPrivate("N");	
		file = fileDAO.save(file);
		
		List<Long> candidatePartyFileIds = candidatePartyFileDAO.getCandidatePartyFileIds(file.getFileId());
		String responseFileIds = "";
		if(candidatePartyFileIds != null && candidatePartyFileIds.size() > 0){
			List<Long> fileIds = newsResponseDAO.getFileIds(candidatePartyFileIds);
			if(fileIds != null && fileIds.size() > 0){
				for(Long fileId:fileIds){
					responseFileIds = responseFileIds+","+fileId;
				}
				responseFileIds = responseFileIds.substring(1);
				fileVO.setResponseFileIdsStr(responseFileIds);
			}
			newsResponseDAO.deleteNewsResponses(candidatePartyFileIds);
			candidatePartyCategoryDAO.deleteCandidatePartyCategories(candidatePartyFileIds);
			candidatePartyKeywordDAO.deleteCandidatePartyKeywords(candidatePartyFileIds);
			//deleating existing who and whome
			candidatePartyFileDAO.deleteCandidatePartyFiles(file.getFileId());
		}
		//newly created keywords saving
		saveNewKeyWords(fileVO);
		
		//saving who,whome,categories,keywords saving
		saveWhoAndWhomeDetails(fileVO,file,file.getCreatedDate());
		if(fileVO.getFileSourceVOList() !=null && fileVO.getFileSourceVOList().size()>0){
			updateUploadedFileDetails(fileVO,file);
		}
		}
		});
		
		
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
		}catch (Exception e) {
			log.error("Exception encountered in editUploadedFileForCandidateParty() method, Exception - ",e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
}
	public void updateUploadedFileDetails(FileVO fileVO,File file){
		log.debug("entered into updateUploadedFileDetails() method of CandidateDetailsService class.");
		try {		
			if(fileVO.getFileSourceVOList() != null && fileVO.getFileSourceVOList().size() > 0)
			{
			  for(FileSourceVO fileSourceVO :fileVO.getFileSourceVOList())
			  { 
				if(fileSourceVO != null){ 
				 if(fileSourceVO.getFileSourceLangId() == null){
				       saveNewSource(file,fileSourceVO);
				 }
			     else if(fileSourceVO.getFileSourceLangId() != null && fileSourceVO.getDeleted() != null &&  fileSourceVO.getDeleted().equalsIgnoreCase("true")){
			    	 filePathsDAO.deleteFilePathsByFileSourceLangId(fileSourceVO.getFileSourceLangId());
			    	 fileSourceLanguageDAO.deleteFileSourceLanguage(fileSourceVO.getFileSourceLangId());
			     }else if(fileSourceVO.getFileSourceLangId() != null){
			    	 FileSourceLanguage fileSourceLanguage = fileSourceLanguageDAO.get(fileSourceVO.getFileSourceLangId());
			    	 if(fileSourceVO.getNewsDescCheck() != null)
			    		 fileSourceLanguage.setFont(fontDAO.get(1));
			    	 else
			    		 fileSourceLanguage.setFont(null);
			    	 
			    		fileSourceLanguage.setNewsDetailedDescription(fileSourceVO.getCompleteDesc());
			    		fileSourceLanguage.setLanguage(sourceLanguageDAO.get(fileSourceVO.getSourceLangId()));
			    		fileSourceLanguage.setSource(sourceDAO.get(fileSourceVO.getFileSourceId()));
			    		fileSourceLanguage = fileSourceLanguageDAO.save(fileSourceLanguage);
			    		Long orderNo = 0L;
			    		for(FileVO filePath:fileSourceVO.getFileVOsList()){
			    			if(filePath.getPathId() != null){
			    				if(filePath.getDeleted() != null && filePath.getDeleted().equalsIgnoreCase("true")){
			    					filePathsDAO.deleteFilePathDetailsByFilePAthsId(filePath.getPathId());
			    				}else{
			    					FilePaths filePaths = filePathsDAO.get(filePath.getPathId());
			    					orderNo = filePaths.getOrderNo();
			    					saveFilePaths(filePath,fileSourceLanguage,filePaths);
			    				}
			    			}else{
			    				FilePaths filePaths = new FilePaths();
			    				orderNo = filePathsDAO.getMaxfilePathsOrderNo(fileSourceVO.getFileSourceLangId());
			    				if(orderNo == null)
			    					orderNo = 0l;
			    				orderNo = orderNo+1;
			    				filePaths.setOrderNo(orderNo);
		    					saveFilePaths(filePath,fileSourceLanguage,filePaths);
			    			}
			    		}
			    		
			     }
			   }
			  }
			}
		} catch (Exception e) {
			log.error("exception occured in updateUploadedFileDetails() method of CandidateDetailsService class.",e);
		}
	}
	public void saveFilePaths(FileVO filePath,FileSourceLanguage fileSourceLanguage,FilePaths filePaths){
		log.debug("entered into saveFilePaths() method of CandidateDetailsService class.");
		try {
				filePaths.setFileSourceLanguage(fileSourceLanguage);
				if(filePath.getDisplayImagePath() != null)
				 filePaths.setFilePath(filePath.getDisplayImagePath().trim());
				 //filePaths.setOrderNo(orderNo);
				 filePaths.setHaveThumnails(IConstants.FALSE);
				 filePaths.setEdition(filePath.getNewsEditionId());
				 if(filePath.getPageNo() != null)
				 filePaths.setPageNo(filePath.getPageNo().intValue());
				 if(filePath.getImportanceId() != null)
				 filePaths.setNewsLength(filePath.getImportanceId().intValue());
				 if(filePath.getFileType()!=null)
				  filePaths.setFileType(fileTypeDAO.getFileType(filePath.getFileType()).get(0));
				 filePaths.setFileSourceLanguage(fileSourceLanguage);
				 filePathsDAO.save(filePaths); 
				 
			} catch (Exception e) {
				log.error("exception occured in saveFilePaths() method of CandidateDetailsService class.",e);
			}
		
	}

public SelectOptionVO getDesignationOfCandidateFromCandidateTable(Long candidateId)
{
	try{
		//List<Object[]> list = candidateDAO.getDesignationsAndLocation(candidateId);
		SelectOptionVO optionVO = new SelectOptionVO();
		Candidate candidate = candidateDAO.get(candidateId);
		optionVO.setId(candidate.getDesignation().getDesignationId());
		if(candidate.getAssembly() != null){
			optionVO.setType("Assembly");
	 		optionVO.setConstituencyId(candidate.getAssembly().getConstituencyId());
		}else if(candidate.getParliament() != null){
			optionVO.setType("Parliament");
	 		optionVO.setConstituencyId(candidate.getParliament().getConstituencyId());
		}else{
			optionVO.setType("Country");
		}
	 	return optionVO;

	}
	catch(Exception e){
		log.error("Exception Occured in getDesignationOfCandidateFromCandidateTable() method, Exception - ",e);
		return new SelectOptionVO();
	}

}
}
