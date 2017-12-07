package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.model.LocalElectionBody;

public class LocalElectionBodyDAO extends GenericDaoHibernate<LocalElectionBody, Long> 
			implements ILocalElectionBodyDAO{

	public LocalElectionBodyDAO() {
		super(LocalElectionBody.class);
	}

	@SuppressWarnings("unchecked")
	public List<LocalElectionBody> findByElectionTypeDistrictTehsilAndLEBName(
			Long electionTypeId, String districtName, String tehsilName,
			String localElecBodyName) {
		Object[] params = {electionTypeId,
				districtName, tehsilName, localElecBodyName};
		return getHibernateTemplate().find("from LocalElectionBody model where model.electionType.electionTypeId = ? and " +
				"model.tehsil.district.districtName = ? and model.tehsil.tehsilName = ? and model.name = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List findByElectionTypeAndState(Long electionTypeId, Long stateId) {
		Object[] params = {electionTypeId, stateId};
		return getHibernateTemplate().find("select distinct model.localElectionBodyId, model.name,model.electionType.electionType from LocalElectionBody model where " +
				"model.electionType.electionTypeId = ? and model.district.state.stateId = ? order by model.name", params);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllLocalBodysByState(Long stateId) {
		Object[] params = {stateId};
		return getHibernateTemplate().find("select distinct model.localElectionBodyId, model.name,model.electionType.electionType," +
				" model.district.districtId, model.district.districtName from LocalElectionBody model where " +
				" model.district.state.stateId = ? order by model.name", params);
	}
	
	@SuppressWarnings("unchecked")
	public List getLocalELectionTypesInAState(Long stateId) {
		return getHibernateTemplate().find("select distinct model.electionType.electionTypeId,model.electionType.electionType "+
				"from LocalElectionBody model where model.district.state.stateId = ?",stateId);
	}
	
	public List findByDistrictId(Long districtId){
		return getHibernateTemplate().find("select model.localElectionBodyId, model.name, model.electionType.electionType from " +
				"LocalElectionBody model where model.district.districtId = ?", districtId);
	}
	
	@SuppressWarnings("unchecked")
	public List<LocalElectionBody> findByLocalElectionBodyIds(List<Long> localElectionBodyIds) {	
		StringBuilder query = new StringBuilder();
		query.append("select model from LocalElectionBody model where model.localElectionBodyId in ( :localElectionBodyIds)");		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("localElectionBodyIds", localElectionBodyIds);
		return queryObject.list();
	}

	public List getStateToLocalElectionBodyByLEB(String localElectionBodyIds) {
		return getHibernateTemplate().find("select model.district.state.stateId, model.district.state.stateName, " +
				"model.district.districtId, model.district.districtName, " +
				"model.localElectionBodyId, model.name " +
				"from LocalElectionBody  model where model.localElectionBodyId in("+localElectionBodyIds+") ");
		
	}

	@SuppressWarnings("unchecked")
	public List getCountOfLocalBodysForALocalElectionBodyType(
			Long electionTypeId) {
		return getHibernateTemplate().find("select count(model.localElectionBodyId) from LocalElectionBody model where "+
				"model.electionType.electionTypeId = ?",electionTypeId);
	}

	@SuppressWarnings("unchecked")
	public List getLocalElectionBodyIdByNameAndDistrictId(String localBodyName,
			Long districtId) {
		Object[] params = {localBodyName, districtId};
		return getHibernateTemplate().find("select model.localElectionBodyId from LocalElectionBody model where model.name = ? and model.district.districtId = ?",params);
	} 
	
	public List<String> getLocalElectionBodyNameById(Long localElectionBodyId){
		
		Query query = getSession()
				.createQuery(
						"select model.name from LocalElectionBody model where model.localElectionBodyId = ?");
		
		query.setParameter(0, localElectionBodyId);
		return query.list();
		
		
	}
	
	public List<Long> getMuncipalitiesAndCorporationsForConstituency(List<Long> tehsilIds){
		
		Query query = getSession().createQuery("select model.localElectionBodyId from LocalElectionBody " +
				"model where model.tehsil.tehsilId in(:tehsilIds)");
		
		query.setParameterList("tehsilIds", tehsilIds);
		
		return query.list();
		
	}
	
	public String getLocationTypeForLocalEleBodyByLocalEleBodyId(Long localEleBodyId)
	{
		Query queryObj = getSession().createQuery("select model.electionType.electionType from LocalElectionBody model where model.localElectionBodyId =:localEleBodyId ");
		
		queryObj.setParameter("localEleBodyId", localEleBodyId);
		return (String) queryObj.uniqueResult();
	}

	public List<Object[]> findByLocalElecBodyIds(List<Long> lclElecBodyIds){
		Query query = getSession().createQuery("select model.localElectionBodyId, model.name, model.electionType.electionType,model.electionType.electionTypeId from " +
				"LocalElectionBody model where model.localElectionBodyId in(:lclElecBodyIds)");
		query.setParameterList("lclElecBodyIds",lclElecBodyIds);
		return query.list();
	}
	
	public String getMuncipalityNameById(Long muncipalityId)
	{
		Query query = getSession().createQuery("select model.name from LocalElectionBody model where model.localElectionBodyId =:localElectionBodyId ");
		query.setParameter("localElectionBodyId", muncipalityId);
		return (String) query.uniqueResult();
	}
	public List<Object[]>  getLocationTypeForLocalEleBodyByLocalEleBodyId(List<Long> localEleBodyIds)
	{
		Query queryObj = getSession().createQuery("select model.electionType.electionType,model.localElectionBodyId from LocalElectionBody model where model.localElectionBodyId in (:localEleBodyIds) ");
		
		queryObj.setParameterList("localEleBodyIds", localEleBodyIds);
		return queryObj.list();	
	}
	
	public List<Object[]> getTehsilsByLocalBody(Long localBodyId)
	{
		Query query = getSession().createQuery("select model.tehsil.tehsilId,model.tehsil.tehsilName from LocalElectionBody model" +
				"  where model.localElectionBodyId = :localBodyId");
		query.setParameter("localBodyId", localBodyId);
		return query.list();
	}
public String getLocalElectionBodyName(Long localElectionBodyId){
		
		Query query = getSession()
				.createQuery(
						"select model.name from LocalElectionBody model where model.localElectionBodyId = ?");
		
		query.setParameter(0, localElectionBodyId);
		return (String)query.uniqueResult();
		
		
	}
  @SuppressWarnings("unchecked")
  public List<Object[]> getLocationElectionBodyList()
  {
	  return getHibernateTemplate().find(" select model.localElectionBodyId,model.name,model.electionType.electionTypeId" +
	  		",model.tehsil.tehsilId,model.district.districtId,model.noOfWards from LocalElectionBody model ");
  }

  public List<Object[]> getLocalElectionBodyType(Set<Long> localElectionBodyIds){
	  Query query = getSession().createQuery("select model.electionType.electionTypeId,model.localElectionBodyId from LocalElectionBody model where model.localElectionBodyId in(:localElectionBodyIds)");
	  query.setParameterList("localElectionBodyIds", localElectionBodyIds);
	  return query.list();
  }
  
  public String getLocalElectionBodyName1(Long localElectionBodyId){
		
		Query query = getSession()
				.createQuery(
						"select concat(model.name,' ',model.electionType.electionType) from LocalElectionBody model where model.localElectionBodyId = ?");
		
		query.setParameter(0, localElectionBodyId);
		return (String)query.uniqueResult();
		
		
	}
  
  public List<Object[]> getLocalElectionBodyNames(List<Long> localElectionBodyIds){
		
		Query query = getSession()
				.createQuery("select model.localElectionBodyId,concat(model.name,' ',model.electionType.electionType) from LocalElectionBody model where model.localElectionBodyId in(:localElectionBodyIds)");
		
		query.setParameterList("localElectionBodyIds", localElectionBodyIds);
		return query.list();
		
		
	}  
  public String getLocalElectionBodyNameNew(Long localElectionBodyId){
		
		Query query = getSession()
				.createQuery(
						"select concat(model.name,' ',model.electionType.electionType) from LocalElectionBody model where model.localElectionBodyId = ?");
		
		query.setParameter(0, localElectionBodyId);
		return (String)query.uniqueResult();
		
		
	}
  
  public List<Object[]> getMuncipalitiesAndCorporationsInAConstituency(List<Long> tehsilIds){
		
		Query query = getSession().createQuery("select model.localElectionBodyId, model.name from LocalElectionBody " +
				"model where model.tehsil.tehsilId in(:tehsilIds)");
		
		query.setParameterList("tehsilIds", tehsilIds);
		
		return query.list();
		
	}
  
  @SuppressWarnings("unchecked")
	public List findByElectionTypeAndState1(Long electionTypeId, Long stateId) {
		
	  StringBuilder str = new StringBuilder();
	 
	  str.append("select distinct model.localElectionBodyId, model.name from LocalElectionBody model where " +
				"model.electionType.electionTypeId = :electionTypeId");
	  if(stateId == 1)
		  str.append(" and model.district.state.stateId = :stateId and model.district.districtId between 11 and 23 order by model.name");
	  else if(stateId == 0)
		  str.append(" and model.state.stateId = 1 and model.district.districtId between 1 and 10 order by model.name");
	  else
		  str.append(" and model.district.state.stateId =:stateId order by model.name");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("electionTypeId", electionTypeId);
	  if(stateId != 0)
	  query.setParameter("stateId", stateId);
	return query.list();
	}
  
  public LocalElectionBody getLocalElectionBodyDetailsByLevel(Long localElectionBodyId){
	  
	  Query query = getSession().createQuery(" select model from LocalElectionBody model " +
	  		" where model.localElectionBodyId = :levelValue ");
	  
	  query.setParameter("levelValue", localElectionBodyId);
	  
	  return (LocalElectionBody) query.uniqueResult();
  }
 
  public List getLocalEleForDistrict(Long districtId){
		return getHibernateTemplate().find("select model.localElectionBodyId, model.name,model.electionType.electionType from " +
				"LocalElectionBody model where model.electionType.electionTypeId = 5 and model.district.districtId = ?", districtId);
	}
  
  public List getCorporationsForDistrict(Long districtId){
		return getHibernateTemplate().find("select model.localElectionBodyId, model.name,model.electionType.electionType from " +
				"LocalElectionBody model where model.electionType.electionTypeId = 6 and model.district.districtId = ?", districtId);
	}
  
  public List getGMCsForDistrict(Long districtId){
		return getHibernateTemplate().find("select model.localElectionBodyId, model.name,model.electionType.electionType from " +
				"LocalElectionBody model where model.electionType.electionTypeId = 7 and model.district.districtId = ?", districtId);
	}
  public List<Object[]> getAllLocalElectionBodyList(Long stateId){
		Query query = getSession().createQuery("select distinct model.localElectionBodyId, model.name from LocalElectionBody model where model.district.state.stateId =:stateId "); 
		query.setParameter("stateId", stateId);
		return query.list();  
	}
  
  public List<Object[]> getAllLocalElectionBodyListByState(){
		Query query = getSession().createQuery("select distinct model.localElectionBodyId," +
									" concat(model.name,' Muncipality')" +
									" from LocalElectionBody model" +
									" where model.district.state.stateId in (1,36)" +
									" order by model.name"); 
		return query.list();  
	}
  
  public List<Long> getLocalElectionBodyIdsByDistrictIdsList(List<Long> districtIdsList){
		Query query = getSession().createQuery("select distinct model.localElectionBodyId " +
									" from LocalElectionBody model" +
									" where model.district.districtId in (:districtIdsList) "); 
		if(districtIdsList != null && districtIdsList.size() >0){
			query.setParameterList("districtIdsList", districtIdsList);
		}
		return query.list();  
	}
}
