package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserResponseVO;

/**
 * @author Administrator Jul 10, 2014
 *
 */
public class SurveyResponceVO extends UserResponseVO  implements Serializable  
{

	
	private static final long serialVersionUID = -7553249000409612768L;
	private Long surveyUserId;
	private Long surveyorId;
	private Long voterId;
	private Long casteId;
	private Long hamletId;
	private Long wardId;
	private String casteName = null;
	private String hamletName = null;
	private String localArea = null;
	private Date date;
	private Long boothId;
	private String longitude = null;
	private String latitude = null;
	
	private String voterCardNo;
	
	
	private String isCadre = "N";
	private String isInfluencingPeople = "N";
	private String mobileNo = null;
	
	private String voterName;
	private String relativeName;
	private String gender;
	private Long age;
	private String houseNo;
	private String dataTypeId;
	
	private List<String> partNos;
	private List<String> boothIds;
	private List<SurveyResponceVO> verifiersData;
	
	
	
	private String uuid;
	private String statusId;
	private String insertTime;
	
	private int votersSize=0;
	
	private Long serialNo;
	private String comment;
	private Long sameCount;
	private Long wmWrong;
	private Long tpWrong;
	private Long newCaste;
	
	private String dcCasteName;
	private String dvCasteName;
	
	private String dcCorrectedCasteName;
	private String dvCorrectedCasteName;
	

	public String getDcCorrectedCasteName() {
		return dcCorrectedCasteName;
	}
	public void setDcCorrectedCasteName(String dcCorrectedCasteName) {
		this.dcCorrectedCasteName = dcCorrectedCasteName;
	}
	public String getDvCorrectedCasteName() {
		return dvCorrectedCasteName;
	}
	public void setDvCorrectedCasteName(String dvCorrectedCasteName) {
		this.dvCorrectedCasteName = dvCorrectedCasteName;
	}
	
	public String getDcCasteName() {
		return dcCasteName;
	}
	public void setDcCasteName(String dcCasteName) {
		this.dcCasteName = dcCasteName;
	}
	public String getDvCasteName() {
		return dvCasteName;
	}
	public void setDvCasteName(String dvCasteName) {
		this.dvCasteName = dvCasteName;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public int getVotersSize() {
		return votersSize;
	}
	public void setVotersSize(int votersSize) {
		this.votersSize = votersSize;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public List<SurveyResponceVO> getVerifiersData() {
		return verifiersData;
	}
	public void setVerifiersData(List<SurveyResponceVO> verifiersData) {
		this.verifiersData = verifiersData;
	}
	public List<String> getPartNos() {
		return partNos;
	}
	public void setPartNos(List<String> partNos) {
		this.partNos = partNos;
	}
	public List<String> getBoothIds() {
		return boothIds;
	}
	public void setBoothIds(List<String> boothIds) {
		this.boothIds = boothIds;
	}
	public String getDataTypeId() {
		return dataTypeId;
	}
	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
	public Long getSurveyUserId() {
		return surveyUserId;
	}
	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
	}
	public Long getSurveyorId() {
		return surveyorId;
	}
	public void setSurveyorId(Long surveyorId) {
		this.surveyorId = surveyorId;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public String getLocalArea() {
		return localArea;
	}
	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public String getIsCadre() {
		return isCadre;
	}
	public void setIsCadre(String isCadre) {
		this.isCadre = isCadre;
	}
	public String getIsInfluencingPeople() {
		return isInfluencingPeople;
	}
	public void setIsInfluencingPeople(String isInfluencingPeople) {
		this.isInfluencingPeople = isInfluencingPeople;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	@Override
	public String toString() {
		return "SurveyResponceVO [surveyUserId=" + surveyUserId
				+ ", surveyorId=" + surveyorId + ", voterId=" + voterId
				+ ", casteId=" + casteId + ", hamletId=" + hamletId
				+ ", casteName=" + casteName + ", hamletName=" + hamletName
				+ ", localArea=" + localArea + ", date=" + date + ", boothId="
				+ boothId + ", longitude=" + longitude + ", latitude="
				+ latitude + ", voterCardNo=" + voterCardNo + ", isCadre="
				+ isCadre + ", isInfluencingPeople=" + isInfluencingPeople
				+ ", mobileNo=" + mobileNo + ", voterName=" + voterName
				+ ", gender=" + gender + ", age=" + age + ", houseNo="
				+ houseNo + ", dataTypeId=" + dataTypeId + ", partNos="
				+ partNos + ", boothIds=" + boothIds + ", verifiersData="
				+ verifiersData + ", uuid=" + uuid + ", statusId=" + statusId
				+ ", insertTime=" + insertTime + ", votersSize=" + votersSize
				+ "]";
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getSameCount() {
		return sameCount;
	}
	public void setSameCount(Long sameCount) {
		this.sameCount = sameCount;
	}
	public Long getWmWrong() {
		return wmWrong;
	}
	public void setWmWrong(Long wmWrong) {
		this.wmWrong = wmWrong;
	}
	public Long getTpWrong() {
		return tpWrong;
	}
	public void setTpWrong(Long tpWrong) {
		this.tpWrong = tpWrong;
	}
	public Long getNewCaste() {
		return newCaste;
	}
	public void setNewCaste(Long newCaste) {
		this.newCaste = newCaste;
	}
	
	
	
}
