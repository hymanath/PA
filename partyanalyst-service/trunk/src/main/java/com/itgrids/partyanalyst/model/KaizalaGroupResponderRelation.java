package com.itgrids.partyanalyst.model;

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
@Table(name = "kaizala_group_responder_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaGroupResponderRelation {
	
	private Long kaizalaGroupResponderRelationId;
	private String groupId;
	private Long kaizalaGroupsId;
	private Long kaizalaResponderInfoId;
	private String groupName;
	private String isDeleted;
	private Long kaizalaEventsResponseId;
	
	private KaizalaGroups kaizalaGroups;
	private KaizalaResponderInfo kaizalaResponderInfo;
	
	@Id
	@Column(name="kaizala_group_responder_relation_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaGroupResponderRelationId() {
		return kaizalaGroupResponderRelationId;
	}
	public void setKaizalaGroupResponderRelationId(
			Long kaizalaGroupResponderRelationId) {
		this.kaizalaGroupResponderRelationId = kaizalaGroupResponderRelationId;
	}
	
	@Column(name="group_id")
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Column(name="kaizala_groups_id")
	public Long getKaizalaGroupsId() {
		return kaizalaGroupsId;
	}
	public void setKaizalaGroupsId(Long kaizalaGroupsId) {
		this.kaizalaGroupsId = kaizalaGroupsId;
	}
	
	@Column(name="kaizala_responder_info_id")
	public Long getKaizalaResponderInfoId() {
		return kaizalaResponderInfoId;
	}
	public void setKaizalaResponderInfoId(Long kaizalaResponderInfoId) {
		this.kaizalaResponderInfoId = kaizalaResponderInfoId;
	}
	
	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_groups_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public KaizalaGroups getKaizalaGroups() {
		return kaizalaGroups;
	}
	public void setKaizalaGroups(KaizalaGroups kaizalaGroups) {
		this.kaizalaGroups = kaizalaGroups;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_responder_info_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public KaizalaResponderInfo getKaizalaResponderInfo() {
		return kaizalaResponderInfo;
	}
	public void setKaizalaResponderInfo(KaizalaResponderInfo kaizalaResponderInfo) {
		this.kaizalaResponderInfo = kaizalaResponderInfo;
	}
	@Column(name="kaizala_events_response_id")
	public Long getKaizalaEventsResponseId() {
		return kaizalaEventsResponseId;
	}
	public void setKaizalaEventsResponseId(Long kaizalaEventsResponseId) {
		this.kaizalaEventsResponseId = kaizalaEventsResponseId;
	}
}