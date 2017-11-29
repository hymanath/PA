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
	
	private Long catgryAServicesCount = 0L;
	private Long catgryBServicesCount = 0L;
	private Long catgryATransCount = 0L;
	private Long catgryBTransCount = 0L;
	private Long catgryAWithInSLACount = 0L;
	private Long catgryBWithInSLACount = 0L;
	private Long catgryABeyondSLACount = 0L;
	private Long catgryBBeyondSLACount = 0L;
	private Long  categoryACount = 0L;
	private Long categoryBCount = 0L;
	private String serviceName;
	private Long approved = 0L;
	private Long rejected = 0L;
	private Long revoke = 0L;
	
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
	public Long getCatgryAServicesCount() {
		return catgryAServicesCount;
	}
	public void setCatgryAServicesCount(Long catgryAServicesCount) {
		this.catgryAServicesCount = catgryAServicesCount;
	}
	public Long getCatgryBServicesCount() {
		return catgryBServicesCount;
	}
	public void setCatgryBServicesCount(Long catgryBServicesCount) {
		this.catgryBServicesCount = catgryBServicesCount;
	}
	public Long getCatgryATransCount() {
		return catgryATransCount;
	}
	public void setCatgryATransCount(Long catgryATransCount) {
		this.catgryATransCount = catgryATransCount;
	}
	public Long getCatgryBTransCount() {
		return catgryBTransCount;
	}
	public void setCatgryBTransCount(Long catgryBTransCount) {
		this.catgryBTransCount = catgryBTransCount;
	}
	public Long getCatgryAWithInSLACount() {
		return catgryAWithInSLACount;
	}
	public void setCatgryAWithInSLACount(Long catgryAWithInSLACount) {
		this.catgryAWithInSLACount = catgryAWithInSLACount;
	}
	public Long getCatgryBWithInSLACount() {
		return catgryBWithInSLACount;
	}
	public void setCatgryBWithInSLACount(Long catgryBWithInSLACount) {
		this.catgryBWithInSLACount = catgryBWithInSLACount;
	}
	public Long getCatgryABeyondSLACount() {
		return catgryABeyondSLACount;
	}
	public void setCatgryABeyondSLACount(Long catgryABeyondSLACount) {
		this.catgryABeyondSLACount = catgryABeyondSLACount;
	}
	public Long getCatgryBBeyondSLACount() {
		return catgryBBeyondSLACount;
	}
	public void setCatgryBBeyondSLACount(Long catgryBBeyondSLACount) {
		this.catgryBBeyondSLACount = catgryBBeyondSLACount;
	}
	public Long getCategoryACount() {
		return categoryACount;
	}
	public void setCategoryACount(Long categoryACount) {
		this.categoryACount = categoryACount;
	}
	public Long getCategoryBCount() {
		return categoryBCount;
	}
	public void setCategoryBCount(Long categoryBCount) {
		this.categoryBCount = categoryBCount;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Long getApproved() {
		return approved;
	}
	public void setApproved(Long approved) {
		this.approved = approved;
	}
	public Long getRejected() {
		return rejected;
	}
	public void setRejected(Long rejected) {
		this.rejected = rejected;
	}
	public Long getRevoke() {
		return revoke;
	}
	public void setRevoke(Long revoke) {
		this.revoke = revoke;
	}
	
}
