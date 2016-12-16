package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITourTypeDAO;
import com.itgrids.partyanalyst.model.TourType;

public class TourTypeDAO extends GenericDaoHibernate<TourType, Long> implements ITourTypeDAO {
   
	public TourTypeDAO(){
		super(TourType.class);
	}
}
