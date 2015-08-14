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
	
	public List getAssemblyLocalElectionBodyIdsList(Long localElectionBodyId,Long constituencyId) {
		Object[] parms = {localElectionBodyId,constituencyId};
		return getHibernateTemplate().find("select model.assemblyLocalElectionBodyId  from AssemblyLocalElectionBody  model " +
				" where model.localElectionBody.localElectionBodyId = ? and model.constituency.constituencyId = ? ", parms);
	}
	
	public List<Object[]> getTehsilsForUrbanConstituency(List<Long> constituencyIds,String year)
	{
		Query query = getSession().createQuery("select  model.assemblyLocalElectionBodyId , model.localElectionBody.name " +
				"from AssemblyLocalElectionBody  model   where " +
				" model.constituency.constituencyId in (:constituencyIds) and model.year = :year ");
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("year", year);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> geLocalElectionBodyListForVotersAnalysis(Long constituencyId)
	{
		Query query = getSession().createQuery(" Select model.localElectionBody.localElectionBodyId,model.localElectionBody.name from AssemblyLocalElectionBody model " +
				" where model.constituency.constituencyId =:constituencyId order by model.localElectionBody.name ");
		
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public String getElectionTypeForMuncipality(Long constituencyId,Long localEleBodyId)
	{
		Query query = getSession().createQuery(" select model.localElectionBody.electionType.electionType from AssemblyLocalElectionBody model where model.constituency.constituencyId =:constituencyId " +
				" and model.localElectionBody.localElectionBodyId =:localElectionBodyId ");
		
		query.setParameter("localElectionBodyId", localEleBodyId);
		query.setParameter("constituencyId", constituencyId);
		
		return (String) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getLocalEleBodyIdsListByConstituencyId(Long constituencyId,Long publicationDateId)
	{
	  Query query = getSession().createQuery(" select distinct model.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody model, Booth model2 where " +
	  		" model.constituency.constituencyId = model2.constituency.constituencyId and model.constituency.constituencyId =:constituencyId and model2.publicationDate.publicationDateId =:publicationDateId  ");
	  
	  query.setParameter("constituencyId", constituencyId);
	  query.setParameter("publicationDateId", publicationDateId);
	  return query.list();
	}
	
	public Object[] getMuncipalityDetails(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct model.localElectionBody.localElectionBodyId,model.localElectionBody.name from AssemblyLocalElectionBody model " +
				" where model.constituency.constituencyId =:constituencyId");
		query.setParameter("constituencyId", constituencyId);
		return (Object[]) query.uniqueResult();
	}
	
	
	public Long getAssemblyLocalElectionBodyIdByConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("Select model.assemblyLocalElectionBodyId from AssemblyLocalElectionBody model " +
				" where model.constituency.constituencyId = :constituencyId");
		query.setParameter("constituencyId", constituencyId);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getAssemblyLocationElectionBodyList(Long constituencyId){
		Query query = getSession().createQuery("Select model.assemblyLocalElectionBodyId,model.localElectionBody.localElectionBodyId,model.constituency.constituencyId,model.localElectionBody.electionType.electionTypeId  " +
				" from AssemblyLocalElectionBody model where model.constituency.constituencyId =:constituencyId and model.localElectionBody.electionType.electionTypeId in(5,6,7)");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List getLocalElectionBodyName(Long assemblyLocalElectionBodyId) {
		return getHibernateTemplate().find("select concat(model.localElectionBody.name,' ',model.localElectionBody.electionType.electionType) from AssemblyLocalElectionBody  model where model.assemblyLocalElectionBodyId = ?", assemblyLocalElectionBodyId);
	}
	
	public List<Object[]> getAssLocalEleBodyIdAndLocalEleBodyIdByConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery(" Select model.localElectionBody.localElectionBodyId,model.assemblyLocalElectionBodyId from AssemblyLocalElectionBody model " +
				" where model.constituency.constituencyId =:constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByConstituencyIdForUrbanType(Long constituencyId,Long urbanTypeId) {
		Object[] params = {constituencyId};
		
		Query query = getSession().createQuery("select model.assemblyLocalElectionBodyId, model.localElectionBody.name , model.localElectionBody.electionType.electionType, model.isPartial,model.localElectionBody.localElectionBodyId  from AssemblyLocalElectionBody model " +
				"where model.constituency.constituencyId = :constituencyId and model.localElectionBody.electionType.electionTypeId= :urbanTypeId and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2) ");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("urbanTypeId", urbanTypeId);
		return query.list();
	}	
	
	 /**
     * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
     * @since 22-NOV-2014
     * This DAO Call is to Get LocalElectionBodyIds
     * @param List<Long> AssemblyLocalElectionBodyIds
     * @return List<Long> LocalElectionBodyIds
     */
	public List<Long> getLEBIdsByALEBIds(List<Long> assemblyLocalElectionBodyIds){
		Query query = getSession().createQuery(" select model.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody  model " +
				" where model.assemblyLocalElectionBodyId in(:assemblyLocalElectionBodyIds)");
		query.setParameterList("assemblyLocalElectionBodyIds", assemblyLocalElectionBodyIds);
		return query.list();
	}
	public List<Object[]> getGHMCConstituencies()
	{
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.constituency.name from AssemblyLocalElectionBody model " +
				" where model.localElectionBody.localElectionBodyId = 20 order by model.constituency.name ");
		
		return query.list();
	
	}
	
	public List<Object[]> getAllLocalBodiesInAConstituency(Long constituencyId) {
		Object[] params = {constituencyId};
		return getHibernateTemplate().find("select model.localElectionBody.localElectionBodyId,concat(model.localElectionBody.name,' ',model.localElectionBody.electionType.electionType)  from AssemblyLocalElectionBody model " +
				"where model.constituency.constituencyId = ? and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2)",params);
	}
	
	public List<Object[]> getGreaterCitiesConstituencies(){ //GHMC, GVMC, BZA
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.constituency.name from AssemblyLocalElectionBody model " +
				" where model.localElectionBody.localElectionBodyId in(20,124,119) order by model.constituency.name ");
		
		return query.list();
	
	}
	public List<Object[]> getAllLocalBodiesInAConstituencyList(List<Long> constituencyIds) {
		//0localBodyId,1constituencyId
		Query query = getSession().createQuery("select distinct model.localElectionBody.localElectionBodyId,model.constituency.name, model.localElectionBody.electionType.electionType" +
				"  from AssemblyLocalElectionBody model " +
				" where model.constituency.constituencyId in(:constituencyIds) and model.year = (select max(model2.year) from AssemblyLocalElectionBody model2)");
		query.setParameterList("constituencyIds",constituencyIds);
		return query.list();
	}
}
