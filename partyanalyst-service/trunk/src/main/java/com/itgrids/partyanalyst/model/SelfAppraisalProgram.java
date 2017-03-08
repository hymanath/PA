package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="self_appraisal_program")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalProgram {

	private Long selfAppraisalProgramId;
	private String program;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_program_id", unique=true, nullable=false)
	public Long getSelfAppraisalProgramId() {
		return selfAppraisalProgramId;
	}
	public void setSelfAppraisalProgramId(Long selfAppraisalProgramId) {
		this.selfAppraisalProgramId = selfAppraisalProgramId;
	}
	@Column(name = "program")
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
    
	
}
