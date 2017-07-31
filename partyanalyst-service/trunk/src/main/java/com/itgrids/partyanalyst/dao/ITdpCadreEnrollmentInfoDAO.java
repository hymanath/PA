package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreEnrollmentInfo;

public interface ITdpCadreEnrollmentInfoDAO extends GenericDao<TdpCadreEnrollmentInfo, Long>{
	public Long getTotalConstituencyForCdrRegStarted(Long stateId);
	public List<Object[]> getLocationTypeWiseCadreCount(final Long locationScopeId,final List<Long> locationValue,final String year);
}