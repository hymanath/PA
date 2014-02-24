package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHHSurveyAnswersDAO;
import com.itgrids.partyanalyst.model.HHSurveyAnswers;


public class HHSurveyAnswersDAO extends GenericDaoHibernate<HHSurveyAnswers,Long> implements IHHSurveyAnswersDAO {
	
	public HHSurveyAnswersDAO() {
		super(HHSurveyAnswers.class);
	}
	
	public List<HHSurveyAnswers> getSurveyAnswersByHouseHoldId(Long houseHoldId){
		Query qry=getSession().createQuery("select model from HHSurveyAnswers model where model.houseHold.houseHoldId=:houseHoldId");
		qry.setParameter("houseHoldId", houseHoldId);
		return qry.list();
	}
	
	
	public int deleteAllPreviousAnswersByHouseHoldsId(Long houseHoldsId)
	{
		Query query = getSession()
				.createQuery(
						"delete from HHSurveyAnswers HHSA where HHSA.houseHoldId = :houseHoldsId");	
		
		
		query.setParameter("houseHoldsId", houseHoldsId);
		
		return query.executeUpdate();
		
	}
	
	
}
