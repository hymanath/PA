package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtAlertActionTypeDAO;
import com.itgrids.partyanalyst.model.GovtAlertActionType;

public class GovtAlertActionTypeDAO extends GenericDaoHibernate<GovtAlertActionType, Long> implements
      IGovtAlertActionTypeDAO {
       public GovtAlertActionTypeDAO(){
    	   super(GovtAlertActionType.class);
       }
}
