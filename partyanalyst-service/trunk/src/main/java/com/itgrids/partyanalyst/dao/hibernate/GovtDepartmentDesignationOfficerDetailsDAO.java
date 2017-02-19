package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsDAO;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerDetails;

public class GovtDepartmentDesignationOfficerDetailsDAO extends GenericDaoHibernate<GovtDepartmentDesignationOfficerDetails, Long> implements IGovtDepartmentDesignationOfficerDetailsDAO{

	public GovtDepartmentDesignationOfficerDetailsDAO() {
		super(GovtDepartmentDesignationOfficerDetails.class);
		
	}

	public List<Object[]> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId){
		Query query = getSession().createQuery("select distinct model.govtOfficer.govtOfficerId," +
												" model.govtOfficer.officerName" +
												" from GovtDepartmentDesignationOfficerDetails model" +
												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId = :designationId" +
												" and model.govtDepartmentDesignationOfficer.govtDepartmentLevel.govtDepartmentLevelId = :levelId" +
												" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
												" and model.isDeleted = 'N'");
		query.setParameter("designationId", designationId);
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		
		return query.list();
	}
	
	public List<Long> getDesignationOfficerIds(Long levelId,Long levelValue,Long designationId,Long officerId){
		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficerId" +
											" from GovtDepartmentDesignationOfficerDetails model" +
											" where model.govtDepartmentDesignationOfficer.govtDepartmentLevelId = :levelId" +
											" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
											" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId" +
											" and model.govtOfficer.govtOfficerId = :officerId");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("designationId", designationId);
		query.setParameter("officerId", officerId);
		return query.list();
	}
	public List<Object[]> getDeptDesigOfficerIdAndGovtOfficerIdForUserId(Long userId,List<Long> deptIdList, Long locValue, List<Long> locIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId, " +
						" model.govtOfficer.govtOfficerId " +
						" from " +
						" GovtDepartmentDesignationOfficerDetails model " +
						" where " +
						" model.user.userId = :userId and " +
						" model.isDeleted = 'N' and " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList) and ");
		if(locValue != null && locValue.longValue() == 2L){
			queryStr.append("  model.govtDepartmentDesignationOfficer.userAddress.state.stateId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 3L){
			queryStr.append("  model.govtDepartmentDesignationOfficer.userAddress.district.districtId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 4l){
			queryStr.append("  model.govtDepartmentDesignationOfficer.userAddress.constituency.constituencyId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 5l){
			queryStr.append(" model.govtDepartmentDesignationOfficer.userAddress.tehsil.tehsilId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 6l){
			queryStr.append(" model.govtDepartmentDesignationOfficer.userAddress.panchayat.panchayatId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 7l){
			queryStr.append(" model.govtDepartmentDesignationOfficer.userAddress.localElectionBody.localElectionBodyId in (:locIdList) ");
		}else if(locValue != null && locValue.longValue() == 8l){
			queryStr.append(" model.govtDepartmentDesignationOfficer.userAddress.ward.constituencyId in (:locIdList)");
		}		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("userId",userId);   
		query.setParameterList("deptIdList",deptIdList);
		query.setParameterList("locIdList", locIdList);
		return query.list();
	}
}
