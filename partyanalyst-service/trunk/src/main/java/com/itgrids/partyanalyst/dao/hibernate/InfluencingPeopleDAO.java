package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IInfluencingPeopleDAO;
import com.itgrids.partyanalyst.model.InfluencingPeople;

public class InfluencingPeopleDAO extends GenericDaoHibernate<InfluencingPeople, Long> implements IInfluencingPeopleDAO{

	public InfluencingPeopleDAO(){
		super(InfluencingPeople.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeople> findByHamletId(Long hamletId){
		return getHibernateTemplate().find("from InfluencingPeople model where model.hamlet.hamletId = ?",hamletId);
	}
	
}
