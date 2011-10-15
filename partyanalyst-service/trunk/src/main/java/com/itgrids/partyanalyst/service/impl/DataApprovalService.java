package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.IApprovalDetailsDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IUserApprovalDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserProblemApprovalDAO;
import com.itgrids.partyanalyst.dto.ApprovalInfoVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.ApprovalDetails;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.model.UserApprovalDetails;
import com.itgrids.partyanalyst.model.UserProblemApproval;
import com.itgrids.partyanalyst.service.IDataApprovalService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;


public class DataApprovalService implements IDataApprovalService {

	private static final Logger log = Logger.getLogger("DataApprovalService.class");
	private TransactionTemplate transactionTemplate = null;
	private IProblemHistoryDAO problemHistoryDAO;
	private IAnanymousUserDAO ananymousUserDAO;
	private IUserProblemApprovalDAO userProblemApprovalDAO; 
	private IUserApprovalDetailsDAO userApprovalDetailsDAO; ; 
	private ApprovalInfoVO approvalInfoVO;
	private IProblemManagementService  problemManagementService;
	private IApprovalDetailsDAO approvalDetailsDAO;
	
	

	public IApprovalDetailsDAO getApprovalDetailsDAO() {
		return approvalDetailsDAO;
	}

	public void setApprovalDetailsDAO(IApprovalDetailsDAO approvalDetailsDAO) {
		this.approvalDetailsDAO = approvalDetailsDAO;
	}

	public IUserApprovalDetailsDAO getUserApprovalDetailsDAO() {
		return userApprovalDetailsDAO;
	}

	public void setUserApprovalDetailsDAO(
			IUserApprovalDetailsDAO userApprovalDetailsDAO) {
		this.userApprovalDetailsDAO = userApprovalDetailsDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
	}

