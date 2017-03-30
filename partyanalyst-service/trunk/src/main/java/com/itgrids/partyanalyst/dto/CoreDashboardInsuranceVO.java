/**
 * @author SRAVANTH
 * MAR 24, 2017
 * CoreDashboardInsuranceVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SRAVANTH
 * @date MAR 24, 2017
 */
public class CoreDashboardInsuranceVO {

	private Long id;
	private String name;
	private Long count = 0l;
	private Long totalCount = 0l;
	private Long deathCount = 0l;
	private Long hospitalizationCount = 0l;
	private List<CoreDashboardInsuranceVO> subList = new ArrayList<CoreDashboardInsuranceVO>();
	private CoreDashboardInsuranceVO coreDashboardInsuranceVO = new CoreDashboardInsuranceVO();
	private Double percentage = 0.00;
	private Long totalCategroyCount = 0L;//death and hospitalization
	private Long totalStatusCount = 0L;
	private Long totalAmount = 0L;
	private List<InsuranceStatusVO> insuranceStatusVOs;
	
	private String remarks;
	private String updatedTime;
	private String subject;
	private String raisedDate;
	private String image;
	private List<CoreDashboardInsuranceVO> overViewList = new ArrayList<CoreDashboardInsuranceVO>();
	private List<CoreDashboardInsuranceVO> deathList = new ArrayList<CoreDashboardInsuranceVO>();
	private List<CoreDashboardInsuranceVO> hospitalizationList = new ArrayList<CoreDashboardInsuranceVO>();
	private Long amount = 0l;
	
	
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<CoreDashboardInsuranceVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CoreDashboardInsuranceVO> subList) {
		this.subList = subList;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getDeathCount() {
		return deathCount;
	}
	public void setDeathCount(Long deathCount) {
		this.deathCount = deathCount;
	}
	public Long getHospitalizationCount() {
		return hospitalizationCount;
	}
	public void setHospitalizationCount(Long hospitalizationCount) {
		this.hospitalizationCount = hospitalizationCount;
	}
	public CoreDashboardInsuranceVO getCoreDashboardInsuranceVO() {
		return coreDashboardInsuranceVO;
	}
	public void setCoreDashboardInsuranceVO(
			CoreDashboardInsuranceVO coreDashboardInsuranceVO) {
		this.coreDashboardInsuranceVO = coreDashboardInsuranceVO;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public Long getTotalCategroyCount() {
		return totalCategroyCount;
	}
	public void setTotalCategroyCount(Long totalCategroyCount) {
		this.totalCategroyCount = totalCategroyCount;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<InsuranceStatusVO> getInsuranceStatusVOs() {
		if(insuranceStatusVOs == null){
			insuranceStatusVOs = new ArrayList<InsuranceStatusVO>();
		}
		return insuranceStatusVOs;
	}
	public Long getTotalStatusCount() {
		return totalStatusCount;
	}
	public void setTotalStatusCount(Long totalStatusCount) {
		this.totalStatusCount = totalStatusCount;
	}
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRaisedDate() {
		return raisedDate;
	}
	public void setRaisedDate(String raisedDate) {
		this.raisedDate = raisedDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<CoreDashboardInsuranceVO> getOverViewList() {
		return overViewList;
	}
	public void setOverViewList(List<CoreDashboardInsuranceVO> overViewList) {
		this.overViewList = overViewList;
	}
	public List<CoreDashboardInsuranceVO> getDeathList() {
		return deathList;
	}
	public void setDeathList(List<CoreDashboardInsuranceVO> deathList) {
		this.deathList = deathList;
	}
	public List<CoreDashboardInsuranceVO> getHospitalizationList() {
		return hospitalizationList;
	}
	public void setHospitalizationList(
			List<CoreDashboardInsuranceVO> hospitalizationList) {
		this.hospitalizationList = hospitalizationList;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
