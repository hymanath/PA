package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRegistrationStatusDAO;
import com.itgrids.partyanalyst.model.RegistrationStatus;

public class RegistrationStatusDAO extends  GenericDaoHibernate<RegistrationStatus,Long> implements IRegistrationStatusDAO {
     public RegistrationStatusDAO(){
    	 super(RegistrationStatus.class);
     }
	
}
