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
@Table(name="nominated_post_application")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostApplication extends BaseModel implements Serializable{
	
	private Long nominatedPostApplicationId;
	private Long nominationPostCandidateId;
	private Long departmentId;
	private Long boardId;
	private Long positionId;
	private Long boardLevelId;
	private Long locationValue;
	private Long applicationStatusId;
	private String  isDeleted;
	private Long insertedBy;
	private Date insertedTime;
	private Long updatedBy;
	private Date updatedTime;
	private Long addressId;
	private Long postTypeId;
	private Long nominatedPostMemberId;
	
	private UserAddress address;
	private NominationPostCandidate nominationPostCandidate;
	private Departments departments;
	private Board board;
	private Position position;
	private BoardLevel boardLevel;
	private ApplicationStatus applicationStatus;
	private PostType postType;
	private NominatedPostMember nominatedPostMember;
	private String  isExpired;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_application_id", unique = true, nullable = false)
	public Long getNominatedPostApplicationId() {
		return nominatedPostApplicationId;
	}
	public void setNominatedPostApplicationId(Long nominatedPostApplicationId) {
		this.nominatedPostApplicationId = nominatedPostApplicationId;
	}
	
	@Column(name = "nomination_post_candidate_id")
	public Long getNominationPostCandidateId() {
		return nominationPostCandidateId;
	}
	public void setNominationPostCandidateId(Long nominationPostCandidateId) {
		this.nominationPostCandidateId = nominationPostCandidateId;
	}
	
	@Column(name = "department_id")
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	@Column(name = "board_id")
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	@Column(name = "position_id")
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	@Column(name = "board_level_id")
	public Long getBoardLevelId() {
		return boardLevelId;
	}
	public void setBoardLevelId(Long boardLevelId) {
		this.boardLevelId = boardLevelId;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nomination_post_candidate_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominationPostCandidate getNominationPostCandidate() {
		return nominationPostCandidate;
	}
	public void setNominationPostCandidate(
			NominationPostCandidate nominationPostCandidate) {
		this.nominationPostCandidate = nominationPostCandidate;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="department_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Departments getDepartments() {
		return departments;
	}
	public void setDepartments(Departments departments) {
		this.departments = departments;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="board_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="position_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
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
	
	@Column(name = "application_status_id")
	public Long getApplicationStatusId() {
		return applicationStatusId;
	}
	public void setApplicationStatusId(Long applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="application_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="address_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	
	@Column(name="post_type_id")
	public Long getPostTypeId() {
		return postTypeId;
	}
	public void setPostTypeId(Long postTypeId) {
		this.postTypeId = postTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="post_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PostType getPostType() {
		return postType;
	}
	public void setPostType(PostType postType) {
		this.postType = postType;
	}
	
	@Column(name="nominated_post_member_id")
	public Long getNominatedPostMemberId() {
		return nominatedPostMemberId;
	}
	public void setNominatedPostMemberId(Long nominatedPostMemberId) {
		this.nominatedPostMemberId = nominatedPostMemberId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nominated_post_member_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominatedPostMember getNominatedPostMember() {
		return nominatedPostMember;
	}
	public void setNominatedPostMember(NominatedPostMember nominatedPostMember) {
		this.nominatedPostMember = nominatedPostMember;
	}
	
	@Column(name="is_expired")
	public String getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}
	
	
	
}
