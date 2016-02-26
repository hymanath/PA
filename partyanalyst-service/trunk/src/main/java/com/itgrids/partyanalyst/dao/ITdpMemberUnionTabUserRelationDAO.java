package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpMemberUnionTabUserRelation;

public interface ITdpMemberUnionTabUserRelationDAO extends GenericDao<TdpMemberUnionTabUserRelation, Long> {
	public List<Object[]> getAccessMemberTypesBtTabUserId( Long unionTabUserId);
}
