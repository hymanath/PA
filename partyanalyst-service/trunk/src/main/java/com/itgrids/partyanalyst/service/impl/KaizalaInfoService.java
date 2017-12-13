package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
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
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.KaizalaDashboardVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.model.KaizalaActions;
import com.itgrids.partyanalyst.model.KaizalaAnswerInfo;
import com.itgrids.partyanalyst.model.KaizalaAnswers;
import com.itgrids.partyanalyst.model.KaizalaEventsResponse;
import com.itgrids.partyanalyst.model.KaizalaGroupDocument;
import com.itgrids.partyanalyst.model.KaizalaGroupResponderRelation;
import com.itgrids.partyanalyst.model.KaizalaGroups;
import com.itgrids.partyanalyst.model.KaizalaJobResponse;
import com.itgrids.partyanalyst.model.KaizalaOptions;
import com.itgrids.partyanalyst.model.KaizalaProperties;
import com.itgrids.partyanalyst.model.KaizalaQuestions;
import com.itgrids.partyanalyst.model.KaizalaResponderInfo;
import com.itgrids.partyanalyst.model.KaizalaTextMessage;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
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
	private ICoreDashboardGenericService coreDashboardGenericService;
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	

	public IActivityMemberAccessLevelDAO getActivityMemberAccessLevelDAO() {
		return activityMemberAccessLevelDAO;
	}
	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}
	public IParliamentAssemblyDAO getParliamentAssemblyDAO() {
		return parliamentAssemblyDAO;
	}
	public void setParliamentAssemblyDAO(
			IParliamentAssemblyDAO parliamentAssemblyDAO) {
		this.parliamentAssemblyDAO = parliamentAssemblyDAO;
	}
	public ICoreDashboardGenericService getCoreDashboardGenericService() {
		return coreDashboardGenericService;
	}
	public void setCoreDashboardGenericService(
			ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	}
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
			
			LOG.error(" Entered into saveKaizalaInstallationTracking method in KaizalaInfoService Class ");
			
			if(jobj == null)
				return;
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://www.mytdp.com/KAIZALA/saveKaizalaInstallationTracking");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://localhost:8085/KAIZALA/saveKaizalaInstallationTracking");
			
			WebResource.Builder builder = webResource.getRequestBuilder();
			
			builder.accept("application/json");
			builder.type("application/json");
			
			ClientResponse response = builder.post(ClientResponse.class,jobj.toString());
			
			LOG.error(" Retrive Response Code in saveKaizalaInstallationTracking method in KaizalaInfoService Class From KAIZALA -- Status :"+response.getStatus()+"");
			
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}else{				
				String output = response.getEntity(String.class);				
				if(output !=null && !output.trim().isEmpty()){
					System.out.println(" Kaizala Installation Tracking Saved Successfully.");
				}				
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveKaizalaInstallationTracking in KaizalaInfoService Class ", e);
		}
	}
	
	public void saveEventResponses(final String output){
		try {
			
			LOG.error("Entered into saveEventResponses method in KaizalaInfoService Class ");
			
			if(output != null && !output.isEmpty()){
				JSONObject jsonObj = new JSONObject(output);
				
				if(jsonObj.getString("objectType").equalsIgnoreCase("Group") 
						&& jsonObj.getString("objectId").equalsIgnoreCase("034607bb-6d16-4819-8ac8-32f22043e4d5")){ // KaizalaInstallationTrack  Group(Public Group)
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
			LOG.error("Exception raised at saveEventResponses in KaizalaInfoService Class", e);
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
	
	public List<KaizalaDashboardVO> getLocationWiseCommitteeMemberDetails(InputVO inputvo){
		List<KaizalaDashboardVO> returnList = new ArrayList<KaizalaDashboardVO>(0);
		try {
			LOG.error(" Entered into getLocationWiseCommitteeMemberDetails method in KaizalaInfoService Class ");
			
			Map<Long,String> assemParMap = new LinkedHashMap<Long, String>();
			List<Long> assemblyIds = new ArrayList<Long>(0);
			Long userAccessLevelId=0l;
			Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>(0);
			Set<Long> levelValues = new java.util.HashSet<Long>();
			
			List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(inputvo.getActivityMemberId());
		    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
			   userAccessLevelId = commonMethodsUtilService.getLongValueForObject(rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0]);
			   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
				Set<Long> locationValuesSet= locationAccessLevelMap.get((Long)param[0]);
				 if(locationValuesSet == null){
					 locationValuesSet = new java.util.HashSet<Long>();
					 locationAccessLevelMap.put((Long)param[0],locationValuesSet);
				 }
				 locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
				 levelValues.add(param[1] != null ? (Long)param[1]:0l);
			}
			   inputvo.setLevelId(userAccessLevelId);
			   inputvo.setLevelValues(new ArrayList<Long>(levelValues));
		   }
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://www.mytdp.com/KAIZALA/getLocationWiseCommitteeMemberDetails");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.131:8080/KAIZALA/getLocationWiseCommitteeMemberDetails");
			
			WebResource.Builder builder = webResource.getRequestBuilder();
			
			builder.accept("application/json");
			builder.type("application/json");
			
			ClientResponse response = builder.post(ClientResponse.class,inputvo);
			
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}else{				
				String output = response.getEntity(String.class);				
				if(output !=null && !output.trim().isEmpty()){
					JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
		 	    		 for(int i=0;i<finalArray.length();i++){
		 	    			KaizalaDashboardVO vo = new KaizalaDashboardVO();
		 	    			JSONObject obj = (JSONObject) finalArray.get(i);
		 	    			vo.setId(obj.getLong("id"));
		 	    			vo.setName(obj.getString("name"));
		 	    			vo.setCadreInstalledCount(obj.getLong("cadreInstalledCount"));
		 	    			vo.setPublicInstalledCount(obj.getLong("publicInstalledCount"));
		 	    			vo.setCadreNoSmartPhoneCount(obj.getLong("cadreNoSmartPhoneCount"));
		 	    			vo.setPublicNoSmartPhoneCount(obj.getLong("publicNoSmartPhoneCount"));
		 	    			if(inputvo.getName().trim().equalsIgnoreCase("constituency"))
		 	    				assemblyIds.add(vo.getId());
		 	    			JSONArray subArr = obj.getJSONArray("subList");
		 	    			if(subArr!=null && subArr.length()>0){
				 	    		 for(int j=0;j<subArr.length();j++){
				 	    			KaizalaDashboardVO subvo = new KaizalaDashboardVO();
				 	    			JSONObject subobj = (JSONObject) subArr.get(j);
				 	    			subvo.setId(subobj.getLong("id"));
				 	    			subvo.setName(subobj.getString("name"));
				 	    			subvo.setTotalCount(subobj.getLong("totalCount"));
				 	    			subvo.setInstalled(subobj.getLong("installed"));
				 	    			subvo.setPending(subobj.getLong("pending"));
				 	    			subvo.setNotHavingSmartPhone(subobj.getLong("notHavingSmartPhone"));
				 	    			subvo.setInstalledPerc(subobj.getString("installedPerc"));
				 	    			subvo.setPendingPerc(subobj.getString("pendingPerc"));
				 	    			subvo.setNotSmartPhonePerc(subobj.getString("notSmartPhonePerc"));
				 	    			vo.getSubList().add(subvo);
				 	    			
				 	    			vo.setTotalCount(vo.getTotalCount()+subvo.getTotalCount());
				 	    			vo.setInstalled(vo.getInstalled()+subvo.getInstalled());
				 	    			vo.setNotHavingSmartPhone(vo.getNotHavingSmartPhone()+subvo.getNotHavingSmartPhone());
				 	    			vo.setPending(vo.getPending()+subvo.getPending());
				 	    		 }
		 	    			}
		 	    			vo.setInstalledPerc(calculatePercantage(vo.getInstalled(), vo.getTotalCount()).toString());
		 	    			vo.setNotSmartPhonePerc(calculatePercantage(vo.getNotHavingSmartPhone(), vo.getTotalCount()).toString());
		 	    			vo.setPendingPerc(calculatePercantage(vo.getPending(), vo.getTotalCount()).toString());
		 	    			
		 	    			vo.setCommitteeInstalled(vo.getInstalled());
		 	    			vo.setCommitteeNoSmartPhone(vo.getNotHavingSmartPhone());
		 	    			vo.setOverAllInstalledCount(vo.getCommitteeInstalled()+vo.getCadreInstalledCount()+vo.getPublicInstalledCount());
		 	    			vo.setOverAllNoSmartPhoneCount(vo.getCommitteeNoSmartPhone()+vo.getCadreNoSmartPhoneCount()+vo.getPublicNoSmartPhoneCount());
		 	    			vo.setCommitteeInstalPerc(calculatePercantage(vo.getCommitteeInstalled(), vo.getOverAllInstalledCount()).toString());
		 	    			vo.setCommitteeNoSmartPerc(calculatePercantage(vo.getCommitteeNoSmartPhone(), vo.getOverAllNoSmartPhoneCount()).toString());
		 	    			vo.setCadreInstallPerc(calculatePercantage(vo.getCadreInstalledCount(), vo.getOverAllInstalledCount()).toString());
		 	    			vo.setCadreNoSmartPerc(calculatePercantage(vo.getCadreNoSmartPhoneCount(), vo.getOverAllNoSmartPhoneCount()).toString());
		 	    			vo.setPublicInstallPerc(calculatePercantage(vo.getPublicInstalledCount(), vo.getOverAllInstalledCount()).toString());
		 	    			vo.setPublicNoSmartPerc(calculatePercantage(vo.getPublicNoSmartPhoneCount(), vo.getOverAllNoSmartPhoneCount()).toString());
		 	    			
		 	    			returnList.add(vo);
		 	    		 }
	 	    		}
				}			
				
				List<Object[]> list = parliamentAssemblyDAO.getParlIdsByAssemblyId(assemblyIds);
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						Long constId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						String parlName = obj[1] != null ? obj[1].toString():"";
						assemParMap.put(constId, parlName);
					}
				}
				
				if(returnList != null && !returnList.isEmpty()){
					for (KaizalaDashboardVO kaizalaDashboardVO : returnList) {
						kaizalaDashboardVO.setParlName(assemParMap.get(kaizalaDashboardVO.getId()));
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseCommitteeMemberDetails in KaizalaInfoService Class ", e);
		}
		return returnList;
	}
	public KaizalaDashboardVO getOverAllCommitteeWiseMembersCounts(InputVO inputvo){
		KaizalaDashboardVO returnvo = new KaizalaDashboardVO();
		try {
			LOG.error(" Entered into getLocationWiseCommitteeMemberDetails method in KaizalaInfoService Class ");
			
			Long userAccessLevelId=0l;
			Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>(0);
			Set<Long> levelValues = new java.util.HashSet<Long>();
			
			List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(inputvo.getActivityMemberId());
		    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
			   userAccessLevelId = commonMethodsUtilService.getLongValueForObject(rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0]);
			   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
				Set<Long> locationValuesSet= locationAccessLevelMap.get((Long)param[0]);
				 if(locationValuesSet == null){
					 locationValuesSet = new java.util.HashSet<Long>();
					 locationAccessLevelMap.put((Long)param[0],locationValuesSet);
				 }
				 locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
				 levelValues.add(param[1] != null ? (Long)param[1]:0l);
			}
			   inputvo.setLevelId(userAccessLevelId);
			   inputvo.setLevelValues(new ArrayList<Long>(levelValues));
		   }
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://www.mytdp.com/KAIZALA/getOverAllCommitteeWiseMembersCounts");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.131:8080/KAIZALA/getOverAllCommitteeWiseMembersCounts");
			
			WebResource.Builder builder = webResource.getRequestBuilder();
			
			builder.accept("application/json");
			builder.type("application/json");
			
			ClientResponse response = builder.post(ClientResponse.class,inputvo);
			
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}else{			
				String output = response.getEntity(String.class);	
				if(output != null && !output.isEmpty()){
					JSONObject obj = new JSONObject(output);
					returnvo.setCadreInstalledCount(obj.getLong("cadreInstalledCount"));
					returnvo.setCadreNoSmartPhoneCount(obj.getLong("cadreNoSmartPhoneCount"));
					returnvo.setPublicInstalledCount(obj.getLong("publicInstalledCount"));
					returnvo.setPublicNoSmartPhoneCount(obj.getLong("publicNoSmartPhoneCount"));
					returnvo.setCommitteeInstalled(obj.getLong("committeeInstalled"));
					returnvo.setCommitteeNoSmartPhone(obj.getLong("committeeNoSmartPhone"));
					returnvo.setOverAllInstalledCount(obj.getLong("overAllInstalledCount"));
					returnvo.setOverAllNoSmartPhoneCount(obj.getLong("overAllNoSmartPhoneCount"));
					returnvo.setCommitteeInstalPerc(obj.getString("committeeInstalPerc"));
					returnvo.setCommitteeNoSmartPerc(obj.getString("committeeNoSmartPerc"));
					returnvo.setCadreInstallPerc(obj.getString("cadreInstallPerc"));
					returnvo.setCadreNoSmartPerc(obj.getString("cadreNoSmartPerc"));
					returnvo.setPublicInstallPerc(obj.getString("publicInstallPerc"));
					returnvo.setPublicNoSmartPerc(obj.getString("publicNoSmartPerc"));
					returnvo.setTotalCount(obj.getLong("totalCount"));
					returnvo.setPending(obj.getLong("pending"));
					returnvo.setPendingPerc(obj.getString("pendingPerc"));
					JSONArray committeeArr = obj.getJSONArray("subList");
					if(committeeArr != null && committeeArr.length() > 0){
						for (int i = 0; i < committeeArr.length(); i++) {
							JSONObject subObj = (JSONObject) committeeArr.get(i);
							KaizalaDashboardVO subvo = new KaizalaDashboardVO();
							subvo.setId(subObj.getLong("id"));
							subvo.setName(subObj.getString("name"));
							subvo.setTotalCount(subObj.getLong("totalCount"));
							subvo.setInstalled(subObj.getLong("installed"));
							subvo.setPending(subObj.getLong("pending"));
							subvo.setNotHavingSmartPhone(subObj.getLong("notHavingSmartPhone"));
							subvo.setInstalledPerc(subObj.getString("installedPerc"));
							subvo.setPendingPerc(subObj.getString("pendingPerc"));
							subvo.setNotSmartPhonePerc(subObj.getString("notSmartPhonePerc"));
							returnvo.getSubList().add(subvo);
						}
					}
				}
				/*String output = response.getEntity(String.class);				
				if(output !=null && !output.trim().isEmpty()){
					JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
		 	    		 for(int i=0;i<finalArray.length();i++){
		 	    			KaizalaDashboardVO vo = new KaizalaDashboardVO();
		 	    			JSONObject obj = (JSONObject) finalArray.get(i);
		 	    			vo.setId(obj.getLong("id"));
		 	    			vo.setName(obj.getString("name"));
		 	    			vo.setTotalCount(obj.getLong("totalCount"));
		 	    			vo.setInstalled(obj.getLong("installed"));
		 	    			vo.setPending(obj.getLong("pending"));
		 	    			vo.setNotHavingSmartPhone(obj.getLong("notHavingSmartPhone"));
		 	    			vo.setInstalledPerc(obj.getString("installedPerc"));
		 	    			vo.setPendingPerc(obj.getString("pendingPerc"));
		 	    			vo.setNotSmartPhonePerc(obj.getString("notSmartPhonePerc"));
		 	    			returnList.add(vo);
		 	    		 }
	 	    		}
				}		*/		
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getOverAllCommitteeWiseMembersCounts in KaizalaInfoService Class ", e);
		}
		return returnvo;
	}
	
	public List<KaizalaDashboardVO> getLevelLocationWiseCounts(InputVO inputvo){
		List<KaizalaDashboardVO> returnList = new ArrayList<KaizalaDashboardVO>(0);
		try {
			LOG.error(" Entered into getLocationWiseCommitteeMemberDetails method in KaizalaInfoService Class ");
			
			Long userAccessLevelId=0l;
			Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>(0);
			Set<Long> levelValues = new java.util.HashSet<Long>();
			
			List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(inputvo.getActivityMemberId());
		    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
			   userAccessLevelId = commonMethodsUtilService.getLongValueForObject(rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0]);
			   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
				Set<Long> locationValuesSet= locationAccessLevelMap.get((Long)param[0]);
				 if(locationValuesSet == null){
					 locationValuesSet = new java.util.HashSet<Long>();
					 locationAccessLevelMap.put((Long)param[0],locationValuesSet);
				 }
				 locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
				 levelValues.add(param[1] != null ? (Long)param[1]:0l);
			}
			   inputvo.setLevelId(userAccessLevelId);
			   inputvo.setLevelValues(new ArrayList<Long>(levelValues));
		   }
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://www.mytdp.com/KAIZALA/getLevelLocationWiseCounts");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.131:8080/KAIZALA/getLevelLocationWiseCounts");
			
			WebResource.Builder builder = webResource.getRequestBuilder();
			
			builder.accept("application/json");
			builder.type("application/json");
			
			ClientResponse response = builder.post(ClientResponse.class,inputvo);
			
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}else{				
				String output = response.getEntity(String.class);				
				if(output !=null && !output.trim().isEmpty()){
					JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
		 	    		 for(int i=0;i<finalArray.length();i++){
		 	    			KaizalaDashboardVO vo = new KaizalaDashboardVO();
		 	    			JSONObject obj = (JSONObject) finalArray.get(i);
		 	    			vo.setId(obj.getLong("id"));
		 	    			vo.setName(obj.getString("name"));
		 	    			vo.setKey(obj.getString("key"));
		 	    			vo.setCadreInstalledCount(obj.getLong("cadreInstalledCount"));
		 	    			vo.setPublicInstalledCount(obj.getLong("publicInstalledCount"));
		 	    			vo.setCadreNoSmartPhoneCount(obj.getLong("cadreNoSmartPhoneCount"));
		 	    			vo.setPublicNoSmartPhoneCount(obj.getLong("publicNoSmartPhoneCount"));
		 	    			JSONArray subArr = obj.getJSONArray("subList");
		 	    			if(subArr!=null && subArr.length()>0){
				 	    		 for(int j=0;j<subArr.length();j++){
				 	    			KaizalaDashboardVO subvo = new KaizalaDashboardVO();
				 	    			JSONObject subobj = (JSONObject) subArr.get(j);
				 	    			subvo.setId(subobj.getLong("id"));
				 	    			subvo.setName(subobj.getString("name"));
				 	    			subvo.setTotalCount(subobj.getLong("totalCount"));
				 	    			subvo.setInstalled(subobj.getLong("installed"));
				 	    			//subvo.setPending(subobj.getLong("pending"));
				 	    			subvo.setNotHavingSmartPhone(subobj.getLong("notHavingSmartPhone"));
				 	    			//subvo.setInstalledPerc(subobj.getString("installedPerc"));
				 	    			//subvo.setPendingPerc(subobj.getString("pendingPerc"));
				 	    			subvo.setPending(subvo.getTotalCount() - subvo.getInstalled() - subvo.getNotHavingSmartPhone());
				 	    			subvo.setInstalledPerc(calculatePercantage(subvo.getInstalled(), subvo.getTotalCount()).toString());
				 	    			subvo.setNotSmartPhonePerc(calculatePercantage(subvo.getNotHavingSmartPhone(), subvo.getTotalCount()).toString());
				 	    			subvo.setPendingPerc(calculatePercantage(subvo.getPending(), subvo.getTotalCount()).toString());
				 	    			vo.getSubList().add(subvo);
				 	    			
				 	    			vo.setTotalCount(vo.getTotalCount()+subobj.getLong("totalCount"));
				 	    			vo.setInstalled(vo.getInstalled()+subobj.getLong("installed"));
				 	    			vo.setNotHavingSmartPhone(vo.getNotHavingSmartPhone()+subobj.getLong("notHavingSmartPhone"));
				 	    			vo.setPending(vo.getPending()+subvo.getPending());
				 	    		 }
		 	    			}
		 	    			vo.setInstalledPerc(calculatePercantage(vo.getInstalled(), vo.getTotalCount()).toString());
		 	    			vo.setNotSmartPhonePerc(calculatePercantage(vo.getNotHavingSmartPhone(), vo.getTotalCount()).toString());
		 	    			vo.setPendingPerc(calculatePercantage(vo.getPending(), vo.getTotalCount()).toString());
		 	    			returnList.add(vo);
		 	    		 }
	 	    		}
				}				
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getOverAllCommitteeWiseMembersCounts in KaizalaInfoService Class ", e);
		}
		return returnList;
	}
	
	public List<List<UserTypeVO>> getUserTypeWiseKaizalaCommitteeMemberDetailsCnt(InputVO inputVO){
		
	    List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>(0);
	    
	    //Map<String,PartyMeetingsVO> meetingCntDtlsMap = new HashMap<String, PartyMeetingsVO>(0);
	    Map<Long,Set<Long>> locationLevelMap = null;
		Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date toDate=null;
		Date fromDate=null;
	  try{
			/*if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 toDate = sdf.parse(toDateStr);
				 fromDate = sdf.parse(fromDateStr);
			 }*/
		     ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(inputVO.getUserId());
		     activityMemberVO.setActivityMemberId(inputVO.getActivityMemberId());
		     activityMemberVO.setUserTypeId(inputVO.getUserTypeId());
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);//calling generic method.
		     userTypeMapDtls = activityMemberVO.getUserTypesMap();
		     locationLevelMap = activityMemberVO.getLocationLevelIdsMap();
		     
		     /*if(locationLevelMap != null && locationLevelMap.size() > 0){
		    	 
		    	 for (Entry<Long, Set<Long>> entry : locationLevelMap.entrySet()) {
		    		 
                   List<Object[]> returnOverAllObjList = partyMeetingDAO.getPartyMeetingOverAllCountLocationWiseByUserAccessLevel(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues);
                  
                   if(returnOverAllObjList!=null && returnOverAllObjList.size()>0){
                	   for(Object[] obj : returnOverAllObjList){
                		   Long userAccessLevelId = entry.getKey();
                		   Long locationLevelValue = (Long)obj[0];
                		   String key = userAccessLevelId+"-"+locationLevelValue;
                		   
                		   PartyMeetingsVO locationVO = null;
                		   locationVO = meetingCntDtlsMap.get(key);
                		   if(locationVO==null){
                			   locationVO = new PartyMeetingsVO();
                			   locationVO.setTotalMeetingCnt(obj[1]!=null?(Long)obj[1]:0l);
                			   meetingCntDtlsMap.put(key, locationVO);
                		   }
                	   }
                   }
				}
		     }
		     
		    if(locationLevelMap != null && locationLevelMap.size() > 0){
		    	for(Entry<Long,Set<Long>> entry:locationLevelMap.entrySet()){
		    		List<Object[]> returnList = partyMeetingStatusDAO.getPartyMeetingCountLocationWiseByUserAccess(entry.getKey(),new ArrayList<Long>(entry.getValue()), stateId, fromDate, toDate,partyMeetingTypeValues);
		    		calculateMeetingStatusWiseDetailsCnt(entry.getKey(),returnList,meetingCntDtlsMap);
		    	}
		    }*/
		    
		     List<KaizalaDashboardVO> kaizalaList = getLevelLocationWiseCounts(inputVO);
		    
		    if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		    	
				  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
					  
				      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				      
				      for(UserTypeVO vo:userTypeMap.values()){
				    	  
				    	  for(Long locationValueId:vo.getLocationValuesSet()){
				    		  
				    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
				    		  
				    		  KaizalaDashboardVO kaizalaDashboardVO = getMatchedVOByString(kaizalaList, key);
				    		  
				    		  if(kaizalaDashboardVO != null){
				    			  if(kaizalaDashboardVO.getSubList() != null && !kaizalaDashboardVO.getSubList().isEmpty()){
				    				  for (KaizalaDashboardVO subvo : kaizalaDashboardVO.getSubList()) {
										UserTypeVO subuservo = getMatchedVO(vo.getSubList1(), subvo.getId());
										if(subuservo == null){
											subuservo = new UserTypeVO();
											subuservo.setId(subvo.getId());
											subuservo.setName(subvo.getName());
											subuservo.setTotalCount(subvo.getTotalCount());
											subuservo.setInstalled(subvo.getInstalled());
											subuservo.setPending(subvo.getPending());
											subuservo.setNotSmartPhone(subvo.getNotHavingSmartPhone());
											
											vo.setTotalCount(subvo.getTotalCount());
											vo.setInstalled(subvo.getInstalled());
											vo.setPending(subvo.getPending());
											vo.setNotSmartPhone(subvo.getNotHavingSmartPhone());
											vo.getSubList1().add(subuservo);
										}else{
											subuservo.setTotalCount(subuservo.getTotalCount()+subvo.getTotalCount());
											subuservo.setInstalled(subuservo.getInstalled()+subvo.getInstalled());
											subuservo.setPending(subuservo.getPending()+subvo.getPending());
											subuservo.setNotSmartPhone(subuservo.getNotSmartPhone()+subvo.getNotHavingSmartPhone());
											
											vo.setTotalCount(vo.getTotalCount()+subvo.getTotalCount());
											vo.setInstalled(vo.getInstalled()+subvo.getInstalled());
											vo.setPending(vo.getPending()+subvo.getPending());
											vo.setNotSmartPhone(vo.getNotSmartPhone()+subvo.getNotHavingSmartPhone());
										}
									}
				    			  }
				    		  }
				    		  
				    		}
				      }
			}  
			} 
		    
		    //Calculate Percentage
		   if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
				  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
				      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				      for(UserTypeVO vo:userTypeMap.values()){
				    	  Long totalMembers = vo.getTotalCount();
				    	  if(totalMembers != null){
				    		  vo.setInstalledPerc(calculatePercantage(vo.getInstalled(), totalMembers).toString());
				    		  vo.setPendingPerc(calculatePercantage(vo.getPending(), totalMembers).toString());
				    		  vo.setNotSmartPhonePerc(calculatePercantage(vo.getNotSmartPhone(), totalMembers).toString());
				    	  }
				    	if(vo.getSubList1() != null && !vo.getSubList1().isEmpty()){
				    		for (UserTypeVO subvo : vo.getSubList1()) {
								Long subTotal = subvo.getTotalCount();
								if(subTotal != null){
									subvo.setInstalledPerc(calculatePercantage(subvo.getInstalled(), subTotal).toString());
									subvo.setPendingPerc(calculatePercantage(subvo.getPending(), subTotal).toString());
									subvo.setNotSmartPhonePerc(calculatePercantage(subvo.getNotSmartPhone(), subTotal).toString());
								}
							}
				    	}
					}
				}
			}
		    
		    //merging secreteries and general secrerteries.
		    if(userTypeMapDtls!=null && userTypeMapDtls.size()>0){
		        Map<Long,UserTypeVO> orgSecAndSecMap = new LinkedHashMap<Long,UserTypeVO>();
		        Map<Long,UserTypeVO>  secreteriesMap = null;
		        if(userTypeMapDtls.containsKey(11l)){
		          secreteriesMap = userTypeMapDtls.get(11l);
		          orgSecAndSecMap.putAll(secreteriesMap);
		          //remove secreteries from Map
		          userTypeMapDtls.remove(11l); 
		        }
		        
		        Map<Long,UserTypeVO>  organizingSecreteriesMap = null;
		        if(userTypeMapDtls.containsKey(4l)){
		          organizingSecreteriesMap = userTypeMapDtls.get(4l);
		          orgSecAndSecMap.putAll(organizingSecreteriesMap);
		        }
		       
		        if(organizingSecreteriesMap!=null && organizingSecreteriesMap.size()>0){
		        	userTypeMapDtls.put(4l, orgSecAndSecMap); 
		        }
		      }
		    
		    
			if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
				  for(Entry<Long, Map<Long, UserTypeVO>> entry:userTypeMapDtls.entrySet()){
				   Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				   resultList.add(new ArrayList<UserTypeVO>(userTypeMap.values()));
			      }
			}
		 	if(resultList != null && resultList.size() > 0){
				for(List<UserTypeVO> memberList:resultList){
					Collections.sort(memberList, installedCountPercDesc);
				}
			}
	 }catch(Exception e) {
	  LOG.error("Exception raised at getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt() method of CoreDashboardPartyMeetingService", e);
	}
	return resultList; 
 }
	
	public static Comparator<UserTypeVO> installedCountPercDesc = new Comparator<UserTypeVO>() {
		public int compare(UserTypeVO member2, UserTypeVO member1) {

		Double perc2 = Double.valueOf(member2.getInstalledPerc());
		Double perc1 = Double.valueOf(member1.getInstalledPerc());
		//descending order of percantages.
		 return perc1.compareTo(perc2);
		}
		}; 
	
	public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
		LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	} 
	
	public UserTypeVO getMatchedVO(List<UserTypeVO> voList ,Long id){
		if(voList != null && voList.size() > 0){
			for (UserTypeVO userTypeVO : voList) {
				if(userTypeVO.getId().equals(id))
					return userTypeVO;
			}
		}
		return null;
	}
	
	public KaizalaDashboardVO getMatchedVOByString(List<KaizalaDashboardVO> voList ,String key){
		if(voList != null && voList.size() > 0){
			for (KaizalaDashboardVO kaizalaDashboardVO : voList) {
				if(kaizalaDashboardVO.getKey().equalsIgnoreCase(key))
					return kaizalaDashboardVO;
			}
		}
		return null;
	}
	
	public static Comparator<UserTypeVO> installedCountDesc = new Comparator<UserTypeVO>() {
		public int compare(UserTypeVO member2, UserTypeVO member1) {

		Long perc2 = member2.getInstalled();
		Long perc1 = member1.getInstalled();
		//descending order of percantages.
		 return perc1.compareTo(perc2);
		}
	}; 
}
