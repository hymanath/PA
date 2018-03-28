package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAreaInchargeMemberDAO;
import com.itgrids.partyanalyst.model.AreaInchargeMember;

public class AreaInchargeMemberDAO extends GenericDaoHibernate<AreaInchargeMember, Long> implements IAreaInchargeMemberDAO{

	public AreaInchargeMemberDAO() {
		super(AreaInchargeMember.class);
		
	}
	
	public List<Object[]> getAreaInchargeDetails(Long voterId,String mobileNo,Long memberShipId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select tdpCadre.tdpCadreId, tdpCadre.firstname,tdpCadre.relativename," +
				" tdpCadre.age, tdpCadre.gender, tdpCadre.houseNo," +
				" address.tehsil.tehsilId,address.tehsil.tehsilName," +
				" tdpCadre.casteState.caste.casteName,tdpCadre.image,AIM.isActive " +
				" from  AreaInchargeMember AIM  left join AIM.tdpCadre tdpCadre" +
				" left join AIM.areaInchargeLocation areaInchargeLocation left join areaInchargeLocation.address address " +
				"  where AIM.isDeleted ='N' and tdpCadre.isDeleted ='N' ");
		if(voterId != null && voterId.longValue()>0l){
			sb.append(" and tdpCadre.voterId =:voterId  ");	
		}
        if(mobileNo != null && !mobileNo.isEmpty()){
        	sb.append(" and tdpCadre.mobileNo =:mobileNo ");	
        }
        if(memberShipId != null && memberShipId.longValue()>0l){
        	sb.append(" and tdpCadre.memberShipNo =:memberShipId ");	
        }
        Query query = getSession().createQuery(sb.toString());
        if(voterId != null && voterId.longValue()>0l){
        	query.setParameter("voterId", voterId);
		}
        if(mobileNo != null && !mobileNo.isEmpty()){
        	query.setParameter("mobileNo", mobileNo);
        }
        if(memberShipId != null && memberShipId.longValue()>0l){
        	query.setParameter("memberShipId", memberShipId);
        }
		return query.list();
		
	}
	
	public String getActiveOrInActiveInchageDetails(Long cadreId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select AIM.isActive from  AreaInchargeMember AIM  where AIM.isDeleted ='N' " );
		if(cadreId != null && cadreId.longValue()>0l){
			sb.append(" and AIM.tdpCadre.tdpCadreId =:cadreId "); 
		}
		Query query = getSession().createQuery(sb.toString());
		if(cadreId != null && cadreId.longValue()>0l){
			query.setParameter("cadreId", cadreId);
		}
		return (String)query.uniqueResult();
		
	}
	
	public List<Object[]> getAssignedCadreList(String status){
		StringBuilder sb = new StringBuilder();
		sb.append(" select AIM.areaInchargeLocationId,tdpCadre.tdpCadreId,tdpCadre.firstname,tdpCadre.houseNo " +
				"from  AreaInchargeMember AIM left join AIM.tdpCadre tdpCadre  where AIM.isDeleted ='N' " );
		if(status != null && !status.isEmpty()){
			sb.append(" AIM.isActive =:status "); 
		}
		Query query = getSession().createQuery(sb.toString());
		if(status != null && !status.isEmpty()){
			query.setParameter("status", status);
		}
		return query.list();
	}
	//0-areaInchargeLoc,1-cadreId,2-cadreName,3-house
	public List<Long> getAssignedInchargeBooths(Long cadreId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct AIM.areaInchargeLocationId "+
				" from  AreaInchargeMember AIM   where AIM.isDeleted ='N' and AIM.isActive ='Y' " );
		if(cadreId != null && cadreId.longValue()>0l){
			sb.append(" AIM.tdpCadre.tdpCadreId =:cadreId "); 
		}
		sb.append(" group by AIM.areaInchargeLocationId order by AIM.areaInchargeLocationId");
		Query query = getSession().createQuery(sb.toString());
		if(cadreId != null && cadreId.longValue()>0l){
			query.setParameter("cadreId", cadreId);
		}
		return query.list();
		
	}
}
