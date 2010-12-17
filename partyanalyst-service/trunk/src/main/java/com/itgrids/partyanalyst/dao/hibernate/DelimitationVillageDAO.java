package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationVillageDAO;
import com.itgrids.partyanalyst.model.DelimitationVillage;

public class DelimitationVillageDAO extends GenericDaoHibernate<DelimitationVillage, Long> implements IDelimitationVillageDAO{
	
	public DelimitationVillageDAO()
	{
		super(DelimitationVillage.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<DelimitationVillage> findByDelimitationMandalAndTownship(Long delimitationMandalId,Long townshipId){
		
		Object[] params = {delimitationMandalId,townshipId};
		return getHibernateTemplate().find("from DelimitationVillage model where model.delimitationConstituencyMandal.dcm_id = ? and "+
				"model.township.townshipId = ?",params);
	}

}
