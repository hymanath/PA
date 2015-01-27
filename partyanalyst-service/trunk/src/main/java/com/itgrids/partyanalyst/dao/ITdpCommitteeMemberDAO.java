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
}
