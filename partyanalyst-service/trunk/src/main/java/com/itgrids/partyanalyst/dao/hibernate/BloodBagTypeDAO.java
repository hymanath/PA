package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBloodBagTypeDAO;
import com.itgrids.partyanalyst.model.AcceptanceStatus;
import com.itgrids.partyanalyst.model.BloodBagType;

public class BloodBagTypeDAO extends GenericDaoHibernate<BloodBagType, Long> implements IBloodBagTypeDAO {
   
	public BloodBagTypeDAO(){
    	super(BloodBagType.class);
    }
}
