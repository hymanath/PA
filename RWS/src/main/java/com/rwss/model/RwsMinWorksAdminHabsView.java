package com.rwss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aprwssuser.rws_min_worksadmin_habs_view")
public class RwsMinWorksAdminHabsView {

	private String workId;
	private String habCode;
	private String programmeCode;

	@Id
	@Column(name = "WORK_ID")
	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	@Column(name = "HAB_CODE")
	public String getHabCode() {
		return habCode;
	}

	public void setHabCode(String habCode) {
		this.habCode = habCode;
	}

	@Column(name = "PROGRAMME_CODE")
	public String getProgrammeCode() {
		return programmeCode;
	}

	public void setProgrammeCode(String programmeCode) {
		this.programmeCode = programmeCode;
	}

}
