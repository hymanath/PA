package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EditionType;

public interface IEditionTypeDAO extends GenericDao<EditionType, Long> {
	public List<Object[]> getEditionTypeList();
}
