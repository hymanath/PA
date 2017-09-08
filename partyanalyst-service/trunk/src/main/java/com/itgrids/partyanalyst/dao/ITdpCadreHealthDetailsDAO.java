package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreHealthDetails;

public interface ITdpCadreHealthDetailsDAO extends GenericDao<TdpCadreHealthDetails,Long>{

	public List<Object[]> getCadreHealthDetailsForCadre(Long cadreId);
}
