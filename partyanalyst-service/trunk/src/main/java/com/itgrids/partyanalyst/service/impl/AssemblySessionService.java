package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IAdminHouseMemberDAO;
import com.itgrids.partyanalyst.dao.IAdminHouseSessionDAO;
import com.itgrids.partyanalyst.dao.IAdminHouseSessionDayDAO;
import com.itgrids.partyanalyst.dao.IAdminHouseTermDAO;
import com.itgrids.partyanalyst.dao.IHouseSessionDAO;
import com.itgrids.partyanalyst.dao.IMemberSpeechAspectDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.ISpeechAspectDAO;
import com.itgrids.partyanalyst.dto.AdminHouseVO;
import com.itgrids.partyanalyst.dto.AssemblySessionReportVO;
import com.itgrids.partyanalyst.model.MemberSpeechAspect;
import com.itgrids.partyanalyst.model.SpeechAspect;
import com.itgrids.partyanalyst.service.IAssemblySessionService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class AssemblySessionService implements IAssemblySessionService{

	
	private final static Logger LOG = Logger.getLogger(AssemblySessionService.class);
	private IAdminHouseMemberDAO adminHouseMemberDAO;
	
	private ISpeechAspectDAO 					speechAspectDAO;
	private TransactionTemplate 				transactionTemplate;
	
	private IAdminHouseTermDAO 					adminHouseTermDAO;
	private CommonMethodsUtilService 	commonMethodsUtilService = new CommonMethodsUtilService();
	private IHouseSessionDAO 					houseSessionDAO;
	private IMemberSpeechAspectDAO 				memberSpeechAspectDAO;
	private IPartyDAO 							partyDAO;
	private IAdminHouseSessionDAO 				adminHouseSessionDAO;
	private IAdminHouseSessionDayDAO            adminHouseSessionDayDAO;
	private IActivityInfoDocumentDAO			activityInfoDocumentDAO;
	
	
	
	
	public IActivityInfoDocumentDAO getActivityInfoDocumentDAO() {
		return activityInfoDocumentDAO;
	}
	public void setActivityInfoDocumentDAO(
			IActivityInfoDocumentDAO activityInfoDocumentDAO) {
		this.activityInfoDocumentDAO = activityInfoDocumentDAO;
	}
	public ISpeechAspectDAO getSpeechAspectDAO() {
		return speechAspectDAO;
	}
	public void setSpeechAspectDAO(ISpeechAspectDAO speechAspectDAO) {
		this.speechAspectDAO = speechAspectDAO;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public IAdminHouseSessionDAO getAdminHouseSessionDAO() {
		return adminHouseSessionDAO;
	}
	public void setAdminHouseSessionDAO(IAdminHouseSessionDAO adminHouseSessionDAO) {
		this.adminHouseSessionDAO = adminHouseSessionDAO;
	}
	public IAdminHouseTermDAO getAdminHouseTermDAO() {
		return adminHouseTermDAO;
	}
	public void setAdminHouseTermDAO(IAdminHouseTermDAO adminHouseTermDAO) {
		this.adminHouseTermDAO = adminHouseTermDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public IHouseSessionDAO getHouseSessionDAO() {
		return houseSessionDAO;
	}
	public void setHouseSessionDAO(IHouseSessionDAO houseSessionDAO) {
		this.houseSessionDAO = houseSessionDAO;
	}
	public IMemberSpeechAspectDAO getMemberSpeechAspectDAO() {
		return memberSpeechAspectDAO;
	}
	public void setMemberSpeechAspectDAO(IMemberSpeechAspectDAO memberSpeechAspectDAO) {
		this.memberSpeechAspectDAO = memberSpeechAspectDAO;
	}
	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}
	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	
	public IAdminHouseMemberDAO getAdminHouseMemberDAO() {
		return adminHouseMemberDAO;
	}
	public void setAdminHouseMemberDAO(IAdminHouseMemberDAO adminHouseMemberDAO) {
		this.adminHouseMemberDAO = adminHouseMemberDAO;
	}
	public IAdminHouseSessionDayDAO getAdminHouseSessionDayDAO() {
		return adminHouseSessionDayDAO;
	}
	public void setAdminHouseSessionDayDAO(IAdminHouseSessionDayDAO adminHouseSessionDayDAO) {
		this.adminHouseSessionDayDAO = adminHouseSessionDayDAO;
	}
	
	
	public List<AdminHouseVO> getAllElecYears(){
		List<AdminHouseVO> returnList = new ArrayList<AdminHouseVO>(0);
		try{
			List<Object[]> yearsList = adminHouseTermDAO.getAllElectionYears();
			if(yearsList != null && yearsList.size() > 0){
				for (Object[] objects : yearsList) {
					AdminHouseVO vo = new AdminHouseVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setDate(commonMethodsUtilService.getStringValueForObject(objects[1]) + "-" +commonMethodsUtilService.getStringValueForObject(objects[2]));
					returnList.add(vo);
				}
			}
		}catch(Exception e){
			 LOG.error("Exception Occured in getAllElecYears() method, Exception - ",e);
		}
		return returnList;
	}
	public List<AdminHouseVO> getAllSessionNames(Long termId,String sessionYr){
		List<AdminHouseVO> returnList = new ArrayList<AdminHouseVO>(0);
		try{
			List<Object[]> sessionsList = adminHouseSessionDAO.getAllSessions(termId,sessionYr);
			if(sessionsList != null && sessionsList.size() > 0){
				for (Object[] objects : sessionsList) {
					AdminHouseVO vo = new AdminHouseVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					returnList.add(vo);
				}
			}
		}catch(Exception e){
			 LOG.error("Exception Occured in getAllSessionNames() method, Exception - ",e);
		}
		return returnList;
	}
	
	public List<AdminHouseVO> getNoOfDaysForSession(Long termId,String sessionYear,Long sessionId,String startDateStr,String endDateStr){
		List<AdminHouseVO> finalList = new ArrayList<AdminHouseVO>(0);
		try{
			Map<Long,AdminHouseVO> sessionListtMap = new LinkedHashMap<Long,AdminHouseVO>();
			List<Long> sessionIds = new ArrayList<Long>(0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = null;
			Date endDate = null;
			if(startDateStr != null && endDateStr != null && !startDateStr.toString().isEmpty() && !endDateStr.toString().isEmpty() ){
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			}
			if(sessionId == null || sessionId.longValue() == 0l){
				List<AdminHouseVO> sessionList = getAllSessionNames(termId,sessionYear);
				if(sessionList != null && sessionIds.size() > 0l ){
					for (AdminHouseVO adminHouseVO : sessionList) {
						sessionIds.add(adminHouseVO.getId());
					}
				}
			}else{
				sessionIds.add(sessionId);
				
			}
			List<Object[]> sesCuntList = memberSpeechAspectDAO.getNoOfDaysForSession(termId, sessionYear, sessionIds, startDate, endDate);
			if(sesCuntList != null && sesCuntList.size() > 0l){
				for (Object[] objects : sesCuntList) {
					AdminHouseVO vo = new AdminHouseVO(); 
						vo.setStartDate(commonMethodsUtilService.getStringValueForObject(objects[0]));
						vo.setSessionId(commonMethodsUtilService.getLongValueForObject(objects[1]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[2]));
						vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[3]));
						
						vo.setAdminHouseSessionId(commonMethodsUtilService.getLongValueForObject(objects[4]));
						sessionListtMap.put(commonMethodsUtilService.getLongValueForObject(objects[4]),vo);
					
				}
			}
			Map<Long,AdminHouseVO> dayMap = new HashMap<Long,AdminHouseVO>();
			List<Object[]> dayWiseList = memberSpeechAspectDAO.getDayWisePartyWiseCount(termId, sessionYear, sessionIds, startDate, endDate);
			if(dayWiseList != null && dayWiseList.size() >0l){
				for (Object[] objects : dayWiseList) {
					AdminHouseVO vo = sessionListtMap.get(commonMethodsUtilService.getLongValueForObject(objects[3]));
					if(vo != null){
						AdminHouseVO dayVO = dayMap.get(commonMethodsUtilService.getLongValueForObject(objects[4]));
						if(dayVO != null){
						
						AdminHouseVO  partyVO = getMatchedVOList(dayVO.getPartyList(), commonMethodsUtilService.getLongValueForObject(objects[1]));
							 if(partyVO != null){
								 partyVO.setCount(partyVO.getCount()+commonMethodsUtilService.getLongValueForObject(objects[0]));
								 partyVO.setPartyName(partyVO.getName() + "-" +partyVO.getCount());
								 dayVO.setCount(dayVO.getCount()+partyVO.getCount());//Day Wise total Members Count
								// partyVO.setPartyId(vo.getPartyId());
							 }else{
								 partyVO = new AdminHouseVO();
								 partyVO.setPartyId(commonMethodsUtilService.getLongValueForObject(objects[1]));
								 partyVO.setName(commonMethodsUtilService.getStringValueForObject(objects[5]));
								 partyVO.setCount(partyVO.getCount()+commonMethodsUtilService.getLongValueForObject(objects[0]));
								 partyVO.setPartyName(partyVO.getName() + "-" +partyVO.getCount());
								 dayVO.setCount(dayVO.getCount()+partyVO.getCount());//Day Wise total Members Count
								dayVO.getPartyList().add(partyVO);
							 }
						   }else{
							   dayVO = new AdminHouseVO();
							   dayVO.setDate(commonMethodsUtilService.getStringValueForObject(objects[2]));
							   dayVO.setStartDate(commonMethodsUtilService.getStringValueForObject(objects[2]));
								dayVO.setAdminHouseSessionDayId(commonMethodsUtilService.getLongValueForObject(objects[4]));
								
								if(commonMethodsUtilService.getLongValueForObject(objects[1]) != null && commonMethodsUtilService.getLongValueForObject(objects[1]) > 0l){
									AdminHouseVO  partyVO1 = new AdminHouseVO();
									partyVO1.setPartyId(commonMethodsUtilService.getLongValueForObject(objects[1]));
									 partyVO1.setName(commonMethodsUtilService.getStringValueForObject(objects[5]));
									 partyVO1.setCount(partyVO1.getCount()+commonMethodsUtilService.getLongValueForObject(objects[0]));
									 partyVO1.setPartyName(partyVO1.getName() + "-" +partyVO1.getCount());
									 dayVO.setCount(dayVO.getCount()+partyVO1.getCount());
									dayVO.getPartyList().add(partyVO1);
								}
								
								vo.getCandidateList().add(dayVO);
							   dayMap.put(commonMethodsUtilService.getLongValueForObject(objects[4]), dayVO);
						   }
						}
					}
				}
			if(commonMethodsUtilService.isMapValid(sessionListtMap)){
				finalList.addAll(sessionListtMap.values());
			}
				
		}catch(Exception e){
			LOG.error("Exception Occured in getNoOfDaysForSession() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<AdminHouseVO> getAllParties(){
		List<AdminHouseVO> returnList = new ArrayList<AdminHouseVO>(0);
		try{
			List<Object[]> partyList = adminHouseMemberDAO.getAllPartyNames();
			if(partyList != null && partyList.size() > 0){
				for (Object[] objects : partyList) {
					AdminHouseVO vo = new AdminHouseVO();
					vo.setPartyId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setPartyName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					returnList.add(vo);
				}
			}
		}catch(Exception e){
			 LOG.error("Exception Occured in getAllParties() method, Exception - ",e);
		}
		return returnList;
	}
	
	public AdminHouseVO getMatchedVOList(List<AdminHouseVO> list, Long partyId)
	{
		try {
			if(list != null && list.size()>0)
			{
				for (AdminHouseVO adminHouseVO : list) {
					if(adminHouseVO.getPartyId().longValue() == partyId.longValue())
						return adminHouseVO;
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in getMatchedVOList() method, Exception - ",e);
		}
		return null;
	}
	
	public List<AdminHouseVO> getSessionYears(Long termId){
		List<AdminHouseVO> returnList = new ArrayList<AdminHouseVO>(0);
		try{
			List<Object[]> sessionsYrsList = adminHouseSessionDAO.getAllSessionYears(termId);
			if(sessionsYrsList != null && sessionsYrsList.size() > 0){
				for (Object[] objects : sessionsYrsList) {
					AdminHouseVO vo = new AdminHouseVO();
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[0]));
					vo.setPartyName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					returnList.add(vo);
				}
			}
		}catch(Exception e){
			 LOG.error("Exception Occured in getSessionYears() method, Exception - ",e);
		}
		return returnList;
	}
	
	public List<AdminHouseVO> getDates(Long adminSessionId){
		List<AdminHouseVO> returnList = new ArrayList<AdminHouseVO>(0);
		try{
			Object[] datesList = adminHouseSessionDayDAO.getDates(adminSessionId);
			if(datesList != null){
					AdminHouseVO vo = new AdminHouseVO();
					vo.setName(commonMethodsUtilService.getStringValueForObject(datesList[0]));
					vo.setPartyName(commonMethodsUtilService.getStringValueForObject(datesList[1]));
					returnList.add(vo);
			}
		}catch(Exception e){
			 LOG.error("Exception Occured in getAllSessionYears() method, Exception - ",e);
		}
		return returnList;
	} 
	
	public List<AdminHouseVO> getDayWiseDetails(Long adminHseSessionDayId,Long partyId){
		List<AdminHouseVO> finalList = new ArrayList<AdminHouseVO>(0);
		try{
			Map<Long,AdminHouseVO> canDetailedMap = new LinkedHashMap<Long, AdminHouseVO>(0);
			Map<Long,AdminHouseVO> partyDetailsMap = new LinkedHashMap<Long, AdminHouseVO>(0);
			List<Object[]> cuntDetailsList = memberSpeechAspectDAO.getDayWiseCountDetails(adminHseSessionDayId,partyId);
			if(cuntDetailsList != null && cuntDetailsList.size() > 0l){
				for (Object[] objects : cuntDetailsList) {
					AdminHouseVO partyVO = partyDetailsMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(partyVO == null){
						partyVO = new AdminHouseVO();
						partyVO.setPartyList(getSpeechAspectList());
						partyVO.setPartyId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						partyVO.setPartyName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						partyDetailsMap.put(partyVO.getPartyId(), partyVO);
					}
							
							
							
							
					//}else{
						AdminHouseVO candidateVO = canDetailedMap.get(commonMethodsUtilService.getLongValueForObject(objects[3]));
						if(candidateVO == null){
							candidateVO = new AdminHouseVO();
							candidateVO.setPartyList(getSpeechAspectList());
							candidateVO.setCandidateId(commonMethodsUtilService.getLongValueForObject(objects[3]));
							partyVO.getCandidateList().add(candidateVO);
							canDetailedMap.put(candidateVO.getCandidateId(), candidateVO);
						}
								
								//candidateVO.setCandidateId(commonMethodsUtilService.getLongValueForObject(objects[3]));
								candidateVO.setName(commonMethodsUtilService.getStringValueForObject(objects[2]));
								candidateVO.setAdminHouseMemberId(commonMethodsUtilService.getLongValueForObject(objects[4]));
								AdminHouseVO  speechAspectVO = getMatchedVOAspectList(candidateVO.getPartyList(), commonMethodsUtilService.getLongValueForObject(objects[5]));
								 if(speechAspectVO != null){
									 speechAspectVO.setScore(speechAspectVO.getScore()+Double.valueOf(objects[6] != null ? objects[6].toString():"0.0"));
									 candidateVO.setTotal(candidateVO.getTotal()+speechAspectVO.getScore());
								 }
								
								
						/*}else{
							AdminHouseVO  speechAspectVO = getMatchedVOAspectList(candidateVO.getPartyList(), commonMethodsUtilService.getLongValueForObject(objects[5]));
							 if(speechAspectVO != null){
								 speechAspectVO.setScore(speechAspectVO.getScore()+Double.valueOf(objects[6] != null ? objects[6].toString():"0.0"));
								 candidateVO.setTotal(candidateVO.getTotal()+speechAspectVO.getScore());
							 }*/
						}
					}
			
			if(commonMethodsUtilService.isMapValid(partyDetailsMap)){
				for (Map.Entry<Long, AdminHouseVO> entry : partyDetailsMap.entrySet()){
					AdminHouseVO partyvo = entry.getValue();
					Double subCount = 0.0d;
					Double presCnt = 0.0d;
					Double countrAttckCnt = 0.0d;
					Double bodyLangCnt = 0.0d;
						List<AdminHouseVO> candidateList = partyvo.getCandidateList();
						if(commonMethodsUtilService.isListOrSetValid(candidateList)){
							for(AdminHouseVO candVO : candidateList){
								List<AdminHouseVO> astectList = candVO.getPartyList();
								if(commonMethodsUtilService.isListOrSetValid(astectList)){
										for(AdminHouseVO aspectVO : astectList){
											if(aspectVO.getSpeechAsceptId().longValue() == 1l){
												subCount = subCount+aspectVO.getScore();
											}
											if(aspectVO.getSpeechAsceptId().longValue() == 2l){
												presCnt = presCnt+aspectVO.getScore();
											}
											if(aspectVO.getSpeechAsceptId().longValue() == 3l){
												countrAttckCnt = countrAttckCnt+aspectVO.getScore();
											}
											if(aspectVO.getSpeechAsceptId().longValue() == 4l){
												bodyLangCnt = bodyLangCnt+aspectVO.getScore();
											}
										}
									}
								}
							}
						Long candidatesCount = (long) candidateList.size();
						partyvo.setCount(candidatesCount);
						partyvo.setAvgSubCount(subCount/partyvo.getCount());
						partyvo.setAvgPresCount(presCnt/partyvo.getCount());
						partyvo.setAvgCunterAttCount(countrAttckCnt/partyvo.getCount());
						partyvo.setAvgBdyLanCount(bodyLangCnt/partyvo.getCount());
					finalList.add(partyvo);
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured in getDayWiseDetails() method, Exception - ",e);
		}
		return finalList;
	}
	public List<AdminHouseVO> getSpeechAspectList(){
		List<AdminHouseVO> returnList = new ArrayList<AdminHouseVO>(0);
		try{
			List<Object[]> spechAspList = speechAspectDAO.getAllSpeechAspectNames();
			if(spechAspList != null && spechAspList.size() > 0l){
				for (Object[] objects : spechAspList) {
					AdminHouseVO vo = new AdminHouseVO();
					 vo.setSpeechAsceptId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					 vo.setAspect(commonMethodsUtilService.getStringValueForObject(objects[1]));
					 returnList.add(vo);
				}
			}
		}catch (Exception e) {
			LOG.error("Exception Occured in getSpeechAspectList() method, Exception - ",e);
		}
		return returnList;
	}
	public AdminHouseVO getMatchedVOAspectList(List<AdminHouseVO> list, Long speechAspectId)
	{
		try {
			if(list != null && list.size()>0)
			{
				for (AdminHouseVO adminHouseVO : list) {
					if(adminHouseVO.getSpeechAsceptId().longValue() == speechAspectId.longValue())
						return adminHouseVO;
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in getMatchedVOList() method, Exception - ",e);
		}
		return null;
	}
	
	public String updateMemberSpeechAspectDetails(final AdminHouseVO adminHouseVO){
		String status = null;
		try{
			if(adminHouseVO != null){
				List<AdminHouseVO> speechAspectList = adminHouseVO.getCandidateList();
				if(speechAspectList != null && speechAspectList.size() > 0l){
					for (AdminHouseVO  adminHousevo: speechAspectList) {
							List<MemberSpeechAspect> memberSpeechList = memberSpeechAspectDAO.updateMemberDetails(adminHouseVO.getAdminHouseMemberId(), adminHouseVO.getAdminHouseSessionDayId(), adminHousevo.getSpeechAsceptId());
							MemberSpeechAspect memberSpeechAspect = memberSpeechList.get(0);
							if(memberSpeechAspect != null){
									memberSpeechAspect.setScore(Double.valueOf(adminHousevo.getValue()));								
									memberSpeechAspect.setUpdatedById(adminHouseVO.getUserId());								
									memberSpeechAspect.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
									memberSpeechAspectDAO.save(memberSpeechAspect);		
								status = "success";
							}
						}
					}
				}
		}catch(Exception e){
			status = "failure";
			 LOG.error("Exception Occured in updateMemberSpeechAspectDetails() method, Exception - ",e);
		}
		return status;
	}
	public String deleteMemberDetails(final AdminHouseVO adminHouseVO){
		String status = null;
		try{
			if(adminHouseVO != null){
				List<AdminHouseVO> speechAspectList = adminHouseVO.getCandidateList();
				if(speechAspectList != null && speechAspectList.size() > 0l){
					for (AdminHouseVO  adminHousevo: speechAspectList) {
							List<MemberSpeechAspect> memberSpeechList = memberSpeechAspectDAO.updateMemberDetails(adminHouseVO.getAdminHouseMemberId(), adminHouseVO.getAdminHouseSessionDayId(), adminHousevo.getSpeechAsceptId());
							MemberSpeechAspect memberSpeechAspect = memberSpeechList.get(0);
							if(memberSpeechAspect != null){
								memberSpeechAspect.setIsDeleted("Y");
								memberSpeechAspect.setUpdatedById(adminHouseVO.getUserId());
								memberSpeechAspect.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
								memberSpeechAspectDAO.save(memberSpeechAspect);
								status = "success";
							}
						}
					}
				}
		}catch(Exception e){
			status = "failure";
			 LOG.error("Exception Occured in deleteMemberDetails() method, Exception - ",e);
		}
		return status;
	}
	public List<AdminHouseVO> getCandidateNameForParty(Long partyId){
		List<AdminHouseVO> returnList = new ArrayList<AdminHouseVO>(0);
		try{
			List<Object[]> partyList = adminHouseMemberDAO.getcandateNameForPartyId(partyId);
			if(partyList != null && partyList.size() > 0){
				for (Object[] objects : partyList) {
					AdminHouseVO vo = new AdminHouseVO();
					vo.setAdminHouseMemberId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					returnList.add(vo);
				}
			}
		}catch(Exception e){
			 LOG.error("Exception Occured in getCandidateNameForParty() method, Exception - ",e);
		}
		return returnList;
	}
	public String saveAssemblySessionCanScoreDetails( AssemblySessionReportVO vo){
		 String resultStatus = new String();
		try{
			LOG.info("Enterd into saveMemberSpeechAspect method in AssemblySessionService class");
			 
			if(vo != null && vo.getMembersList() != null && vo.getMembersList().size() > 0){
				for(AssemblySessionReportVO memberVO : vo.getMembersList() ){
					
					if(memberVO.getScalesList() != null && memberVO.getScalesList().size() > 0){
						Long aspectId = 0l;
						for(AssemblySessionReportVO scaleVO : memberVO.getScalesList()){
							aspectId = aspectId+1;
							MemberSpeechAspect memberSpeechAspect  = memberSpeechAspectDAO.getPrimaryKey(vo.getMembersList().get(0).getAdminHouseSessionDayId(),memberVO.getMemberId(),aspectId);
							if(memberSpeechAspect == null){
								memberSpeechAspect = new MemberSpeechAspect();
							}
							
							SpeechAspect  speechAspect = speechAspectDAO.get(aspectId);
							if(speechAspect != null){
								memberSpeechAspect.setSpeechAspectId(speechAspect.getSpeechAspectId());
							}
							memberSpeechAspect.setIsDeleted("N");
							memberSpeechAspect.setAdminHouseSessionDayId(vo.getMembersList().get(0).getAdminHouseSessionDayId());
							memberSpeechAspect.setScore(Double.valueOf(scaleVO.getScore()));
							memberSpeechAspect.setAdminHouseMemberId(memberVO.getMemberId());
							memberSpeechAspect.setInsertedById(vo.getUserId());
							memberSpeechAspect.setUpdatedById(vo.getUserId());
							memberSpeechAspect.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							memberSpeechAspect.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
							
							memberSpeechAspectDAO.save(memberSpeechAspect);
						}
					}
				}
			}
			 resultStatus = "SUCCESS";
			  return resultStatus;
		
}catch(Exception e){
	LOG.error("Error occured in saveAssemblySessionCanScoreDetails method in AssemblySessionService class",e);
	 resultStatus = "FAILURE";
		}
		return resultStatus;
	}
	
	public List<AdminHouseVO> getDatesForSaving(Long adminHuSessionId){
		List<AdminHouseVO> returnList = new ArrayList<AdminHouseVO>(0);
		try{
			List<Object[]> datesList = adminHouseSessionDayDAO.getSingleDate(adminHuSessionId);
			if(datesList != null && datesList.size() > 0l){
				for (Object[] objects : datesList) {
					AdminHouseVO vo = new AdminHouseVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setDate(commonMethodsUtilService.getStringValueForObject(objects[1]));
					returnList.add(vo);
				}
					
			}
		}catch(Exception e){
			 LOG.error("Exception Occured in getDatesForSaving() method, Exception - ",e);
		}
		return returnList;
	} 
	
	public List<AdminHouseVO> setDayWiseImagesDetails(Long locationId){
		List<AdminHouseVO> returnList = new ArrayList<AdminHouseVO>(0);
		try{
			List<Object[]> datesList = activityInfoDocumentDAO.setDayWiseImagesDetails(locationId);
			
			if(datesList!=null && !datesList.isEmpty()){
				for(Object[] obj : datesList){
					AdminHouseVO vo = new AdminHouseVO();
					
					vo.setDate(commonMethodsUtilService.getStringValueForObject(obj[0]));
					vo.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));
					vo.setCount(commonMethodsUtilService.getLongValueForObject(obj[2]));
					returnList.add(vo);
				}
			}
			
		}catch(Exception e){
			 LOG.error("Exception Occured in setDayWiseImagesDetails() method, Exception - ",e);
		}
		return returnList;
	}
	
}

