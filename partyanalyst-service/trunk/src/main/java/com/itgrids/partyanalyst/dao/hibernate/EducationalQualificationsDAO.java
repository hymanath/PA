package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.GenericDao;
import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.model.EducationalQualifications;

public class EducationalQualificationsDAO extends GenericDaoHibernate<EducationalQualifications, Long> implements IEducationalQualificationsDAO  {

	public EducationalQualificationsDAO() {
		super(EducationalQualifications.class);
		
	}
}
