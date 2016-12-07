package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.IPrintStatusDAO;
import com.itgrids.cardprint.model.PrintStatus;
import com.itgrids.cardprint.model.User;

public class PrintStatusDAO extends GenericDaoHibernate<PrintStatus, Long> implements IPrintStatusDAO {

	public PrintStatusDAO(){
		super(PrintStatus.class);
	}

}
