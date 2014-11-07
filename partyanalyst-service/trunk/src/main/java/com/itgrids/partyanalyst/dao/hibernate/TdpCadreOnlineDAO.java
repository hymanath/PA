package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreOnlineDAO;
import com.itgrids.partyanalyst.model.TdpCadreOnline;

public class TdpCadreOnlineDAO  extends GenericDaoHibernate<TdpCadreOnline,Long> implements ITdpCadreOnlineDAO {

	public TdpCadreOnlineDAO(){
		super(TdpCadreOnline.class);
	}
	
}
