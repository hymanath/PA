package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlertCoreDashBoardVO implements Serializable{

	private Long id;
	private String name;
	private String title;
	private String desc;
	
	private Long totalCount=0l;
	private Long count=0l;
	
	private List<AlertCoreDashBoardVO> alertCoreDashBoardVOList = new ArrayList<AlertCoreDashBoardVO>(0);

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<AlertCoreDashBoardVO> getAlertCoreDashBoardVOList() {
		return alertCoreDashBoardVOList;
	}

	public void setAlertCoreDashBoardVOList(
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOList) {
		this.alertCoreDashBoardVOList = alertCoreDashBoardVOList;
	}
	
	
}
