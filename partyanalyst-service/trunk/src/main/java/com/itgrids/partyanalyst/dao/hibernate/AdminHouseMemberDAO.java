package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAdminHouseMemberDAO;
import com.itgrids.partyanalyst.model.AdminHouseMember;

public class AdminHouseMemberDAO extends GenericDaoHibernate<AdminHouseMember, Long> implements IAdminHouseMemberDAO{

	public AdminHouseMemberDAO() {
		super(AdminHouseMember.class);
		
	}
	public List<Object[]> getcandateNameForPartyId(Long partyId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select model.adminHouseMemberId," +
				" model.memberName " +
				" from AdminHouseMember model" +
				" where  model.isDeleted = 'N' ");
		if(partyId != null && partyId.longValue() > 0l){
			sb.append(" and model.party.partyId = :partyId " );
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(partyId != null && partyId.longValue() > 0l){
			query.setParameter("partyId", partyId);
		}
		return query.list();
	}
	
	public List<Object[]> getAllPartyNames(){
		Query query = getSession().createQuery("select distinct model.party.partyId," +
				" model.party.shortName " +
				" from AdminHouseMember model  ");
		return query.list();
	}
}
