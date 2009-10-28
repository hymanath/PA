package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IConstituencyTehsilDAO;
import com.itgrids.partyanalyst.model.ConstituencyTehsil;

public class ConstituencyTehsilDAO  extends GenericDaoHibernate<ConstituencyTehsil, Long>
							implements IConstituencyTehsilDAO {

	public ConstituencyTehsilDAO() {
		super(ConstituencyTehsil.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConstituencyTehsil> getTehsilsByConstituency(Long constituencyID){
		return getHibernateTemplate().find("from ConstituencyTehsil model where model.constituency.constituencyId = ?",constituencyID);
	}
}
