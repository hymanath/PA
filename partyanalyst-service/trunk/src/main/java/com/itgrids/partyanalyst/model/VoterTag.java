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
@Table(name="voter_tag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterTag extends BaseModel implements Serializable{

	private static final long serialVersionUID = -5278507725777911706L;
	
	private Long voterTagId;
	private Voter voter;
	private String isCadre;
	private String isInfluencingPeople;
	private String tags;
	private String mobileNo;
	private Party party;
	private CasteState casteState;
	private String latitude;
	private String longitude;
	private Date insertTime;
	private Date syncTime;
	private String isdelete;
	private String uniqueCode;
	private Long voterId;
	private Long partyId;
	private Long casteStateId;
	private String isTaggedInserted;
	private String isCadreInserted;
	private String isInfluenceInserted;
	
	
	public VoterTag(){}
	
	public VoterTag(Voter voter,String isCadre,String isInfluencingPeople,String tags,
			String mobileNo,Party party,CasteState casteState,String latitude,String longitude,
			Date insertTime,Date syncTime,String isdelete,String uniqueCode)
	{
		this.voter = voter;
		this.isCadre = isCadre;
		this.isInfluencingPeople = isInfluencingPeople;
		this.tags = tags;
		this.mobileNo = mobileNo;
		this.party = party;
		this.casteState = casteState;
		this.latitude = latitude;
		this.longitude = longitude;
		this.insertTime = insertTime;
		this.syncTime = syncTime;
		this.isdelete = isdelete;
		this.uniqueCode = uniqueCode;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="voter_tag_id", unique=true, nullable=false)
	public Long getVoterTagId() {
		return voterTagId;
	}

	public void setVoterTagId(Long voterTagId) {
		this.voterTagId = voterTagId;
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

	@Column(name="is_cadre",length=10)
	public String getIsCadre() {
		return isCadre;
	}

	public void setIsCadre(String isCadre) {
		this.isCadre = isCadre;
	}

	@Column(name="is_influencing_people",length=10)
	public String getIsInfluencingPeople() {
		return isInfluencingPeople;
	}

	public void setIsInfluencingPeople(String isInfluencingPeople) {
		this.isInfluencingPeople = isInfluencingPeople;
	}

	@Column(name="tags",length=300)
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name="mobile_no",length=20)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="caste_state_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteState getCasteState() {
		return casteState;
	}

	public void setCasteState(CasteState casteState) {
		this.casteState = casteState;
	}

	@Column(name="latitude",length=50)
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name="longitude",length=50)
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "insert_time", length = 50)
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@Column(name = "sync_time", length = 50)
	public Date getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	@Column(name="is_delete",length=10)
	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	@Column(name="unique_code",length=20)
	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	@Column(name="voter_id")
	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	@Column(name="party_id")
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name="caste_state_id")
	public Long getCasteStateId() {
		return casteStateId;
	}

	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	@Column(name="is_tagged_inserted")
	public String getIsTaggedInserted() {
		return isTaggedInserted;
	}

	public void setIsTaggedInserted(String isTaggedInserted) {
		this.isTaggedInserted = isTaggedInserted;
	}
	@Column(name="is_cadre_inserted")
	public String getIsCadreInserted() {
		return isCadreInserted;
	}

	public void setIsCadreInserted(String isCadreInserted) {
		this.isCadreInserted = isCadreInserted;
	}
	@Column(name="is_ip_inserted")
	public String getIsInfluenceInserted() {
		return isInfluenceInserted;
	}

	public void setIsInfluenceInserted(String isInfluenceInserted) {
		this.isInfluenceInserted = isInfluenceInserted;
	}
	
	
	
}
