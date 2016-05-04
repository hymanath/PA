/**
 * @author Sravanth
 * Feb 22, 2016
 * AffiliatedCadreVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

/**
 * @author Sravanth
 * @date Feb 22, 2016
 */
public class AffiliatedCadreVO {

	private Long id;
	private String name;
	
	private Long tdpMemberTypeId;
	private String memberType;
	private Long count = 0L;
	private Long webCount = 0L;
	private Long tabCount = 0L;
	private Long onlineCount = 0L;
	private List<AffiliatedCadreVO> affiliatedCadreVoList;
	
	
	public Long getOnlineCount() {
		return onlineCount;
	}
	public void setOnlineCount(Long onlineCount) {
		this.onlineCount = onlineCount;
	}
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
	public Long getTdpMemberTypeId() {
		return tdpMemberTypeId;
	}
	public void setTdpMemberTypeId(Long tdpMemberTypeId) {
		this.tdpMemberTypeId = tdpMemberTypeId;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getWebCount() {
		return webCount;
	}
	public void setWebCount(Long webCount) {
		this.webCount = webCount;
	}
	public Long getTabCount() {
		return tabCount;
	}
	public void setTabCount(Long tabCount) {
		this.tabCount = tabCount;
	}
	public List<AffiliatedCadreVO> getAffiliatedCadreVoList() {
		return affiliatedCadreVoList;
	}
	public void setAffiliatedCadreVoList(
			List<AffiliatedCadreVO> affiliatedCadreVoList) {
		this.affiliatedCadreVoList = affiliatedCadreVoList;
	}
}
