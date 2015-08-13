package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBodyWard;

public class AssemblyLocalElectionBodyWardDAO  extends GenericDaoHibernate<AssemblyLocalElectionBodyWard, Long> implements IAssemblyLocalElectionBodyWardDAO  {

	public AssemblyLocalElectionBodyWardDAO() {
		super(AssemblyLocalElectionBodyWard.class);
	 
	}

	public List findByLocalElectionBody(Long localElectionBodyId, String year) {
		Object[] params = {localElectionBodyId,year};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.constituency.localElectionBodyWard.wardName from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.localElectionBody.localElectionBodyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBodyWard model2 where model2.year <= ?)", params);
	}
	
	public List findByConstituencyIdAndYear(Long constituencyId, String wardType){
		Object[] params = {constituencyId, wardType};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.constituency.localElectionBody.name " +
				" from AssemblyLocalElectionBodyWard model where model.assemblyLocalElectionBody.constituency.constituencyId = ? and model.year = " +
				"(select max(model2.year) from AssemblyLocalElectionBodyWard model2) and model.constituency.localElectionBody.electionType.electionType = ?",params);
	}
	
	public List findByAssemblyLocalElectionBodyWardAndYear(Long assemblyLocalElectionBodyId, Long wardId, String year){
		Object[] params = {assemblyLocalElectionBodyId, wardId, year};
		return getHibernateTemplate().find("select model.constituency.constituencyId from AssemblyLocalElectionBodyWard model where " +
				"model.assemblyLocalElectionBody.assemblyLocalElectionBodyId = ? and model.constituency.constituencyId = ? and model.year = ?",params);
	}
	
	public List findByAssemblyLocalElectionBody(Long localElectionBodyId, String year) {
		Object[] params = {localElectionBodyId,year};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.assemblyLocalElectionBodyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBodyWard model2 where model2.year <= ?)", params);
	}

	public Integer deleteByWardsAndConstituency(List<Long> assemblyLocalElectionBodyWardIds) {
		
		StringBuilder query = new StringBuilder();
		query.append(" delete from AssemblyLocalElectionBodyWard model ");
		query.append("where model.assemblyLocalElectionBodyWardId in (:assemblyLocalElectionBodyWardIds)");
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("assemblyLocalElectionBodyWardIds", assemblyLocalElectionBodyWardIds);
		
		return queryObject.executeUpdate();
		
	}

	public List getAssemblyLocalElectionBodyWardIds(Long constituencyId,
			List<Long> wardIds) {
		StringBuilder query = new StringBuilder();
		query.append("select model.assemblyLocalElectionBodyWardId from AssemblyLocalElectionBodyWard model ");
		query.append("where model.assemblyLocalElectionBody.constituency.constituencyId = ?");
		query.append(" and model.constituency.constituencyId in (:wardIds)");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, constituencyId);
		queryObject.setParameterList("wardIds", wardIds);
		
		return queryObject.list();
		
		
		
		/*Object[] params = {constituencyId};
		return getHibernateTemplate().find("select model.assemblyLocalElectionBodyWardId from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.constituency.constituencyId = ? and model.constituency.constituencyId in (:wardIds)", params);*/
	}	
	
	/*public List findByLocalElectionBody(Long localElectionBodyId, String year, Long constituencyId) {
		Object[] params = {localElectionBodyId,year, constituencyId};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, model.constituency.localElectionBodyWard.wardName from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.localElectionBody.localElectionBodyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBodyWard model2 where model2.year <= ?) and model.assemblyLocalElectionBody.constituency.constituencyId = ?", params);
	}*/

	public List<Object[]> getWardsByAssemblyLocalElectionBody(Long id){
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.assemblyLocalElectionBodyId = ? ", id);
	}
	
	public List<Long> getWardIdsByAssemblyLocalElectionBody(Long id,Long constituencyId){
		Object[] params = {id,constituencyId};
		return getHibernateTemplate().find("select model.constituency.constituencyId from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.assemblyLocalElectionBodyId = ? and  model.assemblyLocalElectionBody.constituency.constituencyId = ?", params);
	}
	
	
	public List<Long> findWardsByAssemblyLocalBody(Long localElectionBodyId, String year) {
		Object[] params = {localElectionBodyId,year};
		return getHibernateTemplate().find("select model.constituency.constituencyId  from AssemblyLocalElectionBodyWard model " +
				"where model.assemblyLocalElectionBody.assemblyLocalElectionBodyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBodyWard model2 where model2.year <= ?)", params);
	}
	public List<Object[]> findWardsByLocalBodyConstiId(Long localElectionBodyId, Long constituency) {
		Object[] params = {localElectionBodyId,constituency};
		if(localElectionBodyId.longValue() != 20l){
		  return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name  from AssemblyLocalElectionBodyWard model " +
				" where model.assemblyLocalElectionBody.localElectionBody.localElectionBodyId = ? and model.assemblyLocalElectionBody.constituency.constituencyId  = ? ", params);
		}else{
			return getHibernateTemplate().find("select model.constituency.constituencyId,concat(model.constituency.name,'(',model1.wardName,')')  from AssemblyLocalElectionBodyWard model,LocalElectionBodyWard model1 " +
					" where model.assemblyLocalElectionBody.localElectionBody.localElectionBodyId = ? and model.assemblyLocalElectionBody.constituency.constituencyId  = ? " +
					" and model1.constituency.constituencyId = model.constituency.constituencyId ", params);
		}
	}
	public List<Object[]> findWardsByLocalBodyIds(List<Long> localElectionBodyIds) {
		//0wardId,1constituencyId
		  Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.assemblyLocalElectionBody.constituency.constituencyId  from AssemblyLocalElectionBodyWard model " +
				" where model.assemblyLocalElectionBody.localElectionBody.localElectionBodyId in(:localElectionBodyIds)  ");
		  query.setParameterList("localElectionBodyIds",localElectionBodyIds);
			return query.list();
	}
	
	public List<Object[]> findWardsByLocalBodyIdsAndConstituencyIds(List<Long> localElectionBodyIds, List<Long> constituencyIds) {
		//0wardId,1constituencyId
		  Query query = getSession().createQuery("select distinct model.constituency.constituencyId," +
		  		" model.assemblyLocalElectionBody.constituency.constituencyId  from AssemblyLocalElectionBodyWard model " +
				" where model.assemblyLocalElectionBody.localElectionBody.localElectionBodyId in(:localElectionBodyIds)  " +
				" and model.assemblyLocalElectionBody.constituency.constituencyId in (:constituencyIds)");
		  
		  query.setParameterList("localElectionBodyIds",localElectionBodyIds);
		  query.setParameterList("constituencyIds", constituencyIds);
			return query.list();
	}
	
	public List<Long> getWardsByconstituency(Long constituencyId)
	{

		StringBuilder str = new StringBuilder();
		str.append("select distinct model.constituency.constituencyId");
		str.append(" from AssemblyLocalElectionBodyWard model where model.assemblyLocalElectionBody.constituency.constituencyId = :constituencyId " );
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> findWardsByLocalBodyConstiIds(List<Long> localElectionBodyIds, List<Long> constituencyIds) {
		
			Query query = getSession().createQuery("select model.constituency.constituencyId,concat(model.constituency.name,'(',model1.wardName,')')  " +
					" from AssemblyLocalElectionBodyWard model,LocalElectionBodyWard model1 " +
					" where model.assemblyLocalElectionBody.localElectionBody.localElectionBodyId in (:localElectionBodyIds)" +
					" and model.assemblyLocalElectionBody.constituency.constituencyId  in (:constituencyIds) " +
					" and model1.constituency.constituencyId = model.constituency.constituencyId ");
			
			query.setParameterList("localElectionBodyIds", localElectionBodyIds);
			query.setParameterList("constituencyIds", constituencyIds);
		
			return query.list();
	}
	
}
