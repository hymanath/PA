
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
	private String targetPersonDays;
	private String generatedPersonDays;
	private String perAppLB = "0";
	private String avgWageRate;
	private String totalExpenditure = "0";
	
	private Long target = 0l;
	private String grounded = "0";
	private String notGrounded = "0";
	private Long inProgress = 0l;
	private Long completed = 0l;
	private String percentage = "0";
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
	private String achivement = "0";
	private Long parliamentId = 0l;
	private String parliamentName;
	private String sanctionedKms;
	private String sanctionedpercentage;
	private String completedKms = "0";
	
	private String targetKMS;
	private String sanctionedAmount = "0";
	private String sanctionedKMS;
	private String sanctionedPerventage;
	private String expenditureAmount = "0";
	private String completedKMS;
	
	private String targetACRES;
	private String sanctionedACRES;
	private String pittingArea = "0";
	private String plantingArea = "0";
	private String pencentageOfPlanting = "0";
	private String targetPittingPerc;
	private String sancTrgtPittingPerc;
	
	private String pittingKMS;
	private String plantingKMS;
	
	private String sanctionedTarget;
	
	private String achivementPercentage;
	private String wageExpenditure = "0";
	private String materialExpenditure = "0";
	private String materialExpenditurePerc = "0";
	
	private String argicultureExpenditure;
	
	private String ftoNotGenCnt;
	private String ftoNotGenAmt;
	private String ftoNotUploadCnt;
	private String ftoNotUploadAmt;
	private String ftoNotSentCnt;
	private String ftoNotSentAmt;
	private String rejectCnt;
	private String rejectAmt;
	private String pendingResponseCnt;
	private String pendingResponseAmt;
	private String dId;
	private String mId;
	
	private String state;
	private String avgDmdMarks;
	private String avgDMusterMarks;
	private String avgLbMarks;
	private String avgRozgarDivasMarks;
	private String avgDaysMarks;
	private String avgAvgWageMarks;
	private String avgFlagshipMarks;
	private String avgTotMarks;
	
	private List<NregsDataVO> subList = new ArrayList<NregsDataVO>(0);
	private String percSant;
	
	private Long borewellsDrilled;
	private String ltFiles;
	private String sentToTransco;
	private String beneficaryContribution;
	private String amountPaidTransco;
	private String borewellenergisation;
	
	private String achmtGT0;
	private String achmtLT0;
	private String balance;
	private String area;
	private String gross;
	private String stroageCap;
	private String balanceRunOff;
	private String waterBudgtUploaded;
	
	
	public String getTargetPittingPerc() {
		return targetPittingPerc;
	}
	public void setTargetPittingPerc(String targetPittingPerc) {
		this.targetPittingPerc = targetPittingPerc;
	}
	public String getSancTrgtPittingPerc() {
		return sancTrgtPittingPerc;
	}
	public void setSancTrgtPittingPerc(String sancTrgtPittingPerc) {
		this.sancTrgtPittingPerc = sancTrgtPittingPerc;
	}
	public String getAchmtGT0() {
		return achmtGT0;
	}
	public void setAchmtGT0(String achmtGT0) {
		this.achmtGT0 = achmtGT0;
	}
	public String getAchmtLT0() {
		return achmtLT0;
	}
	public void setAchmtLT0(String achmtLT0) {
		this.achmtLT0 = achmtLT0;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getGross() {
		return gross;
	}
	public void setGross(String gross) {
		this.gross = gross;
	}
	public String getStroageCap() {
		return stroageCap;
	}
	public void setStroageCap(String stroageCap) {
		this.stroageCap = stroageCap;
	}
	public String getBalanceRunOff() {
		return balanceRunOff;
	}
	public void setBalanceRunOff(String balanceRunOff) {
		this.balanceRunOff = balanceRunOff;
	}
	public Long getBorewellsDrilled() {
		return borewellsDrilled;
	}
	public void setBorewellsDrilled(Long borewellsDrilled) {
		this.borewellsDrilled = borewellsDrilled;
	}
	public String getLtFiles() {
		return ltFiles;
	}
	public void setLtFiles(String ltFiles) {
		this.ltFiles = ltFiles;
	}
	public String getSentToTransco() {
		return sentToTransco;
	}
	public void setSentToTransco(String sentToTransco) {
		this.sentToTransco = sentToTransco;
	}
	public String getBeneficaryContribution() {
		return beneficaryContribution;
	}
	public void setBeneficaryContribution(String beneficaryContribution) {
		this.beneficaryContribution = beneficaryContribution;
	}
	public String getAmountPaidTransco() {
		return amountPaidTransco;
	}
	public void setAmountPaidTransco(String amountPaidTransco) {
		this.amountPaidTransco = amountPaidTransco;
	}
	public String getBorewellenergisation() {
		return borewellenergisation;
	}
	public void setBorewellenergisation(String borewellenergisation) {
		this.borewellenergisation = borewellenergisation;
	}
	public String getPercSant() {
		return percSant;
	}
	public void setPercSant(String percSant) {
		this.percSant = percSant;
	}
	public List<NregsDataVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NregsDataVO> subList) {
		this.subList = subList;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAvgDmdMarks() {
		return avgDmdMarks;
	}
	public void setAvgDmdMarks(String avgDmdMarks) {
		this.avgDmdMarks = avgDmdMarks;
	}
	public String getAvgDMusterMarks() {
		return avgDMusterMarks;
	}
	public void setAvgDMusterMarks(String avgDMusterMarks) {
		this.avgDMusterMarks = avgDMusterMarks;
	}
	public String getAvgLbMarks() {
		return avgLbMarks;
	}
	public void setAvgLbMarks(String avgLbMarks) {
		this.avgLbMarks = avgLbMarks;
	}
	public String getAvgRozgarDivasMarks() {
		return avgRozgarDivasMarks;
	}
	public void setAvgRozgarDivasMarks(String avgRozgarDivasMarks) {
		this.avgRozgarDivasMarks = avgRozgarDivasMarks;
	}
	public String getAvgDaysMarks() {
		return avgDaysMarks;
	}
	public void setAvgDaysMarks(String avgDaysMarks) {
		this.avgDaysMarks = avgDaysMarks;
	}
	public String getAvgAvgWageMarks() {
		return avgAvgWageMarks;
	}
	public void setAvgAvgWageMarks(String avgAvgWageMarks) {
		this.avgAvgWageMarks = avgAvgWageMarks;
	}
	public String getAvgFlagshipMarks() {
		return avgFlagshipMarks;
	}
	public void setAvgFlagshipMarks(String avgFlagshipMarks) {
		this.avgFlagshipMarks = avgFlagshipMarks;
	}
	public String getAvgTotMarks() {
		return avgTotMarks;
	}
	public void setAvgTotMarks(String avgTotMarks) {
		this.avgTotMarks = avgTotMarks;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getFtoNotGenCnt() {
		return ftoNotGenCnt;
	}
	public void setFtoNotGenCnt(String ftoNotGenCnt) {
		this.ftoNotGenCnt = ftoNotGenCnt;
	}
	public String getFtoNotGenAmt() {
		return ftoNotGenAmt;
	}
	public void setFtoNotGenAmt(String ftoNotGenAmt) {
		this.ftoNotGenAmt = ftoNotGenAmt;
	}
	public String getFtoNotUploadCnt() {
		return ftoNotUploadCnt;
	}
	public void setFtoNotUploadCnt(String ftoNotUploadCnt) {
		this.ftoNotUploadCnt = ftoNotUploadCnt;
	}
	public String getFtoNotUploadAmt() {
		return ftoNotUploadAmt;
	}
	public void setFtoNotUploadAmt(String ftoNotUploadAmt) {
		this.ftoNotUploadAmt = ftoNotUploadAmt;
	}
	public String getFtoNotSentCnt() {
		return ftoNotSentCnt;
	}
	public void setFtoNotSentCnt(String ftoNotSentCnt) {
		this.ftoNotSentCnt = ftoNotSentCnt;
	}
	public String getFtoNotSentAmt() {
		return ftoNotSentAmt;
	}
	public void setFtoNotSentAmt(String ftoNotSentAmt) {
		this.ftoNotSentAmt = ftoNotSentAmt;
	}
	public String getRejectCnt() {
		return rejectCnt;
	}
	public void setRejectCnt(String rejectCnt) {
		this.rejectCnt = rejectCnt;
	}
	public String getRejectAmt() {
		return rejectAmt;
	}
	public void setRejectAmt(String rejectAmt) {
		this.rejectAmt = rejectAmt;
	}
	public String getPendingResponseCnt() {
		return pendingResponseCnt;
	}
	public void setPendingResponseCnt(String pendingResponseCnt) {
		this.pendingResponseCnt = pendingResponseCnt;
	}
	public String getPendingResponseAmt() {
		return pendingResponseAmt;
	}
	public void setPendingResponseAmt(String pendingResponseAmt) {
		this.pendingResponseAmt = pendingResponseAmt;
	}
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
	public String getGeneratedPersonDays() {
		return generatedPersonDays;
	}
	public void setGeneratedPersonDays(String generatedPersonDays) {
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
	public String getWaterBudgtUploaded() {
		return waterBudgtUploaded;
	}
	public void setWaterBudgtUploaded(String waterBudgtUploaded) {
		this.waterBudgtUploaded = waterBudgtUploaded;
	}
	public String getTargetPersonDays() {
		return targetPersonDays;
	}
	public void setTargetPersonDays(String targetPersonDays) {
		this.targetPersonDays = targetPersonDays;
	}
	
}
