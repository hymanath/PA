package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Panchayat;

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
		return getHibernateTemplate().find("select model.panchayatId, model.panchayatName from Panchayat model where model.tehsil.tehsilId in(select model1.tehsil.tehsilId from DelimitationConstituencyMandal model1 where model1.delimitationConstituency.year =2009 and model1.delimitationConstituency.constituency.constituencyId = ?)",constituencyId);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsBymandalId(Long mandalId)
	{
		return getHibernateTemplate().find("select model.panchayatId, model.panchayatName from Panchayat model where model.tehsil.tehsilId =?",mandalId);	
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
				" model.tehsil.tehsilId in (:ids )");
		query.setParameterList("ids", ids);
		return query.list();
	}
	
	public List<Object[]> getAllPanchayatsInAListOfMandals(List<Long> ids)
	{
		String queryString = "select distinct(model.panchayatId), model.panchayatName,model.tehsil.tehsilName from Panchayat model where model.tehsil.tehsilId in (:ids)";
		
		Query query = getSession().createQuery(queryString);
		query.setParameterList("ids", ids);
		return query.list();
	}
} 
