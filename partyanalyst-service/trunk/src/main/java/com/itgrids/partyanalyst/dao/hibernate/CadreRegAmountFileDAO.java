package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreRegAmountFileDAO;
import com.itgrids.partyanalyst.model.CadreRegAmountFile;

public class CadreRegAmountFileDAO extends GenericDaoHibernate<CadreRegAmountFile, Long> implements ICadreRegAmountFileDAO{

	public CadreRegAmountFileDAO() {
		super(CadreRegAmountFile.class);
	}

}
