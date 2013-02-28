package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImportantFamiliesInfoVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3700451727767523916L;

	private Long totalVoters;
	private Long totalFamalies;
	private Long below3;
	private Long below3Popul;
	private Double below3perc = 0d;
	private Long betwn4to6;
	private Long betwn4to6Popul;
	private Double betwn4to6perc = 0d;
	private Long betwn7to10;
	private Long betwn7to10Popul;
	private Double betwn7to10perc = 0d;
	private Long above10;
	private Long above10Popul;
	private Double above10perc = 0d;
	private String name;
	private String type;
	private boolean dataPresent = true;
	private List<ImportantFamiliesInfoVo> subList = new ArrayList<ImportantFamiliesInfoVo>();
	private String totalMaleVoters;
	private String totalFemaleVoters;
	private String unKnowVoters;
	private Long reportLevelId;
	private Long reportLevelValue;
	private Long publicationDateId;
	private Double totalPercentage;
	private Long typeId;
	private Long constituencyId;
	
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Double getTotalPercentage() {
		return totalPercentage;
	}

	public void setTotalPercentage(Double totalPercentage) {
		this.totalPercentage = totalPercentage;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public Long getTotalFamalies() {
		return totalFamalies;
	}

	public void setTotalFamalies(Long totalFamalies) {
		this.totalFamalies = totalFamalies;
	}

	public Long getBelow3() {
		return below3;
	}

	public void setBelow3(Long below3) {
		this.below3 = below3;
	}

	public Double getBelow3perc() {
		return below3perc;
	}

	public void setBelow3perc(Double below3perc) {
		this.below3perc = below3perc;
	}

	public Long getBetwn4to6() {
		return betwn4to6;
	}

	public void setBetwn4to6(Long betwn4to6) {
		this.betwn4to6 = betwn4to6;
	}

	public Double getBetwn4to6perc() {
		return betwn4to6perc;
	}

	public void setBetwn4to6perc(Double betwn4to6perc) {
		this.betwn4to6perc = betwn4to6perc;
	}

	public Long getBetwn7to10() {
		return betwn7to10;
	}

	public void setBetwn7to10(Long betwn7to10) {
		this.betwn7to10 = betwn7to10;
	}

	public Double getBetwn7to10perc() {
		return betwn7to10perc;
	}

	public void setBetwn7to10perc(Double betwn7to10perc) {
		this.betwn7to10perc = betwn7to10perc;
	}

	public Long getAbove10() {
		return above10;
	}

	public void setAbove10(Long above10) {
		this.above10 = above10;
	}

	public Double getAbove10perc() {
		return above10perc;
	}

	public void setAbove10perc(Double above10perc) {
		this.above10perc = above10perc;
	}

	public List<ImportantFamiliesInfoVo> getSubList() {
		return subList;
	}

	public void setSubList(List<ImportantFamiliesInfoVo> subList) {
		this.subList = subList;
	}

	public Long getBelow3Popul() {
		return below3Popul;
	}

	public void setBelow3Popul(Long below3Popul) {
		this.below3Popul = below3Popul;
	}

	public Long getBetwn4to6Popul() {
		return betwn4to6Popul;
	}

	public void setBetwn4to6Popul(Long betwn4to6Popul) {
		this.betwn4to6Popul = betwn4to6Popul;
	}

	public Long getBetwn7to10Popul() {
		return betwn7to10Popul;
	}

	public void setBetwn7to10Popul(Long betwn7to10Popul) {
		this.betwn7to10Popul = betwn7to10Popul;
	}

	public Long getAbove10Popul() {
		return above10Popul;
	}

	public void setAbove10Popul(Long above10Popul) {
		this.above10Popul = above10Popul;
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

	public boolean isDataPresent() {
		return dataPresent;
	}

	public void setDataPresent(boolean dataPresent) {
		this.dataPresent = dataPresent;
	}

	public String getTotalMaleVoters() {
		return totalMaleVoters;
	}

	public void setTotalMaleVoters(String totalMaleVoters) {
		this.totalMaleVoters = totalMaleVoters;
	}

	public String getTotalFemaleVoters() {
		return totalFemaleVoters;
	}

	public void setTotalFemaleVoters(String totalFemaleVoters) {
		this.totalFemaleVoters = totalFemaleVoters;
	}

	public String getUnKnowVoters() {
		return unKnowVoters;
	}

	public void setUnKnowVoters(String unKnowVoters) {
		this.unKnowVoters = unKnowVoters;
	}

	public Long getReportLevelId() {
		return reportLevelId;
	}

	public void setReportLevelId(Long reportLevelId) {
		this.reportLevelId = reportLevelId;
	}

	public Long getReportLevelValue() {
		return reportLevelValue;
	}

	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	
}
