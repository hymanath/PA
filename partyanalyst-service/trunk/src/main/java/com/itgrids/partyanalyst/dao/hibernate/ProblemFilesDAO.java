package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IProblemFilesDAO;
import com.itgrids.partyanalyst.model.ProblemFiles;

public class ProblemFilesDAO extends GenericDaoHibernate<ProblemFiles,Long> implements IProblemFilesDAO{

	public ProblemFilesDAO()
	{
		super(ProblemFiles.class);
	}
}
