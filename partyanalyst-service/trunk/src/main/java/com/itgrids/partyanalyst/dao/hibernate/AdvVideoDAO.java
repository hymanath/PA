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
	public List<?> getPreviousTrendsData(Long tdpPartyId ,Long incPartyId )
	{
		        getSession().createSQLQuery("select election_year,total_votes,total_votes_polled,missing_votes,p.short_name,p.party_id,cr1.votes_earned,concat(round(( cr1.votes_earned/total_votes_polled * 100 ),2),'%') AS tdppercentage,1.short_name,p1.party_id,cr2.votes_earned,concat(round(( cr2.votes_earned/total_votes_polled * 100 ),2),'%') AS incpercentage,'others',sum(cr3.votes_earned),rejected_votes from  constituency_election ce join election e on  ce.election_id=e.election_id join constituency_election_result cr on cr.consti_elec_id= ce.consti_elec_id join nomination n on ce.consti_elec_id=n.consti_elec_id join nomination n1 on ce.consti_elec_id=n1.consti_elec_id join nomination n2 on ce.consti_elec_id=n2.consti_elec_id join party p on p.party_id=n.party_id  join candidate_result cr1 on n.nomination_id=cr1.nomination_id join party p1 on p1.party_id=n1.party_id join candidate_result cr2 on n1.nomination_id=cr2.nomination_id    join party p2 on p2.party_id=n2.party_id join candidate_result cr3 on n2.nomination_id=cr3.nomination_id  join constituency c on ce.constituency_id=c.constituency_id where e.election_scope_id=2 and ce.constituency_id=232 and p.party_id =872 and p1.party_id=362  and p2.party_id not in(662,362,872)  group by election_year,sub_type  order by election_year desc ");
	return null;
	}
	
	
}
