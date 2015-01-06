package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreIVREnquiryDAO;
import com.itgrids.partyanalyst.model.CadreIVREnquiry;

public class CadreIVREnquiryDAO  extends GenericDaoHibernate<CadreIVREnquiry, Long> implements ICadreIVREnquiryDAO {

	public CadreIVREnquiryDAO() {
		super(CadreIVREnquiry.class);
	}
	public List<Object[]> getLocationWiseEnquiryInfo(String locationLvl,Long locationValue,Long userId,String resultType){
		//0 locationTypeId,1locationValue,2details,3mobile,4received,5delivered,6 insertedDate,7callStatus
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.locationTypeId,model.locationValue,model.details,model.mobile,model.received,model.delivered,model.insertedDate," +
				" model.callStatus from CadreIVREnquiry model where model.isDeleted ='N'  ");
		if(userId != null){
			queryStr.append(" and model.userId =:userId and ");
		}
		if(locationLvl.equalsIgnoreCase("all")){
		   queryStr.append(" and model.constituencyId =:locationValue");
		}else if(locationLvl.equalsIgnoreCase("constituency")){
			 queryStr.append(" and model.locationTypeId ='1'");
		}else if(locationLvl.equalsIgnoreCase("tehsil")){
			if(resultType.equalsIgnoreCase("complete")){
				queryStr.append("  and ( model.locationTypeId ='2' or model.locationTypeId ='5' ) "); 
			}else{
			 queryStr.append(" and model.locationTypeId ='2'"); 
			}
		}else if(locationLvl.equalsIgnoreCase("localElecBody")){
			 queryStr.append("  and model.locationTypeId ='5'"); 
		}else if(locationLvl.equalsIgnoreCase("ward")){
			 queryStr.append("  and model.locationTypeId ='6'"); 
		}
		if(resultType.equalsIgnoreCase("complete")){
			queryStr.append(" and model.constituencyId =:locationValue ");
		}else{
			queryStr.append(" and model.locationValue =:locationValue ");
		}
		 queryStr.append(" order by model.locationTypeId,model.insertedDate");
		Query query = getSession().createQuery(queryStr.toString());
		if(userId != null){
			query.setParameter("userId", userId);
		}
		query.setParameter("locationValue", locationValue);
		return query.list();
	}
}
