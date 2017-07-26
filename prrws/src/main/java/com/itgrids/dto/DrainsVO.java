package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DrainsVO implements Serializable {
	
	
	
	private Long id;
	private String name;
	private Long kacha;
	private Double kachhaKM;
	private Long kachaCleaned=0l;
	private Double kachaCleanedKM=0.00;
	private Double kachaPercentage=0.00;
	private Long pakka;
	private Double pakkaKM;
	private Long pakkaCleaned=0l;
	private Double pakkaCleanedKM=0.00;
	private Double pakkaPercentage=0.00;
	private Long underground;
	private Double undergroundKM;
	private Long undergroundCleaned=0l;
	private Double undergroundCleanedKM=0.00;
	private Double undergroundPercentage=0.00;
	private Long  availability;
	private Double availabilityKM;
	private Long cleaned;
	private  Double cleanedKM;	
	private Double percentage=0.00;
	private Long districtId=0l,parliamentId=0l,assemblyId=0l;
	private String districtName="",parliamentName="",assemblyName="";
	private Long totalAvailable=0l,totalCleaned=0l,
			kachaAvailable=0l,pakkaAvailable=0l,ugAvailable=0l,ugCleaned=0l;
	private Double totalAvailableKms=0.00,totalCleanedKms=0.00,kachaAvailableKms=0.00,pakkaAvailableKms=0.00,ugAvailableKms=0.00,ugCleanedKms=0.00,ugPercentage=0.00;
	
	
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public Long getAssemblyId() {
		return assemblyId;
	}
	public void setAssemblyId(Long assemblyId) {
		this.assemblyId = assemblyId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
	}
	public String getAssemblyName() {
		return assemblyName;
	}
	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
	}
	public Double getUgPercentage() {
		return ugPercentage;
	}
	public void setUgPercentage(Double ugPercentage) {
		this.ugPercentage = ugPercentage;
	}
	public Double getUgCleanedKms() {
		return ugCleanedKms;
	}
	public void setUgCleanedKms(Double ugCleanedKms) {
		this.ugCleanedKms = ugCleanedKms;
	}
	public Long getKachaAvailable() {
		return kachaAvailable;
	}
	public void setKachaAvailable(Long kachaAvailable) {
		this.kachaAvailable = kachaAvailable;
	}
	public Double getKachaAvailableKms() {
		return kachaAvailableKms;
	}
	public void setKachaAvailableKms(Double kachaAvailableKms) {
		this.kachaAvailableKms = kachaAvailableKms;
	}
	public Long getPakkaAvailable() {
		return pakkaAvailable;
	}
	public Double getTotalAvailableKms() {
		return totalAvailableKms;
	}
	public void setTotalAvailableKms(Double totalAvailableKms) {
		this.totalAvailableKms = totalAvailableKms;
	}
	public Double getTotalCleanedKms() {
		return totalCleanedKms;
	}
	public void setTotalCleanedKms(Double totalCleanedKms) {
		this.totalCleanedKms = totalCleanedKms;
	}
	
	public Double getPakkaAvailableKms() {
		return pakkaAvailableKms;
	}
	public void setPakkaAvailableKms(Double pakkaAvailableKms) {
		this.pakkaAvailableKms = pakkaAvailableKms;
	}
	public Double getUgAvailableKms() {
		return ugAvailableKms;
	}
	public void setUgAvailableKms(Double ugAvailableKms) {
		this.ugAvailableKms = ugAvailableKms;
	}
	public Long getTotalAvailable() {
		return totalAvailable;
	}
	public void setTotalAvailable(Long totalAvailable) {
		this.totalAvailable = totalAvailable;
	}
	public Long getTotalCleaned() {
		return totalCleaned;
	}
	public void setTotalCleaned(Long totalCleaned) {
		this.totalCleaned = totalCleaned;
	}
	
	public void setPakkaAvailable(Long pakkaAvailable) {
		this.pakkaAvailable = pakkaAvailable;
	}
	public Long getUgAvailable() {
		return ugAvailable;
	}
	public void setUgAvailable(Long ugAvailable) {
		this.ugAvailable = ugAvailable;
	}
	public Long getUgCleaned() {
		return ugCleaned;
	}
	public void setUgCleaned(Long ugCleaned) {
		this.ugCleaned = ugCleaned;
	}
	
	private List<DrainsVO> countList = new ArrayList<DrainsVO>(0);
	private List<DrainsVO> distConsCuntList = new ArrayList<DrainsVO>(0);
	private List<DrainsVO> distMandalCuntList = new ArrayList<DrainsVO>(0);
	private List<DrainsVO> distMandalList = new ArrayList<DrainsVO>(0);
	private List<DrainsVO> consMandalList = new ArrayList<DrainsVO>(0);
	private List<DrainsVO> mandalVillageList = new ArrayList<DrainsVO>(0);
	
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
	public Long getKacha() {
		return kacha;
	}
	public void setKacha(Long kacha) {
		this.kacha = kacha;
	}
	public Double getKachhaKM() {
		return kachhaKM;
	}
	public void setKachhaKM(Double kachhaKM) {
		this.kachhaKM = kachhaKM;
	}
	public Long getKachaCleaned() {
		return kachaCleaned;
	}
	public void setKachaCleaned(Long kachaCleaned) {
		this.kachaCleaned = kachaCleaned;
	}
	public Double getKachaCleanedKM() {
		return kachaCleanedKM;
	}
	public void setKachaCleanedKM(Double kachaCleanedKM) {
		this.kachaCleanedKM = kachaCleanedKM;
	}
	public Double getKachaPercentage() {
		return kachaPercentage;
	}
	public void setKachaPercentage(Double kachaPercentage) {
		this.kachaPercentage = kachaPercentage;
	}
	public Long getPakka() {
		return pakka;
	}
	public void setPakka(Long pakka) {
		this.pakka = pakka;
	}
	public Double getPakkaKM() {
		return pakkaKM;
	}
	public void setPakkaKM(Double pakkaKM) {
		this.pakkaKM = pakkaKM;
	}
	public Long getPakkaCleaned() {
		return pakkaCleaned;
	}
	public void setPakkaCleaned(Long pakkaCleaned) {
		this.pakkaCleaned = pakkaCleaned;
	}
	public Double getPakkaCleanedKM() {
		return pakkaCleanedKM;
	}
	public void setPakkaCleanedKM(Double pakkaCleanedKM) {
		this.pakkaCleanedKM = pakkaCleanedKM;
	}
	public Double getPakkaPercentage() {
		return pakkaPercentage;
	}
	public void setPakkaPercentage(Double pakkaPercentage) {
		this.pakkaPercentage = pakkaPercentage;
	}
	public Long getUnderground() {
		return underground;
	}
	public void setUnderground(Long underground) {
		this.underground = underground;
	}
	public Double getUndergroundKM() {
		return undergroundKM;
	}
	public void setUndergroundKM(Double undergroundKM) {
		this.undergroundKM = undergroundKM;
	}
	public Long getUndergroundCleaned() {
		return undergroundCleaned;
	}
	public void setUndergroundCleaned(Long undergroundCleaned) {
		this.undergroundCleaned = undergroundCleaned;
	}
	public Double getUndergroundCleanedKM() {
		return undergroundCleanedKM;
	}
	public void setUndergroundCleanedKM(Double undergroundCleanedKM) {
		this.undergroundCleanedKM = undergroundCleanedKM;
	}
	public Double getUndergroundPercentage() {
		return undergroundPercentage;
	}
	public void setUndergroundPercentage(Double undergroundPercentage) {
		this.undergroundPercentage = undergroundPercentage;
	}
	public Long getAvailability() {
		return availability;
	}
	public void setAvailability(Long availability) {
		this.availability = availability;
	}
	public Double getAvailabilityKM() {
		return availabilityKM;
	}
	public void setAvailabilityKM(Double availabilityKM) {
		this.availabilityKM = availabilityKM;
	}
	public Long getCleaned() {
		return cleaned;
	}
	public void setCleaned(Long cleaned) {
		this.cleaned = cleaned;
	}
	public Double getCleanedKM() {
		return cleanedKM;
	}
	public void setCleanedKM(Double cleanedKM) {
		this.cleanedKM = cleanedKM;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	private List<DrainsVO> totalList = new ArrayList<DrainsVO>(0);
	

	
	public List<DrainsVO> getTotalList() {
		return totalList;
	}
	public void setTotalList(List<DrainsVO> totalList) {
		this.totalList = totalList;
	}
	public List<DrainsVO> getCountList() {
		return countList;
	}
	public void setCountList(List<DrainsVO> countList) {
		this.countList = countList;
	}
	public List<DrainsVO> getDistConsCuntList() {
		return distConsCuntList;
	}
	public void setDistConsCuntList(List<DrainsVO> distConsCuntList) {
		this.distConsCuntList = distConsCuntList;
	}
	public List<DrainsVO> getDistMandalCuntList() {
		return distMandalCuntList;
	}
	public void setDistMandalCuntList(List<DrainsVO> distMandalCuntList) {
		this.distMandalCuntList = distMandalCuntList;
	}
	public List<DrainsVO> getDistMandalList() {
		return distMandalList;
	}
	public void setDistMandalList(List<DrainsVO> distMandalList) {
		this.distMandalList = distMandalList;
	}
	public List<DrainsVO> getConsMandalList() {
		return consMandalList;
	}
	public void setConsMandalList(List<DrainsVO> consMandalList) {
		this.consMandalList = consMandalList;
	}
	public List<DrainsVO> getMandalVillageList() {
		return mandalVillageList;
	}
	public void setMandalVillageList(List<DrainsVO> mandalVillageList) {
		this.mandalVillageList = mandalVillageList;
	}

	
	

}