package com.itgrids.dao.impl;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmPetitionAssignedOfficerDAO;
import com.itgrids.model.PmPetitionAssignedOfficer;
import com.itgrids.utils.IConstants;

@Repository
public class PmPetitionAssignedOfficerDAO extends GenericDaoHibernate<PmPetitionAssignedOfficer, Long> implements IPmPetitionAssignedOfficerDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmPetitionAssignedOfficerDAO() {
		super(PmPetitionAssignedOfficer.class);
		
	}
	public List<Object[]> getAssignedPetitionforPetitionDeptDesignationOfficer(List<Long> pmDeptDesignationOfficerIdsList){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.petitionId,model.pmSubWorkDetailsId,model.pmSubWorkDetails.pmStatusId from PmPetitionAssignedOfficer model where model.petition.isDeleted='N' and  model.isDeleted='N' and model.pmDepartmentDesignationOfficerId in (:pmDeptDesignationOfficerIdsList)  ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("pmDeptDesignationOfficerIdsList", pmDeptDesignationOfficerIdsList);
		return query.list();
	}
	
	public List<Long> getAssingedOfficerStatusIdsList(List<Long> deptDesignationIdsList){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.pmStatusId from PmPetitionAssignedOfficer model where model.isDeleted='N' and " +
				" model.pmDepartmentDesignationId in (:deptDesignationIdsList) group by model.pmStatusId order by model.insertedTime desc ");
		//str.append("select distinct model.pmPetitionAssignedOfficerId, model.pmStatusId from PmPetitionAssignedOfficer model where model.isDeleted='N' " +
		//		   " order by model.insertedTime desc ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("deptDesignationIdsList", deptDesignationIdsList);
		return query.list();
	}
	
	public List<Object[]> getActionTypeDetailsForDeptDesiOfficerId(List<Long> deptDesignationOfficerIdsList,Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.petitionId,model.actionType,model.pmActionTypeId,model.pmStatus.status,model.pmStatusId from PmPetitionAssignedOfficer model where model.isDeleted='N' and model.pmDepartmentDesignationOfficerId in (:deptDesignationOfficerIdsList) and " +
				"  model.petitionId=:petitionId order by model.insertedTime desc ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("deptDesignationOfficerIdsList", deptDesignationOfficerIdsList);
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	
	public List<Object[]> getPetitionAssignedPrinciplSecretoryDetails(Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.pmDepartmentDesignationOfficer.pmOfficerId, model.pmDepartmentDesignationOfficer.pmDepartmentDesignation.pmOfficerDesignationId" +
				"  from PmPetitionAssignedOfficer model where model.isDeleted='N' and " +
				" model.petitionId=:petitionId and model.pmDepartmentDesignation.pmOfficerDesignationId in (:finalApprovedAccesDesignationIdsList) ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("finalApprovedAccesDesignationIdsList", IConstants.FINAL_APPROVED_ACCESS_DESIGNAION_IDS);
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	
	public List<Object[]> getOfficerDetailsForOfficerIdsList(List<Long> assignedToOfficerIdsList){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.pmPetitionAssignedOfficerId, model.pmDepartmentDesignationOfficer.pmOfficer.name, model.pmDepartmentDesignation.pmOfficerDesignation.designation from PmPetitionAssignedOfficer model where model.isDeleted='N' and model.pmPetitionAssignedOfficerId in (:assignedToOfficerIdsList)  " +
				"  order by model.insertedTime desc ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("assignedToOfficerIdsList", assignedToOfficerIdsList);
		return query.list();
	}
	
	public List<Object[]> getLatestUpdatedDetailsOfPetition(Set<Long> petitionIds){
		StringBuilder str = new StringBuilder();
		if(petitionIds != null && petitionIds.size() >0){
			str.append("select distinct model.pmPetitionAssignedOfficerId, model.pmDepartmentDesignationOfficer.pmOfficer.name," +//0,1
					" model.pmDepartmentDesignation.pmOfficerDesignation.designation" +//2
					",model.pmDepartmentDesignation.pmDepartment.shortName " +//3
					",model.petition.petitionId,model.insertedTime ,model.pmSubWorkDetailsId " +//4,5,6
					"from PmPetitionAssignedOfficer model where model.pmPetitionAssignedOfficerId  " +
					"in (SELECT max(model2.pmPetitionAssignedOfficerId) from PmPetitionAssignedOfficer model2 " +
					" where model2.petitionId in (:petitionIds) and model2.isDeleted='N' group by model2.petitionId)   " );
			Query query = getSession().createQuery(str.toString());
			query.setParameterList("petitionIds", petitionIds);
			return query.list();
		}
		return null;
	}
	
}
