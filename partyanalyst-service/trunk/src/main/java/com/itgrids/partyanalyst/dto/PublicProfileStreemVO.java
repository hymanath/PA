package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PublicProfileStreemVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private String description;
	private String title;
	private String subTitle;
	private String politicalReasion;
	private String politicalDescription;
	private String userImg;
	private String img;
	private String commentedBy;
	private String existinFrom;
	private String problemTitle;
	private String name;
	private String url;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getPoliticalReasion() {
		return politicalReasion;
	}
	public void setPoliticalReasion(String politicalReasion) {
		this.politicalReasion = politicalReasion;
	}
	public String getPoliticalDescription() {
		return politicalDescription;
	}
	public void setPoliticalDescription(String politicalDescription) {
		this.politicalDescription = politicalDescription;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}
	public String getExistinFrom() {
		return existinFrom;
	}
	public void setExistinFrom(String existinFrom) {
		this.existinFrom = existinFrom;
	}
	public String getProblemTitle() {
		return problemTitle;
	}
	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
