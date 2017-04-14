package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlertsSummeryVO implements Serializable {
	private Long ttlAlrtss = 0l;
	private Long effcncyAlerts = 0l;
	private String effcncyPrcnt;
	private String effcncyType;
	private List<AlertsSummeryVO> effcncyRslts = new ArrayList<AlertsSummeryVO>();
	private String clrFrEffcncy;
	private String name;
	private Long id;
	private Integer days;
	
	
	
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Long getTtlAlrtss() {
		return ttlAlrtss;
	}
	public void setTtlAlrtss(Long ttlAlrtss) {
		this.ttlAlrtss = ttlAlrtss;
	}
	public Long getEffcncyAlerts() {
		return effcncyAlerts;
	}
	public void setEffcncyAlerts(Long effcncyAlerts) {
		this.effcncyAlerts = effcncyAlerts;
	}
	public String getEffcncyPrcnt() {
		return effcncyPrcnt;
	}
	public void setEffcncyPrcnt(String effcncyPrcnt) {
		this.effcncyPrcnt = effcncyPrcnt;
	}
	public String getEffcncyType() {
		return effcncyType;
	}
	public void setEffcncyType(String effcncyType) {
		this.effcncyType = effcncyType;
	}
	public List<AlertsSummeryVO> getEffcncyRslts() {
		return effcncyRslts;
	}
	public void setEffcncyRslts(List<AlertsSummeryVO> effcncyRslts) {
		this.effcncyRslts = effcncyRslts;
	}
	public String getClrFrEffcncy() {
		return clrFrEffcncy;
	}
	public void setClrFrEffcncy(String clrFrEffcncy) {
		this.clrFrEffcncy = clrFrEffcncy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
