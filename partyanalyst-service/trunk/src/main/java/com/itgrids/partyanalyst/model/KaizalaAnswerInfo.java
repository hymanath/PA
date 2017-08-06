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
@Table(name = "kaizala_answer_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaAnswerInfo extends BaseModel implements Serializable {
	private Long kaizalaAnswerInfoId;
	private String groupId;
	private String eventType;
	private String eventId;
	private Long kaizalaActionsId;
	private String responseId;
	private Long kaizalaResponderInfoId;
	private String lattitude;
	private String langitude;
	private String address;
	private String responseTime;
	private String isDeleted;
	
	private KaizalaActions kaizalaActions;
	private KaizalaResponderInfo kaizalaResponderInfo;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kaizala_answer_info_id", unique = true, nullable = false)
	public Long getKaizalaAnswerInfoId() {
		return kaizalaAnswerInfoId;
	}

	public void setKaizalaAnswerInfoId(Long kaizalaAnswerInfoId) {
		this.kaizalaAnswerInfoId = kaizalaAnswerInfoId;
	}

	@Column(name = "group_id")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "event_type")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Column(name = "event_id")
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Column(name = "kaizala_actions_id")
	public Long getKaizalaActionsId() {
		return kaizalaActionsId;
	}

	public void setKaizalaActionsId(Long kaizalaActionsId) {
		this.kaizalaActionsId = kaizalaActionsId;
	}

	@Column(name = "responseId")
	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	@Column(name = "kaizala_responder_info_id")
	public Long getKaizalaResponderInfoId() {
		return kaizalaResponderInfoId;
	}

	public void setKaizalaResponderInfoId(Long kaizalaResponderInfoId) {
		this.kaizalaResponderInfoId = kaizalaResponderInfoId;
	}

	@Column(name = "lattitude")
	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	@Column(name = "langitude")
	public String getLangitude() {
		return langitude;
	}

	public void setLangitude(String langitude) {
		this.langitude = langitude;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "response_time")
	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_actions_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public KaizalaActions getKaizalaActions() {
		return kaizalaActions;
	}

	public void setKaizalaActions(KaizalaActions kaizalaActions) {
		this.kaizalaActions = kaizalaActions;
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

}
