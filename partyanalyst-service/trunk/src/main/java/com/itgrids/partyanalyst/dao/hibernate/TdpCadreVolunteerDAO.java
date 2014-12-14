package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreVolunteerDAO;
import com.itgrids.partyanalyst.model.TdpCadreVolunteer;



public class TdpCadreVolunteerDAO extends GenericDaoHibernate<TdpCadreVolunteer, Long> implements ITdpCadreVolunteerDAO{

	public TdpCadreVolunteerDAO() {
		super(TdpCadreVolunteer.class);
	}

}
