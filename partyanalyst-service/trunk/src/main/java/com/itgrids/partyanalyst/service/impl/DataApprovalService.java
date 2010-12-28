package com.itgrids.partyanalyst.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IUserProblemApprovalDAO;
import com.itgrids.partyanalyst.dao.hibernate.ProblemHistoryDAO;
import com.itgrids.partyanalyst.dto.ApprovalInfoVO;
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
	private ApprovalInfoVO approvalInfoVO;
	private IProblemManagementService  problemManagementService;
	
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
					if(userPrevPosts != null && userPrevPosts.size()>0)
					{	
						rs.setExceptionMsg("You have already posted your comment!");
						rs.setResultCode(ResultCodeMapper.FAILURE);
					}
					else if(problemHistory.getProblemLocation().getProblemAndProblemSource().getExternalUser().getUserId() == approvalInfoVO.getUserId())
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
						userApprovalDetails.setUser(ananymousUserDAO.get(approvalInfoVO.getUserId()));
						userApprovalDetails.setApprovalDetails(approvalDetails);
						
						userProblemApproval.setProblemHistory(problemHistory);
						userProblemApproval.setUserApprovalDetails(userApprovalDetails);
						userProblemApprovalDAO.save(userProblemApproval);
						rs.setResultCode(ResultCodeMapper.SUCCESS);						
					}
					
				}catch(Exception e){
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

	public List<ApprovalInfoVO> getAllProblemComments(Long problemHistoryId) throws Exception {
		List result = userProblemApprovalDAO.findApprovalInfoForProblem(problemHistoryId);
		List<ApprovalInfoVO> approvals = new ArrayList<ApprovalInfoVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		if(result != null && result.size()>0)
		{
			for(int i=0;i<result.size();i++){
				ApprovalInfoVO approvalInfoVO = new ApprovalInfoVO();
				Object[] parms = (Object[])result.get(i);
				approvalInfoVO.setReason(parms[3].toString());
				approvalInfoVO.setIsApproved(parms[4].toString());
				approvalInfoVO.setUserName(parms[1].toString()+parms[2].toString());
				String approvedDate = sdf.format((Date)parms[5]);
				approvalInfoVO.setLastUpdate(approvedDate);
				approvals.add(approvalInfoVO);				
			}
		
	}
		return approvals;
	}
	
	
}
