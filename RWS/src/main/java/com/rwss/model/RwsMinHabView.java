package com.rwss.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aprwssuser.rws_min_hab_view")
public class RwsMinHabView {

	
	private String dName; 
	private String mName; 
	private String pName;
	private String vName;
	private String panchName;
	private String panchCode;
	private String censusYear; 
	private Long censusPlainPopu;
	private Long censusSCPopu;
	private Long censusSTPopu;
	private Long plainPopCovered;
	private Date statusDate;
	private BigDecimal unSafeLpcd;
	private BigDecimal safeLpcd;
	private String coverageStatus;
	private String previousYrStatus;
	private BigDecimal latitude;
	private BigDecimal longtitude;
	private Long scPopCovered;
	
	private String dCode;
	private String mCode;
	private String pCode;
	private String vCode;
	
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
	@Column(name="PNAME")
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	@Column(name="VNAME")
	public String getvName() {
		return vName;
	}
	public void setvName(String vName) {
		this.vName = vName;
	}
	@Column(name="PANCH_NAME")
	public String getPanchName() {
		return panchName;
	}
	public void setPanchName(String panchName) {
		this.panchName = panchName;
	}
	@Id
	@Column(name = "PANCH_CODE")
	public String getPanchCode() {
		return panchCode;
	}
	public void setPanchCode(String panchCode) {
		this.panchCode = panchCode;
	}
	@Column(name="CENSUS_YEAR")
	public String getCensusYear() {
		return censusYear;
	}
	public void setCensusYear(String censusYear) {
		this.censusYear = censusYear;
	}
	@Column(name="CENSUS_PLAIN_POPU")
	public Long getCensusPlainPopu() {
		return censusPlainPopu;
	}
	public void setCensusPlainPopu(Long censusPlainPopu) {
		this.censusPlainPopu = censusPlainPopu;
	}
	
	@Column(name="CENSUS_SC_POPU")
	public Long getCensusSCPopu() {
		return censusSCPopu;
	}
	public void setCensusSCPopu(Long censusSCPopu) {
		this.censusSCPopu = censusSCPopu;
	}

	@Column(name="CENSUS_ST_POPU")
	public Long getCensusSTPopu() {
		return censusSTPopu;
	}
	public void setCensusSTPopu(Long censusSTPopu) {
		this.censusSTPopu = censusSTPopu;
	}
	@Column(name="PLAIN_POPU_COVERED")
	public Long getPlainPopCovered() {
		return plainPopCovered;
	}
	public void setPlainPopCovered(Long plainPopCovered) {
		this.plainPopCovered = plainPopCovered;
	}
	@Column(name="STATUS_DATE")
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	@Column(name="UNSAFE_LPCD")
	public BigDecimal getUnSafeLpcd() {
		return unSafeLpcd;
	}
	public void setUnSafeLpcd(BigDecimal unSafeLpcd) {
		this.unSafeLpcd = unSafeLpcd;
	}
	@Column(name="SAFE_LPCD")
	public BigDecimal getSafeLpcd() {
		return safeLpcd;
	}
	public void setSafeLpcd(BigDecimal safeLpcd) {
		this.safeLpcd = safeLpcd;
	}
	@Column(name="COVERAGE_STATUS")
	public String getCoverageStatus() {
		return coverageStatus;
	}
	public void setCoverageStatus(String coverageStatus) {
		this.coverageStatus = coverageStatus;
	}
	@Column(name="PREVIOUS_YR_STATUS")
	public String getPreviousYrStatus() {
		return previousYrStatus;
	}
	public void setPreviousYrStatus(String previousYrStatus) {
		this.previousYrStatus = previousYrStatus;
	}
	
	@Column(name = "LATITUDE")
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	@Column(name = "LONGITUDE")
	public BigDecimal getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(BigDecimal longtitude) {
		this.longtitude = longtitude;
	}
	@Column(name="SC_POP_COVERED")
	public Long getScPopCovered() {
		return scPopCovered;
	}
	public void setScPopCovered(Long scPopCovered) {
		this.scPopCovered = scPopCovered;
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
	@Column(name="PCODE")
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	@Column(name="VCODE")
	public String getvCode() {
		return vCode;
	}
	public void setvCode(String vCode) {
		this.vCode = vCode;
	}
	
}
