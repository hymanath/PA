/**
 * 
 */
package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreUserHourRegInfo;

/**
 * @author sys
 *
 */
public interface ITdpCadreUserHourRegInfo extends GenericDao<TdpCadreUserHourRegInfo, Long>{

	public List<Object[]> getTabUserLastOneHourData(Long lstHour,List<Long> tabUserInfoIds);
	public List<Object[]> getTdpCadreDataHourWiseForTabUsers(Date date,Integer hour);
	public int deleteAllRecords(Date fromDate);
	public List<Object[]> getTdpCadreDataHourWiseForTabUsersOverall();
}
