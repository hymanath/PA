package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IImportantLeadersTypeDAO;
import com.itgrids.partyanalyst.model.ImportantLeadersType;

public class ImportantLeadersTypeDAO extends GenericDaoHibernate<ImportantLeadersType, Long> implements IImportantLeadersTypeDAO{

	public ImportantLeadersTypeDAO() {
		super(ImportantLeadersType.class);
		// TODO Auto-generated constructor stub
	}

	public List<Object[]> getAllImportantLeadersTypes(){
		Query query = getSession().createQuery("select model.importantLeadersTypeId," +
												" model.position," +
												" model.importantLeadersLevelId" +
												" from ImportantLeadersType model" +
												" where model.isActive = 'true'" +
												" order by model.orderNo");
		return query.list();
	}
	
	public Long getLocationScopeIdForTypeId(Long impLeadTypeId){
		Query query = getSession().createQuery("select model.importantLeadersLevelId" +
													" from ImportantLeadersType model" +
													" where model.importantLeadersTypeId = :impLeadTypeId" +
													" and model.isActive = 'true'");
		query.setParameter("impLeadTypeId", impLeadTypeId);
		return (Long) query.uniqueResult();
	}
	
	public Integer getMaxOrderNo(){
		Query query = getSession().createSQLQuery("select max(order_no) from important_leaders_type");
		return  (Integer) query.uniqueResult();
	}
	
	public Long checkTypeExists(String type){
		Query query = getSession().createQuery("select model.importantLeadersTypeId" +
												" from ImportantLeadersType model" +
												" where model.position = :type" +
												" and model.isActive = 'true'");
		query.setParameter("type", type);
		return (Long) query.uniqueResult();
	}
}
