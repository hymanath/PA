package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreCallCenterRemarksDAO;
import com.itgrids.partyanalyst.model.TdpCadreCallCenterRemarks;


public class TdpCadreCallCenterRemarksDAO  extends GenericDaoHibernate<TdpCadreCallCenterRemarks, Long> implements ITdpCadreCallCenterRemarksDAO {
	
	
	public TdpCadreCallCenterRemarksDAO() {
		super(TdpCadreCallCenterRemarks.class);		
	}
}
