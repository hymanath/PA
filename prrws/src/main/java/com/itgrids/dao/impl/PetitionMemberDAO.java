package com.itgrids.dao.impl;

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
	
	public List<Object[]> getRepresentativeSearchDetailsBy(String searchType,String searchValue,Long searchLevelId,Long searchLevelValue){
		
		StringBuilder sb = new StringBuilder();
		Query qry = null;
		if(searchType != null && searchType.equalsIgnoreCase("name")){
			sb.append(" select model.petitionMemberId,model.refCode,model.candidateName,model.mobileNo,model.age from PetitionMember model ");
			sb.append(" where ");
			sb.append(" model.candidateName like '%"+searchValue+"%' and ");
			sb.append("  model.isDeleted ='N'  and model.isExpired = 'N' ");			
			qry = getSession().createQuery(sb.toString());
		}else if(searchType.equalsIgnoreCase("mobileNo")){
			sb.append(" select model.petitionMemberId,model.refCode,model.candidateName,model.mobileNo,model.age from PetitionMember model ");
			sb.append(" where ");
			sb.append(" model.mobileNo in (:searchValue) and  ");
			sb.append("  model.isDeleted ='N'  and model.isExpired = 'N' ");			
			qry = getSession().createQuery(sb.toString());			
			if(searchValue != null && !searchValue.isEmpty()){
				qry.setParameter("searchValue", searchValue);
			}
		}else if(searchType.equalsIgnoreCase("emailId")){
			sb.append(" select model.petitionMemberId,model.refCode,model.candidateName,model.mobileNo,model.age from PetitionMember model ");
			sb.append(" where ");
			sb.append(" model.emailId like '%"+searchValue+"%'  and ");
			sb.append("  model.isDeleted ='N'  and model.isExpired = 'N' ");			
			qry = getSession().createQuery(sb.toString());
		}else if(searchType.equalsIgnoreCase("refCode")){
			sb.append(" select model.petitionMemberId,model.refCode,model.candidateName,model.mobileNo,model.age from PetitionMember model ");
			sb.append(" where ");
			sb.append(" model.petitionMemberId in (:searchValue)  and ");
			sb.append("  model.isDeleted ='N'  and model.isExpired = 'N' ");			
			qry = getSession().createQuery(sb.toString());			
			if(searchValue != null && !searchValue.isEmpty()){
				qry.setParameter("searchValue", Long.valueOf(searchValue));
			}
		}else if(searchType.equalsIgnoreCase("designation")){
			sb.append(" select model.petitionMemberId,model.petitionMember.refCode," +
					" model.petitionMember.candidateName,model.petitionMember.mobileNo," +
					" model.petitionMember.age from PetitionRefferer model ");
			sb.append(" where ");
			if(Long.valueOf(searchValue) >0L)
				sb.append(" model.petitionReffererCandidate.petitionDesignationId in (:searchValue)  and ");
			sb.append("  model.petitionMember.isDeleted ='N'  and model.petitionMember.isExpired = 'N' ");	
			
			qry = getSession().createQuery(sb.toString());			
			if(searchValue != null && !searchValue.isEmpty() && Long.valueOf(searchValue)>0L){
				qry.setParameter("searchValue", Long.valueOf(searchValue));
			}
		}else if(searchType.equalsIgnoreCase("department")){
			sb.append(" select model.petitionWorkDetails.petitionMemberId,model.petitionWorkDetails.petitionMember.refCode," +
					" model.petitionWorkDetails.petitionMember.candidateName,model.petitionWorkDetails.petitionMember.mobileNo," +
					" model.petitionWorkDetails.petitionMember.age from PetitionSubWorkLocationDetails model ");
			sb.append(" where ");
			sb.append("  model.petitionWorkDetails.petitionMember.isDeleted ='N'  and model.petitionWorkDetails.petitionMember.isExpired = 'N' ");	
			if(Long.valueOf(searchValue)>0L)
			sb.append(" and model.petitionWorkDetails.petitionDepartmentId in (:searchValue) ");
			qry = getSession().createQuery(sb.toString());			
			if(searchValue != null && !searchValue.isEmpty() && Long.valueOf(searchValue)>0L){
				qry.setParameter("searchValue", Long.valueOf(searchValue));
			}
		}else if(searchType.equalsIgnoreCase("refLocation")){
			sb.append(" select model.petitionMemberId,model.petitionMember.refCode," +
					" model.petitionMember.candidateName,model.petitionMember.mobileNo," +
					" model.petitionMember.age from PetitionRefferer model ");
			sb.append(" where ");
			sb.append("  model.petitionMember.isDeleted ='N'  and model.petitionMember.isExpired = 'N' ");		
			if(searchLevelId != null && searchLevelId.longValue()>0L){
				if(searchLevelId.longValue() ==2L){
					sb.append(" and  model.petitionReffererCandidate.locationAddress.stateId=:searchValue ");
				}else if(searchLevelId.longValue() ==3L){
					sb.append(" and  model.petitionReffererCandidate.locationAddress.districtId=:searchValue ");
				}else if(searchLevelId.longValue() ==4L){
					sb.append(" and  model.petitionReffererCandidate.locationAddress.constituencyId=:searchValue ");
				}else if(searchLevelId.longValue() ==5L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.petitionReffererCandidate.locationAddress.tehsilId=:searchValue ");
				}else if(searchLevelId.longValue() ==7L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.petitionReffererCandidate.locationAddress.localElectionBodyId=:searchValue ");
				}else if(searchLevelId.longValue() ==6L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.petitionReffererCandidate.locationAddress.panchayatId=:searchValue ");
				}else if(searchLevelId.longValue() ==8L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.petitionReffererCandidate.locationAddress.wardId=:searchValue ");
				}
			}
			qry = getSession().createQuery(sb.toString());			
			if(searchValue != null && !searchValue.isEmpty()){
				qry.setParameter("searchValue", Long.valueOf(searchValue));
			}
		}else if(searchType.equalsIgnoreCase("workLocation")){
			sb.append(" select model.petitionWorkDetails.petitionMemberId,model.petitionWorkDetails.petitionMember.refCode," +
					" model.petitionWorkDetails.petitionMember.candidateName,model.petitionWorkDetails.petitionMember.mobileNo," +
					" model.petitionWorkDetails.petitionMember.age from PetitionSubWorkLocationDetails model ");
			sb.append(" where ");
			sb.append("  model.petitionWorkDetails.petitionMember.isDeleted ='N'  and model.petitionWorkDetails.petitionMember.isExpired = 'N' ");		
			if(searchLevelId != null && searchLevelId.longValue()>0L){
				if(searchLevelId.longValue() ==2L){
					sb.append(" and  model.locationAddress.stateId=:searchValue ");
				}else if(searchLevelId.longValue() ==3L){
					sb.append(" and  model.locationAddress.districtId=:searchValue ");
				}else if(searchLevelId.longValue() ==4L){
					sb.append(" and  model.locationAddress.constituencyId=:searchValue ");
				}else if(searchLevelId.longValue() ==5L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.tehsilId=:searchValue ");
				}else if(searchLevelId.longValue() ==7L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.localElectionBodyId=:searchValue ");
				}else if(searchLevelId.longValue() ==6L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.panchayatId=:searchValue ");
				}else if(searchLevelId.longValue() ==8L){
					searchValue = searchValue.substring(1);
					sb.append(" and  model.locationAddress.wardId=:searchValue ");
				}
			}
			qry = getSession().createQuery(sb.toString());			
			if(searchValue != null && !searchValue.isEmpty()){
				qry.setParameter("searchValue", Long.valueOf(searchValue));
			}
		}
		
		return qry.list();
	}
	
}
