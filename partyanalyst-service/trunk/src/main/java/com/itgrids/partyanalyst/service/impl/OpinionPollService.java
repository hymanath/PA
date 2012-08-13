package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IFeedbackCommentDAO;
import com.itgrids.partyanalyst.dao.IFeedbackDAO;
import com.itgrids.partyanalyst.dao.IFeedbackTaskDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollCommentsDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollQuestionOptionsDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollQuestionsDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollResultDAO;
import com.itgrids.partyanalyst.dao.IQuestionsRepositoryDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserFeedbackVO;
import com.itgrids.partyanalyst.model.FeedBack;
import com.itgrids.partyanalyst.model.FeedBackComment;
import com.itgrids.partyanalyst.model.FeedBackTask;
import com.itgrids.partyanalyst.model.OpinionPoll;
import com.itgrids.partyanalyst.model.OpinionPollComments;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;
import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.model.OpinionPollResult;
import com.itgrids.partyanalyst.model.QuestionsRepository;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.model.AbusedComments;
import com.itgrids.partyanalyst.model.Comment;
import com.itgrids.partyanalyst.dto.CommentVO;
import com.itgrids.partyanalyst.dao.IAbusedCommentsDAO;
import com.itgrids.partyanalyst.dao.ICommentDAO;

public class OpinionPollService implements IOpinionPollService {

	
	private static final Logger log = Logger.getLogger("OpinionPollService.class");
	private TransactionTemplate transactionTemplate;
	private IOpinionPollQuestionsDAO opinionPollQuestionsDAO;
	private IOpinionPollResultDAO opinionPollResultDAO;
	private IOpinionPollQuestionOptionsDAO opinionPollQuestionOptionsDAO;
	private IQuestionsRepositoryDAO questionsRepositoryDAO;
	private IOpinionPollDAO opinionPollDAO;
	private IFeedbackCommentDAO feedbackCommentDAO;
	private IFeedbackTaskDAO feedbackTaskDAO;
	private IFeedbackDAO feedbackDAO;
	private IProblemManagementService problemManagementService;
	private IUserDAO userDAO;
	private ICommentDAO commentDAO;
	private IOpinionPollCommentsDAO opinionPollCommentsDAO;
    private IAbusedCommentsDAO abusedCommentsDAO;
	 
	
	private DateUtilService dateUtilService = new DateUtilService();
	
	GregorianCalendar calendar = new GregorianCalendar();
	SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
		
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
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
	
