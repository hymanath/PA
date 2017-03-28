package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IInstitutionDAO;
import com.itgrids.partyanalyst.model.Institution;

public class InstitutionDAO extends GenericDaoHibernate<Institution, Long> implements IInstitutionDAO{

	public InstitutionDAO() {
		super(Institution.class);
		
	}

}
