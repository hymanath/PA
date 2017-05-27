package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "meekosam_caste_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamCasteCategory extends BaseModel implements java.io.Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long 	meekosamCasteCategoryId;
	private String 	meekosamCasteCategory;
	private String 	isActive;
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "meekosam_caste_category_id", unique = true, nullable = false)
	 public Long getMeekosamCasteCategoryId() {
	 	return meekosamCasteCategoryId;
	 }
	 public void setMeekosamCasteCategoryId(Long meekosamCasteCategoryId) {
		this.meekosamCasteCategoryId = meekosamCasteCategoryId;
	 }
		
	 @Column(name="meekosam_caste_category")
	 public String getMeekosamCasteCategory() {
		return meekosamCasteCategory;
	 }
	 public void setMeekosamCasteCategory(String meekosamCasteCategory) {
		this.meekosamCasteCategory = meekosamCasteCategory;
	 }
	 
	 @Column(name="is_active")
	 public String getIsActive() {
		return isActive;
	 }
	 public void setIsActive(String isActive) {
		this.isActive = isActive;
	 }
	 
}
