package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.model.ParliamentAssembly;

public class ParliamentAssemblyDAO extends GenericDaoHibernate<ParliamentAssembly, Long> implements
		IParliamentAssemblyDAO {
	public ParliamentAssemblyDAO() {
		super(ParliamentAssembly.class);
	}
	public List<Long> getAssemblyConstituencyforParliament(List<Long> userAccessLevelValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.assembly.constituencyId from ParliamentAssembly model where model.parliamentAssembly.constituencyId in (:userAccessLevelValues)");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		return query.list();  
		
	}
	public List<Object[]> getAssemblyConstituencyforByPaliament(List<Long> userAccessLevelValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.parliamentAssembly.constituencyId,model.assembly.constituencyId,model.assembly.name from ParliamentAssembly model where model.parliamentAssembly.constituencyId in (:userAccessLevelValues)");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		return query.list();  
	}
	
	public List<Object[]> getParliamentDetailsOfAssembly(Long stateId){

		Query query = getSession().createQuery(" SELECT model.parliamentAssembly.constituencyId,model.parliamentAssembly.name,model.assembly.constituencyId,model.assembly.name" +
				" FROM ParliamentAssembly model " +
				" WHERE model.stateId = :stateId ");
		
		query.setParameter("stateId", stateId);
		return query.list();  
	}
	
	public List<Object[]> getParliamentByAssemblyId(Long assemblyId){
		Query query = getSession().createQuery(" SELECT distinct model.parliamentAssembly.constituencyId,model.parliamentAssembly.name " +
				" FROM ParliamentAssembly model " +
				" WHERE model.assembly.constituencyId = :assemblyId ");
		
		query.setParameter("assemblyId", assemblyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConsIdsByParliamntsIds(List<Long> locationIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.assembly.constituencyId," +
					" model.assembly.name,'','' " +
	 				" from ParliamentAssembly model " +
	 				" where");
		if(locationIds != null && !locationIds.isEmpty()){
			sb.append(" model.parliamentAssembly.constituencyId in (:locationIds)");
		}
			
		Query query = getSession().createQuery(sb.toString());
		if(locationIds != null && !locationIds.isEmpty())
			query.setParameterList("locationIds", locationIds);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConsIdsByParliamntId(List<Long> parlimentId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct  model.parliamentAssembly.constituencyId,model.parliamentAssembly.name,model.assembly.constituencyId," +
					" model.assembly.name " +
	 				" from ParliamentAssembly model  ");
		if(parlimentId != null && parlimentId.size()>0){
			sb.append(" where model.parliamentAssembly.constituencyId in (:locationIds)");
		}
			
		Query query = getSession().createQuery(sb.toString());
		if(parlimentId != null && parlimentId.size()>0)
			query.setParameterList("locationIds", parlimentId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConstituencyIdsByParliamntId(Long locationId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.assembly.constituencyId from ParliamentAssembly model " +
	 				" where");
		if(locationId != null && locationId.longValue() > 0L){
			sb.append(" model.parliamentAssembly.constituencyId = :locationId");
		}
			
		Query query = getSession().createQuery(sb.toString());
		if(locationId != null && locationId.longValue() > 0L)
			query.setParameter("locationId", locationId);
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getParliamntIdByConsIds(List<Long> constituencyIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct  model.parliamentAssembly.constituencyId,model.parliamentAssembly.name,model.assembly.constituencyId " +
	 				" from ParliamentAssembly model  ");
		if(constituencyIds != null && constituencyIds.size()>0){
			sb.append(" where model.assembly.constituencyId in (:constituencyIds)");
		}
			
		Query query = getSession().createQuery(sb.toString());
		if(constituencyIds != null && constituencyIds.size()>0)
			query.setParameterList("constituencyIds", constituencyIds);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getParlIdsByAssemblyId(List<Long> assemblyIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct  model.parliamentAssembly.constituencyId,model.parliamentAssembly.name,model.assembly.constituencyId," +
					" model.assembly.name " +
	 				" from ParliamentAssembly model  ");
		if(assemblyIds != null && assemblyIds.size()>0){
			sb.append(" where model.assembly.constituencyId in (:assemblyIds)");
		}
			
		Query query = getSession().createQuery(sb.toString());
		if(assemblyIds != null && assemblyIds.size()>0)
			query.setParameterList("assemblyIds", assemblyIds);
		
		return query.list();
	}
}
