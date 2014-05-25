package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyDesignationDAO;
import com.itgrids.partyanalyst.model.PartyDesignation;

public class PartyDesignationDAO extends GenericDaoHibernate<PartyDesignation, Long> implements IPartyDesignationDAO{

	public PartyDesignationDAO() {
		super(PartyDesignation.class);
		// TODO Auto-generated constructor stub
	}
   public List<Object[]> getAllPartyDesignation(){
	   Query query = getSession().createQuery("select model.partyDesignationId , model.partyDesignationName from PartyDesignation model order by model.partyDesignationName");
	
		return query.list();
		
   }
}
