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
	private Long count = 0L;
	private String totl;
	private String minDate;
	private String maxDate;
	private List<String> componentNameList = new ArrayList<String>();;
	private Double sanctionedAmount =0.0;
	
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
	private String timeTaken;
	private String callTime;
	private String serviceProviderName;
	
	private String completionDate;
	private String wrokIdStr;
	
    private String mandalCode;
    private String mandalName;
    private String constituencyName;
    private String constituencyCode;
	private String districtName;
    private String districtCode;
    private String assetType;
    private String workStatus;
    private Long noOfDays;
    private String locationIdStr;
    private List<IdNameVO> subList = new ArrayList<IdNameVO>();
    public String programCode;
    public String programName;

	private String commissionnedDate;

	private String wrokName;
	private String habitationCode;
	private String habitationName;
	
	private Long ongoingPWSExceededCount =0l;
	 private Long completedPWSExceededCount=0l;
	 private Long commissionedPWSExceededCount=0l;
	 private Long workOngoingCount=0l;
	 private Long workComissionedCount=0l ;
	 private Long workCompletedCount=0l;
	 private Long workNotGroundedCount=0l;

	private Long workOngoingPWsCount;
	 private Double percentageOne = 0.0d,percentageTwo = 0.0d,percentageThree = 0.0d,percentageFour = 0.0d;

	private String goNumber;

	private String locationName;
	private Long diffCount = 0L;
	
	private Long zeroCount = 0L;
	private Long belowOneCount = 0L;
	private Long oneToFiveCount = 0L;
	private Long fiveToTenCount = 0L;
	private Long tenToTwentyCount = 0L;
	private Long changedCount = 0L;
	
	
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

	public Long getChangedCount() {
		return changedCount;
	}
	public void setChangedCount(Long changedCount) {
		this.changedCount = changedCount;
	}
	public Long getZeroCount() {
		return zeroCount;
	}
	public void setZeroCount(Long zeroCount) {
		this.zeroCount = zeroCount;
	}
	public Long getBelowOneCount() {
		return belowOneCount;
	}
	public void setBelowOneCount(Long belowOneCount) {
		this.belowOneCount = belowOneCount;
	}
	public Long getOneToFiveCount() {
		return oneToFiveCount;
	}
	public void setOneToFiveCount(Long oneToFiveCount) {
		this.oneToFiveCount = oneToFiveCount;
	}
	public Long getFiveToTenCount() {
		return fiveToTenCount;
	}
	public void setFiveToTenCount(Long fiveToTenCount) {
		this.fiveToTenCount = fiveToTenCount;
	}
	public Long getTenToTwentyCount() {
		return tenToTwentyCount;
	}
	public void setTenToTwentyCount(Long tenToTwentyCount) {
		this.tenToTwentyCount = tenToTwentyCount;
	}
	public Long getDiffCount() {
		return diffCount;
	}
	public void setDiffCount(Long diffCount) {
		this.diffCount = diffCount;
	}
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	public String getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
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
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public String getWrokIdStr() {
		return wrokIdStr;
	}
	public void setWrokIdStr(String wrokIdStr) {
		this.wrokIdStr = wrokIdStr;
	}
	public String getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(String mandalCode) {
		this.mandalCode = mandalCode;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getConstituencyCode() {
		return constituencyCode;
	}
	public void setConstituencyCode(String constituencyCode) {
		this.constituencyCode = constituencyCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public Long getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Long noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getLocationIdStr() {
		return locationIdStr;
	}
	public void setLocationIdStr(String locationIdStr) {
		this.locationIdStr = locationIdStr;
	}
	public List<IdNameVO> getSubList() {
		return subList;
	}
	public void setSubList(List<IdNameVO> subList) {
		this.subList = subList;
	}
	public void setCommissionnedDate(String commissionnedDate) {

		this.commissionnedDate=commissionnedDate;
	}
	public String getProgramCode() {
		return programCode;
	}
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getCommissionnedDate() {
		return commissionnedDate;
	}
	
	public String getWrokName() {
		return wrokName;
	}
	
	public void setWrokName(String wrokName) {
		this.wrokName=wrokName;
		
	}
	public Double getSanctionedAmount() {
		return sanctionedAmount;
	}
	public void setSanctionedAmount(Double sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}
	public String getHabitationCode() {
		return habitationCode;
	}
	public void setHabitationCode(String habitationCode) {
		this.habitationCode = habitationCode;
	}
	public String getHabitationName() {
		return habitationName;
	}
	public void setHabitationName(String habitationName) {
		this.habitationName = habitationName;
	}
	
	public Long getOngoingPWSExceededCount() {
		return ongoingPWSExceededCount;
	}
	public void setOngoingPWSExceededCount(Long ongoingPWSExceededCount) {
		this.ongoingPWSExceededCount = ongoingPWSExceededCount;
	}
	public Long getCompletedPWSExceededCount() {
		return completedPWSExceededCount;
	}
	public void setCompletedPWSExceededCount(Long completedPWSExceededCount) {
		this.completedPWSExceededCount = completedPWSExceededCount;
	}
	public Long getCommissionedPWSExceededCount() {
		return commissionedPWSExceededCount;
	}
	public void setCommissionedPWSExceededCount(Long commissionedPWSExceededCount) {
		this.commissionedPWSExceededCount = commissionedPWSExceededCount;
	}
	public Long getWorkOngoingCount() {
		return workOngoingCount;
	}
	public void setWorkOngoingCount(Long workOngoingCount) {
		this.workOngoingCount = workOngoingCount;
	}
	public Long getWorkComissionedCount() {
		return workComissionedCount;
	}
	public void setWorkComissionedCount(Long workComissionedCount) {
		this.workComissionedCount = workComissionedCount;
	}
	public Long getWorkCompletedCount() {
		return workCompletedCount;
	}
	public void setWorkCompletedCount(Long workCompletedCount) {
		this.workCompletedCount = workCompletedCount;
	}
	public Long getWorkNotGroundedCount() {
		return workNotGroundedCount;
	}
	public void setWorkNotGroundedCount(Long workNotGroundedCount) {
		this.workNotGroundedCount = workNotGroundedCount;
	}
	public void setWorkOngoingPWsCount(Long workOngoingPWsCount) {
		this.workOngoingPWsCount=workOngoingPWsCount;
		
	}
	public Long getWorkOngoingPWsCount() {
		return workOngoingPWsCount;
	}
	public Double getPercentageOne() {
		return percentageOne;
	}
	public void setPercentageOne(Double percentageOne) {
		this.percentageOne = percentageOne;
	}
	public Double getPercentageTwo() {
		return percentageTwo;
	}
	public void setPercentageTwo(Double percentageTwo) {
		this.percentageTwo = percentageTwo;
	}
	public Double getPercentageThree() {
		return percentageThree;
	}
	public void setPercentageThree(Double percentageThree) {
		this.percentageThree = percentageThree;
	}
	public Double getPercentageFour() {
		return percentageFour;
	}
	public void setPercentageFour(Double percentageFour) {
		this.percentageFour = percentageFour;
	}
	public void setGoNumber(String goNumber) {
		this.goNumber=goNumber;
		
	}
	public void setLocationName(String locationName) {
			this.locationName=locationName;		
	}
	public String getGoNumber() {
		return goNumber;
	}
	public String getLocationName() {
		return locationName;
	}
	
}
