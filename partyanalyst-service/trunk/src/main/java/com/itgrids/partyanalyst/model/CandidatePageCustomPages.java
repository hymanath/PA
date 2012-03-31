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

/**
 * candidate_page_custom_pages entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="candidate_page_custom_pages")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidatePageCustomPages extends BaseModel implements Serializable{

	private static final long serialVersionUID = 8136921357695897478L;
	
	private Long candidatePageCustomPagesId;
	private Candidate candidate;
	private CustomPage customPage;
	
	public CandidatePageCustomPages()
	{}
	
	public CandidatePageCustomPages(Candidate candidate,CustomPage customPage)
	{
		this.candidate = candidate;
		this.customPage = customPage;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="candidate_page_custom_pages_id", unique=true, nullable=false)
	public Long getCandidatePageCustomPagesId() {
		return candidatePageCustomPagesId;
	}
	public void setCandidatePageCustomPagesId(Long candidatePageCustomPagesId) {
		this.candidatePageCustomPagesId = candidatePageCustomPagesId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="custom_page_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CustomPage getCustomPage() {
		return customPage;
	}
	public void setCustomPage(CustomPage customPage) {
		this.customPage = customPage;
	}
	
	

}
