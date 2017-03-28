/**
 * 
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDetailsDAO;
import com.itgrids.partyanalyst.model.DelimitationConstituencyMandalDetails;

/**
 * @author sys
 * 
 */
public class DelimitationConstituencyMandalDetailsDAO extends
		GenericDaoHibernate<DelimitationConstituencyMandalDetails, Long>
		implements IDelimitationConstituencyMandalDetailsDAO {

	public DelimitationConstituencyMandalDetailsDAO() {
		super(DelimitationConstituencyMandalDetails.class);

	}

	public List<Object[]> getConstitencyWiseTehsil(Long constituencyId) {
		Query query = getSession()
				.createQuery(
						" select distinct model.tehsil.tehsilId,model.tehsil.tehsilName from  DelimitationConstituencyMandalDetails model"
								+ " where model.delimitationConstituency.constituency.constituencyId= :constituencyId and model.delimitationConstituency.year=2009" +
								" order by model.tehsil.tehsilName ");

		query.setParameter("constituencyId", constituencyId);
		return query.list();

	}

}
