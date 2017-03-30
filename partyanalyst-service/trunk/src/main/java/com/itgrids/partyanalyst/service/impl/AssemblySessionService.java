package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAdminHouseSessionDAO;
import com.itgrids.partyanalyst.dao.IAdminHouseTermDAO;
import com.itgrids.partyanalyst.dao.IHouseSessionDAO;
import com.itgrids.partyanalyst.dao.IMemberSpeechAspectDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.AdminHouseVO;
import com.itgrids.partyanalyst.service.IAssemblySessionService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class AssemblySessionService implements IAssemblySessionService{

	
	private final static Logger LOG = Logger.getLogger(AssemblySessionService.class);
	private IAdminHouseTermDAO adminHouseTermDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IHouseSessionDAO houseSessionDAO;
	private IMemberSpeechAspectDAO memberSpeechAspectDAO;
	private IPartyDAO partyDAO;
	private IAdminHouseSessionDAO adminHouseSessionDAO;
	
	
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
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
			Date startDate = null;
			Date endDate = null;
			if(startDateStr != null && endDateStr != null){
				startDate = sdf.parse(endDateStr);
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
			List<Object[]> dayWiseList = memberSpeechAspectDAO.getDayWisePartyWiseCount(termId, sessionYear, sessionIds, startDate, endDate);
			if(dayWiseList != null && dayWiseList.size() >0l){
				for (Object[] objects : dayWiseList) {
					AdminHouseVO vo = sessionListtMap.get(commonMethodsUtilService.getLongValueForObject(objects[3]));
					if(vo != null){
						vo = new AdminHouseVO();
						vo.setDate(commonMethodsUtilService.getStringValueForObject(objects[2]));
						vo.setPartyList(getAllParties());
						AdminHouseVO  partyVO = getMatchedVOList(vo.getPartyList(), commonMethodsUtilService.getLongValueForObject(objects[1]));
							 if(partyVO != null){
								 partyVO.setCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
								 partyVO.setPartyName(vo.getPartyName() + "-" +partyVO.getCount());
								 partyVO.setPartyId(vo.getPartyId());
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
			List<Object[]> partyList = partyDAO.getAllPartyNames();
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
	
	public List<AdminHouseVO> getDates(Long termId,String sessionYear,Long sessionId){
		List<AdminHouseVO> returnList = new ArrayList<AdminHouseVO>(0);
		try{
			Object[] datesList = adminHouseSessionDAO.getDates(termId, sessionYear, sessionId);
			if(datesList != null){
				//for (Object[] objects : datesList) {
					AdminHouseVO vo = new AdminHouseVO();
					vo.setName(commonMethodsUtilService.getStringValueForObject(datesList[0]));
					vo.setPartyName(commonMethodsUtilService.getStringValueForObject(datesList[1]));
					returnList.add(vo);
				//}
			}
		}catch(Exception e){
			 LOG.error("Exception Occured in getAllSessionYears() method, Exception - ",e);
		}
		return returnList;
	} 
}

