package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadre;

public interface ITdpCadreDAO extends GenericDao<TdpCadre, Long>{

	public List<Object[]> getRegisterCadreInfoBetweenDates(Date fromDate,Date toDate);
	
	public List<Object[]> getRegisterCadreInfoConstituencyWise();
	
	public List<Object[]> getCadreDetailsForCadreRegistratiobByconstituencId(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String villagesCovered);
	
	public List<Object[]> getRegisterCadreInfoDistrictWise();
	
	public String getLatestMemberNumber();
}
