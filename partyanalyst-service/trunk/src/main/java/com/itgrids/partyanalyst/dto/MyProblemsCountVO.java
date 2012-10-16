package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class MyProblemsCountVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -186417923453363126L;
	private Long myPrivateProbCount;
	private Long myPublicProbCount;
	private Long takenByMeCount;
	private Long commntByMecount;
	private Long allPublicProbcount;
	private Long postedByMeCount;
	private Long userId;
	private String userType;
	
	
	public Long getMyPrivateProbCount() {
		return myPrivateProbCount;
	}
	public void setMyPrivateProbCount(Long myPrivateProbCount) {
		this.myPrivateProbCount = myPrivateProbCount;
	}
	public Long getMyPublicProbCount() {
		return myPublicProbCount;
	}
	public void setMyPublicProbCount(Long myPublicProbCount) {
		this.myPublicProbCount = myPublicProbCount;
	}
	public Long getTakenByMeCount() {
		return takenByMeCount;
	}
	public void setTakenByMeCount(Long takenByMeCount) {
		this.takenByMeCount = takenByMeCount;
	}
	public Long getCommntByMecount() {
		return commntByMecount;
	}
	public void setCommntByMecount(Long commntByMecount) {
		this.commntByMecount = commntByMecount;
	}
	public Long getAllPublicProbcount() {
		return allPublicProbcount;
	}
	public void setAllPublicProbcount(Long allPublicProbcount) {
		this.allPublicProbcount = allPublicProbcount;
	}
	public Long getPostedByMeCount() {
		return postedByMeCount;
	}
	public void setPostedByMeCount(Long postedByMeCount) {
		this.postedByMeCount = postedByMeCount;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
