package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterAgeInfo;

public interface IVoterAgeInfoDAO extends GenericDao<VoterAgeInfo, Long>{
	
	public List<VoterAgeInfo> getVoterAgeInfoByPublicationDateAndReportLevelId(Long reportLevelId, Long reportLevelValue, Long publicationDateId,Long constituencyId);
	
	public List<VoterAgeInfo> getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(Long reportLevelId, Long reportLevelValue, Long publicationDateId, Long ageRangeId);
	
	public Long getVoterInfoIdByReportLevelValueAndReportLevelId(Long reportLevelId, Long reportLevelValue, Long ageRangeId);
	
	public Integer deleteVoterAgeInfoByReportLevelIdAndReportLevelValue(Long reportLevelId, List<Long> reportLevelValue, Long publicationDateId);

	public List<VoterAgeInfo> getAgewiseVoterDetailsInAllRange(Long reportLevelId, Set<Long> reportLevelValue, Long publicationDateId,Long constituencyId);
	
	public Integer deleteVoterAgeInfoByConstituencyIdAndReportLevelValue(Long reportLevelId,Long constituencyId, Long publicationDateId);
	
	public Integer deleteVoterAgeInfoByConstituencyId(Long constituencyId, Long publicationDateId);
	
	public List<Long> getVoterAgeInfoByConstituencyId(Long constituencyId, Long publicationDateId,Long voterReportLevel);
	
	public List<Object[]> getVoterAgeDetails(Long constituencyId, Long publicationDateId,Long voterReportLevel);
	
	public List<VoterAgeInfo> getVoterAgeInfoList(Long constituencyId);
	
	public List<Object[]> getPanchayatWiseVoterDetailsForSuggestiveModel(Long constituencyId,Long publicationId,Long reportLevelId,Long ageRangeId);
	
	public List<Object[]> getVoterAgeInfoListByconstituency(Long constituencyId,Long publicationDateId);
	
	
	public List<Object[]> getTotalVotersBasedOnAConstituency(List<Long> constituencyIds,Long publicationDateId);
	public List<Object[]> getGenderWiseVoterDetailsByConstituency(List<Long> constituencyIds,Long publicationDateId);
	
	 public List<Object[]> getYouthVotersInfoForDistrict(List<Long> ids);
	 
	 public List<Object[]> getYouthVotersInfo(Long constiId,List<Long> ids,Long reportLevel);
	 
	 public List<Object[]> getVoterAgeInfoListByconstituencyExceptYouth(Long constituencyId,Long publicationDateId);
	 
	 public List<Object[]> getVoterAgesInfoListByconstituencyExceptYouths(List<Long> districtList,String accessType,Long publicationDateId);
	 public List<Object[]> getVotersAgeWiseCount(List<Long> constituencyId,Long publicationDateId, Long reportLevelId);
}
