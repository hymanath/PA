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
	
	public List<Object[]> getRepresentativeSearchDetailsBy(String searchType,String searchValue){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.petitionMemberId,model.refCode,model.candidateName,model.mobileNo,model.age from PetitionMember model ");
		sb.append(" where ");
		if(searchType != null && searchType.equalsIgnoreCase("name")){
			sb.append(" model.candidateName in(:searchValue) ");
		}else if(searchType.equalsIgnoreCase("mobileNo")){
			sb.append(" model.mobileNo in(:searchValue) ");
		}else if(searchType.equalsIgnoreCase("emailId")){
			sb.append(" model.emailId in(:searchValue) ");
		}else if(searchType.equalsIgnoreCase("refCode")){
			sb.append(" model.refCode in(:searchValue) ");
		}
		sb.append(" and model.isDeleted ='N'  and model.isExpired = 'N' ");
		
		Query qry = getSession().createQuery(sb.toString());
		
		if(searchValue != null && !searchValue.isEmpty()){
			qry.setParameter("searchValue", searchValue);
		}
		return qry.list();
		
	
	}
	
}
