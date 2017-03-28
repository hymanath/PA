package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyTownDAO;
import com.itgrids.partyanalyst.model.DelimitationConstituencyTown;

public class DelimitationConstituencyTownDAO extends GenericDaoHibernate<DelimitationConstituencyTown,Long> implements IDelimitationConstituencyTownDAO{

	public DelimitationConstituencyTownDAO(){
		super(DelimitationConstituencyTown.class);
	}
	@SuppressWarnings("unchecked")
	public List<DelimitationConstituencyTown> findByDelimitationConstituencyAndTownship(Long delimitationConstiId,Long townId){
		
		Object[] params = {delimitationConstiId,townId};
		return getHibernateTemplate().find("from DelimitationConstituencyTown model where model.delimitationConstituency.delimitationConstituencyID = ? and "+
				"model.township.townshipId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestTownsForAConstituency(Long constituencyId) {
		
		return getHibernateTemplate().find("select model.delimitationConstituencyTownId,model.township.townshipId, model.township.townshipName, model.isPartial " +
				" from DelimitationConstituencyTown model where model.delimitationConstituency.delimitationConstituencyID = ? "+
				" and model.township.townshipType like 'T' and model.township.greaterTown = 'N' ",constituencyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestTownsForATehsil(Long delimConstituencyId,Long tehsilId) {
		Object[] params = {delimConstituencyId,tehsilId};
		return getHibernateTemplate().find("select model.delimitationConstituencyTownId,model.township.townshipId,model.isPartial " +
				" from DelimitationConstituencyTown model where model.delimitationConstituency.delimitationConstituencyID = ? and "+
				" model.township.tehsil.tehsilId = ? and model.township.townshipType like 'T' and model.township.greaterTown = 'N' ",params);
	}
	
}
