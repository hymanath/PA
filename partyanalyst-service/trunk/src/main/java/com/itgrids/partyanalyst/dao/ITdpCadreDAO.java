package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadre;

public interface ITdpCadreDAO extends GenericDao<TdpCadre, Long>{

	public List<Object[]> getRegisterCadreInfoBetweenDates(Date fromDate,Date toDate);
	
	public List<Object[]> getRegisterCadreInfoConstituencyWise();
	
	public List<Object[]> getCadreDetailsForCadreRegistratiobByconstituencId(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String villagesCovered);
	
	public List<Object[]> getRegisterCadreInfoDistrictWise();
	
	public String getLatestMemberNumber();
	
	public Long getWorkStartedConstituencyCount(String state);
	
	public Long getWorkStartedConstituencyYearCount(Long year,String state);
		
	public List<Object[]> getNewlyRegisterCadreInfo();
	
	public List<Object[]> getRecentlyRegisteredCadres();
	
	public List<Long> getCadreAvailableConstituencies(Long stateId);
	
	public List<Object[]> getCadreInfoConstituencytWise(List<Long> constituencyIds);
	
	public List<Object[]> getCadreInfoDistrictWise(List<Long> districtIds);
	
	public List<Long> getCadreAvailableDistricts(Long stateId);
	
	public Long getWorkingMembersCount(Date date);
	
	public List<TdpCadre> getVoterByVoterId(Long voterId);
	
	public Long checkRandomNoExistsOrNot(String dataSource,String randomNo);
	
	public List<Object[]> getexistringCadreInfoByLocation(String candidateName, Long constid, Long panchayatId);
	
	public List<Object[]> getCandidateDataCollectionInfo(Date fromDate,Date toDate);

	public Long checkMemberShipExistsOrNot(String randomNo);
	
	public List<Object[]> getCadreDetailsByMemberId(String memberCardNo);
	
	public List<Object[]> getPanchayatWiseCadreDetails(Long panchayatId);
	
	public Integer updateNFCCardNumberByVoterId(Long voterId , String nfcCardNo);
	
	public List<Object[]> getConstituencyWiseAgeRangeCadreCount(Long constituencyId,String ageRange);
	
	public Long getConstituencyWiseYearCount(Long constituencyId,Long enrollmentYear);
	
	public List<Object[]> getDistrictWiseAgeRangeCadreCount(Long districtId,String ageRange);
	
	public Long getDistrictWiseYearCount(Long districtId,Long enrollmentYear);
	
	public List<Object[]> getConstituencyWiseGenderCadreCount(Long constituencyId);
	
	public List<Object[]> getDistrictWiseGenderCadreCount(Long districtId);
	
	public List<Object[]> getConstituencyWiseCastCadreCount(Long constituencyId);
	
	public List<Object[]> getDistrictWiseCastCadreCount(Long districtId);
}
