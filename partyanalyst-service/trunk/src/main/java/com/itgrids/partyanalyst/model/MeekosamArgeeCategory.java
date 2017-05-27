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
@Table(name = "meekosam_argee_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamArgeeCategory extends BaseModel implements java.io.Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long 	meekosamArgeeCateogoryId;
	private String 	meekosamArgeeCategory;
	private String 	isActive;
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "meekosam_argee_category_id", unique = true, nullable = false)
	 public Long getMeekosamArgeeCateogoryId() {
		return meekosamArgeeCateogoryId;
	 }
	 public void setMeekosamArgeeCateogoryId(Long meekosamArgeeCateogoryId) {
		this.meekosamArgeeCateogoryId = meekosamArgeeCateogoryId;
	 }
		
	 @Column(name="meekosam_argee_category")
	 public String getMeekosamArgeeCategory() {
		return meekosamArgeeCategory;
	 }
	 public void setMeekosamArgeeCategory(String meekosamArgeeCategory) {
		this.meekosamArgeeCategory = meekosamArgeeCategory;
	 }
	 
	 @Column(name="is_active")
	 public String getIsActive() {
		return isActive;
	 }
	 public void setIsActive(String isActive) {
		this.isActive = isActive;
	 }
}
