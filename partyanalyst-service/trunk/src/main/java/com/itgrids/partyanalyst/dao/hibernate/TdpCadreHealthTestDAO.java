package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreHealthTestDAO;
import com.itgrids.partyanalyst.model.TdpCadreHealthTest;

public class TdpCadreHealthTestDAO extends GenericDaoHibernate<TdpCadreHealthTest,Long> implements ITdpCadreHealthTestDAO {
	
	public TdpCadreHealthTestDAO() {
		super(TdpCadreHealthTest.class);
	}	

}
