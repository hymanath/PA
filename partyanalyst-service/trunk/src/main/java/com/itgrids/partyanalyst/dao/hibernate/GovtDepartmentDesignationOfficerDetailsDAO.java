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
	
	public List<Long> getDesignationOfficerIds(Long levelId,Long levelValue,Long designationId){
		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficerId" +
											" from GovtDepartmentDesignationOfficer model" +
											" where model.govtDepartmentLevelId = :levelId" +
											" and model.levelValue = :levelValue" +
											" and model.govtDepartmentDesignationId = :designationId");
											//" and model.govtOfficer.govtOfficerId = :officerId" +
											//" and model.isDeleted = 'N'");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("designationId", designationId);
		//query.setParameter("officerId", officerId);
		return query.list();
	}
	public List<Long> getDesignationOfficerIdsNew(Long levelId,Long levelValue,Long designationId,Long officerId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId" +
											" from GovtDepartmentDesignationOfficerDetails model" +
											" where model.govtDepartmentDesignationOfficer.govtDepartmentLevelId = :levelId" +
											" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue" +
											" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId = :designationId" +
											" and model.isDeleted = 'N'  " );
		
		if(officerId !=null && officerId.longValue()>0l){
			sb.append(" and model.govtOfficerId = :officerId");
		}
	
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("designationId", designationId);
		query.setParameter("officerId", officerId);
		return query.list();
	}
	public List<Object[]> getDeptDesigOfficerIdAndGovtOfficerIdForUserId(Long userId,List<Long> deptIdList, Long locValue, List<Long> locIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct" +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignationOfficerId " +
						" , model.govtOfficerId " +
						" , '0' " +
						" from " +
						" GovtDepartmentDesignationOfficerDetails model" +
						" left join model.govtDepartmentDesignationOfficer.userAddress UA" +
						" left join UA.state S" +
						" left join UA.district D" +
						" left join UA.constituency C" +
						" left join UA.tehsil T" +
						" left join UA.panchayat P" +
						" left join UA.localElectionBody LEB" +
						" left join UA.ward W " +
						" where model.isDeleted = 'N' " );
		if(userId != null && userId.longValue() > 0L){
			queryStr.append(" and model.user.userId = :userId  ");
		}			
		if(deptIdList != null && deptIdList.size() > 0){
			queryStr.append(" and model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId in (:deptIdList)  ");
		}
		if(locValue != null && locIdList != null && locIdList.size() > 0){
			if( locValue.longValue() == 2L){
				queryStr.append(" and S.stateId in (:locIdList) ");
			}else if(locValue.longValue() == 3L){
				queryStr.append(" and D.districtId in (:locIdList) ");
			}else if(locValue.longValue() == 4l){
				queryStr.append(" and C.constituencyId in (:locIdList) ");
			}else if(locValue.longValue() == 5l){
				queryStr.append(" and T.tehsilId in (:locIdList) ");
			}else if(locValue.longValue() == 6l){
				queryStr.append(" and P.panchayatId in (:locIdList) ");
			}else if(locValue.longValue() == 7l){
				queryStr.append(" and LEB.localElectionBodyId in (:locIdList) ");
			}else if(locValue.longValue() == 8l){
				queryStr.append("and  W.constituencyId in (:locIdList) ");
			}
		}
				
		Query query = getSession().createQuery(queryStr.toString());
		if(userId != null && userId.longValue() > 0L){
			query.setParameter("userId",userId); 
		}
		
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList",deptIdList);
		}
		if(locValue != null && locIdList != null && locIdList.size() > 0){
			query.setParameterList("locIdList", locIdList);
		}
		
		return query.list();
	}
	
	public List<Object[]> getUserIdForDeptDesigOfficerIdAndGovtOfficerId(Long subTaskGovtOfficerId,Long govtDepartmentDesignationOfficerId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.user.userId, '' " +
						" from " +
						" GovtDepartmentDesignationOfficerDetailsNew model" +
						" where model.isDeleted = 'N' " );
		if(subTaskGovtOfficerId != null && subTaskGovtOfficerId.longValue() > 0L){
			queryStr.append(" and model.govtOfficerId = :subTaskGovtOfficerId  ");
		}			
		if(govtDepartmentDesignationOfficerId != null && govtDepartmentDesignationOfficerId.longValue() > 0L){
			queryStr.append(" and model.govtDepartmentDesignationOfficerId = :govtDepartmentDesignationOfficerId  ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		if(subTaskGovtOfficerId != null && subTaskGovtOfficerId.longValue() > 0L){
			query.setParameter("subTaskGovtOfficerId",subTaskGovtOfficerId); 
		}
		if(govtDepartmentDesignationOfficerId != null && govtDepartmentDesignationOfficerId.longValue() > 0L){
			query.setParameter("govtDepartmentDesignationOfficerId",govtDepartmentDesignationOfficerId); 
		}
		return query.list();
	}
	
	public List<String> getDesignationsForUser(Long userId){
		Query query = getSession().createQuery("select model.govtOfficer.officerName " +
				//("select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
												" from GovtDepartmentDesignationOfficerDetails model" +
												" where model.user.userId = :userId" +
												" and model.isDeleted = 'N'");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getDesignationsForDepartmentAndLevelLocation(Long govtDepartmentId,Long levelId,Long levelValue){
		Query query = getSession().createQuery("select distinct model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId," +
												" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName" +
												" from GovtDepartmentDesignationOfficerDetails model" +
												" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentId = :govtDepartmentId" +
												" and model.govtDepartmentDesignationOfficer.govtDepartmentLevel.govtDepartmentLevelId = :levelId " +
												" and model.govtDepartmentDesignationOfficer.levelValue = :levelValue ");
		
		query.setParameter("govtDepartmentId", govtDepartmentId);
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		return query.list();
	}
	
	public List<Object[]> getLocationInfoOfUser(Long userId){
		
		Query query = getSession().createQuery(" SELECT model.govtDepartmentDesignationOfficer.govtDepartmentLevelId,model.govtDepartmentDesignationOfficer.levelValue " +
				" FROM GovtDepartmentDesignationOfficerDetails model " +
				" WHERE model.isDeleted = 'N'" +
				" and model.userId = :userId  ") ;
		
		query.setParameter("userId", userId);
		return query.list();
		
	}
	
	public List<Object[]> getNewLocationInfoOfUser(Long userId){
		
		Query query = getSession().createQuery(" " +
				" SELECT  '','' "+
				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.departmentName, " +
				" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName, " +
				" model.govtOfficer.officerName, " +
				" model.govtOfficer.mobileNo " +
				" model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationName" +
				" FROM GovtDepartmentDesignationOfficerDetailsNew model " +
				" WHERE model.isDeleted = 'N'" +
				" and model.userId = :userId  ") ;
		
		query.setParameter("userId", userId);
		return query.list();
		
	}
	public List<Long> getDesignationInfoForUser(Long userId){
		Query query = getSession().createQuery(" " +
				 " select model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartmentDesignationId " +
												" from GovtDepartmentDesignationOfficerDetails model" +
												" where model.user.userId = :userId" +
												" and model.isDeleted = 'N' ");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<String> getHigherOfficerMobileNums(List<Long> designationIds){
		Query query = getSession().createQuery(" select distinct model.govtOfficer.mobileNo "
				+ " from GovtDepartmentDesignationOfficerDetailsNew model "
				+ " where model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId in (:designationIds) and model.isDeleted = 'N' ");
		
		query.setParameterList("designationIds", designationIds);
		
		return query.list();
	}
	
	public List<String> getOldHigherOfficerMobileNums(List<Long> designationIds,Long locationTypeId,Long locationValue){
		locationTypeId=4L;
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.govtOfficer.mobileNo from GovtDepartmentDesignationOfficerDetails model  ");
		queryStr.append(" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId in (:designationIds) and " +
				" model.isDeleted = 'N' and model.govtOfficer.mobileNo is not null ");
		
		/*if(locationTypeId != null && locationTypeId.longValue()>0L){
			if(locationTypeId.longValue() ==11L)//village
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.tehsil.tehsilId=:locationValue ");
			else if(locationTypeId.longValue() ==7L)//ward
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.localElectionBody.localElectionBodyId=:locationValue ");
				 
		}*/
		
		if(locationTypeId != null && locationValue != null && locationValue.longValue() > 0){
			if( locationTypeId.longValue() == 2L){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.state.stateId =:locationValue ");
			}else if(locationTypeId.longValue() == 3L){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.district.districtId  =:locationValue ");
			}else if(locationTypeId.longValue() == 4l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.constituency.constituencyId  =:locationValue ");
			}else if(locationTypeId.longValue() == 5l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.tehsil.tehsilId  =:locationValue ");
			}else if(locationTypeId.longValue() == 6l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.panchayat.panchayatId  =:locationValue ");
			}else if(locationTypeId.longValue() == 7l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.localElectionBody.localElectionBodyId  =:locationValue ");
			}else if(locationTypeId.longValue() == 8l){
				queryStr.append(" and  model.govtDepartmentDesignationOfficer.userAddress.ward.constituencyId  =:locationValue ");
			}
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameterList("designationIds", designationIds);
		if(locationValue != null && locationValue.longValue()>0L)
			query.setParameter("locationValue", locationValue);
		
		return query.list();
	}
	
	public List<String> getNewHigherOfficerMobileNums(List<Long> designationIds,Long locationTypeId,Long locationValue){
		locationTypeId=4L;
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.govtOfficer.mobileNo from GovtDepartmentDesignationOfficerDetailsNew model  ");
		queryStr.append(" where model.govtDepartmentDesignationOfficer.govtDepartmentDesignationId in (:designationIds) and " +
				" model.isDeleted = 'N' and model.govtOfficer.mobileNo is not null ");
		
		/*if(locationTypeId != null && locationTypeId.longValue()>0L){
			if(locationTypeId.longValue() ==11L)//village
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.tehsil.tehsilId=:locationValue ");
			else if(locationTypeId.longValue() ==7L)//ward
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.localElectionBody.localElectionBodyId=:locationValue ");
				 
		}*/
		
		if(locationTypeId != null && locationValue != null && locationValue.longValue() > 0){
			if( locationTypeId.longValue() == 2L){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.state.stateId =:locationValue ");
			}else if(locationTypeId.longValue() == 3L){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.district.districtId  =:locationValue ");
			}else if(locationTypeId.longValue() == 4l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.constituency.constituencyId  =:locationValue ");
			}else if(locationTypeId.longValue() == 5l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.tehsil.tehsilId  =:locationValue ");
			}else if(locationTypeId.longValue() == 6l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.panchayat.panchayatId  =:locationValue ");
			}else if(locationTypeId.longValue() == 7l){
				queryStr.append(" and model.govtDepartmentDesignationOfficer.userAddress.localElectionBody.localElectionBodyId  =:locationValue ");
			}else if(locationTypeId.longValue() == 8l){
				queryStr.append(" and  model.govtDepartmentDesignationOfficer.userAddress.ward.constituencyId  =:locationValue ");
			}
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameterList("designationIds", designationIds);
		if(locationValue != null && locationValue.longValue()>0L)
			query.setParameter("locationValue", locationValue);
		
		return query.list();
	}
	public List<Object[]> getLocationInfoForUser(Long loginUserId,Long deptId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.designationName, " +//0
						" model.govtOfficer.officerName,  " +//1
						" model.govtOfficer.mobileNo, " +//2
						" model.govtDepartmentDesignationOfficer.levelValueGovtDepartmentWorkLocation.locationName " +//3
						" from GovtDepartmentDesignationOfficerDetailsNew model " +
						" where " +
						//" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.govtDepartmentId = :deptId" +
						" model.userId = :loginUserId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("loginUserId", loginUserId);
		//query.setParameter("deptId", deptId);
		return query.list();
	}
	public List<String> getAssignedDeptList(Long loginUserId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct" +
						" model.govtDepartmentDesignationOfficer.govtDepartmentDesignation.govtDepartment.shortName " +//0
						" from GovtDepartmentDesignationOfficerDetailsNew model " +
						" where " +
						" model.userId = :loginUserId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("loginUserId", loginUserId);
		return query.list();
	}
}
