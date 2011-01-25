package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;


import com.itgrids.partyanalyst.dao.ICadreProblemDetailsDAO;
import com.itgrids.partyanalyst.model.CadreProblemDetails;

public class CadreProblemDetailsDAO extends GenericDaoHibernate<CadreProblemDetails,Long> implements ICadreProblemDetailsDAO 
{
	public CadreProblemDetailsDAO()
	{
		super(CadreProblemDetails.class);
	}
	
}
