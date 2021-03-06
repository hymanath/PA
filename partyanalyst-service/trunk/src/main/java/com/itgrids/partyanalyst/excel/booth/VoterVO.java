package com.itgrids.partyanalyst.excel.booth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.VoterDataVO;


public class VoterVO implements Serializable , Comparable<VoterVO>{
	
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String voterMiddleName;
	private String voterLastName;
	private String houseNo;
	private String relationshipType;
	private String relativeFirstName;
	private String relativeMiddleName;
	private String relativeLastName;
	private String townShip;
	private String hamlet;
	private String localArea;
	private String cast;
	private String castCatagery;
	private String castSubCatagery;
	private String voterIDCardNo;
	private String gender;
	private Long age;
	private String voterId;
	private Long totalVoters;
	private Long boothNo;
	private String status;
	private String boothName;
	private List<VoterVO> votersList = new ArrayList<VoterVO>();
	private Long voterIds;
	private Long boothId;
	private Long panchayatId;
	private String panchayatName;
	private String publicationName;
	private Long serialNo;
	private Long candidateId;
	private Long assemblyConstituencyId;
	private Long parliamentConstituencyId;
	private String electionType;
	private Long partNo;
	private List<Long> numbers;
	private Long sNo;
	private String villagesCovered;
	private Long statusId;
	private Long hamletId;
	private Long localAreaId;
	private Long movedOrRelocatedBoothId;
	private Long movedOrRelocatedPartNo;
	private String relativeName;
	private String partyName;
	private String casteName;
	private String influencingRange;
	private String influencingRegion;
	private List<Long> voterIdsList;
	private String description;
	private String color;
	private Long maleVoters = 0l;
	private Long femaleVoters = 0l;
	private List<VoterVO> flagList = new ArrayList<VoterVO>();
	private String position;
	private Long locationId;
	private String flagName;
	private Long year;
	private Integer startIndex;
	private Integer maxIndex;
	private Long constituencyId;
	private Long uvdId;
	
	private String casteNameByVoterId;
	
	private String mobileNo;
	
