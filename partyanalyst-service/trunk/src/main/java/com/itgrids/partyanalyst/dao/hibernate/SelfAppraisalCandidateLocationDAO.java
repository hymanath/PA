package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocation;

public class SelfAppraisalCandidateLocationDAO extends GenericDaoHibernate<SelfAppraisalCandidateLocation, Long> implements
		ISelfAppraisalCandidateLocationDAO {
	   public SelfAppraisalCandidateLocationDAO() {
			super(SelfAppraisalCandidateLocation.class);
		 }
}
