package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityMemberRelation;

public interface IActivityMemberRelationDAO extends GenericDao<ActivityMemberRelation,Long>{
	
	public List<Object[]> getChildUserTypeMembers(Long parentActivityMemberId,List<Long> childUserTypeIds);
	public List<Object[]> checkChildActivityMembersByParents(List<Long> parentActivityMemberIds);
	public List<Object[]> getAllChildUserTypeMembersAndParentUserTypeMembers();
}
