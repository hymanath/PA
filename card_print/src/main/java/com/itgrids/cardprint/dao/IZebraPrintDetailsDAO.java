package com.itgrids.cardprint.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.cardprint.model.ZebraPrintDetails;

public interface IZebraPrintDetailsDAO extends GenericDao<ZebraPrintDetails, Long> {
	
	public List<Object[]>  getPrintDetailsByConstituencyId(Long constituencyId);
}
