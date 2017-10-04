package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaGroupTypeDAO;
import com.itgrids.partyanalyst.model.KaizalaGroupType;

public class KaizalaGroupTypeDAO extends GenericDaoHibernate<KaizalaGroupType, Long> implements IKaizalaGroupTypeDAO{

	public KaizalaGroupTypeDAO() {
		super(KaizalaGroupType.class);
		
	}
	public Long checkGroupTypeExistence(String type){
		Query query = getSession().createQuery(" select model.kaizalaGroupTypeId "
				+ " from KaizalaGroupType model "
				+ " where model.groupType =:type ");
		query.setParameter("type", type);
		return (Long) query.uniqueResult();
	}
	

}
