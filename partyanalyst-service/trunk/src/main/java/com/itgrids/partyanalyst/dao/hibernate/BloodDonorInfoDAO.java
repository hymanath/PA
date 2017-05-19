package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBloodDonorInfoDAO;
import com.itgrids.partyanalyst.model.BloodDonorInfo;

public class BloodDonorInfoDAO extends GenericDaoHibernate<BloodDonorInfo, Long> implements IBloodDonorInfoDAO {
 
	public BloodDonorInfoDAO(){
		super(BloodDonorInfo.class);
	}
	
/*public List<Object[]> getBloodDonorCounts(Date campFromDate,Date campToDate ){
		
		Query query = getSession().createQuery(" select count(model.tdpCadreId)," +
				" model.bloodDonation.acceptanceStatusId " +
				" from BloodDonorInfo model " +
				" where " +
				" date(model.insertedTime) between :campFromDate and :campToDate " +
				" group by model.bloodDonation.acceptanceStatusId ");
		
		query.setDate("campFromDate",campFromDate);
		query.setDate("campToDate",campToDate);
		return query.list();
	}*/
/*public List<Object[]> getBloodDonorDayWiseCounts(Date campFromDate,Date campToDate ){
	
	Query query = getSession().createQuery(" select count(model.tdpCadre.tdpCadreId)," +
			" date(model.insertedTime)," +
			" model.bloodDonation.acceptanceStatusId " +
			" from BloodDonorInfo model " +
			" date(model.insertedTime) between :campFromDate and :campToDate " +
			" group by date(model.insertedTime),model.bloodDonation.acceptanceStatusId ");
	
	query.setDate("campFromDate",campFromDate);
	query.setDate("campToDate",campToDate);
	return query.list();
   }*/
	@SuppressWarnings("unchecked")
	public List<Object[]> getBloodDonorDetails(String dataType,int firstIndex,int maxResult,String attended){
		StringBuilder  str = new StringBuilder();
		if(dataType != null && dataType.equalsIgnoreCase("count")){
			str.append(" select " +
					" count( distinct model.bloodDonorInfoId), ''  from BloodDonorInfo model " +
					" left join model.tdpCadre tdpCadre " +
					" where model.isDeleted = 'N' and model.registeredSource = 'app' ");
		}else{
			if(attended.equalsIgnoreCase("true")){
				str.append(" select " +
						" distinct model.bloodDonorInfoId," +
						" model.donorName,model.mobileNo," +
						" model.donationTime, " +
						" model.tdpCadre.memberShipNo " +
						" from EventAttendee model2,BloodDonorInfo model " +
						" where ");
				str.append(" model.tdpCadreId = model2.tdpCadreId  and model2.eventId >= 51l and " );
			}else{
				str.append(" select " +
						" distinct model.bloodDonorInfoId," +
						" model.donorName,model.mobileNo," +
						" model.donationTime, " +
						" tdpCadre.memberShipNo " +
						" from BloodDonorInfo model " +
						" left join model.tdpCadre tdpCadre " +
						" where ");
				
			}
			
			str.append(" model.isDeleted = 'N' and model.registeredSource = 'app' ");
			
		}
		Query query = getSession().createQuery(str.toString());
		if(maxResult >0){
			query.setFirstResult(firstIndex);
			query.setMaxResults(maxResult);
		}
		return query.list();
	}
}
