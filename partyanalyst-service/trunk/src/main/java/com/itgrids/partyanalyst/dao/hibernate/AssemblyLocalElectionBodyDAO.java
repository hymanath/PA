package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBody;

public class AssemblyLocalElectionBodyDAO extends GenericDaoHibernate<AssemblyLocalElectionBody, Long> implements IAssemblyLocalElectionBodyDAO {

	public AssemblyLocalElectionBodyDAO() {
		super(AssemblyLocalElectionBody.class);
		 
	}

	/*@SuppressWarnings("unchecked")
	public List findByConstituencyId(Long constituencyId) {
		Object[] params = {constituencyId};
		return getHibernateTemplate().find("select model.localElectionBody.localElectionBodyId, model.localElectionBody.name , model.localElectionBody.electionType.electionType, model.isPartial  from AssemblyLocalElectionBody model " +
				"where model.constituency.constituencyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2)",params);
	}*/
	
	@SuppressWarnings("unchecked")
	public List getLocalElectionBodyIdByConstituencyId(Long constituencyId,String type) {
		Object[] params = {constituencyId,type};
		return getHibernateTemplate().find("select model.localElectionBody.localElectionBodyId, model.localElectionBody.name " +
				"from AssemblyLocalElectionBody model " +
				"where model.constituency.constituencyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2)" +
				"and model.localElectionBody.electionType.electionType=?",params);
	}

	@SuppressWarnings("unchecked")
	public List findConstituencyByLocalELectionBody(Long LocalElectionBodyId,
			String year) {
		Object[] params = {LocalElectionBodyId,year};
		return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name "+
				"from AssemblyLocalElectionBody model where model.localElectionBody.localElectionBodyId = ? "+
				"and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2 where model2.year <= ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAllLocalElectionBodiesForAConstituencyForLatestElectionYear(List<Long> constituencyIds) {
		StringBuilder query = new StringBuilder();
		query.append("select model.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody model " +
				" where model.constituency.constituencyId in( :constituencyIds)" +
				" and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2)");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("constituencyIds", constituencyIds);
		return queryObject.list();
	}
		
	public List getMuncipalityCandidateDetails(String electionType,Long constituencyId){
		  Object[] params = {electionType, constituencyId};
		 return getHibernateTemplate().find("select model.localElectionBody.name," +
		 		" model.localElectionBody.localElectionBodyId," +
		 		" model.localElectionBody.noOfWards," +
		 		" sum(model.constituencyElection.constituencyElectionResult.totalVotes)" +
		 		" from Nomination model where model.constituencyElection.constituency.localElectionBody.electionType.electionType = ? and " +
		    	" model.constituencyElection.constituency.localElectionBody.tehsil.district.districtId = ? " +
		    	" group by model.constituencyElection.constituency.localElectionBody.localElectionBodyId ",params);
	}

