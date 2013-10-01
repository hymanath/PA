package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.IAdvVideoDAO;
import com.itgrids.partyanalyst.model.AdvVideo;

public class AdvVideoDAO extends GenericDaoHibernate<AdvVideo, Long> implements IAdvVideoDAO{

	public AdvVideoDAO() {
		super(AdvVideo.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This DAO is used for getting the top videos for displayinfg based on start index and max index
	 * @param int startIndex
	 * @param int maxIndex
	 * @return  List<AdvVideo>
	 * @date 25-09-2013
	 * @author Prasad Thiragabathina
	 */
	@SuppressWarnings("unchecked")
	public List<AdvVideo> getTopVideosForDisplaying(int startIndex,
			int maxIndex) {
		Query query = getSession().createQuery(" from AdvVideo model order by date(model.updateTime) desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	
	/**
	 * This DAO is used for getting the video for selectd video
	 * @param Long videoId
	 * @return  List<AdvVideo>
	 * @date 25-09-2013
	 * @author Prasad Thiragabathina
	 */
	@SuppressWarnings("unchecked")
	public List<AdvVideo> getSelectdVideoToDisplay(Long videoId)
	{
		return getHibernateTemplate().find(" from AdvVideo model where model.advVideoId = ? ",videoId);
	}
	
	/**
	 * This DAO is used for getting the all videos for selectd dates
	 * @param Date fromDate
	 * @param Date toDate
	 * @return List<AdvVideo>
	 * @date 26-09-2013
	 * @author Prasad Thiragabathina
	 */
	@SuppressWarnings("unchecked")
	public List<AdvVideo> getSelectedVideosForDates(Date fromDate,Date toDate)
	{
		Object[] parms = {fromDate,toDate};
		return getHibernateTemplate().find(" from AdvVideo model where " +
				" date(model.updateTime) >= ?  and " +
				" date(model.updateTime) <= ? ",parms);
	}
	

}
