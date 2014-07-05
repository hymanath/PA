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
				"TM.dateofBirth  ," +
				"TM.panchayat.panchayatName  " +
				"from TdMember TM " +
				"where " +
				"TM.panchayat.panchayatId = :panchayatId  order by TM.nMemberId" );
		
		query.setParameter("panchayatId", panchayatId);
		
		return query.list();
		
	}
	
	public List<Object[]> getUrbanMembersDetails(Long constituencyId)
	{
		Query query = getSession().createQuery("select " +	
				"TM.nMemberId , " +
				"TM.sMemberName , " +
				"TM.nRelationType , " +
				"TM.sRelationName , " +
				"TM.sGender , " +
				"TM.dateofBirth  " +
				//"TM.panchayat.panchayatName  " +
				"from TdMember TM " +
				"where " +
				"TM.assemblyIdTemp = :constituencyId  and TM.panchayat is null order by TM.nMemberId" );
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	public List<Object[]> getMembersDetailsByBooth(String boothNo,Long constId,int inttial,int max)
	{
		
		
		Query query = getSession().createQuery("select " +	
		        "TM.areaInchargesId , "+
				"TM.voterIdCardNo , " +
				"TM.candidateName , " +
				"TM.surName , " +
				"TM.ownBooth , " +
				"TM.mobileNo , " +
				"TM.age , " +
				"TM.boothId  " +
				"from AreaInchargesTemp  TM " +
				"where " +
				"TM.constituencyId = :constituencyId  group  by TM.areaInchargesId " );//and TM.ownBooth=:boothNo
		
		query.setParameter("constituencyId", constId);
		//query.setParameter("boothNo", boothNo);
		//query.setFirstResult(inttial);
		//query.setMaxResults(max);

		
		return query.list();
		
		
	}
	
	public List<Object[]> getPanchayatsDetailsByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct TM.panchayat.panchayatId,TM.panchayat.panchayatName from TdMember TM " +
				"where TM.assemblyIdTemp = :constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
		
	}
	
}
