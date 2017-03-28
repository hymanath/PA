package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
	
	public void updateStatus(Long authId,String cause,Long userId){
		Query query = getSession().createQuery("update TabLogInAuth model set model.isDeleted='Y',model.cause=:cause,model.updatedById=:userId where model.tabLogInAuthId=:authId ");
		query.setParameter("authId", authId);
		query.setParameter("cause", cause);
		query.setParameter("userId", userId);
		
		query.executeUpdate();
	}
	
	public List<Object[]> getAuthDetailsByUserId(String userName){
		//0authId,1userName,2name,3mobileNo,4imei
		Query query = getSession().createQuery("select model.tabLogInAuthId,model.cadreSurveyUser.userName,model.cadreSurveyUser.name,model.cadreSurveyUser.mobileNo,  " +
				"  model.imeiNo from TabLogInAuth model where model.cadreSurveyUser.userName=:userName and model.isDeleted ='N'");
		query.setParameter("userName", userName);
		return query.list();
	}
	
	public List<Object[]> getAuthDetailsByImei(String imei){
		//0authId,1userName,2name,3mobileNo,4imei
		Query query = getSession().createQuery("select model.tabLogInAuthId,model.cadreSurveyUser.userName,model.cadreSurveyUser.name,model.cadreSurveyUser.mobileNo,  " +
				"  model.imeiNo from TabLogInAuth model where model.isDeleted ='N' and (model.imeiNo like '%"+imei+"%' or model.imeiNo like '%"+imei+"%') ");
		return query.list();
	}
	public List<Object[]> getTabLoginDetails(String cadreSurveyUserName){
		Query query = getSession().createQuery("select model.imeiNo, " +
				" model.insertedTime,model.status,model.isDeleted,model.tabLogInAuthId " +
				" from TabLogInAuth model" +
				" where model.cadreSurveyUser.userName=:cadreSurveyUserName order by  model.tabLogInAuthId desc");
		query.setParameter("cadreSurveyUserName", cadreSurveyUserName);
		return query.list();		
	}
	public List<Object[]> getTabUserDetails(String imeiNo){
		Query query = getSession().createQuery("select distinct model.cadreSurveyUser.cadreSurveyUserId,model.cadreSurveyUser.userName, " +
				 "  model.insertedTime,model.status,model.isDeleted,model.tabLogInAuthId " +
				 "  from TabLogInAuth model where" +
				 "  model.imeiNo=:imeiNo  " +
				 "  order by model.tabLogInAuthId desc");
		query.setParameter("imeiNo", imeiNo);
		return query.list();		
	}
	
	public int updateUserORIMEIDetails(Long loginAuthId){
		//0authId,1userName,2name,3mobileNo,4imei
		Query query = getSession().createQuery("update TabLogInAuth model set model.isDeleted = 'Y' " +
				" where model.tabLogInAuthId =:loginAuthId ");
		query.setParameter("loginAuthId", loginAuthId);
		return query.executeUpdate();
	}
	
	
	public List<Long> checkAlreadyExisyToUserIMEINo(String userName,String imei){
		Query query = getSession().createQuery("select distinct model.tabLogInAuthId from TabLogInAuth model where model.cadreSurveyUser.isDeleted='N' " +
				" and model.cadreSurveyUser.isEnabled ='Y' and  model.isDeleted='N' and (model.cadreSurveyUser.userName =:userName OR " +
				" (model.imeiNo = :imei or model.imeiNo2=:imei) )");
		query.setParameter("userName", userName);
		query.setParameter("imei", imei);
		return query.list();
	}
	
}
