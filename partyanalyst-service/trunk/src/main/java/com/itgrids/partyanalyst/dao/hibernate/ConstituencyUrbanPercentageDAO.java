package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IConstituencyUrbanPercentageDAO;
import com.itgrids.partyanalyst.model.ConstituencyUrbanPercentage;

public class ConstituencyUrbanPercentageDAO extends GenericDaoHibernate<ConstituencyUrbanPercentage,Long> implements IConstituencyUrbanPercentageDAO{

	public ConstituencyUrbanPercentageDAO()
	{
		super(ConstituencyUrbanPercentage.class);
	}
}
