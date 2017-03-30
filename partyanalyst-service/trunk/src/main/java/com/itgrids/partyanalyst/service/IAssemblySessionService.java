package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AdminHouseVO;


public interface IAssemblySessionService 
{
	public List<AdminHouseVO> getAllElecYears();
	public List<AdminHouseVO> getAllSessionNames(Long termId,String sessionYr);
	public List<AdminHouseVO> getNoOfDaysForSession(Long termId,String sessionYear,Long sessionId,String startDateStr,String endDateStr);
	public List<AdminHouseVO> getAllParties();
	public List<AdminHouseVO> getSessionYears(Long termId);
	public List<AdminHouseVO> getDates(Long termId,String sessionYear,Long sessionId);
	public List<AdminHouseVO> getDayWiseDetails(Long adminHseSessionDayId);
	public List<AdminHouseVO> getSpeechAspectList();
	public String deleteMemberDetails(final AdminHouseVO adminHouseVO);
	public String updateMemberSpeechAspectDetails(final AdminHouseVO adminHouseVO);
	public List<AdminHouseVO> getCandidateNameForParty(Long partyId);
}
