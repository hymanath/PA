package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class PartyMeetingDataVO implements Serializable{
	
	private Long id;
	private String name;
	private Long totalMeetings = 0l;
	private Long conductedMeetings = 0l;
	private Long notConductedMeetings = 0l;
	private Long maybeeMeetings = 0l;
	private Long notUpdatedCount = 0l;
	
	private Long partyMeetingId;
	private String partyMeetingName;
	private String remarks;
	
	private List<PartyMeetingDataVO> meetingsList;
	private List<PartyMeetingDataVO> levelList = new ArrayList<PartyMeetingDataVO>();
	private Map<Long,PartyMeetingDataVO> subMap;
	private String meetingLevel;
	private String conductedDate;
	private String momStatus;
	private String atrStatus;
	private Long invitedCount=0L;
	private Long attendedCount = 0L;
	private Long inviteeAttendedCount = 0L;
	private Long nonInviteesCount=0L;
	private Long absentCount =0L;
	private Set<Long> attendedIds =new HashSet<Long>();
	private Set<Long> inviteeIds =new HashSet<Long>();
	private Set<Long> recentInviteeIds =new HashSet<Long>();
	private Long imagesCnt = 0l;
	private Long meetingId;
	private Long recentMeetingInviteesCnt = 0l;
	private Long recentImagesCnt = 0l;
	private Set<Long> recentAttendedIds = new HashSet<Long>();
	private Long recentInviteeAttended = 0l;
	private Long recentAbcent = 0l;
	private Long recentAttended = 0l;
	private Long recentLate = 0l;
	private Long recentNonInvitee = 0l;
	private String attendedPerc;
	private String abcentPerc;
	private String NonInviteePerc;
	private List<PartyMeetingDataVO> datesList = new ArrayList<PartyMeetingDataVO>(0);
	private String month;
	private Long yesCount = 0L;
	private Long noCount = 0L;
	private Long maybeCount = 0L;
	private Long lateCount = 0l;
	private String latePerc;
	private String yesPerc;
	private String noPerc;
	private String mayBePerc;
	private String totalPerc;
	private String NotUpdatePerc;
	private Long designationId = 0l;
	private String designationname;
	private List<String> stringList;
	public PartyMeetingDataVO(){}
	
	
	
	public List<String> getStringList() {
		return stringList;
	}



	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}



	public String getYesPerc() {
		return yesPerc;
	}



	public void setYesPerc(String yesPerc) {
		this.yesPerc = yesPerc;
	}



	public String getNoPerc() {
		return noPerc;
	}



	public void setNoPerc(String noPerc) {
		this.noPerc = noPerc;
	}



	public String getMayBePerc() {
		return mayBePerc;
	}



	public void setMayBePerc(String mayBePerc) {
		this.mayBePerc = mayBePerc;
	}



	public String getTotalPerc() {
		return totalPerc;
	}



	public void setTotalPerc(String totalPerc) {
		this.totalPerc = totalPerc;
	}



	public String getNotUpdatePerc() {
		return NotUpdatePerc;
	}



	public void setNotUpdatePerc(String notUpdatePerc) {
		NotUpdatePerc = notUpdatePerc;
	}



	public Long getRecentAttended() {
		return recentAttended;
	}



	public void setRecentAttended(Long recentAttended) {
		this.recentAttended = recentAttended;
	}



	public Set<Long> getRecentAttendedIds() {
		return recentAttendedIds;
	}



	public void setRecentAttendedIds(Set<Long> recentAttendedIds) {
		this.recentAttendedIds = recentAttendedIds;
	}



	public Long getRecentInviteeAttended() {
		return recentInviteeAttended;
	}



	public void setRecentInviteeAttended(Long recentInviteeAttended) {
		this.recentInviteeAttended = recentInviteeAttended;
	}



	public Long getRecentAbcent() {
		return recentAbcent;
	}



	public void setRecentAbcent(Long recentAbcent) {
		this.recentAbcent = recentAbcent;
	}



	public Long getRecentLate() {
		return recentLate;
	}



	public void setRecentLate(Long recentLate) {
		this.recentLate = recentLate;
	}



	public Long getRecentNonInvitee() {
		return recentNonInvitee;
	}



	public void setRecentNonInvitee(Long recentNonInvitee) {
		this.recentNonInvitee = recentNonInvitee;
	}



	public String getLatePerc() {
		return latePerc;
	}
	public void setLatePerc(String latePerc) {
		this.latePerc = latePerc;
	}

	public Long getLateCount() {
		return lateCount;
	}
	public void setLateCount(Long lateCount) {
		this.lateCount = lateCount;
	}
	public String getAttendedPerc() {
		return attendedPerc;
	}
	public void setAttendedPerc(String attendedPerc) {
		this.attendedPerc = attendedPerc;
	}
	public String getAbcentPerc() {
		return abcentPerc;
	}
	public void setAbcentPerc(String abcentPerc) {
		this.abcentPerc = abcentPerc;
	}



	public String getNonInviteePerc() {
		return NonInviteePerc;
	}



	public void setNonInviteePerc(String nonInviteePerc) {
		NonInviteePerc = nonInviteePerc;
	}



	public Set<Long> getRecentInviteeIds() {
		return recentInviteeIds;
	}



	public void setRecentInviteeIds(Set<Long> recentInviteeIds) {
		this.recentInviteeIds = recentInviteeIds;
	}



	public Long getRecentImagesCnt() {
		return recentImagesCnt;
	}



	public void setRecentImagesCnt(Long recentImagesCnt) {
		this.recentImagesCnt = recentImagesCnt;
	}



	public Long getRecentMeetingInviteesCnt() {
		return recentMeetingInviteesCnt;
	}



	public void setRecentMeetingInviteesCnt(Long recentMeetingInviteesCnt) {
		this.recentMeetingInviteesCnt = recentMeetingInviteesCnt;
	}



	public Long getMeetingId() {
		return meetingId;
	}



	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}



	public Long getImagesCnt() {
		return imagesCnt;
	}



	public void setImagesCnt(Long imagesCnt) {
		this.imagesCnt = imagesCnt;
	}



	public Set<Long> getAttendedIds() {
		return attendedIds;
	}



	public void setAttendedIds(Set<Long> attendedIds) {
		this.attendedIds = attendedIds;
	}



	public Set<Long> getInviteeIds() {
		return inviteeIds;
	}



	public void setInviteeIds(Set<Long> inviteeIds) {
		this.inviteeIds = inviteeIds;
	}



	public Long getMaybeeMeetings() {
		return maybeeMeetings;
	}



	public void setMaybeeMeetings(Long maybeeMeetings) {
		this.maybeeMeetings = maybeeMeetings;
	}



	public Long getNotUpdatedCount() {
		return notUpdatedCount;
	}



	public void setNotUpdatedCount(Long notUpdatedCount) {
		this.notUpdatedCount = notUpdatedCount;
	}



	public PartyMeetingDataVO(Long id , String name){
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTotalMeetings() {
		return totalMeetings;
	}
	public void setTotalMeetings(Long totalMeetings) {
		this.totalMeetings = totalMeetings;
	}
	public Long getConductedMeetings() {
		return conductedMeetings;
	}
	public void setConductedMeetings(Long conductedMeetings) {
		this.conductedMeetings = conductedMeetings;
	}
	public Long getNotConductedMeetings() {
		return notConductedMeetings;
	}
	public void setNotConductedMeetings(Long notConductedMeetings) {
		this.notConductedMeetings = notConductedMeetings;
	}
	

	public List<PartyMeetingDataVO> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<PartyMeetingDataVO> levelList) {
		this.levelList = levelList;
	}

	public Map<Long, PartyMeetingDataVO> getSubMap() {
		return subMap;
	}

	public void setSubMap(Map<Long, PartyMeetingDataVO> subMap) {
		this.subMap = subMap;
	}

	public List<PartyMeetingDataVO> getMeetingsList() {
		return meetingsList;
	}

	public void setMeetingsList(List<PartyMeetingDataVO> meetingsList) {
		this.meetingsList = meetingsList;
	}

	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}

	public String getPartyMeetingName() {
		return partyMeetingName;
	}

	public void setPartyMeetingName(String partyMeetingName) {
		this.partyMeetingName = partyMeetingName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMeetingLevel() {
		return meetingLevel;
	}

	public void setMeetingLevel(String meetingLevel) {
		this.meetingLevel = meetingLevel;
	}

	public String getConductedDate() {
		return conductedDate;
	}

	public void setConductedDate(String conductedDate) {
		this.conductedDate = conductedDate;
	}

	public String getMomStatus() {
		return momStatus;
	}

	public void setMomStatus(String momStatus) {
		this.momStatus = momStatus;
	}

	public String getAtrStatus() {
		return atrStatus;
	}

	public void setAtrStatus(String atrStatus) {
		this.atrStatus = atrStatus;
	}
	
	public Long getInvitedCount() {
		return invitedCount;
	}

	public void setInvitedCount(Long invitedCount) {
		this.invitedCount = invitedCount;
	}
	
	public Long getAttendedCount() {
		return attendedCount;
	}

	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	
	public Long getInviteeAttendedCount() {
		return inviteeAttendedCount;
	}

	public void setInviteeAttendedCount(Long inviteeAttendedCount) {
		this.inviteeAttendedCount = inviteeAttendedCount;
	}
	
	public Long getNonInviteesCount() {
		return nonInviteesCount;
	}

	public void setNonInviteesCount(Long nonInviteesCount) {
		this.nonInviteesCount = nonInviteesCount;
	}
	
	public Long getAbsentCount() {
		return absentCount;
	}

	public void setAbsentCount(Long absentCount) {
		this.absentCount = absentCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((conductedDate == null) ? 0 : conductedDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((meetingLevel == null) ? 0 : meetingLevel.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartyMeetingDataVO other = (PartyMeetingDataVO) obj;
		if (conductedDate == null) {
			if (other.conductedDate != null)
				return false;
		} else if (!conductedDate.equals(other.conductedDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (meetingLevel == null) {
			if (other.meetingLevel != null)
				return false;
		} else if (!meetingLevel.equals(other.meetingLevel))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public List<PartyMeetingDataVO> getDatesList() {
		return datesList;
	}

	public void setDatesList(List<PartyMeetingDataVO> datesList) {
		this.datesList = datesList;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getYesCount() {
		return yesCount;
	}

	public void setYesCount(Long yesCount) {
		this.yesCount = yesCount;
	}

	public Long getNoCount() {
		return noCount;
	}

	public void setNoCount(Long noCount) {
		this.noCount = noCount;
	}

	public Long getMaybeCount() {
		return maybeCount;
	}

	public void setMaybeCount(Long maybeCount) {
		this.maybeCount = maybeCount;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public String getDesignationname() {
		return designationname;
	}
	public void setDesignationname(String designationname) {
		this.designationname = designationname;
	}

	
}
