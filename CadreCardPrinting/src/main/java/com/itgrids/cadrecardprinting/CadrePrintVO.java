package com.itgrids.cadrecardprinting;

import java.io.Serializable;

import javafx.beans.property.BooleanProperty;

import javafx.beans.property.SimpleBooleanProperty;

import javafx.beans.property.SimpleStringProperty;

public class CadrePrintVO implements Serializable{

	
	private static final long serialVersionUID = 9113376811379344093L;
	private Long   cadrePrintVOId;
	private String villageName;
	private String mandalName;
	private String constituencyName;
	private String districtName;
	private String firstCode;
	private String secondCode;
	private String voterName;
	private String relativeName;
	
	private String districtEng;
	private String constiEng;
	private String mandalEng;
	private String villageEng;
	private String printStatus = "Pending";
	
	private SimpleStringProperty nfcNumber = new SimpleStringProperty("");
	private String voterId;
	private String voterCardNo;
	private  BooleanProperty  voterIdTagging1 = new SimpleBooleanProperty(false); 

	private String refNumber;
	private String cardNumber;
	private String image;
	private String voterImgPath;
	
	
	private String village;
    private String mandal;
    private String constituency;
    private String district;
    private String name;
   
    private String muncipalityName;
   
    private String constituencyType;
	private String photoType;
	
	public Long getCadrePrintVOId() {
		return cadrePrintVOId;
	}
	public void setCadrePrintVOId(Long cadrePrintVOId) {
		this.cadrePrintVOId = cadrePrintVOId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
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
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getFirstCode() {
		return firstCode;
	}
	public void setFirstCode(String firstCode) {
		this.firstCode = firstCode;
	}
	public String getSecondCode() {
		return secondCode;
	}
	public void setSecondCode(String secondCode) {
		this.secondCode = secondCode;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getDistrictEng() {
		return districtEng;
	}
	public void setDistrictEng(String districtEng) {
		this.districtEng = districtEng;
	}
	public String getConstiEng() {
		return constiEng;
	}
	public void setConstiEng(String constiEng) {
		this.constiEng = constiEng;
	}
	public String getMandalEng() {
		return mandalEng;
	}
	public void setMandalEng(String mandalEng) {
		this.mandalEng = mandalEng;
	}
	public String getVillageEng() {
		return villageEng;
	}
	public void setVillageEng(String villageEng) {
		this.villageEng = villageEng;
	}
	
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	
	public BooleanProperty voterIdTagging1Property() { return voterIdTagging1; }
	
	//public String nfcNumber() { return nfcNumber; }
	
	public SimpleStringProperty nfcNumberProperty() { return nfcNumber; }
	
	public BooleanProperty getVoterIdTagging1() {
		return voterIdTagging1;
	}
	public void setVoterIdTagging1(BooleanProperty voterIdTagging1) {
		this.voterIdTagging1 = voterIdTagging1;
	}
	public String getNfcNumber() {
		return nfcNumber.getValue();
	}
	public void setNfcNumber(SimpleStringProperty nfcNumber) {
		this.nfcNumber = nfcNumber;
	}
	public String getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVoterImgPath() {
		return voterImgPath;
	}
	public void setVoterImgPath(String voterImgPath) {
		this.voterImgPath = voterImgPath;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMuncipalityName() {
		return muncipalityName;
	}
	public void setMuncipalityName(String muncipalityName) {
		this.muncipalityName = muncipalityName;
	}
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	
	
}

