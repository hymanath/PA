package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITirupathiByeleMobileBoothDAO;
import com.itgrids.partyanalyst.model.TirupathiByeleMobileBooth;

public class TirupathiByeleMobileBoothDAO extends GenericDaoHibernate<TirupathiByeleMobileBooth, Long> implements ITirupathiByeleMobileBoothDAO {

	public TirupathiByeleMobileBoothDAO() {
		super(TirupathiByeleMobileBooth.class);
	}
}
