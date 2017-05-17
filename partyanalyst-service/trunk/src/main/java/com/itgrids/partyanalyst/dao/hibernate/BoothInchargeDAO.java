package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.model.BoothIncharge;

public class BoothInchargeDAO extends GenericDaoHibernate<BoothIncharge, Long> implements IBoothInchargeDAO{

	public BoothInchargeDAO() {
		super(BoothIncharge.class);
	}
	
	public List<Long> getCadreIdsForLocation(List<Long> tdpCadreIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select " +
				" model.tdpCadre.tdpCadreId " +
				" from BoothIncharge model " +
				" where model.isActive = 'Y' and model.isDeleted = 'N'");
		if(tdpCadreIds != null && tdpCadreIds.size() > 0l)
		{
			sb.append(" and model.tdpCadre.tdpCadreId in (:tdpCadreIds)");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(tdpCadreIds != null && tdpCadreIds.size() > 0l) 
			query.setParameterList("tdpCadreIds", tdpCadreIds);
		
		return query.list();
	}
	public BoothIncharge getExistingMember(Long tdpCadreId,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select model" +
				" from BoothIncharge model" );
		
		if(type != null && type.trim().equalsIgnoreCase("removeOption"))
			sb.append(" where model.isActive = 'Y' and model.isDeleted = 'N'");
		else if(type != null && type.trim().equalsIgnoreCase("addOption"))
			sb.append(" where model.isActive = 'N' and model.isDeleted = 'N'");
		
		if(tdpCadreId != null && tdpCadreId.longValue() > 0l){
			sb.append(" and model.tdpCadreId = :tdpCadreId");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(tdpCadreId != null && tdpCadreId.longValue() > 0l)
			query.setParameter("tdpCadreId", tdpCadreId);
		
		return (BoothIncharge) query.uniqueResult();
		
	}
}
