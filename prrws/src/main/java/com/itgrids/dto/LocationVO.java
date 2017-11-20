package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocationVO implements Serializable {
	private Long locationLevelId;
	private Long locaitonLevelName;
	private Long locationId;
	private String locationName;
	private Long programId;
	private String programName;
	private Long amount = 0L;
	private Long count = 0L;
	private Long financialYearId;
	private String financialYear;
	private List<LocationVO> locationList1;
	private List<LocationVO> locationList2;
	private String amunt = "0.0";
	private String workName;
	private Long workNumber = 0L;
	private String departmentName;
	private String schemeName;
	private String goNoDate;
	private String sactionAmount;
	private Long fundSanctionId;
	private StringBuilder locName;
	private String parentLocationId;
	private Long govtOrderId;
	private String goNumber;
	private String issueDate;
	private String filePath;
	private String tehsilName;
	private String panchayatName;
	private String mlaName;
	private Long govtOrderCount;
	private String amountInDecimal="";
	private Long grantTypeId;
	private String grantTypeName;
	
	private Long streetHabitationCount;
	private Long totalCount;
	
	private Long plainGoCount;
	private Long scpGoCount;
	private Long tspGoCount;
	
	private Long plainWorkCount;
	private Long scpWorkCount;
	private Long tspWorkCount;
	
	private Long plainAmount;
	private Long scpAmount;
	private Long tspAmount;
	
	private String plainAmountInDecimal="";
	private String scpAmountInDecimal="";
	private String tspAmountInDecimal="";
	
	private List<StatusVO> statusList = new ArrayList<StatusVO>(0);
	private AddressVO addressVO = new AddressVO();
	
	
	
	public String getGrantTypeName() {
		return grantTypeName;
	}
	public void setGrantTypeName(String grantTypeName) {
		this.grantTypeName = grantTypeName;
	}
	
	public String getMlaName() {
		return mlaName;
	}
	public void setMlaName(String mlaName) {
		this.mlaName = mlaName;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public String getParentLocationId() {
		return parentLocationId;
	}
	public void setParentLocationId(String parentLocationId) {
		this.parentLocationId = parentLocationId;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getLocaitonLevelName() {
		return locaitonLevelName;
	}
	public void setLocaitonLevelName(Long locaitonLevelName) {
		this.locaitonLevelName = locaitonLevelName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getFinancialYearId() {
		return financialYearId;
	}
	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public List<LocationVO> getLocationList1() {
		if(locationList1 == null){
			locationList1 = new ArrayList<LocationVO>();
		}
		return locationList1;
	}
	public List<LocationVO> getLocationList2() {
		if(locationList2 == null){
			locationList2 = new ArrayList<LocationVO>();
		}
		return locationList2;
	}
	public String getAmunt() {
		return amunt;
	}
	public void setAmunt(String amunt) {
		this.amunt = amunt;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getGoNoDate() {
		return goNoDate;
	}
	public void setGoNoDate(String goNoDate) {
		this.goNoDate = goNoDate;
	}
	public Long getStreetHabitationCount() {
		return streetHabitationCount;
	}
	public void setStreetHabitationCount(Long streetHabitationCount) {
		this.streetHabitationCount = streetHabitationCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<StatusVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<StatusVO> statusList) {
		this.statusList = statusList;
	}
	public String getSactionAmount() {
		return sactionAmount;
	}
	public void setSactionAmount(String sactionAmount) {
		this.sactionAmount = sactionAmount;
	}
	
	public StringBuilder getLocName() {
		return locName;
	}
	public void setLocName(StringBuilder locName) {
		this.locName = locName;
	}
	public Long getFundSanctionId() {
		return fundSanctionId;
	}
	public void setFundSanctionId(Long fundSanctionId) {
		this.fundSanctionId = fundSanctionId;
	}
	public Long getGovtOrderId() {
		return govtOrderId;
	}
	public void setGovtOrderId(Long govtOrderId) {
		this.govtOrderId = govtOrderId;
	}
	public String getGoNumber() {
		return goNumber;
	}
	public void setGoNumber(String goNumber) {
		this.goNumber = goNumber;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getWorkNumber() {
		return workNumber;
	}
	public void setWorkNumber(Long workNumber) {
		this.workNumber = workNumber;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public Long getGovtOrderCount() {
		return govtOrderCount;
	}
	public void setGovtOrderCount(Long govtOrderCount) {
		this.govtOrderCount = govtOrderCount;
	}
	public String getAmountInDecimal() {
		return amountInDecimal;
	}
	public void setAmountInDecimal(String amountInDecimal) {
		this.amountInDecimal = amountInDecimal;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getPlainGoCount() {
		return plainGoCount;
	}
	public void setPlainGoCount(Long plainGoCount) {
		this.plainGoCount = plainGoCount;
	}
	public Long getScpGoCount() {
		return scpGoCount;
	}
	public void setScpGoCount(Long scpGoCount) {
		this.scpGoCount = scpGoCount;
	}
	public Long getTspGoCount() {
		return tspGoCount;
	}
	public void setTspGoCount(Long tspGoCount) {
		this.tspGoCount = tspGoCount;
	}
	public Long getPlainWorkCount() {
		return plainWorkCount;
	}
	public void setPlainWorkCount(Long plainWorkCount) {
		this.plainWorkCount = plainWorkCount;
	}
	public Long getScpWorkCount() {
		return scpWorkCount;
	}
	public void setScpWorkCount(Long scpWorkCount) {
		this.scpWorkCount = scpWorkCount;
	}
	public Long getTspWorkCount() {
		return tspWorkCount;
	}
	public void setTspWorkCount(Long tspWorkCount) {
		this.tspWorkCount = tspWorkCount;
	}
	public Long getPlainAmount() {
		return plainAmount;
	}
	public void setPlainAmount(Long plainAmount) {
		this.plainAmount = plainAmount;
	}
	public Long getScpAmount() {
		return scpAmount;
	}
	public void setScpAmount(Long scpAmount) {
		this.scpAmount = scpAmount;
	}
	
	public Long getTspAmount() {
		return tspAmount;
	}
	public void setTspAmount(Long tspAmount) {
		this.tspAmount = tspAmount;
	}
	public String getPlainAmountInDecimal() {
		return plainAmountInDecimal;
	}
	public void setPlainAmountInDecimal(String plainAmountInDecimal) {
		this.plainAmountInDecimal = plainAmountInDecimal;
	}
	public String getScpAmountInDecimal() {
		return scpAmountInDecimal;
	}
	public void setScpAmountInDecimal(String scpAmountInDecimal) {
		this.scpAmountInDecimal = scpAmountInDecimal;
	}
	public String getTspAmountInDecimal() {
		return tspAmountInDecimal;
	}
	public void setTspAmountInDecimal(String tspAmountInDecimal) {
		this.tspAmountInDecimal = tspAmountInDecimal;
	}
	public Long getGrantTypeId() {
		return grantTypeId;
	}
	public void setGrantTypeId(Long grantTypeId) {
		this.grantTypeId = grantTypeId;
	}
	
}
