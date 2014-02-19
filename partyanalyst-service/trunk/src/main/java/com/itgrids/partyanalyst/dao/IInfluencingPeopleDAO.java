package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.SMSSearchCriteriaVO;
import com.itgrids.partyanalyst.model.InfluencingPeople;

public interface IInfluencingPeopleDAO extends GenericDao<InfluencingPeople, Long> {
	
	public List<InfluencingPeople> findByHamletId(Long hamletId);
	public List<InfluencingPeople> findByTehsils(String tehsilIds);	
	public List<Object[]> getDetailsByInfluencingPersonId(Long influencingPersonId);
	public Integer deleteInfluencingPeopleById(Long influencingPeopleId);
	public List<InfluencingPeople> findByStateId(Long stateId,Long userId);
	public List<InfluencingPeople> findByDistrictId(Long districtId,Long userId);
	public List<InfluencingPeople> findByConstituencyId(Long constituencyId,Long userId);
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInState(Long userId,Long stateId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInState(Long userId,Long stateId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInDistrictsByState(Long userId,Long stateId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInState(Long userId,Long stateId);
		
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInDistrict(Long userId,Long districtId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInDistrict(Long userId,Long districtId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInAssemblyConstituencies(Long userId,List<Long> contituencyIds);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInConstituenciesByDistrict(Long userId,Long districtId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInAssemblyConstituenciesGroupByConstituency(Long userId,List<Long> constituencyIds);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInDistrict(Long userId,Long districtId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInAssemblyConstituencies(Long userId,List<Long> constituencyIds);
	
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInConstituency(Long userId,Long constituencyId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInAssemblyConstituencies(Long userId,List<Long> constituencyIds);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInConstituency(Long userId,Long constituencyId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInTehsilsByConstituency(Long userId,Long constituencyId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInLocalBodysByConstituency(Long userId,Long constituencyId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInConstituencyByLocalBody(Long userId,Long constituencyId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInConstituency(Long userId,Long constituencyId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInConstituencyByLocalBody(Long userId,Long constituencyId);
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInLocalBodys(Long userId,Long localBodyId,Long constituencyId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInLocalBodys(Long userId,Long localBodyId,Long constituencyId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInWardsByLocalBody(Long userId,Long localBodyId,Long constituencyId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInBoothsByLocalBody(Long userId,Long localBodyId,Long constituencyId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInLocalBodys(Long userId,Long localBodyId,Long constituencyId);
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInTehsil(Long userId,Long tehsilId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInTehsil(Long userId,Long tehsilId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInVillagesByTehsil(Long userId,Long tehsilId);
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInBoothsByTehsil(Long userId,Long tehsilId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInTehsil(Long userId,Long tehsilId);
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInVillage(Long userId,Long hamletId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInVillage(Long userId,Long hamletId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInVillage(Long userId,Long hamletId);
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInWard(Long userId,Long wardId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInWard(Long userId,Long wardId);
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInBooth(Long userId, Long boothId);
	
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsInBooth(Long userId, Long boothId);
	
	@SuppressWarnings("unchecked")
	public List getTotalCountOfInfluencingPeopleInBoothsByWard(Long userId,Long wardId);
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressInWard(Long userId, Long wardId);
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleCountByInfluencingScope(Long userId);
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleCountByInfluencingScope(Long userId,String influencingScope);
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsByInfluencingScope(Long userId,String influencingScope,String scopeValueId);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressByInfluencingScope(Long userId,String influencingScope, String scopeValueId);
	
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleDetailsByInfluencingScope(Long userId,String influencingScope);
	@SuppressWarnings("unchecked")
	public List getTotalInfluencingPeopleAddressByInfluencingScope(Long userId,String influencingScope);
	
	@SuppressWarnings("unchecked")
	public List getInfluencingPeopleNameAndMobileNOByIds(List<Long> infIds);
	@SuppressWarnings("unchecked")
	public List getInfluencingPeopleNameAndMobileNOByIds(String infIds);
	
	@SuppressWarnings("unchecked")
	public List getInfluencingPersonDetailsById(Long influencingPersonId);
	@SuppressWarnings("unchecked")
	public List getInfluencingPersonLocationDetailsById(Long influencingPersonId);
	
	public List<Long> getinfluencingPeopleVoterId(Long voterId);
	
	public List<InfluencingPeople> getInfluencePeopleBySearch(
			InfluencingPeopleVO influencingPeopleVO, String queryString);
	
	public List<Long> findInfluencingPeopleDetails(List<Long> voterIds,Long userId);
	
	public List<Long> getInfluencingPeopleCountByLocation(Long userId,List<String> locationValue,String type);
	
	public List<InfluencingPeople> getInfluencingPeopleVoterIDs(Long userId,List<String> locationValue,String type,Integer startIndex,
			Integer maxRecords);
	public List getVotersMobileDetailsByConstituencyId(Long userId,String locationValue,String type);
	
	public List<Long> getInfluencingPeopleCountInHamlets(Long userId,List<Long> locationValue);
	
	public List<Long> getInfluencingPeopleCount(Long userId,List<Long> locationValues,String type,Long constituencyId,String partNo);
	
	public List<InfluencingPeople> getAllDetailsOfInfluencingPeople(Long userId,List<Long> ids,String type,Integer startIndex,
			Integer maxRecords,String columnName,String order);
	
	public String getPartyIdUsingVoterId(Long voterId);
	
	public List<InfluencingPeople> getInfluencingPeopleByUserAndAccessType(Long userId,String type);
	
	public List<String> getSelInfluencingScopeValues(Long userId,String type);
	
	public List getTotalInfluencingPeopleDetailsByInfluencingSelScope(Long userId,String influencingScope,String scopeValueId,int startIndex,int maxIndex);
	
	public List getTotalInfluencingPeopleAddressByInfluencingSelScope(Long userId,String influencingScope, String scopeValueId,int startIndex,int maxIndex);
	
	public List getTotalInfluencingPeopleDetailsByInfluencingSelScope(Long userId,String influencingScope,int startIndex,int maxIndex);
	
	public List getTotalInfluencingPeopleAddressByInfluencingSelScope(Long userId,String influencingScope,int startIndex,int maxIndex);
	
	public Long getTotalCountForInfluencingPeople(Long userId,String scope);
	
	public List<Object[]> getInfluencingPeopleDetailsToSendSMS(String query,SMSSearchCriteriaVO searchVO);
	
	public List<Object[]> getInfluencingPeopleDetailsToSendSMS(String query,SMSSearchCriteriaVO searchVO,String count);
	
	public List<InfluencingPeople> getInfluencingPeopleInAConstituencyForAUser(Long userId, Long constituencyId);
	public List<Long> checkVoterExistAsInfluencePeopleByVoterId(Long voterId);

}

