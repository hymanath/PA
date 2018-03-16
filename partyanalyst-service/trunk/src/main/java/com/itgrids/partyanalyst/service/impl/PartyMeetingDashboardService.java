package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dto.MeetingSummeryVO;
import com.itgrids.partyanalyst.dto.PartyMeetingDetailsVO;
import com.itgrids.partyanalyst.service.IPartyMeetingDashboardService;

public class PartyMeetingDashboardService implements IPartyMeetingDashboardService{

	private static Logger LOG = Logger.getLogger(PartyMeetingDashboardService.class);
	private IPartyMeetingDAO partyMeetingDAO;
	private IPartyMeetingInviteeDAO partyMeetingInviteeDAO;
	private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO;
			
	public void setPartyMeetingAttendanceDAO(
			IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO) {
		this.partyMeetingAttendanceDAO = partyMeetingAttendanceDAO;
	}

	public void setPartyMeetingInviteeDAO(
			IPartyMeetingInviteeDAO partyMeetingInviteeDAO) {
		this.partyMeetingInviteeDAO = partyMeetingInviteeDAO;
	}

	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}

	public List<PartyMeetingDetailsVO> getPartyMeetingDetailsForDashboard(Long partyMeetingLevelId,Date fromDate,Date toDate,Long locationLevelId,Long locationValue)
	{
		List<PartyMeetingDetailsVO> result = new ArrayList<PartyMeetingDetailsVO>(0);
		try{
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in PartyMeetingDashboardService() Method - ",e);
		}
		return result;
	}
	
	//public MeetingSummeryVO getMeetingsSummeryForDashboard(Long partyMeetingLevelId,String fromDateString,String toDateString,Long partyMeetingTypeId,Long locationLevelId,Long locationValue)
	public MeetingSummeryVO getMeetingsSummeryForDashboard(Long partyMeetingLevelId,String fromDateString,String toDateString,Long partyMeetingTypeId,Long locationLevelId,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId)
	{
		MeetingSummeryVO meetingSummeryVO = new MeetingSummeryVO();
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date fromDate = sdf.parse(fromDateString);
			Date toDate = sdf.parse(toDateString);
			
			List<Long> statesList = new ArrayList<Long>(0);
			statesList.add(stateId);
			
			List<Long> districtList = new ArrayList<Long>(0);
			districtList.add(distId);
			
			List<Long> constituencyList = new ArrayList<Long>(0);
			constituencyList.add(constId);
			
			List<Long> mandalList = new ArrayList<Long>(0);
			List<Long> townList = new ArrayList<Long>(0);
			List<Long> divisonList = new ArrayList<Long>(0);
			List<Long> villageList = new ArrayList<Long>(0);
			List<Long> wardList = new ArrayList<Long>(0);
			
			if(locationLevelId==4){
				String mtdId = manTowDivId.toString();
				char temp = mtdId.charAt(0);
				locationLevelId=Long.parseLong(temp+"");
				if(locationLevelId==1l){
					mandalList.add(Long.parseLong(mtdId.substring(1)));
					locationLevelId = 4l;
				}else if(locationLevelId==2l){
					townList.add(Long.parseLong(mtdId.substring(1)));
					locationLevelId = 5l;
				}else if(locationLevelId==3l){
					divisonList.add(Long.parseLong(mtdId.substring(1)));
					locationLevelId = 6l;
				}
			}
			
			if(locationLevelId==5){
				String vwId = wardPanId.toString();
				char temp = vwId.charAt(0);
				locationLevelId=Long.parseLong(temp+"");
				if(locationLevelId==1l){
					villageList.add(Long.parseLong(vwId.substring(1)));
					locationLevelId=7l;
				}else if(locationLevelId==2l){
					wardList.add(Long.parseLong(vwId.substring(1)));
					locationLevelId=8l;
				}
			}
			
			//List<Long> allPartyMeetingsList = partyMeetingDAO.getPartyMeetingIdsByLevelAndLocation(partyMeetingLevelId,fromDate,toDate,partyMeetingTypeId,locationLevelId,locationValue);
			List<Long> allPartyMeetingsList = partyMeetingDAO.getPartyMeetingIdsByLevelAndLocation(partyMeetingLevelId,fromDate,toDate,partyMeetingTypeId,locationLevelId,statesList,districtList,constituencyList,mandalList,townList,divisonList,villageList,wardList);
			/*List<Long> partyMeetingsList = new ArrayList<Long>(0);
			if(allPartyMeetingsList!=null && allPartyMeetingsList.size()>0){
				partyMeetingsList = partyMeetingAttendanceDAO.getConductedMeetings(allPartyMeetingsList);
			}*/
			List<Long> partyMeetingsList = new ArrayList<Long>(0);
           
            if(allPartyMeetingsList != null && allPartyMeetingsList.size()>0){
                 int filterCount = 300;
                 int i = 0;
                 int j = filterCount;
                 int maxcount = allPartyMeetingsList.size();
                 while (maxcount >0){ 
                     if(maxcount<filterCount)
                         j = i+maxcount;       
                     List<Long>  tempList  = partyMeetingAttendanceDAO.getConductedMeetings(allPartyMeetingsList.subList(i, j));
                        if(tempList != null && tempList.size()>0l){
                        	partyMeetingsList.addAll(tempList);
                        }
                     i=j;
                     maxcount = maxcount-filterCount;
                     j=j+filterCount;
                 }
            }
			
			if(partyMeetingsList != null && partyMeetingsList.size() > 0)
			{
				Long totalInvitees = 0L;
				Long totalNonInvitees = 0L;
				Long totalAbsent = 0L;
				Long totalAttended = 0L;
				Long totalInviteeAttended = 0L;
				Long totalCandidateInvitees = 0L;
				Long totalCommitteeMemberInvitees = 0L;
				Long totalCandidateAttended = 0L;
				Long totalCommitteeMemberAttended = 0L;
				Long totalCandidateInviteeAttended = 0L;
				Long totalCandidateAbsent = 0L;
				Long totalCommitteeMemberInviteeAttended = 0L;
				Long totalCommitteeMemberAbsent = 0L;
				Long totalCandidateNonInvitees = 0L;
				Long totalCommitteeMemberNonInvitees = 0L;
				
				List<String> meetingInvitees = new ArrayList<String>(0);
				List<String> meetingAttendees = new ArrayList<String>(0);
				List<String> meetingAbsentees = new ArrayList<String>(0);
				List<String> candidateInvitees = new ArrayList<String>(0);
				List<String> committeeMemberInvitees = new ArrayList<String>(0);
				List<String> candidateAttendees = new ArrayList<String>(0);
				List<String> committeeMemberAttendees = new ArrayList<String>(0);
				
				List<Object[]> inviteesList = partyMeetingInviteeDAO.getInviteesForPartyMeetings(partyMeetingsList);
				
				if(inviteesList != null && inviteesList.size() > 0)
				{
					totalInvitees = (long)inviteesList.size();
					for(Object[] params : inviteesList)
						meetingInvitees.add(params[0].toString()+"-"+params[1].toString());
				}
				
				List<Object[]> attendeeList = partyMeetingAttendanceDAO.getAttendanceForMeetings(partyMeetingsList);
				
				if(attendeeList != null && attendeeList.size() > 0)
				{
					totalAttended = (long) attendeeList.size();
					for(Object[] params : attendeeList)
						meetingAttendees.add(params[0].toString()+"-"+params[1].toString());
				}
				
				for(String str : meetingInvitees)
				{
					if(!meetingAttendees.contains(str))
					{
						totalAbsent++;
						meetingAbsentees.add(str);
					}
					else
						totalInviteeAttended++;
				}
				totalNonInvitees = totalAttended-totalInviteeAttended;
				
				List<Object[]> candidateInviteesList = partyMeetingInviteeDAO.getPublicRepresentativeInviteesForPartyMeetings(partyMeetingsList);
				if(candidateInviteesList != null && candidateInviteesList.size() > 0)
				{
					totalCandidateInvitees = (long)candidateInviteesList.size();
					for(Object[] params : candidateInviteesList)
						candidateInvitees.add(params[0].toString()+"-"+params[1].toString());
				}
				
				List<Object[]> committeeMemberInviteeList = partyMeetingInviteeDAO.getCommitteeMemberInviteesForPartyMeetings(partyMeetingsList);
				if(committeeMemberInviteeList != null && committeeMemberInviteeList.size() > 0)
				{
					for(Object[] params : committeeMemberInviteeList)
					{
						String str = params[0].toString()+"-"+params[1].toString();
						
						if(!candidateInvitees.contains(str))
						{
							totalCommitteeMemberInvitees++;
							committeeMemberInvitees.add(str);
						}
					}
				}
				
				List<Object[]> candidateAttendeeList = partyMeetingAttendanceDAO.getCandidateAttendanceForMeetings(partyMeetingsList);
				if(candidateAttendeeList != null && candidateAttendeeList.size() > 0)
				{
					totalCandidateAttended = (long)candidateAttendeeList.size();
					for(Object[] params : candidateAttendeeList)
						candidateAttendees.add(params[0].toString()+"-"+params[1].toString());
				}
				
				List<Object[]> committeeMemberAttendeeList = partyMeetingAttendanceDAO.getCommitteeMemberAttendanceForMeetings(partyMeetingsList);
				if(committeeMemberAttendeeList != null && committeeMemberAttendeeList.size() > 0)
				{
					for(Object[] params : committeeMemberAttendeeList)
					{
						String str = params[0].toString()+"-"+params[1].toString();
						if(!candidateAttendees.contains(str))
						{
							totalCommitteeMemberAttended++;
							committeeMemberAttendees.add(str);
						}
					}
				}
				
				for(String str : candidateInvitees)
				{
					if(candidateAttendees.contains(str))
						totalCandidateInviteeAttended++;
					else
						totalCandidateAbsent++;
				}
				totalCandidateNonInvitees = totalCandidateAttended-totalCandidateInviteeAttended;
				
				for(String str : committeeMemberInvitees)
				{
					if(committeeMemberAttendees.contains(str))
						totalCommitteeMemberInviteeAttended++;
					else
						totalCommitteeMemberAbsent++;
				}
				totalCommitteeMemberNonInvitees = totalCommitteeMemberAttended-totalCommitteeMemberInviteeAttended;
				
				meetingSummeryVO.setTotalInvitees(totalInvitees);
				meetingSummeryVO.setTotalAttended(totalAttended);
				meetingSummeryVO.setTotalAbsent(totalAbsent);
				meetingSummeryVO.setTotalNonInvitees(totalNonInvitees);
				meetingSummeryVO.setTotalInviteeAttended(totalInviteeAttended);
				meetingSummeryVO.setTotalCandidateInvitees(totalCandidateInvitees);
				meetingSummeryVO.setTotalCommitteeMemberInvitees(totalCommitteeMemberInvitees);
				meetingSummeryVO.setTotalCandidateAttended(totalCandidateAttended);
				meetingSummeryVO.setTotalCommitteeMemberAttended(totalCommitteeMemberAttended);
				meetingSummeryVO.setTotalCandidateInviteeAttended(totalCandidateInviteeAttended);
				meetingSummeryVO.setTotalCandidateAbsent(totalCandidateAbsent);
				meetingSummeryVO.setTotalCommitteeMemberInviteeAttended(totalCommitteeMemberInviteeAttended);
				meetingSummeryVO.setTotalCommitteeMemberAbsent(totalCommitteeMemberAbsent);
				meetingSummeryVO.setTotalCandidateNonInvitees(totalCandidateNonInvitees);
				meetingSummeryVO.setTotalCommitteeMemberNonInvitees(totalCommitteeMemberNonInvitees);
				meetingSummeryVO.setTotalNoRoleInvitees(totalInvitees-(totalCandidateInvitees+totalCommitteeMemberInvitees));
				meetingSummeryVO.setTotalNoRoleAttended(totalAttended-(totalCandidateAttended+totalCommitteeMemberAttended));
				meetingSummeryVO.setTotalNoRoleAbsent(totalAbsent-(totalCandidateAbsent+totalCommitteeMemberAbsent));
				meetingSummeryVO.setTotalNoRoleNonInvitees(totalNonInvitees-(totalCandidateNonInvitees+totalCommitteeMemberNonInvitees));
				
				
			}
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getMeetingsSummeryForDashboard Method() - ",e);
		}
		return meetingSummeryVO;
	}

}
