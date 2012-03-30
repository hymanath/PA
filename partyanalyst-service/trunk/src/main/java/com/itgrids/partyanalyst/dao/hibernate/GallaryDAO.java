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
				"model.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate order by model.createdDate desc");
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
				" model.candidate.candidateId = ? and model.gallaryId not in(:gallaryIds)");
		query.setParameter(0, contentType);
		query.setParameter(1, candidateId);
		query.setParameterList("gallaryIds", gallaryIds);
		return query.list();
	}
	
}
