package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAdminHouseMemberAttendanceDAO;
import com.itgrids.partyanalyst.dao.IAdminHouseMemberDAO;
import com.itgrids.partyanalyst.dao.IAdminHouseMemberQuestionDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.service.IMpAndMlaAttendanceService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class MpAndMlaAttendanceService implements IMpAndMlaAttendanceService{

	private static Logger LOG = Logger.getLogger(MpAndMlaAttendanceService.class);
	private ITdpCadreDAO tdpCadreDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IAdminHouseMemberDAO adminHouseMemberDAO;
	private IAdminHouseMemberAttendanceDAO adminHouseMemberAttendanceDAO;
	private IAdminHouseMemberQuestionDAO adminHouseMemberQuestionDAO;
	
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public IAdminHouseMemberDAO getAdminHouseMemberDAO() {
		return adminHouseMemberDAO;
	}
	public void setAdminHouseMemberDAO(IAdminHouseMemberDAO adminHouseMemberDAO) {
		this.adminHouseMemberDAO = adminHouseMemberDAO;
	}
	public IAdminHouseMemberAttendanceDAO getAdminHouseMemberAttendanceDAO() {
		return adminHouseMemberAttendanceDAO;
	}
	public void setAdminHouseMemberAttendanceDAO(
			IAdminHouseMemberAttendanceDAO adminHouseMemberAttendanceDAO) {
		this.adminHouseMemberAttendanceDAO = adminHouseMemberAttendanceDAO;
	}
	
	public IAdminHouseMemberQuestionDAO getAdminHouseMemberQuestionDAO() {
		return adminHouseMemberQuestionDAO;
	}
	public void setAdminHouseMemberQuestionDAO(
			IAdminHouseMemberQuestionDAO adminHouseMemberQuestionDAO) {
		this.adminHouseMemberQuestionDAO = adminHouseMemberQuestionDAO;
	}
	public List<IdAndNameVO> getSessionWiseTopAbsentMembersInfo(Long sessionId,String startDateStr,String endDateStr)
	{
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		List<Object[]>	membersList = null; 
		IdAndNameVO vo = null;
		try {
			 Date startDate=new SimpleDateFormat("MM/dd/yyyy").parse(startDateStr);
			 Date endDate=new SimpleDateFormat("MM/dd/yyyy").parse(endDateStr);
			 
			 membersList = adminHouseMemberAttendanceDAO.getSessionWiseTopAbsentMembersInfo(sessionId,startDate, endDate);
			 if(membersList != null && membersList.size() > 0){
				 for (Object[] obj : membersList) {
					 vo = new IdAndNameVO();
					 vo.setId(obj[0] !=null ? (Long)obj[0]:0l);
					 vo.setInviteeCount(obj[1] !=null ? (Long)obj[1]:0l);//attendentMembers
					 
					 returnList.add(vo);
				}
			 }
		} catch (Exception e) {
			LOG.error("Exception raised in getSessionWiseTopAbsentMembersInfo in MpAndMlaAttendanceService service", e);
		}
		
		return returnList;
		
	}
	public List<IdAndNameVO> getPartyWiseAttendanceInfo()
	{
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		List<Object[]>	partiesWiseList = null; 
		IdAndNameVO vo = null;
		try {
			partiesWiseList = adminHouseMemberAttendanceDAO.getPartyWiseAttendanceInfo();
			if (partiesWiseList != null && partiesWiseList.size() > 0){
				for (Object[] obj : partiesWiseList) {
					vo = new IdAndNameVO();
					vo.setId(obj[0] !=null ? (Long)obj[0]:0l);
					vo.setPartyName(obj[1]!=null?obj[1].toString():"");//PartyName
					vo.setSessionNo(obj[0] !=null ? (Long)obj[0]:0l);//sessionNo
					vo.setApTotal(obj[0] !=null ? (Long)obj[0]:0l);//TotalMembers
					vo.setTsTotal(obj[0] !=null ? (Long)obj[0]:0l);//Preseties
					vo.setApNow(vo.getApTotal() - vo.getTsTotal());//Abseties
					vo.setInviteeCount(obj[0] !=null ? (Long)obj[0]:0l);//Questions
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyWiseAttendanceInfo in MpAndMlaAttendanceService service", e);
		}
		
		return returnList;
		
	}
	public List<IdAndNameVO> getMPWiseAttendanceInfo()
	{
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		List<Object[]>	mpWiseList = null; 
		IdAndNameVO vo = null;
		try {
			mpWiseList = adminHouseMemberAttendanceDAO.getMPWiseAttendanceInfo();
			if (mpWiseList != null && mpWiseList.size() > 0){
				for (Object[] obj : mpWiseList) {
					vo = new IdAndNameVO();
					vo.setId(obj[0] !=null ? (Long)obj[0]:0l);
					vo.setPartyName(obj[1]!=null?obj[1].toString():"");//PartyName
					vo.setName(obj[1]!=null?obj[1].toString():"");//NameOfMember
					vo.setSessionNo(obj[0] !=null ? (Long)obj[0]:0l);//SessionNo
					vo.setStartTime(obj[1]!=null?obj[1].toString():"");//fromdate
					vo.setEndTime(obj[1]!=null?obj[1].toString():"");//endDate
					vo.setApTotal(obj[0] !=null ? (Long)obj[0]:0l);//holiDays
					vo.setTsTotal(obj[0] !=null ? (Long)obj[0]:0l);//presentDays
					vo.setApNow(obj[0] !=null ? (Long)obj[0]:0l);//AbsetDays
					vo.setInviteeCount(vo.getApTotal()+vo.getTsTotal()+vo.getApNow());//totalDays
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getMPWiseAttendanceInfo in MpAndMlaAttendanceService service", e);
		}
		
		return returnList;
		
	}
	public List<IdAndNameVO> getLeaderWiseQuestionsInfo(List<Long> questionIdsList)
	{
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		List<Object[]>	questionsList = null; 
		IdAndNameVO vo = null;
		try {
			//date-0,question-1,memberName-2,remarks-3
			questionsList = adminHouseMemberQuestionDAO.getLeaderWiseQuestionsInfo(questionIdsList);
			if (questionsList != null && questionsList.size() > 0){
				for (Object[] obj : questionsList){
					vo = new IdAndNameVO();
					vo.setStartTime(obj[0]!=null?obj[0].toString():"");
					vo.setQuestion(StringEscapeUtils.unescapeJava(obj[1]!=null?obj[1].toString():""));
					vo.setName(obj[2]!=null?obj[2].toString():"");
					vo.setIsCsd(obj[3]!=null?obj[3].toString():"");
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getLeaderWiseQuestionsInfo in MpAndMlaAttendanceService service", e);
		}
		return returnList;
		
	}
}
