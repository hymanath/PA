package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartialBoothPanchayat;

public interface IPartialBoothPanchayatDAO extends 
					GenericDao<PartialBoothPanchayat,Long>{
	public List<PartialBoothPanchayat> getPartialBoothPanchayatDetailsByPanchayatId(Long PanchayatId);

}
