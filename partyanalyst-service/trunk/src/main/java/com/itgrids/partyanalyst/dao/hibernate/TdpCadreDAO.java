package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.model.TdpCadre;

public class TdpCadreDAO extends GenericDaoHibernate<TdpCadre, Long> implements ITdpCadreDAO{

	public TdpCadreDAO() {
		super(TdpCadre.class);
	}

}
