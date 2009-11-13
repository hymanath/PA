package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.District;

public class DelimitationConstituencyDAO extends GenericDaoHibernate<DelimitationConstituency, Long> implements
IDelimitationConstituencyDAO {

	public DelimitationConstituencyDAO() {
		super(DelimitationConstituency.class);
	}

	public List<DelimitationConstituency> findDelimitationConstituencyByConstituencyID(
			Long constituencyID) {
		return getHibernateTemplate().find("from DelimitationConstituency model where " +
				"model.constituency.constituencyId =? order by model.year desc", 
				constituencyID);
	}

}
