package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationWardDAO;
import com.itgrids.partyanalyst.model.DelimitationWard;

public class DelimitationWardDAO extends GenericDaoHibernate<DelimitationWard, Long> implements IDelimitationWardDAO{
	
	public DelimitationWardDAO()
	{
		super(DelimitationWard.class);
	}

	@SuppressWarnings("unchecked")
	public List<DelimitationWard> findByDelimitationConstituenyTownAndWard(Long delimconstiTownId,Long wardId){
		Object[] params = {delimconstiTownId,wardId};
		return getHibernateTemplate().find("from DelimitationWard model where model.delimitationConstituencyTown.delimitationConstituencyTownId = ? and "+
				"model.ward.wardId = ?",params);
	}
}
