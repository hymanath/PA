package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITelecastTypeDAO;
import com.itgrids.partyanalyst.model.Channel;
import com.itgrids.partyanalyst.model.TelecastType;

public class TelecastTypeDAO extends GenericDaoHibernate<TelecastType, Long> implements ITelecastTypeDAO{

	public TelecastTypeDAO() {
		super(TelecastType.class);
		// TODO Auto-generated constructor stub
	}

	 @SuppressWarnings("unchecked")
	public List<TelecastType> getTelecastTimeDetails(){
		Query query = getSession().createQuery("select model from TelecastType model");
		 
		return query.list();
	 }
	 
	 @SuppressWarnings("unchecked")
		public List<Object[]> getTelecastTimeDetailsNew(){
			Query query = getSession().createQuery("select model.telecastTypeId,model.name from TelecastType model " +
					" where model.isDeleted='N' ");
			 
			return query.list();
		 }
}
