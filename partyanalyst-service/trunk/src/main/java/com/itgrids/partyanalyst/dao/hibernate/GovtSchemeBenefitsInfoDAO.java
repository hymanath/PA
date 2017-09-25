package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtSchemeBenefitsInfoDAO;
import com.itgrids.partyanalyst.model.GovtSchemeBenefitsInfo;

public class GovtSchemeBenefitsInfoDAO  extends GenericDaoHibernate<GovtSchemeBenefitsInfo, Long> implements IGovtSchemeBenefitsInfoDAO{

	public GovtSchemeBenefitsInfoDAO() {
		super(GovtSchemeBenefitsInfo.class);
		
	}

	public List<Object[]> getGovtSchemeWiseBenefitMemberCount(Long locationType, Long locationValue,List<Long> constituencyIds) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.govtSchemes.govtSchemesId," +
						" model.govtSchemes.schemeName," );
		if(locationType != null && locationType.longValue()>0l && locationType.longValue() != 10l){
			queryStr.append(" model.grivenaceCount " );
		}else if(locationType != null && locationType.longValue()>0l && locationType.longValue() == 10l){
			queryStr.append(" sum(model.grivenaceCount) " );
		}
		queryStr.append(" from " +
				        " GovtSchemeBenefitsInfo model " +
				        " where ");
		if(locationType != null && locationType.longValue()>0l && locationType.longValue() != 10l){
			queryStr.append(" model.locationScopeId =:locationType and ");
		}
		if(locationValue != null && locationValue.longValue()>0l && locationType.longValue() != 10l){
			queryStr.append("  model.locationValue =:locationValue  ");
		}else if(locationType != null && locationType.longValue() == 10l && constituencyIds != null && constituencyIds.size()>0){
			queryStr.append("  model.locationValue in(:constituencyIds)  ");
		}
		queryStr.append(" group by model.govtSchemes.govtSchemesId");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(locationType != null && locationType.longValue()>0l && locationType.longValue() != 10l){
			query.setParameter("locationType", locationType);
		}
		if(locationValue != null && locationValue.longValue()>0l && locationType.longValue() != 10l){
			query.setParameter("locationValue", locationValue);
		}else if(locationType != null && locationType.longValue() == 10l && constituencyIds != null && constituencyIds.size()>0){
			query.setParameterList("constituencyIds", constituencyIds);
		}
		return query.list();
	}
	public List<Object[]> getMandalWiseBenefitMemberCountByGovtScheme(Long locationType, Long locationValue,Long govtSchemeId,List<Long> constituencyIds) {
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select " );
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationType == 3l || locationType == 10l) {
				queryStr.append(" constituency.constituencyId,constituency.name, ");
			}else if (locationType == 4l) {
				queryStr.append(" tehsil.tehsil.tehsilId,tehsil.tehsil.tehsilName, ");
			} else if (locationType == 5l) {
				queryStr.append(" tehsil.tehsil.tehsilId,tehsil.tehsil.tehsilName, ");
			}else if (locationType == 6l) {
				queryStr.append(" panchayat.panchayatId,panchayat.panchayatName, ");
			}else if (locationType == 7l) {
				queryStr.append("  localElectionBody.localElectionBodyId,localElectionBody.name, ");
			}else if (locationType == 8l) {
				queryStr.append("  ward.constituencyId,ward.name, ");
			}
		}
		if(locationType != null && locationType != 10l){
		queryStr.append(" model.grivenaceCount " );
		}else if(locationType != null && locationType == 10l){
			queryStr.append(" sum(model.grivenaceCount) " );
		}
		queryStr.append(" from " +
				        " GovtSchemeBenefitsInfo model " );
				       // " where " );
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationType == 3l) {
				queryStr.append(" ,Constituency constituency where  model.locationValue=constituency.district.districtId and model.locationValue=:locationValue ");
			}else if (locationType == 10l && constituencyIds != null && constituencyIds.size()>0) {
				queryStr.append(" ,Constituency constituency where  model.locationValue=constituency.constituencyId and model.locationValue in(:constituencyIds) ");
			}else if (locationType == 4l) {
				queryStr.append(" ,TehsilConstituency tehsil where  model.locationValue =tehsil.constituency.constituencyId and model.locationValue=:locationValue ");
			} else if (locationType == 5l) {
				queryStr.append(" ,TehsilConstituency tehsil where  model.locationValue =tehsil.tehsil.tehsilId and model.locationValue=:locationValue ");
			}else if (locationType == 6l) {
				queryStr.append(" ,Panchayat panchayat where model.locationValue =panchayat.panchayatId and model.locationValue=:locationValue ");
			}else if (locationType == 7l) {
				queryStr.append(" ,LocalElectionBody  localElectionBody where model.locationValue =localElectionBody.localElectionBodyId and model.locationValue=:locationValue ");
			}else if (locationType == 8l) {
				queryStr.append(" ,Constituency ward where ward.localElectionBody is not null  and model.locationValue=:locationValue  ");
			}
		}
				        
		if (govtSchemeId != null && govtSchemeId.longValue() > 0){
			queryStr.append(" and model.govtSchemes.govtSchemesId=:govtSchemeId ");
		}
		
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationType == 3l) {
				queryStr.append(" group by constituency.district.districtId ");
			}else if (locationType == 10l && constituencyIds != null && constituencyIds.size()>0) {
				queryStr.append(" group by constituency.constituencyId ");
			}else if (locationType == 4l) {
				queryStr.append(" group by tehsil.constituency.constituencyId ");
			}else if (locationType == 5l) {
				queryStr.append(" group by tehsil.tehsil.tehsilId ");
			}else if (locationType == 6l) {
				queryStr.append(" group by panchayat.panchayatId ");
			}else if (locationType == 7l) {
				queryStr.append("  group by localElectionBody.localElectionBodyId ");
			}else if (locationType == 8l) {
				queryStr.append(" group by ward.constituencyId ");
			}
		}
		
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if (locationValue != null && locationValue.longValue() > 0l && locationType != 10l) {
			query.setParameter("locationValue", locationValue);
		}else if (locationValue != null && locationValue.longValue() > 0l && locationType != null && locationType == 10l && constituencyIds != null && constituencyIds.size()>0) {
			query.setParameterList("constituencyIds", constituencyIds);
		}
		if (govtSchemeId != null && govtSchemeId.longValue() > 0){
			query.setParameter("govtSchemeId", govtSchemeId);
		}
		return query.list();
	}
}
