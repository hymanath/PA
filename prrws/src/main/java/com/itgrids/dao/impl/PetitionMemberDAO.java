package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionMemberDAO;
import com.itgrids.model.PetitionMember;

@Repository
public class PetitionMemberDAO extends GenericDaoHibernate<PetitionMember, Long> implements IPetitionMemberDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	PetitionMemberDAO(){
	super(PetitionMember.class);
	} 
	
	public List<Object[]> getRepresentativeSearchDetailsBy(String searchType,String searchValue,Long searchLevelId,Long searchLevelValue,
			Date startDate,Date endDate){
		
		StringBuilder sb = new StringBuilder();
		Query qry = null;
		if(searchType != null && (searchType.equalsIgnoreCase("name") || searchType.equalsIgnoreCase("mobileNo") || searchType.equalsIgnoreCase("emailId")
				 || searchType.equalsIgnoreCase("refCode") || searchType.equalsIgnoreCase("designation"))){
			sb.append(" select model.petitionWorkDetails.petitionMember.petitionMemberId,model.petitionWorkDetails.petitionMember.refCode," +
					" model.petitionWorkDetails.petitionMember.candidateName,model.petitionWorkDetails.petitionMember.mobileNo,model.petitionWorkDetails.petitionMember.age," +
					" petitionDesignation.petitionDesignationId" +
					",petitionDesignation.designationName,model.petitionWorkDetails.petitionMember.memberType,district.districtId,district.districtName," +
					" constituency.constituencyId,constituency.name," +
					" refdistrict.districtId,refdistrict.districtName," +
					" refconstituency.constituencyId,refconstituency.name, " +
					" petitionDesignation1.designationName " +
					" from PetitionSubWorkLocationDetails model " +
					" left join model.petitionWorkDetails.petitionMember.petitionDesignation petitionDesignation1 " +
					" left join model.locationAddress locationAddress " +
					" left join locationAddress.state state " +
					" left join locationAddress.district district " +
					" left join locationAddress.constituency constituency " +
					" left join locationAddress.tehsil tehsil " +
					" left join locationAddress.localElectionBody localElectionBody " +
					" left join locationAddress.panchayat panchayat " +
					" left join model.petitionWorkDetails.petitionMember.petitionReffererCandidate petitionReffererCandidate " +
					" left join petitionReffererCandidate.petitionDesignation petitionDesignation " +
					" left join petitionReffererCandidate.locationAddress reflocationAddress1 " +
					" left join reflocationAddress1.district refdistrict " +
					" left join reflocationAddress1.constituency refconstituency ");
			sb.append(" where ");
			
			if(searchType.equalsIgnoreCase("name") && searchValue != null && !searchValue.isEmpty()){
				sb.append(" model.petitionWorkDetails.petitionMember.candidateName like '%"+searchValue+"%' and ");
			}else if(searchType.equalsIgnoreCase("mobileNo") && searchValue != null && !searchValue.isEmpty()){
				sb.append(" model.petitionWorkDetails.petitionMember.mobileNo ='"+searchValue+"' and  ");
			}else if(searchType.equalsIgnoreCase("emailId") && searchValue != null && !searchValue.isEmpty()){
				sb.append(" model.petitionWorkDetails.petitionMember.emailId like '%"+searchValue+"%'  and ");
			}else if(searchType.equalsIgnoreCase("refCode") && searchValue != null && !searchValue.isEmpty()){
				sb.append(" model.petitionWorkDetails.petitionMember.refCode = '"+searchValue+"'   and ");
			}else if(searchType.equalsIgnoreCase("designation") &&searchValue != null && !searchValue.isEmpty() && Long.valueOf(searchValue)>0L){
				sb.append(" ( petitionReffererCandidate.petitionDesignationId =:searchValue OR model.petitionWorkDetails.petitionMember.petitionDesignationId =:searchValue) and ");
			}
			sb.append("  model.petitionWorkDetails.petitionMember.isDeleted ='N'  and  model.petitionWorkDetails.petitionMember.isExpired = 'N'  ");
			//sb.append(" and petitionReffererCandidate.isDeleted='N' ");
			if(startDate != null && endDate != null){
				sb.append(" and  date(model.petitionWorkDetails.petitionMember.insertedTime) between :startDate and :endDate "); 
			}
			
		}else if(searchType.equalsIgnoreCase("department")){
			sb.append(" select model.petitionWorkDetails.petitionMemberId,model.petitionWorkDetails.petitionMember.refCode," +
					" model.petitionWorkDetails.petitionMember.candidateName,model.petitionWorkDetails.petitionMember.mobileNo," +
					" model.petitionWorkDetails.petitionMember.age,petitionDesignation.petitionDesignationId" +
					",petitionDesignation.designationName,model.petitionWorkDetails.petitionMember.memberType,district.districtId,district.districtName," +
					" constituency.constituencyId,constituency.name," +
					" refdistrict.districtId,refdistrict.districtName," +
					" refconstituency.constituencyId,refconstituency.name, "+
					" petitionDesignation1.designationName " +//16
					" from PetitionSubWorkLocationDetails model " +
					" left join model.petitionWorkDetails.petitionMember.petitionDesignation petitionDesignation1 " +
					" left join model.petitionWorkDetails.petitionMember.locationAddress locationAddress " +
					" left join model.locationAddress locationAddress " +
					" left join locationAddress.state state " +
					" left join locationAddress.district district " +
					" left join locationAddress.constituency constituency " +
					" left join locationAddress.tehsil tehsil " +
					" left join locationAddress.localElectionBody localElectionBody " +
					" left join locationAddress.panchayat panchayat " +
					" left join model.petitionWorkDetails.petitionMember.petitionReffererCandidate petitionReffererCandidate " +
					" left join petitionReffererCandidate.petitionDesignation petitionDesignation " +
					" left join petitionReffererCandidate.locationAddress reflocationAddress1 " +
					" left join reflocationAddress1.district refdistrict " +
					" left join reflocationAddress1.constituency refconstituency ");
			sb.append(" where ");
			sb.append("  model.petitionWorkDetails.petitionMember.isDeleted ='N'  and" +
					"  model.petitionWorkDetails.petitionMember.isExpired = 'N' " );
			//sb.append(" and petitionReffererCandidate.isDeleted='N' ");	
			if(searchValue != null && !searchValue.isEmpty()){
				sb.append(" and model.petitionWorkDetails.petitionDepartmentId in (:searchValue) ");
			}
			if(searchLevelId != null && searchLevelId.longValue()>0L && searchLevelValue != null && searchLevelValue.longValue()>0l){
				if(searchLevelId.longValue() ==2L){
					sb.append(" and  model.locationAddress.stateId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==3L){
					sb.append(" and  model.locationAddress.districtId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==4L){
					sb.append(" and  model.locationAddress.constituencyId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==5L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.tehsilId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==7L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.localElectionBodyId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==6L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.panchayatId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==8L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.wardId=:searchLevelValue ");
				}
			}
			if(startDate != null && endDate != null){
				sb.append(" and date(model.insertedTime) between :startDate and :endDate "); 
			}
			
		}else if(searchType.equalsIgnoreCase("refLocation")){
			sb.append(" select model.petitionMemberId,model.petitionMember.refCode," +
					" model.petitionMember.candidateName,model.petitionMember.mobileNo," +
					" model.petitionMember.age,petitionDesignation.petitionDesignationId" +
					",petitionDesignation.designationName,model.petitionMember.memberType,district.districtId,district.districtName," +
					" constituency.constituencyId,constituency.name," +
					" refdistrict.districtId,refdistrict.districtName," +
					" refconstituency.constituencyId,refconstituency.name, " +
					" petitionDesignation1.designationName " +
					" from PetitionRefferer model , PetitionSubWorkLocationDetails model1 " +
					" left join model.petitionMember.petitionDesignation petitionDesignation1 " +
					" left join model1.locationAddress locationAddress " +
					" left join locationAddress.state state " +
					" left join locationAddress.district district " +
					" left join locationAddress.constituency constituency " +
					" left join locationAddress.tehsil tehsil " +
					" left join locationAddress.localElectionBody localElectionBody " +
					" left join locationAddress.panchayat panchayat " +
					" left join model.petitionReffererCandidate petitionReffererCandidate " +
					" left join petitionReffererCandidate.petitionDesignation petitionDesignation " +
					" left join petitionReffererCandidate.locationAddress reflocationAddress1 " +
					" left join reflocationAddress1.district refdistrict " +
					" left join reflocationAddress1.constituency refconstituency ");
			sb.append(" where model.petitionMemberId = model1.petitionWorkDetails.petitionMemberId and ");
			sb.append("  model.petitionMember.isDeleted ='N'  and model.petitionMember.isExpired = 'N' " );
			//sb.append(" and petitionReffererCandidate.isDeleted='N' ");		
			if(searchLevelId != null && searchLevelId.longValue()>0L && searchLevelValue != null && searchLevelValue.longValue()>0l){
				if(searchLevelId.longValue() ==2L){
					sb.append(" and  model.petitionReffererCandidate.locationAddress.stateId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==3L){
					sb.append(" and  model.petitionReffererCandidate.locationAddress.districtId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==4L){
					sb.append(" and  model.petitionReffererCandidate.locationAddress.constituencyId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==5L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.petitionReffererCandidate.locationAddress.tehsilId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==7L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.petitionReffererCandidate.locationAddress.localElectionBodyId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==6L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.petitionReffererCandidate.locationAddress.panchayatId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==8L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.petitionReffererCandidate.locationAddress.wardId=:searchLevelValue ");
				}
			}
			if(startDate != null && endDate != null){
				sb.append(" and date(model.insertedTime) between :startDate and :endDate "); 
			}
		}else if(searchType.equalsIgnoreCase("workLocation")){
			sb.append(" select model.petitionWorkDetails.petitionMemberId,model.petitionWorkDetails.petitionMember.refCode," +
					" model.petitionWorkDetails.petitionMember.candidateName,model.petitionWorkDetails.petitionMember.mobileNo," +
					" model.petitionWorkDetails.petitionMember.age,petitionDesignation.petitionDesignationId" +
					",petitionDesignation.designationName,model.petitionWorkDetails.petitionMember.memberType,district.districtId,district.districtName," +
					" constituency.constituencyId,constituency.name," +
					" refdistrict.districtId,refdistrict.districtName," +
					" refconstituency.constituencyId,refconstituency.name, " +
					" petitionDesignation1.designationName " +
					" from PetitionSubWorkLocationDetails model " +
					" left join model.petitionWorkDetails.petitionMember.petitionDesignation petitionDesignation1 " +
					" left join model.locationAddress locationAddress " +
					" left join locationAddress.state state " +
					" left join locationAddress.district district " +
					" left join locationAddress.constituency constituency " +
					" left join locationAddress.tehsil tehsil " +
					" left join locationAddress.localElectionBody localElectionBody " +
					" left join locationAddress.panchayat panchayat " +
					" left join model.petitionWorkDetails.petitionMember.petitionReffererCandidate petitionReffererCandidate " +
					" left join petitionReffererCandidate.petitionDesignation petitionDesignation " +
					" left join petitionReffererCandidate.locationAddress reflocationAddress1 " +
					" left join reflocationAddress1.district refdistrict " +
					" left join reflocationAddress1.constituency refconstituency ");
			sb.append(" where ");
			sb.append("  model.petitionWorkDetails.petitionMember.isDeleted ='N' " +
					" and model.petitionWorkDetails.petitionMember.isExpired = 'N' " );
			//sb.append("   and petitionReffererCandidate.isDeleted='N' ");		
			if(searchLevelId != null && searchLevelId.longValue()>0L && searchLevelValue != null && searchLevelValue.longValue()>0l){
				if(searchLevelId.longValue() ==2L){
					sb.append(" and  model.locationAddress.stateId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==3L){
					sb.append(" and  model.locationAddress.districtId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==4L){
					sb.append(" and  model.locationAddress.constituencyId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==5L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.tehsilId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==7L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.localElectionBodyId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==6L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.panchayatId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==8L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.wardId=:searchLevelValue ");
				}
			}
			if(startDate != null && endDate != null){
				sb.append(" and date(model.insertedTime) between :startDate and :endDate "); 
			}
			
		}
		
			if(searchType != null && (searchType.equalsIgnoreCase("name") || searchType.equalsIgnoreCase("mobileNo") || searchType.equalsIgnoreCase("emailId")
					|| searchType.equalsIgnoreCase("refCode") || searchType.equalsIgnoreCase("designation"))
					&& searchLevelId != null && searchLevelId.longValue()>0L && searchLevelValue != null && searchLevelValue.longValue()>0l){
				if(searchLevelId.longValue() ==2L){
					sb.append(" and  state.stateId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==3L){
					sb.append(" and  district.districtId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==4L){
					sb.append(" and constituency.constituencyId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==5L){
					searchValue = searchValue.substring(1);
					sb.append(" and  tehsil.tehsilId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==7L){
					searchValue = searchValue.substring(1);
					sb.append(" and  localElectionBody.localElectionBodyId=:searchLevelValue ");
				}else if(searchLevelId.longValue() ==6L){
					searchValue = searchValue.substring(1);
					sb.append(" and  panchayat.panchayatId=:searchLevelValue ");
				}/*else if(searchLevelId.longValue() ==8L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.wardId=:searchLevelValue ");
				}*/
			}
		
		qry = getSession().createQuery(sb.toString());			
		if(searchValue != null && !searchValue.isEmpty() && (searchType.equalsIgnoreCase("name") || searchType.equalsIgnoreCase("emailId") 
				 || searchType.equalsIgnoreCase("mobileNo")  || searchType.equalsIgnoreCase("refCode") )){
			//qry.setParameter("searchValue", searchValue);
		}else if(searchValue != null && !searchValue.isEmpty()  && Long.valueOf(searchValue)>0L){
			qry.setParameter("searchValue", Long.valueOf(searchValue));
		}
		if(startDate != null && endDate != null){
			qry.setParameter("startDate", startDate);
			qry.setParameter("endDate", endDate);
		}
		if(searchLevelId != null && searchLevelId.longValue()>0L && searchLevelValue != null && searchLevelValue.longValue()>0l){
			qry.setParameter("searchLevelValue", Long.valueOf(searchLevelValue));
		}
		return qry.list();
	}
	
}
