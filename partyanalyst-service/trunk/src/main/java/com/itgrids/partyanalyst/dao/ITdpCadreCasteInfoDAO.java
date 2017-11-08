package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCasteInfo;

public interface ITdpCadreCasteInfoDAO extends GenericDao<TdpCadreCasteInfo, Long>{
	public int deleteTdpCadreCasteInfoTableBeforeUpdate( Long enrollmentYearId);
	public Integer updateTdpCadreCasteInfoTableByScheduler(String locationType, Long enrollmentYearId);
	public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,List<Long> locationIdsList,Long casteStateId, Long enrollmentYearId);
	public List<Object[]> getVoterCadreCasteDetailsByAgeRange(Long locationTypeId,List<Long> locationValue,Long casteGroupId, Long casteId);
	public List<Object[]> getAgeGenerAndCasteGroupWiseCadresCount(Long locationTypeId, List<Long> locationValue, Long enrollmentYearId);
	public List<Object[]> getCasteNGenderWiseCadreCounts(Long locationTypeId,List<Long> locationValue, Long enrollmentYearId, Long casteGroupId);
	public List<Object[]> getCasteGroupWiseCadreCounts(Long locationTypeId,List<Long> locationValue, Long enrollmentId);
	public List<Object[]> getCadresCasteNAgeGroupWiseCounts(Long locationTypeId, List<Long> locationValue, Long casteGroupId,	Long casteId, Long enrollemntyearId);
	public List<Object[]> getGenderAndAgeGroupWiseCadreCount(Long locationTypeId, List<Long> locationValues, Long enrollmentYearId);
	List<Object[]> getCasteWiseCadreCounts(Long locationTypeId,List<Long> locationValue, List<Long> enrollmentYearId);
	public List<Object[]> enrollmentYearsBasedOnenrollmentYearIds(List<Long> enrollmentYearIds);
	List<Object[]> getCategoryWiseGenderCount(Long locationScopeId,List<Long> locationValuesList,List<Long> enrollmentYearIdsList);
	public List<Object[]> getLocationWiseCadreCounts(Long locationTypeId,List<Long> locationValues,Long casteId,Long enrollmentYearId);
}
