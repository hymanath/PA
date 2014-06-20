package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdMemberDAO;
import com.itgrids.partyanalyst.model.TdMember;

public class TdMemberDAO extends GenericDaoHibernate<TdMember, Long> implements ITdMemberDAO {
	
	public TdMemberDAO() {
		super(TdMember.class);
	}
	
	
	public List<Object[]> getMembersDetailsBypanchayatId(Long panchayatId)
	{
		Query query = getSession().createQuery("select " +	
				"TM.nMemberId , " +
				"TM.sMemberName , " +
				"TM.nRelationType , " +
				"TM.sRelationName , " +
				"TM.sGender , " +
				"TM.dateofBirth  " +
				"from TdMember TM " +
				"where " +
				"TM.panchayat.panchayatId = :panchayatId  order by TM.nMemberId" );
		
		query.setParameter("panchayatId", panchayatId);
		
		return query.list();
		
		
	}
	
}
