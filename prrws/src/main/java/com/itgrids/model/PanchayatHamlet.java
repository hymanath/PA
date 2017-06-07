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
	
	private static final long serialVersionUID = -2853930539938433902L;
	
	private Long panchayatHamletId;
	private Long panchayatId;
	private Long hamletId;

	private Panchayat panchayat;
	private Hamlet hamlet;
	
	@Id
	@Column(name="panchayat_hamlet_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPanchayatHamletId() {
		return panchayatHamletId;
	}
	public void setPanchayatHamletId(Long panchayatHamletId) {
		this.panchayatHamletId = panchayatHamletId;
	}

	@Column(name="panchayat_id")
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	@Column(name="hamlet_id")
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	
}
