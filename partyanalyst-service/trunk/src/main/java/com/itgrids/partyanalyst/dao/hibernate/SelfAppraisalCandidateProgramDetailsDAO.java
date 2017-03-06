package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateProgramDetailsDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateProgramDetails;

public class SelfAppraisalCandidateProgramDetailsDAO extends GenericDaoHibernate<SelfAppraisalCandidateProgramDetails, Long>
		implements ISelfAppraisalCandidateProgramDetailsDAO {
        public SelfAppraisalCandidateProgramDetailsDAO(){
        	super(SelfAppraisalCandidateProgramDetails.class);
        }
}
