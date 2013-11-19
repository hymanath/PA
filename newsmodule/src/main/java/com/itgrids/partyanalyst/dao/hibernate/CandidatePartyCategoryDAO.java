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
				str.append(" select distinct model.candidatePartyFile.file from CandidatePartyCategory model where (model.candidatePartyFile.sourceCandidate.candidateId =:candidateId) or (model.candidatePartyFile.destinationCandidate.candidateId =:candidateId)");
				str.append(" and (model.candidatePartyFile.file.isDeleted='N') or (model.candidatePartyFile.file.isDeleted is null)");
				if(queryType.equals("Public"))
				  str.append(" and (model.candidatePartyFile.file.isPrivate='N') or (model.candidatePartyFile.file.isPrivate is null)");
						
				else if(queryType.equals("Private"))
				  str.append("  and model.candidatePartyFile.file.isPrivate='Y'");
				
				if(fromDate != null)
				 str.append(" and date(model.candidatePartyFile.file.fileDate) >= :fromDate");
				if(toDate != null)
				 str.append(" and date(model.candidatePartyFile.file.fileDate) <= :toDate");
			
				if(categoryIdsList != null && categoryIdsList.size() > 0)
				str.append(" and model.gallary.gallaryId in ( :categoryIdsList) ");
					
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
}
