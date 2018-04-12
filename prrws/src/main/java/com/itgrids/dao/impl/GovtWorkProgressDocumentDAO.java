package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkProgressDocumentDAO;
import com.itgrids.model.GovtWorkProgressDocument;

@Repository
public class GovtWorkProgressDocumentDAO extends GenericDaoHibernate<GovtWorkProgressDocument, Long> implements IGovtWorkProgressDocumentDAO{

	public GovtWorkProgressDocumentDAO() {
		super(GovtWorkProgressDocument.class);
	}
	
	public List<Object[]> getStatusDocumentsOfGovtWork(Long workId){
		//0-statusTypeId,1-govtStatusId,2-documentId,3-path,4-mobileAppUserId,5-username,6-insertedTime
		Query query = getSession().createQuery(" select model.govtWorkProgress.govtWorkStatus.statusTypeId,model.govtWorkProgress.govtWorkStatusId,"
				+ " model.document.documentId,model.document.path,model.document.mobileAppUser.mobileAppUserId,model.document.mobileAppUser.username,"
				+ " date(model.document.insertedTime) "
				+ " from GovtWorkProgressDocument model"
				+ " where model.govtWorkProgress.govtWorkId=:workId ");
		query.setParameter("workId", workId);
		return query.list();
	}
	
	public List<Object[]> getStatusWiseDocs(List<Long> workZoneIds,Long locationScopeId,List<Long> locationValues,Long statusId,Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.document.documentId,model.document.path "
				+ " from GovtWorkProgressDocument model "
				+ " where model.isDeleted='N'");
		
		if(statusId != null && statusId > 0l){
			sb.append(" and model.govtWorkProgress.govtWorkStatusId=:statusId ");
		}
		if(workZoneIds != null && workZoneIds.size() > 0){
			sb.append(" and model.govtWorkProgress.govtWorkId in (:workZoneIds) ");
		}else{
			if(locationScopeId == 3l){
				sb.append(" and model.govtWorkProgress.govtWork.govtMainWork.locationAddress.districtId in (:locationValues) ");
			}else if(locationScopeId == 4l){
				sb.append(" and model.govtWorkProgress.govtWork.govtMainWork.locationAddress.constituencyId in (:locationValues) ");
			}else if(locationScopeId == 5l){
				sb.append(" and model.govtWorkProgress.govtWork.govtMainWork.locationAddress.tehsilId in (:locationValues) ");
			}else if(locationScopeId == 6l){
				sb.append(" and model.govtWorkProgress.govtWork.govtMainWork.locationAddress.panchayatId in (:locationValues) ");
			}else if(locationScopeId == 12l){
				sb.append(" and model.govtWorkProgress.govtWork.govtMainWork.locationAddress.divisionId in (:locationValues) ");
			}else if(locationScopeId == 13l){
				sb.append(" and model.govtWorkProgress.govtWork.govtMainWork.locationAddress.subDivisionId in (:locationValues) ");
			}	
		}
		
		if(startDate != null && endDate != null){
			sb.append(" and date(model.updatedTime) between :startDate and :endDate ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(statusId != null && statusId > 0l){
			query.setParameter("statusId", statusId);
		}
		if(workZoneIds != null && workZoneIds.size() > 0){
			query.setParameterList("workZoneIds", workZoneIds);
		}else{
			if(locationScopeId == 3l || locationScopeId == 4l || locationScopeId == 5l || locationScopeId == 6l || locationScopeId == 12l || locationScopeId == 13l){
				query.setParameterList("locationValues", locationValues);
			}
		}
		
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getRecentWorkDocuments(Long workTypeId){
		//0-panchayatId,1-mandalId,2-docId,3-path,4-insertedDate,5-lattitude,6-longitude
		Query query = getSession().createQuery(" select panchayat.panchayatId,tehsil.tehsilId,model.document.documentId,model.document.path,date(model.updatedTime),model.document.lattitude,model.document.longitude "
				+ " from GovtWorkProgressDocument model "
				+ " left join model.govtWorkProgress.govtWork.govtMainWork.locationAddress.panchayat panchayat "
				+ " left join model.govtWorkProgress.govtWork.govtMainWork.locationAddress.tehsil tehsil "
				+ " where model.govtWorkProgress.govtWork.govtMainWork.govtWorkTypeId=:workTypeId "
				+ " order by model.updatedTime desc ");
		query.setParameter("workTypeId", workTypeId);
		query.setMaxResults(5);
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getWorkZoneDocumentDetailsInfo(Date startDate,Date endDate,Long workId){
		//0-docId,1-path,2-date
	StringBuilder sb = new StringBuilder();
		sb.append(" select model.document.documentId,model.document.path,date(model.updatedTime) " +
				" from GovtWorkProgressDocument model " +
				" where " +
				" model.isDeleted ='N' and model.govtWorkProgress.govtWorkId =:workId ");
		
		if(startDate !=null && endDate !=null){
			sb.append(" and date(model.updatedTime) between :startDate and :endDate ");
		}
		sb.append(" order by model.updatedTime desc ");
		
	Query query = getSession().createQuery(sb.toString());
	if(startDate != null && endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
	}
	query.setParameter("workId",workId);
	return query.list();
		
	}
}
