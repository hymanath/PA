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
@Table(name="comment_reply")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommentReply extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4102287979951474267L;
	private Long commentReplyId;
	private Comment comment;
	private Long commentedOn;
		
	public CommentReply(Comment comment,Long commentedOn){
		this.comment=comment;
		this.commentedOn=commentedOn;
	}
	public CommentReply(){}
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_reply_id",unique=true,nullable=false)
	public Long getCommentReplyId() {
		return commentReplyId;
	}
	public void setCommentReplyId(Long commentReplyId) {
		this.commentReplyId = commentReplyId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	@Column(name="commented_on")
	public Long getCommentedOn() {
		return commentedOn;
	}
	public void setCommentedOn(Long commentedOn) {
		this.commentedOn = commentedOn;
	}
	
	
	
	
}
