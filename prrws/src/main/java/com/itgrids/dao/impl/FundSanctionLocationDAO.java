package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IFundSanctionLocationDAO;
import com.itgrids.model.FundSanctionLocation;
import com.itgrids.utils.IConstants;

@Repository
public class FundSanctionLocationDAO extends GenericDaoHibernate<FundSanctionLocation, Long> implements IFundSanctionLocationDAO {

	@Autowired
	SessionFactory sessionFactory;

	public FundSanctionLocationDAO() {
		super(FundSanctionLocation.class);

	}
	public List<Object[]> getLocationWiseFundSanctionDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			Date sDate,Date eDate,Long locationScopeId,List<Long> searchLvlVals,List<Long> schmeIdsList ){
		StringBuilder sb = new StringBuilder();
		sb.append(" select modal.fundSanction.workName" );
		if(locationScopeId != null  && locationScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID ){
			sb.append("  ,modal.locationAddress.state.stateId,modal.locationAddress.state.stateName ");
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.district.districtId,modal.locationAddress.district.districtName " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.constituency.constituencyId,modal.locationAddress.constituency.name " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.tehsil.tehsilId,modal.locationAddress.tehsil.tehsilName " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.panchayat.panchayatId,modal.locationAddress.panchayat.panchayatName " );
		}
		sb.append(",modal.fundSanction.department.departmentName," +
				" modal.fundSanction.govtScheme.schemeName,modal.fundSanction.goNoDate,modal.fundSanction.sactionAmount ");
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' ");		
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(locationScopeId != null && locationScopeId.longValue() > 0l && locationScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID && searchLvlVals != null && searchLvlVals.size()>0){
			sb.append(" and modal.locationAddress.state.stateId in (:searchLvlVals) ");
		}else if(locationScopeId != null && locationScopeId.longValue() > 0l && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && searchLvlVals != null && searchLvlVals.size()>0){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(locationScopeId != null && locationScopeId.longValue() > 0l && locationScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID && searchLvlVals != null && searchLvlVals.size()>0){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}else if(locationScopeId != null && locationScopeId.longValue() > 0l && locationScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID && searchLvlVals != null && searchLvlVals.size()>0){
			sb.append("  and modal.locationAddress.tehsil.tehsilId in (:searchLvlVals) " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID && searchLvlVals != null && searchLvlVals.size()>0){
			sb.append("  and modal.locationAddress.panchayat.panchayatId in(:searchLvlVals) " );
		}
		
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		
		if(sDate != null && eDate != null){
			sb.append(" and (date(modal.fundSanction.insertedTime) between  :sDate and :eDate) " );
		}
		if(schmeIdsList != null && schmeIdsList.size()>0)
			sb.append(" and modal.fundSanction.govtScheme.govtSchemeId in (:schmeIdsList) ");
		
		Query query = getSession().createQuery(sb.toString());
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(schmeIdsList != null && schmeIdsList.size()>0)
			query.setParameterList("schmeIdsList", schmeIdsList);
		if(locationScopeId != null && locationScopeId.longValue() > 0l && searchLvlVals != null && searchLvlVals.size()>0){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		return query.list();
		
	}

}
