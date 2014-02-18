package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHHQuestionOptionsDAO;
import com.itgrids.partyanalyst.model.HHQuestionOptions;


public class HHQuestionOptionsDAO extends GenericDaoHibernate<HHQuestionOptions,Long> implements IHHQuestionOptionsDAO {
	
	public HHQuestionOptionsDAO() {
		super(HHQuestionOptions.class);
	}
	
	
}
