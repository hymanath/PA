package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private List<NregsProjectsVO> subList = new ArrayList<NregsProjectsVO>();
	private String componentType;
	private Long id;
	private String name;
	private Long count = 0L;
	
	private String state;
	private String district;
	private String constituency;
	private String mandal;
	private String panchayat;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
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
	public List<NregsProjectsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NregsProjectsVO> subList) {
		this.subList = subList;
	}
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
}
