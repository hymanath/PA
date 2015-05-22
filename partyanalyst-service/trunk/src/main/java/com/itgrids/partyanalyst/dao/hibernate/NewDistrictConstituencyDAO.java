package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewDistrictConstituencyDAO;
import com.itgrids.partyanalyst.model.NewDistrictConstituency;

public class NewDistrictConstituencyDAO extends GenericDaoHibernate<NewDistrictConstituency, Long> implements INewDistrictConstituencyDAO {
	public NewDistrictConstituencyDAO() {
		super(NewDistrictConstituency.class);
	}

}
