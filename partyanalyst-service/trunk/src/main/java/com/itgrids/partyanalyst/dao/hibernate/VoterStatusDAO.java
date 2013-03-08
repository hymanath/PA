package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterStatusDAO;
import com.itgrids.partyanalyst.model.VoterStatus;

public class VoterStatusDAO extends GenericDaoHibernate<VoterStatus, Long> implements IVoterStatusDAO{

	public VoterStatusDAO()
	{
		super(VoterStatus.class);
	}
	
	public Long getVoterStatusIdByStatus(String status)
	{
		Query queryObj = getSession().createQuery("select model.voterStatusId from VoterStatus model where " +
				" model.status = :status ");
		queryObj.setParameter("status", status);
		return (Long) queryObj.uniqueResult();
	}
}
