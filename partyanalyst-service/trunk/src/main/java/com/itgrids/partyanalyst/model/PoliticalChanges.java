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
@Table(name = "political_changes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PoliticalChanges extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// Fields
	private Long politicalChangesId;
	private Party party;
	private Registration registration;
	private ProblemExternalSource externalSource;
	private InformationSource politicalChangesInformationSource;
	private String title;
	private String description;
	private Date identifiedDate;
	private Date occuredDate;
	private Date updatedDate;	
	private String isDelete;
	private String effectedRange;
	private Long effectedLocation;
	
	// Constructors

	/** default constructor */
	public PoliticalChanges() {
	}

	/** minimal constructor */
	public PoliticalChanges(Long politicalChangesId) {
		this.politicalChangesId = politicalChangesId;
	}

	/** full constructor */
	public PoliticalChanges(Long politicalChangesId,String sourceOfInformation,
			                     Party party,Registration registration,ProblemExternalSource externalSource,
			                     Date identifiedDate,Date occuredDate,
			                     Date updatedDate,String description,InformationSource politicalChangesInformationSource
								 ,String title,String isDelete,String effectedRange,Long effectedLocation) {
		this.politicalChangesId = politicalChangesId;
		this.party = party;
		this.registration = registration;
		this.externalSource = externalSource;
		this.politicalChangesInformationSource = politicalChangesInformationSource;		
		this.title = title;
		this.identifiedDate = identifiedDate;
		this.occuredDate = occuredDate;
		this.updatedDate = updatedDate;
		this.description = description;
		this.isDelete = isDelete;
		this.effectedRange = effectedRange;
		this.effectedLocation = effectedLocation;
	}


	
	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "political_changes_id", unique = true, nullable = false)
	public Long getPoliticalChangesId() {
		return politicalChangesId;
	}

	public void setPoliticalChangesId(Long politicalChangesId) {
		this.politicalChangesId = politicalChangesId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}
	
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}	 
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "external_source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemExternalSource getExternalSource() {
		return externalSource;
	}

	public void setExternalSource(ProblemExternalSource externalSource) {
		this.externalSource = externalSource;
	}


	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "information_source_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public InformationSource getPoliticalChangesInformationSource() {
		return politicalChangesInformationSource;
	}

	public void setPoliticalChangesInformationSource(
			InformationSource politicalChangesInformationSource) {
		this.politicalChangesInformationSource = politicalChangesInformationSource;
	}

	@Column(name = "title", length = 200)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "identified_date", length = 25)
	public Date getIdentifiedDate() {
		return identifiedDate;
	}

	public void setIdentifiedDate(Date identifiedDate) {
		this.identifiedDate = identifiedDate;
	}

	@Column(name = "occured_date", length = 25)
	public Date getOccuredDate() {
		return occuredDate;
	}

	public void setOccuredDate(Date occuredDate) {
		this.occuredDate = occuredDate;
	}

	@Column(name = "updated_date", length = 25)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "is_delete", length = 25)
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}		
	
	@Column(name = "effected_range", length = 25)
	public String getEffectedRange() {
		return effectedRange;
	}

	public void setEffectedRange(String effectedRange) {
		this.effectedRange = effectedRange;
	}

	@Column(name = "effected_location", length = 25)
	public Long getEffectedLocation() {
		return effectedLocation;
	}

	public void setEffectedLocation(Long effectedLocation) {
		this.effectedLocation = effectedLocation;
	}
	
}
