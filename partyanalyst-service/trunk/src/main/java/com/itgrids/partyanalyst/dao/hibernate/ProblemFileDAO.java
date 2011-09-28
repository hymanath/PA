package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemFileDAO;
import com.itgrids.partyanalyst.model.CadreProblemDetails;
import com.itgrids.partyanalyst.model.ProblemFile;

public class ProblemFileDAO extends GenericDaoHibernate<ProblemFile, Long>
		implements IProblemFileDAO {

	public ProblemFileDAO() {
		super(ProblemFile.class);
	}
}
