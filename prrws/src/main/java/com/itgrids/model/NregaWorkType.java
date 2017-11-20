package com.itgrids.model;

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

@Entity
@Table(name = "nrega_work_type")
public class NregaWorkType {

	private Long nregaWorkTypeId;
	private String workType;
	private String workTypeCode;
	private Long nreaProgramId;
	private Long workMainCategoryId;
	private Long workGroupId;
	
	private NregaProgram nregaProgram;
	private WorkMainCategory workMainCategory;
	private WorkGroup workGroup;
	
	@Id
	@Column(name="nrega_work_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaWorkTypeId() {
		return nregaWorkTypeId;
	}
	public void setNregaWorkTypeId(Long nregaWorkTypeId) {
		this.nregaWorkTypeId = nregaWorkTypeId;
	}
	
	@Column(name="work_type")
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	
	@Column(name="work_type_code")
	public String getWorkTypeCode() {
		return workTypeCode;
	}
	public void setWorkTypeCode(String workTypeCode) {
		this.workTypeCode = workTypeCode;
	}
	
	@Column(name="nrea_program_id")
	public Long getNreaProgramId() {
		return nreaProgramId;
	}
	public void setNreaProgramId(Long nreaProgramId) {
		this.nreaProgramId = nreaProgramId;
	}
	
	@Column(name="work_main_category_id")
	public Long getWorkMainCategoryId() {
		return workMainCategoryId;
	}
	public void setWorkMainCategoryId(Long workMainCategoryId) {
		this.workMainCategoryId = workMainCategoryId;
	}
	
	@Column(name="work_group_id")
	public Long getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(Long workGroupId) {
		this.workGroupId = workGroupId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrea_program_id", insertable = false, updatable = false)
	public NregaProgram getNregaProgram() {
		return nregaProgram;
	}
	public void setNregaProgram(NregaProgram nregaProgram) {
		this.nregaProgram = nregaProgram;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "work_main_category_id", insertable = false, updatable = false)
	public WorkMainCategory getWorkMainCategory() {
		return workMainCategory;
	}
	public void setWorkMainCategory(WorkMainCategory workMainCategory) {
		this.workMainCategory = workMainCategory;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "work_group_id", insertable = false, updatable = false)
	public WorkGroup getWorkGroup() {
		return workGroup;
	}
	public void setWorkGroup(WorkGroup workGroup) {
		this.workGroup = workGroup;
	}
}
