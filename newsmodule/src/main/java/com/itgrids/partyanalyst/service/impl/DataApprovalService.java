package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IDataApprovalService;


public class DataApprovalService implements IDataApprovalService {/*

	private static final Logger log = Logger.getLogger("DataApprovalService.class");
	private TransactionTemplate transactionTemplate = null;
	private IProblemHistoryDAO problemHistoryDAO;
	private IUserProblemApprovalDAO userProblemApprovalDAO; 
	private IUserApprovalDetailsDAO userApprovalDetailsDAO; ; 
	private ApprovalInfoVO approvalInfoVO;
	private IProblemManagementService  problemManagementService;
	private IApprovalDetailsDAO approvalDetailsDAO;
	private IUserDAO userDAO;
	private IProblemDAO problemDAO;
	private IProblemCommentsDAO problemCommentsDAO;
	private ICommentDAO commentDAO;
	private IProblemProgressDAO problemProgressDAO;
	private IUserProblemDAO userProblemDAO;
	private IVisibilityDAO visibilityDAO;
	private IProblemActivityDAO problemActivityDAO;
	
	
    public IProblemActivityDAO getProblemActivityDAO() {
		return problemActivityDAO;
	}

	public void setProblemActivityDAO(IProblemActivityDAO problemActivityDAO) {
		this.problemActivityDAO = problemActivityDAO;
	}

	public IVisibilityDAO getVisibilityDAO() {
		return visibilityDAO;
	}

	public void setVisibilityDAO(IVisibilityDAO visibilityDAO) {
		this.visibilityDAO = visibilityDAO;
	}

	public IUserProblemDAO getUserProblemDAO() {
		return userProblemDAO;
	}

	public void setUserProblemDAO(IUserProblemDAO userProblemDAO) {
		this.userProblemDAO = userProblemDAO;
	}

	public IProblemProgressDAO getProblemProgressDAO() {
		return problemProgressDAO;
	}

	public void setProblemProgressDAO(IProblemProgressDAO problemProgressDAO) {
		this.problemProgressDAO = problemProgressDAO;
	}

	public ICommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(ICommentDAO commentDAO) {
		this.commentDAO = commentDAO;
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

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

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
					if(problemHistory.getProblemLocation().getProblemAndProblemSource().getFreeUser().getUserId().equals(approvalInfoVO.getUserId()) && !"FollowUp".equals(approvalInfoVO.getIsApproved()))
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

	*//**
	 * This method is used to save the acceptance/rejections of a problem by a user.
	 *//*
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
				Comment comment = new Comment();
				ProblemComments problemComments =new ProblemComments();
				ProblemProgress problemProgress = new ProblemProgress();
				DateUtilService dateUtilService = new DateUtilService();
				
				
				
				try{
					//save comment details in Comment class
					comment.setComment(approvalInfoVO.getReason());
					comment.setIsAbused(IConstants.FALSE);
					comment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					
					//save problem details in problemComments class
					problemComments.setProblem(problemDAO.get(approvalInfoVO.getProblemHistoryId()));
					//problemComments.setComment(comment);(or)
					problemComments.setComment(commentDAO.save(comment));
					problemComments.setUser(userDAO.get(approvalInfoVO.getUserId()));
					problemComments.setIsApproved(IConstants.FALSE);
					problemComments.setIsDelete(IConstants.FALSE);
					problemComments.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					problemCommentsDAO.save(problemComments);
					
					
					List userPrevPosts = userProblemApprovalDAO.findProblemApprovalsByUser(approvalInfoVO.getUserId(), approvalInfoVO.getProblemHistoryId());
					ProblemHistory problemHistory = problemHistoryDAO.get(approvalInfoVO.getProblemHistoryId());
					if(userPrevPosts != null && userPrevPosts.size()>0 && !"FollowUp".equals(approvalInfoVO.getIsApproved()))
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
					else {
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
						userApprovalDetails.setUser(userDAO.get(approvalInfoVO.getUserId()));
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
	
	
	*//**
	 * This method can be used to get total number of accepted and rejected votes count for a problem.
	 * @author Ravi Kiran.Y
	 * @version 1.0, 16/02/11
	 * @param problemHistoryId
	 * @return
	 *//*
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
			
			
			List result=problemCommentsDAO.findUserApprovalStatusbetweendates(yearFormatSdf.parse(fromDate),yearFormatSdf.parse(toDate));
			
			if(log.isInfoEnabled())
				log.info("userapprovaldetailsSize:"+result.size());
			
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			
			if(result != null && result.size()>0)
			{
				for(int i=0;i<result.size();i++)
				{
					ApprovalInfoVO approvalInfoVO = new ApprovalInfoVO();
					Object[] parms = (Object[])result.get(i);
					approvalInfoVO.setApprovalDetailsId(parms[0].toString());
					approvalInfoVO.setReason(parms[1].toString());
				
					String postedDate = sdf.format((Date)parms[2]);
					approvalInfoVO.setPostedDate(postedDate);
					
					approvalInfoVO.setUserName(parms[3].toString()+" "+parms[4].toString());
					if(parms[5] != null && parms[5].toString().equalsIgnoreCase(IConstants.FALSE))
						approvalInfoVO.setIsApproved(IConstants.NEW);
					if(parms[5] != null && parms[5].toString().equalsIgnoreCase(IConstants.TRUE))
						approvalInfoVO.setIsApproved(IConstants.APPROVED);
					if(parms[5] != null && parms[5].toString().equalsIgnoreCase(IConstants.REJECTED))
						approvalInfoVO.setIsApproved(IConstants.REJECTED);
					if(parms[5]==null)
						approvalInfoVO.setIsApproved(IConstants.NEW);
					if(parms[5] != null && parms[5].toString().equalsIgnoreCase(IConstants.FALSE))
						approvalInfoVO.setIsApproved(IConstants.REJECTED);
					
					if(parms[5] != null && parms[5].toString().equalsIgnoreCase(IConstants.TRUE))
						approvalInfoVO.setIsApproved(IConstants.APPROVED);
					
					
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
			String isApproved = null;	
		List<ApprovalInfoVO> approval = new ArrayList<ApprovalInfoVO>(0);
				
		try {
			
			ApprovalInfoVO approvalInfoVO = new ApprovalInfoVO();
			if(approvedStatus.toString().equalsIgnoreCase(IConstants.APPROVED))
				isApproved = IConstants.TRUE;
			else if(approvedStatus.toString().equalsIgnoreCase(IConstants.REJECTED))
				isApproved = IConstants.REJECTED;
			//int count =  approvalDetailsDAO.updateSetIsApprovedStatusToPostedProblems(approvalDetailsIds, approvedStatus);
			int count =  problemCommentsDAO.updatesCommentsByAdmin(approvalDetailsIds, isApproved);
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
		//List<Object[]> no =	problemHistoryDAO.checkUserFileUploadRight(userId ,problemHistoryId);
		List<Object[]> no =userProblemDAO.checkUserFileUploadRight(userId, problemHistoryId);
	
		if(no.size()>=1)
		{	
	   		return true;	
		}
			return false;
	}
*/}