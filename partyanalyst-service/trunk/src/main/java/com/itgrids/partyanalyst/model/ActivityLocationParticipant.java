package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="activity_location_participant")
public class ActivityLocationParticipant {

	private Long activityLocationParticipantId;
	private ActivityLocationInfo activityLocationInfo;
	private ActivityParticipantMemberType activityParticipantMemberType;
	private Long totalVoters;
	private Long participantVoters;
	private String isDeleted;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_location_participant_id", unique = true, nullable = false)
	public Long getActivityLocationParticipantId() {
		return activityLocationParticipantId;
	}

	public void setActivityLocationParticipantId(Long activityLocationParticipantId) {
		this.activityLocationParticipantId = activityLocationParticipantId;
	}

	@OneToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "activity_location_info_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityLocationInfo getActivityLocationInfo() {
		return activityLocationInfo;
	}

	public void setActivityLocationInfo(ActivityLocationInfo activityLocationInfo) {
		this.activityLocationInfo = activityLocationInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "activity_participant_member_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityParticipantMemberType getActivityParticipantMemberType() {
		return activityParticipantMemberType;
	}

	public void setActivityParticipantMemberType(
			ActivityParticipantMemberType activityParticipantMemberType) {
		this.activityParticipantMemberType = activityParticipantMemberType;
	}

	@Column(name="total_voters")
	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	@Column(name="participant_voters")
	public Long getParticipantVoters() {
		return participantVoters;
	}

	public void setParticipantVoters(Long participantVoters) {
		this.participantVoters = participantVoters;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	
}
