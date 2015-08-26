package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreContestedLocationDAO;
import com.itgrids.partyanalyst.model.TdpCadreContestedLocation;

public class TdpCadreContestedLocationDAO extends GenericDaoHibernate<TdpCadreContestedLocation, Long> implements ITdpCadreContestedLocationDAO{

	public TdpCadreContestedLocationDAO() {
		super(TdpCadreContestedLocation.class);
		// TODO Auto-generated constructor stub
	}
}
