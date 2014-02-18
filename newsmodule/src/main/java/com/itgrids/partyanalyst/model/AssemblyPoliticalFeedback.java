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
@Table(name = "assembly_political_feedback")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AssemblyPoliticalFeedback implements Serializable{

	
	
	private static final long serialVersionUID = -1760411490868335990L;
	private Long assemblyPoliticalFeedbackId;
	private ParlimentPoliticalFeedback parlimentPoliticalFeedback;
	private Constituency constituency;
	private String impNews;
	private String cmPoliticalFeedback;
	private String otherPoliticalBack;
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "assembly_political_feedback_id", unique = true, nullable = false)
	public Long getAssemblyPoliticalFeedbackId() {
		return assemblyPoliticalFeedbackId;
	}
	public void setAssemblyPoliticalFeedbackId(Long assemblyPoliticalFeedbackId) {
		this.assemblyPoliticalFeedbackId = assemblyPoliticalFeedbackId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "parliment_political_feedback_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ParlimentPoliticalFeedback getParlimentPoliticalFeedback() {
		return parlimentPoliticalFeedback;
	}
	public void setParlimentPoliticalFeedback(
			ParlimentPoliticalFeedback parlimentPoliticalFeedback) {
		this.parlimentPoliticalFeedback = parlimentPoliticalFeedback;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@Column(name="imp_news")
	public String getImpNews() {
		return impNews;
	}
	public void setImpNews(String impNews) {
		this.impNews = impNews;
	}
	
	@Column(name="cm_political_feedback")
	public String getCmPoliticalFeedback() {
		return cmPoliticalFeedback;
	}
	public void setCmPoliticalFeedback(String cmPoliticalFeedback) {
		this.cmPoliticalFeedback = cmPoliticalFeedback;
	}
	
	@Column(name="other_political_back")
	public String getOtherPoliticalBack() {
		return otherPoliticalBack;
	}
	public void setOtherPoliticalBack(String otherPoliticalBack) {
		this.otherPoliticalBack = otherPoliticalBack;
	}
	
	
}
