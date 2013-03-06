package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class SmsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long cadreId;
	private String cadreName;
	private String mobileNO;
	private List<SmsVO> smslist;
	
	
	public List<SmsVO> getSmslist() {
		return smslist;
	}
	public void setSmslist(List<SmsVO> smslist) {
		this.smslist = smslist;
	}
	//Getters and Setters
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public String getMobileNO() {
		return mobileNO;
	}
	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}

}
