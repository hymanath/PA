package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreHealthTest;

public interface ITdpCadreHealthTestDAO extends GenericDao<TdpCadreHealthTest, Long> {
	
	public List<Object[]> getCadreHealthTestsForCadre(Long tdpCadreId);
}
