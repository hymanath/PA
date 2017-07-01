package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IFundSanctionDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.FundSanction;
import com.itgrids.model.FundSanctionLocation;
import com.itgrids.utils.IConstants;

@Repository
public class FundSanctionDAO extends GenericDaoHibernate<FundSanction, Long> implements IFundSanctionDAO {

	@Autowired
	SessionFactory sessionFactory;

	public FundSanctionDAO() {
		super(FundSanction.class);

	}
	
	/*
	 * Date : 06/06/2017
	 * Author :Srishailam Pittala
	 * @description : to get financial year  and scheme wise funds transaction details
	 */
	
	public List<Object[]> getFinancialYearWiseScheameDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList,Long searchLevelId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  "
					  + " model.fundSanction.govtSchemeId, "//0
					  + " model.fundSanction.govtScheme.schemeName, "//1
					  + " model.fundSanction.financialYear.financialYearId, "//2
					  + " model.fundSanction.financialYear.yearDesc, "//3
					  + " count( distinct model.fundSanction.fundSactionId), "//4
					  + " sum(model.fundSanction.sactionAmount), ");//5
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			queryStr.append(" state.stateId, "//6
					  + " state.stateName ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" district.districtId, "//6
					  + " district.districtName ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" constituency.constituencyId, "//6
					  + " constituency.name ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" parliament.constituencyId, "//6
					  + " parliament.name ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" tehsil.tehsilId, "//6
					  + " tehsil.tehsilName ");//7
		}
		else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" panchayat.panchayatId, "//6
					  + " panchayat.panchayatName ");//7
		}
		queryStr.append(", state.stateId, state.stateName , district.districtId, district.districtName,constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName, "
				+ "  panchayat.panchayatId,  panchayat.panchayatName,parliament.constituencyId,parliament.name ");
		
		queryStr.append(" from FundSanctionLocation model "
				+ " left join model.locationAddress locationAddress "
				+ " left join locationAddress.district district "
				+ " left join locationAddress.state state "
				+ " left join locationAddress.constituency constituency "
				+ " left join locationAddress.parliament parliament "
				+ " left join locationAddress.tehsil  tehsil "
				+ " left join locationAddress.panchayat panchayat "
				
				);
		queryStr.append(" where model.isDeleted ='N' ");
		
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0){
			if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				queryStr.append(" and district.stateId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				queryStr.append("  and district.districtId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				queryStr.append(" and  constituency.constituencyId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				queryStr.append(" and  parliament.constituencyId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				queryStr.append("  and tehsil.tehsilId in (:searchScopeValuesList) ");//7
			}
			else if(searchScopeId != null && searchScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				queryStr.append(" and  panchayatId in (:searchScopeValuesList) ");//7
			}
		}
		
		
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			queryStr.append(" and model.fundSanction.financialYearId in (:financialYearIdsList) ");
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append(" and model.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		if(schemeIdsList != null && schemeIdsList.size()>0)
			queryStr.append(" and model.fundSanction.govtSchemeId in (:schemeIdsList) ");
		if(startDate != null && endDate != null)
			queryStr.append(" and ( date(model.fundSanction.updatedTime) between :startDate and :endDate)  ");
		
		queryStr.append(" group by ");
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			queryStr.append(" district.stateId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" district.districtId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" constituency.constituencyId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" parliament.constituencyId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" tehsil.tehsilId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" panchayat.panchayatId, ");
		}
		queryStr.append(" model.fundSanction.financialYearId,model.fundSanction.govtSchemeId ");
		
		queryStr.append(" order by ");
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			queryStr.append(" district.stateId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" district.districtId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" constituency.constituencyId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" parliament.constituencyId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" tehsil.tehsilId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" panchayat.panchayatId, ");
		}
		queryStr.append(" model.fundSanction.financialYearId,model.fundSanction.govtSchemeId ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		if(schemeIdsList != null && schemeIdsList.size()>0)
			query.setParameterList("schemeIdsList", schemeIdsList);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0)
			query.setParameterList("searchScopeValuesList", searchScopeValuesList);
		
		return query.list();
	}
	/*
	 * Date : 08/06/2017
	 * Author :Srishailam Pittala
	 * @description : to get financial year ,  Department wise and scheme wise funds transaction details
	 */
	public List<Object[]> getFinancialYearWiseDeptsWiseScheameDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList,Long searchLevelId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  "
				+ " model.fundSanction.govtSchemeId, "//0
				+ " model.fundSanction.govtScheme.schemeName, "//1
				+ " model.fundSanction.financialYear.financialYearId, "//2
				+ " model.fundSanction.financialYear.yearDesc, "//3
				+ " count(distinct model.fundSanction.fundSactionId), "//4
				+ " sum(model.fundSanction.sactionAmount), ");//5
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			queryStr.append(" state.stateId, "//6
					  + " state.stateName ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" district.districtId, "//6
					  + " district.districtName ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" constituency.constituencyId, "//6
					  + " constituency.name ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" parliament.constituencyId, "//6
					  + " parliament.name ");//7
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" tehsil.tehsilId, "//6
					  + " tehsil.tehsilName ");//7
		}
		else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" panchayat.panchayatId, "//6
					  + " panchayat.panchayatName ");//7
		}
		
		queryStr.append(" ,model.fundSanction.department.departmentId, "//8
				+ " model.fundSanction.department.departmentName ");//9
		queryStr.append(", state.stateId, state.stateName , district.districtId, district.districtName,constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName, "
				+ "  panchayat.panchayatId,  panchayat.panchayatName,parliament.constituencyId,parliament.name ");
		
		queryStr.append(" from FundSanctionLocation model "
				+ " left join model.locationAddress locationAddress "
				+ " left join locationAddress.district district "
				+ " left join locationAddress.state state "
				+ " left join locationAddress.constituency constituency "
				+ " left join locationAddress.parliament parliament "
				+ " left join locationAddress.tehsil  tehsil "
				+ " left join locationAddress.panchayat panchayat "
				
				);
		queryStr.append(" where model.isDeleted ='N' ");
		
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0){
			if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				queryStr.append("  and  state.stateId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				queryStr.append(" and  district.districtId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				queryStr.append("  and constituency.constituencyId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				queryStr.append("  and parliament.constituencyId in (:searchScopeValuesList) ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				queryStr.append("  and tehsil.tehsilId in (:searchScopeValuesList) ");//7
			}
			else if(searchScopeId != null && searchScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				queryStr.append("  and panchayat.panchayatId in (:searchScopeValuesList) ");//7
			}
		}
		
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			queryStr.append(" and model.fundSanction.financialYearId in (:financialYearIdsList) ");
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append(" and model.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		if(schemeIdsList != null && schemeIdsList.size()>0)
			queryStr.append(" and model.fundSanction.govtSchemeId in (:schemeIdsList) ");
		if(startDate != null && endDate != null)
			queryStr.append(" and ( date(model.fundSanction.updatedTime) between :startDate and :endDate)  ");
		/*if(searchScopeId != null && searchScopeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId =:searchScopeId ");
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0)
			queryStr.append(" and model.locationValue in (:searchScopeValuesList) ");*/
		
		/*queryStr.append(" group by model.locationScopeId,model.locationValue,model.fundSanction.financialYearId,model.fundSanction.department.departmentId,model.fundSanction.govtSchemeId ");
		queryStr.append(" order by model.locationScopeId,model.locationValue,model.fundSanction.financialYearId,model.fundSanction.department.departmentId,model.fundSanction.govtSchemeId ");
		*/
		queryStr.append(" group by ");
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			queryStr.append(" district.stateId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" district.districtId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" constituency.constituencyId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" parliament.constituencyId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" tehsil.tehsilId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" panchayat.panchayatId, ");
		}
		
		queryStr.append(" model.fundSanction.financialYearId,model.fundSanction.department.departmentId,model.fundSanction.govtSchemeId ");
		
		queryStr.append(" order by ");
		if(searchLevelId != null && searchLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			queryStr.append(" district.stateId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" district.districtId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" constituency.constituencyId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" parliament.constituencyId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" tehsil.tehsilId, ");
		}else if(searchLevelId != null && searchLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" panchayat.panchayatId, ");
		}
		queryStr.append(" model.fundSanction.financialYearId,model.fundSanction.govtSchemeId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		if(schemeIdsList != null && schemeIdsList.size()>0)
			query.setParameterList("schemeIdsList", schemeIdsList);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(searchScopeValuesList != null && searchScopeValuesList.size()>0)
			query.setParameterList("searchScopeValuesList", searchScopeValuesList);
		
		return query.list();
	}
	
	/**
	 * @author Srishailam Pittala <srishailam.pittala@itgrids.com>
	 * @date  6th June,2017
	 * @description  to get financial year wise fund details 
	 * @param public List<Object[]> getFinancialYearWiseFundDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId)
	 * @return List<Object[]>
	 */
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFinancialYearWiseFundDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.fundSanction.financialYear.financialYearId,model.fundSanction.financialYear.yearDesc,  (sum(model.fundSanction.sactionAmount)/10000000),model.locationScopeId ");
		if(searchScopeId != null && searchScopeId.longValue()>0L){
			if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.district.stateId, "//4
						  + " 'Andhra Pradesh' ");//5
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.district.districtId, "//4
						  + " model.locationAddress.district.districtName ");//5
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.constituency.constituencyId, "//6
						  + " model.locationAddress.constituency.name ");//7
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.tehsil.tehsilId, "//4
						  + " model.locationAddress.tehsil.tehsilName ");//5
			}
			else if(searchScopeId != null && searchScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.panchayat.panchayatId, "//4
						  + " model.locationAddress.panchayat.panchayatName ");//5
			}
		} 
		queryStr.append(" from FundSanctionLocation model ");
		queryStr.append(" where model.fundSanction.isDeleted ='N' ");
		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			queryStr.append(" and model.fundSanction.financialYearId in (:financialYearIdsList) ");
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append(" and model.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		if(schemeIdsList != null && schemeIdsList.size()>0)
			queryStr.append(" and model.fundSanction.govtSchemeId in (:schemeIdsList) ");
		//if(searchScopeId != null && searchScopeId.longValue()>0L)
		//	queryStr.append(" and model.locationScopeId =:searchScopeId ");

		queryStr.append(" group by model.locationScopeId,model.fundSanction.financialYearId ");
		if(searchScopeId != null && searchScopeId.longValue()>0L){
			if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.district.stateId ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.district.districtId ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				queryStr.append(",model.locationAddress.constituency.constituencyId ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.tehsil.tehsilId ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				queryStr.append(", model.locationAddress.panchayat.panchayatId ");
			}
		}
		
		queryStr.append(" order by model.locationScopeId,model.fundSanction.financialYearId ");
		if(searchScopeId != null && searchScopeId.longValue()>0L){
			if(searchScopeId != null && searchScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.district.stateId ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.district.districtId ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				queryStr.append(",model.locationAddress.constituency.constituencyId ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				queryStr.append(" ,model.locationAddress.tehsil.tehsilId ");
			}else if(searchScopeId != null && searchScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				queryStr.append(", model.locationAddress.panchayat.panchayatId ");
			}
		}
		 
		Query query = getSession().createQuery(queryStr.toString());

		if(financialYearIdsList != null && financialYearIdsList.size()>0)
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		if(schemeIdsList != null && schemeIdsList.size()>0)
			query.setParameterList("schemeIdsList", schemeIdsList);
		//if(searchScopeId != null && searchScopeId.longValue()>0L)
		//	query.setParameter("searchScopeId", searchScopeId);

		return query.list();
	}
	/**
	 * @author Swadhin K Lenka
	 * @date  16th June,2017
	 * @description  to get financial year wise fund details 
	 * @param public List<Object[]> getFinancialYearWiseFundDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId)
	 * @return List<Object[]>
	 */
	
	@Override
	public List<Object[]> financialYearWiseFundDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Long scopeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select "
					  + " model.fundSanction.financialYear.financialYearId, ");
		if(scopeId != null && scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.district.districtId ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append("model.locationAddress.constituency.constituencyId ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.tehsil.tehsilId ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" model.locationAddress.panchayat.panchayatId ");
		}
		queryStr.append(" ,(sum(model.fundSanction.sactionAmount)/100000) ");
		queryStr.append(" from FundSanctionLocation model ");
		queryStr.append(" where model.fundSanction.isDeleted ='N' and model.isDeleted ='N'");
		
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append(" and model.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		if(schemeIdsList != null && schemeIdsList.size()>0)
			queryStr.append(" and model.fundSanction.govtSchemeId in (:schemeIdsList) ");
		
		queryStr.append(" and model.fundSanction.financialYearId in (:financialYearIdsList) ");
		queryStr.append(" group by model.fundSanction.financialYearId, ");
		if(scopeId != null && scopeId.longValue()>0L){
			if(scopeId != null && scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				queryStr.append(" model.locationAddress.district.districtId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				queryStr.append(" model.locationAddress.constituency.constituencyId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				queryStr.append(" model.locationAddress.tehsil.tehsilId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				queryStr.append(" model.locationAddress.panchayat.panchayatId ");
			}
		}
		
		queryStr.append(" order by model.fundSanction.financialYearId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		if(schemeIdsList != null && schemeIdsList.size()>0)
			query.setParameterList("schemeIdsList", schemeIdsList);
		
		query.setParameterList("financialYearIdsList", financialYearIdsList);
		return query.list();
	}
	
	
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId,List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList
			,Long searchLevlId,List<Long> searchLvlVals,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.locationScopeId ");
		if(locatioinTypeId != null && locatioinTypeId.longValue() >0l && locatioinTypeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append("  ,count( distinct model.locationAddress.district.districtId ) " );
		}else if(locatioinTypeId != null && locatioinTypeId.longValue() >0l && locatioinTypeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append("  ,count(distinct model.locationAddress.constituency.constituencyId)  " );
		}else if(locatioinTypeId != null && locatioinTypeId.longValue() >0l && locatioinTypeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" ,count(distinct model.locationAddress.tehsil.tehsilId ) " );
		}else if(locatioinTypeId != null && locatioinTypeId.longValue() >0l && locatioinTypeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append("  ,count(distinct model.locationAddress.panchayat.panchayatId ) " );
		}else if(locatioinTypeId != null && locatioinTypeId.longValue() >0l && locatioinTypeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append("  ,count(distinct model.locationAddress.parliament.constituencyId ) " );
		}
		//queryStr.append(" ,count( distinct model.locationValue) ");
		queryStr.append(" ,sum(model.fundSanction.sactionAmount) from   ");
		queryStr.append(" FundSanctionLocation model  ");
		queryStr.append(" where model.isDeleted ='N' and model.locationAddress.district.stateId = 1 and model.fundSanction.isDeleted = 'N' ");
		/*if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			queryStr.append(" and model.locationScopeId = :locatioinTypeId ");*/
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			queryStr.append(" and model.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(fromDate != null && toDate != null)
			queryStr.append(" and ( date(model.fundSanction.updatedTime) between :startDate and :endDate)  ");
		if(deptIdsList != null && deptIdsList.size()>0)
			queryStr.append(" and model.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			queryStr.append("  ");
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			queryStr.append(" and model.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" and model.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			queryStr.append(" and model.locationAddress.tehsil.tehsilId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			queryStr.append(" and model.locationAddress.panchayat.panchayatId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			queryStr.append(" and model.locationAddress.parliament.constituencyId in (:searchLvlVals) ");
		}
		//queryStr.append(" group by model.locationScopeId ");
		//queryStr.append(" order by model.locationScopeId ");
		Query query = getSession().createQuery(queryStr.toString());
		/*if(locatioinTypeId != null && locatioinTypeId.longValue()>0L)
			query.setParameter("locatioinTypeId", locatioinTypeId);*/
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		if(fromDate != null && toDate != null){
			query.setDate("startDate", fromDate);
			query.setDate("endDate", toDate);
		}
		return query.list();
	}

	@Override
	public List<Long> getLocationValues(Long scopeId) {
		Criteria criteria=getSession().createCriteria(FundSanctionLocation.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.distinct(Projections.property("locationValue")));
		criteria.setProjection(projectionList);
		criteria.add(Restrictions.eq("locationScopeId", scopeId));
		return criteria.list();
	}
	@Override
	public List<Object[]> getLocationWiseAmount(Long BlocklevelId,Long locationId,Long locationLevelId ,Date fromDate,Date toDate,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList){
		StringBuilder sb = new StringBuilder();  
		sb.append(" select "
				+ " fundSanctionLocation.fundSanction.financialYear.financialYearId "//0
				+ " ,fundSanctionLocation.fundSanction.financialYear.yearDesc ");//1
		if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" ,state.stateId "//2
					+ " ,state.stateName ");//3
					
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" ,district.districtId "//2
					+ " ,district.districtName ");//3
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" ,constituency.constituencyId "//2
					+ " ,constituency.name" );//3
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" ,parliament.constituencyId "//2
					+ " ,parliament.name" );//3
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" ,tehsil.tehsilId "//2
					+ " ,tehsil.tehsilName"); //3
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" ,panchayat.panchayatId "//2
					+ " ,panchayat.panchayatName");//3
		}
		
		sb.append(" ,count(distinct fundSanctionLocation.fundSanction.fundSactionId) ");//4
		sb.append(" ,sum(fundSanctionLocation.fundSanction.sactionAmount) ");//5
		sb.append(", state.stateId, state.stateName , district.districtId, district.districtName,constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName, "
				+ "  panchayat.panchayatId,  panchayat.panchayatName,parliament.constituencyId,parliament.name ");
		
		sb.append(" from FundSanctionLocation fundSanctionLocation "
				+ " left join fundSanctionLocation.locationAddress locationAddress "
				+ " left join locationAddress.district district "
				+ " left join locationAddress.state state "
				+ " left join locationAddress.constituency constituency "
				+ " left join locationAddress.parliament parliament "
				+ " left join locationAddress.tehsil  tehsil "
				+ " left join locationAddress.panchayat panchayat "
				);
		
		sb.append(" where "
				+ " fundSanctionLocation.isDeleted = 'N' "
				+ " and fundSanctionLocation.fundSanction.isDeleted = 'N' "
				+ " and fundSanctionLocation.fundSanction.insertedTime between :fromDate and :toDate ");
		if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID ){
			if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and district.districtId = :locationId ");
			}
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and district.districtId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and parliament.constituencyId = :locationId ");
			}
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and district.districtId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and parliament.constituencyId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and constituency.constituencyId = :locationId ");
			}
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and district.districtId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and constituency.constituencyId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and parliament.constituencyId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and tehsil.tehsilId = :locationId ");
			}
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and district.districtId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and parliament.constituencyId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and constituency.constituencyId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and tehsil.tehsilId = :locationId ");
			}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID && locationId != null && locationId.longValue() > 0L){
				sb.append(" and panchayat.panchayatId = :locationId ");
			}
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYear.financialYearId in (:financialYrIdList) ");
		}
		if(deptIdList !=null && deptIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(sourceIdList !=null && sourceIdList.size() > 0){
			
		}
		sb.append(" group by fundSanctionLocation.fundSanction.financialYear.financialYearId ");
		if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" ,district.stateId ");
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" ,district.districtId ");
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" ,constituency.constituencyId ");
		}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" , parliament.constituencyId  ");
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" ,tehsil.tehsilId ");
		}else if(BlocklevelId != null && BlocklevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" ,panchayat.panchayatId ");
		}
		sb.append(" order by fundSanctionLocation.fundSanction.financialYear.financialYearId ");
		Query query = getSession().createQuery(sb.toString());
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		if(locationId != null && locationId.longValue() > 0L){
			query.setParameter("locationId", locationId);
		}
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		if(deptIdList !=null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		return query.list();
	}
	
	public List<Object[]> getLocationWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,String type,Long searchLevlId,List<Long> searchLvlVals){
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.fundSanction.sactionAmount) " );
		if(locationScopeId != null && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.district.districtId,modal.locationAddress.district.districtName " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.constituency.constituencyId,modal.locationAddress.constituency.name " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.parliament.constituencyId,modal.locationAddress.parliament.name " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.tehsil.tehsilId,modal.locationAddress.tehsil.tehsilName " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" ,modal.locationAddress.panchayat.panchayatId,modal.locationAddress.panchayat.panchayatName " );
		}
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' ");
		
		/*if(locationScopeId != null && locationScopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}*/
		
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.parliament.constituencyId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.tehsil.tehsilId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.panchayat.panchayatId in (:searchLvlVals) " );
		}
		
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		if(sDate != null && eDate != null){
			sb.append(" and (date(modal.fundSanction.insertedTime) between  :sDate and :eDate) " );
		}
		
		if(locationScopeId != null && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID ){
			sb.append(" group by modal.locationAddress.district.districtId " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" group by modal.locationAddress.constituency.constituencyId " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" group by modal.locationAddress.parliament.constituencyId " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" group by modal.locationAddress.tehsil.tehsilId ");
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" group by modal.locationAddress.panchayat.panchayatId ");
		}
		if(type != null && type.equalsIgnoreCase("highest")){
			sb.append("   order by sum(modal.fundSanction.sactionAmount) desc ");
		}else if(type != null && type.equalsIgnoreCase("lowest")){
			sb.append("  order by sum(modal.fundSanction.sactionAmount) asc ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setFirstResult(0);
		query.setMaxResults(2);
		/*if(locationScopeId != null && locationScopeId.longValue() >0l ){
			query.setParameter("locationScopeId", locationScopeId);
		}*/
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		return query.list();
		
	}
	public List<Object[]> getSchemeWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,String type,Long searchLevlId,List<Long> searchLvlVals){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.fundSanction.sactionAmount) " );
		
		sb.append(" ,modal.fundSanction.govtScheme.govtSchemeId,modal.fundSanction.govtScheme.schemeName " );
		
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N' ");
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and  modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.parliament.constituencyId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.tehsil.tehsilId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.panchayat.panchayatId in (:searchLvlVals) " );
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(type != null && type.equalsIgnoreCase("highest")){
			sb.append(" group by modal.fundSanction.govtScheme.govtSchemeId order by sum(modal.fundSanction.sactionAmount) desc ");
		}else if(type != null && type.equalsIgnoreCase("lowest")){
			sb.append(" group by modal.fundSanction.govtScheme.govtSchemeId order by sum(modal.fundSanction.sactionAmount) asc ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setFirstResult(0);
		query.setMaxResults(2);
		
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		return query.list();
		
	}
	
	public Long getTotalFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long scopeId,Long searchLevlId,List<Long> searchLvlVals){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.fundSanction.sactionAmount) " );
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N' " +
				" and modal.locationAddress.state.stateId=1");
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		/*if(scopeId != null && scopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}*/
		
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.tehsil.tehsilId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.panchayat.panchayatId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.parliament.constituencyId in (:searchLvlVals) " );
		}
		
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		
		/*if(scopeId != null && scopeId.longValue() >0l && scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" group by modal.locationAddress.district.districtId " );
		}else if(scopeId != null && scopeId.longValue() >0l && scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" group by modal.locationAddress.constituency.constituencyId " );
		}else if(scopeId != null && scopeId.longValue() >0l && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" group by modal.locationAddress.tehsil.tehsilId " );
		}else if(scopeId != null && scopeId.longValue() >0l && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" group by modal.locationAddress.panchayat.panchayatId " );
		}*/
		Query query = getSession().createQuery(sb.toString());
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		
		/*if(scopeId != null && scopeId.longValue() >0l ){
			query.setParameter("locationScopeId", scopeId);
		}*/
		if(deptIdsList != null && deptIdsList.size()>0){
			query.setParameterList("deptIdsList", deptIdsList);
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getLocationWiseGrantTypesFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,Long locationVal,Long searchLevlId,List<Long> searchLvlVals){
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.fundSanction.sactionAmount) " );
		sb.append(" ,modal.fundSanction.grantType.grantTypeId,modal.fundSanction.grantType.type " );
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N' " +
				" and modal.locationAddress.state.stateId=1  ");
		/*if(locationScopeId != null && locationScopeId.longValue() >0l ){
			sb.append(" and modal.locationScopeId = :locationScopeId  " );
		}*/
		if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID && locationVal != null && locationVal.longValue() >0l){
			sb.append(" and  modal.locationAddress.state.stateId  = :locationVal " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID && locationVal != null && locationVal.longValue() >0l){
			sb.append(" and  modal.locationAddress.district.districtId  = :locationVal " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID && locationVal != null && locationVal.longValue() >0l){
			sb.append(" and modal.locationAddress.constituency.constituencyId = :locationVal  " );
		}else if(locationScopeId != null && locationScopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.parliament.constituencyId = :locationVal  " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID && locationVal != null && locationVal.longValue() >0l){
			sb.append(" and modal.locationAddress.tehsil.tehsilId = :locationVal " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID && locationVal != null && locationVal.longValue() >0l){
			sb.append(" and  modal.locationAddress.panchayat.panchayatId = :locationVal " );
		}
		/*if(locationVal != null && locationVal.longValue() >0l ){
			sb.append(" and modal.locationValue = :locationVal  " );
		}*/
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.parliament.constituencyId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.tehsil.tehsilId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.panchayat.panchayatId in (:searchLvlVals) " );
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		if(sDate != null && eDate != null){
			sb.append(" and (date(modal.fundSanction.insertedTime) between  :sDate and :eDate) " );
		}
		sb.append(" group by modal.fundSanction.grantType.grantTypeId  ");
		/*if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" , modal.locationAddress.district.districtId " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" , modal.locationAddress.constituency.constituencyId " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" , modal.locationAddress.tehsil.tehsilId " );
		}else if(locationScopeId != null && locationScopeId.longValue() >0l && locationScopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" , modal.locationAddress.panchayat.panchayatId " );
		}*/
		
		Query query = getSession().createQuery(sb.toString());
		/*if(locationScopeId != null && locationScopeId.longValue() >0l ){
			query.setParameter("locationScopeId", locationScopeId);
		}*/
		if(locationVal != null && locationVal.longValue() >0l ){
			query.setParameter("locationVal", locationVal);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		if(deptIdsList != null && deptIdsList.size()>0){
			query.setParameterList("deptIdsList", deptIdsList);
		}
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		return query.list();
	}
	
	public Long getTotalSchemes(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,Date sDate,Date eDate,Long searchLevlId,List<Long> searchLvlVals,String type){
		
		StringBuilder sb = new StringBuilder();
		if(type  != null && type.equalsIgnoreCase("scheme")){
			sb.append(" select count(distinct modal.fundSanction.govtScheme.govtSchemeId ) " );
		}else if(type  != null && type.equalsIgnoreCase("grant")){
			sb.append(" select count(distinct modal.fundSanction.grantType.grantTypeId ) " );
		}
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N'  ");
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.parliament.constituencyId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.tehsil.tehsilId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" and modal.locationAddress.panchayat.panchayatId in (:searchLvlVals) " );
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		
		Query query = getSession().createQuery(sb.toString());
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getSchemeWiseGrantTypesFund(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate
			,Long schemeId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.sactionAmount) " );
		sb.append(" ,modal.govtScheme.govtSchemeId,'' " );
		sb.append(" from FundSanction modal where modal.isDeleted='N'  ");
		if(schemeId != null && schemeId.longValue() >0l ){
			sb.append(" and modal.govtScheme.govtSchemeId = :schemeId  " );
		}
		if(financialYrId != null && financialYrId.longValue() >0l ){
			sb.append(" and modal.financialYearId = :financialYrId  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and (date(modal.insertedTime) between  :sDate and :eDate) " );
		}
		sb.append(" group by modal.locationValue,modal.govtScheme.govtSchemeId  ");
		Query query = getSession().createQuery(sb.toString());
		if(schemeId != null && schemeId.longValue() >0l ){
			query.setParameter("schemeId", schemeId);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrId != null && financialYrId.longValue() >0l ){
			query.setParameter("financialYrId", financialYrId);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getTotalFundAndCountDtls(List<Long> financialYrIdList,List<Long> departmentIdList,List<Long> sourceIdList,Date sDate,Date eDate,Long scopeId,String group
			,Long searchLevlId,List<Long> searchLvlVals){
		StringBuilder sb = new StringBuilder();
		sb.append(" select " );
		if(group.equalsIgnoreCase("two")){
			sb.append(" fundSanctionLocation.fundSanction.grantType.grantTypeId, fundSanctionLocation.fundSanction.grantType.type, " );
		}
		
		if(scopeId != null && scopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.district.stateId) ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.district.districtId) ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.constituency.constituencyId) ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.tehsil.tehsilId) ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.panchayat.panchayatId) ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" count(distinct fundSanctionLocation.locationAddress.parliament.constituencyId) ");
		}
		
		sb.append(" ,sum(fundSanctionLocation.fundSanction.sactionAmount) ");
		sb.append(" from FundSanctionLocation fundSanctionLocation where fundSanctionLocation.isDeleted='N' ");
		sb.append(" and fundSanctionLocation.fundSanction.isDeleted='N' ");
		if(scopeId != null && scopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.district.stateId is not null ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.district is not null ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.constituency is not null ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.tehsil is not null ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.panchayat is not null ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.parliament is not null ");
		}
		if(group.equalsIgnoreCase("two")){
			sb.append(" and fundSanctionLocation.fundSanction.grantType is not null ");
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" and fundSanctionLocation.locationAddress.tehsil.tehsilId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" and fundSanctionLocation.locationAddress.panchayat.panchayatId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" and fundSanctionLocation.locationAddress.parliament.constituencyId in (:searchLvlVals) " );
		}
		if(scopeId != null && scopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.district.districtId in ("+IConstants.TOTAL_AP_DISTRICT_IDS+")) ");
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(departmentIdList != null && departmentIdList.size() > 0 ){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:departmentIdList)  " );
		}
		if(sourceIdList != null && sourceIdList.size() > 0 ){
			
		}
		sb.append(" group by ");
		if(group.equalsIgnoreCase("two")){
			sb.append(" fundSanctionLocation.fundSanction.grantType.grantTypeId ");
		}else{
			if(scopeId != null && scopeId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.district.stateId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.district.districtId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.constituency.constituencyId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.tehsil.tehsilId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.panchayat.panchayatId ");
			}else if(scopeId != null && scopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" fundSanctionLocation.locationAddress.parliament.constituencyId ");
			}
		}
		
	
		
		Query query = getSession().createQuery(sb.toString());
		
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		
		if(departmentIdList != null && departmentIdList.size() > 0 ){
			query.setParameterList("departmentIdList", departmentIdList);
		}
		
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTotalFundForScheme(List<Long> financialYrIdList,List<Long> departmentIdList,List<Long> sourceIdLIst,List<Long> schemeIdList,Date sDate,Date eDate,String type
			,Long searchLevlId,List<Long> searchLvlVals){
		StringBuilder sb = new StringBuilder();
		if(type != null && type.equalsIgnoreCase("scheme")){
			sb.append(" select count(distinct fundSanctionLocation.fundSanction.govtScheme.govtSchemeId),sum(fundSanctionLocation.fundSanction.sactionAmount) " );
		}else if(type != null && type.equalsIgnoreCase("grant")){
			sb.append(" select count(distinct fundSanctionLocation.fundSanction.grantType.grantTypeId),sum(fundSanctionLocation.fundSanction.sactionAmount) " );
		}
		sb.append(" from FundSanctionLocation fundSanctionLocation where fundSanctionLocation.isDeleted='N' ");
		sb.append(" and fundSanctionLocation.fundSanction.isDeleted='N' ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(schemeIdList != null && schemeIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.govtScheme.govtSchemeId in (:schemeIdList) " );
		}
		if(departmentIdList != null && departmentIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:departmentIdList)  " );
		}
		if(sourceIdLIst != null && sourceIdLIst.size() > 0){
			//;
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID ){
			sb.append(" and fundSanctionLocation.locationAddress.tehsil.tehsilId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID ){
			sb.append(" and fundSanctionLocation.locationAddress.panchayat.panchayatId in (:searchLvlVals) " );
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" and fundSanctionLocation.locationAddress.parliament.constituencyId in (:searchLvlVals) " );
		}
		Query query = getSession().createQuery(sb.toString());
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		
		if(schemeIdList != null && schemeIdList.size() > 0){
			query.setParameter("schemeIdList", schemeIdList);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(departmentIdList != null && departmentIdList.size() > 0){
			query.setParameterList("departmentIdList", departmentIdList);
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		return query.list();
	}
	@Override
	public List<Long> getLocationBlockLevelIds(Long locationId,Long locationLevelId,Long blockLevelId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		if(blockLevelId != null && blockLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" district.districtId ");
		}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" constituency.constituencyId ");
		}
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.fundSanction fundSanction "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district ");
		if(blockLevelId != null && blockLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" left outer join locationAddress.constituency constituency ");
		}
		sb.append(" where fundSanctionLocation.isDeleted='N' and fundSanctionLocation.fundSanction.isDeleted='N' and fundSanctionLocation.locationScopeId = :blockLevelId ");
		if(locationLevelId != null && locationLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and district.districtId = :locationId ");
		}else if(locationLevelId != null && locationLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and constituency.constituencyId = :locationId ");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("blockLevelId", blockLevelId);
		query.setParameter("locationId", locationId);
		return query.list();
	}
	@Override
	public List<Object[]> getAllDistrictByStateId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate,Long scopeId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct district.districtId, district.districtName");
		
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district ");
		sb.append(" where fundSanctionLocation.isDeleted='N' ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(scopeId != null && scopeId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.constituency is not null ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.tehsil is not null ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.panchayat is not null ");
		}
		if(sourceIdList != null && sourceIdList.size() > 0){
			
		}
		sb.append("order by district.districtName asc ");
		Query query = getSession().createQuery(sb.toString());
		
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getAllConstituencyByDistrictId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate,Long scopeId,Long superLocationLevelId){
		StringBuilder sb = new StringBuilder();
		if(superLocationLevelId == 10){
		sb.append(" select distinct constituency.constituencyId, constituency.name ");
		}else if(superLocationLevelId == 3){
			sb.append("select distinct locationAddress.parliament.constituencyId, locationAddress.parliament.name ");
		}
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency ");
		sb.append(" where fundSanctionLocation.isDeleted='N' ");
		if(superLocationLevelId == 3){
			sb.append( " and district.districtId = :superLocationId ");
		}else if(superLocationLevelId == 10){
			sb.append( " and locationAddress.parliament.constituencyId = :superLocationId ");
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(sourceIdList != null && sourceIdList.size() > 0){
			
		}
		if(scopeId != null && scopeId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.tehsil is not null ");
		}else if(scopeId != null && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.panchayat is not null ");
		}
		sb.append("order by constituency.name asc ");
		Query query = getSession().createQuery(sb.toString());
		if(superLocationLevelId != null)
			query.setParameter("superLocationId", superLocationId);
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		return query.list();
	}
	
	@Override
	public List<Object[]> getFundSactionCount(List<Long> financialYrIdList){
	    
	    StringBuffer sb=new StringBuffer();
	    sb.append(" select distinct fundSanctionLocation.fundSanction.fundSactionId," );
	    sb.append(" fundSanctionLocation.fundSanction.financialYear.yearDesc,");
	    sb.append(" sum(fundSanctionLocation.fundSanction.sactionAmount) ");
	    sb.append(" from ");
	    sb.append(" FundSanctionLocation fundSanctionLocation ");
	    sb.append(" where ");
	    sb.append(" fundSanctionLocation.fundSanction.financialYearId in(:financialYearId) and");
	    sb.append(" fundSanctionLocation.isDeleted='N' ");
	    if(financialYrIdList != null && !financialYrIdList.isEmpty()){
	    sb.append(" group by fundSanctionLocation.fundSanction.financialYearId ");
	    }
	    Query query=getSession().createQuery(sb.toString());
	    
	    if(financialYrIdList != null && !financialYrIdList.isEmpty()){
	    	query.setParameterList("financialYearId", financialYrIdList);
	    }
	    
	    return query.list();
	    
	  }
	@Override
	public List<Object[]> getAllTehsilByConstituencyId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate,Long scopeId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct tehsil.tehsilId, tehsil.tehsilName ");
		
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency "
				+ " left outer join locationAddress.tehsil tehsil ");
		sb.append(" where fundSanctionLocation.isDeleted='N' "
				+ " and constituency.constituencyId = :superLocationId ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(sourceIdList != null && sourceIdList.size() > 0){
			
		}
		if(scopeId != null && scopeId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" and fundSanctionLocation.locationAddress.panchayat is not null ");
		}
		sb.append("order by tehsil.tehsilName asc ");
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("superLocationId", superLocationId);
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		return query.list();
	}
	@Override
	public List<Object[]> getAllPanchayatByTehsilId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct panchayat.panchayatId, panchayat.panchayatName ");
		
		sb.append(" from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " left outer join fundSanctionLocation.locationAddress locationAddress "
				+ " left outer join locationAddress.district district "
				+ " left outer join locationAddress.constituency constituency "
				+ " left outer join locationAddress.tehsil tehsil "
				+ " left outer join locationAddress.panchayat panchayat ");
		sb.append(" where fundSanctionLocation.isDeleted='N' "
				+ " and tehsil.tehsilId = :superLocationId ");
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYearId in (:financialYrIdList)  " );
		}
		if(sDate != null && eDate != null){
			sb.append(" and date(fundSanctionLocation.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(deptIdList != null && deptIdList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(sourceIdList != null && sourceIdList.size() > 0){
			
		}
		sb.append("order by  panchayat.panchayatName asc ");
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("superLocationId", superLocationId);
		if(deptIdList != null && deptIdList.size() > 0){
			query.setParameterList("deptIdList", deptIdList);
		}
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(financialYrIdList != null && financialYrIdList.size() > 0){
			query.setParameterList("financialYrIdList", financialYrIdList);
		}
		return query.list();
	}
	
	@Override
	public List<Object[]> getLocationWiseAmountAndCountDetails(Date fromDate,Date toDate,Long blockLevelId,List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> levelValues){
		StringBuilder sb = new StringBuilder();  
		sb.append(" select "
				+ " fundSanctionLocation.fundSanction.financialYear.financialYearId "//0
				+ " ,fundSanctionLocation.fundSanction.financialYear.yearDesc ");//1
		if(blockLevelId != null && blockLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.stateId "//2
					+ " ,'Andhra Pradesh' "//3
					+ " ,count(distinct fundSanctionLocation.fundSanction.fundSactionId) ");//4
		}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.districtId "//2
					+ " ,fundSanctionLocation.locationAddress.district.districtName "//3
					+ " ,count(distinct fundSanctionLocation.fundSanction.fundSactionId) ");//4
		}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.constituency.constituencyId "//2
					+ " ,fundSanctionLocation.locationAddress.constituency.name"//3
					+ " ,count(distinct fundSanctionLocation.fundSanction.fundSactionId) ");//4
		}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.tehsil.tehsilId "//2
					+ " ,fundSanctionLocation.locationAddress.tehsil.tehsilName"//3
					+ " ,count(distinct fundSanctionLocation.fundSanction.fundSactionId) ");//4
		}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.panchayat.panchayatId "//2
					+ " ,fundSanctionLocation.locationAddress.panchayat.panchayatName"//3
					+ " ,count(distinct fundSanctionLocation.fundSanction.fundSactionId) ");//4
		}
		
		sb.append(" ,sum(fundSanctionLocation.fundSanction.sactionAmount) "//5
				+ " from "
				+ " FundSanctionLocation fundSanctionLocation "
				+ " where "
				+ " fundSanctionLocation.isDeleted = 'N' "
				+ " and fundSanctionLocation.fundSanction.isDeleted = 'N' "
				+ " and fundSanctionLocation.fundSanction.insertedTime between :fromDate and :toDate ");
		
			if(blockLevelId != null && blockLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
				sb.append(" and fundSanctionLocation.locationAddress.district.districtId in (:locationIds) ");
			}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
				sb.append(" and fundSanctionLocation.locationAddress.constituency.constituencyId in (:locationIds) ");
			}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
				sb.append(" and fundSanctionLocation.locationAddress.tehsil.tehsilId in (:locationIds) ");
			}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
				sb.append(" and fundSanctionLocation.locationAddress.panchayat.panchayatId in (:locationIds) ");
			}
		
		if(financialYearIdsList != null && financialYearIdsList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.financialYear.financialYearId in (:financialYrIdList) ");
		}
		if(deptIdsList  !=null && deptIdsList.size() > 0){
			sb.append(" and fundSanctionLocation.fundSanction.department.departmentId in (:deptIdList) ");
		}
		if(sourceIdsList !=null && sourceIdsList.size() > 0){
			
		}
		sb.append(" group by fundSanctionLocation.fundSanction.financialYear.financialYearId ");
		if(blockLevelId != null && blockLevelId.longValue() == IConstants.STATE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.stateId ");
		}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.district.districtId ");
		}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.constituency.constituencyId ");
		}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.tehsil.tehsilId ");
		}else if(blockLevelId != null && blockLevelId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" ,fundSanctionLocation.locationAddress.panchayat.panchayatId ");
		}
		sb.append(" order by fundSanctionLocation.fundSanction.financialYear.financialYearId ");
		Query query = getSession().createQuery(sb.toString());
		if(financialYearIdsList != null && financialYearIdsList.size() > 0){
			query.setParameterList("financialYrIdList", financialYearIdsList);
		}
		if(levelValues != null && levelValues.size() > 0L){
			query.setParameterList("locationIds", levelValues);
		}
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		if(deptIdsList !=null && deptIdsList .size() > 0){
			query.setParameterList("deptIdList", deptIdsList );
		}
		return query.list();
	}
	public Object[] getMinMaxDates(){
		StringBuilder sb = new StringBuilder();
		sb.append(" select min(model.inserted_time),max(model.inserted_time) from fund_sanction model");
		Query query = getSession().createSQLQuery(sb.toString());
		return (Object[])query.uniqueResult();
	}
	
	public List<Object[]> getGrantTypeHighestAndLowestFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,String type,Long searchLevlId,List<Long> searchLvlVals){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select sum(modal.fundSanction.sactionAmount) " );
		
		sb.append(" ,modal.fundSanction.grantType.grantTypeId,modal.fundSanction.grantType.type " );
		
		sb.append(" from FundSanctionLocation modal where modal.isDeleted='N' and modal.fundSanction.isDeleted = 'N' ");
		
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			sb.append(" and modal.fundSanction.financialYearId in (:financialYearIdsList)  " );
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.DISTRICT_LEVEL_SCOPE_ID){
			sb.append(" and  modal.locationAddress.district.districtId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.constituency.constituencyId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.MANDAL_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.tehsil.tehsilId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() > 0l && searchLevlId.longValue() == IConstants.VILLAGE_LEVEL_SCOPE_ID){
			sb.append(" and modal.locationAddress.panchayat.panchayatId in (:searchLvlVals) ");
		}else if(searchLevlId != null && searchLevlId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID ){
			sb.append(" and fundSanctionLocation.locationAddress.parliament.constituencyId in (:searchLvlVals) " );
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			sb.append(" and modal.fundSanction.department.departmentId in (:deptIdsList) ");
		if(sourceIdsList != null && sourceIdsList.size()>0)
			sb.append("  ");
		if(sDate != null && eDate != null){
			sb.append(" and date(modal.fundSanction.insertedTime) between  :sDate and :eDate " );
		}
		if(type != null && type.equalsIgnoreCase("highest")){
			sb.append(" group by modal.fundSanction.grantType.grantTypeId order by sum(modal.fundSanction.sactionAmount) desc ");
		}else if(type != null && type.equalsIgnoreCase("lowest")){
			sb.append(" group by modal.fundSanction.grantType.grantTypeId order by sum(modal.fundSanction.sactionAmount) asc ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setFirstResult(0);
		query.setMaxResults(2);
		
		if(sDate != null && eDate != null){
			query.setDate("sDate", sDate);
			query.setDate("eDate", eDate);
		}
		if(searchLevlId != null && searchLevlId.longValue() > 0l){
			query.setParameterList("searchLvlVals", searchLvlVals);
		}
		if(financialYearIdsList != null && financialYearIdsList.size() >0l ){
			query.setParameterList("financialYearIdsList", financialYearIdsList);
		}
		if(deptIdsList != null && deptIdsList.size()>0)
			query.setParameterList("deptIdsList", deptIdsList);
		if(sourceIdsList != null && sourceIdsList.size()>0)
			;
		return query.list();
		
	}
}
