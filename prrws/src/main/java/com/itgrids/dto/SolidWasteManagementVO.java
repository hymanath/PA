package com.itgrids.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolidWasteManagementVO {
	
	private String id;
	private String name;
	private Long mgnres=0L;
	private Long pr=0L;
	private Long publicType=0L;
	private Long tractor=0L;
	private Long auto=0L;
	private Long tricycle=0L;
	private Long evehicle=0L;
	private Long rfidTags=0L;
	private Long farmers=0L;
	private Double nadap=0.00;
	private Double vermi=0.00;
	private Double onekg=0.00;
	private Double fivekg=0.00;
	private Double tenkg=0.00;
	private Double twentyFivekg=0.00;
	private Double fiftykg=0.00;
	private Double vermiStock=0.00;
	private Double farmerCollection=0.00;
	private Double houseCollecion=0.00;
	private Double swmCollection=0.00;
	private Long blocks=0L;
	private Long rfidTracking=0L;
	private Long inTime=0L;
	private Long outTime=0L;
	private Long outOfTarget=0L;
	private Long averageTarget=0L;
	private Long averageInTime=0L;
	private Long averageOutTime=0L;
	private Long achieve=0L;
	private Long target = 0L;
	private Long totalRfidTags=0L;
	private Double trackingPer =0.00; 
	private Long totalTime =0L;  
	private Double AvertrackingPer =0.00;
	private Long averageTime=0L;
	private Long locationId=0L;
	private String  locationName;
	private Long   blockNo=0L;
	private Long gpCnt=0L;
	private Long       webServiceDataId;
	private Long       webserviceId;
	private String     inputData;
	private String     responceData;
	private Date       dataDate;
	private String     insertedTime;
	private String     isDeleted;
	private String     status;
	private Double inTimePer =0.00;
	private Double outTimePer =0.00;
	        

	private List<SolidWasteManagementVO> subList = new ArrayList<SolidWasteManagementVO>(0);
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMgnres() {
		return mgnres;
	}
	public void setMgnres(Long mgnres) {
		this.mgnres = mgnres;
	}
	public Long getPr() {
		return pr;
	}
	public void setPr(Long pr) {
		this.pr = pr;
	}
	public Long getPublicType() {
		return publicType;
	}
	public void setPublicType(Long publicType) {
		this.publicType = publicType;
	}
	public Long getTractor() {
		return tractor;
	}
	public void setTractor(Long tractor) {
		this.tractor = tractor;
	}
	public Long getAuto() {
		return auto;
	}
	public void setAuto(Long auto) {
		this.auto = auto;
	}
	public Long getTricycle() {
		return tricycle;
	}
	public void setTricycle(Long tricycle) {
		this.tricycle = tricycle;
	}
	public Long getEvehicle() {
		return evehicle;
	}
	public void setEvehicle(Long evehicle) {
		this.evehicle = evehicle;
	}
	public Long getRfidTags() {
		return rfidTags;
	}
	public void setRfidTags(Long rfidTags) {
		this.rfidTags = rfidTags;
	}
	public Long getFarmers() {
		return farmers;
	}
	public void setFarmers(Long farmers) {
		this.farmers = farmers;
	}
	public Double getNadap() {
		return nadap;
	}
	public void setNadap(Double nadap) {
		this.nadap = nadap;
	}
	public Double getVermi() {
		return vermi;
	}
	public void setVermi(Double vermi) {
		this.vermi = vermi;
	}
	public Double getOnekg() {
		return onekg;
	}
	public void setOnekg(Double onekg) {
		this.onekg = onekg;
	}
	public Double getFivekg() {
		return fivekg;
	}
	public void setFivekg(Double fivekg) {
		this.fivekg = fivekg;
	}
	public Double getTenkg() {
		return tenkg;
	}
	public void setTenkg(Double tenkg) {
		this.tenkg = tenkg;
	}
	public Double getTwentyFivekg() {
		return twentyFivekg;
	}
	public void setTwentyFivekg(Double twentyFivekg) {
		this.twentyFivekg = twentyFivekg;
	}
	public Double getFiftykg() {
		return fiftykg;
	}
	public void setFiftykg(Double fiftykg) {
		this.fiftykg = fiftykg;
	}
	public Double getVermiStock() {
		return vermiStock;
	}
	public void setVermiStock(Double vermiStock) {
		this.vermiStock = vermiStock;
	}
	public Double getFarmerCollection() {
		return farmerCollection;
	}
	public void setFarmerCollection(Double farmerCollection) {
		this.farmerCollection = farmerCollection;
	}
	public Double getHouseCollecion() {
		return houseCollecion;
	}
	public void setHouseCollecion(Double houseCollecion) {
		this.houseCollecion = houseCollecion;
	}
	public Double getSwmCollection() {
		return swmCollection;
	}
	public void setSwmCollection(Double swmCollection) {
		this.swmCollection = swmCollection;
	}
	public Long getBlocks() {
		return blocks;
	}
	public void setBlocks(Long blocks) {
		this.blocks = blocks;
	}
	public Long getRfidTracking() {
		return rfidTracking;
	}
	public void setRfidTracking(Long rfidTracking) {
		this.rfidTracking = rfidTracking;
	}
	public Long getInTime() {
		return inTime;
	}
	public void setInTime(Long inTime) {
		this.inTime = inTime;
	}
	public Long getOutTime() {
		return outTime;
	}
	public void setOutTime(Long outTime) {
		this.outTime = outTime;
	}
	public Long getOutOfTarget() {
		return outOfTarget;
	}
	public void setOutOfTarget(Long outOfTarget) {
		this.outOfTarget = outOfTarget;
	}
	public Long getAverageTarget() {
		return averageTarget;
	}
	public void setAverageTarget(Long averageTarget) {
		this.averageTarget = averageTarget;
	}
	public Long getAverageInTime() {
		return averageInTime;
	}
	public void setAverageInTime(Long averageInTime) {
		this.averageInTime = averageInTime;
	}
	public Long getAverageOutTime() {
		return averageOutTime;
	}
	public void setAverageOutTime(Long averageOutTime) {
		this.averageOutTime = averageOutTime;
	}
	public Long getAchieve() {
		return achieve;
	}
	public void setAchieve(Long achieve) {
		this.achieve = achieve;
	}
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public Long getTotalRfidTags() {
		return totalRfidTags;
	}
	public void setTotalRfidTags(Long totalRfidTags) {
		this.totalRfidTags = totalRfidTags;
	}
	public Double getTrackingPer() {
		return trackingPer;
	}
	public void setTrackingPer(Double trackingPer) {
		this.trackingPer = trackingPer;
	}
	public Long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}
	public Double getAvertrackingPer() {
		return AvertrackingPer;
	}
	public void setAvertrackingPer(Double avertrackingPer) {
		AvertrackingPer = avertrackingPer;
	}
	public Long getAverageTime() {
		return averageTime;
	}
	public void setAverageTime(Long averageTime) {
		this.averageTime = averageTime;
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
	public Long getBlockNo() {
		return blockNo;
	}
	public void setBlockNo(Long blockNo) {
		this.blockNo = blockNo;
	}
	public List<SolidWasteManagementVO> getSubList() {
		return subList;
	}
	public void setSubList(List<SolidWasteManagementVO> subList) {
		this.subList = subList;
	}
	public Long getGpCnt() {
		return gpCnt;
	}
	public void setGpCnt(Long gpCnt) {
		this.gpCnt = gpCnt;
	}
	public Long getWebServiceDataId() {
		return webServiceDataId;
	}
	public void setWebServiceDataId(Long webServiceDataId) {
		this.webServiceDataId = webServiceDataId;
	}
	public Long getWebserviceId() {
		return webserviceId;
	}
	public void setWebserviceId(Long webserviceId) {
		this.webserviceId = webserviceId;
	}
	public String getInputData() {
		return inputData;
	}
	public void setInputData(String inputData) {
		this.inputData = inputData;
	}
	public String getResponceData() {
		return responceData;
	}
	public void setResponceData(String responceData) {
		this.responceData = responceData;
	}
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getInTimePer() {
		return inTimePer;
	}
	public void setInTimePer(Double inTimePer) {
		this.inTimePer = inTimePer;
	}
	public Double getOutTimePer() {
		return outTimePer;
	}
	public void setOutTimePer(Double outTimePer) {
		this.outTimePer = outTimePer;
	}
	
	}
