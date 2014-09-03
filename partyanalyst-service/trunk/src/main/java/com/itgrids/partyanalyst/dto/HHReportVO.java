package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class HHReportVO implements Serializable{
private Long panchayatId;
private String panchayatName;
private Long tehsilId;
private String tehsilName;
private Long leaderBookId;
private String bookNo;
private Long houseHoldId;
private String houseNo;
private String constituency;
private Long constituencyId;
private Long votersCount;
private Long nonVotersCount;
private Long ledersCount;
private Long houseHoldsCount;

private List<HHReportVO> votersList;
private List<HHReportVO> nonVotersList;

private List<HHReportVO> panchayatList;
private List<HHReportVO> houseHoldsList;
private List<HHReportVO> hhListFinal;
private List<HHReportVO> leadersList;
private List<Long> leaders;
private String leaderName;
private String leaderVoterId;

private String headVoterId;
private String headName;
private Long leaderId;
private Long relationShipId;

private List<HHReportVO> personsInHH;
private List<Long> houseHolds;
private List<Long> panchayatIds;


public List<Long> getPanchayatIds() {
	return panchayatIds;
}
public void setPanchayatIds(List<Long> panchayatIds) {
	this.panchayatIds = panchayatIds;
}
public List<Long> getHouseHolds() {
	return houseHolds;
}
public void setHouseHolds(List<Long> houseHolds) {
	this.houseHolds = houseHolds;
}
public List<Long> getLeaders() {
	return leaders;
}
public void setLeaders(List<Long> leaders) {
	this.leaders = leaders;
}
public Long getConstituencyId() {
	return constituencyId;
}
public void setConstituencyId(Long constituencyId) {
	this.constituencyId = constituencyId;
}
public String getConstituency() {
	return constituency;
}
public void setConstituency(String constituency) {
	this.constituency = constituency;
}
public List<HHReportVO> getLeadersList() {
	return leadersList;
}
public void setLeadersList(List<HHReportVO> leadersList) {
	this.leadersList = leadersList;
}
public Long getLeaderId() {
	return leaderId;
}
public void setLeaderId(Long leaderId) {
	this.leaderId = leaderId;
}
public List<HHReportVO> getPersonsInHH() {
return personsInHH;
}
public void setPersonsInHH(List<HHReportVO> personsInHH) {
this.personsInHH = personsInHH;
}
public Long getRelationShipId() {
return relationShipId;
}
public void setRelationShipId(Long relationShipId) {
this.relationShipId = relationShipId;
}
public List<HHReportVO> getVotersList() {
return votersList;
}
public void setVotersList(List<HHReportVO> votersList) {
this.votersList = votersList;
}
public List<HHReportVO> getNonVotersList() {
return nonVotersList;
}
public void setNonVotersList(List<HHReportVO> nonVotersList) {
this.nonVotersList = nonVotersList;
}
public Long getHouseHoldId() {
return houseHoldId;
}
public void setHouseHoldId(Long houseHoldId) {
this.houseHoldId = houseHoldId;
}
public String getHouseNo() {
return houseNo;
}
public void setHouseNo(String houseNo) {
this.houseNo = houseNo;
}
public Long getPanchayatId() {
return panchayatId;
}
public void setPanchayatId(Long panchayatId) {
this.panchayatId = panchayatId;
}
public String getPanchayatName() {
return panchayatName;
}
public void setPanchayatName(String panchayatName) {
this.panchayatName = panchayatName;
}
public Long getTehsilId() {
return tehsilId;
}
public void setTehsilId(Long tehsilId) {
this.tehsilId = tehsilId;
}
public String getTehsilName() {
return tehsilName;
}
public void setTehsilName(String tehsilName) {
this.tehsilName = tehsilName;
}
public Long getLeaderBookId() {
return leaderBookId;
}
public void setLeaderBookId(Long leaderBookId) {
this.leaderBookId = leaderBookId;
}
public String getBookNo() {
return bookNo;
}
public void setBookNo(String bookNo) {
this.bookNo = bookNo;
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
public Long getLedersCount() {
return ledersCount;
}
public void setLedersCount(Long ledersCount) {
this.ledersCount = ledersCount;
}
public Long getHouseHoldsCount() {
return houseHoldsCount;
}
public void setHouseHoldsCount(Long houseHoldsCount) {
this.houseHoldsCount = houseHoldsCount;
}
public List<HHReportVO> getPanchayatList() {
return panchayatList;
}
public void setPanchayatList(List<HHReportVO> panchayatList) {
this.panchayatList = panchayatList;
}
public List<HHReportVO> getHouseHoldsList() {
return houseHoldsList;
}
public void setHouseHoldsList(List<HHReportVO> houseHoldsList) {
this.houseHoldsList = houseHoldsList;
}
public List<HHReportVO> getHhListFinal() {
return hhListFinal;
}
public void setHhListFinal(List<HHReportVO> hhListFinal) {
this.hhListFinal = hhListFinal;
}
public String getLeaderName() {
return leaderName;
}
public void setLeaderName(String leaderName) {
this.leaderName = leaderName;
}
public String getLeaderVoterId() {
return leaderVoterId;
}
public void setLeaderVoterId(String leaderVoterId) {
this.leaderVoterId = leaderVoterId;
}
public String getHeadVoterId() {
return headVoterId;
}
public void setHeadVoterId(String headVoterId) {
this.headVoterId = headVoterId;
}
public String getHeadName() {
return headName;
}
public void setHeadName(String headName) {
this.headName = headName;
}





}
