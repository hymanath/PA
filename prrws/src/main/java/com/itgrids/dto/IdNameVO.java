package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private List<String> componentNameList;
	
	private String url;
	private List<Long> componentIds = new ArrayList<Long>(0);
	private Long orderNo;
	
	private Long workId;
	private String workName;
	private String adminDate;
	private String adminNo;
	private Long sanctionAmount;
	private String typeOfAssestName;
	private String groundedDate;
	private String targetDate;
	private List<IdNameVO> completedList = new ArrayList<IdNameVO>(0);
	private Long pwsCount = 0L;
	private Long cpwsCount = 0L;
	private Long pwsAmount = 0L;
	private Long cpwsAmount = 0L;
	
	private Long onGoingPwsCount = 0L;
	private Long onGoingCpwsCount = 0L;
	private Long onGoingPwsAmount = 0L;
	private Long onGoingCpwsAmount = 0L;
	
	
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
	public List<Long> getComponentIds() {
		return componentIds;
	}
	public void setComponentIds(List<Long> componentIds) {
		this.componentIds = componentIds;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public List<String> getComponentNameList() {
		return componentNameList;
	}
	public void setComponentNameList(List<String> componentNameList) {
		this.componentNameList = componentNameList;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getAdminDate() {
		return adminDate;
	}
	public void setAdminDate(String adminDate) {
		this.adminDate = adminDate;
	}
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	public Long getSanctionAmount() {
		return sanctionAmount;
	}
	public void setSanctionAmount(Long sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}
	public String getTypeOfAssestName() {
		return typeOfAssestName;
	}
	public void setTypeOfAssestName(String typeOfAssestName) {
		this.typeOfAssestName = typeOfAssestName;
	}
	public String getGroundedDate() {
		return groundedDate;
	}
	public void setGroundedDate(String groundedDate) {
		this.groundedDate = groundedDate;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	public List<IdNameVO> getCompletedList() {
		return completedList;
	}
	public void setCompletedList(List<IdNameVO> completedList) {
		this.completedList = completedList;
	}
	public Long getPwsCount() {
		return pwsCount;
	}
	public void setPwsCount(Long pwsCount) {
		this.pwsCount = pwsCount;
	}
	public Long getCpwsCount() {
		return cpwsCount;
	}
	public void setCpwsCount(Long cpwsCount) {
		this.cpwsCount = cpwsCount;
	}
	public Long getPwsAmount() {
		return pwsAmount;
	}
	public void setPwsAmount(Long pwsAmount) {
		this.pwsAmount = pwsAmount;
	}
	public Long getCpwsAmount() {
		return cpwsAmount;
	}
	public void setCpwsAmount(Long cpwsAmount) {
		this.cpwsAmount = cpwsAmount;
	}
	public Long getOnGoingPwsCount() {
		return onGoingPwsCount;
	}
	public void setOnGoingPwsCount(Long onGoingPwsCount) {
		this.onGoingPwsCount = onGoingPwsCount;
	}
	public Long getOnGoingCpwsCount() {
		return onGoingCpwsCount;
	}
	public void setOnGoingCpwsCount(Long onGoingCpwsCount) {
		this.onGoingCpwsCount = onGoingCpwsCount;
	}
	public Long getOnGoingPwsAmount() {
		return onGoingPwsAmount;
	}
	public void setOnGoingPwsAmount(Long onGoingPwsAmount) {
		this.onGoingPwsAmount = onGoingPwsAmount;
	}
	public Long getOnGoingCpwsAmount() {
		return onGoingCpwsAmount;
	}
	public void setOnGoingCpwsAmount(Long onGoingCpwsAmount) {
		this.onGoingCpwsAmount = onGoingCpwsAmount;
	}
}
