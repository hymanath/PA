package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreFamilyDetails;

public interface ITdpCadreFamilyDetailsDAO extends GenericDao<TdpCadreFamilyDetails, Long>
{
	public Integer inActiveCadreFamilyDetailsById(Long tdpCadreId);
}
