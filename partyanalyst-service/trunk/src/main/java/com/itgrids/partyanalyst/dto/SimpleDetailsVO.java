package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleDetailsVO implements  java.io.Serializable {
	   private Long id;
	   private String name;
	   private Long count;
	   private Long total =0l;
	   private Long totalCount =0l;
	   private Long totl=0l;

	   
	   private List<SimpleDetailsVO> simpleVOList1 = new ArrayList<SimpleDetailsVO>(0);
	   private List<SimpleDetailsVO> simpleVOList2 = new ArrayList<SimpleDetailsVO>(0);
	  
	   
	   private Map<Long,SimpleDetailsVO> map;
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
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Long getTotl() {
		return totl;
	}
	public void setTotl(Long totl) {
		this.totl = totl;
	}
	public List<SimpleDetailsVO> getSimpleVOList1() {
		return simpleVOList1;
	}
	public void setSimpleVOList1(List<SimpleDetailsVO> simpleVOList1) {
		this.simpleVOList1 = simpleVOList1;
	}
	public List<SimpleDetailsVO> getSimpleVOList2() {
		return simpleVOList2;
	}
	public void setSimpleVOList2(List<SimpleDetailsVO> simpleVOList2) {
		this.simpleVOList2 = simpleVOList2;
	}
	public Map<Long, SimpleDetailsVO> getMap() {
		return map;
	}
	public void setMap(Map<Long, SimpleDetailsVO> map) {
		this.map = map;
	}
	
	   
	   
}
