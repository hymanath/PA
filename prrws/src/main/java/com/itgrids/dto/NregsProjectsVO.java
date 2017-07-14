package com.itgrids.dto;

import java.io.Serializable;

public class NregsProjectsVO implements Serializable{

	private String parameter;
	private String target;
	private String completed;
	private String percentage;
	private String type;
	
	private String FTONOTGENCNT;
	private String FTONOTUPLOADCNT;
	private String FTONOTSENTCNT;
	private String REJECTCNT;
	private String PENDINGRESPONSECNT;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFTONOTGENCNT() {
		return FTONOTGENCNT;
	}
	public void setFTONOTGENCNT(String fTONOTGENCNT) {
		FTONOTGENCNT = fTONOTGENCNT;
	}
	public String getFTONOTUPLOADCNT() {
		return FTONOTUPLOADCNT;
	}
	public void setFTONOTUPLOADCNT(String fTONOTUPLOADCNT) {
		FTONOTUPLOADCNT = fTONOTUPLOADCNT;
	}
	public String getFTONOTSENTCNT() {
		return FTONOTSENTCNT;
	}
	public void setFTONOTSENTCNT(String fTONOTSENTCNT) {
		FTONOTSENTCNT = fTONOTSENTCNT;
	}
	public String getREJECTCNT() {
		return REJECTCNT;
	}
	public void setREJECTCNT(String rEJECTCNT) {
		REJECTCNT = rEJECTCNT;
	}
	public String getPENDINGRESPONSECNT() {
		return PENDINGRESPONSECNT;
	}
	public void setPENDINGRESPONSECNT(String pENDINGRESPONSECNT) {
		PENDINGRESPONSECNT = pENDINGRESPONSECNT;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
}
