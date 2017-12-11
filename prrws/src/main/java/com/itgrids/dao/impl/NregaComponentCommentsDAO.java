package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaComponentCommentsDAO;
import com.itgrids.model.NregaComponentComments;

@Repository
public class NregaComponentCommentsDAO extends GenericDaoHibernate<NregaComponentComments, Long> implements INregaComponentCommentsDAO {

	@Autowired
	SessionFactory sessionFactory; 
	
	public NregaComponentCommentsDAO() {
		super(NregaComponentComments.class);
	}
	public List<Object[]> getNregaComponentComments(List<String> uniqueCode) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select model.nregaComponentStatus.status,model.comment,model.actionPlan,model.uniqueCode," +
		 		" model.nregaComponentStatus.nregaComponentStatusId,model.nregaComponentCommentsId " +
		 		" from NregaComponentComments model where model.isDeleted='N' " );
		 if(uniqueCode != null && uniqueCode.size()>0){
			 queryStr.append(" and model.uniqueCode in (:uniqueCode) ");
		 }
		 Query query = getSession().createQuery(queryStr.toString());
		 if(uniqueCode != null && uniqueCode.size()>0){
		    query.setParameterList("uniqueCode", uniqueCode);
		 }
		 return query.list();
	}
}
