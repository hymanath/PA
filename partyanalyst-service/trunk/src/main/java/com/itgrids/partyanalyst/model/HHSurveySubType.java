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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="hh_survey_sub_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HHSurveySubType extends BaseModel implements Serializable{
	
	
	private Long HHSurveySubTypeId;
	private String name;
	private String isMain;
	private Long parentId;
	private HHSurveySubType parent;
	


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hh_survey_sub_type_id", unique = true, nullable = false)
	public Long getHHSurveySubTypeId() {
		return HHSurveySubTypeId;
	}
	public void setHHSurveySubTypeId(Long hHSurveySubTypeId) {
		HHSurveySubTypeId = hHSurveySubTypeId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="is_main")
	public String getIsMain() {
		return isMain;
	}
	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
	
	
	@Column(name="parent_id")
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHSurveySubType getParent() {
		return parent;
	}
	public void setParent(HHSurveySubType parent) {
		this.parent = parent;
	}
}
