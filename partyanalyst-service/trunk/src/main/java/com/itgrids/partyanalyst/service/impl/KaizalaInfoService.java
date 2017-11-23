package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IKaizalaActionTypeDAO;
import com.itgrids.partyanalyst.dao.IKaizalaActionsDAO;
import com.itgrids.partyanalyst.dao.IKaizalaAnswerInfoDAO;
import com.itgrids.partyanalyst.dao.IKaizalaAnswersDAO;
import com.itgrids.partyanalyst.dao.IKaizalaEventsDAO;
import com.itgrids.partyanalyst.dao.IKaizalaEventsResponseDAO;
import com.itgrids.partyanalyst.dao.IKaizalaGroupDocumentDAO;
import com.itgrids.partyanalyst.dao.IKaizalaGroupDocumentTypeDAO;
import com.itgrids.partyanalyst.dao.IKaizalaGroupResponderRelationDAO;
import com.itgrids.partyanalyst.dao.IKaizalaGroupTypeDAO;
import com.itgrids.partyanalyst.dao.IKaizalaGroupsDAO;
import com.itgrids.partyanalyst.dao.IKaizalaInstallationTrackingDAO;
import com.itgrids.partyanalyst.dao.IKaizalaJobResponseDAO;
import com.itgrids.partyanalyst.dao.IKaizalaOptionsDAO;
import com.itgrids.partyanalyst.dao.IKaizalaPropertiesDAO;
import com.itgrids.partyanalyst.dao.IKaizalaQuestionsDAO;
import com.itgrids.partyanalyst.dao.IKaizalaResponderInfoDAO;
import com.itgrids.partyanalyst.dao.IKaizalaResponderTypeDAO;
import com.itgrids.partyanalyst.dao.IKaizalaTextMessageDAO;
import com.itgrids.partyanalyst.dao.hibernate.KaizalaInstallationTrackingDAO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.model.KaizalaActions;
import com.itgrids.partyanalyst.model.KaizalaAnswerInfo;
import com.itgrids.partyanalyst.model.KaizalaAnswers;
import com.itgrids.partyanalyst.model.KaizalaEventsResponse;
import com.itgrids.partyanalyst.model.KaizalaGroupDocument;
import com.itgrids.partyanalyst.model.KaizalaGroupDocumentType;
import com.itgrids.partyanalyst.model.KaizalaGroupResponderRelation;
import com.itgrids.partyanalyst.model.KaizalaGroups;
import com.itgrids.partyanalyst.model.KaizalaInstallationTracking;
import com.itgrids.partyanalyst.model.KaizalaJobResponse;
import com.itgrids.partyanalyst.model.KaizalaOptions;
import com.itgrids.partyanalyst.model.KaizalaProperties;
import com.itgrids.partyanalyst.model.KaizalaQuestions;
import com.itgrids.partyanalyst.model.KaizalaResponderInfo;
import com.itgrids.partyanalyst.model.KaizalaTextMessage;
import com.itgrids.partyanalyst.service.IKaizalaInfoService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class KaizalaInfoService implements IKaizalaInfoService{
	private TransactionTemplate transactionTemplate = null;
	private static final Logger LOG = Logger.getLogger(KaizalaInfoService.class);
	private IKaizalaActionsDAO kaizalaActionsDAO;
	private IKaizalaResponderInfoDAO kaizalaResponderInfoDAO;
	private IKaizalaAnswerInfoDAO kaizalaAnswerInfoDAO; 
	private IKaizalaQuestionsDAO kaizalaQuestionsDAO;
	private IKaizalaAnswersDAO kaizalaAnswersDAO;
	private IKaizalaGroupsDAO kaizalaGroupsDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	private IKaizalaOptionsDAO kaizalaOptionsDAO;
	private IKaizalaActionTypeDAO kaizalaActionTypeDAO;
	private IKaizalaEventsResponseDAO kaizalaEventsResponseDAO;
	private IKaizalaGroupTypeDAO kaizalaGroupTypeDAO;
	private IKaizalaEventsDAO kaizalaEventsDAO;
	private IKaizalaPropertiesDAO kaizalaPropertiesDAO;
	private IKaizalaGroupResponderRelationDAO kaizalaGroupResponderRelationDAO ;
	private IKaizalaTextMessageDAO kaizalaTextMessageDAO;
	private IKaizalaJobResponseDAO kaizalaJobResponseDAO;
	private IKaizalaGroupDocumentDAO kaizalaGroupDocumentDAO;
	private IKaizalaGroupDocumentTypeDAO kaizalaGroupDocumentTypeDAO;
	private IKaizalaResponderTypeDAO kaizalaResponderTypeDAO;
	private IKaizalaInstallationTrackingDAO kaizalaInstallationTrackingDAO;


	public void setKaizalaInstallationTrackingDAO(
			IKaizalaInstallationTrackingDAO kaizalaInstallationTrackingDAO) {
		this.kaizalaInstallationTrackingDAO = kaizalaInstallationTrackingDAO;
	}
	public IKaizalaResponderTypeDAO getKaizalaResponderTypeDAO() {
		return kaizalaResponderTypeDAO;
	}
	public void setKaizalaResponderTypeDAO(
			IKaizalaResponderTypeDAO kaizalaResponderTypeDAO) {
		this.kaizalaResponderTypeDAO = kaizalaResponderTypeDAO;
	}
	public IKaizalaJobResponseDAO getKaizalaJobResponseDAO() {
		return kaizalaJobResponseDAO;
	}
	public void setKaizalaJobResponseDAO(
			IKaizalaJobResponseDAO kaizalaJobResponseDAO) {
		this.kaizalaJobResponseDAO = kaizalaJobResponseDAO;
	}
	public IKaizalaGroupDocumentDAO getKaizalaGroupDocumentDAO() {
		return kaizalaGroupDocumentDAO;
	}
	public void setKaizalaGroupDocumentDAO(
			IKaizalaGroupDocumentDAO kaizalaGroupDocumentDAO) {
		this.kaizalaGroupDocumentDAO = kaizalaGroupDocumentDAO;
	}
	public IKaizalaGroupDocumentTypeDAO getKaizalaGroupDocumentTypeDAO() {
		return kaizalaGroupDocumentTypeDAO;
	}
	public void setKaizalaGroupDocumentTypeDAO(
			IKaizalaGroupDocumentTypeDAO kaizalaGroupDocumentTypeDAO) {
		this.kaizalaGroupDocumentTypeDAO = kaizalaGroupDocumentTypeDAO;
	}
	public IKaizalaTextMessageDAO getKaizalaTextMessageDAO() {
		return kaizalaTextMessageDAO;
	}
	public void setKaizalaTextMessageDAO(
			IKaizalaTextMessageDAO kaizalaTextMessageDAO) {
		this.kaizalaTextMessageDAO = kaizalaTextMessageDAO;
	}
	public IKaizalaGroupResponderRelationDAO getKaizalaGroupResponderRelationDAO() {
		return kaizalaGroupResponderRelationDAO;
	}
	public void setKaizalaGroupResponderRelationDAO(
			IKaizalaGroupResponderRelationDAO kaizalaGroupResponderRelationDAO) {
		this.kaizalaGroupResponderRelationDAO = kaizalaGroupResponderRelationDAO;
	}
	public IKaizalaPropertiesDAO getKaizalaPropertiesDAO() {
		return kaizalaPropertiesDAO;
	}
	public void setKaizalaPropertiesDAO(IKaizalaPropertiesDAO kaizalaPropertiesDAO) {
		this.kaizalaPropertiesDAO = kaizalaPropertiesDAO;
	}
	public IKaizalaEventsResponseDAO getKaizalaEventsResponseDAO() {
		return kaizalaEventsResponseDAO;
	}
	public void setKaizalaEventsResponseDAO(
			IKaizalaEventsResponseDAO kaizalaEventsResponseDAO) {
		this.kaizalaEventsResponseDAO = kaizalaEventsResponseDAO;
	}
	public IKaizalaGroupTypeDAO getKaizalaGroupTypeDAO() {
		return kaizalaGroupTypeDAO;
	}
	public void setKaizalaGroupTypeDAO(IKaizalaGroupTypeDAO kaizalaGroupTypeDAO) {
		this.kaizalaGroupTypeDAO = kaizalaGroupTypeDAO;
	}
	public IKaizalaActionTypeDAO getKaizalaActionTypeDAO() {
		return kaizalaActionTypeDAO;
	}
	public void setKaizalaActionTypeDAO(IKaizalaActionTypeDAO kaizalaActionTypeDAO) {
		this.kaizalaActionTypeDAO = kaizalaActionTypeDAO;
	}
	public IKaizalaOptionsDAO getKaizalaOptionsDAO() {
		return kaizalaOptionsDAO;
	}
	public void setKaizalaOptionsDAO(IKaizalaOptionsDAO kaizalaOptionsDAO) {
		this.kaizalaOptionsDAO = kaizalaOptionsDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public IKaizalaGroupsDAO getKaizalaGroupsDAO() {
		return kaizalaGroupsDAO;
	}
	public void setKaizalaGroupsDAO(IKaizalaGroupsDAO kaizalaGroupsDAO) {
		this.kaizalaGroupsDAO = kaizalaGroupsDAO;
	}
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

	public IKaizalaEventsDAO getKaizalaEventsDAO() {
		return kaizalaEventsDAO;
	}
	public void setKaizalaEventsDAO(IKaizalaEventsDAO kaizalaEventsDAO) {
		this.kaizalaEventsDAO = kaizalaEventsDAO;
	}
	
	public void saveKaizalAnswerInfo(final String output){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					
					if(output != null && !output.isEmpty()){
						KaizalaEventsResponse eventRes = new KaizalaEventsResponse();
						Long eventResId=0l;
						DateUtilService dateUtilService = new DateUtilService();
						JSONObject jsonObj = new JSONObject(output);
						
						
						List<Long> resId=kaizalaResponderInfoDAO.getRespondentId(jsonObj.getString("fromUser"));
						if(resId!=null && resId.size()>0){
							eventRes.setInsertedBy(resId.get(0));
							eventRes.setUpdatedBy(resId.get(0));
							eventRes.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							eventRes.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							eventRes.setIsDeleted("N");
						}
						eventRes.setEventId(jsonObj.getString("eventId"));
						Long eventId = kaizalaEventsDAO.getEventId(jsonObj.getString("eventType"));
						if(eventId!=null){
							eventRes.setKaizalaEventsId(eventId);
						}
						JSONObject dataObj = jsonObj.getJSONObject("data");
						
						List<Long> ids = kaizalaGroupsDAO.checkGroupExistence(dataObj.getString("groupId"));
						Long id = null;
						if(ids == null || ids.size() == 0l){
							KaizalaGroups kg = new KaizalaGroups();
							
							kg.setGroupId(dataObj.getString("groupId"));
							kg.setIsDeleted("N");
							kg.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							id = kaizalaGroupsDAO.save(kg).getKaizalaGroupsId();
						}else{
							id = ids.get(ids.size()-1);
						}
						eventRes.setGroupId(dataObj.getString("groupId"));
						eventRes.setKaizalaGroupsId(id);
						boolean newAction = false;
						List<Long>  kaizalaActionsIds = kaizalaActionsDAO.getKaizalaActionId(dataObj.getString("actionId"));
						if(kaizalaActionsIds == null || kaizalaActionsIds.size() == 0){
							newAction = true;
							KaizalaActions ka = new KaizalaActions();
							ka.setActionId(dataObj.getString("actionId"));
							ka.setActionPackegeId(dataObj.has("actionPackageId")?dataObj.getString("actionPackageId"):null);
							List<String> list = getActionDetails(dataObj.getString("groupId"),dataObj.getString("actionId"));
							//0-title,1-actiontype,2-acceptmultires,3-expirydate
							if(list != null && list.size() > 0){
								ka.setTitle(list.get(0));
								ka.setActionType(list.get(1).trim().isEmpty()?"Custom Action":list.get(1).trim());
								ka.setKaizalaActionTypeId(kaizalaActionTypeDAO.getActionTypeId(ka.getActionType()));
								ka.setAcceptMultiresponse(list.get(2));
								if(!list.get(3).equals(""))
									ka.setExpiryDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(list.get(3)));
							}
							ka.setKaizalaGroupsId(id);
							ka.setIsDeleted("N");
							ka.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							kaizalaActionsIds.add(kaizalaActionsDAO.save(ka).getKaizalaActionsId());
						}
						if(kaizalaActionsIds != null && kaizalaActionsIds.size() > 0){
							KaizalaAnswerInfo kaiAnsInfo = new KaizalaAnswerInfo();
							kaiAnsInfo.setKaizalaGroupsId(id);
							kaiAnsInfo.setEventType(jsonObj.getString("eventType"));
							kaiAnsInfo.setEventId(jsonObj.getString("eventId"));
							
							kaiAnsInfo.setKaizalaActionsId(kaizalaActionsIds != null && kaizalaActionsIds.size() > 0 ? kaizalaActionsIds.get(kaizalaActionsIds.size()-1) : null);
							kaiAnsInfo.setResponseId(dataObj.getString("responseId"));
							
							List<Long> resIds = kaizalaResponderInfoDAO.getRespondentId(dataObj.getString("responder"));
							eventRes.setActionId(dataObj.getString("actionId"));
							eventRes.setKaizalaActionsId(kaizalaActionsIds.get(0));
							eventResId = kaizalaEventsResponseDAO.save(eventRes).getKaizalaEventsResponseId();
							if(resIds != null && resIds.size() > 0){
								kaiAnsInfo.setKaizalaResponderInfoId(resIds.get(resIds.size()-1));
							}else{
								KaizalaResponderInfo kri = new KaizalaResponderInfo();
								kri.setMobileNumber(dataObj.getString("responder"));
								kri.setIsDeleted("N");
								kri = kaizalaResponderInfoDAO.save(kri);
								kaiAnsInfo.setKaizalaResponderInfoId(kri.getKaizalaResponderInfoId());
							}
							List<KeyValueVO> newQuestionsList = new ArrayList<KeyValueVO>(0);
							if(dataObj.getJSONObject("responseDetails") != null){
								JSONObject inObj1 = dataObj.getJSONObject("responseDetails");
								JSONArray inObjArr = inObj1.getJSONArray("responseWithQuestions");
								if(inObjArr != null && inObjArr.length() > 0){
									for (int i = 0; i < inObjArr.length(); i++) {
										JSONObject obj = (JSONObject)inObjArr.get(i);
										if(newAction){
											KeyValueVO vo = new KeyValueVO();
											vo.setName(obj.getString("title"));
											vo.setDate(obj.getString("type"));
											if(obj.getJSONArray("options") != null && obj.getJSONArray("options").length() > 0){
												for(int t=0;t<obj.getJSONArray("options").length();t++){
													vo.getImageList().add(obj.getJSONArray("options").getJSONObject(t).getString("title"));
												}
											}
											newQuestionsList.add(vo);
										}
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
							
							if(newAction && newQuestionsList != null && newQuestionsList.size() > 0){
								for (KeyValueVO keyValueVO : newQuestionsList) {
									KaizalaQuestions kq = new KaizalaQuestions();
									kq.setKaizalaActionsId(kaizalaActionsIds != null && kaizalaActionsIds.size() > 0 ? kaizalaActionsIds.get(kaizalaActionsIds.size()-1):null);
									kq.setQuestion(keyValueVO.getName());
									kq.setType(keyValueVO.getDate());
									kq.setIsDeleted("N");
									kq = kaizalaQuestionsDAO.save(kq);
									
									if(keyValueVO .getImageList() != null && keyValueVO.getImageList().size() > 0){
										for (String option : keyValueVO.getImageList()) {
											KaizalaOptions kaiOpt = new KaizalaOptions();
											kaiOpt.setKaizalaQuestionsId(kq.getKaizalaQuestionsId());
											kaiOpt.setTitle(option);
											kaizalaOptionsDAO.save(kaiOpt);
										}
									}
								}
							}
							
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
											answer.setKaizalaEventsResponseId(eventResId);
											kaizalaAnswersDAO.save(answer);
										}else if(obj.getString("type").equalsIgnoreCase("SingleOption") || obj.getString("type").equalsIgnoreCase("MultiOption")){
											if(obj.getJSONArray("answer") != null && obj.getJSONArray("answer").length() > 0){
												JSONArray ansArr = obj.getJSONArray("answer");
												for (int j = 0; j < ansArr.length(); j++) {
													KaizalaAnswers answer = new KaizalaAnswers();
													answer.setKaizalaQuestionsId(questionsMap.get(obj.getString("title")) != null && questionsMap.get(obj.getString("title")) != null ? questionsMap.get(obj.getString("title")):null);
													answer.setAnswer(ansArr.get(j).toString());
													if(answer.getKaizalaQuestionsId() != null && answer.getKaizalaQuestionsId() > 0l){
														Long optionId = kaizalaOptionsDAO.getOptionId(answer.getKaizalaQuestionsId(),answer.getAnswer());
														if(optionId != null && optionId > 0l)
															answer.setKaizalaOptionsId(optionId);
													}
													answer.setEventId(kaiAnsInfo.getEventId());
													answer.setKaizalaAnswerInfoId(kaiAnsInfo.getKaizalaAnswerInfoId());
													answer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
													answer.setKaizalaEventsResponseId(eventResId);
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
	
	public void saveKaizalaInstallationTracking(JSONObject jobj){
		try {
			
			KaizalaInstallationTracking tracking = new KaizalaInstallationTracking();
			
			tracking.setObjectId(jobj.getString("objectId"));
			tracking.setObjectType(jobj.getString("objectType"));
			tracking.setEventId(jobj.getString("eventId"));
			tracking.setEventType(jobj.getString("eventType"));
			
			if(jobj !=null){
				
				JSONObject dataObj = jobj.getJSONObject("data");
				if(dataObj !=null){
					
					tracking.setResponderName(dataObj.getString("responder"));
					tracking.setResponderMobileNo(dataObj.getString("responderName"));
					
					JSONObject responseObj = dataObj.getJSONObject("responseDetails");
					if(responseObj !=null){
						JSONArray resonseArray  = responseObj.getJSONArray("responseWithQuestions");
						if(resonseArray !=null && resonseArray.length()>0){
							
							JSONObject mobileObj = (JSONObject)resonseArray.get(1);							
							if(mobileObj !=null)
								tracking.setInstalledMobileNo(mobileObj.getString("answer"));
							
							JSONObject nameObj = (JSONObject)resonseArray.get(2);							
							if(nameObj !=null)
								tracking.setName(nameObj.getString("answer"));
							
							JSONObject urlObj = (JSONObject)resonseArray.get(3);							
							if(urlObj !=null)
								tracking.setUrl(urlObj.getString("answer"));
							
							JSONObject locationObj = (JSONObject)resonseArray.get(4);							
							if(locationObj !=null){
								JSONObject locationInnerObj  = locationObj.getJSONObject("answer");
								tracking.setLocation(locationInnerObj.getString("n"));
							}
						}
					}
				}
				
				tracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
				kaizalaInstallationTrackingDAO.save(tracking);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveKaizalaInstallationTracking in KaizalaInfoService Class ", e);
		}
	}
	
	public void saveEventResponses(final String output){
		try {
			
			if(output != null && !output.isEmpty()){
				JSONObject jsonObj = new JSONObject(output);
				
				if(jsonObj.getString("objectType").equalsIgnoreCase("Group") 
						&& jsonObj.getString("objectId").equalsIgnoreCase("034607bb-6d16-4819-8ac8-32f22043e4d5")){
					saveKaizalaInstallationTracking(jsonObj);
					return;
				}
				
				
				if(jsonObj.getString("eventType").equalsIgnoreCase("TextMessageCreated")){
					saveTextMsgCreated(output);
				}else if(jsonObj.getString("eventType").equalsIgnoreCase("JobCreated")){
					saveJobCreated(output);
				}else if(jsonObj.getString("eventType").equalsIgnoreCase("AttachmentCreated")){
					saveAttachmentInfo(output);
				}else if(jsonObj.getString("eventType").equalsIgnoreCase("SurveyCreated")||jsonObj.getString("eventType").equalsIgnoreCase("ActionCreated")){
					saveSurveyCreatedInfo(output);
				}else if(jsonObj.getString("eventType").equalsIgnoreCase("SurveyResponse")||jsonObj.getString("eventType").equalsIgnoreCase("ActionResponse")){
					saveKaizalAnswerInfo(output);
				}else if(jsonObj.getString("eventType").equalsIgnoreCase("JobResponse")){
					saveJobResponseInfo(output);
				}else if(jsonObj.getString("eventType").equalsIgnoreCase("GroupAdded")){
					saveSubGroupAddedEvent(output);
				}else if(jsonObj.getString("eventType").equalsIgnoreCase("GroupRemoved")){
					saveSubGroupRemovedEvent(output);
				}else if(jsonObj.getString("eventType").equalsIgnoreCase("MemberAdded")){
					saveMemberAddedInfo(output);
				}else if(jsonObj.getString("eventType").equalsIgnoreCase("MemberRemoved")){
					updateMemberRemovedInfo(output);
				}
			}
		}catch (Exception e) {
			LOG.error("Exception raised at saveEventResponses", e);
		}
	}
	
	public void saveTextMsgCreated(final String output){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					if(output != null && !output.isEmpty()){
						KaizalaEventsResponse KER = new KaizalaEventsResponse(); 
						DateUtilService dateService = new DateUtilService();
						JSONObject jsonObj = new JSONObject(output);
						JSONObject dataObj = jsonObj.getJSONObject("data");
							List<Long> ids = kaizalaGroupsDAO.checkGroupExistence(jsonObj.getString("objectId"));
							Long id = null;
							if(ids == null || ids.size() == 0l){
								Long groupTypeId = kaizalaGroupTypeDAO.checkGroupTypeExistence(jsonObj.getString("objectType"));
								KaizalaGroups kg = new KaizalaGroups();
								kg.setGroupId(jsonObj.getString("objectId"));
								kg.setIsDeleted("N");
								kg.setInsertedTime(dateService.getCurrentDateAndTime());
								kg.setKaizalaGroupTypeId(groupTypeId);
								id = kaizalaGroupsDAO.save(kg).getKaizalaGroupsId();
							}else{
								id = ids.get(ids.size()-1);
							}
							KER.setGroupId(jsonObj.getString("objectId"));
							KER.setKaizalaGroupsId(id);
							
							List<Long> resIds = kaizalaResponderInfoDAO.getRespondentId(jsonObj.getString("fromUser"));
							if(resIds != null && resIds.size() > 0){
								KER.setInsertedBy(resIds.get(0));
								KER.setUpdatedBy(resIds.get(0));
							}else{
								KaizalaResponderInfo kri = new KaizalaResponderInfo();
								kri.setMobileNumber(jsonObj.getString("fromUser"));
								kri.setIsDeleted("N");
								kri = kaizalaResponderInfoDAO.save(kri);
								
								KaizalaGroupResponderRelation kgrr = new KaizalaGroupResponderRelation();
								kgrr.setGroupId(jsonObj.getString("objectId"));
								kgrr.setKaizalaGroupsId(id);
								kgrr.setKaizalaResponderInfoId(kri.getKaizalaResponderInfoId());
								kgrr.setIsDeleted("N");
								kaizalaGroupResponderRelationDAO.save(kgrr);
								
								KER.setInsertedBy(kri.getKaizalaResponderInfoId());
								KER.setUpdatedBy(kri.getKaizalaResponderInfoId());
							}
							KER.setKaizalaEventsId(7l);
							KER.setEventId(jsonObj.getString("eventId"));
							KER.setInsertedTime(dateService.getCurrentDateAndTime());
							KER.setUpdatedTime(dateService.getCurrentDateAndTime());
							KER.setIsDeleted("N");
							KER = kaizalaEventsResponseDAO.save(KER);
							
							KaizalaTextMessage ktm = new KaizalaTextMessage();
							ktm.setKaizalaEventsResponseId(KER.getKaizalaEventsResponseId());
							ktm.setTextMessage(dataObj.getString("text"));
							kaizalaTextMessageDAO.save(ktm);
						}
				} catch (Exception e) {
					LOG.error("Exception raised at saveTextMsgCreated", e);
				}		
			}
		});
	}
	public void saveJobCreated(final String output){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					if(output != null && !output.isEmpty()){
						KaizalaEventsResponse KER = new KaizalaEventsResponse(); 
						DateUtilService dateService = new DateUtilService();
						JSONObject jsonObj = new JSONObject(output);
						JSONObject dataObj = jsonObj.getJSONObject("data");
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
						Date dueDate = sdf.parse(dataObj.getString("dueDate"));
						
						List<Long> ids = kaizalaGroupsDAO.checkGroupExistence(jsonObj.getString("objectId"));
						Long id = null;
							if(ids == null || ids.size() == 0l){
								Long groupTypeId = kaizalaGroupTypeDAO.checkGroupTypeExistence(jsonObj.getString("objectType"));
								KaizalaGroups kg = new KaizalaGroups();
								kg.setGroupId(jsonObj.getString("objectId"));
								kg.setIsDeleted("N");
								kg.setInsertedTime(dateService.getCurrentDateAndTime());
								kg.setKaizalaGroupTypeId(groupTypeId);
								id = kaizalaGroupsDAO.save(kg).getKaizalaGroupsId();
							}else{
								id = ids.get(ids.size()-1);
							}
							KER.setGroupId(jsonObj.getString("objectId"));
							KER.setKaizalaGroupsId(id);
							
							List<Long> resIds = kaizalaResponderInfoDAO.getRespondentId(jsonObj.getString("fromUser"));
							if(resIds != null && resIds.size() > 0){
								KER.setInsertedBy(resIds.get(resIds.size()-1));
								KER.setUpdatedBy(resIds.get(resIds.size()-1));
							}else{
								KaizalaResponderInfo kri = new KaizalaResponderInfo();
								kri.setMobileNumber(jsonObj.getString("fromUser"));
								kri.setIsDeleted("N");
								kri = kaizalaResponderInfoDAO.save(kri);
								
								KaizalaGroupResponderRelation kgrr = new KaizalaGroupResponderRelation();
								kgrr.setGroupId(jsonObj.getString("objectId"));
								kgrr.setKaizalaGroupsId(id);
								kgrr.setKaizalaResponderInfoId(kri.getKaizalaResponderInfoId());
								kgrr.setIsDeleted("N");
								
								KER.setInsertedBy(kri.getKaizalaResponderInfoId());
								KER.setUpdatedBy(kri.getKaizalaResponderInfoId());
							}
							
							Long actionId = kaizalaActionsDAO.checkexistenceOrNot(dataObj.getString("actionId"));
							if(actionId == null || actionId == 0l){
								KaizalaActions ka = new KaizalaActions();
								ka.setActionId(dataObj.getString("actionId"));
								ka.setKaizalaGroupsId(id);
								ka.setTitle(jsonObj.getString("title"));
								ka.setIsDeleted("N");
								ka.setInsertedTime(dateService.getCurrentDateAndTime());
								ka.setExpiryDate(dueDate);
								actionId = kaizalaActionsDAO.save(ka).getKaizalaActionsId();
							}
							KER.setActionId(dataObj.getString("actionId"));
							KER.setKaizalaActionsId(actionId);
							KER.setEventId(jsonObj.getString("eventId"));
							KER.setKaizalaEventsId(5l);
							KER.setInsertedTime(dateService.getCurrentDateAndTime());
							KER.setUpdatedTime(dateService.getCurrentDateAndTime());
							KER.setIsDeleted("N");
							
							KER = kaizalaEventsResponseDAO.save(KER);
					}
			} catch (Exception e) {
				LOG.error("Exception raised at saveJobCreated", e);
			}		
		}
	});
}  
	
	public List<String> getActionDetails(String groupId,String actionId){
		List<String> actiondetails = null;
		try {
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://api.kaiza.la/v1/groups/"+groupId+"/actions/"+actionId);
				
				WebResource.Builder builder = webResource.getRequestBuilder();
				
				builder.header("applicationId", "7FA7D81370298643999D1610852D5734F6A832EE485E6D23DFF98353811F8DB8");
			    builder.header("accessToken", getAccessToken());
			    
			    builder.accept("application/json");
			    builder.type("application/json");
			    
			    ClientResponse response = builder.get(ClientResponse.class);
			    if(response.getStatus() != 200){
		        	throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	    }else{
		 	    	actiondetails = new ArrayList<String>(0);
		 	    	String output = response.getEntity(String.class);
		 	    	JSONObject jsonObj = new JSONObject(output);
		 	    	if(jsonObj.has("actionDetails")){
		 	    		JSONObject adObject = jsonObj.getJSONObject("actionDetails");//0-title,1-actiontype,2-acceptmultires,3-expirydate
		 	    		if(adObject.has("title"))
		 	    			actiondetails.add(0, adObject.getString("title"));
		 	    		else
		 	    			actiondetails.add(0, "");
		 	    		
		 	    		if(jsonObj.has("actionType"))
		 	    			actiondetails.add(1, jsonObj.getString("actionType"));
		 	    		else
		 	    			actiondetails.add(1, "");
		 	    		
		 	    		if(adObject.has("acceptMultipleResponses"))
		 	    			actiondetails.add(2, adObject.getString("acceptMultipleResponses"));
		 	    		else
		 	    			actiondetails.add(2, "");
		 	    		
		 	    		if(adObject.has("expiryDate"))
		 	    			actiondetails.add(3, adObject.getString("expiryDate"));
		 	    		else
		 	    			actiondetails.add(3,"");		 	    			
		 	    	}
		 	    }
		} catch (Exception e) {
			LOG.error("Exception raised at getActionTitle", e);
		}
		return actiondetails;
	}
	
	public String getAccessToken(){
		String accessToken = null;
		try {
			accessToken = kaizalaActionsDAO.getAccessToken();
			//System.out.println(dateUtilService.getCurrentDateAndTimeInStringFormat()+"::"+accessToken);
		} catch (Exception e) {
			LOG.error(" Error occured in getAccessToken method in getAllGroupsInfo class ");
		}
		return accessToken;
	}
	
	public void saveAttachmentInfo(final String output){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					if(output != null && !output.isEmpty()){
						DateUtilService dateUtilService = new DateUtilService();
						JSONObject jsonObj = new JSONObject(output);
						List<Long> ids = kaizalaGroupsDAO.checkGroupExistence(jsonObj.getString("objectId"));
						Long id = null;
						if(ids == null || ids.size() == 0l){
							Long groupTypeId = kaizalaGroupTypeDAO.checkGroupTypeExistence(jsonObj.getString("objectType"));
							KaizalaGroups kg = new KaizalaGroups();
							kg.setGroupId(jsonObj.getString("objectId"));
							kg.setIsDeleted("N");
							kg.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							kg.setKaizalaGroupTypeId(groupTypeId);
							id = kaizalaGroupsDAO.save(kg).getKaizalaGroupsId();
						}else{
							id = ids.get(ids.size()-1);
						}
						
						KaizalaEventsResponse eventRes = new KaizalaEventsResponse();
							eventRes.setKaizalaGroupsId(id);
							eventRes.setGroupId(jsonObj.getString("objectId"));
							Long eventId = kaizalaEventsDAO.getEventId(jsonObj.getString("eventType"));
						    if(eventId!=null && eventId!=0l){
								eventRes.setKaizalaEventsId(eventId);
							}
							eventRes.setEventId(jsonObj.getString("eventId"));
							JSONObject innerJsonObj = jsonObj.getJSONObject("data");
							
							String mobileNo = jsonObj.getString("fromUser");
							List<Long> responderIds = null;
							if(mobileNo!=null){
								responderIds = kaizalaResponderInfoDAO.getRespondentId(mobileNo);
								
								if(responderIds!=null && responderIds.size()>0){
									eventRes.setInsertedBy(responderIds.get(0));
									eventRes.setUpdatedBy(responderIds.get(0));
								}
							}
							eventRes.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							eventRes.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							eventRes.setIsDeleted("N");
							eventRes = kaizalaEventsResponseDAO.save(eventRes);
							
							JSONArray inObjArr = innerJsonObj.getJSONArray("media");
							if(inObjArr != null && inObjArr.length() > 0){
								for (int i = 0; i < inObjArr.length(); i++) {
									JSONObject obj = (JSONObject)inObjArr.get(i);
									KaizalaGroupDocument kgdt = new KaizalaGroupDocument();
									kgdt.setKaizalaGroupsId(id);
									kgdt.setKaizalaResponderInfoId(responderIds != null && responderIds.size() > 0 ? responderIds.get(0) : null);
									kgdt.setKaizalaGroupDocumentTypeId(kaizalaGroupDocumentTypeDAO.getGroupDocumentTypeId(innerJsonObj.getString("actionType")));
									kgdt.setImageUrl(obj.getString("mediaUrl"));
									kgdt.setReferenceId(jsonObj.getString("eventId"));
									kgdt.setKaizalaEventsResponseId(eventRes.getKaizalaEventsResponseId());
									kgdt.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									kgdt.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									kgdt.setIsDeleted("N");
									kaizalaGroupDocumentDAO.save(kgdt);
									//eventRes.setResponseText(obj.getString("mediaUrl"));sandeep check
								}
							}
						
					}
				}catch (Exception e) {
					LOG.error("Exception raised at saveAttachmentInfo", e);
				}
			}
		});
	}
	public void saveSurveyCreatedInfo(final String output){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try {
				 if(output != null && !output.isEmpty()){
						KaizalaEventsResponse KER = new KaizalaEventsResponse(); 
						DateUtilService dateService = new DateUtilService();
						JSONObject jsonObj = new JSONObject(output);
						JSONObject dataObj = jsonObj.getJSONObject("data");
						JSONArray queArray = dataObj.getJSONArray("questions");
						JSONArray propArray = dataObj.getJSONArray("properties");
						
						
						List<Long> ids = kaizalaGroupsDAO.checkGroupExistence(jsonObj.getString("objectId"));
						Long id = null;
						if(ids == null || ids.size() == 0l){
							Long groupTypeId = kaizalaGroupTypeDAO.checkGroupTypeExistence(jsonObj.getString("objectType"));
							KaizalaGroups kg = new KaizalaGroups();
							kg.setGroupId(jsonObj.getString("objectId"));
							kg.setIsDeleted("N");
							kg.setInsertedTime(dateService.getCurrentDateAndTime());
							kg.setKaizalaGroupTypeId(groupTypeId);
							id = kaizalaGroupsDAO.save(kg).getKaizalaGroupsId();
						}else{
							id = ids.get(ids.size()-1);
						}
						
						Long actionId = kaizalaActionsDAO.checkexistenceOrNot(dataObj.getString("actionId"));
						if(actionId == null || actionId == 0l){
							KaizalaActions ka = new KaizalaActions();
							ka.setActionId(dataObj.getString("actionId"));
							ka.setTitle(dataObj.getString("title"));
							ka.setIsDeleted("N");
							ka.setKaizalaGroupsId(id);
							ka.setInsertedTime(dateService.getCurrentDateAndTime());
							actionId = kaizalaActionsDAO.save(ka).getKaizalaActionsId();
						}
						List<Long> resIds = kaizalaResponderInfoDAO.getRespondentId(jsonObj.getString("fromUser"));
						if(resIds != null && resIds.size() > 0){
							KER.setInsertedBy(resIds.get(0));
							KER.setUpdatedBy(resIds.get(0));
						}else{
							KaizalaResponderInfo kri = new KaizalaResponderInfo();
							kri.setMobileNumber(jsonObj.getString("fromUser"));
							kri.setIsDeleted("N");
							kri = kaizalaResponderInfoDAO.save(kri);
							KER.setInsertedBy(kri.getKaizalaResponderInfoId());
							KER.setUpdatedBy(kri.getKaizalaResponderInfoId());
						}
						KER.setKaizalaGroupsId(id);
						KER.setKaizalaEventsId(kaizalaEventsDAO.getEventId(jsonObj.getString("eventType")));
						KER.setKaizalaActionsId(actionId);
						KER.setGroupId(jsonObj.getString("objectId"));
						KER.setActionId(dataObj.getString("actionId"));
						KER.setEventId(jsonObj.getString("eventId"));
						KER.setInsertedTime(dateService.getCurrentDateAndTime());
						KER.setUpdatedTime(dateService.getCurrentDateAndTime());
						KER.setIsDeleted("N");
						
					KER = kaizalaEventsResponseDAO.save(KER);
					
					if(queArray != null && queArray.length()>0){
						for (int i=0; i < queArray.length(); i++) {
						    KaizalaQuestions kq = new KaizalaQuestions();
						    
						    kq.setQuestion(queArray.getJSONObject(i).getString("title"));
						    kq.setType(queArray.getJSONObject(i).getString("type"));
						    kq.setKaizalaActionsId(actionId);
						    kq.setKaizalaEventsResponseId(KER.getKaizalaEventsResponseId());
						    kq.setIsDeleted("N");
						    
						    kq = kaizalaQuestionsDAO.save(kq);
						    
						    JSONArray optionArray = queArray.getJSONObject(i).getJSONArray("options");
						    for (int j=0; j < optionArray.length(); j++) {
						    	 KaizalaOptions ko = new KaizalaOptions();
						    	 
						    	 ko.setKaizalaQuestionsId(kq.getKaizalaQuestionsId());
						    	 ko.setTitle(optionArray.getJSONObject(j).getString("title"));
						    	 kaizalaOptionsDAO.save(ko);
						    }
						}
					}
					if(propArray != null && propArray.length() >0){
						
						for(int k=0; k < propArray.length(); k++){
							KaizalaProperties kp = new KaizalaProperties();
							
							kp.setName(propArray.getJSONObject(k).getString("name"));
							kp.setType(propArray.getJSONObject(k).getString("type"));
							kp.setValue(propArray.getJSONObject(k).getString("value"));
							
							kaizalaPropertiesDAO.save(kp);
						}
					}
				}
			}catch(Exception e){
			   LOG.error("Exception raised at saveSurveyCreatedInfo", e);
			}		
		}
	});
}  
	public void saveJobResponseInfo(final String output){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try {
				 if(output != null && !output.isEmpty()){
						KaizalaEventsResponse KER = new KaizalaEventsResponse(); 
						DateUtilService dateService = new DateUtilService();
						JSONObject jsonObj = new JSONObject(output);
						JSONObject dataObj = jsonObj.getJSONObject("data");
						JSONObject responseDetailsObj = dataObj.getJSONObject("responseDetails");
						JSONObject responseObj = responseDetailsObj.getJSONObject("response");
						
						List<Long> ids = kaizalaGroupsDAO.checkGroupExistence(jsonObj.getString("objectId"));
						Long id = null;
						if(ids == null || ids.size() == 0l){
							Long groupTypeId = kaizalaGroupTypeDAO.checkGroupTypeExistence(jsonObj.getString("objectType"));
							KaizalaGroups kg = new KaizalaGroups();
							kg.setGroupId(jsonObj.getString("objectId"));
							kg.setIsDeleted("N");
							kg.setInsertedTime(dateService.getCurrentDateAndTime());
							kg.setKaizalaGroupTypeId(groupTypeId);
							id = kaizalaGroupsDAO.save(kg).getKaizalaGroupsId();
						}else{
							id = ids.get(ids.size()-1);
						}
						
						Long actionId = kaizalaActionsDAO.checkexistenceOrNot(dataObj.getString("actionId"));
						if(actionId == null || actionId == 0l){
							KaizalaActions ka = new KaizalaActions();
							ka.setActionId(dataObj.getString("actionId"));
							ka.setTitle(dataObj.getString("title"));
							ka.setIsDeleted("N");
							ka.setKaizalaGroupsId(id);
							ka.setInsertedTime(dateService.getCurrentDateAndTime());
							actionId = kaizalaActionsDAO.save(ka).getKaizalaActionsId();
						}
						List<Long> resIds = kaizalaResponderInfoDAO.getRespondentId(jsonObj.getString("fromUser"));
						if(resIds != null && resIds.size() > 0){
							KER.setInsertedBy(resIds.get(0));
							KER.setUpdatedBy(resIds.get(0));
						}else{
							KaizalaResponderInfo kri = new KaizalaResponderInfo();
							kri.setMobileNumber(jsonObj.getString("fromUser"));
							kri.setIsDeleted("N");
							kri = kaizalaResponderInfoDAO.save(kri);
							KER.setInsertedBy(kri.getKaizalaResponderInfoId());
							KER.setUpdatedBy(kri.getKaizalaResponderInfoId());
						}
						KER.setGroupId(jsonObj.getString("objectId"));
						KER.setKaizalaGroupsId(id);
						KER.setActionId(dataObj.getString("actionId"));
						KER.setKaizalaActionsId(actionId);
						KER.setInsertedTime(dateService.getCurrentDateAndTime());
						KER.setUpdatedTime(dateService.getCurrentDateAndTime());
						KER.setIsDeleted("N");
						KER.setEventId(jsonObj.getString("eventId"));
						KER.setKaizalaEventsId(6l);
						
						KER = kaizalaEventsResponseDAO.save(KER);
						
						KaizalaJobResponse kjb = new KaizalaJobResponse();
						kjb.setKaizalaEventsResponseId(KER.getKaizalaEventsResponseId());
						kjb.setJobResponse(responseObj.getString("isCompleted"));
						
						kaizalaJobResponseDAO.save(kjb);
				}
			}catch(Exception e){
			   LOG.error("Exception raised at saveJobResponseInfo", e);
			}		
		}
	});
}
	
	public void saveSubGroupAddedEvent(final String output){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					 if(output != null && !output.isEmpty()){
						KaizalaEventsResponse KER = new KaizalaEventsResponse(); 
						DateUtilService dateService = new DateUtilService();
						JSONObject jsonObj = new JSONObject(output);
						
						List<Long> parentGroupIds = kaizalaGroupsDAO.checkGroupExistence(jsonObj.getString("objectId"));
						
						JSONObject dataObj = jsonObj.getJSONObject("data");
						KaizalaGroups subGroup = new KaizalaGroups();
						subGroup.setGroupId(dataObj.getString("groupId"));
						subGroup.setGroupName(dataObj.getString("groupName"));
						subGroup.setKaizalaGroupTypeId(kaizalaGroupTypeDAO.checkGroupTypeExistence(dataObj.getString("groupType")));
						subGroup.setParentKaizalaGroupsId(parentGroupIds.get(parentGroupIds.size()-1));
						subGroup.setInsertedTime(dateService.getCurrentDateAndTime());
						subGroup.setIsDeleted("N");
						subGroup = kaizalaGroupsDAO.save(subGroup);
						
						
						KER.setKaizalaGroupsId(parentGroupIds.get(parentGroupIds.size()-1));
						KER.setGroupId(jsonObj.getString("objectId"));
						KER.setEventId(jsonObj.getString("eventId"));
						KER.setKaizalaEventsId(11l);
						KER.setAddedKaizalaGroupsId(subGroup.getKaizalaGroupsId());
						KER.setInsertedTime(dateService.getCurrentDateAndTime());
						KER.setUpdatedTime(dateService.getCurrentDateAndTime());
						
						List<Long> resIds = kaizalaResponderInfoDAO.getRespondentId(jsonObj.getString("fromUser"));
						if(resIds != null && resIds.size() > 0){
							KER.setInsertedBy(resIds.get(0));
							KER.setUpdatedBy(resIds.get(0));
						}else{
							KaizalaResponderInfo kri = new KaizalaResponderInfo();
							kri.setMobileNumber(jsonObj.getString("fromUser"));
							kri.setName(jsonObj.getString("fromUserName"));
							kri.setIsDeleted("N");
							kri = kaizalaResponderInfoDAO.save(kri);
							KER.setInsertedBy(kri.getKaizalaResponderInfoId());
							KER.setUpdatedBy(kri.getKaizalaResponderInfoId());
						}
						
						KER.setIsDeleted("N");
						
						kaizalaEventsResponseDAO.save(KER);
						
						
					 }
				} catch (Exception e) {
					LOG.error("Exception raised at saveSubGroupAddedEvent", e);
				}
			}
		});
	}
	
	public void saveSubGroupRemovedEvent(final String output){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					 if(output != null && !output.isEmpty()){
						KaizalaEventsResponse KER = new KaizalaEventsResponse(); 
						DateUtilService dateService = new DateUtilService();
						JSONObject jsonObj = new JSONObject(output);
						
						List<Long> parentGroupIds = kaizalaGroupsDAO.checkGroupExistence(jsonObj.getString("objectId"));
						List<Long> childGroupIds = kaizalaGroupsDAO.checkGroupExistence(jsonObj.getJSONObject("data").getString("groupId"));
						
						KER.setKaizalaGroupsId(parentGroupIds.get(parentGroupIds.size()-1));
						KER.setGroupId(jsonObj.getString("objectId"));
						KER.setEventId(jsonObj.getString("eventId"));
						KER.setKaizalaEventsId(12l);
						KER.setAddedKaizalaGroupsId(childGroupIds.get(childGroupIds.size()-1));
						KER.setInsertedTime(dateService.getCurrentDateAndTime());
						KER.setUpdatedTime(dateService.getCurrentDateAndTime());
						
						List<Long> resIds = kaizalaResponderInfoDAO.getRespondentId(jsonObj.getString("fromUser"));
						if(resIds != null && resIds.size() > 0){
							KER.setInsertedBy(resIds.get(0));
							KER.setUpdatedBy(resIds.get(0));
						}else{
							KaizalaResponderInfo kri = new KaizalaResponderInfo();
							kri.setMobileNumber(jsonObj.getString("fromUser"));
							kri.setName(jsonObj.getString("fromUserName"));
							kri.setIsDeleted("N");
							kri = kaizalaResponderInfoDAO.save(kri);
							KER.setInsertedBy(kri.getKaizalaResponderInfoId());
							KER.setUpdatedBy(kri.getKaizalaResponderInfoId());
						}
						
						KER.setIsDeleted("N");
						
						kaizalaEventsResponseDAO.save(KER);
						
						kaizalaGroupsDAO.removeParentGroup(childGroupIds.get(childGroupIds.size()-1));
					 }
					
				} catch (Exception e) {
					LOG.error("Exception raised at saveSubGroupRemovedEvent", e);
				}
			}
		});
	}
	
	public void saveMemberAddedInfo(final String output){
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					try {
					 if(output != null && !output.isEmpty()){
							KaizalaEventsResponse KER = new KaizalaEventsResponse();
							DateUtilService dateService = new DateUtilService();
							JSONObject jsonObj = new JSONObject(output);
							JSONObject dataObj = jsonObj.getJSONObject("data");
							
							List<Long> ids = kaizalaGroupsDAO.checkGroupExistence(jsonObj.getString("objectId"));
							Long id = null;
							if(ids == null || ids.size() == 0l){
								Long groupTypeId = kaizalaGroupTypeDAO.checkGroupTypeExistence(jsonObj.getString("objectType"));
								KaizalaGroups kg = new KaizalaGroups();
								kg.setGroupId(jsonObj.getString("objectId"));
								kg.setIsDeleted("N");
								kg.setInsertedTime(dateService.getCurrentDateAndTime());
								kg.setKaizalaGroupTypeId(groupTypeId);
								id = kaizalaGroupsDAO.save(kg).getKaizalaGroupsId();
							}else{
								id = ids.get(ids.size()-1);
							}
							
							List<Long> resIds = kaizalaResponderInfoDAO.getRespondentId(jsonObj.getString("fromUser"));
							if(resIds != null && resIds.size() > 0){
								KER.setInsertedBy(resIds.get(0));
								KER.setUpdatedBy(resIds.get(0));
							}else{
								KaizalaResponderInfo kri = new KaizalaResponderInfo();
								kri.setMobileNumber(jsonObj.getString("fromUser"));
								kri.setIsDeleted("N");
								kri = kaizalaResponderInfoDAO.save(kri);
								KER.setInsertedBy(kri.getKaizalaResponderInfoId());
								KER.setUpdatedBy(kri.getKaizalaResponderInfoId());
							}
							
							List<Long> memberResponId = kaizalaResponderInfoDAO.getRespondentId(dataObj.getString("member"));
							if(memberResponId == null || memberResponId.size() == 0){
								Long responderTypeId = kaizalaResponderTypeDAO.getResponderType(dataObj.getString("type"));
								KaizalaResponderInfo kri = new KaizalaResponderInfo();
								kri.setMobileNumber(dataObj.getString("member"));
								kri.setName(dataObj.getString("memberName"));
								kri.setKaizalaResponderTypeId(responderTypeId);
								kri.setIsDeleted("N");
								memberResponId.add(kaizalaResponderInfoDAO.save(kri).getKaizalaResponderInfoId());								
							}
							
							KER.setAddedKaizalaResponderInfoId(memberResponId.get(0));
							KER.setGroupId(jsonObj.getString("objectId"));
							KER.setKaizalaGroupsId(id);
							KER.setInsertedTime(dateService.getCurrentDateAndTime());
							KER.setUpdatedTime(dateService.getCurrentDateAndTime());
							KER.setEventId(jsonObj.getString("eventId"));
							KER.setKaizalaEventsId(9l);
							KER.setIsDeleted("N");
							
							KER = kaizalaEventsResponseDAO.save(KER);
							
							KaizalaGroupResponderRelation KGRR = new KaizalaGroupResponderRelation();
							KGRR.setGroupId(jsonObj.getString("objectId"));
							KGRR.setKaizalaGroupsId(id);
							KGRR.setIsDeleted("N");
							KGRR.setKaizalaResponderInfoId(memberResponId.get(0));
							KGRR.setKaizalaEventsResponseId(KER.getKaizalaEventsResponseId());
							kaizalaGroupResponderRelationDAO.save(KGRR);
							
					}
				}catch(Exception e){
				   LOG.error("Exception raised at saveMemberAddedInfo", e);
				}		
			}
		});
	}
	public void updateMemberRemovedInfo(final String output){
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					try {
					 if(output != null && !output.isEmpty()){
							KaizalaEventsResponse KER = new KaizalaEventsResponse(); 
							DateUtilService dateService = new DateUtilService();
							JSONObject jsonObj = new JSONObject(output);
							JSONObject dataObj = jsonObj.getJSONObject("data");
							
							List<Long> ids = kaizalaGroupsDAO.checkGroupExistence(jsonObj.getString("objectId"));
							Long  id = null;
							if(ids == null || ids.size() == 0l){
								Long groupTypeId = kaizalaGroupTypeDAO.checkGroupTypeExistence(jsonObj.getString("objectType"));
								KaizalaGroups kg = new KaizalaGroups();
								kg.setGroupId(jsonObj.getString("objectId"));
								kg.setIsDeleted("N");
								kg.setInsertedTime(dateService.getCurrentDateAndTime());
								kg.setKaizalaGroupTypeId(groupTypeId);
								id = kaizalaGroupsDAO.save(kg).getKaizalaGroupsId();
							}else{
								id = ids.get(ids.size()-1);
							}
							
							List<Long> resIds = kaizalaResponderInfoDAO.getRespondentId(jsonObj.getString("fromUser"));
							if(resIds != null && resIds.size() > 0){
								KER.setInsertedBy(resIds.get(0));
								KER.setUpdatedBy(resIds.get(0));
							}else{
								KaizalaResponderInfo kri = new KaizalaResponderInfo();
								kri.setMobileNumber(jsonObj.getString("fromUser"));
								kri.setIsDeleted("N");
								kri = kaizalaResponderInfoDAO.save(kri);
								KER.setInsertedBy(kri.getKaizalaResponderInfoId());
								KER.setUpdatedBy(kri.getKaizalaResponderInfoId());
							}
							
							List<Long> memberResponId = kaizalaResponderInfoDAO.getRespondentId(dataObj.getString("member"));
							if(memberResponId == null || memberResponId.size() == 0){
								Long responderTypeId = kaizalaResponderTypeDAO.getResponderType(dataObj.getString("type"));
								KaizalaResponderInfo kri = new KaizalaResponderInfo();
								kri.setMobileNumber(dataObj.getString("member"));
								kri.setName(dataObj.getString("memberName"));
								kri.setKaizalaResponderTypeId(responderTypeId);
								kri.setIsDeleted("N");
								memberResponId.add(kaizalaResponderInfoDAO.save(kri).getKaizalaResponderInfoId());
							}
							KER.setAddedKaizalaResponderInfoId(memberResponId.get(0));
							KER.setGroupId(jsonObj.getString("objectId"));
							KER.setKaizalaGroupsId(id);
							KER.setInsertedTime(dateService.getCurrentDateAndTime());
							KER.setUpdatedTime(dateService.getCurrentDateAndTime());
							KER.setEventId(jsonObj.getString("eventId"));
							KER.setKaizalaEventsId(10l);
							KER.setIsDeleted("N");
							
							KER = kaizalaEventsResponseDAO.save(KER);
							
							kaizalaGroupResponderRelationDAO.updateMemberRemoved(KER.getKaizalaEventsResponseId(),id,KER.getAddedKaizalaResponderInfoId());
					}
				}catch(Exception e){
				   LOG.error("Exception raised at updateMemberRemovedInfo", e);
				}		
			}
		});
	}
}
