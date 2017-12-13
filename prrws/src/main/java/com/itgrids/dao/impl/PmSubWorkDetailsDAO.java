package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmSubWorkDetailsDAO;
import com.itgrids.model.PmSubWorkDetails;

@Repository
public class PmSubWorkDetailsDAO extends GenericDaoHibernate<PmSubWorkDetails, Long> implements IPmSubWorkDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;
	PmSubWorkDetailsDAO(){
		super(PmSubWorkDetails.class);
	}
	
	public List<Object[]> getAllDistricts(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.district.districtId");
		sb.append(",model.locationAddress.district.districtName ");
		sb.append(" from PmSubWorkDetails model where model.isDeleted = 'N' order by model.locationAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
	
	public List<Object[]> getAllConstituenciesByDistricId(Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.constituency.constituencyId");
		sb.append(",model.locationAddress.constituency.name ");
		sb.append(" from PmSubWorkDetails model where model.isDeleted ='N' ");
		if(districtId != null && districtId.longValue() >0L ){ 
			sb.append("and model.locationAddress.districtId =:districtId ");
		}
		sb.append( "order by model.locationAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(districtId != null && districtId.longValue() >0L ){ 
			query.setParameter("districtId", districtId);
		}
		return query.list();
	}
	public List<Object[]> getAllMandalsByDistricId(Long constincyIdId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.tehsil.tehsilId");
		sb.append(",model.locationAddress.tehsil.tehsilName ");
		sb.append(" from PmSubWorkDetails model where model.isDeleted ='N' ");
		if(constincyIdId != null && constincyIdId.longValue() >0L ){ 
			sb.append("and model.locationAddress.constituencyId =:constincyIdId ");
		}
		sb.append( "order by model.locationAddress.tehsil.tehsilName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constincyIdId != null && constincyIdId.longValue() >0L ){ 
			query.setParameter("constincyIdId", constincyIdId);
		}
		return query.list();
	}
	
	public List<Object[]> getDepartmentsByWorks(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmDepartment.pmDepartmentId,model.pmDepartment.department "
				+ "from PmSubWorkDetails model where model.isDeleted='N' and model.pmDepartment.isDeleted='N' "
				+ "order by model.pmDepartment.orderNo asc ");
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
}
