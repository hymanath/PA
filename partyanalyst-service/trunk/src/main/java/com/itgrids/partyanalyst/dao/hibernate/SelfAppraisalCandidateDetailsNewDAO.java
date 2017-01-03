package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsNewDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetailsNew;

public class SelfAppraisalCandidateDetailsNewDAO extends GenericDaoHibernate<SelfAppraisalCandidateDetailsNew, Long> implements ISelfAppraisalCandidateDetailsNewDAO {

	public SelfAppraisalCandidateDetailsNewDAO() {
		super(SelfAppraisalCandidateDetailsNew.class);
	}
}
