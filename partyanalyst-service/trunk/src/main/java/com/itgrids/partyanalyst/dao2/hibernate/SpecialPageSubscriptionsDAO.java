package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISpecialPageSubscriptionsDAO;
import com.itgrids.partyanalyst.model.SpecialPageSubscriptions;

public class SpecialPageSubscriptionsDAO extends GenericDaoHibernate<SpecialPageSubscriptions,Long> implements ISpecialPageSubscriptionsDAO{

	public SpecialPageSubscriptionsDAO()
	{
		super(SpecialPageSubscriptions.class);
	}
	
	public Long getSubscriptionCount(Long userId,Long specialPageId)
	{
		Query queryObject = getSession().createQuery("select count(model.specialPageSubscriptionId) from SpecialPageSubscriptions model where " +
				" model.user.userId = ? and model.specialpage.specialPageId = ?");
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, specialPageId);
		return (Long) queryObject.uniqueResult();
		
	} 
	
	@SuppressWarnings("unchecked")
	public List<SpecialPageSubscriptions> getSubscriberDetails(Long specialPageId ,Long userId)
	{
		Object[] params = {specialPageId,userId};
		return getHibernateTemplate().find("select model from SpecialPageSubscriptions model where model.specialpage.specialPageId = ? and model.user.userId = ?",params);
	}
	
	public Integer unSubscriptionDetails(Long specialPageId,Long userId)
	{
		Query queryObject=getSession().createQuery("delete from SpecialPageSubscriptions model where model.specialpage.specialPageId = ? and model.user.userId = ?");
		queryObject.setParameter(0,specialPageId);
		queryObject.setParameter(1,userId);
		return queryObject.executeUpdate();
	}
	
	public List<Object[]> getAllSubscriberDetails(){
		return getHibernateTemplate().find("select model.specialpage.specialPageId,model.user.email,model.user.userId,model.user.firstName,model.user.lastName,model.specialpage.name from SpecialPageSubscriptions model where model.user.email is not null");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUserSubscribedSpecialPages(Long userId)
	{
		return getHibernateTemplate().find("select model.specialpage.specialPageId,model.specialpage.name,model2.showImgPath,model2.title,model2.description,model.user.userId " +
				" from SpecialPageSubscriptions model,SpecialPageInfo model2  " +
				" where model.user.userId = ? and model.specialpage.specialPageId = model2.specialPage.specialPageId",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getUserSubscribedSpecialPageIds(Long userId)
	{
		return getHibernateTemplate().find("select model.specialpage.specialPageId from SpecialPageSubscriptions model,SpecialPageInfo model2 where " +
				" model.user.userId = ? and model.specialpage.specialPageId = model2.specialPage.specialPageId",userId);
		
	}
	
	public List<Object[]> getAllSpecialPages(){
		return getHibernateTemplate().find("select distinct model.specialpage.specialPageId,model.specialpage.name from SpecialPageSubscriptions model ");
	
	}
	public List<Long> getAllSpecialPagesSubscribedByUser(Long userId){
		return getHibernateTemplate().find("select model.specialpage.specialPageId from SpecialPageSubscriptions model where model.user.userId = ?",userId);	
	}
	
	public List<Object[]> getSpecialSubscriptionsForPublicProfileStreeming(Long userId,Date toDate,Date fromDate)
	{
		Query query = getSession().createQuery("select model.specialpage.title ," +
				" model.updatedTime," +
				" model.user.firstName , " +//2
				" model.user.lastName ," +//3
				" model.user.profileImg ," +//4
				" model.specialpage.profileImgPath ," +//5
				" model.specialpage.specialPageId " +//6
				" from SpecialPageSubscriptions model " +
				" where model.user.userId = :userId and " +
				" date(model.updatedTime) >= :fromDate  and " +
				" date(model.updatedTime) <= :toDate");
		query.setParameter("userId", userId);
		query.setParameter("toDate", toDate);
		query.setParameter("fromDate", fromDate);
		return query.list();
	}
}
