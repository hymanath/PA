package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.model.Gallary;

public class GallaryDAO extends GenericDaoHibernate<Gallary, Long> implements IGallaryDAO{
	
	public GallaryDAO(){
		super(Gallary.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateGallaryDetail(Long candidateId,String type,String isDelete) {
		 
		Object[] parameters = {candidateId,type,isDelete};
		 
		return getHibernateTemplate().find("select model.gallaryId,model.name,model.description,model.createdDate," +
				"model.updateddate from Gallary model  where model.candidate.candidateId= ? and  " +
				"model.contentType.contentType= ?  and model.isDelete = ? ",parameters);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGallariesByCandidateId(Long candidateId)
	{
		return getHibernateTemplate().find("select model.gallaryId,model.name from Gallary model where model.candidate.candidateId = ?",candidateId);
	}
}
