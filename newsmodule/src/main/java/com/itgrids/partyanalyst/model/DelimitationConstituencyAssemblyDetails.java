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

@Entity
@Table(name = "delimitation_constituency_assembly_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DelimitationConstituencyAssemblyDetails extends BaseModel implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long delimitationCADId;
	private DelimitationConstituency delimitationConstituency;
	private Constituency constituency;
	
	public DelimitationConstituencyAssemblyDetails(){
		
	}	
	
	
	public DelimitationConstituencyAssemblyDetails(Long delimitationCADId){
		this.delimitationCADId = delimitationCADId;
	}
	
	public DelimitationConstituencyAssemblyDetails(Long delimitationCADId,
			DelimitationConstituency delimitationConstituency,
			Constituency constituency) {
		this.delimitationCADId = delimitationCADId;
		this.delimitationConstituency = delimitationConstituency;
		this.constituency = constituency;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dca_id", unique = true, nullable = false)
	public Long getDelimitationCADId() {
		return delimitationCADId;
	}
	public void setDelimitationCADId(Long delimitationCADId) {
		this.delimitationCADId = delimitationCADId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "delimitation_constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DelimitationConstituency getDelimitationConstituency() {
		return delimitationConstituency;
	}
	public void setDelimitationConstituency(
			DelimitationConstituency delimitationConstituency) {
		this.delimitationConstituency = delimitationConstituency;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	
}
