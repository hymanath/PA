package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkDAO;
import com.itgrids.model.GovtWork;

@Repository
public class GovtWorkDAO extends GenericDaoHibernate<GovtWork, Long> implements IGovtWorkDAO{

	public GovtWorkDAO() {
		super(GovtWork.class);
	}
	
	public List<Object[]> getWorkZoneDetailsOfMainWork(Long userId,Long mainWorkId){
		//0-workId,1-workZoneName,2-workLength,3-locationScopeId,4-locationScopeValue
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.govtWorkId,model.workZoneName,model.workLength,model.govtMainWork.locationScopeId,model.govtMainWork.locationValue "
				+ " from GovtWork model "
				+ " where model.isDeleted='N' and model.createdBy=:userId and model.govtMainWork.govtMainWorkId=:mainWorkId ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		query.setParameter("mainWorkId", mainWorkId);
		
		return query.list();
	}
	
	public Long getWorkTypeId(Long govtWorkId){
		Query query = getSession().createQuery(" select model.govtMainWork.govtWorkTypeId "
				+ " from GovtWork model where model.govtWorkId=:govtWorkId ");
		query.setParameter("govtWorkId", govtWorkId);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getUsersGovtMainWorks(Long userId,Long govtWorkTypeId){
		//0-mainWorkId,1-mainWorkName,2-totalKms,3-locationScopeId,4-locationValue,5-locationAddressId,6-count,7-workLength
		Query query = getSession().createSQLQuery(" select gmw.govt_main_work_id as mainWorkId,gmw.govt_main_work_name as mainWorkName,"
				+ " gmw.approved_km as totalKms,gmw.location_scope_id as locationScopeId,gmw.location_value as locationValue,"
				+ " gmw.location_address_id as locationAddressId, count(distinct govt_work_id) as count,sum(gw.work_length) as workLength "
				+ " from govt_work gw, govt_main_work gmw "
				+ " where gw.is_deleted='N' and gw.govt_main_work_id=gmw.govt_main_work_id "
				+ " and gmw.govt_work_type_id=:govtWorkTypeId "
				+ "and gw.created_by=:userId group by gmw.govt_main_work_id;");
		
		query.setParameter("userId", userId);
		query.setParameter("govtWorkTypeId", govtWorkTypeId);
		
		return query.list();
	}
	
	public Object getOverallWork(Long workTypeId,Long workId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select ");
		
		if(workId != null && workId > 0l){
			sb.append(" gw.work_length ");
		}else{
			sb.append(" sum(gmw.approved_km) ");
		}
		
		sb.append(" from govt_main_work gmw ");
		
		if(workId != null && workId > 0l){
			sb.append(" ,govt_work gw ");
		}
		
		sb.append(" where gmw.govt_work_type_id=:workTypeId ");
		
		if(workId != null && workId > 0l){
			sb.append(" and gw.is_deleted='N' and gw.govt_main_work_id=gmw.govt_main_work_id and gw.govt_work_id=:workId ");
		}
		
		Query query = getSession().createSQLQuery(sb.toString());
		
		query.setParameter("workTypeId",workTypeId);
		
		if(workId != null && workId > 0l){
			query.setParameter("workId",workId);
		}
		
		return (Object)query.uniqueResult();
	}
	
	public List<Object[]> getWorksCountByMainType(Date fromDate,Date toDate){
		//0-workTypeId,1-workscount
		Query query = getSession().createQuery(" select model.govtMainWork.govtWorkTypeId,count(distinct model.govtWorkId) "
				+ " from GovtWork model "
				+ " where model.isDeleted='N' and date(model.createdTime) between :fromDate and :toDate "
				+ " group by model.govtMainWork.govtWorkTypeId ");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
		
	}
	
	public List<Object[]> getWorkZonesCountForDateType(Date fromDate,Date toDate){
		//0-workTypeId,1-worksCount
		Query query = getSession().createQuery(" select model.govtMainWork.govtWorkTypeId,count(model.govtWorkId) "
				+ " from GovtWork model "
				+ " where model.isDeleted='N' and date(model.createdTime) between :fromDate and :toDate "
				+ " group by model.govtMainWork.govtWorkTypeId ");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
}
