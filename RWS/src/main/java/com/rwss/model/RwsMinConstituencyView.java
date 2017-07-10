package com.rwss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aprwssuser.rws_min_constituency_view")
public class RwsMinConstituencyView {

	private String dName;
	private String mName;
	private String contituencyName;
	private String dCode;
	private String mCode;
	private String constituencyCode;
	
	@Id
	@Column(name="DNAME")
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	@Column(name="MNAME")
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	@Column(name="CONSTITUENCY_NAME")
	public String getContituencyName() {
		return contituencyName;
	}
	public void setContituencyName(String contituencyName) {
		this.contituencyName = contituencyName;
	}
	@Column(name="CONSTITUENCY_CODE")
	public String getConstituencyCode() {
		return constituencyCode;
	}
	public void setConstituencyCode(String constituencyCode) {
		this.constituencyCode = constituencyCode;
	}
	@Column(name="DCODE")
	public String getdCode() {
		return dCode;
	}
	public void setdCode(String dCode) {
		this.dCode = dCode;
	}
	@Column(name="MCODE")
	public String getmCode() {
		return mCode;
	}
	public void setmCode(String mCode) {
		this.mCode = mCode;
	}
	
	
	
}
