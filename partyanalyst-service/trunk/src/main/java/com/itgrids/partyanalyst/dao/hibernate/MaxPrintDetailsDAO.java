package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMaxPrintDetailsDAO;
import com.itgrids.partyanalyst.model.MaxPrintDetails;

public class MaxPrintDetailsDAO extends GenericDaoHibernate<MaxPrintDetails, Long> implements IMaxPrintDetailsDAO {

	public MaxPrintDetailsDAO(){
		super(MaxPrintDetails.class);
	}
	
}

