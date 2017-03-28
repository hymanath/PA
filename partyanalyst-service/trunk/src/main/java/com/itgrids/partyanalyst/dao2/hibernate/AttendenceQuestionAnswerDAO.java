package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.AttendenceQuestionAnswer;

public class AttendenceQuestionAnswerDAO extends GenericDaoHibernate<AttendenceQuestionAnswer, Long>{

	public AttendenceQuestionAnswerDAO() {
		super(AttendenceQuestionAnswer.class);
		
	}

}
