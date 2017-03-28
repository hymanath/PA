package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateSmsQuestionDAO;
import com.itgrids.partyanalyst.model.DebateSmsQuestion;

public class DebateSmsQuestionDAO extends GenericDaoHibernate<DebateSmsQuestion, Long> implements IDebateSmsQuestionDAO{

	public DebateSmsQuestionDAO() {
		super(DebateSmsQuestion.class);
		// TODO Auto-generated constructor stub
	}
	 @SuppressWarnings("unchecked")
		public List<DebateSmsQuestion> getDebateSmsQuestionDetails(){
			Query query = getSession().createQuery("select model from DebateSmsQuestion model where model.isDeleted !='Y'");
			 
			return query.list();
		 }
	 
	 public List<Object[]> getDebateSmsQuestionDetailsNew(){
			Query query = getSession().createQuery("select model.debateSmsQuestionId,model.question from DebateSmsQuestion model where model.isDeleted !='Y'");
			 
			return query.list();
		 }
}
