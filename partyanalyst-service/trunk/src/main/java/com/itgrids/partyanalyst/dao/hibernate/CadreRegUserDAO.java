package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreRegUserDAO;
import com.itgrids.partyanalyst.model.CadreRegUser;

public class CadreRegUserDAO extends GenericDaoHibernate<CadreRegUser, Long> implements ICadreRegUserDAO {

	public CadreRegUserDAO() {
		super(CadreRegUser.class);
		
	}

}
