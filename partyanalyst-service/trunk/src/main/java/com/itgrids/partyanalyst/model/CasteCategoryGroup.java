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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="caste_category_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CasteCategoryGroup extends BaseModel implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Long casteCategoryGroupId;
	private CasteCategory casteCategory;
	private String casteCategoryGroupName;
	
	
	private Set<CasteState> casteStatewise=new HashSet<CasteState>();
	
		
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "casteCategoryGroup")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CasteState> getCasteStatewise() {
		return casteStatewise;
	}
	public void setCasteStatewise(Set<CasteState> casteStatewise) {
		this.casteStatewise = casteStatewise;
	}

	
	public CasteCategoryGroup(){
		
	}
	public CasteCategoryGroup(Long casteCategoryGroupId,CasteCategory castecategory,String casteCategoryGroupName){
		
		this.casteCategoryGroupId=casteCategoryGroupId;
		this.casteCategory=castecategory;
		this.casteCategoryGroupName=casteCategoryGroupName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "caste_category_group_id", unique = true, nullable = false)
	public Long getCasteCategoryGroupId() {
		return casteCategoryGroupId;
	}

	public void setCasteCategoryGroupId(Long casteCategoryGroupId) {
		this.casteCategoryGroupId = casteCategoryGroupId;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="caste_category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteCategory getCasteCategory() {
		return casteCategory;
	}
	
	public void setCasteCategory(CasteCategory casteCategory) {
		this.casteCategory = casteCategory;
	}
	
	@Column(name="caste_category_group_name")
	public String getCasteCategoryGroupName() {
		return casteCategoryGroupName;
	}
	public void setCasteCategoryGroupName(String casteCategoryGroupName) {
		this.casteCategoryGroupName = casteCategoryGroupName;
	}



	

}
