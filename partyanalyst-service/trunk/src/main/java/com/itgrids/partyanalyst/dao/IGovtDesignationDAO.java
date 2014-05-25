package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDesignation;

public interface IGovtDesignationDAO extends GenericDao<GovtDesignation, Long>{
	 public List<Object[]> getAllGovtDesignation();
}
