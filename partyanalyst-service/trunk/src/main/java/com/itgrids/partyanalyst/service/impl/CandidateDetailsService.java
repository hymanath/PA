/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.util.StringUtils;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidateProfileDescriptionDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserGallaryDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ContentType;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Gallary;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.RegionScopes;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.UserGallary;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CandidateDetailsService implements ICandidateDetailsService {

	private ICandidateResultDAO candidateResultDAO;
	private ICandidateDAO candidateDAO;
	private IGallaryDAO gallaryDAO;
	private IFileGallaryDAO fileGallaryDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IContentTypeDAO contentTypeDAO;
	private IUserGallaryDAO userGallaryDAO;
	private IRegistrationDAO registrationDAO;
	private IFileDAO fileDAO;
	
	private IRegionScopesDAO regionScopesDAO;
	private ICountryDAO countryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO; 
	private ITehsilDAO tehsilDAO;  
	private IHamletDAO hamletDAO;  
	private ILocalElectionBodyDAO localElectionBodyDAO;  
	private IBoothDAO boothDAO; 
	private ICandidateProfileDescriptionDAO candidateProfileDescriptionDAO;
		
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
	
	public IFileDAO getFileDAO() {
		return fileDAO;
	}

	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
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
		   	  	fileVO.setPath(IConstants.UPLOADED_FILES+"/"+newsDetails[0].toString());
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
				candidateDetails.setConstituencyName(constituency.getName());
				candidateDetails.setConstituencyId(constituency.getConstituencyId());
				candidateDetails.setElectionId(election.getElectionId());
				candidateDetails.setElectionType(election.getElectionScope().getElectionType().getElectionType());
				candidateDetails.setElectionYear(election.getElectionYear());
				candidateDetails.setPartyName(party.getLongName());
				candidateDetails.setShortName(party.getShortName());
				candidateDetails.setPartyFlag(party.getPartyFlag());
				candidateDetails.setRank(result.getRank());
				candidateDetails.setEducation(candidate.getEducation());
				String votes = result.getVotesEarned().toString();
				String votesEarn[] = StringUtils.split(votes, ".");
				candidateDetails.setVotesEarned(votesEarn[0]);
				candidateDetails.setVotesPercentage(result.getVotesPercengate());
				if(constituency.getDistrict() != null)
					districtName = constituency.getDistrict().getDistrictName();
				candidateDetails.setDistrictName(districtName);
				candidateDetails.setStateName(constituency.getState().getStateName());
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
	
	public List<FileVO> getCandidatesPhotoGallaryDetail(Long candidateId,String type){
		 List<FileVO> retValue = new ArrayList<FileVO>();
	 try{
		List<Object[]> results = gallaryDAO.getCandidateGallaryDetail(candidateId,type, "false");
		
		for(Object[] gallary: results){
			FileVO fileVO = new FileVO();
		    List<Object[]> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    for(Object[] startingRecord: record){
		    	fileVO.setFileId((Long)startingRecord[0]);
		    	fileVO.setName(startingRecord[1] != null ? startingRecord[1].toString() :"");		    			    	
		    	fileVO.setPath(IConstants.UPLOADED_FILES+"/"+startingRecord[1].toString());
		    	
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
		   	  	fileVO.setPath(IConstants.UPLOADED_FILES+"/"+imageDetails[1].toString());
		   	    fileVO.setFileTitle1(imageDetails[3] != null ? imageDetails[3].toString() :"");
		   	    fileVO.setFileDescription1(imageDetails[4] != null ? imageDetails[4].toString() :"");
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
			 List<Object[]> results = fileGallaryDAO.getFirstFourNewsToDisplay(candidateId);
			 for(Object[] newsDetails: results){
				    FileVO fileVO = new FileVO();
				    fileVO.setFileId((Long)newsDetails[0]);
			    	fileVO.setName(newsDetails[1] != null ? newsDetails[1].toString() :"");		    			    	
			   	  	fileVO.setPath(IConstants.UPLOADED_FILES+"/"+newsDetails[1].toString());
			   	    fileVO.setFileTitle1(newsDetails[3] != null ? newsDetails[3].toString() :"");
			   	    String desc=""; 
			   	    if(newsDetails[4] != null && newsDetails[4].toString().length()>=55)
			   	    {
			   	    desc = newsDetails[4].toString().substring(0, 50);
			   	    desc = desc+"...";
			   	    }
			   	    else
			   	    {
			   	     desc = newsDetails[4].toString();
			   	     desc = desc+"...";
			   	    }
			   	    fileVO.setFileDescription1(desc);
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
	public List<FileVO> getNewsToDisplay(Long candidateId){
		 List<FileVO> retValue = new ArrayList<FileVO>();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 try{
			 List<Object[]> results = fileGallaryDAO.getFirstFourNewsToDisplay(candidateId);
			 for(Object[] newsDetails: results){
				    FileVO fileVO = new FileVO();
				    fileVO.setFileId((Long)newsDetails[0]);
			    	fileVO.setName(newsDetails[1] != null ? newsDetails[1].toString() :"");		    			    	
			   	  	fileVO.setPath(IConstants.UPLOADED_FILES+"/"+newsDetails[1].toString());
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
			   	  	fileVO.setPath(IConstants.UPLOADED_FILES+"/"+newsDetails[1].toString());
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
	
	public ResultStatus createNewGallary(GallaryVO gallaryVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			Gallary gallary = new Gallary();
			UserGallary userGallary = null;
			gallary.setName(gallaryVO.getGallaryName());
			gallary.setDescription(gallaryVO.getDescription());
			gallary.setCandidate(candidateDAO.get(gallaryVO.getCandidateId()));
			gallary.setContentType((ContentType)contentTypeDAO.getContentTypeByType(gallaryVO.getContentType()));
			gallary.setCreatedDate(dateUtilService.getCurrentDateAndTime());
			gallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
			gallary.setIsPrivate(gallaryVO.getVisibility());
			gallary.setIsDelete(IConstants.FALSE);
			
			gallary = gallaryDAO.save(gallary);
			
			userGallary = new UserGallary();
			userGallary.setGallary(gallary);
			userGallary.setRegistration(registrationDAO.get(gallaryVO.getUserId()));
			userGallaryDAO.save(userGallary);
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
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
		 return null;
	 }


	}
	
}
