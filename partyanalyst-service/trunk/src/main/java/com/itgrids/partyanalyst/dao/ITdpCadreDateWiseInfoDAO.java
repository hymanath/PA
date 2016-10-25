package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreDateWiseInfo;

public interface ITdpCadreDateWiseInfoDAO extends GenericDao<TdpCadreDateWiseInfo,Long>{
	
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
	public List<Object[]> get2016TotalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Date fromDate,Date toDate);
	public List<Object[]> get2016TotalRenewalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Date fromDate,Date toDate);
	public List<Object[]> get2016TotalNewCadreCountLocationWise(Long locationScopeId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate);
	public List<Object[]> get2016TotalCadreCountBasedOnUserType(Long locationScopeId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate,Long userType);
	public List<Object[]> get2016TotalRenewalCadreCountBasedOnUserType(Long locationScopeId,List<Long> locationValue,Long stateId,Date fromDate,Date toDate,Long userType);
	
}
