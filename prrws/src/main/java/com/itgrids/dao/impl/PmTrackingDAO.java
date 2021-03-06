package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import com.itgrids.dao.IPmTrackingDAO;
import com.itgrids.model.PmTracking;
import com.sun.org.apache.bcel.internal.generic.Select;

@Repository
public class PmTrackingDAO extends GenericDaoHibernate<PmTracking, Long> implements IPmTrackingDAO {

	@Autowired
	SessionFactory sessionFactory;
	PmTrackingDAO(){
		super(PmTracking.class);
	}
	
	public List<Object[]> getPetitionTrackingHistoryDetails(Long petitionId,List<Long> subWorksList){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.pmTrackingId,petition.petitionId,"//0 ==> pmTrackingId,1==>petitionId
				+ " pmSubWorkDetails.pmSubWorkDetailsId, " //2 ==> pmSubWorkDetailsId
				+ " pmTrackingAction.pmTrackingActionId,pmTrackingAction.actionName," //3 ==>pmTrackingActionId, 4 ==> actionName
				+ " pmStatus.pmStatusId,pmStatus.status,model.remarks, " // ==>
				+ " document.documentId,document.path, "
				+ " pmDepartmentDesignationOfficer.pmDepartmentDesignationOfficerId,"
				+ " pmDepartmentDesignation.pmDepartmentDesignationId, pmDepartment.pmDepartmentId,pmDepartment.department,"
				+ " pmOfficer.pmOfficerId,pmOfficer.name,pmOfficer.mobileNo,"
				+ " pmDeptDesignation.pmDepartmentDesignationId,"
				+ " pmDept.pmDepartmentId,pmDept.department,"
				+ " insertedUser.userId,"
				+ " updatedUser.userId,"
				+ " model.insertedTime " // 22 ==> insertedTime
				+ " from PmTracking model "
				+ " left join model.petition petition "
				+ " left join model.pmSubWorkDetails pmSubWorkDetails "
				+ " left join model.pmTrackingAction pmTrackingAction "
				+ " left join model.pmStatus pmStatus "
				+ " left join model.document document "
				+ " left join model.pmDepartmentDesignationOfficer pmDepartmentDesignationOfficer "
				//+ " left join model.pmDepartmentDesignationOfficer.pmDepartmentDesignation pmDepartmentDesignation "
				//+ " left join model.pmDepartmentDesignationOfficer.pmDepartmentDesignation.pmDepartment pmDepartment "
				+ " left join pmDepartmentDesignationOfficer.pmDepartmentDesignation pmDepartmentDesignation "
				+ " left join pmDepartmentDesignation.pmDepartment pmDepartment "
				+ " left join model.pmDepartmentDesignationOfficer.pmOfficer pmOfficer "
				+ " left join model.pmDepartmentDesignation pmDeptDesignation "
				+ " left join model.pmDepartmentDesignation.pmDepartment pmDept "
				+ " left join model.insertedUser insertedUser "
				+ " left join model.updatedUser updatedUser where " );
				
		   
		if((petitionId != null && petitionId.longValue() >0l) && (subWorksList == null || subWorksList.size() == 0)){
			sb.append(" petition.petitionId = :petitionId and  (pmSubWorkDetails.pmSubWorkDetailsId is null) ");
		}else if(subWorksList != null && subWorksList.size() > 0){
			sb.append(" pmSubWorkDetails.pmSubWorkDetailsId in(:subWorksList)");
		}
		
