/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.util.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAbusedCommentsDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidatePageCustomPagesDAO;
import com.itgrids.partyanalyst.dao.ICandidateProfileDescriptionDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateSubscriptionsDAO;
import com.itgrids.partyanalyst.dao.ICandidateUpdatesEmailDAO;
import com.itgrids.partyanalyst.dao.ICategoryDAO;
import com.itgrids.partyanalyst.dao.ICommentDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.ICustomPageDAO;
import com.itgrids.partyanalyst.dao.ICustomPageTypeDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionGoverningBodyDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IFileTypeDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMessageToCandidateDAO;
import com.itgrids.partyanalyst.dao.IMessageToPartyDAO;
import com.itgrids.partyanalyst.dao.INewsImportanceDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
import com.itgrids.partyanalyst.dao.IPartyPageCustomPagesDAO;
import com.itgrids.partyanalyst.dao.IPartySubscriptionsDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageCustomPagesDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageSubscriptionsDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserCandidateRelationDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserGallaryDAO;
import com.itgrids.partyanalyst.dao.IUserPartyRelationDAO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateMinistriesVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.CustomPageVO;
import com.itgrids.partyanalyst.dto.ElectionGoverningBodyVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.MetaInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AbusedComments;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidatePageCustomPages;
import com.itgrids.partyanalyst.model.CandidateProfileDescription;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.CandidateSubscriptions;
import com.itgrids.partyanalyst.model.CandidateUpdatesEmail;
import com.itgrids.partyanalyst.model.Category;
import com.itgrids.partyanalyst.model.Comment;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ContentType;
import com.itgrids.partyanalyst.model.CustomPage;
import com.itgrids.partyanalyst.model.CustomPageType;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionGoverningBody;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.model.Gallary;
import com.itgrids.partyanalyst.model.MessageToCandidate;
import com.itgrids.partyanalyst.model.NewsImportance;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyPageCustomPages;
import com.itgrids.partyanalyst.model.PartySubscriptions;
import com.itgrids.partyanalyst.model.RegionScopes;
import com.itgrids.partyanalyst.model.Source;
import com.itgrids.partyanalyst.model.SourceLanguage;
import com.itgrids.partyanalyst.model.SpecialPage;
import com.itgrids.partyanalyst.model.SpecialPageCustomPages;
import com.itgrids.partyanalyst.model.SpecialPageSubscriptions;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.UserCandidateRelation;
import com.itgrids.partyanalyst.model.UserGallary;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CandidateDetailsService implements ICandidateDetailsService {

	private ICandidateResultDAO candidateResultDAO;
	private static final Logger log = Logger.getLogger(CandidateDetailsService.class);
	private ICandidateDAO candidateDAO;
	private IGallaryDAO gallaryDAO;
	private IFileGallaryDAO fileGallaryDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IContentTypeDAO contentTypeDAO;
	private IUserGallaryDAO userGallaryDAO;
	private IRegionScopesDAO regionScopesDAO;
	private ISourceDAO sourceDAO;
	private ICountryDAO countryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO; 
	private ITehsilDAO tehsilDAO;  
	private IHamletDAO hamletDAO;  
	private ILocalElectionBodyDAO localElectionBodyDAO;  
	private IBoothDAO boothDAO; 
	private ICandidateProfileDescriptionDAO candidateProfileDescriptionDAO;
	private IFileDAO fileDAO;
	private IFileTypeDAO fileTypeDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private INominationDAO nominationDAO;
	private CandidateProfileDescription candidateProfileDescription;
    private IMessageToCandidateDAO messageToCandidateDAO;
	private IUserCandidateRelationDAO userCandidateRelationDAO;
	private ICandidateUpdatesEmailDAO candidateUpdatesEmailDAO;
	private ISourceLanguageDAO sourceLanguageDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private List<SelectOptionVO> candidatesList;
	private ICategoryDAO categoryDAO; 
	private TransactionTemplate transactionTemplate;
	private INewsImportanceDAO newsImportanceDAO;
	private IPartyGalleryDAO partyGalleryDAO;	
	private ISpecialPageGalleryDAO specialPageGalleryDAO;
	private IMessageToPartyDAO messageToPartyDAO;
	private IElectionDAO electionDAO;
	private IElectionGoverningBodyDAO electionGoverningBodyDAO;
	private ICandidatePageCustomPagesDAO candidatePageCustomPagesDAO;
	private ICustomPageTypeDAO customPageTypeDAO;
	private ICustomPageDAO customPageDAO;
	private IPartyDAO partyDAO;
	private IPartyPageCustomPagesDAO partyPageCustomPagesDAO;
	private ISpecialPageDAO specialPageDAO;
	private ISpecialPageCustomPagesDAO specialPageCustomPagesDAO;
	private IFilePathsDAO filePathsDAO;
	private IFileSourceLanguageDAO fileSourceLanguageDAO;
	private IUserDAO userDAO;
	private ICandidateSubscriptionsDAO candidateSubscriptionsDAO;
	private IPartySubscriptionsDAO partySubscriptionsDAO;
	private ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO;
	private IUserPartyRelationDAO userPartyRelationDAO;	
	private IAbusedCommentsDAO abusedCommentsDAO;
	private ICommentDAO commentDAO;
	
	
	public ICommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(ICommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public IAbusedCommentsDAO getAbusedCommentsDAO() {
		return abusedCommentsDAO;
	}

	public void setAbusedCommentsDAO(IAbusedCommentsDAO abusedCommentsDAO) {
		this.abusedCommentsDAO = abusedCommentsDAO;
	}

	public IPartySubscriptionsDAO getPartySubscriptionsDAO() {
		return partySubscriptionsDAO;
	}

	public void setPartySubscriptionsDAO(
			IPartySubscriptionsDAO partySubscriptionsDAO) {
		this.partySubscriptionsDAO = partySubscriptionsDAO;
	}

	public ISpecialPageSubscriptionsDAO getSpecialPageSubscriptionsDAO() {
		return specialPageSubscriptionsDAO;
	}

	public void setSpecialPageSubscriptionsDAO(
			ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO) {
		this.specialPageSubscriptionsDAO = specialPageSubscriptionsDAO;
	}

	public ICandidateSubscriptionsDAO getCandidateSubscriptionsDAO() {
		return candidateSubscriptionsDAO;
	}

	public void setCandidateSubscriptionsDAO(
			ICandidateSubscriptionsDAO candidateSubscriptionsDAO) {
		this.candidateSubscriptionsDAO = candidateSubscriptionsDAO;
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

	public ISpecialPageCustomPagesDAO getSpecialPageCustomPagesDAO() {
		return specialPageCustomPagesDAO;
	}

	public void setSpecialPageCustomPagesDAO(
			ISpecialPageCustomPagesDAO specialPageCustomPagesDAO) {
		this.specialPageCustomPagesDAO = specialPageCustomPagesDAO;
	}

	public ISpecialPageDAO getSpecialPageDAO() {
		return specialPageDAO;
	}

	public void setSpecialPageDAO(ISpecialPageDAO specialPageDAO) {
		this.specialPageDAO = specialPageDAO;
	}

	public IPartyPageCustomPagesDAO getPartyPageCustomPagesDAO() {
		return partyPageCustomPagesDAO;
	}

	public void setPartyPageCustomPagesDAO(
			IPartyPageCustomPagesDAO partyPageCustomPagesDAO) {
		this.partyPageCustomPagesDAO = partyPageCustomPagesDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public ICustomPageDAO getCustomPageDAO() {
		return customPageDAO;
	}

	public void setCustomPageDAO(ICustomPageDAO customPageDAO) {
		this.customPageDAO = customPageDAO;
	}

	public ICustomPageTypeDAO getCustomPageTypeDAO() {
		return customPageTypeDAO;
	}

	public void setCustomPageTypeDAO(ICustomPageTypeDAO customPageTypeDAO) {
		this.customPageTypeDAO = customPageTypeDAO;
	}

	public ICandidatePageCustomPagesDAO getCandidatePageCustomPagesDAO() {
		return candidatePageCustomPagesDAO;
	}

	public void setCandidatePageCustomPagesDAO(
			ICandidatePageCustomPagesDAO candidatePageCustomPagesDAO) {
		this.candidatePageCustomPagesDAO = candidatePageCustomPagesDAO;
	}

	public IElectionGoverningBodyDAO getElectionGoverningBodyDAO() {
		return electionGoverningBodyDAO;
	}

	public void setElectionGoverningBodyDAO(
			IElectionGoverningBodyDAO electionGoverningBodyDAO) {
		this.electionGoverningBodyDAO = electionGoverningBodyDAO;
	}

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IMessageToPartyDAO getMessageToPartyDAO() {
		return messageToPartyDAO;
	}

	public void setMessageToPartyDAO(IMessageToPartyDAO messageToPartyDAO) {
		this.messageToPartyDAO = messageToPartyDAO;
	}

	public ISpecialPageGalleryDAO getSpecialPageGalleryDAO() {
		return specialPageGalleryDAO;
	}

	public void setSpecialPageGalleryDAO(
			ISpecialPageGalleryDAO specialPageGalleryDAO) {
		this.specialPageGalleryDAO = specialPageGalleryDAO;
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

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public List<SelectOptionVO> getCandidatesList() {
		return candidatesList;
	}

	public void setCandidatesList(List<SelectOptionVO> candidatesList) {
		this.candidatesList = candidatesList;
	}

		public ISourceLanguageDAO getSourceLanguageDAO() {
		return sourceLanguageDAO;
	}

	public void setSourceLanguageDAO(ISourceLanguageDAO sourceLanguageDAO) {
		this.sourceLanguageDAO = sourceLanguageDAO;
	}

	public ICandidateUpdatesEmailDAO getCandidateUpdatesEmailDAO() {
		return candidateUpdatesEmailDAO;
	}

	public void setCandidateUpdatesEmailDAO(
			ICandidateUpdatesEmailDAO candidateUpdatesEmailDAO) {
		this.candidateUpdatesEmailDAO = candidateUpdatesEmailDAO;
	}

	
      
	public IMessageToCandidateDAO getMessageToCandidateDAO() {
		return messageToCandidateDAO;
	}

	public void setMessageToCandidateDAO(
			IMessageToCandidateDAO messageToCandidateDAO) {
		this.messageToCandidateDAO = messageToCandidateDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
		
	public CandidateProfileDescription getCandidateProfileDescription() {
		return candidateProfileDescription;
	}

	public void setCandidateProfileDescription(
			CandidateProfileDescription candidateProfileDescription) {
		this.candidateProfileDescription = candidateProfileDescription;
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

	public ICandidateResultDAO getCandidateResultDAO() {
		return candidateResultDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
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

	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}

	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}
	
	
	public ICandidateProfileDescriptionDAO getCandidateProfileDescriptionDAO() {
		return candidateProfileDescriptionDAO;
	}

	public void setCandidateProfileDescriptionDAO(
			ICandidateProfileDescriptionDAO candidateProfileDescriptionDAO) {
		this.candidateProfileDescriptionDAO = candidateProfileDescriptionDAO;
	}

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

	public IUserPartyRelationDAO getUserPartyRelationDAO() {
		return userPartyRelationDAO;
	}

	public void setUserPartyRelationDAO(IUserPartyRelationDAO userPartyRelationDAO) {
		this.userPartyRelationDAO = userPartyRelationDAO;
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
	}
	public List<FileVO> searchNewsDetails(FileVO inputs){
		  List<FileVO> retValue = new ArrayList<FileVO>();
		  try{
			  List<Object[]> results = fileGallaryDAO.getNewsRecordsBySearchCriteria(inputs,IConstants.NEWS_GALLARY);
			
			  for(Object[] newsDetails: results){
			    FileVO fileVO = new FileVO();
		    	fileVO.setName(newsDetails[0] != null ? newsDetails[0].toString() :"");		    			    	
		   	  	fileVO.setPath(newsDetails[1] != null ? newsDetails[1].toString() :"");
		   	    fileVO.setFileTitle1(newsDetails[2] != null ? newsDetails[2].toString() :"");
		   	    fileVO.setFileDescription1(newsDetails[3] != null ? newsDetails[3].toString() :"");
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

	
	public List<FileVO> getDistrictDetailsByStateId(Long stateId)
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
	}
	
	public List<FileVO> getStateDetails()
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
	}
	
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
		    			
		    			filePaths.add(filePath.getFilePath());
		    			
		    				
		    		}
		    	
		    		fileVO.setFilePath(filePaths);	
		    	}
		   	  	fileVO.setPath(imageDetails[2] != null ? imageDetails[2].toString() :"");
		   	    fileVO.setFileTitle1(file.getFileTitle() != null ? file.getFileTitle() :"");
		   	    fileVO.setFileDescription1(file.getFileDescription() != null ? file.getFileDescription() :"");
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
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try{
			  FileVO fileVO;
			  List<Long> list = fileGallaryDAO.getNewsCountByScope(candidateId,null,queryType); 
			 
			 List<Object[]> results = fileGallaryDAO.getFirstFourNewsToDisplay(candidateId,firstResult,maxResult,queryType);
			 for(Object[] newsDetails: results){
				    fileVO = new FileVO();
				    fileVO.setFileId((Long)newsDetails[0]);
			    	fileVO.setName(newsDetails[1] != null ? newsDetails[1].toString() :"");		    			    	
			   	  	fileVO.setPath(newsDetails[2] != null ? newsDetails[2].toString() :"");
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? newsDetails[3].toString() :"");
			   	    fileVO.setFileDescription1(newsDetails[4] != null ? newsDetails[4].toString() :"");
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
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? newsDetails[3].toString() :"");
			   	    fileVO.setFileDescription1(newsDetails[4] != null ? newsDetails[4].toString() :"");
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
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? newsDetails[3].toString() :"");
			   	    fileVO.setFileDescription1(newsDetails[4] != null ? newsDetails[4].toString() :"");
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
	
	
	/**
	* This Method will Create A New Gallary by taking Basic information
	* @param GallaryVO 
	* @return ResultStatus
	* @author kamalakarDandu
	*/
	
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
	
	public ResultStatus uploadAFile(final FileVO fileVO)
	{
		
		ResultStatus resultStatus = new ResultStatus();
		try{
			log.debug("Entered into uploadAFile() method in Candidate Details Service()");
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
			File file = new File();
			FileSourceLanguage fileSourceLanguage = new FileSourceLanguage();
			FilePaths filePaths = new FilePaths();
			FileGallary fileGallary = new FileGallary();
			Long orderNO = 1L;
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			file.setFileName(fileVO.getName());
			file.setFilePath(fileVO.getPath());
			//file.setFileType(fileTypeDAO.getFileType(fileVO.getContentType()).get(0));
			file.setFileTitle(fileVO.getTitle());
			file.setFileDescription(fileVO.getDescription().replace("\r\n", ""));
			file.setKeywords(fileVO.getKeywords());
			
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
			
				file = fileDAO.save(file);
				/*List<Object> maxOrderNo = filePathsDAO.getMaxOrderNo();
					if(maxOrderNo == null && maxOrderNo.size()==0)
					 	orderNO = 1L;
					else if(maxOrderNo.get(0)!=null)
						orderNO = (Long)maxOrderNo.get(0)+1;*/
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
						}
						if(fileVO.getFilePath() !=null)
						{
							
						 filePaths.setFilePath(fileVO.getFilePath().get(i));
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
						/*Long languageId = (Long)sourceLanguageDAO.getLanguageIdByLanguage("No Language").get(0);
						Long sourceId = (Long)sourceDAO.getSourceIdBySource("No Source").get(0);*/
						fileSourceLanguage.setLanguage(null);
						fileSourceLanguage.setSource(null);
						fileSourceLanguage.setFile(file);
						fileSourceLanguage = fileSourceLanguageDAO.save(fileSourceLanguage);
						if(fileVO.getFilePath() !=null)
						{
						for(int i=0;i<fileVO.getFilePath().size();i++)
						{
							 filePaths.setFilePath(fileVO.getFilePath().get(i));
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
				
				fileGallaryDAO.save(fileGallary);
				
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
	
	/**
	 * This method will give Gallaries Id and name in List<SelectOptionVO>, 
	 * when we pass candidateId as Argument.
	 * @author Kamalakar Dandu
	 * @param Long candidateId
	 * @return List<SelectOptionVO>
	 */
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
	
	/**
	 * This method returns Candidate Profile Descriptions when we pass CandidateId as Argument
	 * @author Sachin
	 * @param Long candidateId
	 * @return List <String>
	 */
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
	
	/**
	 * This method will save the Candidate Profile Descriptions when we pass the candidate id and description (In GallaryVO) as Argument
	 * @author Sachin
	 * @param GallaryVO gallaryVO
	 * @return ResultStatus
	 */
	
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
	
	/**
	 * This method will populate  the Candidate Profile Descriptions information when we pass the candidate id  as Argument
	 * @author Sachin
	 * @param Long candidateId
	 * @return List<GallaryVO>
	 */
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
		gallary.setDescription(params[1].toString());
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
	
	/**
	 * This method will update  the Candidate Profile Descriptions information when we pass the gallaryVO (to update the description and order no) and candidate id  as Argument
	 * @author Sachin
	 * @param List<GallaryVO> gallaryVO
	 * @param Long candidateId
	 * @return ResultStatus
	 */
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
	
	/**
	 * This method will save  the message send to the candidate and it will take argument as gallaryVO(sender name,constituencyId and message)
	 * @author Sachin
	 * @param GallaryVO gallaryVO
	 * @return ResultStatus
	 */
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
	
	/**
	 * This method will delete  the Candidate Profile Descriptions information when we pass the CandidateProfileDescriptionId  as argument
	 * @author Sachin
	 * @param Long profDescId
	 * @return ResultStatus
	 */
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
	/**
	 * This Method will give Videos Details As List<FileVO> when we pass candidateId,start Index and Max results As Arguements
	 * @author Kamalakar Dandu
	 * @param Long candidateId
	 * @param Integer startIndex
	 * @param Integer maxRecords
	 * @return List<FileVO>
	 */
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
	
	/**
	 * This Method Copy The Data From File Object To FileVO Object
	 * @author Kamalakar Dandu
	 * @param File
	 * @return FileVO
	 */
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
	{/*sasi*/
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
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? newsDetails[3].toString() :"");
			   	    fileVO.setFileDescription1(newsDetails[4] != null ? newsDetails[4].toString() :"");
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

	
	/**
	 * This method will return source (for Video and news)
	 * 
	 * @return
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
		
	try{
		List<Category> category = categoryDAO.getAll();
		SelectOptionVO selectOptionVO = null;
		for(Category result:category)
		{
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(result.getCategoryId());
			selectOptionVO.setName(result.getCategoryType());
			categorySelectList.add(selectOptionVO);
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
		List<NewsImportance> newsImportance = newsImportanceDAO.getAll();
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
			fileVO.setFileTitle1(objects[3]!=null?objects[3].toString():"");
			fileVO.setFileDescription1(objects[4]!=null?objects[4].toString():"");
			fileVO.setFilePath1(objects[5]!=null?objects[5].toString():"");
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
	   file.setFileTitle(fileVO.getFileTitle1());
	   file.setFileDescription(fileVO.getDescription());
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
	 List<FileGallary> videoGallaryresultList = new ArrayList<FileGallary>();
	 List<FileVO> resultList = new ArrayList<FileVO>();
	 
 	 List<Long> photosList = fileGallaryDAO.getRecentlyUploadedPhotoIds(startIndex, maxResults);
	 photoGallaryresultList = fileGallaryDAO.getFileGallaryByFileIdsList(photosList);
	 resultList = setToFileVO(photoGallaryresultList);
	 resultMap.put("photogallary", resultList);
	 
	// String queryStr1 = "where model.gallary.contentType.contentType = 'News Gallary'";
	 //List<Long> newsList = fileGallaryDAO.getRecentlyUploadedFileIds(startIndex, maxResults, queryStr1);
	 //newsGallaryresultList = fileGallaryDAO.getFileGallaryByFileIdsListForNews(newsList);
	 newsGallaryresultList = fileGallaryDAO.getHomePageNewsDetails(startIndex, maxResults);
	 resultList = setToFileVO(newsGallaryresultList);
	 resultMap.put("NewsGallary", resultList);
	 
	 String queryStr2 = "where model.gallary.contentType.contentType = 'Video Gallary'";
	 List<Long> videosList = fileGallaryDAO.getRecentlyUploadedFileIds(startIndex, maxResults, queryStr2);
	 videoGallaryresultList = fileGallaryDAO.getFileGallaryByFileIdsList(videosList);
	 resultList = setToFileVO(videoGallaryresultList);
	 resultMap.put("VideoGallary", resultList);
		 
     return resultMap;
	 }catch (Exception e) {
		 log.error("Exception occured - "+e);
		 return null;
	 }
}
 
 
 public List<FileVO> setToFileVO(List<FileGallary> result)
 {
	List<FileVO> fileVOs = new ArrayList<FileVO>();
	FileVO fileVO = new FileVO();
	
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
			 }
			 else
			 {
				List<Party> party = partyGalleryDAO.getPartyByGalleryId(result.get(i).getGallary().getGallaryId());
				
				if(party != null && party.size() > 0)
				{
					fileVO.setCandidateName(party.get(0).getShortName()+" Party");
					fileVO.setFileType("Party");
					fileVO.setCandidateId(party.get(0).getPartyId());
				}
				else
				{
					List<SpecialPage> specialPage = specialPageGalleryDAO.getSpecialPageByGalleryId(result.get(i).getGallary().getGallaryId());
					
					if(specialPage != null && specialPage.size() > 0)
					{
						fileVO.setCandidateName(specialPage.get(0).getHeading()+" Page");
						fileVO.setFileType("Special Page");
						fileVO.setCandidateId(specialPage.get(0).getSpecialPageId());
					}
				}
			 }
			 
			 fileVO.setContentType(result.get(i).getGallary().getContentType().getContentType());
			 fileVO.setContentId(result.get(i).getFileGallaryId());
			 fileVO.setFileId(result.get(i).getFile().getFileId());
			 fileVO.setDescription(result.get(i).getFile().getFileDescription());
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
			 fileVO.setFileTitle1(result.get(i).getFile().getFileTitle());
			 fileVO.setGallaryName(result.get(i).getGallary().getName());
			 fileVO.setGallaryUpdatedDate(result.get(i).getGallary().getUpdateddate().toString());
			 
			 if(result.get(i).getGallary().getContentType().getContentType().equalsIgnoreCase(IConstants.VIDEO_GALLARY))
				 fileVO.setPathOfFile(filePath);
			 
			 if(result.get(i).getFile().getFileDate()!= null)
				 fileVO.setFileDate(result.get(i).getFile().getFileDate().toString());
			 if(result.get(i).getGallary().getContentType().getContentType().equalsIgnoreCase(IConstants.PHOTO_GALLARY))
			 	fileVO.setPathOfFile(filePath);
			 if(result.get(i).getFile().getFileDate() != null)
				 fileVO.setFileDate(result.get(i).getFile().getFileDate().toString());
			 
			 if(source != null)
				 fileVO.setSource(source);
			 
			 if(language != null)
				 fileVO.setLanguage(language);
			 
			 if(!checkForFileExistance(fileVOs,fileVO.getFileId()))
				 fileVOs.add(fileVO);
		   }
		  }
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
	}
 public String getLocationDetails(Long scope,Long locationValue){
	 if(scope != null)
	 { 
	   if(scope == 1L)
	    {
	    	return countryDAO.get(1L).getCountryName();
	    }
	    else if(scope == 2L && locationValue != null)
	    {
	    	return stateDAO.get(locationValue).getStateName();
	    }
	    else if(scope == 3L && locationValue != null)
	    {
	    	return districtDAO.get(locationValue).getDistrictName();
	    }
	    else if(scope == 4L && locationValue != null)
	    {
	    	return constituencyDAO.get(locationValue).getName();
	    }
	    else if(scope == 5L && locationValue != null)
	    {
	    	return tehsilDAO.get(locationValue).getTehsilName();
	    }
	    else if(scope == 6L && locationValue != null)
	    {
	    	return hamletDAO.get(locationValue).getHamletName();
	    }
	    else if(scope == 7L && locationValue != null)
	    {
	    	return localElectionBodyDAO.get(locationValue).getName();
	    }
	    else if(scope == 8L && locationValue != null)
	    {
	    	return constituencyDAO.get(locationValue).getName();
	    }
	    else if(scope == 9L && locationValue != null)
	    {
	    	return boothDAO.get(locationValue).getPartName();
	    }
	    else  return " ";
	 }
	    return " ";
   }
 public List<CandidateCommentsVO> getMessages(String fromDate, String toDate,String selectstatus,String decidestatus)
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
		/**
		 * 
		 * @param electionId
		 * @return selectOptionList
		 * @author Swathi
		 */
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
			    			
			    			filePaths.add(filePath.getFilePath());
			    			
			    				
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
    	try
    	{
	    	List<ElectionGoverningBody> list = electionGoverningBodyDAO.getAllMinistersDetails(electionId);
	    	if(list !=null && list.size() > 0)
	    	{
	    		resultList = new ArrayList<CandidateMinistriesVO>(0);
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
		    		}
		    		
		    		ElectionGoverningBodyVO governingBodyVO = new ElectionGoverningBodyVO();
	    			governingBodyVO.setMinistry(params.getPositionScope().getElectionGoverningBodyPosition().getGoverningBodyPosition());
	    			
	    			if(governingBodyVO.getMinistry().equalsIgnoreCase(IConstants.CHIEF_MINISTER))
	    				candidateMinistriesVO.setIsChiefMinister(true);
	    			
	    			if(governingBodyVO.getMinistry().equalsIgnoreCase(IConstants.DEPUTY_CHIEF_MINISTER))
	    				candidateMinistriesVO.setIsDeputyChiefMinister(true);
	    			
	    			if(governingBodyVO.getMinistry().equalsIgnoreCase(IConstants.PRIME_MINISTER))
	    				candidateMinistriesVO.setIsPrimeMinister(true);
	    			
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
	    	
    	return resultList;
    	}
    	
    	catch(Exception e)
    	{
    		log.error("Exception occured in getAllMinistersDetails() of CandidateDetailsService");
    		return resultList;
    	}
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
     }
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

	private List<FileVO> convertDataToFileVO(List<Object[]> dataList,List<Long> list,Long candidateId,String language,String source){
		
		if(log.isDebugEnabled())
			log.debug("Entered into convertDataToFileVO() of candidateDetailsService");

	     List<FileVO> retValue = new ArrayList<FileVO>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try{
			  FileVO fileVO;
			  
			  for(Object[] data : dataList){
				  
				 try{
				 File file = (File)data[0];
				 fileVO = new FileVO();
                 
				 fileVO.setFileId(file.getFileId());
				 fileVO.setTotalResultsCount(list.get(0));
				 fileVO.setFileTitle1(file.getFileTitle());
				 fileVO.setFileDescription1(file.getFileDescription());
				 fileVO.setFileDate(data[2] != null ? (sdf.format((Date)data[2])) :"");
				 fileVO.setContentId((Long)data[1]);
				 fileVO.setCandidateId(candidateId);
				 fileVO.setCategoryId(file.getCategory().getCategoryId());
				 fileVO.setCategoryType(file.getCategory().getCategoryType());
				 fileVO.setNewsImportanceId(file.getNewsImportance().getNewsImportanceId());
				 fileVO.setImportance(file.getNewsImportance().getImportance());
				 
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
				
				
				List comments = abusedCommentsDAO.getAllAbuseComment(firstDate, secondDate,selectstatus);
				candidateComments = commentsDetailsFromAbuseCommeentList(comments);
							
			}catch(Exception e){
				log.error("Exception in getAbuseComment in candidate details service",e);
			}
			return candidateComments;
	 }
	public List<CandidateCommentsVO> commentsDetailsFromAbuseCommeentList(List comments)
	{
		List<CandidateCommentsVO> commentsList = new ArrayList<CandidateCommentsVO>();
		 if(log.isDebugEnabled())
			 log.debug("commentsDetailsFromList()method ......");
		
		if(comments != null || comments.size() > 0)
		{
			
			for (int i = 0; i < comments.size(); i++)
			{  
				String username = null;
				CandidateCommentsVO comment = new CandidateCommentsVO();
				Object[] params = (Object[])comments.get(i);
				comment.setCandidate(params[0].toString()!= null ? params[0].toString():"");
				if(params[1].toString()!=null && params[2].toString()!=null)
					 username = params[1].toString()+" "+params[2].toString();
				
				comment.setPostedBY(username);
				comment.setMessage(params[3].toString()!= null ? params[3].toString():"");
				
				if(params[4] != null && params[4].toString().equalsIgnoreCase(IConstants.TRUE))
					comment.setStatus(IConstants.APPROVED);
				if(params[4] != null && params[4].toString().equalsIgnoreCase(IConstants.FALSE))
					comment.setStatus(IConstants.NEW);
				if(params[5] != null && params[5].toString().equalsIgnoreCase(IConstants.TRUE))
					comment.setStatus(IConstants.REJECTED);
				comment.setTime(params[6].toString() != null ? params[6].toString():"");
				
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
	 
	
}