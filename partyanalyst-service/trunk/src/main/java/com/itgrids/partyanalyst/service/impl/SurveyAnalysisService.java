package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IOptionDAO;
import com.itgrids.partyanalyst.dao.IOptionTypeDAO;
import com.itgrids.partyanalyst.dao.IQuestionOptionsDAO;
import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Option;
import com.itgrids.partyanalyst.model.QuestionOptions;
import com.itgrids.partyanalyst.model.SurveyQuestion;
import com.itgrids.partyanalyst.service.ISurveyAnalysisService;

public class SurveyAnalysisService implements ISurveyAnalysisService {
	
	private static Logger Log = Logger.getLogger(SurveyAnalysisService.class);
	
	private ISurveyQuestionDAO surveyQuestionDAO;
	private IOptionDAO optionDAO;
	private IOptionTypeDAO optionTypeDAO;
	private IQuestionOptionsDAO questionOptionsDAO;
	private TransactionTemplate transactionTemplate;
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public IQuestionOptionsDAO getQuestionOptionsDAO() {
		return questionOptionsDAO;
	}
	public void setQuestionOptionsDAO(IQuestionOptionsDAO questionOptionsDAO) {
		this.questionOptionsDAO = questionOptionsDAO;
	}

	public IOptionTypeDAO getOptionTypeDAO() {
		return optionTypeDAO;
	}

	public void setOptionTypeDAO(IOptionTypeDAO optionTypeDAO) {
		this.optionTypeDAO = optionTypeDAO;
	}

	public ISurveyQuestionDAO getSurveyQuestionDAO() {
		return surveyQuestionDAO;
	}

	public void setSurveyQuestionDAO(ISurveyQuestionDAO surveyQuestionDAO) {
		this.surveyQuestionDAO = surveyQuestionDAO;
	}

	public IOptionDAO getOptionDAO() {
		return optionDAO;
	}

	public void setOptionDAO(IOptionDAO optionDAO) {
		this.optionDAO = optionDAO;
	}


	public List<SelectOptionVO>  getOptionTypes(){
		List<SelectOptionVO>  optionTypes = new ArrayList<SelectOptionVO>();
		SelectOptionVO valuesVo = null;
		List<Object[]> list = null;
		try{
		Log.debug("Enterd into getOptionTypes Method in SurveyAnalysis service");
		list =  optionTypeDAO.getOptionTypes();
		if(list != null){
			for(Object[] params : list){
				valuesVo = new SelectOptionVO();
				valuesVo.setId((Long)params[0]);
				valuesVo.setName(params[1].toString());
				
				optionTypes.add(valuesVo);
			}
		}
		
		}
		catch (Exception e) {
			Log.error("Exception in getOptionTypes Method in SurveyAnalysis service",e);
			
		}
		
		return optionTypes;
	}

	public ResultStatus saveQuestion(final List<QuestionsOptionsVO> questionsOptionsList)
	{
		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();

						try {

			if(questionsOptionsList!= null && questionsOptionsList.size() > 0)
			{
			SurveyQuestion surveyQuestion = new SurveyQuestion();
			surveyQuestion.setQuestion(questionsOptionsList.get(0).getQuestion());
			surveyQuestion.setOptionType(optionTypeDAO.get(new Long(questionsOptionsList.get(0).getQuestionType())));
			surveyQuestion.setHasRemarks(String.valueOf(questionsOptionsList.get(0).getHasRemark()));
			surveyQuestion = surveyQuestionDAO.save(surveyQuestion);
			
				if(questionsOptionsList.get(0).getOptions() != null && questionsOptionsList.get(0).getOptions().size() > 0)
					{
							for(int i=0;i<questionsOptionsList.get(0).getOptions().size();i++)
							{
							Option option = new Option();
							option.setOptions(questionsOptionsList.get(0).getOptions().get(i).getOption());
							option.setHasRemarks(String.valueOf(questionsOptionsList.get(0).getOptions().get(i).getHasRemark()));
							if(questionsOptionsList.get(0).getOptions().get(i).getHasSubQuestion()!= null && questionsOptionsList.get(0).getOptions().get(i).getHasSubQuestion() == true)
							{
							option.setSubOptionName(questionsOptionsList.get(0).getOptions().get(i).getSubquestion());
							option.setOptionType(optionTypeDAO.get(new Long(questionsOptionsList.get(0).getOptions().get(i).getSubquestionType())));
							}
							
							option = optionDAO.save(option);
							
							if(questionsOptionsList.get(0).getOptions().get(i).getHasSubQuestion()!= null && questionsOptionsList.get(0).getOptions().get(i).getHasSubQuestion() == true)
							{
								if(questionsOptionsList.get(0).getOptions().get(i).getSubOptionList() != null && questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().size() > 0)
								{
								for(int j=0;j< questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().size();j++)
								{
									
									Option option1 = new Option();
									option1.setParentOption(option);
									option1.setOptions(questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().get(j).getOption());
									option1.setHasRemarks(String.valueOf(questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().get(j).getHasRemark()));
									
									option1 = optionDAO.save(option1);
									}
								}	
							}
							QuestionOptions	questionOptions = new QuestionOptions();
							questionOptions.setSurveyQuestion(surveyQuestion);
							questionOptions.setOptions(option);
							questionOptionsDAO.save(questionOptions);
							rs.setResultCode(ResultCodeMapper.SUCCESS);
						}
							
					}
				
				
			}
		}
		catch (Exception e) {
			Log.error("Exception Occured in saveQuestion() method - Exception",e);
		}
						return rs;
					}
				});

		return resultStatus;
	}
	
	public ResultStatus saveQuestionForMultipleText(final List<QuestionsOptionsVO> questionsOptionsList)
	{
		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {

						ResultStatus rs = new ResultStatus();

						try {
			if(questionsOptionsList!= null && questionsOptionsList.size() > 0)
			{
			SurveyQuestion surveyQuestion = new SurveyQuestion();
			surveyQuestion.setQuestion(questionsOptionsList.get(0).getQuestion());
			surveyQuestion.setOptionType(optionTypeDAO.get(new Long(questionsOptionsList.get(0).getQuestionType())));
			surveyQuestion.setHasRemarks(String.valueOf(questionsOptionsList.get(0).getHasRemark()));
			surveyQuestion = surveyQuestionDAO.save(surveyQuestion);
			if(questionsOptionsList.get(0).getOptions() != null && questionsOptionsList.get(0).getOptions().size() > 0)
			{
					for(int i=0;i<questionsOptionsList.get(0).getOptions().size();i++)
					{
					Option option = new Option();
					option.setOptions(questionsOptionsList.get(0).getOptions().get(i).getOption());
					option = optionDAO.save(option);
					
					if(questionsOptionsList.get(0).getOptions().get(i).getSubOptionList() != null && questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().size() > 0)
					{
					for(int j=0;j< questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().size();j++)
						{
						Option option1 = new Option();
						option1.setParentOption(option);
						option1.setSubOptionName(questionsOptionsList.get(0).getOptions().get(i).getSubOptionList().get(j).getSubquestion());
						option1 = optionDAO.save(option1);
						}
					}	
					
					QuestionOptions	questionOptions = new QuestionOptions();
					questionOptions.setSurveyQuestion(surveyQuestion);
					questionOptions.setOptions(option);
					questionOptionsDAO.save(questionOptions);	
					rs.setResultCode(ResultCodeMapper.SUCCESS);
					}
					
			}
			
					
		}
						
	}
			catch (Exception e) {
				Log.error("Exception Occured in saveQuestion() method - Exception",e);
			}
							return rs;
						}
					});

			return resultStatus;
		}
}