		sb.append(" order by date(model.insertedUser) " );
		 Query query = getSession().createQuery(sb.toString());
		 if(petitionId != null && petitionId.longValue() >0l){
			 query.setParameter("petitionId", petitionId);
		 }
		 if(subWorksList != null && subWorksList.size() > 0){
			 query.setParameterList("subWorksList", subWorksList);
		 }
		 return query.list();
		 }
	
	public List<Object[]> getLatestTrackingDetails(Long petitionId,List<Long> subWorksList){
		StringBuilder str = new StringBuilder();
		str.append(" select model.petitionId, model.pmSubWorkDetailsId, pmTrackingAction.actionName, document.path, model.remarks, model.insertedTime," +
				"  insertedUser.username,pmStatus.status, pmOfficer.name, pmOfficer.mobileNo, pmOfficerDesignation.designation " +
				" from PmTracking model  " +
				" left join  model.pmTrackingAction pmTrackingAction " +
				" left join  model.document document " +
				" left join  model.insertedUser insertedUser " +
				" left join model.pmStatus pmStatus" +
				" left join model.pmDepartmentDesignationOfficer pmDepartmentDesignationOfficer " +
				" left join pmDepartmentDesignationOfficer.pmOfficer pmOfficer" +
				" left join pmDepartmentDesignationOfficer.pmDepartmentDesignation pmDepartmentDesignation" +
				" left join pmDepartmentDesignation.pmOfficerDesignation pmOfficerDesignation" +
				"  where model.petitionId =:petitionId ");
		if(subWorksList != null && subWorksList.size()>0)
			str.append(" and model.pmSubWorkDetailsId in (:subWorksList) ");
		str.append(" order by model.insertedTime desc ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		if(subWorksList != null && subWorksList.size()>0)
			query.setParameterList("subWorksList", subWorksList);
		
		return query.list();
	}
	
	public List<Object[]> getPetitionAndWorkWiseHistoryDetails(Long petitionId,List<Long> subWorksList){
		StringBuilder str = new StringBuilder();
		str.append(" select model.petitionId, model.pmSubWorkDetailsId,"//0,1 
				+ " pmTrackingAction.pmTrackingActionId,pmTrackingAction.actionName, "//2,3 
				+ " document.path, model.remarks, model.insertedTime," //4,5,6
				+ " insertedUser.userId,insertedUser.username,"//7,8
				+ " pmStatus.pmStatusId,pmStatus.status,"//9,10
				+ " pmOfficer.pmOfficerId,pmOfficer.name, pmOfficer.mobileNo, "//11,12,13
				+ " pmOfficerDesignation.pmOfficerDesignationId,pmOfficerDesignation.designation," +//14,15
				"  pmDepartment.department ,pmDepartment.shortName,pmSubWorkDetails.grievanceDescrption,petition.workName , document.documentId," +//16,17,18,19,20
				"  model.assignedToPmDepartmentDesignationOfficerId, assignedToPmOfficerDesignation.designation,assignedToPmOfficerDesignation.shortName," +//21,22,23
				"  assignToPmOfficer.name, assignToPmOfficer.mobileNo,pmSubWorkDetails.isDeleted ,petition.isDeleted,documentType.pmDocumentType," +//24,25,26,27,28
				"  pmOfficerDepartment.shortName " +//29
				" from PmTracking model  " +
				" left join  model.pmTrackingAction pmTrackingAction " +
				" left join  model.document document " +
				" left join  model.insertedUser insertedUser " +
				" left join model.pmStatus pmStatus" +
				" left join model.pmDepartmentDesignationOfficer pmDepartmentDesignationOfficer " +
				" left join pmDepartmentDesignationOfficer.pmOfficer pmOfficer" +
				" left join pmDepartmentDesignationOfficer.pmDepartmentDesignation pmDepartmentDesignation" +
				" left join pmDepartmentDesignation.pmOfficerDesignation pmOfficerDesignation " +
				" left join model.pmSubWorkDetails pmSubWorkDetails " +
				" left join pmSubWorkDetails.pmDepartment pmDepartment " +
				" left join model.petition petition " +
				" left join model.assignedToPmDepartmentDesignationOfficer assignedToPmDepartmentDesignationOfficer " +
				" left join assignedToPmDepartmentDesignationOfficer.pmDepartmentDesignation assignedToPmDepartmentDesignation " +
				" left join assignedToPmDepartmentDesignation.pmOfficerDesignation assignedToPmOfficerDesignation " +
				" left join assignedToPmDepartmentDesignationOfficer.pmOfficer assignToPmOfficer" +
				" left join model.documentType documentType " +
				" left join pmDepartmentDesignation.pmDepartment pmOfficerDepartment " );
			    //" left join model.pmDepartmentDesignationOfficer pmDepartmentDesignationOfficer " +
			   // " left join pmDepartmentDesignationOfficer.pmDepartmentDesignation pmDepartmentDesignation " +
			   // " left join pmDepartmentDesignation.pmOfficerDesignation pmOfficerDesignation " +
		
		   str.append(" where model.isDeleted='N' and model.pmStatusId != 2  ");
			   if(petitionId != null && petitionId.longValue() >0l){
				   str.append(" and model.petitionId =:petitionId ");
			   }	  
				if(subWorksList != null && subWorksList.size()>0){
					str.append(" and  model.pmSubWorkDetailsId in (:subWorksList) ");
				}
			
		str.append(" order by model.insertedTime desc ");
		Query query = getSession().createQuery(str.toString());
		 if(petitionId != null && petitionId.longValue() >0l){
		query.setParameter("petitionId", petitionId);
		 }
		if(subWorksList != null && subWorksList.size()>0) {
			query.setParameterList("subWorksList", subWorksList);
		}
		return query.list();
	}
	
	public Long getLatestUpdatedDetailsOfPetition(Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append("select distinct max(model.pmTrackingId) from PmTracking model where model.petitionId =:petitionId and model.isDeleted='N'  " );
		Query query = getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		return (Long) query.uniqueResult();
	}
}
