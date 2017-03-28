package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreHourRegInfo;

public interface ITdpCadreHourRegInfoDAO extends GenericDao<TdpCadreHourRegInfo, Long>{
	
	public List<Object[]> getDateHourWiseTdpCadreCount(Date date , Long stateId);
	public int deleteAllRecords(Date fromDate);
	public int insertCadreDataHourWiseToday();
	public int insertCadreDataHourWiseOverall();
	public List<Object[]> getHourWiseReport(Long stateId, Date surveyDate);
	public List<Object[]> getHourWiseCombineReport(Date surveyDate);
}
