package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreImagesValid;

public interface ITdpCadreImagesValidDAO extends GenericDao<TdpCadreImagesValid, Long>{

	public List<TdpCadreImagesValid> checkForExists(Long tdpCadreId);
	//public List<Long> getExistinceTdpCadre(List<Long> tdpCadreIds);
	public List<Object[]> getValidOrInValidDetails(Long districtId,Long constituencyId,String type);
	
}
