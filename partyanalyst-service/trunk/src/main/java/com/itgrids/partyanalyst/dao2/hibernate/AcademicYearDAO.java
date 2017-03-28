package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAcademicYearDAO;
import com.itgrids.partyanalyst.model.AcademicYear;

public class AcademicYearDAO extends GenericDaoHibernate<AcademicYear, Long> implements IAcademicYearDAO{

	public AcademicYearDAO() {
		super(AcademicYear.class);
	}

}
