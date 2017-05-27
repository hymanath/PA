package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ITdpResolutionTypeDAO;
import com.itgrids.model.TdpResolutionType;

@Repository
public class TdpResolutionTypeDAO extends GenericDaoHibernate<TdpResolutionType,Long> implements ITdpResolutionTypeDAO{

	@Autowired
	SessionFactory sessionFactory;

	public TdpResolutionTypeDAO() {
		super(TdpResolutionType.class);
	}

	
	public List<Object[] > getResolutions(List<String> daysList) {
		
		Query query = getSession().createQuery("select model.tdpResolutionTypeId, model.resolutionName, model.day," +
				" model.resolutionDate from  TdpResolutionType model where day in (:days)");
		query.setParameterList("days", daysList);
		return query.list();
	}

}
