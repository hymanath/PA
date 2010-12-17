package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyTownDAO;
import com.itgrids.partyanalyst.model.DelimitationConstituencyTown;

public class DelimitationConstituencyTownDAO extends GenericDaoHibernate<DelimitationConstituencyTown,Long> implements IDelimitationConstituencyTownDAO{

	public DelimitationConstituencyTownDAO(){
		super(DelimitationConstituencyTown.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<DelimitationConstituencyTown> findByDelimitationConstituencyAndTownship(Long delimitationConstiId,Long townId){
		
		Object[] params = {delimitationConstiId,townId};
		return getHibernateTemplate().find("from DelimitationConstituencyTown model where model.delimitationConstituency.delimitationConstituencyID = ? and "+
				"model.township.townshipId = ?",params);
	}
}
