package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITabLogInAuthDAO;
import com.itgrids.partyanalyst.model.TabLogInAuth;

public class TabLogInAuthDAO extends GenericDaoHibernate<TabLogInAuth,Long> implements ITabLogInAuthDAO {

	public TabLogInAuthDAO(){
		super(TabLogInAuth.class);
	}
	
	public Long checkRecordExistWithGivenDetailsOrNot(Long userId,String imei){
		Query query = getSession().createQuery("select count(*) from TabLogInAuth model where model.isDeleted='N' and (model.cadreSurveyUser.cadreSurveyUserId=:userId or " +
				" model.imeiNo = :imei or model.imeiNo2=:imei) ");
		query.setParameter("userId", userId);
		query.setParameter("imei", imei);
		
		return (Long)query.uniqueResult();
	}
	
	public Long checkRecordExistWithGivenDetailsOrNot(Long userId,String imei1,String imei2){
		Query query = getSession().createQuery("select count(*) from TabLogInAuth model where model.isDeleted='N' and (model.cadreSurveyUser.cadreSurveyUserId=:userId or " +
				" (model.imeiNo = :imei1 and model.imeiNo2=:imei2) or (model.imeiNo = :imei2 and model.imeiNo2=:imei1) ) ");
		query.setParameter("userId", userId);
		query.setParameter("imei1", imei1);
		query.setParameter("imei2", imei2);
		
		return (Long)query.uniqueResult();
	}
	
	public Long checkRecordBelongsToUserOrNot(Long userId,String imei){
		Query query = getSession().createQuery("select count(*) from TabLogInAuth model where model.isDeleted='N' and model.cadreSurveyUser.cadreSurveyUserId=:userId and " +
				" (model.imeiNo = :imei or model.imeiNo2=:imei) ");
		query.setParameter("userId", userId);
		query.setParameter("imei", imei);
		
		return (Long)query.uniqueResult();
	}
	
	public Long checkRecordBelongsToUserOrNot(Long userId,String imei1,String imei2){
		Query query = getSession().createQuery("select count(*) from TabLogInAuth model where model.isDeleted='N' and model.cadreSurveyUser.cadreSurveyUserId=:userId and " +
				" ((model.imeiNo = :imei1 and model.imeiNo2=:imei2) or (model.imeiNo = :imei2 and model.imeiNo2=:imei1) ) ");
		query.setParameter("userId", userId);
		query.setParameter("imei1", imei1);
		query.setParameter("imei2", imei2);
		
		return (Long)query.uniqueResult();
	}
	
	public void updateRecordBelongsToUserOrNot(Long userId,String imei){
		Query query = getSession().createQuery("update TabLogInAuth model set model.isDeleted='Y' where model.cadreSurveyUser.cadreSurveyUserId=:userId and " +
				" (model.imeiNo = :imei or model.imeiNo2=:imei) ");
		query.setParameter("userId", userId);
		query.setParameter("imei", imei);
		
		query.executeUpdate();
	}
	
	public void updateRecordBelongsToUserOrNot(Long userId,String imei1,String imei2){
		Query query = getSession().createQuery("update TabLogInAuth model set model.isDeleted='Y' where model.cadreSurveyUser.cadreSurveyUserId=:userId and " +
				" ((model.imeiNo = :imei1 and model.imeiNo2=:imei2) or (model.imeiNo = :imei2 and model.imeiNo2=:imei1) ) ");
		query.setParameter("userId", userId);
		query.setParameter("imei1", imei1);
		query.setParameter("imei2", imei2);
		
		query.executeUpdate();
	}
	
	public Long checkUserAlreadyLoggedInAnotherTab(Long userId,String imei){
		Query query = getSession().createQuery("select count(*) from TabLogInAuth model where model.isDeleted='N' and model.cadreSurveyUser.cadreSurveyUserId=:userId and " +
				" ((model.imeiNo is not null and model.imeiNo != :imei) or (model.imeiNo2 is not null and  model.imeiNo2 != :imei)) ");
		query.setParameter("userId", userId);
		query.setParameter("imei", imei);
		
		return (Long)query.uniqueResult();
	}
	
	public Long checkUserAlreadyLoggedInAnotherTab(Long userId,String imei1,String imei2){
		Query query = getSession().createQuery("select count(*) from TabLogInAuth model where model.isDeleted='N' and model.cadreSurveyUser.cadreSurveyUserId=:userId and " +
				" ((model.imeiNo != :imei1 and model.imeiNo2 != :imei2) and (model.imeiNo != :imei2 and model.imeiNo2 != :imei1) ) ");
		query.setParameter("userId", userId);
		query.setParameter("imei1", imei1);
		query.setParameter("imei2", imei2);
		
		return (Long)query.uniqueResult();
	}
}
