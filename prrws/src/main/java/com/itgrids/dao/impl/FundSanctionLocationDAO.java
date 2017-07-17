package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IFundSanctionLocationDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.FundSanctionLocation;
import com.itgrids.utils.IConstants;

@Repository
public class FundSanctionLocationDAO extends GenericDaoHibernate<FundSanctionLocation, Long> implements IFundSanctionLocationDAO {

	@Autowired
	SessionFactory sessionFactory;

	public FundSanctionLocationDAO() {
		super(FundSanctionLocation.class);

	}
	//click
	public List<Object[]> getLocationWiseFundSanctionDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			Date sDate,Date eDate,Long locationScopeId,List<Long> searchLvlVals,List<Long> schmeIdsList,List<Long> subProgramIdsList ){
		StringBuilder sb = new StringBuilder();
		sb.append(" select " 
				+ " modal.fundSanction.workName, "//0
				+ " d.districtId, "//1
				+ " d.districtName ,"//2
				+ " dep.departmentName, "//3
				+ " modal.fundSanction.subProgram.programName, "//4
				+ " modal.fundSanction.goNoDate, "//5
				+ " sum(modal.fundSanction.sactionAmount), "//6
				+ " modal.locationValue, "//7
				+ " d.districtName, "//8
				+ " c.name, "//9
				+ " t.tehsilName, "//10
				+ " p.panchayatName, "//11
				+ " modal.locationScopeId, "//12
				+ " modal.fundSanction.fundSactionId, "//13
				+ " govtOrder.govtOrderId, "//14
				+ " govtOrder.goNumber, "//15
				+ " govtOrder.issueDate, "//16
				+ " govtOrder.filePath ");//17  
		sb.append(" from FundSanctionLocation modal " +
				" left outer join modal.locationAddress.district d  " +
				" left outer join modal.locationAddress.constituency c " +
				" left outer join modal.locationAddress.tehsil t  " +
				" left outer join modal.locationAddress.panchayat p " +
				" left outer join modal.fundSanction.govtScheme gs " +
				" left outer join modal.fundSanction.department dep " +
				" left outer join modal.fundSanction.govtOrder  govtOrder" +
				" where modal.isDeleted='N' ");		
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(locationScopeId != null && locationScopeId.longValue() > 0l && locationScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID && searchLvlVals != null && searchLvlVals.size()>0){
			sb.append(" and modal.locationAddress.state.stateId in (:searchLvlVals) ");
		}else if(locationScopeId != null && locationScopeId.longValue() > 0l && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && searchLvlVals != null && searchLvlVals.size()>0){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(locationScopeId != null && locationScopeId.longValue() > 0l && locationScopeId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID && searchLvlVals != null && searchLvlVals.size()>0){
			sb.append(" and modal.locationAddress.parliament.constituencyId in (:searchLvlVals) ");
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
			sb.append(" and modal.fundSanction.subProgram.subProgramId in (:schmeIdsList) ");
		if(subProgramIdsList != null && subProgramIdsList.size()>0)
			sb.append(" and modal.fundSanction.govtScheme.govtSchemeId in (:subProgramIdsList) ");
		sb.append(" group by modal.fundSanction.fundSactionId ");
		
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
		if(subProgramIdsList != null && subProgramIdsList.size()>0)
			query.setParameterList("subProgramIdsList", subProgramIdsList);
		return query.list();
		
	}
	
	public List<Object[]> getALlProgramesAmountDetails(InputVO inputVO,Date sDate,Date eDate){
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append(" select sum(modal.fundSanction.sactionAmount),modal.fundSanction.govtScheme.govtSchemeId,modal.fundSanction.grantType.grantTypeId  " +
		 		"from FundSanctionLocation modal where modal.isDeleted = 'N' and modal.fundSanction.isDeleted = 'N' ");
		 
		 if(inputVO.getFinancialYrIdList() != null && inputVO.getFinancialYrIdList().size() >0){
			 sb.append(" and modal.fundSanction.financialYear.financialYearId in (:financialYrIds) ");
		 }
		 if(inputVO.getSearchLevelId() != null && inputVO.getSearchLevelId().longValue() > 0l && inputVO.getSearchLevelId().longValue() == IConstants.STATE_LEVEL_SCOPE_ID && inputVO.getSearchLvlVals() != null && inputVO.getSearchLvlVals().size()>0){
				sb.append(" and modal.locationAddress.state.stateId in (:searchLvlVals) ");
			}else if(inputVO.getSearchLevelId() != null && inputVO.getSearchLevelId().longValue() > 0l && inputVO.getSearchLevelId().longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && inputVO.getSearchLvlVals() != null && inputVO.getSearchLvlVals().size()>0){
				sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
			}else if(inputVO.getSearchLevelId() != null && inputVO.getSearchLevelId().longValue() > 0l && inputVO.getSearchLevelId().longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID && inputVO.getSearchLvlVals() != null && inputVO.getSearchLvlVals().size()>0){
				sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
			}else if(inputVO.getSearchLevelId() != null && inputVO.getSearchLevelId().longValue() > 0l && inputVO.getSearchLevelId().longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID && inputVO.getSearchLvlVals() != null && inputVO.getSearchLvlVals().size()>0){
				sb.append("  and modal.locationAddress.tehsil.tehsilId in (:searchLvlVals) " );
			}else if(inputVO.getSearchLevelId() != null && inputVO.getSearchLevelId().longValue() > 0l && inputVO.getSearchLevelId().longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID && inputVO.getSearchLvlVals() != null && inputVO.getSearchLvlVals().size()>0){
				sb.append("  and modal.locationAddress.panchayat.panchayatId in(:searchLvlVals) " );
			}
			
			if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size()>0)
				sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
			
			if(sDate != null && eDate != null){
				sb.append(" and (date(modal.fundSanction.insertedTime) between  :sDate and :eDate) " );
			}
			if(inputVO.getSourceIdsList() != null && inputVO.getSourceIdsList().size()>0)
				sb.append(" and modal.fundSanction.govtScheme.govtSchemeId in (:schmeIdsList) ");
			
			sb.append(" group by modal.fundSanction.govtScheme.govtSchemeId,modal.fundSanction.grantType.grantTypeId order by sum(modal.fundSanction.sactionAmount) desc");
			
		 Query query = getSession().createQuery(sb.toString());
		 
		 if(inputVO.getFinancialYrIdList() != null && inputVO.getFinancialYrIdList().size() >0l ){
				query.setParameterList("financialYrIds", inputVO.getFinancialYrIdList());
			}
		 
		 	if(sDate != null && eDate != null){
				query.setDate("sDate", sDate);
				query.setDate("eDate", eDate);
			}
		 	if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size()>0)
				query.setParameterList("deptIdsList", inputVO.getDeptIdsList());
			if(inputVO.getSourceIdsList() != null && inputVO.getSourceIdsList().size()>0)
				query.setParameterList("schmeIdsList", inputVO.getSourceIdsList());
			if(inputVO.getSearchLevelId() != null && inputVO.getSearchLevelId().longValue() > 0l && inputVO.getSearchLvlVals() != null && inputVO.getSearchLvlVals().size()>0){
				query.setParameterList("searchLvlVals", inputVO.getSearchLvlVals());
			}
		 
		 return query.list();
	}

}
