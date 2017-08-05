package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocationFundDetailsVO implements Serializable{
	
	private static final long serialVersionUID = -5436365311016546551L;
	
	private Long id;
	private String name;
	private String type;
	private String totalAmt="0.0";
	private Double averageAmt=0.0D;
	private Double perc=0.0D;
	private Long fundedLoc;
	private Long notFundedLoc;
	private Double fundedPerc;
	private Double nonFundedPerc;
	private Long totSchemes;
	private Long count;
	private String year;
	private Long sum;
	private String ttlAmt="0.0";
	private String avrgeAmt="0.0";
	private String fundAvageAmt ="0.0";
	
	private List<IdNameVO> subList = new ArrayList<IdNameVO>();
	private List<LocationFundDetailsVO> detailsVOs;	
	private List<LocationFundDetailsVO> fundDetailsVOs;
	private List<LocationFundDetailsVO> schemeDetailsVOs;
	private List<FundVO> fundList = new ArrayList<FundVO>();
	
	private String districtName;
	private String constituencyName;
	private String mandalName;
	private Double totalExpenditure=0.0d;
	
	public Long getTotSchemes() {
		return totSchemes;
	}
	public void setTotSchemes(Long totSchemes) {
		this.totSchemes = totSchemes;
	}
	public Long getFundedLoc() {
		return fundedLoc;
	}
	public void setFundedLoc(Long fundedLoc) {
		this.fundedLoc = fundedLoc;
	}
	public Long getNotFundedLoc() {
		return notFundedLoc;
	}
	public void setNotFundedLoc(Long notFundedLoc) {
		this.notFundedLoc = notFundedLoc;
	}
	public Double getFundedPerc() {
		return fundedPerc;
	}
	public void setFundedPerc(Double fundedPerc) {
		this.fundedPerc = fundedPerc;
	}
	public Double getNonFundedPerc() {
		return nonFundedPerc;
	}
	public void setNonFundedPerc(Double nonFundedPerc) {
		this.nonFundedPerc = nonFundedPerc;
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
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public Double getPerc() {
		return perc;
	}
	public void setPerc(Double perc) {
		this.perc = perc;
	}
	public List<IdNameVO> getSubList() {
		return subList;
	}
	public void setSubList(List<IdNameVO> subList) {
		this.subList = subList;
	}
	public Double getAverageAmt() {
		return averageAmt;
	}
	public void setAverageAmt(Double averageAmt) {
		this.averageAmt = averageAmt;
	}
	public List<LocationFundDetailsVO> getDetailsVOs() {
		if(detailsVOs == null){
			detailsVOs = new ArrayList<LocationFundDetailsVO>();
		}
		return detailsVOs;
	}
	public List<LocationFundDetailsVO> getFundDetailsVOs() {
		if(fundDetailsVOs == null){
			fundDetailsVOs = new ArrayList<LocationFundDetailsVO>();
		}
		return fundDetailsVOs;
	}
	public List<LocationFundDetailsVO> getSchemeDetailsVOs() {
		if(schemeDetailsVOs == null){
			schemeDetailsVOs = new ArrayList<LocationFundDetailsVO>();
		}
		return schemeDetailsVOs;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getSum() {
		return sum;
	}
	public void setSum(Long sum) {
		this.sum = sum;
	}	
	
	public String getTtlAmt() {
		return ttlAmt;
	}
	public void setTtlAmt(String ttlAmt) {
		this.ttlAmt = ttlAmt;
	}
	public String getAvrgeAmt() {
		return avrgeAmt;
	}
	public void setAvrgeAmt(String avrgeAmt) {
		this.avrgeAmt = avrgeAmt;
	}
	public String getFundAvageAmt() {
		return fundAvageAmt;
	}
	public void setFundAvageAmt(String fundAvageAmt) {
		this.fundAvageAmt = fundAvageAmt;
	}
	public List<FundVO> getFundList() {
		return fundList;
	}
	public void setFundList(List<FundVO> fundList) {
		this.fundList = fundList;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public Double getTotalExpenditure() {
		return totalExpenditure;
	}
	public void setTotalExpenditure(Double totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	
	
	
}
