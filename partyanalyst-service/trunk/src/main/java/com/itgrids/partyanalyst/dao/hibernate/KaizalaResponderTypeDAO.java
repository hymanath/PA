package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaResponderTypeDAO;
import com.itgrids.partyanalyst.model.ActionType;
import com.itgrids.partyanalyst.model.KaizalaResponderType;
import com.itgrids.partyanalyst.utils.IConstants;

public class KaizalaResponderTypeDAO extends GenericDaoHibernate<KaizalaResponderType, Long> implements IKaizalaResponderTypeDAO {

	public KaizalaResponderTypeDAO(){
		super(KaizalaResponderType.class);
	}
	public Long getResponderType(String type){
		Query query = getSession().createQuery(" select model.kaizalaResponderTypeId" +
				" from KaizalaResponderType model where model.type = :type ");
		
		query.setParameter("type",type);
		return (Long) query.uniqueResult();
	}
	
}
