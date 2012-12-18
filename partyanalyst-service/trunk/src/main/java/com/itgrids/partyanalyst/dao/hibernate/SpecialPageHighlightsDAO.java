package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISpecialPageHighlightsDAO;
import com.itgrids.partyanalyst.model.SpecialPageHighlights;

public class SpecialPageHighlightsDAO extends GenericDaoHibernate<SpecialPageHighlights, Long> implements ISpecialPageHighlightsDAO{

	public SpecialPageHighlightsDAO() {
		super(SpecialPageHighlights.class);
		
	}

}
