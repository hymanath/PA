package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreFamilyDetails;

public interface ITdpCadreFamilyDetailsDAO extends GenericDao<TdpCadreFamilyDetails, Long>
{
	public Integer inActiveCadreFamilyDetailsById(List<Long> tdpCadreId);
	
	public List<Object[]> getCadreFamilyDetailsBytdpCadreId(Long tdpCadreId);
	
}
