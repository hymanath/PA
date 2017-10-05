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
public class KaizalaGroupDocument extends BaseModel implements Serializable{
	
	private Long kaizalaGroupDocumentId;
	private Long kaizalaGroupsId;
	private Long kaizalaResponderInfoId;
	private Long kaizalaGroupDocumentTypeId;
	private String imageUrl;
	private String referenceId;
	private Date sentAt;
	private Long kaizalaEventsResponseId;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	
	private KaizalaGroups kaizalaGroups;
	private KaizalaResponderInfo kaizalaResponderInfo;
	private KaizalaGroupDocumentType kaizalaGroupDocumentType;
	private KaizalaEventsResponse kaizalaEventsResponse;
	
	
	@Id
	@Column(name="kaizala_group_document_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaGroupDocumentId() {
		return kaizalaGroupDocumentId;
	}
	public void setKaizalaGroupDocumentId(Long kaizalaGroupDocumentId) {
		this.kaizalaGroupDocumentId = kaizalaGroupDocumentId;
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
	
	@Column(name="kaizala_group_document_type_id")
	public Long getKaizalaGroupDocumentTypeId() {
		return kaizalaGroupDocumentTypeId;
	}
	public void setKaizalaGroupDocumentTypeId(Long kaizalaGroupDocumentTypeId) {
		this.kaizalaGroupDocumentTypeId = kaizalaGroupDocumentTypeId;
	}
	
	@Column(name="image_url")
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Column(name="reference_id")
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
	@Column(name="sent_at")
	public Date getSentAt() {
		return sentAt;
	}
	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
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
	@JoinColumn(name = "kaizala_responder_info_id", insertable = false, updatable = false)
	public KaizalaResponderInfo getKaizalaResponderInfo() {
		return kaizalaResponderInfo;
	}
	public void setKaizalaResponderInfo(KaizalaResponderInfo kaizalaResponderInfo) {
		this.kaizalaResponderInfo = kaizalaResponderInfo;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_group_document_type_id", insertable = false, updatable = false)
	public KaizalaGroupDocumentType getKaizalaGroupDocumentType() {
		return kaizalaGroupDocumentType;
	}
	public void setKaizalaGroupDocumentType(
			KaizalaGroupDocumentType kaizalaGroupDocumentType) {
		this.kaizalaGroupDocumentType = kaizalaGroupDocumentType;
	}
	
	@Column(name="kaizala_events_response_id")
	public Long getKaizalaEventsResponseId() {
		return kaizalaEventsResponseId;
	}
	public void setKaizalaEventsResponseId(Long kaizalaEventsResponseId) {
		this.kaizalaEventsResponseId = kaizalaEventsResponseId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "kaizala_events_response_id", insertable = false, updatable = false)
	public KaizalaEventsResponse getKaizalaEventsResponse() {
		return kaizalaEventsResponse;
	}
	public void setKaizalaEventsResponse(KaizalaEventsResponse kaizalaEventsResponse) {
		this.kaizalaEventsResponse = kaizalaEventsResponse;
	}
	
	
}