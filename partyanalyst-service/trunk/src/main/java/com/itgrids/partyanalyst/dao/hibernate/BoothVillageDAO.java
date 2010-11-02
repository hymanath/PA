package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothVillageDAO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothVillage;

public class BoothVillageDAO extends GenericDaoHibernate<BoothVillage, Long>  implements IBoothVillageDAO{

	public BoothVillageDAO() {
		super(BoothVillage.class);		
	}
	
	@SuppressWarnings("unchecked")
	public List getVillagesForABoothInAConstituency(String constituencyName, Long partNo) {
		Object[] params = {constituencyName, partNo};
		return getHibernateTemplate().find("select model.localityIde,model.boothVillageId from BoothVillage model where " +
				" model.acName = ? and model.partNo = ?",params);
	}
	
}
