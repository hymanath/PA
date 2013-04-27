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
	

	
}
