package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

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
	
	public List<String> getActiveOrInActiveInchageDetails(Long cadreId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select AIM.isActive from  AreaInchargeMember AIM " );
		if(cadreId != null && cadreId.longValue()>0l){
			sb.append(" where AIM.tdpCadre.tdpCadreId =:cadreId "); 
		}
		Query query = getSession().createQuery(sb.toString());
		if(cadreId != null && cadreId.longValue()>0l){
			query.setParameter("cadreId", cadreId);
		}
		return query.list();
		
	}
	
	public List<Object[]> getAssignedCadreList(String status){
		StringBuilder sb = new StringBuilder();
		sb.append(" select AIM.areaInchargeLocationId,tdpCadre.tdpCadreId,tdpCadre.firstname,tdpCadre.houseNo " +
				"from  AreaInchargeMember AIM left join AIM.tdpCadre tdpCadre  where AIM.isDeleted ='N' " );
		if(status != null && !status.isEmpty()){
			sb.append(" and AIM.isActive =:status "); 
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
			sb.append("  and AIM.tdpCadre.tdpCadreId =:cadreId "); 
		}
		sb.append(" group by AIM.areaInchargeLocationId order by AIM.areaInchargeLocationId");
		Query query = getSession().createQuery(sb.toString());
		if(cadreId != null && cadreId.longValue()>0l){
			query.setParameter("cadreId", cadreId);
		}
		return query.list();
		
	}
	public int deleteAreaInchargeAssignBooths(Long candidateId,Long boothId){
		StringBuilder sb = new StringBuilder();
		sb.append(" update AreaInchargeMember model set model.isActive = 'N',model.isDeleted = 'Y',model.areaInchargeLocation.isAssinged ='N',model.areaInchargeLocation.isDeleted ='Y' ");
		if(candidateId != null && candidateId.longValue()>0l){
			sb.append(" where model.tdpCadreId =:candidateId ");
		}
		if(boothId != null && boothId.longValue()>0l && boothId != 0){
			sb.append("and model.areaInchargeLocationId =:boothId");
		}
		Query queObject = getSession().createQuery(sb.toString());
		if(candidateId != null && candidateId.longValue()>0l){
		  queObject.setParameter("candidateId",candidateId);
		}
		if(boothId != null && boothId.longValue()>0l && boothId != 0){
			queObject.setParameter("boothId",boothId);
			}
		return queObject.executeUpdate();
	}
	public List<Object[]> getAreaInchargeAssignedBoothDetails(Long levelId,Long levelValue){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct tdpCadre.tdpCadreId,tdpCadre.firstname,tdpCadre.relativename," +
				" tdpCadre.age,tdpCadre.gender,tdpCadre.memberShipNo,tdpCadre.mobileNo," +
				" AIM.areaInchargeLocationId,tdpCadre.image "+
				" from  AreaInchargeMember AIM left join AIM.tdpCadre tdpCadre  where AIM.isDeleted ='N' and AIM.isActive ='Y' " );
		if(levelId != null &&  levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			sb.append("  and AIM.areaInchargeLocation.address.district.districtId =:levelValue "); 
		}else if(levelId != null &&  levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
			sb.append("  and AIM.areaInchargeLocation.address.constituency.constituencyId =:levelValue "); 
		}
		//sb.append(" group by tdpCadre.tdpCadreId ");
		Query query = getSession().createQuery(sb.toString());
		if(levelId != null &&  levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			query.setParameter("levelValue", levelValue);
		}else if(levelId != null &&  levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
			query.setParameter("levelValue", levelValue);
		}
		return query.list();
		
	}
	public Long getInchargeMembers(Set<Long> assignIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(distinct AIM.tdpCadre.tdpCadreId )"+
				" from  AreaInchargeMember AIM   where AIM.isDeleted ='N' and AIM.isActive ='Y' " );
		if(assignIds != null && assignIds.size()>0){
			sb.append("  and AIM.areaInchargeLocationId in(:assignIds)"); 
		}
		//sb.append(" group by AIM.areaInchargeLocationId order by AIM.areaInchargeLocationId");
		Query query = getSession().createQuery(sb.toString());
		if(assignIds != null && assignIds.size()>0){
			query.setParameterList("assignIds", assignIds);
		}
		return (Long)query.uniqueResult();
		
	}
}
