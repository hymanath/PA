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
@Table(name = "self_appraisal_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalComment extends BaseModel implements Serializable {
	
	private Long selfAppraisalcommentId;
	private Long tdpCadreId;
	private Long selfAppraisalToursMonthId;
	private Long insertedBy;
	private Date insertedtime;
	private String isDeleted;
	private String comment;
	
	private TdpCadre tdpCadre;
	private SelfAppraisalToursMonth selfAppraisalToursMonth;
	
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "self_appraisal_comment_id", unique = true, nullable = false)
	public Long getSelfAppraisalcommentId() {
		return selfAppraisalcommentId;
	}
	public void setSelfAppraisalcommentId(Long selfAppraisalcommentId) {
		this.selfAppraisalcommentId = selfAppraisalcommentId;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "self_appraisal_tours_month_id")
	public Long getSelfAppraisalToursMonthId() {
		return selfAppraisalToursMonthId;
	}
	public void setSelfAppraisalToursMonthId(Long selfAppraisalToursMonthId) {
		this.selfAppraisalToursMonthId = selfAppraisalToursMonthId;
	}
	
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedtime() {
		return insertedtime;
	}
	public void setInsertedtime(Date insertedtime) {
		this.insertedtime = insertedtime;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_cadre_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "self_appraisal_tours_month_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public SelfAppraisalToursMonth getSelfAppraisalToursMonth() {
		return selfAppraisalToursMonth;
	}	
	
	public void setSelfAppraisalToursMonth(
			SelfAppraisalToursMonth selfAppraisalToursMonth) {
		this.selfAppraisalToursMonth = selfAppraisalToursMonth;
	}
	
		
	}