	public void setProblemHistoryDAO(IProblemHistoryDAO problemHistoryDAO) {
		this.problemHistoryDAO = problemHistoryDAO;
	}

	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}
    
	public IUserProblemApprovalDAO getUserProblemApprovalDAO() {
		return userProblemApprovalDAO;
	}

	public void setUserProblemApprovalDAO(
			IUserProblemApprovalDAO userProblemApprovalDAO) {
		this.userProblemApprovalDAO = userProblemApprovalDAO;
	}	

	public ApprovalInfoVO getApprovalInfoVO() {
		return approvalInfoVO;
	}

	public void setApprovalInfoVO(ApprovalInfoVO approvalInfoVO) {
		this.approvalInfoVO = approvalInfoVO;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}
	public ResultStatus checkApprovalStatus(ApprovalInfoVO approvalInfoVO){
		if(log.isDebugEnabled()){
			log.debug("Entered Into saveProblemsApprovalData Method in DataApprovalService");
		}
		
		final ResultStatus rs = new ResultStatus();
		
				ApprovalDetails approvalDetails = new ApprovalDetails();
				UserApprovalDetails userApprovalDetails = new UserApprovalDetails();
				UserProblemApproval userProblemApproval = new UserProblemApproval(); 
				try{
					
					List userPrevPosts = userProblemApprovalDAO.findProblemApprovalsByUser(approvalInfoVO.getUserId(), approvalInfoVO.getProblemHistoryId());
					ProblemHistory problemHistory = problemHistoryDAO.get(approvalInfoVO.getProblemHistoryId());
					if(problemHistory.getProblemLocation().getProblemAndProblemSource().getExternalUser().getUserId().equals(approvalInfoVO.getUserId()) && !"FollowUp".equals(approvalInfoVO.getIsApproved()))
					{
						rs.setExceptionMsg("This problem was reported by you.You can not Accept/Reject problems reported by you!");
						rs.setResultState(1l);
						return rs;
					}
					else if(userPrevPosts != null && userPrevPosts.size() == 0 )
					{	
						rs.setExceptionMsg("You should have Accepted or Rejected a probelm to participate in the discussion!");
						rs.setResultState(0l);
						return rs;
					}
					
					rs.setResultState(1l);				
				}catch(Exception e){
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while saving problemApporval data in DataApprovalService", e);
					}
					rs.setExceptionEncountered(e);
					rs.setExceptionClass(e.getClass().toString());
					rs.setResultCode(ResultCodeMapper.FAILURE);
					rs.setExceptionMsg("An Error ocured.Please Try Again!");
					e.printStackTrace();
				}		
		
		return rs;
	}

	/**
	 * This method is used to save the acceptance/rejections of a problem by a user.
	 */
	public ResultStatus saveProblemsApprovalData(ApprovalInfoVO approvalInfoVOToSave) {
		if(log.isDebugEnabled()){
			log.debug("Entered Into saveProblemsApprovalData Method in DataApprovalService");
		}
		this.approvalInfoVO = approvalInfoVOToSave;
		final ResultStatus rs = new ResultStatus();
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				ApprovalDetails approvalDetails = new ApprovalDetails();
				UserApprovalDetails userApprovalDetails = new UserApprovalDetails();
				UserProblemApproval userProblemApproval = new UserProblemApproval(); 
				try{
					
					List userPrevPosts = userProblemApprovalDAO.findProblemApprovalsByUser(approvalInfoVO.getUserId(), approvalInfoVO.getProblemHistoryId());
					ProblemHistory problemHistory = problemHistoryDAO.get(approvalInfoVO.getProblemHistoryId());
					/*if(userPrevPosts != null && userPrevPosts.size()>0 && !"FollowUp".equals(approvalInfoVO.getIsApproved()))
					{	
						rs.setExceptionMsg("You have already posted your comment!");
						rs.setResultCode(ResultCodeMapper.FAILURE);
					}
					else if(userPrevPosts != null && userPrevPosts.size() == 0 && "FollowUp".equals(approvalInfoVO.getIsApproved()))
					{	
						rs.setExceptionMsg("You should have Accepted or Rejected a probelm to participate in the discussion!");
						rs.setResultCode(ResultCodeMapper.FAILURE);
					}
					else if(problemHistory.getProblemLocation().getProblemAndProblemSource().getExternalUser().getUserId().equals(approvalInfoVO.getUserId()) && !"FollowUp".equals(approvalInfoVO.getIsApproved()))
					{
						rs.setExceptionMsg("This problem was reported by you.You can not Accept/Reject problems reported by you!");
						rs.setResultCode(ResultCodeMapper.FAILURE);						
					}
					else {*/
						java.util.Date today = new java.util.Date();
						String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
						SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
						String formatDate = sdf.format(today) ;
						today = sdf.parse(formatDate);
						
						approvalDetails.setIsApproved(approvalInfoVO.getIsApproved());
						approvalDetails.setReason(approvalInfoVO.getReason());
						approvalDetails.setPostedDate(problemManagementService.getCurrentDateAndTime());
						System.out.println("DAte is ::::::::::::::::::::::::::::"+problemManagementService.getCurrentDateAndTime());
						approvalDetails.setIsAdminApproved(null);
						userApprovalDetails.setUser(ananymousUserDAO.get(approvalInfoVO.getUserId()));
						userApprovalDetails.setApprovalDetails(approvalDetails);
						
						userProblemApproval.setProblemHistory(problemHistory);
						userProblemApproval.setUserApprovalDetails(userApprovalDetails);
						userProblemApprovalDAO.save(userProblemApproval);
						rs.setResultCode(ResultCodeMapper.SUCCESS);						
					}
					
				catch(Exception e){
					status.setRollbackOnly();
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while saving problemApporval data in DataApprovalService", e);
					}
					rs.setExceptionEncountered(e);
					rs.setExceptionClass(e.getClass().toString());
					rs.setResultCode(ResultCodeMapper.FAILURE);
					rs.setExceptionMsg("An Error ocured.Please Try Again!");
					e.printStackTrace();
				}
			}
		});
		
		return rs;
	}
	
	
	/**
	 * This method can be used to get total number of accepted and rejected votes count for a problem.
	 * @author Ravi Kiran.Y
	 * @version 1.0, 16/02/11
	 * @param problemHistoryId
	 * @return
	 */
	public ProblemBeanVO getCountOfPosts(Long problemHistoryId){
		ProblemBeanVO problemBeanVO  = new ProblemBeanVO();
		Long totalPostsCount = 0l;			
		try{
			List postsCount = userProblemApprovalDAO.findCountOfPosts(problemHistoryId);
			if(postsCount != null && postsCount.size()>0)
			{
				for(int i=0;i<postsCount.size();i++){
					Object[] params = (Object[])postsCount.get(i);
					if(IConstants.ACCEPT.equals(params[2].toString()))
						problemBeanVO.setAcceptedCount(params[1].toString());
					else if(IConstants.REJECT.equals(params[2].toString()))
						problemBeanVO.setRejectedCount(params[1].toString());				
					totalPostsCount += (Long)params[0]; 							
				}
			}
			problemBeanVO.setTotalResultsCount(totalPostsCount.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return problemBeanVO;
	}

	
	@SuppressWarnings("unchecked")
	public ProblemBeanVO getAllProblemComments(Long problemHistoryId, int startIndex, int maxResult) throws Exception {
		List<ApprovalInfoVO> approvals = new ArrayList<ApprovalInfoVO>(0);
		ProblemBeanVO problemBeanVO  = new ProblemBeanVO();
		ProblemBeanVO count = new ProblemBeanVO();
		try{
			List result = userProblemApprovalDAO.findApprovalInfoForProblem(problemHistoryId, startIndex,maxResult);
			count = getCountOfPosts(problemHistoryId);
			
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
			if(result != null && result.size()>0)
			{
				for(int i=0;i<result.size();i++){
					ApprovalInfoVO approvalInfoVO = new ApprovalInfoVO();
					Object[] parms = (Object[])result.get(i);
					approvalInfoVO.setReason(parms[3].toString());
					approvalInfoVO.setIsApproved(parms[4].toString());
					approvalInfoVO.setUserName(parms[1].toString()+" "+parms[2].toString());
					String approvedDate = sdf.format((Date)parms[5]);
					approvalInfoVO.setLastUpdate(approvedDate);
					approvals.add(approvalInfoVO);				
				}
			
			}
			problemBeanVO.setAcceptedCount(count.getAcceptedCount());
			problemBeanVO.setRejectedCount(count.getRejectedCount());	
			problemBeanVO.setTotalResultsCount(count.getTotalResultsCount().toString());
			problemBeanVO.setProblemApproovals(approvals);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return problemBeanVO;
	}
	
	@SuppressWarnings("unchecked")
	public List<ApprovalInfoVO> userApprovalDetailsbetweenDates(String fromDate,String toDate)
	{
		if(log.isDebugEnabled())
			log.debug("userApprovalDetailsbetweenDates(String fromDate,String toDate)");
				
		List<ApprovalInfoVO> approval = new ArrayList<ApprovalInfoVO>(0);
		SimpleDateFormat yearFormatSdf = new SimpleDateFormat(IConstants.DATE_PATTERN_YYYY_MM_DD);
		
		try {
			
			
			List result=userApprovalDetailsDAO.findUserApprovalStatusbetweendates(yearFormatSdf.parse(fromDate),yearFormatSdf.parse(toDate));
			
			if(log.isInfoEnabled())
				log.info("userapprovaldetailsSize:"+result.size());
			
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			
			if(result != null && result.size()>0)
			{
				for(int i=0;i<result.size();i++)
				{
					ApprovalInfoVO approvalInfoVO = new ApprovalInfoVO();
					Object[] parms = (Object[])result.get(i);
					
					approvalInfoVO.setReason(parms[0].toString());
					
					approvalInfoVO.setIsApproved(parms[1].toString());
					
					String postedDate = sdf.format((Date)parms[2]);
					approvalInfoVO.setPostedDate(postedDate);
					
					approvalInfoVO.setUserName(parms[3].toString()+" "+parms[4].toString());
					
					approvalInfoVO.setApprovalDetailsId(parms[5].toString());
					
					approval.add(approvalInfoVO);				
				}
			
			}
			
			
		} catch (Exception e) {
			if(log.isDebugEnabled())
				log.error("dataIteration problem in try block");
			e.printStackTrace();			
		}
			return approval;
}
	
	
	public List<ApprovalInfoVO> scrutinizePostedApprovals(List<Long> approvalDetailsIds,String approvedStatus) 
	{
		if(log.isDebugEnabled())
			log.debug("userApprovalDetailsbetweenDates(String fromDate,String toDate)");
				
		List<ApprovalInfoVO> approval = new ArrayList<ApprovalInfoVO>(0);
				
		try {
			
			ApprovalInfoVO approvalInfoVO = new ApprovalInfoVO();
			
			int count =  approvalDetailsDAO.updateSetIsApprovedStatusToPostedProblems(approvalDetailsIds, approvedStatus);
			approvalInfoVO.setCount(count);
			
		    }
				catch (Exception e) {
					if(log.isDebugEnabled())
						log.error("error occured while scrutinizing the approvals");
					e.printStackTrace();
			
		}
				return approval;
	}
	
	public Boolean checkUserFileUploadRight(Long userId, Long problemHistoryId)
	{
		List<Object[]> no =	problemHistoryDAO.checkUserFileUploadRight(userId ,problemHistoryId);
	
		if(no.size()>=1)
		{	
	   		return true;	
		}
			return false;
	}
}