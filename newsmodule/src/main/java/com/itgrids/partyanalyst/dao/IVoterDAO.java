package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Voter;

public interface IVoterDAO extends GenericDao<Voter, Long>{

	List<Voter> findByVoterFirstNameLastNameRelativeFirstNameLastNameAndVoterIdNo(
			String firstName, String lastName, String relativeFirstName,
			String relativeLastName, String voterIDCardNo);

	public List findCastWiseVotersForMandal(Long mandalID);
	
	public List findGenderAgeWiseVotersForMandal(Long mandalID,Long minAge, Long maxAge);
	
	public List getCastCatageory();
	
	public List getCastSubatageroryDetails(String castCatageory);
	
	public List getsubCastCatageoryCastDetails(String castCatageory,String subCastCatageory);
	
	public List getCastDetails(String castCatageory);
	
	public List<Object[]> getVotersInfoForPollingStationAndElectionYear(Long boothId,
			String year);
	
	public List findVotersCastInfoByPollingStationAndElectionYear(Long hamletId, String year);
	
	public List findVotersInfoForPollingStationAndElectionYear(Long boothId,String year);
	
	public List findVotersCastInfoByConstituencyAndElectionYear(Long hamletId, String year);
	
	public List findVotersCastInfoByMandalAndElectionYear(Long hamletId, String year);
	
	public Long getVotersAboveAgeInfoForPollingstationAndElectionYear(Long panchayatId,
			String year);
	
	public Long getVotersAgeInfoForPollingstationAndElectionYear(Long panchayatId,
			String year,Long minAge,Long maxAge);
	public List<Object[]> getVotersBasicInfoByConstituencyId(Long constituencyId, String year);
	
	public List<Object[]> getVotersBasicInfoByManadalId(Long manadalId, String year);
	
	public List<Object[]> getVotersBasicInfoByPollingStationId(Long boothId, String year);
	
	public List getVotersBasicInfoByUrbanId(Long urbanId, String year);
	
	public List findVotersCastInfoByUrbanAndElectionYear(Long urbanId, String year);
	
	public List findTotalVotersCountByPollingStationAndElectionYear(Long panchayatId, String year);
	
	public List<Voter> getVoterPersonalDetailsByVoterId(Long voterId);
	
	public void flushAndclearSession();
	
	public Integer updateCasteByVoterCardId(String voterCardId, String caste);
	
	public Voter getVoterByVoterIDCardNo(String voterID);
	
	public Integer updateVoterNameAndRelativeName(String name, String relativeName,Long voterId);
	
	public List<Object[]>getVoterIdsByVoterIdCardNos(List<String> voterIdCardNosList);
	
	public List<Voter> getVoterByVoterCardNo(String voterCardId);

	public Integer updateVoterMobileNo(String mobileNo,Long voterId);
	
	public List<Object[]> getVoterIdsByCardNos(List<String> voterIdCardNosList);
	
	public List<Voter> findVoterDetailsBasedOnVoterId(Long voterId);
	
	public List<Object> getTestVotersInfo();
	public List<Object> getTestVotersInfo1();
	
	public List<Object[]> getSnoFromVoterTemp(Long constituencyId);
	//public List<Voter> getVoterInfoByVoterId(List<Long> voterIds,Integer startIndex,String order,String columnName,Integer maxRecords);
	public List<Voter> getVoterInfoByVoterId(List<Long> voterIds);
	public List<String> checkForVoterCardId(Set<String> voterCardIds);
	public Voter getVoterByVoterID(Long voterID);
}
