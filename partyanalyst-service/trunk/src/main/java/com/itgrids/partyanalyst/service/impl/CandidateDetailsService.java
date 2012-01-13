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
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.util.StringUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidateProfileDescriptionDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateUpdatesEmailDAO;
import com.itgrids.partyanalyst.dao.ICategoryDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IFileTypeDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMessageToCandidateDAO;
import com.itgrids.partyanalyst.dao.INewsImportanceDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserCandidateRelationDAO;
import com.itgrids.partyanalyst.dao.IUserGallaryDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateProfileDescription;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.CandidateUpdatesEmail;
import com.itgrids.partyanalyst.model.Category;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ContentType;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.Gallary;
import com.itgrids.partyanalyst.model.MessageToCandidate;
import com.itgrids.partyanalyst.model.NewsImportance;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.RegionScopes;
import com.itgrids.partyanalyst.model.Source;
import com.itgrids.partyanalyst.model.SourceLanguage;
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
	private IRegistrationDAO registrationDAO;
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

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
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
		   	    Long scope = (Long)newsDetails[5];
		   	    Long locationValue = (Long)newsDetails[6];
		   	    
		   	    if(scope == 1L)
		   	    {
		   	    	fileVO.setLocationValue(countryDAO.get(locationValue).getCountryName());
		   	    }
		   	    else if(scope == 2L)
		   	    {
		   	    	fileVO.setLocationValue(stateDAO.get(locationValue).getStateName());
		   	    }
		   	    else if(scope == 3L)
		   	    {
		   	    	fileVO.setLocationValue(districtDAO.get(locationValue).getDistrictName());
		   	    }
		   	    else if(scope == 4L)
		   	    {
		   	    	fileVO.setLocationValue(constituencyDAO.get(locationValue).getName());
		   	    }
		   	    else if(scope == 5L)
		   	    {
		   	 	   	fileVO.setLocationValue(tehsilDAO.get(locationValue).getTehsilName());
		   	    }
		   	    else if(scope == 6L)
		   	    {
	    	    	fileVO.setLocationValue(hamletDAO.get(locationValue).getHamletName());
		   	    }
		   	    else if(scope == 7L)
		   	    {
		   	    	fileVO.setLocationValue(localElectionBodyDAO.get(locationValue).getName());
		   	    }
		   	    else if(scope == 8L)
		   	    {
		   	    	fileVO.setLocationValue(constituencyDAO.get(locationValue).getName());
		   	    }
		   	    else if(scope == 9L)
		   	    {
		   	    	fileVO.setLocationValue(boothDAO.get(locationValue).getPartName());
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
				party = result.getNomination().getParty();
				constituency = result.getNomination().getConstituencyElection().getConstituency();
				election = result.getNomination().getConstituencyElection().getElection();
				
				CandidateDetailsVO candidateDetails = new CandidateDetailsVO();
				
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
			
		 return candidateElectionDetails;
		 }
	return null; 
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
		    List<Object[]> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    for(Object[] startingRecord: record){
		    	fileVO.setFileId((Long)startingRecord[0]);
		    	fileVO.setName(startingRecord[1] != null ? WordUtils.capitalize(startingRecord[1].toString()) :"");
		    	fileVO.setPath(startingRecord[2].toString());
		    	fileVO.setTitle(startingRecord[3] != null ? WordUtils.capitalize(startingRecord[3].toString()) :"");
		    	
		    }
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
		    List<Object[]> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    for(Object[] startingRecord: record){
		    if(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()>0L)
		    {
		    	fileVO.setFileId((Long)startingRecord[0]);
		    	fileVO.setName(startingRecord[1] != null ? startingRecord[1].toString() :"");		    			    	
		    	fileVO.setPath(startingRecord[2] != null ? startingRecord[2].toString() :"");
		    	fileVO.setTitle(startingRecord[3] != null ? startingRecord[3].toString() :"");
		    	fileVO.setGallaryId((Long)gallary[0]);
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
		    List<Object[]> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    for(Object[] startingRecord: record){
		    	fileVO.setFileId((Long)startingRecord[0]);
		    	fileVO.setName(startingRecord[1] != null ? startingRecord[1].toString() :"");		    			    	
		    	fileVO.setPath(startingRecord[2] != null ? startingRecord[2].toString() :"");
		    	String title =""; 
		   	    if(startingRecord[3] != null && startingRecord[3].toString().length()>=18)
		   	    {
		   	    	title = startingRecord[3].toString().substring(0, 17);
		   	    	title = title+"...";
		   	    }
		   	    else
		   	    {
		   	    if(startingRecord[3] != null)
		   	    {	
		   	    	title = startingRecord[3].toString();
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
			    fileVO.setFileId((Long)imageDetails[0]);
		    	fileVO.setName(imageDetails[1] != null ? imageDetails[1].toString() :"");		    			    	
		   	  	fileVO.setPath(imageDetails[2] != null ? imageDetails[2].toString() :"");
		   	    fileVO.setFileTitle1(imageDetails[3] != null ? imageDetails[3].toString() :"");
		   	    fileVO.setFileDescription1(imageDetails[4] != null ? imageDetails[4].toString() :"");
		   	    fileVO.setGallaryName(imageDetails[5] != null ? imageDetails[5].toString() :"");
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
		 List<FileVO> retValue = new ArrayList<FileVO>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try{
			 List<Object[]> results = fileGallaryDAO.getFirstFourNewsToDisplay(candidateId,0,4,"Public");
			 for(Object[] newsDetails: results){
				    FileVO fileVO = new FileVO();
				    fileVO.setFileId((Long)newsDetails[0]);
			    	fileVO.setName(newsDetails[1] != null ? newsDetails[1].toString() :"");		    			    	
			   	  	fileVO.setPath(newsDetails[2] != null ? newsDetails[2].toString() :"");
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? newsDetails[3].toString() :"");
			   	    String desc=""; 
			   	    if(newsDetails[4] != null && newsDetails[4].toString().length()>=55)
			   	    {
			   	    desc = newsDetails[4].toString().substring(0, 50);
			   	    desc = desc+"...";
			   	    }
			   	    else
			   	    {
			   	    if(newsDetails[4] != null)
			   	     {
			   	      desc = newsDetails[4].toString();
			   	     }
			   	    }
			   	    fileVO.setFileDescription1(desc);
			   	    fileVO.setSource(newsDetails[5] != null ? newsDetails[5].toString() :"");
			   	 fileVO.setLanguage(newsDetails[6] != null ? newsDetails[6].toString() :"");
			   	    fileVO.setFileDate(newsDetails[7] != null ? (sdf.format((Date)newsDetails[7])) :"");
			   	    fileVO.setCandidateId((Long)newsDetails[8]);
			    	retValue.add(fileVO);	  
			 }
			 
			return retValue;
			}
			catch(Exception e){
				e.printStackTrace();
				return retValue;
			}
		}
	public List<FileVO> getNewsToDisplay(Long candidateId,int firstResult,int maxResult,String queryType){
		 List<FileVO> retValue = new ArrayList<FileVO>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try{
			 List<Object[]> results = fileGallaryDAO.getFirstFourNewsToDisplay(candidateId,firstResult,maxResult,queryType);
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
			   	    fileVO.setNewsImportanceId((Long)newsDetails[9]);
			   	    fileVO.setImportance(newsDetails[10] != null ? newsDetails[10].toString() :"");
			   	    
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
				    FileVO fileVO = new FileVO();
				    fileVO.setFileId((Long)newsDetails[0]);
			    	fileVO.setName(newsDetails[1] != null ? newsDetails[1].toString() :"");		    			    	
			   	  	fileVO.setPath(newsDetails[2] != null ? newsDetails[2].toString() :"");
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? newsDetails[3].toString() :"");
			   	    fileVO.setFileDescription1(newsDetails[4] != null ? newsDetails[4].toString() :"");
			   	    fileVO.setSource(newsDetails[5] != null ? newsDetails[5].toString() :"");
			   	    fileVO.setFileDate(newsDetails[6] != null ? (sdf.format((Date)newsDetails[6])) :"");
			    	retValue.add(fileVO);	  
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
			userGallary.setRegistration(registrationDAO.get(gallaryVO.getUserId()));
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
			
			if(fileVO.getFileDate() != null && fileVO.getFileDate().length() > 0)
				try {
					file.setFileDate(sdf.parse(fileVO.getFileDate()));
				} catch (ParseException e) {
					log.error(e);
				}
			
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
		candidate.setMessage(gallaryVO.getDescription());
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
				fileVO.setPath(file.getFilePath());
				fileVO.setFileType(file.getFileType() != null ? file.getFileType().getType() : "");
				fileVO.setTitle(file.getFileTitle());
				fileVO.setDescription(file.getFileDescription());
				fileVO.setKeywords(file.getKeywords());
				fileVO.setSource(file.getSource());
				fileVO.setFileDate(file.getFileDate() != null ? sdf.format(file.getFileDate()) : "");
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
			
			fileVO = new FileVO();
			fileVO.setFileId((Long)videos[0]);
			fileVO.setName(videos[1]!=null ? WordUtils.capitalize(videos[1].toString()):"");
			fileVO.setPathOfFile(videos[2].toString());
			fileVO.setTitle(WordUtils.capitalize(videos[3].toString()));
			fileVO.setDescription(WordUtils.capitalize(videos[4].toString()));
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
		  userCandidateRelation.setRegistration(registrationDAO.get(userId));
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{
			 List<Object[]> results = fileGallaryDAO.getNewsByScope(candidateId,scopeType,startIndex,maxResults,queryType,null,null,null,null);
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
			   	    fileVO.setNewsImportanceId((Long)newsDetails[9]);
			   	    fileVO.setImportance(newsDetails[10] != null ? newsDetails[10].toString() :"");
			   	    
			   	    List<Object[]> category = fileDAO.getCategoryDetailsOfAFile((Long)newsDetails[0]);
			   	    if(category != null && category.size() > 0)
			   	    {
			   	    	fileVO.setCategoryId((Long)category.get(0)[0]);
			   	    	fileVO.setCategoryType(category.get(0)[1].toString());
			   	    }
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
	
	public List<FileVO> getNewsByLanguage(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType,String language)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{
			 List<Object[]> results = fileGallaryDAO.getNewsByScope(candidateId,scopeType,startIndex,maxResults,queryType,null,language,null,null);
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
			   	    fileVO.setNewsImportanceId((Long)newsDetails[9]);
			   	    fileVO.setImportance(newsDetails[10] != null ? newsDetails[10].toString() :"");
			   	    
			   	    List<Object[]> category = fileDAO.getCategoryDetailsOfAFile((Long)newsDetails[0]);
			   	    if(category != null && category.size() > 0)
			   	    {
			   	    	fileVO.setCategoryId((Long)category.get(0)[0]);
			   	    	fileVO.setCategoryType(category.get(0)[1].toString());
			   	    }
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
	
	public List<FileVO> getNewsBySource(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType , String source)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{
			 List<Object[]> results = fileGallaryDAO.getNewsByScope(candidateId,scopeType,startIndex,maxResults,queryType,source,null,null,null);
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
			   	    fileVO.setNewsImportanceId((Long)newsDetails[9]);
			   	    fileVO.setImportance(newsDetails[10] != null ? newsDetails[10].toString() :"");
			   	    
			   	    List<Object[]> category = fileDAO.getCategoryDetailsOfAFile((Long)newsDetails[0]);
			   	    if(category != null && category.size() > 0)
			   	    {
			   	    	fileVO.setCategoryId((Long)category.get(0)[0]);
			   	    	fileVO.setCategoryType(category.get(0)[1].toString());
			   	    }
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
	 public ResultStatus subScribeEmailAlertForAUser(String emailId ,Long candidateId){
		 
		 ResultStatus statusCode = new ResultStatus()  ;
		 try {
			  CandidateUpdatesEmail candidateUpdatesEmail = new CandidateUpdatesEmail();
				 
			     candidateUpdatesEmail.setEmail(emailId);
				 candidateUpdatesEmail.setCandidate(candidateDAO.get(candidateId));
				 candidateUpdatesEmail.setUnsubscribed("false");
				 candidateUpdatesEmailDAO.save(candidateUpdatesEmail);
		
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
 
 public Map<String, List<FileVO>> getPhotosNewsVideosUpdateForACandidate(int startIndex,int maxResults){
	
	 Map<String ,List<FileVO>> resultMap = new HashMap<String,List<FileVO>>();
	 List<FileGallary> photoGallaryresultList = new ArrayList<FileGallary>();
	 List<FileGallary> newsGallaryresultList = new ArrayList<FileGallary>();
	 List<FileGallary> videoGallaryresultList = new ArrayList<FileGallary>();
	 List<FileVO> resultList = new ArrayList<FileVO>();
	 
		 String queryStr = "where model.gallary.contentType.contentType ='Photo Gallary'";
		 photoGallaryresultList = fileGallaryDAO.getRecentlyUploadedFiles(startIndex, maxResults, queryStr);
		 resultList = setToFileVO(photoGallaryresultList);
		 resultMap.put("photogallary", resultList);
		 String queryStr1 = "where model.gallary.contentType.contentType = 'News Gallary'";
		 newsGallaryresultList = fileGallaryDAO.getRecentlyUploadedFiles(startIndex, maxResults, queryStr1);
		 resultList = setToFileVO(newsGallaryresultList);
		 resultMap.put("NewsGallary", resultList);
		 String queryStr2 = "where model.gallary.contentType.contentType = 'Video Gallary'";
		 videoGallaryresultList = fileGallaryDAO.getRecentlyUploadedFiles(startIndex, maxResults, queryStr2);
		 resultList = setToFileVO(videoGallaryresultList);
		 resultMap.put("VideoGallary", resultList);
		
     return resultMap;
	 
 
}
 
 
 public List<FileVO> setToFileVO(List<FileGallary> result){
	 List<FileVO> fileVOs = new ArrayList<FileVO>();
	 
	FileVO fileVO = new FileVO();
	if(result !=null){
		  for(int i=0; i<result.size(); i++){
			 fileVO = new FileVO();
			 fileVO.setCandidateName(result.get(i).getGallary().getCandidate().getLastname());
			 fileVO.setContentType(result.get(i).getGallary().getContentType().getContentType());
			 fileVO.setFileName1(result.get(i).getFile().getFileName());
			 fileVO.setPathOfFile(result.get(i).getFile().getFilePath());
			 if(result.get(i).getGallary().getContentType().getContentType().equalsIgnoreCase(IConstants.VIDEO_GALLARY))
				 fileVO.setPathOfFile(result.get(i).getFile().getFilePath());
			 
			 fileVO.setFileTitle1(result.get(i).getFile().getFileTitle());
			 fileVO.setGallaryName(result.get(i).getGallary().getName());
			 fileVO.setGallaryUpdatedDate(result.get(i).getGallary().getUpdateddate().toString());
			 fileVOs.add(fileVO);
		 }
	}
	 return fileVOs;
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
 public List<FileVO> getNewsGalleryByUserIdFromUserGallery(Long userId) {
	List<FileVO> fileVOList = new ArrayList<FileVO>();
	GallaryVO gallaryVO = new GallaryVO();
	FileVO fileVO = new FileVO();
	List gallaryIds = new ArrayList();

	List<Object[]> gallaries = userGallaryDAO.getAllNewsGallaryBasedOnUserId(userId);
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
		fileVO.setPathOfFile(IConstants.UPLOADED_FILES +"/"+filesObj[1].toString());
		fileVO.setTitle(filesObj[3].toString());
		fileVO.setDescription(filesObj[4].toString());
		fileVO.setFileType(filesObj[5].toString());
		fileVO.setScope(filesObj[6].toString());
		fileVO.setFileDate(filesObj[7].toString());
		fileVO.setSource(filesObj[8].toString());
		fileVO.setLanguage(filesObj[9].toString());
		fileVO.setLocationScope(new Long(filesObj[10].toString()));
		Long scopeId = new Long(filesObj[10].toString());
		Long locationId = new Long(filesObj[11].toString());
		fileVO.setLocationScopeValue(getLocationBasedOnScopeId(scopeId,locationId));
		fileVOList.add(fileVO);
	}
	return fileVOList;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{
			 List<Object[]> results = fileGallaryDAO.getNewsByScope(candidateId,scopeType,startIndex,maxResults,queryType,null,null,source,null);
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
			   	    fileVO.setNewsImportanceId((Long)newsDetails[9]);
			   	    fileVO.setImportance(newsDetails[10] != null ? newsDetails[10].toString() :"");
			   	    
			   	    List<Object[]> category = fileDAO.getCategoryDetailsOfAFile((Long)newsDetails[0]);
			   	    if(category != null && category.size() > 0)
			   	    {
			   	    	fileVO.setCategoryId((Long)category.get(0)[0]);
			   	    	fileVO.setCategoryType(category.get(0)[1].toString());
			   	    }
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
 public List<FileVO> getNewsByNewsImportance(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType , String newsImportance)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<FileVO> returnValue = new ArrayList<FileVO>();
		try
		{
			 List<Object[]> results = fileGallaryDAO.getNewsByScope(candidateId,scopeType,startIndex,maxResults,queryType,null,null,null,newsImportance);
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
			   	    fileVO.setNewsImportanceId((Long)newsDetails[9]);
			   	    fileVO.setImportance(newsDetails[10] != null ? newsDetails[10].toString() :"");
			   	    
			   	    List<Object[]> category = fileDAO.getCategoryDetailsOfAFile((Long)newsDetails[0]);
			   	    if(category != null && category.size() > 0)
			   	    {
			   	    	fileVO.setCategoryId((Long)category.get(0)[0]);
			   	    	fileVO.setCategoryType(category.get(0)[1].toString());
			   	    }
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
}
