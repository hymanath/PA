package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ExceptCastsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long casteId;
	private String casteName;
	private Double castePerc;
	private Long selectLevelId;
	private Long selectLevelvalue;
	private  Long panchayatId;
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public Double getCastePerc() {
		return castePerc;
	}
	public void setCastePerc(Double castePerc) {
		this.castePerc = castePerc;
	}
	public Long getSelectLevelId() {
		return selectLevelId;
	}
	public void setSelectLevelId(Long selectLevelId) {
		this.selectLevelId = selectLevelId;
	}
	public Long getSelectLevelvalue() {
		return selectLevelvalue;
	}
	public void setSelectLevelvalue(Long selectLevelvalue) {
		this.selectLevelvalue = selectLevelvalue;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	
	
	
}
