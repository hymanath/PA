package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadrePsychometricTest;

public interface ITdpCadrePsychometricTestDAO extends GenericDao<TdpCadrePsychometricTest, Long>{

	public String getUrlForMemberShipNo(String memberShipNo);
}
