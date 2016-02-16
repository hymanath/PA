package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Comparator;

public class IdNameVO implements Serializable,Comparator<IdNameVO>{
	private Long id;
	private String name;
	private Long districtid;
	private Long availableCount;
	private Long actualCount=0l;
	private Long orderId;
	
	
	@Override
	public int compare(IdNameVO o2, IdNameVO o1) {
		// TODO Auto-generated method stub
		return o2.getOrderId().compareTo(o1.getOrderId());
		
	}

	public IdNameVO(){}
	
	public IdNameVO(Long id,String name){
		this.id=id;
		this.name=name;
	}
	
	public Long getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(Long availableCount) {
		this.availableCount = availableCount;
	}

	public Long getActualCount() {
		return actualCount;
	}

	public void setActualCount(Long actualCount) {
		this.actualCount = actualCount;
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

	public Long getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	
}
