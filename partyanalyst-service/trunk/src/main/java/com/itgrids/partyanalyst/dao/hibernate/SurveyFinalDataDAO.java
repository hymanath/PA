package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyFinalDataDAO;
import com.itgrids.partyanalyst.model.SurveyFinalData;

public class SurveyFinalDataDAO extends GenericDaoHibernate<SurveyFinalData, Long>  implements ISurveyFinalDataDAO
{

	public SurveyFinalDataDAO() {
		super(SurveyFinalData.class);
		
	}
	
	public int deleteExistingBoothDetails(Long boothId)
	{
		Query query = getSession().createQuery("delete from SurveyFinalData model where model.boothId = :boothId");
		query.setParameter("boothId", boothId);
		int c= query.executeUpdate();
		return c;
		
	}

}
