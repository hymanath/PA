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
	public static void main(String[] sr) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();/*cal.add(Calendar.DATE, 2);*/cal.set(Calendar.YEAR, 2010);
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		System.out.println(cal.getTime());
		Calendar cal1 = Calendar.getInstance(); cal1.add(Calendar.MONTH, 2);
		Date date = new Date();  
		Date startDate = date;
		int month = startDate.getMonth();
		int day = startDate.getDate();
		int hrs = startDate.getHours();
		int mins = startDate.getMinutes();
		int year =Calendar.getInstance().get(Calendar.YEAR);
		//Calendar cal = Calendar.getInstance();
		cal.set(2000,13,32);
		System.out.println("Narender");
		//date.setYear(1);
		/*
		System.out.println(date);
		cal.add(Calendar.DATE, 30);
		System.out.println(sdf.format(cal.getTime()));

		Date d = sdf.parse("24-12-2003 12:30:44");
		System.out.println(sdf.format(d));*/
	}
}
