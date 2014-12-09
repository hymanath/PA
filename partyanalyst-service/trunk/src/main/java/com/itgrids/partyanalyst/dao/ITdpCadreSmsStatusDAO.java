package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreSmsStatus;

public interface ITdpCadreSmsStatusDAO extends GenericDao<TdpCadreSmsStatus, Long>{
	public List<Object[]> getTdpCadreIds();
	public Integer updateTdpCadre(Long Id,Long tdpCadreId);
}
