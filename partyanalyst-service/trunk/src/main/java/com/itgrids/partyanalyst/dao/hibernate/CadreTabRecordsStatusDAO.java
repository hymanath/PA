package com.itgrids.partyanalyst.dao.hibernate;

import java.io.Serializable;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreTabRecordsStatusDAO;
import com.itgrids.partyanalyst.model.CadreTabRecordsStatus;

public class CadreTabRecordsStatusDAO extends GenericDaoHibernate<CadreTabRecordsStatus, Long> implements ICadreTabRecordsStatusDAO{

	public CadreTabRecordsStatusDAO() {
		super(CadreTabRecordsStatus.class);
	}
	
}