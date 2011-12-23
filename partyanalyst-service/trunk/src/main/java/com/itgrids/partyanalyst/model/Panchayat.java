package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * panchayat entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="panchayat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Panchayat extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = -6994854562631806997L;
	
	private Long panchayatId;
	private String panchayatName;
	private Tehsil tehsil;
	private Set<PanchayatHamlet> panchayatHamlets = new HashSet<PanchayatHamlet>(0);
	
	public Panchayat()
	{}
	
	public Panchayat(String panchayatName,Tehsil tehsil)
	{
		this.panchayatName = panchayatName;
		this.tehsil = tehsil;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="panchayat_id", unique=true, nullable=false)
	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	@Column(name="panchayat_name",length=100)
	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tehsil_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Tehsil getTehsil() {
		return tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "panchayat")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<PanchayatHamlet> getPanchayatHamlets() {
		return panchayatHamlets;
	}

	public void setPanchayatHamlets(Set<PanchayatHamlet> panchayatHamlets) {
		this.panchayatHamlets = panchayatHamlets;
	}

}
