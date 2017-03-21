package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICustomReportProgramDAO;
import com.itgrids.partyanalyst.model.CustomReportProgram;

public class CustomReportProgramDAO extends GenericDaoHibernate<CustomReportProgram, Long> implements ICustomReportProgramDAO {

	public CustomReportProgramDAO(){
		super(CustomReportProgram.class);
	}
}
