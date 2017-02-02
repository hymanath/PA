package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.TdpCadreLoginDetails;

public interface ITdpCadreLoginDetailsDAO extends GenericDao<TdpCadreLoginDetails, Long>{

	public List<Long> getAssignedCadreIdsForLoginUserId(Long userId);
}
