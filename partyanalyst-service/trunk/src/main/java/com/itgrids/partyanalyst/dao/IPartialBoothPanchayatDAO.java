package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartialBoothPanchayat;

public interface IPartialBoothPanchayatDAO extends 
					GenericDao<PartialBoothPanchayat,Long>{
	public List<Long> getPartialBoothPanchayatDetailsByPanchayatId(Long PanchayatId,Long publicationDateId);
	public List<PartialBoothPanchayat> getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(Long panchayatId,Long publicationDateId);
	public List<Long> getPartialBoothDetailsByPanchayatIdsAndPublicationDateId(List<Long> panchayatIds , Long publicationDateId);

}
