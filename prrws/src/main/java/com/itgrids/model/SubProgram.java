package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "sub_program")
public class SubProgram implements Serializable {
	private Long subProgramId;
	private String programName;
	private String isDeleted;
	private Long orderNo;
	@Id
	@Column(name="sub_program_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getSubProgramId() {
		return subProgramId;
	}
	public void setSubProgramId(Long subProgramId) {
		this.subProgramId = subProgramId;
	}
	@Column(name="program_name")
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
		
	
}
