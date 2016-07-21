package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INominatedPostAccessTypeDAO;
import com.itgrids.partyanalyst.model.NominatedPostAccessType;

public class NominatedPostAccessTypeDAO extends GenericDaoHibernate<NominatedPostAccessType, Long> implements INominatedPostAccessTypeDAO {

	public NominatedPostAccessTypeDAO(){
		super(NominatedPostAccessType.class);
	}

}
