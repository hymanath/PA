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
@Table(name="verified_data_request")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VerifiedDataRequest extends BaseModel implements Serializable{

	private static final long serialVersionUID = 6883113944423032312L;
	
	private Long verifiedDataRequestId;
	private TdpCadreVerfiedData tdpCadreVerfiedData;
	private CadreSurveyUser cadreSurveyUser;
	private Voter voter;
	private Voter familyVoter;
	private String uniqueKey;
	private Date insertedTime;
	private Date updatedTime;
	
	private Long tdpCadreVerfiedDataId;
	private Long cadreSurveyUserId;
	private Long voterId;
	private Long familyVoterId;

	public VerifiedDataRequest(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="verified_data_request_id", unique=true, nullable=false)
	public Long getVerifiedDataRequestId() {
		return verifiedDataRequestId;
	}

	public void setVerifiedDataRequestId(Long verifiedDataRequestId) {
		this.verifiedDataRequestId = verifiedDataRequestId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_verfied_data_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadreVerfiedData getTdpCadreVerfiedData() {
		return tdpCadreVerfiedData;
	}

	public void setTdpCadreVerfiedData(TdpCadreVerfiedData tdpCadreVerfiedData) {
		this.tdpCadreVerfiedData = tdpCadreVerfiedData;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getCadreSurveyUser() {
		return cadreSurveyUser;
	}

	public void setCadreSurveyUser(CadreSurveyUser cadreSurveyUser) {
		this.cadreSurveyUser = cadreSurveyUser;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="family_voter_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getFamilyVoter() {
		return familyVoter;
	}

	public void setFamilyVoter(Voter familyVoter) {
		this.familyVoter = familyVoter;
	}

	@Column(name = "unique_key")
	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
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

	@Column(name = "tdp_cadre_verfied_data_id")
	public Long getTdpCadreVerfiedDataId() {
		return tdpCadreVerfiedDataId;
	}

	public void setTdpCadreVerfiedDataId(Long tdpCadreVerfiedDataId) {
		this.tdpCadreVerfiedDataId = tdpCadreVerfiedDataId;
	}

	@Column(name = "user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}

	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}

	@Column(name = "voter_id")
	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	@Column(name = "family_voter_id")
	public Long getFamilyVoterId() {
		return familyVoterId;
	}

	public void setFamilyVoterId(Long familyVoterId) {
		this.familyVoterId = familyVoterId;
	}
}
