package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaAnswersDAO;
import com.itgrids.partyanalyst.dao.IKaizalaQuestionsDAO;
import com.itgrids.partyanalyst.model.KaizalaAnswers;
import com.itgrids.partyanalyst.model.KaizalaQuestions;

public class KaizalaQuestionsDAO extends GenericDaoHibernate<KaizalaQuestions, Long> implements IKaizalaQuestionsDAO {
	public KaizalaQuestionsDAO() {
		super(KaizalaQuestions.class); 
	}
	
	public List<Object[]> getQuestionsForKizalaActionId(Long kaizalaActionId){
		Query query = getSession().createQuery(" select model.kaizalaQuestionsId,model.question " +
				" from KaizalaQuestions model " +
				" where model.isDeleted='N' and model.kaizalaActionsId=:kaizalaActionId ");
		query.setParameter("kaizalaActionId", kaizalaActionId);
		return query.list();
	}
}