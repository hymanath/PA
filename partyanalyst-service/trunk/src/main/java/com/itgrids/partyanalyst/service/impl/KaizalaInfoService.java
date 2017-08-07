package com.itgrids.partyanalyst.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.hsqldb.lib.HashMap;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IKaizalaActionsDAO;
import com.itgrids.partyanalyst.dao.IKaizalaAnswerInfoDAO;
import com.itgrids.partyanalyst.dao.IKaizalaAnswersDAO;
import com.itgrids.partyanalyst.dao.IKaizalaQuestionsDAO;
import com.itgrids.partyanalyst.dao.IKaizalaResponderInfoDAO;
import com.itgrids.partyanalyst.model.KaizalaAnswerInfo;
import com.itgrids.partyanalyst.model.KaizalaAnswers;
import com.itgrids.partyanalyst.model.KaizalaResponderInfo;
import com.itgrids.partyanalyst.service.IKaizalaInfoService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class KaizalaInfoService implements IKaizalaInfoService{
	private TransactionTemplate transactionTemplate = null;
	private static final Logger LOG = Logger.getLogger(KaizalaInfoService.class);
	private IKaizalaActionsDAO kaizalaActionsDAO;
	private IKaizalaResponderInfoDAO kaizalaResponderInfoDAO;
	private IKaizalaAnswerInfoDAO kaizalaAnswerInfoDAO; 
	private IKaizalaQuestionsDAO kaizalaQuestionsDAO;
	private IKaizalaAnswersDAO kaizalaAnswersDAO;
	
	
	public IKaizalaAnswersDAO getKaizalaAnswersDAO() {
		return kaizalaAnswersDAO;
	}


	public void setKaizalaAnswersDAO(IKaizalaAnswersDAO kaizalaAnswersDAO) {
		this.kaizalaAnswersDAO = kaizalaAnswersDAO;
	}


	public IKaizalaQuestionsDAO getKaizalaQuestionsDAO() {
		return kaizalaQuestionsDAO;
	}


	public void setKaizalaQuestionsDAO(IKaizalaQuestionsDAO kaizalaQuestionsDAO) {
		this.kaizalaQuestionsDAO = kaizalaQuestionsDAO;
	}


	public IKaizalaAnswerInfoDAO getKaizalaAnswerInfoDAO() {
		return kaizalaAnswerInfoDAO;
	}


	public void setKaizalaAnswerInfoDAO(IKaizalaAnswerInfoDAO kaizalaAnswerInfoDAO) {
		this.kaizalaAnswerInfoDAO = kaizalaAnswerInfoDAO;
	}


	public IKaizalaResponderInfoDAO getKaizalaResponderInfoDAO() {
		return kaizalaResponderInfoDAO;
	}


	public void setKaizalaResponderInfoDAO(
			IKaizalaResponderInfoDAO kaizalaResponderInfoDAO) {
		this.kaizalaResponderInfoDAO = kaizalaResponderInfoDAO;
	}


	public IKaizalaActionsDAO getKaizalaActionsDAO() {
		return kaizalaActionsDAO;
	}


	public void setKaizalaActionsDAO(IKaizalaActionsDAO kaizalaActionsDAO) {
		this.kaizalaActionsDAO = kaizalaActionsDAO;
	}


	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}


	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}


	public void saveKaizalAnswerInfo(final String output){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					
					if(output != null && !output.isEmpty()){
						DateUtilService dateUtilService = new DateUtilService();
						JSONObject jsonObj = new JSONObject(output);
						
						JSONObject dataObj = jsonObj.getJSONObject("data");
						
						List<Long>  kaizalaActionsIds = kaizalaActionsDAO.getKaizalaActionId(dataObj.getString("actionId"));
						if(kaizalaActionsIds != null && kaizalaActionsIds.size() > 0){
							KaizalaAnswerInfo kaiAnsInfo = new KaizalaAnswerInfo();
							kaiAnsInfo.setGroupId(dataObj.getString("groupId"));
							kaiAnsInfo.setEventType(jsonObj.getString("eventType"));
							kaiAnsInfo.setEventId(jsonObj.getString("eventId"));
							
							kaiAnsInfo.setKaizalaActionsId(kaizalaActionsIds != null && kaizalaActionsIds.size() > 0 ? kaizalaActionsIds.get(kaizalaActionsIds.size()-1) : null);
							kaiAnsInfo.setResponseId(dataObj.getString("responseId"));
							
							List<Long> resIds = kaizalaResponderInfoDAO.getRespondentId(dataObj.getString("responder"));
							
							if(resIds != null && resIds.size() > 0){
								kaiAnsInfo.setKaizalaResponderInfoId(resIds.get(resIds.size()-1));
							}else{
								KaizalaResponderInfo kri = new KaizalaResponderInfo();
								kri.setMobileNumber(dataObj.getString("responder"));
								kri.setIsDeleted("N");
								kri = kaizalaResponderInfoDAO.save(kri);
								kaiAnsInfo.setKaizalaResponderInfoId(kri.getKaizalaResponderInfoId());
							}
							
							if(dataObj.getJSONObject("responseDetails") != null){
								JSONObject inObj1 = dataObj.getJSONObject("responseDetails");
								JSONArray inObjArr = inObj1.getJSONArray("responseWithQuestions");
								if(inObjArr != null && inObjArr.length() > 0){
									for (int i = 0; i < inObjArr.length(); i++) {
										JSONObject obj = (JSONObject)inObjArr.get(i);
										if(obj.getString("title").equalsIgnoreCase("Responder Location") && obj.getString("type").equalsIgnoreCase("location")){
											JSONObject answerObj = obj.getJSONObject("answer");
											kaiAnsInfo.setLangitude(answerObj.getString("lg"));
											kaiAnsInfo.setLattitude(answerObj.getString("lt"));
											kaiAnsInfo.setAddress(answerObj.getString("n"));
										}
										if(obj.getString("title").equalsIgnoreCase("ResponseTime") && obj.getString("type").equalsIgnoreCase("DateTime")){
											kaiAnsInfo.setResponseTime(obj.getString("answer"));
										}
									}
								}
							}
							kaiAnsInfo.setIsDeleted("N");
							
							kaiAnsInfo = kaizalaAnswerInfoDAO.save(kaiAnsInfo);
							
							List<Object[]> objList = kaizalaQuestionsDAO.getQuestionsForKizalaActionId(kaiAnsInfo.getKaizalaActionsId());
							Map<String,Long> questionsMap = new java.util.HashMap<String, Long>(0);
							if(objList != null && objList.size() > 0){
								for (Object[] objects : objList) {
									questionsMap.put(objects[1].toString().trim(), (Long)objects[0]);
								}
							}
							
							if(dataObj.getJSONObject("responseDetails") != null){
								JSONObject inObj1 = dataObj.getJSONObject("responseDetails");
								JSONArray inObjArr = inObj1.getJSONArray("responseWithQuestions");
								if(inObjArr != null && inObjArr.length() > 0){
									for (int i = 0; i < inObjArr.length()-3; i++) {
										JSONObject obj = (JSONObject)inObjArr.get(i);
										if(obj.getString("type").equalsIgnoreCase("text") || obj.getString("type").equalsIgnoreCase("Numeric") || obj.getString("type").equalsIgnoreCase("Image")){
											KaizalaAnswers answer = new KaizalaAnswers();
											answer.setKaizalaQuestionsId(questionsMap.get(obj.getString("title")) != null ? questionsMap.get(obj.getString("title")):null);
											answer.setAnswer(obj.getString("answer"));
											answer.setEventId(kaiAnsInfo.getEventId());
											answer.setKaizalaAnswerInfoId(kaiAnsInfo.getKaizalaAnswerInfoId());
											answer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
											kaizalaAnswersDAO.save(answer);
										}else if(obj.getString("type").equalsIgnoreCase("SingleOption") || obj.getString("type").equalsIgnoreCase("MultiOption")){
											if(obj.getJSONArray("answer") != null && obj.getJSONArray("answer").length() > 0){
												JSONArray ansArr = obj.getJSONArray("answer");
												for (int j = 0; j < ansArr.length(); j++) {
													KaizalaAnswers answer = new KaizalaAnswers();
													answer.setKaizalaQuestionsId(questionsMap.get(obj.getString("title")) != null ? questionsMap.get(obj.getString("title")):null);
													answer.setAnswer(ansArr.get(j).toString());
													answer.setEventId(kaiAnsInfo.getEventId());
													answer.setKaizalaAnswerInfoId(kaiAnsInfo.getKaizalaAnswerInfoId());
													answer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
													kaizalaAnswersDAO.save(answer);
												}
											}
										}
									}
								}
							}
						}
					}
				} catch (Exception e) {
					LOG.error("Exception raised at saveKaizalAnswerInfo", e);
				}		
						
			}
		});
		
	}
}
