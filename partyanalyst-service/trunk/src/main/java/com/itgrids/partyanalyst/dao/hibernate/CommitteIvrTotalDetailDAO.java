package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICommitteIvrTotalDetailDAO;
import com.itgrids.partyanalyst.model.CommitteIvrTotalDetail;

public class CommitteIvrTotalDetailDAO extends GenericDaoHibernate<CommitteIvrTotalDetail, Long> implements ICommitteIvrTotalDetailDAO{
	public CommitteIvrTotalDetailDAO() {
		super(CommitteIvrTotalDetail.class);
	}
	
	public List<Object[]> getStateWiseIvrDetails(String state)
	{
		Query query = getSession().createQuery("select model.totalVilages,model.totalIvrCalls,model.totalLifted,model.totalWards,model.totalWardIvrCalls,model.totalWardAnswedIvrCalls" +
				"  from CommitteIvrTotalDetail model where model.state = :state");
		query.setParameter("state", state);
		return query.list();
	}
}
