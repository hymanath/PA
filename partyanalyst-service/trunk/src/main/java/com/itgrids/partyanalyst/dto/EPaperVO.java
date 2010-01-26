package com.itgrids.partyanalyst.dto;

public class EPaperVO {
	
	private String paperName;
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
	public String getMainUrl() {
		return mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public String getEpaperUrl() {
		return epaperUrl;
	}

	public void setEpaperUrl(String epaperUrl) {
		this.epaperUrl = epaperUrl;
	}

}
