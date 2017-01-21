package com.itgrids.partyanalyst.dao.hibernate;


import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IConstituencyPrintStatusDAO;
import com.itgrids.partyanalyst.model.ConstituencyPrintStatus;

public class ConstituencyPrintStatusDAO extends GenericDaoHibernate<ConstituencyPrintStatus, Long> implements IConstituencyPrintStatusDAO {

	public ConstituencyPrintStatusDAO(){
		super(ConstituencyPrintStatus.class);
	}
	
	public List<Object[]> getConstListByVendorId(Long vendorId,Long districtId){
		StringBuilder sb = new StringBuilder();
			sb.append("select  distinct model.constituency.constituencyId," +
				" model.constituency.name" +
				" from ConstituencyPrintStatus model");
		if((vendorId != null && vendorId.longValue() > 0l) || (districtId != null && districtId.longValue() > 0l))
				sb.append(" where");
		if(vendorId != null && vendorId.longValue() > 0l){
				sb.append(" model.printVendorId = :vendorId");
		}
		if((vendorId != null && vendorId.longValue() > 0l) && (districtId != null && districtId.longValue() > 0l))
			sb.append(" and");
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" model.constituency.district.districtId = :districtId");
		Query query = getSession().createQuery(sb.toString());
		if(vendorId != null && vendorId.longValue() > 0l)
			query.setParameter("vendorId", vendorId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		return query.list();
		
	}
	public List<Object[]> getDstrListByVendorId(Long vendorId){
		StringBuilder sb = new StringBuilder();
		  		sb.append("select  distinct model.constituency.district.districtId," +
		  		" model.constituency.district.districtName" +
		  		" from  ConstituencyPrintStatus model ");
		  if(vendorId != null && vendorId.longValue() > 0l){
				sb.append(" where model.printVendorId = :vendorId");
		  }
		  Query query = getSession().createQuery(sb.toString());
			if(vendorId != null && vendorId.longValue() > 0l)
				query.setParameter("vendorId", vendorId);
			return query.list();
	}

	public List<Object[]> getStatusWisePrintingConstituencyDetails(Long stateId,Long vendorId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.printStatus.printStatusId,count(distinct model.constituency.constituencyId)," +
					" model.printStatus.printStatus" +
					" from ConstituencyPrintStatus model" +
					" where");
		if(fromDate != null && toDate != null)
			sb.append(" date(model.updatedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() == 1l)
			sb.append(" and model.constituency.district.districtId between 11 and 23");
		else if(stateId != null && stateId.longValue() == 36l)
			sb.append(" and model.constituency.district.districtId between 1 and 10");
		
		if(vendorId != null && vendorId.longValue() > 0l)
			sb.append(" and model.cardPrintVendor.cardPrintVendorId = :vendorId");
		
		sb.append(" group by model.printStatus.printStatusId" +
					" order by model.printStatus.printStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		if(vendorId != null && vendorId.longValue() > 0l)
			query.setParameter("vendorId", vendorId);
		
		return query.list();
	}
	
	public List<Object[]> getStatusWiseDistrictWisePrintingConstituencyDetails(Long stateId,Long vendorId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.constituency.district.districtId,model.constituency.district.districtName," +
					" model.printStatus.printStatusId," +
					" count(distinct model.constituency.constituencyId)" +
					" from ConstituencyPrintStatus model" +
					" where");
		if(fromDate != null && toDate != null)
			sb.append(" date(model.updatedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() == 1l)
			sb.append(" and model.constituency.district.districtId between 11 and 23");
		else if(stateId != null && stateId.longValue() == 36l)
			sb.append(" and model.constituency.district.districtId between 1 and 10");
		
		if(vendorId != null && vendorId.longValue() > 0l)
			sb.append(" and model.cardPrintVendor.cardPrintVendorId = :vendorId");
		
		sb.append(" group by model.printStatus.printStatusId,model.constituency.district.districtId" +
					" order by model.constituency.district.districtId,model.printStatus.printStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		if(vendorId != null && vendorId.longValue() > 0l)
			query.setParameter("vendorId", vendorId);
		
		return query.list();
	}
	
	public List<Object[]> getStatusWiseVendorWiseConstituencyDetails(Long stateId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.cardPrintVendor.cardPrintVendorId,model.printStatus.printStatusId,count(distinct model.constituency.constituencyId)" +
					" from ConstituencyPrintStatus model" +
					" where");
		if(fromDate != null && toDate != null)
			sb.append(" date(model.updatedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() == 1l)
			sb.append(" and model.constituency.district.districtId between 11 and 23");
		else if(stateId != null && stateId.longValue() == 36l)
			sb.append(" and model.constituency.district.districtId between 1 and 10");
		
		sb.append(" group by model.printStatus.printStatusId,model.cardPrintVendor.cardPrintVendorId" +
					" order by model.cardPrintVendor.cardPrintVendorId,model.printStatus.printStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	///
	public List<Long> getConstituencyPrintStatusIds(Long printVendorId , Long constituencyId){
		Query query = getSession().createQuery("" +
		" select model.constituencyPrintStatusId " +
		" from   ConstituencyPrintStatus model " +
		" where  model.printVendorId =:printVendorId and  model.constituencyId = :constituencyId ");
		query.setParameter("printVendorId", printVendorId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public String getPresentStatusForAConstituencyByVendor(Long vendorId , Long constituencyId){
		Query query = getSession().createQuery("" +
	    " select model.printStatus.printStatus from   ConstituencyPrintStatus model where model.printVendorId =:vendorId and model.constituencyId =:constituencyId ");
		query.setParameter("vendorId", vendorId);
		query.setParameter("constituencyId", constituencyId);
		return (String)query.uniqueResult();
	}
}
