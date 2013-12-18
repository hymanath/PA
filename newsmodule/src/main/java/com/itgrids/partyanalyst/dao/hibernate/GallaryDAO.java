package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.model.Gallary;
import com.itgrids.partyanalyst.utils.IConstants;

public class GallaryDAO extends GenericDaoHibernate<Gallary, Long> implements IGallaryDAO{
	
	public GallaryDAO(){
		super(Gallary.class);
	}
	
	public List<Object[]> getCandidateGallaryDetail(Long candidateId,int firstResult,int maxResult,String type) {
		 	 
		Query query = getSession().createQuery("select model.gallaryId,model.name,model.description,model.createdDate," +
				"model.updateddate from Gallary model  where model.candidate.candidateId=:candidateId and  " +
				"model.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate " +
				"and (select count(*)  from FileGallary model1 where model1.gallary.gallaryId = model.gallaryId and model1.isDelete = :isDelete " +
				"and model1.isPrivate = :isPrivate ) > 0 order by model.createdDate desc");
		query.setLong("candidateId", candidateId);
		query.setString("type", type);
		query.setString("isDelete", "false");
		query.setString("isPrivate","false" );
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
			
						
		return query.list(); 
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGallariesByCandidateId(Long candidateId,String contentType)
	{
		Object[] params = {candidateId,contentType};
		return getHibernateTemplate().find("select model.gallaryId,model.name from Gallary model where model.candidate.candidateId = ? and model.contentType.contentType = ? and model.isDelete = 'false' order by model.name asc",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGallariesByCandidateIds(List<Long> candidateIds,String contentType)
	{
		
		
		Query query = getSession().createQuery("select distinct model.gallaryId,model.name,model.candidate.firstname,model.candidate.lastname from Gallary model where model.candidate.candidateId in(:candidateIds) and model.contentType.contentType = :contentType and model.isDelete = 'false' order by model.name asc");
		
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("contentType", contentType);
		
		return query.list();
	}
	
	public Integer deleteGallary(Long gallaryId)
	{
		StringBuilder query = new StringBuilder();
		query.append("update Gallary model set model.isDelete = 'true' where model.gallaryId = ?");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, gallaryId);
		return queryObject.executeUpdate();	
	}
	
	public List<Object[]> getCandidatesGallaryDescForUpdate(Long gallaryId , Long candidateId)
		{
		  Query query = getSession().createQuery("select model.gallaryId,model.name,model.description," +
				"model.isPrivate from Gallary model  where model.candidate.candidateId=:candidateId and  " +
				"model.gallaryId = :gallaryId");
		   query.setLong("candidateId", candidateId);
		   query.setLong("gallaryId", gallaryId);			
		   return query.list(); 
		
		}
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialPageGallaryDescForUpdate(Long gallaryId )
		{
		  Query query = getSession().createQuery("select model.gallaryId,model.name,model.description," +
				"model.isPrivate from Gallary model  where model.gallaryId = :gallaryId " );
		   query.setLong("gallaryId", gallaryId);			
		   return query.list(); 
		
		}
	
	@SuppressWarnings("unchecked")
	public List<Object> getDetailsOfVisibility(Long gallaryId)
    {
		return getHibernateTemplate().find("select model.isPrivate from Gallary model where model.gallaryId=?",gallaryId); 
    }
	
	@SuppressWarnings("unchecked")
	public List<Object> getOtherGalleries(Long candidateId,List<Long> gallaryIds,String contentType)
	{
		Query query = getSession().createQuery("select model.gallaryId from Gallary model where model.contentType.contentType = ? and "+
				" model.candidate.candidateId = ? and model.gallaryId not in(:gallaryIds) and model.isPrivate = 'false' and model.isDelete = 'false'");
		query.setParameter(0, contentType);
		query.setParameter(1, candidateId);
		query.setParameterList("gallaryIds", gallaryIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getOtherGalleriesForCandidate(Long candidateId,List<Long> gallaryIds,String contentType)
	{
		Query query = getSession().createQuery("select model.gallaryId from Gallary model where model.contentType.contentType = ? and "+
				" model.candidate.candidateId = ? and model.gallaryId not in(:gallaryIds) and model.isDelete = 'false'");
		query.setParameter(0, contentType);
		query.setParameter(1, candidateId);
		query.setParameterList("gallaryIds", gallaryIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> checkGallaryNameExistenceForSelectedCandidate(Long candidateId, String gallaryName, String contentType)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select model.gallaryId, model.name from Gallary model where model.candidate.candidateId = :candidateId ");
		stringBuilder.append(" and model.name = :gallaryName and model.contentType.contentType = :contentType ");
		stringBuilder.append(" and model.isDelete = :isDelete ");
		
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		queryObj.setParameter("candidateId", candidateId);
		queryObj.setParameter("gallaryName", gallaryName);
		queryObj.setParameter("contentType", contentType);
		queryObj.setParameter("isDelete", IConstants.FALSE);
		return queryObj.list();
	}
	
	public List<Object[]> getAllGallaries(String contentType)
	{
		Query query = getSession().createQuery("select distinct model.gallaryId,model.name from Gallary model where model.contentType.contentType = :contentType");
		
		query.setParameter("contentType", contentType);
		return query.list();
		
	}
	
	public List<Object[]> getAllGallariesForParty(Long partyId ,String contentType)
	{
		
		Query query = getSession().createQuery("select model.gallery.gallaryId , model.gallery.name" +
				" from PartyGallery model where model.party.partyId = :partyId and model.gallery.contentType.contentType = :contentType");
		
		query.setParameter("partyId", partyId);
		query.setParameter("contentType", contentType);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGallariesForSelectedCategory(Long categoryId)
	{
	  Query query = getSession().createQuery(" select distinct model.gallaryId, model.name from Gallary model where model.category.categoryId =:categoryId " +
	  		"order by model.name ");
	  
	  query.setParameter("categoryId", categoryId);
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestGalleries()
	{
		Query query = getSession().createQuery("select model.gallaryId, model.name from Gallary model order by model.gallaryId desc ");
		query.setFirstResult(0);
		query.setMaxResults(5);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGallaries()
	{
		return getHibernateTemplate().find("select model.gallaryId, model.name from Gallary model where model.isDelete = 'false' and model.isNewsPortal = 'true' order by model.name");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGalleriesForIds(List<Long> galleryIds)
	{
		Query query = getSession().createQuery("select model.gallaryId, model.name from Gallary model where model.gallaryId in (:galleryIds)");
		query.setParameterList("galleryIds", galleryIds);
		return query.list();
		
	}
}
