package com.itgrids.eliteclub.model;

// Generated Apr 11, 2014 7:30:00 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Status generated by hbm2java
 */
@Entity
@Table(name = "status")
public class Status implements java.io.Serializable {

	private Integer statausId;
	private String statusName;
	private Set<ContentStatusRelation> contentStatusRelations = new HashSet<ContentStatusRelation>(
			0);

	public Status() {
	}

	public Status(String statusName,
			Set<ContentStatusRelation> contentStatusRelations) {
		this.statusName = statusName;
		this.contentStatusRelations = contentStatusRelations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "stataus_id", unique = true, nullable = false)
	public Integer getStatausId() {
		return this.statausId;
	}

	public void setStatausId(Integer statausId) {
		this.statausId = statausId;
	}

	@Column(name = "status_name", length = 45)
	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	public Set<ContentStatusRelation> getContentStatusRelations() {
		return this.contentStatusRelations;
	}

	public void setContentStatusRelations(
			Set<ContentStatusRelation> contentStatusRelations) {
		this.contentStatusRelations = contentStatusRelations;
	}

}
