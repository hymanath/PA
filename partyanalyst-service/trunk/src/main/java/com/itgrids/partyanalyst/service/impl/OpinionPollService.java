package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IOpinionPollDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollQuestionOptionsDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollQuestionsDAO;
import com.itgrids.partyanalyst.dao.IOpinionPollResultDAO;
import com.itgrids.partyanalyst.dao.IQuestionsRepositoryDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.OpinionPoll;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;
import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.model.OpinionPollResult;
import com.itgrids.partyanalyst.model.QuestionsRepository;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.itgrids.partyanalyst.utils.IConstants;

public class OpinionPollService implements IOpinionPollService {

	private TransactionTemplate transactionTemplate;
	private IOpinionPollQuestionsDAO opinionPollQuestionsDAO;
	private IOpinionPollResultDAO opinionPollResultDAO;
	private IOpinionPollQuestionOptionsDAO opinionPollQuestionOptionsDAO;
	private IQuestionsRepositoryDAO questionsRepositoryDAO;
	private IOpinionPollDAO opinionPollDAO;
	private IRegistrationDAO registrationDAO;
	
	
	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
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
					latestPollId = poll.getOpinionPollQuestionsId();
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
	
}
