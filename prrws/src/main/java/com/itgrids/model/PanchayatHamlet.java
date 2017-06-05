package com.itgrids.model;

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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * Description
 * @author <a href="mailto:kondababu.kurakula@itgrids.com">kurakula kondababu</a> 
 * @version 1.0/
 */


@Entity
@Table(name = "panchayat_hamlet")
public class PanchayatHamlet {
	
	@Id
	@Column(name="panchayat_hamlet_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long panchayatHamletId;
	
	@Column(name="panchayat_id")
	private Long panchayatId;
	
	@Column(name="hamlet_id")
	private Long hamletId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	private Panchayat panchayat;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	private Hamlet hamlet;
	
	public Long getPanchayatHamletId() {
		return panchayatHamletId;
	}
	public void setPanchayatHamletId(Long panchayatHamletId) {
		this.panchayatHamletId = panchayatHamletId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
	public Hamlet getHamlet() {
		return hamlet;
	}
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	
}
