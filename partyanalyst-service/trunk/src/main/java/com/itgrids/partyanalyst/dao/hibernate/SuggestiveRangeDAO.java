package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISuggestiveRangeDAO;
import com.itgrids.partyanalyst.model.SuggestiveRange;

public class SuggestiveRangeDAO extends GenericDaoHibernate<SuggestiveRange, Long> implements ISuggestiveRangeDAO{

	public SuggestiveRangeDAO()
	{
	  super(SuggestiveRange.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<SuggestiveRange> getSuggestiveRangeList()
	{
		return getHibernateTemplate().find(" from SuggestiveRange model ");
	}
	
	
}
