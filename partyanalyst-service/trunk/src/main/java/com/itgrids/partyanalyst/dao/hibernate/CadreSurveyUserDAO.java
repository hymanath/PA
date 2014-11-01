package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.model.CadreSurveyUser;

public class CadreSurveyUserDAO extends GenericDaoHibernate<CadreSurveyUser, Long> implements ICadreSurveyUserDAO{

	public CadreSurveyUserDAO() {
		super(CadreSurveyUser.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getUserByUserNameAndPassword(String userName , String password)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserId from CadreSurveyUser model where model.userName = :userName and model.password = :password and model.isDeleted = 'N' ");
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return query.list();
	}
	
	public List<Object[]> getCadreSurveyUsersList(List<Long> assignedCadreSurveyUsers)
	{
		Query query = getSession().createQuery("select model.cadreSurveyUserId, model.userName from CadreSurveyUser model where " +
				" model.isDeleted = 'N' and model.cadreSurveyUserId not in (:assignedCadreSurveyUsers) order by model.userName ");
		
		query.setParameterList("assignedCadreSurveyUsers", assignedCadreSurveyUsers);
		
		return query.list();
		
	}
	
	public List<Object[]> getUserMobileNos(Set<Long> ids){
		//0 userId,1mobile,2constituencyId,3constiname,4distiictId,5districtName
		Query query = getSession().createQuery("select model.cadreSurveyUser.cadreSurveyUserId, model.cadreSurveyUser.mobileNo,model.constituency.constituencyId,model.constituency.name,model.constituency.district.districtId," +
				" model.constituency.district.districtName from CadreSurveyUserAssignDetails model where model.cadreSurveyUser.cadreSurveyUserId  in (:ids) ");
		
		query.setParameterList("ids", ids);
		
		return query.list();
		
	}
	
	public Long  getTotalCadreSurveyDetails()
	{
		Query query = getSession().createQuery("select count(model.cadreSurveyUserId) from CadreSurveyUser model where " +
				"  model.isDeleted ='N'  ");		
		return (Long) query.uniqueResult();
	}
}
