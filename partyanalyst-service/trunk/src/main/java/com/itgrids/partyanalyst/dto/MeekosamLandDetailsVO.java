/**
 * @author SRAVANTH
 * MAY 31, 2017
 * MeekosamLandDetailsVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

/**
 * @author SRAVANTH
 * @date MAY 31, 2017
 */
public class MeekosamLandDetailsVO {

	private Long id;
	private String name;
	
	private Long districtId;
	private Long mandalId;
	private Long villageId;
	private String surveyNO;
	private String landInAcres;
	private String landInCents;
	
	
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
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public String getSurveyNO() {
		return surveyNO;
	}
	public void setSurveyNO(String surveyNO) {
		this.surveyNO = surveyNO;
	}
	public String getLandInAcres() {
		return landInAcres;
	}
	public void setLandInAcres(String landInAcres) {
		this.landInAcres = landInAcres;
	}
	public String getLandInCents() {
		return landInCents;
	}
	public void setLandInCents(String landInCents) {
		this.landInCents = landInCents;
	}
}
