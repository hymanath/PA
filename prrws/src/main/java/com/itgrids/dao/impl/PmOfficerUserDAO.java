package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmOfficerUserDAO;
import com.itgrids.model.PmOfficerUser;

@Repository
public class PmOfficerUserDAO extends GenericDaoHibernate<PmOfficerUser, Long> implements IPmOfficerUserDAO {

	@Autowired
	SessionFactory sessionFactory;

	PmOfficerUserDAO(){
		super(PmOfficerUser.class);
	}
	
	public List<Object[]> getPmOffceUserDetails(Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.pmDepartmentDesignation.pmDepartment.pmDepartmentId,");// 0-deptId
		sb.append(" model.pmDepartmentDesignation.pmDepartment.department,");//1-deptName
		sb.append( "model.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId,");//2-designtionId
		sb.append("model.pmDepartmentDesignation.pmOfficerDesignation.designation, ");//3-designtionName
		sb.append("model.pmOfficer.pmOfficerId,model.pmOfficer.name, model.pmOfficer.mobileNo, ");//4-officerId,5-name,6-mobileNo
		sb.append(" model2.pmDepartmentDesignationOfficerId , model.pmDepartmentDesignationId," +//7-deptdesiofficerId,8- deptdesigId
				" model2.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId ");//9-pmOfficerDesignationId
		//sb.append("from PmOfficerUser model,PmDepartmentDesignationOfficer model2 ");
	//	sb.append( "where model.pmDepartmentDesignationId=model2.pmDepartmentDesignationId and ");
	//	sb.append( "model.isActive ='Y' and model2.pmOfficer.isActive='Y' and model2.isActive='Y' and "
	//			+ " model.userId=:userId " );*/
		sb.append("  from PmOfficerUser model,PmDepartmentDesignationOfficer model2 where model.isActive ='Y' and model.userId =:userId and " +
				" model.pmOfficerId = model2.pmOfficerId and model.pmOfficer.isActive='Y' and model2.isActive='Y' ");
		Query query = getSession().createQuery(sb.toString());//
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getPmDeptIdByUserId(Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct dept.pmDepartmentId, model.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId, model.pmDepartmentDesignation.pmOfficerDesignation.designation" +
				",dept.shortName,model.pmDepartmentDesignation.pmDepartmentDesignationId, model.pmOfficerId from PmOfficerUser model "
				  + " left outer join model.pmDepartmentDesignation.pmDepartment dept " 
				  + " where model.userId =:userId " 
				  +"  and model.pmDepartmentDesignation.isDeleted = 'N'"
				  + " and model.pmDepartmentDesignation.pmDepartment.isDeleted = 'N' " +
				  " and model.insertedUser.isEnabled='Y' and model.insertedUser.isDeleted = 'N' order by dept.orderNo asc ");
		
		Query query = getSession().createQuery(sb.toString());//
		if(userId != null && userId.longValue() >0l){
			query.setParameter("userId", userId);
		}	
		return query.list();
		
	}
	
	public List<Long> getPmDeptStatusIdByUserIdsLst(Long userId,String isDashboard){

		if(userId != null && userId.longValue()>0L){
			StringBuilder sb = new StringBuilder();
			sb.append("select distinct model1.pmStatusId  from PmOfficerUser model,PmDepartmentDesignationStatus model1 "
					+ " where model.pmDepartmentDesignationId =  model1.pmDepartmentDesignationId ");
					if(userId != null && userId.longValue() >0l){
						sb.append(" and model.userId =:userId");
					}
					sb.append(" and model.isActive ='Y'");
					
				if(isDashboard != null && isDashboard.equalsIgnoreCase("true"))
					sb.append("  and model1.priority=1 ");
				
			Query query = getSession().createQuery(sb.toString());
			if(userId != null && userId.longValue() >0l){
				query.setParameter("userId", userId);
			}	
			return query.list();
		}
		return null;
	}
	
	public List<Long> getPmDeptDesignationIdByUserId(Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.pmDepartmentDesignationId from PmOfficerUser model where model.userId =:userId " 
				  +"  and model.pmDepartmentDesignation.isDeleted = 'N'"
				  + " and model.pmDepartmentDesignation.pmDepartment.isDeleted = 'N'");
		
		Query query = getSession().createQuery(sb.toString());//
		if(userId != null && userId.longValue() >0l){
			query.setParameter("userId", userId);
		}	
		return query.list();
		
	}
	
	public List<Object[]> getPmOffceDetailsByUserIdsList(List<Long> userIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.pmDepartmentDesignation.pmDepartment.pmDepartmentId,");// 0-deptId
		sb.append(" model.pmDepartmentDesignation.pmDepartment.department,");//1-deptName
		sb.append( "model.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId,");//2-designtionId
		sb.append("model.pmDepartmentDesignation.pmOfficerDesignation.designation, ");//3-designtionName
		sb.append("model.pmOfficer.pmOfficerId,model.pmOfficer.name, model.pmOfficer.mobileNo, ");//4-officerId,5-name,6-mobileNo
		sb.append(" model2.pmDepartmentDesignationOfficerId , model.pmDepartmentDesignationId," +//7-deptdesiofficerId,8- deptdesigId
				" model2.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId ");//9-pmOfficerDesignationId
		sb.append(",model.userId "); // 10-userId
		sb.append("   from PmOfficerUser model,PmDepartmentDesignationOfficer model2 where model.isActive ='Y' and model.userId in (:userIdsList) and " +
				" model.pmOfficerId = model2.pmOfficerId and model.pmOfficer.isActive='Y' and model2.isActive='Y' group by model.userId ");
		Query query = getSession().createQuery(sb.toString());//
		query.setParameterList("userIdsList", userIdsList);
		return query.list();
	}
}
