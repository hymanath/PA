package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="favorite_link_page")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FavoriteLinkPage extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long favoriteLinkPageId;
	private String page;
	private String url;
	private String description;
	private Long oredrNo;
	private Set<UserFavoriteLinks> userFavoriteLinks = new HashSet<UserFavoriteLinks>(0);
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "favorite_link_page_id", unique = true, nullable = false)
	public Long getFavoriteLinkPageId() {
		return favoriteLinkPageId;
	}
	public void setFavoriteLinkPageId(Long favoriteLinkPageId) {
		this.favoriteLinkPageId = favoriteLinkPageId;
	}
	
	@Column(name="page" , length = 30)
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	@Column(name="url" , length = 200)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="description" ,length = 300)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="order_no" , length = 3)
	public Long getOredrNo() {
		return oredrNo;
	}
	public void setOredrNo(Long oredrNo) {
		this.oredrNo = oredrNo;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "favoriteLinkPage")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserFavoriteLinks> getUserFavoriteLinks() {
		return userFavoriteLinks;
	}

	public void setUserFavoriteLinks(Set<UserFavoriteLinks> userFavoriteLinks) {
		this.userFavoriteLinks = userFavoriteLinks;
	}

}
