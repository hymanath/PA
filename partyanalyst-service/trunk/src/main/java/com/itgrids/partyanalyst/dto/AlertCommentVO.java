package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlertCommentVO implements Serializable {
	private Long statusId;
	private String status;
	private String date;
	private Long commentId;
	private String comment;
	private String cadreName;
	private List<String> nameList = new ArrayList<String>(0);
	private String userName;
	private String timeString;
	private Long LocationId;
	private String locaitonName;
	private Long count = 0l;
	private List<List<AlertCommentVO>> sublist = new ArrayList<List<AlertCommentVO>>(0);
	private List<AlertCommentVO> sublist1 = new ArrayList<AlertCommentVO>(0);
	private List<AlertCommentVO> sublist2 = new ArrayList<AlertCommentVO>(0);
	private Long orderNo;
	private Long deptId;
	private String deptName;
	private List<String> documentList = new ArrayList<String>();
	private List<String> cmtList = new ArrayList<String>();
	private List<KeyValueVO> docList = new ArrayList<KeyValueVO>(0);
	private String currentSts;
	private Long currentStsId;
	
	
	public String getCurrentSts() {
		return currentSts;
	}
	public void setCurrentSts(String currentSts) {
		this.currentSts = currentSts;
	}
	public Long getCurrentStsId() {
		return currentStsId;
	}
	public void setCurrentStsId(Long currentStsId) {
		this.currentStsId = currentStsId;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
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
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTimeString() {
		return timeString;
	}
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<AlertCommentVO> getSublist1() {
		return sublist1;
	}
	public void setSublist1(List<AlertCommentVO> sublist1) {
		this.sublist1 = sublist1;
	}
	public List<AlertCommentVO> getSublist2() {
		return sublist2;
	}
	public void setSublist2(List<AlertCommentVO> sublist2) {
		this.sublist2 = sublist2;
	}
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	public List<List<AlertCommentVO>> getSublist() {
		return sublist;
	}
	public void setSublist(List<List<AlertCommentVO>> sublist) {
		this.sublist = sublist;
	}
	public Long getLocationId() {
		return LocationId;
	}
	public void setLocationId(Long locationId) {
		LocationId = locationId;
	}
	public String getLocaitonName() {
		return locaitonName;
	}
	public void setLocaitonName(String locaitonName) {
		this.locaitonName = locaitonName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<String> getCmtList() {  
		return cmtList;
	}
	public void setCmtList(List<String> cmtList) {
		this.cmtList = cmtList;
	}
	public List<String> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<String> documentList) {
		this.documentList = documentList;
	}
	public List<KeyValueVO> getDocList() {
		return docList;
	}
	public void setDocList(List<KeyValueVO> docList) {
		this.docList = docList;
	}
	
}
