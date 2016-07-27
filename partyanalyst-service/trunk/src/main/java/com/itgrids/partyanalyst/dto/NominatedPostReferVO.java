/**
 * @author SRAVANTH
 * July 27, 2016
 * NominatedPostReferVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SRAVANTH
 * @date July 27, 2016
 */
public class NominatedPostReferVO {

	private Long id;
	private String name;
	
	private String membershipNo;
	private String mobileNo;
	private String partyPosition;
	private Long count = 0l;
	private List<NominatedPostReferVO> subList = new ArrayList<NominatedPostReferVO>();
	
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
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPartyPosition() {
		return partyPosition;
	}
	public void setPartyPosition(String partyPosition) {
		this.partyPosition = partyPosition;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<NominatedPostReferVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NominatedPostReferVO> subList) {
		this.subList = subList;
	}
}
