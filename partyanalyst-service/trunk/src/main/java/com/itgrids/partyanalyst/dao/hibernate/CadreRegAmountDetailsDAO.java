package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreRegAmountDetailsDAO;
import com.itgrids.partyanalyst.model.CadreRegAmountDetails;

public class CadreRegAmountDetailsDAO extends GenericDaoHibernate<CadreRegAmountDetails, Long> implements ICadreRegAmountDetailsDAO{

	public CadreRegAmountDetailsDAO() {
		super(CadreRegAmountDetails.class);
		
	} 

}
