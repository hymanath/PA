package com.itgrids.dto;

import java.io.Serializable;

public class IdNameVO implements Serializable{
	
	private static final long serialVersionUID = -5436365311016546551L;
	
	private Long id;
	private String name;
	private Double total;
	private double average;
	private double percentage;
	private Long count;
	private String totl;
	private String minDate;
	private String maxDate;
	
	private String url;
	
	public IdNameVO() {
		super();
	}
	public IdNameVO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public IdNameVO(Long id, String name, String totl) {
		super();
		this.id = id;
		this.name = name;
		this.totl = totl;
	}
	public String getMinDate() {
		return minDate;
	}
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}
	public String getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
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
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getTotl() {
		return totl;
	}
	public void setTotl(String totl) {
		this.totl = totl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
