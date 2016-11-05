package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreSmsLeaderDAO;
import com.itgrids.partyanalyst.model.TdpCadreSmsLeader;

public class TdpCadreSmsLeaderDAO extends GenericDaoHibernate<TdpCadreSmsLeader, Long> implements ITdpCadreSmsLeaderDAO{

	public TdpCadreSmsLeaderDAO() {
		super(TdpCadreSmsLeader.class);
		// TODO Auto-generated constructor stub
	}

}
