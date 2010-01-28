package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserImpDatesDAO;
import com.itgrids.partyanalyst.model.UserImpDate;
/**
 * 
 * @author Narender Akula
 *
 */
public class UserImpDatesDAO extends GenericDaoHibernate<UserImpDate, Long> implements IUserImpDatesDAO {

	private final static Logger log = Logger.getLogger(UserImpDatesDAO.class);
	
	public UserImpDatesDAO() {
		super(UserImpDate.class);
	}

	@SuppressWarnings("unchecked")
	public List<UserImpDate> findByUserId(Long userID, Calendar inputDate) {
		log.debug("UserImpDatesDAO.findByuser() ...Start");
		Object[] params = {userID, inputDate.getTime(),"NO"};
		List<UserImpDate> result = getHibernateTemplate().find(" from UserImpDate model where model.user.registrationId=? and ? <= model.tillDate and model.isDeleted=?",params);// and " +
					//"Month(model.effectiveDate) - " + currentMonth + " in (0,1)" , userID);
		log.debug("UserImpDatesDAO.findByuser() result.size()"+result.size());
		return  result;
	}
}
