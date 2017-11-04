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
		if(locationType != null && locationType.longValue()>0l && locationType.longValue() != 10l && locationType.longValue() != 2l){
			queryStr.append(" model.grivenaceCount " );
		}else if(locationType != null && locationType.longValue()>0l && (locationType.longValue() == 10l || locationType.longValue() == 2l )){
			queryStr.append(" sum(model.grivenaceCount) " );
		}
		queryStr.append(" from " +
				        " GovtSchemeBenefitsInfo model " +
				        " where ");
		if(locationType != null && locationType.longValue()>0l && locationType.longValue() != 10l){
			if(locationType.longValue() == 2l){
				queryStr.append(" model.locationScopeId =3 and ");
			}else{
				queryStr.append(" model.locationScopeId =:locationType and ");
			}
		}
		if(locationValue != null && locationValue.longValue()>0l && locationType.longValue() != 10l){
			if(locationType.longValue() == 2l){
				queryStr.append("  model.locationValue between 11 and 23  ");
			}else{
				queryStr.append("  model.locationValue =:locationValue  ");
			}
		}else if(locationType != null && locationType.longValue() == 10l && constituencyIds != null && constituencyIds.size()>0){
			queryStr.append("  model.locationValue in(:constituencyIds)  ");
		}
		queryStr.append(" group by model.govtSchemes.govtSchemesId");
		
		Query query = getSession().createQuery(queryStr.toString());
		if(locationType != null && locationType.longValue()>0l && locationType.longValue() != 10l && locationType.longValue() != 2l){
			query.setParameter("locationType", locationType);
		}
		if(locationValue != null && locationValue.longValue()>0l && locationType.longValue() != 10l && locationType.longValue() != 2l){
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
			if (locationType == 2l ) {
				queryStr.append(" district.districtId,district.districtName, ");
			}else if (locationType == 3l || locationType == 10l) {
				queryStr.append(" constituency.constituencyId,constituency.name, ");
			}else if (locationType == 4l) {
				//queryStr.append(" tehsil.tehsil.tehsilId,tehsil.tehsil.tehsilName, ");
				queryStr.append(" tehsil.tehsilId,tehsil.tehsilName, ");
			} else if (locationType == 5l) {
				//queryStr.append(" panchayat.panchayatId,panchayat.panchayatName, ");
				queryStr.append(" tehsil.tehsilId,tehsil.tehsilName, ");
			}else if (locationType == 6l) {
				queryStr.append(" panchayat.panchayatId,panchayat.panchayatName, ");
			}else if (locationType == 7l) {
				queryStr.append("  localElectionBody.localElectionBodyId,localElectionBody.name, ");
			}else if (locationType == 8l) {
				queryStr.append("  ward.constituencyId,ward.name, ");
			}
		}
		if(locationType != null && locationType != 10l && locationType != 2l && govtSchemeId.longValue()>0l){
		   queryStr.append(" model.grivenaceCount " );
		}else if(locationType != null && (locationType == 10l || locationType == 2l || govtSchemeId.longValue() == 0l)){
			queryStr.append(" sum(model.grivenaceCount) " );
		}
		queryStr.append(" from " +
				        " GovtSchemeBenefitsInfo model " );
				       // " where " );
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationType == 2l) {
				//queryStr.append(" ,Constituency constituency where  model.locationValue=constituency.district.districtId and model.locationValue between 11 and 23 and model.locationScopeId =3 ");
				queryStr.append(" ,District district where  model.locationValue=district.districtId and model.locationValue between 11 and 23 and model.locationScopeId =3 ");
			}else if (locationType == 3l) {
				queryStr.append(" ,Constituency constituency where  model.locationValue=constituency.constituencyId and model.locationValue=:locationValue and model.locationScopeId=3");
			}else if (locationType == 10l && constituencyIds != null && constituencyIds.size()>0l) {
				queryStr.append(" ,Constituency constituency where  model.locationValue=constituency.constituencyId and model.locationValue in(:constituencyIds) ");
			}else if (locationType == 4l) {
				//queryStr.append(" ,TehsilConstituency tehsil where  model.locationValue =tehsil.constituency.constituencyId and model.locationValue=:locationValue and model.locationScopeId=4 ");
				queryStr.append(" ,Tehsil tehsil where  model.locationValue =tehsil.tehsilId and model.locationValue=:locationValue and model.locationScopeId=4 ");
			} else if (locationType == 5l) {
				//queryStr.append(" ,Panchayat panchayat where model.locationValue =panchayat.tehsil.tehsilId and model.locationValue=:locationValue ");
				queryStr.append(" ,Tehsil tehsil where model.locationValue =tehsil.tehsilId and model.locationValue=:locationValue  and model.locationScopeId=5 ");
			}else if (locationType == 6l) {
				queryStr.append(" ,Panchayat panchayat where model.locationValue =panchayat.panchayatId and model.locationValue=:locationValue ");
			}else if (locationType == 7l) {
				queryStr.append(" ,LocalElectionBody  localElectionBody where model.locationValue =localElectionBody.localElectionBodyId and model.locationValue=:locationValue and model.locationScopeId=7 ");
			}else if (locationType == 8l) {
				queryStr.append(" ,Constituency ward where ward.localElectionBody is not null and ward.constituencyId=model.locationValue and model.locationValue=:locationValue  ");
			}
		}
				        
		if (govtSchemeId != null && govtSchemeId.longValue() > 0l){
			queryStr.append(" and model.govtSchemes.govtSchemesId=:govtSchemeId ");
		}
		
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if(locationType == 2l){
				queryStr.append(" group by district.districtId ");
			}else if (locationType == 3l ) {
				queryStr.append(" group by constituency.district.districtId ");
				//queryStr.append(" group by constituency.constituencyId  ");
			}else if (locationType == 10l && constituencyIds != null && constituencyIds.size()>0l) {
				queryStr.append(" group by constituency.constituencyId ");
			}else if (locationType == 4l) {
				//queryStr.append(" group by tehsil.constituency.constituencyId ");
				queryStr.append(" group by tehsil.tehsilId ");
			}else if (locationType == 5l) {
				//queryStr.append(" group by panchayat.tehsil.tehsilId ");
				queryStr.append(" group by tehsil.tehsilId ");
			}else if (locationType == 6l) {
				queryStr.append(" group by panchayat.panchayatId ");
			}else if (locationType == 7l) {
				queryStr.append("  group by localElectionBody.localElectionBodyId ");
			}else if (locationType == 8l) {
				queryStr.append(" group by ward.constituencyId ");
			}
		}
		
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if (locationValue != null && locationValue.longValue() > 0l && locationType != 10l && locationType != 2l) {
			query.setParameter("locationValue", locationValue);
		}else if (locationValue != null && locationValue.longValue() > 0l && locationType != null && locationType == 10l && constituencyIds != null && constituencyIds.size()>0) {
			query.setParameterList("constituencyIds", constituencyIds);
		}
		if (govtSchemeId != null && govtSchemeId.longValue() > 0l){
			query.setParameter("govtSchemeId", govtSchemeId);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationWiseSchemeOverview(Long locationType, Long locationValue,String type, boolean isLocalBody ){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select " );
		if(type.equalsIgnoreCase("Location")){
			if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
				if (locationType == 2l) {
					queryStr.append(" district.districtId,district.districtName,count(model.govtSchemeId),sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}else if (locationType == 3l || locationType == 10l) {
					queryStr.append(" constituency.constituencyId,constituency.name,count(model.govtSchemeId),sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}else if (locationType == 4l) {
					if(!isLocalBody){
						queryStr.append(" tehsilCons.tehsilId,tehsilCons.tehsil.tehsilName,count(model.govtSchemeId),sum(model.grivenaceCount),sum(model.benefitAmount) ");
					}else{
						queryStr.append(" ALB.localElectionBody.localElectionBodyId,concat(ALB.localElectionBody.name,'-',ALB.localElectionBody.electionType.electionType),COUNT(DISTINCT model.govtSchemeId),COUNT(model.grivenaceCount),sum(model.benefitAmount) ");
					}
				}else if (locationType == 5l) {
					queryStr.append(" panchayat.panchayatId,panchayat.panchayatName,count(model.govtSchemeId),sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}else if (locationType == 6l) {
					queryStr.append(" panchayat.panchayatId,panchayat.panchayatName,count(model.govtSchemeId),sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}else if (locationType == 7l) {
					queryStr.append(" c.localElectionBodyId, c.name,model.govtSchemeId,sum(model.grivenaceCount),sum(model.benefitAmount) ");

				}
			}
		}else if(type.equalsIgnoreCase("Schemes")){
			if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
				if (locationType == 2l) {
					queryStr.append(" model.govtSchemeId,model.govtSchemes.schemeName," +
							" sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}else if (locationType == 3l || locationType == 10l) {
					queryStr.append(" model.govtSchemeId," +
							" model.govtSchemes.schemeName,sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}else if (locationType == 4l) {
					if(!isLocalBody){
						
						queryStr.append(" model.govtSchemeId,model.govtSchemes.schemeName," +
								" sum(model.grivenaceCount),sum(model.benefitAmount) ");
					}else{
						queryStr.append(" model.govtSchemeId,model.govtSchemes.schemeName," +
								" sum(model.grivenaceCount),sum(model.benefitAmount) ");
					}
				}else if (locationType == 5l || locationType == 6l) {
					queryStr.append(" model.govtSchemeId,model.govtSchemes.schemeName," +
							" sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}else if (locationType == 7l) {
					queryStr.append(" model.govtSchemeId,model.govtSchemes.schemeName,sum(model.grivenaceCount),sum(model.benefitAmount) ");

				}
			}
		}
		
		queryStr.append(" from " +
				        " GovtSchemeBenefitsInfo model " );
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationType == 2l) {
				queryStr.append(" ,District district where  model.locationValue= district.districtId " +
						" and (district.districtId between 11 and 23) and model.locationScopeId =3 ");
			}else if (locationType == 3l){
				queryStr.append(" ,Constituency constituency where  model.locationValue= constituency.constituencyId" +
						"  and constituency.district.districtId =:locationValue and model.locationScopeId=:locationType");
			}else if (locationType == 4l) {
				if(!isLocalBody){
					queryStr.append(" ,TehsilConstituency tehsilCons where model.locationValue = tehsilCons.tehsilId " +
							" and tehsilCons.constituencyId =:locationValue and model.locationScopeId=:locationType ");
				}else{
					queryStr.append(" ,AssemblyLocalElectionBody ALB where model.locationValue = ALB.localElectionBody.localElectionBodyId and" +
							" ALB.constituency.constituencyId = :locationValue and model.locationScopeId=:locationType");
				}
			}else if (locationType == 5l) {
				queryStr.append(" ,Panchayat panchayat where model.locationValue = panchayat.panchayatId " +
						" and panchayat.tehsil.tehsilId =:locationValue and model.locationScopeId=:locationType ");
			}else if(locationType == 10l){
				queryStr.append(" ,Constituency constituency where  model.locationValue= constituency.constituencyId" +
						"  and constituency.constituencyId in(select distinct assemblyId from ParliamentAssembly where parliamentId=:locationValue) and model.locationScopeId=:locationType");
			}else if (locationType == 6l) {
				queryStr.append(" ,Panchayat panchayat where model.locationValue = panchayat.panchayatId " +
						" and panchayat.panchayatId =:locationValue and model.locationScopeId=:locationType ");
			}else if (locationType == 7l) {
				queryStr.append(" ,LocalElectionBody c where model.locationValue = c.localElectionBodyId " +
						" and c.localElectionBodyId =:locationValue and model.locationScopeId=:locationType ");
			}
		}
		
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if(type.equalsIgnoreCase("Location")){
				if(locationType == 2l ){
					queryStr.append(" group by district.districtId ");
				}else if (locationType == 10l || locationType == 3l) {
					queryStr.append(" group by constituency.constituencyId ");
				}else if (locationType == 4l) {
					if(!isLocalBody){
						
						queryStr.append(" group by tehsilCons.tehsilId ");
					}else{
						queryStr.append(" GROUP BY ALB.localElectionBody.localElectionBodyId ");
					}
				}else if (locationType == 5l || locationType == 6l) {
					queryStr.append(" group by panchayat.panchayatId ");
				}else if(locationType == 7l){
					queryStr.append(" group by c.localElectionBodyId ");
				}
			}else{
				queryStr.append(" group by model.govtSchemeId ");
			}
		}
		Query query = getSession().createQuery(queryStr.toString());
			if (locationType != 2l ){ 
				if(locationType !=6l && locationType !=7l){				
					if(!isLocalBody){
						query.setParameter("locationType", locationType+1l);
					}else{
						query.setParameter("locationType", 7l);
					}
				}else{
					query.setParameter("locationType", locationType);
				}
				if(locationType == 4l){
					query.setParameter("locationValue", locationValue);
				}else if(locationType == 5l || locationType == 6l){
					query.setParameter("locationValue", locationValue);
				}else if(locationType == 10l ){
					query.setParameter("locationType", 4L);
					query.setParameter("locationValue", locationValue);
				}else if(locationType == 3l){
					query.setParameter("locationValue", locationValue);
				}else if(locationType == 7l){
					query.setParameter("locationValue", locationValue);
				}
			}
					
		  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationWiseSchemesDetailsInfo(Long locationType, Long locationValue,boolean isLocalBody){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select " );
			if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
				if (locationType == 2l) {
					queryStr.append(" district.districtId,district.districtName,model.govtSchemeId,model.govtSchemes.schemeName," +
							" sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}else if (locationType == 3l || locationType == 10l) {
					queryStr.append(" constituency.constituencyId,constituency.name,model.govtSchemeId," +
							" model.govtSchemes.schemeName,sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}else if (locationType == 4l) {
					if(!isLocalBody){
						queryStr.append(" tehsilCons.tehsilId,tehsilCons.tehsil.tehsilName,model.govtSchemeId,model.govtSchemes.schemeName," +
								" sum(model.grivenaceCount),sum(model.benefitAmount) ");
					}else{
						queryStr.append(" ALB.localElectionBody.localElectionBodyId,concat(ALB.localElectionBody.name,'-',ALB.localElectionBody.electionType.electionType),model.govtSchemeId,model.govtSchemes.schemeName," +
								"sum(model.grivenaceCount),sum(model.benefitAmount) ");
					}
				}else if (locationType == 5l || locationType == 6l) {
					queryStr.append(" panchayat.panchayatId,panchayat.panchayatName,model.govtSchemeId,model.govtSchemes.schemeName," +
							" sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}else if(locationType == 7l){
					queryStr.append(" c.localElectionBodyId, c.name,model.govtSchemeId,model.govtSchemes.schemeName," +
							" sum(model.grivenaceCount),sum(model.benefitAmount) ");
				}
			}
		
		queryStr.append(" from " +
				        " GovtSchemeBenefitsInfo model " );
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationType == 2l) {
				queryStr.append(" ,District district where  model.locationValue= district.districtId " +
						" and (district.districtId between 11 and 23) and model.locationScopeId =3 ");
			}else if (locationType == 3l){
				queryStr.append(" ,Constituency constituency where  model.locationValue= constituency.constituencyId" +
						"  and constituency.district.districtId =:locationValue and model.locationScopeId=:locationType");
			}else if (locationType == 4l) {
				if(!isLocalBody){
					queryStr.append(" ,TehsilConstituency tehsilCons where model.locationValue = tehsilCons.tehsilId " +
							" and tehsilCons.constituencyId =:locationValue and model.locationScopeId=:locationType ");
				}else{
					queryStr.append(" ,AssemblyLocalElectionBody ALB where model.locationValue = ALB.localElectionBody.localElectionBodyId and" +
							" ALB.constituency.constituencyId = :locationValue and model.locationScopeId=:locationType");
				}
			}else if (locationType == 5l) {
				queryStr.append(" ,Panchayat panchayat where model.locationValue = panchayat.panchayatId " +
						" and panchayat.tehsil.tehsilId =:locationValue and model.locationScopeId=:locationType ");
			}else if(locationType == 10l){
				queryStr.append(" ,Constituency constituency where  model.locationValue= constituency.constituencyId" +
						"  and constituency.constituencyId in(select distinct assemblyId from ParliamentAssembly where parliamentId=:locationValue) and model.locationScopeId=:locationType");
			}else if (locationType == 6l) {
				queryStr.append(" ,Panchayat panchayat where model.locationValue = panchayat.panchayatId " +
						" and panchayat.panchayatId =:locationValue and model.locationScopeId=:locationType ");
			}else if (locationType == 7l) {
				queryStr.append(" ,LocalElectionBody c where model.locationValue = c.localElectionBodyId " +
						" and c.localElectionBodyId =:locationValue and model.locationScopeId=:locationType ");
			}
		}
		
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
				if(locationType == 2l ){
					queryStr.append(" group by district.districtId,model.govtSchemeId ");
				}else if (locationType == 10l || locationType == 3l) {
					queryStr.append(" group by constituency.constituencyId,model.govtSchemeId ");
				}else if (locationType == 4l) {
					if(!isLocalBody){
						queryStr.append(" group by tehsilCons.tehsilId,model.govtSchemeId ");
					}else{
						queryStr.append(" GROUP BY ALB.localElectionBody.localElectionBodyId,model.govtSchemeId ");
					}
				}else if (locationType == 5l || locationType == 6l) {
					queryStr.append(" group by panchayat.panchayatId,model.govtSchemeId ");
				}else if(locationType == 7l){
					queryStr.append(" group by c.localElectionBodyId,model.govtSchemeId ");
				}
		}
		Query query = getSession().createQuery(queryStr.toString());
			if (locationType != 2l){ 
				if(locationType !=6l && locationType !=7l){				
					if(!isLocalBody){
						query.setParameter("locationType", locationType+1l);
					}else{
						query.setParameter("locationType", 7l);
					}
				}else{
					query.setParameter("locationType", locationType);
				}
				if(locationType == 4l){
					query.setParameter("locationValue", locationValue);
				}else if(locationType == 5l || locationType == 6l){
					query.setParameter("locationValue", locationValue);
				}else if(locationType == 10l ){
					query.setParameter("locationType", 4L);
					query.setParameter("locationValue", locationValue);
				}else if(locationType == 3l){
					query.setParameter("locationValue", locationValue);
				}else if(locationType == 7l){
					query.setParameter("locationValue", locationValue);
				}
			}
					
		  return query.list();
	}
}
