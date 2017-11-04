package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtSchemeBeneficiaryDetailsDAO;
import com.itgrids.partyanalyst.dao.IGovtSchemeBenefitTypeDAO;
import com.itgrids.partyanalyst.model.GovtSchemeBeneficiaryDetails;
import com.itgrids.partyanalyst.model.Schemephase;

public class GovtSchemeBeneficiaryDetailsDAO extends GenericDaoHibernate<GovtSchemeBeneficiaryDetails, Long> implements IGovtSchemeBeneficiaryDetailsDAO{

	public GovtSchemeBeneficiaryDetailsDAO() {
		super(GovtSchemeBeneficiaryDetails.class);
		
	}

	public List<Object[]> getBenefitsApprovedDetails(List<Long> cadreIds){
		
		Query query = getSession().createQuery(" select model.tdpCadreId,model.benefiaryName,model.govtSchemesId,model.govtSchemes.schemeName," +
				" sum(model.benefitedAmount) " +
				" from GovtSchemeBeneficiaryDetails model " +
				" where model.isDeleted='N' and model.tdpCadreId in (:cadreIds) group by model.tdpCadreId,model.govtSchemesId " +
				" order by model.tdpCadreId,model.govtSchemesId ");
		query.setParameterList("cadreIds", cadreIds);
		
		return query.list();
	}
	
	public List<Object[]> getOwnAndParticipatedConstituenciesBenefitDetails(List<Long> constIds){
		//0-constId,1-name,2-schemeId,3-count
		Query query = getSession().createQuery(" select model.userAddress.constituency.constituencyId,model.userAddress.constituency.name," +
				" model.govtSchemesId,count(model.govtSchemeBeneficiaryDetailsId) " +
				" from GovtSchemeBeneficiaryDetails model " +
				" where model.isDeleted='N'" +
				" and model.userAddress.constituency.constituencyId in (:constIds) " +
				" group by model.userAddress.constituency.constituencyId,model.govtSchemesId ");
		
		query.setParameterList("constIds", constIds);
		return query.list();
	}
	
