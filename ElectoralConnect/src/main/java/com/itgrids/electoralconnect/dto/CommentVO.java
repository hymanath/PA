package com.itgrids.electoralconnect.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.itgrids.electoralconnect.model.CommentReply;

@SuppressWarnings("serial")
public class CommentVO implements Serializable{

	private Long commentId;
	private String comment;
	private String name;
	private Date date;
	private String announcement;
	private Long total;
	private String abused;
	private String commentedTime;
	private List<CommentVO> cmmntRplyList;
	private Long replyCount;
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAnnouncement() {
		return announcement;
	}
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getAbused() {
		return abused;
	}
	public void setAbused(String abused) {
		this.abused = abused;
	}
	public String getCommentedTime() {
		return commentedTime;
	}
	public void setCommentedTime(String commentedTime) {
		this.commentedTime = commentedTime;
	}
	public List<CommentVO> getCmmntRplyList() {
		return cmmntRplyList;
	}
	public void setCmmntRplyList(List<CommentVO> cmmntRplyList) {
		this.cmmntRplyList = cmmntRplyList;
	}
	public Long getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(Long replyCount) {
		this.replyCount = replyCount;
	}
		
	
}
