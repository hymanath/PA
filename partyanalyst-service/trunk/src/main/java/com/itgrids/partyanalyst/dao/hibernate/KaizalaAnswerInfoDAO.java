package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IKaizalaAnswerInfoDAO;
import com.itgrids.partyanalyst.model.KaizalaAnswerInfo;

public class KaizalaAnswerInfoDAO extends GenericDaoHibernate<KaizalaAnswerInfo, Long> implements IKaizalaAnswerInfoDAO {
	public KaizalaAnswerInfoDAO() {
		super(KaizalaAnswerInfo.class); 
	}
	public List<Object[]> getConstituencyWiseTargetList(Long stateId,String location){
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			sb.append(" a.constituency_id as constituencyId, ");
		}
		sb.append(" a.parliament_id as palriamentId, ");
		sb.append(" count(m.kaizala_member_id) as count ");
		sb.append(" from ");
		sb.append(" kzl_kaizala_committee_member m,kaizala_location_address a ");
		sb.append(" where ");
		sb.append(" m.kaizala_location_address_id = a.kaizala_location_address_id and ");
		sb.append(" a.state_id = :stateId and ");
		sb.append(" m.is_deleted = 'N' ");
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			sb.append(" group by a.constituency_id,a.parliament_id ");
		}else{
			sb.append(" group by a.parliament_id ");
		}
		
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			query.addScalar("constituencyId", Hibernate.LONG).addScalar("palriamentId", Hibernate.LONG).addScalar("count", Hibernate.LONG);
		}else{
			query.addScalar("palriamentId", Hibernate.LONG).addScalar("count", Hibernate.LONG);
		}
		query.setParameter("stateId", stateId);
		return query.list();
	}
	public List<Object[]> getConstituencyWiseInstalledCommitteeMembers(Long stateId,String location){
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			sb.append(" a.constituency_id as constituencyId, ");
		}
		sb.append(" a.parliament_id as palriamentId, ");
		sb.append(" count(m.kaizala_member_id) as count ");
		sb.append(" from ");
		sb.append(" kzl_kaizala_committee_member m,kaizala_location_address a,kzl_kaizala_member km ");
		sb.append(" where ");
		sb.append(" m.kaizala_location_address_id = a.kaizala_location_address_id and  ");
		sb.append(" a.state_id = :stateId and ");
		sb.append(" m.is_deleted = 'N' and ");
		sb.append(" m.kaizala_member_id = km.kaizala_member_id and  ");
		sb.append(" km.is_installed='Y' and  ");
		sb.append(" km.is_deleted='N'  ");
		
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			sb.append(" group by a.constituency_id,a.parliament_id ");
		}else{
			sb.append(" group by a.parliament_id ");
		}
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			query.addScalar("constituencyId", Hibernate.LONG).addScalar("palriamentId", Hibernate.LONG).addScalar("count", Hibernate.LONG);
		}else{
			query.addScalar("palriamentId", Hibernate.LONG).addScalar("count", Hibernate.LONG);
		}
		query.setParameter("stateId", stateId);
		return query.list();
	}
	public List<Object[]> getConstituencyWiseNotHavingSmartPhone(Long stateId,String location){
		StringBuilder sb = new StringBuilder();
		sb.append(" select   ");
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			sb.append(" a.constituency_id as constituencyId, ");
		}
		sb.append(" a.parliament_id as palriamentId, ");
		sb.append(" count(m.kaizala_member_id) as count  ");
		sb.append(" from ");
		sb.append(" kzl_kaizala_committee_member m,kaizala_location_address a,kzl_kaizala_member km ");
		sb.append(" where ");
		sb.append(" m.kaizala_location_address_id = a.kaizala_location_address_id and ");
		sb.append(" a.state_id = :stateId and ");
		sb.append(" m.is_deleted = 'N' and ");
		sb.append(" m.kaizala_member_id = km.kaizala_member_id and ");
		sb.append(" km.is_deleted='N' and ");
		sb.append(" km.is_smart_phone='No' ");
		
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			sb.append(" group by a.constituency_id,a.parliament_id ");
		}else{
			sb.append(" group by a.parliament_id ");
		}
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			query.addScalar("constituencyId", Hibernate.LONG).addScalar("palriamentId", Hibernate.LONG).addScalar("count", Hibernate.LONG);
		}else{
			query.addScalar("palriamentId", Hibernate.LONG).addScalar("count", Hibernate.LONG);
		}
		query.setParameter("stateId", stateId);
		return query.list();
	}
	public List<Object[]> getConstituencyWisePublicInstalled(Long stateId,String location){
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			sb.append(" A.constituency_id as constituencyId, ");
		}
		sb.append(" A.parliament_id as palriamentId, ");
		sb.append(" count(distinct installed_mobile_no) as count ");
		sb.append(" from ");
		sb.append(" kaizala_installation_tracking T,kaizala_installation_verification V,kaizala_field_user U,kaizala_field_user_location L,kaizala_location_address A ");
		sb.append(" where ");
		sb.append(" T.kaizala_installation_tracking_id = V.kaizala_installation_tracking_id and ");
		sb.append(" V.is_installed='Y' and ");
		sb.append(" T.kaizala_field_user_id = U.kaizala_field_user_id   and ");
		sb.append(" U.is_active='Y' and ");
		sb.append(" U.kaizala_field_user_id = L.kaizala_field_user_id and ");
		sb.append(" L.is_deleted='N' and ");
		sb.append(" T.is_committee_member is null and ");
		sb.append(" T.membership_id is   null and ");
		sb.append(" A.kaizala_location_address_id  = L.kaizala_location_address_id and ");
		sb.append(" A.state_id=:stateId ");
		
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			sb.append(" group by A.constituency_id,A.parliament_id ");
		}else{
			sb.append(" group by A.parliament_id ");
		}
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			query.addScalar("constituencyId", Hibernate.LONG).addScalar("palriamentId", Hibernate.LONG).addScalar("count", Hibernate.LONG);
		}else{
			query.addScalar("palriamentId", Hibernate.LONG).addScalar("count", Hibernate.LONG);
		}
		query.setParameter("stateId", stateId);
		return query.list();
	}
	public List<Object[]> getConstituecnyWiseCadreInstalled(Long stateId,String location){
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			sb.append(" A.constituency_id  as constituencyId, ");
		}
		sb.append(" A.parliament_id as palriamentId, ");
		sb.append(" count(distinct installed_mobile_no) as count ");
		sb.append(" from ");
		sb.append(" kaizala_installation_tracking T,kaizala_installation_verification V,kaizala_field_user U,kaizala_field_user_location L,kaizala_location_address A ");
		sb.append(" where ");
		sb.append(" T.kaizala_installation_tracking_id = V.kaizala_installation_tracking_id and ");
		sb.append(" V.is_installed='Y' and ");
		sb.append(" T.kaizala_field_user_id = U.kaizala_field_user_id and ");
		sb.append(" U.is_active='Y' and ");
		sb.append(" U.kaizala_field_user_id = L.kaizala_field_user_id and ");
		sb.append(" L.is_deleted='N' and ");
		sb.append(" L.is_active='Y' and ");
		sb.append(" T.is_committee_member='N' and ");
		sb.append(" T.membership_id is not  null and ");
		sb.append(" A.kaizala_location_address_id  = L.kaizala_location_address_id and ");
		sb.append(" A.state_id=:stateId ");
		
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			sb.append(" group by A.constituency_id,A.parliament_id ");
		}else{
			sb.append(" group by A.parliament_id ");
		}
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		if(location != null && location.trim().equalsIgnoreCase("constituency")){
			query.addScalar("constituencyId", Hibernate.LONG).addScalar("palriamentId", Hibernate.LONG).addScalar("count", Hibernate.LONG);
		}else{
			query.addScalar("palriamentId", Hibernate.LONG).addScalar("count", Hibernate.LONG);
		}
		query.setParameter("stateId", stateId);
		return query.list();
	}
	public Long getTotalTarget(Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(m.kaizala_member_id) as count from kzl_kaizala_committee_member m,kaizala_location_address a ");
		sb.append(" where m.kaizala_location_address_id = a.kaizala_location_address_id and a.state_id = :stateId and m.is_deleted = 'N' ");
		SQLQuery query = getSession().createSQLQuery(sb.toString()).addScalar("count", Hibernate.LONG);
		query.setParameter("stateId", stateId);
		return (Long) query.uniqueResult();
	}
	public Long getTotalInstalled(Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select  count(m.kaizala_member_id) as count from kzl_kaizala_committee_member m,kaizala_location_address a,kzl_kaizala_member km ");
		sb.append(" where m.kaizala_location_address_id = a.kaizala_location_address_id and a.state_id = :stateId and m.is_deleted = 'N' and ");
		sb.append(" m.kaizala_member_id = km.kaizala_member_id and km.is_installed='Y' and km.is_deleted='N' ");
		SQLQuery query = getSession().createSQLQuery(sb.toString()).addScalar("count", Hibernate.LONG);
		query.setParameter("stateId", stateId);
		return (Long) query.uniqueResult();
	}
	public Long getNotHavingSmartPhone(Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(m.kaizala_member_id) as count from kzl_kaizala_committee_member m,kaizala_location_address a,kzl_kaizala_member km ");
		sb.append(" where m.kaizala_location_address_id = a.kaizala_location_address_id and a.state_id = :stateId and m.is_deleted = 'N' and ");
		sb.append(" m.kaizala_member_id = km.kaizala_member_id and  km.is_deleted='N' and km.is_smart_phone='No' ");
		SQLQuery query = getSession().createSQLQuery(sb.toString()).addScalar("count", Hibernate.LONG);
		query.setParameter("stateId", stateId);
		return (Long) query.uniqueResult();
	}
}