package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NregaPaymentsVO implements Serializable{

	private String id;
	private String name;
	private String type;
	
	private String generatedQuantity;
	private String generatedAmount;
	private String generatedPendingQuantity;
	private String generatedPendingAmount;
	
	private String uploadQuantity;
	private String uploadAmount;
	private String uploadPendingQunatity;
	private String uploadPendingAmount;
	
	private String sentToBankQuantity;
	private String sentToBankAmount;
	private String sentToBankPendingQuantity;
	private String sentToBankPendingAmount;
	
	private String failedTransactionQuantity;
	private String failedTransactionAmount;
	private String failedTransactionPendingQuantity;
	private String failedTransactionPendingAmount;
	
	
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
}
