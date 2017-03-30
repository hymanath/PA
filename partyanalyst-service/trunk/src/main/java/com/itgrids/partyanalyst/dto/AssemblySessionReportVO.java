package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class AssemblySessionReportVO {
	
	private Long termId;
	private Long sessionYrId;
	private Long sessionId;
	private Long sessionDate;
	private String summery;
	private Long adminHouseSessionDayId;
	private Long memberId;
	private Long speechAspectId; 
	private Long score;
	//private List<AssemblySessionReportVO> partyList = new ArrayList<AssemblySessionReportVO>(0);
	private List<AssemblySessionReportVO> membersList = new ArrayList<AssemblySessionReportVO>(0);
	private List<AssemblySessionReportVO> scalesList = new ArrayList<AssemblySessionReportVO>(0);
	
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Long getSpeechAspectId() {
		return speechAspectId;
	}
	public void setSpeechAspectId(Long speechAspectId) {
		this.speechAspectId = speechAspectId;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getAdminHouseSessionDayId() {
		return adminHouseSessionDayId;
	}
	public void setAdminHouseSessionDayId(Long adminHouseSessionDayId) {
		this.adminHouseSessionDayId = adminHouseSessionDayId;
	}
	public String getSummery() {
		return summery;
	}
	public void setSummery(String summery) {
		this.summery = summery;
	}
	public Long getTermId() {
		return termId;
	}
	public void setTermId(Long termId) {
		this.termId = termId;
	}
	public Long getSessionYrId() {
		return sessionYrId;
	}
	public void setSessionYrId(Long sessionYrId) {
		this.sessionYrId = sessionYrId;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public Long getSessionDate() {
		return sessionDate;
	}
	public void setSessionDate(Long sessionDate) {
		this.sessionDate = sessionDate;
	}
	/*public List<AssemblySessionReportVO> getPartyList() {
		return partyList;
	}
	public void setPartyList(List<AssemblySessionReportVO> partyList) {
		this.partyList = partyList;
	}*/
	public List<AssemblySessionReportVO> getMembersList() {
		return membersList;
	}
	public void setMembersList(List<AssemblySessionReportVO> membersList) {
		this.membersList = membersList;
	}
	public List<AssemblySessionReportVO> getScalesList() {
		return scalesList;
	}
	public void setScalesList(List<AssemblySessionReportVO> scalesList) {
		this.scalesList = scalesList;
	}
	
	
	
}
