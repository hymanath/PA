package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CampCallStatus;

public interface ICampCallStatusDAO extends GenericDao<CampCallStatus, Long>{
	public List<Object[]> getCallStatusList();

}
