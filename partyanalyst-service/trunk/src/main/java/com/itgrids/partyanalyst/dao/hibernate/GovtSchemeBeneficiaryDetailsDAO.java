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
				" model.benefitedAmount " +
				" from GovtSchemeBeneficiaryDetails model " +
				" where model.isDeleted='N' and model.tdpCadreId in (:cadreIds) ");
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
	
	public List<Object[]> getBenefitSchemesMembersDetails(Long locationLevelId,Long benefitId){
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
}
