/**
 * @author Sravanth
 * Feb 08, 2017
 * GovtDepartmentVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sravanth
 * @date Feb 08, 2017
 *
 */
public class GovtDepartmentVO {

	private Long id;
	private String name;
	
	private String department;
	private String mobileNo;
	private String designation;
	
	private Long commentId;
	private String comment;
	
	
	private Long departmentId;
	private Long count = 0l;
	private Long totalCount =0l;
	public List<GovtDepartmentVO> govtDepartmentVOList=new ArrayList<GovtDepartmentVO>(0);
	public List<GovtDepartmentVO> govtDepttVOList=new ArrayList<GovtDepartmentVO>(0);
	public List<GovtDepartmentVO> govtDeptList=new ArrayList<GovtDepartmentVO>(0);
	public GovtDepartmentVO  govtDepartmentVO ;
	private Double percentage = 0.0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<GovtDepartmentVO> getGovtDepartmentVOList() {
		return govtDepartmentVOList;
	}
	public void setGovtDepartmentVOList(List<GovtDepartmentVO> govtDepartmentVOList) {
		this.govtDepartmentVOList = govtDepartmentVOList;
	}
	public List<GovtDepartmentVO> getGovtDepttVOList() {
		return govtDepttVOList;
	}
	public void setGovtDepttVOList(List<GovtDepartmentVO> govtDepttVOList) {
		this.govtDepttVOList = govtDepttVOList;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<GovtDepartmentVO> getGovtDeptList() {
		return govtDeptList;
	}
	public void setGovtDeptList(List<GovtDepartmentVO> govtDeptList) {
		this.govtDeptList = govtDeptList;
	}
	public GovtDepartmentVO getGovtDepartmentVO() {
		return govtDepartmentVO;
	}
	public void setGovtDepartmentVO(GovtDepartmentVO govtDepartmentVO) {
		this.govtDepartmentVO = govtDepartmentVO;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	
	
}
