package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AffiliatedMember;

public interface IAffiliatedMemberDAO extends GenericDao<AffiliatedMember, Long> {
	 public List<Object[]> searchAffiliatedMemberDetails(String searchType,String searchValue,String locationType, Long locationValues);

	public List<Object[]> searchAffiliatedMemberDetailsThroughTC(String searchType, String searchValue, String locationType,Long locationValues);

	public List<Object[]> searchAffiliatedMemberDetailsThroughVoter(String searchType, String searchValue, String locationType,Long locationValues);

	public Long getAffiliatedMemberId(Long cadreId);
}
