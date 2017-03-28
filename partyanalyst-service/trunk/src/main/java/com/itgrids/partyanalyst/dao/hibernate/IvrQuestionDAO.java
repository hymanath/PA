package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrQuestionDAO;
import com.itgrids.partyanalyst.model.IvrQuestion;


public class IvrQuestionDAO extends GenericDaoHibernate<IvrQuestion, Long> implements IIvrQuestionDAO{

	public IvrQuestionDAO() {
		super(IvrQuestion.class);
		
	}
	
}