	public List<Object[]> getLocalityBasedBenefitSchemesDetails(Long accessLevelId,List<Long> accessLevelValuesList){
		//0-levelId,1-levelName,2-benefitSchemeId,3-count
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select ");
		
		if(accessLevelId == 1l){
			sb.append(" model.userAddress.country.countryId,model.userAddress.country.countryName ");
		}else if(accessLevelId == 2l){
			sb.append(" model.userAddress.state.stateId,model.userAddress.state.stateName ");
		}else if(accessLevelId == 3l){
			sb.append(" model.userAddress.district.districtId,model.userAddress.district.districtName ");
		}else if(accessLevelId == 4l){
			sb.append(" model.userAddress.parliamentConstituency.constituencyId,model.userAddress.parliamentConstituency.name ");
		}else if(accessLevelId == 5l){
			sb.append(" model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");
		}else if(accessLevelId == 6l){
			sb.append(" model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");
		}else if(accessLevelId == 7l){
			sb.append(" model.userAddress.localElectionBody.localElectionBodyId,model.userAddress.localElectionBody.name ");
		}else if(accessLevelId == 8l){
			sb.append(" model.userAddress.panchayat.panchayatId,model.userAddress.panchayat.panchayatName ");
		}else if(accessLevelId == 9l){
			sb.append(" model.userAddress.ward.constituencyId,model.userAddress.ward.name ");
		}
		
		sb.append(",model.govtSchemesId,count(model.govtSchemeBeneficiaryDetailsId) ");
		
		sb.append(" from GovtSchemeBeneficiaryDetails model " +
				" where model.isDeleted='N' ");
		
		if(accessLevelId == 1l){
			sb.append(" and model.userAddress.country.countryId in (:accessLevelValuesList) ");
		}else if(accessLevelId == 2l){
			sb.append(" and model.userAddress.state.stateId in (:accessLevelValuesList) ");
		}else if(accessLevelId == 3l){
			sb.append(" and model.userAddress.district.districtId in (:accessLevelValuesList) ");
		}else if(accessLevelId == 4l){
			sb.append(" and model.userAddress.parliamentConstituency.constituencyId in (:accessLevelValuesList) ");
		}else if(accessLevelId == 5l){
			sb.append(" and model.userAddress.constituency.constituencyId in (:accessLevelValuesList) ");
		}else if(accessLevelId == 6l){
			sb.append(" and model.userAddress.tehsil.tehsilId in (:accessLevelValuesList) ");
		}else if(accessLevelId == 7l){
			sb.append(" and model.userAddress.localElectionBody.localElectionBodyId in (:accessLevelValuesList) ");
		}else if(accessLevelId == 8l){
			sb.append(" and model.userAddress.panchayat.panchayatId in (:accessLevelValuesList) ");
		}else if(accessLevelId == 9l){
			sb.append(" and model.userAddress.ward.constituencyId in (:accessLevelValuesList) ");
		}
		
		sb.append(" group by model.govtSchemesId ");
		
		if(accessLevelId == 1l){
			sb.append(" ,model.userAddress.country.countryId ");
		}else if(accessLevelId == 2l){
			sb.append(" ,model.userAddress.state.stateId ");
		}else if(accessLevelId == 3l){
			sb.append(" ,model.userAddress.district.districtId ");
		}else if(accessLevelId == 4l){
			sb.append(" ,model.userAddress.parliamentConstituency.constituencyId ");
		}else if(accessLevelId == 5l){
			sb.append(" ,model.userAddress.constituency.constituencyId ");
		}else if(accessLevelId == 6l){
			sb.append(" ,model.userAddress.tehsil.tehsilId ");
		}else if(accessLevelId == 7l){
			sb.append(" ,model.userAddress.localElectionBody.localElectionBodyId ");
		}else if(accessLevelId == 8l){
			sb.append(" ,model.userAddress.panchayat.panchayatId ");
		}else if(accessLevelId == 9l){
			sb.append(" ,model.userAddress.ward.constituencyId ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("accessLevelValuesList", accessLevelValuesList);
		
		return query.list();
	}
	
	public List<Object[]> getBenefitSchemesMembersDetails(Long locationLevelId,Long benefitId,Integer minValue,Integer maxValue){
		//0-id,1-name,2-relativeName,3-cadreId,4-membershipNum,5-adharNum,6-mobileNum,7-tehsilId,8-tehsinName,9-lebId,10-lebName,11-schemeId,12-schemeName,13-amount
		Query query = getSession().createQuery(" select model.govtSchemeBeneficiaryDetailsId,model.benefiaryName," +
				" tdpCadre.relativename,tdpCadre.tdpCadreId,tdpCadre.memberShipNo,model.avmId,model.mobileNo," +
				" tehsil.tehsilId,tehsil.tehsilName," +
				" localElectionBody.localElectionBodyId,localElectionBody.name," +
				" model.govtSchemesId,model.govtSchemes.schemeName,model.benefitedAmount " +
				" from  GovtSchemeBeneficiaryDetails model " +
				" left join model.tdpCadre tdpCadre " +
				" left join model.userAddress userAddress " +
				" left join userAddress.tehsil tehsil " +
				" left join userAddress.localElectionBody localElectionBody " +
				" where model.isDeleted='N' " +
				" and model.userAddress.constituency.constituencyId = :locationLevelId and model.govtSchemesId = :benefitId ");
		
		query.setParameter("locationLevelId", locationLevelId);
		query.setParameter("benefitId", benefitId);
		if(minValue != null && minValue > 0){
			query.setFirstResult(minValue);
		}
		if(maxValue != null && maxValue > 0){
			query.setMaxResults(maxValue);
		}
		return query.list();
	}
	
	public List<Object[]> getAllConstBenefitDetailsForADist(Long distId){
		//0-constId,1-constName,2-schemeId,3-count
		Query query = getSession().createQuery(" select model.userAddress.constituency.constituencyId,model.userAddress.constituency.name," +
				" model.govtSchemesId,count(model.govtSchemeBeneficiaryDetailsId) " +
				" from GovtSchemeBeneficiaryDetails model " +
				" where model.isDeleted='N' and model.userAddress.district.districtId=:distId " +
				" group by model.userAddress.constituency.constituencyId,model.govtSchemesId ");
		
		query.setParameter("distId", distId);
		
		return query.list();
	}

	public List<Object[]> getGovtSchemeWiseBenefitMemberCount(Long locationType, Long locationValue) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.govtSchemes.govtSchemesId," +
						" model.govtSchemes.schemeName," +
				        " count(model.govtSchemeBeneficiaryDetailsId) " +
				        " from " +
				        " GovtSchemeBeneficiaryDetails model " +
				        " where " +
				        " model.isDeleted='N' ");
		
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationType == 3l) {
				queryStr.append(" and model.userAddress.district.districtId=:locationValue ");
			} else if (locationType == 10l) {
				queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId=:locationValue ");
			} else if (locationType == 4l) {
				queryStr.append(" and model.userAddress.constituency.constituencyId=:locationValue ");
			} else if (locationType == 5l) {
				queryStr.append(" and model.userAddress.tehsil.tehsilId=:locationValue ");
			}else if (locationType == 6l) {
				queryStr.append(" and model.userAddress.panchayat.panchayatId=:locationValue ");
			}else if (locationType == 7l) {
				queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId=:locationValue ");
			}else if (locationType == 8l) {
				queryStr.append(" and model.userAddress.ward.constituencyId=:locationValue ");
			}
		}
		queryStr.append(" group by model.govtSchemesId");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if (locationValue != null && locationValue.longValue() > 0l) {
			query.setParameter("locationValue", locationValue);
		}
		return query.list();
	}
	public List<Object[]> getMandalWiseBenefitMemberCountByGovtScheme(Long locationType, Long locationValue,Long govtSchemeId) {
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select " );
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationType == 3 || locationType == 10) {
				queryStr.append(" constituency.constituencyId,constituency.name, ");
			}else if (locationType == 4) {
				queryStr.append(" tehsil.tehsilId,tehsil.tehsilName, ");
			} else if (locationType == 5 || locationType == 6l) {
				queryStr.append(" panchayat.panchayatId,panchayat.panchayatName, ");
			}else if (locationType == 7l) {
				queryStr.append("  localElectionBody.localElectionBodyId,localElectionBody.name, ");
			}else if (locationType == 8l) {
				queryStr.append("  ward.constituencyId,ward.name, ");
			}
		}
		queryStr.append("  count(model.govtSchemeBeneficiaryDetailsId) " +
				        " from " +
				        " GovtSchemeBeneficiaryDetails model " +
				        " left join model.userAddress userAddress " +
				        " left join  userAddress.district district" +
				        " left join  userAddress.parliamentConstituency parliamentConstituency" +
				        " left join  userAddress.constituency constituency " +
				        " left join  userAddress.tehsil tehsil" +
				        " left join  userAddress.panchayat panchayat" +
				        " left join  userAddress.localElectionBody localElectionBody  " +
				        " left join  userAddress.ward ward " +
				        " where " +
				        " model.isDeleted='N' ");
		
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationType == 3) {
				queryStr.append(" and district.districtId=:locationValue ");
			} else if (locationType == 10) {
				queryStr.append(" and parliamentConstituency.constituencyId=:locationValue ");
			} else if (locationType == 4) {
				queryStr.append(" and constituency.constituencyId=:locationValue ");
			} else if (locationType == 5) {
				queryStr.append(" and tehsil.tehsilId=:locationValue ");
			}else if (locationType == 6l) {
				queryStr.append(" and panchayat.panchayatId=:locationValue ");
			}else if (locationType == 7l) {
				queryStr.append(" and localElectionBody.localElectionBodyId=:locationValue ");
			}else if (locationType == 8l) {
				queryStr.append(" and ward.constituencyId=:locationValue ");
			}
		}
		if (govtSchemeId != null && govtSchemeId.longValue() > 0){
			queryStr.append(" and model.govtSchemes.govtSchemesId=:govtSchemeId");
		}
		
		if (locationType != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationType == 3 || locationType == 10 ) {
				queryStr.append(" group by constituency.constituencyId ");
			} else if (locationType == 4) {
				queryStr.append(" group by tehsil.tehsilId ");
			} else if (locationType == 5 || locationType == 6l) {
				queryStr.append(" group by panchayat.panchayatId ");
			}else if (locationType == 7l) {
				queryStr.append("  group by localElectionBody.localElectionBodyId ");
			}else if (locationType == 8l) {
				queryStr.append(" group by ward.constituencyId ");
			}
		}
		
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if (locationValue != null && locationValue.longValue() > 0l) {
			query.setParameter("locationValue", locationValue);
		}
		if (govtSchemeId != null && govtSchemeId.longValue() > 0){
			query.setParameter("govtSchemeId", govtSchemeId);
		}
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getMemberDetailsForBenefitInfo(Long locationscopeId, Long locationValue,Long govtSchemeId,boolean isLocalBody) {
		StringBuilder queryStr = new StringBuilder();
		if (locationscopeId != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationscopeId == 2) {
					queryStr.append(" SELECT t.district_id,t.district_name,b.benefiary_name,b.mobile_no,gs.scheme_name,sum(b.benefited_amount),b.govt_scheme_beneficiary_details_id " +
							" from " +
							" govt_scheme_beneficiary_details b  " +
							" LEFT JOIN user_address ua  ON b.user_address_id = ua.user_address_id " +
							" LEFT JOIN district t  ON ua.district_id = t.district_id " +
							" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id " +
							" where ua.district_id =:locationValue and b.is_deleted='N' ");
			}else if (locationscopeId == 3 || locationscopeId == 10) {
				queryStr.append(" SELECT t.constituency_id,t.name,b.benefiary_name,b.mobile_no,gs.scheme_name,sum(b.benefited_amount),b.govt_scheme_beneficiary_details_id " +
						" from govt_scheme_beneficiary_details b " +
						" LEFT JOIN user_address ua  ON b.user_address_id = ua.user_address_id LEFT JOIN constituency t  ON ua.constituency_id = t.constituency_id" +
						" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id " +
						" where ua.constituency_id =:locationValue and b.is_deleted='N'");
			}else if (locationscopeId == 4) {
				if(!isLocalBody){
					
					queryStr.append(" SELECT t.tehsil_id,t.tehsil_name,b.benefiary_name,b.mobile_no,gs.scheme_name,sum(b.benefited_amount),b.govt_scheme_beneficiary_details_id " +
							" from govt_scheme_beneficiary_details b " +
							" LEFT JOIN user_address ua  ON b.user_address_id = ua.user_address_id LEFT JOIN tehsil t  ON ua.tehsil_id = t.tehsil_id" +
							" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id " +
							" where ua.tehsil_id =:locationValue and ua.local_election_body is null and b.is_deleted='N' ");
				}else{
					queryStr.append(" SELECT leb.local_election_body_id,leb.name,b.benefiary_name,b.mobile_no,gs.scheme_name,SUM(b.benefited_amount)," +
							" b.govt_scheme_beneficiary_details_id FROM govt_scheme_beneficiary_details b LEFT JOIN " +
							" user_address ua ON b.user_address_id = ua.user_address_id LEFT JOIN local_election_body leb ON ua.local_election_body = leb.local_election_body_id" +
							" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id WHERE	ua.local_election_body =:locationValue " +
							" AND ua.local_election_body IS Not NULL AND b.is_deleted = 'N' ");
				}
			}else if (locationscopeId == 5 || locationscopeId == 6l) {
				queryStr.append(" SELECT t.panchayat_id,t.panchayat_name,b.benefiary_name,b.mobile_no,gs.scheme_name,sum(b.benefited_amount),b.govt_scheme_beneficiary_details_id " +
						" from  govt_scheme_beneficiary_details b " +
						" LEFT JOIN user_address ua  ON b.user_address_id = ua.user_address_id LEFT JOIN panchayat t  ON ua.panchayat_id = t.panchayat_id" +
						" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id " +
						" where ua.panchayat_id =:locationValue and b.is_deleted='N' ");
			}else if (locationscopeId == 7l) {
				queryStr.append(" SELECT t.local_election_body_id,t.name,b.benefiary_name,b.mobile_no,gs.scheme_name,sum(b.benefited_amount),b.govt_scheme_beneficiary_details_id " +
						" from  govt_scheme_beneficiary_details b " +
						" LEFT JOIN user_address ua  ON b.user_address_id = ua.user_address_id " +
						" LEFT JOIN local_election_body t  ON ua.local_election_body = t.local_election_body_id " +
						" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id " +
						" where ua.local_election_body =:locationValue and b.is_deleted='N' ");
			}
		}
		if(govtSchemeId != null && govtSchemeId.longValue() >0){
			queryStr.append(" and b.govt_schemes_id =:govtSchemeId ");
		}
		queryStr.append(" GROUP BY b.govt_scheme_beneficiary_details_id  ");
			
		Query query = getSession().createSQLQuery(queryStr.toString());
		if (locationscopeId != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationscopeId == 2){
				query.setParameter("locationValue", locationValue);
			}else if (locationscopeId == 3 || locationscopeId == 10){
				query.setParameter("locationValue", locationValue);
			}else if (locationscopeId == 4){
				query.setParameter("locationValue", locationValue);
			} else if (locationscopeId == 5 || locationscopeId == 6l){
				query.setParameter("locationValue", locationValue);
			}else if (locationscopeId == 7l){
				query.setParameter("locationValue", locationValue);
			}
		}
		if (govtSchemeId != null && govtSchemeId.longValue() > 0){
			query.setParameter("govtSchemeId", govtSchemeId);
		}
		return query.list();
	}
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getMemberDetailsForBenefitInfoForClick(Long locationscopeId, Long locationValue,Long govtSchemeId) {
		StringBuilder queryStr = new StringBuilder();
		if (locationscopeId != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationscopeId == 2) {
				queryStr.append(" SELECT b.govt_scheme_beneficiary_details_id,c.caste_name from " +
						" govt_scheme_beneficiary_details b  " +
						" LEFT JOIN user_address ua  ON b.user_address_id = ua.user_address_id " +
						" LEFT JOIN district t  ON ua.district_id = t.district_id " +
						" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id " +
						" LEFT JOIN  user_voter_details uvd  on b.voter_id = uvd.voter_id " +
						" LEFT JOIN caste_state cs on uvd.caste_state_id = cs.caste_state_id " +
						" LEFT JOIN caste c on cs.caste_id = c.caste_id " +
						" where uvd.user_id = 1 and ua.district_id =:locationValue and b.is_deleted='N' ");
			}else if (locationscopeId == 3 || locationscopeId == 10) {
				queryStr.append(" SELECT b.govt_scheme_beneficiary_details_id,c.caste_name " +
						" from govt_scheme_beneficiary_details b " +
						" LEFT JOIN user_address ua  ON b.user_address_id = ua.user_address_id LEFT JOIN constituency t  ON ua.constituency_id = t.constituency_id" +
						" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id LEFT JOIN  user_voter_details uvd  on b.voter_id = uvd.voter_id " +
						" LEFT JOIN caste_state cs on uvd.caste_state_id = cs.caste_state_id LEFT JOIN caste c on cs.caste_id = c.caste_id " +
						" where uvd.user_id = 1 and ua.constituency_id =:locationValue and b.is_deleted='N'");
			}else if (locationscopeId == 4) {
				queryStr.append(" SELECT b.govt_scheme_beneficiary_details_id,c.caste_name " +
						" from govt_scheme_beneficiary_details b " +
						" LEFT JOIN user_address ua  ON b.user_address_id = ua.user_address_id LEFT JOIN tehsil t  ON ua.tehsil_id = t.tehsil_id" +
						" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id  LEFT JOIN  user_voter_details uvd  on b.voter_id = uvd.voter_id " +
						" LEFT JOIN caste_state cs on uvd.caste_state_id = cs.caste_state_id LEFT JOIN caste c on cs.caste_id = c.caste_id " +
						" where uvd.user_id = 1 and ua.tehsil_id =:locationValue and ua.local_election_body is null and b.is_deleted='N' ");
			}else if (locationscopeId == 5 || locationscopeId == 6l) {
				queryStr.append(" SELECT b.govt_scheme_beneficiary_details_id,c.caste_name " +
						" from  govt_scheme_beneficiary_details b " +
						" LEFT JOIN user_address ua  ON b.user_address_id = ua.user_address_id LEFT JOIN panchayat t  ON ua.panchayat_id = t.panchayat_id" +
						" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id " +
						" LEFT JOIN  user_voter_details uvd  on b.voter_id = uvd.voter_id LEFT JOIN caste_state cs on uvd.caste_state_id = cs.caste_state_id" +
						" LEFT JOIN caste c on cs.caste_id = c.caste_id " +
						" where uvd.user_id = 1 and ua.panchayat_id =:locationValue and b.is_deleted='N' ");
			}else if (locationscopeId == 7l) {
				queryStr.append(" SELECT b.govt_scheme_beneficiary_details_id,c.caste_name " +
						" from  govt_scheme_beneficiary_details b " +
						" LEFT JOIN user_address ua  ON b.user_address_id = ua.user_address_id " +
						" LEFT JOIN local_election_body t  ON ua.local_election_body = t.local_election_body_id" +
						" LEFT JOIN govt_schemes gs ON gs.govt_schemes_id = b.govt_schemes_id  " +
						" LEFT JOIN  user_voter_details uvd  on b.voter_id = uvd.voter_id LEFT JOIN caste_state cs on uvd.caste_state_id = cs.caste_state_id" +
						" LEFT JOIN caste c on cs.caste_id = c.caste_id " +
						" where uvd.user_id = 1 and ua.local_election_body =:locationValue and b.is_deleted='N' ");
			}
		}
		if(govtSchemeId != null && govtSchemeId.longValue() >0){
			queryStr.append(" and b.govt_schemes_id =:govtSchemeId ");
		}
		queryStr.append(" GROUP BY b.govt_scheme_beneficiary_details_id  ");
			
		Query query = getSession().createSQLQuery(queryStr.toString());
		if (locationscopeId != null && locationValue != null && locationValue.longValue() > 0l) {
			if (locationscopeId == 2){
				query.setParameter("locationValue", locationValue);
			}else if (locationscopeId == 3 || locationscopeId == 10){
				query.setParameter("locationValue", locationValue);
			}else if (locationscopeId == 4){
				query.setParameter("locationValue", locationValue);
			} else if (locationscopeId == 5 || locationscopeId == 6l){
				query.setParameter("locationValue", locationValue);
			}else if (locationscopeId == 7l){
				query.setParameter("locationValue", locationValue);
			}
		}
		if (govtSchemeId != null && govtSchemeId.longValue() > 0){
			query.setParameter("govtSchemeId", govtSchemeId);
		}
		return query.list();
	}
}
