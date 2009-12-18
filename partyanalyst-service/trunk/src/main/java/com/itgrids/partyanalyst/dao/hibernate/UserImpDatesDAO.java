package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserImpDatesDAO;
import com.itgrids.partyanalyst.model.UserImpDate;
/**
 * 
 * @author Narender Akula
 *
 */
public class UserImpDatesDAO extends GenericDaoHibernate<UserImpDate, Long> implements IUserImpDatesDAO {

	public UserImpDatesDAO() {
		super(UserImpDate.class);
	}

	public List<UserImpDate> findByUsedrId(Long userID) {
		//jan-0, feb-1, mar-2.... dec-11
		Calendar calendar = Calendar.getInstance();
		int currentMonth = calendar.get(Calendar.MONTH);
		
		return getHibernateTemplate().find(" from UserImpDate model where model.user.registrationId=? and " +
				calendar.getTime()+ " <= model.tillDate and " +
				"Month(model.impDate) - " + currentMonth + " in (0,1)" , userID);
	}
}
