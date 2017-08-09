package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.WardBooth;

public interface IWardBoothDAO extends GenericDao<WardBooth, Long>{
	public Long getWardsCount(Long wardId,Long boothId,Long publicationDateId);
	public List<Object[]> getWardBothData(Long publicationDateId,Long constituencyId);
	public List<Constituency> getWardDetailsByBoothId(Long boothId);
}
