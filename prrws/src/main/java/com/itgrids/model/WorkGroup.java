package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "work_group")
public class WorkGroup {

	private Long workGroupId;
	private String groupName;
	
	@Id
	@Column(name="work_group_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getWorkGroupId() {
		return workGroupId;
	}
	public void setWorkGroupId(Long workGroupId) {
		this.workGroupId = workGroupId;
	}
	
	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
