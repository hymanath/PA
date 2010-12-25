package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.IUserRelationDAO;
import com.itgrids.partyanalyst.model.UserRelation;

public class UserRelationDAO extends GenericDaoHibernate<UserRelation, Long> implements IUserRelationDAO{

	public UserRelationDAO() {
		super(UserRelation.class);
	}

	@SuppressWarnings("unchecked")
	public List<UserRelation> findByRelationType(String relation) {
		
		return getHibernateTemplate().find("from UserRelation model where model.relationship = ?",relation);
	}
	
}
