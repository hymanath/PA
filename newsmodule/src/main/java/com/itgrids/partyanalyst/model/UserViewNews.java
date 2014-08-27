package com.itgrids.partyanalyst.model;

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
@Table(name = "user_view_news")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserViewNews extends BaseModel{
	
	private Long userViewNewsId;
	private User user;
	private File file;
	
	public UserViewNews()
	{
		
	}
	
	public UserViewNews(Long userViewNewsId,User user,File file)
	{
		this.userViewNewsId = userViewNewsId;
		this.user = user;
		this.file = file;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_view_news_id", unique=true, nullable=false)
	public Long getUserViewNewsId() {
		return userViewNewsId;
	}

	public void setUserViewNewsId(Long userViewNewsId) {
		this.userViewNewsId = userViewNewsId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="file_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	
}
