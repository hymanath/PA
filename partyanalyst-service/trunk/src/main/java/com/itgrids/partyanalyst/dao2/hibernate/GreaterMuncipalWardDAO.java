package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGreaterMuncipalWardDAO;
import com.itgrids.partyanalyst.model.GreaterMuncipalWard;

public class GreaterMuncipalWardDAO extends GenericDaoHibernate<GreaterMuncipalWard,Long > implements IGreaterMuncipalWardDAO{

	public GreaterMuncipalWardDAO() {
		super(GreaterMuncipalWard.class);
	}

	public List<Object[]> getWardsByWardsIdsLsit(Long publicationDateId,List<Long> getWardsByWardsIdsLsit){
		Query query = getSession().createQuery(" select distinct model.ward.constituencyId,model.divisionName from GreaterMuncipalWard model, Booth model2 " +
			" where model.ward.constituencyId = model2.localBodyWard.constituencyId and model2.publicationDate.publicationDateId = :publicationDateId and " +
			" model.ward.constituencyId in (:getWardsByWardsIdsLsit)  " +
			" order by model.ward.constituencyId");			
		query.setParameterList("getWardsByWardsIdsLsit", getWardsByWardsIdsLsit);
		query.setParameter("publicationDateId", publicationDateId);			
		return query.list();
	}
	
	public List<Object[]> getDivisionWiseVoters(List<Long> divisionIds){
		Query query = getSession().createQuery(" select model.wardId," +
				" model.totalVoters" +
				" from GreaterMuncipalWard model" +
				" where model.wardId in(:divisionIds)");
		query.setParameterList("divisionIds", divisionIds);
		return  query.list();
	}
	
	public Object getTotalVotersByDivisionIds(List<Long> divisionIds){
		Query query = getSession().createQuery(" select sum(gmw.totalVoters) from GreaterMuncipalWard gmw where gmw.wardId in(:divisionIds)" ) ;
		query.setParameterList("divisionIds", divisionIds);
		return  query.uniqueResult();
	}
}
