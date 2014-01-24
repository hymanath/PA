package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.IDebateQuestionsDAO;
import com.itgrids.partyanalyst.model.DebateQuestions;

public class DebateQuestionsDAO extends GenericDaoHibernate<DebateQuestions, Long> implements IDebateQuestionsDAO{

	public DebateQuestionsDAO() {
		super(DebateQuestions.class);
		// TODO Auto-generated constructor stub
	}

	 @SuppressWarnings("unchecked")
		public List<DebateQuestions> getDebateQuestionDetails(){
			Query query = getSession().createQuery("select model from DebateQuestions model where model.isDeleted != 'Y'");
			 
			return query.list();
		 }
}
