package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreDeleteReasonDAO;
import com.itgrids.partyanalyst.model.CadreDeleteReason;

public class CadreDeleteReasonDAO extends GenericDaoHibernate<CadreDeleteReason, Long> implements ICadreDeleteReasonDAO{

	public CadreDeleteReasonDAO() {
		super(CadreDeleteReason.class);
	}

	public List<Object[]> getAllCadreDeleteReasons(){
		
		Query query = getSession().createQuery("select model.cadreDeleteReasonId,model.reason from CadreDeleteReason model ");
			
		return query.list();
	}
}
