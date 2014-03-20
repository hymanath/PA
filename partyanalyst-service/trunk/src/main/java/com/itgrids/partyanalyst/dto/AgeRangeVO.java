package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;

public class AgeRangeVO {
 String panchayat;
 String hamlet;
 Long youngVoters = 0l;
 Long  age18To25 = 0l;
 Long age26to35 = 0l;
 Long age36to45 = 0l;
 Long age46to60 = 0l;
 Long above60 = 0l;
 Long delYoungVoters = 0l;
 Long  delAge18To25 = 0l;
 Long delAge26to35 = 0l;
 Long delAge36to45 = 0l;
 Long delAge46to60 = 0l;
 Long delAbove60 = 0l;
 Long youngVotersM = 0l;
 Long  age18To25M = 0l;
 Long age26to35M = 0l;
 Long age36to45M = 0l;
 Long age46to60M = 0l;
 Long above60M = 0l;
 Long youngVotersF = 0l;
 Long  age18To25F = 0l;
 Long age26to35F = 0l;
 Long age36to45F = 0l;
 Long age46to60F = 0l;
 Long above60F = 0l;
 
 Long totalVotersInBooth =0l;
 
 Long totalVotersAdded=0l;
 String totalVotersAddedPer="";
 
 Long totalVotersDeleted=0l;
 String totalVotersDeletedPer="";
 
 
 String youngVotersPer = "";
 String  age18To25Per = "";
 String age26to35Per = "";
 String age36to45Per = "";
 String age46to60Per = "";
 String above60Per = "";
 
 String delYoungVotersPer = "";
 String  delAge18To25Per = "";
 String delAge26to35Per = "";
 String delAge36to45Per = "";
 String delAge46to60Per = "";
 String delAbove60Per = "";
 
 private String queryString;
 private String partQueryString;
 private List<Object[]>   output;
 private List<Object[]>   output1;
 private List<AgeRangeVO> ageRangeVOList;
 private double ageRangePerc;

 private long tehsilId;
 private String tehsilName;
 private Long panchayatId=0l;
 private Long maleCount=0l;
 private Long femaleCount=0l;
 private Long totalMaleCount=0l;
 private Long totalFemaleCount=0l;
 private Map<String,AgeRangeVO> map;
 private String panchayatName;
 private Long hamletId=0l;
 private String hamletName;
 private Long particularAgeVotersCount=0l;
 private String ageRange;
 private Long totalCount=0l;

