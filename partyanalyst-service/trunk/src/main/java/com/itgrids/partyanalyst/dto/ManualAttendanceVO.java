/**
 * @author SRAVANTH
 * Mar 6, 2017
 * ManualAttendanceVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

/**
 * @author SRAVANTH
 * @date Mar 6, 2017
 */
public class ManualAttendanceVO {

	private Long tdpCadreId;
	private String remarks;
	private Long partyMeetingId;
	private String base64ImageStr;
	private String imeiNo;
	private String uniqueKey;
	private String latitute;
	private String longitude;
	private Long userId;
	private Long tabPrimaryKey;
	private Long partyMeetingSessionId;
	private String attendedTime;
	private String path;
	private String documentType;
	private String synched;
	
	
	public String getSynched() {
		return synched;
	}
	public void setSynched(String synched) {
		this.synched = synched;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getAttendedTime() {
		return attendedTime;
	}
	public void setAttendedTime(String attendedTime) {
		this.attendedTime = attendedTime;
	}
	public Long getPartyMeetingSessionId() {
		return partyMeetingSessionId;
	}
	public void setPartyMeetingSessionId(Long partyMeetingSessionId) {
		this.partyMeetingSessionId = partyMeetingSessionId;
	}
	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getLatitute() {
		return latitute;
	}
	public void setLatitute(String latitute) {
		this.latitute = latitute;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}
	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	public String getBase64ImageStr() {
		return base64ImageStr;
	}
	public void setBase64ImageStr(String base64ImageStr) {
		this.base64ImageStr = base64ImageStr;
	}
}
