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

@Entity
@Table(name="voter_category_values")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterCategoryValues extends BaseModel implements Serializable{
//voter_category_values_id            
	private static final long serialVersionUID = 1L;
	private Long voterCategoryValuesId;
	private CategoryValues categoryValues;
	private Voter voter;
	
	public VoterCategoryValues(){
		
	}
	public VoterCategoryValues(Long voterCategoryValuesId,CategoryValues categoryValues,Voter voter){
		this.voterCategoryValuesId=voterCategoryValuesId;
		this.categoryValues=categoryValues;
		this.voter=voter;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_category_values_id", unique = true, nullable = false)
	public Long getVoterCategoryValuesId() {
		return voterCategoryValuesId;
	}
	public void setVoterCategoryValuesId(Long voterCategoryValuesId) {
		this.voterCategoryValuesId = voterCategoryValuesId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="category_values_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CategoryValues getCategoryValues() {
		return categoryValues;
	}
	public void setCategoryValues(CategoryValues categoryValues) {
		this.categoryValues = categoryValues;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	
}
