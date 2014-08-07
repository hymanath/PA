package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVerifierBoothPercentageDAO;
import com.itgrids.partyanalyst.model.VerifierBoothPercentage;

public class VerifierBoothPercentageDAO extends GenericDaoHibernate<VerifierBoothPercentage, Long> implements IVerifierBoothPercentageDAO {
	
	public VerifierBoothPercentageDAO() {
		super(VerifierBoothPercentage.class);
	}
	
	
	public List<String> getBoothWisePercentage(Long boothId)
	{
		Query query = getSession().createQuery("select model.percentage from VerifierBoothPercentage model where model.boothId = :boothId");
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public Long checkForBoothPercentages(Long boothId)
	{
		Query query = getSession().createQuery("select model.verifierBoothPercentageId from VerifierBoothPercentage model where model.boothId = :boothId");
		query.setParameter("boothId",boothId);
		return (Long) query.uniqueResult();
	}
}