 private String mainHeading1;
 private String mainHeading2;
 
 
public String getMainHeading1() {
	return mainHeading1;
}
public void setMainHeading1(String mainHeading1) {
	this.mainHeading1 = mainHeading1;
}
public String getMainHeading2() {
	return mainHeading2;
}
public void setMainHeading2(String mainHeading2) {
	this.mainHeading2 = mainHeading2;
}
public String getPanchayat() {
	return panchayat;
}
public void setPanchayat(String panchayat) {
	this.panchayat = panchayat;
}
public String getHamlet() {
	return hamlet;
}
public void setHamlet(String hamlet) {
	this.hamlet = hamlet;
}
public Long getYoungVoters() {
	return youngVoters;
}
public void setYoungVoters(Long youngVoters) {
	this.youngVoters = youngVoters;
}
public Long getAge18To25() {
	return age18To25;
}
public void setAge18To25(Long age18To25) {
	this.age18To25 = age18To25;
}
public Long getAge26to35() {
	return age26to35;
}
public void setAge26to35(Long age26to35) {
	this.age26to35 = age26to35;
}
public Long getAge36to45() {
	return age36to45;
}
public void setAge36to45(Long age36to45) {
	this.age36to45 = age36to45;
}
public Long getAge46to60() {
	return age46to60;
}
public void setAge46to60(Long age46to60) {
	this.age46to60 = age46to60;
}
public Long getAbove60() {
	return above60;
}
public void setAbove60(Long above60) {
	this.above60 = above60;
}
public Long getDelYoungVoters() {
	return delYoungVoters;
}
public void setDelYoungVoters(Long delYoungVoters) {
	this.delYoungVoters = delYoungVoters;
}
public Long getDelAge18To25() {
	return delAge18To25;
}
public void setDelAge18To25(Long delAge18To25) {
	this.delAge18To25 = delAge18To25;
}
public Long getDelAge26to35() {
	return delAge26to35;
}
public void setDelAge26to35(Long delAge26to35) {
	this.delAge26to35 = delAge26to35;
}
public Long getDelAge36to45() {
	return delAge36to45;
}
public void setDelAge36to45(Long delAge36to45) {
	this.delAge36to45 = delAge36to45;
}
public Long getDelAge46to60() {
	return delAge46to60;
}
public void setDelAge46to60(Long delAge46to60) {
	this.delAge46to60 = delAge46to60;
}
public Long getDelAbove60() {
	return delAbove60;
}
public void setDelAbove60(Long delAbove60) {
	this.delAbove60 = delAbove60;
}
public Long getYoungVotersM() {
	return youngVotersM;
}
public void setYoungVotersM(Long youngVotersM) {
	this.youngVotersM = youngVotersM;
}
public Long getAge18To25M() {
	return age18To25M;
}
public void setAge18To25M(Long age18To25M) {
	this.age18To25M = age18To25M;
}
public Long getAge26to35M() {
	return age26to35M;
}
public void setAge26to35M(Long age26to35m) {
	age26to35M = age26to35m;
}
public Long getAge36to45M() {
	return age36to45M;
}
public void setAge36to45M(Long age36to45m) {
	age36to45M = age36to45m;
}
public Long getAge46to60M() {
	return age46to60M;
}
public void setAge46to60M(Long age46to60m) {
	age46to60M = age46to60m;
}
public Long getAbove60M() {
	return above60M;
}
public void setAbove60M(Long above60m) {
	above60M = above60m;
}
public Long getYoungVotersF() {
	return youngVotersF;
}
public void setYoungVotersF(Long youngVotersF) {
	this.youngVotersF = youngVotersF;
}
public Long getAge18To25F() {
	return age18To25F;
}
public void setAge18To25F(Long age18To25F) {
	this.age18To25F = age18To25F;
}
public Long getAge26to35F() {
	return age26to35F;
}
public void setAge26to35F(Long age26to35f) {
	age26to35F = age26to35f;
}
public Long getAge36to45F() {
	return age36to45F;
}
public void setAge36to45F(Long age36to45f) {
	age36to45F = age36to45f;
}
public Long getAge46to60F() {
	return age46to60F;
}
public void setAge46to60F(Long age46to60f) {
	age46to60F = age46to60f;
}
public Long getAbove60F() {
	return above60F;
}
public void setAbove60F(Long above60f) {
	above60F = above60f;
}
public Long getTotalVotersInBooth() {
	return totalVotersInBooth;
}
public void setTotalVotersInBooth(Long totalVotersInBooth) {
	this.totalVotersInBooth = totalVotersInBooth;
}
public Long getTotalVotersAdded() {
	return totalVotersAdded;
}
public void setTotalVotersAdded(Long totalVotersAdded) {
	this.totalVotersAdded = totalVotersAdded;
}
public String getTotalVotersAddedPer() {
	return totalVotersAddedPer;
}
public void setTotalVotersAddedPer(String totalVotersAddedPer) {
	this.totalVotersAddedPer = totalVotersAddedPer;
}
public Long getTotalVotersDeleted() {
	return totalVotersDeleted;
}
public void setTotalVotersDeleted(Long totalVotersDeleted) {
	this.totalVotersDeleted = totalVotersDeleted;
}
public String getTotalVotersDeletedPer() {
	return totalVotersDeletedPer;
}
public void setTotalVotersDeletedPer(String totalVotersDeletedPer) {
	this.totalVotersDeletedPer = totalVotersDeletedPer;
}
public String getYoungVotersPer() {
	return youngVotersPer;
}
public void setYoungVotersPer(String youngVotersPer) {
	this.youngVotersPer = youngVotersPer;
}
public String getAge18To25Per() {
	return age18To25Per;
}
public void setAge18To25Per(String age18To25Per) {
	this.age18To25Per = age18To25Per;
}
public String getAge26to35Per() {
	return age26to35Per;
}
public void setAge26to35Per(String age26to35Per) {
	this.age26to35Per = age26to35Per;
}
public String getAge36to45Per() {
	return age36to45Per;
}
public void setAge36to45Per(String age36to45Per) {
	this.age36to45Per = age36to45Per;
}
public String getAge46to60Per() {
	return age46to60Per;
}
public void setAge46to60Per(String age46to60Per) {
	this.age46to60Per = age46to60Per;
}
public String getAbove60Per() {
	return above60Per;
}
public void setAbove60Per(String above60Per) {
	this.above60Per = above60Per;
}
public String getDelYoungVotersPer() {
	return delYoungVotersPer;
}
public void setDelYoungVotersPer(String delYoungVotersPer) {
	this.delYoungVotersPer = delYoungVotersPer;
}
public String getDelAge18To25Per() {
	return delAge18To25Per;
}
public void setDelAge18To25Per(String delAge18To25Per) {
	this.delAge18To25Per = delAge18To25Per;
}
public String getDelAge26to35Per() {
	return delAge26to35Per;
}
public void setDelAge26to35Per(String delAge26to35Per) {
	this.delAge26to35Per = delAge26to35Per;
}
public String getDelAge36to45Per() {
	return delAge36to45Per;
}
public void setDelAge36to45Per(String delAge36to45Per) {
	this.delAge36to45Per = delAge36to45Per;
}
public String getDelAge46to60Per() {
	return delAge46to60Per;
}
public void setDelAge46to60Per(String delAge46to60Per) {
	this.delAge46to60Per = delAge46to60Per;
}
public String getDelAbove60Per() {
	return delAbove60Per;
}
public void setDelAbove60Per(String delAbove60Per) {
	this.delAbove60Per = delAbove60Per;
}
public String getQueryString() {
	return queryString;
}
public void setQueryString(String queryString) {
	this.queryString = queryString;
}
public String getPartQueryString() {
	return partQueryString;
}
public void setPartQueryString(String partQueryString) {
	this.partQueryString = partQueryString;
}
public List<Object[]> getOutput() {
	return output;
}
public void setOutput(List<Object[]> output) {
	this.output = output;
}
public List<Object[]> getOutput1() {
	return output1;
}
public void setOutput1(List<Object[]> output1) {
	this.output1 = output1;
}
public List<AgeRangeVO> getAgeRangeVOList() {
	return ageRangeVOList;
}
public void setAgeRangeVOList(List<AgeRangeVO> ageRangeVOList) {
	this.ageRangeVOList = ageRangeVOList;
}
public double getAgeRangePerc() {
	return ageRangePerc;
}
public void setAgeRangePerc(double ageRangePerc) {
	this.ageRangePerc = ageRangePerc;
}
public long getTehsilId() {
	return tehsilId;
}
public void setTehsilId(long tehsilId) {
	this.tehsilId = tehsilId;
}
public String getTehsilName() {
	return tehsilName;
}
public void setTehsilName(String tehsilName) {
	this.tehsilName = tehsilName;
}
public Long getPanchayatId() {
	return panchayatId;
}
public void setPanchayatId(Long panchayatId) {
	this.panchayatId = panchayatId;
}
public Long getMaleCount() {
	return maleCount;
}
public void setMaleCount(Long maleCount) {
	this.maleCount = maleCount;
}
public Long getFemaleCount() {
	return femaleCount;
}
public void setFemaleCount(Long femaleCount) {
	this.femaleCount = femaleCount;
}
public Long getTotalMaleCount() {
	return totalMaleCount;
}
public void setTotalMaleCount(Long totalMaleCount) {
	this.totalMaleCount = totalMaleCount;
}
public Long getTotalFemaleCount() {
	return totalFemaleCount;
}
public void setTotalFemaleCount(Long totalFemaleCount) {
	this.totalFemaleCount = totalFemaleCount;
}
public Map<String, AgeRangeVO> getMap() {
	return map;
}
public void setMap(Map<String, AgeRangeVO> map) {
	this.map = map;
}
public String getPanchayatName() {
	return panchayatName;
}
public void setPanchayatName(String panchayatName) {
	this.panchayatName = panchayatName;
}
public Long getHamletId() {
	return hamletId;
}
public void setHamletId(Long hamletId) {
	this.hamletId = hamletId;
}
public String getHamletName() {
	return hamletName;
}
public void setHamletName(String hamletName) {
	this.hamletName = hamletName;
}
public Long getParticularAgeVotersCount() {
	return particularAgeVotersCount;
}
public void setParticularAgeVotersCount(Long particularAgeVotersCount) {
	this.particularAgeVotersCount = particularAgeVotersCount;
}
public String getAgeRange() {
	return ageRange;
}
public void setAgeRange(String ageRange) {
	this.ageRange = ageRange;
}
public Long getTotalCount() {
	return totalCount;
}
public void setTotalCount(Long totalCount) {
	this.totalCount = totalCount;
}




 
}
