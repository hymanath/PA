package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;
import com.itgrids.partyanalyst.model.CandidatePartyCategory;
import com.itgrids.partyanalyst.model.File;

public class CandidatePartyCategoryDAO extends GenericDaoHibernate<CandidatePartyCategory, Long> implements ICandidatePartyCategoryDAO{

	public CandidatePartyCategoryDAO() {
		super(CandidatePartyCategory.class);
		
	}
	  @SuppressWarnings("unchecked")
		public List<Object[]> getCandidateRelatedCategories(Long candidateId,Date fromDate, Date toDate,String newsType)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append(" select distinct model.gallary.gallaryId,model.gallary.name from CandidatePartyCategory model where (model.candidatePartyFile.sourceCandidate.candidateId =:candidateId) or (model.candidatePartyFile.destinationCandidate.candidateId =:candidateId)");
			
			 str.append(" and model.gallary.isDelete = 'false' ");
			 if(!newsType.equals(""))
				str.append(" and model.gallary.isPrivate = 'false' "); 
			 
			 if(fromDate != null)
				 str.append(" and model.candidatePartyFile.file.fileDate >= :fromDate ");
			 if(toDate != null)
				 str.append(" and model.candidatePartyFile.file.fileDate <= :toDate ");
			 
			 str.append(" order by model.gallary.name ");
			 Query query = getSession().createQuery(str.toString());
			 
			 query.setParameter("candidateId", candidateId);
			
			 if(fromDate != null)
			  query.setParameter("fromDate", fromDate);
			 if(toDate != null)
			  query.setParameter("toDate", toDate);
			
			 
			 return query.list();
		 }
		
		 public List<File> getFileListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate,List<Long> categoryIdsList)
		  {
			  StringBuilder str = new StringBuilder();
				str.append(" select distinct (model.candidatePartyFile.file) from CandidatePartyCategory model where (model.candidatePartyFile.sourceCandidate.candidateId = :candidateId or model.candidatePartyFile.destinationCandidate.candidateId = :candidateId)");
				str.append(" and (model.candidatePartyFile.file.isDeleted !='Y' or model.candidatePartyFile.file.isDeleted is null)");
				if(queryType.equals("Public") || queryType.equals(""))
				  str.append(" and (model.candidatePartyFile.file.isPrivate !='Y' or model.candidatePartyFile.file.isPrivate is null)");
						
				else if(queryType.equals("Private"))
				  str.append("  and model.candidatePartyFile.file.isPrivate='Y'");
				
				if(fromDate != null)
				 str.append(" and date(model.candidatePartyFile.file.fileDate) >= :fromDate");
				if(toDate != null)
				 str.append(" and date(model.candidatePartyFile.file.fileDate) <= :toDate");
			
				if(categoryIdsList != null && categoryIdsList.size() > 0)
				str.append(" and model.gallary.gallaryId in (:categoryIdsList)");
					
				 str.append(" order by model.candidatePartyFile.file.fileDate desc ");
				Query query = getSession().createQuery(str.toString());
					 
				 query.setLong("candidateId", candidateId);
				
				 if(fromDate != null)
				  query.setParameter("fromDate", fromDate);
				 if(toDate != null)
				  query.setParameter("toDate", toDate);
				 
				 if(categoryIdsList != null && categoryIdsList.size() > 0)
				  query.setParameterList("categoryIdsList", categoryIdsList);
				 
				 if(firstResult != null)
				 query.setFirstResult(firstResult);
				 if(maxResult != null)
				 query.setMaxResults(maxResult);
				 return query.list();
		  }
	@SuppressWarnings("unchecked")
	public List<Object[]> getSelectdGalleryNews(int startIndex,int maxIndex,Long gallaryId)
	{
		Query query = getSession().createQuery("select distinct  model.candidatePartyFile.file.fileTitle ," +
				" model.candidatePartyFile.file.fileDescription , " +
				" model.candidatePartyFile.file.fileDate ,  " +
				" model.candidatePartyFile.file.filePath ," +
				" model.candidatePartyFile.file.fileId ," +
				" model.candidatePartyFile.file.font.fontId from CandidatePartyCategory model " +
				" where model.gallary.gallaryId = :gallaryId");
		query.setParameter("gallaryId", gallaryId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list(); 
	}
	
	
	public Long getCountForNewsInASelectedGallery(Long gallaryId)
	{
		Query query = getSession().createQuery("select count( distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model " +
				" where model.gallary.gallaryId = :gallaryId");
		query.setParameter("gallaryId", gallaryId);
		return (Long)query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestGallerices()
	{
		Query query = getSession().createQuery("select distinct model.gallary.gallaryId, " +
				" model.gallary.name from CandidatePartyCategory model order by model.candidatePartyCategoryId desc ");
		query.setFirstResult(0);
		query.setMaxResults(5);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllCategoryes()
	{
		return getHibernateTemplate().find("select  model.gallary.gallaryId , model.gallary.name ,model.gallary.description , count(distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model group by model.gallary.gallaryId");
	}
}
