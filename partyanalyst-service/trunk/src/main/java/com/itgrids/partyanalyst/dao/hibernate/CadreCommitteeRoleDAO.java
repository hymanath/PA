package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreCommitteeRoleDAO;
import com.itgrids.partyanalyst.model.CadreCommitteeRole;

public class CadreCommitteeRoleDAO extends GenericDaoHibernate<CadreCommitteeRole, Long> implements ICadreCommitteeRoleDAO{

	public CadreCommitteeRoleDAO() 
	{
		super(CadreCommitteeRole.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<Long> getCadreCommitteRoleIdBySelection(Long cadreCommitteeLevelId,Long cadreRoleId,Long cadreCommiteeId)
	{
		Query query = getSession().createQuery("select model.cadreCommitteeRoleId from CadreCommitteeRole model where model.cadreCommitteeLevelId =:cadreCommitteeLevelId and model.cadreCommiteeId =:cadreCommiteeId and model.cadreRolesId = :cadreRoleId");
		query.setParameter("cadreCommitteeLevelId", cadreCommitteeLevelId);
		query.setParameter("cadreRoleId", cadreRoleId);
		query.setParameter("cadreCommiteeId", cadreCommiteeId);
		return query.list();
	}
	
	public List<Object[]> getCommitteeRolesByLevelId(Long cadreCommitteeLevelId)
	{
		Query query = getSession().createQuery("select DISTINCT model.cadreCommittee.cadreCommitteeId, model.cadreCommittee.name from CadreCommitteeRole model " +
				" where model.cadreCommitteeLevelId = :cadreCommitteeLevelId order by model.cadreCommittee.name ");
		query.setParameter("cadreCommitteeLevelId", cadreCommitteeLevelId);
		return query.list();
		
	}
	
	public List<Object[]> getCommitteeRolesByLevelIdAndCommitteeId(Long cadreCommitteeLevelId,Long cadreCommiteeId)
	{
		Query query = getSession().createQuery("select DISTINCT  model.cadreRoles.cadreRolesId,model.cadreRoles.role  from CadreCommitteeRole model " +
				" where model.cadreCommitteeLevelId = :cadreCommitteeLevelId and model.cadreCommittee.cadreCommitteeId = :cadreCommiteeId order by model.cadreRoles.role ");
		
		query.setParameter("cadreCommitteeLevelId", cadreCommitteeLevelId);
		query.setParameter("cadreCommiteeId", cadreCommiteeId);
		return query.list();
	}
	
	
	public List<Object[]> getPartyCommitteeLeadersDeatails(Long stateId,List<Long> enrollmentIdsList,List<Long>  committeeLevelIdsList,List<Long> committeeTypeIdsLsit,
			 Long levelId, List<Long> locationIdsList,List<Long> designationIds1List,int firstIndex,int maxIndex,String fetchTypeStr){
		
		List<Long> validIds = new ArrayList<Long>(0);
		if(designationIds1List !=null && designationIds1List.size()>0){
			for (Long id : designationIds1List) {
				if(id.toString().substring(0, 1).equalsIgnoreCase("1"))
					validIds.add(Long.valueOf(id.toString().substring(1)));
			}
			if(validIds == null || validIds.size()==0){
					return null;
			}
		}
		
		
		Set<Long> urbanIdsList = new HashSet<Long>();
		Set<Long> ruralIdsList = new HashSet<Long>();
		if(locationIdsList != null && locationIdsList.size()>0){
			if(levelId.longValue() == 5L || levelId.longValue() == 7L){
				for (Long id : locationIdsList) {
					if(Long.valueOf(id.toString().substring(0,1)) == 2L)
						ruralIdsList.add(Long.valueOf(id.toString().substring(1)));
					if(Long.valueOf(id.toString().substring(0,1)) == 1L)
						urbanIdsList.add(Long.valueOf(id.toString().substring(1)));
				}
			}
			else if(levelId.longValue() == 6L || levelId.longValue() == 8L ){
				for (Long id : locationIdsList) {
					if(Long.valueOf(id.toString().substring(0,1)) == 2L)
						urbanIdsList.add(Long.valueOf(id.toString().substring(1)));
					if(Long.valueOf(id.toString().substring(0,1)) == 1L)
						ruralIdsList.add(Long.valueOf(id.toString().substring(1)));
				}
			}
		}
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("  SELECT   ");
		if(fetchTypeStr != null && fetchTypeStr.equalsIgnoreCase("count")){
			queryStr.append("  count(distinct tc1.tdp_cadre_id) as count  , '' as empty ");
		}
		else{
			queryStr.append("  distinct tc1.tdp_cadre_id as tdp_cadre_di,  ");
			queryStr.append("  tc1.membership_id as membership_id,   ");
			queryStr.append("  tc1.first_name as first_name ,  ");
			queryStr.append("  tc1.mobile_no as mobile_no ,   ");
			queryStr.append("  concat(tcl.tdp_committee_level,'-',tr.role) as position,  ");
			queryStr.append("  s.state_id as state_id ,  ");
			queryStr.append("  s.state_name as state_name ,  ");
			queryStr.append("  d.district_id as district_id ,  ");
			queryStr.append("  d.district_name as district_name,  ");
			queryStr.append("  pc.constituency_id as pId ,  ");
			queryStr.append("  pc.`name` as pName,  ");
			queryStr.append("  c.constituency_id as assemblyId,  ");
			queryStr.append("  c.`name` as assemblyName,  ");
			queryStr.append("  t.tehsil_id as tehsil_id,  ");
			queryStr.append("  t.tehsil_name as tehsil_name ,  ");
			queryStr.append("  l.local_election_body_id as local_election_body_id ,  ");
			queryStr.append("  l.`name` as lebName,  ");
			queryStr.append("  p.panchayat_id as panchayat_id,  ");
			queryStr.append("  p.panchayat_name as panchayat_name,  ");
			queryStr.append("  w.constituency_id as wardId,  ");
			queryStr.append("  w.`name` as wardName,  ");
			queryStr.append(" tc1.image as image, ");
			queryStr.append(" tbc.name as committeeName ");
		}
		
		queryStr.append("  from   ");
		queryStr.append("  tdp_committee tc,  ");
		queryStr.append("  tdp_basic_committee tbc, tdp_committee_level tcl , ");
		queryStr.append("  tdp_committee_role tcr,  ");
		queryStr.append("  tdp_committee_member tcm ,  ");
		queryStr.append("  tdp_roles tr,  ");
		queryStr.append("  tdp_cadre tc1,  ");
		queryStr.append("  tdp_cadre_enrollment_year tcey,  ");
		queryStr.append("  user_address ua   ");
		queryStr.append("  left outer join state s on ua.state_id = s.state_id  ");
		queryStr.append("  left outer join district d on ua.district_id = d.district_id   ");
		queryStr.append("  left outer join constituency c on ua.constituency_id = c.constituency_id   ");
		queryStr.append("  left outer join constituency pc on ua.parliament_constituency_id = pc.constituency_id  ");
		queryStr.append("  left outer join tehsil t on ua.tehsil_id = t.tehsil_id   ");
		queryStr.append("  left outer join local_election_body l on ua.local_election_body = l.local_election_body_id   ");
		queryStr.append("  left outer join panchayat  p on ua.panchayat_id = p.panchayat_id   ");
		queryStr.append("  left outer join constituency w on ua.ward = w.constituency_id  ");
		queryStr.append("  where   tc.tdp_committee_level_id = tcl.tdp_committee_level_id and ");
		queryStr.append("  tc.tdp_committee_id = tcr.tdp_committee_id and   ");
		queryStr.append("  tc.tdp_basic_committee_id = tbc.tdp_basic_committee_id AND  ");
		queryStr.append("  tcr.tdp_committee_role_id = tcm.tdp_committee_role_id and   ");
		queryStr.append("  tcr.tdp_roles_id = tr.tdp_roles_id and  ");
		queryStr.append("  tcm.is_active='Y' and ");
		queryStr.append("  tc.tdp_committee_enrollment_id = 2 AND  ");
		queryStr.append("  tcey.tdp_cadre_id = tc1.tdp_cadre_id and ");
		queryStr.append("  tcey.enrollment_year_id=4 and ");
		queryStr.append("  tcey.is_deleted='N' and ");
		queryStr.append("  tc1.is_deleted='N' and ");
		queryStr.append("  tc.address_id = ua.user_address_id  and ");
		queryStr.append("  tcm.tdp_cadre_id = tcey.tdp_cadre_id  ");
		
		if(levelId != null && levelId.longValue()>0L && locationIdsList != null && locationIdsList.size()>0){
			if(levelId.longValue() == 2L)
				queryStr.append(" and ua.state_id in (:locationIdsList)   ");
			else if(levelId.longValue() == 3L)
				queryStr.append(" and ua.district_id in (:locationIdsList)   "); 
			else if(levelId.longValue() == 4L)
				queryStr.append(" and ua.constituency_id in (:locationIdsList)   "); 
			else if(levelId.longValue() == 5L){
				queryStr.append(" and ( ");
				if(ruralIdsList != null && ruralIdsList.size()>0){
					queryStr.append("  ua.tehsil_id in (:ruralIdsList)   ");
					if(urbanIdsList != null && urbanIdsList.size()>0){
						queryStr.append(" or ua.local_election_body in (:urbanIdsList)   ");
					}
				}else if(urbanIdsList != null && urbanIdsList.size()>0){
					queryStr.append(" ua.local_election_body in (:urbanIdsList)   ");
				}
				queryStr.append(" ) ");
			}
			else if(levelId.longValue() == 6L){
				queryStr.append(" and ( ");
				if(ruralIdsList != null && ruralIdsList.size()>0){
					queryStr.append("  ua.panchayat_id in (:ruralIdsList)   ");
					if(urbanIdsList != null && urbanIdsList.size()>0){
						queryStr.append(" or ua.ward in (:urbanIdsList)   ");
					}
				}else if(urbanIdsList != null && urbanIdsList.size()>0){
					queryStr.append(" ua.ward in (:urbanIdsList)   ");
				}
				queryStr.append(" ) ");
			}
			else if(levelId.longValue() == 7L){
				queryStr.append(" and ( ");
				if(ruralIdsList != null && ruralIdsList.size()>0){
					queryStr.append("  ua.tehsil_id in (:ruralIdsList)   ");
					if(urbanIdsList != null && urbanIdsList.size()>0){
						queryStr.append(" or ua.local_election_body in (:urbanIdsList)   ");
					}
				}else if(urbanIdsList != null && urbanIdsList.size()>0){
					queryStr.append(" ua.local_election_body in (:urbanIdsList)   ");
				}
				queryStr.append(" ) ");
			}
			else if(levelId.longValue() == 8L){
				queryStr.append(" and ( ");
				if(ruralIdsList != null && ruralIdsList.size()>0){
					queryStr.append("  ua.panchayat_id in (:ruralIdsList)   ");
					if(urbanIdsList != null && urbanIdsList.size()>0){
						queryStr.append(" or ua.ward in (:urbanIdsList)   ");
					}
				}else if(urbanIdsList != null && urbanIdsList.size()>0){
					queryStr.append(" ua.ward in (:urbanIdsList)   ");
				}
				queryStr.append(" ) ");
			}
		}
		
		
		if(committeeTypeIdsLsit != null && committeeTypeIdsLsit.size()>0)
			queryStr.append(" and tc.tdp_basic_committee_id in (:committeeTypeIdsLsit) ");
		
		if(committeeLevelIdsList != null && committeeLevelIdsList.size()>0)
			queryStr.append(" and tc.tdp_committee_level_id in (:committeeLevelIdsList) ");
		
		if(validIds != null && validIds.size()>0)
			queryStr.append(" and tr.tdp_roles_id in (:designationIdsList)   ");
		if(stateId != null && stateId.longValue()>0L)
			queryStr.append(" and ua.state_id =:stateId  ");
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
			queryStr.append("  and tcey.enrollment_year_id in (:enrollmentIdsList)   ");
		
		queryStr.append("  order by  ");
		queryStr.append("  tc1.tdp_cadre_id ");
		
		Query query =  null;
		if(fetchTypeStr != null && fetchTypeStr.equalsIgnoreCase("count")){
			query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("count", Hibernate.LONG)
					.addScalar("empty", Hibernate.STRING);
		}
		else{
			query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("tdp_cadre_di", Hibernate.LONG)
					.addScalar("membership_id", Hibernate.STRING)
					.addScalar("first_name", Hibernate.STRING)
					.addScalar("mobile_no", Hibernate.STRING)
					.addScalar("position", Hibernate.STRING)
					.addScalar("state_id", Hibernate.LONG)
					.addScalar("state_name", Hibernate.STRING)
					.addScalar("district_id", Hibernate.LONG)
					.addScalar("district_name", Hibernate.STRING)
					.addScalar("pId", Hibernate.LONG)
					.addScalar("pName", Hibernate.STRING)
					.addScalar("assemblyId", Hibernate.LONG)
					.addScalar("assemblyName", Hibernate.STRING)
					.addScalar("tehsil_id", Hibernate.LONG)
					.addScalar("tehsil_name", Hibernate.STRING)
					.addScalar("local_election_body_id", Hibernate.LONG)
					.addScalar("lebName", Hibernate.STRING)
					.addScalar("panchayat_id", Hibernate.LONG)
					.addScalar("panchayat_name", Hibernate.STRING)
					.addScalar("wardId", Hibernate.LONG)
					.addScalar("wardName", Hibernate.STRING)
					.addScalar("image", Hibernate.STRING)
					.addScalar("committeeName", Hibernate.STRING);
		}
		
		if(stateId != null && stateId.longValue()>0L)
			query.setParameter("stateId", stateId);
		if(validIds != null && validIds.size()>0)
			query.setParameterList("designationIdsList", validIds);
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
			query.setParameterList("enrollmentIdsList", enrollmentIdsList);
		if(committeeTypeIdsLsit != null && committeeTypeIdsLsit.size()>0)
			query.setParameterList("committeeTypeIdsLsit", committeeTypeIdsLsit);
		if(committeeLevelIdsList != null && committeeLevelIdsList.size()>0)
			query.setParameterList("committeeLevelIdsList", committeeLevelIdsList);
		
		/*if(locationIdsList != null && locationIdsList.size()>0){
		Set<Long> idsList = new HashSet<Long>();
		if(levelId.longValue() == 5L || levelId.longValue() == 6L || levelId.longValue() == 7L || levelId.longValue() == 8L ){
			for (Long id : locationIdsList) {
				idsList.add(Long.valueOf(id.toString().substring(1)));
			}
		}else{
			idsList.addAll(locationIdsList);
		}
		query.setParameterList("locationIdsList", idsList);
	}*/

		if(locationIdsList != null && locationIdsList.size()>0){
			if(levelId.longValue() == 5L || levelId.longValue() == 6L || levelId.longValue() == 7L || levelId.longValue() == 8L ){
				if(urbanIdsList != null && urbanIdsList.size()>0)
					query.setParameterList("urbanIdsList", urbanIdsList);
				if(ruralIdsList != null && ruralIdsList.size()>0)
					query.setParameterList("ruralIdsList", ruralIdsList);
			}
			else{
				query.setParameterList("locationIdsList", locationIdsList);
			}
			
		}
		
		if(maxIndex>0){
			query.setFirstResult(firstIndex);
			query.setMaxResults(maxIndex);
		}
		return query.list();
	}
	
}
