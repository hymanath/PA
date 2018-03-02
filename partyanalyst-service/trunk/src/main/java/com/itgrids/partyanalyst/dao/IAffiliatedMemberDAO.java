package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AffiliatedMember;

public interface IAffiliatedMemberDAO extends GenericDao<AffiliatedMember, Long> {
	 public List<Object[]> searchAffiliatedMemberDetails(String searchType,String searchValue,String locationType, Long locationValues);

	public List<Object[]> searchAffiliatedMemberDetailsThroughTC(String searchType, String searchValue, String locationType,Long locationValues);

	public List<Object[]> searchAffiliatedMemberDetailsThroughVoter(String searchType, String searchValue, String locationType,Long locationValues);

	public Long getAffiliatedMemberId(Long cadreId, String type);
	
	public List<Object[]> searchAffiliatedMemberDetailsForMobile(String searchType,String searchValue,String locationType, Long locationValue);

	public List<Object[]> getDayWisrRegisteredCount(Date fromDate, Date toDate,Long locationScopeId, Long locationValue, String type);

	public List<Object[]> getDayWisrVisitedCount(Date fromDate, Date toDate,Long locationScopeId, Long locationValue);
}
