package com.itgrids.partyanalyst.model;

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
@Table(name = "kaizala_groups")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaGroups {

	private Long kaizalaGroupsId;
	private String groupId;
	private String groupName;
	private Long kaizalaGroupTypeId;
	private String isDeleted;
	private Date insertedTime;
	private Long parentKaizalaGroupsId;
	
	private KaizalaGroupType kaizalaGroupType;

	@Id
	@Column(name="kaizala_groups_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaGroupsId() {
		return kaizalaGroupsId;
	}

	public void setKaizalaGroupsId(Long kaizalaGroupsId) {
		this.kaizalaGroupsId = kaizalaGroupsId;
	}

	@Column(name="groupId")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name="kaizala_group_type_id")
	public Long getKaizalaGroupTypeId() {
		return kaizalaGroupTypeId;
	}

	public void setKaizalaGroupTypeId(Long kaizalaGroupTypeId) {
		this.kaizalaGroupTypeId = kaizalaGroupTypeId;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_group_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public KaizalaGroupType getKaizalaGroupType() {
		return kaizalaGroupType;
	}

	public void setKaizalaGroupType(KaizalaGroupType kaizalaGroupType) {
		this.kaizalaGroupType = kaizalaGroupType;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="parent_kaizala_groups_id")
	public Long getParentKaizalaGroupsId() {
		return parentKaizalaGroupsId;
	}

	public void setParentKaizalaGroupsId(Long parentKaizalaGroupsId) {
		this.parentKaizalaGroupsId = parentKaizalaGroupsId;
	}
	

}