	public ICommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(ICommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	
	public IOpinionPollCommentsDAO getOpinionPollCommentsDAO() {
		return opinionPollCommentsDAO;
	}

	public void setOpinionPollCommentsDAO(
			IOpinionPollCommentsDAO opinionPollCommentsDAO) {
		this.opinionPollCommentsDAO = opinionPollCommentsDAO;
	}
	
	public IAbusedCommentsDAO getAbusedCommentsDAO() {
		return abusedCommentsDAO;
	}

	public void setAbusedCommentsDAO(IAbusedCommentsDAO abusedCommentsDAO) {
		this.abusedCommentsDAO = abusedCommentsDAO;
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
			Set<String> set = new HashSet<String>(result);
			 for(int i=0;i<set.size();i++){
				 Iterator itr = set.iterator();
					Object[] parms = (Object[])itr.next();
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
				
				if(new Long(parms[3].toString()) >0)
					question.setPollExpire(true);
			}		
			
			question.setQuestionId(opinionPollQuestionId);
			question.setOptions(opinionPollQuestionAndPercentages);		
			question.setTotalVotesObtainedForPoll(totalPolledVotes);
			 List<Object> list = opinionPollQuestionsDAO.getTitleForQuestion(opinionPollQuestionId);
			if(list != null)
			{
			 for(Object params : list)
			 {
				 question.setTitle(params.toString());
			 }
			}
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
				  Set<String> set = new HashSet<String>(result);
				  
				 for(int i=0;i<set.size();i++){
					 Iterator itr = set.iterator();
						Object[] parms = (Object[])itr.next();
						OpinionPoll poll = (OpinionPoll) parms[0];
						opinionPollVO.setDescription(poll.getDescription());
						opinionPollVO.setTitle(poll.getTitle());
						Set<OpinionPollQuestions> qr = (Set<OpinionPollQuestions>) poll.getOpinionPollQuestions();	
						
						for(OpinionPollQuestions ops : qr){
							QuestionsOptionsVO opinionPollQuestionsVO = new QuestionsOptionsVO();
							List<OptionVO> listOfOptionVO = new ArrayList<OptionVO>(0); 
							
							QuestionsRepository questionsRepository = (QuestionsRepository) ops.getQuestionsRepository();
							opinionPollQuestionsVO.setQuestion(questionsRepository.getQuestion());
							opinionPollQuestionsVO.setQuestionId(ops.getOpinionPollQuestionsId());
							
							List<Object[]> options = opinionPollQuestionOptionsDAO.getOptions(questionsRepository.getQuestionsRepositoryId());
							{
								for(Object[] params : options)
								{
									OptionVO optionVO = new OptionVO();
									optionVO.setOptionId(Long.parseLong(params[0].toString()));
									optionVO.setOption(params[1].toString());
									listOfOptionVO.add(optionVO);
								}
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
			opinionPoll.setUser(userDAO.get(userId));
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

		if(feedbackVO.getUserId() != null && feedbackVO.getUserId() > 0)
			feedBack.setUser(userDAO.get(feedbackVO.getUserId()));
		
		feedBack.setFeedBackTask(feedbackTaskDAO.get(feedbackVO.getTaskName()));
		feedbackDAO.save(feedBack);
		
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
	        feedbackVO.setComment(feedback.getComment().replaceAll("\\s+", " "));
	        feedbackVO.setPosteddate(feedback.getPostedDate());
	        feedbackVO.setStatus(feedback.getStatus());
	        feedbackVO.setResponseCategory(feedback.getResponseCategory());
	        feedbackVO.setKindOfComment(feedback.getFeedBackComment().getCommentType());
	        feedbackVO.setTask(feedback.getFeedBackTask().getFeedBackTaskName());
	        
	        if(feedback.getUser()!=null){
		         
	        	feedbackVO.setUserName(feedback.getUser().getUserName());
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
	    
	    
	    /**
	     * This method will save the comments posted at opinion pole
	     * @param pollId
	     * @param userId
	     * @param commentDscr
	     * @param firstName
	     * @param lastName
	     */
	    
	     
	    public void saveOpinionPollComment(final Long pollId,final RegistrationVO  regVO,final String commentDscr,final String firstName,final String lastName){    	
	    	
	    	log.debug("Entered into the saveOpinionPollComment service method");
	    	
	    	try{
	    		
	    		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
	    			public void doInTransactionWithoutResult(TransactionStatus status) {	
	    		
		    	Comment comment = new Comment();
		    	
		    	comment.setComment(commentDscr);
		    	comment.setIsAbused("false");
		    	comment.setInsertedTime(dateUtilService.getCurrentDateAndTime());		        
		    	
		    	comment = commentDAO.save(comment);
		    	
		    	
		    	OpinionPollComments opinionPollComments = new OpinionPollComments();
		    	
		    	
		    	opinionPollComments.setFirstName(firstName);
		    	opinionPollComments.setLastName(lastName);		    	
		    	opinionPollComments.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		    	opinionPollComments.setIsApproved(IConstants.TRUE);
		    	opinionPollComments.setIsDelete(IConstants.FALSE);
		    	opinionPollComments.setOpinionPollId(pollId);
		    	opinionPollComments.setCommentId(comment.getCommentId());
		    	
		    	if(regVO != null)
		    	opinionPollComments.setUserId(regVO.getRegistrationID());
		    	else
		    		opinionPollComments.setUserId(null);
		    	
		    	
		    	opinionPollCommentsDAO.save(opinionPollComments);
		    	
	    			}
	    		});
			    	
	    	}catch(Exception e){
	    		
	    		log.error("Exception raised in  saveOpinionPollComment service method");	    		
	    		e.printStackTrace();
	    		
	    	}
	   }
	    
	    
public List<QuestionsOptionsVO> getAllQuestionAndPercentageOfVotesForChoices(){
	
	log.debug("Entered into the getAllQuestionAndPercentageOfVotesForChoices service method");
	Long totalPolledVotes=0l;		
	
	List<QuestionsOptionsVO> qstnOptnLst = new ArrayList<QuestionsOptionsVO>();
	
	ResultStatus resultStatus = new ResultStatus();
	try{
		
		List polIdLst = opinionPollResultDAO.getOpinionPollIds();
		
		Iterator itr = polIdLst.iterator();
		
		while(itr.hasNext()){
			
			totalPolledVotes = 0L;
			QuestionsOptionsVO question = new QuestionsOptionsVO();
		
		Long opinionPollQuestionId=(Long)itr.next();
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
		 List<Object> list = opinionPollQuestionsDAO.getTitleForQuestion(opinionPollQuestionId);
		if(list != null)
		{
		 for(Object params : list)
		 {
			 question.setTitle(params.toString());
		 }
		}
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
		 
		 qstnOptnLst.add(question);
		 question.setResultStatus(resultStatus);
		 
		
		 
		}
		return qstnOptnLst; 
	}catch(Exception e){
		resultStatus.setExceptionEncountered(e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		log.error("Exception raised  in the getAllQuestionAndPercentageOfVotesForChoices service method");
		//question.setResultStatus(resultStatus);
		return qstnOptnLst;
	}
 }


/**
 * This method is used to get all the comments for selected poll
 * @param pollId
 * @return cmntDtlsLst
 */
public List<CommentVO> getCommentsnOpinionPollByQuestionId(Long pollId){
	
	log.debug("Entered into getCommentsnOpinionPollByQuestionId service method");
	
	List<Object[]> commentsList = new ArrayList<Object[]>(0);
	List<CommentVO> cmntDtlsLst = new ArrayList<CommentVO>(0);
		
	try{
	
	//commentsist = opinionPollCommentsDAO.getCommentDetailsByQuestionId(pollId);
		
		commentsList = opinionPollCommentsDAO.getCommentDetailsByQuestionId(pollId);
		
		Iterator itr = commentsList.iterator();
		
			while(itr.hasNext()){
				
				Object[] obj = (Object[])itr.next();
				
			     CommentVO  commentVO= new CommentVO();
			     
			     commentVO.setCommentId((Long)obj[0]);
			     commentVO.setComment(obj[1].toString());
			     
			     if(obj[2] != null)
			    	 commentVO.setFirstName(obj[2].toString());
			     
			     if(obj[3] != null)
			        commentVO.setLastName(obj[3].toString());
			     
			     if(obj[4] != null)
			       commentVO.setDate(obj[4].toString());			     
			     
			     cmntDtlsLst.add(commentVO);
				
	     }
	  }catch(Exception e){
		  log.error("Exception raised in  getCommentsnOpinionPollByQuestionId service method");
		e.printStackTrace();		
	  }
	
	return cmntDtlsLst;	
}

/**
 * This method is used to save abused comment details
 * @param cmntId 
 * @param regVO
 */
public String saveAbuseCommentDetails(Long cmntId,RegistrationVO regVO){
	
	log.debug("Entered into saveAbuseCommentDetails service method");
	
	try{
	    Long cnt = abusedCommentsDAO.checkForAlreadyAbused(cmntId);
	
	    if(cnt == 0){
		
			AbusedComments abusedComments = new AbusedComments();
			
			abusedComments.setCommentId(cmntId);
			abusedComments.setStatus(IConstants.FALSE);
			abusedComments.setTime(dateUtilService.getCurrentDateAndTime());
			abusedComments.setIsDelete(IConstants.FALSE);
			
			if(regVO != null)
			abusedComments.setUserId(regVO.getRegistrationID());
			
			abusedCommentsDAO.save(abusedComments);
	   }
	    
	  }catch(Exception e){
		  
		log.error("Exception raised in saveAbuseCommentDetails service method");		
		e.printStackTrace();
		
	  }
	return IConstants.SUCCESS;
	
}
}
