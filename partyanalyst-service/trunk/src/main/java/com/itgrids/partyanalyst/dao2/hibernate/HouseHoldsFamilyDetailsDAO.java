package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHouseHoldsFamilyDetailsDAO;
import com.itgrids.partyanalyst.model.HouseHoldsFamilyDetails;

public class HouseHoldsFamilyDetailsDAO extends GenericDaoHibernate<HouseHoldsFamilyDetails,Long> implements IHouseHoldsFamilyDetailsDAO {


	public HouseHoldsFamilyDetailsDAO()
	{
		super(HouseHoldsFamilyDetails.class);
	}

}
