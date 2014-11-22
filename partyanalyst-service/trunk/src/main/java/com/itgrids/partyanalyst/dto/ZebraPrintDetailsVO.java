package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class ZebraPrintDetailsVO implements java.io.Serializable{

	private Long Id;
	private String district;
	private String parliament;
	private String name;
	
	private String dataPushDate;
	private Long totalPushCount;
	
	private String updatedDate;
	private String printStatus;
	private Long printStatusCount;
	
	private String errorStatus;
	private Long errorStatusCount;
	
	private Long rowCount;
	
	List<ZebraPrintDetailsVO> zebraPrintDetailsVOList = new ArrayList<ZebraPrintDetailsVO>(0);
	List<String> datesList = new ArrayList<String>(); 
	
	
	public List<String> getDatesList() {
		return datesList;
	}
	public void setDatesList(List<String> datesList) {
		this.datesList = datesList;
	}
	public Long getRowCount() {
		return rowCount;
	}
	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}
	public List<ZebraPrintDetailsVO> getZebraPrintDetailsVOList() {
		return zebraPrintDetailsVOList;
	}
	public void setZebraPrintDetailsVOList(
			List<ZebraPrintDetailsVO> zebraPrintDetailsVOList) {
		this.zebraPrintDetailsVOList = zebraPrintDetailsVOList;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getParliament() {
		return parliament;
	}
	public void setParliament(String parliament) {
		this.parliament = parliament;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataPushDate() {
		return dataPushDate;
	}
	public void setDataPushDate(String dataPushDate) {
		this.dataPushDate = dataPushDate;
	}
	public Long getTotalPushCount() {
		return totalPushCount;
	}
	public void setTotalPushCount(Long totalPushCount) {
		this.totalPushCount = totalPushCount;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public Long getPrintStatusCount() {
		return printStatusCount;
	}
	public void setPrintStatusCount(Long printStatusCount) {
		this.printStatusCount = printStatusCount;
	}
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	public Long getErrorStatusCount() {
		return errorStatusCount;
	}
	public void setErrorStatusCount(Long errorStatusCount) {
		this.errorStatusCount = errorStatusCount;
	}
	
}
