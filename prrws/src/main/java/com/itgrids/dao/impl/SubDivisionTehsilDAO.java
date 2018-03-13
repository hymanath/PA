package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ISubDivisionTehsilDAO;
import com.itgrids.model.SubDivisionTehsil;

@Repository
public class SubDivisionTehsilDAO extends GenericDaoHibernate<SubDivisionTehsil, Long> implements ISubDivisionTehsilDAO {

	@Autowired
	SessionFactory sessionFactory; 
	
	public SubDivisionTehsilDAO() {
		super(SubDivisionTehsil.class);
	}
	
	public List<Object[]> getTehsilsOfSubDivision(Long subDivisonId){
		Query query = getSession().createSQLQuery("select sdt.tehsil_id as tehsilId, teh.tehsil_name as tehsilName "
				+ " from sub_division_tehsil sdt, tehsil teh "
				+ " where sdt.tehsil_id=teh.tehsil_id and sdt.sub_division_id=:subDivisonId").addScalar("tehsilId", StandardBasicTypes.LONG).addScalar("tehsilName",StandardBasicTypes.STRING);
		query.setParameter("subDivisonId", subDivisonId);
		return query.list();
	}
}
