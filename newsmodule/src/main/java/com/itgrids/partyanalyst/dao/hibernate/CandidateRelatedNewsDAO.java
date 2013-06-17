package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateRelatedNewsDAO;
import com.itgrids.partyanalyst.model.CandidateRealatedNews;

public class CandidateRelatedNewsDAO extends GenericDaoHibernate<CandidateRealatedNews, Long> implements 
ICandidateRelatedNewsDAO {
	
	
	public CandidateRelatedNewsDAO() {
		super(CandidateRealatedNews.class);
	}
	
	public List<Object[]> getAllfileGallariesOfCandidate(Long candidateId)
	{
		Query query = getSession().createQuery("select model.fileGallary.fileGallaryId , model.fileGallary.file.fileTitle " +
				"from CandidateRealatedNews model where model.candidate.candidateId = :candidateId and model.fileGallary.isDelete = :isDelete " +
				"order by model.fileGallary.file.fileTitle");
		
		query.setParameter("candidateId", candidateId);
		query.setParameter("isDelete", "false");
		return query.list();
		
		
	}
	public List<Object[]> getCandidates(){
		Query queryObj=getSession().createQuery("select distinct model.candidate.candidateId,model.candidate.firstname,model.candidate.lastname,model.candidate.lastname " +
				"from CandidateRealatedNews model ");
		return queryObj.list();
	}

}
