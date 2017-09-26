package com.itgrids.dto;

import java.util.List;

public class MeesevaDtlsVO implements Cloneable {

	private String id;
	private String name;
	private Long departmentCount;
	private Long totalCount;
	private Long grandTotalCount;
	private Long totalTransactionCount;
	private Long totalWithInSlaCount;
	private Long totalBeyondSlaCount;
	private String cateoryA;
	private String  categoryB;
	private String bApproved;
	private String  bRejected;
	private Long pendingWithinSla;
	private Long pendingBeyondSla;
	private String revoked;
	
	private List<MeesevaDtlsVO> subList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDepartmentCount() {
		return departmentCount;
	}
	public void setDepartmentCount(Long departmentCount) {
		this.departmentCount = departmentCount;
	}
	public Long getGrandTotalCount() {
		return grandTotalCount;
	}
	public void setGrandTotalCount(Long grandTotalCount) {
		this.grandTotalCount = grandTotalCount;
	}
	public Long getTotalTransactionCount() {
		return totalTransactionCount;
	}
	public void setTotalTransactionCount(Long totalTransactionCount) {
		this.totalTransactionCount = totalTransactionCount;
	}
	public List<MeesevaDtlsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<MeesevaDtlsVO> subList) {
		this.subList = subList;
	}

	public String getCateoryA() {
		return cateoryA;
	}
	public void setCateoryA(String cateoryA) {
		this.cateoryA = cateoryA;
	}
	public String getCategoryB() {
		return categoryB;
	}
	public void setCategoryB(String categoryB) {
		this.categoryB = categoryB;
	}
	public String getbApproved() {
		return bApproved;
	}
	public void setbApproved(String bApproved) {
		this.bApproved = bApproved;
	}
	public String getbRejected() {
		return bRejected;
	}
	public void setbRejected(String bRejected) {
		this.bRejected = bRejected;
	}
	public Long getPendingWithinSla() {
		return pendingWithinSla;
	}
	public void setPendingWithinSla(Long pendingWithinSla) {
		this.pendingWithinSla = pendingWithinSla;
	}
	public Long getPendingBeyondSla() {
		return pendingBeyondSla;
	}
	public void setPendingBeyondSla(Long pendingBeyondSla) {
		this.pendingBeyondSla = pendingBeyondSla;
	}
	public String getRevoked() {
		return revoked;
	}
	public void setRevoked(String revoked) {
		this.revoked = revoked;
	}
	public Long getTotalWithInSlaCount() {
		return totalWithInSlaCount;
	}
	public void setTotalWithInSlaCount(Long totalWithInSlaCount) {
		this.totalWithInSlaCount = totalWithInSlaCount;
	}
	public Long getTotalBeyondSlaCount() {
		return totalBeyondSlaCount;
	}
	public void setTotalBeyondSlaCount(Long totalBeyondSlaCount) {
		this.totalBeyondSlaCount = totalBeyondSlaCount;
	}
	public Object clone() throws CloneNotSupportedException {
	        return super.clone();
   }
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
}
