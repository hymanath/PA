package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.model.EducationalQualifications;

public class EducationalQualificationsDAO extends GenericDaoHibernate<EducationalQualifications, Long> implements IEducationalQualificationsDAO  {

	public EducationalQualificationsDAO() {
		super(EducationalQualifications.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<EducationalQualifications> getEducationalQualificationsByQualificationType(String qualificationType) {
		
		return getHibernateTemplate().find("from EducationalQualifications model where model.qualification like '%"+qualificationType+"%'");
	}
	
	@SuppressWarnings("unchecked")
	public List<EducationalQualifications> getEducationalQualificationsList()
	{
		return getHibernateTemplate().find(" from EducationalQualifications model ");
	}
}
