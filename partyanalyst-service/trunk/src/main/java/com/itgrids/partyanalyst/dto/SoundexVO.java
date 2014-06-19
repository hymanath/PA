package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class SoundexVO {
	
	private Long id;
	private Long voterId;
	private String voterIDCardNo;
	private String name;
	private String relationShipType;
	private String relativeName;
	private Long age = 0L;
	private String gender;
	private Long exactMatchCount = 0L;
	private Long soundexCount = 0L;
	private boolean ageMatched;
	private boolean relativeNameMatch;
	private boolean unMatched;
	private boolean split;

	public boolean isSplit() {
		return split;
	}
	public void setSplit(boolean split) {
		this.split = split;
	}
	public boolean isUnMatched() {
		return unMatched;
	}
	public void setUnMatched(boolean unMatched) {
		this.unMatched = unMatched;
	}
	private boolean genderMatch;
	
	private List<SoundexVO> exactMatchList = new ArrayList<SoundexVO>();
	private List<SoundexVO> soundexMatchList = new ArrayList<SoundexVO>();
	
	private List<SoundexVO> exactMatchList1 = new ArrayList<SoundexVO>();
	private List<SoundexVO> soundexMatchList1 = new ArrayList<SoundexVO>();
	
	
	
	public List<SoundexVO> getExactMatchList1() {
		return exactMatchList1;
	}
	public void setExactMatchList1(List<SoundexVO> exactMatchList1) {
		this.exactMatchList1 = exactMatchList1;
	}
	public List<SoundexVO> getSoundexMatchList1() {
		return soundexMatchList1;
	}
	public void setSoundexMatchList1(List<SoundexVO> soundexMatchList1) {
		this.soundexMatchList1 = soundexMatchList1;
	}
	public List<SoundexVO> getExactMatchList() {
		return exactMatchList;
	}
	public void setExactMatchList(List<SoundexVO> exactMatchList) {
		this.exactMatchList = exactMatchList;
	}
	public List<SoundexVO> getSoundexMatchList() {
		return soundexMatchList;
	}
	public void setSoundexMatchList(List<SoundexVO> soundexMatchList) {
		this.soundexMatchList = soundexMatchList;
	}
	public Long getExactMatchCount() {
		return exactMatchCount;
	}
	public void setExactMatchCount(Long exactMatchCount) {
		this.exactMatchCount = exactMatchCount;
	}
	public Long getSoundexCount() {
		return soundexCount;
	}
	public void setSoundexCount(Long soundexCount) {
		this.soundexCount = soundexCount;
	}
	public boolean isAgeMatched() {
		return ageMatched;
	}
	public void setAgeMatched(boolean ageMatched) {
		this.ageMatched = ageMatched;
	}
	
	public boolean isGenderMatch() {
		return genderMatch;
	}
	public void setGenderMatch(boolean genderMatch) {
		this.genderMatch = genderMatch;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getVoterIDCardNo() {
		return voterIDCardNo;
	}
	public void setVoterIDCardNo(String voterIDCardNo) {
		this.voterIDCardNo = voterIDCardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelationShipType() {
		return relationShipType;
	}
	public void setRelationShipType(String relationShipType) {
		this.relationShipType = relationShipType;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public boolean isRelativeNameMatch() {
		return relativeNameMatch;
	}
	public void setRelativeNameMatch(boolean relativeNameMatch) {
		this.relativeNameMatch = relativeNameMatch;
	}

	
}