	@SuppressWarnings("unchecked")
	public List<AssemblyLocalElectionBody> findByAssemblyLocalBodyAndYear(
			Long localBodyId, Long assemblyId, String year) {
		Object[] params = {localBodyId, assemblyId, year};
		return getHibernateTemplate().find("select model from AssemblyLocalElectionBody model where " +
				"model.localElectionBody.localElectionBodyId = ? and model.constituency.constituencyId = ? and " +
				"model.year = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findByConstituencyId(Long constituencyId) {
		Object[] params = {constituencyId};
		return getHibernateTemplate().find("select model.assemblyLocalElectionBodyId, model.localElectionBody.name , model.localElectionBody.electionType.electionType, model.isPartial,model.localElectionBody.localElectionBodyId  from AssemblyLocalElectionBody model " +
				"where model.constituency.constituencyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2)",params);
	}

	public List getLocalElectionBodyId(Long assemblyLocalElectionBodyId) {
		return getHibernateTemplate().find("select model.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody  model where model.assemblyLocalElectionBodyId = ?", assemblyLocalElectionBodyId);
	}
	
	public Long getLocalElectionBodyIdByAssemblyLocalElectionBodyId(Long assemblyLocalElectionBodyId) {
		Query query = getSession().createQuery("select model.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody  model where model.assemblyLocalElectionBodyId = :assemblyLocalElectionBodyId");
		query.setParameter("assemblyLocalElectionBodyId",assemblyLocalElectionBodyId);
		return (Long)query.uniqueResult();
	}
	
	public List getAssemblyLocalElectionBodyId(Long localElectionBodyId) {
		return getHibernateTemplate().find("select model.assemblyLocalElectionBodyId  from AssemblyLocalElectionBody  model where model.localElectionBody.localElectionBodyId = ?", localElectionBodyId);
	}

	public List findByConstituencyIds(String constituencyIds) {
		return getHibernateTemplate().find("select model.localElectionBody.localElectionBodyId,model.localElectionBody.name from AssemblyLocalElectionBody model where " +
				"model.constituency.constituencyId in(" + constituencyIds + ") and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2 group by model2.year) order by model.localElectionBody.name"); 
		
	}

	@SuppressWarnings("unchecked")
	public List findAssemblyLocalElectionBodyByLocalBodyAndConstituency(
			Long localBodyId, Long constituencyId) {
		Object[] params = {localBodyId,constituencyId};
		return getHibernateTemplate().find("select model.assemblyLocalElectionBodyId from AssemblyLocalElectionBody  model where model.localElectionBody.localElectionBodyId = ? and model.constituency.constituencyId = ?", params);
	}

	public Integer deleteByLocalElectionBodyAndConstituency(List<Long> localBodyIds,
			Long constituencyId) {
		StringBuilder query = new StringBuilder();
		query.append("delete from AssemblyLocalElectionBody model ");
		query.append("where model.constituency.constituencyId = ?");
		query.append(" and model.localElectionBody.localElectionBodyId in (:localBodyIds)");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, constituencyId);
		queryObject.setParameterList("localBodyIds", localBodyIds);		
		return queryObject.executeUpdate();
		
	}
	
	public Long getLocalElectionBodyIdByassemblyLocalElectionBodyId(Long assemblyLocalElectionBodyId) {
		Query query = getSession().createQuery("select model.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody  model where model.assemblyLocalElectionBodyId = ?");
		query.setParameter(0, assemblyLocalElectionBodyId);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getLocalElecBodyName(String assemblyLocalElectionBodyId){
		
		return getHibernateTemplate().find("select model.localElectionBody.name , model.localElectionBody.electionType.electionType,model.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody  model where model.assemblyLocalElectionBodyId = "+assemblyLocalElectionBodyId+"");
	}
	
	public List<Long> getLocalElectionBodyIdByConstituency(Long constituencyId){
		
		Query query = getSession().createQuery("select model.localElectionBody.localElectionBodyId from " +
				"AssemblyLocalElectionBody  model where model.constituency.constituencyId = ?");
		
		query.setParameter(0, constituencyId);
		
		return query.list();
		
		
	}

	/**
	 * This method Is Used To get the details of the Corpration or muncipality based on local election body id
	 * @author PrasadThiragabathina
	 * @date 26/02/2013
	 * @param Long localElectionBodyId
	 * @return List<Object[]>
	 */
	public List<Object[]> getAssemblyLocalElectionBodyDetails(Long localElectionBodyId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("select model.assemblyLocalElectionBodyId ,model.localElectionBody.name,model.localElectionBody.electionType.electionType from AssemblyLocalElectionBody " +
				" model where model.localElectionBody.localElectionBodyId = ?", localElectionBodyId);
	}
	
	public Long getTehsilValues(Long localElectionBodyId,Long constituencyId){
		
		Query query = getSession().createQuery("select model.assemblyLocalElectionBodyId from " +
				"AssemblyLocalElectionBody  model where model.localElectionBody.localElectionBodyId = ? and model.constituency.constituencyId = ?");
		
		query.setParameter(0, localElectionBodyId);
		query.setParameter(1, constituencyId);
		
		return (Long)query.uniqueResult();
	}
	
	public Long getLocalBodyIdBasedOnConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select model.localElectionBody.localElectionBodyId from " +
				" AssemblyLocalElectionBody  model where model.constituency.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return (Long)query.uniqueResult();
	}
}
