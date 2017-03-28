package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PingingType;

public interface IPingingTypeDAO extends GenericDao<PingingType, Long>{
	
	public List<Object> getPingingTypeIdByType(String type);

}
