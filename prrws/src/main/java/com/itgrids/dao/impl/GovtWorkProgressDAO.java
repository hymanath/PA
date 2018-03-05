package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.solr.search.QueryUtils;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkProgressDAO;
import com.itgrids.model.GovtWorkProgress;
import com.itgrids.utils.IConstants;

@Repository
public class GovtWorkProgressDAO extends GenericDaoHibernate<GovtWorkProgress, Long> implements IGovtWorkProgressDAO{

	public GovtWorkProgressDAO() {
		super(GovtWorkProgress.class);
	}

	public List<Object[]> getLatestStatusOfWork(List<Long> workIds){
		Query query = getSession().createQuery(" select model.govtWorkId,max(model.govtWorkStatusId) "
				+ " from GovtWorkProgress model "
				+ " where model.govtWorkId in (:workIds) group by model.govtWorkId ");
		query.setParameterList("workIds", workIds);
		return query.list();
	}
	
	public GovtWorkProgress getWorkProgressId(Long govtWorkId,Long govtWorkStatusId){
		Query query = getSession().createQuery(" select model "
				+ " from GovtWorkProgress model "
				+ " where model.govtWorkId=:govtWorkId and model.govtWorkStatusId=:govtWorkStatusId ");
		query.setParameter("govtWorkId", govtWorkId);
		query.setParameter("govtWorkStatusId", govtWorkStatusId);
		return (GovtWorkProgress)query.uniqueResult();
	}
	
	public List<Object[]> getAllstatusInfoOfGovtWork(Long govtWorkId){
		//0-statusTypeId,1-statusType,2-govtWorkStatusId,3-statusName,4-workLength,5-completedPercentage,6-isCompleted,7-updateById,8-updatedByName,9-time,10-govtWorkProgressId
		Query query = getSession().createQuery(" select model.govtWorkStatus.statusType.statusTypeId,model.govtWorkStatus.statusType.typeName,"
				+ " model.govtWorkStatusId,model.govtWorkStatus.statusName,"
				+ " model.workLength,model.completedPercentage,model.isCompleted,model.updatedBy,model.updatedByUser.username,date(model.updatedTime),model.govtWorkProgressId "
				+ " from GovtWorkProgress model "
				+ " where model.govtWorkId=:govtWorkId ");
		query.setParameter("govtWorkId", govtWorkId);
		return query.list();
	}
	
	public List<Object[]> getCompletedMianWorkPercentage(Long userId,Long workTypeId,Long locationScopeId,List<Long> locationValues){
		//0-mainWorkId,1-totalKms,2-completedKms
		Query query = getSession().createSQLQuery("select gmw.govt_main_work_id as mainWorkId,gmw.approved_km as totalKms,sum(gwp.work_length) as completedKms "
				+ " from govt_work_progress gwp,govt_main_work gmw,govt_work gw "
				+ " where gw.is_deleted='N' "
				+ " and gwp.govt_work_id=gw.govt_work_id "
				+ " and gw.govt_main_work_id=gmw.govt_main_work_id "
				+ " and gw.created_by=:userId and gmw.govt_work_type_id=:workTypeId and gwp.govt_work_status_id=:statusId  "
				+ " and gmw.location_scope_id=:locationScopeId and gmw.location_scope_id in (:locationValues) "
				+ " group by gmw.govt_main_work_id");
		
		query.setParameter("userId", userId);
		query.setParameter("workTypeId", workTypeId);
		query.setParameter("locationScopeId", locationScopeId);
		query.setParameterList("locationValues", locationValues);
		if(workTypeId == 1l){
			query.setParameter("statusId", IConstants.UGDFINISHSTATUSID);
		}
		
		return query.list();
	}
	
	public List<Object[]> getAllFininsedKmsOfWorks(List<Long> workIds){
		//0-workId,1-statusCount,2-lengthSum
		Query query = getSession().createSQLQuery(" select model.govt_work_id,model.work_length "
				+ " from govt_work_progress model "
				+ " where model.govt_work_id in (:workIds) and model.govt_work_status_id=:finishStatusId ");
		query.setParameterList("workIds", workIds);
		query.setParameter("finishStatusId", IConstants.UGDFINISHSTATUSID);
		return query.list();
	}
	
	public List<Object[]> getCompletedWorksCount(Date fromDate,Date toDate){
		//0-workTypeId,1-workscount,2-worklength
		Query query = getSession().createSQLQuery(" select gmw.govt_work_type_id,count(distinct gw.govt_work_id),sum(gw.work_length) "
				+ " from govt_work_progress gwp,govt_work gw,govt_main_work gmw "
				+ " where gwp.govt_work_id=gw.govt_work_id and gw.is_deleted='N' and gw.govt_main_work_id=gmw.govt_main_work_id "
				+ "  and gwp.govt_work_status_id in (:govtWorkFinishStatusIds) and date(gw.created_time) between :fromDate and :toDate "
				+ " group by gmw.govt_work_type_id ");
		
		query.setParameterList("govtWorkFinishStatusIds", IConstants.GOVTWORKFINISHSTATUSIDS);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return null;
	}
}
