package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NregaPaymentsVO implements Serializable{

	private String id;
	private String districtName;
	private String type;
	private String constName;
	private String mandalName;
	private String panchayatName;
	
	private String generatedQuantity = "0";
	private String generatedAmount = "0";
	private String generatedPendingQuantity = "0";
	private String generatedPendingAmount = "0";
	
	private String uploadQuantity = "0";
	private String uploadAmount = "0";
	private String uploadPendingQunatity = "0";
	private String uploadPendingAmount = "0";
	
	private String sentToBankQuantity = "0";
	private String sentToBankAmount = "0";
	private String sentToBankPendingQuantity = "0";
	private String sentToBankPendingAmount = "0";
	
	private String failedTransactionQuantity = "0";
	private String failedTransactionAmount = "0";
	private String failedTransactionPendingQuantity = "0";
	private String failedTransactionPendingAmount = "0";
	
	private Long parliamentId;
	private String parliamentName;
	
	private String totalPayments;
	private String totalAmount;
	private String totalWage;
	private String totalMaterial;
	private String completedWage;
	private String completedMaterial;
	private String pendingWage;
	private String pendingMaterial;
	private String failedWage;
	private String failedMaterial;
	private String generatedWage;
	private String generatedMaterial;
	private String uploadWage;
	private String uploadMaterial;
	private String sentBankWage;
	private String sentBankMaterial;
	private String completedWageAmount;
	private String completedMaterialAmount;
	private String pendingWageAmount;
	private String pendingMaterialAmount;
	private String failedWageAmount;
	private String failedMaterialAmount;
	private String generatedWageAmount;
	private String generatedMaterialAmount;
	private String totalGenerates;
	private String uploadedWageAmount;
	private String uploadedMaterialAmount;
	private String totalUploads;
	private String sentBankWageAmount;
	private String sentBankMaterialAmount;
	private String totalSentBankS;
	private String totalPendings;
	private String totalGeneratesAmount;
	private String totalUploadsAmount;
	private String totalSentBankAmount;
	private String totalPendinAmount;
	
	private String ftoSentToBank = "0";
	private String ftoSentToAmount = "0";
	private String sentToBankSuccess = "0";
	private String sentToBankSuccessAmt = "0";
	
	private String pendingAtBankQuantity;
	private String pendingAtBankAmount;
	
	private String notGeneratedWagesAmount="0";
	private String notGeneratedMaterialAmount="0";
	private String totalNotGeneratedAmount="0" ;
	private String notUploadedWagesAmount="0";
	private String notUploadedMaterialAmount="0";
	private String totalNotUploadedAmount="0";
	private String notSentBankWageAmount="0";
	private String notSentBankMaterialAmount="0";
	private String totalNotSentBankAmount="0";
    private String totalCompletedAmount="0";
    private String rejectedWagesAmount="0";
    private String rejectedMaterialAmount="0";
    private String totalRejectedAmount="0";
    private String releasePendingWageAmount="0";
    private String releasePendingMaterialAmount="0";
    private String totalReleasePendingAmount="0";
    private String responsePendingWageAmount="0";
    private String responsePendingMaterialAmount="0";
    private String totalResponsePendingAmount="0";
    private String reprocessPendingWageAmount="0";
    private String reprocessPendingMaterialAmount="0";
    private String totalReprocessPendingAmount="0";
    private List<NregaPaymentsVO> subList = new ArrayList<NregaPaymentsVO>(0);
    
    
	public List<NregaPaymentsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NregaPaymentsVO> subList) {
		this.subList = subList;
	}
	public String getPendingAtBankQuantity() {
		return pendingAtBankQuantity;
	}
	public void setPendingAtBankQuantity(String pendingAtBankQuantity) {
		this.pendingAtBankQuantity = pendingAtBankQuantity;
	}
	public String getPendingAtBankAmount() {
		return pendingAtBankAmount;
	}
	public void setPendingAtBankAmount(String pendingAtBankAmount) {
		this.pendingAtBankAmount = pendingAtBankAmount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGeneratedQuantity() {
		return generatedQuantity;
	}
	public void setGeneratedQuantity(String generatedQuantity) {
		this.generatedQuantity = generatedQuantity;
	}
	public String getGeneratedAmount() {
		return generatedAmount;
	}
	public void setGeneratedAmount(String generatedAmount) {
		this.generatedAmount = generatedAmount;
	}
	public String getGeneratedPendingQuantity() {
		return generatedPendingQuantity;
	}
	public void setGeneratedPendingQuantity(String generatedPendingQuantity) {
		this.generatedPendingQuantity = generatedPendingQuantity;
	}
	public String getGeneratedPendingAmount() {
		return generatedPendingAmount;
	}
	public void setGeneratedPendingAmount(String generatedPendingAmount) {
		this.generatedPendingAmount = generatedPendingAmount;
	}
	public String getUploadQuantity() {
		return uploadQuantity;
	}
	public void setUploadQuantity(String uploadQuantity) {
		this.uploadQuantity = uploadQuantity;
	}
	public String getUploadAmount() {
		return uploadAmount;
	}
	public void setUploadAmount(String uploadAmount) {
		this.uploadAmount = uploadAmount;
	}
	public String getUploadPendingQunatity() {
		return uploadPendingQunatity;
	}
	public void setUploadPendingQunatity(String uploadPendingQunatity) {
		this.uploadPendingQunatity = uploadPendingQunatity;
	}
	public String getUploadPendingAmount() {
		return uploadPendingAmount;
	}
	public void setUploadPendingAmount(String uploadPendingAmount) {
		this.uploadPendingAmount = uploadPendingAmount;
	}
	public String getSentToBankQuantity() {
		return sentToBankQuantity;
	}
	public void setSentToBankQuantity(String sentToBankQuantity) {
		this.sentToBankQuantity = sentToBankQuantity;
	}
	public String getSentToBankAmount() {
		return sentToBankAmount;
	}
	public void setSentToBankAmount(String sentToBankAmount) {
		this.sentToBankAmount = sentToBankAmount;
	}
	public String getSentToBankPendingQuantity() {
		return sentToBankPendingQuantity;
	}
	public void setSentToBankPendingQuantity(String sentToBankPendingQuantity) {
		this.sentToBankPendingQuantity = sentToBankPendingQuantity;
	}
	public String getSentToBankPendingAmount() {
		return sentToBankPendingAmount;
	}
	public void setSentToBankPendingAmount(String sentToBankPendingAmount) {
		this.sentToBankPendingAmount = sentToBankPendingAmount;
	}
	public String getFailedTransactionQuantity() {
		return failedTransactionQuantity;
	}
	public void setFailedTransactionQuantity(String failedTransactionQuantity) {
		this.failedTransactionQuantity = failedTransactionQuantity;
	}
	public String getFailedTransactionAmount() {
		return failedTransactionAmount;
	}
	public void setFailedTransactionAmount(String failedTransactionAmount) {
		this.failedTransactionAmount = failedTransactionAmount;
	}
	public String getFailedTransactionPendingQuantity() {
		return failedTransactionPendingQuantity;
	}
	public void setFailedTransactionPendingQuantity(String failedTransactionPendingQuantity) {
		this.failedTransactionPendingQuantity = failedTransactionPendingQuantity;
	}
	public String getFailedTransactionPendingAmount() {
		return failedTransactionPendingAmount;
	}
	public void setFailedTransactionPendingAmount(String failedTransactionPendingAmount) {
		this.failedTransactionPendingAmount = failedTransactionPendingAmount;
	}
	public String getConstName() {
		return constName;
	}
	public void setConstName(String constName) {
		this.constName = constName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
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
	public String getTotalPayments() {
		return totalPayments;
	}
	public void setTotalPayments(String totalPayments) {
		this.totalPayments = totalPayments;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalWage() {
		return totalWage;
	}
	public void setTotalWage(String totalWage) {
		this.totalWage = totalWage;
	}
	public String getTotalMaterial() {
		return totalMaterial;
	}
	public void setTotalMaterial(String totalMaterial) {
		this.totalMaterial = totalMaterial;
	}
	public String getCompletedWage() {
		return completedWage;
	}
	public void setCompletedWage(String completedWage) {
		this.completedWage = completedWage;
	}
	public String getCompletedMaterial() {
		return completedMaterial;
	}
	public void setCompletedMaterial(String completedMaterial) {
		this.completedMaterial = completedMaterial;
	}
	public String getPendingWage() {
		return pendingWage;
	}
	public void setPendingWage(String pendingWage) {
		this.pendingWage = pendingWage;
	}
	public String getPendingMaterial() {
		return pendingMaterial;
	}
	public void setPendingMaterial(String pendingMaterial) {
		this.pendingMaterial = pendingMaterial;
	}
	public String getFailedWage() {
		return failedWage;
	}
	public void setFailedWage(String failedWage) {
		this.failedWage = failedWage;
	}
	public String getFailedMaterial() {
		return failedMaterial;
	}
	public void setFailedMaterial(String failedMaterial) {
		this.failedMaterial = failedMaterial;
	}
	public String getGeneratedWage() {
		return generatedWage;
	}
	public void setGeneratedWage(String generatedWage) {
		this.generatedWage = generatedWage;
	}
	public String getGeneratedMaterial() {
		return generatedMaterial;
	}
	public void setGeneratedMaterial(String generatedMaterial) {
		this.generatedMaterial = generatedMaterial;
	}
	public String getUploadWage() {
		return uploadWage;
	}
	public void setUploadWage(String uploadWage) {
		this.uploadWage = uploadWage;
	}
	public String getUploadMaterial() {
		return uploadMaterial;
	}
	public void setUploadMaterial(String uploadMaterial) {
		this.uploadMaterial = uploadMaterial;
	}
	public String getSentBankWage() {
		return sentBankWage;
	}
	public void setSentBankWage(String sentBankWage) {
		this.sentBankWage = sentBankWage;
	}
	public String getSentBankMaterial() {
		return sentBankMaterial;
	}
	public void setSentBankMaterial(String sentBankMaterial) {
		this.sentBankMaterial = sentBankMaterial;
	}
	public String getCompletedWageAmount() {
		return completedWageAmount;
	}
	public void setCompletedWageAmount(String completedWageAmount) {
		this.completedWageAmount = completedWageAmount;
	}
	public String getCompletedMaterialAmount() {
		return completedMaterialAmount;
	}
	public void setCompletedMaterialAmount(String completedMaterialAmount) {
		this.completedMaterialAmount = completedMaterialAmount;
	}
	public String getPendingWageAmount() {
		return pendingWageAmount;
	}
	public void setPendingWageAmount(String pendingWageAmount) {
		this.pendingWageAmount = pendingWageAmount;
	}
	public String getPendingMaterialAmount() {
		return pendingMaterialAmount;
	}
	public void setPendingMaterialAmount(String pendingMaterialAmount) {
		this.pendingMaterialAmount = pendingMaterialAmount;
	}
	public String getFailedWageAmount() {
		return failedWageAmount;
	}
	public void setFailedWageAmount(String failedWageAmount) {
		this.failedWageAmount = failedWageAmount;
	}
	public String getFailedMaterialAmount() {
		return failedMaterialAmount;
	}
	public void setFailedMaterialAmount(String failedMaterialAmount) {
		this.failedMaterialAmount = failedMaterialAmount;
	}
	public String getGeneratedWageAmount() {
		return generatedWageAmount;
	}
	public void setGeneratedWageAmount(String generatedWageAmount) {
		this.generatedWageAmount = generatedWageAmount;
	}
	public String getGeneratedMaterialAmount() {
		return generatedMaterialAmount;
	}
	public void setGeneratedMaterialAmount(String generatedMaterialAmount) {
		this.generatedMaterialAmount = generatedMaterialAmount;
	}
	public String getTotalGenerates() {
		return totalGenerates;
	}
	public void setTotalGenerates(String totalGenerates) {
		this.totalGenerates = totalGenerates;
	}
	public String getUploadedWageAmount() {
		return uploadedWageAmount;
	}
	public void setUploadedWageAmount(String uploadedWageAmount) {
		this.uploadedWageAmount = uploadedWageAmount;
	}
	public String getUploadedMaterialAmount() {
		return uploadedMaterialAmount;
	}
	public void setUploadedMaterialAmount(String uploadedMaterialAmount) {
		this.uploadedMaterialAmount = uploadedMaterialAmount;
	}
	public String getTotalUploads() {
		return totalUploads;
	}
	public void setTotalUploads(String totalUploads) {
		this.totalUploads = totalUploads;
	}
	public String getSentBankWageAmount() {
		return sentBankWageAmount;
	}
	public void setSentBankWageAmount(String sentBankWageAmount) {
		this.sentBankWageAmount = sentBankWageAmount;
	}
	public String getSentBankMaterialAmount() {
		return sentBankMaterialAmount;
	}
	public void setSentBankMaterialAmount(String sentBankMaterialAmount) {
		this.sentBankMaterialAmount = sentBankMaterialAmount;
	}
	public String getTotalSentBankS() {
		return totalSentBankS;
	}
	public void setTotalSentBankS(String totalSentBankS) {
		this.totalSentBankS = totalSentBankS;
	}
	public String getTotalPendings() {
		return totalPendings;
	}
	public void setTotalPendings(String totalPendings) {
		this.totalPendings = totalPendings;
	}
	public String getTotalGeneratesAmount() {
		return totalGeneratesAmount;
	}
	public void setTotalGeneratesAmount(String totalGeneratesAmount) {
		this.totalGeneratesAmount = totalGeneratesAmount;
	}
	public String getTotalUploadsAmount() {
		return totalUploadsAmount;
	}
	public void setTotalUploadsAmount(String totalUploadsAmount) {
		this.totalUploadsAmount = totalUploadsAmount;
	}
	public String getTotalSentBankAmount() {
		return totalSentBankAmount;
	}
	public void setTotalSentBankAmount(String totalSentBankAmount) {
		this.totalSentBankAmount = totalSentBankAmount;
	}
	public String getTotalPendinAmount() {
		return totalPendinAmount;
	}
	public void setTotalPendinAmount(String totalPendinAmount) {
		this.totalPendinAmount = totalPendinAmount;
	}
	public String getFtoSentToBank() {
		return ftoSentToBank;
	}
	public void setFtoSentToBank(String ftoSentToBank) {
		this.ftoSentToBank = ftoSentToBank;
	}
	public String getFtoSentToAmount() {
		return ftoSentToAmount;
	}
	public void setFtoSentToAmount(String ftoSentToAmount) {
		this.ftoSentToAmount = ftoSentToAmount;
	}
	public String getSentToBankSuccess() {
		return sentToBankSuccess;
	}
	public void setSentToBankSuccess(String sentToBankSuccess) {
		this.sentToBankSuccess = sentToBankSuccess;
	}
	public String getSentToBankSuccessAmt() {
		return sentToBankSuccessAmt;
	}
	public void setSentToBankSuccessAmt(String sentToBankSuccessAmt) {
		this.sentToBankSuccessAmt = sentToBankSuccessAmt;
	}
	public String getNotGeneratedWagesAmount() {
		return notGeneratedWagesAmount;
	}
	public void setNotGeneratedWagesAmount(String notGeneratedWagesAmount) {
		this.notGeneratedWagesAmount = notGeneratedWagesAmount;
	}
	public String getNotGeneratedMaterialAmount() {
		return notGeneratedMaterialAmount;
	}
	public void setNotGeneratedMaterialAmount(String notGeneratedMaterialAmount) {
		this.notGeneratedMaterialAmount = notGeneratedMaterialAmount;
	}
	public String getTotalNotGeneratedAmount() {
		return totalNotGeneratedAmount;
	}
	public void setTotalNotGeneratedAmount(String totalNotGeneratedAmount) {
		this.totalNotGeneratedAmount = totalNotGeneratedAmount;
	}
	public String getNotUploadedWagesAmount() {
		return notUploadedWagesAmount;
	}
	public void setNotUploadedWagesAmount(String notUploadedWagesAmount) {
		this.notUploadedWagesAmount = notUploadedWagesAmount;
	}
	public String getNotUploadedMaterialAmount() {
		return notUploadedMaterialAmount;
	}
	public void setNotUploadedMaterialAmount(String notUploadedMaterialAmount) {
		this.notUploadedMaterialAmount = notUploadedMaterialAmount;
	}
	public String getTotalNotUploadedAmount() {
		return totalNotUploadedAmount;
	}
	public void setTotalNotUploadedAmount(String totalNotUploadedAmount) {
		this.totalNotUploadedAmount = totalNotUploadedAmount;
	}
	public String getNotSentBankWageAmount() {
		return notSentBankWageAmount;
	}
	public void setNotSentBankWageAmount(String notSentBankWageAmount) {
		this.notSentBankWageAmount = notSentBankWageAmount;
	}
	public String getNotSentBankMaterialAmount() {
		return notSentBankMaterialAmount;
	}
	public void setNotSentBankMaterialAmount(String notSentBankMaterialAmount) {
		this.notSentBankMaterialAmount = notSentBankMaterialAmount;
	}
	public String getTotalNotSentBankAmount() {
		return totalNotSentBankAmount;
	}
	public void setTotalNotSentBankAmount(String totalNotSentBankAmount) {
		this.totalNotSentBankAmount = totalNotSentBankAmount;
	}
	public String getTotalCompletedAmount() {
		return totalCompletedAmount;
	}
	public void setTotalCompletedAmount(String totalCompletedAmount) {
		this.totalCompletedAmount = totalCompletedAmount;
	}
	public String getRejectedWagesAmount() {
		return rejectedWagesAmount;
	}
	public void setRejectedWagesAmount(String rejectedWagesAmount) {
		this.rejectedWagesAmount = rejectedWagesAmount;
	}
	public String getRejectedMaterialAmount() {
		return rejectedMaterialAmount;
	}
	public void setRejectedMaterialAmount(String rejectedMaterialAmount) {
		this.rejectedMaterialAmount = rejectedMaterialAmount;
	}
	public String getTotalRejectedAmount() {
		return totalRejectedAmount;
	}
	public void setTotalRejectedAmount(String totalRejectedAmount) {
		this.totalRejectedAmount = totalRejectedAmount;
	}
	public String getReleasePendingWageAmount() {
		return releasePendingWageAmount;
	}
	public void setReleasePendingWageAmount(String releasePendingWageAmount) {
		this.releasePendingWageAmount = releasePendingWageAmount;
	}
	public String getReleasePendingMaterialAmount() {
		return releasePendingMaterialAmount;
	}
	public void setReleasePendingMaterialAmount(String releasePendingMaterialAmount) {
		this.releasePendingMaterialAmount = releasePendingMaterialAmount;
	}
	public String getTotalReleasePendingAmount() {
		return totalReleasePendingAmount;
	}
	public void setTotalReleasePendingAmount(String totalReleasePendingAmount) {
		this.totalReleasePendingAmount = totalReleasePendingAmount;
	}
	public String getResponsePendingWageAmount() {
		return responsePendingWageAmount;
	}
	public void setResponsePendingWageAmount(String responsePendingWageAmount) {
		this.responsePendingWageAmount = responsePendingWageAmount;
	}
	public String getResponsePendingMaterialAmount() {
		return responsePendingMaterialAmount;
	}
	public void setResponsePendingMaterialAmount(
			String responsePendingMaterialAmount) {
		this.responsePendingMaterialAmount = responsePendingMaterialAmount;
	}
	public String getTotalResponsePendingAmount() {
		return totalResponsePendingAmount;
	}
	public void setTotalResponsePendingAmount(String totalResponsePendingAmount) {
		this.totalResponsePendingAmount = totalResponsePendingAmount;
	}
	public String getReprocessPendingWageAmount() {
		return reprocessPendingWageAmount;
	}
	public void setReprocessPendingWageAmount(String reprocessPendingWageAmount) {
		this.reprocessPendingWageAmount = reprocessPendingWageAmount;
	}
	public String getReprocessPendingMaterialAmount() {
		return reprocessPendingMaterialAmount;
	}
	public void setReprocessPendingMaterialAmount(
			String reprocessPendingMaterialAmount) {
		this.reprocessPendingMaterialAmount = reprocessPendingMaterialAmount;
	}
	public String getTotalReprocessPendingAmount() {
		return totalReprocessPendingAmount;
	}
	public void setTotalReprocessPendingAmount(String totalReprocessPendingAmount) {
		this.totalReprocessPendingAmount = totalReprocessPendingAmount;
	}
	
  	
	
	
}
