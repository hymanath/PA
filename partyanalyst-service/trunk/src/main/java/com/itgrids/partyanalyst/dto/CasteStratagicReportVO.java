package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CasteStratagicReportVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private String caste;
	private String casteCategory;
	private Long totalCasteVoters;
	private Long maleCasteVoters;
	private Long femaleCasteVoters;
	private Float castePercentage;
	private String message;
	private String heading;
	
	private List<CasteStratagicReportVO> strategicVOList = new ArrayList<CasteStratagicReportVO>();

	
	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}

	public Long getTotalCasteVoters() {
		return totalCasteVoters;
	}

	public void setTotalCasteVoters(Long totalCasteVoters) {
		this.totalCasteVoters = totalCasteVoters;
	}

	public Long getMaleCasteVoters() {
		return maleCasteVoters;
	}

	public void setMaleCasteVoters(Long maleCasteVoters) {
		this.maleCasteVoters = maleCasteVoters;
	}

	public Long getFemaleCasteVoters() {
		return femaleCasteVoters;
	}

	public void setFemaleCasteVoters(Long femaleCasteVoters) {
		this.femaleCasteVoters = femaleCasteVoters;
	}

	
	public Float getCastePercentage() {
		return castePercentage;
	}

	public void setCastePercentage(Float castePercentage) {
		this.castePercentage = castePercentage;
	}

	public List<CasteStratagicReportVO> getStrategicVOList() {
		return strategicVOList;
	}

	public void setStrategicVOList(List<CasteStratagicReportVO> strategicVOList) {
		this.strategicVOList = strategicVOList;
	}
	
	
}
