package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartyActivitiesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7615519783966522564L;
      
	   private Long id;
	   private String name = "";
	   private String location;
	   private String title;
	   private String description;
	   private String date;
	   private boolean tileFont;
	   private boolean descFont;
	   private String keywords ="";
	   private Long count;
	   private Long partyId;
	   private List<String> keywordsList;
	   
	   private List<PartyActivitiesVO> activitiesList;
	   
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
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public boolean isTileFont() {
		return tileFont;
	}
	
	public void setTileFont(boolean tileFont) {
		this.tileFont = tileFont;
	}
	
	public boolean isDescFont() {
		return descFont;
	}
	
	public void setDescFont(boolean descFont) {
		this.descFont = descFont;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public List<PartyActivitiesVO> getActivitiesList() {
		return activitiesList;
	}

	public void setActivitiesList(List<PartyActivitiesVO> activitiesList) {
		this.activitiesList = activitiesList;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public List<String> getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(List<String> keywordsList) {
		this.keywordsList = keywordsList;
	}
	   
	   
	
}
