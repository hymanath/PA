package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="caste")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Caste extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long casteId;
	private String casteName;
	
	private Set<CasteState> casteStatewise=new HashSet<CasteState>(0);
	
	public Caste(){
	
	}
	public Caste(Long casteId,String casteName){
		this.casteId=casteId;
		this.casteName=casteName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "caste_id", unique = true, nullable = false)
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	
	@Column(name="caste_name")
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "caste")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CasteState> getCasteStatewise() {
		return casteStatewise;
	}

	public void setCasteStatewise(Set<CasteState> casteStatewise) {
		this.casteStatewise = casteStatewise;
	}
	

}
