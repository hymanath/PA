package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtSchemeBeneficiaryDetails;

public interface IGovtSchemeBeneficiaryDetailsDAO extends GenericDao<GovtSchemeBeneficiaryDetails, Long>{
	
	public List<Object[]> getBenefitsApprovedDetails(List<Long> cadreIds);
	public List<Object[]> getOwnAndParticipatedConstituenciesBenefitDetails(List<Long> constIds);
	public List<Object[]> getLocalityBasedBenefitSchemesDetails(Long accessLevelId,List<Long> accessLevelValuesList);
	public List<Object[]> getBenefitSchemesMembersDetails(Long locationLevelId,Long benefitId,Integer minValue,Integer maxValue);
	public List<Object[]> getAllConstBenefitDetailsForADist(Long distId);
	public List<Object[]> getGovtSchemeWiseBenefitMemberCount(Long locationType, Long locationValue);
	public List<Object[]> getMandalWiseBenefitMemberCountByGovtScheme(Long locationType, Long locationValue,Long govtSchemeId);
	public List<Object[]> getMemberDetailsForBenefitInfo(Long locationscopeId, Long locationValue,Long govtSchemeId, boolean islocalBody);
	public List<Object[]> getMemberDetailsForBenefitInfoForClick(Long locationscopeId, Long locationValue,Long govtSchemeId) ;
}
