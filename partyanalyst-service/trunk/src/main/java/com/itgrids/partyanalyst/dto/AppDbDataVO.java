package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class AppDbDataVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4289704657311841260L;

	private Long id;
	private String status;
	private String script;
	private Long order;
	private List<Double> versions;
	private List<AppDbDataVO> dataList;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getScript() {
		return script;
	}
	
	public void setScript(String script) {
		this.script = script;
	}
	
	public Long getOrder() {
		return order;
	}
	
	public void setOrder(Long order) {
		this.order = order;
	}
	
	public List<Double> getVersions() {
		return versions;
	}
	
	public void setVersions(List<Double> versions) {
		this.versions = versions;
	}
	
	public List<AppDbDataVO> getDataList() {
		return dataList;
	}
	
	public void setDataList(List<AppDbDataVO> dataList) {
		this.dataList = dataList;
	}
	
}
