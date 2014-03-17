package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class ArticleVO extends ResultStatus implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String content;
	private String url;
	private String mediaSource;
	private String newsTitle;
	private String place;
	//private Date date;
	private String date;
	private int totalRecords;
	private List<ArticleVO> articleVOList;
	private String fileDateAsString;
	
	
	public String getFileDateAsString() {
		return fileDateAsString;
	}
	public void setFileDateAsString(String fileDateAsString) {
		this.fileDateAsString = fileDateAsString;
	}
	public List<ArticleVO> getArticleVOList() {
		return articleVOList;
	}
	public void setArticleVOList(List<ArticleVO> articleVOList) {
		this.articleVOList = articleVOList;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMediaSource() {
		return mediaSource;
	}
	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	/*public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}*/
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
