package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPrintStatusDAO;
import com.itgrids.partyanalyst.model.PrintStatus;

public class PrintStatusDAO extends GenericDaoHibernate<PrintStatus, Long> implements IPrintStatusDAO {

	public PrintStatusDAO(){
		super(PrintStatus.class);
	}
	
	public List<Object[]> getAllPrintStatus(){
		Query query = getSession().createQuery(" select model.printStatusId , model.printStatus , model.description from PrintStatus model");
		return query.list();
	}

}