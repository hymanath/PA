/**
 * 
 */
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfo;
import com.itgrids.partyanalyst.model.TdpCadreUserHourRegInfo;

/**
 * @author sys
 *
 */
public class TdpCadreUserHourRegInfoDAO  extends GenericDaoHibernate<TdpCadreUserHourRegInfo, Long> implements ITdpCadreUserHourRegInfo{

	public TdpCadreUserHourRegInfoDAO() {
		super(TdpCadreUserHourRegInfo.class);
	}
	

}
