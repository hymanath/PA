package com.itgrids.partyanalyst.dto;

public class EPaperVO implements java.io.Serializable{
	
	private String paperName;
	private String description;
	private String classification;
	private String epaperUrl;
	private String mainUrl;
	private String districtName;
	private String language;
	private String image;

	public String getLanguage() {
		return language;
	}

	public String getImage() {
		return image;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public String getClassification() {
		return classification;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}


	public String getEpaperUrl() {
		return epaperUrl;
	}

	public String getMainUrl() {
		return mainUrl;
	}

	public void setEpaperUrl(String epaperUrl) {
		this.epaperUrl = epaperUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	
}
