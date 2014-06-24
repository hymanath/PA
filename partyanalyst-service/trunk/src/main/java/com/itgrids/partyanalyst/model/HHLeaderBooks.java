package com.itgrids.partyanalyst.model;

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
@Table(name="hh_leader_books")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class HHLeaderBooks extends BaseModel{
	
	/**
	 * 	HHLeaderBooks Entity
	 * 	@author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * 	@since	March 27 2014
	 */
	private static final long serialVersionUID = 1L;
	private Long hhLeaderBookId;
	private Long leaderId;
	private Long bookNo;
	private HHLeader leader;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hh_leader_book_id", unique = true, nullable = false)
	public Long getHhLeaderBookId() {
		return hhLeaderBookId;
	}
	public void setHhLeaderBookId(Long hhLeaderBookId) {
		this.hhLeaderBookId = hhLeaderBookId;
	}
	
	
	@Column(name="leader_id")
	public Long getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}
	
	@Column(name="book_no")
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "leader_id", insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHLeader getLeader() {
		return leader;
	}
	public void setLeader(HHLeader leader) {
		this.leader = leader;
	}
	
		

}
