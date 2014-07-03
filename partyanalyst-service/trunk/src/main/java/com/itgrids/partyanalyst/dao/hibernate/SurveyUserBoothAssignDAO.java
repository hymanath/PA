package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserBoothAssignDAO;
import com.itgrids.partyanalyst.model.SurveyUserBoothAssign;

public class SurveyUserBoothAssignDAO extends GenericDaoHibernate<SurveyUserBoothAssign, Long>  implements ISurveyUserBoothAssignDAO{

	public SurveyUserBoothAssignDAO()
	{
		super(SurveyUserBoothAssign.class);
	}
	
	public List<Object[]> getAllTheAssignedBoothsByConstituencyIdAndUserId(Long constituencyId,Long userId)
	{
		Query query = getSession().createQuery("select SUBA.surveyUserBoothAssignId , SUBA.booth.boothId from  SurveyUserBoothAssign SUBA where " +
				"SUBA.surveyUser.surveyUserId = :userId and SUBA.booth.constituency.constituencyId = :constituencyId and SUBA.isDelete = 'N'");
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		
		return query.list();
		
	}

}
