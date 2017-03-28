package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommunicationMediaQuestionDAO;
import com.itgrids.partyanalyst.model.CommunicationMediaQuestion;

public class CommunicationMediaQuestionDAO extends GenericDaoHibernate<CommunicationMediaQuestion,Long> implements ICommunicationMediaQuestionDAO{

	public  CommunicationMediaQuestionDAO()
	{
		super( CommunicationMediaQuestion.class);
	}

}
