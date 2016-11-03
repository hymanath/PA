/**
 * 
 */
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreUserRecordsTimeInfo;
import com.itgrids.partyanalyst.model.TdpCadreUserRecordsTimeInfo;

/**
 * @author sys
 *
 */
public class TdpCadreUserRecordsTimeInfoDAO extends GenericDaoHibernate<TdpCadreUserRecordsTimeInfo, Long> implements ITdpCadreUserRecordsTimeInfo{

	public TdpCadreUserRecordsTimeInfoDAO() {
		super(TdpCadreUserRecordsTimeInfo.class);
	}

	

}
