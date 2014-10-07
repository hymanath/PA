package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsActivityVO implements Serializable{
   /**
	 * 
	 */
   private static final long serialVersionUID = 3211106698708008720L;
   private String name;
   private String date;
   private String description;
   private String font;
   private String title;
   private String titleFont;
   private String paper;
   private Long id;
   private Long count;
   private Long ttlcount = 0L;
   private List<NewsActivityVO> list= new ArrayList<NewsActivityVO>();

   private List<NewsActivityVO> districtsList =new ArrayList<NewsActivityVO>();
   private List<NewsActivityVO> acList = new ArrayList<NewsActivityVO>();
   private List<NewsActivityVO> groupList1 = new ArrayList<NewsActivityVO>();
   private List<NewsActivityVO> pcList= new ArrayList<NewsActivityVO>();
   private List<NewsActivityVO> groupList2 = new ArrayList<NewsActivityVO>();
   private List<NewsActivityVO> groupList3 = new ArrayList<NewsActivityVO>();
   private List<NewsActivityVO> categoryList = new ArrayList<NewsActivityVO>();
   
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getFont() {
		return font;
	}
	
	public void setFont(String font) {
		this.font = font;
	}
	
	public List<NewsActivityVO> getList() {
		return list;
	}
	
	public void setList(List<NewsActivityVO> list) {
		this.list = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(String titleFont) {
		this.titleFont = titleFont;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<NewsActivityVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<NewsActivityVO> districtsList) {
		this.districtsList = districtsList;
	}

	public List<NewsActivityVO> getAcList() {
		return acList;
	}

	public void setAcList(List<NewsActivityVO> acList) {
		this.acList = acList;
	}

	public List<NewsActivityVO> getPcList() {
		return pcList;
	}

	public void setPcList(List<NewsActivityVO> pcList) {
		this.pcList = pcList;
	}

	public List<NewsActivityVO> getGroupList1() {
		return groupList1;
	}

	public void setGroupList1(List<NewsActivityVO> groupList1) {
		this.groupList1 = groupList1;
	}

	public List<NewsActivityVO> getGroupList2() {
		return groupList2;
	}

	public void setGroupList2(List<NewsActivityVO> groupList2) {
		this.groupList2 = groupList2;
	}

	public List<NewsActivityVO> getGroupList3() {
		return groupList3;
	}

	public void setGroupList3(List<NewsActivityVO> groupList3) {
		this.groupList3 = groupList3;
	}

	public List<NewsActivityVO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<NewsActivityVO> categoryList) {
		this.categoryList = categoryList;
	}

	public Long getTtlcount() {
		return ttlcount;
	}

	public void setTtlcount(Long ttlcount) {
		this.ttlcount = ttlcount;
	}
   
}
