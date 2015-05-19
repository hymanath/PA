package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserAuthDAO;
import com.itgrids.partyanalyst.model.SurveyUserAuth;

public class SurveyUserAuthDAO  extends GenericDaoHibernate<SurveyUserAuth,Long> implements ISurveyUserAuthDAO {

	public SurveyUserAuthDAO(){
		super(SurveyUserAuth.class);
	}
	public Long checkRecordExistWithGivenDetailsOrNot(Long userId,String imei){
		Query query = getSession().createQuery("select count(*) from SurveyUserAuth model where model.isDeleted='N' and (model.eventSurveyUser.eventSurveyUserId=:userId or " +
				" model.imeiNo = :imei or model.imeiNo2=:imei) ");
		query.setParameter("userId", userId);
		query.setParameter("imei", imei);
		
		return (Long)query.uniqueResult();
	}
	
	public Long checkRecordExistWithGivenDetailsOrNot(Long userId,String imei1,String imei2){
		Query query = getSession().createQuery("select count(*) from SurveyUserAuth model where model.isDeleted='N' and (model.eventSurveyUser.eventSurveyUserId=:userId or " +
				" (model.imeiNo = :imei1 and model.imeiNo2=:imei2) or (model.imeiNo = :imei2 and model.imeiNo2=:imei1) ) ");
		query.setParameter("userId", userId);
		query.setParameter("imei1", imei1);
		query.setParameter("imei2", imei2);
		
		return (Long)query.uniqueResult();
	}
	
	public Long checkRecordBelongsToUserOrNot(Long userId,String imei){
		Query query = getSession().createQuery("select count(*) from SurveyUserAuth model where model.isDeleted='N' and model.eventSurveyUser.eventSurveyUserId=:userId and " +
				" (model.imeiNo = :imei or model.imeiNo2=:imei) ");
		query.setParameter("userId", userId);
		query.setParameter("imei", imei);
		
		return (Long)query.uniqueResult();
	}
	
	public Long checkRecordBelongsToUserOrNot(Long userId,String imei1,String imei2){
		Query query = getSession().createQuery("select count(*) from SurveyUserAuth model where model.isDeleted='N' and model.eventSurveyUser.eventSurveyUserId=:userId and " +
				" ((model.imeiNo = :imei1 and model.imeiNo2=:imei2) or (model.imeiNo = :imei2 and model.imeiNo2=:imei1) ) ");
		query.setParameter("userId", userId);
		query.setParameter("imei1", imei1);
		query.setParameter("imei2", imei2);
		
		return (Long)query.uniqueResult();
	}
	
	public void updateRecordBelongsToUserOrNot(Long userId,String imei){
		Query query = getSession().createQuery("update SurveyUserAuth model set model.isDeleted='Y' where model.eventSurveyUser.eventSurveyUserId=:userId and " +
				" (model.imeiNo = :imei or model.imeiNo2=:imei) ");
		query.setParameter("userId", userId);
		query.setParameter("imei", imei);
		
		query.executeUpdate();
	}
	
	public void updateRecordBelongsToUserOrNot(Long userId,String imei1,String imei2){
		Query query = getSession().createQuery("update SurveyUserAuth model set model.isDeleted='Y' where model.eventSurveyUser.eventSurveyUserId=:userId and " +
				" ((model.imeiNo = :imei1 and model.imeiNo2=:imei2) or (model.imeiNo = :imei2 and model.imeiNo2=:imei1) ) ");
		query.setParameter("userId", userId);
		query.setParameter("imei1", imei1);
		query.setParameter("imei2", imei2);
		
		query.executeUpdate();
	}
	
	public Long checkUserAlreadyLoggedInAnotherTab(Long userId,String imei){
		Query query = getSession().createQuery("select count(*) from SurveyUserAuth model where model.isDeleted='N' and model.eventSurveyUser.eventSurveyUserId=:userId and " +
				" ((model.imeiNo is not null and model.imeiNo != :imei) or (model.imeiNo2 is not null and  model.imeiNo2 != :imei)) ");
		query.setParameter("userId", userId);
		query.setParameter("imei", imei);
		
		return (Long)query.uniqueResult();
	}
	
	public Long checkUserAlreadyLoggedInAnotherTab(Long userId,String imei1,String imei2){
		Query query = getSession().createQuery("select count(*) from SurveyUserAuth model where model.isDeleted='N' and model.eventSurveyUser.eventSurveyUserId=:userId and " +
				" ((model.imeiNo != :imei1 and model.imeiNo2 != :imei2) and (model.imeiNo != :imei2 and model.imeiNo2 != :imei1) ) ");
		query.setParameter("userId", userId);
		query.setParameter("imei1", imei1);
		query.setParameter("imei2", imei2);
		
		return (Long)query.uniqueResult();
	}
	public List<Object[]> getAuthDetailsByUserId(String userName){
		//0authId,1userName,2name,3mobileNo,4imei
		Query query = getSession().createQuery("select model.surveyUserAuthId,model.eventSurveyUser.userName,model.eventSurveyUser.firstName,model.eventSurveyUser.mobile,  " +
				"  model.imeiNo from SurveyUserAuth model where model.eventSurveyUser.userName=:userName and model.isDeleted ='N'");
		query.setParameter("userName", userName);
		return query.list();
	}
	
	public List<Object[]> getAuthDetailsByImei(String imei){
		//0authId,1userName,2name,3mobileNo,4imei
		Query query = getSession().createQuery("select model.surveyUserAuthId,model.eventSurveyUser.userName,model.eventSurveyUser.firstName,model.eventSurveyUser.mobile,  " +
				"  model.imeiNo from SurveyUserAuth model where model.isDeleted ='N' and (model.imeiNo like '%"+imei+"%' or model.imeiNo2 like '%"+imei+"%') ");
		return query.list();
	}
	
	public void updateStatus(Long authId,String cause,Long userId){
		Query query = getSession().createQuery("update SurveyUserAuth model set model.isDeleted='Y',model.cause=:cause,model.updatedById=:userId where model.surveyUserAuthId=:authId ");
		query.setParameter("authId", authId);
		query.setParameter("cause", cause);
		query.setParameter("userId", userId);
		
		query.executeUpdate();
	}
}
