package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "demo_request")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DemoRequest extends BaseModel implements Serializable{
	
	
	private static final long serialVersionUID = 2212178178995032070L;
	private Long demoRequestId;
	private String aspirantName;
	private String mobileNo;
	private String constituency;
	private String email;
	private Date requesteTime;
	private String assignedTo;
	private Long updatedBy;
	private String isDelete;
	private Set<DemoRequestActions> demoRequestActions = new HashSet<DemoRequestActions>(0);
	
	public DemoRequest(){}
	public DemoRequest(String aspirantName,String mobileNo,String constituency,String email,
			Date requesteTime,String assignedTo,Long updatedBy,String isDelete)
	{
		this.aspirantName = aspirantName;
		this.mobileNo = mobileNo;
		this.constituency = constituency;
		this.email = email;
		this.requesteTime = requesteTime;
		this.assignedTo = assignedTo;
		this.updatedBy = updatedBy;
		this.isDelete = isDelete;
	}
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "demo_request_id", unique = true, nullable = false)
	public Long getDemoRequestId() {
		return demoRequestId;
	}
	public void setDemoRequestId(Long demoRequestId) {
		this.demoRequestId = demoRequestId;
	}
	
	@Column(name = "aspirant_name",length = 50)
	public String getAspirantName() {
		return aspirantName;
	}
	public void setAspirantName(String aspirantName) {
		this.aspirantName = aspirantName;
	}
	
	@Column(name = "mobile_no", length = 15)
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "constituency",length = 50)
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	
	@Column(name = "email",length = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "requeste_time")
	public Date getRequesteTime() {
		return requesteTime;
	}
	public void setRequesteTime(Date requesteTime) {
		this.requesteTime = requesteTime;
	}
	
	@Column(name = "assigned_to",length = 50)
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	
	@Column(name = "updated_by",length=11)
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "demoRequest")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<DemoRequestActions> getDemoRequestActions() {
		return demoRequestActions;
	}
	public void setDemoRequestActions(Set<DemoRequestActions> demoRequestActions) {
		this.demoRequestActions = demoRequestActions;
	}
	
	
}
