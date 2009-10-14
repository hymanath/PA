package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothVillageCensusDAO;
import com.itgrids.partyanalyst.dao.columns.enums.BoothColumnNames;
import com.itgrids.partyanalyst.model.BoothVillageCensus;

public class BoothVillageCensusDAO extends GenericDaoHibernate<BoothVillageCensus, Long> implements IBoothVillageCensusDAO{

	public BoothVillageCensusDAO() {
		super(BoothVillageCensus.class);
	}
	
	@SuppressWarnings("unchecked")
	public BoothVillageCensus findByBoothAndCensusCode(Long boothId, Long cencusCode) {
		Object[] params = {boothId, cencusCode};
		BoothVillageCensus boothVillageCensus = null;
		List<BoothVillageCensus> list = getHibernateTemplate().find("from BoothVillageCensus model where model.booth.boothId = ? and model.villageCensusCode = ?", params);
		if(list != null && list.size() > 0){
			boothVillageCensus = list.get(0);
		}
		return boothVillageCensus;
	}
}
