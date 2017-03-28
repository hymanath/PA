package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigDecimal;
import java.util.Date;
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
			queryStr.append(" and model.userId =:userId  ");
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
	//SELECT received,location_value,inserted_date FROM cadre_ivr_enquiry t1 JOIN (SELECT MAX(cadre_ivr_enquiry_id) cadre_ivr_enquiry_id FROM cadre_ivr_enquiry  where location_type_id = 1 and is_deleted = 'N' GROUP BY location_value ) t2 ON t1.cadre_ivr_enquiry_id = t2.cadre_ivr_enquiry_id ORDER BY inserted_date desc ;
	public BigDecimal getTotalReceivedCount(Date startDate, Date endDate,List<Long> locationTypeIds,List<Long> constiIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("SELECT sum(t1.received) FROM cadre_ivr_enquiry t1 JOIN " +
				" (SELECT MAX(cadre_ivr_enquiry_id) cadre_ivr_enquiry_id FROM cadre_ivr_enquiry  where is_deleted = 'N' and constituency_id in (:constiIds)"); 
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		str.append(" and date(inserted_date) >=:startDate and date(inserted_date) <=:endDate");
		else if(startDate != null && endDate != null && startDate.equals(endDate))
		str.append(" and date(inserted_date) >=:startDate");
		
		str.append(" and location_type_id in (:locationTypeIds) GROUP BY location_value,location_type_id ) t2 ON t1.cadre_ivr_enquiry_id = t2.cadre_ivr_enquiry_id ORDER BY inserted_date desc ");
		Query query = getSession().createSQLQuery(str.toString());
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		{
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		}
		else if(startDate != null && endDate != null && startDate.equals(endDate))
		query.setDate("startDate", startDate);
		query.setParameterList("locationTypeIds", locationTypeIds);
		query.setParameterList("constiIds", constiIds);
		return  (BigDecimal) query.uniqueResult();
	}
	public BigDecimal getTotalDeliveredCount(Date startDate, Date endDate,List<Long> locationTypeIds,List<Long> constiIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("SELECT sum(t1.delivered) FROM cadre_ivr_enquiry t1 JOIN " +
				" (SELECT MAX(cadre_ivr_enquiry_id) cadre_ivr_enquiry_id FROM cadre_ivr_enquiry  where is_deleted = 'N' and constituency_id in (:constiIds) "); 
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		str.append(" and date(inserted_date) >=:startDate and date(inserted_date) <=:endDate");
		else if(startDate != null && endDate != null && startDate.equals(endDate))
		str.append(" and date(inserted_date) >=:startDate");
		
		str.append(" and location_type_id in(:locationTypeIds) GROUP BY location_value,location_type_id ) t2 ON t1.cadre_ivr_enquiry_id = t2.cadre_ivr_enquiry_id ORDER BY date(inserted_date) desc ");
		Query query = getSession().createSQLQuery(str.toString());
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		{
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		}
		else if(startDate != null && endDate != null && startDate.equals(endDate))
		query.setDate("startDate", startDate);
		query.setParameterList("locationTypeIds", locationTypeIds);
		query.setParameterList("constiIds", constiIds);
		return  (BigDecimal) query.uniqueResult();
	}
	public List<Object[]> getNoOfLocationCountByTypeId(List<Long> locationTypeIds,Date startDate, Date endDate,List<Long> constiIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.locationValue),model.locationValue,model.locationTypeId from CadreIVREnquiry model where model.locationTypeId in ( :locationTypeIds) and " +
				" model.isDeleted ='N' and model.constituencyId in (:constiIds) ");
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		str.append(" and date(model.insertedDate) >=:startDate and  date(model.insertedDate) <=:endDate"); 
		else if(startDate != null && endDate != null && startDate.equals(endDate))
			str.append(" and date(model.insertedDate) =:startDate");
		str.append("  GROUP BY model.locationTypeId,model.locationValue " ); 
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("locationTypeIds", locationTypeIds);
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		{
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		}
		else if(startDate != null && endDate != null && startDate.equals(endDate))
		query.setDate("startDate", startDate);
		query.setParameterList("constiIds", constiIds);
		return  query.list();
	}
	public List<Object[]> getLocationIdsByTypeId(List<Long> locationTypeIds,Date startDate, Date endDate,List<Long> constiIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.locationValue,model.locationTypeId from CadreIVREnquiry model where model.locationTypeId in( :locationTypeIds) and " +
				" model.isDeleted ='N' and model.constituencyId in (:constiIds) ");
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		str.append(" and date(model.insertedDate) >=:startDate and  date(model.insertedDate) <=:endDate"); 
		else if(startDate != null && endDate != null && startDate.equals(endDate))
			str.append(" and date(model.insertedDate) =:startDate");	
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("locationTypeIds", locationTypeIds);
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		{
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		}
		else if(startDate != null && endDate != null && startDate.equals(endDate))
		query.setDate("startDate", startDate);
		query.setParameterList("constiIds", constiIds);
		return query.list();
	}
	public List<Date> getAvailableDates()
	{
		return getHibernateTemplate().find("select distinct Date(model.insertedDate) from CadreIVREnquiry model where model.isDeleted ='N' ");
		
	}
	
	public List<Object[]> getDeliveredAndReceivedCount(List<Long> locationValue,Date startDate, Date endDate,Long locationTypeId)
	{
		StringBuilder str = new StringBuilder();
		str.append("SELECT t1.delivered,t1.received,t1.location_value,t1.location_type_id FROM cadre_ivr_enquiry t1 JOIN " +
				" (SELECT MAX(cadre_ivr_enquiry_id) cadre_ivr_enquiry_id FROM cadre_ivr_enquiry  where is_deleted = 'N' and location_type_id = :locationTypeId  "); 
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		str.append(" and date(inserted_date) >=:startDate and date(inserted_date) <=:endDate");
		else if(startDate != null && endDate != null && startDate.equals(endDate))
		str.append(" and date(inserted_date) >=:startDate");
		
		str.append(" and location_value in (:locationValue) GROUP BY location_value ) t2 ON t1.cadre_ivr_enquiry_id = t2.cadre_ivr_enquiry_id ORDER BY inserted_date desc ");
		Query query = getSession().createSQLQuery(str.toString());
		if(startDate != null && endDate != null && !startDate.equals(endDate))
		{
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		}
		else if(startDate != null && endDate != null && startDate.equals(endDate))
		query.setDate("startDate", startDate);
		query.setParameterList("locationValue", locationValue);
		query.setParameter("locationTypeId", locationTypeId);
		return  query.list();
	}
	
	
	
	public List<Object[]> getMandalRecievedCountConstituency(List<Long> constituencyIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("SELECT sum(t1.received),constituency_id FROM cadre_ivr_enquiry t1 JOIN " +
				" (SELECT MAX(cadre_ivr_enquiry_id) cadre_ivr_enquiry_id FROM cadre_ivr_enquiry  where is_deleted = 'N' and constituency_id in (:constituencyIds) "); 
	
		str.append(" and location_type_id in (2,5) GROUP BY location_value,location_type_id ) t2 ON t1.cadre_ivr_enquiry_id = t2.cadre_ivr_enquiry_id  GROUP BY constituency_id ORDER BY date(inserted_date) desc ");
		Query query = getSession().createSQLQuery(str.toString());

		query.setParameterList("constituencyIds", constituencyIds);
		return  query.list();
	}
}
