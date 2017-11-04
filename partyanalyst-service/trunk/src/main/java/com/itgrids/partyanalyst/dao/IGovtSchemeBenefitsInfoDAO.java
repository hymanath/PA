package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtSchemeBenefitsInfo;

public interface IGovtSchemeBenefitsInfoDAO extends GenericDao<GovtSchemeBenefitsInfo, Long>{
	public List<Object[]> getGovtSchemeWiseBenefitMemberCount(Long locationType, Long locationValue,List<Long> constituencyIds);
	public List<Object[]> getMandalWiseBenefitMemberCountByGovtScheme(Long locationType, Long locationValue,Long govtSchemeId,List<Long> constituencyIds);
	public List<Object[]> getLocationWiseSchemeOverview(Long locationType, Long locationValue,String type,boolean isLocalBody);
	public List<Object[]> getLocationWiseSchemesDetailsInfo(Long locationType, Long locationValue,boolean isLocalBody);
}
