package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IBloodGroupDAO;
import com.itgrids.partyanalyst.model.BloodGroup;

public class BloodGroupDAO extends GenericDaoHibernate<BloodGroup,Long> implements IBloodGroupDAO{

	public BloodGroupDAO()
	{
		super(BloodGroup.class);
	}
}
