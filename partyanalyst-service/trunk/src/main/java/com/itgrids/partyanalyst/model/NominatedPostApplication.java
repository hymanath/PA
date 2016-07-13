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
	private Long tdpCadreId;
	private Long departmentId;
	private Long boardId;
	private Long positionId;
	private Long boardLevelId;
	private Long locationValue;
	
	private TdpCadre tdpCadre;
	private Departments departments;
	private Board board;
	private Position position;
	private BoardLevel boardLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_application_id", unique = true, nullable = false)
	public Long getNominatedPostApplicationId() {
		return nominatedPostApplicationId;
	}
	public void setNominatedPostApplicationId(Long nominatedPostApplicationId) {
		this.nominatedPostApplicationId = nominatedPostApplicationId;
	}
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
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
	@JoinColumn(name="tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
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
	
	
	
}
