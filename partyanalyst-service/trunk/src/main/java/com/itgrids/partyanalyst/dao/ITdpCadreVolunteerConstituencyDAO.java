package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreVolunteerConstituency;




public interface ITdpCadreVolunteerConstituencyDAO extends GenericDao<TdpCadreVolunteerConstituency, Long>{
	public List<Object[]> getVolunteerInfoByLocation(Long constituencyId,String searchType);
	public List<Object[]> getconsituencyListById(List<Long> volunteerId);
	public List<Object[]> getDeviceInfo(String deviceType,Long constituencyId,String searchType);
}
