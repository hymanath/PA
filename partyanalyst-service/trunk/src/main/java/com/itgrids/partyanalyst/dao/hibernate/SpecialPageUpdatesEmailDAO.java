package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.ISpecialPageUpdatesEmailDAO;
import com.itgrids.partyanalyst.model.SpecialPageUpdatesEmail;
import com.itgrids.partyanalyst.utils.IConstants;

public class SpecialPageUpdatesEmailDAO extends GenericDaoHibernate<SpecialPageUpdatesEmail,Long> implements ISpecialPageUpdatesEmailDAO{

	public SpecialPageUpdatesEmailDAO()
	{
	   super(SpecialPageUpdatesEmail.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getSpecialPageUpdatesEmail(String emailId, Long specialPageId) 
	{
		Object[] params = {specialPageId,emailId};
		return getHibernateTemplate().find("select model from SpecialPageUpdatesEmail model where model.specialPage.specialPageId = ? and model.email = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpecialPageUpdatesEmail> getAllSubscriberDetails() 
	{
		return getHibernateTemplate().find("select model from SpecialPageUpdatesEmail model where model.unsubscribed = ?",IConstants.FALSE);
	}
	
}
