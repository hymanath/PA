package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ArticleDataVO implements Serializable{
	
	private Long    articleId;
	private String  articleTitle;
	private Long    pageNo;
	private String  articleInsertedTime;
	private Long    editionId;
	private String  editionName;
	private String  editionSource;
	private Long    editionDownloadedId;
	private String  editionDate;
	private String  imageURL;
	private String  description;
	private Double  imageWidth;
	private String  sourceUrl;
	private String  lnkdArtcls;
	private String  groupedArtcls;
	
	private Map<Long,ArticleDataVO> subMap;
	private Map<Long,ArticleDataVO> fromMap;
	private Map<Long,ArticleDataVO> toMap;
	
	private List<ArticleDataVO> subList;
	private List<ArticleDataVO> fromList;
	private List<ArticleDataVO> toList;
	
	private Long orderNo;
	private Long candidateArticleId;
	
	private String organizationName;
	private String candidateName;
	private String designation;
	private String benefit;
	private String impactLevel;
	
	private String categories;
	
	private String newsActivity;
	private String newsType;
	private String newsRelated;
	private String priority;
	private String solution;
	
	private String important;
	private String actionable;
	private String newsBulliten;
	
	private Long totalArticlesCount;
	
	private String trackLocationScope;
	private String trackLocationValue;
	private Long trackLocationValueId;
	private String trackLabelName;
	
	private String scopeLocation; 
	private Long   impactScopeId;
	
	private String publishedArticle;
	private String articleNature;
	
	private List<ArticleDataVO> groupedArticlesList;
	private List<ArticleDataVO> linkedList;
	
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public Long getPageNo() {
		return pageNo;
	}
	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}
	public String getArticleInsertedTime() {
		return articleInsertedTime;
	}
	public void setArticleInsertedTime(String articleInsertedTime) {
		this.articleInsertedTime = articleInsertedTime;
	}
	public Long getEditionId() {
		return editionId;
	}
	public void setEditionId(Long editionId) {
		this.editionId = editionId;
	}
	public String getEditionName() {
		return editionName;
	}
	public void setEditionName(String editionName) {
		this.editionName = editionName;
	}
	public Long getEditionDownloadedId() {
		return editionDownloadedId;
	}
	public void setEditionDownloadedId(Long editionDownloadedId) {
		this.editionDownloadedId = editionDownloadedId;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(Double imageWidth) {
		this.imageWidth = imageWidth;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getLnkdArtcls() {
		return lnkdArtcls;
	}
	public void setLnkdArtcls(String lnkdArtcls) {
		this.lnkdArtcls = lnkdArtcls;
	}
	public String getGroupedArtcls() {
		return groupedArtcls;
	}
	public void setGroupedArtcls(String groupedArtcls) {
		this.groupedArtcls = groupedArtcls;
	}
	public Map<Long, ArticleDataVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, ArticleDataVO> subMap) {
		this.subMap = subMap;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Map<Long, ArticleDataVO> getFromMap() {
		return fromMap;
	}
	public void setFromMap(Map<Long, ArticleDataVO> fromMap) {
		this.fromMap = fromMap;
	}
	public Map<Long, ArticleDataVO> getToMap() {
		return toMap;
	}
	public void setToMap(Map<Long, ArticleDataVO> toMap) {
		this.toMap = toMap;
	}
	public Long getCandidateArticleId() {
		return candidateArticleId;
	}
	public void setCandidateArticleId(Long candidateArticleId) {
		this.candidateArticleId = candidateArticleId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getBenefit() {
		return benefit;
	}
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	public String getImpactLevel() {
		return impactLevel;
	}
	public void setImpactLevel(String impactLevel) {
		this.impactLevel = impactLevel;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getNewsActivity() {
		return newsActivity;
	}
	public void setNewsActivity(String newsActivity) {
		this.newsActivity = newsActivity;
	}
	public List<ArticleDataVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ArticleDataVO> subList) {
		this.subList = subList;
	}
	public List<ArticleDataVO> getFromList() {
		return fromList;
	}
	public void setFromList(List<ArticleDataVO> fromList) {
		this.fromList = fromList;
	}
	public List<ArticleDataVO> getToList() {
		return toList;
	}
	public void setToList(List<ArticleDataVO> toList) {
		this.toList = toList;
	}
	public Long getTotalArticlesCount() {
		return totalArticlesCount;
	}
	public void setTotalArticlesCount(Long totalArticlesCount) {
		this.totalArticlesCount = totalArticlesCount;
	}
	public String getImportant() {
		return important;
	}

	public void setImportant(String important) {
		this.important = important;
	}

	public String getActionable() {
		return actionable;
	}

	public void setActionable(String actionable) {
		this.actionable = actionable;
	}

	public String getNewsBulliten() {
		return newsBulliten;
	}

	public void setNewsBulliten(String newsBulliten) {
		this.newsBulliten = newsBulliten;
	}
	public String getTrackLocationScope() {
		return trackLocationScope;
	}
	public void setTrackLocationScope(String trackLocationScope) {
		this.trackLocationScope = trackLocationScope;
	}
	public String getTrackLocationValue() {
		return trackLocationValue;
	}
	public void setTrackLocationValue(String trackLocationValue) {
		this.trackLocationValue = trackLocationValue;
	}
	public String getTrackLabelName() {
		return trackLabelName;
	}
	public void setTrackLabelName(String trackLabelName) {
		this.trackLabelName = trackLabelName;
	}
	public Long getTrackLocationValueId() {
		return trackLocationValueId;
	}
	public void setTrackLocationValueId(Long trackLocationValueId) {
		this.trackLocationValueId = trackLocationValueId;
	}
	public String getScopeLocation() {
		return scopeLocation;
	}
	public void setScopeLocation(String scopeLocation) {
		this.scopeLocation = scopeLocation;
	}
	public Long getImpactScopeId() {
		return impactScopeId;
	}
	public void setImpactScopeId(Long impactScopeId) {
		this.impactScopeId = impactScopeId;
	}
	public String getPublishedArticle() {
		return publishedArticle;
	}
	public void setPublishedArticle(String publishedArticle) {
		this.publishedArticle = publishedArticle;
	}
	public String getArticleNature() {
		return articleNature;
	}
	public void setArticleNature(String articleNature) {
		this.articleNature = articleNature;
	}
	public List<ArticleDataVO> getGroupedArticlesList() {
		return groupedArticlesList;
	}
	public void setGroupedArticlesList(List<ArticleDataVO> groupedArticlesList) {
		this.groupedArticlesList = groupedArticlesList;
	}
	public String getEditionSource() {
		return editionSource;
	}
	public void setEditionSource(String editionSource) {
		this.editionSource = editionSource;
	}
	public List<ArticleDataVO> getLinkedList() {
		return linkedList;
	}
	public void setLinkedList(List<ArticleDataVO> linkedList) {
		this.linkedList = linkedList;
	}
	public String getNewsRelated() {
		return newsRelated;
	}
	public void setNewsRelated(String newsRelated) {
		this.newsRelated = newsRelated;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getEditionDate() {
		return editionDate;
	}
	public void setEditionDate(String editionDate) {
		this.editionDate = editionDate;
	}
	
}
