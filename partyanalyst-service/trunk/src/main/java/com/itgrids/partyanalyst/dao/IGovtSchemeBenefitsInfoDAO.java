package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtSchemeBenefitsInfo;

public interface IGovtSchemeBenefitsInfoDAO extends GenericDao<GovtSchemeBenefitsInfo, Long>{
	public List<Object[]> getGovtSchemeWiseBenefitMemberCount(Long locationType, Long locationValue,List<Long> constituencyIds);	

}
