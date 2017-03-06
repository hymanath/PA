package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "party_meeting_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingGroup extends BaseModel implements Serializable{
	
	private Long partyMeetingGroupId;
	private String groupName;
	private Long orderNo;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_meeting_group_id", unique = true, nullable = false)
	public Long getPartyMeetingGroupId() {
		return partyMeetingGroupId;
	}
	public void setPartyMeetingGroupId(Long partyMeetingGroupId) {
		this.partyMeetingGroupId = partyMeetingGroupId;
	}
	@Column(name = "group_name")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
