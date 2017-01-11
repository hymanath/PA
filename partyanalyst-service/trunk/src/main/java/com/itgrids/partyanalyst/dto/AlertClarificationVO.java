package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class AlertClarificationVO {
	
	private Long alertId;
	private String clarificationRequired;
	private Long clarificationStatusId=0l;
	private List<KeyValueVO> clarificationComments = new ArrayList<KeyValueVO>(0);
	private List<KeyValueVO> documentsList = new ArrayList<KeyValueVO>(0);
	
	
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public String getClarificationRequired() {
		return clarificationRequired;
	}
	public void setClarificationRequired(String clarificationRequired) {
		this.clarificationRequired = clarificationRequired;
	}
	public Long getClarificationStatusId() {
		return clarificationStatusId;
	}
	public void setClarificationStatusId(Long clarificationStatusId) {
		this.clarificationStatusId = clarificationStatusId;
	}
	public List<KeyValueVO> getClarificationComments() {
		return clarificationComments;
	}
	public void setClarificationComments(List<KeyValueVO> clarificationComments) {
		this.clarificationComments = clarificationComments;
	}
	public List<KeyValueVO> getDocumentsList() {
		return documentsList;
	}
	public void setDocumentsList(List<KeyValueVO> documentsList) {
		this.documentsList = documentsList;
	}
	
	
	
}
