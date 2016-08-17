package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.columns.enums.TehsilColumnNames;
import com.itgrids.partyanalyst.model.Tehsil;

public class TehsilDAO extends GenericDaoHibernate<Tehsil, Long> implements ITehsilDAO
{

	public TehsilDAO() {
		super(Tehsil.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tehsil> findByProperty(TehsilColumnNames propertyName, Object value){
		return getHibernateTemplate().find("from Tehsil where " + propertyName.getValue() + "=?", value);
	}

	public List<Tehsil> findByTehsilName(Object tehsilName){
		return findByProperty(TehsilColumnNames.TEHSIL_NAME, tehsilName);
	}

	public List<Tehsil> findByTehsilCode(Object tehsilCode){
		return findByProperty(TehsilColumnNames.TEHSIL_CODE, tehsilCode);
	}

	public List<Tehsil> findByDeformDate(Object deformDate){
		return findByProperty(TehsilColumnNames.DEFORM_DATE, deformDate);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tehsil> findByConstituency(Long constituencyId){
		return getHibernateTemplate().find("from Tehsil model where model.tehsilId in(select model1.tehsil.tehsilId from Booth model1 where model1.boothId in(select model2.booth.boothId from BoothConstituencyElection model2 where model2.constituencyElection.constiElecId in(select model3.constiElecId from ConstituencyElection model3 where model3.constituency.constituencyId=?)))", constituencyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tehsil> findByTehsilNameAndDistrict(String name, Long districtId){
		Object[] params = {name, districtId};
		return getHibernateTemplate().find("from Tehsil model where model.tehsilName = ? and model.district.districtId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List findTehsilsByDistrict(Long districtId){
		StringBuilder query = new StringBuilder();
		query.append("Select model.tehsilId, model.tehsilName ");
		query.append("from Tehsil model where ");
		query.append("model.district.districtId ="+districtId);
		query.append("order by model.tehsilName");
		return getHibernateTemplate().find(query.toString());
	}
	
	public List getStateToMandalByTehsil(String tehsilIDs){
		return getHibernateTemplate().find("select model.district.state.stateId, model.district.state.stateName, " +
				"model.district.districtId, model.district.districtName, model.tehsilId, model.tehsilName " +
				" from Tehsil model where model.tehsilId in("+tehsilIDs+") ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Tehsil> findByDistrict(Long districtId){
		return getHibernateTemplate().find("from Tehsil model where model.district.districtId = ?", districtId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tehsil> findByState(Long stateId){
		return getHibernateTemplate().find("from Tehsil model where model.district.state.stateId = ?", stateId);
	}

	@SuppressWarnings("unchecked")
	public List findTehsilIdByTehsilNameAndDistrict(String name, Long districtId) {
		Object[] params = {name, districtId};
		return getHibernateTemplate().find("select model.tehsilId from Tehsil model where model.tehsilName = ? and model.district.districtId = ?", params);
	}
	
	public List<Object[]> getAllTehsilInfoDetails(){
		
		Query query = getSession().createQuery("select model.tehsilId , model.tehsilName from Tehsil model");
		return query.list();
		
		
	}
	
public List<Object[]> getAllTehsilDetails(Long districtId){
		
		Query query = getSession().createQuery("select model.tehsilId , model.tehsilName from Tehsil model where model.district.districtId=?");
		query.setParameter(0, districtId);
		return query.list();
		
		
	}
	
	public List<Object[]> findTehsilsByConstituencyIdAndPublicationDateId(
			Long constituencyId, Long publicationDateId) {
		
		String queryString = "select distinct model.tehsil.tehsilId , model.tehsil.tehsilName from Booth model where " +
				"model.publicationDate.publicationDateId = ? and model.constituency.constituencyId = ?";
		
		Query query = getSession().createQuery(queryString);
		
		query.setParameter(0, publicationDateId);
		query.setParameter(1, constituencyId);
		
		return query.list();
		
		
		
	}
	
	public List<Object[]> findAllTehsilsByConstituencyIdAndPublicationDateId(
			Long constituencyId, Long publicationDateId) {
		
		String queryString = "select distinct model.tehsil.tehsilId , model.tehsil.tehsilName from Booth model where " +
				"model.publicationDate.publicationDateId = ? and model.constituency.constituencyId = ? and model.localBody is null";
		
		Query query = getSession().createQuery(queryString);
		
		query.setParameter(0, publicationDateId);
		query.setParameter(1, constituencyId);
		
		return query.list();
		
		
		
	}
	public List<Object[]> findAllLocalElecBodyByConstituencyIdAndPublicationDateId(
			Long constituencyId, Long publicationDateId) {
		
		String queryString = "select distinct model.localBody.localElectionBodyId , model.localBody.name,model.localBody.electionType.electionType from Booth model where " +
				"model.publicationDate.publicationDateId = ? and model.constituency.constituencyId = ? and model.localBody is not null";
		
		Query query = getSession().createQuery(queryString);
		
		query.setParameter(0, publicationDateId);
		query.setParameter(1, constituencyId);
		
		return query.list();
		
		
		
	}
	
	public String getTehsilNameByTehsilId(Long tehsilId)
	{
		Query query = getSession().createQuery("select model.tehsilName from Tehsil model where model.tehsilId = :tehsilId");
		query.setParameter("tehsilId",tehsilId);
		return (String)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTehsilNameByTehsilIdsList(List<Long> tehsilIdsList)
	{
		Query query = getSession().createQuery("select model.tehsilId,model.tehsilName from Tehsil model where model.tehsilId in (:tehsilIdsList) order by model.tehsilName ");
		query.setParameterList("tehsilIdsList", tehsilIdsList);
		return query.list();
	}
	
	public String getTehsilNameById(Long tehsilId)
	{
		Query query = getSession().createQuery("select model.tehsilName from Tehsil model where model.tehsilId =:tehsilId ");
		query.setParameter("tehsilId", tehsilId);
		return (String) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestAssemblyConstitueciesOfTehsil1(Long tehsilId){
		return getHibernateTemplate().find("Select model.district.state.stateId,model.district.state.stateName," +
				" model.district.districtId,model.district.districtName" +
				" from Tehsil model where model.tehsilId = ? ",tehsilId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTehsilList()
	{
		return getHibernateTemplate().find(" select model.tehsilId,model.tehsilName,model.district.districtId from Tehsil model ");
		
	}
	
	public Long getStateByTehsilId(Long tehsilId){
		Query query = getSession().createQuery("Select model.district.state.stateId from Tehsil model where model.tehsilId = :tehsilId ");
		query.setParameter("tehsilId", tehsilId);
		return (Long) query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getMandalsByDistricts(List<Long> districtIds) {
		Query query = getSession().createQuery("select distinct model.tehsilId , model.tehsilName from Tehsil model where model.district.districtId in(:districtIds)  order by model.tehsilName");
		query.setParameterList("districtIds",districtIds);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMandalsForRegion(String region) {
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.tehsilId,model.tehsilName from Tehsil model where ");
		if(region.equalsIgnoreCase("Telangana"))
		str.append("model.district.districtId between 1 and 10 order by model.district.districtName");
		else if(region.equalsIgnoreCase("Andhra Pradesh"))
		{
			str.append("model.district.districtId between 11 and 23 order by model.district.districtName");	
		}
		else
		{
			str.append("model.district.districtId between 1 and 23 order by model.district.districtName");	
		}
		Query query = getSession().createQuery(str.toString());
		return query.list();
		
	}
	
	public List<Object[]> getTehsilsByConstituencyIdsListAndPublicationDateId(List<Long> constituencyIdList, Long publicationDateId) 
	{
		String queryString = "select distinct model.tehsil.tehsilId , model.tehsil.tehsilName from Booth model where " +
				"model.publicationDate.publicationDateId = :publicationDateId and model.constituency.constituencyId in (:constituencyIdList) and model.localBody is null order by model.tehsil.tehsilName ";
		
		Query query = getSession().createQuery(queryString);
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("constituencyIdList", constituencyIdList);
		
		return query.list();
	}
	
	public List<Object[]> getAllLocalElecBodyListByConstituencyIdsListAndPublicationDateId(List<Long> constituencyIdList, Long publicationDateId) 
	{
		String queryString = "select distinct model.localBody.localElectionBodyId , model.localBody.name,model.localBody.electionType.electionType from Booth model where " +
				"model.publicationDate.publicationDateId = :publicationDateId and model.constituency.constituencyId in (:constituencyIdList) and model.localBody is not null";
		
		Query query = getSession().createQuery(queryString);
		
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameterList("constituencyIdList", constituencyIdList);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTehsilDetailsByStateId(Long stateId,Long publicationDateId){
		Object[] params = {stateId, publicationDateId};
		return getHibernateTemplate().find("select distinct model.tehsil.tehsilId , model.tehsil.tehsilName from Booth model where model.tehsil.district.state.stateId = ? and model.publicationDate.publicationDateId = ? ", params);
	}
	public List<Long> getAllTehsilDetails(List<Long> districtIds){
		
		Query query = getSession().createQuery("select model.tehsilId from Tehsil model where model.district.districtId in (:districtIds)");
		query.setParameterList("districtIds", districtIds);
		return query.list();
		
	}
	public List<Object[]> getMandalsForConstituencyId( Long constituencyId) {

		Query query = getSession().createQuery(" select distinct model.tehsil.tehsilId , model.tehsil.tehsilName from Booth model where " +
				" model.constituency.constituencyId =:constituencyId ");
		
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	public List<Object[]> getAllTehsilList( Long stateId) {

		Query query = getSession().createQuery(" select distinct model.tehsilId , model.tehsilName from Tehsil model where " +
				" model.district.state.stateId =:stateId ");
		
		query.setParameter("stateId", stateId);
		return query.list();
	}

	
}
