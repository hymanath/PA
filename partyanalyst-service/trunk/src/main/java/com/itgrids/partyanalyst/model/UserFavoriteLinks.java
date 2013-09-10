package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="user_favorite_links")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserFavoriteLinks extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long userFavoriteLinksId;
	private User user;
	private FavoriteLinkPage favoriteLinkPage;
	private String url;
	private String pageTitle;
	private Date createdTime;
	
	public UserFavoriteLinks()
	{
		
	}
	public UserFavoriteLinks(Long userFavoriteLinksId,User user,FavoriteLinkPage favoriteLinkPage,String url,String pageTitle)
	{
	this.userFavoriteLinksId = userFavoriteLinksId;
	this.favoriteLinkPage = favoriteLinkPage;
	this.user = user;
	this.url = url;
	this.pageTitle = pageTitle;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_favorite_links_id", unique = true, nullable = false)
	public Long getUserFavoriteLinksId() {
		return userFavoriteLinksId;
	}
	public void setUserFavoriteLinksId(Long userFavoriteLinksId) {
		this.userFavoriteLinksId = userFavoriteLinksId;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "favorite_link_page_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FavoriteLinkPage getFavoriteLinkPage() {
		return favoriteLinkPage;
	}
	public void setFavoriteLinkPage(FavoriteLinkPage favoriteLinkPage) {
		this.favoriteLinkPage = favoriteLinkPage;
	}
	
	@Column(name="url",length = 200)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="page_title",length = 500)
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	@Column(name="created_time")
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	

}
