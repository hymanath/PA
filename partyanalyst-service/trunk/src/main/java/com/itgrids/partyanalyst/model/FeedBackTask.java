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
@Table(name="feedback_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FeedBackTask extends BaseModel implements java.io.Serializable  {
		/**
		 * 
		 */
	 private static final long serialVersionUID = 1L;
	 
	 // Fields
	 
	 private Long feedBackTaskId;
	
	 private String feedBackTaskName;
	 
	 /** default constructor */
		public FeedBackTask() {
		}
      
		/** default constructor */
		 
		public FeedBackTask(Long feedBackTaskId,String feedBackTaskName ){
			
			this.feedBackTaskId=feedBackTaskId;
			this.feedBackTaskName=feedBackTaskName;
			
		}
		
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="feedback_task_id", unique=true, nullable=false)	
	 public Long getFeedBackTaskId() {
		return feedBackTaskId;
	}
	public void setFeedBackTaskId(Long feedBackTaskId) {
		this.feedBackTaskId = feedBackTaskId;
	}
	
	@Column(name="feedback_task_name", length=100)
	public String getFeedBackTaskName() {
		return feedBackTaskName;
	}
	public void setFeedBackTaskName(String feedBackTaskName) {
		this.feedBackTaskName = feedBackTaskName;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	 
}