	private Long publicationDateId;
	private Date publicationDate;
	private Long electionId;
	private Date electionDate;
	private String type;
	private Boolean isInfluencePerson;
	private String influencePerson;
	private Boolean isCadrePerson;
	private Boolean isPoliticion;
	private List<VoterVO> categoriesList;
	private Long CategoryValuesId;
	private String name;
	private String imagePath;
	private Long tdpCadreId;
	private String memberShipNo;
	private Long enrollmentYearId;
	
	
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}

	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}

	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}

	public String getMemberShipNo() {
		return memberShipNo;
	}

	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getUvdId() {
		return uvdId;
	}

	public void setUvdId(Long uvdId) {
		this.uvdId = uvdId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(Integer maxIndex) {
		this.maxIndex = maxIndex;
	}

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<VoterVO> getFlagList() {
		return flagList;
	}

	public void setFlagList(List<VoterVO> flagList) {
		this.flagList = flagList;
	}

	public Long getMaleVoters() {
		return maleVoters;
	}

	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}

	public Long getFemaleVoters() {
		return femaleVoters;
	}

	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Long> getVoterIdsList() {
		return voterIdsList;
	}

	public void setVoterIdsList(List<Long> voterIdsList) {
		this.voterIdsList = voterIdsList;
	}

	public Long getLocalAreaId() {
		return localAreaId;
	}

	public void setLocalAreaId(Long localAreaId) {
		this.localAreaId = localAreaId;
	}

	public Long getHamletId() {
		return hamletId;
	}

	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	
	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getVillagesCovered() {
		return villagesCovered;
	}

	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}

	public Long getsNo() {
		return sNo;
	}

	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}

	public List<Long> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Long> numbers) {
		this.numbers = numbers;
	}
	
	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
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

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	
	public String getInfluencePerson() {
		return influencePerson;
	}

	public void setInfluencePerson(String influencePerson) {
		this.influencePerson = influencePerson;
	}

	public Boolean getIsInfluencePerson() {
		return isInfluencePerson;
	}

	public void setIsInfluencePerson(Boolean isInfluencePerson) {
		this.isInfluencePerson = isInfluencePerson;
	}

	public Boolean isInfluencePerson() {
		return isInfluencePerson;
	}

	public void setInfluencePerson(Boolean isInfluencePerson) {
		this.isInfluencePerson = isInfluencePerson;
	}

	public List<VoterVO> getVotersList() {
		return votersList;
	}

	public void setVotersList(List<VoterVO> votersList) {
		this.votersList = votersList;
	}

	
	public VoterVO(){
		
	}

	public VoterVO(String firstName, String voterMiddleName,
			String voterLastName, String houseNo, String relationshipType,
			String relativeFirstName, String relativeMiddleName,
			String relativeLastName, String townShip, String hamlet, String localArea,
			String cast, String castCatagery, String castSubCatagery,
			String voterIDCardNo, String gender, Long age) {
		this.firstName = firstName;
		this.voterMiddleName = voterMiddleName;
		this.voterLastName = voterLastName;
		this.houseNo = houseNo;
		this.relationshipType = relationshipType;
		this.relativeFirstName = relativeFirstName;
		this.relativeMiddleName = relativeMiddleName;
		this.relativeLastName = relativeLastName;
		this.townShip = townShip;
		this.hamlet = hamlet;
		this.localArea = localArea;
		this.cast = cast;
		this.castCatagery = castCatagery;
		this.castSubCatagery = castSubCatagery;
		this.voterIDCardNo = voterIDCardNo;
		this.gender = gender;
		this.age = age;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getVoterMiddleName() {
		return voterMiddleName;
	}

	public void setVoterMiddleName(String voterMiddleName) {
		this.voterMiddleName = voterMiddleName;
	}

	public String getVoterLastName() {
		return voterLastName;
	}

	public void setVoterLastName(String voterLastName) {
		this.voterLastName = voterLastName;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getRelativeFirstName() {
		return relativeFirstName;
	}

	public void setRelativeFirstName(String relativeFirstName) {
		this.relativeFirstName = relativeFirstName;
	}

	public String getRelativeMiddleName() {
		return relativeMiddleName;
	}

	public void setRelativeMiddleName(String relativeMiddleName) {
		this.relativeMiddleName = relativeMiddleName;
	}

	public String getRelativeLastName() {
		return relativeLastName;
	}

	public void setRelativeLastName(String relativeLastName) {
		this.relativeLastName = relativeLastName;
	}

	public String getTownShip() {
		return townShip;
	}

	public void setTownShip(String townShip) {
		this.townShip = townShip;
	}

	public String getHamlet() {
		return hamlet;
	}

	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getCastCatagery() {
		return castCatagery;
	}

	public void setCastCatagery(String castCatagery) {
		this.castCatagery = castCatagery;
	}

	public String getCastSubCatagery() {
		return castSubCatagery;
	}

	public void setCastSubCatagery(String castSubCatagery) {
		this.castSubCatagery = castSubCatagery;
	}

	public String getVoterIDCardNo() {
		return voterIDCardNo;
	}

	public void setVoterIDCardNo(String voterIDCardNo) {
		this.voterIDCardNo = voterIDCardNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getLocalArea() {
		return localArea;
	}

	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	
	public Long getBoothNo() {
		return boothNo;
	}

	public void setBoothNo(Long boothNo) {
		this.boothNo = boothNo;
	}

	public String getBoothName() {
		return boothName;
	}

	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}

	public Long getVoterIds() {
		return voterIds;
	}

	public void setVoterIds(Long voterIds) {
		this.voterIds = voterIds;
	}
	public String getCasteNameByVoterId() {
		return casteNameByVoterId;
	}

	public void setCasteNameByVoterId(String casteNameByVoterId) {
		this.casteNameByVoterId = casteNameByVoterId;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Date getElectionDate() {
		return electionDate;
	}

	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int compareTo(VoterVO obj) {
		if(obj instanceof VoterVO){
			VoterVO vo = (VoterVO) obj;
			return electionDate.compareTo(vo.getElectionDate());
		}
		else
			return 0;
	}

	public Boolean getIsCadrePerson() {
		return isCadrePerson;
	}

	public void setIsCadrePerson(Boolean isCadrePerson) {
		this.isCadrePerson = isCadrePerson;
	}

	public Boolean getIsPoliticion() {
		return isPoliticion;
	}

	public void setIsPoliticion(Boolean isPoliticion) {
		this.isPoliticion = isPoliticion;
	}
	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}


	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	
	public Long getAssemblyConstituencyId() {
		return assemblyConstituencyId;
	}
	public void setAssemblyConstituencyId(Long assemblyConstituencyId) {
		this.assemblyConstituencyId = assemblyConstituencyId;
	}
	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}
	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public Long getPartNo() {
		return partNo;
	}

	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}


	public Long getMovedOrRelocatedBoothId() {
		return movedOrRelocatedBoothId;
	}

	public void setMovedOrRelocatedBoothId(Long movedOrRelocatedBoothId) {
		this.movedOrRelocatedBoothId = movedOrRelocatedBoothId;
	}

	public Long getMovedOrRelocatedPartNo() {
		return movedOrRelocatedPartNo;
	}

	public void setMovedOrRelocatedPartNo(Long movedOrRelocatedPartNo) {
		this.movedOrRelocatedPartNo = movedOrRelocatedPartNo;
	}

	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getCasteName() {
		return casteName;
	}

	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}

	public List<VoterVO> getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(List<VoterVO> categoriesList) {
		this.categoriesList = categoriesList;
	}

	public Long getCategoryValuesId() {
		return CategoryValuesId;
	}

	public void setCategoryValuesId(Long categoryValuesId) {
		CategoryValuesId = categoryValuesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfluencingRange() {
		return influencingRange;
	}

	public void setInfluencingRange(String influencingRange) {
		this.influencingRange = influencingRange;
	}

	public String getInfluencingRegion() {
		return influencingRegion;
	}

	public void setInfluencingRegion(String influencingRegion) {
		this.influencingRegion = influencingRegion;
	}
	
	
	
}
