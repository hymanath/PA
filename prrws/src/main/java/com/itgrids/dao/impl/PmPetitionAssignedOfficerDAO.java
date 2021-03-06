package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmPetitionAssignedOfficerDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.PmPetitionAssignedOfficer;
import com.itgrids.utils.IConstants;

@Repository
public class PmPetitionAssignedOfficerDAO extends GenericDaoHibernate<PmPetitionAssignedOfficer, Long> implements IPmPetitionAssignedOfficerDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmPetitionAssignedOfficerDAO() {
		super(PmPetitionAssignedOfficer.class);
		
	}
	public List<Object[]> getAssignedPetitionforPetitionDeptDesignationOfficer(List<Long> pmDeptDesignationOfficerIdsList,InputVO inputVO){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.petitionId,model.pmSubWorkDetailsId,model.pmSubWorkDetails.pmStatusId from PmPetitionAssignedOfficer model where model.petition.isDeleted='N' and  model.isDeleted='N' and model.pmDepartmentDesignationOfficerId in (:pmDeptDesignationOfficerIdsList)  ");
		if(inputVO != null && inputVO.getSearchDeptIdsList() != null && inputVO.getSearchDeptIdsList().size()>0)
			str.append(" and model.pmSubWorkDetails.pmDepartmentId in (:pmDepartmentIdsList) ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("pmDeptDesignationOfficerIdsList", pmDeptDesignationOfficerIdsList);
		
		if(inputVO != null && inputVO.getSearchDeptIdsList() != null && inputVO.getSearchDeptIdsList().size()>0)
			query.setParameterList("pmDepartmentIdsList", inputVO.getSearchDeptIdsList());
		
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
					",model.petition.petitionId,model.insertedTime ,model.pmSubWorkDetailsId,model.pmDepartmentDesignationOfficer.pmOfficerId " +//4,5,6,7
					"from PmPetitionAssignedOfficer model where model.pmPetitionAssignedOfficerId  " +
					"in (SELECT max(model2.pmPetitionAssignedOfficerId) from PmPetitionAssignedOfficer model2 " +
					" where model2.petitionId in (:petitionIds) and model2.isDeleted='N' group by model2.petitionId)   " );
			Query query = getSession().createQuery(str.toString());
			query.setParameterList("petitionIds", petitionIds);
			return query.list();
		}
		return null;
	}
	
	public List<Object[]> getPmOfficerAssignedPetitionDetails(InputVO inputVO,Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		/*sb.append(" select model.pmDepartmentDesignationOfficer.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId" +//0
				",model.pmDepartmentDesignationOfficer.pmDepartmentDesignation.pmOfficerDesignation.designation" +//1
				",model.pmDepartmentDesignationOfficer.pmOfficer.pmOfficerId" +//2
				",model.pmDepartmentDesignationOfficer.pmOfficer.name" +//3
				",model.petitionId" +//4
				",pmSubWorkDetails.pmSubWorkDetailsId" +//5
				//",count(distinct model.petitionId)" +//4
				//",count(distinct pmSubWorkDetails.pmSubWorkDetailsId)" +//5
				",pmSubWorkDetails.pmStatus.pmStatusId" +//6
				",pmSubWorkDetails.pmStatus.status" +//7
				",model.pmStatus.pmStatusId "+//8
				",pmSubWorkDetails.costEstimation "+//9
				" from PmPetitionAssignedOfficer model " +
				"left join  model.pmSubWorkDetails pmSubWorkDetails " +
				",PmRepresenteeRefDetails model1 " +
				"where model.isDeleted='N' and model.isDeleted='N' " +
				" and pmSubWorkDetails.petition.petitionId=model.petitionId " +
				" and pmSubWorkDetails.isDeleted='N' " +
				" and model1.isDeleted='N' " +
				"and model1.pmRepresenteeDesignation.isDeleted='N' " +
				" and  model1.petition.petitionId=model.petitionId " +
				//" and model.pmDepartmentDesignationOfficer.pmOfficer.isActive='Y'" +
				" and model.petition.isDeleted='N' ");
		if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size() >0){
			sb.append(" and model.pmDepartmentDesignationOfficer.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId in (:officerDesigids) ");
		}
		if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
			sb.append(" and pmSubWorkDetails.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		//sb.append(" group by model.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId," +
			//	"model.pmDepartmentDesignationOfficer.pmOfficer.pmOfficerId,model.pmStatus.pmStatusId,pmSubWorkDetails.pmStatus.pmStatusId  " +
			//	"order by model.pmDepartmentDesignation.pmOfficerDesignation.orderNO asc ");
		Query query = getSession().createQuery(sb.toString());
		if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size() >0){
			query.setParameterList("officerDesigids", inputVO.getDesignationIds());
		}
		if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
			query.setParameterList("deptIds", inputVO.getDeptIdsList());
		}
		return query.list();*/
		
		sb.append("SELECT DISTINCT p.petition_id as petitionId , " +//0
		" COUNT(DISTINCT pswd.pm_sub_work_details_id)  as workCount" +//1
		",d.pm_officer_designation_id  as officerDesigId" +//2
		",d.designation  as officerDesig" +//3
		",po.pm_officer_id  as officerId,po.name  as officerName, " + //4,5
		" pswd.pm_status_id  as workStatusId,ppao.pm_status_id as assignedStatusId " +//6,7
		" ,sum(pswd.cost_estimation) as workCost" +//8
		" from pm_petition_assigned_officer ppao, " +
		" petition p " +
		" ,pm_sub_work_details pswd " +
		",pm_dept_designation_officer c  " +
		", pm_officer_designation d  " +
		",pm_officer po " +
		",pm_dept_designation e  " +
		"where ppao.pm_status_id is not null " + 
		"and pswd.pm_status_id is not null " + 
		"and p.petition_id=ppao.petition_id and p.is_deleted='N' and ppao.is_deleted='N'  " +
		"and pswd.petition_id=ppao.petition_id  " +
		"and pswd.is_deleted='N'  " +
		"and ppao.pm_dept_designation_id = e.pm_dept_designation_id and  " +
		"e.pm_officer_designation_id = d.pm_officer_designation_id  " +
		"and po.pm_officer_id=c.pm_officer_id  " +
		"and c.pm_dept_designation_officer_id=ppao.pm_dept_designation_officer_id" +
		" and po.is_active ='Y' " );
		
		
		if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size() >0){
			sb.append(" and d.pm_officer_designation_id in (:officerDesigids) ");
		}
		if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
			sb.append(" and pswd.pm_department_id in (:deptIds) ");
		}
		if(startDate != null && endDate != null){
			sb.append(" and  date(pswd.inserted_time) between :startDate and :endDate  ");
		}
	sb.append("GROUP BY p.petition_id ,d.pm_officer_designation_id,po.pm_officer_id ORDER BY d.order_no ASC ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("petitionId")
				.addScalar("workCount")
				.addScalar("officerDesigId")
				.addScalar("officerDesig")
				.addScalar("officerId")
				.addScalar("officerName")
				.addScalar("workStatusId")
				.addScalar("assignedStatusId")
				.addScalar("workCost");
		if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size() >0){
			query.setParameterList("officerDesigids", inputVO.getDesignationIds());
		}
		if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
			query.setParameterList("deptIds", inputVO.getDeptIdsList());
		}
		if(startDate != null && endDate != null){
			query.setDate("startDate",startDate);
			query.setDate("endDate",endDate);
		}
		return query.list();
	}
	
}
