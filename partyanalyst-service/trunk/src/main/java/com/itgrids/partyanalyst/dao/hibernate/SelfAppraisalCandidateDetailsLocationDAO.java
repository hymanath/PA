package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsLocationDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetailsLocation;

public class SelfAppraisalCandidateDetailsLocationDAO extends GenericDaoHibernate<SelfAppraisalCandidateDetailsLocation, Long>
		implements ISelfAppraisalCandidateDetailsLocationDAO {


	public SelfAppraisalCandidateDetailsLocationDAO() {
		super(SelfAppraisalCandidateDetailsLocation.class);
	}
}
