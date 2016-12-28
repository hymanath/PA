package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAdminHouseMemberQuestionDAO;
import com.itgrids.partyanalyst.model.AdminHouseMemberQuestion;

public class AdminHouseMemberQuestionDAO extends GenericDaoHibernate<AdminHouseMemberQuestion, Long> implements IAdminHouseMemberQuestionDAO{

	public AdminHouseMemberQuestionDAO() {
		super(AdminHouseMemberQuestion.class);
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getLeaderWiseQuestionsInfo(List<Long> questionIdsList)  
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.adminHouseSessionDay.sessionDate,model.question,model.adminHouseMember.memberName,model.remarks " +
				" from AdminHouseMemberQuestion model where " +
				" model.adminHouseMemberQuestionId in (:questionIdsList) and model.isDeleted='N' ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("questionIdsList",questionIdsList);

		return query.list();
	}
}
