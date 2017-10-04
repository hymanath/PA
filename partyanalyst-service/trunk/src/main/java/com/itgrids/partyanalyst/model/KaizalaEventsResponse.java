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

@Entity
@Table(name = "kaizala_events_response")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaEventsResponse extends BaseModel implements Serializable{
	
	private Long kaizalaEventsResponseId;
	private Long kaizalaGroupsId;
	private String groupId;
	private Long kaizalaActionsId;
	private String actionId;
	private String eventId;
	private Long kaizalaEventsId;
	private Long kaizalaAttachementTypeId;
	private String responseText;
	private Long addedKaizalaResponderInfoId;
	private Long addedKaizalaGroupsId;
	private Date dueDate;
	private Date insertedTime;
	private Date updatedTime;
	private Long insertedBy;
	private Long updatedBy;
	private String isDeleted;
	
	
	private KaizalaGroups kaizalaGroups;
	private KaizalaActions kaizalaActions;
	private KaizalaEvents kaizalaEvents;
	private KaizalaAttachementType kaizalaAttachementType;
	
	@Id
	@Column(name="kaizala_events_response_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaEventsResponseId() {
		return kaizalaEventsResponseId;
	}
	public void setKaizalaEventsResponseId(Long kaizalaEventsResponseId) {
		this.kaizalaEventsResponseId = kaizalaEventsResponseId;
	}
	@Column(name="kaizala_groups_id")
	public Long getKaizalaGroupsId() {
		return kaizalaGroupsId;
	}
	public void setKaizalaGroupsId(Long kaizalaGroupsId) {
		this.kaizalaGroupsId = kaizalaGroupsId;
	}
	@Column(name="group_id")
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Column(name="kaizala_action_id")
	public Long getKaizalaActionsId() {
		return kaizalaActionsId;
	}
	public void setKaizalaActionsId(Long kaizalaActionsId) {
		this.kaizalaActionsId = kaizalaActionsId;
	}
	@Column(name="action_id")
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	@Column(name="event_id")
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	@Column(name="kaizala_events_id")
	public Long getKaizalaEventsId() {
		return kaizalaEventsId;
	}
	public void setKaizalaEventsId(Long kaizalaEventsId) {
		this.kaizalaEventsId = kaizalaEventsId;
	}
	@Column(name="kaizala_attachement_type_id")
	public Long getKaizalaAttachementTypeId() {
		return kaizalaAttachementTypeId;
	}
	public void setKaizalaAttachementTypeId(Long kaizalaAttachementTypeId) {
		this.kaizalaAttachementTypeId = kaizalaAttachementTypeId;
	}
	@Column(name="response_text")
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	@Column(name="added_kaizala_responder_info_id")
	public Long getAddedKaizalaResponderInfoId() {
		return addedKaizalaResponderInfoId;
	}
	public void setAddedKaizalaResponderInfoId(Long addedKaizalaResponderInfoId) {
		this.addedKaizalaResponderInfoId = addedKaizalaResponderInfoId;
	}
	@Column(name="added_kaizala_groups_id")
	public Long getAddedKaizalaGroupsId() {
		return addedKaizalaGroupsId;
	}
	public void setAddedKaizalaGroupsId(Long addedKaizalaGroupsId) {
		this.addedKaizalaGroupsId = addedKaizalaGroupsId;
	}
	@Column(name="due_date")
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
	public KaizalaGroups getKaizalaGroups() {
		return kaizalaGroups;
	}
	public void setKaizalaGroups(KaizalaGroups kaizalaGroups) {
		this.kaizalaGroups = kaizalaGroups;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_action_id", insertable = false, updatable = false)
	public KaizalaActions getKaizalaActions() {
		return kaizalaActions;
	}
	public void setKaizalaActions(KaizalaActions kaizalaActions) {
		this.kaizalaActions = kaizalaActions;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_events_id", insertable = false, updatable = false)
	public KaizalaEvents getKaizalaEvents() {
		return kaizalaEvents;
	}
	public void setKaizalaEvents(KaizalaEvents kaizalaEvents) {
		this.kaizalaEvents = kaizalaEvents;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_attachement_type_id", insertable = false, updatable = false)
	public KaizalaAttachementType getKaizalaAttachementType() {
		return kaizalaAttachementType;
	}
	public void setKaizalaAttachementType(KaizalaAttachementType kaizalaAttachementType) {
		this.kaizalaAttachementType = kaizalaAttachementType;
	}
}
