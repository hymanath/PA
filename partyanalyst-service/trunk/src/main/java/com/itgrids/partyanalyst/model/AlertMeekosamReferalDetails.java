package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "alert_meekosam_referal_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertMeekosamReferalDetails extends BaseModel implements Serializable{

	private Long alertMeekosamReferalDetailsId;
	private Long alertId;
	private Long meekosamPublicRepresentativeTypeRelationId;
	private String isDeleted;
	
	private Alert alert;
	private MeekosamPublicRepresentativeTypeRelation meekosamPublicRepresentativeTypeRelation;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_meekosam_referal_details_id", unique = true, nullable = false)
	public Long getAlertMeekosamReferalDetailsId() {
		return alertMeekosamReferalDetailsId;
	}
	public void setAlertMeekosamReferalDetailsId(Long alertMeekosamReferalDetailsId) {
		this.alertMeekosamReferalDetailsId = alertMeekosamReferalDetailsId;
	}
	
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	
	@Column(name = "meekosam_public_representative_type_relation_id")
	public Long getMeekosamPublicRepresentativeTypeRelationId() {
		return meekosamPublicRepresentativeTypeRelationId;
	}
	public void setMeekosamPublicRepresentativeTypeRelationId(
			Long meekosamPublicRepresentativeTypeRelationId) {
		this.meekosamPublicRepresentativeTypeRelationId = meekosamPublicRepresentativeTypeRelationId;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_public_representative_type_relation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamPublicRepresentativeTypeRelation getMeekosamPublicRepresentativeTypeRelation() {
		return meekosamPublicRepresentativeTypeRelation;
	}
	public void setMeekosamPublicRepresentativeTypeRelation(
			MeekosamPublicRepresentativeTypeRelation meekosamPublicRepresentativeTypeRelation) {
		this.meekosamPublicRepresentativeTypeRelation = meekosamPublicRepresentativeTypeRelation;
	}
}
