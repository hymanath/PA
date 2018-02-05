package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class NominatedPostCandidateDtlsVO {

	private Long tdpCadreId;
	private Long boardId;
	private Long departmentId;
	private Long positionId;
	private String board;
	private String department;
	private String position;
	private String memberShipId;
	private String candidateName;
	private String image;
	private String status;
	private String locationName;
	private Long boardLevelId;
	private Long nominatedPostMemberId;
	private Long nominatedPostApplicationId;
	private Long nominatedPostCandiateId;
	private Long levelValue;
	private Long addressId;
	private Long nominatedPostId;
	private Long statusId;
	private Long postCount=0l;
	private Long totalPosts=0l;
	private Long openCount=0l;
	private Long goIsuuedCount=0l;
	private Long recivedCount=0l;
	private String gender;
	private String casteCategory;
	private String Date;
	private Long totalRecevedCount=0l;
	private double totalRecePer = 0.0;
	private double openPostPer=0.0;
	private double goIssuedPer=0.0;
	private double totalPer=0.0;
	private Long finalizedPost =0l;
	private double finalizedPer =0.0;
	private Long expireOneMonth =0l;
	private Long exprireTwoMnth=0l;
	private Long expireThreeMnth =0l;
	private List<NominatedPostCandidateDtlsVO> levelList = new ArrayList<NominatedPostCandidateDtlsVO>();
	private String key;
	private Long statePosts=0l;
	private Long centralPosts=0l;
	private Long districtPosts=0l;
	private Long constncyPosts=0l;
	private Long mandalPosts=0l;
	private Long villagePosts=0l;
	private Long overalTotal =0l;
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public Long getNominatedPostMemberId() {
		return nominatedPostMemberId;
	}
	public void setNominatedPostMemberId(Long nominatedPostMemberId) {
		this.nominatedPostMemberId = nominatedPostMemberId;
	}
	public Long getNominatedPostApplicationId() {
		return nominatedPostApplicationId;
	}
	public void setNominatedPostApplicationId(Long nominatedPostApplicationId) {
		this.nominatedPostApplicationId = nominatedPostApplicationId;
	}
	public Long getNominatedPostCandiateId() {
		return nominatedPostCandiateId;
	}
	public void setNominatedPostCandiateId(Long nominatedPostCandiateId) {
		this.nominatedPostCandiateId = nominatedPostCandiateId;
	}
	public Long getBoardLevelId() {
		return boardLevelId;
	}
	public void setBoardLevelId(Long boardLevelId) {
		this.boardLevelId = boardLevelId;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public Long getNominatedPostId() {
		return nominatedPostId;
	}
	public void setNominatedPostId(Long nominatedPostId) {
		this.nominatedPostId = nominatedPostId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getPostCount() {
		return postCount;
	}
	public void setPostCount(Long postCount) {
		this.postCount = postCount;
	}
	public Long getTotalPosts() {
		return totalPosts;
	}
	public void setTotalPosts(Long totalPosts) {
		this.totalPosts = totalPosts;
	}
	public Long getOpenCount() {
		return openCount;
	}
	public void setOpenCount(Long openCount) {
		this.openCount = openCount;
	}
	public Long getGoIsuuedCount() {
		return goIsuuedCount;
	}
	public void setGoIsuuedCount(Long goIsuuedCount) {
		this.goIsuuedCount = goIsuuedCount;
	}
	public Long getRecivedCount() {
		return recivedCount;
	}
	public void setRecivedCount(Long recivedCount) {
		this.recivedCount = recivedCount;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public Long getTotalRecevedCount() {
		return totalRecevedCount;
	}
	public void setTotalRecevedCount(Long totalRecevedCount) {
		this.totalRecevedCount = totalRecevedCount;
	}
	public double getTotalRecePer() {
		return totalRecePer;
	}
	public void setTotalRecePer(double totalRecePer) {
		this.totalRecePer = totalRecePer;
	}
	public double getOpenPostPer() {
		return openPostPer;
	}
	public void setOpenPostPer(double openPostPer) {
		this.openPostPer = openPostPer;
	}
	public double getGoIssuedPer() {
		return goIssuedPer;
	}
	public void setGoIssuedPer(double goIssuedPer) {
		this.goIssuedPer = goIssuedPer;
	}
	public double getTotalPer() {
		return totalPer;
	}
	public void setTotalPer(double totalPer) {
		this.totalPer = totalPer;
	}
	public Long getFinalizedPost() {
		return finalizedPost;
	}
	public void setFinalizedPost(Long finalizedPost) {
		this.finalizedPost = finalizedPost;
	}
	public double getFinalizedPer() {
		return finalizedPer;
	}
	public void setFinalizedPer(double finalizedPer) {
		this.finalizedPer = finalizedPer;
	}
	public List<NominatedPostCandidateDtlsVO> getLevelList() {
		return levelList;
	}
	public void setLevelList(List<NominatedPostCandidateDtlsVO> levelList) {
		this.levelList = levelList;
	}
	public Long getExpireOneMonth() {
		return expireOneMonth;
	}
	public void setExpireOneMonth(Long expireOneMonth) {
		this.expireOneMonth = expireOneMonth;
	}
	public Long getExprireTwoMnth() {
		return exprireTwoMnth;
	}
	public void setExprireTwoMnth(Long exprireTwoMnth) {
		this.exprireTwoMnth = exprireTwoMnth;
	}
	public Long getExpireThreeMnth() {
		return expireThreeMnth;
	}
	public void setExpireThreeMnth(Long expireThreeMnth) {
		this.expireThreeMnth = expireThreeMnth;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Long getStatePosts() {
		return statePosts;
	}
	public void setStatePosts(Long statePosts) {
		this.statePosts = statePosts;
	}
	public Long getCentralPosts() {
		return centralPosts;
	}
	public void setCentralPosts(Long centralPosts) {
		this.centralPosts = centralPosts;
	}
	public Long getDistrictPosts() {
		return districtPosts;
	}
	public void setDistrictPosts(Long districtPosts) {
		this.districtPosts = districtPosts;
	}
	public Long getConstncyPosts() {
		return constncyPosts;
	}
	public void setConstncyPosts(Long constncyPosts) {
		this.constncyPosts = constncyPosts;
	}
	public Long getMandalPosts() {
		return mandalPosts;
	}
	public void setMandalPosts(Long mandalPosts) {
		this.mandalPosts = mandalPosts;
	}
	public Long getVillagePosts() {
		return villagePosts;
	}
	public void setVillagePosts(Long villagePosts) {
		this.villagePosts = villagePosts;
	}
	public Long getOveralTotal() {
		return overalTotal;
	}
	public void setOveralTotal(Long overalTotal) {
		this.overalTotal = overalTotal;
	}
	
}
