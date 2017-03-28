package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreRegUserAccessDAO;
import com.itgrids.partyanalyst.model.CadreRegUserAccess;

public class CadreRegUserAccessDAO extends GenericDaoHibernate<CadreRegUserAccess, Long> implements ICadreRegUserAccessDAO {

	public CadreRegUserAccessDAO() {
		super(CadreRegUserAccess.class);
		
	}
}
