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
@Table(name = "leader_occasion_wish_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LeaderOccasionWishDetails extends BaseModel implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long leaderOccasionWishDetailsId;
	private LeaderOccasion leaderOccasion;
	private Long leaderOccasionId;
	private String year;
	private Date wishTime;
	private String isDeleted;
	private String description;
	private Date insertedTime;
	private Date updatedTime;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leader_occasion_wish_details_id", unique = true, nullable = false)
	public Long getLeaderOccasionWishDetailsId() {
		return leaderOccasionWishDetailsId;
	}

	public void setLeaderOccasionWishDetailsId(Long leaderOccasionWishDetailsId) {
		this.leaderOccasionWishDetailsId = leaderOccasionWishDetailsId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "leader_occasion_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public LeaderOccasion getLeaderOccasion() {
		return leaderOccasion;
	}

	public void setLeaderOccasion(LeaderOccasion leaderOccasion) {
		this.leaderOccasion = leaderOccasion;
	}

	@Column(name = "leader_occasion_id")
	public Long getLeaderOccasionId() {
		return leaderOccasionId;
	}

	public void setLeaderOccasionId(Long leaderOccasionId) {
		this.leaderOccasionId = leaderOccasionId;
	}

	@Column(name = "year")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "wish_time")
	public Date getWishTime() {
		return wishTime;
	}

	public void setWishTime(Date wishTime) {
		this.wishTime = wishTime;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	
}
