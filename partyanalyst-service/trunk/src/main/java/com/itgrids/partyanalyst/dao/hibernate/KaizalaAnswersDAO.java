package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKaizalaAnswersDAO;
import com.itgrids.partyanalyst.model.KaizalaAnswers;

public class KaizalaAnswersDAO extends GenericDaoHibernate<KaizalaAnswers, Long> implements IKaizalaAnswersDAO {
	public KaizalaAnswersDAO() {
		super(KaizalaAnswers.class); 
	}
	
}