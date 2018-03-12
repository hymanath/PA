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
@Table(name="nominated_post_access_multiple_positions_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostAccessMultiplePositionsDetails extends BaseModel implements Serializable {
		
	private Long nominatedPostAccessMultiplePositionsDetailsId;
	private Long departmentsId;
	private Long boardId;
	private String isDeleted;
	private Long insertedUserId;
	private Long updatedUserId;
	private Date insertedTime;
	private Date updatedTime;
	
	private Departments departments;
	private Board board;
	private User insertedUser;
	private User updatedUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_access_multiple_positions_details_id", unique = true, nullable = false)
	public Long getNominatedPostAccessMultiplePositionsDetailsId() {
		return nominatedPostAccessMultiplePositionsDetailsId;
	}
	public void setNominatedPostAccessMultiplePositionsDetailsId(
			Long nominatedPostAccessMultiplePositionsDetailsId) {
		this.nominatedPostAccessMultiplePositionsDetailsId = nominatedPostAccessMultiplePositionsDetailsId;
	}
	@Column(name = "departments_id")
	public Long getDepartmentsId() {
		return departmentsId;
	}
	public void setDepartmentsId(Long departmentsId) {
		this.departmentsId = departmentsId;
	}
	@Column(name = "board_id")
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}
	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
	}
	@Column(name = "updated_user_id")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="departments_id", insertable=false, updatable = false)
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
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	
	
	
}
