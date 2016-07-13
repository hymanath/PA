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
@Table(name="department_board")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DepartmentBoard extends BaseModel implements Serializable{
	
	private Long departmentBoardId;
	private Long departmentId;
	private Long boardId;
	private String isDeleted;
	
	private Departments departments;
	private Board board;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "department_board_id", unique = true, nullable = false)
	public Long getDepartmentBoardId() {
		return departmentBoardId;
	}
	public void setDepartmentBoardId(Long departmentBoardId) {
		this.departmentBoardId = departmentBoardId;
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
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
	
	
	
}
