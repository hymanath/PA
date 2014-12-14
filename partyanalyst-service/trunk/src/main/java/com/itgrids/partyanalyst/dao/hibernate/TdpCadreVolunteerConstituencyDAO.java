package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreVolunteerConstituencyDAO;
import com.itgrids.partyanalyst.model.TdpCadreVolunteerConstituency;


public class TdpCadreVolunteerConstituencyDAO extends GenericDaoHibernate<TdpCadreVolunteerConstituency, Long> implements ITdpCadreVolunteerConstituencyDAO{

	public TdpCadreVolunteerConstituencyDAO() {
		super(TdpCadreVolunteerConstituency.class);
	}
}
