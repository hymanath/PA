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
@Table(name="activity_location_public_attendance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityLocationPublicAttendance {
	private Long activityLocationPublicAttendanceId;
	private String name;
	private String mobile;
	private String voterCard;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_location_public_attendance_id", unique = true, nullable = false)
	public Long getActivityLocationPublicAttendanceId() {
		return activityLocationPublicAttendanceId;
	}
	public void setActivityLocationPublicAttendanceId(
			Long activityLocationPublicAttendanceId) {
		this.activityLocationPublicAttendanceId = activityLocationPublicAttendanceId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "voter_card")
	public String getVoterCard() {
		return voterCard;
	}
	public void setVoterCard(String voterCard) {
		this.voterCard = voterCard;
	}
	
	
	

}
