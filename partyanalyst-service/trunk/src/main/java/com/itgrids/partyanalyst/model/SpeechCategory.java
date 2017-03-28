package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "speech_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpeechCategory extends BaseModel implements Serializable{
	
	private Long speechCategoryId;
	private String category;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "speech_category_id", unique = true, nullable = false)
	public Long getSpeechCategoryId() {
		return speechCategoryId;
	}
	public void setSpeechCategoryId(Long speechCategoryId) {
		this.speechCategoryId = speechCategoryId;
	}
	
	@Column(name = "category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
