package com.itgrids.partyanalyst.dto;

import java.util.List;

public class HouseHoldsVO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String familiRange ;
	private String familyCount;
	private String familyPercentage;
	private List<HouseHoldsVO> houseHoldsVOList = null;
	private String message;
	private String calcMessage;
	private String heading;
	
	private Long boothId;
	private Long partNo;
	private boolean enableBooth;
	private boolean enableBook;
	
	private boolean disableBooth;
	private boolean disableBook;
	
	private String leaderName;
	private String voterId;
	
	private Long leaderId;
	
	private List<HouseHoldsVO> boothsInConstituency;
	private List<HouseHoldsVO> booksInConstituency;
	private List<Long> booksInConsti;
	private List<Long> boothsOfLeader;
	private List<Long> booksOfLeader;
	
	private List<HouseHoldsVO> leadersOfConsti;
	
	private String statusOfBook;
	private Long bookNo;
	private Long bookId;
	private Long votersCount;
	private Long nonVotersCount;
	private Long familiesCount;
	
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public boolean isDisableBooth() {
		return disableBooth;
	}
	public void setDisableBooth(boolean disableBooth) {
		this.disableBooth = disableBooth;
	}
	public boolean isDisableBook() {
		return disableBook;
	}
	public void setDisableBook(boolean disableBook) {
		this.disableBook = disableBook;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public List<HouseHoldsVO> getBooksInConstituency() {
		return booksInConstituency;
	}
	public void setBooksInConstituency(List<HouseHoldsVO> booksInConstituency) {
		this.booksInConstituency = booksInConstituency;
	}
	public boolean isEnableBook() {
		return enableBook;
	}
	public void setEnableBook(boolean enableBook) {
		this.enableBook = enableBook;
	}
	public boolean isEnableBooth() {
		return enableBooth;
	}
	public void setEnableBooth(boolean enableBooth) {
		this.enableBooth = enableBooth;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getPartNo() {
		return partNo;
	}
	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}
	public List<HouseHoldsVO> getBoothsInConstituency() {
		return boothsInConstituency;
	}
	public void setBoothsInConstituency(List<HouseHoldsVO> boothsInConstituency) {
		this.boothsInConstituency = boothsInConstituency;
	}
	
	public List<HouseHoldsVO> getLeadersOfConsti() {
		return leadersOfConsti;
	}
	public void setLeadersOfConsti(List<HouseHoldsVO> leadersOfConsti) {
		this.leadersOfConsti = leadersOfConsti;
	}
	public String getStatusOfBook() {
		return statusOfBook;
	}
	public void setStatusOfBook(String statusOfBook) {
		this.statusOfBook = statusOfBook;
	}
	
	public List<Long> getBooksInConsti() {
		return booksInConsti;
	}
	public void setBooksInConsti(List<Long> booksInConsti) {
		this.booksInConsti = booksInConsti;
	}
	public List<Long> getBoothsOfLeader() {
		return boothsOfLeader;
	}
	public void setBoothsOfLeader(List<Long> boothsOfLeader) {
		this.boothsOfLeader = boothsOfLeader;
	}
	public List<Long> getBooksOfLeader() {
		return booksOfLeader;
	}
	public void setBooksOfLeader(List<Long> booksOfLeader) {
		this.booksOfLeader = booksOfLeader;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getCalcMessage() {
		return calcMessage;
	}
	public void setCalcMessage(String calcMessage) {
		this.calcMessage = calcMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFamiliRange() {
		return familiRange;
	}
	public void setFamiliRange(String familiRange) {
		this.familiRange = familiRange;
	}
	public String getFamilyCount() {
		return familyCount;
	}
	public void setFamilyCount(String familyCount) {
		this.familyCount = familyCount;
	}
	public String getFamilyPercentage() {
		return familyPercentage;
	}
	public void setFamilyPercentage(String familyPercentage) {
		this.familyPercentage = familyPercentage;
	}
	public List<HouseHoldsVO> getHouseHoldsVOList() {
		return houseHoldsVOList;
	}
	public void setHouseHoldsVOList(List<HouseHoldsVO> houseHoldsVOList) {
		this.houseHoldsVOList = houseHoldsVOList;
	}
	public Long getVotersCount() {
		return votersCount;
	}
	public void setVotersCount(Long votersCount) {
		this.votersCount = votersCount;
	}
	public Long getNonVotersCount() {
		return nonVotersCount;
	}
	public void setNonVotersCount(Long nonVotersCount) {
		this.nonVotersCount = nonVotersCount;
	}
	public Long getFamiliesCount() {
		return familiesCount;
	}
	public void setFamiliesCount(Long familiesCount) {
		this.familiesCount = familiesCount;
	}

	
}
