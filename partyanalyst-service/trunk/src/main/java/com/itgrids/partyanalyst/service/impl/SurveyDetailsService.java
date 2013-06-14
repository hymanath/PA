package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ISurveyAccessUsersDAO;
import com.itgrids.partyanalyst.dao.ISurveyDAO;
import com.itgrids.partyanalyst.dao.IUpdationDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.SurveyAccessUsers;
import com.itgrids.partyanalyst.model.UpdationDetails;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class SurveyDetailsService implements ISurveyDetailsService {

	private static final Logger log = Logger.getLogger(SurveyDetailsService.class);
	private ISurveyDAO surveyDAO;
	private TransactionTemplate transactionTemplate;
	private IUpdationDetailsDAO updationDetailsDAO;
	private IUserDAO userDAO;
	private ISurveyAccessUsersDAO surveyAccessUsersDAO;
	private DateUtilService dateUtilService = new DateUtilService();

	
	public ISurveyAccessUsersDAO getSurveyAccessUsersDAO() {
		return surveyAccessUsersDAO;
	}

	public void setSurveyAccessUsersDAO(ISurveyAccessUsersDAO surveyAccessUsersDAO) {
		this.surveyAccessUsersDAO = surveyAccessUsersDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ISurveyDAO getSurveyDAO() {
		return surveyDAO;
	}
	
	public void setSurveyDAO(ISurveyDAO surveyDAO) {
		this.surveyDAO = surveyDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IUpdationDetailsDAO getUpdationDetailsDAO() {
		return updationDetailsDAO;
	}

	public void setUpdationDetailsDAO(IUpdationDetailsDAO updationDetailsDAO) {
		this.updationDetailsDAO = updationDetailsDAO;
	}

	public List<SelectOptionVO> getAllSurveys(){
		List<SelectOptionVO> listOfSurveys = null; 
		try{
			log.info("Entered into getAllRegisterUsersForAssigningParty()");
			List<Object[]> surveyList = surveyDAO.getAllSurveys();
			if(surveyList != null && surveyList.size() > 0)
			{
				listOfSurveys = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(Object[] params : surveyList)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					listOfSurveys.add(selectOptionVO);
				}
			}
			
			return listOfSurveys;
		}catch (Exception e) {
			log.error("Exception Occured in getAllRegisterUsersForAssigningParty(), Exception - "+e);
			e.printStackTrace();
			return  null;
		}
	}
	
	public ResultStatus saveSurveyDetails(final Long userId,final Long surveyId){
		ResultStatus resultStatus = new ResultStatus();
		try{
			log.debug("Entered into saveSurveyDetails() method in Survey Details Service()");
			
			Long value = surveyAccessUsersDAO.checkForDuplicateRecords(userId,surveyId);
			if(value == 0){
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					UpdationDetails updationDetails = new UpdationDetails();
					updationDetails.setCreatedBy(userDAO.get(userId));
					updationDetails.setUpdatedBy(userDAO.get(userId));
					updationDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					updationDetails.setLastUpdatedTime(dateUtilService.getCurrentDateAndTime());
					updationDetails = updationDetailsDAO.save(updationDetails);
					SurveyAccessUsers surveyAccessUsers = new SurveyAccessUsers();
						surveyAccessUsers.setUser(userDAO.get(userId));
						surveyAccessUsers.setSurvey(surveyDAO.get(surveyId));
						surveyAccessUsers.setUpdationDetails(updationDetails);
						surveyAccessUsersDAO.save(surveyAccessUsers);
				}
			});
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}else{
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			}
			return resultStatus;
		}catch (Exception e) {
			log.error("Exception encountered, Check log for Details - ",e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}	
	}
	
}
