package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmBriefLead;

public interface IPmBriefLeadDAO extends GenericDao<PmBriefLead, Long> {
	public List<Object[]> gePmBriefLeadDetailsList();

}
