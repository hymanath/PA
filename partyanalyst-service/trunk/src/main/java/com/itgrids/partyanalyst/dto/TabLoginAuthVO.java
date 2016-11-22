/**
 * 
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sys
 *
 */
public class TabLoginAuthVO implements Serializable{
	private Long cadreSurveyUserId;
	private String imiNo;
	private String imiNo2;
	private String isDeleted;
	private String insertedTime;
	private String status;
	private String name;
	private Long tabLoginAuthId;
	private String constistuencyName;
	
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	public String getImiNo() {
		return imiNo;
	}
	public void setImiNo(String imiNo) {
		this.imiNo = imiNo;
	}
	public String getImiNo2() {
		return imiNo2;
	}
	public void setImiNo2(String imiNo2) {
		this.imiNo2 = imiNo2;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Long getTabLoginAuthId() {
		return tabLoginAuthId;
	}
	public void setTabLoginAuthId(Long tabLoginAuthId) {
		this.tabLoginAuthId = tabLoginAuthId;
	}
	public String getConstistuencyName() {
		return constistuencyName;
	}
	public void setConstistuencyName(String constistuencyName) {
		this.constistuencyName = constistuencyName;
	}
	
	
}
