package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Occupation;

public interface IOccupationDAO extends GenericDao<Occupation, Long> {

	public List<Occupation> getOccupationDetailsByOccupationType(String occupationType);
	
	public List<Occupation> getOccupationList();
	
}
