package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.utils.IConstants;

public class PanchayatDAO extends GenericDaoHibernate<Panchayat,Long> implements IPanchayatDAO{

	public PanchayatDAO()
	{
		super(Panchayat.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsByTehsilId(Long tehsilId)
	{
		return getHibernateTemplate().find("select model.panchayatId, model.panchayatName from Panchayat model where model.tehsil.tehsilId = ?",tehsilId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPanchayatIdsByTehsilId(Long tehsilId)
	{
		return getHibernateTemplate().find("select distinct(model.panchayatId) from Panchayat model where model.tehsil.tehsilId = ?",tehsilId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPanchayatIdsByTehsilIds(List<Long> tehsilIds)
	{
		
		Query query = getSession().createQuery("select distinct(model.panchayatId) from Panchayat model where model.tehsil.tehsilId in (:tehsilIds)");
		
		query.setParameterList("tehsilIds", tehsilIds);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatIdsByMandalIdsList(List<Long> mandalIdsList)
	{
		Query query = getSession().createQuery("select distinct(model.panchayatId),model.tehsil.tehsilId from Panchayat model where model.tehsil.tehsilId in (:mandalIdsList)");
		query.setParameterList("mandalIdsList",mandalIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsByConstituencyId(Long constituencyId)
	{
		return getHibernateTemplate().find("select model.panchayatId, model.panchayatName from Panchayat model where model.tehsil.tehsilId in(select model1.tehsil.tehsilId from DelimitationConstituencyMandal model1 where model1.delimitationConstituency.year =2009 and model1.delimitationConstituency.constituency.constituencyId = ?) order by model.panchayatName",constituencyId);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsBymandalId(Long mandalId)
	{
		return getHibernateTemplate().find("select model.panchayatId, model.panchayatName from Panchayat" +
				"  model where model.tehsil.tehsilId =? order by model.panchayatName ",mandalId );	
	}
	public List<Object> getPanchayatsBymandalId1(Long mandalId)
	{
		List<Object> panchayats= getHibernateTemplate().find("select model from Panchayat model where model.tehsil.tehsilId =?",mandalId);	
	return panchayats;
	}
	
	
	/*public Long getPanchayatiesCount(Long id,String type)
	  {
		  StringBuilder str = new StringBuilder();
		  str.append("select distinct count(model.panchayatId) from Panchayat model where");

		  if(type.equalsIgnoreCase("constituency"))
			  str.append(" model.tehsil.tehsilId in(select model1.tehsil.tehsilId from DelimitationConstituencyMandal model1 where model1.delimitationConstituency.year =2009 and model1.delimitationConstituency.constituency.constituencyId = :id)");
		  else if(type.equalsIgnoreCase("mandal"))
		 str.append(" model.tehsil.tehsilId = :id ");
		  Query query =getSession().createQuery(str.toString());
		  query.setParameter("id",id);
		  
		  return (Long)query.uniqueResult();
		  
	  }
	*/
	public List<Object[]> getPanchayatiesCount(Long id,String type,Long publicationId)
	  {
		  StringBuilder str = new StringBuilder();
		  //str.append("select distinct count(model.panchayatId) from Panchayat model where");
		  str.append("select model.panchayatId,model.panchayatName from Panchayat model where");
		  if(type.equalsIgnoreCase("constituency")){
			  str.append(" model.tehsil.tehsilId in(select model1.tehsil.tehsilId from DelimitationConstituencyMandal model1 where model1.delimitationConstituency.year =2009 and model1.delimitationConstituency.constituency.constituencyId = :id)" +
			  		" and model.tehsil.tehsilId in(select model2.tehsil.tehsilId from Booth model2 where model2.constituency.constituencyId = :id and model2.localBody is null and model2.publicationDate.publicationDateId = :publicationId)");
		  }
		else if(type.equalsIgnoreCase("mandal"))
		 str.append(" model.tehsil.tehsilId = :id ");
		  Query query =getSession().createQuery(str.toString());
		  query.setParameter("id",id);
		  if(type.equalsIgnoreCase("constituency")){
		   query.setParameter("publicationId",publicationId);
		  }
		 // return (Long)query.uniqueResult();
		  return query.list();
		  
	  }
	
	@SuppressWarnings("unchecked")
	public List<Long> getPanchayatIdsBytehsilIdsList(List<Long> mandalIdsList)
	{
		Query query = getSession().createQuery("select distinct(model.panchayatId) from Panchayat model where model.tehsil.tehsilId in (:mandalIdsList)");
		query.setParameterList("mandalIdsList", mandalIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPanchayatIdsListByMandalId(Long mandalId)
	{
		return getHibernateTemplate().find("select distinct(model.panchayatId) from Panchayat model where model.tehsil.tehsilId = ?",mandalId);
	}
	
	public List<Object[]> getPanchayatsInAMandal(Long id)
	{
		return getHibernateTemplate().find("select distinct(model.panchayatId), model.panchayatName from Panchayat model where model.tehsil.tehsilId = ?",id);
	}
	
	public List<Object[]> getAllPanchaytesInAConstituency(List<Long> ids)
	{
		
		Query query = getSession().createQuery("select distinct(model.panchayatId), model.panchayatName from Panchayat model where" +
				" model.tehsil.tehsilId in (:ids ) order by model.panchayatName");
		query.setParameterList("ids", ids);
		return query.list();
	}
	
	public List<Object[]> getAllPanchayatsInAListOfMandals(List<Long> ids)
	{
		String queryString = "select distinct(model.panchayatId), model.panchayatName,model.tehsil.tehsilName from Panchayat model where model.tehsil.tehsilId in (:ids) order by model.panchayatName";
		
		Query query = getSession().createQuery(queryString);
		query.setParameterList("ids", ids);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsByPanchayatIdsList(List<Long> panchayatIdsList)
	{
		Query query = getSession().createQuery("select model.panchayatId,model.panchayatName from Panchayat model where model.panchayatId in(:panchayatIdsList) order by model.panchayatName ");
		query.setParameterList("panchayatIdsList", panchayatIdsList);
		return query.list();
	}
	
	public String getPanchayatNameById(Long panchayatId)
	{
		Query query = getSession().createQuery(" select model.panchayatName from Panchayat model where model.panchayatId =:panchayatId ");
		query.setParameter("panchayatId", panchayatId);
		return (String) query.uniqueResult();
		
	}
	
	public List<Object[]> getPanchaytsForConstituencyList(List<Long> maldalIds)
	{
		Query query = getSession().createQuery("select model.panchayatId,model.panchayatName,model.tehsil.tehsilName from Panchayat model " +
				" where  model.tehsil.tehsilId in (:maldalIds)");
		query.setParameterList("maldalIds", maldalIds);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPanchayatsByMangals(List<Long> tehsilIds)
	{
		Query query = getSession().createQuery("select distinct model.panchayatId from Panchayat model " +
				" where  model.tehsil.tehsilId in (:tehsilIds)");
		query.setParameterList("tehsilIds", tehsilIds);
		
		return query.list();
				
	}
	
	public List<Object[]> getCasteInPanchayat(List<Long> panchayatIds){
		Query query = getSession().createQuery("select model1.panchayat.panchayatId,model1.panchayat.panchayatName,count(model.voter.voterId),model.casteState.caste.casteName from UserVoterDetails model,PanchayatHamlet model1  "+
				"where  model1.hamlet.hamletId = model.hamlet.hamletId and model1.panchayat.panchayatId in (:panchayatIds) group by model.casteState.caste.casteId order by model.hamlet.hamletId,count(model.voter.voterId) desc");
		query.setParameterList("panchayatIds", panchayatIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatDetails()
	{
		return getHibernateTemplate().find(" select model.panchayatId,model.panchayatName,model.tehsil.tehsilId from Panchayat model ");
	}
	
	public List<Object[]> getMandalsAnaPanchayays(List<Long> mandalIds)
	{
		Query query = getSession().createQuery("select model.panchayatId,model.panchayatName," +
				" model.tehsil.tehsilId,model.tehsil.tehsilName from Panchayat model " +
				" where model.tehsil.tehsilId in (:mandalIds)");
		query.setParameterList("mandalIds", mandalIds);
		return query.list();
	}
	
	public List<Object[]> getPanchayatIdsForMandals(List<Long> mandalIdsList,Set<Long> panchayatIds)
	{
		Query query = getSession().createQuery("select distinct(model.panchayatId),model.tehsil.tehsilId from Panchayat model where model.tehsil.tehsilId in (:mandalIdsList) and model.panchayatId not in (:panchayatIds) ");
		query.setParameterList("mandalIdsList",mandalIdsList);
		query.setParameterList("panchayatIds",panchayatIds);
		return query.list();
	}
	
	public List<Object[]> getMandalAndPanchayatIds(Set<Long> partialPanchayatIds)
	{
		Query query = getSession().createQuery("select distinct(model.panchayatId),model.tehsil.tehsilId from Panchayat model where model.panchayatId in (:partialPanchayatIds)  ");
		query.setParameterList("partialPanchayatIds",partialPanchayatIds);
		return query.list();
	}
	
	public List<Object[]> getPanchayatsByPanchayatIdsListAlongMandal(List<Long> panchayatIdsList)
	{
		Query query = getSession().createQuery("select model.panchayatId,model.panchayatName,model.tehsil.tehsilName from Panchayat model where model.panchayatId in(:panchayatIdsList) order by model.panchayatName ");
		query.setParameterList("panchayatIdsList", panchayatIdsList);
		return query.list();
	}
	
	public List<Long> getPanchayatIdsForDelemationEffect(Long constituencyId,Long year)
	{
		Query query = getSession().createQuery("select distinct P.panchayatId from Panchayat P , " +
				" PanchayatHamlet PH, HamletBoothElection HBE where P.panchayatId = PH.panchayat.panchayatId " +
				" and PH.hamlet.hamletId = HBE.hamlet.hamletId and HBE.boothConstituencyElection.booth.constituency.constituencyId = :constituencyId " +
				" and HBE.boothConstituencyElection.booth.year = :year");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("year", year);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsAndMandalsForPanchayatIdsList(List<Long> panchayatIdsList)
	{
		Query query = getSession().createQuery("Select model.tehsil.tehsilId,model.tehsil.tehsilName,model.panchayatId,model.panchayatName from Panchayat model where model.panchayatId in (:panchayatIdsList) order by model.tehsil.tehsilName,model.panchayatName ");
		query.setParameterList("panchayatIdsList",panchayatIdsList);
		return query.list();
	}
	
	public List<Object[]> getPanchayatNamesByIds(List<Long> panchayatIds)
	{
		Query query = getSession().createQuery(" select model.panchayatId,model.panchayatName from Panchayat model where model.panchayatId in(:panchayatIds) ");
		query.setParameterList("panchayatIds", panchayatIds);
		return query.list();
		
	}
	public List<Object[]> getAllPanchayatsInMandals(List<Long> ids)
	{
		String queryString = "select distinct model.panchayatId, model.panchayatName,model.tehsil.tehsilName from Panchayat model where model.tehsil.tehsilId in (:ids) order by model.tehsil.tehsilName,model.panchayatName";
		
		Query query = getSession().createQuery(queryString);
		query.setParameterList("ids", ids);
		return query.list();
	}
	
	public List<Object[]> getAllPanchayatsWithTehsilIdsInMandals(List<Long> ids){
		String queryString = "select distinct model.panchayatId," +
				" model.panchayatName," +
				" model.tehsil.tehsilName," +
				" model.tehsil.tehsilId " +
				" from Panchayat model " +
				" where model.tehsil.tehsilId in (:ids) " +
				" order by model.tehsil.tehsilName,model.panchayatName";
		
		Query query = getSession().createQuery(queryString);
		query.setParameterList("ids", ids);
		return query.list();
	}
	
	public List<Object[]> getAllPanchayatsInMandalsByPublciationId(Long constituencyId,List<Long> ids,Long publicationId)
	{
		String queryString = "select distinct B.panchayat.panchayatId, B.panchayat.panchayatName,B.tehsil.tehsilName from  Booth B " +
				"  where B.publicationDate.publicationDateId = :publicationId and B.constituency.constituencyId = :constituencyId " +
				" and B.tehsil.tehsilId in (:ids) order by B.tehsil.tehsilName,B.panchayat.panchayatName";
		
		Query query = getSession().createQuery(queryString);
		query.setParameterList("ids", ids);
		query.setParameter("publicationId", publicationId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public Long getPanchayatIdByTehsilIdAndPanchayatName(Long tehsilId,String panchayatName)
	{
		Query query = getSession().createQuery("Select model.panchayatId from Panchayat model where model.tehsil.tehsilId = :tehsilId and model.panchayatName = :panchayatName");
		query.setParameter("tehsilId",tehsilId);
		query.setParameter("panchayatName",panchayatName);
		return (Long)query.uniqueResult();
	}
	public List<Object[]> getAllPanchayatList(Long stateId)
	{
		Query query = getSession().createQuery("Select model.panchayatId, model.panchayatName from Panchayat model where model.tehsil.district.state.stateId = :stateId");     
		query.setParameter("stateId",stateId);
		return query.list();  
	}
	
	public List<Object[]> getPanchayatList(Long tehsilId){
		Query query=getSession().createQuery("select distinct model.panchayatId,model.panchayatName"
				+ " from Panchayat model"
				+ " where model.tehsil.tehsilId=:tehsilId");
		query.setParameter("tehsilId", tehsilId);
		return query.list();
	}
	public Long getHamletCountOnPanchayatIds(List<Long> panchIds)
	{
		String queryString = "select count(distinct model.panchayatHamletId) from PanchayatHamlet model where model.panchayat.panchayatId in (:ids)";
		
		Query query = getSession().createQuery(queryString);
		query.setParameterList("ids", panchIds);
		return (Long) query.uniqueResult();
	}

	public Long getBoothIdsCount(Long locationTypeId,List<Long> constituencyIds,Long publicationDateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(distinct model.boothId) from Booth model where model.publicationDate.publicationDateId =:publicationDateId and ");
		if(locationTypeId != null && locationTypeId.longValue()>0){
			if(locationTypeId  == 2l ||locationTypeId == 3l || locationTypeId == 4l || locationTypeId == 10l){
				sb.append("	model.constituency.constituencyId in (:constituencyId)");
			}else if(locationTypeId == 5l){
				sb.append("	model.tehsil.tehsilId in (:constituencyId)");
			}else if(locationTypeId == 6l){
				sb.append("	model.panchayat.panchayatId in (:constituencyId)");
			}else if(locationTypeId == 7l){
				sb.append("	model.localBody.localElectionBodyId in (:constituencyId)");
			}
		}
	
	Query query = getSession().createQuery(sb.toString());
	query.setParameterList("constituencyId", constituencyIds);
	query.setParameter("publicationDateId", publicationDateId);
	return (Long) query.uniqueResult();
	
	}
	
	public List<Object[]> getAllPanchayatsInMandalsList(List<Long> ids)
	{
		StringBuilder queryString = new StringBuilder();
		queryString.append( "select distinct model.panchayatId, model.panchayatName,model.tehsil.tehsilName from Panchayat model where  model.isDeleted='N'  ") ;
		
		if(ids != null && ids.size()>0){
			queryString.append(" and model.tehsil.tehsilId in (:ids) ");
		}
		
		queryString.append(" order by model.tehsil.tehsilName,model.panchayatName ");
		
		Query query = getSession().createQuery(queryString.toString());
		if(ids != null && ids.size()>0)
		query.setParameterList("ids", ids);
		return query.list();
	}
	
	public List<Long> getPanchayatInfoOfTehsilByPanchayatName(Long tehsilId,String panchayatName){
		Query query=getSession().createQuery("select distinct model.panchayatId "
				+ " from Panchayat model"
				+ " where model.tehsil.tehsilId=:tehsilId " +
				" and model.panchayatName = :panchayatName ");
		query.setParameter("tehsilId", tehsilId);
		query.setParameter("panchayatName", panchayatName);
		return query.list();
	}
}