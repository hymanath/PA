package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreTravelsDAO;
import com.itgrids.partyanalyst.model.TdpCadreTravels;



public class TdpCadreTravelsDAO extends GenericDaoHibernate<TdpCadreTravels, Long> implements ITdpCadreTravelsDAO{

	public TdpCadreTravelsDAO() {
		super(TdpCadreTravels.class);
	}

}
