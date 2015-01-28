package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "cadre_committee_increased_positions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreCommitteeIncreasedPositions extends BaseModel implements Serializable
{
	private Long cadreCommitteeIncreasedPositionsId;
	private TdpCommitteeRole tdpCommitteeRole;
	private User userIdRequest;
	private User approvedUser;
	private Long currentCount;
	private Long requestCount;
	private String status;
	private Long approvedCount;
	private Date insertedTime; 
	private Date updatedTime;
	private String type;
	private String userComments;
	private String refNo;
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="cadre_committee_increased_positions_id", unique=true, nullable=false) 
	public Long getCadreCommitteeIncreasedPositionsId() {
		return cadreCommitteeIncreasedPositionsId;
	}
	public void setCadreCommitteeIncreasedPositionsId(
			Long cadreCommitteeIncreasedPositionsId) {
		this.cadreCommitteeIncreasedPositionsId = cadreCommitteeIncreasedPositionsId;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_committee_role_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeRole getTdpCommitteeRole() {
		return tdpCommitteeRole;
	}
	public void setTdpCommitteeRole(TdpCommitteeRole tdpCommitteeRole) {
		this.tdpCommitteeRole = tdpCommitteeRole;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id_request")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUserIdRequest() {
		return userIdRequest;
	}
	public void setUserIdRequest(User userIdRequest) {
		this.userIdRequest = userIdRequest;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="approved_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getApprovedUser() {
		return approvedUser;
	}
	public void setApprovedUser(User approvedUser) {
		this.approvedUser = approvedUser;
	}
	
	@Column(name = "current_count")
	public Long getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Long currentCount) {
		this.currentCount = currentCount;
	}
	
	@Column(name = "request_count")
	public Long getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(Long requestCount) {
		this.requestCount = requestCount;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "approved_count")
	public Long getApprovedCount() {
		return approvedCount;
	}
	public void setApprovedCount(Long approvedCount) {
		this.approvedCount = approvedCount;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "user_comments")
	public String getUserComments() {
		return userComments;
	}
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	
	@Column(name = "ref_no")
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	
	
    
	
}
