package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="activity_participant_member_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityParticipantMemberType {
	
	private Long activityParticipantMemberTypeId;
	private String memberType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="activity_participant_member_type_id", unique = true, nullable = false)
	public Long getActivityParticipantMemberTypeId() {
		return activityParticipantMemberTypeId;
	}

	public void setActivityParticipantMemberTypeId(
			Long activityParticipantMemberTypeId) {
		this.activityParticipantMemberTypeId = activityParticipantMemberTypeId;
	}
	
	@Column(name="member_type")
	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
		

}
