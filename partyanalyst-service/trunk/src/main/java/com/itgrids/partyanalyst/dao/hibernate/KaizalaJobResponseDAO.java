package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKaizalaJobResponseDAO;
import com.itgrids.partyanalyst.model.KaizalaJobResponse;

public class KaizalaJobResponseDAO extends GenericDaoHibernate<KaizalaJobResponse, Long> implements IKaizalaJobResponseDAO {

	public KaizalaJobResponseDAO(){
		super(KaizalaJobResponse.class);
	}
}
