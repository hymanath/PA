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
	public List<Long> getAllParliamentMandalByAllLevels(List<Long> locationValues,Long loactionTypeId) {
		StringBuilder sb= new StringBuilder();
		sb.append("select distinct model.delimitationConstituency.constituency.constituencyId from DelimitationConstituencyMandalDetails model " );
		if(loactionTypeId != null && loactionTypeId.longValue() == 6l){
			sb.append(" ,Panchayat P " );
		}else if(loactionTypeId != null && loactionTypeId.longValue() == 7l){
			sb.append(" ,LocalElectionBody LEB " );
		}
		if(locationValues != null && locationValues.size()>0){
		if(loactionTypeId != null && loactionTypeId.longValue() == 5l){
			sb.append(" where  model.tehsil.tehsilId in(:locationValues) and " );
		}else if(loactionTypeId != null && loactionTypeId.longValue() == 6l){
			sb.append(" where  model.tehsil.tehsilId=P.tehsil.tehsilId  and P.panchayatId in(:locationValues) and ");
		}else if(loactionTypeId != null && loactionTypeId.longValue() == 7l){
			sb.append(" where  model.tehsil.tehsilId=LEB.tehsil.tehsilId  and LEB.localElectionBodyId in(:locationValues) and ");
		}
		}
		sb.append("  model.delimitationConstituency.year =2009 " );
		Query query = getSession().createQuery(sb.toString());
		if(locationValues != null && locationValues.size()>0){
		if(loactionTypeId != null && loactionTypeId.longValue()>0l){
			query.setParameterList("locationValues", locationValues);
		}
		}
	 return query.list();
	}
}
