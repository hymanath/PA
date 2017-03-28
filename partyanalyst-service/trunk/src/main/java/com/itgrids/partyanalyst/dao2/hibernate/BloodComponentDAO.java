package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBloodComponentDAO;
import com.itgrids.partyanalyst.model.BloodComponent;

public class BloodComponentDAO extends GenericDaoHibernate<BloodComponent, Long> implements IBloodComponentDAO {

  public BloodComponentDAO(){
	  super(BloodComponent.class);
  }

	
}
