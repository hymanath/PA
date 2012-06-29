package com.itgrids.partyanalyst.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long commentId;
	private String comment;
	private String isAbused;
	private Date insertedTime;
	
	private Set<OpinionPollComments> opinionPollComments = new HashSet<OpinionPollComments>(0);
	private Set<AbusedComments> abusedComments = new HashSet<AbusedComments>(0);
	
	public Comment(){}
	
	public Comment( String comment, String isAbused, Date insertedTime)
	{
		this.comment = comment;
		this.isAbused = isAbused;
		this.insertedTime = insertedTime;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id", unique=true, nullable=false)	
	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	
    @Column(name="comment" ,length = 1000)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
    
	@Column(name="is_abused" , length = 5)
	public String getIsAbused() {
		return isAbused;
	}

	public void setIsAbused(String isAbused) {
		this.isAbused = isAbused;
	}

	@Column(name="time" ,length = 10)
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "comment")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<OpinionPollComments> getOpinionPollComments() {
		return opinionPollComments;
	}

	public void setOpinionPollComments(Set<OpinionPollComments> opinionPollComments) {
		this.opinionPollComments = opinionPollComments;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "comment")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<AbusedComments> getAbusedComments() {
		return abusedComments;
	}

	public void setAbusedComments(Set<AbusedComments> abusedComments) {
		this.abusedComments = abusedComments;
	}

}
