/* 
 * Copyright (c) 2011 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 27, 2011
 */
package com.itgrids.partyanalyst.model;

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
import org.hibernate.annotations.NotFoundAction;

/**
 * file entity.
 * 
 * @author sachin
 */
@Entity
@Table(name = "source")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Source extends BaseModel implements java.io.Serializable {

	private static final long serialVersionUID = -1588780888291765414L;
	private Long sourceId;
	private String source;
	private Set<File> files = new HashSet<File>(0);

	/** default constructor */
	public Source() {

	}

	/** full constructor */
	public Source(Long sourceId, String source) {
		this.sourceId = sourceId;
		this.source = source;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "source_id", unique = true, nullable = false)
	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source", length = 100)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "source")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

}
