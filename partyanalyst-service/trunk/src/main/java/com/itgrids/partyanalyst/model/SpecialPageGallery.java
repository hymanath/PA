package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="special_page_gallery")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageGallery  implements Serializable{
	
	private static final long serialVersionUID = -1142255989596343369L;
	private Long specialPageGalleryId;
	private Long galleryId;
	private Long specialPageId;
	private Date createdDate;
	private Date updatedDate; 
	private String isDelect;
	
	public SpecialPageGallery(){
		
	}
	public SpecialPageGallery(Long specialPageGalleryId,Long galleryId,Long specialPageId,Date createdDate,Date updatedDate,String isDelect){
		 this.createdDate = createdDate;
		 this.specialPageGalleryId =specialPageGalleryId;
		 this.galleryId = galleryId;
		 this.specialPageId = specialPageId;
		 this.updatedDate = updatedDate;
		 this.isDelect = isDelect;
		 
		 }
	//special_page_gallery_id    bigint(15) AUTO_INCREMENT NOT NULL
	//PRIMARY KEY(special_page_gallery_id)

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="special_page_gallery_id", unique=true, nullable=false)
	public Long getSpecialPageGalleryId() {
		return specialPageGalleryId;
	}
	public void setSpecialPageGalleryId(Long specialPageGalleryId) {
		this.specialPageGalleryId = specialPageGalleryId;
	}
	//gallary_id        bigint(15) NULL
	@Column(name="gallary_id")
	public Long getGalleryId() {
		return galleryId;
	}
	public void setGalleryId(Long galleryId) {
		this.galleryId = galleryId;
	}
	//special_page_id        bigint(15) NULL
	@Column(name="special_page_id")
	public Long getSpecialPageId() {
		return specialPageId;
	}
	public void setSpecialPageId(Long specialPageId) {
		this.specialPageId = specialPageId;
	}
	//created_date        timestamp NULL
	@Column(name="created_date",length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	//updated_date        timestamp NULL
	@Column(name="updated_date",length=10)
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	// is_delete       varchar(10) NULL
	@Column(name="is_delete",length=10)
	public String getIsDelect() {
		return isDelect;
	}
	public void setIsDelect(String isDelect) {
		this.isDelect = isDelect;
	}
	
}
