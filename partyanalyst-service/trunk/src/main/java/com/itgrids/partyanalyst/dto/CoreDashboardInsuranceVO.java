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
}
