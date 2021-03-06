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

/**
 * Description
 * @author <a href="mailto:shrinu.pittala@itgrids.com">Shrinu Pittala</a> 
 * @version 1.0/
 */

@Entity
@Table(name = "tehsil_constituency")
public class TehsilConstituency {
	
	private static final long serialVersionUID = -2853930539938433902L;
	
	private Long tehsilConstituencyId;
	private Long constituencyId;
	private Long tehsilId;
	
	private Constituency constituency;
	private Tehsil tehsil;	
	
	@Id
	@Column(name="tehsil_constituency_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getTehsilConstituencyId() {
		return tehsilConstituencyId;
	}
	public void setTehsilConstituencyId(Long tehsilConstituencyId) {
		this.tehsilConstituencyId = tehsilConstituencyId;
	}

	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@Column(name="tehsil_id")
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id", insertable = false, updatable = false)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id", insertable = false, updatable = false)
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}

}
