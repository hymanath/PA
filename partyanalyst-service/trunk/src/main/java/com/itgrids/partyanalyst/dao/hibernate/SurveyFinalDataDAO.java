package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
	
	public List<Object[]> getBoothWiseVoterDetails(Long boothId)
	{
		Query query = getSession().createQuery("select model.booth.boothId,model.voter.voterId,model.voter.voterIDCardNo,model.voter.name,model.voter.gender,model.voter.age " +
				" , model.voter.houseNo , model.mobileNo,model.isCadre , model.isInfluencingPeople , model.casteStateId , model.casteName ," +
				" model.hamletId , model.hamletName , model.wardId,model.localArea from SurveyFinalData model where model.boothId = :boothId");
		query.setParameter("boothId", boothId);
		return query.list();
	}

	public List<Object[]> getSurveyFinalConstituencyInfo()
	{
		Query query = getSession().createQuery("select distinct model.booth.constituency.constituencyId,model.booth.constituency.name from SurveyFinalData model order by model.booth.constituency.name asc ");		
		return query.list();
		
	}

}
