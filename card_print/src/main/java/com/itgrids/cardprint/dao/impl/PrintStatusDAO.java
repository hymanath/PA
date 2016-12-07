package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.cardprint.dao.IPrintStatusDAO;
import com.itgrids.cardprint.model.PrintStatus;

public class PrintStatusDAO extends GenericDaoHibernate<PrintStatus, Long> implements IPrintStatusDAO {

	public PrintStatusDAO(){
		super(PrintStatus.class);
	}
	
	public List<Object[]> getAllPrintStatus(){
		Query query = getSession().createQuery(" select model.printStatusId , model.printStatus , model.description from PrintStatus model");
		return query.list();
	}

}
