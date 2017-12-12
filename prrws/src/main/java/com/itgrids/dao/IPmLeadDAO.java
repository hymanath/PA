package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmLead;

public interface IPmLeadDAO extends GenericDao<PmLead, Long> {
	public List<Object[]> getPmLeadDetailsList();

}
