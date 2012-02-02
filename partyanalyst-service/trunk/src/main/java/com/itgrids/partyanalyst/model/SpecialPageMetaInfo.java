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
@Table(name="special_page_meta_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpecialPageMetaInfo extends BaseModel implements Serializable {

	private static final long serialVersionUID = -8981989003734123977L;
	
	private Long specialPageMetaInfoId;
	private SpecialPage specialPage;
	private String keywords;
	private String description;
	
	public SpecialPageMetaInfo()
	{}
	
	public SpecialPageMetaInfo(SpecialPage specialPage,String keywords,String description)
	{
		this.specialPage = specialPage;
		this.keywords = keywords;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="special_page_meta_info_id", unique=true, nullable=false)
	public Long getSpecialPageMetaInfoId() {
		return specialPageMetaInfoId;
	}
	public void setSpecialPageMetaInfoId(Long specialPageMetaInfoId) {
		this.specialPageMetaInfoId = specialPageMetaInfoId;
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
	
	@Column(name="keywords",length=1000)
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@Column(name="description",length=2000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
