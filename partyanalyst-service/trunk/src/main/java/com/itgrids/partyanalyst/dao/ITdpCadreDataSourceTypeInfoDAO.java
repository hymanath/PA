package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreDataSourceTypeInfo;

public interface ITdpCadreDataSourceTypeInfoDAO extends GenericDao<TdpCadreDataSourceTypeInfo, Long>{
	
	public int deleteAllRecords();
	
	public List<Object[]> getTdpCadreCountsByDataDourceType(Date date);
	public List<Object[]> getRenewalTdpCadreCountsByDataDourceType(Date date);
	public List<Object[]> getTdpCadreRecordsCountByPartyOffice(Date date);
	public List<Object[]> getRenewalTdpCadreRecordsCountByPartyOffice(Date date);
}
