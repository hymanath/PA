package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRwsWorkDAO;
import com.itgrids.model.RwsWork;

@Repository
public class RwsWorkDAO extends GenericDaoHibernate<RwsWork, Long> implements IRwsWorkDAO {

	public RwsWorkDAO()
	{
		super(RwsWork.class);
	}
	
	public List<String> getWorkdetailsById() {
		
		Query query= getSession().createQuery("select model.workId from RwsWork model");
		
		return query.list();
	}

	@Override
	public List<Object[]> getWorksData(Date fromDate,Date toDate,String assetType, String locationType,String locationValue,String districtId) {
		
		StringBuilder sb = new StringBuilder();
		//0-workId,1-WorkName,2-status,3-assetType,4-adminDate,5-groundDate,6-targetrDate,7-completionDate
		//8-dCode,9-dname,10-ccode ,11-Cname,12-mCode,13-mname ,14-habcode,15-habname
		sb.append("select model.rwsWork.workId,model.rwsWork.workName,model.rwsWork.workStatus,model.rwsWork.assetType,model.rwsWork.adminDate,model.rwsWork.groundedDate,model.rwsWork.targetDate,model.rwsWork.completedDate," +
				" model.districtCode,model.districtName, model.constituencyCode,model.constituencyName,model.mandalCode,model.mandalName,model.habitationCode," +
				"model.habitationName,model.rwsWork.sanctionedAmount, 01, 'AndraPradesh' from RwsWorkLocation model where model.rwsWork.workStatus != 'Not grounded' ");
		/*if(fromDate!= null && toDate!=null && (locationType == null || !locationType.equalsIgnoreCase("district"))){
			sb.append(" and model.adminDate between DATE_FORMAT(:fromDate,'%y-%m-%d') and DATE_FORMAT(:toDate,'%y-%m-%d')");
		}
		else*/ if(fromDate!= null && toDate!=null){
			sb.append(" and model.rwsWork.adminDate between :fromDate and :toDate ");
		}
		if(assetType!= null && assetType.length()>0){
			sb.append(" and model.rwsWork.assetType in (:assetType)");
		}
		if(districtId!=null && districtId.length()>0){
			sb.append(" and model.districtCode =:districtId");
		}
		if(locationType!= null && locationType.length()>0 ){
			if(locationType.equalsIgnoreCase("district")){
				sb.append(" and model.districtCode =:locationValue");
			}else if(locationType.equalsIgnoreCase("constituency")){
				sb.append(" and model.constituencyCode =:locationValue");
			}else if(locationType.equalsIgnoreCase("mandal")){
				sb.append(" and model.mandalCode=:locationValue");
			}
		}
		Query query = getSession().createQuery(sb.toString());
		
		if(fromDate!= null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(assetType!= null && assetType.length()>0){
			query.setParameter("assetType", assetType);
		}
		if(locationType!= null && locationType.length()>0 && locationValue !=null && locationValue.length()>0 && !locationType.equalsIgnoreCase("state")){
			query.setParameter("locationValue", locationValue.toString());
		}
		if(districtId!=null && districtId.length()>0){
			query.setParameter("districtId",districtId.toString());
		}
		return query.list();
	}

	@Override
	public RwsWork getWorkdetailsByIds(String workId) {
		Query query= getSession().createQuery("from RwsWork where workId=:workId");
		query.setParameter("workId", workId);
		RwsWork work = (RwsWork) query.uniqueResult();
		return work;
	}

	@Override
	public List<Object[]> getWorksDataByDistrict(Date fromDate, Date toDate,String assetType, String locationType, Long locationValue,Long districtId) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("  select rwswork0_.work_id as workId, rwswork0_.work_name as workName, rwswork0_.work_status as workStatus, " +
				" rwswork0_.asset_type as assetType, rwswork0_.admin_date as adminDate, rwswork0_.grounded_date as groundedDate," +
				" rwswork0_.target_date as targetDate, rwswork0_.completed_date as completedDate, rwsworkloc1_.district_code as districtCode," +
				" rwsworkloc1_.district_name as districtName, rwsworkloc1_.constituency_code as constituencyCode, rwsworkloc1_.constituency_name as constituencyName," +
				" rwsworkloc1_.mandal_code as mandalCode, rwsworkloc1_.mandal_name as mandalName, rwsworkloc1_.habitation_code as habitationCode," +
				" rwsworkloc1_.habitation_name as habitationName, rwswork0_.sanction_amount as sanctionAmount, 1 as stateId, 'AndraPradesh' as stateName" +
				" from rws_work rwswork0_ ,rws_work_location rwsworkloc1_ where rwswork0_.rws_work_id=rwsworkloc1_.rws_work_id and" +
				" rwswork0_.work_status <>'Not grounded'");
		if(fromDate!= null && toDate!=null){
			queryStr.append(" and (rwswork0_.admin_date between date_format(:fromDate,'%y-%m-%d') and  date_format(:toDate, '%y-%m-%d')) ");
		}
		if(assetType!= null && assetType.length()>0){
			queryStr.append(" and rwswork0_.asset_type in (:assetType)");
		}
		if(districtId!=null && districtId !=0){
			queryStr.append(" and location.districtCode =:districtId");
		}
		if(locationType!= null && locationType.length()>0 ){
			if(locationType.equalsIgnoreCase("district")){
				queryStr.append(" and rwsworkloc1_.district_code =:locationValue");
			}
		}

		Query query = getSession().createSQLQuery(queryStr.toString())
				      .addScalar("workId", StandardBasicTypes.STRING)
		.addScalar("workName", StandardBasicTypes.STRING)
		.addScalar("workStatus", StandardBasicTypes.STRING)
		.addScalar("assetType", StandardBasicTypes.STRING)
		.addScalar("adminDate", StandardBasicTypes.DATE)
		.addScalar("groundedDate", StandardBasicTypes.DATE)
		.addScalar("targetDate", StandardBasicTypes.DATE)
		.addScalar("completedDate", StandardBasicTypes.DATE)
		.addScalar("districtCode", StandardBasicTypes.STRING)
		.addScalar("districtName", StandardBasicTypes.STRING)
		.addScalar("constituencyCode", StandardBasicTypes.STRING)
		.addScalar("constituencyName", StandardBasicTypes.STRING)
		.addScalar("mandalCode", StandardBasicTypes.STRING)
		.addScalar("mandalName", StandardBasicTypes.STRING)
		.addScalar("habitationCode", StandardBasicTypes.STRING)
		.addScalar("habitationName", StandardBasicTypes.STRING)
		.addScalar("sanctionAmount", StandardBasicTypes.DOUBLE)
		.addScalar("stateId", StandardBasicTypes.STRING)
		.addScalar("stateName", StandardBasicTypes.STRING);
		
		if(fromDate!= null && toDate!=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(assetType!= null && assetType.length()>0){
			query.setParameter("assetType", assetType);
		}
		if(locationType!= null && locationType.length()>0 && locationValue !=null && !locationType.equalsIgnoreCase("state")){
			query.setParameter("locationValue", locationValue.toString());
		}

		return query.list();
	
	}
	
}
