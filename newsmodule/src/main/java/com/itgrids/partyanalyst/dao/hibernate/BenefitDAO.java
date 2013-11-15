package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBenefitDAO;
import com.itgrids.partyanalyst.model.Benefit;

public class BenefitDAO extends GenericDaoHibernate<Benefit, Long> implements IBenefitDAO{

	public BenefitDAO() {
		super(Benefit.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Benefit> getBenifitsList()
	{
	  Query query = getSession().createQuery(" from Benefit model ");
	  return query.list();
	}

}
