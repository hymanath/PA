package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ZebraPrintDetails;

public interface IZebraPrintDetailsDAO extends GenericDao<ZebraPrintDetails, Long>{ 

	public List<Object[]> getMemberShipCardPrintStatusByDate(List<Long> constituencyIds,Date fromDate, Date toDate,String type);
}
