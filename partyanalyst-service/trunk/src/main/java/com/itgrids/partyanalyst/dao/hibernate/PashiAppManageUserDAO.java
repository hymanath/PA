package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPashiAppManageUserDAO;
import com.itgrids.partyanalyst.model.PashiAppManageUser;

public class PashiAppManageUserDAO extends GenericDaoHibernate<PashiAppManageUser, Long> implements IPashiAppManageUserDAO {

public PashiAppManageUserDAO(){
	super(PashiAppManageUser.class);
}

}
