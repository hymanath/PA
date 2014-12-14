package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreVolunteerDateDAO;
import com.itgrids.partyanalyst.model.TdpCadreVolunteerDate;



public class TdpCadreVolunteerDateDAO extends GenericDaoHibernate<TdpCadreVolunteerDate, Long> implements ITdpCadreVolunteerDateDAO{

	public TdpCadreVolunteerDateDAO() {
		super(TdpCadreVolunteerDate.class);
	}

}
