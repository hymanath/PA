/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 24, 2011
 */
package com.itgrids.partyanalyst.model;

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
 * problem_file entity.
 * 
 * @author sachin
 */

@Entity
@Table(name = "problem_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemFile extends BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long problemFileId;
	private ProblemHistory problemHistory;
	private String isApproved;
	private File file;

	/** default constructor */
	public ProblemFile() {

	}

	/** full constructor */
	public ProblemFile(Long problemFileId, ProblemHistory problemHistory,
			String isApproved, File file) {
		this.problemFileId = problemFileId;
		this.problemHistory = problemHistory;
		this.isApproved = isApproved;
		this.file = file;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_file_id", unique = true, nullable = false)
	public Long getProblemFileId() {
		return problemFileId;
	}

	public void setProblemFileId(Long problemFileId) {
		this.problemFileId = problemFileId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_history_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public ProblemHistory getProblemHistory() {
		return problemHistory;
	}

	public void setProblemHistory(ProblemHistory problemHistory) {
		this.problemHistory = problemHistory;
	}

	@Column(name = "is_approved")
	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
