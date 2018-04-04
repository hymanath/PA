package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDepartmentDesignationOfficerDAO;
import com.itgrids.model.PmDepartmentDesignationOfficer;

@Repository
public class PmDepartmentDesignationOfficerDAO extends GenericDaoHibernate<PmDepartmentDesignationOfficer, Long>implements IPmDepartmentDesignationOfficerDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmDepartmentDesignationOfficerDAO() {
		super(PmDepartmentDesignationOfficer.class);
	}

	public List<Object[]> getDeptDesignationOfficerDetailsByDeptDesignation(List<Long> designationIdsList,List<Long> deptIdsList,List<Long> districtIdsList){
		StringBuilder str = new StringBuilder();
		//str.append(" select distinct model.pmDepartmentDesignationOfficerId, model.pmOfficer.name," +
		str.append(" select distinct model.pmOfficer.pmOfficerId, model.pmOfficer.name," +
				"  model.pmOfficer.mobileNo,model.pmDepartmentDesignation.pmDepartment.department, " +
				" model.pmDepartmentDesignation.pmOfficerDesignation.designation , state.stateId,district.districtId, constituency.constituencyId" +//4,5,6
				" from PmDepartmentDesignationOfficer model , PmOfficerUser model1 "
				+ " left join model1.address address "
				+ " left join address.state state "
				+ " left join address.district district "
				+ " left join address.constituency constituency " +
				//"  where model.pmDepartmentDesignationId =:deptDesignationId and " +
				"  where model.pmOfficerId = model1.pmOfficerId and model.pmDepartmentDesignation.pmOfficerDesignationId in (:designationIdsList) and " +
				" model.isActive ='Y' and model.pmOfficer.isActive ='Y' and model.pmDepartmentDesignation.isDeleted='N' " );
		if(deptIdsList != null && deptIdsList.size()>0){
			str.append("  and model.pmDepartmentDesignation.pmDepartmentId in (:deptIdsList) ");
		}
		if(districtIdsList != null && districtIdsList.size()>0){
			str.append("  and district.districtId in (:districtIdsList) ");
		}
				//"  group by model.pmOfficer.pmOfficerId order by model.pmDepartmentDesignation.pmDepartment.department,model.pmDepartmentDesignation.pmOfficerDesignation.designation ");
		str.append("   group by model.pmOfficer.pmOfficerId order by model.pmOfficer.name ");
		Query query = getSession().createQuery(str.toString());
		if(designationIdsList != null && designationIdsList.size()>0)
			query.setParameterList("designationIdsList", designationIdsList);
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(districtIdsList != null && districtIdsList.size()>0){
			query.setParameterList("districtIdsList", districtIdsList);
		}
		return query.list();
		
	}
	
	public List<Object[]> getDeptDesignationOfficerDetailsByDeptAndOffId(Long officerDesignationId,Long pmDepartmentDesignationOfficerId){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.pmDepartmentDesignationOfficerId,model.pmDepartmentDesignationId, model.pmOfficer.name," +
				"  model.pmOfficer.mobileNo,model.pmDepartmentDesignation.pmDepartment.department, " +
				" model.pmDepartmentDesignation.pmOfficerDesignation.designation" +
				",model.pmDepartmentDesignation.pmDepartment.shortName from PmDepartmentDesignationOfficer model" +
				"  where model.pmDepartmentDesignation.pmOfficerDesignationId =:officerDesignationId and model.pmOfficerId =:pmDepartmentDesignationOfficerId and " +
				" model.isActive ='Y' and model.pmOfficer.isActive ='Y' " +
				" order by model.pmDepartmentDesignation.pmDepartment.department,model.pmDepartmentDesignation.pmOfficerDesignation.designation ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("pmDepartmentDesignationOfficerId", pmDepartmentDesignationOfficerId);
		query.setParameter("officerDesignationId", officerDesignationId);
		return query.list();
	}
	
	public Long geOfficerIdByDeptDesigIds(List<Long> deptdesigIds) {
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.pmOfficer.pmOfficerId from PmDepartmentDesignationOfficer model where model.isActive ='Y' and model.pmOfficer.isActive ='Y' " );
		if(deptdesigIds != null && deptdesigIds.size() >0){
			str.append(" and model.pmDepartmentDesignation.pmDepartmentDesignationId in (:deptdesigIds) ");
		}
		Query query = getSession().createQuery(str.toString());
		if(deptdesigIds != null && deptdesigIds.size() >0){
			query.setParameterList("deptdesigIds", deptdesigIds);
		}
		return (Long)query.uniqueResult();
	}
	
}
