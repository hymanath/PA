package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreRegisterInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2883187909007470353L;

	private Long totalCount;
	private Long apCount;
	private Long tgCount;
	private String name;
	private Long percentage;
	private Long count_2012;
	private Long count_2014;
	
	public Long getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Long getApCount() {
		return apCount;
	}
	
	public void setApCount(Long apCount) {
		this.apCount = apCount;
	}
	
	public Long getTgCount() {
		return tgCount;
	}
	
	public void setTgCount(Long tgCount) {
		this.tgCount = tgCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPercentage() {
		return percentage;
	}

	public void setPercentage(Long percentage) {
		this.percentage = percentage;
	}

	public Long getCount_2012() {
		return count_2012;
	}

	public void setCount_2012(Long count_2012) {
		this.count_2012 = count_2012;
	}

	public Long getCount_2014() {
		return count_2014;
	}

	public void setCount_2014(Long count_2014) {
		this.count_2014 = count_2014;
	}
	
	
}
