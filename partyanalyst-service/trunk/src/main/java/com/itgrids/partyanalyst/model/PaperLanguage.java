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
@Table(name = "paper_language")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaperLanguage  extends BaseModel implements Serializable
{
	
	private Long paperLanguageId;
	private String  paperLanguage;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="paper_language_id", unique=true, nullable=false)
	public Long getPaperLanguageId() {
		return paperLanguageId;
	}
	public void setPaperLanguageId(Long paperLanguageId) {
		this.paperLanguageId = paperLanguageId;
	}
	
	@Column(name = "paper_language", length = 75)
	public String getPaperLanguage() {
		return paperLanguage;
	}
	public void setPaperLanguage(String paperLanguage) {
		this.paperLanguage = paperLanguage;
	}
	
	

	
}

