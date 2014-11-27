package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreVerfiedDataDAO;
import com.itgrids.partyanalyst.model.TdpCadreVerfiedData;

public class TdpCadreVerfiedDataDAO extends GenericDaoHibernate<TdpCadreVerfiedData,Long> implements ITdpCadreVerfiedDataDAO{

	public TdpCadreVerfiedDataDAO() {
		super(TdpCadreVerfiedData.class);
	}
}
