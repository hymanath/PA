package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateNewsResponseDAO;
import com.itgrids.partyanalyst.model.CandidateNewsResponse;

public class CandidateNewsResponseDAO extends GenericDaoHibernate<CandidateNewsResponse, Long> implements  ICandidateNewsResponseDAO{
	public CandidateNewsResponseDAO() {
		super(CandidateNewsResponse.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getResponsefileGallaryIds(Long fileGallaryId, Integer startIndex, Integer maxIndex)
	{
		Query query = getSession().createQuery(" select distinct CNR.responseFileGallary.fileGallaryId,CRN.candidate.candidateId " +
				" from CandidateNewsResponse CNR, CandidateRealatedNews CRN where CNR.responseFileGallary.fileGallaryId = CRN.fileGallary.fileGallaryId " +
				"  and CNR.fileGallary.fileGallaryId =:fileGallaryId ");
		
		query.setParameter("fileGallaryId", fileGallaryId);
		if(startIndex != null)
		 query.setFirstResult(startIndex);
		if(maxIndex != null)
		 query.setMaxResults(maxIndex);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFileGallaryIdsByResponseGallaryId(Long fileGallaryId, Integer startIndex, Integer maxIndex)
	{
		Query query = getSession().createQuery(" select distinct CNR.fileGallary.fileGallaryId,CRN.candidate.candidateId " +
				" from CandidateNewsResponse CNR, CandidateRealatedNews CRN where CNR.fileGallary.fileGallaryId = CRN.fileGallary.fileGallaryId " +
				"  and CNR.responseFileGallary.fileGallaryId =:fileGallaryId  ");
		
		query.setParameter("fileGallaryId", fileGallaryId);
		if(startIndex != null)
		 query.setFirstResult(startIndex);
		if(maxIndex != null)
		 query.setMaxResults(maxIndex);
		
		return query.list();
		
	}
	
}
