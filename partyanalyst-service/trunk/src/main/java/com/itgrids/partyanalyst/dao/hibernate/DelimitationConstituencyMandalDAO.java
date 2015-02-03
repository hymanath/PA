package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.utils.IConstants;

public class DelimitationConstituencyMandalDAO extends GenericDaoHibernate<DelimitationConstituencyMandal, Long> implements
IDelimitationConstituencyMandalDAO {

	public DelimitationConstituencyMandalDAO() {
		super(DelimitationConstituencyMandal.class);
	}

	@SuppressWarnings("unchecked")
	public List<DelimitationConstituencyMandal> findDelConstMandalByDelConstID(
			Long delimitationConstituencyID) {
		return getHibernateTemplate().find("from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID =?", 
				delimitationConstituencyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<DelimitationConstituencyMandal> findDelConstMandalByDelConstID(
			Long delimitationConstituencyID,Long tehsilId) {
		
		Object[] params = {delimitationConstituencyID,tehsilId};
		return getHibernateTemplate().find("from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID =? and model.tehsil.tehsilId = ?", 
				params);
	}

	@SuppressWarnings("unchecked")
	public List<Tehsil> getTehsilsByDelimitationConstituencyID(Long delimitationConstituencyID) {
		return getHibernateTemplate().find("Select distinct model.tehsil from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID =? order by model.tehsil.tehsilName", 
				delimitationConstituencyID);
	}
	
	
	public List<Tehsil> getTehsilsByDelimitationConstituencyIds(List<Long> delimitationConstituencyIds) {
		
		Query query = getSession().createQuery("Select model.tehsil from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID in(:delimitationConstituencyIds) order by model.tehsil.tehsilName");


		 query.setParameterList("delimitationConstituencyIds", delimitationConstituencyIds);
		 return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List getStateDistConstituencyMandalByMandalID(Long mandalID){
		return getHibernateTemplate().find("Select model.tehsil.district.state.stateId, model.tehsil.district.state.stateName," +
				" model.tehsil.district.districtId, model.tehsil.district.districtName," +
				" model.delimitationConstituency.constituency.constituencyId, model.delimitationConstituency.constituency.name," +
				" model.tehsil.tehsilName, model.delimitationConstituency.year from DelimitationConstituencyMandal model where model.tehsil.tehsilId=?" +
				" group by model.delimitationConstituency.year, model.delimitationConstituency.constituency.name" +
				" order by model.delimitationConstituency.year, model.delimitationConstituency.constituency.name ",mandalID);
	}
	
	
	public List getMandalsOfConstituency(Long constituencyId){
		return getHibernateTemplate().find("select model.tehsil.tehsilId, model.tehsil.tehsilName, model.delimitationConstituency.year, " +
				"model.isPartial from DelimitationConstituencyMandal model where model.delimitationConstituency.constituency.constituencyId = ? " +
				"order by model.delimitationConstituency.year desc,model.tehsil.tehsilName ", constituencyId);
	}
	
	@SuppressWarnings("unchecked")
	public List getLatestMandalDetailsForAConstituency(Long constituencyId){
		return getHibernateTemplate().find("select model.tehsil.tehsilId, model.tehsil.tehsilName" +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID = " +
				" (select model1.delimitationConstituencyID from DelimitationConstituency model1 where model1.constituency.constituencyId = ?" +
				" group by model1.constituency.constituencyId order by model1.year desc) ", constituencyId);
	}
	
	public List getMandalDetailsForAConstituency(Long constituencyId,Long electionYear){
		Object[] parms = {constituencyId,electionYear};
		return getHibernateTemplate().find("select model.tehsil.tehsilId, model.tehsil.tehsilName" +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.constituency.constituencyId = ? " +
				" and model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1 where model1.year <= ?)" +
				" ", parms);
	}
	

	@SuppressWarnings("unchecked")
	public List<Tehsil> getLatestMandalDetailsForAConstituencies(String constituencyIds){
		return getHibernateTemplate().find("select model.tehsil" +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID in " +
				" (select model1.delimitationConstituencyID from DelimitationConstituency model1 where model1.constituency.constituencyId in("+constituencyIds+")"+
				" group by model1.constituency.constituencyId order by model1.year desc) ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getLatestMandalIdsByConstituenciesIds(List<Long> constituencyIds){
		StringBuilder query = new StringBuilder();
		query.append("select model.tehsil.tehsilId from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID in " +
				" (select model1.delimitationConstituencyID from DelimitationConstituency model1 where model1.constituency.constituencyId in( :constituencyIds)"+
				" group by model1.constituency.constituencyId order by model1.year desc) ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("constituencyIds", constituencyIds);
		return queryObject.list();
	}	
	
	public List getLatestAssemblyConstitueciesOfTehsil(Long tehsilId){
		return getHibernateTemplate().find("Select model.tehsil.district.state.stateId, model.tehsil.district.state.stateName," +
				" model.tehsil.district.districtId, model.tehsil.district.districtName," +
				" model.delimitationConstituency.constituency.constituencyId, model.delimitationConstituency.constituency.name" +
				" from DelimitationConstituencyMandal model where model.tehsil.tehsilId = ? and model.delimitationConstituency.year = " +
				" (select max(model1.year) from DelimitationConstituency model1)",tehsilId);
	}

	public List getLatestMandalsInConstituencies(String constituencyIds) {
		
		return getHibernateTemplate().find("select model.tehsil.tehsilId, model.tehsil.tehsilName" +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID in " +
				" (select model1.delimitationConstituencyID from DelimitationConstituency model1 where model1.constituency.constituencyId in("+constituencyIds+")"+
				" group by model1.constituency.constituencyId order by model1.year desc) ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestMandalsForAConstituency(Long delimconstituencyId) {
		
		return getHibernateTemplate().find("select model.dcm_id,model.tehsil.tehsilId, model.tehsil.tehsilName, model.isPartial " +
				" from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID = ? order by model.tehsil.tehsilName",delimconstituencyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsByConstituencyId(Long constituencyId)
	{
		return getHibernateTemplate().find("select model.tehsil.tehsilId from DelimitationConstituencyMandal model where model.delimitationConstituency.delimitationConstituencyID =?",constituencyId);
	}
	
	public List<Constituency> getConstituencyByTehsilId(Long tehsilId){
		
		return getHibernateTemplate()
				.find("select model.delimitationConstituency.constituency from model where model.tehsil.tehsilId = ?",
						tehsilId);
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConstituencyIdByMandalID(Long mandalId)
	{
		return getHibernateTemplate().find("select model.delimitationConstituency.constituency.constituencyId " +
				" from DelimitationConstituencyMandal model where model.tehsil.tehsilId = ? and " +
				"model.delimitationConstituency.year = (select max(model1.year) from DelimitationConstituency model1)",mandalId);
	}
	
	
	public List<Long> getMadalDetailsByDelimitationConstituencyIds(List<Long> delimitationConstituencyIds)
	{
		
		Query query = getSession().createQuery("select distinct(model.tehsil.tehsilId) from DelimitationConstituencyMandal model " +
				"where model.delimitationConstituency.delimitationConstituencyID in(:delimitationConstituencyIds)");
		
		query.setParameterList("delimitationConstituencyIds", delimitationConstituencyIds);
		
		return query.list();
		
	}
	
	public List<Object[]> getMadalDtlsByDelimitationConstituencyIds(List<Long> delimitationConstituencyIds)
	{
		
		Query query = getSession().createQuery("select distinct(model.tehsil.tehsilId) , model.delimitationConstituency.delimitationConstituencyID,model.delimitationConstituency.year from DelimitationConstituencyMandal model " +
				"where model.delimitationConstituency.delimitationConstituencyID in(:delimitationConstituencyIds)");
		
		query.setParameterList("delimitationConstituencyIds", delimitationConstituencyIds);
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getIspartialForMandalByMandalIdsList(List<Long> mandalIdsList,Long year)
	{
		Query query = getSession().createQuery(" select model.tehsil.tehsilId, model.isPartial from DelimitationConstituencyMandal model where model.tehsil.tehsilId in (:mandalIdsList) and model.delimitationConstituency.year =:year ");
		query.setParameterList("mandalIdsList", mandalIdsList);
		query.setParameter("year", year);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTehsilsForRuralConstituencyes(List<Long> constituencyIds,Long year)
	{
		Query query = getSession().createQuery("select distinct model.tehsil.tehsilId , model.tehsil.tehsilName " +
				"  from DelimitationConstituencyMandal model where " +
				" model.delimitationConstituency.constituency.constituencyId in (:constituencyIds) " +
				" and model.delimitationConstituency.year = :year");
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("year", year);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTehsilByDelimitationConstituencyIds(List<Long> delimitationConstituencyIDs){
		Query query = getSession().createQuery("Select distinct model.tehsil.tehsilId,model.tehsil.tehsilName, model.delimitationConstituency.constituency.name from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID in (:delimitationConstituencyIDs) order by model.tehsil.tehsilName");
		query.setParameterList("delimitationConstituencyIDs", delimitationConstituencyIDs);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyByMandals(List<Long> mandalIds)
	{
		Query query = getSession().createQuery("select distinct model.delimitationConstituency.constituency.constituencyId , model.delimitationConstituency.constituency.name " +
				" from DelimitationConstituencyMandal model where " +
				" model.tehsil.tehsilId in (:mandalIds)");
		query.setParameterList("mandalIds", mandalIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMandalIdsByConstituencyId(Long constituencyId,Long year)
	{
		Query query = getSession().createQuery("select distinct model.tehsil.tehsilId,model.tehsil.tehsilName from DelimitationConstituencyMandal model " +
				" where model.delimitationConstituency.constituency.constituencyId = :constituencyId  " +
				" and model.delimitationConstituency.year = :year");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("year", year);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAssemblyConstituencyAndMandalsInAState(Long stateId)
	{
		Query query = getSession().createQuery("select model.delimitationConstituency.constituency.constituencyId, model.tehsil.tehsilId, model.isPartial from DelimitationConstituencyMandal model " +
				" where model.delimitationConstituency.constituency.state.stateId = :stateId and model.delimitationConstituency.constituency.electionScope.electionType.electionType = :electionType and " +
				" model.delimitationConstituency.constituency.deformDate is null and model.delimitationConstituency.year = :year order by model.delimitationConstituency.constituency.constituencyId, model.tehsil.tehsilId ");
		
		query.setParameter("stateId",stateId);
		query.setParameter("electionType",IConstants.ASSEMBLY_ELECTION_TYPE);
		query.setParameter("year",2009l);
		return query.list();
	}
	
	public List<Object[]> getTehsilsByDelimitationConstituencyIDs(List<Long> ids) {
		Query query = getSession().createQuery("select distinct model.tehsil.tehsilId,model.delimitationConstituency.constituency.constituencyId " +
				" from DelimitationConstituencyMandal model where " +
				"model.delimitationConstituency.delimitationConstituencyID in(:ids) ");
		query.setParameterList("ids",ids);
		 return query.list();
	}
}
