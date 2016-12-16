package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDocumentDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDocument;

public class SelfAppraisalCandidateDocumentDAO extends GenericDaoHibernate<SelfAppraisalCandidateDocument, Long> implements
		ISelfAppraisalCandidateDocumentDAO {
        public SelfAppraisalCandidateDocumentDAO(){
        	super(SelfAppraisalCandidateDocument.class);
        }
}
