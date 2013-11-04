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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;





@Entity
@Table(name = "main_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MainCategory extends BaseModel implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2406334218882711232L;
	private Long mainCategoryId;
	private String categoryType;
	private Set<Category> categorys = new HashSet<Category>(0);


	public MainCategory() {

	}
	
	public MainCategory(Long mainCategoryId,String  categoryType)
	{
		this.mainCategoryId=mainCategoryId;
		this.categoryType=categoryType;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "main_category_id", unique = true, nullable = false)
	public Long getMainCategoryId() {
		return mainCategoryId;
	}
	public void setMainCategoryId(Long mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}
	
	@Column(name = "category_type", length=20)
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "maincategory")
	public Set<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(Set<Category> categorys) {
		this.categorys = categorys;
	}
}
