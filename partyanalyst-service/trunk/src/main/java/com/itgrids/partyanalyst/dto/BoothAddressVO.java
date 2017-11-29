package com.itgrids.partyanalyst.dto;

public class BoothAddressVO {

	private Long stateId;
	private String stateName;
	private Long districtId;
	private String districtName;
	private Long parliamentConstituencyId;
	private String parliamentConstituency;
	private Long constituencyId;
	private String constituencyName;
	private Long tehsilId;
	private String tehsilName;
	private Long panchayatId;
	private String panchayat;
	private Long boothId;
	private String boothName;
	private String status;
	private String isReadyToConfirm;
	private String isConfirm;
	
	private Long tdpCadreId;
	private String cadreName;
	private Long serialNo;
	private String image;
	private String base64imageStr;
	private String mobileNo;
	private String role;
	private String memberShipNo;
	private Long rangeId;
	private String range;
	private Long memberCount=0l;
	private Long convenerCount=0l;
	private Long totalCount =0L;
	
	private String localElectionBody;
	private String ward;
	private Long ownBoothId;
	private String ownBoothNo;
	
	
	public Long getOwnBoothId() {
		return ownBoothId;
	}
	public void setOwnBoothId(Long ownBoothId) {
		this.ownBoothId = ownBoothId;
	}
	public String getOwnBoothNo() {
		return ownBoothNo;
	}
	public void setOwnBoothNo(String ownBoothNo) {
		this.ownBoothNo = ownBoothNo;
	}
	public String getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(String localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getIsReadyToConfirm() {
		return isReadyToConfirm;
	}
	public void setIsReadyToConfirm(String isReadyToConfirm) {
		this.isReadyToConfirm = isReadyToConfirm;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}
	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}
	public String getParliamentConstituency() {
		return parliamentConstituency;
	}
	public void setParliamentConstituency(String parliamentConstituency) {
		this.parliamentConstituency = parliamentConstituency;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public Long getRangeId() {
		return rangeId;
	}
	public void setRangeId(Long rangeId) {
		this.rangeId = rangeId;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getIsConfirm() {
		return isConfirm;
	}
	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}
	public Long getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Long memberCount) {
		this.memberCount = memberCount;
	}
	public Long getConvenerCount() {
		return convenerCount;
	}
	public void setConvenerCount(Long convenerCount) {
		this.convenerCount = convenerCount;
	}
	public String getBase64imageStr() {
		return base64imageStr;
	}
	public void setBase64imageStr(String base64imageStr) {
		this.base64imageStr = base64imageStr;
	}
	
		
}
