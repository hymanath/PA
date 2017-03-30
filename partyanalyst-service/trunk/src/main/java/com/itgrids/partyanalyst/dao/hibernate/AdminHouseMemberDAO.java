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
		Query query = getSession().createQuery("select model.adminHouseMemberId," +
				" model.memberName " +
				" from AdminHouseMember model" +
				" where model.party.partyId = :partyId and model.isDeleted = 'N' ");
		query.setParameter("partyId", partyId);
		return query.list();
	}
}
