package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/**
 * @author Teja
 * @date may 3, 2016
 */
/**
 * @author Client
 *
 */

public class GrievanceDetailsVO implements Serializable{

	private Long id;
	private String name;
	private Long Count;
	private String status;
	
	private Long partyCount=0l;
	private Long govtCount=0l;
	private Long welfareCount=0l;
	
	
	
	public Long getPartyCount() {
		return partyCount;
	}
	public void setPartyCount(Long partyCount) {
		this.partyCount = partyCount;
	}
	public Long getGovtCount() {
		return govtCount;
	}
	public void setGovtCount(Long govtCount) {
		this.govtCount = govtCount;
	}
	public Long getWelfareCount() {
		return welfareCount;
	}
	public void setWelfareCount(Long welfareCount) {
		this.welfareCount = welfareCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return Count;
	}
	public void setCount(Long count) {
		Count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
