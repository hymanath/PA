package com.itgrids.partyanalyst.dto;

import java.util.List;

public class AlertVerificationVO {

	private Long alertId;
	private String heading;
	private String name;
	private Long alertActionTypeStatusId;
	private String actionTypeStatus;
	private String updateTime;
	private String time;
	private String comments;
	private List<AlertVerificationVO> conversationList;
	private List<AlertVerificationVO> programCommittList;
	private List<AlertVerificationVO> infoCellList;
	private List<String> prgramCmmmtteedcumentsLst;
	private List<String> infoCellDocList;
	private List<String> documentList;
	
	private AlertVerificationVO programCommitteeVO;
	private AlertVerificationVO infoCellVO;
	
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public Long getAlertActionTypeStatusId() {
		return alertActionTypeStatusId;
	}
	public void setAlertActionTypeStatusId(Long alertActionTypeStatusId) {
		this.alertActionTypeStatusId = alertActionTypeStatusId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public AlertVerificationVO getProgramCommitteeVO() {
		return programCommitteeVO;
	}
	public void setProgramCommitteeVO(AlertVerificationVO programCommitteeVO) {
		this.programCommitteeVO = programCommitteeVO;
	}
	public AlertVerificationVO getInfoCellVO() {
		return infoCellVO;
	}
	public void setInfoCellVO(AlertVerificationVO infoCellVO) {
		this.infoCellVO = infoCellVO;
	}
	public String getActionTypeStatus() {
		return actionTypeStatus;
	}
	public void setActionTypeStatus(String actionTypeStatus) {
		this.actionTypeStatus = actionTypeStatus;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AlertVerificationVO> getProgramCommittList() {
		return programCommittList;
	}
	public void setProgramCommittList(List<AlertVerificationVO> programCommittList) {
		this.programCommittList = programCommittList;
	}
	public List<AlertVerificationVO> getInfoCellList() {
		return infoCellList;
	}
	public void setInfoCellList(List<AlertVerificationVO> infoCellList) {
		this.infoCellList = infoCellList;
	}
	public List<String> getPrgramCmmmtteedcumentsLst() {
		return prgramCmmmtteedcumentsLst;
	}
	public void setPrgramCmmmtteedcumentsLst(List<String> prgramCmmmtteedcumentsLst) {
		this.prgramCmmmtteedcumentsLst = prgramCmmmtteedcumentsLst;
	}
	public List<String> getInfoCellDocList() {
		return infoCellDocList;
	}
	public void setInfoCellDocList(List<String> infoCellDocList) {
		this.infoCellDocList = infoCellDocList;
	}
	public List<AlertVerificationVO> getConversationList() {
		return conversationList;
	}
	public void setConversationList(List<AlertVerificationVO> conversationList) {
		this.conversationList = conversationList;
	}
	public List<String> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<String> documentList) {
		this.documentList = documentList;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	
}
