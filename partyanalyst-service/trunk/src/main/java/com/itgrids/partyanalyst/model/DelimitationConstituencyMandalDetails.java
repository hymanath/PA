/**
 * 
 */
package com.itgrids.partyanalyst.model;

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
 * @author sys
 *
 */
@Entity
@Table(name = "delimitation_constituency_mandal_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DelimitationConstituencyMandalDetails implements java.io.Serializable {

	private Long dcmId;
	private DelimitationConstituency delimitationConstituency;
	private Tehsil tehsil;
	private String isPartial;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dcm_id", unique = true, nullable = false)
	public Long getDcmId() {
		return dcmId;
	}

	public void setDcmId(Long dcmId) {
		this.dcmId = dcmId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "delimitation_constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public DelimitationConstituency getDelimitationConstituency() {
		return delimitationConstituency;
	}

	public void setDelimitationConstituency(DelimitationConstituency delimitationConstituency) {
		this.delimitationConstituency = delimitationConstituency;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Tehsil getTehsil() {
		return tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}

	@Column(name = "is_partial", length = 50)
	public String getIsPartial() {
		return isPartial;
	}

	public void setIsPartial(String isPartial) {
		this.isPartial = isPartial;
	}

}

