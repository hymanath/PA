package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPRPWeightegesDAO;
import com.itgrids.partyanalyst.model.PRPWeighteges;

public class PRPWeightegesDAO extends GenericDaoHibernate<PRPWeighteges, Long> implements IPRPWeightegesDAO
{
	public PRPWeightegesDAO() {
		super(PRPWeighteges.class);
	}

	public List<?> getPRPWeightegeDetails(){
		return getHibernateTemplate().find(" select model.constituency.constituencyId,model.constituency.name,model.weightege from PRPWeighteges model " +
				" group by model.constituency.constituencyId order by model.constituency.name asc");
	}
	
	
	public List<?> getPRPWeightageDetailsByConstiIds(List<Long> constiIds){
		Query query = getSession().createQuery("select model.constituency.constituencyId,model.constituency.name,model.weightege from PRPWeighteges model " +
				" where model.constituency.constituencyId in(:constiIds)");
		query.setParameterList("constiIds", constiIds);
		return query.list();
	}
	
	public Double getPRPWeightageByConstiId(Long constiId){
		Query query = getSession().createQuery(" select model.weightege from PRPWeighteges model " +
				" where model.constituency.constituencyId =:constiId)");
		query.setParameter("constiId", constiId);
		return (Double) query.uniqueResult();
	}
}
