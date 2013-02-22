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
}
