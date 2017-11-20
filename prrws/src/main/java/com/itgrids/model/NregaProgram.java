package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nrega_program")
public class NregaProgram {

	private Long nregaProgramId;
	private String programName;
	
	@Id
	@Column(name="nrega_program_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaProgramId() {
		return nregaProgramId;
	}
	public void setNregaProgramId(Long nregaProgramId) {
		this.nregaProgramId = nregaProgramId;
	}
	
	@Column(name="program_name")
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
}
