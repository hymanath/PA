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
		return getHibernateTemplate().find("select model.gallaryId,model.name from Gallary model where model.candidate.candidateId = ? and model.contentType.contentType = ? ",params);
	}
}
