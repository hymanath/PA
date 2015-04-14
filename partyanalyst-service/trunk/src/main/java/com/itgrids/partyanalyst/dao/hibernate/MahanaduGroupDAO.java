package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMahanaduGroupDAO;
import com.itgrids.partyanalyst.model.MahanaduGroup;

public class MahanaduGroupDAO extends GenericDaoHibernate< MahanaduGroup,Long> implements IMahanaduGroupDAO{

	public MahanaduGroupDAO() {
		super(MahanaduGroup.class);
	}

	public MahanaduGroup checkIsExistGroupName(String groupName)
	{
		Query query = getSession().createQuery(" select model from MahanaduGroup model where model.groupName like '"+groupName+"'");
		return (MahanaduGroup)query.uniqueResult();
	}
}
