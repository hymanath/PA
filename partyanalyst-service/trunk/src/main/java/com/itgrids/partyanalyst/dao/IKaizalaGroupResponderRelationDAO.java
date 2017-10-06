package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaGroupResponderRelation;

public interface IKaizalaGroupResponderRelationDAO extends GenericDao<KaizalaGroupResponderRelation, Long>{
	public int updateMemberRemoved(Long eventResponseId,Long kaizalaGroupsId,Long responderInfoId);
}