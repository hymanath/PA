package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreHealthStatus;

public interface ICadreHealthStatusDAO extends GenericDao<CadreHealthStatus, Long>{
	  public List<Object[]> getAllCadreHealthStatus();
}
