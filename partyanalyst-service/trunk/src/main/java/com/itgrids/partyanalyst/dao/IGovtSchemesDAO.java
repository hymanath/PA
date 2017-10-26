package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityAttendanceAcceptance;
import com.itgrids.partyanalyst.model.GovtSchemes;

public interface IGovtSchemesDAO extends GenericDao<GovtSchemes,Long>{
	public List<Object[]> getAllSchemeDetails();
}
