package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class EncTargetsVO {
	private Long locationId;
	private String locationName;
	private Long q2Achv=0L;
	private Long scheme=0L;
	private Double agreementAmount=0.00;
	private Long q1Achv=0L;
	private Double totPopu=0.00;
	private Long q3Achv=0L;
	private Long q1Target=0L;
	private Long q3Target=0L;
	private Long totTarget=0L;
	private Long q4Target=0L;
	private Long q2Target=0L;
	private Long totWorks=0L;
	private Long q4Achv=0L;
	private Double q3Per=0.0;
	private Double q1Per=0.0;
	private Long totAchv=0L;
	private Double totLength=0.00;
	private Double q2Per=0.0;
	private String schemeName;
	private Double totPer=0.00;
	private Double q4Per=0.0;
	private Long constituencyId;
	private String constituencyname;
	private List<EncTargetsVO> subList= new ArrayList<EncTargetsVO>();
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getQ2Achv() {
		return q2Achv;
	}
	public void setQ2Achv(Long q2Achv) {
		this.q2Achv = q2Achv;
	}
	public Long getScheme() {
		return scheme;
	}
	public void setScheme(Long scheme) {
		this.scheme = scheme;
	}
	public Double getAgreementAmount() {
		return agreementAmount;
	}
	public void setAgreementAmount(Double agreementAmount) {
		this.agreementAmount = agreementAmount;
	}
	public Long getQ1Achv() {
		return q1Achv;
	}
	public void setQ1Achv(Long q1Achv) {
		this.q1Achv = q1Achv;
	}
	public Double getTotPopu() {
		return totPopu;
	}
	public void setTotPopu(Double totPopu) {
		this.totPopu = totPopu;
	}
	public Long getQ3Achv() {
		return q3Achv;
	}
	public void setQ3Achv(Long q3Achv) {
		this.q3Achv = q3Achv;
	}
	public Long getQ1Target() {
		return q1Target;
	}
	public void setQ1Target(Long q1Target) {
		this.q1Target = q1Target;
	}
	public Long getQ3Target() {
		return q3Target;
	}
	public void setQ3Target(Long q3Target) {
		this.q3Target = q3Target;
	}
	public Long getTotTarget() {
		return totTarget;
	}
	public void setTotTarget(Long totTarget) {
		this.totTarget = totTarget;
	}
	public Long getQ4Target() {
		return q4Target;
	}
	public void setQ4Target(Long q4Target) {
		this.q4Target = q4Target;
	}
	public Long getQ2Target() {
		return q2Target;
	}
	public void setQ2Target(Long q2Target) {
		this.q2Target = q2Target;
	}
	public Long getTotWorks() {
		return totWorks;
	}
	public void setTotWorks(Long totWorks) {
		this.totWorks = totWorks;
	}
	public Long getQ4Achv() {
		return q4Achv;
	}
	public void setQ4Achv(Long q4Achv) {
		this.q4Achv = q4Achv;
	}
	public Double getQ3Per() {
		return q3Per;
	}
	public void setQ3Per(Double q3Per) {
		this.q3Per = q3Per;
	}
	public Double getQ1Per() {
		return q1Per;
	}
	public void setQ1Per(Double q1Per) {
		this.q1Per = q1Per;
	}
	public void setQ2Per(Double q2Per) {
		this.q2Per = q2Per;
	}
	public void setTotPer(Double totPer) {
		this.totPer = totPer;
	}
	public void setQ4Per(Double q4Per) {
		this.q4Per = q4Per;
	}
	public Long getTotAchv() {
		return totAchv;
	}
	public void setTotAchv(Long totAchv) {
		this.totAchv = totAchv;
	}
	public Double getTotLength() {
		return totLength;
	}
	public void setTotLength(Double totLength) {
		this.totLength = totLength;
	}
	public Double getQ2Per() {
		return q2Per;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public Double getTotPer() {
		return totPer;
	}
	public Double getQ4Per() {
		return q4Per;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId=constituencyId;
	}
	public void setConstituencyname(String constituencyname) {
		this.constituencyname=constituencyname;
	}
	public String getConstituencyname() {
		return constituencyname;
	}
	public List<EncTargetsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<EncTargetsVO> subList) {
		this.subList = subList;
	}
	
}
