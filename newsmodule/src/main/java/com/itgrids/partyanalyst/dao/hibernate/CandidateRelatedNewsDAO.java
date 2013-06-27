package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateRelatedNewsDAO;
import com.itgrids.partyanalyst.model.CandidateRealatedNews;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.utils.IConstants;

public class CandidateRelatedNewsDAO extends GenericDaoHibernate<CandidateRealatedNews, Long> implements 
ICandidateRelatedNewsDAO {
	
	
	public CandidateRelatedNewsDAO() {
		super(CandidateRealatedNews.class);
	}
	
	public List<Object[]> getAllfileGallariesOfCandidate(Long candidateId  ,Date fromDate  ,Date toDate)
	{
		Query query = getSession().createQuery("select model.fileGallary.fileGallaryId , model.fileGallary.file.fileTitle , model.fileGallary.file " +
				"from CandidateRealatedNews model where model.candidate.candidateId = :candidateId and " +
				" date(model.fileGallary.file.fileDate) >= :fromDate and date(model.fileGallary.file.fileDate) <= :toDate" +
				" and model.fileGallary.isDelete = :isDelete " +
				"order by model.fileGallary.file.fileTitle");
		
		query.setParameter("candidateId", candidateId);
		query.setParameter("isDelete", "false");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
		
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidates(){
		Query queryObj=getSession().createQuery("select distinct model.candidate.candidateId,model.candidate.lastname " +
				"from CandidateRealatedNews model order by model.candidate.lastname ");
		return queryObj.list();
	}
	
	public List<Object[]> getCandidatesContainsNews()
	{
		Query queryObj=getSession().createQuery("select distinct model.candidate.candidateId,model.candidate.firstname,model.candidate.lastname,model.candidate.lastname " +
				"from CandidateRealatedNews model order by model.candidate.lastname");
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FileGallary> getFileGallaryListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate,List<Long> gallaryIdsList)
    {
		StringBuilder str = new StringBuilder();
		str.append(" select model.fileGallary from CandidateRealatedNews model where model.candidate.candidateId =:candidateId and ");
		str.append(" model.fileGallary.gallary.isDelete='false' and model.fileGallary.gallary.contentType.contentType= :type and model.fileGallary.isDelete = 'false' ");
		if(queryType.equals("Public"))
		  str.append(" and model.fileGallary.gallary.isPrivate='false' and model.fileGallary.isPrivate ='false' ");
				
		else if(queryType.equals("Private"))
		  str.append("  and ( (model.fileGallary.gallary.isPrivate='true') or(model.fileGallary.gallary.isPrivate='false' and model.fileGallary.isPrivate ='true') ) ");
		
		if(fromDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
		if(toDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
			
		if(gallaryIdsList != null && gallaryIdsList.size() > 0)
		 str.append(" and model.fileGallary.gallary.gallaryId in (:gallaryIdsList) ");
		
		 str.append(" order by model.fileGallary.file.fileDate desc ");
		Query query = getSession().createQuery(str.toString());
			 
		 query.setLong("candidateId", candidateId);
		 query.setString("type", IConstants.NEWS_GALLARY);
		 if(fromDate != null)
		  query.setParameter("fromDate", fromDate);
		 if(toDate != null)
		  query.setParameter("toDate", toDate);
		 
		 if(gallaryIdsList !=null && gallaryIdsList.size() > 0)
		  query.setParameterList("gallaryIdsList", gallaryIdsList);
		 if(firstResult != null)
		 query.setFirstResult(firstResult);
		 if(maxResult != null)
		 query.setMaxResults(maxResult);
		 return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidatesWithCount(){
		Query queryObj=getSession().createQuery("select count(distinct model.fileGallary.fileGallaryId), model.candidate.candidateId,model.candidate.lastname " +
				"from CandidateRealatedNews model where model.fileGallary.isPrivate='false' and model.fileGallary.isDelete ='false' and model.fileGallary.gallary.isDelete ='false' " +
				" and model.fileGallary.gallary.isPrivate='false' and model.fileGallary.file.fileId is not null group by model.candidate.candidateId order by model.candidate.lastname ");
		return queryObj.list();
	}

}
