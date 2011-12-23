package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

/**
 * panchayat_hamlet entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="panchayat_hamlet")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PanchayatHamlet extends BaseModel implements Serializable{

	private static final long serialVersionUID = -7340284662875795488L;
	
	private Long panchayatHamletId;
	private Panchayat panchayat;
	private Hamlet hamlet;
	
	public PanchayatHamlet()
	{}
	
	public PanchayatHamlet(Panchayat panchayat,Hamlet hamlet)
	{
		this.panchayat = panchayat;
		this.hamlet = hamlet;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="panchayat_hamlet_id", unique=true, nullable=false)
	public Long getPanchayatHamletId() {
		return panchayatHamletId;
	}

	public void setPanchayatHamletId(Long panchayatHamletId) {
		this.panchayatHamletId = panchayatHamletId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="panchayat_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Panchayat getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}

	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}

	
}
