package com.itgrids.voterdata.VO;

import java.util.List;

public class BoothVO {
	private String name;
	private String partNo;
	private String mandalName;
	private int totalVoters = 0;
	private int insertedVotes = 0;
	private int missedVotes = 0;
	private int maleVoters = 0;
	private int femaleVoters = 0;
	private int endingSerialNo = 0;
	private List<Integer> missedVotesList;
	private boolean isVotesMissed = false;
	private String fileName;
	private String constituencyId;
	private String constituencyName;
	
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean isVotesMissed() {
		return isVotesMissed;
	}
	public void setVotesMissed(boolean isVotesMissed) {
		this.isVotesMissed = isVotesMissed;
	}
	public int getMaleVoters() {
		return maleVoters;
	}
	public void setMaleVoters(int maleVoters) {
		this.maleVoters = maleVoters;
	}
	public int getFemaleVoters() {
		return femaleVoters;
	}
	public void setFemaleVoters(int femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public int getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(int totalVoters) {
		this.totalVoters = totalVoters;
	}
	public int getInsertedVotes() {
		return insertedVotes;
	}
	public void setInsertedVotes(int insertedVotes) {
		this.insertedVotes = insertedVotes;
	}
	public int getMissedVotes() {
		return missedVotes;
	}
	public void setMissedVotes(int missedVotes) {
		this.missedVotes = missedVotes;
	}
	public List<Integer> getMissedVotesList() {
		return missedVotesList;
	}
	public void setMissedVotesList(List<Integer> missedVotesList) {
		this.missedVotesList = missedVotesList;
	}
	public int getEndingSerialNo() {
		return endingSerialNo;
	}
	public void setEndingSerialNo(int endingSerialNo) {
		this.endingSerialNo = endingSerialNo;
	}
	
}
