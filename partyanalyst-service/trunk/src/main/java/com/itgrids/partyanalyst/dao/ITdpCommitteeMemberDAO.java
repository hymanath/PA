package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeMember;

public interface ITdpCommitteeMemberDAO  extends GenericDao<TdpCommitteeMember, Long>{
	public List<Object[]> getRoleWiseAllocatedMembersCount(Set<Long> committeeRoleIds);
	public List<Object[]> getMembersInfo(Set<Long> committeeRoleIds);
	public List<Object[]> getMemberInfo(Long tdpCadreId);
	public List<TdpCommitteeMember> getTdpCommitteeMemberByTdpCadreId(Long tdpCadreId);
	public List<Object[]> getTdpCommitteeMemberForTdpCadreIdList(List<Long> tdpCadreIdsList);
	public List<Object[]> getStartedCommitteesCountByLocation(String state,List<Long> levelIds);
	public Long getMembersCountByLocation(String state,List<Long> levelIds);
	public List<Object[]> getMembersCountInCommitteeByLocation(String state,List<Long> levelIds);
	public List<Object[]> getMembersInfoForRequest(Set<Long> committeeRoleIds);
	public List<Object[]> getComitteeMembersByCommiteTypeAndLocation(List<Long> levelIds,List<Long> locationVals,Long committeeTypeId);
	public List<Object[]> getComitteeMembersInfoByCommiteTypeAndLocation(Long levelId,Long locationVal,Long committeeTypeId);
	public List<Long> getTdpCommitteIds(Long levelId,Long locationVal,Long committeeTypeId);
	public Integer updateTdpComitte(List<Long> tdpCommitteeIds);
	public Integer updateCadreRole(Long Id);
	
}
