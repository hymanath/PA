package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NregsDataVO implements Serializable{

	private Long uniqueId;
	private String district;
	private String constituency;
	private String mandal;
	private String panchayat;
	private Long targetPersonDays;
	private Long generatedPersonDays;
	private String perAppLB;
	private String avgWageRate;
	private String totalExpenditure;
	
	private Long target;
	private String grounded;
	private String notGrounded;
	private Long inProgress;
	private Long completed;
	private String percentage;
	private Long total = 0l;
	private Long constiInRed = 0l;
	private Long constiInOrange = 0l;
	private Long constiInGreen = 0l;
	private Long mandalsInRed = 0l;
	private Long mandalsInOrange = 0l;
	private Long mandalsInGreen = 0l;
	private Long count = 0l;
	private Long villagesInRed = 0l;
	private Long villagesInOrange = 0l;
	private Long VillagesInGreen = 0l;
	
	private List<NregsDataVO> distConsCuntList = new ArrayList<NregsDataVO>(0);
	private List<NregsDataVO> distMandalCuntList = new ArrayList<NregsDataVO>(0);
	private List<NregsDataVO> distList = new ArrayList<NregsDataVO>(0);
	private List<NregsDataVO> countList = new ArrayList<NregsDataVO>(0);
	private List<NregsDataVO> distMandalList = new ArrayList<NregsDataVO>(0);
	private List<NregsDataVO> consMandalList = new ArrayList<NregsDataVO>(0);
	private List<NregsDataVO> mandalVillageList = new ArrayList<NregsDataVO>(0);
	private String achivement;
	private Long parliamentId;
	private String parliamentName;
	private String sanctionedKms;
	private String sanctionedpercentage;
	private String completedKms;
	
	private String targetKMS;
	private String sanctionedAmount;
	private String sanctionedKMS;
	private String sanctionedPerventage;
	private String expenditureAmount;
	private String completedKMS;
	
	private String targetACRES;
	private String sanctionedACRES;
	private String pittingArea;
	private String plantingArea;
	private String pencentageOfPlanting;
	
	private String pittingKMS;
	private String plantingKMS;
	
	private String sanctionedTarget;
	
	private String achivementPercentage;
	private String wageExpenditure;
	private String materialExpenditure;
	private String materialExpenditurePerc;
	
	private String argicultureExpenditure;
	
	public String getArgicultureExpenditure() {
		return argicultureExpenditure;
	}
	public void setArgicultureExpenditure(String argicultureExpenditure) {
		this.argicultureExpenditure = argicultureExpenditure;
	}
	public String getAchivementPercentage() {
		return achivementPercentage;
	}
	public void setAchivementPercentage(String achivementPercentage) {
		this.achivementPercentage = achivementPercentage;
	}
	public String getWageExpenditure() {
		return wageExpenditure;
	}
	public void setWageExpenditure(String wageExpenditure) {
		this.wageExpenditure = wageExpenditure;
	}
	public String getMaterialExpenditure() {
		return materialExpenditure;
	}
	public void setMaterialExpenditure(String materialExpenditure) {
		this.materialExpenditure = materialExpenditure;
	}
	public String getMaterialExpenditurePerc() {
		return materialExpenditurePerc;
	}
	public void setMaterialExpenditurePerc(String materialExpenditurePerc) {
		this.materialExpenditurePerc = materialExpenditurePerc;
	}
	public String getSanctionedTarget() {
		return sanctionedTarget;
	}
	public void setSanctionedTarget(String sanctionedTarget) {
		this.sanctionedTarget = sanctionedTarget;
	}
	public String getPittingKMS() {
		return pittingKMS;
	}
	public void setPittingKMS(String pittingKMS) {
		this.pittingKMS = pittingKMS;
	}
	public String getPlantingKMS() {
		return plantingKMS;
	}
	public void setPlantingKMS(String plantingKMS) {
		this.plantingKMS = plantingKMS;
	}
	public String getTargetACRES() {
		return targetACRES;
	}
	public void setTargetACRES(String targetACRES) {
		this.targetACRES = targetACRES;
	}
	public String getSanctionedACRES() {
		return sanctionedACRES;
	}
	public void setSanctionedACRES(String sanctionedACRES) {
		this.sanctionedACRES = sanctionedACRES;
	}
	public String getPittingArea() {
		return pittingArea;
	}
	public void setPittingArea(String pittingArea) {
		this.pittingArea = pittingArea;
	}
	public String getPlantingArea() {
		return plantingArea;
	}
	public void setPlantingArea(String plantingArea) {
		this.plantingArea = plantingArea;
	}
	public String getPencentageOfPlanting() {
		return pencentageOfPlanting;
	}
	public void setPencentageOfPlanting(String pencentageOfPlanting) {
		this.pencentageOfPlanting = pencentageOfPlanting;
	}
	public String getTargetKMS() {
		return targetKMS;
	}
	public void setTargetKMS(String targetKMS) {
		this.targetKMS = targetKMS;
	}
	public String getSanctionedAmount() {
		return sanctionedAmount;
	}
	public void setSanctionedAmount(String sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}
	public String getSanctionedKMS() {
		return sanctionedKMS;
	}
	public void setSanctionedKMS(String sanctionedKMS) {
		this.sanctionedKMS = sanctionedKMS;
	}
	public String getSanctionedPerventage() {
		return sanctionedPerventage;
	}
	public void setSanctionedPerventage(String sanctionedPerventage) {
		this.sanctionedPerventage = sanctionedPerventage;
	}
	public String getExpenditureAmount() {
		return expenditureAmount;
	}
	public void setExpenditureAmount(String expenditureAmount) {
		this.expenditureAmount = expenditureAmount;
	}
	public String getCompletedKMS() {
		return completedKMS;
	}
	public void setCompletedKMS(String completedKMS) {
		this.completedKMS = completedKMS;
	}
	public Long getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(Long uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public Long getTargetPersonDays() {
		return targetPersonDays;
	}
	public void setTargetPersonDays(Long targetPersonDays) {
		this.targetPersonDays = targetPersonDays;
	}
	public Long getGeneratedPersonDays() {
		return generatedPersonDays;
	}
	public void setGeneratedPersonDays(Long generatedPersonDays) {
		this.generatedPersonDays = generatedPersonDays;
	}
	public String getPerAppLB() {
		return perAppLB;
	}
	public void setPerAppLB(String perAppLB) {
		this.perAppLB = perAppLB;
	}
	public String getAvgWageRate() {
		return avgWageRate;
	}
	public void setAvgWageRate(String avgWageRate) {
		this.avgWageRate = avgWageRate;
	}
	public String getTotalExpenditure() {
		return totalExpenditure;
	}
	public void setTotalExpenditure(String totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public String getGrounded() {
		return grounded;
	}
	public void setGrounded(String grounded) {
		this.grounded = grounded;
	}
	public String getNotGrounded() {
		return notGrounded;
	}
	public void setNotGrounded(String notGrounded) {
		this.notGrounded = notGrounded;
	}
	public Long getInProgress() {
		return inProgress;
	}
	public void setInProgress(Long inProgress) {
		this.inProgress = inProgress;
	}
	public Long getCompleted() {
		return completed;
	}
	public void setCompleted(Long completed) {
		this.completed = completed;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public Long getConstiInRed() {
		return constiInRed;
	}
	public void setConstiInRed(Long constiInRed) {
		this.constiInRed = constiInRed;
	}
	public Long getConstiInOrange() {
		return constiInOrange;
	}
	public void setConstiInOrange(Long constiInOrgange) {
		this.constiInOrange = constiInOrgange;
	}
	public Long getConstiInGreen() {
		return constiInGreen;
	}
	public void setConstiInGreen(Long constiInGreen) {
		this.constiInGreen = constiInGreen;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getMandalsInRed() {
		return mandalsInRed;
	}
	public void setMandalsInRed(Long mandalsInRed) {
		this.mandalsInRed = mandalsInRed;
	}
	public Long getMandalsInOrange() {
		return mandalsInOrange;
	}
	public void setMandalsInOrange(Long mandalsInOrange) {
		this.mandalsInOrange = mandalsInOrange;
	}
	public Long getMandalsInGreen() {
		return mandalsInGreen;
	}
	public void setMandalsInGreen(Long mandalsInGreen) {
		this.mandalsInGreen = mandalsInGreen;
	}
	public List<NregsDataVO> getDistConsCuntList() {
		return distConsCuntList;
	}
	public void setDistConsCuntList(List<NregsDataVO> distConsCuntList) {
		this.distConsCuntList = distConsCuntList;
	}
	public List<NregsDataVO> getDistMandalCuntList() {
		return distMandalCuntList;
	}
	public void setDistMandalCuntList(List<NregsDataVO> distMandalCuntList) {
		this.distMandalCuntList = distMandalCuntList;
	}
	public List<NregsDataVO> getDistList() {
		return distList;
	}
	public void setDistList(List<NregsDataVO> distList) {
		this.distList = distList;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<NregsDataVO> getCountList() {
		return countList;
	}
	public void setCountList(List<NregsDataVO> countList) {
		this.countList = countList;
	}
	public List<NregsDataVO> getDistMandalList() {
		return distMandalList;
	}
	public void setDistMandalList(List<NregsDataVO> distMandalList) {
		this.distMandalList = distMandalList;
	}
	public List<NregsDataVO> getConsMandalList() {
		return consMandalList;
	}
	public void setConsMandalList(List<NregsDataVO> consMandalList) {
		this.consMandalList = consMandalList;
	}
	public Long getVillagesInRed() {
		return villagesInRed;
	}
	public void setVillagesInRed(Long villagesInRed) {
		this.villagesInRed = villagesInRed;
	}
	public Long getVillagesInOrange() {
		return villagesInOrange;
	}
	public void setVillagesInOrange(Long villagesInOrange) {
		this.villagesInOrange = villagesInOrange;
	}
	public Long getVillagesInGreen() {
		return VillagesInGreen;
	}
	public void setVillagesInGreen(Long villagesInGreen) {
		VillagesInGreen = villagesInGreen;
	}
	public List<NregsDataVO> getMandalVillageList() {
		return mandalVillageList;
	}
	public void setMandalVillageList(List<NregsDataVO> mandalVillageList) {
		this.mandalVillageList = mandalVillageList;
	}
	public String getAchivement() {
		return achivement;
	}
	public void setAchivement(String achivement) {
		this.achivement = achivement;
	}
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
	}
	public String getSanctionedKms() {
		return sanctionedKms;
	}
	public void setSanctionedKms(String sanctionedKms) {
		this.sanctionedKms = sanctionedKms;
	}
	public String getSanctionedpercentage() {
		return sanctionedpercentage;
	}
	public void setSanctionedpercentage(String sanctionedpercentage) {
		this.sanctionedpercentage = sanctionedpercentage;
	}
	public String getCompletedKms() {
		return completedKms;
	}
	public void setCompletedKms(String completedKms) {
		this.completedKms = completedKms;
	}
	
}
