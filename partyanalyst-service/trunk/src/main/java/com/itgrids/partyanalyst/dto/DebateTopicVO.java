package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class DebateTopicVO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long partyId;
	private String party = "-";
	
	private Long candidateId;
	private String candidate= "-";
	
	private Long subjectId;
	private String subject= "-";
	
	private Double countScale;
	
	private Long channelId;
	private String channel = "-";
	
	
	private List<DebateTopicVO> subList;
	
	private Long count = 0l;
	
	private List<DebateTopicVO> top;
	private List<DebateTopicVO> weak;
	
	private Long debateId;
	private Long characterSticsId;
	private Long maxScale;

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<DebateTopicVO> getSubList() {
		return subList;
	}

	public void setSubList(List<DebateTopicVO> subList) {
		this.subList = subList;
	}

	public Double getCountScale() {
		return countScale;
	}

	public void setCountScale(Double countScale) {
		this.countScale = countScale;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<DebateTopicVO> getTop() {
		return top;
	}

	public void setTop(List<DebateTopicVO> top) {
		this.top = top;
	}

	public List<DebateTopicVO> getWeak() {
		return weak;
	}

	public void setWeak(List<DebateTopicVO> weak) {
		this.weak = weak;
	}

	public Long getDebateId() {
		return debateId;
	}

	public void setDebateId(Long debateId) {
		this.debateId = debateId;
	}

	public Long getCharacterSticsId() {
		return characterSticsId;
	}

	public void setCharacterSticsId(Long characterSticsId) {
		this.characterSticsId = characterSticsId;
	}

	public Long getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(Long maxScale) {
		this.maxScale = maxScale;
	}

}
