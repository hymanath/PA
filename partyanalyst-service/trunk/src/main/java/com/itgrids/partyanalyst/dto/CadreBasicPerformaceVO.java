package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.service.impl.VoterAddressVO;

/**
 * @author Srishailam Pittala
 * @Date 1st March, 2017
 * */
public class CadreBasicPerformaceVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String imagePath;
	private String membershipNo;
	private String mobileNO;
	private String casteGroup;
	private String casteName;
	private String regDate;
	private List<CadreBasicPerformaceVO> subList = new ArrayList<CadreBasicPerformaceVO>(0);
	private Long casteStateId;
	private Long enrollmentYearId;
	private Long year;
	private String status;
	private String constituencyName;
	private String districtName;
	private String designation;
	private String partyPosition;
	private Long voterId;
	private Long previsBoothId;
	private VoterAddressVO voterAddressVO;
	private List<CadreStatsVO> cadreStatsVOList = new ArrayList<CadreStatsVO>(0);
	
	
	public Long getPrevisBoothId() {
		return previsBoothId;
	}
	public void setPrevisBoothId(Long previsBoothId) {
		this.previsBoothId = previsBoothId;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public List<CadreStatsVO> getCadreStatsVOList() {
		return cadreStatsVOList;
	}
	public void setCadreStatsVOList(List<CadreStatsVO> cadreStatsVOList) {
		this.cadreStatsVOList = cadreStatsVOList;
	}
	public VoterAddressVO getVoterAddressVO() {
		return voterAddressVO;
	}
	public void setVoterAddressVO(VoterAddressVO voterAddressVO) {
		this.voterAddressVO = voterAddressVO;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPartyPosition() {
		return partyPosition;
	}
	public void setPartyPosition(String partyPosition) {
		this.partyPosition = partyPosition;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}
	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getMobileNO() {
		return mobileNO;
	}
	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}
	public String getCasteGroup() {
		return casteGroup;
	}
	public void setCasteGroup(String casteGroup) {
		this.casteGroup = casteGroup;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public List<CadreBasicPerformaceVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreBasicPerformaceVO> subList) {
		this.subList = subList;
	}
	
}
