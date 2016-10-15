package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreRegIssueTypeDAO;
import com.itgrids.partyanalyst.model.BloodDonation;
import com.itgrids.partyanalyst.model.CadreRegIssueType;

public class CadreRegIssueTypeDAO extends GenericDaoHibernate<CadreRegIssueType, Long> implements ICadreRegIssueTypeDAO {
	
	
	public CadreRegIssueTypeDAO(){
		 super(CadreRegIssueType.class);
	}

}
