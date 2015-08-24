package com.itgrids.partyanalyst.service.impl;

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
	
	public MeetingSummeryVO getMeetingsSummeryForDashboard(Long partyMeetingLevelId,Date fromDate,Date toDate,Long partyMeetingTypeId,Long locationLevelId,Long locationValue)
	{
		MeetingSummeryVO meetingSummeryVO = new MeetingSummeryVO();
		try{
			List<Long> partyMeetingsList = partyMeetingDAO.getPartyMeetingIdsByLevelAndLocation(partyMeetingLevelId,fromDate,toDate,partyMeetingTypeId,locationLevelId,locationValue);
			
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
				Long totalNonInviteeCandidate = 0L;
				Long totalNonInviteeCommitteeMember = 0L;
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
				
				List<Object[]> committeeMemberInviteeList = partyMeetingInviteeDAO.getPublicRepresentativeInviteesForPartyMeetings(partyMeetingsList);
				if(committeeMemberInviteeList != null && committeeMemberInviteeList.size() > 0)
				{
					for(Object[] params : candidateInviteesList)
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
				
				for(String str : candidateAttendees)
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
				meetingSummeryVO.setTotalNonInviteeCandidate(totalNonInviteeCandidate);
				meetingSummeryVO.setTotalNonInviteeCommitteeMember(totalNonInviteeCommitteeMember);
				meetingSummeryVO.setTotalCommitteeMemberAbsent(totalCommitteeMemberAbsent);
				meetingSummeryVO.setTotalCandidateNonInvitees(totalCandidateNonInvitees);
				meetingSummeryVO.setTotalCommitteeMemberNonInvitees(totalCommitteeMemberNonInvitees);
			}
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getMeetingsSummeryForDashboard Method() - ",e);
		}
		return meetingSummeryVO;
	}

}
