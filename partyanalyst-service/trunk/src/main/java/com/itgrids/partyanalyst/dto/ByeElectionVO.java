package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ByeElectionVO implements Serializable,Comparable<ByeElectionVO>{

	
	private Long id;
	private String name;
	private String type;
	private Long boothId;
	private Long prevBoothId;
	private String partNo;
	private String prevPartNo;
	private String boothLocation;
	private Long totalVoters = 0l;
	private Long preTotalVoters =0l;
	private Long preVotersInPresent=0l;
	private String prevPercentage;
	private Long polledVotes;
	private Long prevPolledVotes;
	private String percentage;
	List<ByeElectionVO> recognizeList;
	List<ByeElectionVO> unRecognizeList;
	public ByeElectionVO(){}
	
	private String time;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	public Long getPrevBoothId() {
		return prevBoothId;
	}
	public void setPrevBoothId(Long prevBoothId) {
		this.prevBoothId = prevBoothId;
	}
	public String getPrevPartNo() {
		return prevPartNo;
	}
	public void setPrevPartNo(String prevPartNo) {
		this.prevPartNo = prevPartNo;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	
	public String getBoothLocation() {
		return boothLocation;
	}
	public void setBoothLocation(String boothLocation) {
		this.boothLocation = boothLocation;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getPreTotalVoters() {
		return preTotalVoters;
	}
	public void setPreTotalVoters(Long preTotalVoters) {
		this.preTotalVoters = preTotalVoters;
	}
	public Long getPreVotersInPresent() {
		return preVotersInPresent;
	}
	public void setPreVotersInPresent(Long preVotersInPresent) {
		this.preVotersInPresent = preVotersInPresent;
	}
	public String getPrevPercentage() {
		return prevPercentage;
	}
	public void setPrevPercentage(String prevPercentage) {
		this.prevPercentage = prevPercentage;
	}
	public Long getPolledVotes() {
		return polledVotes;
	}
	public void setPolledVotes(Long polledVotes) {
		this.polledVotes = polledVotes;
	}
	public Long getPrevPolledVotes() {
		return prevPolledVotes;
	}
	public void setPrevPolledVotes(Long prevPolledVotes) {
		this.prevPolledVotes = prevPolledVotes;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public List<ByeElectionVO> getRecognizeList() {
		return recognizeList;
	}
	public void setRecognizeList(List<ByeElectionVO> recognizeList) {
		this.recognizeList = recognizeList;
	}
	public List<ByeElectionVO> getUnRecognizeList() {
		return unRecognizeList;
	}
	public void setUnRecognizeList(List<ByeElectionVO> unRecognizeList) {
		this.unRecognizeList = unRecognizeList;
	}
	@Override
	public int compareTo(ByeElectionVO obj) {
		if(obj instanceof ByeElectionVO){
			ByeElectionVO vo = (ByeElectionVO) obj;
			return name.compareToIgnoreCase(vo.getName());
		}
		else
			return 0;
	}
	
	
	
	
	
}
