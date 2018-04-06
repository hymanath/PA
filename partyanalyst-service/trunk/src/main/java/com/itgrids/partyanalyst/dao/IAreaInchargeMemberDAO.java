package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AreaInchargeMember;

public interface IAreaInchargeMemberDAO extends GenericDao<AreaInchargeMember, Long>{
	public List<String> getActiveOrInActiveInchageDetails(Long cadreId);
	public List<Object[]> getAssignedCadreList(String status);
	public List<Long> getAssignedInchargeBooths(Long cadreId);
	public int deleteAreaInchargeAssignBooths(Long candidateId,Long boothId);
	public List<Object[]> getAreaInchargeAssignedBoothDetails(Long levelId,Long levelValue);
	public Long getInchargeMembers(Set<String> assignIds);
	public List<Long> getdeletedBoothIds(Long cadreId);
}
