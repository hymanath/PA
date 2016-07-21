package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostAccessUserDAO;
import com.itgrids.partyanalyst.model.NominatedPostAccessUser;

public class NominatedPostAccessUserDAO extends GenericDaoHibernate<NominatedPostAccessUser, Long> implements INominatedPostAccessUserDAO {

	public NominatedPostAccessUserDAO(){
		super(NominatedPostAccessUser.class);
	}

}
