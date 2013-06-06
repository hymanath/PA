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
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "delimitation_constituency_mandal_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DelimitationConstituencyMandal extends BaseModel {

	private static final long serialVersionUID = 834695361806308898L;
	private Long dcm_id;
	private DelimitationConstituency delimitationConstituency;
	private Tehsil tehsil;
	private String isPartial;
	//private Set<DelimitationVillage> delimitationVillage = new HashSet<DelimitationVillage>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dcm_id", unique = true, nullable = false)
	public Long getDcm_id() {
		return dcm_id;
	}
	public void setDcm_id(Long dcm_id) {
		this.dcm_id = dcm_id;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "delimitation_constituency_id")
	public DelimitationConstituency getDelimitationConstituency() {
		return delimitationConstituency;
	}
	public void setDelimitationConstituency(
			DelimitationConstituency delimitationConstituency) {
		this.delimitationConstituency = delimitationConstituency;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	@Column(name = "is_partial")
	public String getIsPartial() {
		return isPartial;
	}
	public void setIsPartial(String isPartial) {
		this.isPartial = isPartial;
	}
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "delimitationConstituencyMandal")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<DelimitationVillage> getDelimitationVillage() {
		return delimitationVillage;
	}
	public void setDelimitationVillage(Set<DelimitationVillage> delimitationVillage) {
		this.delimitationVillage = delimitationVillage;
	}*/

}
