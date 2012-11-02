package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserEventActionPlanDAO;
import com.itgrids.partyanalyst.model.UserEventActionPlan;

public class UserEventActionPlanDAO extends GenericDaoHibernate<UserEventActionPlan, Long> implements IUserEventActionPlanDAO {

	public UserEventActionPlanDAO() {
		super(UserEventActionPlan.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<UserEventActionPlan> findByUserEventsId(Long userEventsId) {
		
		return getHibernateTemplate().find("from UserEventActionPlan model where model.userEvents.userEventsId = ?", userEventsId) ;
	}
    public List<Long> getEventActionPlanIds(Long eventId){
    	Query queryObject = getSession().createQuery("select model.eventActionPlanId from UserEventActionPlan model where model.userEvents.userEventsId = ?");
		queryObject.setParameter(0, eventId);	
		 return queryObject.list(); 	
    }
    public List<Long> getEventActionPlanIds(Long eventId,List<Long> eventActionPlanIds){
    	Query queryObject = getSession().createQuery("select model.eventActionPlanId from UserEventActionPlan model where model.userEvents.userEventsId = (:eventId) and model.eventActionPlanId not in (:eventActionPlanIds)");
		queryObject.setParameter("eventId", eventId);	
		queryObject.setParameterList("eventActionPlanIds", eventActionPlanIds);		
		 return queryObject.list(); 	
    }
	public void removeEventActionPlans(Long eventId){
		
		Query queryObject = getSession().createQuery("delete from UserEventActionPlan model where model.userEvents.userEventsId = ?");
		queryObject.setParameter(0, eventId);	
		 queryObject.executeUpdate();
	}
   public void removeDeletedEventActionPlans(Long eventId,List<Long> eventActionPlanIds){
		
		Query queryObject = getSession().createQuery("delete from UserEventActionPlan model where model.userEvents.userEventsId = (:eventId) and model.eventActionPlanId not in (:eventActionPlanIds)");
		queryObject.setParameter("eventId", eventId);	
		queryObject.setParameterList("eventActionPlanIds", eventActionPlanIds);	
		 queryObject.executeUpdate();
	}
	
}
