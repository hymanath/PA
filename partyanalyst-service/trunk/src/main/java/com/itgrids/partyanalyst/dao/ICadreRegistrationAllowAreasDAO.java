package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreRegistrationAllowAreas;

public interface ICadreRegistrationAllowAreasDAO extends GenericDao<CadreRegistrationAllowAreas, Long>{
	public List<Long> getAssignedBoothDetailsInAssemblyList(List<Long> constituencyIdsList);
}
