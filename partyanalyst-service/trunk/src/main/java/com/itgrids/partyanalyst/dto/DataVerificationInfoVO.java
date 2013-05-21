package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class DataVerificationInfoVO implements Serializable{

	private static final long serialVersionUID = 7082311856382280103L;
	private List<SelectOptionVO> repeatedMandalList;
	private List<SelectOptionVO> repeatedPanchayatList;
	private List<SelectOptionVO> repeatedBoothsList;
	private List<SelectOptionVO> repeatedMuncipalityList;
	private List<SelectOptionVO> repeatedWardList;
	private List<SelectOptionVO> repeatedConstituencyList;
	
	private List<SelectOptionVO> missedMandalList;
	private List<SelectOptionVO> missedPanchayatList;
	private List<SelectOptionVO> missedBoothsList;
	private List<SelectOptionVO> missedMuncipalityList;
	private List<SelectOptionVO> missedWardList;
	private List<SelectOptionVO> missedConstituencyList;
	
	private Long missedBoothsCount = 0L;
	private Long repeatedBoothsCount = 0L;
	private Long missedWardsCount = 0L;
	private Long repeatedWardsCount = 0L;
	private Long missedMandalsCount = 0L;
	private Long repeatedMandalsCount = 0L;
	private Long missedPanchayatsCount = 0L;
	private Long repeatedPanchayatsCount = 0L;
	private Long missedMuncipalitiesCount = 0L;
	private Long repeatedMuncipalitiesCount = 0L;
	private Long missedConstituencyCount = 0L;
	private Long repeatedConstituencyCount = 0L;
	
	private Long savedBoothsCount = 0L;
	private Long savedMandalsCount = 0L;
	private Long savedPanchayatsCount = 0L;
	private Long savedWardsCount = 0L;
	private Long savedMuncipalitiesCount = 0L;
	private Long savedConstituencyCount = 0L;
	
	private Long familyMandalCount = 0L;
	private Long familyPanchayatCount = 0L;
	private Long familyWardCount = 0L;
	private Long familyMuncipalityCount = 0L;
	private Long familyBoothCount = 0L;
	private Long familyConstituencyCount = 0L;
	
	private List<SelectOptionVO> familyMandalsList;
	private List<SelectOptionVO> familyPanchayatsList;
	private List<SelectOptionVO> familyWardsList;
	private List<SelectOptionVO> familyMuncipalitiesList;
	private List<SelectOptionVO> familyBoothsList;
	private List<SelectOptionVO> familyConstituencyList;
	
	private Long ageWiseMandalCount = 0L;
	private Long ageWisePanchayatCount = 0L;
	private Long ageWiseWardCount = 0L;
	private Long ageWiseMuncipalityCount = 0L;
	private Long ageWiseBoothCount = 0L;
	private Long ageWiseConstituencyCount = 0L;
	
	private List<SelectOptionVO> ageWiseMandalsList;
	private List<SelectOptionVO> ageWisePanchayatsList;
	private List<SelectOptionVO> ageWiseWardsList;
	private List<SelectOptionVO> ageWiseMuncipalitiesList;
	private List<SelectOptionVO> ageWiseBoothsList;
	private List<SelectOptionVO> ageWiseConstituencyList;
	
	private Long statusWiseMandalCount = 0L;
	private Long statusWisePanchayatCount = 0L;
	private Long statusWiseWardCount = 0L;
	private Long statusWiseMuncipalityCount = 0L;
	private Long statusWiseBoothCount = 0L;
	private Long statusWiseConstituencyCount = 0L;
	
	private List<SelectOptionVO> statusWiseMandalsList;
	private List<SelectOptionVO> statusWisePanchayatsList;
	private List<SelectOptionVO> statusWiseWardsList;
	private List<SelectOptionVO> statusWiseMuncipalitiesList;
	private List<SelectOptionVO> statusWiseBoothsList;
	private List<SelectOptionVO> statusWiseConstituencyList;
	
	private List<SelectOptionVO> modificationAgeInfoList;
	
	public List<SelectOptionVO> getRepeatedMandalList() {
		return repeatedMandalList;
	}
	public void setRepeatedMandalList(List<SelectOptionVO> repeatedMandalList) {
		this.repeatedMandalList = repeatedMandalList;
	}
	public List<SelectOptionVO> getRepeatedPanchayatList() {
		return repeatedPanchayatList;
	}
	public void setRepeatedPanchayatList(List<SelectOptionVO> repeatedPanchayatList) {
		this.repeatedPanchayatList = repeatedPanchayatList;
	}
	public List<SelectOptionVO> getRepeatedBoothsList() {
		return repeatedBoothsList;
	}
	public void setRepeatedBoothsList(List<SelectOptionVO> repeatedBoothsList) {
		this.repeatedBoothsList = repeatedBoothsList;
	}
	public List<SelectOptionVO> getRepeatedMuncipalityList() {
		return repeatedMuncipalityList;
	}
	public void setRepeatedMuncipalityList(
			List<SelectOptionVO> repeatedMuncipalityList) {
		this.repeatedMuncipalityList = repeatedMuncipalityList;
	}
	public List<SelectOptionVO> getRepeatedWardList() {
		return repeatedWardList;
	}
	public void setRepeatedWardList(List<SelectOptionVO> repeatedWardList) {
		this.repeatedWardList = repeatedWardList;
	}
	public List<SelectOptionVO> getMissedMandalList() {
		return missedMandalList;
	}
	public void setMissedMandalList(List<SelectOptionVO> missedMandalList) {
		this.missedMandalList = missedMandalList;
	}
	public List<SelectOptionVO> getMissedPanchayatList() {
		return missedPanchayatList;
	}
	public void setMissedPanchayatList(List<SelectOptionVO> missedPanchayatList) {
		this.missedPanchayatList = missedPanchayatList;
	}
	public List<SelectOptionVO> getMissedBoothsList() {
		return missedBoothsList;
	}
	public void setMissedBoothsList(List<SelectOptionVO> missedBoothsList) {
		this.missedBoothsList = missedBoothsList;
	}
	public List<SelectOptionVO> getMissedMuncipalityList() {
		return missedMuncipalityList;
	}
	public void setMissedMuncipalityList(List<SelectOptionVO> missedMuncipalityList) {
		this.missedMuncipalityList = missedMuncipalityList;
	}
	public List<SelectOptionVO> getMissedWardList() {
		return missedWardList;
	}
	public void setMissedWardList(List<SelectOptionVO> missedWardList) {
		this.missedWardList = missedWardList;
	}
	public Long getMissedBoothsCount() {
		return missedBoothsCount;
	}
	public void setMissedBoothsCount(Long missedBoothsCount) {
		this.missedBoothsCount = missedBoothsCount;
	}
	public Long getRepeatedBoothsCount() {
		return repeatedBoothsCount;
	}
	public void setRepeatedBoothsCount(Long repeatedBoothsCount) {
		this.repeatedBoothsCount = repeatedBoothsCount;
	}
	public Long getMissedWardsCount() {
		return missedWardsCount;
	}
	public void setMissedWardsCount(Long missedWardsCount) {
		this.missedWardsCount = missedWardsCount;
	}
	public Long getRepeatedWardsCount() {
		return repeatedWardsCount;
	}
	public void setRepeatedWardsCount(Long repeatedWardsCount) {
		this.repeatedWardsCount = repeatedWardsCount;
	}
	public Long getMissedMandalsCount() {
		return missedMandalsCount;
	}
	public void setMissedMandalsCount(Long missedMandalsCount) {
		this.missedMandalsCount = missedMandalsCount;
	}
	public Long getRepeatedMandalsCount() {
		return repeatedMandalsCount;
	}
	public void setRepeatedMandalsCount(Long repeatedMandalsCount) {
		this.repeatedMandalsCount = repeatedMandalsCount;
	}
	public Long getMissedPanchayatsCount() {
		return missedPanchayatsCount;
	}
	public void setMissedPanchayatsCount(Long missedPanchayatsCount) {
		this.missedPanchayatsCount = missedPanchayatsCount;
	}
	public Long getRepeatedPanchayatsCount() {
		return repeatedPanchayatsCount;
	}
	public void setRepeatedPanchayatsCount(Long repeatedPanchayatsCount) {
		this.repeatedPanchayatsCount = repeatedPanchayatsCount;
	}
	public Long getMissedMuncipalitiesCount() {
		return missedMuncipalitiesCount;
	}
	public void setMissedMuncipalitiesCount(Long missedMuncipalitiesCount) {
		this.missedMuncipalitiesCount = missedMuncipalitiesCount;
	}
	public Long getRepeatedMuncipalitiesCount() {
		return repeatedMuncipalitiesCount;
	}
	public void setRepeatedMuncipalitiesCount(Long repeatedMuncipalitiesCount) {
		this.repeatedMuncipalitiesCount = repeatedMuncipalitiesCount;
	}
	public Long getSavedBoothsCount() {
		return savedBoothsCount;
	}
	public void setSavedBoothsCount(Long savedBoothsCount) {
		this.savedBoothsCount = savedBoothsCount;
	}
	public Long getSavedMandalsCount() {
		return savedMandalsCount;
	}
	public void setSavedMandalsCount(Long savedMandalsCount) {
		this.savedMandalsCount = savedMandalsCount;
	}
	public Long getSavedPanchayatsCount() {
		return savedPanchayatsCount;
	}
	public void setSavedPanchayatsCount(Long savedPanchayatsCount) {
		this.savedPanchayatsCount = savedPanchayatsCount;
	}
	public Long getSavedWardsCount() {
		return savedWardsCount;
	}
	public void setSavedWardsCount(Long savedWardsCount) {
		this.savedWardsCount = savedWardsCount;
	}
	public Long getSavedMuncipalitiesCount() {
		return savedMuncipalitiesCount;
	}
	public void setSavedMuncipalitiesCount(Long savedMuncipalitiesCount) {
		this.savedMuncipalitiesCount = savedMuncipalitiesCount;
	}
	public Long getFamilyMandalCount() {
		return familyMandalCount;
	}
	public void setFamilyMandalCount(Long familyMandalCount) {
		this.familyMandalCount = familyMandalCount;
	}
	public Long getFamilyPanchayatCount() {
		return familyPanchayatCount;
	}
	public void setFamilyPanchayatCount(Long familyPanchayatCount) {
		this.familyPanchayatCount = familyPanchayatCount;
	}
	public Long getFamilyWardCount() {
		return familyWardCount;
	}
	public void setFamilyWardCount(Long familyWardCount) {
		this.familyWardCount = familyWardCount;
	}
	public Long getFamilyMuncipalityCount() {
		return familyMuncipalityCount;
	}
	public void setFamilyMuncipalityCount(Long familyMuncipalityCount) {
		this.familyMuncipalityCount = familyMuncipalityCount;
	}
	public Long getFamilyBoothCount() {
		return familyBoothCount;
	}
	public void setFamilyBoothCount(Long familyBoothCount) {
		this.familyBoothCount = familyBoothCount;
	}
	public List<SelectOptionVO> getFamilyMandalsList() {
		return familyMandalsList;
	}
	public void setFamilyMandalsList(List<SelectOptionVO> familyMandalsList) {
		this.familyMandalsList = familyMandalsList;
	}
	public List<SelectOptionVO> getFamilyPanchayatsList() {
		return familyPanchayatsList;
	}
	public void setFamilyPanchayatsList(List<SelectOptionVO> familyPanchayatsList) {
		this.familyPanchayatsList = familyPanchayatsList;
	}
	public List<SelectOptionVO> getFamilyWardsList() {
		return familyWardsList;
	}
	public void setFamilyWardsList(List<SelectOptionVO> familyWardsList) {
		this.familyWardsList = familyWardsList;
	}
	public List<SelectOptionVO> getFamilyMuncipalitiesList() {
		return familyMuncipalitiesList;
	}
	public void setFamilyMuncipalitiesList(
			List<SelectOptionVO> familyMuncipalitiesList) {
		this.familyMuncipalitiesList = familyMuncipalitiesList;
	}
	public List<SelectOptionVO> getFamilyBoothsList() {
		return familyBoothsList;
	}
	public void setFamilyBoothsList(List<SelectOptionVO> familyBoothsList) {
		this.familyBoothsList = familyBoothsList;
	}
	public Long getAgeWiseMandalCount() {
		return ageWiseMandalCount;
	}
	public void setAgeWiseMandalCount(Long ageWiseMandalCount) {
		this.ageWiseMandalCount = ageWiseMandalCount;
	}
	public Long getAgeWisePanchayatCount() {
		return ageWisePanchayatCount;
	}
	public void setAgeWisePanchayatCount(Long ageWisePanchayatCount) {
		this.ageWisePanchayatCount = ageWisePanchayatCount;
	}
	public Long getAgeWiseWardCount() {
		return ageWiseWardCount;
	}
	public void setAgeWiseWardCount(Long ageWiseWardCount) {
		this.ageWiseWardCount = ageWiseWardCount;
	}
	public Long getAgeWiseMuncipalityCount() {
		return ageWiseMuncipalityCount;
	}
	public void setAgeWiseMuncipalityCount(Long ageWiseMuncipalityCount) {
		this.ageWiseMuncipalityCount = ageWiseMuncipalityCount;
	}
	public Long getAgeWiseBoothCount() {
		return ageWiseBoothCount;
	}
	public void setAgeWiseBoothCount(Long ageWiseBoothCount) {
		this.ageWiseBoothCount = ageWiseBoothCount;
	}
	public List<SelectOptionVO> getAgeWiseMandalsList() {
		return ageWiseMandalsList;
	}
	public void setAgeWiseMandalsList(List<SelectOptionVO> ageWiseMandalsList) {
		this.ageWiseMandalsList = ageWiseMandalsList;
	}
	public List<SelectOptionVO> getAgeWisePanchayatsList() {
		return ageWisePanchayatsList;
	}
	public void setAgeWisePanchayatsList(List<SelectOptionVO> ageWisePanchayatsList) {
		this.ageWisePanchayatsList = ageWisePanchayatsList;
	}
	public List<SelectOptionVO> getAgeWiseWardsList() {
		return ageWiseWardsList;
	}
	public void setAgeWiseWardsList(List<SelectOptionVO> ageWiseWardsList) {
		this.ageWiseWardsList = ageWiseWardsList;
	}
	public List<SelectOptionVO> getAgeWiseMuncipalitiesList() {
		return ageWiseMuncipalitiesList;
	}
	public void setAgeWiseMuncipalitiesList(
			List<SelectOptionVO> ageWiseMuncipalitiesList) {
		this.ageWiseMuncipalitiesList = ageWiseMuncipalitiesList;
	}
	public List<SelectOptionVO> getAgeWiseBoothsList() {
		return ageWiseBoothsList;
	}
	public void setAgeWiseBoothsList(List<SelectOptionVO> ageWiseBoothsList) {
		this.ageWiseBoothsList = ageWiseBoothsList;
	}
	public Long getStatusWiseMandalCount() {
		return statusWiseMandalCount;
	}
	public void setStatusWiseMandalCount(Long statusWiseMandalCount) {
		this.statusWiseMandalCount = statusWiseMandalCount;
	}
	public Long getStatusWisePanchayatCount() {
		return statusWisePanchayatCount;
	}
	public void setStatusWisePanchayatCount(Long statusWisePanchayatCount) {
		this.statusWisePanchayatCount = statusWisePanchayatCount;
	}
	public Long getStatusWiseWardCount() {
		return statusWiseWardCount;
	}
	public void setStatusWiseWardCount(Long statusWiseWardCount) {
		this.statusWiseWardCount = statusWiseWardCount;
	}
	public Long getStatusWiseMuncipalityCount() {
		return statusWiseMuncipalityCount;
	}
	public void setStatusWiseMuncipalityCount(Long statusWiseMuncipalityCount) {
		this.statusWiseMuncipalityCount = statusWiseMuncipalityCount;
	}
	public Long getStatusWiseBoothCount() {
		return statusWiseBoothCount;
	}
	public void setStatusWiseBoothCount(Long statusWiseBoothCount) {
		this.statusWiseBoothCount = statusWiseBoothCount;
	}
	public List<SelectOptionVO> getStatusWiseMandalsList() {
		return statusWiseMandalsList;
	}
	public void setStatusWiseMandalsList(List<SelectOptionVO> statusWiseMandalsList) {
		this.statusWiseMandalsList = statusWiseMandalsList;
	}
	public List<SelectOptionVO> getStatusWisePanchayatsList() {
		return statusWisePanchayatsList;
	}
	public void setStatusWisePanchayatsList(
			List<SelectOptionVO> statusWisePanchayatsList) {
		this.statusWisePanchayatsList = statusWisePanchayatsList;
	}
	public List<SelectOptionVO> getStatusWiseWardsList() {
		return statusWiseWardsList;
	}
	public void setStatusWiseWardsList(List<SelectOptionVO> statusWiseWardsList) {
		this.statusWiseWardsList = statusWiseWardsList;
	}
	public List<SelectOptionVO> getStatusWiseMuncipalitiesList() {
		return statusWiseMuncipalitiesList;
	}
	public void setStatusWiseMuncipalitiesList(
			List<SelectOptionVO> statusWiseMuncipalitiesList) {
		this.statusWiseMuncipalitiesList = statusWiseMuncipalitiesList;
	}
	public List<SelectOptionVO> getStatusWiseBoothsList() {
		return statusWiseBoothsList;
	}
	public void setStatusWiseBoothsList(List<SelectOptionVO> statusWiseBoothsList) {
		this.statusWiseBoothsList = statusWiseBoothsList;
	}
	public List<SelectOptionVO> getRepeatedConstituencyList() {
		return repeatedConstituencyList;
	}
	public void setRepeatedConstituencyList(
			List<SelectOptionVO> repeatedConstituencyList) {
		this.repeatedConstituencyList = repeatedConstituencyList;
	}
	public List<SelectOptionVO> getMissedConstituencyList() {
		return missedConstituencyList;
	}
	public void setMissedConstituencyList(
			List<SelectOptionVO> missedConstituencyList) {
		this.missedConstituencyList = missedConstituencyList;
	}
	public Long getMissedConstituencyCount() {
		return missedConstituencyCount;
	}
	public void setMissedConstituencyCount(Long missedConstituencyCount) {
		this.missedConstituencyCount = missedConstituencyCount;
	}
	public Long getRepeatedConstituencyCount() {
		return repeatedConstituencyCount;
	}
	public void setRepeatedConstituencyCount(Long repeatedConstituencyCount) {
		this.repeatedConstituencyCount = repeatedConstituencyCount;
	}
	public Long getSavedConstituencyCount() {
		return savedConstituencyCount;
	}
	public void setSavedConstituencyCount(Long savedConstituencyCount) {
		this.savedConstituencyCount = savedConstituencyCount;
	}
	public Long getFamilyConstituencyCount() {
		return familyConstituencyCount;
	}
	public void setFamilyConstituencyCount(Long familyConstituencyCount) {
		this.familyConstituencyCount = familyConstituencyCount;
	}
	public List<SelectOptionVO> getFamilyConstituencyList() {
		return familyConstituencyList;
	}
	public void setFamilyConstituencyList(
			List<SelectOptionVO> familyConstituencyList) {
		this.familyConstituencyList = familyConstituencyList;
	}
	public Long getAgeWiseConstituencyCount() {
		return ageWiseConstituencyCount;
	}
	public void setAgeWiseConstituencyCount(Long ageWiseConstituencyCount) {
		this.ageWiseConstituencyCount = ageWiseConstituencyCount;
	}
	public List<SelectOptionVO> getAgeWiseConstituencyList() {
		return ageWiseConstituencyList;
	}
	public void setAgeWiseConstituencyList(
			List<SelectOptionVO> ageWiseConstituencyList) {
		this.ageWiseConstituencyList = ageWiseConstituencyList;
	}
	public Long getStatusWiseConstituencyCount() {
		return statusWiseConstituencyCount;
	}
	public void setStatusWiseConstituencyCount(Long statusWiseConstituencyCount) {
		this.statusWiseConstituencyCount = statusWiseConstituencyCount;
	}
	public List<SelectOptionVO> getStatusWiseConstituencyList() {
		return statusWiseConstituencyList;
	}
	public void setStatusWiseConstituencyList(
			List<SelectOptionVO> statusWiseConstituencyList) {
		this.statusWiseConstituencyList = statusWiseConstituencyList;
	}
	public List<SelectOptionVO> getModificationAgeInfoList() {
		return modificationAgeInfoList;
	}
	public void setModificationAgeInfoList(
			List<SelectOptionVO> modificationAgeInfoList) {
		this.modificationAgeInfoList = modificationAgeInfoList;
	}
	
	
	
}
