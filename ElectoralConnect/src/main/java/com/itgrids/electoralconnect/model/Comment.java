package com.itgrids.electoralconnect.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4102287979951474267L;
	private Long commentId;
	private String comment;
	private User user;
	private String isDelete;
	private Date time;
	private Announcements announcements;
	
	public Comment(String comment,User user,String isDelete,Date time,Announcements announcements){
		this.comment=comment;
		this.user=user;
		this.isDelete=isDelete;
		this.time=time;
		this.announcements=announcements;
	}
	public Comment(){}
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id",unique=true,nullable=false)
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	
	@Column(name="comment",length=200)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name="time")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "announcement_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Announcements getAnnouncements() {
		return announcements;
	}
	public void setAnnouncements(Announcements announcements) {
		this.announcements = announcements;
	}
	
	
	
}
