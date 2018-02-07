package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAffiliatedMemberDAO;
import com.itgrids.partyanalyst.model.AffiliatedMember;

public class AffiliatedMemberDAO extends GenericDaoHibernate<AffiliatedMember, Long> implements IAffiliatedMemberDAO {

	public AffiliatedMemberDAO(){
		super(AffiliatedMember.class);
	}
	//0-cadreId,1-mobileNum,2-memShipNo,3-voterId,4-cardNum,5-releativename,6-houseno,7-castename,8-firstnAMme,9-casteid,10-gender
	  public List<Object[]> searchAffiliatedMemberDetails(String searchType,String searchValue,String locationType, Long locationValue){
					
				StringBuilder sb=new StringBuilder();
				
				sb.append("select model.tdpCadreId,model.mobileNo,model.tdpCadre.memberShipNo,model.voterId,model.voter.voterIDCardNo, model.voter.relativeName," +
						" model.tdpCadre.houseNo,'',model.fullName, model.tdpCadre.casteState.casteStateId,model.gender,model.userAddress.userAddressId,model.affiliatedMemberId" +
						"  from AffiliatedMember model where model.isDeleted = 'N' ");
				
				if(searchType.equalsIgnoreCase("mobileno")){
					
					sb.append(" and model.mobileNo =:searchValue ");
					
				}else if(searchType.equalsIgnoreCase("mebershipno")){
					
					sb.append(" and model.tdpCadre.memberShipNo = :searchValue ");
					
				}else if(searchType.equalsIgnoreCase("votercardno")){
					sb.append(" and model.voter.voterIDCardNo = :searchValue ");
				}
				
				if(locationType != null && locationType.equalsIgnoreCase("district")){
					sb.append(" and model.userAddress.district.districtId=:locationValue");
				}else if(locationType != null && locationType.equalsIgnoreCase("constituency")){
					sb.append(" and model.userAddress.constituency.constituencyId=:locationValue");
				}else if(locationType != null && locationType.equalsIgnoreCase("mandal")){
					sb.append(" and model.userAddress.tehsil.tehsilId=:locationValue");
				}else if(locationType != null && locationType.equalsIgnoreCase("panchayat")){
					sb.append(" and model.userAddress.panchayat.panchayatId=:locationValue");
				}else if(locationType != null && locationType.equalsIgnoreCase("muncipality")){
					sb.append(" and model.userAddress.localElectionBody.localElectionBodyId=:locationValue");
				}else if(locationType != null && locationType.equalsIgnoreCase("ward")){
					sb.append(" and model.userAddress.ward.constituencyId=:locationValue");
				}
				
				Query query = getSession().createQuery(sb.toString());
				if(searchValue != null && searchValue.trim().length() > 0){
					   query.setParameter("searchValue",searchValue);
				 }
				if(locationType != null && locationType.trim().length() > 0 && locationValue != null && locationValue.longValue()>0){
					   query.setParameter("locationValue",locationValue);
				 }
				return query.list();
	  }
	  
	@Override
	public List<Object[]> searchAffiliatedMemberDetailsThroughTC(String searchType, String searchValue, String locationType,Long locationValue) {
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(" select model.tdpCadreId,model.tdpCadre.mobileNo,model.tdpCadre.memberShipNo,model.tdpCadre.voterId,model.tdpCadre.voter.voterIDCardNo, " +
				" model.tdpCadre.voter.relativeName,model.tdpCadre.houseNo,model.tdpCadre.casteState.caste.casteName,model.tdpCadre.firstname," +
				" model.tdpCadre.casteState.casteStateId,model.tdpCadre.gender,model.tdpCadre.userAddress.userAddressId,'' " +
				" from TdpCadreEnrollmentYear model where model.tdpCadre.isDeleted='N' and model.enrollmentYear.enrollmentYearId='4'");
		
		if(searchType.equalsIgnoreCase("mobileno")){
			
			sb.append(" and model.tdpCadre.mobileNo =:searchValue ");
			
		}else if(searchType.equalsIgnoreCase("mebershipno")){
			
			sb.append(" and model.tdpCadre.memberShipNo = :searchValue ");
			
		}
		
		if(locationType != null && locationType.equalsIgnoreCase("district")){
			sb.append(" and model.tdpCadre.userAddress.district.districtId=:locationValue");
		}else if(locationType != null && locationType.equalsIgnoreCase("constituency")){
			sb.append(" and model.tdpCadre.userAddress.constituency.constituencyId=:locationValue");
		}else if(locationType != null && locationType.equalsIgnoreCase("mandal")){
			sb.append(" and model.tdpCadre.userAddress.tehsil.tehsilId=:locationValue");
		}else if(locationType != null && locationType.equalsIgnoreCase("panchayat")){
			sb.append(" and model.tdpCadre.userAddress.panchayat.panchayatId=:locationValue");
		}else if(locationType != null && locationType.equalsIgnoreCase("muncipality")){
			sb.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId=:locationValue");
		}else if(locationType != null && locationType.equalsIgnoreCase("ward")){
			sb.append(" and model.tdpCadre.userAddress.ward.constituencyId=:locationValue");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(searchValue != null && searchValue.trim().length() > 0){
			   query.setParameter("searchValue",searchValue);
		 }
		if(locationType != null && locationType.trim().length() > 0 && locationValue != null && locationValue.longValue()>0){
			   query.setParameter("locationValue",locationValue);
		 }
		return query.list();
	}
	
	@Override
	public List<Object[]> searchAffiliatedMemberDetailsThroughVoter(String searchType, String searchValue, String locationType,Long locationValue) {
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(" select '',model.voter.mobileNo,'',model.voter.voterId,model.voter.voterIDCardNo, model.voter.relativeName," +
						" model.voter.houseNo,'',model.voter.name,'',model.voter.gender,'','' " +
						" from BoothPublicationVoter model where model.booth.publicationDate.publicationDateId=24 and model.voter.voterIDCardNo=:searchValue ");
		
		
		if(locationType != null && locationType.equalsIgnoreCase("constituency")){
			sb.append(" and model.booth.constituency.constituencyId=:locationValue");
		}else if(locationType != null && locationType.equalsIgnoreCase("mandal")){
			sb.append(" and model.booth.tehsil.tehsilId=:locationValue");
		}else if(locationType != null && locationType.equalsIgnoreCase("panchayat")){
			sb.append(" and model.booth.panchayat.panchayatId=:locationValue");
		}else if(locationType != null && locationType.equalsIgnoreCase("muncipality")){
			sb.append(" and model.booth.localBody.localElectionBodyId=:locationValue");
		}else if(locationType != null && locationType.equalsIgnoreCase("ward")){
			sb.append(" and model.booth.localBodyWard.constituencyId=:locationValue");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(searchValue != null && searchValue.trim().length() > 0){
			   query.setParameter("searchValue",searchValue);
		 }
		if(locationType != null && locationType.trim().length() > 0 && locationValue != null && locationValue.longValue()>0){
			   query.setParameter("locationValue",locationValue);
		 }
		return query.list();
	}
	@Override
	public Long getAffiliatedMemberId(Long cadreId) {
		
		Query query = getSession().createQuery("select  model.affiliatedMemberId from AffiliatedMember model where model.tdpCadreId =:cadreId ");
		query.setParameter("cadreId",cadreId);
		return (Long) query.uniqueResult();
	}
}