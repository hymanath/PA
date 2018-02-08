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
	
	public List<String> getWorkdetailsById(String type) {
		StringBuilder sb = new StringBuilder();
		if(type !=null && type.equalsIgnoreCase("all")){
			sb.append("select distinct model.workId from RwsWork model ");
			
		}else{
			sb.append("select distinct model.workId from RwsWork model where is_active='Y'");
		}
		Query query= getSession().createQuery(sb.toString());
		
		return query.list();
	}

	@Override
	public List<Object[]> getWorksData(Date fromDate,Date toDate,String status,String assetType, String locationType,String locationValue,String districtId) {
		
		StringBuilder sb = new StringBuilder();
		//0-workId,1-WorkName,2-status,3-assetType,4-adminDate,5-groundDate,6-targetrDate,7-completionDate
		//8-dCode,9-dname,10-ccode ,11-Cname,12-mCode,13-mname ,14-habcode,15-habname
		sb.append("select model.rwsWork.workId,model.rwsWork.workName,model.rwsWork.workStatus,model.rwsWork.assetType,model.rwsWork.adminDate,model.rwsWork.groundedDate,model.rwsWork.targetDate,model.rwsWork.completedDate," +
				" model.districtCode,model.districtName, model.constituencyCode,model.constituencyName,model.mandalCode,model.mandalName,model.habitationCode," +
				"model.habitationName,model.rwsWork.sanctionedAmount, 01, 'AndraPradesh',model.rwsWork.stipulatedTargetDate,model.rwsWork.programCode,model.rwsWork.programName from RwsWorkLocation model where model.rwsWork.workStatus != 'Not grounded'" +
				" and model.rwsWork.isActive ='Y' and  model.rwsWork.stipulatedTargetDate is not null");
		if(fromDate!= null && toDate!=null){
			sb.append(" and model.rwsWork.adminDate between :fromDate and :toDate ");
		}
		if(assetType!= null && assetType.length()>0){
			sb.append(" and model.rwsWork.assetType in (:assetType)");
		}
		if(districtId!=null && districtId.length()>0){
			sb.append(" and model.districtCode =:districtId");
		}
		if(status != null && status.length()>0){
			sb.append(" and model.rwsWork.workStatus =:status ");
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
		if(status != null && status.length()>0){
			if(status.equalsIgnoreCase("onGoing")){
				status="Grounded";
			}
			query.setParameter("status",status);
		}
		if(districtId!=null && districtId.length()>0){
			query.setParameter("districtId",districtId.toString());
		}
		return query.list();
	}

	@Override
	public RwsWork getWorkdetailsByIds(String workId) {
		Query query= getSession().createQuery("from RwsWork model where workId=:workId and model.isActive='Y' ");
		query.setParameter("workId", workId);
		RwsWork work = (RwsWork) query.uniqueResult();
		return work;
	}

	@Override
	public List<RwsWork> getWorksbyWorkIdList(List<String> workIds) {
		Query query= getSession().createQuery("from RwsWork where workId in(:workId)");
		query.setParameterList("workId", workIds);
		return query.list();
	}

	@Override
	public List<Object[]> getnotGroundedWorkList(Date fromDate, Date toDate,String assetType, String locationType, String locationValue, String districtId) {
		//0-workId,2-workName,3-workStatus,4-assesttype,5-admindate,6-diff,7-dcode, 8-dname,9-ccode,10-cname,11-mcode,12-mname,13-hcode,14-hname,15-sancAmnt,16-sid,17-sname
		StringBuilder sb = new StringBuilder();
		sb.append("select  model.rwsWork.workId,model.rwsWork.workName,model.rwsWork.workStatus,model.rwsWork.assetType,model.rwsWork.adminDate, datediff(curdate(),model.rwsWork.adminDate), " +
				" model.districtCode,model.districtName, model.constituencyCode,model.constituencyName,model.mandalCode,model.mandalName,model.habitationCode," +
				" model.habitationName,model.rwsWork.sanctionedAmount, 01, 'AndraPradesh',model.rwsWork.programCode,model.rwsWork.programName from RwsWorkLocation model where model.rwsWork.isActive='Y' and model.rwsWork.workStatus='Not Grounded' ");
		if(fromDate!= null && toDate!=null){
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
		return query.list();
	}

}
