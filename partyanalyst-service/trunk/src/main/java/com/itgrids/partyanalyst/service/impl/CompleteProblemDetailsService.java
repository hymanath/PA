package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.dao.IProblemProgressDAO;
import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ProblemStatusDataVO;
import com.itgrids.partyanalyst.model.ProblemProgress;
import com.itgrids.partyanalyst.model.UserProblem;
import com.itgrids.partyanalyst.service.ICompleteProblemDetailsService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CompleteProblemDetailsService implements ICompleteProblemDetailsService {
	
	private static final Logger LOG = Logger.getLogger(CompleteProblemDetailsService.class);
	private IUserProblemDAO userProblemDAO;
	private IProblemManagementService problemManagementService;
	private IProblemProgressDAO problemProgressDAO;
	private IFilePathsDAO filePathsDAO;
	
	public IUserProblemDAO getUserProblemDAO() {
		return userProblemDAO;
	}

	public void setUserProblemDAO(IUserProblemDAO userProblemDAO) {
		this.userProblemDAO = userProblemDAO;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public IProblemProgressDAO getProblemProgressDAO() {
		return problemProgressDAO;
	}

	public void setProblemProgressDAO(IProblemProgressDAO problemProgressDAO) {
		this.problemProgressDAO = problemProgressDAO;
	}
	
	public IFilePathsDAO getFilePathsDAO() {
		return filePathsDAO;
	}

	public void setFilePathsDAO(IFilePathsDAO filePathsDAO) {
		this.filePathsDAO = filePathsDAO;
	}

	
	public CompleteProblemDetailsVO getProblemCompleteDetails(final Long problemId,final Long userId,final String userStatus){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into getProblemCompleteDetails method ");
		}
		CompleteProblemDetailsVO completeProblemDetailsVO = new CompleteProblemDetailsVO();
		
		try{
			if(userStatus.equalsIgnoreCase(IConstants.NOT_LOGGED_IN)){
				getProblemDetailsForNotLoggedUser(problemId,completeProblemDetailsVO);		
			}else if(userStatus.equalsIgnoreCase(IConstants.FREE_USER)){
				getProblemDetailsForFreeLoggedUser(problemId,userId,completeProblemDetailsVO);			
			}else if(userStatus.equalsIgnoreCase(IConstants.PARTY_ANALYST_USER)){
				getProblemDetailsForCustomerLoggedUser(problemId,userId,completeProblemDetailsVO,"customer");	
			}else if(userStatus.equalsIgnoreCase(IConstants.BOTH)){
				getProblemDetailsForCustomerLoggedUser(problemId,userId,completeProblemDetailsVO,"both");	
			}
			if(checkIsPublicProblem(problemId)){
				completeProblemDetailsVO.setIsPublic("true");
			}
			if(completeProblemDetailsVO.getPostedUserId() != null && userId != null && completeProblemDetailsVO.getPostedUserId().longValue() == userId.longValue())
				completeProblemDetailsVO.setIsConnectPeopleReq("false");
		    if(LOG.isDebugEnabled()){
			 LOG.debug("getProblemCompleteDetails method executed successfully");
		    }
		}catch(Exception e){
			LOG.error("Exception rised in getProblemCompleteDetails method ",e);
		}
		return completeProblemDetailsVO;
	}
	
	private void getProblemDetailsForNotLoggedUser(Long problemId,CompleteProblemDetailsVO completeProblemDetailsVO ){
	  if(LOG.isDebugEnabled()){
		LOG.debug("Enter into getProblemDetailsForNotLoggedUser method ");
	  }
	  try{
		completeProblemDetailsVO.setUserStatus("notlogged");
		if(checkIsPublicProblem(problemId)){
			getProblemAndItsOwnerDetails(problemId,completeProblemDetailsVO);
			completeProblemDetailsVO.setModifyAccess(IConstants.FALSE);
		}else{
			completeProblemDetailsVO.setNoAccess(IConstants.TRUE);
		}
	  }catch(Exception e){
		 LOG.error("Exception rised in getProblemDetailsForNotLoggedUser method ",e);
	  }
	}

	private void getProblemDetailsForFreeLoggedUser(Long problemId,Long userId,CompleteProblemDetailsVO completeProblemDetailsVO ){
	  if(LOG.isDebugEnabled()){
		LOG.debug("Enter into getProblemDetailsForFreeLoggedUser method ");
	  }
	  try{
		completeProblemDetailsVO.setUserStatus("freeuser");
		if(checkIsPublicProblem(problemId)){
			getProblemAndItsOwnerDetails(problemId,completeProblemDetailsVO);
			if(checkIsProblemOwner(problemId,userId)){
			  completeProblemDetailsVO.setModifyAccess(IConstants.TRUE);
			}else{
			  completeProblemDetailsVO.setModifyAccess(IConstants.FALSE);
			}
		}else{
			completeProblemDetailsVO.setNoAccess(IConstants.TRUE);
		}
	   }catch(Exception e){
		 LOG.error("Exception rised in getProblemDetailsForFreeLoggedUser method ",e);
	   }
	}
	
	private void getProblemDetailsForCustomerLoggedUser(Long problemId,Long userId,CompleteProblemDetailsVO completeProblemDetailsVO,String userType){
	  if(LOG.isDebugEnabled()){
		LOG.debug("Enter into getProblemDetailsForCustomerLoggedUser method ");
	  }
	  try{	
		completeProblemDetailsVO.setUserStatus(userType);
		final boolean status = checkIsTakenUpProblem(problemId,userId);
		if(checkIsProblemOwner(problemId,userId) || status){
			if(status){
				completeProblemDetailsVO.setIsTaken(IConstants.TRUE);
				if(!checkIsTakenUpProblemIsInPublicVisiblty(problemId)){
					completeProblemDetailsVO.setChangedToPrivate(IConstants.TRUE);
					return;
				}
			}
			completeProblemDetailsVO.setModifyAccess(IConstants.TRUE);
			getProblemAndItsOwnerDetailsForCustmor(problemId,userId,completeProblemDetailsVO);			
		}else if(checkIsPublicProblem(problemId)){
			completeProblemDetailsVO.setModifyAccess(IConstants.FALSE);
			getProblemAndItsOwnerDetails(problemId,completeProblemDetailsVO);			
		}else{
			completeProblemDetailsVO.setNoAccess(IConstants.TRUE);
		}
	  }catch(Exception e){
		LOG.error("Exception rised in getProblemDetailsForCustomerLoggedUser method ",e);
	 }
	}

	private boolean checkIsPublicProblem(Long problemId){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into checkIsPublicProblem method ");
		}
		final List<Long> count = userProblemDAO.checkIsPublicProblem(problemId);
		
		if(!count.isEmpty() && count.get(0).longValue() >0l){
		    return true;
		}		
		return false;
	}
	
	private boolean checkIsProblemOwner(Long problemId,Long userId){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into checkIsProblemOwner method ");
		}
		final List<String> isOwnerList = userProblemDAO.checkIsProblemOwner(problemId,userId);
		
		if(!isOwnerList.isEmpty() && isOwnerList.get(0).equalsIgnoreCase(IConstants.TRUE)){
		  return true;
		}
		return false;
	}
	
	private boolean checkIsTakenUpProblem(Long problemId,Long userId){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into checkIsTakenUpProblem method ");
		}
		List<Long> takenUpCount = userProblemDAO.checkIsTakenUpProblem(problemId,userId);
		if(!takenUpCount.isEmpty() && takenUpCount.get(0).longValue() >0l)
		   return true;
		return false;
	}
	
	private boolean checkIsTakenUpProblemIsInPublicVisiblty(Long problemId){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into checkIsTakenUpProblem method ");
		}
		List<Long> takenUpPublicCount = userProblemDAO.checkIsTakenUpProblemIsInPublicVisiblty(problemId);
		if(!takenUpPublicCount.isEmpty() && takenUpPublicCount.get(0).longValue() >0l)
		   return true;
		return false;
	}
	
	private void getProblemAndItsOwnerDetails(Long problemId,CompleteProblemDetailsVO completeProblemDetailsVO){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into getProblemAndItsOwnerDetails method ");
		}
	  try{
		final List<UserProblem> userProblemList = userProblemDAO.getProblemAndOwnerDetails(problemId);
		if(!userProblemList.isEmpty()){
			final UserProblem userProblem = userProblemList.get(0);
			completeProblemDetailsVO.setProblemTitle(userProblem.getProblem().getTitle());
			completeProblemDetailsVO.setProblemDesc(userProblem.getProblem().getDescription());
			completeProblemDetailsVO.setProblemCompleteLoc(problemManagementService.getProblemLocationString(userProblem.getProblem().getProblemCompleteLocation()));
			completeProblemDetailsVO.setFirstName(userProblem.getUser().getFirstName());
			completeProblemDetailsVO.setLastName(userProblem.getUser().getLastName());
			final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
			completeProblemDetailsVO.setExistingFrom(dateFormat.format(userProblem.getProblem().getExistingFrom()));
			completeProblemDetailsVO.setIdentifiedOn(dateFormat.format(userProblem.getProblem().getIdentifiedOn()));
			completeProblemDetailsVO.setStatus(userProblem.getProblem().getProblemStatus().getStatus());
			completeProblemDetailsVO.setProblemRecentActivity(getProblemProgressDetails(problemId,IConstants.PUBLIC));
			completeProblemDetailsVO.setProblemFiles(getProblemRelatedFiles(problemId,null,IConstants.PUBLIC));
			completeProblemDetailsVO.setPostedUserId(userProblem.getUser().getUserId());
		}
		if(LOG.isDebugEnabled()){
			LOG.debug(" getProblemAndItsOwnerDetails method executed successfully");
		}
	   }catch(Exception e){
		  LOG.error("Exception rised in getProblemAndItsOwnerDetails method ",e);
	   }
	}
	
	private void getProblemAndItsOwnerDetailsForCustmor(Long problemId,Long userId,CompleteProblemDetailsVO completeProblemDetailsVO){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into getProblemAndItsOwnerDetailsForCustmor method ");
		}
	  try{
		final List<UserProblem> userProblemList = userProblemDAO.getProblemAndOwnerDetails(problemId);
		if(!userProblemList.isEmpty()){
			final UserProblem userProblem = userProblemList.get(0);
			completeProblemDetailsVO.setProblemTitle(userProblem.getProblem().getTitle());
			completeProblemDetailsVO.setProblemDesc(userProblem.getProblem().getDescription());
			completeProblemDetailsVO.setProblemCompleteLoc(problemManagementService.getProblemLocationString(userProblem.getProblem().getProblemCompleteLocation()));
			completeProblemDetailsVO.setFirstName(userProblem.getUser().getFirstName());
			completeProblemDetailsVO.setLastName(userProblem.getUser().getLastName());
			final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
			completeProblemDetailsVO.setExistingFrom(dateFormat.format(userProblem.getProblem().getExistingFrom()));
			completeProblemDetailsVO.setIdentifiedOn(dateFormat.format(userProblem.getProblem().getIdentifiedOn()));
			completeProblemDetailsVO.setStatus(userProblem.getProblem().getProblemStatus().getStatus());
			completeProblemDetailsVO.setProblemRecentActivity(getProblemProgressDetails(problemId,null));
			completeProblemDetailsVO.setProblemFiles(getProblemRelatedFiles(problemId,null,null));
			completeProblemDetailsVO.setProblemStatus(problemManagementService.getProblemRecentDetailsByProblemId(problemId,userId));
			completeProblemDetailsVO.setPostedUserId(userProblem.getUser().getUserId());
		}
		if(LOG.isDebugEnabled()){
			LOG.debug(" getProblemAndItsOwnerDetailsForCustmor method executed successfully");
		}
	  }catch(Exception e){
		  LOG.error("Exception rised in getProblemAndItsOwnerDetailsForCustmor method ",e);
	  }
	}
	
	
	private List<ProblemStatusDataVO> getProblemProgressDetails(Long problemId,String visibility){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into getProblemProgressDetails method ");
		}
		List<ProblemStatusDataVO> problemRecentActivityList = new ArrayList<ProblemStatusDataVO>();
		ProblemStatusDataVO problemStatusDataVO = null;
		List<ProblemProgress> problemProgressList = problemProgressDAO.getAllProblemProgressDetails(problemId,visibility);
		for(ProblemProgress problemProgress :problemProgressList){
			try{
				problemStatusDataVO = problemManagementService.getProblemActivityDetailsSetToVO(problemProgress);
				problemRecentActivityList.add(problemStatusDataVO);
			}catch(Exception e){
				LOG.error("Exception rised in getProblemProgressDetails method while getting problem activities ",e);
			}
		}
		if(LOG.isDebugEnabled()){
			LOG.debug(" getProblemProgressDetails method executed successfully");
		}
		return problemRecentActivityList;
	}
	
	private List<FileVO> getProblemRelatedFiles(Long problemId,Long userId,String visibility){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into getProblemRelatedFiles method ");
		}
		List<Object[]> problemFilesList = null;
		try{
		   problemFilesList = filePathsDAO.getProblemFiles(problemId, userId, visibility);	   
		}catch(Exception e){
			LOG.error("Exception rised in getProblemRelatedFiles method  ",e);
		}
		return populateDataToVO(problemFilesList);
	}
	
	private List<FileVO> populateDataToVO(List<Object[]> problemFilesList){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into populateDataToVO method ");
		}
		List<FileVO> returnVal = new ArrayList<FileVO>();
		FileVO fileVO = null;
		for(Object[] problemFile : problemFilesList){
		 try{
			fileVO = new FileVO();
			fileVO.setFile(problemFile[2].toString());
			fileVO.setTitle(problemFile[0].toString());
			fileVO.setDescription(problemFile[1].toString());
			fileVO.setPathOfFile(IConstants.UPLOADED_FILES + "/Problem_Files/"+ problemFile[2]);
			fileVO.setCandidateId((Long)problemFile[3]);
			fileVO.setName(problemFile[4]!= null?problemFile[4].toString():"");
			fileVO.setNames(problemFile[5]!= null?problemFile[5].toString():"");
			returnVal.add(fileVO);
		  }catch(Exception e){
			  LOG.error("Exception rised in populateDataToVO method while iterating and getting problem related files data ",e);
		  }
		}
		
		return returnVal;
	}
}
