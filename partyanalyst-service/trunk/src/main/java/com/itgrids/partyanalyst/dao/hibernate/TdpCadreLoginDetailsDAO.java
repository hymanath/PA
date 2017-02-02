package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreLoginDetailsDAO;
import com.itgrids.partyanalyst.model.TdpCadreLoginDetails;

public class TdpCadreLoginDetailsDAO extends GenericDaoHibernate<TdpCadreLoginDetails, Long> implements ITdpCadreLoginDetailsDAO{

	public TdpCadreLoginDetailsDAO() {
		super(TdpCadreLoginDetails.class);

	}

	public List<Long> getAssignedCadreIdsForLoginUserId(Long userId){
		Query query = getSession().createQuery("select distinct model.tdpCadreId" +
											" from TdpCadreLoginDetails model" +
											" where model.loginId = :userId" +
											" and model.isDeleted = 'N'");
		query.setParameter("userId", userId);
		return query.list();
	}
}
