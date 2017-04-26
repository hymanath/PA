package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtOfficerNew;

public interface IGovtOfficerNewDAO extends GenericDao<GovtOfficerNew, Long> {
	public List<String> getOfficerDetailsByOfficerId (Long officerId);
}
