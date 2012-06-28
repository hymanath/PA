package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IQuestionsRepositoryDAO;
import com.itgrids.partyanalyst.model.QuestionsRepository;

public class QuestionsRepositoryDAO extends GenericDaoHibernate<QuestionsRepository, Long> implements IQuestionsRepositoryDAO {

	
	public QuestionsRepositoryDAO() {
		super(QuestionsRepository.class);
	}
}

