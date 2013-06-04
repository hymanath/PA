package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EducationalQualifications;

public interface IEducationalQualificationsDAO extends GenericDao<EducationalQualifications, Long> {

	public List<EducationalQualifications> getEducationalQualificationsByQualificationType(String qualificationType);
}
