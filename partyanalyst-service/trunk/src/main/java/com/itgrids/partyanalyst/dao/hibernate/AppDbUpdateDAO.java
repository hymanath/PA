package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppDbUpdateDAO;
import com.itgrids.partyanalyst.model.AppDbUpdate;

public class AppDbUpdateDAO extends GenericDaoHibernate<AppDbUpdate,Long> implements IAppDbUpdateDAO {

	public AppDbUpdateDAO(){
		super(AppDbUpdate.class);
	}
	
	public List<Double> getAllVersionsOfAnApp(String appName,Double currentVerison,boolean includeTest){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.dbVersion from AppDbUpdate model where model.appName = :appName and model.dbVersion > :currentVerison and model.isDeleted ='N' ");
		if(!includeTest){
			queryStr.append(" and model.environment != 'test' ");
		}
		queryStr.append(" order by model.dbVersion ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("appName", appName);
		query.setParameter("currentVerison", currentVerison);
		
		return query.list();
	}
	
	public List<Object[]> getAllUpdatesByVersion(String appName,Double version){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.appDbUpdateId,model.orderId,model.scriptFile from AppDbUpdate model where model.appName = :appName and model.dbVersion = :version " +
				"  and model.isDeleted ='N' order by model.orderId ");
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("appName", appName);
		query.setParameter("version", version);
		
		return query.list();
	}
}
