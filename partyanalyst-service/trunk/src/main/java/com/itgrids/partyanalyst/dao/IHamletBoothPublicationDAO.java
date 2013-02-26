package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HamletBoothPublication;

public interface IHamletBoothPublicationDAO extends GenericDao<HamletBoothPublication, Long> {
	public List<Object[]> getBoothsInPanchayatByPublicationId(Long panchayatId,Long publicationDateId);
	
	public List<HamletBoothPublication> getHameletDetailsByBoothId(Long boothId);
	public List<Object> getBoothsIds(Long panchayatId,Long publicationDateId);
	public List<Object[]> getBoothsInHamlet(Long panchayatId,Long publicationDateId);
}
