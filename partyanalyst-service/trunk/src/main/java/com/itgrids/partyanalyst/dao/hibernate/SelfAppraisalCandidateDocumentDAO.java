package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDocumentDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDocument;

public class SelfAppraisalCandidateDocumentDAO extends GenericDaoHibernate<SelfAppraisalCandidateDocument, Long> implements
		ISelfAppraisalCandidateDocumentDAO {
        public SelfAppraisalCandidateDocumentDAO(){
        	super(SelfAppraisalCandidateDocument.class);
        }
        
        public List<Object[]> getSelfAppraisalDocumentDetails(Long candidateId,Long year,Long month){
        	
        	Query query = getSession().createQuery(" select model.selfAppraisalCandidateDocumentId,model.documentPath " +
        			"  from SelfAppraisalCandidateDocument model " +
        			" where model.isDeleted='N' " +
        			" and model.selfAppraisalCandidateId=:candidateId " +
        			" and month(model.tourDate)=:month " +
        			" and year(model.tourDate)=:year  ");
        	
        	query.setParameter("candidateId", candidateId);
        	query.setParameter("month", month);
        	query.setParameter("year", year);
        	
        	return query.list();
        	
        }
}
