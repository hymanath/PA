package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreImageSinkDataDAO;
import com.itgrids.partyanalyst.model.TdpCadreImageSinkData;

public class TdpCadreImageSinkDataDAO extends GenericDaoHibernate<TdpCadreImageSinkData, Long> implements ITdpCadreImageSinkDataDAO{

	public TdpCadreImageSinkDataDAO() {
		super(TdpCadreImageSinkData.class);
		// TODO Auto-generated constructor stub
	}

}
