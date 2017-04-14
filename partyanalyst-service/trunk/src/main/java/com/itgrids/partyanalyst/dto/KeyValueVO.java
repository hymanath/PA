package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KeyValueVO implements Serializable {

	private Long id;
	private String name;
	private Long count = 0l;
	private List<KeyValueVO> list = new ArrayList<KeyValueVO>(0);
	private Long totalCount = 0l;
	
	
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
	public List<KeyValueVO> getList() {
		return list;
	}
	public void setList(List<KeyValueVO> list) {
		this.list = list;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
