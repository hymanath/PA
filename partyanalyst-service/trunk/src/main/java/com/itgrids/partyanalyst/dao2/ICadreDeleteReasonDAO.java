package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreDeleteReason;

public interface ICadreDeleteReasonDAO extends GenericDao<CadreDeleteReason, Long>{

	public List<Object[]> getAllCadreDeleteReasons();
}
