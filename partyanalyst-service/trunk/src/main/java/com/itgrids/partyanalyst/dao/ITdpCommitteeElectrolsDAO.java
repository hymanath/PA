package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeElectrols;

public interface ITdpCommitteeElectrolsDAO  extends GenericDao<TdpCommitteeElectrols, Long>{
	public List<Object[]> getTdpCommitteeElectrolsForTdpCadreIdList(List<Long> tdpCadreIdsList);
}
