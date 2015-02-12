package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRegistrationStatusTempDAO;
import com.itgrids.partyanalyst.model.RegistrationStatusTemp;



public class RegistrationStatusTempDAO extends  GenericDaoHibernate<RegistrationStatusTemp,Long> implements IRegistrationStatusTempDAO {
    public RegistrationStatusTempDAO(){
   	 super(RegistrationStatusTemp.class);
    }
	

}
