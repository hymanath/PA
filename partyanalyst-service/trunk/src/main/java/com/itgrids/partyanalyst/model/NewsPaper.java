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
@Table(name = "news_paper")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsPaper  extends BaseModel implements Serializable
{
	private Long newsPaperId;
	private String newsPaper;
	private PaperLanguage paperLanguage;
	private String districtEditionName;
	private String paperName;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="news_paper_id", unique=true, nullable=false)
	public Long getNewsPaperId() {
		return newsPaperId;
	}
	public void setNewsPaperId(Long newsPaperId) {
		this.newsPaperId = newsPaperId;
	}
	
	@Column(name = "news_paper", length = 75)
	public String getNewsPaper() {
		return newsPaper;
	}
	public void setNewsPaper(String newsPaper) {
		this.newsPaper = newsPaper;
	}
	
	
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	 @JoinColumn(name="paper_language_id")
	 @LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PaperLanguage getPaperLanguage() {
		return paperLanguage;
	}
	public void setPaperLanguage(PaperLanguage paperLanguage) {
		this.paperLanguage = paperLanguage;
	}
	
	@Column(name = "district_edition_name", length = 45)
	public String getDistrictEditionName() {
		return districtEditionName;
	}
	public void setDistrictEditionName(String districtEditionName) {
		this.districtEditionName = districtEditionName;
	}
	
	@Column(name = "paper_name")
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	
		
}
