package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IFinancialYearDAO;
import com.itgrids.model.FinancialYear;

@Repository
public class FinancialYearDAO extends GenericDaoHibernate<FinancialYear, Long> implements IFinancialYearDAO {

	@Autowired
	SessionFactory sessionFactory;

	public FinancialYearDAO() {
		super(FinancialYear.class);

	}
	
	public List<Object[]> getAllFiniancialYears(){
	      StringBuilder sb = new StringBuilder();
	      sb.append(" select distinct  model.financialYearId,model.yearDesc from FinancialYear model "+ 
	                 " order by model.yearDesc desc ");
	      Query query = getSession().createQuery(sb.toString());
	      return query.list(); 
	      
	    }
	public List<Object[]> getAllFiniancialYearsByIds(List<Long> financialYearIds){
	      StringBuilder sb = new StringBuilder();
	      sb.append(" select distinct  model.financialYearId,model.yearDesc from FinancialYear model ");
	      if(financialYearIds != null && financialYearIds.size() >0)
	    	  sb.append(" where model.financialYearId in (:financialYearIds) ");
	      
	    		  sb.append(" order by model.yearDesc desc ");
	      Query query = getSession().createQuery(sb.toString());
	      if(financialYearIds != null && financialYearIds.size() >0)
	    	  query.setParameterList("financialYearIds", financialYearIds);
	      return query.list(); 
	      
	    }

}
