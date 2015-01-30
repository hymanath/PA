package com.itgrids.partyanalyst.dao;

import java.util.Date;
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
	public List<Object[]> getStartedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate);
	public Long getMembersCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate);
	public List<Object[]> getMembersCountInCommitteeByLocation(String state,List<Long> levelIds,Long committeeId,Date startDate,Date endDate);
	public List<Object[]> getMembersInfoForRequest(Set<Long> committeeRoleIds);
	public List<Long> getTdpCommitteIds(Long levelId,Long locationVal,Long committeeTypeId);
	public Integer deleteCadreRole(Long Id);
	public List<Object[]> getStartedCommitteesCountInALocation(Long constituencyId);
/*	public List<Object[]> getMandalTotalCommittees(Long constituencyId,List mandalIds);
	public List<Object[]> getMuncipalTotalCommittees(Long constituencyId,List muncipalIds);
	public List<Object[]> getDivisonTotalCommittees(Long constituencyId,List divisionIds);*/
	public List<Object[]> getLocationWiseStartedCount(List<Long> locationIds,Long locationTypeId);
	public List<Object[]> basicCommitteeDetails();
	public List<Object[]> getVillageStartedCount(Long constituencyId);
	public List<Object[]> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate);
	public List<Object[]> getComitteeMembersByCommiteTypeAndLocation(List<Long> levelIds,List<Long> locationVals,Long committeeTypeId,String status);
	public List<Object[]> getComitteeMembersInfoByCommiteTypeAndLocation(Long levelId,Long locationVal,Long committeeTypeId,String status);;
	public Integer updateTdpComitte(List<Long> tdpCommitteeIds);
	public List<Object[]> getCommitteStatusAndId(Long tdpCommitteMemberId);
	
}
