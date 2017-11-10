package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "jb_committee_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbCommitteeRole extends BaseModel implements java.io.Serializable {

	
	private Long jbCommitteeRoleId;
	private Long jbCommitteeId;
	private Long  jbRoleId ;
	private Long jbMemberTypeId;
	private Long maxMembers;
	private Date updatedTime;
	private String roleType;
	private String isDeleted;
	
	private JbMemberType jbMemberType;
	private JbRole jbRole;
	private JbCommittee jbCommittee;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jb_committee_role_id", unique = true, nullable = false)
	public Long getJbCommitteeRoleId() {
		return jbCommitteeRoleId;
	}
	public void setJbCommitteeRoleId(Long jbCommitteeRoleId) {
		this.jbCommitteeRoleId = jbCommitteeRoleId;
	}
	@Column(name="jb_committee_id")
	public Long getJbCommitteeId() {
		return jbCommitteeId;
	}
	public void setJbCommitteeId(Long jbCommitteeId) {
		this.jbCommitteeId = jbCommitteeId;
	}
	@Column(name="jb_role_id")
	public Long getJbRoleId() {
		return jbRoleId;
	}
	public void setJbRoleId(Long jbRoleId) {
		this.jbRoleId = jbRoleId;
	}
	@Column(name="jb_member_type_id")
	public Long getJbMemberTypeId() {
		return jbMemberTypeId;
	}
	public void setJbMemberTypeId(Long jbMemberTypeId) {
		this.jbMemberTypeId = jbMemberTypeId;
	}
	@Column(name="max_members")
	public Long getMaxMembers() {
		return maxMembers;
	}
	public void setMaxMembers(Long maxMembers) {
		this.maxMembers = maxMembers;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name="role_type")
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "jb_member_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public JbMemberType getJbMemberType() {
		return jbMemberType;
	}
	public void setJbMemberType(JbMemberType jbMemberType) {
		this.jbMemberType = jbMemberType;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "jb_role_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public JbRole getJbRole() {
		return jbRole;
	}
	public void setJbRole(JbRole jbRole) {
		this.jbRole = jbRole;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "jb_committee_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public JbCommittee getJbCommittee() {
		return jbCommittee;
	}
	public void setJbCommittee(JbCommittee jbCommittee) {
		this.jbCommittee = jbCommittee;
	}
	
	
}
