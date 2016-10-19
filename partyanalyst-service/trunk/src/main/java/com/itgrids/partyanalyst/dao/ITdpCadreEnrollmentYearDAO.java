package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreEnrollmentYear;

public interface ITdpCadreEnrollmentYearDAO extends GenericDao<TdpCadreEnrollmentYear, Long>{

	public List<Long> getPreviousElectionYearsOfCadre(Long tdpCadreId);
	public Long getMaxRecordFromEnrollmentYear(Long tdpCadreId);
	public List<Object[]> getTotalRenewlCadreLocationWise(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date frmDt, Date toDt);
	public List<Object[]> getTotalRenewlCadreSourceWise(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date frmDt, Date toDt);
	public List<Object[]> getTotalRenewlCadreBasedOnUserType(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userType,Date fromDate,Date toDate);
	public List<Object[]> getTotalRenewlCadreCntLocationWise(Long stateId,String locationType,Date fromDate,Date toDate,Long userAccessLevelId,List<Long> userAccessLevelValues);
	public List<Object[]> getCadreRegistrationCountByDataSourceType(Date fromDate,Date toDate);
	 public List<Object[]> getCadreRegistrationCountByCadreVerificationStatus(Date fromDate,Date toDate);
	
}
