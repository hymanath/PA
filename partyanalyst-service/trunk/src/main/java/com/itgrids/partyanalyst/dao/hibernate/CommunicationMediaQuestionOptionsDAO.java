package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommunicationMediaQuestionOptionsDAO;
import com.itgrids.partyanalyst.model.CommunicationMediaQuestionOptions;

public class CommunicationMediaQuestionOptionsDAO extends GenericDaoHibernate<CommunicationMediaQuestionOptions,Long> implements ICommunicationMediaQuestionOptionsDAO{

	public CommunicationMediaQuestionOptionsDAO()
	{
		super(CommunicationMediaQuestionOptions.class);
	}

}
