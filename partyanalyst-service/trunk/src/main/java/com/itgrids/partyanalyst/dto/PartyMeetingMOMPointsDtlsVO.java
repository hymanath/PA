package com.itgrids.partyanalyst.dto;

import java.util.List;

public class PartyMeetingMOMPointsDtlsVO {

	private Long id;
	private String momPoints;
	private String filePath;
	
	private List<PartyMeetingMOMPointsDtlsVO> documentList;
	private List<PartyMeetingMOMPointsDtlsVO> momPointsList;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMomPoints() {
		return momPoints;
	}
	public void setMomPoints(String momPoints) {
		this.momPoints = momPoints;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public List<PartyMeetingMOMPointsDtlsVO> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<PartyMeetingMOMPointsDtlsVO> documentList) {
		this.documentList = documentList;
	}
	public List<PartyMeetingMOMPointsDtlsVO> getMomPointsList() {
		return momPointsList;
	}
	public void setMomPointsList(List<PartyMeetingMOMPointsDtlsVO> momPointsList) {
		this.momPointsList = momPointsList;
	}
	
	
}
