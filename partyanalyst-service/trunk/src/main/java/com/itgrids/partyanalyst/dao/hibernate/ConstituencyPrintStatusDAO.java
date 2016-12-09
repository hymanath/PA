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
		if(stateId != null && stateId.longValue() > 0l)
			query.setParameter("stateId", stateId);
		if(vendorId != null && vendorId.longValue() > 0l)
			query.setParameter("vendorId", vendorId);
		
		return query.list();
	}
	
	public List<Object[]> getStatusWiseDistrictWisePrintingConstituencyDetails(Long stateId,Long vendorId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.district.districtId,model.district.districtName," +
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
		
		sb.append(" group by model.printStatus.printStatusId,model.district.districtId" +
					" order by model.district.districtId,model.printStatus.printStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(stateId != null && stateId.longValue() > 0l)
			query.setParameter("stateId", stateId);
		if(vendorId != null && vendorId.longValue() > 0l)
			query.setParameter("vendorId", vendorId);
		
		return query.list();
	}
}
