package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalDesignation;

public class SelfAppraisalDesignationDAO extends GenericDaoHibernate<SelfAppraisalDesignation, Long> implements ISelfAppraisalDesignationDAO {
	public SelfAppraisalDesignationDAO() {
		super(SelfAppraisalDesignation.class);
	}
}
