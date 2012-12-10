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
@Table(name="caste_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CasteCategory extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Long casteCategoryId;
	private String categoryName;
	private String description;
	
	private Set<CasteCategoryGroup> casteCategoryGroup=new HashSet<CasteCategoryGroup>(0);
	
	public CasteCategory(){
			
		}
	
	public CasteCategory(Long casteCategoryId,String categoryName, String desctiption){
		this.casteCategoryId=casteCategoryId;
		this.categoryName=categoryName;
		this.description=desctiption;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "caste_category_id", unique = true, nullable = false)
	public Long getCasteCategoryId() {
		return casteCategoryId;
	}
	public void setCasteCategoryId(Long casteCategoryId) {
		this.casteCategoryId = casteCategoryId;
	}
	
	@Column(name = "category_name", length = 40)
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Column(name = "description", length = 40)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "casteCategory")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CasteCategoryGroup> getCasteCategoryGroup() {
		return casteCategoryGroup;
	}

	public void setCasteCategoryGroup(Set<CasteCategoryGroup> casteCategoryGroup) {
		this.casteCategoryGroup = casteCategoryGroup;
	}
	
}
