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
/**
 * 
 * @author Srishailam Pittala
 * 
 * This Model Describes About Stores all Mahanadu Event details .
 *
 */

@Entity
@Table(name = "mahanadu_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MahanaduGroup implements java.io.Serializable{

	private Long mahanaduGroupId;
	private String groupName;
	private String description;
	private CommitteeLevel committeeLevel;
	private Long committeeLevelId;
	private Long committeeLevelValue;
	private User user;
	private Long userId;
	private Date createdDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mahanadu_group_id", unique = true, nullable = false)
	public Long getMahanaduGroupId() {
		return mahanaduGroupId;
	}
	
	@Column(name="groupName")
	public String getGroupName() {
		return groupName;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "committee_level_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommitteeLevel getCommitteeLevel() {
		return committeeLevel;
	}
	
	@Column(name="committee_level_id")
	public Long getCommitteeLevelId() {
		return committeeLevelId;
	}
	
	@Column(name="committee_level_value")
	public Long getCommitteeLevelValue() {
		return committeeLevelValue;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	
	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setMahanaduGroupId(Long mahanaduGroupId) {
		this.mahanaduGroupId = mahanaduGroupId;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setCommitteeLevel(CommitteeLevel committeeLevel) {
		this.committeeLevel = committeeLevel;
	}
	public void setCommitteeLevelId(Long committeeLevelId) {
		this.committeeLevelId = committeeLevelId;
	}
	public void setCommitteeLevelValue(Long committeeLevelValue) {
		this.committeeLevelValue = committeeLevelValue;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
