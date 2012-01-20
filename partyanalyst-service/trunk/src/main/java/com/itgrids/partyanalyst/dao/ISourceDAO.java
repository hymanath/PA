package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Source;

public interface ISourceDAO extends GenericDao<Source, Long>{
	public List<Object[]> getSourceDetails();
}
