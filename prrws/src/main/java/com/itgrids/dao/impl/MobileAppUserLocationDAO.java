package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMobileAppUserLocationDAO;
import com.itgrids.model.MobileAppUserLocation;

@Repository
public class MobileAppUserLocationDAO extends GenericDaoHibernate<MobileAppUserLocation, Long> implements IMobileAppUserLocationDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public MobileAppUserLocationDAO() {
		super(MobileAppUserLocation.class);
 	}
	
	public List<Object[]> getMobileAppuserLocations(Long userId){
		//0-locationScopeId,1-locationScope,2-locationValue
		Query query = getSession().createQuery(" select model.locationScope.regionScopesId,model.locationScope.scope,model.locationValue "
				+ " from MobileAppUserLocation model "
				+ " where model.mobileAppUserId=:userId and model.isDeleted='N' ");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Object[]> getSubUserDetails(Long requiredUserTypeId,Long parentLocationScopeId,List<Long> parentLocationValues,Long workTypeId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.mobileAppUser.mobileAppUserId,model.mobileAppUser.fullName "
				+ " from MobileAppUserLocation model,MobileAppUserWorkType model2 "
				+ " where model.mobileAppUserId=model2.mobileAppUserId and model2.govtWorkTypeId=:workTypeId "
				+ " and model.mobileAppUser.mobileAppUserTypeId=:requiredUserTypeId ");
		
		if(parentLocationScopeId == 3l){//district
			sb.append(" and model.locationAddress.districtId in (:parentLocationValues) ");
		}else if(parentLocationScopeId == 4l){//constituency
			sb.append(" and model.locationAddress.constituencyId in (:parentLocationValues) ");
		}else if(parentLocationScopeId == 5l){//mandal
			sb.append(" and model.locationAddress.tehsilId in (:parentLocationValues) ");
		}else if(parentLocationScopeId == 12l){//divison
			sb.append(" and model.locationAddress.divisionId in (:parentLocationValues) ");
		}else if(parentLocationScopeId == 13l){//sub-divison
			sb.append(" and model.locationAddress.subDivisionId in (:parentLocationValues) ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("requiredUserTypeId", requiredUserTypeId);
		
		if(parentLocationScopeId == 3l || parentLocationScopeId == 4l || parentLocationScopeId == 5l || parentLocationScopeId == 12l || parentLocationScopeId == 13l){
			query.setParameterList("parentLocationValues",parentLocationValues);
		}
		
		query.setParameter("workTypeId", workTypeId);
		return query.list();
	}
	
	public List<Object[]> getAllEngineers(Long mobileAppUserTypeId){
		//0-locationValue,1-userId
		Query query = getSession().createQuery(" select model.locationValue,model.mobileAppUserId "
				+ " from MobileAppUserLocation model "
				+ " where model.mobileAppUser.mobileAppUserTypeId=:mobileAppUserTypeId ");
		query.setParameter("mobileAppUserTypeId", mobileAppUserTypeId);
		return query.list();
	}
}
