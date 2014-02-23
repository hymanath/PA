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
@Table(name="voter_booth_activities")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterBoothActivities extends BaseModel implements Serializable{

	private static final long serialVersionUID = -16282299641769171L;
	
	private Long voterBoothActivitiesId;
	private Voter voter;
	private Booth booth;
	private BoothActivities boothActivities;
	private String latitude;
	private String longitude;
	private Date insertTime;
	private Date syncTime;
	private String isdelete;
	private String uniqueCode;
	private Long voterId;
	private Long boothId;
	private Long boothActivitiesId;
	
	public VoterBoothActivities(){}
	
	public VoterBoothActivities(Long voterId,Long boothId,Long boothActivitiesId)
	{
		this.voterId = voterId;
		this.boothId = boothId;
		this.boothActivitiesId = boothActivitiesId;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="voter_booth_activities_id", unique=true, nullable=false)
	public Long getVoterBoothActivitiesId() {
		return voterBoothActivitiesId;
	}

	public void setVoterBoothActivitiesId(Long voterBoothActivitiesId) {
		this.voterBoothActivitiesId = voterBoothActivitiesId;
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
	@JoinColumn(name="booth_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_activities_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothActivities getBoothActivities() {
		return boothActivities;
	}

	public void setBoothActivities(BoothActivities boothActivities) {
		this.boothActivities = boothActivities;
	}

	@Column(name = "voter_id")
	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	@Column(name = "booth_id")
	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	@Column(name = "booth_activities_id")
	public Long getBoothActivitiesId() {
		return boothActivitiesId;
	}

	public void setBoothActivitiesId(Long boothActivitiesId) {
		this.boothActivitiesId = boothActivitiesId;
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
	
}
