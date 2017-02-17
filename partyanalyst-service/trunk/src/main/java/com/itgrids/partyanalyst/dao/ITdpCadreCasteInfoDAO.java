package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCasteInfo;

public interface ITdpCadreCasteInfoDAO extends GenericDao<TdpCadreCasteInfo, Long>{
	public int deleteTdpCadreCasteInfoTableBeforeUpdate( Long enrollmentYearId);
	public Integer updateTdpCadreCasteInfoTableByScheduler(String locationType, Long enrollmentYearId);
	public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,List<Long> locationIdsList,Long casteStateId, Long enrollmentYearId);
}
