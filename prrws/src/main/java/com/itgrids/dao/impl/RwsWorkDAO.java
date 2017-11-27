package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRwsWorkDAO;
import com.itgrids.model.RwsWork;

@Repository
public class RwsWorkDAO extends GenericDaoHibernate<RwsWork, Long> implements IRwsWorkDAO {

	@Autowired
	SessionFactory sessionFactory;
	public RwsWorkDAO() {
		super(RwsWork.class);
	}
	
	@Override
	public List<String> getWorkdetailsById() {
		
		Query query= sessionFactory.getCurrentSession().createQuery("select model.workId from RwsWork model");
		
		return query.list();
	}

	@Override
	public List<Object[]> getWorksData(Date fromDate,Date toDate,String assetType, String locationType,Long locationValue) {
		
		StringBuilder sb = new StringBuilder();
		//0-workId,1-WorkName,2-status,3-assetType,4-adminDate,5-groundDate,6-targetrDate,7-completionDate
		//8-dCode,9-dname,10-ccode ,11-Cname,12-mCode,13-mname ,14-habcode,15-habname
		sb.append("select model.workId,model.workName,model.workStatus,model.assetType,model.adminDate,model.groundedDate,model.targetDate,model.completedDate," +
				" location.districtCode,location.districtName, location.constituencyCode,location.constituencyName,location.mandalCode,location.mandalName,location.habitationCode," +
				"location.habitationName,model.sanctionedAmount, 01, 'AndraPradesh' from RwsWork model, RwsWorkLocation location where model.rwsWorkId=location.rwsWorkId and model.workStatus <> 'Not grounded' ");
		if(fromDate!= null && toDate!=null){
			sb.append(" and model.adminDate between DATE_FORMAT(:fromDate,'%y-%m-%d') and DATE_FORMAT(:toDate,'%y-%m-%d')");
		}
		if(assetType!= null && assetType.length()>0){
			sb.append(" and model.assetType in (:assetType)");
		}
		if(locationType!= null && locationType.length()>0 ){
			if(locationType.equalsIgnoreCase("district")){
				sb.append(" and location.districtCode =:locationValue");
			}else if(locationType.equalsIgnoreCase("constituency")){
				sb.append(" and location.constituencyCode =:locationValue");
			}else if(locationType.equalsIgnoreCase("mandal")){
				sb.append(" and location.mandalCode=:locationValue");
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		
		if(fromDate!= null && toDate!=null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(assetType!= null && assetType.length()>0){
			query.setParameter("assetType", assetType);
		}
		if(locationType!= null && locationType.length()>0 && locationValue !=null){
			query.setParameter("locationValue", locationValue.toString());
		}
		return query.list();
	}

	@Override
	public RwsWork getWorkdetailsByIds(String workId) {
		Query query= sessionFactory.getCurrentSession().createQuery("from RwsWork where workId=:workId");
		query.setParameter("workId", workId);
		RwsWork work = (RwsWork) query.uniqueResult();
		return work;
	}
	
}
