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
@Table(name = "tdp_cadre_psychometric_test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadrePsychometricTest extends BaseModel implements Serializable{
	
	private Long tdpCadrePsychometricTestId;
	private Long tdpCadreId;
	private Long psychometricTestId;
	private String isDeleted;
	private Date insertedTime;
	
	private TdpCadre tdpCadre;
	private PsychometricTest psychometricTest;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_psychometric_test_id", unique = true, nullable = false)
	public Long getTdpCadrePsychometricTestId() {
		return tdpCadrePsychometricTestId;
	}
	public void setTdpCadrePsychometricTestId(Long tdpCadrePsychometricTestId) {
		this.tdpCadrePsychometricTestId = tdpCadrePsychometricTestId;
	}
	
	@Column(name = "tdp_cadre_id", length = 100)
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "psychometric_test_id", length = 100)
	public Long getPsychometricTestId() {
		return psychometricTestId;
	}
	public void setPsychometricTestId(Long psychometricTestId) {
		this.psychometricTestId = psychometricTestId;
	}
	
	@Column(name = "is_deleted", length = 100)
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name = "inserted_time", length = 100)
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "psychometric_test_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public PsychometricTest getPsychometricTest() {
		return psychometricTest;
	}
	public void setPsychometricTest(PsychometricTest psychometricTest) {
		this.psychometricTest = psychometricTest;
	}
}
