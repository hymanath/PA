package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="feedback_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FeedBackComment extends BaseModel implements java.io.Serializable  {
		/**
		 * 
		 */
	 private static final long serialVersionUID = 1L;
	 
	 // Fields
	 
	 private Long feedBackCommentId;
     private String commentType;
	 
	 /** default constructor */
		public FeedBackComment() {
		}
      
		/** full constructor */
		 
		public FeedBackComment(String commentType ){
			
			this.commentType=commentType;
		}
		
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="feedback_comment_id", unique=true, nullable=false)	
	 public Long getFeedBackCommentId() {
		return feedBackCommentId;
	}

	public void setFeedBackCommentId(Long feedBackCommentId) {
		this.feedBackCommentId = feedBackCommentId;
	}

	@Column(name="comment_type",length=100)
	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
		
	
	 