package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmWorkTypeDAO;
import com.itgrids.model.PmWorkType;
@Repository
public class PmWorkTypeDAO extends GenericDaoHibernate<PmWorkType, Long> implements IPmWorkTypeDAO {
	@Autowired
	SessionFactory sessionFactory; 
	public PmWorkTypeDAO() {
		super(PmWorkType.class);
	}

	public List<Object[]> getWorkTypeList(){
		StringBuilder sb = new StringBuilder();
		sb.append("select  model.pmWorkTypeId,model.workType from PmWorkType model where model.isDeleted='N' order by model.orderNo ");
		Query qry = getSession().createQuery(sb.toString());
		return qry.list();
	}
	

}
