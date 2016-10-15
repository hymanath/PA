package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreTargetCount;

public interface ITdpCadreTargetCountDAO extends GenericDao<TdpCadreTargetCount, Long> {
	public List<Object[]> getTotalCadreTargetCountLocationWise(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long enrollmentYearId);
	public List<Object[]> getTotalCadreTargetCountLocationType(String locationType,Long stateId,Long entollmentTearId);
	public Long getTargetCount(Long stateId);
	
}
