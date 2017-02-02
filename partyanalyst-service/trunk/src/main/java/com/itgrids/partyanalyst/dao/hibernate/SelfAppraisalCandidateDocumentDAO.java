package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDocumentDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDocument;

public class SelfAppraisalCandidateDocumentDAO extends GenericDaoHibernate<SelfAppraisalCandidateDocument, Long> implements
		ISelfAppraisalCandidateDocumentDAO {
        public SelfAppraisalCandidateDocumentDAO(){
        	super(SelfAppraisalCandidateDocument.class);
        }
        
        public List<Object[]> getSelfAppraisalDocumentDetails(Long candidateId,Long toursMonthId){
        	
        	Query query = getSession().createQuery(" select model.selfAppraisalCandidateDocumentId,model.documentPath " +
        			"  from SelfAppraisalCandidateDocument model " +
        			" where model.isDeleted='N' " +
        			" and model.selfAppraisalCandidateId=:candidateId " +
        			" and selfAppraisalToursMonthId=:toursMonthId " );
        	
        	query.setParameter("candidateId", candidateId);
        	query.setParameter("toursMonthId", toursMonthId);
        	
        	return query.list();
        	
        }
        
        public List<Object[]> getDocumentsOfCandidates(Date fromDate,Date toDate,Set<Long> candidateIds,List<Long> monthyearIds){
        	
        	StringBuilder str = new StringBuilder();
        	
        	str.append(" select model.selfAppraisalCandidateId,count(model.documentPath) " +
        			" from SelfAppraisalCandidateDocument model " +
        			" where model.isDeleted='N' " );
        	
        	if(candidateIds !=null && candidateIds.size()>0){
        		str.append(" and model.selfAppraisalCandidateId in (:candidateIds) " ); 
        	}
        	
        	/*if(fromDate !=null && toDate !=null){
        		str.append(" and date(model.tourDate) between :fromDate and :toDate ");
        	}*/
        	
        	if(monthyearIds !=null && monthyearIds.size()>0){
        		str.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthyearIds) ");
        	}
        	
        	str.append(" group by model.selfAppraisalCandidateId ");
        	
        	Query query = getSession().createQuery(str.toString());
        	
        	if(candidateIds !=null && candidateIds.size()>0){
        		query.setParameterList("candidateIds", candidateIds);
        	}
        	/*if(fromDate !=null && toDate !=null){
        		query.setDate("fromDate",fromDate);
        		query.setDate("toDate",toDate);
        	}*/
        	
        	if(monthyearIds !=null && monthyearIds.size()>0){
        		query.setParameterList("monthyearIds", monthyearIds);
        	}
        	
        	return query.list();
        }
        public int deleteDocumentByDocument(List<Long> documents){
        	
        	Query query = getSession().createQuery(" update SelfAppraisalCandidateDocument model set model.isDeleted= 'Y'   " +
        			" where model.selfAppraisalCandidateDocumentId in (:documents) ");
        	
        	query.setParameterList("documents", documents);
        	
        	return query.executeUpdate();        	
        }
        public List<Object[]> getCandiateDocument(List<Long> monthYearIds,Long tdpCadreId){
        	 StringBuilder queryStr = new StringBuilder();
        	 
        	   queryStr.append("select model.selfAppraisalToursMonthId,model.documentPath from SelfAppraisalCandidateDocument model where model.isDeleted='N' and model.tdpCadreId=:tdpCadreId ");
        	 
        	   if(monthYearIds != null && monthYearIds.size() > 0 ){
		            queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
		        }
        	   Query query = getSession().createQuery(queryStr.toString());
        	   query.setParameter("tdpCadreId", tdpCadreId);
        	   if(monthYearIds != null && monthYearIds.size() > 0 ){
      				query.setParameterList("monthYearIds", monthYearIds);
      		   }
        	   return query.list();
        }
        @SuppressWarnings("unchecked")
		public List<Object[]> getSelfAppraisalDocuments(Long cadreId,Long toursMonthId){
        	
        	Query query = getSession().createQuery(" select model.selfAppraisalCandidateDocumentId,model.documentPath," +
        			" model.selfAppraisalCandidateId,model.insertedTime " +
        			"  from SelfAppraisalCandidateDocument model " +
        			" where model.isDeleted='N' " +
        			" and model.tdpCadreId =:cadreId " +
        			" and selfAppraisalToursMonthId=:toursMonthId order by model.selfAppraisalCandidateDocumentId desc " );
        	
        	query.setParameter("cadreId", cadreId);
        	query.setParameter("toursMonthId", toursMonthId);
        	
        	return query.list();
        	
        }
}
