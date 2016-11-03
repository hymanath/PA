/**
 * 
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfo;
import com.itgrids.partyanalyst.model.TdpCadreUserHourRegInfo;
import com.itgrids.partyanalyst.utils.DateUtilService;

/**
 * @author sys
 *
 */
public class TdpCadreUserHourRegInfoDAO  extends GenericDaoHibernate<TdpCadreUserHourRegInfo, Long> implements ITdpCadreUserHourRegInfo{

	public TdpCadreUserHourRegInfoDAO() {
		super(TdpCadreUserHourRegInfo.class);
	}
	
public List<Object[]> getTabUserLastOneHourData(Long lstHour,List<Long> tabUserInfoIds){
	StringBuilder queryStr = new StringBuilder();
	
	queryStr.append(" select model.regCount ,model.hour ,model.cadreSurveyUserId,model.tabUserInfoId from " +
			" TdpCadreUserHourRegInfo model where model.tabUserInfoId in (:tabUserInfoIds) and date(model.surveyDate) = :today and model.hour = :lstHour " );
	
	Query query = getSession().createQuery(queryStr.toString());
	query.setParameterList("tabUserInfoIds", tabUserInfoIds);   
	query.setDate("today",new DateUtilService().getCurrentDateAndTime());
	query.setParameter("lstHour", lstHour);   
	
	return query.list();
}
}
