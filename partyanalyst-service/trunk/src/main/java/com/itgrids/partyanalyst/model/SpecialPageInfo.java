package com.itgrids.partyanalyst.model;

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
@Table(name = "special_page_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageInfo extends BaseModel implements java.io.Serializable{

	private Long specialPageInfoId;
	private String title;
	private String description;
	private String isDisplayEnabled;
	private String showImgPath;
	private SpecialPage specialPage;
	
	public SpecialPageInfo(){}
	public SpecialPageInfo(String title,String description,
			String isDisplayEnabled,String showImgPath,SpecialPage specialPage)
	{
		this.title = title;
		this.description = description;
		this.isDisplayEnabled = isDisplayEnabled;
		this.showImgPath = showImgPath;
		this.specialPage = specialPage;
	}
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="special_page_info_id", unique=true, nullable=false)
	public Long getSpecialPageInfoId() {
		return specialPageInfoId;
	}
	public void setSpecialPageInfoId(Long specialPageInfoId) {
		this.specialPageInfoId = specialPageInfoId;
	}
	
	@Column(name="title",length=300)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="description",length=500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="is_display_enabled",length=10)
	public String getIsDisplayEnabled() {
		return isDisplayEnabled;
	}
	public void setIsDisplayEnabled(String isDisplayEnabled) {
		this.isDisplayEnabled = isDisplayEnabled;
	}
	
	@Column(name="show_img_path",length=200)
	public String getShowImgPath() {
		return showImgPath;
	}
	public void setShowImgPath(String showImgPath) {
		this.showImgPath = showImgPath;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="special_page_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SpecialPage getSpecialPage() {
		return specialPage;
	}
	public void setSpecialPage(SpecialPage specialPage) {
		this.specialPage = specialPage;
	}
	
	
}
