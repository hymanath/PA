package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreInsuranceInfo;

public interface ITdpCadreInsuranceInfoDAO extends GenericDao<TdpCadreInsuranceInfo, Long>{

	public List<Object[]> getDeathsAndHospitalizationDetails(Long typeId,String type);
	public List<Object[]> getDeathsAndHospitalizationDetailsForParliament(List<Long> typeIds,String type);
	public List<Long> getCadresIdsByInsuranceType(Long locationId,String locationType,Long insuranceTypeId);
	public List<Long> getCadresIdsByParliament(List<Long> locationIds,String locationType,Long insuranceTypeId);
	public List<Object[]> getDeathAndHospitalizationDetails(Long locationValue,String searchType);
}
