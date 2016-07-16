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
@Table(name = "nominated_post_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostMember extends BaseModel implements Serializable{

	private Long nominatedPostMemberId;
	private Long nominatedPostPositionId;
	private Long boardLevelId;
	private Long locationValue;
	private Long maxMembers;
	private Long insertedBy;
	private Date insertedTime;
	private Long updatedBy;
	private Date updatedTime;
	private String isDeleted;
	
	private NominatedPostPosition nominatedPostPosition;
	private BoardLevel boardLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_member_id", unique = true, nullable = false)
	public Long getNominatedPostMemberId() {
		return nominatedPostMemberId;
	}
	public void setNominatedPostMemberId(Long nominatedPostMemberId) {
		this.nominatedPostMemberId = nominatedPostMemberId;
	}
	
	@Column(name="nominated_post_position_id")
	public Long getNominatedPostPositionId() {
		return nominatedPostPositionId;
	}
	public void setNominatedPostPositionId(Long nominatedPostPositionId) {
		this.nominatedPostPositionId = nominatedPostPositionId;
	}
	
	@Column(name="board_level_id")
	public Long getBoardLevelId() {
		return boardLevelId;
	}
	public void setBoardLevelId(Long boardLevelId) {
		this.boardLevelId = boardLevelId;
	}
	
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@Column(name="max_members")
	public Long getMaxMembers() {
		return maxMembers;
	}
	public void setMaxMembers(Long maxMembers) {
		this.maxMembers = maxMembers;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nominated_post_position_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominatedPostPosition getNominatedPostPosition() {
		return nominatedPostPosition;
	}
	public void setNominatedPostPosition(NominatedPostPosition nominatedPostPosition) {
		this.nominatedPostPosition = nominatedPostPosition;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="board_level_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoardLevel getBoardLevel() {
		return boardLevel;
	}
	public void setBoardLevel(BoardLevel boardLevel) {
		this.boardLevel = boardLevel;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
}
