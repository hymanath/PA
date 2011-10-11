package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserCandidateRelationDAO;
import com.itgrids.partyanalyst.model.UserCandidateRelation;

public class UserCandidateRelationDAO extends GenericDaoHibernate<UserCandidateRelation,Long> implements IUserCandidateRelationDAO{

	public UserCandidateRelationDAO()
	{
		super(UserCandidateRelation.class);
	}
}
