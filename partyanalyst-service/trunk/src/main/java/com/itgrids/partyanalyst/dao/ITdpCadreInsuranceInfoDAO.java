package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreInsuranceInfo;

public interface ITdpCadreInsuranceInfoDAO extends GenericDao<TdpCadreInsuranceInfo, Long>{

	public List<Object[]> getDeathsAndHospitalizationDetails(Long typeId,String type);
	public List<Object[]> getDeathsAndHospitalizationDetailsForParliament(List<Long> typeIds,String type);
}
