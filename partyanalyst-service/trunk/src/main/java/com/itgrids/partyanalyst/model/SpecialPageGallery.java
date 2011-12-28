package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="special_page_gallery")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageGallery  implements Serializable{
	
	private static final long serialVersionUID = -1142255989596343369L;
	private Long specialPageGalleryId;
	private Gallary gallary;
	private SpecialPage specialPage;
	private Date createdDate;
	private Date updatedDate; 
	private String isDelect;
	
	public SpecialPageGallery(){
		
	}
	public SpecialPageGallery(Long specialPageGalleryId,Gallary gallary,SpecialPage specialPage,Date createdDate,Date updatedDate,String isDelect){
		 this.createdDate = createdDate;
		 this.specialPageGalleryId =specialPageGalleryId;
		 this.gallary = gallary;
		 this.specialPage = specialPage;
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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "gallary_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Gallary getGallary() {
		return gallary;
	}
	public void setGallary(Gallary gallary) {
		this.gallary = gallary;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "special_page_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public SpecialPage getSpecialPage() {
		return specialPage;
	}
	public void setSpecialPage(SpecialPage specialPage) {
		this.specialPage = specialPage;
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
