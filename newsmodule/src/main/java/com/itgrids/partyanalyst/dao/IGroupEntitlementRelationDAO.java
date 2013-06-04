package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Entitlement;
import com.itgrids.partyanalyst.model.GroupEntitlementRelation;

public interface IGroupEntitlementRelationDAO extends GenericDao<GroupEntitlementRelation, Long> {

	public List<Entitlement> getAllEntitlementsForAGroupByGroupId(Long groupId);
	
	public List<Object> getAllEntitlementsBasedOnUserGroupId(Long groupId);
	
	public Integer deleteAllRelations(Long groupId);
}
