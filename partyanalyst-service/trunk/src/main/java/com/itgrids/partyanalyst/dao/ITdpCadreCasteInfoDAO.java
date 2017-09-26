package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCasteInfo;

public interface ITdpCadreCasteInfoDAO extends GenericDao<TdpCadreCasteInfo, Long>{
	public int deleteTdpCadreCasteInfoTableBeforeUpdate( Long enrollmentYearId);
	public Integer updateTdpCadreCasteInfoTableByScheduler(String locationType, Long enrollmentYearId);
	public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,List<Long> locationIdsList,Long casteStateId, Long enrollmentYearId);
	public List<Object[]> getVoterCadreCasteDetailsByAgeRange(Long locationTypeId,Long locationValue,Long casteGroupId, Long casteId);
	public List<Object[]> getAgeGenerAndCasteGroupWiseCadresCount(Long locationTypeId, Long locationValue, Long enrollmentYearId);
	public List<Object[]> getCasteNGenderWiseCadreCounts(Long locationTypeId,Long locationValue, Long enrollmentYearId, Long casteGroupId);
	public List<Object[]> getCasteGroupWiseCadreCounts(Long locationTypeId,Long locationValue, Long enrollmentId);
	public List<Object[]> getCadresCasteNAgeGroupWiseCounts(Long locationTypeId, Long locationValue, Long casteGroupId,	Long casteId, Long enrollemntyearId);
	public List<Object[]> getGenderAndAgeGroupWiseCadreCount(Long locationTypeId, Long locationValue, Long enrollmentYearId);
}
