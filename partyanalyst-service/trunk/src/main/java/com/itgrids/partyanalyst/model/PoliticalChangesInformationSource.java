package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "political_changes_information_source")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PoliticalChangesInformationSource extends BaseModel implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Fields
	private Long sourceId;
	private String sourceName;
	private String description;
	private Set<PoliticalChanges> localPoliticalChanges = new HashSet<PoliticalChanges>(0);
	// Constructors

	/** default constructor */
	public PoliticalChangesInformationSource() {
	}

	/** minimal constructor */
	public PoliticalChangesInformationSource(Long sourceId) {
		this.sourceId = sourceId;
	}

	//Full Constructor
	public PoliticalChangesInformationSource(Long sourceId,String sourceName,String description,Set<PoliticalChanges> localPoliticalChanges) {
		this.sourceId = sourceId;
		this.sourceName = sourceName;
		this.description = description;
		this.localPoliticalChanges = localPoliticalChanges;
	}

	//Getters and Setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "source_id", unique = true, nullable = false)
	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_name", length = 25)
	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	@Column(name = "description", length = 25)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "politicalChangesInformationSource")
	public Set<PoliticalChanges> getLocalPoliticalChanges() {
		return localPoliticalChanges;
	}

	public void setLocalPoliticalChanges(
			Set<PoliticalChanges> localPoliticalChanges) {
		this.localPoliticalChanges = localPoliticalChanges;
	}
	
}
