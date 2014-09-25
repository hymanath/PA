package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
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
   
   private List<NewsActivityVO> list;

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
	   
   
}
