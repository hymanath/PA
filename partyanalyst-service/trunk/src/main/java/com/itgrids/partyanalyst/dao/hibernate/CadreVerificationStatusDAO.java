package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreVerificationStatusDAO;
import com.itgrids.partyanalyst.model.CadreVerificationStatus;

public class CadreVerificationStatusDAO extends GenericDaoHibernate<CadreVerificationStatus, Long> implements ICadreVerificationStatusDAO {

	public CadreVerificationStatusDAO(){
		super(CadreVerificationStatus.class);
	}

}
