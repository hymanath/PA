package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationProgramTargetDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalDesignationProgramTarget;

public class SelfAppraisalDesignationProgramTargetDAO extends GenericDaoHibernate<SelfAppraisalDesignationProgramTarget, Long> implements ISelfAppraisalDesignationProgramTargetDAO {
 
	public SelfAppraisalDesignationProgramTargetDAO(){
		super(SelfAppraisalDesignationProgramTarget.class);
	}
}
