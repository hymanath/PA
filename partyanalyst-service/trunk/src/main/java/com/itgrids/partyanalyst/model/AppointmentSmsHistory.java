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
@Table(name="appointment_sms_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentSmsHistory extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long appointmentSmsHistoryId;
	private Long appointmentId;
	private Long appointmentStatusId;
	private Long sentBy;
	private Date sentTime;
	private Appointment appointment;
	private AppointmentStatus appointmentStatus;
	private SmsHistory smsHistory;
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_sms_history_id", unique = true, nullable = false)
	public Long getAppointmentSmsHistoryId() {
		return appointmentSmsHistoryId;
	}
	public void setAppointmentSmsHistoryId(Long appointmentSmsHistoryId) {
		this.appointmentSmsHistoryId = appointmentSmsHistoryId;
	}
	
	@Column(name = "appointment_id")
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	@Column(name = "appointment_status_id")
	public Long getAppointmentStatusId() {
		return appointmentStatusId;
	}
	public void setAppointmentStatusId(Long appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}
	
	@Column(name = "sent_by")
	public Long getSentBy() {
		return sentBy;
	}
	public void setSentBy(Long sentBy) {
		this.sentBy = sentBy;
	}
	
	@Column(name = "sent_time")
	public Date getSentTime() {
		return sentTime;
	}
	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_status_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "sms_history_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SmsHistory getSmsHistory() {
		return smsHistory;
	}
	public void setSmsHistory(SmsHistory smsHistory) {
		this.smsHistory = smsHistory;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "sent_by" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	/*private String status;
	private Date time;
	private Comment comment;
	private User user;
	
	private Long userId;
	private Long commentId;
	private String isDelete;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "abused_comments_id", unique = true, nullable = false)
	public Long getAbusedCommentsId() {
		return abusedCommentsId;
	}

	public void setAbusedCommentsId(Long abusedCommentsId) {
		this.abusedCommentsId = abusedCommentsId;
	}	
	
	@Column(name="status" ,length=20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Column(name="time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="comment_id")
	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	@Column(name = "is_delete")
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	*/
	
	
}
