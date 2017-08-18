package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaActionsDAO;
import com.itgrids.partyanalyst.dao.IKaizalaOptionsDAO;
import com.itgrids.partyanalyst.model.KaizalaActions;
import com.itgrids.partyanalyst.model.KaizalaOptions;

public class KaizalaOptionsDAO extends GenericDaoHibernate<KaizalaOptions, Long> implements IKaizalaOptionsDAO {
	public KaizalaOptionsDAO() {
		super(KaizalaOptions.class); 
	}
	
	public Long getOptionId(Long questionId,String answer){
		Query query = getSession().createQuery(" select model.kaizalaOptionsId " +
				" from KaizalaOptions model " +
				"where model.kaizalaQuestionsId=:questionId and model.title=:answer ");
		query.setParameter("questionId", questionId);
		query.setParameter("answer", answer);
		return (Long)query.uniqueResult();
	}
	
}