package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.dao.IProblemCommentsDAO;
import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.dao.IProblemFilesDAO;
import com.itgrids.partyanalyst.dao.IProblemProgressDAO;
import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ProblemStatusDataVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemProgress;
import com.itgrids.partyanalyst.model.UserProblem;
import com.itgrids.partyanalyst.service.ICompleteProblemDetailsService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CompleteProblemDetailsService implements ICompleteProblemDetailsService {
	
	private static final Logger LOG = Logger.getLogger(CompleteProblemDetailsService.class);
	private IUserProblemDAO userProblemDAO;
	private IProblemManagementService problemManagementService;
	private IProblemProgressDAO problemProgressDAO;
	private IFilePathsDAO filePathsDAO;
	private IProblemCommentsDAO problemCommentsDAO;
	private IProblemDAO problemDAO;
	private IProblemManagementReportService problemManagementReportService;
	private IFileDAO fileDAO;
	private IProblemFilesDAO problemFilesDAO;
	
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
	
	public IProblemCommentsDAO getProblemCommentsDAO() {
		return problemCommentsDAO;
	}

	public void setProblemCommentsDAO(IProblemCommentsDAO problemCommentsDAO) {
		this.problemCommentsDAO = problemCommentsDAO;
	}

	public IProblemDAO getProblemDAO() {
		return problemDAO;
	}

	public void setProblemDAO(IProblemDAO problemDAO) {
		this.problemDAO = problemDAO;
	}

	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}

	public IFileDAO getFileDAO() {
		return fileDAO;
	}

	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	public IProblemFilesDAO getProblemFilesDAO() {
		return problemFilesDAO;
	}

	public void setProblemFilesDAO(IProblemFilesDAO problemFilesDAO) {
		this.problemFilesDAO = problemFilesDAO;
	}

	public CompleteProblemDetailsVO getProblemCompleteDetails(final Long problemId,final Long userId,final String userStatus,String queryReq){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into getProblemCompleteDetails method ");
		}
		CompleteProblemDetailsVO completeProblemDetailsVO = new CompleteProblemDetailsVO();
		completeProblemDetailsVO.setProblemId(problemId);
		try{
			if(checkIsProblemNotExist(problemId)){
				completeProblemDetailsVO.setIsProblemDel("true");
				return completeProblemDetailsVO;
			}
			if(userStatus.equalsIgnoreCase(IConstants.NOT_LOGGED_IN)){
				getProblemDetailsForNotLoggedUser(problemId,completeProblemDetailsVO,queryReq);		
			}else if(userStatus.equalsIgnoreCase(IConstants.FREE_USER)){
				getProblemDetailsForFreeLoggedUser(problemId,userId,completeProblemDetailsVO,queryReq);			
			}else if(userStatus.equalsIgnoreCase(IConstants.PARTY_ANALYST_USER)){
				getProblemDetailsForCustomerLoggedUser(problemId,userId,completeProblemDetailsVO,"customer",queryReq);	
			}else if(userStatus.equalsIgnoreCase(IConstants.BOTH)){
				getProblemDetailsForCustomerLoggedUser(problemId,userId,completeProblemDetailsVO,"both",queryReq);	
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
	
	private void getProblemDetailsForNotLoggedUser(Long problemId,CompleteProblemDetailsVO completeProblemDetailsVO,String queryReq ){
	  if(LOG.isDebugEnabled()){
		LOG.debug("Enter into getProblemDetailsForNotLoggedUser method ");
	  }
	  try{
		completeProblemDetailsVO.setUserStatus("notlogged");
		if(checkIsPublicProblem(problemId)){
			getProblemAndItsOwnerDetails(problemId,completeProblemDetailsVO,queryReq,null);
			completeProblemDetailsVO.setModifyAccess(IConstants.FALSE);
			completeProblemDetailsVO.setRelatedProblems(getRelatedProblemsByProblemId(problemId,null,"notlogged"));
		}else{
			completeProblemDetailsVO.setNoAccess(IConstants.TRUE);
		}
	  }catch(Exception e){
		 LOG.error("Exception rised in getProblemDetailsForNotLoggedUser method ",e);
	  }
	}

	private void getProblemDetailsForFreeLoggedUser(Long problemId,Long userId,CompleteProblemDetailsVO completeProblemDetailsVO,String queryReq){
	  if(LOG.isDebugEnabled()){
		LOG.debug("Enter into getProblemDetailsForFreeLoggedUser method ");
	  }
	  try{
		completeProblemDetailsVO.setUserStatus("freeuser");
		if(checkIsPublicProblem(problemId)){
			getProblemAndItsOwnerDetails(problemId,completeProblemDetailsVO,queryReq,userId);
			completeProblemDetailsVO.setRelatedProblems(getRelatedProblemsByProblemId(problemId,userId,"freeuser"));
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
	
	private void getProblemDetailsForCustomerLoggedUser(Long problemId,Long userId,CompleteProblemDetailsVO completeProblemDetailsVO,String userType,String queryReq){
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
			getProblemAndItsOwnerDetailsForCustmor(problemId,userId,completeProblemDetailsVO,queryReq);			
		}else if(checkIsPublicProblem(problemId)){
			completeProblemDetailsVO.setModifyAccess(IConstants.FALSE);
			getProblemAndItsOwnerDetails(problemId,completeProblemDetailsVO,queryReq,userId);			
		}else{
			completeProblemDetailsVO.setNoAccess(IConstants.TRUE);
		}
		completeProblemDetailsVO.setRelatedProblems(getRelatedProblemsByProblemId(problemId,userId,userType));
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
	
	private boolean checkIsProblemNotExist(Long problemId){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into checkIsProblemExist method ");
		}
		List<Long> problemNotExistCount = problemDAO.isProblemDeleted(problemId);
		if(!problemNotExistCount.isEmpty() && problemNotExistCount.get(0).longValue() >0l)
		   return false;
		return true;
	}
	private void getProblemAndItsOwnerDetails(Long problemId,CompleteProblemDetailsVO completeProblemDetailsVO,String queryReq,Long userId){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into getProblemAndItsOwnerDetails method ");
		}
	  try{
		final List<UserProblem> userProblemList = userProblemDAO.getProblemAndOwnerDetails(problemId);
		if(!userProblemList.isEmpty()){
			final UserProblem userProblem = userProblemList.get(0);
			if(queryReq == null){
			  completeProblemDetailsVO.setRating(problemManagementService.getAverageRatingOfAProblem(problemId).getAvgRating());
			  completeProblemDetailsVO.setProblemTitle(userProblem.getProblem().getTitle());
			  completeProblemDetailsVO.setProblemDesc(userProblem.getProblem().getDescription());
			  completeProblemDetailsVO.setProblemCompleteLoc(problemManagementService.getProblemLocationString(userProblem.getProblem().getProblemCompleteLocation()));
			  completeProblemDetailsVO.setFirstName(userProblem.getUser().getFirstName());
			  completeProblemDetailsVO.setLastName(userProblem.getUser().getLastName());
			  final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
			  completeProblemDetailsVO.setExistingFrom(dateFormat.format(userProblem.getProblem().getExistingFrom()));
			  completeProblemDetailsVO.setIdentifiedOn(dateFormat.format(userProblem.getProblem().getIdentifiedOn()));
			  completeProblemDetailsVO.setPostedUserId(userProblem.getUser().getUserId());
			  completeProblemDetailsVO.setProfileImg(userProblem.getUser().getProfileImg());
			  if(userProblem.getProblem().getReferenceNo() != null)
			  completeProblemDetailsVO.setReferenceNo(userProblem.getProblem().getReferenceNo().toString());
			  if(userId != null){
			   completeProblemDetailsVO.setIsAlreadyRated(problemManagementService.getIsUserRatedAProblem(userId,problemId));
			   Integer rating = problemManagementService.getUserRatingOfAProblem(problemId,userId);
			    completeProblemDetailsVO.setRatingByyou(rating!= null?rating.toString():null);
			   }
			  }
			if(queryReq == null || queryReq.equalsIgnoreCase("getstatustypedetails"))
			completeProblemDetailsVO.setStatus(userProblem.getProblem().getProblemStatus().getStatus());
			completeProblemDetailsVO.setProblemRecentActivity(getProblemProgressDetails(problemId,IConstants.PUBLIC));
			if(queryReq == null || queryReq.equalsIgnoreCase("getphotodetails"))
			completeProblemDetailsVO.setProblemFiles(getProblemRelatedFiles(problemId,null,IConstants.PUBLIC,userId));
			
		}
		if(LOG.isDebugEnabled()){
			LOG.debug(" getProblemAndItsOwnerDetails method executed successfully");
		}
	   }catch(Exception e){
		  LOG.error("Exception rised in getProblemAndItsOwnerDetails method ",e);
	   }
	}
	
	private void getProblemAndItsOwnerDetailsForCustmor(Long problemId,Long userId,CompleteProblemDetailsVO completeProblemDetailsVO,String queryReq){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into getProblemAndItsOwnerDetailsForCustmor method ");
		}
	  try{
		final List<UserProblem> userProblemList = userProblemDAO.getProblemAndOwnerDetails(problemId);
		if(!userProblemList.isEmpty()){
			final UserProblem userProblem = userProblemList.get(0);
			if(queryReq == null){
			  completeProblemDetailsVO.setRating(problemManagementService.getAverageRatingOfAProblem(problemId).getAvgRating());
			  completeProblemDetailsVO.setProblemTitle(userProblem.getProblem().getTitle());
			  completeProblemDetailsVO.setProblemDesc(userProblem.getProblem().getDescription());
			  completeProblemDetailsVO.setProblemCompleteLoc(problemManagementService.getProblemLocationString(userProblem.getProblem().getProblemCompleteLocation()));
			  completeProblemDetailsVO.setFirstName(userProblem.getUser().getFirstName());
			  completeProblemDetailsVO.setLastName(userProblem.getUser().getLastName());
			  final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
			  completeProblemDetailsVO.setExistingFrom(dateFormat.format(userProblem.getProblem().getExistingFrom()));
			  completeProblemDetailsVO.setIdentifiedOn(dateFormat.format(userProblem.getProblem().getIdentifiedOn()));
			  completeProblemDetailsVO.setPostedUserId(userProblem.getUser().getUserId());
			  completeProblemDetailsVO.setProfileImg(userProblem.getUser().getProfileImg());
			  completeProblemDetailsVO.setIsOwner(userProblem.getIsOwner());
			  if(userProblem.getProblem().getReferenceNo() != null)
				  completeProblemDetailsVO.setReferenceNo(userProblem.getProblem().getReferenceNo());
			  if(userId != null){
				   completeProblemDetailsVO.setIsAlreadyRated(problemManagementService.getIsUserRatedAProblem(userId,problemId));
				   Integer rating = problemManagementService.getUserRatingOfAProblem(problemId,userId);
				    completeProblemDetailsVO.setRatingByyou(rating!= null?rating.toString():null);
				 }
			}
			if(queryReq == null || queryReq.equalsIgnoreCase("getstatustypedetails"))
			 completeProblemDetailsVO.setStatus(userProblem.getProblem().getProblemStatus().getStatus());
			completeProblemDetailsVO.setProblemRecentActivity(getProblemProgressDetails(problemId,null));
			if(queryReq == null || queryReq.equalsIgnoreCase("getphotodetails"))
			 completeProblemDetailsVO.setProblemFiles(getProblemRelatedFiles(problemId,null,null,userId));
			if(queryReq == null || queryReq.equalsIgnoreCase("getotheractvdetails"))
			completeProblemDetailsVO.setProblemStatus(problemManagementService.getProblemRecentDetailsByProblemId(problemId,userId));
			
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
	
	private List<FileVO> getProblemRelatedFiles(Long problemId,Long userId,String visibility,Long loggedUserId){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into getProblemRelatedFiles method ");
		}
		List<Object[]> problemFilesList = null;
		try{
		   problemFilesList = filePathsDAO.getProblemFiles(problemId, userId, visibility);	   
		}catch(Exception e){
			LOG.error("Exception rised in getProblemRelatedFiles method  ",e);
		}
		return populateDataToVO(problemFilesList,loggedUserId);
	}
	
	private List<FileVO> populateDataToVO(List<Object[]> problemFilesList,Long userId){
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
			if(userId != null && userId.equals(problemFile[3]))
				fileVO.setUpdatable(true);
			fileVO.setProblemFileId((Long)problemFile[6]);
			returnVal.add(fileVO);
		  }catch(Exception e){
			  LOG.error("Exception rised in populateDataToVO method while iterating and getting problem related files data ",e);
		  }
		}
		
		return returnVal;
	}
	
	public List<UserCommentsInfoVO> getPostedComments(final Long problemId,final Long userId){
		if(LOG.isDebugEnabled()){
			LOG.debug("Enter into getPostedComments method ");
		}
		List<UserCommentsInfoVO> userCommentsInfoVOList = new ArrayList<UserCommentsInfoVO>();
		try{
	      List<Long> userIdsList = userProblemDAO.getUserIds(problemId);
		  List<Object[]> commentDetails = problemCommentsDAO.getAllProblemComments(problemId,userId,userIdsList);
		  popolateDataToVo(commentDetails,userCommentsInfoVOList);
		}catch(Exception e){
			LOG.error("Exception rised in getPostedComments method ",e);
		}
		return userCommentsInfoVOList;
	}
	private void popolateDataToVo(List<Object[]> commentDetails,List<UserCommentsInfoVO> userCommentsInfoVOList){
		UserCommentsInfoVO userCommentsInfoVO = null;
		final SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy HH:MM");
		for(Object[] comment:commentDetails){
		 try{
			userCommentsInfoVO = new UserCommentsInfoVO();
			userCommentsInfoVO.setComment(comment[0]!= null?comment[0].toString():"");
			userCommentsInfoVO.setFirstName(comment[1]!= null?comment[1].toString():"");
			userCommentsInfoVO.setLastName(comment[2]!= null?comment[2].toString():"");
			userCommentsInfoVO.setUserId((Long)comment[3]);
			userCommentsInfoVO.setDate(comment[4]!= null?dateFormat.format((Date)comment[4]):"");
			userCommentsInfoVO.setProfileImg(comment[5]!= null?comment[5].toString():null);
			userCommentsInfoVO.setCommentId(comment[6] !=null ?(Long)comment[6]:null);
			
			userCommentsInfoVOList.add(userCommentsInfoVO);
		 }catch(Exception e){
			 LOG.error("Exception rised in popolateDataToVo method while iterating and getting problem related files data ",e);
		 }
		}
	}
	
	/**
	 * This method is to get related problems by ProblemId		
	 * @param problemId
	 */
			
	public List<CompleteProblemDetailsVO> getRelatedProblemsByProblemId(Long problemId,Long userId,String userType){
		if(LOG.isDebugEnabled())
			   LOG.debug("Entered into the getRelatedProblemsByProblemId service method");
		List<CompleteProblemDetailsVO> completeProblemDetailsVOList = new ArrayList<CompleteProblemDetailsVO>();
		Map<Long,Problem> problemMap = new HashMap<Long,Problem>();
		try{
			Problem problem = problemDAO.get(problemId);			
			Map<Long,Problem> problemsMap = getAllRelatedProblems(problem ,problemMap,userId);
			convertMapToCompleteProblemDetailsVOList(problemsMap,completeProblemDetailsVOList,userType,userId);
		}catch(Exception e){
			LOG.error("Exception rised in getRelatedProblemsByProblemId method",e);
		}
		
		return completeProblemDetailsVOList;	
		
	}
	
	public Map<Long,Problem> getAllRelatedProblems(Problem problem ,Map<Long,Problem> problemMap,Long userId){
		if(LOG.isDebugEnabled())
			   LOG.debug("Entered into the getAllRelatedProblems service method");	
		try{
			if(problem.getProblemType() != null && problem.getProblemType().getProblemType() != null){
				List<Problem> problemsList = getRelatedProblemsByProblemType(problem.getProblemId(),problem.getProblemType().getProblemType());
				populateDataToMap(problemsList,problemMap);
				if(problemMap.size() >= IConstants.MAX_PROBLES)
					return problemMap;
			}
			if(problem.getProblemCompleteLocation() != null){
				if(problem.getProblemCompleteLocation().getConstituency() != null){
					Set<Long> keys = problemMap.keySet();
					List<Long> problemIds = getProblemIdsList(keys);
					problemIds.add(problem.getProblemId());
					List<Problem> problemsList = getRelatedProblemsByConstituencyType(problemIds,problem.getProblemCompleteLocation().getConstituency().getConstituencyId(),userId);
					populateDataToMap(problemsList,problemMap);
					if(problemMap.size() >= IConstants.MAX_PROBLES)
						return problemMap;
				}
				if(problem.getProblemCompleteLocation().getDistrict() != null){
					Set<Long> keys = problemMap.keySet();
					List<Long> problemIds = getProblemIdsList(keys);
					problemIds.add(problem.getProblemId());
					List<Problem> problemsList = getRelatedProblemsByDistrictType(problemIds,problem.getProblemCompleteLocation().getDistrict().getDistrictId(),userId);
					populateDataToMap(problemsList,problemMap);
					if(problemMap.size() >= IConstants.MAX_PROBLES)
						return problemMap;
				}
			 }
			Set<Long> keys = problemMap.keySet();
			List<Long> problemIds = getProblemIdsList(keys);
			problemIds.add(problem.getProblemId());
			List<Problem> problemsList = getRelatedProblemsByUser(problemIds,userId);
			populateDataToMap(problemsList,problemMap);
			if(userId != null){
				keys = problemMap.keySet();
				problemIds = getProblemIdsList(keys);
				if(!(problemMap.size() >= IConstants.MAX_PROBLES)){
					problemsList = getRelatedProblemsByUser(problemIds,null);
					populateDataToMap(problemsList,problemMap);
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception rised in getAllRelatedProblems method",e);
		}
		return problemMap;
	}
    
	public List<Problem> getRelatedProblemsByProblemType(Long problemId,String problemTyp){
		if(LOG.isDebugEnabled())
			   LOG.debug("Entered into the getRelatedProblemsByProblemType service method");	
		try{
		   return userProblemDAO.getUserProblemsByProblemTyp(problemId ,problemTyp);
		}catch(Exception e){
			LOG.error("Exception rised in getRelatedProblemsByProblemType method",e);
			return new ArrayList<Problem>();
		}
	}
	public List<Problem> getRelatedProblemsByConstituencyType(List<Long> problemIds,Long cnstncyId,Long userId){
		if(LOG.isDebugEnabled())
			   LOG.debug("Entered into the getRelatedProblemsByConstituencyType service method");	
		try{
		  return userProblemDAO.geUserProblemsByConstituency(problemIds,cnstncyId,userId);
		}catch(Exception e){
			LOG.error("Exception rised in getRelatedProblemsByConstituencyType method",e);
			return new ArrayList<Problem>();
		}
	}
	public List<Problem> getRelatedProblemsByDistrictType(List<Long> problemIds,Long districtId,Long userId){
		if(LOG.isDebugEnabled())
			   LOG.debug("Entered into the getRelatedProblemsByDistrictType service method");	
		try{
		  return userProblemDAO.getProblemsByDistrictId(problemIds,districtId,userId);
		}catch(Exception e){
			LOG.error("Exception rised in getRelatedProblemsByDistrictType method",e);
			return new ArrayList<Problem>();
		}
	}
	public List<Problem> getRelatedProblemsByUser(List<Long> problemIds,Long userId){
		if(LOG.isDebugEnabled())
			   LOG.debug("Entered into the getRelatedProblemsByUser service method");	
		try{
		  return userProblemDAO.getProblemsByUserId(problemIds,userId);
		}catch(Exception e){
			LOG.error("Exception rised in getRelatedProblemsByUser method",e);
			return new ArrayList<Problem>();
		}
	}
	
	private void populateDataToMap(List<Problem> problemsList,Map<Long,Problem> problemMap){
		 for(Problem problem: problemsList){
			 if(problemMap.size() >= IConstants.MAX_PROBLES)
				 return;
			 problemMap.put(problem.getProblemId(), problem);
		 }
	}
	private void convertMapToCompleteProblemDetailsVOList(Map<Long,Problem> problemsMap,List<CompleteProblemDetailsVO> completeProblemDetailsVOList,String userType,Long userId){
		
		CompleteProblemDetailsVO completeProblemDetailsVO = null;
	        for(Problem problem : problemsMap.values()){
	         try{
	        	completeProblemDetailsVO = new CompleteProblemDetailsVO();
	        	completeProblemDetailsVO.setProblemId(problem.getProblemId());
	        	if(!userType.equalsIgnoreCase("notlogged") && userId != null){
	        		if(checkIsTakenUpProblem(problem.getProblemId(),userId))
	        		    completeProblemDetailsVO.setIsTaken(IConstants.TRUE);
	        		if(checkIsProblemOwner(problem.getProblemId(),userId))
	        		    completeProblemDetailsVO.setIsOwner(IConstants.TRUE);
	        		
	        	}
	        	completeProblemDetailsVO.setRating(problemManagementService.getAverageRatingOfAProblem(problem.getProblemId()).getAvgRating());
	        	completeProblemDetailsVO.setProblemTitle(problem.getTitle());
	        	if(problem.getRegionScopes() != null && problem.getRegionScopes().getRegionScopesId() != null && problem.getImpactLevelValue() != null)
	        		completeProblemDetailsVO.setProblemCompleteLoc(problemManagementReportService.getProblemLocation(problem.getRegionScopes().getRegionScopesId(),problem.getImpactLevelValue()));
	        	completeProblemDetailsVOList.add(completeProblemDetailsVO);
	          }catch(Exception e){
	        	 LOG.error("Exception rised in convertMapToCompleteProblemDetailsVOList method",e);
	          }
	         }
	}
	public List<Long> getProblemIdsList(Set<Long> keys){
		List<Long> problemIds = new ArrayList<Long>();
		for(Long key : keys){
			problemIds.add(key);
		}
		return problemIds;
	}
	
	public FileVO getProbleFileDetailsByProblemFileId(Long problemFileId)
	{
		FileVO fileVO = null; 
		try{
			List<Object[]> list = problemFilesDAO.getProblemFileDetailsByProblemFileId(problemFileId);
			if(list != null && list.size() > 0)
			{
				fileVO = new FileVO();
				for(Object[] params : list)
				{
					fileVO.setFileId((Long)params[0]);
					fileVO.setFileTitle1(params[1] != null ? params[1].toString() : " ");
					fileVO.setFileDescription1(params[2]!=null ? params[2].toString() : " ");
					fileVO.setProblemFileId((Long)params[3]);
				}
			}
			return fileVO;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getProbleFileDetailsByProblemFileId() Method, Exception - "+e);
			return fileVO;
		}
	}
	
	public ResultStatus upDateProbleFileDetails(Long fileId, String fileTitle, String fileDescription)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			Integer result = fileDAO.updateProblemFileDetailsByFileId(fileId, fileTitle, fileDescription);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  return resultStatus;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in upDateProbleFileDetails() Method, Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
}
