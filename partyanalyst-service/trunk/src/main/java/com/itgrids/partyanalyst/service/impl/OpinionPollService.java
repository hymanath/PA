package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.IFeedbackCommentDAO;
import com.itgrids.partyanalyst.dao.IFeedbackDAO;
import com.itgrids.partyanalyst.dao.IFeedbackTaskDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollQuestionOptionsDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollQuestionsDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollResultDAO;
import com.itgrids.partyanalyst.dao.IQuestionsRepositoryDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.hibernate.AnanymousUserDAO;
import com.itgrids.partyanalyst.dao.hibernate.FeedbackDAO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserFeedbackVO;
import com.itgrids.partyanalyst.model.CensusParameter;
import com.itgrids.partyanalyst.model.FeedBack;
import com.itgrids.partyanalyst.model.FeedBackComment;
import com.itgrids.partyanalyst.model.FeedBackTask;
import com.itgrids.partyanalyst.model.OpinionPoll;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;
import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.model.OpinionPollResult;
import com.itgrids.partyanalyst.model.QuestionsRepository;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class OpinionPollService implements IOpinionPollService {

	
	private static final Logger log = Logger.getLogger("OpinionPollService.class");
	private TransactionTemplate transactionTemplate;
	private IOpinionPollQuestionsDAO opinionPollQuestionsDAO;
	private IOpinionPollResultDAO opinionPollResultDAO;
	private IOpinionPollQuestionOptionsDAO opinionPollQuestionOptionsDAO;
	private IQuestionsRepositoryDAO questionsRepositoryDAO;
	private IOpinionPollDAO opinionPollDAO;
	private IRegistrationDAO registrationDAO;
	private IAnanymousUserDAO ananymousUserDAO;
	private IFeedbackCommentDAO feedbackCommentDAO;
	private IFeedbackTaskDAO feedbackTaskDAO;
	private IFeedbackDAO feedbackDAO;
	private IProblemManagementService problemManagementService;
	GregorianCalendar calendar = new GregorianCalendar();
	SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
		
	
	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
     
	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
	}
	
	public IQuestionsRepositoryDAO getQuestionsRepositoryDAO() {
		return questionsRepositoryDAO;
	}

	public void setQuestionsRepositoryDAO(
			IQuestionsRepositoryDAO questionsRepositoryDAO) {
		this.questionsRepositoryDAO = questionsRepositoryDAO;
	}

	public IOpinionPollDAO getOpinionPollDAO() {
		return opinionPollDAO;
	}

	public void setOpinionPollDAO(IOpinionPollDAO opinionPollDAO) {
		this.opinionPollDAO = opinionPollDAO;
	}

	public IOpinionPollQuestionOptionsDAO getOpinionPollQuestionOptionsDAO() {
		return opinionPollQuestionOptionsDAO;
	}

	public void setOpinionPollQuestionOptionsDAO(
			IOpinionPollQuestionOptionsDAO opinionPollQuestionOptionsDAO) {
		this.opinionPollQuestionOptionsDAO = opinionPollQuestionOptionsDAO;
	}

	public IOpinionPollResultDAO getOpinionPollResultDAO() {
		return opinionPollResultDAO;
	}

	public void setOpinionPollResultDAO(IOpinionPollResultDAO opinionPollResultDAO) {
		this.opinionPollResultDAO = opinionPollResultDAO;
	}

	public IOpinionPollQuestionsDAO getOpinionPollQuestionsDAO() {
		return opinionPollQuestionsDAO;
	}

	public void setOpinionPollQuestionsDAO(
			IOpinionPollQuestionsDAO opinionPollQuestionsDAO) {
		this.opinionPollQuestionsDAO = opinionPollQuestionsDAO;
	}
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public IFeedbackCommentDAO getFeedbackCommentDAO() {
		return feedbackCommentDAO;
	}

	public void setFeedbackCommentDAO(IFeedbackCommentDAO feedbackCommentDAO) {
		this.feedbackCommentDAO = feedbackCommentDAO;
	}

	public IFeedbackTaskDAO getFeedbackTaskDAO() {
		return feedbackTaskDAO;
	}

	public void setFeedbackTaskDAO(IFeedbackTaskDAO feedbackTaskDAO) {
		this.feedbackTaskDAO = feedbackTaskDAO;
	}

	public IFeedbackDAO getFeedbackDAO() {
		return feedbackDAO;
	}

	public void setFeedbackDAO(IFeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}

	
		
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public QuestionsOptionsVO saveSelectionResultOfThePoll(final Long opinionPollQuestionId,final Long opinionPollQuestionOptionsId){
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {	
				OpinionPollResult opinionPollResult = new OpinionPollResult();
				List result = opinionPollResultDAO.getOpinionPollResultByQuestionAndOptionId(opinionPollQuestionId,opinionPollQuestionOptionsId);
				if(result.size()==0){			
					opinionPollResult.setOpinionPollQuestions(opinionPollQuestionsDAO.get(opinionPollQuestionId));
					opinionPollResult.setOpinionPollQuestionOptions(opinionPollQuestionOptionsDAO.get(opinionPollQuestionOptionsId));
					opinionPollResult.setCount(1l);
					opinionPollResultDAO.save(opinionPollResult);
				}else{					
					Long count = 0l;
					Long opinionPollResultId =0l;					
					for(int i=0;i<result.size();i++){
						Object[] parms = (Object[]) result.get(i);
						count = new Long(parms[0].toString());
						opinionPollResultId = new Long(parms[1].toString());		
					}
					opinionPollResult = opinionPollResultDAO.get(opinionPollResultId);
					opinionPollResult.setCount(count+1l);
					opinionPollResultDAO.save(opinionPollResult);
				}				
			}
		});
		
		return getQuestionAndPercentageOfVotesForChoices(opinionPollQuestionId);
	}
	

	/**
	 * This method is used to get the results of the latest opinion poll
	 * @return
	 */
	public OpinionPollVO getDetailsOfTheLatestOpinionPoll(){
		List result  = null;
		OpinionPollVO opinionPollVO  = new OpinionPollVO();
		ResultStatus resultStatus = new ResultStatus();
		Long latestPollId=1l;
		try{
			result  = opinionPollQuestionsDAO.getAllPollsForThePresentDay(getCurrentDateAndTime(),IConstants.TRUE);	
			 for(int i=0;i<result.size();i++){
					Object[] parms = (Object[])result.get(i);
					OpinionPollQuestions poll = (OpinionPollQuestions) parms[3];
					if(i==0){
						latestPollId = poll.getOpinionPollQuestionsId();	
					}
			 }
			opinionPollVO.setQuestionsOptionsVO(getQuestionAndPercentageOfVotesForChoices(latestPollId));
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
			opinionPollVO.setResultStatus(resultStatus);
			return opinionPollVO; 
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			opinionPollVO.setResultStatus(resultStatus);
			return opinionPollVO;
		}
		 
	}
	
	/**
	 * This method caluculates the percentage of votes that are obtained for each option in that question and 
	 * sets the data in to QuestionsOptionsVO.
	 *   
	 * @param opinionPollQuestionId
	 * @return
	 */
	public QuestionsOptionsVO getQuestionAndPercentageOfVotesForChoices(Long opinionPollQuestionId){
		Long totalPolledVotes=0l;		
		QuestionsOptionsVO question = new QuestionsOptionsVO();
		ResultStatus resultStatus = new ResultStatus();
		try{
			List result = opinionPollResultDAO.getOpinionPollAnswersForAQuestionByQuestionId(opinionPollQuestionId);		
			List<OptionVO> opinionPollQuestionAndPercentages = new ArrayList<OptionVO>(0);
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				totalPolledVotes+=new Long(parms[0].toString());
				question.setQuestion(parms[2].toString());			
			}		
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				OptionVO optionVO = new OptionVO();
				optionVO.setOption(parms[1].toString());	
				optionVO.setVotesObtained(new Long(parms[0].toString()));
				optionVO.setPercentage(new BigDecimal((new Long(parms[0].toString())*100.0)/totalPolledVotes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				opinionPollQuestionAndPercentages.add(optionVO);			
				question.setDifferenceBetweenCurrentDateAndPolledDate(new Long(parms[3].toString()));
			}		
			
			question.setQuestionId(opinionPollQuestionId);
			question.setOptions(opinionPollQuestionAndPercentages);		
			question.setTotalVotesObtainedForPoll(totalPolledVotes);
			 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
			 question.setResultStatus(resultStatus);
			return question; 
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			question.setResultStatus(resultStatus);
			return question;
		}
	}
	
	
	
	/**
	 * This method gets all the polls that are needed to be displayed for the user for that day.
	 * 
	 * @return OpinionPollVO
	 * @author Ravi Kiran.Y
	 */
	
	public OpinionPollVO getAllPollsForTheDay(){
			List result  = null;
			OpinionPollVO opinionPollVO  = new OpinionPollVO();
			ResultStatus resultStatus = new ResultStatus();
			try{
				List<QuestionsOptionsVO> listOfQuestionsOptionsVO = new ArrayList<QuestionsOptionsVO>();
				 result  = opinionPollQuestionsDAO.getAllPollsForThePresentDay(getCurrentDateAndTime(),IConstants.TRUE);
				 for(int i=0;i<result.size();i++){
						Object[] parms = (Object[])result.get(i);
						OpinionPoll poll = (OpinionPoll) parms[0];
						opinionPollVO.setDescription(poll.getDescription());
						Set<OpinionPollQuestions> qr = (Set<OpinionPollQuestions>) poll.getOpinionPollQuestions();	
						
						for(OpinionPollQuestions ops : qr){
							QuestionsOptionsVO opinionPollQuestionsVO = new QuestionsOptionsVO();
							List<OptionVO>  listOfOptionVO = new ArrayList<OptionVO>(0); 
							
							QuestionsRepository questionsRepository = (QuestionsRepository) ops.getQuestionsRepository();
							opinionPollQuestionsVO.setQuestion(questionsRepository.getQuestion());
							opinionPollQuestionsVO.setQuestionId(questionsRepository.getQuestionsRepositoryId());
							Set<OpinionPollQuestionOptions> opinionPollQuestionOptions = (Set<OpinionPollQuestionOptions>)  questionsRepository.getOpinionPollQuestionOptions();
							
							for(OpinionPollQuestionOptions options : opinionPollQuestionOptions){
								OptionVO optionVO = new OptionVO();
								optionVO.setOptionId(options.getOpinionPollQuestionOptionsId());
								optionVO.setOption(options.getQuestionOption());
								listOfOptionVO.add(optionVO);
							}
							opinionPollQuestionsVO.setOptions(listOfOptionVO);
							listOfQuestionsOptionsVO.add(opinionPollQuestionsVO);
						}
						
						opinionPollVO.setQuesitons(listOfQuestionsOptionsVO);
					 }
				 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
				 opinionPollVO.setResultStatus(resultStatus);
				return opinionPollVO; 
			}catch(Exception e){
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				opinionPollVO.setResultStatus(resultStatus);
				return opinionPollVO;
			}
	}	

	/**
	 * This method is useful to retrive all the polls that are polled till now.
	 * @return List<SelectOptionVO>
	 * @author Ravi Kiran.Y
	 */
	public List<QuestionsOptionsVO> getAllPolls(){		
		List<QuestionsOptionsVO> allPolls = new ArrayList<QuestionsOptionsVO>(0);		 
		try{
			List result  = opinionPollQuestionsDAO.getAllOpinionPolls(IConstants.TRUE);
			 for(int i=0;i<result.size();i++){
				 Object[] parms = (Object[])result.get(i);
				 QuestionsOptionsVO resultVo = new QuestionsOptionsVO(); 
				 resultVo.setQuestionId(new Long(parms[0].toString()));
				 resultVo.setQuestion(parms[1].toString());
				 resultVo.setStartDate(parms[2].toString());
				 allPolls.add(resultVo);
			 }
			return allPolls;
		}catch(Exception e){
			return null;
		}		 
	}
	
	/**
	 * 
	 * This method returns current date in dd/MM/yyyy format.
	 * 
	 * @return Date
	 * @author Ravi Kiran.Y 
	 */
	 public Date getCurrentDateAndTime(){
			try {
					java.util.Date now = new java.util.Date();
			        String DATE_FORMAT = "dd/MM/yyyy";
			        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			        String strDateNew = sdf.format(now);        
					now = sdf.parse(strDateNew);
				return now;
		    } catch (ParseException e) {
		    		e.printStackTrace();
				return null;
			}
	}
	 
	 
	 /**
		 * This method creates the poll given by admin/user.
		 * 
		 * @return boolean
		 * @author Kamalalakar Dandu
		 */
	 
	 
	 public boolean createPoll(OpinionPollVO dataVO, Long userId) {
			boolean isSaved = false;
			OpinionPoll opinionPoll = new OpinionPoll();
			opinionPoll.setRegistration(registrationDAO.get(userId)); 
			opinionPoll.setDescription(dataVO.getDescription());
			opinionPoll.setOpinionPollStartDate(dataVO.getOpinionPollStartDate());
			opinionPoll.setOpinionPollEndDate(dataVO.getOpinionPollEndDate());
			opinionPoll.setTitle(dataVO.getTitle());
			opinionPoll.setIs_delete("false");
			opinionPoll = opinionPollDAO.save(opinionPoll);
			
			for(QuestionsOptionsVO result:dataVO.getQuesitons()){
				
				QuestionsRepository questionsRepository = new QuestionsRepository();
				questionsRepository.setQuestion(result.getQuestion());
				questionsRepository = questionsRepositoryDAO.save(questionsRepository);
				
				OpinionPollQuestions opinionPollQuestions = new OpinionPollQuestions();
				opinionPollQuestions.setOpinionPoll(opinionPoll);
				opinionPollQuestions.setQuestionsRepository(questionsRepository);
				opinionPollQuestions = opinionPollQuestionsDAO.save(opinionPollQuestions);
							
				for(OptionVO result2 : result.getOptions()){
					
					OpinionPollQuestionOptions opinionPollQuestionOptions = new OpinionPollQuestionOptions();
					opinionPollQuestionOptions.setQuestionsRepository(questionsRepository);
					opinionPollQuestionOptions.setQuestionOption(result2.getOption());
					
					opinionPollQuestionOptions = opinionPollQuestionOptionsDAO.save(opinionPollQuestionOptions);
					
					/*OpinionPollResult opinionPollResult =new OpinionPollResult();
					opinionPollResult.setOpinionPollQuestions(opinionPollQuestions);
					opinionPollResult.setOpinionPollQuestionOptions(opinionPollQuestionOptions);
					opinionPollResult.setCount(0l);
					
					opinionPollResult = opinionPollResultDAO.save(opinionPollResult);  */
				}
					
			}
			isSaved = true;
			return isSaved;
			
		}
	 
	public ResultStatus saveUserFeedback(UserFeedbackVO feedbackVO) 
	{
		ResultStatus rs = new ResultStatus();
		FeedBack feedBack =  new FeedBack();
		try {
			
		feedBack.setComment(feedbackVO.getComment().replace("\n"," "));
		feedBack.setResponseCategory(feedbackVO.getResponseCategory());
		
		feedBack.setFeedBackComment(feedbackCommentDAO.get(feedbackVO.getCommentType()));
		feedBack.setStatus(IConstants.NEW);
		feedBack.setPostedDate(problemManagementService.getCurrentDateAndTime());
		
		if(feedbackVO.getUserType() != null && feedbackVO.getUserType().equalsIgnoreCase(IConstants.PARTY_ANALYST_USER))
			
			feedBack.setRegistration(registrationDAO.get(feedbackVO.getUserId()));
		
		else if(feedbackVO.getUserType() != null && feedbackVO.getUserType().equalsIgnoreCase(IConstants.FREE_USER))
			
			feedBack.setAnanymousUser(ananymousUserDAO.get(feedbackVO.getUserId()));
		
		feedBack.setFeedBackTask(feedbackTaskDAO.get(feedbackVO.getTaskName()));
		feedbackDAO.save(feedBack);
		
		//saved successfully
		rs.setExceptionMsg("Feedback Saved Successfully ..");
		
		}catch(Exception e){
			
			log.error("Exception Raised :" + e);
			rs.setExceptionEncountered(e);
			rs.setExceptionMsg(e.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
		 return rs;
		}
		
		
	 return rs;
	}
	
	public List<SelectOptionVO> getAllValuesFromFeedbackComment()
	{
		List<SelectOptionVO> selectOptionVOList=new ArrayList<SelectOptionVO>(0);
		List<FeedBackComment> list = feedbackCommentDAO.getAll();
		for(FeedBackComment feedbackComment:list)
		{
			SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(feedbackComment.getFeedBackCommentId());
			selectOption.setName(feedbackComment.getCommentType());
			selectOptionVOList.add(selectOption);
		}
			return selectOptionVOList;
	
	}
    
	public List<SelectOptionVO> getAllValuesFromFeedbackTask()
	{
		List<SelectOptionVO> selectOptionVOList=new ArrayList<SelectOptionVO>(0);
		List<FeedBackTask> list = feedbackTaskDAO.getAll();
		for(FeedBackTask feedbackTask:list)
		{
			SelectOptionVO selectOption = new SelectOptionVO();
			selectOption.setId(feedbackTask.getFeedBackTaskId());
			selectOption.setName(feedbackTask.getFeedBackTaskName());
			selectOptionVOList.add(selectOption);
		}
			return selectOptionVOList;
	}

	public List<UserFeedbackVO> getAllDetailsForToday(){
		
		List<UserFeedbackVO> userFeedbackVO= new ArrayList<UserFeedbackVO>(0);
		
		List<FeedBack> feedbackList = feedbackDAO.getAll();
		for(FeedBack feedback : feedbackList){
			
	        UserFeedbackVO feedbackVO=new UserFeedbackVO();
	        
	        feedbackVO.setCommentId(feedback.getFeedbackId());
	        feedbackVO.setComment(feedback.getComment());
	        feedbackVO.setPosteddate(feedback.getPostedDate());
	        feedbackVO.setStatus(feedback.getStatus());
	        feedbackVO.setResponseCategory(feedback.getResponseCategory());
	        feedbackVO.setKindOfComment(feedback.getFeedBackComment().getCommentType());
	        feedbackVO.setTask(feedback.getFeedBackTask().getFeedBackTaskName());
	        
	        if(feedback.getRegistration()!=null){
		         
	        	feedbackVO.setUserName(feedback.getRegistration().getUserName());
	        }
	        else if(feedback.getAnanymousUser()!=null){
	        	feedbackVO.setUserName(feedback.getAnanymousUser().getUsername());
	        }
	       
	        userFeedbackVO.add(feedbackVO);
		}
	        return userFeedbackVO;
		}
		
	    public Integer getAllApprovedDetails(List<Long> commentIdsList,String approveType)
	    {
	    	try
	    	{
	    		String actionType = null ;
	    		
	    		if(approveType.equalsIgnoreCase("approve"))
	    			actionType = IConstants.APPROVED;
	    	
	    		else if(approveType.equalsIgnoreCase("reject"))
	    			actionType = IConstants.REJECTED;

	    		return  feedbackDAO.updateStatusToApproveOrReject(commentIdsList,actionType);
	    	}
	    	catch(Exception e){
	    		return 0;
	    	}
	    }
	
	
	
	
	
	}
