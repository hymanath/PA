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
@Table(name = "editions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Editions  extends BaseModel implements Serializable
{
	
	
	private Long editionId;
	private String editionName;
	private NewsPaper newsPaper;
    private EditionType editionType;
    private Long editionParentId;
	private String area;
	private String available;
	private District district;
	private String editionAlias;
	private Long maxPages;
	
	private State state;
	private String downloadUrl;
	private Long newsPaperId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="edition_id", unique=true, nullable=false)
	public Long getEditionId() {
		return editionId;
	}
	public void setEditionId(Long editionId) {
		this.editionId = editionId;
	}
	
	
	@Column(name = "edition_name", length = 75)
	public String getEditionName() {
		return editionName;
	}
	public void setEditionName(String editionName) {
		this.editionName = editionName;
	}
	

     @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	 @JoinColumn(name="news_paper_id", insertable=false, updatable = false)
	 @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NewsPaper getNewsPaper() {
		return newsPaper;
	}
	public void setNewsPaper(NewsPaper newsPaper) {
		this.newsPaper = newsPaper;
	}
	
	

	  @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	  @JoinColumn(name="edition_type_id")
	  @LazyToOne(LazyToOneOption.NO_PROXY)
	  @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EditionType getEditionType() {
		return editionType;
	}
	public void setEditionType(EditionType editionType) {
		this.editionType = editionType;
	}
	
	@Column(name = "edition_parent_id")
    public Long getEditionParentId() {
		return editionParentId;
	}
	public void setEditionParentId(Long editionParentId) {
		this.editionParentId = editionParentId;
	}
	
	@Column(name = "area")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	@Column(name = "available")
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="district_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="state_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	@Column(name = "edition_alias")
	public String getEditionAlias() {
		return editionAlias;
	}
	public void setEditionAlias(String editionAlias) {
		this.editionAlias = editionAlias;
	}
	
	@Column(name = "download_url")
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
	@Column(name = "max_pages")
	public Long getMaxPages() {
		return maxPages;
	}
	public void setMaxPages(Long maxPages) {
		this.maxPages = maxPages;
	}
	
	@Column(name = "news_paper_id")
	public Long getNewsPaperId() {
		return newsPaperId;
	}
	public void setNewsPaperId(Long newsPaperId) {
		this.newsPaperId = newsPaperId;
	}
	
	
}
