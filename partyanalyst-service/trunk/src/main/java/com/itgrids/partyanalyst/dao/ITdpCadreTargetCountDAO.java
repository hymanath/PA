package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreTargetCount;

public interface ITdpCadreTargetCountDAO extends GenericDao<TdpCadreTargetCount, Long> {
public List<Object[]> getTotalCadreTargetCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId);
	
}
