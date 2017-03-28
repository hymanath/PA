package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFieldVendorLocationDAO;
import com.itgrids.partyanalyst.model.FieldVendorLocation;

public class FieldVendorLocationDAO extends GenericDaoHibernate<FieldVendorLocation, Long> implements IFieldVendorLocationDAO{

	public FieldVendorLocationDAO() {
		super(FieldVendorLocation.class);
	}
   
	public List<Object[]> getVendors(Long stateId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.fieldVendor.fieldVendorId ,model.fieldVendor.vendorName " +
				"   from   FieldVendorLocation model " +
				"   where  model.isDeleted = 'N' and model.fieldVendor.isActive = 'Y' ");
		if(stateId != null && stateId > 0l){
			if(stateId == 36){
				sb.append(" and model.constituency.district.districtId between 1 and 10");
			}else if(stateId == 1){
				sb.append(" and model.constituency.district.districtId between 11 and 23");
			}
		}
		
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}
	
	public List<Object[]> getVendorDistricts(Long stateId,Long vendorId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.constituency.district.districtId , model.constituency.district.districtName " +
				"   from   FieldVendorLocation model " +
				"   where  model.isDeleted = 'N' and model.fieldVendor.isActive = 'Y' and model.fieldVendor.fieldVendorId = :vendorId ");
		if(stateId != null && stateId > 0l){
			if(stateId == 36){
				sb.append(" and model.constituency.district.districtId between 1 and 10");
			}else if(stateId == 1){
				sb.append(" and model.constituency.district.districtId between 11 and 23");
			}
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("vendorId",vendorId);
		return query.list();
	}
	
	public List<Object[]> getVendorConstituencies(Long vendorId,Long districtId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.constituency.constituencyId , model.constituency.name " +
				"   from   FieldVendorLocation model " +
				"   where  model.isDeleted = 'N' and model.fieldVendor.isActive = 'Y' and " +
				"          model.fieldVendor.fieldVendorId = :vendorId and  model.constituency.district.districtId =:districtId ");
		
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("vendorId",vendorId);
		query.setParameter("districtId",districtId);
		return query.list();
	}
	public List<Object[]> getConstituencyByVendor(Long fieldVendorId)
	{
	   Query query = getSession().createQuery("select distinct model.constituency.constituencyId ,model.constituency.name from FieldVendorLocation model" +
	   		" where model.isDeleted = 'N' and model.fieldVendor.isActive = 'Y' and " +
	   		"       model.fieldVendor.fieldVendorId = :fieldVendorId ");
	   query.setParameter("fieldVendorId", fieldVendorId);
	   
	   return query.list();
	}

	
}
